package com.example.testselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndeedJunitTest {

    WebDriver driver;

    @BeforeEach // avant chaque test
    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver", "node_modules/chromedriver/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://fr.indeed.com");
    }

    @Test
    public void home(){
        String titreAttendu = "Emploi | Indeed";
        String lienAttendu = "À propos";
        String guide = "Guide";

        String titreObtenu = driver.getTitle();
        assertEquals(titreObtenu,titreAttendu);

        String lienObtenu = "";
        String lienGuide= "";

        List<WebElement> liens = driver.findElements(By.tagName("a"));
        for(WebElement webElement : liens){
            String lien = webElement.getText();
            if(lien.equals(lienAttendu)){
                lienObtenu = lien;
            }
            if(lien.contains(guide)){
                lienGuide = lien;
            }
        }
        assertEquals(lienObtenu, lienAttendu);
        assertTrue(lienGuide.contains("Guide"));


        List<WebElement> elements = driver.findElements(By.id("jobsearch"));
       assertEquals(elements.size(),1);

    }

    @Test
    public void search() throws InterruptedException {
        WebElement form = driver.findElement(By.tagName("form"));
        WebElement input = form.findElement(By.tagName("input"));
        if (input.getAttribute("name").contentEquals("q")){
            input.sendKeys("développeur web");
            input.sendKeys(Keys.TAB);
            driver.findElement(By.name("l")).sendKeys("Aix-en-Provence");
            driver.findElement(By.cssSelector(".yosegi-InlineWhatWhere-primaryButton")).submit();
            driver.findElement(By.xpath("//*[@id=\"google-Only-Modal\"]/div/div[1]/button\n")).click();
            driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]\n")).click();
            assertTrue(driver.findElement(By.id("filter-radius")).getTagName().equals("button"));
        }
    }




    @AfterEach  // apres chaque test
    public void quitBrowser() {
        driver.close();
    }
}
