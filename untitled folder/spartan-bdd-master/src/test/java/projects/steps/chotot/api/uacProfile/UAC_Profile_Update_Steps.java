package projects.steps.chotot.api.uacProfile;

import io.cucumber.java.en.Then;
import projects.functions.chotot.uacProfile.UAC_Profile_Update_Functions;

import java.util.Arrays;
import java.util.List;

import static api.configuration.GatewayConfig.global_accessToken;

public class UAC_Profile_Update_Steps extends UAC_Profile_Update_Functions {
    @Then("I update my profile is successful with valid value")
    public static void iUpdateMyInformationSuccessful() {
        List<Integer> listFavorites = Arrays.asList(5010, 5020);
        setFullName("Customer Tester");
        setGenderToMale();
        setIdNumber("123456789");
        setFavorites(listFavorites);
        setBirthday("2010-02-28");
        setAddress("2/2/3 Ngô Đức Kế");
        setTaxCode("9876543210");
        setAccessToken(global_accessToken);
        verifyUpdateIsSuccessful();
    }

    @Then("I update my profile failed with name have special character")
    public void iUpdateFailedWithNameHaveSpecialCharacter() {
        setFullName("Customer Tester @");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidName();
    }

    @Then("I update my profile failed with name is phone number")
    public void iUpdateFailedWithNameIsPhoneNumber() {
        setFullName("0911319772");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidName();
    }

    @Then("I update failed with email format incorrect")
    public void iUpdateFailedWithEmail() {
        setEmail("ermail.com");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidEmail();
    }

    @Then("I update email failed with account have email before")
    public void iUpdateEmailFailedWithAccountHaveEmailBefore() {
        setEmail("testchotot@gmail.com");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithAccountHasEmail();
    }

    @Then("I update my profile failed with gender param invalid")
    public void iUpdateFailedWithGenderParamInvalid() {
        setGenderToFemale();
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidGender();
    }

    @Then("I update my profile failed with favorites param invalid")
    public void iUpdateFailedWithFavoritesParamInvalid() {
        List<Integer> favorites = Arrays.asList(5010, 5020);
        setFavorites(favorites);
        verifyUpdateProfileFailedWithInvalidFavorites();
    }

    @Then("I update my profile failed with tax code param is alphabet")
    public void iUpdateFailedWithTaxCodeParamIsAlphabet() {
        setTaxCode("AVC");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidTaxCode();
    }

    @Then("I update my profile failed with tax code param is special character")
    public void iUpdateFailedWithTaxCodeParamIsSpecialCharacter() {
        setTaxCode("@#");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidTaxCode();
    }

    @Then("I update my profile failed with tax code param is less than 10 character")
    public void iUpdateFailedWithTaxCodeParamIsLessThan10Character() {
        setTaxCode("123456789");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidTaxCode();
    }

    @Then("I update my profile failed with tax code param is more than 10 character")
    public void iUpdateFailedWithTaxCodeParamIsMoreThan10Character() {
        setTaxCode("12345678910");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidTaxCode();
    }

    @Then("I update my profile failed with address param is invalid")
    public void iUpdateFailedWithAddressParamIsInvalid() {
        setAddress("@#1");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidAddress();
    }

    @Then("I update my profile failed with birthday param is different YYYY-MM-DD format")
    public void iUpdateFailedWithBirthdayParamIsDifferentFormat() {
        setBirthday("2010/23/02");
        setAccessToken(global_accessToken);
        verifyUpdateProfileFailedWithInvalidBirthday();
    }
}
