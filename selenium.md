Seleniumは、Webブラウザの操作を自動化するためのフレームワークです。主にWebアプリケーションのテストに利用されます。また、ブラウザ操作をプログラムで行うことができるので、テスト以外の自動化ツールでも利用されることがあります。(RPAの一部として使ったりなど)

Seleniumを利用してさらにWebアプリケーションのテストに特化されたSelenideやGebといったフレームワークもあります。

## Seleniumの昔と今

昔のSelenium(Selenium 1)はSelenium RC(Remote Control)を使っていて、テスト対象のサイトにJavaScriptを埋め込んで操作するような仕組みでした。そのため、ブラウザのセキュリティの制限などを受けて動かなかったり、安定しないといった問題が良く発生しました。

Selenium 2以降でWebDriverを使うようになりました。WebDriverはブラウザ操作のためのWeb API群で、各ブラウザ毎にWebDriverの実装が用意されています。これを使って操作することで、ブラウザに依存せずに様々な操作を行うことができます。操作するブラウザを変える際には、WebDriverの実装を差し変えます。

WebDriverの実装は、各ブラウザベンダから提供されています。たとえばChrome用のWebDriverならばGoogle、Firefox用ならばMozillaといった形です。

WebDriverのAPI呼び出しは、クライアントライブラリが用意されているので、HTTPを意識する必要はなく、単なるメソッド呼び出しとして実装できます。言語はJava、Python、C#、Ruby、JavaScriptなどです。

## WebDriver

* [WebDriver :: Seleniumドキュメント](https://www.selenium.dev/documentation/ja/webdriver/)

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

## Selenium IDE

* [Selenium IDE · Open source record and playback test automation for the web](https://www.selenium.dev/selenium-ide/)

Selenium IDEはブラウザ操作の記録、実行が行えるブラウザの拡張機能です。ChromeとFirefox版があります。

昔のSelenium IDEはFirefox版のみでした。Firefoxの拡張機能のWebExtensions化に伴い、まったく新しいものとして作り替えられ、今に至っています。  
当初は出来ることも少なく、旧Selenium IDEより機能がかなり落ちるものでしたが、現在ではコマンドも揃ってきて、十分使えるものとなっています。

* [Commands · Selenium IDE](https://docs.seleniumhq.org/selenium-ide/docs/en/api/commands/)

拡張機能をインストール後、Selenium IDEを起動し、ブラウザ操作のレコーディングを行います。Assertもブラウザ上で右クリックから追加することができます。 

レコーディングしたものは再度実行することができます。ファイルとしても保存できるので、再度開いて実行することも可能です。  
また保存したファイル(`*.side`)は、`selenium-side-runner`というコマンドラインツールによって実行することも出来ます。

* [Command\-line Runner · Selenium IDE](https://www.selenium.dev/selenium-ide/docs/en/introduction/command-line-runner)

for each や if 文などの制御構文も書くことができるので、コマンドを直接入力することで、複雑な動きも実現できます。

## 参考サイト

* [SeleniumHQ Browser Automation](https://www.selenium.dev/) Seleniumの本家サイト
* [Seleniumブラウザー自動化プロジェクト :: Seleniumドキュメント](https://www.selenium.dev/documentation/ja/) Seleniumのドキュメント(日本語訳)

