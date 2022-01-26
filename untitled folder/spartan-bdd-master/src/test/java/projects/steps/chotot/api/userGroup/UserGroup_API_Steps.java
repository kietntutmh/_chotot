package projects.steps.chotot.api.userGroup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.userGroup.UserGroup_API_Functions;

import java.util.ArrayList;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;

public class UserGroup_API_Steps extends UserGroup_API_Functions {

    @Given("I create a new User Group")
    public void i_create_a_new_User_Group() {
        setUserGroupID(createUserGroup());
    }

    @When("I add {string} new users into my User Group")
    public void i_add_new_users_into_my_User_Group(String numberOfUser) {
        userGroupAccIDs = new ArrayList<Integer>();
        userGroupPhones = new ArrayList<String>();
        for(int i = 0; i < Integer.parseInt(numberOfUser); i++){
            getAccessTokenOfNewUser();
            userGroupAccIDs.add(Integer.parseInt(global_accountID));
            userGroupPhones.add(newUserPhone);
        }
        setUserGroupPhones(userGroupPhones);
        setUserGroupAccIDs(userGroupAccIDs);
    }

    @Then("The users should be added to the User Group")
    public void the_new_users_should_be_added_to_the_User_Group() {
        //verify API add users to usergroup
        addUserToUserGroup(getUserGroupID(), getUserGroupAccIDs());
    }

    @Then("The users should contain in the User Group")
    public void the_new_users_should_contain_in_the_User_Group() {
        //verify API get usergroup
        verifyUserInUserGroup(getUserGroupID(), getUserGroupAccIDs());
    }
}
