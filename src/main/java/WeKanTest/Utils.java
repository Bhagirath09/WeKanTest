package WeKanTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Utils extends BasePage {

    public static void click(By by) {
        driver.findElement(by).click();
    }

    public static void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //Select by Index
    public static void selectByIndex(By by, int index) {
        Select s = new Select (driver.findElement(by));
        s.selectByIndex(index);
    }

    //Select by Value
    public static void selectByValue(By by, String value) {
        Select s = new Select (driver.findElement(by));
        s.selectByValue(value);
    }

    //Select by VisibleText
    public static Select selectByVisibleText(By by, String text) {
        Select s = new Select (driver.findElement(by));
        s.selectByVisibleText(text);
        return s;
    }

    //
    public static void waitUntilClickableAndClickCommand(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public static void waitUntilVisibleAndClickCommand(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.click();
    }

    //clear text method
    public static void clearText(By by){
        driver.findElement(by).clear();
    }

    //generate new email
    public static String createAndEnterNewEmail(By by) {
        String myDate = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
        driver.findElement(by).sendKeys( myDate+"@yaho.com");
        return myDate+"@yaho.com";
    }

    //generate new username
    public static String createAndEnterNewUsername(By by) {
        String myDate = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
        driver.findElement(by).sendKeys( myDate);
        return myDate;
    }


    //provide linkText in String and make sure link is present on Page and it not empty
    public boolean linkIsPresentOnPage(String linkText){

        String url, actualText = "", actualURL = null;

        List<WebElement> links = driver.findElements(By.tagName("a"));

        int i = 0;

        for (WebElement e : links) {
            url = e.getAttribute("href");

            if(e.getText().equalsIgnoreCase(linkText)){
                actualText = e.getText();
                actualURL = url;
            }
            i++;
        }

        return  (actualText.contains(linkText) && actualURL != null);
    }

    public static String captureScreen(ITestResult result) throws IOException {

        String fileName = result.getName()+".png";
        String imagePath = "target/ScreenShots/"+fileName;

        File imgFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(imgFile, new File(imagePath));

        return "target/ScreenShots/"+fileName;

    }

}
