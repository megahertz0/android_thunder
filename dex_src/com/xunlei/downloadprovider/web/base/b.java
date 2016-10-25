package com.xunlei.downloadprovider.web.base;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

// compiled from: CommentDialog.java
final class b implements TextWatcher {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.h.setText(charSequence.length() + "/100");
    }

    public final void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable.toString().trim())) {
            if (this.a.g.isEnabled()) {
                this.a.g.setEnabled(false);
            }
        } else if (!this.a.g.isEnabled()) {
            this.a.g.setEnabled(true);
        }
    }
}
