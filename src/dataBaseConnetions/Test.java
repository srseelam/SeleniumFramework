package dataBaseConnetions;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Test {
     private static String dbUrl;
     private static String dbuserName;
     private static String dbpassword;
     private static int returnCode = 0;
     //  private Long iPromotionId = null;
     private int iPromotionId =10940011;
       private String iFileName = "Filename";
       private Connection connection = null;
       private static Statement stmt=null;
      
       public Test(Connection con, int promotionId, String fileName) {
            this.connection=con;
            this.iPromotionId=promotionId;
            this.iFileName=fileName;
          }
      
     
       // TODO Auto-generated constructor stub
      
       
       public static void main(String args[])
       { 
                String mainJobName = "PROMO_DETAIL";
                dbUrl="jdbc:oracle:thin:@10.92.193.91:1521:XCSD00";
                dbuserName="wcsadm";
                dbpassword="wcsadm_123";                 
        Connection mainConnection = dBConnection(mainJobName);
               
                try {
                                stmt = mainConnection.createStatement();
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
 
       
       
       
//       public static void main(String[] args) {
//
//              try {
//                              stmt = con.createStatement();
//                              Long promotionId = Long.valueOf("11290735");
//                   String mainJobName = "PROMO_DETAIL";
//                   dbUrl="jdbc:oracle:thin:@10.92.193.91:1521:XCSD00";
//                   dbuserName="wcsadm";
//                   dbpassword="wcsadm_123";                 
//                        Connection mainConnection = dBConnection(mainJobName);
//                       
//
//
//              } catch (Exception e) {
//                   System.out.println("Exception in DELTA PROMO MAIN JOB.. "+ e);
//                  
//              }
//       }
 
          private static Connection dBConnection(String jobName) {
              Connection connect = null;
              try {
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  System.out.println("Get connection URL: " + dbUrl + " @" + dbuserName);
                  connect = DriverManager
                            .getConnection(dbUrl, dbuserName, dbpassword);
                  System.out.println("Connection for " + jobName + " is successful..");
                  return connect;
              } catch (ClassNotFoundException e) {
                  System.out.println("Oracle JDBC Driver loding is missing will getting DB Connection"+e);
                  connect = null;
                  returnCode = 1;
              } catch (SQLException e) {
                  System.out.println("SQLException will getting DB Connection"+e);
                  connect = null;
                  returnCode = 1;
              }
              return connect;
         }
 
          public static int  getNextPromotionTypeC(int promoID)throws Exception{
                    ResultSet rs = stmt.executeQuery("select type_c from xpxpromotion where px_promotion_id="+promoID);
                    System.out.println(rs);
                    rs.next();
                    return rs.getInt(1);
                  }
 
 
}