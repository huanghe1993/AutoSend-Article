package com.github.codedrinker.dispatcher;

import com.github.codedrinker.adapter.GithubSigninAdapter;
import com.github.codedrinker.entity.DispatchMarkdown;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @Author huanghe
 * @Date 2019/6/30 9:50
 * @Description
 */
public class CSDNMarkdownDispatcher extends AbstractDispatcher {

    public CSDNMarkdownDispatcher(WebDriver driver) {
        super(driver);
    }

    @Override
    void innerPost(DispatchMarkdown dispatchMarkdown) {
        directToPostPage();
        publishPost(dispatchMarkdown);
    }

    private void publishPost(DispatchMarkdown dispatchMarkdown) {
        String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        findElementUntil(By.className("article-bar__title--input")).sendKeys(del + dispatchMarkdown.getTitle());
        driver.findElement(By.className("editor__inner")).clear();
        String[] lines = StringUtils.split(dispatchMarkdown.getContent(),"\n");
        for (String line : lines) {
            driver.findElement(By.className("editor__inner")).sendKeys(line);
            driver.findElement(By.className("editor__inner")).sendKeys(Keys.ENTER);
            driver.findElement(By.className("editor__inner")).sendKeys(Keys.ENTER);
        }
        findElementUntil(By.className("btn-publish")).click();
        Select selType = new Select(findElementUntil(By.xpath("//select[1]")));
        selType.selectByValue("original");
        Select radChl = new Select(findElementUntil(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div[3]/div[2]/div/select")));
        radChl.selectByIndex(6);
        findElementUntil(By.className("btn-c-blue")).click();
    }

    private void directToPostPage() {
        driver.get("http://write.blog.csdn.net/mdeditor");
        // 点击开始创作按钮
        findElementUntil(By.id("btnStart")).click();
        // 点击MarkDown编辑器
        findElementUntil(By.linkText("Markdown编辑器")).click();
    }

    @Override
    void login() {
        getDriver().get("https://passport.csdn.net/account/login");
        WebElement githubSigninLink = findElementUntil(By.className("icon-github"));
        githubSigninLink.click();
        new GithubSigninAdapter(driver).signin();
    }
}
