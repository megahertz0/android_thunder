package com.xunlei.tdlive.a;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetMsgListRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: MsgListAdapter.java
public class p extends i<String> implements OnScrollListener {
    private int b;
    private int c;
    private int d;
    private int e;
    private long f;
    private long g;

    // compiled from: MsgListAdapter.java
    class a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        View e;
        int f;

        a() {
        }
    }

    public p(com.xunlei.tdlive.a.j.a aVar) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.g = 0;
        this.f = 30000;
        this.a = aVar;
    }

    public void a(String str, boolean z, boolean z2) {
        if ((!z2 || SystemClock.uptimeMillis() - this.g >= this.f) && b()) {
            if (this.a != null) {
                this.a.a(str, false, z2);
            }
            if (z2) {
                this.b++;
                this.d = this.b;
            } else {
                this.d = this.c;
            }
            XLog.d("MsgListAdapter", new StringBuilder("load page:").append(this.d).append(", loadmore:").append(z2).append(", mTotalPage:").append(this.b).append(", mCurPage:").append(this.c).toString());
            new XLLiveGetMsgListRequest(f.a().k(), f.a().l(), this.d, 100).send(new q(this, z2, str));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xllive_msg_list_item, viewGroup, false);
            a aVar2 = new a();
            view.setTag(aVar2);
            aVar2.a = (TextView) view.findViewById(R.id.time);
            aVar2.b = (TextView) view.findViewById(R.id.title);
            aVar2.c = (ImageView) view.findViewById(R.id.image);
            aVar2.d = (TextView) view.findViewById(R.id.detail);
            aVar2.e = view.findViewById(R.id.more);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.f = i;
        JsonWrapper a = a(i);
        if (a != null) {
            aVar.a.setText(a.getString("create_time", "0000\u5e7400\u670800\u65e5 \u4e0a\u534800:00"));
            aVar.b.setText(a.getString(SetKey.TITLE, BuildConfig.VERSION_NAME));
            aVar.d.setText(a.getString(WeiXinShareContent.TYPE_TEXT, BuildConfig.VERSION_NAME));
            String string = a.getString("after_open", BuildConfig.VERSION_NAME);
            String string2 = a.getString(SHubBatchQueryKeys.url, BuildConfig.VERSION_NAME);
            String string3 = a.getString("img", BuildConfig.VERSION_NAME);
            if (string3.length() <= 0 || a.getString("msg_type", WeiXinShareContent.TYPE_TEXT).equals(WeiXinShareContent.TYPE_TEXT)) {
                aVar.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                if (!string.equals("go_url") || string2.length() <= 0) {
                    aVar.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar.e.setVisibility(0);
                }
                aVar.c.setVisibility(0);
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.c, string3);
            }
        }
        return view;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.e = i;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.e > 0) {
            this.c = i / 100;
            if (i3 - i <= i2 * 5) {
                c(null);
            }
        }
    }
}
