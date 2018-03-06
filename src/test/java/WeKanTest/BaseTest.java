package WeKanTest;

import PageObjects.WeKanForgotPasswordPage;
import PageObjects.WeKanSignInPage;
import PageObjects.WeKanSignUpPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class BaseTest extends Utils {

    protected BrowserSelector browserSelector = new BrowserSelector();
    protected LoadProp loadProp = new LoadProp();
    protected WeKanSignInPage weKanSignInPage = new WeKanSignInPage();
    protected WeKanSignUpPage weKanSignUpPage = new WeKanSignUpPage();
    protected WeKanForgotPasswordPage weKanForgotPasswordPage = new WeKanForgotPasswordPage();
    protected LocaleReader localeReader = new LocaleReader();
    SoftAssert softAssert = new SoftAssert();

    static ExtentReports report = new ExtentReports("target/Reports/Extent-report.html");
    static ExtentTest logger;
    static String imagePath;
    static String methodName;


    @BeforeMethod
    public void openBrowser( ) {

        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger = report.startTest(methodName);

        String browser = loadProp.getProperty("browser");
        logger.log(LogStatus.INFO, "Selected browser is "+browser);


        //open browser
        browserSelector.openBrowser(browser);

        //navigate to homepage (URL)
        driver.get(loadProp.getProperty("testURL"));
        logger.log(LogStatus.INFO, "Website  is "+loadProp.getProperty("testURL"));

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }



    @AfterMethod
    public static void tearDown(ITestResult result)
    {
        logger.log(LogStatus.INFO,  "Executing tearDown Method");

        // Here will compare if test is failing then only it will enter into if condition
        if(ITestResult.FAILURE==result.getStatus())
        {
            logger.log(LogStatus.FAIL,  "Test case failed");

          try
            {
                imagePath = captureScreen(result);
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }

            String image = logger.addScreenCapture(imagePath);
            logger.log(LogStatus.FAIL,  "Failed", image);
            report.endTest(logger);
            report.flush();
        }

        // close application
        driver.quit();
        logger.log(LogStatus.INFO,  "Browser closed");
        report.endTest(logger);
        report.flush();
    }
}
