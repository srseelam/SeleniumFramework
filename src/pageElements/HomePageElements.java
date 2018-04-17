package pageElements;
 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.PropertiesReader;
 
 
public class HomePageElements {
 
                               
private final WebDriver driver;
 
public HomePageElements(WebDriver driver){
                this.driver = driver;
}
 
 
 
@FindBy(id="searchTerm")
private WebElement searchBox;
 
public WebElement getSerchBox(){        
                return searchBox;
}
 
 
@FindBy(id="goSearch")
private WebElement searchButton;
               
public WebElement getSearchButton(){
                return searchButton;
}
 
public WebDriver getDriver() {
                return driver;
}
               
               
}