package TestContainer;

import Base.BaseFinal;
import Page.CalculatorElements;
import Page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Steps extends BaseFinal {

    public void clickButton(By locator) {
        WebElement element = locateElementAndScrollIntoView(locator);
        element.click();
        log.info("Clicked to "+ locator.toString() + " element");
        waitForSeconds(1);
    }

    public void clickAdditionButton() {
        WebElement plus = locateElementAndScrollIntoView(CalculatorElements.addition);
        plus.click();
        log.info("Clicked to "+ CalculatorElements.addition.toString() + " element");
        waitForSeconds(1);
    }

    public void clickEqualityButton() {
        clickButton(CalculatorElements.equality);
        waitForSeconds(2);
    }

    public void loginUser(String username, String password) {
        WebElement usernameInput = locateElementAndScrollIntoView(LoginPage.usernameInput);
        usernameInput.sendKeys(username);
        waitForSeconds(1);

        WebElement passwordInput = locateElementAndScrollIntoView(LoginPage.passwordInput);
        passwordInput.sendKeys(password);
        waitForSeconds(1);

        WebElement loginButton = locateElementAndScrollIntoView(LoginPage.loginButton);
        loginButton.click();

        log.info("Logged into user.");
    }

    public void openCalculator() {
        WebElement openCalculator = locateElementAndScrollIntoView(LoginPage.openCalculatorButton);
        openCalculator.click();
        log.info("Calculator opened.");
        locateElement(CalculatorElements.displayResult);
        waitForSeconds(1);
    }

    public double operate(String oprStr) {
        // 5 * 6
        char opType = 0;
        String opTypes = "+-*/";
        for(int i = 0, len = opTypes.length(); i < len; ++i) {
            char ch = opTypes.charAt(i);
            if(oprStr.indexOf(ch) != -1) {
                opType = ch;
                break;
            }
        }
        double value1 = Double.parseDouble(oprStr.substring(0, oprStr.indexOf(opType)).trim());
        double value2 = Double.parseDouble(oprStr.substring(oprStr.indexOf(opType) + 1).trim());

        switch(opType) {
            case '+':
                return value1 + value2;
            case '-':
                return value1 - value2;
            case '*':
                return value1 * value2;
            case '/':
                return value1 / value2;
        }
        throw new IllegalArgumentException("Unsupported operation type!");
    }
    public void verifyResultIsTrue() {
        WebElement operation = locateElementAndScrollIntoView(CalculatorElements.displayResult);
        String calculatorDisplayText = operation.getText();
        int equalityIndex = calculatorDisplayText.indexOf('=');
        Assertions.assertNotEquals(-1, equalityIndex, "The equality sign was not displayed on the screen. The calculation was not performed!");
        String operationStr = calculatorDisplayText.substring(0, equalityIndex).trim();
        double expectedResult = operate(operationStr);
        log.info("Expected result: " + expectedResult);
        double actualResult = Double.parseDouble(calculatorDisplayText.substring(calculatorDisplayText.indexOf('=') + 1).trim());
        log.info("Actual result: " + actualResult);

        Assertions.assertEquals(expectedResult, actualResult, String.format("Result is not correct (%.2f, %.2f)!", expectedResult, actualResult));
        waitForSeconds(1);
    }

    public void verifyElementContainsValue(By locator, String value) {
        WebElement element = locateElementAndScrollIntoView(locator);
        String elementStr = element.getText();
        boolean containmentStatus = elementStr.contains(value);
        Assertions.assertTrue(containmentStatus, String.format("Element is not containing %s value!", value));
        log.info(value + " values have been found in the element text.");
        waitForSeconds(1);
    }
}
