package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AirplaneModeTest {
    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723/wd/hub";
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","UiAutomator2");
        dc.setCapability("appium:appPackage","com.android.settings");
        dc.setCapability("appium:appActivity",".Settings");


        driver = new AndroidDriver(new URL(appiumServerUrl),dc);

    }

    @Test
    public void test(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Network & internet\")")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/switch_widget"))).click();
//        driver.findElement(AppiumBy.id("com.android.settings:id/switchWidget")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")"))).isDisplayed());
//                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")")).isDisplayed());

    }

    @AfterTest
    public void close(){
        driver.quit();
    }

}
