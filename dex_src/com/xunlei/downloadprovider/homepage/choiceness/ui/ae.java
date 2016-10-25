package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: HomeChoicenessFragment.java
final class ae implements d {
    final /* synthetic */ HomeChoicenessFragment a;

    ae(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        if (this.a.getActivity() != null) {
            if (HomeChoicenessFragment.l(this.a) == null) {
                HomeChoicenessFragment.a(this.a, new j(this.a.getActivity(), "vip_renew_homePage"));
            }
            HomeChoicenessFragment.a(this.a, new bx());
            if (HomeChoicenessFragment.m(this.a) != null) {
                HomeChoicenessFragment.l(this.a).a(new StringBuilder("dateAndUser").append(HomeChoicenessFragment.m(this.a).d).toString(), BuildConfig.VERSION_NAME);
            }
            HomeChoicenessFragment.n(this.a);
            if (HomeChoicenessFragment.o(this.a)) {
                g a = g.a("android_renewTip", "renewTip_show", "renewTip_show").a("from", "home_collect", MqttConnectOptions.MQTT_VERSION_3_1);
                String str = "is_login";
                LoginHelper.a();
                g a2 = a.a(str, LoginHelper.c() ? c.f : "0", MqttConnectOptions.MQTT_VERSION_3_1).a("is_vip", LoginHelper.a().f() ? c.f : "0", MqttConnectOptions.MQTT_VERSION_3_1);
                ThunderReporter.a(a2);
                ThunderReporter.a(a2, true);
            }
            if (i2 == 0) {
                this.a.b();
            }
        }
    }
}
