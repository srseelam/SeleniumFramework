package pages;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
 
import pageElements.HomePageElements;
import pageElements.PDPPageElements;
 
 
public class PDPPage extends AbstractPage{
 
                public PDPPage(WebDriver driver) {
                                super(driver);   
                }
 
                private PDPPageElements elements;     
               
               
               
                @Override
                public void setPageElement(Object pageElement) {
                                this.elements = (PDPPageElements) pageElement;
 
                }
               
public void verifyShortDescriptionOnPDP(String shortDescription){
                System.out.println("Short description in PDP is: " +
                                                                                elements.getShortDescriptionInPDP().getText());
                Assert.assertEquals(elements.getShortDescriptionInPDP(), shortDescription);
 
}
 
public void clickOnAddToCartButton(){
                elements.getAddToCartButton().click();
}
 
 
               
                               
                               
}