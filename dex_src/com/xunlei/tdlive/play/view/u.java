package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.protocol.XLLiveGetPlayerInfoRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.UserCenterActivity;
import com.xunlei.tdlive.util.ac;
import org.android.spdy.SpdyProtocol;

// compiled from: PlayerInfoWindowHelper.java
public class u extends b {
    private RoundImageView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;

    // compiled from: PlayerInfoWindowHelper.java
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public long e;
        public long f;
        public long g;
        public boolean h;
        public String i;
        public String j;
    }

    public u(Activity activity) {
        super(activity);
    }

    protected View a(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.xllive_window_presenter_info, null);
        inflate.findViewById(R.id.close).setOnClickListener(new v(this));
        this.e = (RoundImageView) inflate.findViewById(R.id.head_image);
        this.e.setOnClickListener(new w(this));
        this.f = (TextView) inflate.findViewById(R.id.user_name);
        this.g = (TextView) inflate.findViewById(R.id.t1);
        this.h = (TextView) inflate.findViewById(R.id.t2);
        this.i = (TextView) inflate.findViewById(R.id.t3);
        this.j = (TextView) inflate.findViewById(R.id.t4);
        this.k = (TextView) inflate.findViewById(R.id.complain);
        this.k.setOnClickListener(new x(this));
        f();
        b(true);
        return inflate;
    }

    public void a(boolean z) {
        this.k.setVisibility(z ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void b(boolean z) {
        Object e = e();
        if (e instanceof a) {
            a aVar = (a) e;
            this.f.setText(aVar.c);
            this.g.setText(aVar.e);
            this.h.setText(aVar.f);
            this.i.setText(aVar.g);
            this.j.setText(ac.a("HH:mm:ss", ac.f(aVar.j) - ac.f(aVar.i), "00:00:00"));
            if (z) {
                Context context = (Context) this.b.get();
                if (context != null) {
                    com.xunlei.tdlive.util.a.a(context).a(this.e, aVar.d, com.xunlei.tdlive.util.a.a(context, R.drawable.xllive_avatar_default));
                }
            }
        }
    }

    public static a a(com.xunlei.tdlive.play.a.c.a aVar) {
        a aVar2 = new a();
        aVar2.b = aVar.a.b;
        aVar2.c = aVar.a.a;
        aVar2.d = aVar.a.c;
        aVar2.e = 0;
        aVar2.f = 0;
        aVar2.g = 0;
        aVar2.i = aVar.f;
        aVar2.j = aVar.g;
        return aVar2;
    }

    public void f() {
        a aVar = (a) e();
        new XLLiveGetPlayerInfoRequest(f.a().k(), f.a().l(), aVar.b, aVar.a).send(new y(this, aVar));
    }

    private void g() {
        Context context = (Context) this.b.get();
        if (context != null) {
            Object e = e();
            if (e instanceof a) {
                a();
                a aVar = (a) e;
                UserCenterActivity.a(context, aVar.b, aVar.c, "roomhostavatar");
            }
        }
    }
}
