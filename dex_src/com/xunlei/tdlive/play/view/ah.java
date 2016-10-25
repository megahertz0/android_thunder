package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.play.view.ah.b;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.protocol.XLLiveGetContributRankRequest;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.d;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserInfoWindowHelper.java
public class ah extends b {
    private TextView e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private View k;
    private RoundImageView l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private TextView p;
    private View q;
    private ImageView r;

    // compiled from: UserInfoWindowHelper.java
    public static interface b {
        void a(boolean z);
    }

    // compiled from: UserInfoWindowHelper.java
    public static class a {
        public String a;
        public String b;
        public long c;
        public long d;
        public String e;
        public String f;
        public boolean g;
        public b h;
        public boolean i;
        public String j;
        public boolean k;
        public boolean l;
        public boolean m;
        public LevelInfo n;
        public String o;
        public long p;
        public boolean q;
        public String r;

        public a() {
            this.q = true;
        }
    }

    public ah(Activity activity) {
        super(activity);
    }

    protected View a(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.xllive_window_visitor_info, null);
        inflate.setLayoutParams(new LayoutParams(-1, (int) d.a((Context) this.b.get(), 176.0f)));
        this.f = inflate.findViewById(R.id.close);
        this.f.setOnClickListener(new ai(this));
        this.e = (TextView) inflate.findViewById(R.id.concern);
        this.e.setOnClickListener(new al(this));
        this.g = (TextView) inflate.findViewById(R.id.sign);
        this.l = (RoundImageView) inflate.findViewById(R.id.avatar);
        this.i = (TextView) inflate.findViewById(R.id.left_text);
        this.j = (TextView) inflate.findViewById(R.id.right_text);
        this.k = inflate.findViewById(R.id.mid_text);
        this.h = (TextView) inflate.findViewById(R.id.user_name);
        this.o = (ImageView) inflate.findViewById(R.id.user_level);
        this.m = (TextView) inflate.findViewById(R.id.ban);
        this.m.setOnClickListener(new an(this));
        this.n = (TextView) inflate.findViewById(R.id.complain);
        this.n.setOnClickListener(new ao(this));
        this.p = (TextView) inflate.findViewById(R.id.user_uuid);
        this.q = inflate.findViewById(R.id.guard_frame);
        this.r = (ImageView) this.q.findViewById(R.id.guard_avatar);
        return inflate;
    }

    private void b(boolean z) {
        Object e = e();
        if (e instanceof a) {
            a aVar = (a) e;
            new XLLiveFollowRequest(f.a().k(), f.a().l(), aVar.f, z).send(new ap(this, aVar, z));
            com.xunlei.tdlive.usercenter.ah.a(SimpleLog.LOG_LEVEL_ERROR, z, aVar.f);
        }
    }

    public void b() {
        Object e = e();
        if (e instanceof a) {
            a(((a) e).f);
        }
        super.b();
    }

    private void a(String str) {
        String k = f.a().k();
        String l = f.a().l();
        new XLLiveGetOtherUserInfoRequest(k, l, str, ((a) e()).j).send(new aq(this));
        if (((a) e()).i) {
            new XLLiveGetContributRankRequest(k, l, str, "sum").send(new ar(this));
        }
    }

    public void a(boolean z) {
        Object e = e();
        if (e instanceof a) {
            a aVar = (a) e;
            this.h.setText(aVar.b);
            if (aVar.i) {
                b(aVar.d, z, aVar.i);
            } else {
                a(aVar.q ? aVar.p : aVar.c, z, aVar.i);
                d(z);
            }
            a(aVar.e, z);
            a(this.l, aVar.a, R.drawable.xllive_avatar_default);
            e(z);
            c(z);
            if (aVar.n != null) {
                this.o.setVisibility(0);
                a(this.o, aVar.n.getIconFullPath(), R.drawable.xllive_avatar_default);
            } else {
                this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (TextUtils.isEmpty(aVar.o)) {
                this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.p.setVisibility(0);
                this.p.setText(new StringBuilder("\u76f4\u64ad\u53f7\uff1a").append(aVar.o).toString());
            }
            if (TextUtils.isEmpty(aVar.r)) {
                this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            this.q.setVisibility(0);
            a(this.r, aVar.r, R.drawable.xllive_avatar_default);
        }
    }

    private void a(ImageView imageView, String str, int i) {
        Context context = (Context) this.b.get();
        if (context != null) {
            com.xunlei.tdlive.util.a.a(context).a(imageView, str, com.xunlei.tdlive.util.a.a(context, i));
        }
    }

    private void a(String str, boolean z) {
        Context context = (Context) this.b.get();
        if (context != null) {
            String str2 = BuildConfig.VERSION_NAME;
            if (TextUtils.isEmpty(str)) {
                str = !z ? context.getResources().getString(R.string.signature_default) : str2;
            }
            this.g.setText(str);
        }
    }

    private void c(boolean z) {
        a aVar = (a) e();
        this.e.setText(aVar.g ? "\u5df2\u5173\u6ce8" : "\u5173\u6ce8");
        if (aVar.f.equals(f.a().k())) {
            this.e.setEnabled(false);
        } else {
            this.e.setSelected(aVar.g);
        }
    }

    private void d(boolean z) {
        a aVar = (a) e();
        if (aVar.l) {
            this.m.setText(aVar.k ? "\u5df2\u7981\u8a00" : "\u7981\u8a00");
            this.m.setVisibility(0);
            return;
        }
        this.m.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    private void e(boolean z) {
        boolean z2 = false;
        this.n.setVisibility(((a) e()).m ? 0 : MqttConnectOptions.MQTT_VERSION_3_1_1);
        TextView textView = this.n;
        if (!z) {
            z2 = true;
        }
        textView.setEnabled(z2);
    }

    private void a(long j, boolean z, boolean z2) {
        if (!z) {
            this.k.setVisibility(j <= 0 ? MqttConnectOptions.MQTT_VERSION_3_1_1 : 0);
        }
        if (((a) e()).q) {
            this.i.setText("\u4eca\u65e5\u9001\u51fa\uff1a");
        } else {
            this.i.setText("\u9001\u51fa\uff1a");
        }
        this.j.setText(String.valueOf(j));
        this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.xllive_coin, 0);
        this.j.setCompoundDrawablePadding((int) d.a((Context) this.b.get(), 6.0f));
    }

    private void b(long j, boolean z, boolean z2) {
        this.k.setVisibility(0);
        this.i.setText("\u7c89\u4e1d\uff1a");
        this.j.setText(String.valueOf(j));
        this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.j.setCompoundDrawablePadding(0);
    }

    private void a(Context context, String str, String str2, String str3) {
        if (context != null) {
            if (((a) e()).k) {
                a(context, a(context, str, str2), new StringBuilder("\u89e3\u7981 ").append(str3).append(" \u5417\uff1f").toString(), "\u53d6\u6d88", "\u786e\u5b9a");
                return;
            }
            a(context, b(context, str, str2), new StringBuilder("\u7981\u8a00 ").append(str3).append(" \u5417\uff1f").toString(), "\u53d6\u6d88", "\u786e\u5b9a");
        }
    }

    private void a(Context context, Runnable runnable, String str, String str2, String str3) {
        new c(context, null, str, str2, new String[]{str3}).b(new as(this, runnable));
    }

    private Runnable a(Context context, String str, String str2) {
        return new at(this, str, str2, context);
    }

    private Runnable b(Context context, String str, String str2) {
        return new aj(this, str, str2, context);
    }

    private void a(Context context, int i, String str, String str2, boolean z) {
        if (i == 0) {
            n.a(context, str2 + "\u6210\u529f");
            ((a) e()).k = z;
            a(false);
            return;
        }
        n.a(context, str2 + "\u5931\u8d25\uff0c" + str);
    }
}
