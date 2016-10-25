package com.xunlei.downloadprovider.frame.user;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: HistoryCommentItemFragment.java
final class k implements OnClickListener {
    final /* synthetic */ HistoryCommentItemFragment a;

    k(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onClick(View view) {
        c cVar = HistoryCommentItemFragment.d(this.a).b;
        long j = cVar.a;
        String str = cVar.s.a;
        LoginHelper.a();
        boolean c = LoginHelper.c();
        com.xunlei.downloadprovidercommon.a.c a = a.a("android_personal_account", "personal_space_discuss_submit");
        a.a("movieid", str);
        a.a("discussid", String.valueOf(j));
        a.a("level", j == -1 ? "0" : com.xunlei.analytics.b.c.f);
        a.a("is_login", c ? com.xunlei.analytics.b.c.f : "0");
        d.a(a);
        LoginHelper.a();
        if (LoginHelper.c()) {
            this.a.a();
            return;
        }
        Intent intent = new Intent(HistoryCommentItemFragment.c(this.a), LoginActivity.class);
        intent.putExtra("login_from", "user_discuss");
        this.a.getActivity().startActivityForResult(intent, 1101);
    }
}
