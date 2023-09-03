package com.github.onozaty.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedmineTest {

    private WebDriver driver;

    @Before
    public void setupTest() {

        ChromeOptions options = new ChromeOptions()
                .addArguments("--headless=new")
                .addArguments("-window-size=1280,1024");
        driver = new ChromeDriver(options);
    }

    @Test
    public void チケット作成() {

        driver.get("http://192.168.33.10/");

        login();

        driver.findElement(By.cssSelector("a[href=\"/projects\"]")).click();
        driver.findElement(By.cssSelector("a[href=\"/projects/a\"]")).click();

        driver.findElement(By.id("new-object")).click();
        driver.findElement(By.cssSelector("a.new-issue-sub")).click();

        // チケットの作成
        String subject = RandomStringUtils.randomAlphanumeric(20);
        driver.findElement(By.id("issue_subject")).sendKeys(subject);

        String description = RandomStringUtils.randomAlphanumeric(50);
        driver.findElement(By.id("issue_description")).sendKeys(description);

        driver.findElement(By.id("issue_assigned_to_id")).click();
        {
            WebElement dropdown = driver.findElement(By.id("issue_assigned_to_id"));
            dropdown.findElement(By.cssSelector("option[value=\"1\"]")).click();
        }

        driver.findElement(By.id("issue_category_id")).click();
        {
            WebElement dropdown = driver.findElement(By.id("issue_category_id"));
            dropdown.findElement(By.cssSelector("option[value=\"2\"]")).click();
        }

        driver.findElement(By.name("commit")).click();

        // チケット作成結果の確認
        assertThat(driver.findElement(By.id("flash_notice")).getText())
                .matches("チケット #[0-9]+ が作成されました。");

        assertThat(driver.findElement(By.cssSelector("div.subject h3")).getText())
                .isEqualTo(subject);
        assertThat(driver.findElement(By.cssSelector("div.description div.wiki")).getText())
                .contains(description); // 改行など入り込むので部分一致で確認
        assertThat(driver.findElement(By.cssSelector("div.attributes div.assigned-to div.value a")).getText())
                .isEqualTo("Redmine Admin");
        assertThat(driver.findElement(By.cssSelector("div.attributes div.category div.value")).getText())
                .isEqualTo("カテゴリ2");
    }

    @Test
    public void ニュース投稿() {

        driver.get("http://192.168.33.10/");

        login();

        driver.findElement(By.cssSelector("a[href=\"/projects\"]")).click();
        driver.findElement(By.cssSelector("a[href=\"/projects/a\"]")).click();

        driver.findElement(By.id("new-object")).click();
        driver.findElement(By.cssSelector("a.new-news")).click();

        // ニュースの追加
        String title = RandomStringUtils.randomAlphanumeric(20);
        driver.findElement(By.id("news_title")).sendKeys(title);

        String description = RandomStringUtils.randomAlphanumeric(50);
        driver.findElement(By.id("news_description")).sendKeys(description);

        driver.findElement(By.name("commit")).click();

        // ニュース追加の確認
        assertThat(driver.findElement(By.id("flash_notice")).getText()).isEqualTo("作成しました。");
        assertThat(driver.findElement(By.xpath("//*[@id=\"content\"]/article[1]/header/h3")).getText())
                .isEqualTo(title);
    }

    private void login() {

        driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("login-submit")).click();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
