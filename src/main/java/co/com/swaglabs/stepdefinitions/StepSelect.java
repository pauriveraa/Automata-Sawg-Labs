package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.pages.LoginPage;
import co.com.swaglabs.pages.SelectPage;
import co.com.swaglabs.utils.Utilidades;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class StepSelect {
    WebDriver driver;

    @Given("que un cliente potencial conoce la ruta de autenticacion de la pagina")
    public void queUnClientePotencialConoceLaRutaDeAutenticacionDeLaPagina() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("el usuario ingresa credenciales validas para comprar")
    public void elUsuarioIngresaCredencialesValidasParaComprar() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginSuccess("standard_user","secret_sauce");
    }

    @When("escoge los productos que desea comprar")
    public void escogeLosProductosQueDeseaComprar() {
        SelectPage selectpage = PageFactory.initElements(driver, SelectPage.class);
        System.out.println("Esta es la lista de productos: " + selectpage.getProducts());
        int tamano = selectpage.getProducts().size();
        int cantidadProductos = (int)Math.floor(Math.random()*tamano+1);
        System.out.println("cantidad de productos: "+cantidadProductos);

        Set<Integer> alreadyUsedNumbers = new HashSet<>();
        for(int i=1; i<(cantidadProductos+1);i++){
            int aleatorio = (int)(Math.random()*tamano);
            System.out.println("Posicion: "+aleatorio);
            if (!alreadyUsedNumbers.contains(aleatorio)){
                alreadyUsedNumbers.add(aleatorio);
                selectpage.getProducts().get(aleatorio).click();
            }
        }
    }

    @Then("se visualizaran en el carrito de compras")
    public void seVisualizaranEnElCarritoDeCompras() {
        SelectPage selectpage = PageFactory.initElements(driver, SelectPage.class);
        selectpage.clicBtnCart();
        final String expected = "your cart";
        String actual = selectpage.validateMessageProducts();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
    }

}
