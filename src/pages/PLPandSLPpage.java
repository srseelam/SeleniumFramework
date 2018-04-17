package pages;
 
import org.openqa.selenium.WebDriver;
 
import pageElements.HomePageElements;
import pageElements.PLPandSLPpageElements;
 
 
public class PLPandSLPpage extends AbstractPage{
 
                public PLPandSLPpage(WebDriver driver) {
                                super(driver);                   
                }
 
                private PLPandSLPpageElements elements;
               
               
                @Override
                public void setPageElement(Object pageElement) {
                                this.elements = (PLPandSLPpageElements) pageElement;
 
                }
               
public void clickOnQILinkForFirstItemInSLP(){
                elements.getFirstQuickInfoButton().click();                        
}
                               
public void clickOnFirstItemInSLP(){
                elements.getFirstItemLink().click();                        
}
                               
} 