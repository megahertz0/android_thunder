package anetwork.channel.b;

import anet.channel.util.ALog;
import com.taobao.orange.OrangeConfig;
import com.taobao.orange.OrangeConfigListenerV1;

// compiled from: Taobao
public final class c {
    private static boolean a;

    static {
        a = false;
        try {
            Class.forName("com.taobao.orange.OrangeConfig");
            a = true;
        } catch (Exception e) {
            a = false;
        }
    }

    public static void a(String[] strArr, OrangeConfigListenerV1 orangeConfigListenerV1) {
        if (a) {
            OrangeConfig.getInstance().registerListener(strArr, orangeConfigListenerV1);
        } else {
            ALog.w("OrangeAdapter", "no orange sdk", null, new Object[0]);
        }
    }

    public static String a(String str, String str2, String str3) {
        if (a) {
            return OrangeConfig.getInstance().getConfig(str, str2, str3);
        }
        ALog.w("OrangeAdapter", "no orange sdk", null, new Object[0]);
        return str3;
    }
}
