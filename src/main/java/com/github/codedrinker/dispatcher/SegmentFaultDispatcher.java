package com.github.codedrinker.dispatcher;

import com.github.codedrinker.entity.DispatchMarkdown;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author huanghe
 * @Date 2019/6/30 9:50
 * @Description
 */
public class SegmentFaultDispatcher extends AbstractDispatcher {

    public SegmentFaultDispatcher(WebDriver driver) {
        super(driver);
    }

    @Override
    void innerPost(DispatchMarkdown dispatchMarkdown) {
        directToPostPage();
        publishPost(dispatchMarkdown);
    }

    private void publishPost(DispatchMarkdown dispatchMarkdown) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("撰写文章 - SegmentFault 思否"));
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myTitle")))
                .sendKeys(dispatchMarkdown.getTitle());
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myEditor")))
                .sendKeys(dispatchMarkdown.getContent());


        WebElement tagsElement = driver.findElement(By.className("sf-typeHelper--fortags")).findElement(By.tagName("input"));
        tagsElement.sendKeys(dispatchMarkdown.getTags());
        try {
            Thread.sleep(5000L);
            tagsElement.sendKeys(Keys.ENTER);
        } catch (InterruptedException e) {
        }
        driver.findElement(By.id("publishIt")).click();
    }

    private void directToPostPage() {
        // 在设置时间内，默认每隔一段时间检测一次当前页面元素是否存在，如果超过设置时间检测不到则抛出异常。
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.className("fa-file-text-o")));
        driver.get("https://segmentfault.com/record");
    }

    @Override
    void login() {
        // 使用get方法来打开segmentfault的登录页面
        getDriver().get("https://segmentfault.com/user/login");
        // 通过通过name定位，sendKeys()方法模拟键盘向输入框里输入内容。
        getDriver().findElement(By.name("username")).sendKeys(getConfiguration().getSfUsername());
        getDriver().findElement(By.name("password")).sendKeys(getConfiguration().getSfPassword());
        getDriver().findElement(By.tagName("form")).submit();
    }
}
