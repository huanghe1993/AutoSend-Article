package com.github.codedrinker.dispatcher;

import com.github.codedrinker.adapter.DouBanSigninAdapter;
import com.github.codedrinker.entity.DispatchMarkdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Author huanghe
 * @Date 2019/6/30 9:50
 * @Description
 */
public class JianshuDispatcher extends AbstractDispatcher {

    public JianshuDispatcher(WebDriver driver) {
        super(driver);
    }

    @Override
    void innerPost(DispatchMarkdown dispatchMarkdown) {
        directToPostPage();
        publishPost(dispatchMarkdown);
    }

    private void publishPost(DispatchMarkdown dispatchMarkdown) {
        findElementUntil(By.className("_24i7u")).sendKeys(dispatchMarkdown.getTitle());
        findElementUntil(By.id("arthur-editor")).sendKeys(dispatchMarkdown.getContent());
        findElementUntil(By.className("tGbI7")).click();
    }

    /**
     * 跳转到写文章的页面
     */
    private void directToPostPage() {
        driver.get("https://www.jianshu.com/writer#");
        //选择发表文章的类型
        findElementUntil(By.className("fa-plus-circle")).click();
    }

    /**
     * 简书的登录，使用的是豆瓣第三方登录授权功能
     */
    @Override
    void login() {
        getDriver().get("https://www.jianshu.com/sign_in");
        getDriver().findElement(By.className("ic-more")).click();
        getDriver().findElement(By.className("ic-douban")).click();
        new DouBanSigninAdapter(driver).signin();


    }
}
