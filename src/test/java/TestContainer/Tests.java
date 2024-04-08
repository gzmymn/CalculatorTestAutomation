package TestContainer;

import Page.CalculatorElements;
import Page.LoginPage;
import org.junit.jupiter.api.Test;

public final class Tests extends Steps {

    public final String username = "Yaman";
    public final String password = "Gzm-020424";

    @Test
    public void multiplicationTest() {
        navigateTo("https://webclient.catchylabs.co/");
        loginUser(username, password);
        verifyElementContainsValue(LoginPage.usernameField, "Gizem Kübra Yaman");
        openCalculator();
        clickButton(CalculatorElements.one);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.multiplication);
        clickButton(CalculatorElements.five);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickEqualityButton();
        verifyResultIsTrue();
    }

    @Test
    public void additionTest() {
        navigateTo("https://webclient.catchylabs.co/");
        loginUser(username, password);
        openCalculator();
        clickButton(CalculatorElements.five);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.addition);
        clickButton(CalculatorElements.seven);
        clickButton(CalculatorElements.five);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickEqualityButton();
        verifyResultIsTrue();
    }

    @Test
    public void subtractionTest() {
        navigateTo("https://webclient.catchylabs.co/");
        loginUser(username, password);
        openCalculator();
        clickButton(CalculatorElements.five);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.subtraction);
        clickButton(CalculatorElements.five);
        clickButton(CalculatorElements.zero);
        clickButton(CalculatorElements.zero);
        clickEqualityButton();
        verifyResultIsTrue();
    }
}
