package users;
 
//import framework.AppUser;
import framework.PageStore;
import framework.TestSession;
import pages.AddToCartOverlayPage;
//import pages.AllPages;
import pages.HomePage;
import pages.PDPPage;
import pages.PLPandSLPpage;
 
public class ProductPageUser {
 
                private PageStore pageStore;
              //  TestSession session;
               
                public ProductPageUser(PageStore pageStore, TestSession session) {
                                this.pageStore = pageStore;
                      //          this.session = session;
                }
               
 
//                public AllPages getAllPagesPage(){
//        return pageStore.getPage(AllPages.class);
//                }
               
                public AddToCartOverlayPage getAddToCartOverlayPage(){
        return pageStore.getPage(AddToCartOverlayPage.class);
                }
               
                public HomePage getHomePage(){
        return pageStore.getPage(HomePage.class);
                }
               
                public PDPPage getPDPPage(){
        return pageStore.getPage(PDPPage.class);
                }
               
                public PLPandSLPpage getPLPandSLPpage(){
        return pageStore.getPage(PLPandSLPpage.class);
                }
}