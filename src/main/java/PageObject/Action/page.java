package PageObject.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class page {
    private static page instance ;
    private final WebDriver driver;
    private Page_Home homePage;
    private Page_Login loginPage;

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


    public static Page_Home home() {

        if (instance.homePage == null) {
            instance.homePage = new Page_Home(instance.driver);
        }
        return instance.homePage;
    }


}
