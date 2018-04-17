package pageElements;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
import framework.DriverFactory;
import framework.PropertiesReader;
 
public class AllPagesElements {
 
                               
                private final WebDriver driver;
               
 
 
                public AllPagesElements(WebDriver driver){
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
               
               
                @FindBy(id="searchTerm")
                private WebElement searchBox;
 
                public WebElement getSerchBox(){        
                                return searchBox;
                }
 
 
                @FindBy(id="searchMagnify")
                private WebElement searchButton;
                               
                public WebElement getSearchButton(){
                                return searchButton;
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
 
}