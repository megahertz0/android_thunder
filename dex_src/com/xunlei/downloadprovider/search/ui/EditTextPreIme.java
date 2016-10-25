package com.xunlei.downloadprovider.search.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditTextPreIme extends EditText {
    private a a;

    public static interface a {
    }

    public EditTextPreIme(Context context) {
        super(context);
    }

    public EditTextPreIme(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextPreIme(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (getContext() != null && ((InputMethodManager) getContext().getSystemService("input_method")).isActive()) {
            keyEvent.getKeyCode();
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    public void setEditBackListener(a aVar) {
        this.a = aVar;
    }
}
