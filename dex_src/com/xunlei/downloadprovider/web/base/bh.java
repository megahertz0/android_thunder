package com.xunlei.downloadprovider.web.base;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.xllib.R;

// compiled from: ShortMovieDetailFragment.java
final class bh implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    bh(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        long j;
        c cVar = ShortMovieDetailFragment.w(this.a).b;
        if (cVar == null) {
            j = -1;
        } else {
            j = cVar.a;
        }
        String x = ShortMovieDetailFragment.x(this.a);
        LoginHelper.a();
        bk.b(x, j, LoginHelper.c());
        LoginHelper.a();
        if (LoginHelper.c()) {
            this.a.b();
            return;
        }
        Intent intent = new Intent(ShortMovieDetailFragment.y(this.a), LoginActivity.class);
        intent.putExtra("login_from", "user_discuss");
        ShortMovieDetailFragment.z(this.a).startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyleSmall);
    }
}
