package anet.channel.util;

import android.util.Base64;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

// compiled from: Taobao
public class f {
    public final Proxy a;
    public final String b;
    public final String c;

    private f(Proxy proxy, String str, String str2) {
        this.a = proxy;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(this.b).append(":").append(this.c);
        String encodeToString = Base64.encodeToString(stringBuilder.toString().getBytes(), 0);
        StringBuilder stringBuilder2 = new StringBuilder(64);
        stringBuilder2.append("Basic ").append(encodeToString);
        return stringBuilder2.toString();
    }

    public static f a(String str, int i, String str2, String str3) {
        return new f(new Proxy(Type.HTTP, new InetSocketAddress(str, i)), str2, str3);
    }
}
