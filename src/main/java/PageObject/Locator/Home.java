package PageObject.Locator;
import org.openqa.selenium.By;

public class Home {

    public static final By btnLogin =  By.xpath("//b[text()='Đăng nhập']");
    public static  By btnProfile (String profile_name){
        return By.xpath("//span[text()='"+profile_name+"']");
    }

}

