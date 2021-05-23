package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContextMenuTest {

    /**
     * Context Menu
     * Правый клик по элементу
     * Валидация текста на алерте
     * Закрытие алерта
     */
    @Test
    public void contextMenuTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/context_menu");
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.xpath("//*[@id='hot-spot']"))).perform();
        Assert.assertEquals(driver.switchTo().alert().getText(), "You selected a context menu");
        driver.switchTo().alert().accept();
        driver.quit();
    }
}
