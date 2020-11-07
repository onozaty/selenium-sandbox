package com.github.onozaty.selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class RedmineTest {

    @BeforeClass
    public static void setupClass() {

        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.browserSize = "1280x1024";
        Configuration.headless = true;
    }

    @Test
    public void チケット作成() {

        open("http://192.168.33.10/");

        login();

        $("a[href=\"/projects\"]").click();
        $("a[href=\"/projects/a\"]").click();

        $("#new-object").click();
        $("a.new-issue-sub").click();

        // チケットの作成
        String subject = RandomStringUtils.randomAlphanumeric(20);
        $("#issue_subject").sendKeys(subject);

        String description = RandomStringUtils.randomAlphanumeric(50);
        $("#issue_description").sendKeys(description);

        $("#issue_assigned_to_id").click();
        $("#issue_assigned_to_id").$("option[value=\"1\"]").click();

        $("#issue_category_id").click();
        $("#issue_category_id").$("option[value=\"2\"]").click();

        $(By.name("commit")).click();

        // チケット作成結果の確認
        $("#flash_notice").shouldHave(matchText("チケット #[0-9]+ が作成されました。"));

        $("div.subject h3").shouldHave(exactText(subject));
        $("div.description div.wiki").shouldHave(text(description)); // 改行など入り込むので部分一致で確認
        $("div.attributes div.assigned-to div.value a").shouldHave(exactText("Redmine Admin"));
        $("div.attributes div.category div.value").shouldHave(exactText("カテゴリ2"));
    }

    @Test
    public void ニュース投稿() {

        open("http://192.168.33.10/");

        login();

        $("a[href=\"/projects\"]").click();
        $("a[href=\"/projects/a\"]").click();

        $("#new-object").click();
        $("a.new-news").click();

        // ニュースの追加
        String title = RandomStringUtils.randomAlphanumeric(20);
        $("#news_title").sendKeys(title);

        String description = RandomStringUtils.randomAlphanumeric(50);
        $("#news_description").sendKeys(description);

        $(By.name("commit")).click();

        // ニュース追加の確認
        $("#flash_notice").shouldHave(exactText("作成しました。"));
        $(By.xpath("//*[@id=\"content\"]/article[1]/header/h3")).shouldHave(exactText(title));
    }

    private void login() {

        $("a[href=\"/login\"]").click();
        $("#username").sendKeys("admin");
        $("#password").sendKeys("password");
        $("#login-submit").click();
    }

    @After
    public void teardown() {
        // テスト毎にWebDriverを停止/起動
        WebDriverRunner.closeWebDriver();
    }
}
