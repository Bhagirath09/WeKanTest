package WeKanTest;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LocaleReader {

    LoadProp loadProp = new LoadProp();

    String languageSignInPage = loadProp.getExcelProperty(0, 2, 1);
    String languageSignUpPage = loadProp.getExcelProperty(0, 3, 1);
    String languageForgotPasswordPage = loadProp.getExcelProperty(0, 4, 1);

    private ResourceBundle resourceBundle = null;
    private Locale locale = null;

    public String getString(String page, String code){

        if(page.equalsIgnoreCase("signup")){
            locale = new Locale(languageSignUpPage);
        }
        else if(page.equalsIgnoreCase("signin")){
            locale = new Locale(languageSignInPage);
        }
        else if(page.equalsIgnoreCase("forgotpassword")){
            locale = new Locale(languageForgotPasswordPage);
        }
        else {
            System.out.println("Wrong Page Name provided");
        }

        resourceBundle = PropertyResourceBundle.getBundle("LocaleLanguage", locale);

        String message = resourceBundle.getString(code).trim();

        return message;
    }

}
