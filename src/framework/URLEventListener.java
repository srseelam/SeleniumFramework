package framework;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
 
public class URLEventListener implements WebDriverEventListener {
 
    PageStore pageStore;
    
    public URLEventListener(PageStore pageStore)
    {
        this.pageStore = pageStore;
    }
    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
       // System.out.println("NAVIGATED TO URL+"+arg0);
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) arg1;
            jsExecutor.executeScript("s.trackingServer='127.0.0.1:"+pageStore.getNonSslPort()+"';");
            jsExecutor.executeScript("s.trackingServerSecure='127.0.0.1:"+pageStore.getSslPort()+"';");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            ////e.printStackTrace();
        }
    }
 
    @Override
    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) arg1;
            jsExecutor.executeScript("s.trackingServer='127.0.0.1:"+pageStore.getNonSslPort()+"';");
            jsExecutor.executeScript("s.trackingServerSecure='127.0.0.1:"+pageStore.getSslPort()+"';");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            ////e.printStackTrace();
        }
       
    }
 
    @Override
    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub
                String currenturl = arg2.getCurrentUrl();
       
        if((System.getProperty("target_env") !=null) && (!System.getProperty("target_env").equals("prod")))
        {
            try {
                            URL aURL = new URL(currenturl);
                            URL bURL = new URL(System.getProperty("baseURL"));
                            if( aURL.getHost().equals("www.target.com") || aURL.getHost().equals("www-secure.target.com") )
                            {
                                currenturl =  currenturl.replaceFirst("www.target.com", bURL.getHost()).replaceFirst("www-secure.target.com", bURL.getHost());
                                System.out.println("Updated URL"+currenturl);
                                arg2.navigate().to(currenturl);
                            }
                        } catch (MalformedURLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
       
        
        }
    }
 
    @Override
    public void beforeNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void onException(Throwable arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
       
    }
 
    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
       
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) arg1;
            jsExecutor.executeScript("s.trackingServer='127.0.0.1:"+pageStore.getNonSslPort()+"';");
            jsExecutor.executeScript("s.trackingServerSecure='127.0.0.1:"+pageStore.getSslPort()+"';");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        String currenturl = arg1.getCurrentUrl();
       
        if((System.getProperty("target_env") !=null) && (!System.getProperty("target_env").equals("prod")))
        {
            System.out.println(arg1.getCurrentUrl());
        try {
            URL aURL = new URL(currenturl);
            URL bURL = new URL(System.getProperty("baseURL"));
            if( aURL.getHost().equals("www.target.com") || aURL.getHost().equals("www-secure.target.com") )
            {
                currenturl =  currenturl.replaceFirst("www.target.com", bURL.getHost()).replaceFirst("www-secure.target.com", bURL.getHost());
                System.out.println("Updated URL"+currenturl);
                arg1.navigate().to(currenturl);
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       
        
        }
       
        
    }
 
    @Override
   public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub
       
    }
 
    @Override
    public void afterNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
       
    }
 
    @Override
    public void afterNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
       
    }
 
}
