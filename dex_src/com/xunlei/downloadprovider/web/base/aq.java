package com.xunlei.downloadprovider.web.base;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.frame.user.ReportActivity;

// compiled from: ShortMovieDetailFragment.java
final class aq implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    aq(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        c cVar = ShortMovieDetailFragment.C(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        Intent intent = new Intent(ShortMovieDetailFragment.F(this.a), ReportActivity.class);
        intent.putExtra("report_target", 1);
        intent.putExtra("comment_id", cVar.a);
        intent.putExtra("comment_res_id", ShortMovieDetailFragment.G(this.a).g);
        intent.putExtra("comment_source_id", ShortMovieDetailFragment.G(this.a).a);
        this.a.startActivity(intent);
        ShortMovieDetailFragment.C(this.a).dismiss();
        bk.a(cVar.a, "jubao");
    }
}
