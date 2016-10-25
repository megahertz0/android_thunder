package com.xunlei.downloadprovider.web.base.core;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;

// compiled from: CustomWebChromeClient.java
final class b extends WebChromeClient {
    WebChromeClient a;

    b() {
    }

    public final boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        return (this.a == null || VERSION.SDK_INT < 21) ? super.onShowFileChooser(webView, valueCallback, fileChooserParams) : this.a.onShowFileChooser(webView, valueCallback, fileChooserParams);
    }

    public final void onProgressChanged(WebView webView, int i) {
        if (this.a != null) {
            this.a.onProgressChanged(webView, i);
        } else {
            super.onProgressChanged(webView, i);
        }
    }

    public final void onReceivedTitle(WebView webView, String str) {
        if (!"\u627e\u4e0d\u5230\u7f51\u9875".equals(str) && !"null".equalsIgnoreCase(str)) {
            if (this.a != null) {
                this.a.onReceivedTitle(webView, str);
            } else {
                super.onReceivedTitle(webView, str);
            }
        }
    }

    public final void onReceivedIcon(WebView webView, Bitmap bitmap) {
        if (this.a != null) {
            this.a.onReceivedIcon(webView, bitmap);
        } else {
            super.onReceivedIcon(webView, bitmap);
        }
    }

    public final void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
        if (this.a != null) {
            this.a.onReceivedTouchIconUrl(webView, str, z);
        } else {
            super.onReceivedTouchIconUrl(webView, str, z);
        }
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        if (this.a != null) {
            this.a.onShowCustomView(view, customViewCallback);
        } else {
            super.onShowCustomView(view, customViewCallback);
        }
    }

    public final void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        if (this.a != null) {
            this.a.onShowCustomView(view, i, customViewCallback);
        } else {
            super.onShowCustomView(view, i, customViewCallback);
        }
    }

    public final void onHideCustomView() {
        if (this.a != null) {
            this.a.onHideCustomView();
        } else {
            super.onHideCustomView();
        }
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        if (this.a != null) {
            this.a.onCreateWindow(webView, z, z2, message);
        }
        return super.onCreateWindow(webView, z, z2, message);
    }

    public final void onRequestFocus(WebView webView) {
        if (this.a != null) {
            this.a.onRequestFocus(webView);
        } else {
            super.onRequestFocus(webView);
        }
    }

    public final void onCloseWindow(WebView webView) {
        if (this.a != null) {
            this.a.onCloseWindow(webView);
        } else {
            super.onCloseWindow(webView);
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        if (this.a != null) {
            this.a.onJsAlert(webView, str, str2, jsResult);
        }
        return super.onJsAlert(webView, str, str2, jsResult);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (this.a != null) {
            this.a.onJsConfirm(webView, str, str2, jsResult);
        }
        return super.onJsConfirm(webView, str, str2, jsResult);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (this.a != null) {
            this.a.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return this.a != null ? this.a.onJsBeforeUnload(webView, str, str2, jsResult) : super.onJsBeforeUnload(webView, str, str2, jsResult);
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        if (this.a != null) {
            this.a.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        } else {
            super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        }
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        if (this.a != null) {
            this.a.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        } else {
            super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }
    }

    public final void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        if (this.a != null) {
            this.a.onGeolocationPermissionsShowPrompt(str, callback);
        } else {
            super.onGeolocationPermissionsShowPrompt(str, callback);
        }
    }

    public final void onGeolocationPermissionsHidePrompt() {
        if (this.a != null) {
            this.a.onGeolocationPermissionsHidePrompt();
        } else {
            super.onGeolocationPermissionsHidePrompt();
        }
    }

    public final void onPermissionRequest(PermissionRequest permissionRequest) {
        if (this.a == null) {
            super.onPermissionRequest(permissionRequest);
        } else if (VERSION.SDK_INT >= 21) {
            this.a.onPermissionRequest(permissionRequest);
        }
    }

    public final void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
        if (this.a == null) {
            super.onPermissionRequestCanceled(permissionRequest);
        } else if (VERSION.SDK_INT >= 21) {
            this.a.onPermissionRequestCanceled(permissionRequest);
        }
    }

    public final boolean onJsTimeout() {
        return this.a != null ? this.a.onJsTimeout() : super.onJsTimeout();
    }

    public final void onConsoleMessage(String str, int i, String str2) {
        if (this.a != null) {
            this.a.onConsoleMessage(str, i, str2);
        } else {
            super.onConsoleMessage(str, i, str2);
        }
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return this.a != null ? this.a.onConsoleMessage(consoleMessage) : super.onConsoleMessage(consoleMessage);
    }

    public final Bitmap getDefaultVideoPoster() {
        return this.a != null ? this.a.getDefaultVideoPoster() : super.getDefaultVideoPoster();
    }

    public final View getVideoLoadingProgressView() {
        return this.a != null ? this.a.getVideoLoadingProgressView() : super.getVideoLoadingProgressView();
    }

    public final void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        if (this.a != null) {
            this.a.getVisitedHistory(valueCallback);
        } else {
            super.getVisitedHistory(valueCallback);
        }
    }
}
