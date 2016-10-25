package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.tasklist.list.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.d;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter$XZBCardClickArea;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskXZBCardViewHolder.java
public final class c extends d implements OnClickListener {
    private static int m;
    private a h;
    private e k;
    private boolean l;
    private XZBDevice n;

    private c(View view, a aVar) {
        super(view);
        this.a.setImageResource(R.drawable.download_center_xzb_icon_new);
        this.c.setText("\u83b7\u53d6\u201c\u4e0b\u8f7d\u5b9d\u201d\u667a\u80fd\u786c\u4ef6");
        this.d.setText("\u4e3a\u624b\u673a\u65e0\u9650\u6269\u5bb9");
        this.g.setText("\u63a8\u8350");
        this.f.setText("\u67e5\u770b");
        this.h = aVar;
        this.g.setOnClickListener(this);
    }

    public final void a(e eVar) {
        if (m == 0) {
            super.a(eVar);
            this.k = eVar;
            Object obj = eVar.c;
            if (obj != null) {
                XZBDevice xZBDevice = (XZBDevice) obj;
                this.n = xZBDevice;
                LoginHelper.a();
                boolean z = LoginHelper.c() && this.n != this.h.i;
                this.l = z;
                if (this.l) {
                    this.g.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    this.c.setText(xZBDevice.getDeviceName());
                    this.c.setText("\u6211\u7684\u4e0b\u8f7d\u5b9d");
                    this.d.setText("\u70b9\u51fb\u67e5\u770b\u4e0b\u8f7d\u5b9d\u4efb\u52a1");
                } else {
                    CharSequence mainTitleBarStr = XZBShouleiUtil.getInstance().getMainTitleBarStr();
                    CharSequence subTitleBarStr = XZBShouleiUtil.getInstance().getSubTitleBarStr();
                    this.c.setText(mainTitleBarStr);
                    this.d.setText(subTitleBarStr);
                    this.g.setVisibility(0);
                }
                this.b.setOnClickListener(new d(this));
                this.e.setOnClickListener(this);
                this.f.setOnClickListener(this);
            }
        }
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeButton:
                XZBReporter.b();
                e.a();
                e.a(false);
                this.h.a(this.k);
                this.k = null;
            case R.id.tagView:
                XZBWebviewActivity.a(c(), "v_an_shoulei_downloadcenter");
                XZBReporter.a(XZBReporter$XZBCardClickArea.extend);
            case R.id.actionButton:
                XZBReporter.a(XZBReporter$XZBCardClickArea.button);
                if (this.l) {
                    e.a();
                    e.a(c());
                    return;
                }
                XZBWebviewActivity.a(c(), "v_an_shoulei_downloadcenter");
            default:
                break;
        }
    }

    public static c a(Context context, ViewGroup viewGroup, a aVar, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_task_card_template_basic_promotion_card, viewGroup, false);
        m = i;
        return new c(inflate, aVar);
    }
}
