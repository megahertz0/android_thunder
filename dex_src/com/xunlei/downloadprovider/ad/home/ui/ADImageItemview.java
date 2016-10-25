package com.xunlei.downloadprovider.ad.home.ui;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.home.a.c;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ADImageItemview extends FrameLayout implements ADItemView, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private final String a;
    private String b;
    private com.xunlei.downloadprovider.ad.common.a c;
    private com.xunlei.downloadprovider.commonview.dialog.d d;
    private f e;

    private class a {
        TextView a;
        TextView b;
        ImageView c;
        View d;
        TextView e;
        TextView f;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        String str = c.a;
        new StringBuilder("bindView resId: ").append(aVar.d);
        if (view instanceof ADItemView) {
            ((ADItemView) view).a(aVar.d);
            this.e = fVar;
            com.xunlei.downloadprovider.ad.common.a a = c.a(getContext().getApplicationContext()).c.a(aVar.d);
            if (this.c != a || a == null) {
                this.c = null;
                a aVar2 = (a) getTag();
                if (aVar2 != null) {
                    aVar2.e.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    aVar2.f.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    aVar2.b.setText(BuildConfig.VERSION_NAME);
                    aVar2.e.setText(BuildConfig.VERSION_NAME);
                    aVar2.c.setImageResource(R.drawable.choiceness_icon_default);
                    com.nostra13.universalimageloader.core.d.a().a(aVar2.c);
                    TextView textView = aVar2.a;
                    r.a();
                    textView.setText(r.b());
                    aVar2.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    aVar2.c.setTag(aVar2.c.getId(), null);
                }
            }
            c.a(getContext()).a(aVar, (ADItemView) view, (n) fVar);
            return;
        }
        str = c.a;
    }

    public ADImageItemview(Context context) {
        super(context);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    public ADImageItemview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    public ADImageItemview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = c.a;
        this.d = null;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.choiceness_ad_image_item_test05, this, true);
        a aVar = new a();
        aVar.b = (TextView) findViewById(R.id.item_title);
        aVar.c = (ImageView) findViewById(R.id.item_icon);
        aVar.a = (TextView) findViewById(R.id.choiceness_ad_download_status_tv);
        aVar.f = (TextView) findViewById(R.id.choiceness_ad_extend_tv);
        aVar.e = (TextView) findViewById(R.id.choiceness_ad_source_tv);
        aVar.d = findViewById(R.id.choiceness_ad_download_rl);
        setTag(aVar);
    }

    private void a() {
        if (this.c != null) {
            String str;
            com.xunlei.downloadprovider.ad.common.a aVar = c.a(getContext().getApplicationContext()).c.e;
            String str2 = (aVar == null || !aVar.s().equals(this.c.s())) ? Utility.NETWORK_OTHER : "first";
            String str3 = "adv_homeflow_pic_click";
            String a = b.a(this.c);
            String sourceName = this.c.o().getSourceName();
            String j = this.c.j();
            if (this.c.e()) {
                str = "download";
            } else {
                str = "open";
            }
            new StringBuilder("reportChoicenessADImageClickStatus  id: ").append(a).append(" ad_type: ").append(sourceName).append(" eventType: ").append(str3);
            g a2 = g.a("android_advertise", str3, str3);
            String str4 = AgooConstants.MESSAGE_ID;
            if (a == null) {
                a = BuildConfig.VERSION_NAME;
            }
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(a2.a(str4, a).a("ad_type", sourceName == null ? BuildConfig.VERSION_NAME : sourceName, MqttConnectOptions.MQTT_VERSION_3_1).a("material", j == null ? BuildConfig.VERSION_NAME : j, MqttConnectOptions.MQTT_VERSION_3_1).a("ad_click_action", str).a(AgooConstants.MESSAGE_TYPE, str2));
            this.c.onClick(this);
            if (!this.c.e()) {
                return;
            }
            if (this.c.o() == AD_TYPE.SOURCE_SSP_FLAG || this.c.o() == AD_TYPE.SOURCE_SSP_DEFAULT_FLAG || this.c.o() == AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG) {
                a = this.c.h();
                if (!TextUtils.isEmpty(a)) {
                    com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
                    bVar.c = this.c.n();
                    bVar.e = this.c.m();
                    bVar.d = true;
                    com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(3, a, null);
                    if (this.c.o() == AD_TYPE.SOURCE_SSP_FLAG) {
                        gVar.d = com.xunlei.downloadprovider.service.a.c + this.c.s();
                    } else {
                        gVar.d = com.xunlei.downloadprovider.service.a.c + "inmobi";
                    }
                    ((ThunderTask) getContext()).createLocalTaskWithAdditionInfo(a, this.c.m(), 0, null, null, null, 0, gVar, null, false, bVar);
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
        return AD_LAYOUT_TYPE.IMAGE_TYPE_VIEW;
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar) {
        if (aVar != null) {
            String str;
            this.c = aVar;
            a aVar2 = (a) getTag();
            if (aVar2 != null) {
                CharSequence a;
                aVar2.b.setText(aVar.b());
                if (!TextUtils.isEmpty(aVar.d())) {
                    str = this.b;
                    a aVar3 = (a) getTag();
                    if (!(aVar3 == null || aVar3.c == null || !c.a(getContext()).c.b.contains(str))) {
                        aVar3.c.setImageResource(R.drawable.choiceness_icon_default);
                    }
                    c.a(getContext()).c.b.add(str);
                    com.nostra13.universalimageloader.core.d.a().a(aVar3.c);
                    com.nostra13.universalimageloader.core.d.a().a(this.c.d(), com.xunlei.downloadprovider.homepage.choiceness.a.a(), new g(this, str));
                }
                TextView textView = aVar2.a;
                if (this.c.e()) {
                    r.a();
                    a = r.a(this.c);
                } else {
                    a = "\u67e5\u770b\u8be6\u60c5";
                }
                textView.setText(a);
                aVar2.e.setText(this.c.o().getSourceTagString());
                aVar2.a.setVisibility(0);
                aVar2.e.setVisibility(0);
                aVar2.f.setVisibility(0);
                aVar2.d.setVisibility(0);
            }
            Set set = c.a(getContext()).c.c;
            if (set != null && !set.contains(this.b)) {
                com.xunlei.downloadprovider.ad.common.a aVar4 = c.a(getContext().getApplicationContext()).c.e;
                String str2 = (aVar4 == null || !aVar4.s().equals(aVar.s())) ? Utility.NETWORK_OTHER : "first";
                String str3 = "adv_homeflow_pic_show";
                String a2 = b.a(aVar);
                str = aVar.o().getSourceName();
                String j = aVar.j();
                new StringBuilder("reportADImageStatus  id: ").append(a2).append(" adtype: ").append(str).append(" eventType: ").append(str3);
                g a3 = g.a("android_advertise", str3, str3);
                String str4 = AgooConstants.MESSAGE_ID;
                if (a2 == null) {
                    a2 = BuildConfig.VERSION_NAME;
                }
                com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(a3.a(str4, a2).a(AgooConstants.MESSAGE_TYPE, str2).a("ad_type", str == null ? BuildConfig.VERSION_NAME : str, MqttConnectOptions.MQTT_VERSION_3_1).a("material", j == null ? BuildConfig.VERSION_NAME : j, MqttConnectOptions.MQTT_VERSION_3_1));
                set.add(this.b);
                aVar.a((View) this);
                return;
            }
            return;
        }
        str2 = c.a;
    }

    public View getContainer() {
        return this;
    }

    public final /* synthetic */ boolean a(int i, e eVar) {
        if (this.c != null) {
            if (this.c.o() == AD_TYPE.SOURCE_BAIDU_FLAG && com.xunlei.xllib.a.b.g(getContext()) && this.c.e()) {
                OnClickListener fVar = new f(this);
                if (this.d == null) {
                    this.d = new com.xunlei.downloadprovider.commonview.dialog.d(getContext());
                    this.d.setTitle("\u6e29\u99a8\u63d0\u793a");
                    this.d.b("\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u5f00\u59cb\u4e0b\u8f7d/\u5b89\u88c5\u5e94\u7528\uff1f");
                    this.d.d("\u786e\u8ba4");
                    this.d.c("\u53d6\u6d88");
                    this.d.b(fVar);
                    this.d.a(new h(this));
                }
                this.d.show();
            } else {
                a();
            }
        }
        return true;
    }
}
