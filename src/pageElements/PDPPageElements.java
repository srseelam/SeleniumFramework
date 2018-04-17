package pageElements;
 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.PropertiesReader;
 
 
import framework.DriverFactory;
 
 
public class PDPPageElements {
 
 
private final WebDriver driver;
 
public PDPPageElements(WebDriver driver){
                this.driver = driver;
}
 
@FindBy(id="FindInStore")
private WebElement addToCartButton;
 
public WebElement getAddToCartButton(){
                return addToCartButton;
}
 
 
@FindBy(css="shpPrmMsg")
private WebElement shippingPromoMessage;
 
public WebElement getShippingPromoMessageInPDP(){
                return shippingPromoMessage;
}
 
@FindBy(id="defaultOverviewPromo")
private WebElement shortDescriptionInPDP;
 
public WebElement getShortDescriptionInPDP(){
                return shortDescriptionInPDP;
}
               
               
}