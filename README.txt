I have created framework in below Environment 

Selenium Webdriver - 3.8.1
IntelliJ IDEA 2017.3.3
Language - Java (1.8)
Build - Maven 4
Tools - TestNg 6.10
Windows 10 - 64Bit

It is a Page Object Model. For each web page I have created class under src/main/java/PageObjects directory. 

In src/main/java there is a WeKanTest directory in which:

	- I have created BasePage where Webdriver is declared. 
	  I have extended this page to all test pages so no need to declare driver again anywhere in framework.

	- I have created BrowserSelector class for browser selection. I am using different version of driver for each web browser.
      I have included all drivers (gecko, chrome & IE) in framework for your ready reference.	
	  Browsers are latest version. Chrome 64, Firefox 57. 
	  
	- Test data is coming from property files located under test/Resources/Properties. 
	  In LoadProp class property/excel file loading configuration is there.

	- For language Localisation, I have created LocaleReader class which will retrieve value from property files 
      saved under Resource Bundle LocaleLanguage.
      Language selection control point is from excel. You can select language for each page from there.

    - All reusable methods are stored in Utils class. 	  

In src/test/java there is a WeKanTest directory in which:	
	  
	- There is a BeseTest class in which I have created all object of pages, BeforeMethod to openBrowser 
	  and AfterMethod to close browser and take screenshot if test fails.

    - I have created TestSuite pages for each web page. All the tests are written in there.
	  Run these classes and all test will be executed.
	  We can create Regression pack using Tests from these TestSuite pages.

TestNG report will be saved under test-output directory.

Screenshots will be saved under target/Screenshots

I have used ExtentReport to generate html report which looks much better compare to default TestNG report. 
I have used for one method in ForgotPasswordTestSuite. Since we need to provide logs to generate report, it will look
duplication in framework as description of comments and logs are same. It is upto individual organization preference which
report they want to use. Report is saved under target/Reports


Normally data are being read from property files. However, I have tried excel for language selection. One can go to excel file 
and enter language in column B, row 3/4/5. One can see below all languages application is supporting which looks better in excel file
then property file. For this test I have created only 5 language property file in Resource bundle.

Language assertion in some Tests will fail as actual output is not same. When driver is fetching value from property file, it displays
in junk character. However, it works okay with firefox.

 



 




	  
  


