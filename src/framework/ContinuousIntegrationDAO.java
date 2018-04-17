package framework;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
 
public class ContinuousIntegrationDAO {
               
 
private static FileInputStream fStream=null;
private static String dbUrl;
private static String dbuserName;
private static String dbpassword;
static Connection con =null;
private static Statement stmt=null;
 
public static void main(String args[])
{
                ContinuousIntegrationDAO continuousIntegrationDAO=new ContinuousIntegrationDAO();
                continuousIntegrationDAO.setdbProperties();
                con=      continuousIntegrationDAO.dBConnection();
                try {
                                stmt = con.createStatement();
                                BufferedWriter writer=createCSV();
                                int count=getPromotionCount();
                                for(int i=0;i<count;i++)
                                {
                                                                                                int promId=getNextPromotionID();
                                                                                                int typeC=getNextPromotionTypeC(promId);
                                                                                                String promotionType=getPromotionType(typeC);
                                                                                                String includePartNumber1=getIncludePartNumber(promId);
                                                                                                String excludePartNumber=getExcludePartNumber(promId);
                                                                                                String includePartNumber2=null;
                                                                                                String includePartNumber3=null;
                                                                                                String includePartNumberY=null;
                                                                                                //String includePartNumber2=getIncludePartNumberTwo(promId);
                                                                                                String shipMethod=getShippingMethod(promId);
                                                                                                String shipState=getShippingState(promId);
                                                                                                String paymentType=getPaymentType(promId);
                                                                                                String shortDescription=getShortDescription(promId);
                                                                                                String abbreviatedDescription=getAbbreviatedDescription(promId);
                                                                                                String eligibleDescription=getEligibleDescription(promId);
                                                                                                String appliedDescription=getAppliedDescription(promId);
                                                                                                int minQuantityForPromotionEligibility=2;
                                                                                                int discountForIndividualCatalogEntries=50;
                                                                                                int discountForSubCategoryEntries=50;
                                                                                                String freeGiftPartnumber=null;
                                                                                                String inputDataToCSV=typeC+","+promotionType+","+includePartNumber1+","+excludePartNumber+","+includePartNumber2+","+includePartNumber3+","+includePartNumberY+","+shipMethod+","+shipState+","+paymentType+","+shortDescription+","+abbreviatedDescription+","+eligibleDescription+","+appliedDescription+","+minQuantityForPromotionEligibility+","+discountForIndividualCatalogEntries+","+discountForSubCategoryEntries+","+freeGiftPartnumber;                               
                                                                                                addDataToCSV(inputDataToCSV,writer);
                                }
                                writer.flush();
                                writer.close();
                               
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
               
}
 
 
 
 
 
 
 
private void setdbProperties() {
                try{
                                Properties  properties= new Properties();
                                fStream=new FileInputStream("C:\\RestRegression\\environment.properties");
                                //fStream=new FileInputStream("/apps/GUESTFEED/environment.properties");
                               properties.load(fStream);
                               dbUrl=properties.getProperty("dbUrlPA");
                               dbuserName="wcsadm";
                               dbpassword="wcsadm_123";
                               //dbuserName=EKMCUtil.decrypt("/apps/BODL/credentials/encrypt_dbu");                                     
                                //dbpassword=EKMCUtil.decrypt("/apps/BODL/credentials/encrypt_dbp");     
                               fStream.close();
                } catch(IOException e) {
                                //log.error("exception during the property file reading", e);
                                System.exit(1);
                }catch(Exception e) {
                                //log.error("exception during the setting of property file", e);
                                System.exit(1);
                }
}
private  Connection dBConnection() {
                Connection connect=null;
                try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                connect = DriverManager.getConnection(dbUrl,dbuserName,dbpassword);
                //            log.info("Connection is successful..");
                                return connect;               
                }catch (ClassNotFoundException e) {                                     
                                //log.error("Oracle JDBC Driver loding is missing will getting DB Connection", e);
                                connect=null;
                                System.exit(1);
                } catch (SQLException e) {
                                //log.error("SQLException will getting DB Connection", e);
                                connect=null;
                                System.out.println("dbUrl "+dbUrl+"dbuserName "+dbuserName+"dbpassword "+dbpassword);
                                System.out.println(e);
                                System.exit(1);
                }
                return connect;
}
 
 
 
 
public static int  getNextPromotionID()throws Exception{
    ResultSet rs = stmt.executeQuery("select px_promotion_id from px_promotion where name like '%CI_PROMO%'");
    rs.next();
    return rs.getInt(1);
  }
 
public static int  getNextPromotionTypeC(int promoID)throws Exception{
    ResultSet rs = stmt.executeQuery("select type_c from xpxpromotion where px_promotion_id="+promoID);
    rs.next();
    return rs.getInt(1);
  }
 
public static int  getPromotionCount()throws Exception{
    ResultSet rs = stmt.executeQuery("select count(*) from px_promotion where name like '%CI_PROMO'"         );
    rs.next();
    return rs.getInt(1);
  }
 
 
public static String  getPromotionType(int typeC)throws Exception{
    ResultSet rs = stmt.executeQuery("select type_desc_t  from xpxpromotypecnfg where type_c="+typeC);
    rs.next();
    return rs.getString(1);
  }
 
private static String getIncludePartNumber(int promoId) {
                ResultSet rs;
                String value=null;
                try {
                                rs = stmt.executeQuery("select value from px_elementnvp where px_element_id in(select px_element_id from px_element where px_promotion_id="+promoId+" and type='IncludeCatalogEntryIdentifier')");
                                if(rs.next())
                                value= rs.getString(1);
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                return value;          
}
 
private static String getExcludePartNumber(int promoId) {
                ResultSet rs;
                String value=null;
                try {
                                rs = stmt.executeQuery("select value from px_elementnvp where px_element_id in(select px_element_id from px_element where px_promotion_id="+promoId+" and type='ExcludeCatalogEntryIdentifier')");
                                if(rs.next())
                                value= rs.getString(1);
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                return value;
}
 
private static String getShippingMethod(int promoId) {
                ResultSet rs;
                String value=null;
                String shipModeValues = null;
                try {
                                rs = stmt.executeQuery("select value from px_elementnvp where px_element_id=(select px_element_id from px_element where (type='AnyMultiShipMode' or type='IncludeMultiShipModeIdentifier') and px_promotion_id="+promoId+")");
                                if(rs.next())
                                value= rs.getString(1);
                                if (value!=null )
                                {
                                                /* if (value.contains(","))
                                                  {
                                                                  shipModeValues = value.split(",");
                                                                  for(int i=0;i<shipModeValues.length;i++)
                                                                  {
                                                                                */
                                                ResultSet result=stmt.executeQuery("select code from shipmode where shipmode_id in ("+value+")");
                                                while (result.next())
                                                {
                                                shipModeValues=shipModeValues+result.getString(1)+" ";
                                                }
                                }
                                else
                                                return value;                    
                                 
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                return shipModeValues;
}
 
private static String getShippingState(int promoId) {
                ResultSet rs;
                String value=null;
                String shipStateValues = null;
                try {
                                rs = stmt.executeQuery("select value from px_elementnvp where px_element_id=(select px_element_id from px_element where (type='AnyShipStateMethod' or type='IncludeShipStateIdentifier') and px_promotion_id="+promoId+")");
                                if(rs.next())
                                value= rs.getString(1);
                               
                               
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                return value;
}
 
 
private static String getPaymentType(int promoId) {
                ResultSet rs;
                String value=null;
                String paymentTypeValues = null;
                try {
                                rs = stmt.executeQuery("select value from px_elementnvp where px_element_id=(select px_element_id from px_element where (type='AnyPaymentMethod' or type='IncludePaymentTypeIdentifier') and px_promotion_id="+promoId+")");
                                if(rs.next())
                                value= rs.getString(1);
                               
                               
                } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                }
                return value;
}
 
public static String  getShortDescription(int promoId)throws Exception{
    ResultSet rs = stmt.executeQuery("select shortdesc  from px_description where px_promotion_id="+promoId);
    rs.next();
    return rs.getString(1);
  }
 
public static String  getAbbreviatedDescription(int promoId)throws Exception{
    ResultSet rs = stmt.executeQuery("select abbr_desc_t  from xpxdescription where px_promotion_id="+promoId);
    rs.next();
    return rs.getString(1);
  }
 
 
 
public static String  getEligibleDescription(int promoId)throws Exception{
    ResultSet rs = stmt.executeQuery("select longdesc  from px_description where px_promotion_id="+promoId);
    rs.next();
    return rs.getString(1);
  }
 
public static String  getAppliedDescription(int promoId)throws Exception{
    ResultSet rs = stmt.executeQuery("select appl_desc_t  from xpxdescription where px_promotion_id="+promoId);
    rs.next();
    return rs.getString(1);
  }
 
 
 
public static String  getMinQuantityForPromotionEligibility(int promoId)throws Exception{
    ResultSet rs = stmt.executeQuery("select appl_desc_t  from xpxdescription where px_promotion_id="+promoId);
    rs.next();
    return rs.getString(1);
  }
 
public static BufferedWriter createCSV() throws IOException
{
                DateFormat dateFormatFile = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                String fileDateStr = dateFormatFile.format(Calendar.getInstance().getTime());
                String csvFileName = "Promotions_CI"+"_"+ fileDateStr + ".csv";
                String header="typeC,Promotiontype,IncludedPartnumber1,ExCluded partNumber2,Included Partnumber3,                Included Partnumber4, Included PartnumberY, ShippingMethod,ShipState,PaymentType,                shortDescription,AbbDesc,          EligibleDesc,AppliedDesc,Minimum Purchase condition on Quantity        Discount for subcatagory entries,       Discount for IndividulCatalog entries,     Choice of Free Gift Item(partnumber)";
                File file = new File("C:\\Users\\A041522\\Desktop\\"+csvFileName);
                if (!file.exists()) {
                                file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.write(header);
                writer.newLine();
                return writer;
               
                }
               
               
public static void addDataToCSV(String inputData,BufferedWriter writer) throws IOException
{
                writer.write(inputData);
                writer.newLine();
}
               
 
 
 
 
}