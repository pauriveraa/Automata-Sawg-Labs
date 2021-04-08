package co.com.swaglabs.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    FluentWait<WebDriver> wait;
    private File screenshotPath;
    private File finalPathOfScreenshot;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    @FindBy(id="user-name")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;

    @FindBy(name = "login-button")
    WebElement btnLogin;

    @FindBy(xpath = "//span[@class='title']")
    WebElement messageProducts;

    public void sendInfoUserName(String user){

        userName.sendKeys(user);
        screenshotPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        finalPathOfScreenshot = new File("src/main/resources/screen/loginPage/userCreate.png");

        try {
            FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
        } catch (IOException exception) {
            try {
                Files.delete(FileSystems.getDefault().getPath(finalPathOfScreenshot.getPath()));
                FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendIfoPassword(String pass){

        password.sendKeys(pass);
        screenshotPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        finalPathOfScreenshot = new File("src/main/resources/screen/loginPage/passwordCreate.png");

        try {
            FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
        } catch (IOException exception) {
            try {
                Files.delete(FileSystems.getDefault().getPath(finalPathOfScreenshot.getPath()));
                FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void clickBtnLogin(){

        btnLogin.click();
        screenshotPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        finalPathOfScreenshot = new File("src/main/resources/screen/loginPage/autenticationCreate.png");

        try {
            FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
        } catch (IOException exception) {
            try {
                Files.delete(FileSystems.getDefault().getPath(finalPathOfScreenshot.getPath()));
                FileUtils.moveFile(screenshotPath, finalPathOfScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String validateMessageProducts(){
        messageProducts = wait.until(webDriver -> driver.findElement(By.xpath("//span[@class='title']")));
        return messageProducts.getText();
    }

    public void loginSuccess(String userName, String password){
        sendInfoUserName(userName);
        sendIfoPassword(password);
        clickBtnLogin();
    }
}

