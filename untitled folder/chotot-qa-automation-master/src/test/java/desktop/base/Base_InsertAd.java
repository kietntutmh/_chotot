package desktop.base;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import static api.feature.profile.UpdateProfile.getAccountID;
import static api.feature.register.Register.createNewRandomUser;
import static api.utils.CheckAccessToken.setAccessToken;
import static com.vn.chotot.configuration.Constant.CREATE_NEW_USER;

public class Base_InsertAd extends BaseTest {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    public void setupAccountWithVersionBOfBump() {
        if (CREATE_NEW_USER) {
            log.info("\n----- Create the specific user for insert ads");
            while (true) {
                createNewRandomUser();
                String version = checkBumpVersionByAccount();
                log.info("\n----- The current version of Bump: " + version);
                if (version.equalsIgnoreCase("B")) {
                    break;
                }
            }
        }
    }

//    @BeforeMethod
    public void getNewAccessTokenForUser() {
        // Set access token again to prevent token expired
        setAccessToken();
    }

    public String checkBumpVersionByAccount() {
        String accountID = getAccountID();
        int sum = 0;
        int temp;
        for (int i = 0; i < accountID.length(); i++) {
            temp = Character.getNumericValue(accountID.charAt(i));
            sum += temp;
        }
        int remainder = sum % 3;
        switch (remainder) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
        }
        return null;
    }
}
