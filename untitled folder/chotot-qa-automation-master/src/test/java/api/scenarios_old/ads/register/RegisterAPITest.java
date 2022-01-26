package api.scenarios_old.ads.register;

import api.base.BaseAPITest;
import api.feature.register.Register;
import org.testng.annotations.Test;

public class RegisterAPITest extends BaseAPITest {
    Register register;

    private void initObjects() {
        register = new Register();
    }

    @Test(description = "UAC - Register, Verify register a new user successfully, Vu Hoang, SRE")
    public void verifyRegisterValidUsername() {
        initObjects();
        Register.createNewAccountWithDefaultPassword();
    }
}
