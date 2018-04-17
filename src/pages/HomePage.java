package pages;
 
import org.openqa.selenium.WebDriver;
import pageElements.HomePageElements;
import framework.PropertiesReader;
 
public class HomePage extends AbstractPage{
 
               
                public HomePage(WebDriver driver) {
                                super(driver);                   
                }
               
                private HomePageElements elements; 
                //PropertiesReader reader = new PropertiesReader();
               
               
               
                @Override
                public void setPageElement(Object pageElement) {
                                this.elements = (HomePageElements) pageElement;
 
                }
               
//            public void openHomePage(){
//                            elements.openHomePage();
//            }
               
                public void searchForPartNumner(int partNumber){
                                String partNumber1 = String.valueOf(partNumber);                       
                                elements.getSerchBox().clear();
                                elements.getSerchBox().sendKeys(partNumber1);
                                elements.getSearchButton().click();
                               
                }
               
               
                               
                               
} 