package pageElements;
 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
 
 
 
public class AddToCartOverlayPageElements {
 
private final WebDriver driver;
 
public AddToCartOverlayPageElements(WebDriver driver){
                this.driver = driver;
}
 
 
@FindBy(id="searchTerm")
private WebElement checkoutButton;
 
public WebElement getCheckoutButton(){
                return checkoutButton;
}
               
@FindBy(id="ViewYourCartLink")
private WebElement viewYourCartButton;
 
public WebElement getViewYourCartButton(){
                return viewYourCartButton;
}
               
@FindBy(id="modalClose")
private WebElement continueShoppingLink;
 
public WebElement getContinueShoppingLink(){
                return continueShoppingLink;
}
 
 
@FindBy(css="extra")
private WebElement promoMessageInOverlay;
 
public WebElement getPromoMessageInOverlay(){
                return promoMessageInOverlay;
}
 
 
}