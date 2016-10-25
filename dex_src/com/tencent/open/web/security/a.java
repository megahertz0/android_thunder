package com.tencent.open.web.security;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.tencent.open.a.f;

// compiled from: ProGuard
public class a extends InputConnectionWrapper {
    public static String a;
    public static boolean b;
    public static boolean c;

    static {
        b = false;
        c = false;
    }

    public a(InputConnection inputConnection, boolean z) {
        super(inputConnection, z);
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        c = true;
        a = charSequence.toString();
        f.a("openSDK_LOG.CaptureInputConnection", new StringBuilder("-->setComposingText: ").append(charSequence.toString()).toString());
        return super.setComposingText(charSequence, i);
    }

    public boolean commitText(CharSequence charSequence, int i) {
        c = true;
        a = charSequence.toString();
        f.a("openSDK_LOG.CaptureInputConnection", new StringBuilder("-->commitText: ").append(charSequence.toString()).toString());
        return super.commitText(charSequence, i);
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            f.c("openSDK_LOG.CaptureInputConnection", "sendKeyEvent");
            a = String.valueOf((char) keyEvent.getUnicodeChar());
            c = true;
            f.b("openSDK_LOG.CaptureInputConnection", new StringBuilder("s: ").append(a).toString());
        }
        f.b("openSDK_LOG.CaptureInputConnection", new StringBuilder("-->sendKeyEvent: ").append(a).toString());
        return super.sendKeyEvent(keyEvent);
    }
}
