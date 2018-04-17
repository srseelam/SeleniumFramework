package framework;
 
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.events.EventFiringWebDriver;
 
public class DriverFactory {
 
                public static WebDriver getDriver(String driverName, PageStore pageStore) {
                                System.out.println("Creating driver");
                               
                                try{
                                    if (driverName.equals("firefox")) {
                                                WebDriver driver = null;
                                                DesiredCapabilities cap = DesiredCapabilities.firefox();
                                                FirefoxBinary binary = null;
                                                FirefoxProfile profile = new FirefoxProfile();
                                                EventFiringWebDriver efd = null;
                                      
                                                try{
                                                                                Proxy proxy = new org.openqa.selenium.Proxy();
                                                                                proxy.setProxyAutoconfigUrl("http://browserconfig.target.com/proxy-global.pac");
                                                                                cap.setCapability(CapabilityType.PROXY, proxy );
                                                                               
                                                                                if(ValidateOS.isWindows()){
                                                                                                /*
                                                                                                * By default FirfoxBinary will pick up your firefox if it is in Program Files.
                                                                                                * If it is not there, you will have to set your path manually in the pathToBinary
                                                                                                * parameter. 
                                                                                                 */
                                                                                                binary = new FirefoxBinary();
                                                                                }else if(ValidateOS.isMac()){
                                                                                                /*
                                                                                                * Mac Path to Firefox. If you have changed the default path in anyway make sure you
                                                                                                * change the pathToBinary to what you have it set to.
                                                                                                */
                                                                                                File pathToBinary = new File("/Applications/Firefox.app/Contents/MacOS/firefox");
                                                                                                binary = new FirefoxBinary(pathToBinary);
                                                                                }else if(ValidateOS.isNix()){
                                                                                                if(ValidateOS.is64bit()){
                                                                                                                File pathToBinary = new File("/usr/lib64/firefox");
                                                                                                                binary = new FirefoxBinary(pathToBinary);
                                                                                                }else{
                                                                                                                File pathToBinary = new File("/usr/lib/firefox");
                                                                                                                binary = new FirefoxBinary(pathToBinary);
                                                                                                }
                                                                                }
                                                                               
                                                                                //profile.setPreference("network.ntlm.send-lm-response", true);
                                            profile.setAcceptUntrustedCertificates(true);
                                                                                profile.setAssumeUntrustedCertificateIssuer(true);
                                                                               
                                                                                //event firing webdriver is required for 2 reasons
                                                                                // 1. for rewriting the urls in case of scs and pky to handle situation where scs and pky redirect to prod
                                                                                // 2. for rewriting the Sitecatalyst server url to capture tagging requests
                                                                                driver = ThreadGuard.protect(new FirefoxDriver(binary,profile,cap));
                                                                                driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
                                                                                driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
                                                                                efd = new EventFiringWebDriver(driver);
                                                        efd.register(new URLEventListener(pageStore));
                                        }
                                        catch(Exception e)
                                        {
                                                driver = ThreadGuard.protect(new FirefoxDriver(binary,profile,cap));
                                                                                driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
                                                                                driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
                                                                                efd = new EventFiringWebDriver(driver);
                                                        efd.register(new URLEventListener(pageStore));
                                            e.printStackTrace();
                                        }
                                                               
                                                                return efd;
                                    }else if (driverName.equals("chrome")) {
                                                               
                                                                 WebDriver driver = null;
                                                                                EventFiringWebDriver efd = null;
                                                                                DesiredCapabilities cap = DesiredCapabilities.chrome();
                                                                                Proxy proxy = new org.openqa.selenium.Proxy();
                                                                                proxy.setProxyAutoconfigUrl("http://browserconfig.target.com/proxy-global.pac");
                                                                                cap.setCapability(CapabilityType.PROXY, proxy );
                                                                               
                                                                                if(ValidateOS.isWindows()){
                                                                                                System.setProperty("webdriver.chrome.driver", "exe/chromedriver.exe");               
                                                                                }else if (ValidateOS.isMac()){
                                                                                                System.setProperty("webdriver.chrome.driver", "exe/mac/chromedriver");
                                                                                }else if (ValidateOS.isNix()){
                                                                                                if(ValidateOS.is64bit()){
                                                                                                                System.setProperty("webdriver.chrome.driver", "exe/unix/64bit/chromedriver");
                                                                                                }else{
                                                                                                                System.setProperty("webdriver.chrome.driver", "exe/unix/32bit/chromedriver");
                                                                                                }
                                                                                }
                                                                               
                                                                                try{
                                                                               
                                                                                    driver = ThreadGuard.protect(new ChromeDriver(cap));
                                                                                                driver.manage().deleteAllCookies();
                                                                                                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                                                                                //event firing webdriver is required for 2 reasons
                                                // 1. for rewriting the urls in case of scs and pky to handle situation where scs and pky redirect to prod
                                                // 2. for rewriting the Sitecatalyst server url to capture tagging requests
                                                                                                efd = new EventFiringWebDriver(driver);
                                                                                                efd.register(new URLEventListener(pageStore));
                                                                                }
                                                                                catch (Exception e) {
                                                                                                               driver = ThreadGuard.protect(new ChromeDriver(cap));
                                                                                                                driver.manage().deleteAllCookies();
                                                                                                                efd = new EventFiringWebDriver(driver);
                                                                                                                efd.register(new URLEventListener(pageStore));
                                                            }
                                                                               
                                                                                return efd;
                                                               
                                                    /*ChromeDriver driver = null;
                                                   
                                                    Proxy proxy = new org.openqa.selenium.Proxy();
                            //proxy.setSslProxy("proxy-mdha.target.com:8080");
                                                    proxy.setProxyType(ProxyType.DIRECT);
                                                    System.setProperty("webdriver.chrome.driver", "exe/chromedriver.exe");
                                                    ChromeOptions opts = new ChromeOptions();
                                                    DesiredCapabilities cap = DesiredCapabilities.chrome();
                                                    cap.setCapability(CapabilityType.PROXY, proxy );
                            opts.setBinary(new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"));
                                                    //opts.setBinary(new File("C:/Users/Z013T94/AppData/Local/Google/Chrome/Application/chrome.exe"));
                                          
                                                    cap.setCapability(ChromeOptions.CAPABILITY, opts);
                            
                                                    try {
                                driver = new ChromeDriver(cap);
                            } catch (Exception e) {
                               
                            }
                            return driver;*/
                                                } else if (driverName.equals("tla_chrome")) {
                                                                System.setProperty("webdriver.chrome.driver", "exe/chromedriver.exe");
                                                                ChromeOptions options = new ChromeOptions();
                                                                options.addArguments("--disable-web-security");
                                                                return new ChromeDriver(options);
                                                }
                                                else {
                                         DesiredCapabilities caps = new DesiredCapabilities();
                                         caps.setJavascriptEnabled(true);
                                         caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "exe/phantomjs.exe");
                                         caps.setCapability("takesScreenshot", true);
                                         return new PhantomJSDriver(caps);
                                                }
                                } catch (Exception exception) {
                                                System.out.println(exception.getMessage());
                                                System.out.println("Some Error occurred while getting the Driver. Please check the driver initialization. Failed for Driver ::: " + driverName);
                                                return null;
                                }
                               
                }
               
}