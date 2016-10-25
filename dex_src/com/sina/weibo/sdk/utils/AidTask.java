package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.AidTask.AidInfo;
import com.sina.weibo.sdk.utils.AidTask.AidResultCallBack;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.stat.DeviceInfo;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Cipher;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class AidTask {
    private static final String AID_FILE_NAME = "weibo_sdk_aid";
    private static final int MAX_RETRY_NUM = 3;
    private static final String TAG = "AidTask";
    private static final int VERSION = 1;
    public static final int WHAT_LOAD_AID_ERR = 1002;
    public static final int WHAT_LOAD_AID_SUC = 1001;
    private static AidTask sInstance;
    private AidInfo mAidInfo;
    private String mAppKey;
    private Context mContext;
    private CallbackHandler mHandler;
    private volatile ReentrantLock mTaskLock;

    class AnonymousClass_3 implements Runnable {
        private final /* synthetic */ AidResultCallBack val$callback;

        AnonymousClass_3(AidResultCallBack aidResultCallBack) {
            this.val$callback = aidResultCallBack;
        }

        public void run() {
            AidTask.this.mTaskLock.lock();
            Object access$2 = AidTask.this.loadAidInfoFromCache();
            Object obj = null;
            if (access$2 == null) {
                try {
                    String access$3 = AidTask.this.loadAidFromNet();
                    access$2 = AidInfo.parseJson(access$3);
                    AidTask.this.cacheAidInfo(access$3);
                    AidTask.this.mAidInfo = access$2;
                } catch (WeiboException e) {
                    obj = e;
                    LogUtil.e(TAG, new StringBuilder("AidTaskInit WeiboException Msg : ").append(obj.getMessage()).toString());
                }
            }
            AidTask.this.mTaskLock.unlock();
            Message obtain = Message.obtain();
            if (access$2 != null) {
                obtain.what = 1001;
                obtain.obj = access$2;
            } else {
                obtain.what = 1002;
                obtain.obj = obj;
            }
            AidTask.this.mHandler.setCallback(this.val$callback);
            AidTask.this.mHandler.sendMessage(obtain);
        }
    }

    public static final class AidInfo {
        private String mAid;
        private String mSubCookie;

        public final String getAid() {
            return this.mAid;
        }

        public final String getSubCookie() {
            return this.mSubCookie;
        }

        public static com.sina.weibo.sdk.utils.AidTask.AidInfo parseJson(String str) throws WeiboException {
            com.sina.weibo.sdk.utils.AidTask.AidInfo aidInfo = new com.sina.weibo.sdk.utils.AidTask.AidInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error") || jSONObject.has("error_code")) {
                    LogUtil.d(TAG, "loadAidFromNet has error !!!");
                    throw new WeiboException("loadAidFromNet has error !!!");
                }
                aidInfo.mAid = jSONObject.optString(DeviceInfo.TAG_ANDROID_ID, a.d);
                aidInfo.mSubCookie = jSONObject.optString("sub", a.d);
                return aidInfo;
            } catch (JSONException e) {
                LogUtil.d(TAG, new StringBuilder("loadAidFromNet JSONException Msg : ").append(e.getMessage()).toString());
                throw new WeiboException("loadAidFromNet has error !!!");
            }
        }

        final com.sina.weibo.sdk.utils.AidTask.AidInfo cloneAidInfo() {
            com.sina.weibo.sdk.utils.AidTask.AidInfo aidInfo = new com.sina.weibo.sdk.utils.AidTask.AidInfo();
            aidInfo.mAid = this.mAid;
            aidInfo.mSubCookie = this.mSubCookie;
            return aidInfo;
        }
    }

    public static interface AidResultCallBack {
        void onAidGenFailed(Exception exception);

        void onAidGenSuccessed(AidInfo aidInfo);
    }

    private static class CallbackHandler extends Handler {
        private WeakReference<AidResultCallBack> callBackReference;

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public void setCallback(AidResultCallBack aidResultCallBack) {
            if (this.callBackReference == null) {
                this.callBackReference = new WeakReference(aidResultCallBack);
            } else if (((AidResultCallBack) this.callBackReference.get()) != aidResultCallBack) {
                this.callBackReference = new WeakReference(aidResultCallBack);
            }
        }

        public void handleMessage(Message message) {
            AidResultCallBack aidResultCallBack = (AidResultCallBack) this.callBackReference.get();
            switch (message.what) {
                case WHAT_LOAD_AID_SUC:
                    if (aidResultCallBack != null) {
                        aidResultCallBack.onAidGenSuccessed(((AidInfo) message.obj).cloneAidInfo());
                    }
                case WHAT_LOAD_AID_ERR:
                    if (aidResultCallBack != null) {
                        aidResultCallBack.onAidGenFailed((WeiboException) message.obj);
                    }
                default:
                    break;
            }
        }
    }

    private AidTask(Context context) {
        this.mTaskLock = new ReentrantLock(true);
        this.mContext = context.getApplicationContext();
        this.mHandler = new CallbackHandler(this.mContext.getMainLooper());
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= 0; i++) {
                    try {
                        AidTask.this.getAidInfoFile(0).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    public static synchronized AidTask getInstance(Context context) {
        AidTask aidTask;
        synchronized (AidTask.class) {
            if (sInstance == null) {
                sInstance = new AidTask(context);
            }
            aidTask = sInstance;
        }
        return aidTask;
    }

    public void aidTaskInit(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "aidTaskInit ");
            initAidInfo(str);
        }
    }

    private void initAidInfo(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mAppKey = str;
            new Thread(new Runnable() {
                public void run() {
                    if (AidTask.this.mTaskLock.tryLock()) {
                        AidInfo access$2 = AidTask.this.loadAidInfoFromCache();
                        if (access$2 == null) {
                            int i = VERSION;
                            do {
                                i++;
                                try {
                                    String access$3 = AidTask.this.loadAidFromNet();
                                    AidInfo parseJson = AidInfo.parseJson(access$3);
                                    AidTask.this.cacheAidInfo(access$3);
                                    AidTask.this.mAidInfo = parseJson;
                                    break;
                                } catch (WeiboException e) {
                                    LogUtil.e(TAG, new StringBuilder("AidTaskInit WeiboException Msg : ").append(e.getMessage()).toString());
                                    if (i >= 3) {
                                    }
                                }
                            } while (i >= 3);
                        } else {
                            AidTask.this.mAidInfo = access$2;
                        }
                        AidTask.this.mTaskLock.unlock();
                        return;
                    }
                    LogUtil.e(TAG, "tryLock : false, return");
                }
            }).start();
        }
    }

    public AidInfo getAidSync(String str) throws WeiboException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.e(TAG, "getAidSync ");
        if (this.mAidInfo == null) {
            aidTaskInit(str);
        }
        return this.mAidInfo;
    }

    public void getAidAsync(String str, AidResultCallBack aidResultCallBack) {
        if (!TextUtils.isEmpty(str)) {
            if (this.mAidInfo == null || aidResultCallBack == null) {
                generateAid(str, aidResultCallBack);
            } else {
                aidResultCallBack.onAidGenSuccessed(this.mAidInfo.cloneAidInfo());
            }
        }
    }

    private void generateAid(String str, AidResultCallBack aidResultCallBack) {
        if (!TextUtils.isEmpty(str)) {
            this.mAppKey = str;
            new Thread(new AnonymousClass_3(aidResultCallBack)).start();
        }
    }

    private synchronized AidInfo loadAidInfoFromCache() {
        AidInfo aidInfo = null;
        synchronized (this) {
            try {
                FileInputStream fileInputStream = new FileInputStream(getAidInfoFile(VERSION));
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    aidInfo = AidInfo.parseJson(new String(bArr));
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                } catch (Exception e2) {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return aidInfo;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th2;
                }
            } catch (Exception e3) {
                fileInputStream = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return aidInfo;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = null;
                th2 = th4;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                    }
                }
                throw th2;
            }
        }
        return aidInfo;
    }

    private File getAidInfoFile(int i) {
        return new File(this.mContext.getFilesDir(), new StringBuilder(AID_FILE_NAME).append(i).toString());
    }

    private String loadAidFromNet() throws WeiboException {
        String packageName = this.mContext.getPackageName();
        String sign = Utility.getSign(this.mContext, packageName);
        String mfp = getMfp(this.mContext);
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppKey);
        weiboParameters.put(Constants.SP_KEY_APPKEY, this.mAppKey);
        weiboParameters.put("mfp", mfp);
        weiboParameters.put(LogBuilder.KEY_PACKAGE_NAME, packageName);
        weiboParameters.put(LogBuilder.KEY_HASH, sign);
        try {
            packageName = NetUtils.internalHttpRequest(this.mContext, "https://api.weibo.com/oauth2/getaid.json", com.tencent.connect.common.Constants.HTTP_GET, weiboParameters);
            LogUtil.d(TAG, new StringBuilder("loadAidFromNet response : ").append(packageName).toString());
            return packageName;
        } catch (WeiboException e) {
            LogUtil.d(TAG, new StringBuilder("loadAidFromNet WeiboException Msg : ").append(e.getMessage()).toString());
            throw e;
        }
    }

    private synchronized void cacheAidInfo(String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(getAidInfoFile(VERSION));
            } catch (Exception e) {
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
            try {
                fileOutputStream.write(str.getBytes());
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                }
            } catch (Exception e5) {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream2 = fileOutputStream;
                th = th4;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
    }

    private static String getMfp(Context context) {
        String str;
        String genMfpString = genMfpString(context);
        String str2 = a.d;
        try {
            str = new String(genMfpString.getBytes(), GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            str = str2;
        }
        LogUtil.d(TAG, new StringBuilder("genMfpString() utf-8 string : ").append(str).toString());
        try {
            str = encryptRsa(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
            LogUtil.d(TAG, new StringBuilder("encryptRsa() string : ").append(str).toString());
            return str;
        } catch (Exception e2) {
            LogUtil.e(TAG, e2.getMessage());
            return a.d;
        }
    }

    private static String genMfpString(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            CharSequence os = getOS();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(MessageService.MSG_DB_NOTIFY_REACHED, os);
            }
            os = getImei(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(MessageService.MSG_DB_NOTIFY_CLICK, os);
            }
            os = getMeid(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(MessageService.MSG_DB_NOTIFY_DISMISS, os);
            }
            os = getImsi(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(MessageService.MSG_ACCS_READY_REPORT, os);
            }
            os = getMac(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_TEXT, os);
            }
            os = getIccid(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO, os);
            }
            os = getSerialNo();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(MsgConstant.MESSAGE_NOTIFY_ARRIVAL, os);
            }
            os = getAndroidId(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, os);
            }
            os = getCpu();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_JOININ_GROUP, os);
            }
            os = getModel();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_MAKE_FRIEND, os);
            }
            os = getSdSize();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_WPA_STATE, os);
            }
            os = getResolution(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_START_WAP, os);
            }
            os = getSsid(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_START_GROUP, os);
            }
            os = getDeviceName();
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_BIND_GROUP, os);
            }
            os = getConnectType(context);
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put(com.tencent.connect.common.Constants.VIA_ACT_TYPE_NINETEEN, os);
            }
            os = a.d;
            try {
                os = Utility.generateUAAid(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(os)) {
                jSONObject.put("20", os);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            return a.d;
        }
    }

    private static String encryptRsa(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(VERSION, getPublicKey(str2));
        byte[] bytes = str.getBytes(GameManager.DEFAULT_CHARSET);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (true) {
                try {
                    int splite = splite(bytes, i, 117);
                    if (splite == -1) {
                        break;
                    }
                    byte[] doFinal = instance.doFinal(bytes, i, splite);
                    byteArrayOutputStream.write(doFinal);
                    LogUtil.d(TAG, new StringBuilder("encryptRsa offset = ").append(i).append("     len = ").append(splite).append("     enBytes len = ").append(doFinal.length).toString());
                    i += splite;
                } catch (Throwable th) {
                    Throwable th2 = th;
                }
            }
            byteArrayOutputStream.flush();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            LogUtil.d(TAG, new StringBuilder("encryptRsa total enBytes len = ").append(toByteArray.length).toString());
            toByteArray = Base64.encodebyte(toByteArray);
            LogUtil.d(TAG, new StringBuilder("encryptRsa total base64byte len = ").append(toByteArray.length).toString());
            String toString = new StringBuilder("01").append(new String(toByteArray, GameManager.DEFAULT_CHARSET)).toString();
            LogUtil.d(TAG, new StringBuilder("encryptRsa total base64string : ").append(toString).toString());
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
            }
            return toString;
        } catch (Throwable th3) {
            th2 = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
            }
            throw th2;
        }
    }

    private static int splite(byte[] bArr, int i, int i2) {
        return i >= bArr.length ? -1 : Math.min(bArr.length - i, i2);
    }

    private static PublicKey getPublicKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes())));
    }

    private static String getOS() {
        try {
            return new StringBuilder("Android ").append(VERSION.RELEASE).toString();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getImei(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getMeid(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getImsi(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getMac(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager == null) {
                return a.d;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getMacAddress() : a.d;
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getIccid(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getSerialNo() {
        String str = a.d;
        try {
            Class forName = Class.forName("android.os.SystemProperties");
            return (String) forName.getMethod("get", new Class[]{String.class, String.class}).invoke(forName, new Object[]{"ro.serialno", UtilityImpl.NET_TYPE_UNKNOWN});
        } catch (Exception e) {
            return str;
        }
    }

    private static String getAndroidId(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getCpu() {
        try {
            return Build.CPU_ABI;
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getSdSize() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getResolution(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return new StringBuilder(String.valueOf(String.valueOf(displayMetrics.widthPixels))).append("*").append(String.valueOf(displayMetrics.heightPixels)).toString();
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getSsid(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
        } catch (Exception e) {
        }
        return a.d;
    }

    private static String getDeviceName() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return a.d;
        }
    }

    private static String getConnectType(Context context) {
        String str = IXAdSystemUtils.NT_NONE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case VERSION:
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                        case R.styleable.Toolbar_contentInsetLeft:
                        case XZBDevice.Success:
                            return "2G";
                        case MAX_RETRY_NUM:
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        case R.styleable.Toolbar_contentInsetEnd:
                        case XZBDevice.Wait:
                        case XZBDevice.Pause:
                        case XZBDevice.Stop:
                        case XZBDevice.Fail:
                        case XZBDevice.Predownload:
                        case XZBDevice.Delete:
                            return "3G";
                        case XZBDevice.Upload:
                            return "4G";
                        default:
                            return IXAdSystemUtils.NT_NONE;
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    return UtilityImpl.NET_TYPE_WIFI;
                }
            }
        } catch (Exception e) {
        }
        return str;
    }
}
