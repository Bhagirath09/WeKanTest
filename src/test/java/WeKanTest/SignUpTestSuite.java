package WeKanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTestSuite extends BaseTest {

    private String actualTitle, actualInputValue, actualLanguage, actualSignInMessage1, actualSignInMessage2, actualSignInMessage3;
    private String minimumPassword = "Password: Minimum required length: 6";
    private String passwordRequired = "Password: Required Field";
    private String invalidEmailError = "Email: Invalid email";
    private String emailRequired = "Email: Required Field";
    private String longUserNameError = "Internal server error";
    private String shortUserNameError = "Username: Minimum required length: 2";
    private String usernameRequired = "Username: Required Field";
    private String usernameExist = "Username already exists.";
    private String emailExist = "Email already exists.";


    private By _content = By.xpath("//div[@id=\"content\"]/div/ul/li/a/span/span");
    private By _error1 = By.xpath("//div[@class=\"at-error\"]/p[1]");
    private By _error2 = By.xpath("//div[@class=\"at-error\"]/p[2]");
    private By _error3 = By.xpath("//div[@class=\"at-error\"]/p[3]");


    @Test
    public void toVerifyUserShouldBeAbleToNavigateToSignUpPage() {
        weKanSignInPage.clickOnRegisterLink();

        //verifying "Sign in" header
        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Create an Account"), "Sign up page is not loaded");
    }

    @Test
    public void toVerifySignUpPageIsLoadedCorrectlyWithUserNameEmailAndPasswordInputFieldRegisterButtonSignInlinkAndDropdownmenu() {

        toVerifyUserShouldBeAbleToNavigateToSignUpPage();

        //verifying "Username" input field is there
        actualInputValue = weKanSignUpPage.userNameInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Username"), "Username input field is not present");

        //verifying "Email" input field is there
        actualInputValue = weKanSignUpPage.emailInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Email"), "Email input field is not present");

        //verifying "Password" input field is there
        actualInputValue = weKanSignUpPage.passwordInputFieldIsPresent();
        softAssert.assertTrue(actualInputValue.contains("Password"), "Password input field is not present");

        //verifying "Register" button is working
        boolean registerButton = driver.findElement(By.id("at-btn")).isEnabled();
        softAssert.assertTrue(registerButton, "Register button is not working");

        //verifying "sign in" link is present and  it is valid
        softAssert.assertTrue(linkIsPresentOnPage("sign in"), "sign in Link is not present or not working");

        //verify "Language Drop Down Menu" is present
        boolean button = driver.findElement(By.xpath("//div[@class=\"at-form-lang\"]/select")).isEnabled();
        softAssert.assertTrue(button, "Language Dropdown Menu button is not working");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    //testing with valid username & valid email & valid password
    public void toVerifyUserShouldBeAbleToRegisterwithValidUserNameAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.createValidUserNameSignUp();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_content));

        actualSignInMessage1 = driver.findElement(_content).getText();
        Assert.assertEquals(actualSignInMessage1, "Welcome Board", "Registration is not successfull. Welcome page is not loaded" );
    }

    @Test(priority = 2)
    //testing with valid username & valid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameValidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterValidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 3)
    //testing with valid username & valid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameValidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterValidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, passwordRequired, "Password not entered" );
    }

    @Test(priority = 4)
    //testing with valid username & invalid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameInvalidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );
    }

    @Test(priority = 5)
    //testing with valid username & invalid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameInvalidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage2, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 6)
    //testing with valid username & invalid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameInvalidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage2, passwordRequired, "Password not entered" );
    }

    @Test(priority = 7)
    //testing with valid username & blank email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameBlankEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, emailRequired, "Email is not entered" );
    }

    @Test(priority = 8)
    //testing with valid username & blank email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameBlankEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        Assert.assertEquals(actualSignInMessage1, emailRequired, "Email is not entered" );
        Assert.assertEquals(actualSignInMessage2, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 9)
    //testing with valid username & blank email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameBlankEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidUserNameSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        Assert.assertEquals(actualSignInMessage1, emailRequired, "Email is not entered" );
        Assert.assertEquals(actualSignInMessage2, passwordRequired, "Password not entered" );
    }

    @Test(priority = 10)
    //testing with invalid username & valid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameValidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidLongUserNameSignUp();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error1));
        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, longUserNameError, "invalid username entered" );

    }


    @Test(priority = 11)
    //testing with invalid username & valid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameValidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidLongUserNameSignUp();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, minimumPassword, "Password is not valid" );

    }
    @Test(priority = 12)
    //testing with invalid username & valid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameValidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidLongUserNameSignUp();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, passwordRequired, "Password not entered" );

    }

    @Test(priority = 13)
    //testing with invalid username & invalid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameInvalidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidLongUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );

    }


    @Test(priority = 14)
    //testing with invalid username & invalid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameInvalidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidShortUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        actualSignInMessage3  = driver.findElement(_error3).getText();
        Assert.assertEquals(actualSignInMessage1, shortUserNameError, "Username is invalid" );
        Assert.assertEquals(actualSignInMessage2, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage3, passwordRequired, "Password not entered" );
    }

    @Test(priority = 15)
    //testing with invalid username & blank email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameBlankEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidShortUserNameSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        Assert.assertEquals(actualSignInMessage1, shortUserNameError, "Username is invalid" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is not entered" );
    }

    @Test(priority = 16)
    //testing with invalid username & blank email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameBlankEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidShortUserNameSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        actualSignInMessage3  = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, shortUserNameError, "Username is invalid" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is not entered" );
        Assert.assertEquals(actualSignInMessage3, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 17)
    //testing with invalid username & blank email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameBlankEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidShortUserNameSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2  = driver.findElement(_error2).getText();
        actualSignInMessage3  = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, shortUserNameError, "Username is invalid" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is not entered" );
        Assert.assertEquals(actualSignInMessage3, passwordRequired, "Password not entered" );
    }

    @Test(priority = 18)
    //testing with blank username & valid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameValidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
    }

    @Test(priority = 19)
    //testing with blank username & valid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameValidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 20)
    //testing with blank username & valid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameValidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, passwordRequired, "Password not entered" );
    }

    @Test(priority = 21)
    //testing with blank username & invalid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameInvalidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, invalidEmailError, "Email is invalid" );
    }

    @Test(priority = 22)
    //testing with blank username & invalid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameInvalidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();
        actualSignInMessage3 = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage3, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 23)
    //testing with blank username & invalid email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameInvalidEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();
        actualSignInMessage3 = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage3, passwordRequired, "Password not entered" );
    }

    @Test(priority = 24)
    //testing with blank username & blank email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameBlankEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is not entered" );
    }

    @Test(priority = 25)
    //testing with blank username & blank email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameBlankEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();
        actualSignInMessage3 = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage3, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 26)
    //testing with invalid username & invalid email & invalid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameInvalidEmailAndInvalidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidLongUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterInvalidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();

        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );
        Assert.assertEquals(actualSignInMessage2, minimumPassword, "Password is not valid" );
    }

    @Test(priority = 27)
    //testing with blank username & blank email & blank password
    public void toVerifyUserShouldNotBeAbleToRegisterwithBlankUserNameBlankEmailAndBlankPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        actualSignInMessage2 = driver.findElement(_error2).getText();
        actualSignInMessage3 = driver.findElement(_error3).getText();

        Assert.assertEquals(actualSignInMessage1, usernameRequired, "Username not entered" );
        Assert.assertEquals(actualSignInMessage2, emailRequired, "Email is not entered" );
        Assert.assertEquals(actualSignInMessage3, passwordRequired, "Password not entered" );
    }

    @Test(priority = 28)
    //testing with same username & same email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithSameUserNameSameEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterSameUserNameSignUp();
        weKanSignUpPage.enterSameEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error1));
        actualSignInMessage1 = driver.findElement(_error1).getText();

        Assert.assertEquals(actualSignInMessage1, usernameExist, "Same username entered" );
    }

    @Test(priority = 29)
    //testing with same username & valid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithSameUserNameValidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterSameUserNameSignUp();
        weKanSignUpPage.createValidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error1));
        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, usernameExist, "Same username entered" );
    }

    @Test(priority = 30)
    //testing with same username & invalid email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithSameUserNameInvalidEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterSameUserNameSignUp();
        weKanSignUpPage.enterInvalidEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, invalidEmailError, "Email is invalid" );
    }

    @Test(priority = 31)
    //testing with valid username & same email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithValidUserNameSameEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.createValidUserNameSignUp();
        weKanSignUpPage.enterSameEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_error1));
        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, emailExist, "Email already exist" );
    }

    @Test(priority = 32)
    //testing with invalid username & same email & valid password
    public void toVerifyUserShouldNotBeAbleToRegisterwithInvalidUserNameSameEmailAndValidPassword(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.enterInvalidShortUserNameSignUp();
        weKanSignUpPage.enterSameEmailSignUp();
        weKanSignUpPage.enterValidPasswordSignUp();
        weKanSignUpPage.clickRegisterInButtonSignUp();

        actualSignInMessage1 = driver.findElement(_error1).getText();
        Assert.assertEquals(actualSignInMessage1, shortUserNameError, "Username is invalid" );
    }


    @Test
    public void toVerifyLanguageIsChangingAccordingToSelectionMadeFromLanguageDropdownMenu() {

        toVerifyUserShouldBeAbleToNavigateToSignUpPage();

        //select language from dropdown
        weKanSignInPage.selectLanguageFromDropDown(localeReader.languageSignUpPage);

        //getting value from property file
        actualLanguage = localeReader.getString("signUp", "signUppage.header");
        Assert.assertEquals(actualLanguage, "Crea un Account", "Language is not as per selection");

    }

    //go to sign in page from sign up page by clicking signin link
    @Test
    public void toVerifyUserShouldBeAbleToNavigateToSignInPage(){
        toVerifyUserShouldBeAbleToNavigateToSignUpPage();
        weKanSignUpPage.clickSignInSignUp();
        actualTitle = driver.findElement(By.className("at-title")).getText();
        Assert.assertTrue(actualTitle.contains("Sign In"), "Sign In page is not loaded");
    }

}
