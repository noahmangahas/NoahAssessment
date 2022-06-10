package com.accesshq.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PizzaHQTestSuite {
    WebDriver driver = new ChromeDriver();

    @BeforeEach
    private void setup() {
        driver.manage().window().maximize();
        driver.get("https://d3eczsrdruv8hn.cloudfront.net");
    }

    @Test
    public void testSignup() throws InterruptedException {
        //Arrange
        var page = new MainPage(driver);
        page.clickLogin();
        page.clickSignupAsNewMember();

        //Act
        page.clickSubmitSignup();

        //Assert
        Assertions.assertEquals("Username is required", driver.findElement(By.id("username-err")).getText());
        Assertions.assertEquals("Password is required", driver.findElement(By.id("password-err")).getText());
        Assertions.assertEquals("Please confirm your password", driver.findElement(By.id("confirm-err")).getText());
    }

    @Test
    public void testSignupRequiredFields() throws InterruptedException {
        //Arrange
        var page = new MainPage(driver);
        page.clickLogin();
        page.clickSignupAsNewMember();

        //Act
        page.fillForm();
        page.clickSubmitSignup();

        //Assert
        Assertions.assertEquals("Username must be minimum of 6 characters", driver.findElement(By.id("username-err")).getText());
        Assertions.assertEquals("Password must be minimum of 8 characters", driver.findElement(By.id("password-err")).getText());
        Assertions.assertEquals("Your passwords do not match", driver.findElement(By.id("confirm-err")).getText());
    }

    @Test
    public void testUsernameExists() throws InterruptedException {
        //Arrange
        var page = new MainPage(driver);
        page.clickLogin();
        page.clickSignupAsNewMember();

        //Act
        page.enterUsername("donaldtrump");
        page.clickSubmitSignup();

        //Assert
        Assertions.assertEquals("Username already exists", driver.findElement(By.id("username-err")).getText());
    }

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        //Arrange
        var page = new MainPage(driver);
        page.clickLogin();
        page.clickSignupAsNewMember();

        //Act
        page.enterUsername("robinhood");
        page.enterPassword("letmein2019", "letmein2019");
        page.clickSubmitSignup();

        //Assert
//        Assertions.assertFalse(driver.findElement(By.id("username-err")).isDisplayed());
//        Assertions.assertFalse(driver.findElement(By.id("password-err")).isDisplayed());
        Assertions.assertEquals("Thanks robinhood, you can now login.", page.getPopupMessage());
    }

    @Test
    public void testMenuPage() {
        //Arrange
        var page = new MainPage(driver);
        page.clickMenu();
        page.clickTab();

        //Act


        //Assert
    }

    @AfterEach
    private void cleanup() {
        driver.quit();
    }

}
