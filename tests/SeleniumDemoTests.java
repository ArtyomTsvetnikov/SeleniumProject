import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumDemoTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void openBigTest() {
        String input = "Selenium";
        By searchFieldCss = By.cssSelector("#sb_form_q");
        WebElement searchField = driver.findElement(searchFieldCss);
        searchField.sendKeys(input);
        searchField.submit();

        WebElement searchPageField = driver.findElement(searchFieldCss);
        assertEquals(input, searchPageField.getAttribute("value"), "Значения не совпали.");
    }

    @Test
    public void linkSeleniumTest() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();

        //При переходе на сайт появляется банер, он мешает клику по ссылке.
        //Этот кусок кода нажимает кнопку "позже" на банере и он пропадает.
        WebElement banner = driver.findElement(By.xpath("//div[@aria-label=\"Позже\"]"));
        banner.click();

        List<WebElement> results = driver.findElements(By.cssSelector("h2 > a[href]"));
        clickElement(results, 0);

        ArrayList tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.selenium.dev/", currentUrl, "URL не совпали.");
    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
        System.out.println("Клик");
    }
}
