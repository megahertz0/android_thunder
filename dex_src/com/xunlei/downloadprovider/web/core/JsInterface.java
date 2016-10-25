package com.xunlei.downloadprovider.web.core;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.bugly.Bugly;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.frame.user.bo;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.util.j;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.tdlive.R;
import com.xunlei.xllib.b.g;
import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsInterface {
    public static final String FROM_KEY = "from_key";
    public static final String FUNPLAY_AD_TITLE = "title";
    public static final String FUNPLAY_AD_TRPE = "type";
    public static final String FUNPLAY_AD_URL_OR_WHICHACTIVITY = "url_or_whichactivity";
    private static final String INTENT_KEY_APP_PACKAGE_NAME_FROM_SHOULEI = "appPackageNameFromShoulei";
    private static final String INTENT_KEY_APP_TITLE_FROM_SHOULEI = "appTitleFromShoulei";
    private static final String INTENT_KEY_APP_VERSION_CODE_FROM_SHOULEI = "appVersionCodeFromShoulei";
    private static final String INTENT_KEY_IS_LAUNCH_BY_SHOULEI = "isLaunchByShoulei";
    public static final String KEY_ACTION = "action";
    public static final String KEY_APK = "InstalledPackages";
    public static final String KEY_APK_NAME = "packageName";
    public static final String KEY_APK_VERSION_CODE = "versionCode";
    public static final String KEY_APK_VERSION_NAME = "versionName";
    public static final String KEY_PAGE = "page";
    public static final String LOG_TAG_JS = "JS_LOG";
    private static final String MARKET_ACTION = "android.intent.action.VIEW";
    private static final String MARKET_DETAIL_ACTIVITY = "com.xunlei.appmarket.app.detail.AppDetailActivity";
    private static final String MARKET_MINE_TYPE = "application/browserApp";
    private static final String MARKET_PACKAGENAME = "com.xunlei.appmarket";
    public static final String MODULE_KEY = "module_key";
    public static final int MSG_ADD_REMOTE_DEVICE_BY_ACTIVE_CODE = 1379;
    public static final int MSG_ADD_REMOTE_DEVICE_BY_PC_THUNDER = 1380;
    public static final int MSG_ADD_REMOTE_DEVICE_BY_QRCODE_SCAN = 1378;
    public static final int MSG_BACK_TO_FIND_FROM_REMOTE_MAIN_PAGE = 1381;
    public static final int MSG_DIRECT_PAY_AND_CALLBACK = 1376;
    public static final int MSG_GET_PAYMENT_USER_PARAMS = 1374;
    public static final int MSG_GOTO_PAYMENT_PROTOCOL_ACTIVITY = 1373;
    public static final int MSG_JS_ADDTASK = 1000;
    public static final int MSG_JS_ADDTASKS_NEW = 1017;
    public static final int MSG_JS_ADDTASK_BY_URL = 1029;
    public static final int MSG_JS_BACK_PROCESS = 1012;
    public static final int MSG_JS_BACK_TO_UC = 1049;
    public static final int MSG_JS_CALLBACK_ON_PAGE_CLICK = 1011;
    public static final int MSG_JS_CANCEL_WEBSITE = 1025;
    public static final int MSG_JS_COLLECT_WEBSITE = 1024;
    public static final int MSG_JS_CREATE_LIXIAN_TASK = 1040;
    public static final int MSG_JS_CREATE_TASK = 1043;
    public static final int MSG_JS_DETAILPAGE_PAY_FOR_CHOUJIANG = 1369;
    public static final int MSG_JS_DISABLE_BOTTOM_TIP = 1020;
    public static final int MSG_JS_GET_DISPLAY_SNIFF_PAGE_URL = 1052;
    public static final int MSG_JS_GET_PLAY_RECORDS = 1214;
    public static final int MSG_JS_GET_PRICE_CONFIG = 1372;
    public static final int MSG_JS_GET_SNIFF_PAGE_CONTENT_CALLBACK = 1055;
    public static final int MSG_JS_GET_SNIFF_RESULT_LIST = 1056;
    public static final int MSG_JS_GET_USER_ID = 1006;
    public static final int MSG_JS_GET_USER_INFO_CHANGE = 1005;
    public static final int MSG_JS_GOTO_ACTIVATION_PAGE = 1037;
    public static final int MSG_JS_GOTO_LOGIN_PAGE = 1036;
    public static final int MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK = 1042;
    public static final int MSG_JS_GOTO_LOGOUT_PAGE_AND_CALLBACK = 1044;
    public static final int MSG_JS_GO_TO_ACTIVITY_MYRECORD = 1059;
    public static final int MSG_JS_GO_TO_ACTIVITY_VIP = 1060;
    public static final int MSG_JS_GO_TO_ACTIVITY_VIP_FOR_USER_CENTER = 1061;
    public static final int MSG_JS_GO_TO_DOWNLOADLISTACTIVITY = 1062;
    public static final int MSG_JS_GO_TO_DOWNLOAD_LIST = 1046;
    public static final int MSG_JS_GO_TO_MAIN_PAGE = 1050;
    public static final int MSG_JS_GO_TO_USER_INFO = 1048;
    public static final int MSG_JS_HIDE_BOTTOM_TIP = 1019;
    public static final int MSG_JS_HIDE_LOADING_VIEW = 1018;
    public static final int MSG_JS_INSTALL_APK = 1047;
    public static final int MSG_JS_IS_CURRENT_TASK_EXITS = 1045;
    public static final int MSG_JS_JUMP_TO_RESOURCE_CHANNEL = 1031;
    public static final int MSG_JS_KUAINIAO_INTERFACE_END = 1368;
    public static final int MSG_JS_KUAINIAO_INTERFACE_START = 1218;
    public static final int MSG_JS_LATEST_MESSAGE_ID = 1010;
    public static final int MSG_JS_LAYER_STATE = 1033;
    public static final int MSG_JS_LISTENER_CLICK_UP_AND_DOWN = 1053;
    public static final int MSG_JS_LISTENER_FINISH_LOAD_SNIFF_PAGE = 1054;
    public static final int MSG_JS_LOAD_COMPLETE = 1058;
    public static final int MSG_JS_LOCATION_INTERFACE_END = 1213;
    public static final int MSG_JS_LOCATION_INTERFACE_START = 1063;
    public static final int MSG_JS_ON_SEARCH_RESULT_CALLBACK = 1057;
    public static final int MSG_JS_OPENWINDOW = 1001;
    public static final int MSG_JS_OPENWINDOW_WITH_DOWNLOADLIST = 1002;
    public static final int MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX = 1003;
    public static final int MSG_JS_OPEN_BROWSER_PAGE = 1014;
    public static final int MSG_JS_OPEN_DETAIL_PAGE = 1015;
    public static final int MSG_JS_OPEN_DETAIL_PAGE_CALLBACK = 1016;
    public static final int MSG_JS_OPEN_SEARCH_RESULT_PAGE = 1027;
    public static final int MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST = 1004;
    public static final int MSG_JS_OPEN_WINDOW_WITH_TYPE = 1007;
    public static final int MSG_JS_PROMOTION_SUCCESS = 1034;
    public static final int MSG_JS_RECEIVER_ERROR = 1030;
    public static final int MSG_JS_REFRESH_USER_INFO = 1217;
    public static final int MSG_JS_REPORT_UMENG = 1039;
    public static final int MSG_JS_SET_HOT_KEY = 1023;
    public static final int MSG_JS_SET_WEBSITE_STATE = 1028;
    public static final int MSG_JS_SHOWTOAST = 1008;
    public static final int MSG_JS_SHOWTOAST_BY_TYPE = 1009;
    public static final int MSG_JS_SHOW_BOTTOM_TIP = 1021;
    public static final int MSG_JS_SHOW_LOADING_VIEW = 1032;
    public static final int MSG_JS_SHOW_PAYMENT_TYPE_WINDOW = 1371;
    public static final int MSG_JS_SHOW_TITLE_BAR = 1022;
    public static final int MSG_JS_SHOW_UMENG_SHARE = 1035;
    public static final int MSG_JS_SHOW_UMENG_SHARE_NEW = 1041;
    public static final int MSG_JS_TIMEBUM = 1370;
    public static final int MSG_JS_UPDATE_WEBSITE = 1026;
    public static final int MSG_JS_USER_OPTION = 1038;
    public static final int MSG_JS_VOD_FROM_THIRD_SERVER = 1216;
    public static final int MSG_JS_VOD_PLAY = 1013;
    public static final int MSG_JS_VOD_PLAY_FOR_COOPERATION = 1051;
    public static final int MSG_JS_VOD_YUNBO = 1215;
    public static final int MSG_LOG_OUT_CALLBACK = 1382;
    public static final int MSG_REPORT_H5_PAGE_SHOW = 1375;
    public static final int MSG_SEND_RELAX_GOODPAGE_TO_CLIENT = 1383;
    public static final int MSG_SIGN_IN_CALLBACK = 1377;
    public static final String NAME_KEY = "name_key";
    public static final String NameSpace = "share";
    public static final String PAGE_COMMENT = "comments";
    public static final String PAGE_DETAIL = "detail";
    public static final String PAGE_HOME = "homepage";
    public static final String SHARE_CONTENT_KEY = "share_content_key";
    public static final String SHARE_FROM_KEY = "share_from_key";
    public static final String SHARE_IMAGE_ID = "share_id";
    public static final String SHARE_IMGURL_KEY = "share_imgurl_key";
    public static final String SHARE_PAGEURL_KEY = "share_pageurl_key";
    public static final String SHARE_TITLE_KEY = "share_title_key";
    public static final String SHARE_TYPE_KEY = "share_type_key";
    public static final String SHARE_WXURL_KEY = "share_wxurl_key";
    public static final String TAG;
    public static final String URL_KEY = "url_key";
    public Handler mHandler;
    private String mImei;
    private String mPartnerId;
    private String mPeerId;
    private String mProductId;
    private String mVersion;

    static {
        TAG = JsInterface.class.getSimpleName();
    }

    public JsInterface(Handler handler, String str, String str2, String str3, String str4, String str5) {
        this.mHandler = handler;
        this.mPeerId = str;
        this.mVersion = str2;
        this.mImei = str3;
        this.mProductId = str4;
        this.mPartnerId = str5;
    }

    public static String preprocessUrl(String str) {
        try {
            CharSequence encode = URLEncoder.encode(SocializeConstants.OP_DIVIDER_PLUS, GameManager.DEFAULT_CHARSET);
            CharSequence encode2 = URLEncoder.encode("*", GameManager.DEFAULT_CHARSET);
            str = str.replace(SocializeConstants.OP_DIVIDER_PLUS, encode);
            return str.replace("*", encode2);
        } catch (Exception e) {
            Exception exception = e;
            String str2 = str;
            exception.printStackTrace();
            return str2;
        }
    }

    @JavascriptInterface
    public void jumpToResourceChannel(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_JUMP_TO_RESOURCE_CHANNEL).sendToTarget();
        }
    }

    @JavascriptInterface
    public void receivedError(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_RECEIVER_ERROR, str).sendToTarget();
        }
    }

    @JavascriptInterface
    public void addTasks(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_ADDTASKS_NEW, str).sendToTarget();
        }
    }

    @JavascriptInterface
    public void addTask(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1000;
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void addTask(String str, String str2) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Bundle bundle = new Bundle();
            bundle.putString(SelectCountryActivity.EXTRA_COUNTRY_NAME, str);
            bundle.putString(SocialConstants.PARAM_URL, str2);
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_ADDTASK_BY_URL);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void openWindow(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("windowType", i);
        bundle.putString("titlename", str);
        bundle.putString("titleurl", str2);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_WINDOW_WITH_TYPE);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openWindow(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("titlename", str);
        bundle.putString("titleurl", str2);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPENWINDOW);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openTranscodeDownloadList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        String preprocessUrl = preprocessUrl(str);
        String preprocessUrl2 = preprocessUrl(str2);
        String preprocessUrl3 = preprocessUrl(str3);
        String preprocessUrl4 = preprocessUrl(str4);
        String preprocessUrl5 = preprocessUrl(str5);
        String preprocessUrl6 = preprocessUrl(str6);
        String preprocessUrl7 = preprocessUrl(str7);
        String preprocessUrl8 = preprocessUrl(str8);
        String preprocessUrl9 = preprocessUrl(str9);
        Bundle bundle = new Bundle();
        try {
            bundle.putString("pageUrl", preprocessUrl);
            ArrayList decodeJsEncodedArray = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl2, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray2 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl3, GameManager.DEFAULT_CHARSET));
            bundle.putStringArrayList("fileNameList", decodeJsEncodedArray);
            bundle.putStringArrayList("fileFormatList", decodeJsEncodedArray2);
            if (preprocessUrl4 != null) {
                bundle.putStringArrayList("downloadUrlList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl4, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl5 != null) {
                bundle.putStringArrayList("cidList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl5, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl6 != null) {
                bundle.putStringArrayList("gcidList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl6, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl7 != null) {
                bundle.putStringArrayList("fileSizeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl7, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl8 != null) {
                bundle.putStringArrayList("downloadCountList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl8, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl9 != null) {
                bundle.putStringArrayList("updateTimeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl9, GameManager.DEFAULT_CHARSET)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openWindowWithDownloadList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        String preprocessUrl = preprocessUrl(str);
        String preprocessUrl2 = preprocessUrl(str3);
        String preprocessUrl3 = preprocessUrl(str4);
        String preprocessUrl4 = preprocessUrl(str5);
        String preprocessUrl5 = preprocessUrl(str6);
        String preprocessUrl6 = preprocessUrl(str7);
        String preprocessUrl7 = preprocessUrl(str8);
        String preprocessUrl8 = preprocessUrl(str9);
        String preprocessUrl9 = preprocessUrl(str10);
        Bundle bundle = new Bundle();
        try {
            bundle.putString(SocialConstants.PARAM_URL, preprocessUrl);
            bundle.putString("isCollection", str2);
            ArrayList decodeJsEncodedArray = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl4, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray2 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl2, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray3 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl3, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray4 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl5, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray5 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl6, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray6 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl7, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray7 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl8, GameManager.DEFAULT_CHARSET));
            bundle.putStringArrayList("fileNameList", decodeJsEncodedArray);
            bundle.putStringArrayList("fileCidList", decodeJsEncodedArray2);
            bundle.putStringArrayList("fileGcidList", decodeJsEncodedArray3);
            bundle.putStringArrayList("fileSizeList", decodeJsEncodedArray4);
            bundle.putStringArrayList("fileFormatList", decodeJsEncodedArray5);
            bundle.putStringArrayList("fileCaptionList", decodeJsEncodedArray6);
            bundle.putStringArrayList("fileHotList", decodeJsEncodedArray7);
            if (preprocessUrl9 != null) {
                bundle.putStringArrayList("updateTimeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl9, GameManager.DEFAULT_CHARSET)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPENWINDOW_WITH_DOWNLOADLIST);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openWindowWithDownloadListEx(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_URL, str);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void onUserInfoChange(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(FUNPLAY_AD_TRPE, i);
        bundle.putInt(ParamKey.COUNT, i2);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GET_USER_INFO_CHANGE);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    private ArrayList<String> decodeJsEncodedArray(String str) {
        ArrayList<String> arrayList = new ArrayList();
        if (str == null || str == a.d || !str.endsWith(h.b)) {
            return null;
        }
        int indexOf = str.indexOf("!@!");
        while (indexOf != -1) {
            arrayList.add(str.substring(0, indexOf));
            str = str.substring(indexOf + 3, str.length());
            indexOf = str.indexOf("!@!");
        }
        arrayList.add(str.substring(0, str.length() - 1));
        return arrayList;
    }

    @JavascriptInterface
    public String getClientVersion() {
        return this.mVersion;
    }

    @JavascriptInterface
    public String getClientType() {
        return "shoulei_android";
    }

    @JavascriptInterface
    public String getPeerId() {
        return this.mPeerId;
    }

    @JavascriptInterface
    public static String getUserName() {
        LoginHelper.a();
        return LoginHelper.c() ? LoginHelper.a().o() : XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.NickName);
    }

    @JavascriptInterface
    public String getUserAccount() {
        LoginHelper.a();
        return LoginHelper.c() ? LoginHelper.a().e() : a.d;
    }

    @JavascriptInterface
    public String getPortaitPath() {
        LoginHelper.a();
        if (!LoginHelper.c()) {
            return XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.ImgURL);
        }
        return String.format("http://img.user.kanimg.com/usrimg/%1$s/100x100", new Object[]{Long.valueOf(LoginHelper.a().j)});
    }

    @JavascriptInterface
    public String getImei() {
        return this.mImei;
    }

    @JavascriptInterface
    public String getUserId() {
        LoginHelper.a();
        return LoginHelper.c() ? String.valueOf(LoginHelper.a().j) : XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID);
    }

    @JavascriptInterface
    public int getBusinessType() {
        return R.styleable.AppCompatTheme_textAppearanceLargePopupMenu;
    }

    @JavascriptInterface
    public int isVip() {
        LoginHelper.a();
        if (LoginHelper.c()) {
            return LoginHelper.a().f() ? 1 : 0;
        } else {
            return XLUserUtil.getInstance().getCurrentUser().getIntValue(USERINFOKEY.IsVip);
        }
    }

    @JavascriptInterface
    public boolean isYear() {
        return LoginHelper.a().m();
    }

    @JavascriptInterface
    public boolean isMonth() {
        return LoginHelper.a().g == 3;
    }

    @JavascriptInterface
    public boolean isLogged() {
        LoginHelper.a();
        return LoginHelper.c();
    }

    @JavascriptInterface
    public boolean isMiniMember() {
        return LoginHelper.a().h();
    }

    @JavascriptInterface
    public boolean isNormalMember() {
        return LoginHelper.a().g();
    }

    @JavascriptInterface
    public boolean isPlatinumMember() {
        return LoginHelper.a().i();
    }

    @JavascriptInterface
    public boolean isDiamondMember() {
        return LoginHelper.a().j();
    }

    @JavascriptInterface
    public String getUserNickName() {
        return LoginHelper.a().i;
    }

    @JavascriptInterface
    public int getLevel() {
        return LoginHelper.a().e;
    }

    @JavascriptInterface
    public void showToast(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_SEND_MSG, str);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_SHOWTOAST);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void showToast(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_SEND_MSG, str);
        bundle.putInt(FUNPLAY_AD_TRPE, i);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_SHOWTOAST_BY_TYPE);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void latestMessageId(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("latestMessageId", i);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_LATEST_MESSAGE_ID);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    private void addWeiboData(Bundle bundle, int i, String str) {
        if (bundle != null) {
            bundle.putInt("isWeiboLike", i);
            bundle.putString("weiboSiteName", str);
        }
    }

    @JavascriptInterface
    public static Boolean weiboIsLike(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return Boolean.valueOf(bundle.getInt("isWeiboLike") != 0);
    }

    @JavascriptInterface
    public static String weiboSite(Bundle bundle) {
        return bundle == null ? null : bundle.getString("weiboSiteName");
    }

    @JavascriptInterface
    public void openTranscodeDownloadList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, String str10) {
        String preprocessUrl = preprocessUrl(str);
        String preprocessUrl2 = preprocessUrl(str2);
        String preprocessUrl3 = preprocessUrl(str3);
        String preprocessUrl4 = preprocessUrl(str4);
        String preprocessUrl5 = preprocessUrl(str5);
        String preprocessUrl6 = preprocessUrl(str6);
        String preprocessUrl7 = preprocessUrl(str7);
        String preprocessUrl8 = preprocessUrl(str8);
        String preprocessUrl9 = preprocessUrl(str9);
        Bundle bundle = new Bundle();
        try {
            addWeiboData(bundle, i, str10);
            bundle.putString("pageUrl", preprocessUrl);
            ArrayList decodeJsEncodedArray = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl2, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray2 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl3, GameManager.DEFAULT_CHARSET));
            bundle.putStringArrayList("fileNameList", decodeJsEncodedArray);
            bundle.putStringArrayList("fileFormatList", decodeJsEncodedArray2);
            if (preprocessUrl4 != null) {
                bundle.putStringArrayList("downloadUrlList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl4, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl5 != null) {
                bundle.putStringArrayList("cidList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl5, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl6 != null) {
                bundle.putStringArrayList("gcidList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl6, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl7 != null) {
                bundle.putStringArrayList("fileSizeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl7, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl8 != null) {
                bundle.putStringArrayList("downloadCountList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl8, GameManager.DEFAULT_CHARSET)));
            }
            if (preprocessUrl9 != null) {
                bundle.putStringArrayList("updateTimeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl9, GameManager.DEFAULT_CHARSET)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openWindowWithDownloadList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, String str11) {
        String preprocessUrl = preprocessUrl(str);
        String preprocessUrl2 = preprocessUrl(str3);
        String preprocessUrl3 = preprocessUrl(str4);
        String preprocessUrl4 = preprocessUrl(str5);
        String preprocessUrl5 = preprocessUrl(str6);
        String preprocessUrl6 = preprocessUrl(str7);
        String preprocessUrl7 = preprocessUrl(str8);
        String preprocessUrl8 = preprocessUrl(str9);
        String preprocessUrl9 = preprocessUrl(str10);
        Bundle bundle = new Bundle();
        try {
            addWeiboData(bundle, i, str11);
            bundle.putString(SocialConstants.PARAM_URL, preprocessUrl);
            bundle.putString("isCollection", str2);
            ArrayList decodeJsEncodedArray = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl4, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray2 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl2, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray3 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl3, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray4 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl5, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray5 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl6, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray6 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl7, GameManager.DEFAULT_CHARSET));
            ArrayList decodeJsEncodedArray7 = decodeJsEncodedArray(URLDecoder.decode(preprocessUrl8, GameManager.DEFAULT_CHARSET));
            bundle.putStringArrayList("fileNameList", decodeJsEncodedArray);
            bundle.putStringArrayList("fileCidList", decodeJsEncodedArray2);
            bundle.putStringArrayList("fileGcidList", decodeJsEncodedArray3);
            bundle.putStringArrayList("fileSizeList", decodeJsEncodedArray4);
            bundle.putStringArrayList("fileFormatList", decodeJsEncodedArray5);
            bundle.putStringArrayList("fileCaptionList", decodeJsEncodedArray6);
            bundle.putStringArrayList("fileHotList", decodeJsEncodedArray7);
            if (preprocessUrl9 != null) {
                bundle.putStringArrayList("updateTimeList", decodeJsEncodedArray(URLDecoder.decode(preprocessUrl9, GameManager.DEFAULT_CHARSET)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPENWINDOW_WITH_DOWNLOADLIST);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void openWindowWithDownloadListEx(String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_URL, str);
        addWeiboData(bundle, i, str2);
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void callbackOnPageClick(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object string = jSONObject.getString(str2);
                bundle.putSerializable(str2, string);
                new StringBuilder("callbackOnPageClick name:").append(str2).append(" value:").append(string);
            }
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_CALLBACK_ON_PAGE_CLICK);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getPhoneModel() {
        return b.q();
    }

    @JavascriptInterface
    public String getPhoneBrand() {
        return b.k();
    }

    @JavascriptInterface
    public String getVersionCode() {
        return BrothersApplication.a().h();
    }

    @JavascriptInterface
    public boolean isInstalledApk(String str) {
        return c.a(str);
    }

    @JavascriptInterface
    public String getInstalledApks() {
        String str = a.d;
        List installedPackages = BrothersApplication.a.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            JSONArray jSONArray = new JSONArray();
            int size = installedPackages.size();
            int i = 0;
            while (i < size) {
                try {
                    PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                    JSONObject jSONObject = new JSONObject();
                    String str2 = packageInfo.versionName;
                    jSONObject.put(KEY_APK_NAME, packageInfo.packageName);
                    jSONObject.put(KEY_APK_VERSION_CODE, packageInfo.versionCode);
                    jSONObject.put(KEY_APK_VERSION_NAME, str2);
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(KEY_APK, jSONArray);
            return jSONObject2.toString();
        }
        return str;
    }

    @JavascriptInterface
    public void onTurnBackCalled(int i) {
        if (i != 0) {
            Bundle bundle = new Bundle();
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_BACK_PROCESS);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void vodPlay(String str) {
        if (this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_VOD_PLAY);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void openBrowserPage(String str) {
        if (this.mHandler != null && !this.mHandler.hasMessages(MSG_JS_OPEN_BROWSER_PAGE)) {
            Bundle bundle = new Bundle();
            bundle.putString(URL_KEY, str);
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_BROWSER_PAGE);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    @Deprecated
    public void openDetailPage(String str) {
        new StringBuilder().append(getClass()).append("---openDetailPage---").append(str).append(Thread.currentThread().getId());
        if (this.mHandler != null && !this.mHandler.hasMessages(MSG_JS_OPEN_DETAIL_PAGE)) {
            Bundle bundle = new Bundle();
            bundle.putString(URL_KEY, str);
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_DETAIL_PAGE);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void openDetailPageWithJson(String str) {
        new StringBuilder().append(getClass()).append("---openDetailPageWithJson---").append(str).append(Thread.currentThread().getId());
        if (this.mHandler != null && !this.mHandler.hasMessages(MSG_JS_OPEN_DETAIL_PAGE)) {
            Bundle bundle = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject(str);
                bundle.putString(URL_KEY, jSONObject.optString(SocialConstants.PARAM_URL, a.d));
                bundle.putString(MODULE_KEY, jSONObject.optString("module", a.d));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_DETAIL_PAGE);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void openDetailPageAndCallback(String str) {
        if (this.mHandler != null && !this.mHandler.hasMessages(MSG_JS_OPEN_DETAIL_PAGE_CALLBACK)) {
            Message obtain = Message.obtain(this.mHandler);
            obtain.what = 1016;
            obtain.obj = str;
            obtain.sendToTarget();
        }
    }

    @JavascriptInterface
    public void openSearchResultPage(String str) {
        if (this.mHandler != null && !TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(SocialConstants.PARAM_URL);
                String string2 = jSONObject.getString("key");
                String string3 = jSONObject.getString("fr");
                Bundle bundle = new Bundle();
                bundle.putString(URL_KEY, string);
                bundle.putString(NAME_KEY, string2);
                bundle.putString(FROM_KEY, string3);
                Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_OPEN_SEARCH_RESULT_PAGE);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void hideLoadingView() {
        new StringBuilder("func hideLoadingView mHandler = ").append(this.mHandler);
        if (this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1018;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void showLoadingView() {
        new StringBuilder("func showLoadingView mHandler = ").append(this.mHandler);
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_SHOW_LOADING_VIEW).sendToTarget();
        }
    }

    @JavascriptInterface
    public void hideBottomTip() {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_HIDE_BOTTOM_TIP).sendToTarget();
        }
    }

    @JavascriptInterface
    public void showBottomTip() {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_SHOW_BOTTOM_TIP).sendToTarget();
        }
    }

    @JavascriptInterface
    public void disableBottomTip() {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_DISABLE_BOTTOM_TIP).sendToTarget();
        }
    }

    @JavascriptInterface
    public void switchTitleBar(boolean z) {
        if (z) {
            this.mHandler.obtainMessage(MSG_JS_SHOW_TITLE_BAR, 1, -1).sendToTarget();
        } else {
            this.mHandler.obtainMessage(MSG_JS_SHOW_TITLE_BAR, 0, -1).sendToTarget();
        }
    }

    @JavascriptInterface
    public void setHotKey(String str) {
        this.mHandler.obtainMessage(MSG_JS_SET_HOT_KEY, str).sendToTarget();
    }

    @JavascriptInterface
    public void collectWebsite(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_COLLECT_WEBSITE, str).sendToTarget();
        }
    }

    @JavascriptInterface
    public void cancelWebsite(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_CANCEL_WEBSITE, str).sendToTarget();
        }
    }

    @JavascriptInterface
    public void updateWebsites(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_UPDATE_WEBSITE, str).sendToTarget();
        }
    }

    @JavascriptInterface
    public void setWebSiteEditState(int i) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_SET_WEBSITE_STATE, i, 0).sendToTarget();
        }
    }

    @JavascriptInterface
    public void setLayerState(int i) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_LAYER_STATE, i, i).sendToTarget();
        }
    }

    @JavascriptInterface
    public static void logForJS(String str) {
        TextUtils.isEmpty(str);
    }

    @JavascriptInterface
    public void showUmengShareBtn(String str) {
        if (this.mHandler != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(SocialConstants.PARAM_URL);
                String string2 = jSONObject.getString("imgUrl");
                String string3 = jSONObject.getString(FUNPLAY_AD_TITLE);
                String string4 = jSONObject.getString(FUNPLAY_AD_TRPE);
                Bundle bundle = new Bundle();
                bundle.putString(SHARE_PAGEURL_KEY, string);
                bundle.putString(SHARE_IMGURL_KEY, string2);
                bundle.putString(SHARE_TITLE_KEY, string3);
                bundle.putString(SHARE_TYPE_KEY, string4);
                Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_SHOW_UMENG_SHARE);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void displayUmengShareBtn(String str) {
        if (this.mHandler != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(SocialConstants.PARAM_URL);
                String string2 = jSONObject.getString("imgUrl");
                String string3 = jSONObject.getString(FUNPLAY_AD_TITLE);
                String string4 = jSONObject.getString(FUNPLAY_AD_TRPE);
                String optString = jSONObject.optString(ParamKey.CONTENT);
                String string5 = jSONObject.getString("wxUrl");
                String optString2 = jSONObject.optString("from");
                Bundle bundle = new Bundle();
                bundle.putString(SHARE_PAGEURL_KEY, string);
                bundle.putString(SHARE_IMGURL_KEY, string2);
                bundle.putString(SHARE_TITLE_KEY, string3);
                bundle.putString(SHARE_TYPE_KEY, string4);
                bundle.putString(SHARE_CONTENT_KEY, optString);
                bundle.putString(SHARE_WXURL_KEY, string5);
                bundle.putString(SHARE_FROM_KEY, optString2);
                Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_SHOW_UMENG_SHARE_NEW);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public String addSign(String str) {
        return new StringBuilder(str).append("&sign=").append(g.a((str.substring(str.indexOf("?") + 1) + "&C63xmnzM+").getBytes())).toString();
    }

    @JavascriptInterface
    public String md5PeerIdImei() {
        return g.a(new StringBuilder(this.mPeerId).append(this.mImei).append("thunder").toString().getBytes());
    }

    @JavascriptInterface
    public String getProductId() {
        return this.mProductId;
    }

    @JavascriptInterface
    public String getPartnerId() {
        return this.mPartnerId;
    }

    @JavascriptInterface
    public void goToLoginPage() {
        this.mHandler.sendEmptyMessage(MSG_JS_GOTO_LOGIN_PAGE);
    }

    @JavascriptInterface
    public void goToLoginPageParameter(String str) {
        new StringBuilder().append(getClass()).append("---goToLoginPageParameter---json---").append(str).append(Thread.currentThread().getId());
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GOTO_LOGIN_PAGE);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goToActivationPage(String str) {
        this.mHandler.obtainMessage(MSG_JS_GOTO_ACTIVATION_PAGE, str).sendToTarget();
    }

    @JavascriptInterface
    public String getLocalBookedSites() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        List<com.xunlei.downloadprovider.model.b> b = com.xunlei.downloadprovider.member.login.a.a.a().d.b();
        if (b.size() > 0) {
            for (com.xunlei.downloadprovider.model.b bVar : b) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, bVar.b);
                    jSONObject2.put(SocialConstants.PARAM_URL, bVar.c);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "{\"sitelist\":[]}";
                }
            }
        }
        try {
            jSONObject.put("sitelist", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "{\"sitelist\":[]}";
        }
    }

    @JavascriptInterface
    public void delLocalBookedSites(String str) {
        Object obj = 1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("delall", 0) != 1) {
                obj = null;
            }
            if (obj != null) {
                com.xunlei.downloadprovider.member.login.a.a.a().d.c();
                return;
            }
            obj = jSONObject.optString("delsite", null);
            if (!TextUtils.isEmpty(obj)) {
                com.xunlei.downloadprovider.member.login.a.a.a().a(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void userCountsOption(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            new StringBuilder().append(getClass()).append("---userCountsOption---").append(Thread.currentThread().getId());
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1038;
            obtainMessage.obj = str;
            this.mHandler.dispatchMessage(obtainMessage);
        }
    }

    @JavascriptInterface
    public void reportUmeng(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1039;
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public String getSessionId() {
        LoginHelper.a();
        return LoginHelper.c() ? LoginHelper.a().k : a.d;
    }

    @JavascriptInterface
    public String getJumpKey() {
        LoginHelper.a();
        return LoginHelper.c() ? LoginHelper.a().n : a.d;
    }

    @JavascriptInterface
    public void createLixianTask(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1040;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void copyToClipboard(String str) {
        if (str != null && !str.equals(a.d)) {
            ((ClipboardManager) BrothersApplication.a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str));
        }
    }

    @JavascriptInterface
    public void goToLoginPageAndCallback(String str) {
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goToLogoutPageAndCallback(String str) {
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GOTO_LOGOUT_PAGE_AND_CALLBACK);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public int isCurrentTaskExits(String str) {
        return (ai.e(ThunderWebView.getCurrentUrl()) || str == null || str.equals(a.d)) ? -1 : (int) DownloadService.a().a(str);
    }

    @JavascriptInterface
    public void createTask(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_CREATE_TASK);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goToDownloadList() {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            this.mHandler.sendEmptyMessage(MSG_JS_GO_TO_DOWNLOAD_LIST);
        }
    }

    @JavascriptInterface
    public String checkTaskState(String str, String str2) {
        if (ai.e(ThunderWebView.getCurrentUrl())) {
            return a.d;
        }
        String str3 = a.d;
        if (!(str == null || str.equals(a.d))) {
            d.a();
            TaskInfo d = d.d((long) Integer.parseInt(str.trim()));
            if (d != null) {
                str3 = d.mLocalFileName;
                File file = new File(str3);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("{\"result\":\"");
        stringBuilder.append("-1\", \"filePath\":\"");
        stringBuilder.append(str3).append("\"}");
        return stringBuilder.toString();
    }

    @JavascriptInterface
    public void installApk(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_INSTALL_APK);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public int checkIsInstallSuccess(String str) {
        return ai.b(str);
    }

    @JavascriptInterface
    public void goToUserInfo(String str) {
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GO_TO_USER_INFO);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public int checkIsApkRunning(String str) {
        int c = ai.c(str);
        if (c != 0) {
            return c;
        }
        bo.a();
        return bo.e(str);
    }

    @JavascriptInterface
    public void openApk(String str) {
        new StringBuilder().append(getClass()).append("---openApk---packageName---").append(str).append("---").append(Thread.currentThread().getId());
        ai.d(str);
    }

    @JavascriptInterface
    public void backToUC(String str) {
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_BACK_TO_UC);
        if (str != null && !str.equals(a.d)) {
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goToMainPage(String str) {
        this.mHandler.obtainMessage(MSG_JS_GO_TO_MAIN_PAGE).sendToTarget();
    }

    @JavascriptInterface
    public void vodPlayForCooperation(String str) {
        if (str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_VOD_PLAY_FOR_COOPERATION);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void getDisplaySniffPageUrl(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GET_DISPLAY_SNIFF_PAGE_URL);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void clickUpAndDown(String str) {
        if (str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_LISTENER_CLICK_UP_AND_DOWN);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void onFinishLoadSniffPage(String str) {
        if (str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_LISTENER_FINISH_LOAD_SNIFF_PAGE);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void getSniffPageContentCallbackHandler(String str) {
        if (str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GET_SNIFF_PAGE_CONTENT_CALLBACK);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void getSniffResultList(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && str != null && !str.equals(a.d) && this.mHandler != null) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GET_SNIFF_RESULT_LIST);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void onSearchResultCallBack(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl()) && this.mHandler != null && !TextUtils.isEmpty(str)) {
            try {
                String string = new JSONObject(str).getString("key");
                Bundle bundle = new Bundle();
                bundle.putString(NAME_KEY, string);
                Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_ON_SEARCH_RESULT_CALLBACK);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void saveKeyValueString(String str) {
        if (!TextUtils.isEmpty(str)) {
            Context a = BrothersApplication.a();
            if (!TextUtils.isEmpty(str)) {
                SharedPreferences sharedPreferences = a.getSharedPreferences("shared_for_js", 0);
                Map a2 = j.a(str);
                if (!a2.isEmpty()) {
                    for (String str2 : a2.keySet()) {
                        sharedPreferences.edit().putString(str2, (String) a2.get(str2)).commit();
                    }
                }
            }
        }
    }

    @JavascriptInterface
    public String getValueFromKeyString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return BrothersApplication.a().getSharedPreferences("shared_for_js", 0).getString(str, a.d);
    }

    @JavascriptInterface
    public void onLoadCompleteCallBack(String str) {
        if (this.mHandler != null && !TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString(URL_KEY, str);
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_LOAD_COMPLETE);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goToActivityMyRecord() {
        new StringBuilder().append(getClass()).append("---goToActivityMyRecord()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_GO_TO_ACTIVITY_MYRECORD).sendToTarget();
    }

    @JavascriptInterface
    public void goToXL7DownloadListActivity() {
        new StringBuilder().append(getClass()).append("---goToActivitySettingsIndex()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_GO_TO_DOWNLOADLISTACTIVITY).sendToTarget();
    }

    @JavascriptInterface
    public void goToActivityPaymentWay() {
        new StringBuilder().append(getClass()).append("---goToActivityPaymentWay()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_GO_TO_ACTIVITY_VIP).sendToTarget();
    }

    @JavascriptInterface
    public void goToActivityPaymentWay(String str) {
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_GO_TO_ACTIVITY_VIP_FOR_USER_CENTER);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void inStallTalbumApk(String str) {
        if (this.mHandler != null) {
            this.mHandler.obtainMessage(MSG_JS_TIMEBUM).sendToTarget();
        }
    }

    @JavascriptInterface
    public String getMemberExpireDate() {
        LoginHelper.a();
        return LoginHelper.c() ? LoginHelper.a().n() : XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.ExpireDate);
    }

    @JavascriptInterface
    public int getMemberType() {
        return LoginHelper.a().h;
    }

    @JavascriptInterface
    public String getDownloadFinishedTasks(int i) {
        if (ai.e(ThunderWebView.getCurrentUrl())) {
            return a.d;
        }
        if (DownloadService.a() == null) {
            return null;
        }
        List arrayList = new ArrayList(DownloadService.a().d.f.c);
        List arrayList2 = new ArrayList();
        if (arrayList.size() < i) {
            i = arrayList.size();
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            TaskRunningInfo taskRunningInfo = (TaskInfo) arrayList.get(i2);
            if (XLFileTypeUtil.a(taskRunningInfo.mFileName).equals(EFileCategoryType.E_VIDEO_CATEGORY) && new File(com.xunlei.downloadprovider.download.util.a.b(taskRunningInfo)).exists()) {
                arrayList2.add(taskRunningInfo);
            }
            if (arrayList2.size() >= i) {
                break;
            }
        }
        return parserFinishedTaskToJson(arrayList2);
    }

    private String parserFinishedTaskToJson(List<TaskInfo> list) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            for (TaskInfo taskInfo : list) {
                Object replace;
                JSONObject jSONObject2 = new JSONObject();
                String str = a.d;
                jSONObject2.put(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, taskInfo.mTaskId);
                jSONObject2.put("fileName", taskInfo.mFileName);
                if (taskInfo.mFilePath.contains(taskInfo.mFileName)) {
                    replace = taskInfo.mFilePath.replace(taskInfo.mFileName, a.d);
                } else {
                    String str2 = str;
                }
                jSONObject2.put("filePath", replace);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("taskInfos", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void vodFromLocal(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            VodUtil.a();
            VodUtil.a(BrothersApplication.a, str);
        }
    }

    @JavascriptInterface
    public void getPlayRecords(int i) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1214;
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void vodYunbo(String str) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1215;
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void refreshUserInfo(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1217;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void vodFromThirdServer(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1216;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void jumpPayForChoujiang(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1369;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void showPaymentTypeWindow(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1371;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void getPriceConfig(String str) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1372;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public String getUserInfo() {
        LoginHelper a = LoginHelper.a();
        StringBuilder stringBuilder = new StringBuilder();
        long j = a.j;
        String str = a.n;
        String str2 = a.k;
        String str3 = a.i;
        String e = a.e();
        String str4 = a.f() ? "true" : Bugly.SDK_IS_DEV;
        stringBuilder.append(String.format("{\"userid\":\"%s\",\"jumpkey\":\"%s\",\"sessionid\":\"%s\",\"nickname\":\"%s\",\"username\":\"%s\",\"isVip\":\"%s\"}", new Object[]{Long.valueOf(j), str, str2, str3, e, str4}));
        return stringBuilder.toString();
    }

    @JavascriptInterface
    public String getNetworkType() {
        return com.xunlei.xllib.a.b.d(BrothersApplication.a());
    }

    @JavascriptInterface
    public boolean isLogging() {
        LoginHelper.a();
        return LoginHelper.p();
    }

    @JavascriptInterface
    public int isKuaiNiao() {
        return BrothersApplication.i().d;
    }

    @JavascriptInterface
    public String getHighFlowInfo() {
        LoginHelper a = LoginHelper.a();
        Map hashMap = new HashMap();
        hashMap.put("total", Long.valueOf(a.x));
        hashMap.put("used", Long.valueOf(a.x - a.y));
        return new JSONObject(hashMap).toString();
    }

    @JavascriptInterface
    public String getOfflineSpaceInfo() {
        long j;
        long j2 = 0;
        LoginHelper a = LoginHelper.a();
        Map hashMap = new HashMap();
        com.xunlei.downloadprovider.member.login.b bVar = a.D;
        if (bVar != null) {
            j = bVar.e;
            j2 = bVar.d;
        } else {
            j = 0;
        }
        hashMap.put("total", Long.valueOf(j));
        hashMap.put("used", Long.valueOf(j2));
        return new JSONObject(hashMap).toString();
    }

    @JavascriptInterface
    public void gotoPaymentProtocolActivity() {
        this.mHandler.sendEmptyMessage(MSG_GOTO_PAYMENT_PROTOCOL_ACTIVITY);
    }

    @JavascriptInterface
    public void getPaymentUserParams(String str) {
        Message obtain = Message.obtain(this.mHandler, MSG_GET_PAYMENT_USER_PARAMS);
        obtain.obj = str;
        obtain.sendToTarget();
    }

    @JavascriptInterface
    public void directPayAndCallback(String str) {
        Message obtain = Message.obtain(this.mHandler, MSG_DIRECT_PAY_AND_CALLBACK);
        obtain.obj = str;
        obtain.sendToTarget();
    }

    @JavascriptInterface
    public void reportH5PayShow(String str) {
        Message obtain = Message.obtain(this.mHandler, MSG_REPORT_H5_PAGE_SHOW);
        obtain.obj = str;
        obtain.sendToTarget();
    }

    @JavascriptInterface
    public void xlReportStatistics(String str) {
        ThunderReporter.a(str);
    }

    @JavascriptInterface
    public void onSignResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object string = jSONObject.getString(str2);
                bundle.putSerializable(str2, string);
                new StringBuilder("callbackOnPageClick name:").append(str2).append(" value:").append(string);
            }
            Message obtainMessage = this.mHandler.obtainMessage(MSG_SIGN_IN_CALLBACK);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getSignature() {
        PackageManager packageManager = BrothersApplication.a().getPackageManager();
        String str = a.d;
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(a.d, R.styleable.AppCompatTheme_imageButtonStyle).signatures;
            int length = signatureArr.length;
            String str2 = str;
            int i = 0;
            while (i < length) {
                try {
                    Signature signature = signatureArr[i];
                    i++;
                    str2 = str2 + signature.toCharsString();
                } catch (NameNotFoundException e) {
                    NameNotFoundException e2 = e;
                }
            }
        } catch (NameNotFoundException e3) {
            NameNotFoundException nameNotFoundException = e3;
            str2 = str;
            e2 = nameNotFoundException;
            e2.printStackTrace();
            return TextUtils.isEmpty(str2) ? str2 : "E256AEDC53FDACACC27C680BC4F2D6F2".toLowerCase();
        }
        if (TextUtils.isEmpty(str2)) {
        }
    }

    @JavascriptInterface
    public void addRemoteDeviceByQrcodeScan(String str) {
        this.mHandler.obtainMessage(MSG_ADD_REMOTE_DEVICE_BY_QRCODE_SCAN, str).sendToTarget();
    }

    @JavascriptInterface
    public void addRemoteDeviceByActiveCode() {
        this.mHandler.sendEmptyMessage(MSG_ADD_REMOTE_DEVICE_BY_ACTIVE_CODE);
    }

    @JavascriptInterface
    public void addRemoteDeviceByPCThunder() {
        this.mHandler.sendEmptyMessage(MSG_ADD_REMOTE_DEVICE_BY_PC_THUNDER);
    }

    @JavascriptInterface
    public void goBackFromRemoteDownload() {
        this.mHandler.obtainMessage(MSG_BACK_TO_FIND_FROM_REMOTE_MAIN_PAGE).sendToTarget();
    }

    @JavascriptInterface
    public void loginOutCallback(String str) {
        this.mHandler.obtainMessage(MSG_LOG_OUT_CALLBACK, str).sendToTarget();
    }

    @JavascriptInterface
    public void sendRelaxGoodPageToClient(String str, String str2) {
        if (!ai.e(ThunderWebView.getCurrentUrl())) {
            Message obtain = Message.obtain(this.mHandler, MSG_SEND_RELAX_GOODPAGE_TO_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString(DeviceInfo.TAG_MID, str);
            bundle.putString("num", str2);
            obtain.setData(bundle);
            obtain.sendToTarget();
        }
    }

    @JavascriptInterface
    public boolean isShouleiAppRunningBackGround() {
        ActivityManager activityManager = (ActivityManager) BrothersApplication.a().getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        Object<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (com.xunlei.xllib.b.d.a(runningAppProcesses)) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String str = runningAppProcessInfo.processName;
            if (!TextUtils.isEmpty(str) && str.equals(BrothersApplication.a().getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    new StringBuilder().append(str).append(" --> \u8fd0\u884c\u4e8e\u540e\u53f0");
                    return true;
                }
                new StringBuilder().append(str).append(" --> \u8fd0\u884c\u4e8e\u524d\u53f0");
                return false;
            }
        }
        return false;
    }
}
