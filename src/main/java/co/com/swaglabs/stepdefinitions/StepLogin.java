package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.data.basededatos.DataUser;
import co.com.swaglabs.data.basededatos.DataUserDTO;
import co.com.swaglabs.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;
import java.util.Map;

public class StepLogin {
    WebDriver driver;
    Map<Integer, DataUser> userMap = new DataUserDTO().getUsers();
    int position= 1;

    @Given("que un cliente potencial conoce la ruta de autenticacion")
    public void queUnClientePotencialConoceLaRutaDeAutenticacion() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("el usuario ingresa credenciales validas")
    public void elUsuarioIngresaCredencialesValidas() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.sendInfoUserName(userMap.get(position).getUsername());
        loginPage.sendIfoPassword(userMap.get(position).getPassword());
        loginPage.clickBtnLogin();
    }

    @Then("tendra una autenticacion correcta")
    public void tendraUnaAutenticacionCorrecta() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        final String expected = "products";
        String actual = loginPage.validateMessageProducts();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));
    }
}
