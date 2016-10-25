package com.xunlei.tdlive.play.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.play.view.ConnectMicView;
import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.play.view.NormalScreenLayout$a;
import com.xunlei.tdlive.play.view.SignalLevelView;
import com.xunlei.tdlive.play.view.ae;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.RoomUser;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.util.y;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;

// compiled from: NormalScreenLayoutPresenter.java
public class q extends c {
    private static String v;
    public boolean u;

    // compiled from: NormalScreenLayoutPresenter.java
    public static interface a {
        void a(int i);

        void a(com.xunlei.tdlive.play.view.ah.a aVar);

        void a(com.xunlei.tdlive.play.view.u.a aVar);

        void a(boolean z);
    }

    // compiled from: NormalScreenLayoutPresenter.java
    private class b implements com.xunlei.tdlive.play.a.av.a {
        private b() {
        }

        public void a(com.xunlei.tdlive.play.view.ConnectMicView.a aVar) {
            q.this.getConnectMicView().setState(aVar);
            if (aVar == com.xunlei.tdlive.play.view.ConnectMicView.a.a && "host".equals(q.this.getConnectMicView().getTag())) {
                q.this.getConnectMicView().setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                q.this.getConnectMicView().setVisibility(0);
            }
        }

        public void a(String str) {
            q.this.b(new StringBuilder("http://img.user.kanimg.com/usrimg/").append(str).append("/300x300").toString());
            q.this.getConnectMicView().setOnClickListener(new u(this));
        }
    }

    // compiled from: NormalScreenLayoutPresenter.java
    private class c implements com.xunlei.tdlive.play.a.at.b {
        private c() {
        }

        public void a(float f, com.xunlei.tdlive.play.view.SignalLevelView.a aVar) {
            q.this.a(aVar);
            q.this.getSignalLevelView().setSignalLevel(f, aVar);
        }
    }

    static {
        v = q.class.getSimpleName();
    }

    public q(NormalScreenLayout normalScreenLayout, Activity activity) {
        super(normalScreenLayout, activity);
        this.u = false;
    }

    public void a() {
        super.a();
        o();
        n();
        n();
        m();
    }

    private void m() {
        this.a.mPlayButtonLayout.share_btn.setOnClickListener(this.s);
        this.a.mPlayButtonLayout.full_screen_btn.setOnClickListener(this.t);
        this.a.mPlayButtonLayout.chat_btn.setOnClickListener(this.t);
        this.a.mPlayButtonLayout.gif_btn.setOnClickListener(this.t);
    }

    private void n() {
        this.m = new at(this.i);
        this.m.a(new c());
    }

    private void o() {
        this.l = new av(this.i);
        this.l.a(new b());
    }

    private void p() {
        ConnectMicView connectMicView = this.a.getConnectMicView();
        if (!"host".equals(connectMicView.getTag())) {
            connectMicView.setVisibility(0);
            connectMicView.setOnClickListener(new s(this, connectMicView));
        }
    }

    private void b(String str) {
        com.xunlei.tdlive.util.a.a(d()).a(this.a.getConnectMicView().getHeadImageView(), str, com.xunlei.tdlive.util.a.a(d(), R.drawable.xllive_avatar_default));
    }

    private void a(ConnectMicView connectMicView) {
        if (connectMicView.getState() == com.xunlei.tdlive.play.view.ConnectMicView.a.a) {
            q();
            connectMicView.setState(com.xunlei.tdlive.play.view.ConnectMicView.a.b);
            b(f.a().o());
        } else if (connectMicView.getState() == com.xunlei.tdlive.play.view.ConnectMicView.a.c) {
            this.l.a(0, this.c);
        }
    }

    private void q() {
        this.l.c(this.c);
    }

    private void a(com.xunlei.tdlive.play.view.SignalLevelView.a aVar) {
        SignalLevelView signalLevelView = this.a.getSignalLevelView();
        int i = (aVar == com.xunlei.tdlive.play.view.SignalLevelView.a.b || aVar == com.xunlei.tdlive.play.view.SignalLevelView.a.c) ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH;
        signalLevelView.setVisibility(i);
    }

    public void a(String str, String str2, String str3, boolean z, int i) {
        super.a(str, str2, str3, z, i);
        this.u = z;
        this.a.mPlayButtonLayout.setLaudBitmap(e.a(d(), i));
        b(false);
        r();
    }

    protected boolean e() {
        return this.u;
    }

    public void b(boolean z) {
        super.b(z);
        if (z) {
            this.a.showBottomBar(NormalScreenLayout$a.a);
            this.a.showTopView(false);
            return;
        }
        if (e()) {
            this.a.showBottomBar(NormalScreenLayout$a.c);
        } else {
            this.a.showBottomBar(NormalScreenLayout$a.b);
        }
        this.a.showTopView(true);
    }

    public void a(String str, String str2, int i, String str3) {
        com.xunlei.tdlive.play.view.LevelUpgradeBar.a aVar = new com.xunlei.tdlive.play.view.LevelUpgradeBar.a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = i;
        aVar.d = str3;
        this.a.mLevelUpgradeBar.showLevelUpgrade(aVar);
    }

    private void r() {
        String k = f.a().k();
        String l = f.a().l();
        Object obj = this.c;
        if (!TextUtils.isEmpty(obj)) {
            new XLLiveGetRoomInfoRequest(k, l, obj).send(new t(this));
        }
    }

    private static List<d> b(List<RoomUser> list) {
        List<d> arrayList = new ArrayList();
        for (RoomUser roomUser : list) {
            d dVar = new d();
            dVar.a = roomUser.userid;
            dVar.b = roomUser.nickname;
            dVar.c = roomUser.avatar;
            dVar.d = roomUser.sign;
            dVar.e = roomUser.level;
            dVar.f = roomUser.day_coin;
            arrayList.add(dVar);
            XLog.i(v, new StringBuilder("XLLiveGetRoomInfoRequest onResponse addUser: ").append(dVar.b).toString());
        }
        return arrayList;
    }

    protected void a(View view) {
        if (this.d != null) {
            Context context = this.i;
            com.xunlei.tdlive.play.view.b.a aVar = new com.xunlei.tdlive.play.view.b.a();
            aVar.a(null);
            aVar.a(this.a.mContainer);
            String a = y.a(this.d.a.b);
            String str = this.d.a.c;
            String str2 = this.d.a.a;
            aVar.a(new com.xunlei.tdlive.play.view.ae.a(a, str, y.a(context, str2), y.b(context, str2), this.c, this.d.a.b));
            ae aeVar = new ae(context);
            aeVar.a(aVar);
            aeVar.b();
            com.xunlei.tdlive.util.q.e("zb_share").a("live").a("hostid", this.d.a.b).a(SHubBatchQueryKeys.url, a);
        }
    }

    public av j() {
        return this.l;
    }

    public at k() {
        return this.m;
    }
}
