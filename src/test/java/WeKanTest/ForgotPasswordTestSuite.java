package WeKanTest;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTestSuite extends BaseTest {


    private String actualTitle, actualInputValue, actualLanguage, actualSignInMessage1;

    private String userNotFound = "User not found";
    private String invalidEmailError = "Email: Invalid email";
    private String emailSent = "Email sent";

    private By _error1 = By.xpath("//div[@class=\"at-error\"]/p[1]");


    //go to Forgot Password page from sign in page by clicking Forgot Password link
    @Test
    public void toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage(){

        weKanSignInPage.clickOnForgotPasswordLink();
        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Reset your password"), "Forgot Password page is not loaded");
    }


    @Test
    public void toVerifySignUpPageIsLoadedCorrectlyWithUserNameEmailAndPasswordInputFieldRegisterButtonSignInlinkAndDropdownmenu() {

        toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage();

        //verifying "Email" input field is there
        actualInputValue = weKanForgotPasswordPage.emailInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Email"), "Email input field is not present");

        //verifying "Email Reset Link" button is working
        boolean emailResetLinkButton = driver.findElement(By.id("at-btn")).isEnabled();
        softAssert.assertTrue(emailResetLinkButton, "Email Reset Link button is not working");

        //verifying "Register" link is present and  it is valid
        softAssert.assertTrue(linkIsPresentOnPage("Register"), "Register Link is not present or not working");

        //verify "Language Drop Down Menu" is present
        boolean button = driver.findElement(By.xpath("//div[@class=\"at-form-lang\"]/select")).isEnabled();
        softAssert.assertTrue(button, "Language Dropdown Menu button is not working");
        softAssert.assertAll();
    }

    @Test
    //user should be able to reset link with valid (Registered) email
    public void toVerifyUserShouldBeAbleToGenerateEmailResetLinkWithExistingEmail(){

        toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage();

        weKanForgotPasswordPage.enterExistingEmailUsedForSignUp();
        weKanForgotPasswordPage.clickEmailResetLinkButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("at-result")));
        actualSignInMessage1 = driver.findElement(By.className("at-result")).getText();
        Assert.assertEquals(actualSignInMessage1, emailSent, "User could not generate reset link");
    }

    @Test
    //user should not be able to reset link with non registered email
    public void toVerifyUserShouldNotBeAbleToGenerateEmailResetLinkWithNonRegisteredEmail(){

        toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage();

        weKanForgotPasswordPage.createValidEmailForgotPassword();
        weKanForgotPasswordPage.clickEmailResetLinkButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error1));

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, userNotFound, "User is able to reset link with non-registered email");
    }

    @Test
    //user should not be able to reset link with invalid email
    public void toVerifyUserShouldNotBeAbleToGenerateEmailResetLinkWithInvalidEmail(){

        toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage();

        weKanForgotPasswordPage.enterInvalidEmail();
        weKanForgotPasswordPage.clickEmailResetLinkButton();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "User is able to reset link with invalid email");
    }


    @Test
    public void toVerifyLanguageIsChangingAccordingToSelectionMadeFromLanguageDropdownMenu() {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger = report.startTest(methodName, "Verifying Language");

        toVerifyUserShouldBeAbleToNavigateToForgotPasswordPage();
        logger.log(LogStatus.INFO,"Successfully navigated to Forgot Password page ");

        //select language from dropdown
        weKanSignInPage.selectLanguageFromDropDown(localeReader.languageForgotPasswordPage);
        logger.log(LogStatus.INFO,"selecting language from Dropdown Menu");

        //getting value from property file
        actualLanguage = localeReader.getString("forgotpassword", "forgotPassword.header");
        logger.log(LogStatus.INFO,"getting value for language property file");

        Assert.assertEquals(actualLanguage, "Reinitialiser votre mot de passe", "Language is not as per selection");
        logger.log(LogStatus.INFO,"Language on page is as selected from dropdown menu");
    }

    //go to SignUp page from sign in page by clicking Register link
    @Test
    public void toVerifyUserShouldBeAbleToNavigateToSignUpPageWhenClickOnRegisterLink(){


        weKanForgotPasswordPage.clickRegister();

        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Create an Account"), "Sign up page is not loaded");
    }

}
