package framework;
 
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
 
 
 
 
public class TestDataReaderFromCsvFile
{
    private String scenario;
    private String typeC;
    private String promotionType;
    private String partNumber1;
    private String shortDescription;
    private String appliedDescription;
    private String abbrivatedDescription;
    private String eligibleDescription;
    private String quantity;
    private String filePath; 
    private String line;
    private String firstLine;
    private String[] header;
    private String[] promotionDetails;
    private String splitBy;
    private int maxLength;
    private int i;
   
    
                public TestDataReaderFromCsvFile(){
                                                               
                }
               
    public TestDataReaderFromCsvFile(String typeCandScenario)
    {
                PropertiesReader readFromProperty = new PropertiesReader();
    //        filePath = readFromProperty.getTestDataFilePath();          
        scenario = typeCandScenario;
        BufferedReader br = null;
        splitBy = ",";
        typeC = null;
       
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                         Reading data from csv file                   ");
                System.out.println("----------------------------------------------------------------------");
       
                try {
          br = new BufferedReader(new FileReader(filePath));
          firstLine = br.readLine();
          header = firstLine.split(splitBy);
        
          while ((line = br.readLine()) != null){
                  promotionDetails = line.split(splitBy);
                                  
                  if(scenario.equals(promotionDetails[0])){
                                  maxLength = promotionDetails.length;
                                  for (i=0; i<maxLength; i++){
                                                  if("typeC".equals(header[i])){
                                                                  typeC = promotionDetails[i];
                                                                  System.out.println("typeC= "+ typeC);
                                                  }else if("promotionType".equals(header[i])){
                                                                  promotionType = promotionDetails[i];
                                                                  System.out.println("promotionType= "+ promotionType);
                                                  }else if("partNumber1".equals(header[i])){
                                                                  partNumber1 = promotionDetails[i];
                                                                  System.out.println("partNumber1= "+ partNumber1);
                                                  }else if("shortDescription".equals(header[i])){
                                                                  shortDescription = promotionDetails[i];
                                                                  System.out.println("shortDescription= "+ shortDescription);
                                                  }else if("appliedDescription".equals(header[i])){
                                                                  appliedDescription = promotionDetails[i];
                                                                  System.out.println("appliedDescription= "+ appliedDescription);
                                                  }else if("eligibleDescription".equals(header[i])){
                                                                  eligibleDescription = promotionDetails[i];
                                                                  System.out.println("eligibleDescription= "+ eligibleDescription);
                                                  }else if("quantity".equals(header[i])){
                                                                  quantity = promotionDetails[i];
                                                                  System.out.println("quantity= "+ quantity);
                                                  }                                                             
                                  }
                  }             
                               
               
                  
          }
                  
      //2. Open a sheet
      
         
        
    } catch (FileNotFoundException e) { 
     e.printStackTrace(); 
    } catch (IOException e) { 
     e.printStackTrace(); 
    } finally { 
     if (br != null) { 
      try { 
       br.close(); 
      } catch (IOException e) { 
       e.printStackTrace(); 
      }      
     }
    }
}
 
   
public String getPartNumner1(){
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
               
public String getQuantity(){
                return quantity;
}
 
               
}
