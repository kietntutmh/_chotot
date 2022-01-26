package com.vn.chotot.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class FileHelper {

    public static Properties getProperties(String propertyFileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input =
                    FileHelper.class.getClassLoader().getResourceAsStream(propertyFileName + ".properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (input != null) {
            try {
                input.close();
                return prop;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeContentToFile(String filePath, String content) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print(content);
        writer.close();
    }

}
