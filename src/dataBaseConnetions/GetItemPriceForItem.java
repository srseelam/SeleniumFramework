package dataBaseConnetions;
 
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
 
public class GetItemPriceForItem {
               
 
private static FileInputStream fStream=null;
private static String dbUrl;
private static String dbuserName;
private static String dbpassword;
static Connection con =null;
private static Statement stmt=null;
 
public static void main(String args[])
{
                GetItemPriceForItem GetItemPriceForItem=new GetItemPriceForItem();
                GetItemPriceForItem.setdbProperties();
                con = GetItemPriceForItem.dBConnection();
                try {
                                stmt = con.createStatement();
                                System.out.println("before calling function"+stmt);
                                int promotionID = getNextPromotionTypeC(10940011);                
                                System.out.println(promotionID);
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
                               //dbUrl=properties.getProperty("dbUrlPA");
                               dbUrl = "jdbc:oracle:thin:@xcsd07:1521:xcsd07";
                               dbuserName="dba_test";
                               dbpassword="dba_test";
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
                                System.out.println("connetion out"+connect);
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
 
 
 
 
public static int  getNextPromotionTypeC(int promoID)throws Exception{
    ResultSet rs = stmt.executeQuery("select type_c from xpxpromotion where px_promotion_id="+promoID);
    System.out.println(rs);
    rs.next();
    return rs.getInt(1);
  }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
public static int  getItemPrice(String partNumber)throws Exception{
    ResultSet catentryID = stmt.executeQuery("select catentry_id from catentry where partnumber='"+partNumber+"'");               
 
    return catentryID.getInt(1);
   
                               
  }
 
 
}
/*
<?xml version="1.0" encoding="UTF-8"?>
<Promotion impl="com.ibm.commerce.marketing.promotion.DefaultPromotion">
  <PromotionKey>
    <PromotionName>OTH0_testFormediatorXML1-10605502</PromotionName>
    <StoreKey>
      <DN>ou=target b2c seller organizationtarget,o=target b2c seller organization,o=extended sites seller organization,o=root organization</DN>
      <Identifier>Target</Identifier>
    </StoreKey>
    <Version>1</Version>
    <Revision>0</Revision>
  </PromotionKey>
  <PromotionGroupKey>
    <GroupName>ProductLevelPromotion</GroupName>
    <StoreKey>
      <DN>ou=target b2c seller organizationtarget,o=target b2c seller organization,o=extended sites seller organization,o=root organization</DN>
      <Identifier>Target</Identifier>
    </StoreKey>
  </PromotionGroupKey>
  <TypedNLDescription impl="com.ibm.commerce.marketing.promotion.TypedNLDescription">
    <DefaultLocale>en_US</DefaultLocale>
    <Description locale="en_US" type="admin">testFormediatorXML</Description>
  </TypedNLDescription>
  <Priority>100</Priority>
  <Exclusive>4</Exclusive>
  <ExemptPolicyList/>
  <ExplicitlyAppliedPolicyList/>
  <Status>0</Status>
  <LastUpdate>30-07-2014 08:17:32</LastUpdate>
  <LastUpdateBy>
    <CustomerKey>
      <LogonId>A039981</LogonId>
    </CustomerKey>
  </LastUpdateBy>
  <PerOrderLimit>-1</PerOrderLimit>
  <PerShopperLimit>-1</PerShopperLimit>
  <ApplicationLimit>-1</ApplicationLimit>
  <TargetSales>0</TargetSales>
  <CorrespondingRBDTypeName>TgtMultipleItemsPercentDiscount</CorrespondingRBDTypeName>
  <Schedule impl="com.ibm.commerce.marketing.promotion.schedule.PromotionSchedule">
    <DateRange impl="com.ibm.commerce.marketing.promotion.schedule.DateRangeSchedule">
      <Start inclusive="true">30-07-2014 07:00:00</Start>
      <End inclusive="true">31-12-9999 23:59:59</End>
    </DateRange>
    <TimeWithinADay impl="com.ibm.commerce.marketing.promotion.schedule.TimeRangeWithinADaySchedule">
      <Start inclusive="true">00:00:00</Start>
      <End inclusive="true">23:59:59</End>
    </TimeWithinADay>
    <Week impl="com.ibm.commerce.marketing.promotion.schedule.WeekDaySchedule">
      <WeekDay>SUNDAY</WeekDay>
      <WeekDay>MONDAY</WeekDay>
      <WeekDay>TUESDAY</WeekDay>
    </Week>
  </Schedule>
  <PromotionType>0</PromotionType>
  <PromotionCodeRequired>false</PromotionCodeRequired>
  <SkipTargetingConditionOnProperPromotionCodeEntered>false</SkipTargetingConditionOnProperPromotionCodeEntered>
  <CheckTargetingConditionAtRuntime>true</CheckTargetingConditionAtRuntime>
  <PromotionCodeCondition impl="com.ibm.commerce.marketing.promotion.condition.PromotionCodeCondition"/>
  <Targeting impl="com.ibm.commerce.marketing.promotion.condition.TargetingCondition"/>
  <CustomConditions/>
  <PurchaseCondition impl="com.ibm.commerce.marketing.promotion.condition.AlwaysFalsePurchaseCondition"/>
</Promotion>
 
 
 
 
 
 
*/