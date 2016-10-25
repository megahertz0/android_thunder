package com.xunlei.tdlive;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

// compiled from: LivePublishDialog.java
class cp implements OnEditorActionListener {
    final /* synthetic */ co a;

    cp(co coVar) {
        this.a = coVar;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return i == R.id.publish_title || i == 0;
    }
}
