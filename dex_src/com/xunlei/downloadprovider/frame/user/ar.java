package com.xunlei.downloadprovider.frame.user;

import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

// compiled from: ReportActivity.java
final class ar implements OnCheckedChangeListener {
    final /* synthetic */ ReportActivity a;

    ar(ReportActivity reportActivity) {
        this.a = reportActivity;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.a.e = Integer.parseInt((String) this.a.findViewById(i).getTag());
        if (!this.a.d.isEnabled()) {
            this.a.d.setEnabled(true);
        }
        if (i == 2131755271) {
            this.a.c.requestFocus();
            return;
        }
        this.a.j.requestFocus();
        ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.c.getWindowToken(), 0);
    }
}
