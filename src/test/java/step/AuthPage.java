package step;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.fail;

public class AuthPage {
    @Description("Registration Page")
    private final SelenideElement firstNameField = $x("//input[@id='sylius_shop_customer_registration_firstName']");
    private final SelenideElement lastNameField = $x("//input[@id='sylius_shop_customer_registration_lastName']");
    private final SelenideElement emailField = $x("//input[@id='sylius_shop_customer_registration_email']");
    private final SelenideElement phoneField = $x("//input[@id='sylius_shop_customer_registration_phoneNumber']");
    private final SelenideElement subscribeNewsLetterCheckBox = $x("//input[@id='sylius_shop_customer_registration_subscribedToNewsletter']");
    private final SelenideElement passwordRegField = $x("//input[@id='sylius_shop_customer_registration_user_plainPassword_first']");
    private final SelenideElement verificationPasswordRegField = $x("//input[@id='sylius_shop_customer_registration_user_plainPassword_second']");
    private final SelenideElement newCustomerText = $x("//h1[@class='h2 mb-']");
    private final SelenideElement createAccountButton = $x("//button[@id='register-button']");
    private final SelenideElement alertUsed = $x("//div[@class='invalid-feedback d-block']");
    private final SelenideElement alertSuccessRegText = $x("//body/div[1]/div[4]/div[1]");

    @And("Put firstName, LastName, Email, PhoneNumber, password, VerificationPassword")
    public void putFirstNameLastNameEmailPhoneNumberPasswordVerificationPassword() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String password = faker.internet().password();;

        newCustomerText.isDisplayed();
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        emailField.setValue(email);
        if (alertUsed.isDisplayed()) {
            fail("User already exist");
        }
        phoneField.setValue(phoneNumber);
        subscribeNewsLetterCheckBox.click();
        passwordRegField.setValue(password);
        passwordRegField.pressTab();
//        // Прокручиваем до поля подтверждения пароля, чтобы оно стало видимым
//        verificationPasswordField.scrollIntoView(true);
        verificationPasswordRegField.setValue(password);
        newCustomerText.click();
        verificationPasswordRegField.pressTab(); // убирает фокус, вызывая событие blur
        sleep(300);
        if (alertUsed.isDisplayed()) {
            fail("The entered passwords don't match");
        }
    }

    @And("Click Create an account")
    public void clickCreateAnAccount() {
        createAccountButton.click();

    }

    @Then("checking Success status")
    public void checkingSuccessStatus() {
        String alertSuccessText = alertSuccessRegText.getText();
        String expectedSuccessText = "Success\n" +
                "Thank you for registering.";
        Assertions.assertEquals(expectedSuccessText, alertSuccessText, "the text does not match");

    }
}
