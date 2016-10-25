package com.xunlei.tdlive.usercenter;

import android.widget.TextView;
import com.xunlei.tdlive.usercenter.e.a;

// compiled from: UserListAdapter.java
class w implements a {
    final /* synthetic */ TextView a;
    final /* synthetic */ v b;

    w(v vVar, TextView textView) {
        this.b = vVar;
        this.a = textView;
    }

    public void a(boolean z) {
        v.a(this.b, this.a, z);
        if (!v.a(this.b)) {
            this.b.b(this.b.getCount());
        }
    }
}
