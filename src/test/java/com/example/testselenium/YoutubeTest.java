package com.example.testselenium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class YoutubeTest {
    WebDriver driver;

    @BeforeEach // avant chaque test
    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "node_modules/chromedriver/bin/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void home(){
        String url = "https://www.youtube.com/";
        driver.get(url);
        assertEquals(driver.getTitle(),"YouTube");
    }

    @AfterEach  // apres chaque test
    public void quitBrowser() {
        driver.close();
    }
}
