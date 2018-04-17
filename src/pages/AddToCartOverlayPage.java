package pages;
 
import pageElements.AddToCartOverlayPageElements;
import pageElements.HomePageElements;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
//import lombok.val;
 
public class AddToCartOverlayPage extends AbstractPage{
 
                public AddToCartOverlayPage(WebDriver driver) {
                                super(driver);
 
                }
               
                private AddToCartOverlayPageElements elements;
 
 
               
               
                @Override
                public void setPageElement(Object pageElement) {
                                this.elements = (AddToCartOverlayPageElements) pageElement;
 
                }
               
public void verifyPromoMessageOnOverlay(String promoDescription){
                System.out.println("Promo message in cart overlay"
                                                                + elements.getPromoMessageInOverlay().getText());
                Assert.assertEquals(elements.getPromoMessageInOverlay().getText(), promoDescription);     
}
               
public void clickOnViewCartOnOverlay(){
                elements.getViewYourCartButton().click();
                               
}
               
                               
                               
}