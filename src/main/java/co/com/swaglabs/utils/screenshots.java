package co.com.swaglabs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class screenshots {

    WebDriver driver;

    public screenshots(String page, String name){
        File screenshotPath;
        File finalPathOfScreenshot;
        screenshotPath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        finalPathOfScreenshot = new File("src/main/resources/screen/" + page + "/" + name + ".png");

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

}