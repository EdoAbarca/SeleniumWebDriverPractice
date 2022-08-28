package com.qualitystream.tutorial;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class MultipleDressesShoppingAutomation
{
    private WebDriver driver;

    @Before
    public void SetAutomationPage()
    {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver"); //A corregir en linux
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Buy3PrintedDresses()
    {
        //Usar barra de busqueda
        driver.findElement(By.id("search_query_top")).click();
        
        //Enviar vestido a buscar
        driver.findElement(By.id("search_query_top")).sendKeys("printed dress");

        //Realizar busqueda
        driver.findElement(By.name("submit_search")).click();

        //Encontrar vestido buscado
        driver.findElement(By.cssSelector(".ajax_block_product:nth-child(2) .button:nth-child(2) > span")).click();

        //Encontrar el boton de incremento de cantidad compra y clickearlo 2 veces
        driver.findElement(By.cssSelector(".icon-plus")).click();
        driver.findElement(By.cssSelector(".icon-plus")).click();

        //Encontrar boton add cart
        driver.findElement(By.cssSelector(".exclusive > span")).click();

        //Encontrar boton checkout
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
        String urlResultadoEsperado = "http://automationpractice.com/index.php?controller=order-confirmation&id_cart=4685881&id_module=3&id_order=443018&key=f1569c2033454af0fd3f7b873f42de6f";
        String urlResultadoLogrado = driver.getCurrentUrl();

        //Se comparan las urls, finalizando la prueba
        assertEquals(urlResultadoEsperado,urlResultadoLogrado);
        
    }
}