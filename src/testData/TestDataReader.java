package testData;
 
import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
 
import framework.PropertiesReader;
 
 
 
public class TestDataReader
{
    private String scenario;
    private int rowCount;
    private int columnCount;   
    private String typeC;
    private String promotionType;
    private int partNumber1;
    private String shortDescription;
    private String appliedDescription;
    private String abbrivatedDescription;
    private String eligibleDescription;
    private int quantity;
    private String filePath; 
  
 
   
                public TestDataReader(){
                                                               
                }
               
    public TestDataReader(String typeCandScenario)
    {
                PropertiesReader readFromProperty = new PropertiesReader();
                filePath = "testData\\promotionsTestDataInput.xlsx";   
                //filePath = readFromProperty.getTestDataFilePath();
//        rowCount = readFromProperty.getexcelLastRowNumber();
//        columnCount = readFromProperty.getexcelLastColumnNumber();  
               rowCount = 2;
              columnCount = 8;
        scenario = typeCandScenario;
  //      Workbook wb = WorkbookFactory.create(new File("testData/promotionsTestDataInput.xlsx"));
        Workbook wb = null;
        try {
          wb = WorkbookFactory.create(new File(filePath));
      } catch (InvalidFormatException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
       
      //2. Open a sheet
      Sheet sheet = wb.getSheetAt(0);
      System.out.println("----------------------------------------------------------------------");
      System.out.println("                       Reading data from excel file                   ");
                  System.out.println("----------------------------------------------------------------------");
      
      //3. Get each cell by row & column number
     
      for (int i=0; i<rowCount ; i++){
                Cell cell = sheet.getRow(i).getCell(0);
                if(cell.toString().trim().equals(scenario)){
                                for (int j=0; j<columnCount; j++){
                                                Cell cellColumnHeader = sheet.getRow(0).getCell(j);
                                                Cell cellData = sheet.getRow(i).getCell(j);
                                                if ("typeC".equals(cellColumnHeader.toString().trim())){
                                                                typeC = cellData.toString();
                                                                System.out.println("typeC value is: " + typeC);
                                                }else if ("promotionType".equals(cellColumnHeader.toString().trim())){
                                                                promotionType = cellData.toString();
                                                                System.out.println("promotionType value is: " + promotionType);
                                                }else if ("partNumber1".equals(cellColumnHeader.toString().trim())){
                                                partNumber1 = (int) sheet.getRow(i).getCell(j).getNumericCellValue();                                                               
                                                                System.out.println("partNumber1 value is: " + partNumber1);
                                                }else if ("shortDescription".equals(cellColumnHeader.toString().trim())){
                                                                shortDescription = cellData.toString();
                                                                System.out.println("shortDescription value is: " + shortDescription);
                                                }else if ("appliedDescription".equals(cellColumnHeader.toString().trim())){
                                                                appliedDescription = cellData.toString();
                                                                System.out.println("appliedDescription value is: " + appliedDescription);
                                                }else if ("abbrivatedDescription".equals(cellColumnHeader.toString().trim())){
                                                                abbrivatedDescription = cellData.toString();
                                                                System.out.println("abbrivatedDescription value is: " + abbrivatedDescription);
                                                }else if ("eligibleDescription".equals(cellColumnHeader.toString().trim())){
                                                                eligibleDescription = cellData.toString();
                                                                System.out.println("eligibleDescription value is: " + eligibleDescription);
                                                }else if ("quantity".equals(cellColumnHeader.toString().trim())){
                                                                quantity = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
                                                                System.out.println("quantity value is: " + quantity);
                                                }
                                                                                                               
                                                               
                                }
                                               
                                                               
                }
               
     }
 
}
 
   
public int getPartNumner1(){
                return partNumber1;
}
 
public String getShortDescription(){
                return shortDescription;
}
               
public String getAppliedDescription(){
                return appliedDescription;
}
               
public String getAbbrivatedDescription(){
                return abbrivatedDescription;
}
               
public String getEligibleDescription(){
                return eligibleDescription;
}
               
public int getQuantity(){
                return quantity;
}
 
               
}