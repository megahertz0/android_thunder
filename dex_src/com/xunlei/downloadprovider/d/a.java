package com.xunlei.downloadprovider.d;

import android.support.v4.media.session.PlaybackStateCompat;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.e;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: ConvertUtil.java
public final class a {
    public static final String[] a;
    public static final String[] b;

    static {
        a = new String[]{"B", "KB", "MB", "GB", "TB"};
        b = new String[]{"B", "K", "M", "G", "T"};
    }

    public static String a(long j) {
        return e.a(j, a);
    }

    public static long a(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int b(String str) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Deprecated
    public static String a(long j, int i) {
        String str;
        long j2 = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        double d = (double) j;
        int i2 = 0;
        while (j / 1024 > 0) {
            j /= 1024;
            i2++;
            if (i2 == 4) {
                break;
            }
        }
        switch (i2) {
            case SpdyAgent.ACCS_TEST_SERVER:
                j2 = 1;
                str = "B";
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = "KB";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                j2 = 1048576;
                str = "MB";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                j2 = 1073741824;
                str = "GB";
                break;
            default:
                j2 = 1099511627776L;
                str = "TB";
                break;
        }
        String toString = Double.toString(new BigDecimal(d).divide(new BigDecimal(j2), i, XZBDevice.DOWNLOAD_LIST_ALL).doubleValue());
        int indexOf;
        if (i == 0) {
            indexOf = toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            return -1 == indexOf ? toString + str : toString.substring(0, indexOf) + str;
        } else {
            if (str.equals("B")) {
                toString = toString.substring(0, toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
            }
            if (str.equals("KB")) {
                indexOf = toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
                if (indexOf != -1) {
                    toString = toString.substring(0, indexOf + 2);
                } else {
                    toString = toString + ".0";
                }
            }
            return toString + str;
        }
    }

    public static String a(long j, String str) {
        if (j < 10000) {
            return j >= 0 ? String.valueOf(j) : com.umeng.a.d;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(1);
            decimalFormat.setGroupingSize(0);
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            String format = decimalFormat.format((double) ((((float) j) * 1.0f) / 10000.0f));
            int indexOf = format.indexOf(".");
            if (indexOf > 0 && format.substring(indexOf, indexOf + 1).contentEquals(MessageService.MSG_DB_READY_REPORT)) {
                format = format.substring(0, indexOf + 1);
            }
            return format + str;
        }
    }

    public static String b(long j) {
        return e.a(j, a);
    }
}
