package com.xunlei.downloadprovider.homepage.recommend.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.model.protocol.e.a$c;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;

// compiled from: ShortTimeVideoListAdapter.java
final class d implements OnClickListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        int positionForView = ((ShortTimeVideoListActivity) a.a(this.a)).a.getPositionForView(view);
        a.a(this.a, positionForView - 1);
        a$c com_xunlei_downloadprovider_model_protocol_e_a_c = (a$c) this.a.getItem(positionForView - 1);
        ShortMovieDetailActivity.a(a.a(this.a), a.b(this.a).a, a.b(this.a).b, From.VIDEO_CHANNEL, com_xunlei_downloadprovider_model_protocol_e_a_c.a, com_xunlei_downloadprovider_model_protocol_e_a_c.b, com_xunlei_downloadprovider_model_protocol_e_a_c.c, com_xunlei_downloadprovider_model_protocol_e_a_c.d, com_xunlei_downloadprovider_model_protocol_e_a_c.i, com_xunlei_downloadprovider_model_protocol_e_a_c.e, com_xunlei_downloadprovider_model_protocol_e_a_c.g, -1);
    }
}
