package com.xunlei.downloadprovider.search.ui.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

// compiled from: SearchActivity.java
final class b implements TextWatcher {
    final /* synthetic */ SearchActivity a;

    b(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String editTextContent = SearchActivity.b(this.a).getEditTextContent();
        if (TextUtils.isEmpty(editTextContent)) {
            SearchActivity.c(this.a);
        } else if (SearchActivity.d(this.a)) {
            SearchActivity.a(this.a, editTextContent);
        }
        SearchActivity.e(this.a);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
