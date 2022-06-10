package com.accesshq.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("[aria-label='login or signup']")).click();
//        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.
//                visibilityOfElementLocated(By.id("loginDialog")));
        //Todo
        //Cleanup Thread sleep
        Thread.sleep(500);
    }

    public void clickSignupAsNewMember() throws InterruptedException {
        driver.findElement(By.linkText("Sign Up")).click();
        Thread.sleep(500);
    }

    public void clickSubmitSignup() {
        driver.findElement(By.cssSelector("[aria-label='signup']")).click();
    }

    public void enterUsername(String username) {
        driver.findElement(By.id("input-91")).sendKeys(username);
    }

    public void enterPassword(String password, String confirmPassword) {
        driver.findElement(By.id("input-94")).sendKeys(password);
        driver.findElement(By.id("input-97")).sendKeys(confirmPassword);
    }

    public void fillForm() {
        enterUsername("abc");
        enterPassword("abc", "def");
    }

    public String getPopupMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.
                visibilityOfElementLocated(By.className("popup-message")));
        return driver.findElement(By.className("popup-message")).getText();

    }

    public void clickMenu() {
        driver.findElement(By.cssSelector("[aria-label='menu']")).click();
    }

    public void clickTab() {
        var tabs = driver.findElements(By.cssSelector("[role='tab']"));
        int i = 0;

        for (var tab : tabs) {
            if (i == 1) {
                tab.click();
            }
            i++;
        }

    }
}
