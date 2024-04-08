package Page;

import org.openqa.selenium.By;

public class LoginPage {
    public static By usernameInput = By.cssSelector("input[placeholder='Username']");
    public static By passwordInput = By.cssSelector("input[placeholder='Password']");
    public static By loginButton = By.cssSelector(".r-1i6wzkk");
    public static By openCalculatorButton = By.xpath("//*[text()='Open Calculator']");

    public static By usernameField = By.xpath("//span[contains(text(), 'Challenge name:')]/parent::div");
}
