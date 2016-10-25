package com.xunlei.tdlive.withdraw;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

// compiled from: VerifyCodeCheckPage.java
class g implements TextWatcher {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean z;
        boolean z2 = true;
        TextView a = f.a(this.a);
        if (editable.length() > 0) {
            z = true;
        } else {
            z = false;
        }
        a.setEnabled(z);
        TextView c = f.c(this.a);
        if (editable.length() <= 0 || f.b(this.a).getText().length() <= 0) {
            z2 = false;
        }
        c.setEnabled(z2);
    }
}
