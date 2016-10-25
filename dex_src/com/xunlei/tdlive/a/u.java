package com.xunlei.tdlive.a;

import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.usercenter.e.a;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: RankListAdapter.java
class u implements a {
    final /* synthetic */ TextView a;
    final /* synthetic */ s b;

    u(s sVar, TextView textView) {
        this.b = sVar;
        this.a = textView;
    }

    public void a(boolean z) {
        this.a.setSelected(z);
        this.a.setText(z ? R.string.followed : R.string.follow);
        if (!s.a(this.b)) {
            this.b.b(BuildConfig.VERSION_NAME);
        }
    }
}
