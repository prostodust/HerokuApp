package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FramesTest {
    WebDriver driver;
    /**
     * Frames
     * Открыть iFrame
     * Проверить, что текст внутри параграфа равен “Your content goes here.”
     */
    @Test
    public void framesTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/frames");
        driver.findElement(By.xpath("//a[@href='/iframe']")).click();
        Assert.assertEquals(getIframeText(), "Your content goes here.");
        driver.quit();
    }

    public String getIframeText() {
        WebElement frameWebElement = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frameWebElement);
        String textFrame = driver.findElement(By.xpath("//*[@id='tinymce']")).getText();
        driver.switchTo().defaultContent();
        return textFrame;
    }
}
