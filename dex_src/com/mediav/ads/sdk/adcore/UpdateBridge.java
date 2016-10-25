package com.mediav.ads.sdk.adcore;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import anet.channel.security.ISecurity;
import com.mediav.ads.sdk.adcore.HttpRequester.Listener;
import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.log.MVLog;
import com.mediav.ads.sdk.log.Utils;
import com.tencent.stat.DeviceInfo;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.member.XLErrorCode;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UpdateBridge {
    private static final String SP_SDK_VER = "ad_sdk_ver";
    private static IBridge bridge;
    private static Context context;
    private static String md5;

    final class AnonymousClass_1 implements Listener {
        final /* synthetic */ String val$url;

        AnonymousClass_1(String str) {
            this.val$url = str;
        }

        public final void onGetDataSucceed(byte[] bArr) {
            UpdateBridge.parserJson(bArr);
        }

        public final void onGetDataFailed(String str) {
            if (!"204".equals(str)) {
                MVLog.e((int) XLErrorCode.OAUTH_REG_TOKEN_ERROR, new StringBuilder("Update Jar Error:").append(this.val$url).append(",code:").append(str).toString());
            }
        }
    }

    final class AnonymousClass_2 implements Listener {
        final /* synthetic */ int val$newVer;

        AnonymousClass_2(int i) {
            this.val$newVer = i;
        }

        public final void onGetDataSucceed(byte[] bArr) {
            MVLog.d("download jar");
            File file = new File(new File(context.getFilesDir().getAbsolutePath() + Config.UPDATE_DIR).getAbsolutePath() + "/n1119.jar");
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                if (md5 == null) {
                    return;
                }
                if (UpdateBridge.getMd5ByFile(file).equals(md5)) {
                    MVLog.d("new jar saved");
                    UpdateBridge.setVer(context, this.val$newVer);
                    return;
                }
                MVLog.e((int) XLErrorCode.OAUTH_CHK_TOKEN_ERROR, new StringBuilder("MD5 check error: ").append(md5).toString());
                file.delete();
            } catch (Exception e) {
                MVLog.e(new StringBuilder("\u5199\u5165\u65b0\u5305\u9519\u8bef:").append(e.getMessage()).toString());
            }
        }

        public final void onGetDataFailed(String str) {
            MVLog.e((int) XLErrorCode.OAUTH_CID_INVALID, new StringBuilder("Download new jar error:").append(str).toString());
        }
    }

    static {
        context = null;
        md5 = null;
    }

    @TargetApi(14)
    public static synchronized IBridge getBridge(Context context) {
        IBridge iBridge;
        synchronized (UpdateBridge.class) {
            if (bridge != null) {
                iBridge = bridge;
            } else {
                Context applicationContext = context.getApplicationContext();
                context = applicationContext;
                MVLog.init(applicationContext);
                File file;
                File file2;
                File file3;
                try {
                    MVLog.e(new StringBuilder("path: ").append(context.getFilesDir().getAbsolutePath()).append(Config.UPDATE_DIR).toString());
                    file = new File(context.getFilesDir().getAbsolutePath() + Config.UPDATE_DIR);
                    file2 = new File(context.getFilesDir().getAbsolutePath() + Config.OPT_DIR);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    file3 = new File(file.getAbsolutePath() + "/n1119.jar");
                    File file4 = new File(file.getAbsolutePath() + "/dynamic1119.jar");
                    if (file3.exists()) {
                        if (file4.exists()) {
                            file4.delete();
                        }
                        file3.renameTo(file4);
                        MVLog.d("new jar replaced old jar");
                    } else if (!file4.exists()) {
                        file4.createNewFile();
                        InputStream open = context.getAssets().open(Config.DEFAULT_JAR);
                        byte[] bArr = new byte[open.available()];
                        open.read(bArr);
                        open.close();
                        FileOutputStream fileOutputStream = new FileOutputStream(file4, false);
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                    }
                    bridge = new BridgeProxy((DynamicObject) new DexClassLoader(file4.getAbsolutePath(), file2.getAbsolutePath(), null, context.getClassLoader()).loadClass(Config.PACKAGE_URI).newInstance());
                    getNewJar();
                    iBridge = bridge;
                } catch (Throwable e) {
                    MVLog.e(XLErrorCode.OAUTH_REG_QURL_ERROR, "getBridge ClassNotFoundException", e);
                    file = new File(context.getFilesDir().getAbsolutePath() + Config.UPDATE_DIR);
                    file2 = new File(file.getAbsolutePath() + "/n1119.jar");
                    file3 = new File(file.getAbsolutePath() + "/dynamic1119.jar");
                    if (file2.exists()) {
                        file2.delete();
                    }
                    if (file3.exists()) {
                        file3.delete();
                    }
                    iBridge = null;
                    return iBridge;
                } catch (Throwable e2) {
                    MVLog.e(XLErrorCode.OAUTH_REG_QURL_ERROR, "getBridge Exception", e2);
                    iBridge = null;
                    return iBridge;
                }
            }
        }
        return iBridge;
    }

    private static void getNewJar() {
        String updateUrl = getUpdateUrl();
        HttpRequester.getAsynData(context, updateUrl, Boolean.valueOf(false), new AnonymousClass_1(updateUrl));
    }

    private static String getUpdateUrl() {
        String str = "http://show.m.mediav.com/update?sdkv=1119";
        String str2 = a.d;
        try {
            str2 = new StringBuilder("&nsdkv=").append(getVer(context)).append("&imei=").append(URLEncoder.encode(Utils.getIMEI(), "utf-8")).append("&model=").append(URLEncoder.encode(Utils.getProductModel(), "utf-8").replace(SocializeConstants.OP_DIVIDER_PLUS, "%20")).append("&channelid=").append(URLEncoder.encode(MessageService.MSG_DB_NOTIFY_REACHED, "utf-8")).append("&appv=").append(URLEncoder.encode(Utils.getAppVersion(), "utf-8")).append("&appvc=").append(URLEncoder.encode(Utils.getAppVersionCode(), "utf-8")).append("&apppkg=").append(URLEncoder.encode(Utils.getAppPackageName(), "utf-8")).append("&brand=").append(URLEncoder.encode(Utils.getBrand(), "utf-8").replace(SocializeConstants.OP_DIVIDER_PLUS, "%20")).toString();
        } catch (Exception e) {
            MVLog.d(new StringBuilder("URLEncoder Encode Error:").append(e.getMessage()).toString());
        }
        return str + str2;
    }

    private static void parserJson(byte[] bArr) {
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(new String(bArr)).nextValue();
            int i = jSONObject.getInt(DeviceInfo.TAG_VERSION);
            int parseInt = Integer.parseInt(getVer(context));
            md5 = jSONObject.getString("md5");
            String string = jSONObject.getString("sdk_url");
            if (i > parseInt) {
                MVLog.d("new version sdk found");
                HttpRequester.getAsynData(context, string, Boolean.valueOf(false), new AnonymousClass_2(i));
                return;
            }
            MVLog.d("sdk update to latest");
        } catch (Throwable e) {
            MVLog.e(XLErrorCode.OAUTH_REF_TOKEN_ERROR, new StringBuilder("parse update error").append(new String(bArr)).toString(), e);
        }
    }

    private static void setVer(Context context, String str) {
        Editor edit = context.getSharedPreferences("ver_info", 0).edit();
        edit.putString(SP_SDK_VER, str);
        edit.commit();
    }

    private static String getVer(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ver_info", 0);
        String string = sharedPreferences.getString(SP_SDK_VER, null);
        if (string != null) {
            return string;
        }
        sharedPreferences.edit().putString(SP_SDK_VER, Config.DEFAULT_SDK_VER).commit();
        return sharedPreferences.getString(SP_SDK_VER, null);
    }

    private static String getString(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int length = bArr.length;
        char[] cArr2 = new char[(length * 2)];
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr2[i2] = cArr[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr2[i3] = cArr[b & 15];
            i++;
        }
        return new String(cArr2);
    }

    public static String getMd5ByFile(File file) {
        String string;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteBuffer map = fileInputStream.getChannel().map(MapMode.READ_ONLY, 0, file.length());
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(map);
            string = getString(instance.digest());
            try {
                fileInputStream.close();
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                return string;
            }
        } catch (Exception e3) {
            e2 = e3;
            string = null;
            e2.printStackTrace();
            return string;
        }
        return string;
    }
}
