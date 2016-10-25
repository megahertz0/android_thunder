package com.xunlei.android.xlstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.util.Collections;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class XLUtil {
    public static String a;
    public static int b;
    public static int c;
    public static String d;
    public static boolean e;
    public static boolean f;
    public static boolean g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static boolean l;
    private static String m;

    public enum GUID_TYPE {
        DEFAULT,
        JUST_IMEI,
        JUST_MAC,
        ALL;

        static {
            DEFAULT = new com.xunlei.android.xlstat.XLUtil.GUID_TYPE("DEFAULT", 0);
            JUST_IMEI = new com.xunlei.android.xlstat.XLUtil.GUID_TYPE("JUST_IMEI", 1);
            JUST_MAC = new com.xunlei.android.xlstat.XLUtil.GUID_TYPE("JUST_MAC", 2);
            ALL = new com.xunlei.android.xlstat.XLUtil.GUID_TYPE("ALL", 3);
            a = new com.xunlei.android.xlstat.XLUtil.GUID_TYPE[]{DEFAULT, JUST_IMEI, JUST_MAC, ALL};
        }
    }

    public enum NetWorkCarrier {
        UNKNOWN,
        CMCC,
        CU,
        CT;

        static {
            UNKNOWN = new com.xunlei.android.xlstat.XLUtil.NetWorkCarrier("UNKNOWN", 0);
            CMCC = new com.xunlei.android.xlstat.XLUtil.NetWorkCarrier("CMCC", 1);
            CU = new com.xunlei.android.xlstat.XLUtil.NetWorkCarrier("CU", 2);
            CT = new com.xunlei.android.xlstat.XLUtil.NetWorkCarrier("CT", 3);
            a = new com.xunlei.android.xlstat.XLUtil.NetWorkCarrier[]{UNKNOWN, CMCC, CU, CT};
        }
    }

    static {
        h = null;
        i = null;
        a = null;
        b = -1;
        c = -1;
        d = null;
        e = false;
        f = false;
        g = false;
        j = null;
        k = null;
        l = false;
        m = "Identify.txt";
    }

    public static String a(Context context) {
        if (!l) {
            a(context, m);
        }
        if (h != null) {
            return h;
        }
        if (!g) {
            String str;
            if (!l) {
                a(context, m);
            }
            if (!g || k == null) {
                str = null;
                Object a = a();
                if (!TextUtils.isEmpty(a)) {
                    str = a.replaceAll(":", BuildConfig.VERSION_NAME).replaceAll(",", BuildConfig.VERSION_NAME).replaceAll("[.]", BuildConfig.VERSION_NAME).toUpperCase();
                    g = true;
                    k = str;
                    b(context, m);
                }
            } else {
                str = k;
            }
            k = str;
        }
        if (k == null || !g) {
            if (!f) {
                j = c(context);
            }
            if (j != null && f) {
                h = j + "V";
            }
        } else {
            h = k + "004V";
        }
        if (h != null) {
            b(context, m);
        }
        return h;
    }

    @SuppressLint({"NewApi"})
    private static String a() {
        try {
            String str = "wlan0";
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase(str)) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String c(Context context) {
        Exception exception;
        Exception exception2;
        if (!l) {
            a(context, m);
        }
        if (f && j != null) {
            return j;
        }
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String deviceId;
                try {
                    deviceId = telephonyManager.getDeviceId();
                    if (deviceId == null) {
                        return deviceId;
                    }
                    try {
                        if (deviceId.length() < 15) {
                            String str = deviceId;
                            int length = 15 - deviceId.length();
                            while (true) {
                                int i = length - 1;
                                if (length <= 0) {
                                    break;
                                }
                                try {
                                    str = str + "M";
                                    length = i;
                                } catch (Exception e) {
                                    exception = e;
                                    deviceId = str;
                                    exception2 = exception;
                                }
                            }
                            deviceId = str;
                        }
                        f = true;
                        j = deviceId;
                        b(context, m);
                        return deviceId;
                    } catch (Exception e2) {
                        exception2 = e2;
                    }
                } catch (Exception e3) {
                    exception = e3;
                    deviceId = null;
                    exception2 = exception;
                    exception2.printStackTrace();
                    return deviceId;
                }
            }
        }
        return null;
    }

    private static void a(Context context, String str) {
        XLLog.a("XLUtil", "loadAndParseFile start");
        l = true;
        if (context == null || str == null) {
            XLLog.c("XLUtil", new StringBuilder("loadAndParseFile end, parameter invalid, fileName:").append(str).toString());
            return;
        }
        String c = c(context, str);
        if (c == null) {
            XLLog.a("XLUtil", "loadAndParseFile end, fileContext is empty");
            return;
        }
        String[] split = c.split("\n");
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            if (str2 == null) {
                XLLog.c("XLUtil", "parseIdentify, item invalid");
            } else {
                String[] split2 = str2.split("=");
                if (split2.length == 2) {
                    if (split2[0].compareTo("peerid") == 0) {
                        if (split2[1].trim().length() != 0) {
                            h = split2[1];
                        }
                        if (h != null && h.compareTo("null") == 0) {
                            h = null;
                        }
                    } else if (split2[0].compareTo("MAC") == 0) {
                        if (split2[1].trim().length() != 0) {
                            k = split2[1];
                        }
                        if (k == null || k.compareTo("null") == 0) {
                            k = null;
                        } else {
                            g = true;
                        }
                    } else if (split2[0].compareTo("IMEI") == 0) {
                        if (split2[1].trim().length() != 0) {
                            j = split2[1];
                        }
                        if (j == null || j.compareTo("null") == 0) {
                            j = null;
                        } else {
                            f = true;
                        }
                    }
                }
            }
        }
        XLLog.a("XLUtil", "loadAndParseFile end");
    }

    private static void b(Context context, String str) {
        if (context == null || str == null) {
            XLLog.c("XLUtil", new StringBuilder("saveFile, parameter invalid, fileName:").append(str).toString());
            return;
        }
        String str2 = BuildConfig.VERSION_NAME;
        String str3 = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(h)) {
            str2 = str2 + "peerid=" + h + "\n";
            str3 = str3 + "peerid=" + h + ";";
        }
        if (g && !TextUtils.isEmpty(k)) {
            str2 = str2 + "MAC=" + k + "\n";
            str3 = str3 + "MAC=" + k + ";";
        }
        if (f && !TextUtils.isEmpty(j)) {
            str2 = str2 + "IMEI=" + j;
            new StringBuilder().append(str3).append("IMEI=").append(j);
        }
        str3 = m;
        if (context == null || str2 == null || str3 == null) {
            XLLog.c("XLUtil", new StringBuilder("writeToFile, Parameter invalid, fileName:").append(str3).toString());
            return;
        }
        try {
            OutputStream openFileOutput = context.openFileOutput(str3, 0);
            try {
                openFileOutput.write(str2.getBytes("utf-8"));
                openFileOutput.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
    }

    public static int b(Context context) {
        if (context == null) {
            XLLog.c("XLUtil", "getNetworkType, context invalid");
            return 0;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return 1;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 1;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return SimpleLog.LOG_LEVEL_DEBUG;
        }
        if (type != 0) {
            return 1;
        }
        int i;
        switch (activeNetworkInfo.getSubtype()) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
            case R.styleable.Toolbar_titleMargins:
            case R.styleable.Toolbar_titleMarginStart:
            case R.styleable.Toolbar_titleMarginEnd:
            case R.styleable.Toolbar_titleMarginTop:
                Object obj = MqttConnectOptions.MQTT_VERSION_3_1;
                break;
            default:
                i = 1;
                break;
        }
        return i;
    }

    private static String c(Context context, String str) {
        String str2 = null;
        if (context == null || str == null) {
            XLLog.c("XLUtil", new StringBuilder("readFromFile, parameter invalid, fileName:").append(str).toString());
        } else {
            try {
                InputStream openFileInput = context.openFileInput(str);
                byte[] bArr = new byte[256];
                try {
                    int read = openFileInput.read(bArr);
                    if (read > 0) {
                        str2 = new String(bArr, 0, read, "utf-8");
                    }
                    openFileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e2) {
                XLLog.a("XLUtil", str + " File Not Found");
            }
        }
        return str2;
    }
}
