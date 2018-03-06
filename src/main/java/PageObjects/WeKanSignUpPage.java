package PageObjects;

import WeKanTest.LoadProp;
import WeKanTest.Utils;
import org.openqa.selenium.By;

public class WeKanSignUpPage extends Utils {

    LoadProp loadProp = new LoadProp();

    By _userName = By.id("at-field-username");
    By _password = By.id("at-field-password");
    By _email = By.id("at-field-email");
    By _signIn = By.linkText("sign in");
    By _registerButton = By.id("at-btn");

    private String validUserName = loadProp.getProperty("validUserNameSignUpTest");
    private String invalidLongUserName = loadProp.getProperty("invalidLongUserNameSignUpTest");
    private String invalidShortUserName = loadProp.getProperty("invalidShortUserNameSignUpTest");
    private String validEmail = loadProp.getProperty("validEmailSignUpTest");
    private String invalidEmail = loadProp.getProperty("invalidEmailSignUpTest");
    private String validPassword = loadProp.getProperty("validPasswordSignUpTest");
    private String invalidPassword = loadProp.getProperty("invalidPasswordSignUpTest");


    public void createValidUserNameSignUp(){createAndEnterNewUsername(_userName);}
    public void createValidEmailSignUp(){createAndEnterNewEmail(_email);}

    public void enterValidUserNameSignUp(){enterText(_userName, validUserName);}
    public void enterInvalidLongUserNameSignUp(){enterText(_userName, invalidLongUserName);}
    public void enterInvalidShortUserNameSignUp(){enterText(_userName, invalidShortUserName);}
    public void enterValidEmailSignUp(){enterText(_email, validEmail);}
    public void enterInvalidEmailSignUp(){enterText(_email, invalidEmail);}
    public void enterValidPasswordSignUp(){enterText(_password, validPassword);}
    public void enterInvalidPasswordSignUp(){enterText(_password, invalidPassword);}
    public void enterSameUserNameSignUp(){enterText(_userName, validUserName);}
    public void enterSameEmailSignUp(){enterText(_email, validEmail);}
    public void clickRegisterInButtonSignUp(){click(_registerButton);}
    public void clickSignInSignUp(){click(_signIn);}


    public String userNameInputFieldIsPresent(){
        String inputValue = driver.findElement(_userName).getAttribute("placeholder");
        return inputValue;
    }

    public String emailInputFieldIsPresent(){
        String inputValue = driver.findElement(_email).getAttribute("placeholder");
        return inputValue;
    }

    public String passwordInputFieldIsPresent(){
        String inputValue = driver.findElement(_password).getAttribute("placeholder");
        return inputValue;
    }

}
