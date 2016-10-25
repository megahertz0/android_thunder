package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.strategy.dispatch.a;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import java.util.Locale;

// compiled from: Taobao
public class n {
    public static String[] a;
    public static String[][] b;
    public static String c;
    public static String[] d;
    public static String e;
    public static String[] f;

    static {
        a = new String[]{"acs.m.taobao.com", "acs.wapa.taobao.com", "acs.waptest.taobao.com"};
        r0 = new String[3][];
        r0[0] = new String[]{StringUtils.longToIP(140205166083L), StringUtils.longToIP(106011052093L)};
        r0[1] = new String[]{StringUtils.longToIP(110075206079L)};
        r0[2] = new String[]{StringUtils.longToIP(10125050231L)};
        b = r0;
        c = "unitacs.m.taobao.com";
        d = new String[]{StringUtils.longToIP(42156146086L), StringUtils.longToIP(140205117097L)};
        e = "unszacs.m.taobao.com";
        f = new String[]{StringUtils.longToIP(106011011086L), StringUtils.longToIP(106011012009L)};
    }

    public static String a() {
        return a[GlobalAppRuntimeInfo.getEnv().getEnvMode()];
    }

    public static String[] b() {
        return b[GlobalAppRuntimeInfo.getEnv().getEnvMode()];
    }

    public static boolean a(String str) {
        return TextUtils.isEmpty(str) ? false : str.equalsIgnoreCase(a());
    }

    public static String b(String str) {
        return StringUtils.buildString(str, a());
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        String a = a();
        return toLowerCase.equals(a) || (toLowerCase.startsWith("un") && toLowerCase.endsWith(a));
    }

    public static boolean d(String str) {
        return c(str) || a.a(str);
    }

    public static String a(String str, String str2) {
        return StringUtils.buildString(str, HttpConstant.SCHEME_SPLIT, str2);
    }

    public static String e(String str) {
        int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (indexOf != -1) {
            return str.substring(indexOf + 3);
        }
        throw new RuntimeException("invalid key");
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        if (toCharArray.length <= 0 || toCharArray.length > 255) {
            return false;
        }
        int i = 0;
        boolean z = false;
        while (i < toCharArray.length) {
            if ((toCharArray[i] >= 'A' && toCharArray[i] <= 'Z') || (toCharArray[i] >= 'a' && toCharArray[i] <= 'z')) {
                Object obj = 1;
            } else if ((toCharArray[i] < '0' || toCharArray[i] > '9') && toCharArray[i] != '.' && toCharArray[i] != '-') {
                return false;
            }
            i++;
        }
        return z;
    }
}
