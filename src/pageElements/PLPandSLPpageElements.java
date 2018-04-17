package pageElements;
 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.PropertiesReader;
 
 
import framework.DriverFactory;
 
 
public class PLPandSLPpageElements {
                                               
                private final WebDriver driver;
 
                public PLPandSLPpageElements(WebDriver driver){
                                this.driver = driver;
                }
 
@FindBy(id="prodQuickInfo-slp_medium-1-1")
private WebElement firstQuickInfoButton;
 
public WebElement getFirstQuickInfoButton(){
                return firstQuickInfoButton;
}
               
 
@FindBy(id="prodTitle-slp_medium-1-1")
private WebElement firstIitemLink;
 
public WebElement getFirstItemLink(){
                return firstIitemLink;
}
 
 
@FindBy(id="promotionGiftCardTitle")
private WebElement promotionDescriptioninPLPorSLP;
 
public WebElement getPromotionDescriptioninPLPorSLP(){
                return promotionDescriptioninPLPorSLP;
}
 
public WebDriver getDriver() {
                return driver;
}
 
 
 
}