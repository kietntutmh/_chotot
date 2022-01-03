package PageObject.Action;

import AutomationLibrary.BaseAction.BaseAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class page {
    private static page instance ;
    private final WebDriver driver;
    private Page_Home homePage;
    private Page_Login loginPage;
    static  WebDriver driver_;
    private page(WebDriver driver) {

        this.driver = driver;
    }
    // Init instance
    public static void init() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//chromedriver.exe");
        WebDriver _driver = new ChromeDriver();
        instance=new page(_driver);
    }

    public static void end(){
        instance.driver.close();
    }

 
    public static Page_Login login() {

        if (instance.loginPage == null) {
            instance.loginPage = new Page_Login(instance.driver);
        }

        return instance.loginPage;
    }
   private  static <TPage extends BaseAction> TPage  instantiateNewPage (Class<TPage> Clazz) {

        try {
            if (instance.homePage == null) {
                return Clazz.newInstance();
            }else
                return null;
        } catch (InstantiationException | IllegalAccessException var4) {
            throw new RuntimeException(var4);
        }
   }


    public static Page_Home home() {
            return instantiateNewPage(Page_Home.class);
//        if (instance.homePage == null) {
//            instance.homePage = new Page_Home(instance.driver);
//        }
//        return instance.homePage;
    }


}
