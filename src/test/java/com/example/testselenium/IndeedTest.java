package com.example.testselenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class IndeedTest
{
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(IndeedTest.class);
        WebDriver driver;

        String titreAttendu = "Emploi | Indeed";
        String titreObtenu = "";

        System.setProperty( "webdriver.chrome.driver", "node_modules/chromedriver/bin/chromedriver");
        driver = new ChromeDriver();

        logger.info("Chargement de l'URL https://fr.indeed.com/");
        driver.get("https://fr.indeed.com/");

        titreObtenu = driver.getTitle();


        logger.info("Titre obtenu : " + titreObtenu);
        logger.info("Titre attendu : " + titreAttendu);
        // Vérifier que le titre de la page est celui obtenu
        if (titreObtenu.contentEquals(titreAttendu)){
            logger.info("Succès");
        } else {
            logger.info("Echec");
        }
        /*
            Vérifie que la page web contient un formulaire dont l’id est jobsearch
        */
        WebElement jobsearch = driver.findElement(By.id("jobsearch"));

        String resultatAttendu="form";

        logger.info("Resultat obtenu : " + jobsearch.getTagName());
        logger.info("Resultat attendu : " + resultatAttendu);

        if (jobsearch.getTagName().contentEquals(resultatAttendu)){
            logger.info("Succès");
        } else {
            logger.info("Echec");
        }

         /*
            Vérifie que l’élément h2 contient la chaîne « Recherches populaires ».

        String chaineAttendu="Recherches populaires";
        List<WebElement> h2 = driver.findElements(By.tagName("h2"));

        for(WebElement webElement : h2){


            if (webElement.getText().contains(chaineAttendu)){
                logger.info("Resultat obtenu : " + webElement.getText());
                logger.info("Resultat attendu : " + chaineAttendu);
                logger.info("Succès");
                break;
            }

        }*/

        /*
        Vérifie que la page contient un lien hypertexte dont le texte est « À propos ».
         */

        String lienAttendu= "À propos";
        List<WebElement> link = driver.findElements(By.tagName("a"));

        for(WebElement webElement : link) {
            if (webElement.getText().contains(lienAttendu)) {
                logger.info("Resultat obtenu : " + webElement.getText());
                logger.info("Resultat attendu : " + lienAttendu);
                logger.info("Succès");
                break;
            }
        }

        /*
        Vérifier que la page contient un lien hypertexte dont
        une partie du texte est « Guide ».
         */

        String attendu = "Guide";
        List<WebElement> linksAttendu= driver.findElements(By.tagName("a"));
        for(WebElement webElement : linksAttendu){
            if (webElement.getText().contains(attendu)){
                logger.info("Resultat obtenu : " + webElement.getText());
                logger.info("Resultat attendu : " + attendu);
                logger.info("Succès");
                break;
            }
        }











        // Quitter le navigateur
        driver.close();
    }
}