package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicControlsTest {

    /**
     * Dynamic Controls
     * Найти чекбокс
     * Нажать на кнопку
     * Дождаться надписи “It’s gone”
     * Проверить, что чекбокса нет
     * Найти инпут
     * Проверить, что он disabled
     * Нажать на кнопку
     * Дождаться надписи “It's enabled!”
     * Проверить, что инпут enabled
     */
    @Test
    public void dynamicControlsTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//*[@type='checkbox']")).click();
        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='message']"), "It's gone!"));
        List<WebElement> checkboxList = driver.findElements(By.xpath("//*[@type='checkbox']"));
        Assert.assertTrue(checkboxList.isEmpty());
        Assert.assertFalse(driver.findElement(By.xpath("//*[@type='text']")).isEnabled());
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='message']"), "It's enabled!"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@type='text']")).isEnabled());
        driver.quit();
    }
}
