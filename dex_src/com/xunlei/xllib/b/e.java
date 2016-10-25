package com.xunlei.xllib.b;

import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.tools.ConvertUtil;
import java.math.BigDecimal;
import org.apache.commons.logging.impl.SimpleLog;

public abstract class e {
    public static final String[] a;
    public static final String[] b;

    public static class a {
        public static final String[] a;

        public static interface a {
            boolean a(double d, int i, int i2, String[] strArr, String[] strArr2);
        }

        static {
            a = new String[]{BuildConfig.VERSION_NAME, "K", "M", "G", "T"};
        }

        public static String[] a(long j, int i, String[] strArr, a aVar) {
            String[] strArr2;
            String[] strArr3 = new String[]{BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME};
            if (strArr == null) {
                strArr2 = a;
            } else {
                strArr2 = strArr;
            }
            long j2 = j < 0 ? -j : j;
            int max = Math.max(strArr2.length - 1, 1);
            int i2 = 0;
            while (j2 > 0) {
                j2 /= 1024;
                if (j2 > 0) {
                    i2++;
                    continue;
                }
                if (i2 >= max) {
                    break;
                }
            }
            j2 = 1;
            for (max = 0; max < i2; max++) {
                j2 *= 1024;
            }
            aVar.a(new BigDecimal(j).divide(new BigDecimal(j2), i, 1).doubleValue(), i2, i, strArr3, strArr2);
            return strArr3;
        }
    }

    static {
        a = new String[]{ConvertUtil.UNIT_BIT, ConvertUtil.UNIT_KB, ConvertUtil.UNIT_MB, ConvertUtil.UNIT_GB, ConvertUtil.UNIT_TB};
        b = new String[]{"B/s", "KB/s", "MB/s", "GB/s"};
    }

    public static String a(long j) {
        String[] a = a(j, SimpleLog.LOG_LEVEL_DEBUG, a, true);
        return a[0] + a[1];
    }

    public static String a(long j, String[] strArr) {
        return a(j, strArr, true);
    }

    public static String a(long j, String[] strArr, boolean z) {
        String[] a = a(j, 1, strArr, z);
        return a[0] + a[1];
    }

    public static String[] a(long j, int i, String[] strArr, boolean z) {
        return a.a(j, i, strArr, new f(z, j));
    }
}
