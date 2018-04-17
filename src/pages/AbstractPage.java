package pages;
 
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 
import framework.WebAction;
 
public class AbstractPage {
 
                protected WebDriver driver;
                WebDriverWait wait;
                @FindBy(id = "ajax-curtain")
                protected WebElement ajaxCurtain;
 
                public AbstractPage(WebDriver driver) {
                                this.driver = driver;
                }
 
                public void setPageElement(Object pageElement) {
 
                }
 
                protected void waitUntil(int timeout,
                                                ExpectedCondition<WebElement> expectedCondition) {
                                wait = new WebDriverWait(driver, timeout);
                                wait.until(expectedCondition);
                }
 
                public void chooseItemFromDropDown(WebElement dropBox, String desiredOption) {
 
                                desiredOption = desiredOption.replaceAll("[^a-zA-Z0-9]", "");
                                if(!dropBox.isDisplayed())
                                                return;
                                List<WebElement> options = dropBox.findElements(By.tagName("option"));
                                for (WebElement option : options) {
                                                String optionVal=option.getText().trim().replaceAll("[^a-zA-Z0-9]", "");
                                                if(optionVal.split("in-").length>=2)
                                                {
                                                                String optionCardNo=optionVal.split("in-")[1].substring(optionVal.split("in-")[1].length()-4, optionVal.split("in-")[1].length());
                                                                String desiredCardNo = desiredOption.split("in-")[1].substring(desiredOption.split("in-")[1].length()-4, desiredOption.split("in-")[1].length());
                                                                if(optionVal.split("in-")[0].equalsIgnoreCase(desiredOption.split("in-")[0]) && desiredCardNo.equalsIgnoreCase(optionCardNo))
                                                                {
                                                                                option.click();
                                                                                return;
                                                                }
                                                               
                                                }
                                                else if (desiredOption.equalsIgnoreCase(optionVal)){
                                                                option.click();
                                                                return;
                                                }
                                }
                                Assert.assertTrue(false, desiredOption + "does not exist in dropbox");
 
                }
 
                public void chooseLastItemFromDropDown(WebElement dropBox) {
                               
                                if(!dropBox.isDisplayed())
                                                return;
                                List<WebElement> options = dropBox.findElements(By.tagName("option"));
                               
                                WebElement opt = options.get(options.size()-1);
                                opt.click();
                }
 
                public void clickItemInList(WebElement listItem, String desiredOption) {
 
                                List<WebElement> options = listItem
                                                                .findElements(By.cssSelector("li a"));
 
                                for (WebElement option : options) {
                                                if (desiredOption.equals(option.getText())) {
                                                                option.click();
                                                                break;
                                                }
                                }
                }
 
                protected void waitUntilBool(int timeout,
                                                ExpectedCondition<Boolean> condition) {
                                wait = new WebDriverWait(driver, timeout);
                                wait.until(condition);
 
                }
               
                protected WebElement waitFindElement(String locator, int timeout) {
//                            waitUntil(timeout, ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
//                            waitUntil(timeout, ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
                                return driver.findElement(By.cssSelector(locator));
                }
               
                protected WebElement waitFindElementByXpath(String xpath, int timeout) {
//                            waitUntil(timeout, ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
//                            waitUntil(timeout, ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                                return driver.findElement(By.xpath(xpath));
                }
               
                protected WebElement waitFindElement(WebElement element, int timeout) {
//                            waitUntil(timeout, ExpectedConditions.visibilityOf(element));
                                return element;
                }
 
 
                protected void waitForPageToLoad(long timeout) {
                                driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                }
 
                @Deprecated
                protected void waitForAjaxCurtain() {
//                            waitUntil(50, ExpectedConditions.visibilityOf(ajaxCurtain));
//                            if (ajaxCurtain.isDisplayed()) {
//                                            waitUntilBool(50,
//                                                                            ExpectedConditions.invisibilityOfElementLocated(By
//                                                                                                            .id("ajax-curtain")));
//                            }
                }
               
                protected void waitForAjaxCurtainToDisappear() {
                                try {
                                                while(true)
                                                {
                                                    waitUntil(1, ExpectedConditions.presenceOfElementLocated(By.id("ajax-curtain")));
                                                }
                                                //waitUntilBool(50,ExpectedConditions.invisibilityOfElementLocated(By.id("ajax-curtain")));
                                }
                                catch (Exception e){
                                    //e.printStackTrace();
                                                System.err.println("Ajax Curtain either did not appear or did not disappeard in time" + e.getStackTrace());
                                }
                }
 
                protected void setValue(WebElement element, String value) {
                                ((JavascriptExecutor) driver).executeScript(
                                                                "arguments[0].value = arguments[1]", element, value);
                }
 
                public boolean isNotDisplayed(WebElement element) {
                                try {
                                                return !element.isDisplayed();
                                } catch (NoSuchElementException e) {
                                                return true;
                                }
                }
 
                protected void waitForSeconds(int seconds) {
                                try {
                                                Thread.sleep(seconds * 1000);
                                } catch (InterruptedException e) {
                                                System.err.println("Error While Waiting for code to execute");
                                }
                }
 
                protected void hoverOver(WebElement webElement) {
                                Actions action = new Actions(driver);
                                action.moveToElement(webElement).build().perform();
                }
 
                public String getCurrentUrl() {
                                return driver.getCurrentUrl();
                }
               
                public void chooseItemFromDropDownByOptionValue(WebElement dropBox, String desiredOption) {
                               
                                desiredOption = desiredOption.replace(" ", "").replace("\n", "");
                    List<WebElement> options = dropBox.findElements(By.tagName("option"));
                    for (WebElement option : options) {
                                if (desiredOption.equalsIgnoreCase(option.getAttribute("value").trim().replace(" ", ""))) {
                            option.click();
                            return;
                        }
                    }
                    Assert.assertTrue(false, desiredOption + "does not exist in dropbox");
 
               
                }
               
                public void performActionByHandlingStaleElement(String selector, int retryCount, WebAction action){
                                int count = 0;
                                WebElement element = null;
                                while (count < retryCount){
 
                                                try {
                                                                element = waitFindElement(selector, 10);
                                                                action.perform(element);
                                                                return;
 
                                                } catch (StaleElementReferenceException e){
                                                                count = count+1;
                                                                if(count >= retryCount){
                                                                                throw e;
                                                                }
                                                                System.out.println("Trying to recover from a stale element :" + e.getMessage());
                                                                System.out.println("retrying to fetch stale element ...");
                                                }
                                }
                }
               
 
public void switchToChildWindow() {
                                try {
                                                if (driver.getWindowHandles().size() > 1) {
                                                                Iterator<String> i = driver.getWindowHandles().iterator();
                                                                while (i.hasNext()) {
                                                                                String childBrowser = i.next();
                                                                                driver.switchTo().window(childBrowser);
                                                                }
                                                }
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }
                }
 
                public void closeAndReturnToMainWindow() {
        if(driver.getWindowHandles().size()>1){
               driver.close();
        }
        driver.switchTo().window(driver.getWindowHandle());
                }
 
                public boolean isElementPresent(WebElement webElement) {
        try {
                if(webElement.isDisplayed())
               return true;
        }
        catch (NoSuchElementException e) {
               return false;
        }catch (StaleElementReferenceException e){
                return false;
                                }
        return false;
                }
               
                public void waitForObjectToLoad(WebElement ele, int timeout){
//                            boolean isExist = false;
//                            int cntr = 0;
//                            while (!isExist) {
//                                            if(isElementPresent(ele)){
//                                                            return;
//                                            } else{
//                                                            waitForSeconds(1);
//                                                            cntr++;
//                                                            if(cntr == timeout){ 
//                                                                            return;
//                                                            }
//                                            }
//                            }
                }
               
                public void waitForObjectToDisappear(WebElement ele){
//                            boolean isExist = true;
//                            int cntr = 0;
//                            while (isExist) {
//                                            if(!isElementPresent(ele)){
//                                                            return;
//                                            } else{
//                                                            waitForSeconds(1);
//                                                            cntr++;
//                                                            if(cntr == 60){  // Maximum wait time is for 60 seconds
//                                                                            return;
//                                                            }
//                                            }
//                            }
                }
               
                public void enterKeys(String keys, WebElement webElement){
                                waitUntil(50, ExpectedConditions.visibilityOf(webElement));
                                webElement.clear();
                                webElement.sendKeys(keys);
                }
               
                public void clicksOn(WebElement webElement){
                                waitUntil(50, ExpectedConditions.visibilityOf(webElement));
                                webElement.click();
                }
               
                public void isDisplayed(WebElement webElement){
                                waitUntil(50, ExpectedConditions.visibilityOf(webElement));
                                Assert.assertTrue(webElement.isDisplayed(), webElement + "doesn't exists");
                }
               
                public void waitForLoaderImageToDisappear(){
                                try {
                                                while(true)
                                                {
                                                    waitUntil(2, ExpectedConditions.visibilityOfElementLocated(By.id("loader")));
                                                    //driver.findElement(By.id("loader")).getAttribute("style");
                                                }
                                }
                                catch (Exception e){
                                                System.err.println("Loader image either did not appear or did not disappeard in time" + e.getStackTrace());
                                }
                }
               
                public void addCookieToEnableRushdelivery() {
                                Cookie cookie = new Cookie("cpref", "MKT=RMP");
                                driver.manage().addCookie(cookie);
                }
               
                public void clickOnBrowserBack(){
                                driver.navigate().back();
                }
               
}