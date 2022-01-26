package api.utils;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.gatewayUploadImage;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.utils.GetAccessToken.getAccessTokenOfUser;
import static com.vn.chotot.api.rest_assured.VerifyRespone.*;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class Images {
    static final Logger log = Log4jFactory.instance().createClassLogger(Images.class);
    private static int rWidth = 0;
    private static int rHeight = 0;
    private static boolean isUsingRandomImageAgain = false;

    public static String uploadImage(String imagePath, String propertyName) {
        // Post file
        Response response = RestAssure.instance().postImageFile(global_accessToken, gatewayUploadImage, imagePath);

        String statusCode = getResponseCode(response);

        // get new token when expired
        if (statusCode == "401") {
            System.out.println("Re-generate token when expired");
            setExceptionDebug("Re-generate token when expired");
            getAccessTokenOfUser(tempUserPhone, defaultPassword);
        }

        // Re-upload image
        response = RestAssure.instance().postImageFile(global_accessToken, gatewayUploadImage, imagePath);

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);

        // Get property value
        return getPropertyValue(response, propertyName);
    }

    public static Map<String, Object> getImageID(String imagePath) {
        if (getIsUploadingImage()) {
            // Check image path is single or multiple values
            String[] imagePaths = {imagePath};
            if (imagePath.contains(",")) {
                imagePaths = imagePath.split(", ");
            }

            List<String> listID = new ArrayList<>();
            // Generating random and upload this one
            if (getIsGeneratingRandomImage()) {
                if (imagePaths.length >0) {
                    String randomImagePath;
                    // Get list image_id
                    for (int i=0; i<= imagePaths.length;i++) {
                        randomImagePath = generateRandomJPGImage(240,240);
                        listID.add(uploadImage(randomImagePath, "image_id"));
                    }
                }

            } // Using input images
            else {
                // Get list image_id
                String currentDirectory = System.getProperty("user.dir");
                for (String path : imagePaths) {
                  path = currentDirectory + path;
                  listID.add(uploadImage(path, "image_id"));
                }
            }

            // Create map values
            Map<String, Object> mapData = new HashMap<>();
            for (int i = 0; i < listID.size(); i++) {
                mapData.put("image_id" + i, listID.get(i));
            }
            return mapData;
        } else {
            return getImageID_NoUploadNewImage(imagePath);
        }
    }

    public static int getImageCellIndex(List<String> listData) {
        return IntStream.range(0, listData.size())
                .filter(i -> listData.get(i).contains("/images"))
                .findFirst()
                .orElse(-1);
    }

    public static Map<String, Object> getImageID_NoUploadNewImage(String imagePath) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("image_id0", "2677521862851286347.jpg");
        mapData.put("image_id1", "2677521868394697232.jpg");
        mapData.put("image_id2", "2677521875585389899.jpg");
        return mapData;
    }

    public static String generateRandomJPGImage(int width, int height) {
        if (width < 240) width = 240;
        if (height < 240) height = 240;

        if (rWidth == 0 && rHeight == 0 && !getUsingRandomImageAgain()) {
            rWidth = (int) ((Math.random() * width) + width);
            rHeight = (int) ((Math.random() * height) + height);
        } else {
            String defaultString = System.getProperty("user.dir") + "/images/temp/randomImage.jpg";
            return defaultString;
        }

        Random rColor = new Random();
        float r = rColor.nextFloat();
        float g = rColor.nextFloat();
        float b = rColor.nextFloat();
        Color randomColor = new Color(r, g, b);

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(rWidth, rHeight, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with randomColor
        g2d.setColor(randomColor);
        g2d.fillRect((int) ((Math.random() * width) + width), (int) ((Math.random() * height) + height), rWidth, rHeight);

        // create a circle with black
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) ((Math.random() * width)), (int) ((Math.random() * height)), rWidth/2, rHeight/2);
        g2d.fillOval((int) ((Math.random() * width)), (int) ((Math.random() * height)), rWidth/2, rHeight/2);

        // create a string with yellow
        g2d.setColor(Color.YELLOW);
        long l = System.currentTimeMillis();
        String timeStamp = String.valueOf(l*l*l*l*l);
        g2d.drawString(timeStamp, (int)(Math.random()*rWidth), (int)(Math.random()*rHeight));
        String generatedString = RandomStringUtils.randomAlphabetic(300);
        g2d.drawString(generatedString, (int)(Math.random()*rWidth), (int)(Math.random()*rHeight));


        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as JPEG in temp folder
        String currentDir = System.getProperty("user.dir");
        File theDir = new File(currentDir +"/images/temp");
        if (!theDir.exists()) theDir.mkdir();
        File file = new File(theDir.getAbsolutePath() + "/randomImage.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", file);
            return file.getAbsolutePath();
        } catch (IOException e) {
            log.debug("Error in creating random image: " + e.getMessage());
        }
        return "";
    }

    public static void setUsingRandomImageAgain(boolean isUsingImageAgain) {
        isUsingRandomImageAgain = isUsingImageAgain;
    }

    public static boolean getUsingRandomImageAgain() {
        return isUsingRandomImageAgain;
    }
}
