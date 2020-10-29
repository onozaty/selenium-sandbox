## Seleniumの昔と今

昔のSelenium(Selenium 1)はSelenium RC(Remote Control)を使っていて、テスト対象のサイトにJavaScriptを埋め込んで操作するような仕組みでした。そのため、ブラウザのセキュリティの制限などを受けて動かなかったり、安定しないといった問題が良く発生しました。

Selenium 2以降でWebDriverを使うようになりました。WebDriverはブラウザ操作のためのWeb API群で、各ブラウザ毎にWebDriverの実装が用意されています。これを使って操作することで、ブラウザに依存せずに様々な操作を行うことができます。操作するブラウザを変える際には、WebDriverの実装を差し変えます。

WebDriverの実装は、各ブラウザベンダから提供されています。たとえばChrome用のWebDriverならばGoogle、Firefox用ならばMozillaといった形です。

WebDriverのAPI呼び出しは、クライアントライブラリが用意されているので、HTTPを意識する必要はなく、単なるメソッド呼び出しとして実装できます。言語はJava、Python、C#、Ruby、JavaScriptなどです。


## 参考サイト

* [SeleniumHQ Browser Automation](https://www.selenium.dev/) Seleniumの本家サイト
* [Seleniumブラウザー自動化プロジェクト :: Seleniumドキュメント](https://www.selenium.dev/documentation/ja/) Seleniumのドキュメント(日本語訳)

