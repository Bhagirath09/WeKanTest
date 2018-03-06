package WeKanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTestSuite extends BaseTest {

    static String actualSignInMessage, actualLanguage, actualInputValue, actualTitle;

    private By _error = By.className("at-error");

    @Test
    public void toVerifySignInPageIsLoadedCorrectlyWithUserNameAndPasswordInputFieldSignInButtonWeblinksAndDropdownmenu(){

        //verifying "Sign in" header
        actualInputValue = driver.findElement(By.className("at-title")).getText();
        softAssert.assertTrue(actualInputValue.contains("Sign In"), "Sign in page is not loaded");

        //verifying "UsernameEmail" input field is there
        actualInputValue = weKanSignInPage.userNameOrEmailInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Username"), "UsernameEmail input field is not present");

        //verifying "Password" input field is there
        actualInputValue = weKanSignInPage.passwordInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Password"), "Password input field is not present");

        //verifying "Sign In" button is working
        boolean signInButton = driver.findElement(By.id("at-btn")).isEnabled();
        softAssert.assertTrue(signInButton, "Sign In button is not working");

        //verifying "Forgot your password" link is present and  it is valid
        softAssert.assertTrue(weKanSignInPage.linkIsPresentOnPage("Forgot your password?"), "Forgot Your Password Link is not present or not working");

        //verifying "Register" link is present and  it is valid
        softAssert.assertTrue(weKanSignInPage.linkIsPresentOnPage("Register"), "Register Link is not present or not working");

        //verify "Language Drop Down Menu" is present
        boolean button = driver.findElement(By.xpath("//div[@class=\"at-form-lang\"]/select")).isEnabled();
        softAssert.assertTrue(button, "Language Dropdown Menu button is not working");
        softAssert.assertAll();
    }


    @Test(priority = 1)
    //testing with valid username & valid password
    public void toVerifyUserShouldBeAbleToLogInwithValidUserNameAndValidPassword() {

        weKanSignInPage.enterValidUserNameEmail();
        weKanSignInPage.enterValidPassword();
        weKanSignInPage.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"content\"]/div/ul/li/a/span/span")));

        actualSignInMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/div/ul/li/a/span/span")).getText();
        Assert.assertEquals(actualSignInMessage, "Welcome Board", "Login is not successfull. Welcome page is not loaded" );
    }

    @Test(priority = 2)
    //testing with blank username & blank password
    public void toVerifyUserShouldNotBeAbleToLogInWithBlankUserNameAndBlankPassword(){
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }

    @Test(priority = 3)
    //testing with blank username & invalid password
    public void toVerifyUserShouldNotBeAbleToLogInWithBlankUserNameAndInvalidPassword(){

        weKanSignInPage.enterInvalidPassword();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }

    @Test(priority = 4)
    //testing with blank username & valid password
    public void toVerifyUserShouldNotBeAbleToLogInWithBlankUserNameAndValidPassword(){

        weKanSignInPage.enterValidPassword();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }

    @Test(priority = 5)
    //testing with invalid username & blank password
    public void toVerifyUserShouldNotBeAbleToLogInWithInvalidUserNameAndBlankPassword(){

        weKanSignInPage.enterInvalidUserNameEmail();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }

    @Test(priority = 6)
    //testing with invalid username &  invalid password
    public void toVerifyUserShouldNotBeAbleToLogInWithInvalidUserNameAndInvalidPassword(){

        weKanSignInPage.enterInvalidUserNameEmail();
        weKanSignInPage.enterInvalidPassword();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Internal server error", "User might log in with invalid credential");
    }

    @Test(priority = 7)
    //testing with invalid username & valid password
    public void toVerifyUserShouldNotBeAbleToLogInWithInvalidUserNameAndValidPassword(){

        weKanSignInPage.enterInvalidUserNameEmail();
        weKanSignInPage.enterValidPassword();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Internal server error", "User might log in with invalid credential");
    }

    @Test(priority = 8)
    //testing with valid username & blank password
    public void toVerifyUserShouldNotBeAbleToLogInWithValidUserNameAndBlankPassword(){

        weKanSignInPage.enterValidUserNameEmail();
        weKanSignInPage.clickSignInButton();
        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }

    @Test(priority = 9)
    //testing with valid username & invalid password
    public void toVerifyUserShouldNotBeAbleToLogInWithValidUserNameAndInvalidPassword(){

        weKanSignInPage.enterValidUserNameEmail();
        weKanSignInPage.enterInvalidPassword();
        weKanSignInPage.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error));

        actualSignInMessage = driver.findElement(_error).getText();
        Assert.assertEquals(actualSignInMessage, "Login forbidden", "User might log in with invalid credential");
    }


    @Test
    public void toVerifyLanguageIsChangingAccordingToSelectionMadeFromLanguageDropdownMenu() {

        //select language from dropdown
        weKanSignInPage.selectLanguageFromDropDown(localeReader.languageSignInPage);

        //getting value from property file
        actualLanguage = localeReader.getString("signin","signinpage.header");
        Assert.assertEquals(actualLanguage, "登录", "Language is not as per selection");

    }

    //go to Forgot Password page from sign in page by clicking Forgot Password link
    @Test
    public void toVerifyUserShouldBeAbleToNavigateToForgotPasswordPageWhenClickOnForgotPasswordLink(){

        weKanSignInPage.clickOnForgotPasswordLink();
        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Reset your password"), "Forgot Password page is not loaded");
    }

    //go to SignUp page from sign in page by clicking Register link
    @Test
    public void toVerifyUserShouldBeAbleToNavigateToSignUpPageWhenClickOnRegisterLink(){

        weKanSignInPage.clickOnRegisterLink();
        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Create an Account"), "Sign up page is not loaded");
    }




}
