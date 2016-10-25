package com.xunlei.tdlive;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xllib.b.g;
import java.net.URLEncoder;
import org.android.spdy.SpdyProtocol;
import org.json.JSONException;
import org.json.JSONObject;

public class XLLiveHelpers {
    private static final String TAG = "XLLiveHelpers";
    private static boolean mPushTagChecked;
    private static p sUserInfoObserver;

    final class AnonymousClass_1 implements p {
        final /* synthetic */ Context val$ctx;

        AnonymousClass_1(Context context) {
            this.val$ctx = context;
        }

        public final void OnRefreshUserInfoCompleted(int i, boolean z) {
            if (i == 0 && XLLiveHelpers.showXLLiveTableImpl(this.val$ctx)) {
                new Handler().post(new Runnable() {
                    public void run() {
                        LoginHelper.a().b(sUserInfoObserver);
                        sUserInfoObserver = null;
                    }
                });
            }
        }
    }

    private static final class XLiveTabHelper {
        private JSONObject mConfig;

        public XLiveTabHelper() {
            this.mConfig = r.c().g.a;
        }

        public final boolean showLiveTab(Context context, boolean z, boolean z2) throws JSONException {
            XLog.d(TAG, new StringBuilder("showLiveTab() vip = ").append(z).append(", logined = ").append(z2).toString());
            if (isCloseAll()) {
                XLog.d(TAG, "close all");
                return false;
            } else if (XLLiveHelpers.getXLiveTabFlag(context)) {
                XLog.d(TAG, "new flag is set");
                return true;
            } else if (XLLiveSDK.getInstance(context).hasLiveFutrue()) {
                XLog.d(TAG, "open_flag is set");
                return true;
            } else if ("0x10810361".equals(b.g())) {
                XLog.d(TAG, "match channel");
                return true;
            } else if (testGrayVersion(b.w())) {
                XLog.d(TAG, "match gray version");
                return true;
            } else {
                int deviceId = getDeviceId();
                XLog.d(TAG, new StringBuilder("DEVICE_ID = ").append(deviceId).toString());
                if (z && testDevice(deviceId, "vip")) {
                    XLog.d(TAG, "match vip");
                    return true;
                } else if (z2 && testDevice(deviceId, "logined")) {
                    XLog.d(TAG, "match logined");
                    return true;
                } else if (!testDevice(deviceId, SocializeProtocolConstants.PROTOCOL_NORMAL_SHARE)) {
                    return false;
                } else {
                    XLog.d(TAG, "match normal");
                    return true;
                }
            }
        }

        public final boolean isCloseAll() {
            try {
                return this.mConfig.optBoolean("close_all", false);
            } catch (Exception e) {
                return false;
            }
        }

        private boolean testDevice(int i, String str) {
            try {
                JSONObject jSONObject = this.mConfig.getJSONObject(str);
                return i >= jSONObject.optInt("min_gray", -1) && i <= jSONObject.optInt("max_gray", -1);
            } catch (Exception e) {
                return false;
            }
        }

        private boolean testGrayVersion(String str) {
            return str != null && str.equals(this.mConfig.optString("gray_ver_name"));
        }

        private int getDeviceId() {
            try {
                String f = b.f();
                if (f == null || f.length() <= 0) {
                    f = b.e();
                }
                if (f == null || f.length() <= 0) {
                    return 245;
                }
                f = g.a(f.getBytes());
                return Integer.parseInt(f.substring(f.length() - 2, f.length()), SpdyProtocol.CUSTOM);
            } catch (Exception e) {
                return 245;
            }
        }
    }

    static {
        mPushTagChecked = false;
    }

    public static boolean showXLLiveTable(Context context) {
        boolean showXLLiveTableImpl = showXLLiveTableImpl(context);
        if (!showXLLiveTableImpl) {
            XLLiveSDK.getInstance(context).managePushTag(context);
        }
        return showXLLiveTableImpl;
    }

    public static boolean showXLLiveTableImpl(Context context) {
        try {
            XLUserUtil instance = XLUserUtil.getInstance();
            boolean userIsOnline = instance.userIsOnline();
            boolean z = userIsOnline ? instance.getCurrentUser().getIntValue(USERINFOKEY.IsVip) == 1 : false;
            if (new XLiveTabHelper().showLiveTab(context, z, userIsOnline)) {
                setXLiveTabFlag(context, true);
                return true;
            }
            if (!userIsOnline && sUserInfoObserver == null) {
                sUserInfoObserver = new AnonymousClass_1(context);
                LoginHelper.a().a(sUserInfoObserver);
            }
            return false;
        } catch (Throwable th) {
        }
    }

    public static boolean getXLiveTabFlag(Context context) {
        try {
            return context.getApplicationContext().getSharedPreferences("_tdlive_config_", 0).getBoolean("open_xllive_tab", false);
        } catch (Throwable th) {
            return false;
        }
    }

    public static void setXLiveTabFlag(Context context, boolean z) {
        try {
            Editor edit = context.getApplicationContext().getSharedPreferences("_tdlive_config_", 0).edit();
            edit.putBoolean("open_xllive_tab", z);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static String encode(String str) {
        return urlEncode(str, CharsetConvert.UTF_8);
    }

    public static String urlEncode(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (Exception e) {
            return null;
        }
    }
}
