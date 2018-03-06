package PageObjects;

import WeKanTest.LoadProp;
import WeKanTest.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WeKanSignInPage extends Utils {

    LoadProp loadProp = new LoadProp();

    By _userNameEmail = By.id("at-field-username_and_email");
    By _password = By.id("at-field-password");
    By _signInButton = By.id("at-btn");
    By _selectLanguage = By.xpath("//div[@class=\"at-form-lang\"]/select");
    By _register = By.linkText("Register");
    By _forgotPassword = By.linkText("Forgot your password?");


    private String validUserNameEmail = loadProp.getProperty("validUserNameEmail");
    private String invalidUserNameEmail = loadProp.getProperty("InvalidUserNameEmail");
    private String validPassword = loadProp.getProperty("validPassword");
    private String invalidPassword = loadProp.getProperty("invalidPassword");

    public void enterValidUserNameEmail(){enterText(_userNameEmail, validUserNameEmail);}
    public void enterInvalidUserNameEmail(){enterText(_userNameEmail, invalidUserNameEmail);}
    public void enterValidPassword(){enterText(_password, validPassword);}
    public void enterInvalidPassword(){enterText(_password, invalidPassword);}
    public void clickSignInButton(){click(_signInButton);}

    public String userNameOrEmailInputFieldIsPresent(){
        String inputValue = driver.findElement(_userNameEmail).getAttribute("placeholder");
        return inputValue;
    }

    public String passwordInputFieldIsPresent(){
        String inputValue = driver.findElement(_password).getAttribute("placeholder");
        return inputValue;
    }

    public void selectLanguageFromDropDown(String language){
        selectByValue(_selectLanguage, language);
    }

    public void clickOnRegisterLink(){
        click(_register);
    }
    public void clickOnForgotPasswordLink(){
        click(_forgotPassword);
    }

}
