package Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
public class BaseFinal extends BaseWait {
    public WebElement returnElement(By locator, long timeoutDurationSeconds, long pollingDurationMillis) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutDurationSeconds))
                .pollingEvery(Duration.ofMillis(pollingDurationMillis))
                .ignoring(NoSuchElementException.class);

        final int elementStaleCheckCount = 5;
        for(int attempt = 1; true; ++attempt) {
            try {
                return wait.until(
                        ExpectedConditions.presenceOfElementLocated(locator)
                );
            } catch (StaleElementReferenceException ex) {
                if(attempt == elementStaleCheckCount) {
                    throw new StaleElementReferenceException("Element '" + locator.toString() + "' was not found after " + elementStaleCheckCount + " retries!");
                }
                log.warn("StaleElementReferenceException has been occurred! Retrying (" + attempt + "/5)...");
            }
        }
    }

    public WebElement locateElement(By locator) {

        WebElement element = returnElement(locator, 20, 400);
        Point loc = element.getLocation();
        log.info(String.format("Element '%s' has been located at: %d, %d", locator.toString(), loc.x, loc.y));
        return element;
    }

    public WebElement locateElementAndScrollIntoView(By locator) {
        WebElement element = locateElement(locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        log.info("Element has been scrolled into view.");
        return element;
    }

    public void navigateTo(String URL) {
        log.info("Navigating to " + URL);
        driver.get(URL);
        log.info("Navigated to " + URL);
    }
}
