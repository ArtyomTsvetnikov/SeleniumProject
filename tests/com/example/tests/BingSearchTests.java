package com.example.tests;

import com.example.pages.MainPage;
import com.example.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTests {
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
    public void searchFieldTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Значения не совпали.");
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);
        mp.closeBanner();

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);

        rp.switchToNewTab();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.selenium.dev/", currentUrl, "URL не совпали.");
    }


}
