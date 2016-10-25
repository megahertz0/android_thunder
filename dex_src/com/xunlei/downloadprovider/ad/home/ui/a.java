package com.xunlei.downloadprovider.ad.home.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.home.a.c;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;
import com.xunlei.downloadprovider.player.MediaPlayerLoadingView;
import com.xunlei.downloadprovider.player.a.b;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ADGDTVideoItem.java
public final class a extends FrameLayout implements OnClickListener, com.xunlei.downloadprovider.ad.common.e.e.a, ADItemView, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a>, com.xunlei.downloadprovider.homepage.choiceness.ui.n.a, b {
    protected boolean a;
    protected Rect b;
    private String c;
    private Context d;
    private com.xunlei.downloadprovider.player.a.a e;
    private n f;
    private a g;
    private int h;
    private com.xunlei.downloadprovider.ad.common.a i;
    private String j;
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a k;
    private NativeMediaADData l;
    private com.xunlei.downloadprovider.commonview.dialog.d m;

    // compiled from: ADGDTVideoItem.java
    private class a {
        View a;
        View b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        ImageView h;
        MediaPlayerLoadingView i;
        MediaView j;
        View k;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        if (fVar instanceof n) {
            this.f = (n) fVar;
            this.f.c.add(this);
        }
        this.h = i;
        this.k = aVar;
        setTag(R.id.position_layout, Integer.valueOf(i));
        if (view instanceof ADItemView) {
            ((ADItemView) view).a(aVar.d);
            com.xunlei.downloadprovider.ad.common.a a = c.a(getContext().getApplicationContext()).c.a(aVar.d);
            if (this.i != a || a == null) {
                this.g.c.setText(BuildConfig.VERSION_NAME);
                this.g.e.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                this.g.d.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                this.g.f.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                this.g.g.setImageResource(R.drawable.choiceness_icon_default);
                com.nostra13.universalimageloader.core.d.a().a(this.g.g);
                this.g.g.setTag(this.g.g.getId(), null);
                this.g.g.setVisibility(0);
                this.g.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.g.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            c.a(getContext()).a(aVar, (ADItemView) view, this.f);
        }
    }

    public a(Context context, com.xunlei.downloadprovider.player.a.a aVar) {
        super(context);
        this.c = "ADGDTVideoItem";
        this.d = null;
        this.b = new Rect();
        this.m = null;
        this.d = context;
        this.e = aVar;
        this.g = new a();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.choiceness_ad_gdt_play_vod_item, this, true);
        this.g.a = inflate.findViewById(R.id.root_view);
        this.g.c = (TextView) inflate.findViewById(R.id.item_title);
        this.g.g = (ImageView) inflate.findViewById(R.id.item_poster);
        this.g.h = (ImageView) inflate.findViewById(R.id.play_icon);
        this.g.i = (MediaPlayerLoadingView) inflate.findViewById(R.id.loading_view);
        this.g.c = (TextView) inflate.findViewById(R.id.item_title);
        this.g.d = (TextView) inflate.findViewById(R.id.ad_download_btn);
        this.g.e = (TextView) inflate.findViewById(R.id.tv_ad_source);
        this.g.f = (TextView) inflate.findViewById(R.id.tv_ad_tag);
        this.g.b = inflate.findViewById(R.id.ad_download_btn_container);
        this.g.j = (MediaView) inflate.findViewById(R.id.media_view_gdt);
        this.g.k = inflate.findViewById(R.id.item_player_container);
        this.g.a.setOnClickListener(this);
        this.g.b.setOnClickListener(this);
        this.g.g.setOnClickListener(this);
        this.g.j.setOnClickListener(this);
        setTag(this.g);
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar) {
        if (aVar != null) {
            CharSequence a;
            this.i = aVar;
            Object p = this.i.p();
            if (p instanceof NativeMediaADData) {
                this.l = (NativeMediaADData) p;
            }
            Set set = c.a(getContext().getApplicationContext()).c.c;
            if (!(set == null || set.contains(this.j))) {
                com.xunlei.downloadprovider.ad.home.a.a(com.xunlei.downloadprovider.ad.common.b.a(this.i), this.i.o().getSourceName(), getReportAdContent(), this.i.j());
                this.i.a((View) this);
                set.add(this.j);
            }
            this.g.c.setText(this.i.b());
            this.g.e.setText(this.i.o().getSourceTagString());
            TextView textView = this.g.d;
            if (this.i.e()) {
                r.a();
                a = r.a(this.i);
            } else {
                a = getResources().getString(R.string.choiceness_ad_download_status_intalled);
            }
            textView.setText(a);
            this.g.f.setVisibility(0);
            this.g.e.setVisibility(0);
            this.g.d.setVisibility(0);
            if (!TextUtils.isEmpty(this.i.d())) {
                String d = this.i.d();
                ImageView imageView = this.g.g;
                String str = this.j;
                if (c.a(getContext().getApplicationContext()).c.b.contains(str)) {
                    imageView.setImageResource(R.drawable.choiceness_icon_default);
                }
                c.a(getContext().getApplicationContext()).c.b.add(str);
                com.nostra13.universalimageloader.core.d.a().a(imageView);
                com.nostra13.universalimageloader.core.d.a().a(d, com.xunlei.downloadprovider.homepage.choiceness.a.a(), new e(this, str, imageView));
            }
            if (!l()) {
                this.g.g.setVisibility(0);
                this.g.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.g.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.g.i.b();
            } else if (m()) {
                if (this.a) {
                    k();
                } else {
                    i();
                }
            } else if (this.a) {
                j();
            } else {
                i();
            }
            if (l()) {
                this.l.setMediaListener(new d(this));
            }
            if (this.i instanceof com.xunlei.downloadprovider.ad.common.e.e) {
                ((com.xunlei.downloadprovider.ad.common.e.e) this.i).a = this;
            }
        }
    }

    private void b(String str) {
        if (this.i != null) {
            com.xunlei.downloadprovider.ad.home.a.a(com.xunlei.downloadprovider.ad.common.b.a(this.i), this.i.o().getSourceName(), getReportAdContent(), str, this.i.j());
        }
    }

    private String getReportAdContent() {
        return l() ? WeiXinShareContent.TYPE_VIDEO : WeiXinShareContent.TYPE_IMAGE;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_view:
                c(Utility.NETWORK_OTHER);
            case R.id.item_poster:
                if (!l()) {
                    c(WeiXinShareContent.TYPE_IMAGE);
                } else if (this.g.h.getVisibility() == 0) {
                    boolean z;
                    if (!com.xunlei.xllib.a.b.g(getContext()) || m()) {
                        z = false;
                    } else {
                        Object obj = 1;
                    }
                    if (z) {
                        DialogInterface.OnClickListener bVar = new b(this);
                        DialogInterface.OnClickListener cVar = new c(this);
                        if (this.m == null) {
                            this.m = new com.xunlei.downloadprovider.commonview.dialog.d(this.d);
                            this.m.setTitle("\u6e29\u99a8\u63d0\u793a");
                            this.m.b("\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u5f00\u59cb\u64ad\u653e\u89c6\u9891\uff1f");
                            this.m.d("\u786e\u8ba4");
                            this.m.c("\u53d6\u6d88");
                        }
                        this.m.b(bVar);
                        this.m.a(cVar);
                        if (this.m != null) {
                            this.m.show();
                            return;
                        }
                        return;
                    }
                    a(false);
                } else if (this.g.i.getVisibility() != 0) {
                }
            case R.id.ad_download_btn_container:
                c("button");
            default:
                break;
        }
    }

    private void c(String str) {
        if (this.i != null) {
            b(str);
            this.i.onClick(this);
        }
    }

    private void a(boolean z) {
        if (l() && !this.a) {
            new StringBuilder("posKey: ").append(getViewPositionKey()).append(" startPlay isVideoAdLoaded: ").append(m()).append(" auto: ").append(z);
            if (!(z || this.e == null)) {
                b bVar = this.e.f;
                if (bVar != null) {
                    bVar.d();
                    this.e.b(null);
                }
            }
            if (m()) {
                k();
                this.l.bindView(this.g.j, true);
                this.l.play();
            } else {
                this.l.preLoadVideo();
                j();
            }
            if (this.e != null) {
                this.e.b(this);
                this.a = true;
            }
        }
    }

    private void h() {
        if (l() && this.a) {
            new StringBuilder("posKey: ").append(getViewPositionKey()).append(" stopPlay isVideoAdLoaded: ").append(m());
            i();
            if (m()) {
                this.l.stop();
            }
            if (this.e != null) {
                this.e.b(null);
                this.a = false;
            }
        }
    }

    private void i() {
        this.g.h.setVisibility(0);
        this.g.g.setVisibility(0);
        this.g.i.b();
        this.g.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    private void j() {
        this.g.i.a();
        this.g.g.setVisibility(0);
        this.g.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    private void k() {
        this.g.j.setVisibility(0);
        this.g.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.i.b();
    }

    private boolean l() {
        return this.l != null && this.l.isVideoAD();
    }

    private boolean m() {
        return l() && this.l.isVideoLoaded();
    }

    public final String getViewPositionKey() {
        return this.j;
    }

    public final String a(String str) {
        this.j = str;
        return str;
    }

    public final AD_LAYOUT_TYPE getADType() {
        return AD_LAYOUT_TYPE.PLAY_VOD_TYPE_VIEW;
    }

    public final View getContainer() {
        return this;
    }

    public final int getVisibilityPercents() {
        return l() ? com.xunlei.downloadprovider.player.a.d.a(getContext().getApplicationContext(), this.b, this.g.k) : 0;
    }

    public final int getPosition() {
        return this.h;
    }

    public final void c() {
        a(true);
    }

    public final void d() {
        h();
    }

    public final void a(NativeMediaADData nativeMediaADData) {
        new StringBuilder("onADVideoLoaded (mMediaADData == sourceAD): ").append(this.l == nativeMediaADData);
        if (this.l == nativeMediaADData) {
            new StringBuilder("onVideoLoadedCallback isVideoAdLoaded: ").append(m()).append(" isActive(): ").append(this.a);
            if (l() && this.a && m()) {
                k();
                this.l.bindView(this.g.j, true);
                this.l.play();
            }
        }
    }

    public final void a(NativeMediaADData nativeMediaADData, int i) {
        new StringBuilder("onADError (mMediaADData == sourceAD): ").append(this.l == nativeMediaADData);
        if (this.l == nativeMediaADData && i == 700) {
            new StringBuilder("onVideoADLoadErrorCallback isVideoAdLoaded: ").append(m()).append(" isActive(): ").append(this.a);
            if (l() && this.a) {
                h();
                i();
            }
        }
    }

    public final View getLayout() {
        return this.g.k;
    }

    public final boolean e() {
        return this.a;
    }

    public final boolean f() {
        return l();
    }

    public final com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.k;
    }

    protected final void g() {
        if (this.m != null) {
            this.m.dismiss();
        }
    }

    public final void a() {
        if (l() && this.a) {
            new StringBuilder("posKey: ").append(getViewPositionKey()).append(" resumePlay isVideoAdLoaded: ").append(m()).append(" getVisibilityPercents(): ").append(getVisibilityPercents());
            if (m()) {
                k();
                this.l.resume();
                return;
            }
            j();
        }
    }

    public final void b() {
        if (l() && this.a) {
            new StringBuilder("posKey: ").append(getViewPositionKey()).append(" pausePlay isVideoAdLoaded: ").append(m()).append(" getVisibilityPercents(): ").append(getVisibilityPercents());
            i();
            if (m()) {
                this.l.stop();
            }
        }
    }
}
