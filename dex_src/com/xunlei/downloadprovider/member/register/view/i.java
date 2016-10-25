package com.xunlei.downloadprovider.member.register.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.xllib.a.b;

// compiled from: VerifyCodeDialog.java
final class i implements OnClickListener {
    final /* synthetic */ e a;

    i(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        if (e.a(this.a).length() == 0) {
            a.a(this.a.getContext(), R.string.verifycode_empty);
        } else if (!b.a(e.b(this.a))) {
            a.b(this.a.getContext(), R.string.verifycode_empty);
        } else if (e.c(this.a) != null) {
            e.c(this.a).a(this.a.c.getText().toString());
        }
    }
}
