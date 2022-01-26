package projects.steps.chotot.api.supportQAManual;

import io.cucumber.java.en.Given;
import projects.functions.chotot.supportQAManual.QA_CS_Functions;

import java.util.*;

import static api.configuration.DataConfig.newUserPhone;

public class QA_CS_Steps extends QA_CS_Functions {
    @Given("Support to create a new user")
    public void support_to_create_a_new_user() {
        csSupport_CreateUser();
    }

    @Given("Top up Dong Tot")
    public void top_up_dong_tot(){
        topupDongTot();
    }

    @Given("Support to create a list of {int} new user")
    public void support_to_create_a_list_of_new_user(int numberOfUser) {
        List<String> listPhone = new ArrayList<String>();
        for (int i = 0; i < numberOfUser; i++) {
            csSupport_CreateUser();
            listPhone.add(newUserPhone);
        }
        for (String ListPhone : listPhone) {
            System.out.println(ListPhone);
        }
    }
}
