package com.xunlei.downloadprovider.ad.home.ui;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.home.a.c;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ADShortVideoItemView extends FrameLayout implements ADItemView, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private final String a;
    private String b;
    private com.xunlei.downloadprovider.ad.common.a c;
    private com.xunlei.downloadprovider.commonview.dialog.d d;
    private f e;

    private class a {
        TextView a;
        TextView b;
        TextView c;
        View d;
        TextView e;
        ImageView f;
        RatingBar g;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        new StringBuilder("bindView resId: ").append(aVar.d);
        if (view instanceof ADItemView) {
            ((ADItemView) view).a(aVar.d);
            this.e = fVar;
            com.xunlei.downloadprovider.ad.common.a a = c.a(getContext().getApplicationContext()).c.a(aVar.d);
            if (this.c != a || a == null) {
                this.c = null;
                a aVar2 = (a) getTag();
                if (aVar2 != null) {
                    aVar2.a.setText(BuildConfig.VERSION_NAME);
                    aVar2.f.setImageResource(R.drawable.choiceness_icon_default);
                    aVar2.f.setTag(aVar2.f.getId(), null);
                    com.nostra13.universalimageloader.core.d.a().a(aVar2.f);
                    aVar2.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    aVar2.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    aVar2.c.setText(BuildConfig.VERSION_NAME);
                    aVar2.g.setRating(0.0f);
                }
            }
            c.a(getContext()).a(aVar, (ADItemView) view, (n) fVar);
        }
    }

    public ADShortVideoItemView(Context context) {
        super(context);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    public ADShortVideoItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    public ADShortVideoItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.choiceness_ad_short_video_item_style_a, this, true);
        a aVar = new a();
        aVar.a = (TextView) findViewById(R.id.item_title);
        aVar.f = (ImageView) findViewById(R.id.item_icon);
        aVar.c = (TextView) findViewById(R.id.choiceness_ad_title_tv);
        aVar.d = findViewById(R.id.app_ad_rl);
        aVar.b = (TextView) findViewById(R.id.web_ad_extend_tv);
        aVar.g = (RatingBar) findViewById(R.id.score_rb);
        aVar.e = (TextView) findViewById(R.id.app_ad_extend_tv);
        setTag(aVar);
    }

    private void a() {
        if (this.c != null) {
            String str = "adv_homeflow_video_click";
            String a = b.a(this.c);
            String sourceName = this.c.o().getSourceName();
            String j = this.c.j();
            new StringBuilder("reportChoicenessADClickStatus  id: ").append(a).append(" ad_type: ").append(sourceName).append(" eventType: ").append(str);
            g a2 = g.a("android_advertise", str, str);
            String str2 = AgooConstants.MESSAGE_ID;
            if (a == null) {
                a = BuildConfig.VERSION_NAME;
            }
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(a2.a(str2, a).a("ad_type", sourceName == null ? BuildConfig.VERSION_NAME : sourceName, MqttConnectOptions.MQTT_VERSION_3_1).a("material", j == null ? BuildConfig.VERSION_NAME : j, MqttConnectOptions.MQTT_VERSION_3_1));
            this.c.onClick(this);
            if (!this.c.e()) {
                return;
            }
            if (this.c.o() == AD_TYPE.SOURCE_SSP_FLAG || this.c.o() == AD_TYPE.SOURCE_SSP_DEFAULT_FLAG || this.c.o() == AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG) {
                j = this.c.h();
                if (!TextUtils.isEmpty(j)) {
                    com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
                    bVar.c = this.c.n();
                    bVar.e = this.c.m();
                    bVar.d = true;
                    com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(3, j, null);
                    if (this.c.o() == AD_TYPE.SOURCE_SSP_FLAG) {
                        gVar.d = com.xunlei.downloadprovider.service.a.d + this.c.s();
                    } else {
                        gVar.d = com.xunlei.downloadprovider.service.a.d + "inmobi";
                    }
                    ((ThunderTask) getContext()).createLocalTaskWithAdditionInfo(j, this.c.m(), 0, null, null, null, 0, gVar, null, false, bVar);
                }
            }
        }
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return null;
    }

    public String getViewPositionKey() {
        return this.b;
    }

    public final String a(String str) {
        this.b = str;
        return str;
    }

    public AD_LAYOUT_TYPE getADType() {
        return AD_LAYOUT_TYPE.SHORT_VOD_TYPE_VIEW;
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar) {
        this.c = aVar;
        if (aVar != null) {
            a aVar2 = (a) getTag();
            if (aVar2 != null) {
                float f;
                aVar2.a.setText(aVar.b());
                if (!TextUtils.isEmpty(aVar.d())) {
                    String str = this.b;
                    a aVar3 = (a) getTag();
                    if (!(aVar3 == null || aVar3.f == null || !c.a(getContext()).c.b.contains(str))) {
                        aVar3.f.setImageResource(R.drawable.choiceness_icon_default);
                    }
                    c.a(getContext()).c.b.add(str);
                    com.nostra13.universalimageloader.core.d.a().a(aVar3.f);
                    com.nostra13.universalimageloader.core.d.a().a(this.c.d(), com.xunlei.downloadprovider.homepage.choiceness.a.a(), new p(this, str));
                }
                boolean e = aVar.e();
                CharSequence a = aVar.a();
                if (aVar.g() <= 0.0f) {
                    f = 4.5f;
                } else {
                    f = aVar.g();
                }
                CharSequence charSequence = this.c.o().getSourceCompanyName() + "\u5e7f\u544a";
                if (e) {
                    aVar2.c.setText(a);
                    aVar2.g.setRating(f);
                    aVar2.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    aVar2.d.setVisibility(0);
                    aVar2.e.setText(charSequence);
                } else {
                    aVar2.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    aVar2.b.setText(charSequence);
                    aVar2.b.setVisibility(0);
                }
            }
            Set set = c.a(getContext()).c.c;
            if (set != null && !set.contains(this.b)) {
                com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a("adv_homeflow_video_show", b.a(aVar), aVar.o().getSourceName(), aVar.j());
                set.add(this.b);
                this.c.a((View) this);
            }
        }
    }

    public View getContainer() {
        return this;
    }

    public final /* synthetic */ boolean a(int i, e eVar) {
        if (this.c != null) {
            if (this.c.o() == AD_TYPE.SOURCE_BAIDU_FLAG && com.xunlei.xllib.a.b.g(BrothersApplication.a()) && this.c.e()) {
                OnClickListener oVar = new o(this);
                if (this.d == null) {
                    this.d = new com.xunlei.downloadprovider.commonview.dialog.d(getContext());
                    this.d.setTitle("\u6e29\u99a8\u63d0\u793a");
                    this.d.b("\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u5f00\u59cb\u4e0b\u8f7d/\u5b89\u88c5\u5e94\u7528\uff1f");
                    this.d.d("\u786e\u8ba4");
                    this.d.c("\u53d6\u6d88");
                    this.d.b(oVar);
                    this.d.a(new q(this));
                }
                this.d.show();
            } else {
                a();
            }
        }
        return true;
    }
}
