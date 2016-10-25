package com.xunlei.tdlive.play.a;

import android.app.Activity;
import android.view.View;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.play.view.RePlayButtonBar;
import com.xunlei.tdlive.play.view.ae;
import com.xunlei.tdlive.play.view.b.a;
import com.xunlei.tdlive.protocol.XLLiveReplayRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.y;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.android.spdy.SpdyProtocol;

// compiled from: ReplayNormalScreenLayoutPresenter.java
public class ap extends c {
    public ap(NormalScreenLayout normalScreenLayout, Activity activity) {
        super(normalScreenLayout, activity);
    }

    public void a() {
        super.a();
    }

    public void a(String str, String str2, String str3, boolean z, int i) {
        super.a(str, str2, str3, z, i);
        b(false);
        l();
        m();
    }

    private void l() {
        if (this.a.mRePlayButtonLayout == null) {
            this.a.mRePlayButtonBarStub.inflate();
            this.a.mRePlayButtonLayout = (RePlayButtonBar) this.a.findViewById(R.id.replay_btn_layout);
        }
        this.a.mRePlayButtonLayout.share_btn.setOnClickListener(this.s);
    }

    boolean e() {
        return false;
    }

    private void m() {
        new XLLiveReplayRequest(f.a().k(), f.a().l(), this.c).send(new aq(this));
    }

    public void b(boolean z) {
        super.b(z);
        this.a.mPlayButtonLayout.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.mPublishButtonLayout.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.showTopView(!z);
    }

    public void a(String str, String str2, int i, String str3) {
    }

    protected void a(View view) {
        if (this.d != null) {
            a aVar = new a();
            aVar.a(null);
            aVar.a(this.a.mContainer);
            String b = y.b(this.c);
            String str = this.d.a.c;
            String str2 = this.d.a.a;
            aVar.a(new ae.a(b, str, y.a(this.i, str2), y.b(this.i, str2), this.c, this.d.a.b));
            ae aeVar = new ae(this.i);
            aeVar.a(aVar);
            aeVar.b();
            q.e("zb_share").a("replay").a("hostid", this.d.a.b).a(SHubBatchQueryKeys.url, b);
        }
    }

    public av j() {
        return this.l;
    }

    public at k() {
        return this.m;
    }

    public void a(boolean z) {
        super.a(z);
        m();
    }
}
