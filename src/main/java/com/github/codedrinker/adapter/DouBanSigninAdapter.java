package com.github.codedrinker.adapter;

import com.github.codedrinker.util.Loader;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author huanghe
 * @Date 2019/6/30 9:50
 * @Description
 */
public class DouBanSigninAdapter {
    private WebDriver driver;

    public DouBanSigninAdapter(WebDriver driver) {
        this.driver = driver;
    }

    public void signin() {
        String currentWindow = driver.getWindowHandle();
        for (String s : driver.getWindowHandles()) {
            if (!StringUtils.equals(currentWindow, s)) {
                driver.switchTo().window(s);
            }
        }
        WebElement usernameInput = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("inp-alias")));
        usernameInput.sendKeys(Loader.load().getJianshuUsername());
        driver.findElement(By.id("inp-pwd")).sendKeys(Loader.load().getJianshuPassword());
        driver.findElement(By.name("confirm")).click();
    }
}
