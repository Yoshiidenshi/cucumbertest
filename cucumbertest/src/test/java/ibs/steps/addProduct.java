package ibs.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class addProduct {

    static WebDriver driver;

    @Допустим("Тестовый стенд запущен, открыта страница по адресу {string}")
    public void Тестовый_стенд_запущен_открыта_страница_по_адресу(String string) {
        System.setProperty("webdriver.edge.driver", "src\\test\\resources\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(string);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @И("Нажать кнопку {string}")
    public void нажать_кнопку(String string) throws InterruptedException {
        WebElement addButton = driver.findElement(By.xpath("//button[text()='" + string + "']"));
        addButton.click();
        Thread.sleep(300);
    }

    @И("В поле названия вводится значение {string}")
    public void заполнение_названия(String string) {
        WebElement selectInput = driver.findElement(By.xpath("//input[@id='name']"));
        selectInput.sendKeys(string);
        selectInput.click();
    }

    @И("В поле типа продукта выбирается значение {string}")
    public void выбор_типа(String string) {
        WebElement selectType = driver.findElement(By.xpath("//option[text() ='" + string + "']"));
        selectType.click();
    }


    @И("Флаг экзотичности содержит значение {string}")
    public void выбор_флага_экзотичности(String string) {
        WebElement exoticCheck = driver.findElement(By.xpath("//input[@id='exotic']"));
        boolean value;
        value = Boolean.parseBoolean(string);
        if (value) {
            exoticCheck.click();
        }
    }

    @И("Добавленные продукты {string} и {string} видны")
    public void продукты_видны(String string1, String string2) {
        WebElement exoticFruitIsDisplay = driver.findElement(By.xpath("//td[text() = '" + string1 + "']"));
        assertTrue(exoticFruitIsDisplay.isDisplayed());

        WebElement nonexoticFruitIsDisplay = driver.findElement(By.xpath("//td[text() = '" + string2 + "']"));
        assertTrue(nonexoticFruitIsDisplay.isDisplayed());
    }

    @И("Сбросить данные из меню {string} кнопкой {string}")
    public void сбросить_данные(String string1, String string2) {
        WebElement sandboxMenu = driver.findElement(By.xpath("//a[contains(text(), '" + string1 + "')]"));
        sandboxMenu.click();

        WebElement sandboxMenuClear = driver.findElement(By.xpath("//a[text()='" + string2 + "']"));
        sandboxMenuClear.click();
    }

    @И("Проверить отсутствие продуктов {string} и {string}")
    public void проверить_отсутствие(String string1, String string2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[text()='" + string1 + "']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[text()='" + string2 + "']")));
        } catch (Exception e) {
            fail("Элементы не удалены");
        }
    }

    @И("Закрыть браузер")
    public void закрыть_браузер() {
        driver.quit();
    }


}

