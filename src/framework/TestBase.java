package framework;
 
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
 
import testData.ITestDataProvider;
import testData.ITestDataProviderFactory;
import users.AppUser;
 
 
@Listeners(VerificationFailureListener.class)
public abstract class TestBase<U extends ITestDataProvider> {
 
                static {
                                try {
                                                PropertyConfigurator.configure("log4j.properties");
                                } catch (Throwable t) {
                                                System.err.println("Log4j could not initialize");
                                                t.printStackTrace();
                                }
 
                }
 
                protected AppUser user;
                protected PageStore pageStore;
                protected U testDataProvider;
               
//            protected ScenarioContext scenarioContext = new ScenarioContext();
               
               
 
                private Logger logger = Logger.getLogger(TestBase.class);
 
                @BeforeClass(alwaysRun = true)
                public void suiteSetup() {
                                testDataProvider = getTestDataProvider();
 
                }
 
                @SuppressWarnings("unchecked")
                private U getTestDataProvider() {
 
                                // Find the test data provider factory
                                PropertiesReader propertiesReader = new PropertiesReader();
                                String team = propertiesReader.getTeam();
                                String testDataFactoryName = String.format(
                                                                "com.target.testData.%s.TestDataProviderFactory", team);
                                try {
                                                Class<? extends ITestDataProviderFactory> testDataFactoryClass = (Class<? extends ITestDataProviderFactory>) Class
                                                                                .forName(testDataFactoryName);
                                                ITestDataProviderFactory testDataProviderFactory = testDataFactoryClass
                                                                                .newInstance();
                                                // Get the test data provider
                                                return (U) testDataProviderFactory.create();
                                } catch (InstantiationException e) {
                                                logger.fatal("Looks like " + testDataFactoryName
                                                                                + " is an interface, or is abstract", e);
                                } catch (IllegalAccessException e) {
                                                logger.fatal("Looks like " + testDataFactoryName
                                                                                + " does not have a public no-argument constructor", e);
                                } catch (ClassNotFoundException e) {
                                                logger.fatal("Loading " + testDataFactoryName
                                                                                + " failed. Doesn't your team need a test data provider?",
                                                                                e);
                                } catch (Exception e) {
                                                logger.fatal(
                                                                                "General exception in creating test data provider from  "
                                                                                                                + testDataFactoryName, e);
                                }
                                return null;
                }
 
                @BeforeMethod(alwaysRun = true)
                public void setup() {
 
                                try {
                                                initialization();
                                                goToUrl();
                                } catch (Throwable throwable) {
                                                logger.fatal(
                                                                                "Setup failed. There may not be any point in running the tests.",
                                                                                throwable);
                                }
 
                }
 
                private void initialization() {
                                PropertiesReader propertiesReader = new PropertiesReader();
                                pageStore = new PageStore(propertiesReader);
                                user = new AppUser(pageStore);
                                logger.info("Thread id = " + Thread.currentThread().getId());
                }
 
                public WebDriver getDriver() {
                                WebDriver driver;
                                driver = pageStore.getDriver();
                                return driver;
                }
 
                protected abstract void goToUrl();
 
                @AfterMethod(alwaysRun = true)
                public void teardown(ITestResult result) {
                                logger.warn(String
                                                                .format("%s %s", result.getMethod().getMethodName(), statusString(result.getStatus())));
                                if (null == pageStore) {
                                                logger.error("Page store is null - won't be able to close the browser!");
                                                return;
                                }
                                pageStore.captureScreenshot(result, logger);
                                pageStore.kill();
                }
 
                public String statusString(int statusCode) {
                                if (statusCode == ITestResult.SUCCESS) {
                                                return "Passed";
                                }
                                if (statusCode == ITestResult.FAILURE) {
                                                return "Failed";
                                }
                                if (statusCode == ITestResult.SKIP) {
                                                return "Skipped";
                                }
                                return "Unknown status code: " + statusCode;
                }
}