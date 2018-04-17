package framework;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
 
public class Verify {
               
                public static Map<ITestResult, List<Throwable>> verificationFailures = new HashMap<ITestResult, List<Throwable>>();
               
                public static List<Throwable> getExceptions() {
                                List<Throwable> errors = verificationFailures.get(Reporter.getCurrentTestResult());
                                return null != errors ? errors : new ArrayList<Throwable>();
                }
 
                public static void verifyTrue(boolean condition, String message) {
                                try {
                                                Assert.assertTrue(condition, message);
                                } catch (AssertionError e) {
                                                addVerificationFailure(e);
                                }
                }
 
                public static void addVerificationFailure(AssertionError e) {
                                List<Throwable> errors = getExceptions();
                                verificationFailures.put(Reporter.getCurrentTestResult(), errors);
                                errors.add(e);
                }
 
                public static void verifyEquals(String message,Object actual,
                                                Object expected) {
                                try {
                                                Assert.assertEquals(actual, expected, message);
                                } catch (AssertionError e) {
                                                addVerificationFailure(e);
                                }
                               
                }
 
}