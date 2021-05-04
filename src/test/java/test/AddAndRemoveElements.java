package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddAndRemoveElements {

    //    Add/Remove Elements - добавить 2 элемента, удалить элемент, проверить количество элементов
    @Test
    public void addElement() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElementButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        for (int i = 1; i < 3; i++) {
            addElementButton.click();
            WebElement deleteButton = driver.findElement(By.xpath("//*[@onclick='deleteElement()'][" + i + "]"));
            boolean isDeleteButtonDisplayed = deleteButton.isDisplayed();
            Assert.assertTrue(isDeleteButtonDisplayed);
        }
        driver.quit();
    }

    @Test
    public void removeElement() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        for (int i = 1; i < 10; i++) { //создаем тестовые данные
            driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        }
        int countElement = driver.findElements(By.xpath("//*[@onclick='deleteElement()']")).size();
        driver.findElement(By.xpath("//*[@onclick='deleteElement()']")).click();
        int countElementAfter = driver.findElements(By.xpath("//*[@onclick='deleteElement()']")).size();
        Assert.assertTrue(countElementAfter < countElement);
        driver.quit();
    }

    @Test
    public void numberOfElements() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        int expectedQuantity = 5;
        for (int i = 0; i < expectedQuantity; i++) { //создаем тестовые данные
            driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        }
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//*[@onclick='deleteElement()']"));
        Assert.assertEquals(expectedQuantity, deleteButtons.size());
        driver.quit();
    }
}
