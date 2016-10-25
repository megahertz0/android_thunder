package com.tencent.open.c;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.tencent.open.a.f;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.open.web.security.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ProGuard
public class c extends b {
    public static boolean a;
    private KeyEvent b;
    private a c;

    public c(Context context) {
        super(context);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        f.b("openSDK_LOG.SecureWebView", new StringBuilder("-->dispatchKeyEvent, is device support: ").append(a).toString());
        if (!a) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() != 0) {
            return super.dispatchKeyEvent(keyEvent);
        }
        switch (keyEvent.getKeyCode()) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return super.dispatchKeyEvent(keyEvent);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                return super.dispatchKeyEvent(keyEvent);
            case R.styleable.AppCompatTheme_textColorSearchUrl:
                a.b = true;
                return super.dispatchKeyEvent(keyEvent);
            default:
                if (keyEvent.getUnicodeChar() == 0) {
                    return super.dispatchKeyEvent(keyEvent);
                }
                if (SecureJsInterface.isPWDEdit) {
                    int unicodeChar = keyEvent.getUnicodeChar();
                    if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125)) {
                        this.b = new KeyEvent(0, 17);
                        return super.dispatchKeyEvent(this.b);
                    }
                }
                return super.dispatchKeyEvent(keyEvent);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        f.b("openSDK_LOG.SecureWebView", new StringBuilder("-->onKeyDown, is device support: ").append(a).toString());
        if (!a) {
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        switch (keyEvent.getKeyCode()) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return super.onKeyDown(i, keyEvent);
            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                return super.onKeyDown(i, keyEvent);
            case R.styleable.AppCompatTheme_textColorSearchUrl:
                a.b = true;
                return super.onKeyDown(i, keyEvent);
            default:
                if (keyEvent.getUnicodeChar() == 0) {
                    return super.onKeyDown(i, keyEvent);
                }
                if (SecureJsInterface.isPWDEdit) {
                    int unicodeChar = keyEvent.getUnicodeChar();
                    if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125)) {
                        this.b = new KeyEvent(0, 17);
                        return super.onKeyDown(this.b.getKeyCode(), this.b);
                    }
                }
                return super.onKeyDown(i, keyEvent);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        f.c("openSDK_LOG.SecureWebView", new StringBuilder("-->create input connection, is edit: ").append(SecureJsInterface.isPWDEdit).toString());
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        f.a("openSDK_LOG.SecureWebView", new StringBuilder("-->onCreateInputConnection, inputConn is ").append(onCreateInputConnection).toString());
        if (onCreateInputConnection != null) {
            a = true;
            this.c = new a(super.onCreateInputConnection(editorInfo), false);
            return this.c;
        }
        a = false;
        return onCreateInputConnection;
    }
}
