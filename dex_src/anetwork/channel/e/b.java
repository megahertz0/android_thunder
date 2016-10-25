package anetwork.channel.e;

import anet.channel.util.StringUtils;
import com.alipay.sdk.util.h;
import java.util.Collections;
import java.util.Map;

// compiled from: Taobao
final class b implements a {
    private Map<String, String> a;

    // compiled from: Taobao
    private static class a {
        public static b a;

        static {
            a = new b();
        }
    }

    private b() {
        this.a = Collections.synchronizedMap(new c(this));
    }

    public final void a(String str, anetwork.channel.f.a aVar) {
        if (!StringUtils.isBlank(str)) {
            StringBuilder stringBuilder = new StringBuilder(48);
            stringBuilder.append("{\"oneWayTime\" : ").append(aVar.m).append(", \"totalSize\" : ").append(aVar.z).append(h.d);
            this.a.put(str, stringBuilder.toString());
        }
    }
}
