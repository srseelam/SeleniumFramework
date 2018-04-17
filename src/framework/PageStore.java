package framework;
 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
 


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import pages.AbstractPage;
 
//import com.target.domainObject.Item;
//import com.target.watagutils.JettyFactory;
//import com.target.watagutils.WebAnalyticsRequest;
 
public class PageStore {
                private WebDriver driver;
                private List<Object> pages;
                private PropertiesReader propertiesReader;
                //private WebAnalyticsRequest webAnalyticsRequest;
    private int sslPort;
    private int nonSslPort;
    //private JettyFactory jettyFactory;
                public PageStore(){
                                this(new PropertiesReader());
                }
 
                public PageStore(PropertiesReader propertiesReader) {
                                this.propertiesReader = propertiesReader;
                                String driverName = "";
                               
                                if(System.getProperty("browser") != null)
                                                driverName = System.getProperty("browser");
                                else
                                                driverName = propertiesReader.getDriverName();
                               
                                driver = DriverFactory.getDriver(driverName,this);
                                pages = new ArrayList<Object>();
                                /*jettyFactory = new JettyFactory();
                                try {
                                    jettyFactory.setupInterceptor(this);
        } catch (Exception e) {
          
        }*/
                }
 
                public WebDriver getDriver() {
                                return driver;
                }
 
                public void kill() {
                                if (propertiesReader.shouldCloseBrowser()) {
                                                driver.quit();     
                                }
                                //jettyFactory.stopServer();
                                pages.clear();
                }
 
                @SuppressWarnings("unchecked")
                public <T extends AbstractPage>T getPage(Class<T> clazz) {
                                for(Object page : pages){
                                                if(page.getClass() == clazz){
                                                                return (T) page;
                                                }
                                }
                               
                                T page = PageFactory.initElements(driver, clazz);
                                Class<?> pageElementClass = getPageElementClass(clazz);
                                if (pageElementClass!= null) {
                                                Object pageElements = PageFactory.initElements(driver, pageElementClass);
                                                page.setPageElement(pageElements);
                                }
                                pages.add(page);
               
                                return page;
                               
                }
 
                private Class<?> getPageElementClass(Class<?> clazz) {
                                String className = clazz.getSimpleName();
                                String classElementName = className+"Elements";
                                String workStream = propertiesReader.getWorkStream();
                                try {
                                                return Class.forName("pageElements." +workStream + "." + classElementName);
                                } catch (ClassNotFoundException e) {
                                                return getDefaultElementsClass(classElementName);
                                }
                }
 
                private Class<?> getDefaultElementsClass(String classElementName) {
                                try {
                                                return Class.forName("pageElements."
                                                                                + classElementName);
                                } catch (ClassNotFoundException e) {
                                                return null;
                                }
                }             
               
                /**
                * @param urlType
                * @param parameters
                */
                private void getUrl(UrlType urlType, String... parameters) {
                                String url = "";
                                if(propertiesReader.getUrl().equals("mWeb"))
                                                url = System.getProperty("target_url");
                                else
                                                url = propertiesReader.getUrl();
                               
                                System.setProperty("baseURL", url);
                                StringBuilder urlBuilder = new StringBuilder(url)
                                    .append(urlType.create(parameters));                             
                                System.out.println(urlBuilder);
                                try {
                                                driver.manage().window().maximize();
                                                driver.get(urlBuilder.toString());
                                } catch (Exception e1) {
                                                // TODO Auto-generated catch block
                                                driver.manage().window().maximize();
                                                driver.get(urlBuilder.toString());
 
                                }
                                WebDriverWait wait = new WebDriverWait(driver,2);
                                try{
                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("google_image_div")));
                                }
                                catch(Exception e)
                                {
                                   
                                }
                                if (propertiesReader.getWorkStream().equals("modular")) {
                                                Cookie ck = new Cookie("tgtakalb", "SCSvTNL1");
                                                driver.manage().addCookie(ck);
                                }
                }
 
               
               
                public boolean getFFPrgFlag() {
                                if(null != propertiesReader.getFFPrgFlag() && propertiesReader.getFFPrgFlag().equalsIgnoreCase("Y")){
                                                return true;
                                }
                                return false ;
                }
               
                public void getDynamicUrl(String searchTerm) {
                                getUrl(UrlType.DYNAMIC, searchTerm);
                }
 
                public void getPDPUrl(String productString) {
                                driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
                                getUrl(UrlType.PDP, productString);
                }
               
                public void gettbaPDPUrl(String productString) {
                                getUrl(UrlType.TBA_PDP, productString);
                }
 
                public void gettbaQIUrl(String productString) {
                                getUrl(UrlType.TBA_QI, productString);
                               
                }
               
                public void getBloomReachPageUrl() {
                                driver.get(propertiesReader.getBloomReachUrl());
                }             
               
                public void getHomePageUrl() {
                                getUrl(UrlType.HOMEPAGE,"");
                               
                }             
               
                public void getRegistryGiftGiverPageUrl(String registryType) {
                                getUrl(UrlType.REGISTRY, registryType);
                                getDriver().navigate().refresh();
                }             
               
                public void getRegistryGiftGiverPageWithCrossOverItemUrl() {
                                getUrl(UrlType.REGISTRY_WITH_CROSSOVER_ITEM,"");
                                getDriver().navigate().refresh();
                }             
               
                public void getListGiftGiverPageUrl() {
                                getUrl(UrlType.LIST,"");
                                getDriver().navigate().refresh();
                }
 
               
                private String getSendTypeAttrfor(String defaultDeliveryMethod)
                {
                                if(defaultDeliveryMethod.equals("E-MAIL"))
                                                return "E";
                                else if (defaultDeliveryMethod.equals("MOBILE"))
                                                return "M";
                                else
                                                return "P";
                }
               
               
 
                public void getTrackYourOrderUrl() {
                                getUrl(UrlType.TRACK_YOUR_ORDER,"");
                }
 
                public void getCategoryPageUrl(String categoryUrl) {
                                getUrl(UrlType.CATEGORY_PAGE,categoryUrl);
                               
                }
 
                public void gettbaPCPUrl(String PCPUrl) {
                                getUrl(UrlType.COMPARISON_PAGE,PCPUrl);
                               
                }
 
                public void captureScreenshot(ITestResult result, Logger logger) {
                                if (result.getStatus() == ITestResult.FAILURE) {
                                                try {
                                                                File srcFile = ((TakesScreenshot) driver)
                                                                                                .getScreenshotAs(OutputType.FILE);
                                                                String screenShotPath = "test-output\\screenshots\\"
                                                                                                + result.getName() + "-screenshot.png";
                                                                FileUtils.copyFile(srcFile, new File(screenShotPath));
                                                                Reporter.log("<br> <img src=." + screenShotPath + " /> <br>");
                                                } catch (Exception e) {
                                                                logger.error("Error in saving screen-shot", e);
                                                }
                                }
                               
                }
 
                public void getSLPUrl(String searchTerm) {
                                getUrl(UrlType.SLP, searchTerm);
                }
 
                public void getThematicUrl(String searchTerm) {
                                getUrl(UrlType.THEMATIC, searchTerm);
                }
               
//            public WebAnalyticsRequest getWebAnalyticsRequest() {
//                return webAnalyticsRequest;
//    }
//    public void setWebAnalyticsRequest(WebAnalyticsRequest webAnalyticsRequest) {
//        this.webAnalyticsRequest = webAnalyticsRequest;
//    }
    public int getSslPort() {
        return sslPort;
    }
    public void setSslPort(int sslPort) {
        this.sslPort = sslPort;
    }
    public int getNonSslPort() {
        return nonSslPort;
    }
    public void setNonSslPort(int nonSslPort) {
        this.nonSslPort = nonSslPort;
    }
 
                public void getGiftRegistryPage() {
                                getUrl(UrlType.GIFT_REGISTRY);
                }
               
                public void getMixAndMatchPageUrl(String productString){
                                getUrl(UrlType.MIX_AND_MATCH,productString);
                }
                               
}
