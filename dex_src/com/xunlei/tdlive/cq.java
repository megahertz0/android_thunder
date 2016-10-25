package com.xunlei.tdlive;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.xllib.R;

// compiled from: LivePublishDialog.java
class cq implements TextWatcher {
    final /* synthetic */ co a;

    cq(co coVar) {
        this.a = coVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null && editable.length() > 15) {
            editable.delete(R.styleable.Toolbar_titleMarginTop, editable.length());
        }
    }
}
