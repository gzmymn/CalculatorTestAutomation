package Page;

import org.openqa.selenium.By;

public class CalculatorElements {

    public static By displayResult = By.cssSelector("div[style*='background-color: rgb(242, 242, 242); display: flex']>div>div>div>div>div>div");
    public static By clear = By.xpath("//div/div[text()='AC']");
    public static By division = By.xpath("(//div/div)[text()='/'][last()]");
    public static By addition = By.xpath("(//div/div)[text()='+'][last()]");
    public static By subtraction = By.xpath("(//div/div)[text()='-'][last()]");
    public static By multiplication = By.xpath("(//div/div)[text()='*'][last()]");
    public static By equality = By.xpath("(//div/div)[text()='='][last()]");
    public static By comma = By.xpath("(//div/div)[text()=','][last()]");
    public static By zero = By.xpath("(//div/div)[text()='0'][last()]");
    public static By one = By.xpath("(//div/div)[text()='1'][last()]");
    public static By two = By.xpath("(//div/div)[text()='2'][last()]");
    public static By three = By.xpath("(//div/div)[text()='3'][last()]");
    public static By four = By.xpath("(//div/div)[text()='4'][last()]");
    public static By five = By.xpath("(//div/div)[text()='5'][last()]");
    public static By six = By.xpath("(//div/div)[text()='6'][last()]");
    public static By seven = By.xpath("(//div/div)[text()='7'][last()]");
    public static By eight = By.xpath("(//div/div)[text()='8'][last()]");
    public static By nine = By.xpath("(//div/div)[text()='9'][last()]");
}
