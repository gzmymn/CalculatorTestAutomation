package Base;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseInitial {
    protected final Browser browser = Browser.Chrome;
    protected Platform platform;

    protected Logger log;
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected Actions actions;

    protected final String sep = System.getProperty("file.separator");
    protected final String projectPath = System.getProperty("user.dir");

    @BeforeEach
    public void initialiseTest() {
        platform = getPlatform();
        log = LogManager.getLogger(getClass());
        driver = getDriver();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.manage().window().maximize();
        log.info("Driver has been initialised.");
    }

    private WebDriver getDriver() {
        switch(browser) {
            case Chrome:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(
                        new ChromeOptions() {{
                            addArguments("--disable-notifications");
                        }}
                );
            case Firefox:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(
                        new FirefoxOptions() {{
                            addPreference("dom.webnotifications.enabled", false);
                        }}
                );
        }
        throw new IllegalArgumentException("Unsupported Browser!");
    }

    private Platform getPlatform() {
        if(SystemUtils.IS_OS_WINDOWS) {
            return Platform.Win32;
        }
        if(SystemUtils.IS_OS_MAC) {
            return Platform.MacOS;
        }
        if(SystemUtils.IS_OS_LINUX) {
            return Platform.Linux;
        }
        throw new IllegalStateException("Unsupported Operating System!");
    }

    /** Closes the webdriver that works in the background if the window gets closed unexpectedly */
    public void killDriverProcess() {
        log.error("NoSuchWindowException has been thrown. Driver process kill command will be executed...");
        String browserDriver = "";
        if(browser == Browser.Chrome) {
            browserDriver = "chromedriver";
        } else {
            browserDriver = "geckodriver";
        }

        String processKillCommandStr = "";
        if(platform == Platform.Win32) {
            processKillCommandStr = String.format("taskkill /f /im %s.exe", browserDriver);
        } else {
            processKillCommandStr = String.format("pkill -f  %s", browserDriver);
        }
        try {
            Runtime.getRuntime().exec(processKillCommandStr);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info(processKillCommandStr + " command has been executed.");
    }

    @AfterEach
    private void destroyTest() {
        driver.quit();
        log.info("The driver has been closed.");
    }

}

enum Platform {
    Win32,
    MacOS,
    Linux
}

enum Browser {
    Chrome,
    Firefox
}
