package com.qualitystream.tutorial;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class BlouseAndTShirtShoppingAutomation
{
    private WebDriver driver;

    @Before
    public void SetAutomationPage()
    {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void BuyABlouseAndATShirt()
    {
        //Agregar polera a cartera
        driver.findElement(By.cssSelector("#homefeatured > .ajax_block_product:nth-child(1) .button:nth-child(1) > span")).click();

        //Clickear boton continue shopping
        driver.findElement(By.cssSelector(".continue > span")).click();

        //Agregar blusa a cartera
        driver.findElement(By.cssSelector("#homefeatured > .ajax_block_product:nth-child(2) .button:nth-child(1) > span")).click();

        //Clickear boton proceed to checkout
        driver.findElement(By.cssSelector(".button-medium > span")).click();

        //Encontrar boton proceed to checkout (ventana de elementos agregados)
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();

        //Encontrar boton proceed to checkout (ventana para confirmar address)
        driver.findElement(By.cssSelector(".button:nth-child(4) > span")).click();

        //Aceptar terminos y condiciones
        driver.findElement(By.id("cgv")).click();

        //Encontrar boton proceed to checkout (ventana para confirmar terminos y condiciones)
        driver.findElement(By.cssSelector(".standard-checkout > span")).click();

        //Encontrar boton de metodo de pago (Para efectos de esta prueba, sera por cuenta bancaria)
        driver.findElement(By.linkText("Pay by bank wire (order processing will be longer)")).click();

        //Encontrar boton de confirmacion orden
        driver.findElement(By.cssSelector("#cart_navigation span")).click();

        //Aqui finaliza la prueba, se compara la url esperada con el resultado logrado
        String urlResultadoEsperado = "http://automationpractice.com/index.php?controller=order-confirmation&id_cart=4695322&id_module=3&id_order=443039&key=f1569c2033454af0fd3f7b873f42de6f";
        String urlResultadoLogrado = driver.getCurrentUrl();

        //Se comparan las urls, finalizando la prueba
        assertEquals(urlResultadoEsperado,urlResultadoLogrado);

    }
}