package com.umeng.socialize.editorpage;

import android.text.Editable;
import android.text.TextWatcher;

// compiled from: ShareActivity.java
class b implements TextWatcher {
    final /* synthetic */ ShareActivity a;

    b(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.z = this.a.d();
    }
}
