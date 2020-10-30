Seleniumは、Webブラウザの操作を自動化するためのフレームワークです。主にWebアプリケーションのテストに利用されます。また、ブラウザ操作をプログラムで行うことができるので、テスト以外の自動化ツールでも利用されることがあります。(RPAの一部として使ったりなど)

Seleniumを利用してさらにWebアプリケーションのテストに特化されたSelenideやGebといったフレームワークもあります。

## Seleniumの昔と今

昔のSelenium(Selenium 1)はSelenium RC(Remote Control)を使っていて、テスト対象のサイトにJavaScriptを埋め込んで操作するような仕組みでした。そのため、ブラウザのセキュリティの制限などを受けて動かなかったり、安定しないといった問題が良く発生しました。

Selenium 2以降でWebDriverを使うようになりました。WebDriverはブラウザ操作のためのWeb API群で、各ブラウザ毎にWebDriverの実装が用意されています。これを使って操作することで、ブラウザに依存せずに様々な操作を行うことができます。操作するブラウザを変える際には、WebDriverの実装を差し変えます。

WebDriverの実装は、各ブラウザベンダから提供されています。たとえばChrome用のWebDriverならばGoogle、Firefox用ならばMozillaといった形です。

WebDriverのAPI呼び出しは、クライアントライブラリが用意されているので、HTTPを意識する必要はなく、単なるメソッド呼び出しとして実装できます。言語はJava、Python、C#、Ruby、JavaScriptなどです。

## WebDriver

WebDriverを使うことで、ブラウザ操作をプログラムで行うことができます。

実行する際には、各ブラウザ用のドライバを入手し、パスに設定する必要があります。
例えばChrome用のドライバ(ChromeDriver)は、下記サイトからChromeのバージョンと利用OSに応じて入手します。

* [Downloads \- ChromeDriver \- WebDriver for Chrome](https://chromedriver.chromium.org/downloads)

システムプロパティの`webdriver.chrome.driver`にダウンロードしたChromeドライバのパスを設定します。実行時の引数で設定するか、またはプログラムで設定します。

```java
System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
```

WebDriverのインスタンスを生成し、そのインスタンスを通して操作を行います。

```java
WebDriver driver = new ChromeDriver();

// サイトを開く
driver.get("https://selenium.dev");

// id=search-byの要素を選択し、abcといった文字を入力
driver.findElement(By.id("search-by")).click();
driver.findElement(By.id("search-by")).sendKeys("abc");

// id=search-buttonの要素を探してclick
driver.findElement(By.id("search-button")).click();
```

## 参考サイト

* [SeleniumHQ Browser Automation](https://www.selenium.dev/) Seleniumの本家サイト
* [Seleniumブラウザー自動化プロジェクト :: Seleniumドキュメント](https://www.selenium.dev/documentation/ja/) Seleniumのドキュメント(日本語訳)

