package com.xunlei.tdlive.view;

import android.database.DataSetObserver;
import android.view.View;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: LiveListUserFollowView.java
class n extends DataSetObserver {
    final /* synthetic */ LiveListUserFollowView a;

    n(LiveListUserFollowView liveListUserFollowView) {
        this.a = liveListUserFollowView;
    }

    public void onChanged() {
        this.a.b.setText("\u5f53\u524d\u662f\u6e38\u5ba2\uff0c\u767b\u5f55\u540e\u798f\u5229\u591a\u591a");
        this.a.b.setTextColor(-4868683);
        for (int i = 0; i < this.a.a.getChildCount(); i++) {
            this.a.a.getChildAt(i).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (this.a.f) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (this.a.g != null && i2 < this.a.g.getCount()) {
                JsonWrapper jsonWrapper = (JsonWrapper) this.a.g.getItem(i2);
                JsonWrapper object = jsonWrapper.getObject("room_info", "{}");
                if (jsonWrapper.getInt("is_playering", 0) != 0 && object.getString("roomid", BuildConfig.VERSION_NAME).length() > 0 && object.getString("stream_pull", BuildConfig.VERSION_NAME).length() > 0) {
                    if (i3 < this.a.e) {
                        View childAt = this.a.a.getChildAt(i3);
                        View view = this.a.g.getView(i2, childAt, this.a.a);
                        if (!(view == null || view == childAt)) {
                            this.a.a.addView(view);
                        }
                        view.setId(i2);
                        view.setVisibility(0);
                        view.setOnClickListener(this.a);
                        i3++;
                    }
                    i4++;
                }
                i2++;
            }
            this.a.b.setText(i4 == 0 ? "\u65e0\u5173\u6ce8\u4e3b\u64ad\u5f00\u64ad" : new StringBuilder("\u5173\u6ce8\u7684").append(i4).append("\u4f4d\u4e3b\u64ad\u6b63\u5728\u76f4\u64ad").toString());
            this.a.b.setTextColor(i4 == 0 ? -4868683 : -298434);
        }
    }

    public void onInvalidated() {
        onChanged();
    }
}
