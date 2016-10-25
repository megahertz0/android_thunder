package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.user.f;

// compiled from: FollowBtnOnClickListener.java
public class e implements OnClickListener {
    private boolean a;
    private a b;
    private String c;
    private int d;
    private String e;

    // compiled from: FollowBtnOnClickListener.java
    public static interface a {
        void a(boolean z);
    }

    public e(boolean z, String str, int i, a aVar) {
        this(z, str, i, ah.a(i), aVar);
    }

    public e(boolean z, String str, int i, String str2, a aVar) {
        this.a = z;
        this.c = str;
        this.d = i;
        this.b = aVar;
        this.e = str2;
    }

    public void onClick(View view) {
        f.a().a(view.getContext(), this.e, new f(this));
    }

    private void a() {
        boolean z = true;
        new XLLiveFollowRequest(f.a().k(), f.a().l(), this.c, !this.a).send(new g(this));
        int i = this.d;
        if (this.a) {
            z = false;
        }
        ah.a(i, z, this.c);
    }
}
