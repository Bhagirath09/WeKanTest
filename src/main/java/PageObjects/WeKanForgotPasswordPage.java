package PageObjects;

import WeKanTest.LoadProp;
import WeKanTest.Utils;
import org.openqa.selenium.By;

public class WeKanForgotPasswordPage extends Utils {

    LoadProp loadProp = new LoadProp();


    By _email = By.id("at-field-email");
    By _register = By.linkText("Register");
    By _emailResetLinkButton = By.id("at-btn");


    private String validEmail = loadProp.getProperty("validEmailSignUpTest");
    private String invalidEmail = loadProp.getProperty("invalidEmailSignUpTest");

    public void enterExistingEmailUsedForSignUp(){enterText(_email, validEmail);}
    public void enterInvalidEmail(){enterText(_email, invalidEmail);}
    public void clickRegister(){click(_register);}
    public void clickEmailResetLinkButton(){click(_emailResetLinkButton);}

    public void createValidEmailForgotPassword(){createAndEnterNewEmail(_email);}

    public String emailInputFieldIsPresent(){
        String inputValue = driver.findElement(_email).getAttribute("placeholder");
        return inputValue;
    }

}
