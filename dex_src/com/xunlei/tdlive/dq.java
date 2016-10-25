package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.util.h;
import com.xunlei.xllib.R;

// compiled from: MainActivity.java
class dq implements OnClickListener {
    final /* synthetic */ MainActivity$a a;

    dq(MainActivity$a mainActivity$a) {
        this.a = mainActivity$a;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            h.a(this.a.b, e.e, "\u7528\u6237\u53d6\u6d88\u76f4\u64ad\u6062\u590d");
        } else {
            dialogInterface.dismiss();
            LivePlayerActivity.a(this.a.b, e.e, R.styleable.AppCompatTheme_checkboxStyle, e.j);
        }
        e.e = null;
    }
}
