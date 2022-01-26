package projects.functions.chotot._common.api;

import static api.configuration.DataConfig.newUserPhone;
import static api.feature.register.Register.createNewRandomUser;

public class CM_API_CreateNewUser {
    public static void createNewAccount(boolean doResetAccountAfterRun) {
        // Get old phone
        String oldPhone = "";
        if (!newUserPhone.isEmpty()) oldPhone = newUserPhone;

        // Create new user
        createNewRandomUser();

        // Reset to oldPhone
        if (doResetAccountAfterRun && !oldPhone.isEmpty()) {
            newUserPhone = oldPhone;
        }
    }
}
