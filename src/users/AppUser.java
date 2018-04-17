package users;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.Delegate;
import org.testng.Assert; 

import users.ProductPageUser; 
import framework.PageStore;
import framework.TestSession;
import pages.HomePage;
import pages.AddToCartOverlayPage;
import pages.PDPPage;
import pages.PLPandSLPpage;

 
 
public class AppUser implements BaseUser{
 
Map<String, ArrayList<String>> myMap;
 
HomePage homePage; 
AddToCartOverlayPage addToCartOverlayPage;               
PDPPage pDPPage;
PLPandSLPpage pLPandSLPpage;
//QuickInfoPage quickInfoPage;
 
 
PageStore pageStore;
TestSession session;
 
Map<String, Object> accessibility = new HashMap<String, Object>();
 
@SuppressWarnings("deprecation")
@Delegate private ProductPageUser productPageUser;
 
 
public AppUser(PageStore pageStore){
                this.session = new TestSession();
                this.pageStore = pageStore;      
                this.productPageUser = new ProductPageUser(pageStore, session);
}
 
public AppUser opensHomePage() {
                pageStore.getHomePageUrl();
                return new AppUser(pageStore);
               
}
 
//public void openHomePage(){
//            homePage.openHomePage();
//}
 
public void searchForPartNumner(int partNumber){      
                getHomePage().searchForPartNumner(partNumber);
//            homePage.searchForPartNumner(partNumber);
               
}
 
public void clickOnQILinkForFirstItemInSLP(){
                getPLPandSLPpage().clickOnQILinkForFirstItemInSLP();                
}
                               
public void clickOnFirstItemInSLP(){
                getPLPandSLPpage().clickOnFirstItemInSLP();                   
}
               
public void verifyShortDescriptionOnPDP(String shortDescription){
                getPDPPage().verifyShortDescriptionOnPDP(shortDescription);
}
 
public void clickOnAddToCartButton(){
                getPDPPage().clickOnAddToCartButton();
}
 
public void verifyPromoMessageOnOverlay(String promoDescription){
                getAddToCartOverlayPage().verifyPromoMessageOnOverlay(promoDescription);          
}
               
public void clickOnViewCartOnOverlay(){
                getAddToCartOverlayPage().clickOnViewCartOnOverlay();
                               
}
               
 
@Override
public AppUser andUser() {
                return this;
}
 
@Override
public BaseUser thenUser() {
                return this;                        
}
 
@Override
public BaseUser whenUser() {
                return this;
}
 
public void OpenBrowser(){
               
}
 
 
 
}