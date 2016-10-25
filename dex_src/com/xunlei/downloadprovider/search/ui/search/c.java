package com.xunlei.downloadprovider.search.ui.search;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.xunlei.downloadprovider.model.protocol.d.a;

// compiled from: SearchActivity.java
final class c implements OnEditorActionListener {
    final /* synthetic */ SearchActivity a;

    c(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        Object editTextContent = this.a.a.getEditTextContent();
        if (TextUtils.isEmpty(editTextContent)) {
            editTextContent = a.a().b;
        }
        if (TextUtils.isEmpty(editTextContent)) {
            this.a.showToast(2131232422);
        } else {
            this.a.b("keyin", editTextContent);
        }
        return true;
    }
}
