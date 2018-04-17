package framework;
 
import java.util.List;
 
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.Utils;
 
public class VerificationFailureListener implements IInvokedMethodListener {
               
                private Logger logger = Logger.getLogger(VerificationFailureListener.class);
 
                @Override
                public void afterInvocation(IInvokedMethod method,
                                                ITestResult testResult) {
                                if (!method.isTestMethod()) {
                                                return;
                                }
                                List<Throwable> verificationFailures = Verify.getExceptions();
                               
                                if (verificationFailures.size() > 0) {
                                                testResult.setStatus(ITestResult.FAILURE);
                                }
                               
                                if (testResult.getThrowable() != null) {
                                                verificationFailures.add(testResult.getThrowable());
                                }
                               
                                int size = verificationFailures.size();
                                if (size == 0) {
                                                return;
                                }
                               
                                String methodName = method.getTestMethod().getMethodName();
                                for (Throwable throwable : verificationFailures) {
                                                logger.error(methodName + " failed", throwable);
                                }
                               
                               
                                //if there's only one failure just set that
                                if (size == 1) {
                                                testResult.setThrowable(verificationFailures.get(0));
                                } else {
                                                //create a failure message with all failures and stack traces (except last failure)
                                                StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
                                                for (int i = 0; i < size-1; i++) {
                                                                failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":n");
                                                                Throwable t = verificationFailures.get(i);
                                                                String fullStackTrace = Utils.stackTrace(t, false)[1];
                                                                failureMessage.append(fullStackTrace).append("nn");
                                                }
 
                                                //final failure
                                                Throwable last = verificationFailures.get(size-1);
                                                failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
                                                failureMessage.append(last.toString());
 
                                                //set merged throwable
                                                Throwable merged = new Throwable(failureMessage.toString());
                                                merged.setStackTrace(last.getStackTrace());
                                                testResult.setThrowable(merged);
                                }
 
 
                }
 
                @Override
                public void beforeInvocation(IInvokedMethod method,
                                                ITestResult testResult) {
 
                }
 
}