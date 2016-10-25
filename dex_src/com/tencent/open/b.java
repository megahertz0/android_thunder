package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.a.f;
import com.umeng.a;

// compiled from: ProGuard
public abstract class b extends Dialog {
    private static final String TAG = "openSDK_LOG.JsDialog";
    protected a jsBridge;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient mChromeClient;

    protected abstract void onConsoleMessage(String str);

    public b(Context context) {
        super(context);
        this.mChromeClient = new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage == null) {
                    return false;
                }
                f.c(TAG, new StringBuilder("WebChromeClient onConsoleMessage").append(consoleMessage.message()).append(" -- From  111 line ").append(consoleMessage.lineNumber()).append(" of ").append(consoleMessage.sourceId()).toString());
                if (VERSION.SDK_INT > 7) {
                    b.this.onConsoleMessage(consoleMessage == null ? a.d : consoleMessage.message());
                }
                return true;
            }

            public void onConsoleMessage(String str, int i, String str2) {
                f.c(TAG, new StringBuilder("WebChromeClient onConsoleMessage").append(str).append(" -- From 222 line ").append(i).append(" of ").append(str2).toString());
                if (VERSION.SDK_INT == 7) {
                    b.this.onConsoleMessage(str);
                }
            }
        };
    }

    public b(Context context, int i) {
        super(context, i);
        this.mChromeClient = new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage == null) {
                    return false;
                }
                f.c(TAG, new StringBuilder("WebChromeClient onConsoleMessage").append(consoleMessage.message()).append(" -- From  111 line ").append(consoleMessage.lineNumber()).append(" of ").append(consoleMessage.sourceId()).toString());
                if (VERSION.SDK_INT > 7) {
                    b.this.onConsoleMessage(consoleMessage == null ? a.d : consoleMessage.message());
                }
                return true;
            }

            public void onConsoleMessage(String str, int i, String str2) {
                f.c(TAG, new StringBuilder("WebChromeClient onConsoleMessage").append(str).append(" -- From 222 line ").append(i).append(" of ").append(str2).toString());
                if (VERSION.SDK_INT == 7) {
                    b.this.onConsoleMessage(str);
                }
            }
        };
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.jsBridge = new a();
    }
}
