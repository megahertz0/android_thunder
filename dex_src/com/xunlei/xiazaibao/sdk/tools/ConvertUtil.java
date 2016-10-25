package com.xunlei.xiazaibao.sdk.tools;

import android.graphics.Paint;
import android.util.Base64;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ConvertUtil {
    private static final long BASE_B = 1;
    private static final long BASE_GB = 1073741824;
    private static final long BASE_KB = 1024;
    private static final long BASE_MB = 1048576;
    private static final long BASE_TB = 1099511627776L;
    public static final String UNIT_BIT = "B";
    public static final String UNIT_GB = "GB";
    public static final String UNIT_KB = "KB";
    public static final String UNIT_MB = "MB";
    public static final String UNIT_TB = "TB";

    public static String formatByte(long j) {
        if (j < 1024) {
            return j + " B";
        }
        String str = "KMGTPE".charAt(((int) (Math.log((double) j) / Math.log(1024.0d))) - 1);
        return String.format("%.2f %sB", new Object[]{Double.valueOf(((double) j) / Math.pow(1024.0d, (double) r0)), str});
    }

    public static String byteConvert(long j) {
        if (((double) j) / 1.048576E9d >= 1.0d) {
            return Double.toString(new BigDecimal(((double) j) / 1.073741824E9d).setScale(SimpleLog.LOG_LEVEL_DEBUG, 1).doubleValue()) + UNIT_GB;
        } else if (((double) j) / 1024000.0d >= 1.0d) {
            return Double.toString(new BigDecimal(((double) j) / 1048576.0d).setScale(1, 1).doubleValue()) + UNIT_MB;
        } else if (((double) j) / 1000.0d < 1.0d) {
            return j + UNIT_BIT;
        } else {
            return Double.toString(new BigDecimal(((double) j) / 1024.0d).setScale(1, 1).doubleValue()) + UNIT_KB;
        }
    }

    public static String gConvert(double d) {
        double d2 = d / 1024.0d;
        if (d2 >= 1.0d) {
            return Double.toString(new BigDecimal(d2).setScale(SimpleLog.LOG_LEVEL_DEBUG, 1).doubleValue()) + UNIT_TB;
        }
        return Double.toString(new BigDecimal(d).setScale(SimpleLog.LOG_LEVEL_DEBUG, 1).doubleValue()) + UNIT_GB;
    }

    public static String byteConvert(long j, boolean z) {
        String byteConvert = byteConvert(j);
        if (!z || byteConvert.length() < 7) {
            return byteConvert;
        }
        String substring = byteConvert.substring(0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (substring.endsWith(".")) {
            substring = substring.substring(0, MqttConnectOptions.MQTT_VERSION_3_1);
        }
        return substring + byteConvert.substring(byteConvert.length() - 2, byteConvert.length());
    }

    public static int levelToScore(int i) {
        return ((i + 1) * 50) * (i + 4);
    }

    public static int scoreToLevel(int i) {
        int i2 = 0;
        while (i >= (i2 * 50) * (i2 + 3)) {
            i2++;
        }
        return i2 > 1 ? i2 - 1 : 0;
    }

    public static long stringToLong(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int stringToInt(String str) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int ipAddrToInt(String str) {
        if (str == null || !str.matches("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$")) {
            return 0;
        }
        String[] split = str.split("\\.");
        int parseInt = Integer.parseInt(split[2]) << 16;
        return ((Integer.parseInt(split[0]) | (Integer.parseInt(split[1]) << 8)) | parseInt) | (Integer.parseInt(split[3]) << 24);
    }

    public static String[] convertSpeeds(long j, int i) {
        String[] strArr = new String[2];
        String convertFileSize = convertFileSize(j, 0);
        System.out.println(convertFileSize);
        String substring = convertFileSize.substring(convertFileSize.length() - 2);
        strArr[0] = convertFileSize.substring(0, convertFileSize.lastIndexOf(substring));
        if (!(substring.equals(UNIT_KB) || substring.equals(UNIT_MB) || substring.equals(UNIT_GB) || substring.equals(UNIT_TB))) {
            substring = convertFileSize.substring(convertFileSize.length() - 1);
            strArr[0] = convertFileSize.substring(0, convertFileSize.lastIndexOf(substring));
        }
        strArr[1] = substring + "/S";
        return strArr;
    }

    public static String convertPercent(float f, int i, String str) {
        float floatValue = new BigDecimal((double) (100.0f * f)).divide(new BigDecimal(1), i, MqttConnectOptions.MQTT_VERSION_3_1_1).floatValue();
        return Float.compare(floatValue, (float) Math.pow(10.0d, (double) (-i))) < 0 ? str : String.valueOf(floatValue);
    }

    public static String convertFileSize(long j, int i) {
        String str;
        long j2 = BASE_KB;
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
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                j2 = BASE_B;
                str = UNIT_BIT;
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                str = UNIT_KB;
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                j2 = BASE_MB;
                str = UNIT_MB;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                j2 = BASE_GB;
                str = UNIT_GB;
                break;
            default:
                j2 = BASE_TB;
                str = UNIT_TB;
                break;
        }
        String toString = Double.toString(new BigDecimal(d).divide(new BigDecimal(j2), i, MqttConnectOptions.MQTT_VERSION_3_1_1).doubleValue());
        int indexOf;
        if (i == 0) {
            indexOf = toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            return -1 == indexOf ? toString + str : toString.substring(0, indexOf) + str;
        } else {
            if (str.equals(UNIT_BIT)) {
                toString = toString.substring(0, toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
            }
            if (str.equals(UNIT_KB)) {
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

    public static String ipAddressToString(int i) {
        StringBuffer stringBuffer = new StringBuffer(16);
        int i2 = i >>> 8;
        i2 >>>= 8;
        stringBuffer.append(i & 255).append('.').append(i2 & 255).append('.').append(i2 & 255).append('.').append((i2 >>> 8) & 255);
        return stringBuffer.toString();
    }

    public static int parseInt(String str) {
        int i = 0;
        if (str != null) {
            int i2 = 0;
            while (i2 < str.length()) {
                if (str.charAt(i2) >= '0' && str.charAt(i2) <= '9') {
                    i = (i * 10) + (str.charAt(i2) - 48);
                }
                i2++;
            }
        }
        return i;
    }

    public static int Str2Int(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return i;
        }
    }

    public static String byteConvertToSpeed(long j, boolean z) {
        String str;
        if (((double) j) / 1.048576E9d >= 1.0d) {
            str = Double.toString(new BigDecimal(((double) j) / 1.073741824E9d).setScale(SimpleLog.LOG_LEVEL_DEBUG, 1).doubleValue()) + UNIT_GB;
        } else if (((double) j) / 1024000.0d >= 1.0d) {
            str = Double.toString(new BigDecimal(((double) j) / 1048576.0d).setScale(1, 1).doubleValue()) + UNIT_MB;
        } else if (((double) j) / 1000.0d >= 1.0d) {
            str = Double.toString(new BigDecimal(((double) j) / 1024.0d).setScale(1, 1).doubleValue()) + UNIT_KB;
        } else {
            str = j + UNIT_BIT;
            z = false;
        }
        if (!z || str.length() < 7) {
            return str;
        }
        String substring = str.substring(0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (substring.endsWith(".")) {
            substring = substring.substring(0, MqttConnectOptions.MQTT_VERSION_3_1);
        }
        return substring + str.substring(str.length() - 2, str.length());
    }

    public static String convertFromSecToTime(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        long j2 = j / 3600;
        long j3 = (j % 3600) / 60;
        long j4 = j % 60;
        String valueOf = j3 >= 10 ? String.valueOf(j3) : new StringBuilder("0").append(j3).toString();
        String valueOf2 = j4 >= 10 ? String.valueOf(j4) : new StringBuilder("0").append(j4).toString();
        if (j2 > 0) {
            stringBuilder.append(j2 >= 10 ? String.valueOf(j2) : new StringBuilder("0").append(j2).toString()).append(":").append(valueOf).append(":").append(valueOf2);
        } else {
            stringBuilder.append(valueOf).append(":").append(valueOf2);
        }
        return stringBuilder.toString();
    }

    public static String convertFormatNum(long j) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        DecimalFormat decimalFormat2 = new DecimalFormat(",###.0");
        if (j < 100000) {
            return decimalFormat.format(j);
        }
        return decimalFormat2.format((double) (((float) j) / 10000.0f)) + "\u4e07";
    }

    public static String convertFromProductTime(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        long j2 = j / 3600;
        long j3 = j / 60;
        if (j2 > 0) {
            stringBuilder.append(j2).append("\u5c0f\u65f6");
        } else if (j3 > 0) {
            stringBuilder.append(j3).append("\u5206\u949f");
        }
        return stringBuilder.toString();
    }

    public static String longToIP(long j) {
        StringBuffer stringBuffer = new StringBuffer(BuildConfig.VERSION_NAME);
        stringBuffer.append(String.valueOf(255 & j));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((65535 & j) >>> 8));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((16777215 & j) >>> 16));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf(j >>> 24));
        return stringBuffer.toString();
    }

    public static double round(double d, int i) {
        return new BigDecimal(d).setScale(i, MqttConnectOptions.MQTT_VERSION_3_1_1).doubleValue();
    }

    public static BigDecimal roundDown(String str, int i) {
        int i2 = 0;
        int indexOf = str.indexOf(".");
        if (indexOf == -1) {
            String str2 = str + ".";
            while (i2 < i) {
                str2 = str2 + "0";
                i2++;
            }
            return new BigDecimal(str2);
        } else if (str.length() - indexOf > i) {
            return new BigDecimal(str.substring(0, (indexOf + i) + 1));
        } else {
            for (i2 = (str.length() - indexOf) - 1; i2 < i; i2++) {
                str = str + "0";
            }
            return new BigDecimal(str);
        }
    }

    public static String round2String(double d, int i) {
        return String.format(new StringBuilder("%.").append(i).append("f").toString(), new Object[]{Double.valueOf(d)});
    }

    public static String date2String(Date date) {
        return date == null ? "year-month-day hour:minite" : new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
    }

    public static float getTextWidth(Paint paint, String str) {
        float f = 0.0f;
        if (str != null) {
            float[] fArr = new float[str.length()];
            paint.getTextWidths(str, fArr);
            int i = 0;
            while (i < fArr.length) {
                float f2 = fArr[i] + f;
                i++;
                f = f2;
            }
        }
        return f;
    }

    public static int compare(BigDecimal bigDecimal, String str) {
        return bigDecimal.compareTo(new BigDecimal(str));
    }

    public static String timeFormat(long j) {
        return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date(j));
    }

    public static String md5(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            while (i < 16) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new StringBuilder("x").append(new String(cArr2).substring(SpdyProtocol.PUBKEY_PSEQ_ADASH, R.styleable.Toolbar_subtitleTextColor)).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "x1234567890abcde";
        }
    }

    public static String mapConvertRequestBody(Map<String, String> map, int i) {
        if (i != 0) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : map.entrySet()) {
            stringBuffer.append((String) entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) entry.getValue());
            stringBuffer.append(";");
        }
        return stringBuffer.toString();
    }

    public static String mapConvert2UrlParam(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : map.entrySet()) {
            stringBuffer.append((String) entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) entry.getValue());
            stringBuffer.append("&");
        }
        String toString = stringBuffer.toString();
        return toString.substring(0, toString.length() - 1);
    }

    public static String mapConvertCookie(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : map.entrySet()) {
            stringBuffer.append((String) entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) entry.getValue());
            stringBuffer.append(";");
        }
        return stringBuffer.toString();
    }

    public static String thunder2Normal(String str) {
        if (!str.contains("thunder://")) {
            return str;
        }
        XZBLog.d("thunder2Normal", new StringBuilder("thunder\u94fe\uff0c\u9700\u8981\u8f6c\u6362\u539f\u59cburl = ").append(str).toString());
        String replaceAll = str.replaceAll("thunder://", BuildConfig.VERSION_NAME);
        XZBLog.d("thunder2Normal", new StringBuilder("thunder\u94fe\uff0c\u53bb\u6389thunder:// url = ").append(replaceAll).toString());
        try {
            byte[] decode = Base64.decode(replaceAll, 0);
            if (decode == null) {
                return str;
            }
            try {
                replaceAll = new String(decode, "gb2312");
                XZBLog.d("thunder2Normal", "gb2312 \u89e3\u7801\u6210\u529f");
            } catch (UnsupportedEncodingException e) {
                replaceAll = new String(decode);
                XZBLog.d("thunder2Normal", "UnsupportedEncodingException");
            }
            XZBLog.d("thunder2Normal", new StringBuilder("thunder\u94fe\uff0cbase64\u89e3\u7801\u540e\uff1a url = ").append(replaceAll).toString());
            str = replaceAll.substring(SimpleLog.LOG_LEVEL_DEBUG, replaceAll.length() - 2);
            XZBLog.d("thunder2Normal", new StringBuilder("thunder\u94fe\uff0cbase64\u89e3\u7801\u540e\u53bb\u6389\u5934\u5c3e\u7684AA\u548cZZ\uff1a url = ").append(str).toString());
            return str;
        } catch (IllegalArgumentException e2) {
            return str;
        }
    }
}
