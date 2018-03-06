package WeKanTest;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProp extends BasePage{

    static Properties prop;
    static String fileName = "TestDataConfig.properties";
    static String excelFileName = "TestDataConfig.xlsx";
    static String filelocation = "src\\test\\Resources\\Properties\\";
    static FileInputStream input;
    static String data;

    //Fetching data from TestDataConfig.properties file
    public String getProperty(String key) {
        prop = new Properties();
        try
        {
            input = new FileInputStream(filelocation + fileName);
            prop.load(input);
            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    //Fetching data from TestDataConfig.xls file
    public String getExcelProperty(int sheetNumber, int row, int column){

        try
        {
            File src = new File(filelocation + excelFileName);
            input = new FileInputStream(src);

            XSSFWorkbook wb= new XSSFWorkbook(input);

            XSSFSheet sheet1 = wb.getSheetAt(sheetNumber);

            DataFormatter formatter = new DataFormatter();

//            data = sheet1.getRow(row).getCell(column).getStringCellValue();
          data = formatter.formatCellValue(sheet1.getRow(row).getCell(column));

        } catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

}
