package co.com.swaglabs.stepdefinitions;

import co.com.swaglabs.data.basededatos.DataUser;
import co.com.swaglabs.data.basededatos.DataUserDTO;
import co.com.swaglabs.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class StepUserLocked {

    WebDriver driver;
    Map<Integer, DataUser> userMap = new DataUserDTO().getUsers();

    @When("el usuario ingresa sus credenciales")
    public void elUsuarioIngresaSusCredenciales() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.sendInfoUserName(userMap.get(2).getUsername());
        loginPage.sendIfoPassword(userMap.get(2).getPassword());
        loginPage.clickBtnLogin();
    }

    @Then("se le responde con un mensaje de usuario bloqueado")
    public void seLeRespondeConUnMensajeDeUsuarioBloqueado() {

    }
/*
    UserLockedPage userlockedpage = PageFactory.initElements(driver, UserLockedPage.class);
    final String expected = "Epic sadface: Username and password do not match any user in this service";
    String actual = UserLockedPage.validateMessageUserLocked();
        Assertions.assertEquals(expected, actual.toLowerCase(Locale.ROOT));

 */

}
