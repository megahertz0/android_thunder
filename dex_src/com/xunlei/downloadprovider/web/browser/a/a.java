package com.xunlei.downloadprovider.web.browser.a;

import android.graphics.Bitmap;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: XLWebChromeClient.java
public class a extends WebChromeClient {
    public WebChromeClient a;

    public void onProgressChanged(WebView webView, int i) {
        if (this.a != null) {
            this.a.onProgressChanged(webView, i);
        } else {
            super.onProgressChanged(webView, i);
        }
    }

    public void onReceivedTitle(WebView webView, String str) {
        if (this.a != null) {
            this.a.onReceivedTitle(webView, str);
        } else {
            super.onReceivedTitle(webView, str);
        }
    }

    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        if (this.a != null) {
            this.a.onReceivedIcon(webView, bitmap);
        } else {
            super.onReceivedIcon(webView, bitmap);
        }
    }

    @Deprecated
    public void onConsoleMessage(String str, int i, String str2) {
        if (this.a != null) {
            this.a.onConsoleMessage(str, i, str2);
        } else {
            super.onConsoleMessage(str, i, str2);
        }
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return this.a != null ? this.a.onConsoleMessage(consoleMessage) : super.onConsoleMessage(consoleMessage);
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return this.a != null ? this.a.onJsAlert(webView, str, str2, jsResult) : super.onJsAlert(webView, str, str2, jsResult);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return this.a != null ? this.a.onJsConfirm(webView, str, str2, jsResult) : super.onJsConfirm(webView, str, str2, jsResult);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return this.a != null ? this.a.onJsBeforeUnload(webView, str, str2, jsResult) : super.onJsBeforeUnload(webView, str, str2, jsResult);
    }
}
