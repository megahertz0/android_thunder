package com.xunlei.tdlive;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

// compiled from: LivePlayerDialog.java
class bd implements OnEditorActionListener {
    final /* synthetic */ au a;

    bd(au auVar) {
        this.a = auVar;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != R.id.chat_edit && i != 0 && i != 4) {
            return false;
        }
        au.n(this.a);
        return true;
    }
}
