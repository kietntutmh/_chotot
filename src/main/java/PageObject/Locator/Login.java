package PageObject.Locator;


import org.openqa.selenium.By;

public class Login {

    public static final By txtPhoneNumber = By.xpath("//input[@placeholder='Nhập SĐT của bạn']");
    public static final By txtPassWord =  By.xpath("//input[@placeholder='Nhập mật khẩu của bạn']");
    public static final By btnLogin =  By.xpath("//button[text()='Đăng nhập']");

}
