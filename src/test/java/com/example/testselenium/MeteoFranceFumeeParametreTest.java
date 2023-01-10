package com.example.testselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeteoFranceFumeeParametreTest {

    private URL baseUrl;
    WebDriver driver;

    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "node_modules/chromedriver/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://meteofrance.com/");
    }


    private int getStatusCode(String url) throws IOException {
        baseUrl = new URL(url);
        HttpURLConnection http = (HttpURLConnection) baseUrl.openConnection();
        int code = http.getResponseCode();
        return code;
    }

/*
    @ParameterizedTest
    @CsvSource({"/actualites, 200", "/climat, 200", "/meteo-marine, 200"})
    public void okTest(String url, int code) throws IOException {
        int resultat = getStatusCode("https://meteofrance.com/"+ url);
        assertEquals(code,resultat);

    }
*/

    @ParameterizedTest
    @CsvSource({"/actualites, ACTUALITES METEO avec Météo-France",
            "/climat, CLIMAT FRANCE par Météo-France - Normales et relevés sur la France métropolitaine",
            "/meteo-marine,METEO MARINE par Météo-France - COTE, LARGE, BMS"})
    public void okTest(String url, String title) throws IOException {
        driver.get("https://meteofrance.com"+url);
        String resultat = driver.getTitle();
        assertEquals(title,resultat);

    }




    @ParameterizedTest
    @CsvSource({"/clima, 404", "/meteo-marines, 404"})
    public void notFoundTest(String url, int res) throws IOException {
        int code_res = getStatusCode("https://meteofrance.com/"+url);
        assertEquals(res,code_res);
    }


    @AfterEach
    public void quitBrowser(){
        driver.close();
    }
}
