package com.xunlei.downloadprovider.web.core;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.EditText;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState;
import com.xunlei.tdlive.R;

// compiled from: ThunderWebView.java
final class j extends WebChromeClient {
    final /* synthetic */ ThunderWebView a;

    j(ThunderWebView thunderWebView) {
        this.a = thunderWebView;
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        JsInterface.logForJS(consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }

    public final void onHideCustomView() {
        if (this.a.f instanceof p) {
            this.a.f;
        }
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        if (this.a.f instanceof p) {
            this.a.f;
        }
    }

    public final void onProgressChanged(WebView webView, int i) {
        this.a.e;
        new StringBuilder("onProgressChanged : newProgress = ").append(i).append(" , mCurrentShowState = ").append(this.a.g.name());
        webView.getUrl();
        if (this.a.f != null) {
            this.a.f;
        }
        if (this.a.g != CurrentShowState.show_error && i == 100) {
            this.a.setCurShowView(CurrentShowState.show_webview);
        }
    }

    public final void onReceivedTitle(WebView webView, String str) {
        this.a.t = str;
        if (this.a.f != null) {
            this.a.f.b(webView, str);
            this.a.f;
            webView.getUrl();
        }
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.confirm();
        return true;
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        Context b = this.a.o;
        try {
            jsResult.confirm();
            if (b != null && (b instanceof Activity)) {
                Builder builder = new Builder(b);
                builder.setTitle(b.getString(R.string.app_name)).setMessage(str2).setPositiveButton(b.getString(2131231062), null);
                builder.setOnCancelListener(new r(jsResult));
                builder.setCancelable(false);
                builder.create().show();
            }
        } catch (BadTokenException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (BrowserUtil.b(str)) {
            Context b = this.a.o;
            if (b != null) {
                try {
                    if (b instanceof Activity) {
                        Builder builder = new Builder(b);
                        builder.setTitle(b.getString(R.string.app_name)).setMessage(str2).setPositiveButton(b.getString(2131231062), new t(jsResult)).setNeutralButton(b.getString(2131231061), new s(jsResult));
                        builder.setOnCancelListener(new u(jsResult));
                        builder.setCancelable(false);
                        builder.create().show();
                    }
                } catch (BadTokenException e) {
                    jsResult.cancel();
                    e.printStackTrace();
                } catch (Exception e2) {
                    jsResult.cancel();
                    e2.printStackTrace();
                }
            }
        } else {
            jsResult.cancel();
        }
        return true;
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        Context b = this.a.o;
        if (b != null) {
            try {
                if (b instanceof Activity) {
                    Builder builder = new Builder(b);
                    builder.setTitle(b.getString(R.string.app_name)).setMessage(str2);
                    View editText = new EditText(b);
                    editText.setSingleLine();
                    editText.setText(str3);
                    builder.setView(editText).setPositiveButton(b.getString(2131231062), new w(jsPromptResult, editText)).setNeutralButton(b.getString(2131231061), new v(jsPromptResult));
                    builder.setOnCancelListener(new x(jsPromptResult));
                    builder.setCancelable(false);
                    builder.create().show();
                }
            } catch (BadTokenException e) {
                jsPromptResult.cancel();
                e.printStackTrace();
            } catch (Exception e2) {
                jsPromptResult.cancel();
                e2.printStackTrace();
            }
        }
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(5242880);
    }
}
