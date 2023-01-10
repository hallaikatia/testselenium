package com.example.testselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeteoFranceTest {

    WebDriver driver;

    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "node_modules/chromedriver/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://meteofrance.com/");
    }

    private int getStatusCode(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int code = http.getResponseCode();
        return code;
    }

    @Test
    public void homePageTest() throws IOException {
        int code = getStatusCode("https://meteofrance.com/");
        assertEquals(code,200);
    }

    @Test
    public void notFoundPageTest() throws IOException {
        int code = getStatusCode("https://meteofrance.com/meteo-marines");
        assertEquals(code,404);
    }

    @AfterEach
    public void quitBrowser(){
        driver.close();
    }
}
