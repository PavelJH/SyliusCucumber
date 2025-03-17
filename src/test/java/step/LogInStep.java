package step;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.fail;

public class LogInStep {

    private final SelenideElement logInTextButton = $x("//a[@id='login-page-button']");
    private final SelenideElement logOutTextButton = $x("//a[@id='logout-button']");
    private final SelenideElement userNameField = $x("//input[@name='_username']");
    private final SelenideElement passwordField = $x("//input[@name='_password']");
    private final SelenideElement userNameText = $x("//strong[contains(text(), '@')]");
    private final SelenideElement passwordText = $x("//p/strong[preceding-sibling::text()[contains(., 'Password:')]]");
    private final SelenideElement userNameTextOnMainPage = $x("//div[@class='d-none d-lg-flex gap-2 align-items-center ps-2']//span[normalize-space()]");
    private final SelenideElement logInButton = $x("//button[@id='login-button']");
    private final SelenideElement registerHereButton = $x("//a[@id='register-here-button']");
    private final SelenideElement alertDangerText = $x("//div[@class='alert alert-danger']");

    private String name;

    private String password;

    @When("Click LogIn button")
    public void clickLogInButton() {
        logInTextButton.click();
    }

    @And("Read and write the LogIn and password data")
    public void readAndWriteTheLogInAndPasswordData() {
        name = userNameText.getText();
        password = passwordText.getText();
    }

    @And("Click LogIn button on LogIn page")
    public void clickLogInButtonOnLogInPage() {
        logInButton.click();
    }

    @And("Put {string} and {string} to fields")
    public void putAndToFields(String loginParam, String passParam) {
         userNameField.setValue(name);
         passwordField.setValue(password);
    }

    @Then("Checking correct LogIn {string}")
    public void checkingCorrectLogIn(String userNameText) {
        String userNameOnMainPage = userNameTextOnMainPage.getText();
        Assertions.assertEquals(userNameText, userNameOnMainPage, "The text does not match!");
    }


    @And("Click LogOut button and checking output")
    public void clickLogOutButtonAndCheckingOutput() {
        logOutTextButton.click();
        Assertions.assertTrue(logInTextButton.isDisplayed(), "LogIn button is not visible!");
    }


    @And("Put WrongEmail and {string} to fields")
    public void putWrongEmailAndToFields(String passParam) {
        Faker faker = new Faker();
        userNameField.setValue(faker.internet().emailAddress());
        passwordField.setValue(password);
    }

    @Then("checking Error")
    public void checkingError() {
        String alertText = alertDangerText.getText().replaceAll("\\s+", " ").trim();
        String expectedText = "Error Invalid credentials.";

        Assertions.assertEquals(expectedText, alertText, "the text does not match");
    }

    @And("Put {string} and wrongPassword to fields")
    public void putAndWrongPasswordToFields(String emailParam) {
        Faker faker = new Faker();
        userNameField.setValue(name);
        passwordField.setValue(faker.internet().password());
    }

    @And("Click Register here button")
    public void clickRegisterHereButton() {
        registerHereButton.click();
    }


}


