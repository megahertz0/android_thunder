package com.xunlei.tdlive.usercenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.xunlei.tdlive.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: NickModifyActivity.java
class i implements TextWatcher {
    final /* synthetic */ TextView a;
    final /* synthetic */ NickModifyActivity b;

    i(NickModifyActivity nickModifyActivity, TextView textView) {
        this.b = nickModifyActivity;
        this.a = textView;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean z = true;
        String toString = editable.toString();
        CharSequence trim = toString.trim();
        if (!trim.equals(toString)) {
            editable.clear();
            editable.append(trim);
        }
        int a = v.a(editable.toString(), SimpleLog.LOG_LEVEL_DEBUG, 1);
        if (editable != null && a > 32) {
            CharSequence a2 = v.a(editable.toString(), a.ORDERED, SimpleLog.LOG_LEVEL_DEBUG, 1, BuildConfig.VERSION_NAME);
            editable.clear();
            editable.append(a2);
        }
        TextView textView = this.a;
        if (editable.length() <= 0 || !this.b.a(editable.toString())) {
            z = false;
        }
        textView.setEnabled(z);
    }
}
