package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.widget.RiseNumberTextView;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.Iterator;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

private class SnifferResultsFragment$j extends SnifferResultsFragment$c {
    int b;
    SniffingPageResource c;
    final /* synthetic */ SnifferResultsFragment d;
    private final int e;
    private final int f;

    private SnifferResultsFragment$j(SnifferResultsFragment snifferResultsFragment) {
        this.d = snifferResultsFragment;
        super((byte) 0);
        this.e = 0;
        this.f = 1;
        this.b = -1;
    }

    private void c() {
        List list = null;
        String d = f.d(ThunderSnifferUtil.getSearchWordFromUrl(this.c.mPageUrl));
        String e = f.e(ThunderSnifferUtil.getSearchWordFromUrl(this.c.mPageUrl));
        SnifferResultsFragment.u(this.d).setVisibility(0);
        SnifferResultsFragment.d(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.c.groups == null || this.c.groups.size() == 0) {
            SnifferResultsFragment.s(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (this.c == null || this.c.errorCode == 0) {
                SnifferResultsFragment.s(this.d).a((int) R.styleable.AppCompatTheme_buttonStyle, true);
                SnifferResultsFragment.x(this.d).setText(a(0, false, null, null));
                SnifferResultsFragment.J(this.d).setText(SnifferResultsFragment.x(this.d).getText().toString());
                SnifferResultsFragment.E(this.d).setVisibility(0);
                SnifferResultsFragment.u(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (d != null && !d.trim().equals(BuildConfig.VERSION_NAME)) {
                    if (SniffConfigure.a().b() != null) {
                        list = SniffConfigure.a().b().a;
                    }
                    SnifferResultsFragment.N(this.d).setSuffixListItems(a(d, list, e));
                    SnifferResultsFragment.N(this.d).setVisibility(0);
                    SnifferResultsFragment.O(this.d).setVisibility(0);
                    return;
                }
                return;
            } else if (3 == this.c.errorCode) {
                SnifferResultsFragment.F(this.d).setVisibility(0);
                SnifferResultsFragment.s(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.x(this.d).setText(a(0, false, null, null));
                return;
            } else if (1 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, MqttConnectOptions.MQTT_VERSION_3_1_1);
                SnifferResultsFragment.c(this.d);
                return;
            } else if (2 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, MqttConnectOptions.MQTT_VERSION_3_1_1);
                SnifferResultsFragment.c(this.d);
                return;
            } else if (131071 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, R.styleable.Toolbar_titleMargins);
                SnifferResultsFragment.c(this.d);
                return;
            } else {
                SnifferResultsFragment.a(this.d, R.styleable.Toolbar_titleMarginStart);
                SnifferResultsFragment.c(this.d);
                return;
            }
        }
        SnifferResultsFragment.s(this.d).a((int) R.styleable.AppCompatTheme_buttonStyle, true);
        if (SnifferResultsFragment.I(this.d) == null) {
            SnifferResultsFragment.a(this.d, new r(this.d.getActivity()));
        }
        if (SnifferResultsFragment.u(this.d).getAdapter() == null) {
            SnifferResultsFragment.u(this.d).setAdapter(SnifferResultsFragment.I(this.d));
        }
        SnifferResultsFragment.I(this.d).c = true;
        List b = SnifferResultsFragment.I(this.d).b();
        Iterator it = this.c.groups.iterator();
        while (it.hasNext()) {
            SniffingResourceGroup sniffingResourceGroup = (SniffingResourceGroup) it.next();
            if (!b.contains(sniffingResourceGroup)) {
                if (sniffingResourceGroup.sniffingType != 2) {
                    SnifferResultsFragment.I(this.d).a(sniffingResourceGroup);
                } else if (SnifferResultsFragment.n(this.d)) {
                    SnifferResultsFragment.I(this.d).a(sniffingResourceGroup);
                } else if (!SnifferResultsFragment.o(this.d) && SnifferResultsFragment.p(this.d) != null && SnifferResultsFragment.p(this.d).equals(SnifferResultsFragment.q(this.d))) {
                    SnifferResultsFragment.I(this.d).a(sniffingResourceGroup);
                }
            }
        }
        SnifferResultsFragment.h(this.d).setContentListId(com.xunlei.downloadprovider.R.id.sniffer_page_results_list_view);
        SnifferResultsFragment.x(this.d).setText(a(this.c.groups.size(), true, d, e));
        SnifferResultsFragment.J(this.d).setText(SnifferResultsFragment.x(this.d).getText().toString());
        r I = SnifferResultsFragment.I(this.d);
        a$b lVar = new l(this, SnifferResultsFragment.I(this.d).getCount(), d, e);
        I.e = lVar;
        if (lVar.a <= I.d || (I.d >= I.b && I.b > 0)) {
            lVar.a();
        }
        if (SnifferResultsFragment.I(this.d).getCount() == 0) {
            SnifferResultsFragment.s(this.d).a((int) R.styleable.AppCompatTheme_buttonStyle, true);
            SnifferResultsFragment.x(this.d).setText(a(0, false, null, null));
            SnifferResultsFragment.J(this.d).setText(SnifferResultsFragment.x(this.d).getText().toString());
            SnifferResultsFragment.E(this.d).setVisibility(0);
            SnifferResultsFragment.u(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (d != null && !d.trim().equals(BuildConfig.VERSION_NAME)) {
                List list2;
                if (SniffConfigure.a().b() != null) {
                    list2 = SniffConfigure.a().b().a;
                } else {
                    list2 = null;
                }
                SnifferResultsFragment.N(this.d).setSuffixListItems(a(d, list2, e));
                SnifferResultsFragment.O(this.d).setVisibility(0);
                SnifferResultsFragment.N(this.d).setVisibility(0);
            }
        }
    }

    private void d() {
        if (this.c == null || this.c.groups == null || this.c.groups.size() == 0 || ((SniffingResourceGroup) this.c.groups.get(0)).count == 0) {
            if (SnifferResultsFragment.a(this.d) == null || SnifferResultsFragment.a(this.d).getCount() == 0) {
                boolean z = true;
            } else {
                int i = 0;
            }
            if (this.c == null || this.c.errorCode == 0 || r0 == 0) {
                SnifferResultsFragment.s(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.x(this.d).setText(a(0));
                SnifferResultsFragment.J(this.d).setText(SnifferResultsFragment.x(this.d).getText().toString());
                SnifferResultsFragment.E(this.d).setVisibility(0);
                SnifferResultsFragment.d(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.N(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.O(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            } else if (3 == this.c.errorCode) {
                SnifferResultsFragment.F(this.d).setVisibility(0);
                SnifferResultsFragment.s(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.x(this.d).setText(a(0));
                return;
            } else if (1 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, MqttConnectOptions.MQTT_VERSION_3_1_1);
                SnifferResultsFragment.c(this.d);
                return;
            } else if (2 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, MqttConnectOptions.MQTT_VERSION_3_1_1);
                SnifferResultsFragment.c(this.d);
                return;
            } else if (131071 == this.c.errorCode) {
                SnifferResultsFragment.a(this.d, R.styleable.Toolbar_titleMargins);
                SnifferResultsFragment.c(this.d);
                return;
            } else {
                SnifferResultsFragment.a(this.d, R.styleable.Toolbar_titleMarginStart);
                SnifferResultsFragment.c(this.d);
                return;
            }
        }
        SnifferResultsFragment.u(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.d(this.d).setVisibility(0);
        SnifferResultsFragment.s(this.d).a((int) R.styleable.AppCompatTheme_buttonStyle, true);
        if (SnifferResultsFragment.a(this.d) == null) {
            SnifferResultsFragment.a(this.d, new m(this.d.getActivity()));
            SnifferResultsFragment.a(this.d).f = SnifferResultsFragment.G(this.d);
        }
        if (SnifferResultsFragment.d(this.d).getAdapter() == null) {
            SnifferResultsFragment.d(this.d).setAdapter(SnifferResultsFragment.a(this.d));
        }
        int i2 = ((SniffingResourceGroup) this.c.groups.get(0)).count;
        i = ((SniffingResourceGroup) this.c.groups.get(0)).resources.size();
        RiseNumberTextView x = SnifferResultsFragment.x(this.d);
        if (i >= i2) {
            i = i2;
        }
        x.setText(a(i));
        SnifferResultsFragment.J(this.d).setText(SnifferResultsFragment.x(this.d).getText().toString());
        SnifferResultsFragment.a(this.d).c = true;
        SnifferResultsFragment.a(this.d).a(((SniffingResourceGroup) this.c.groups.get(0)).resources);
        if (!(this.c == null || this.c.groups == null || this.c.groups.get(0) == null)) {
            try {
                ((SniffingResourceGroup) this.c.groups.get(0)).getResourceOperationMonitor().setListener(SnifferResultsFragment.H(this.d));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SnifferResultsFragment.h(this.d).setContentListId(com.xunlei.downloadprovider.R.id.sniffer_res_results_list_view);
    }

    public final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        SnifferResultsFragment.C(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.D(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.E(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (SnifferResultsFragment.h(this.d).getVisibilityState() != 10) {
            SnifferResultsFragment.A(this.d).setVisibility(0);
        }
        SnifferResultsFragment.F(this.d).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        switch (this.b) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                c();
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                d();
                break;
            default:
                new StringBuilder("onSnifferFinish performUIChange mCurrSniffType: ").append(this.b);
                break;
        }
        if (SnifferResultsFragment.h(this.d).getVisibilityState() != 10 || SnifferResultsFragment.x(this.d).getTextNumber() == 0) {
            SnifferResultsFragment.y(this.d);
        } else {
            SnifferResultsFragment.w(this.d);
        }
    }
}
