package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.web.sniff.widget.SniffResultLayout$a;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SnifferResultsFragment.java
final class k implements SniffResultLayout$a {
    final /* synthetic */ SnifferResultsFragment a;

    k(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void a(int i, int i2) {
        new StringBuilder("onVisibilityChange: flag: ").append(i).append(" oldFlag:").append(i2);
        if (SnifferResultsFragment.b(this.a) != null) {
            SnifferResultsFragment.b(this.a).a(i, i2);
        }
        if (i == 10) {
            SnifferResultsFragment.u(this.a).smoothScrollToPositionFromTop(0, 0);
            SnifferResultsFragment.d(this.a).smoothScrollToPositionFromTop(0, 0);
        } else if (i == 11 && SnifferResultsFragment.b(this.a) != null) {
            SnifferResultsFragment.b(this.a).a(false);
        }
    }

    public final void a(float f) {
        if (SnifferResultsFragment.b(this.a) != null) {
            SnifferResultsFragment.b(this.a).a(f);
        }
        if (SnifferResultsFragment.m(this.a) == null || (SnifferResultsFragment.m(this.a) instanceof SnifferResultsFragment$i) || (SnifferResultsFragment.m(this.a) instanceof SnifferResultsFragment$m)) {
            if (f == 0.0f) {
                SnifferResultsFragment.w(this.a);
            } else if (SnifferResultsFragment.b(this.a) != null) {
                SnifferResultsFragment.b(this.a).b(false);
            }
        } else if (SnifferResultsFragment.m(this.a) instanceof SnifferResultsFragment$f) {
            if (SnifferResultsFragment.b(this.a) != null) {
                SnifferResultsFragment.b(this.a).b(false);
            }
        } else if (f != 0.0f) {
            SnifferResultsFragment.y(this.a);
        } else if (SnifferResultsFragment.x(this.a).getTextNumber() != 0) {
            SnifferResultsFragment.w(this.a);
        }
    }

    public final void b(float f) {
        if (f > 0.0f) {
            if (f >= 1.0f) {
                SnifferResultsFragment.v(this.a).setAlpha(0.0f);
                SnifferResultsFragment.v(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.z(this.a).setVisibility(0);
                SnifferResultsFragment.z(this.a).setAlpha(1.0f);
                SnifferResultsFragment.A(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            } else {
                SnifferResultsFragment.v(this.a).setAlpha(1.0f - f);
                SnifferResultsFragment.v(this.a).setVisibility(0);
                SnifferResultsFragment.z(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.A(this.a).setVisibility(0);
            }
        }
        if (f != -1.0f) {
            SnifferResultsFragment.y(this.a);
        }
    }
}
