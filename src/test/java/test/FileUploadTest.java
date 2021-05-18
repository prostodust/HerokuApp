package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FileUploadTest {

    /**
     * File Upload
     * Загрузить файл
     * Проверить, что имя файла на странице совпадает с именем загруженного файла
     */
    @Test
    public void fileUploadTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Kom\\IdeaProjects\\HerokuApp\\src\\test\\resources\\testpage.html");
        driver.findElement(By.xpath("//*[@id='file-submit']")).click();
        WebElement webElement = driver.findElement(By.xpath("//*[@id='uploaded-files']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), "testpage.html");
        driver.quit();
    }
}
