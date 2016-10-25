package com.xunlei.androidvip;

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

class XLAndroidVipUtil {
    public static final int IMEI_LEN = 15;
    private static final String TAG = "XLAndroidVipUtil";
    public static boolean isGetIMEI;
    public static boolean isGetMAC;
    private static boolean isLoadAndParseFile;
    private static String mIMEI;
    private static String mIdentifyFileName;
    private static String mMAC;
    private static String mPeerId;

    XLAndroidVipUtil() {
    }

    static {
        mPeerId = null;
        isGetIMEI = false;
        isGetMAC = false;
        mIMEI = null;
        mMAC = null;
        isLoadAndParseFile = false;
        mIdentifyFileName = "Identify.txt";
    }

    public static long getCurrentUnixTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getPeerid(Context context) {
        if (!isLoadAndParseFile) {
            loadAndParseFile(context, mIdentifyFileName);
        }
        if (mPeerId != null) {
            return mPeerId;
        }
        if (!isGetMAC) {
            mMAC = getMAC(context);
        }
        if (mMAC == null || !isGetMAC) {
            if (!isGetIMEI) {
                mIMEI = getIMEI(context);
            }
            if (mIMEI != null && isGetIMEI) {
                mPeerId = mIMEI + "V";
            }
        } else {
            mPeerId = mMAC + "004V";
        }
        return mPeerId;
    }

    public static String getMAC(Context context) {
        if (!isLoadAndParseFile) {
            loadAndParseFile(context, mIdentifyFileName);
        }
        if (isGetMAC && mMAC != null) {
            return mMAC;
        }
        Object wifiMacAddress = getWifiMacAddress();
        if (TextUtils.isEmpty(wifiMacAddress)) {
            return null;
        }
        String toUpperCase = wifiMacAddress.replaceAll(":", BuildConfig.VERSION_NAME).replaceAll(",", BuildConfig.VERSION_NAME).replaceAll("[.]", BuildConfig.VERSION_NAME).toUpperCase();
        isGetMAC = true;
        mMAC = toUpperCase;
        return toUpperCase;
    }

    @SuppressLint({"NewApi"})
    public static String getWifiMacAddress() {
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

    public static String getIMEI(Context context) {
        if (!isLoadAndParseFile) {
            loadAndParseFile(context, mIdentifyFileName);
        }
        if (isGetIMEI && mIMEI != null) {
            return mIMEI;
        }
        if (context != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String deviceId = telephonyManager.getDeviceId();
                if (deviceId == null) {
                    return deviceId;
                }
                if (deviceId.length() < 15) {
                    int length = 15 - deviceId.length();
                    while (true) {
                        int i = length - 1;
                        if (length <= 0) {
                            break;
                        }
                        deviceId = deviceId + "M";
                        length = i;
                    }
                }
                isGetIMEI = true;
                mIMEI = deviceId;
                return deviceId;
            }
        }
        return null;
    }

    public static String getIdentifyContent() {
        String str = BuildConfig.VERSION_NAME;
        String str2 = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(mPeerId)) {
            str = str + "peerid=" + mPeerId + "\n";
            str2 = str2 + "peerid=" + mPeerId + ";";
        }
        if (isGetMAC && !TextUtils.isEmpty(mMAC)) {
            str = str + "MAC=" + mMAC + "\n";
            str2 = str2 + "MAC=" + mMAC + ";";
        }
        if (!isGetIMEI || TextUtils.isEmpty(mIMEI)) {
            return str;
        }
        str = str + "IMEI=" + mIMEI;
        new StringBuilder().append(str2).append("IMEI=").append(mIMEI);
        return str;
    }

    private static void parseIdentify(String str) {
        if (str == null) {
            XLLog.e(TAG, "parseIdentify, item invalid");
            return;
        }
        String[] split = str.split("=");
        if (split.length != 2) {
            return;
        }
        if (split[0].compareTo("peerid") == 0) {
            if (split[1].trim().length() != 0) {
                mPeerId = split[1];
            }
            if (mPeerId != null && mPeerId.compareTo("null") == 0) {
                mPeerId = null;
            }
        } else if (split[0].compareTo("MAC") == 0) {
            if (split[1].trim().length() != 0) {
                mMAC = split[1];
            }
            if (mMAC == null || mMAC.compareTo("null") == 0) {
                mMAC = null;
            } else {
                isGetMAC = true;
            }
        } else if (split[0].compareTo("IMEI") == 0) {
            if (split[1].trim().length() != 0) {
                mIMEI = split[1];
            }
            if (mIMEI == null || mIMEI.compareTo("null") == 0) {
                mIMEI = null;
            } else {
                isGetIMEI = true;
            }
        }
    }

    private static void loadAndParseFile(Context context, String str) {
        XLLog.i(TAG, "loadAndParseFile start");
        isLoadAndParseFile = true;
        if (context == null || str == null) {
            XLLog.e(TAG, new StringBuilder("loadAndParseFile end, parameter invalid, fileName:").append(str).toString());
            return;
        }
        String readFromFile = readFromFile(context, str);
        if (readFromFile == null) {
            XLLog.i(TAG, "loadAndParseFile end, fileContext is empty");
            return;
        }
        String[] split = readFromFile.split("\n");
        for (String str2 : split) {
            parseIdentify(str2);
        }
        XLLog.i(TAG, "loadAndParseFile end");
    }

    private static void saveFile(Context context, String str) {
        if (context == null || str == null) {
            XLLog.e(TAG, new StringBuilder("saveFile, parameter invalid, fileName:").append(str).toString());
        } else {
            writeToFile(context, getIdentifyContent(), mIdentifyFileName);
        }
    }

    public static void writeToFile(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null) {
            XLLog.e(TAG, new StringBuilder("writeToFile, Parameter invalid, fileName:").append(str2).toString());
            return;
        }
        try {
            OutputStream openFileOutput = context.openFileOutput(str2, 0);
            try {
                openFileOutput.write(str.getBytes("utf-8"));
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

    public static String readFromFile(Context context, String str) {
        String str2 = null;
        if (context == null || str == null) {
            XLLog.e(TAG, new StringBuilder("readFromFile, parameter invalid, fileName:").append(str).toString());
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
                XLLog.i(TAG, str + " File Not Found");
            }
        }
        return str2;
    }

    public static int getNetworkType(Context context) {
        if (context == null) {
            XLLog.e(TAG, "getNetworkType, context invalid");
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
            case IMEI_LEN:
                Object obj = MqttConnectOptions.MQTT_VERSION_3_1;
                break;
            default:
                i = 1;
                break;
        }
        return i;
    }
}
