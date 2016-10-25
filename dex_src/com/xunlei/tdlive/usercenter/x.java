package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.xunlei.tdlive.user.f;

// compiled from: UserListAdapter.java
class x implements OnClickListener {
    final /* synthetic */ TextView a;
    final /* synthetic */ e b;
    final /* synthetic */ v c;

    x(v vVar, TextView textView, e eVar) {
        this.c = vVar;
        this.a = textView;
        this.b = eVar;
    }

    public void onClick(View view) {
        v.a(this.c, f.a(this.a.getContext()).b());
        this.b.onClick(view);
    }
}
