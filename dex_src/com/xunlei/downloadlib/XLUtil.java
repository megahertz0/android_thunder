package com.xunlei.downloadlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.xunlei.downloadlib.XLUtil.GUID_TYPE;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

class XLUtil {
    public static final int IMEI_LEN = 15;
    public static int NETWORKSUBTYPE = 0;
    public static int NETWORKTYPE = 0;
    public static boolean PRINT_LOG = false;
    public static String SSID = null;
    private static final String TAG = "XLUtil";
    public static boolean isGetIMEI = false;
    public static boolean isGetMAC = false;
    private static boolean isLoadAndParseFile = false;
    public static String mAPNName = null;
    private static String mIMEI = null;
    private static String mIdentifyFileName = null;
    private static String mMAC = null;
    private static String mOSVersion = null;
    private static String mPeerId = null;
    public static final int mProductId = 10101;

    public enum GUID_TYPE {
        DEFAULT,
        JUST_IMEI,
        JUST_MAC,
        ALL
    }

    public static class GuidInfo {
        public String mGuid;
        public GUID_TYPE mType;

        public GuidInfo() {
            this.mType = GUID_TYPE.DEFAULT;
            this.mGuid = null;
        }
    }

    public enum NetWorkCarrier {
        UNKNOWN,
        CMCC,
        CU,
        CT
    }

    XLUtil() {
    }

    static {
        mPeerId = null;
        mOSVersion = null;
        mAPNName = null;
        NETWORKTYPE = -1;
        NETWORKSUBTYPE = -1;
        SSID = null;
        PRINT_LOG = false;
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
        if (mPeerId != null) {
            saveFile(context, mIdentifyFileName);
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
        saveFile(context, mIdentifyFileName);
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
        Exception exception;
        Exception exception2;
        if (!isLoadAndParseFile) {
            loadAndParseFile(context, mIdentifyFileName);
        }
        if (isGetIMEI && mIMEI != null) {
            return mIMEI;
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
                        isGetIMEI = true;
                        mIMEI = deviceId;
                        saveFile(context, mIdentifyFileName);
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

    public static GuidInfo generateGuid(Context context) {
        GuidInfo guidInfo = new GuidInfo();
        GUID_TYPE guid_type = GUID_TYPE.DEFAULT;
        if (!isGetIMEI) {
            mIMEI = getIMEI(context);
        }
        if (isGetIMEI) {
            guid_type = GUID_TYPE.JUST_IMEI;
        } else {
            mIMEI = "000000000000000";
        }
        if (!isGetMAC) {
            mMAC = getMAC(context);
        }
        if (!isGetMAC) {
            mMAC = "000000000000";
        } else if (guid_type == GUID_TYPE.JUST_IMEI) {
            guid_type = GUID_TYPE.ALL;
        } else {
            guid_type = GUID_TYPE.JUST_MAC;
        }
        guidInfo.mGuid = mIMEI + "_" + mMAC;
        guidInfo.mType = guid_type;
        return guidInfo;
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

    public static String getOSVersion(Context context) {
        if (mOSVersion == null) {
            mOSVersion = new StringBuilder("SDKV = ").append(VERSION.RELEASE).toString();
            mOSVersion += "_MANUFACTURER = " + Build.MANUFACTURER;
            mOSVersion += "_MODEL = " + Build.MODEL;
            mOSVersion += "_PRODUCT = " + Build.PRODUCT;
            mOSVersion += "_FINGERPRINT = " + Build.FINGERPRINT;
            mOSVersion += "_CPU_ABI = " + Build.CPU_ABI;
            mOSVersion += "_ID = " + Build.ID;
        }
        return mOSVersion;
    }

    public static void killProcess() {
        Process.killProcess(Process.myPid());
    }

    public static String getAPNName(Context context) {
        return context != null ? ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0).getExtraInfo() : null;
    }

    public static String getSSID(Context context) {
        if (context == null) {
            XLLog.e(TAG, "getSSID, context invalid");
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
        if (wifiManager != null) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        }
        return null;
    }

    public static String getBSSID(Context context) {
        if (context == null) {
            XLLog.e(TAG, "getBSSID, context invalid");
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Utility.NETWORK_WIFI);
        if (wifiManager != null) {
            try {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getBSSID();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static int getNetworkType(Context context) {
        if (context == null) {
            XLLog.e(TAG, "getNetworkType, context invalid");
            return 0;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return 0;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return SpdyProtocol.PUBKEY_PSEQ_ADASH;
        }
        if (type != 0) {
            return SimpleLog.LOG_LEVEL_ERROR;
        }
        int i;
        switch (activeNetworkInfo.getSubtype()) {
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                return SimpleLog.LOG_LEVEL_DEBUG;
            case MqttConnectOptions.MQTT_VERSION_3_1:
            case SimpleLog.LOG_LEVEL_ERROR:
            case SimpleLog.LOG_LEVEL_FATAL:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case R.styleable.Toolbar_titleMargins:
            case R.styleable.Toolbar_titleMarginEnd:
            case IMEI_LEN:
                return MqttConnectOptions.MQTT_VERSION_3_1;
            case R.styleable.Toolbar_titleMarginStart:
                Object obj = MqttConnectOptions.MQTT_VERSION_3_1_1;
                break;
            default:
                i = 0;
                break;
        }
        return i;
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

    public static int writeStringToFile(String str, String str2) {
        if (str2 == null || str == null) {
            XLLog.e(TAG, new StringBuilder("writeStringToFile, parameter invalid, path:").append(str2).toString());
            return -1;
        }
        try {
            File file = new File(str2);
            if (file.exists()) {
                return 0;
            }
            file.createNewFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(str);
            outputStreamWriter.close();
            return 0;
        } catch (Exception e) {
            XLLog.e(TAG, new StringBuilder("writeStringToFile error:").append(e.getMessage()).toString());
            return -1;
        }
    }

    public static String readStringFromFile(String str) {
        if (str == null) {
            XLLog.e(TAG, "readStringFromFile, path invalid");
            return null;
        }
        File file = new File(str);
        if (file.isDirectory()) {
            return null;
        }
        String str2 = BuildConfig.VERSION_NAME;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    str2 = str2 + readLine;
                } else {
                    fileInputStream.close();
                    return str2;
                }
            }
        } catch (Exception e) {
            XLLog.e(TAG, new StringBuilder("readStringFromFile error:").append(e.getMessage()).toString());
            return null;
        }
    }

    public static void deleteFile(String str) {
        if (str == null) {
            XLLog.e(TAG, "deleteFile, path invalid");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getMd5(String str) {
        if (str == null) {
            XLLog.e(TAG, "getMd5, key invalid");
            return null;
        }
        try {
            char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes();
            instance.update(bytes, 0, bytes.length);
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder(32);
            for (byte b : digest) {
                stringBuilder.append(cArr[(b >> 4) & 15]).append(cArr[(b >> 0) & 15]);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String generateAppKey(String str, short s, byte b) {
        if (str == null) {
            XLLog.e(TAG, "generateAppKey, appName invalid");
            return null;
        }
        int length = str.length();
        byte[] bArr = new byte[(((length + 1) + 2) + 1)];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bArr[i] = bytes[i];
        }
        bArr[length] = (byte) 0;
        bArr[length + 1] = (byte) (s & 255);
        bArr[length + 2] = (byte) ((s >> 8) & 255);
        bArr[length + 3] = b;
        return new String(Base64.encode(bArr, 0)).trim();
    }

    public static Map<String, Object> parseJSONString(String str) {
        if (str == null) {
            XLLog.e(TAG, "parseJSONString, JSONString invalid");
            return null;
        }
        Map<String, Object> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                hashMap.put(str2, jSONObject.getString(str2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xunlei.downloadlib.XLUtil.NetWorkCarrier getNetWorkCarrier(android.content.Context r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadlib.XLUtil.getNetWorkCarrier(android.content.Context):com.xunlei.downloadlib.XLUtil$NetWorkCarrier");
        /*
        if (r2 == 0) goto L_0x0042;
    L_0x0002:
        r0 = "phone";
        r0 = r2.getSystemService(r0);
        r0 = (android.telephony.TelephonyManager) r0;
        if (r0 == 0) goto L_0x0042;
    L_0x000d:
        r0 = r0.getSubscriberId();	 Catch:{ Exception -> 0x003e }
        r1 = "46000";
        r1 = r0.startsWith(r1);	 Catch:{ Exception -> 0x003e }
        if (r1 != 0) goto L_0x0023;
    L_0x001a:
        r1 = "46002";
        r1 = r0.startsWith(r1);	 Catch:{ Exception -> 0x003e }
        if (r1 == 0) goto L_0x0026;
    L_0x0023:
        r0 = com.xunlei.downloadlib.XLUtil.NetWorkCarrier.CMCC;	 Catch:{ Exception -> 0x003e }
    L_0x0025:
        return r0;
    L_0x0026:
        r1 = "46001";
        r1 = r0.startsWith(r1);	 Catch:{ Exception -> 0x003e }
        if (r1 == 0) goto L_0x0032;
    L_0x002f:
        r0 = com.xunlei.downloadlib.XLUtil.NetWorkCarrier.CU;	 Catch:{ Exception -> 0x003e }
        goto L_0x0025;
    L_0x0032:
        r1 = "46003";
        r0 = r0.startsWith(r1);	 Catch:{ Exception -> 0x003e }
        if (r0 == 0) goto L_0x0042;
    L_0x003b:
        r0 = com.xunlei.downloadlib.XLUtil.NetWorkCarrier.CT;	 Catch:{ Exception -> 0x003e }
        goto L_0x0025;
    L_0x003e:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0042:
        r0 = com.xunlei.downloadlib.XLUtil.NetWorkCarrier.UNKNOWN;
        goto L_0x0025;
        */
    }
}
