package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.b;
import com.google.protobuf.micro.d;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class a {

    public static final class a extends d {
        private boolean a;
        private int b;
        private boolean c;
        private boolean d;
        private boolean e;
        private int f;
        private boolean g;
        private boolean h;
        private List<String> i;
        private int j;

        public a() {
            this.b = 0;
            this.d = false;
            this.f = 0;
            this.h = false;
            this.i = Collections.emptyList();
            this.j = -1;
        }

        public static com.xiaomi.push.protobuf.a.a b(byte[] bArr) {
            return (com.xiaomi.push.protobuf.a.a) new com.xiaomi.push.protobuf.a.a().a(bArr);
        }

        public static com.xiaomi.push.protobuf.a.a c(com.google.protobuf.micro.a aVar) {
            return new com.xiaomi.push.protobuf.a.a().b(aVar);
        }

        public final int a() {
            int i = 0;
            int d = d() ? b.d(1, c()) + 0 : 0;
            if (f()) {
                d += b.b(SimpleLog.LOG_LEVEL_DEBUG, e());
            }
            if (h()) {
                d += b.c(MqttConnectOptions.MQTT_VERSION_3_1, g());
            }
            int b = j() ? d + b.b(MqttConnectOptions.MQTT_VERSION_3_1_1, i()) : d;
            for (String str : k()) {
                i += b.b(str);
            }
            d = (b + i) + (k().size() * 1);
            this.j = d;
            return d;
        }

        public final /* synthetic */ d a(com.google.protobuf.micro.a aVar) {
            return b(aVar);
        }

        public final com.xiaomi.push.protobuf.a.a a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public final com.xiaomi.push.protobuf.a.a a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (this.i.isEmpty()) {
                this.i = new ArrayList();
            }
            this.i.add(str);
            return this;
        }

        public final com.xiaomi.push.protobuf.a.a a(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public final void a(b bVar) {
            if (d()) {
                bVar.b(1, c());
            }
            if (f()) {
                bVar.a(SimpleLog.LOG_LEVEL_DEBUG, e());
            }
            if (h()) {
                bVar.a(MqttConnectOptions.MQTT_VERSION_3_1, g());
            }
            if (j()) {
                bVar.a(MqttConnectOptions.MQTT_VERSION_3_1_1, i());
            }
            for (String str : k()) {
                bVar.a(SimpleLog.LOG_LEVEL_ERROR, str);
            }
        }

        public final com.xiaomi.push.protobuf.a.a b(int i) {
            this.e = true;
            this.f = i;
            return this;
        }

        public final com.xiaomi.push.protobuf.a.a b(com.google.protobuf.micro.a aVar) {
            while (true) {
                int a = aVar.a();
                switch (a) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                        return this;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                        a(aVar.f());
                        break;
                    case SpdyProtocol.CUSTOM:
                        a(aVar.d());
                        break;
                    case R.styleable.Toolbar_subtitleTextColor:
                        b(aVar.c());
                        break;
                    case org.android.agoo.common.a.ORDERED:
                        b(aVar.d());
                        break;
                    case R.styleable.AppCompatTheme_dialogTheme:
                        a(aVar.e());
                        break;
                    default:
                        if (!a(aVar, a)) {
                            return this;
                        }
                }
            }
        }

        public final com.xiaomi.push.protobuf.a.a b(boolean z) {
            this.g = true;
            this.h = z;
            return this;
        }

        public final int c() {
            return this.b;
        }

        public final boolean d() {
            return this.a;
        }

        public final boolean e() {
            return this.d;
        }

        public final boolean f() {
            return this.c;
        }

        public final int g() {
            return this.f;
        }

        public final boolean h() {
            return this.e;
        }

        public final boolean i() {
            return this.h;
        }

        public final boolean j() {
            return this.g;
        }

        public final List<String> k() {
            return this.i;
        }

        public final int l() {
            return this.i.size();
        }
    }
}
