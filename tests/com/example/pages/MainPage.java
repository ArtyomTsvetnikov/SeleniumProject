package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://www.bing.com

public class MainPage {

    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    public void sendText(String text) {
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Введен текст: " + text);
    }

    public void closeBanner() {
        WebElement banner = driver.findElement(By.xpath("//div[@aria-label=\"Позже\"]"));
        banner.click();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

