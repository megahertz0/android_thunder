package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LivePublishDialog.java
class cr implements OnFocusChangeListener {
    final /* synthetic */ co a;

    cr(co coVar) {
        this.a = coVar;
    }

    public void onFocusChange(View view, boolean z) {
        EditText editText = (EditText) view;
        if (z) {
            editText.setHint(BuildConfig.VERSION_NAME);
            editText.setTag(editText.getHint().toString());
            return;
        }
        editText.setHint(editText.getTag().toString());
    }
}
