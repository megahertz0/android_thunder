package com.xunlei.downloadprovider.model.protocol.report;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.SystemUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.b.a;
import com.xunlei.b.b;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.model.protocol.report.d.AnonymousClass_1;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class StatReporter {
    private static final String TAG;
    private static d sNewReport;

    static {
        TAG = StatReporter.class.getSimpleName();
    }

    public static void init(Application application) {
        sNewReport = new d(application);
    }

    public static void sendReportParams(a aVar) {
        if (aVar != null && sNewReport != null) {
            sNewReport.a(aVar);
        }
    }

    public static void reportMemberLogin(int i, int i2, int i3, int i4) {
        d dVar = sNewReport;
        a aVar = new a("8006");
        aVar.a("loginResult", i);
        aVar.a("userType", i2);
        aVar.a("isAnnualFee", i3);
        aVar.a("isFromThunder", i4);
        dVar.a(aVar);
    }

    public static void reportChannelDownOrPlay(String str) {
        d dVar = sNewReport;
        a aVar = new a("3216");
        aVar.a(JsInterface.FUNPLAY_AD_TRPE, str);
        dVar.a(aVar);
    }

    public static void reportExitApplication() {
        MobclickAgent.onKillProcess(sNewReport.a.b);
    }

    public static void reportActivityResume(Activity activity) {
        d dVar = sNewReport;
        new StringBuilder("onActivityResume:").append(activity.getClass().getName());
        dVar.a.a = new WeakReference(activity);
        MobclickAgent.onResume(activity);
        b.a((Context) activity);
        d.b(activity);
    }

    public static void reportActivityPause(Activity activity) {
        d dVar = sNewReport;
        new StringBuilder("onActivityPause:").append(activity.getClass().getName());
        b bVar = dVar.a;
        if (bVar.a != null) {
            bVar.a.clear();
        }
        MobclickAgent.onPause(activity);
        b.b(activity);
        d.c(activity);
    }

    public static void reportOneAction(String str) {
        sNewReport.a(new a(String.valueOf(str)));
    }

    public static void reportClick(int i, String str, String str2) {
        d dVar = sNewReport;
        new StringBuilder("func reportClick : actionId = ").append(i).append(" , clickedView = ").append(str).append(" , message = ").append(str2);
        a aVar = new a(String.valueOf(i));
        if (str != null) {
            aVar.a("clicked", str);
        }
        if (str2 != null) {
            aVar.a("attach", str2);
        }
        dVar.a(aVar);
    }

    public static void reportSwitcherClick(int i, String str, boolean z, String str2) {
        d dVar = sNewReport;
        a aVar = new a(String.valueOf(i));
        aVar.a("switcher_clicked", str);
        aVar.a("switcher_state", z ? 1 : 0);
        if (str2 != null) {
            aVar.a("attach", str2);
        }
        dVar.a(aVar);
    }

    public static void reportStartupTime(long j, String str) {
        d dVar = sNewReport;
        a aVar = new a("1001");
        aVar.a("time", j);
        aVar.a(anet.channel.strategy.dispatch.a.NET_TYPE, str);
        dVar.a(aVar);
    }

    public static void reportInstall(String str, String str2) {
        d dVar = sNewReport;
        a aVar = new a("1002");
        aVar.a("metrics", str);
        aVar.a("dispSize", str2);
        dVar.a(aVar);
    }

    public static void reportPushResRecv(String str) {
        d dVar = sNewReport;
        a aVar = new a("1038");
        aVar.a(Constants.KEY_TARGET, str);
        dVar.b(aVar);
    }

    public static void reportPushResClick(String str, int i) {
        d dVar = sNewReport;
        a aVar = new a("1042");
        aVar.a(Constants.KEY_TARGET, str);
        aVar.a("pushType", i);
        dVar.b(aVar);
    }

    public static void reportPhotographRecognition(int i, long j) {
        d dVar = sNewReport;
        a aVar = new a("1006");
        aVar.a("result", i == 0 ? "sucess" : MsgConstant.KEY_FAIL);
        aVar.a("scanTime", j);
        dVar.a(aVar);
    }

    public static void reportVodBehavior(VodSourceType vodSourceType, String str, String str2, String str3) {
        d dVar = sNewReport;
        a aVar = new a("4004");
        aVar.a("vodUrl", str);
        aVar.a("vodTitle", str2);
        switch (AnonymousClass_1.a[vodSourceType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                aVar.a("vodBehaviorSource", "local");
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                aVar.a("vodBehaviorSource", "local_system");
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                aVar.a("vodBehaviorSource", "taskDetail");
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                aVar.a("vodBehaviorSource", "resourceDetail");
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                aVar.a("vodBehaviorSource", "lixian");
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                aVar.a("vodBehaviorSource", "cloudlist");
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                aVar.a("vodBehaviorSource", "vod_history");
                break;
        }
        if (TextUtils.isEmpty(str3)) {
            aVar.a("byCopyrightPage", Boolean.FALSE.toString());
        } else {
            aVar.a("byCopyrightPage", Boolean.TRUE.toString());
        }
        aVar.a("isVIP", Boolean.valueOf(LoginHelper.a().f()));
        aVar.a("vodUrl");
        aVar.a("vodTitle");
        dVar.b(aVar);
    }

    public static void reportVodFormat(String str, VodReportPlayState vodReportPlayState, String str2, String str3, VodSourceType vodSourceType) {
        d dVar = sNewReport;
        a aVar = new a("4003");
        switch (AnonymousClass_1.b[vodReportPlayState.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                aVar.a("vodPlayState", MsgConstant.KEY_FAIL);
                aVar.a("vodfailreason", str3);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                aVar.a("vodPlayState", "sucess");
                break;
        }
        aVar.a("vodFormatType", str);
        aVar.a("vodResourceFrom", str2);
        switch (AnonymousClass_1.a[vodSourceType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                aVar.a("vodBehaviorSource", "local");
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                aVar.a("vodBehaviorSource", "local_system");
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                aVar.a("vodBehaviorSource", "taskDetail");
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                aVar.a("vodBehaviorSource", "resourceDetail");
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                aVar.a("vodBehaviorSource", "lixian");
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                aVar.a("vodBehaviorSource", "cloudlist");
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                aVar.a("vodBehaviorSource", "vod_history");
                break;
        }
        aVar.a("isVIP", Boolean.valueOf(LoginHelper.a().f()));
        dVar.a(aVar);
    }

    public static void reportPayExit(String str) {
        d dVar = sNewReport;
        a aVar = new a("8013");
        aVar.a("exit_event", str);
        dVar.a(aVar);
    }

    public static void reportForgetPwdClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("8018");
        aVar.a("click_forget_pwd_event", str);
        dVar.a(aVar);
    }

    public static void reportPayQuestion(String str) {
        d dVar = sNewReport;
        a aVar = new a("8014");
        aVar.a("question_event", str);
        dVar.a(aVar);
    }

    public static void reportQRXunleiDecode(String str, long j, String str2) {
        d dVar = sNewReport;
        a aVar = new a("1008");
        aVar.a("decodeXunleiResult", str);
        aVar.a("costTime", j);
        aVar.a("qrcodeType", str2);
        aVar.a("costTime");
        b bVar = dVar.a;
        Context context = bVar.b;
        if (!(bVar.a == null || bVar.a.get() == null)) {
            context = (Context) bVar.a.get();
        }
        String valueOf = String.valueOf(aVar.a);
        b.a(aVar.b);
        if (aVar.b == null || aVar.b.size() == 0) {
            MobclickAgent.onEventDuration(context, valueOf, j);
        } else {
            MobclickAgent.onEventDuration(context, valueOf, aVar.b, j);
        }
    }

    public static void reportQRClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("1009");
        aVar.a("clickCode", str);
        dVar.a(aVar);
    }

    public static void reportNotiClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("1011");
        aVar.a("clickCode", str);
        dVar.a(aVar);
    }

    public static void reportOpenWithHandleFile(int i, String str, String str2) {
        d dVar = sNewReport;
        a aVar = new a("1012");
        aVar.a("openRet", i);
        aVar.a("fileType", str);
        if (TextUtils.isEmpty(str2)) {
            aVar.a("from", "normal");
        } else {
            aVar.a("from", str2);
        }
        dVar.a(aVar);
    }

    public static void reportPayWayPay() {
        a aVar = new a("8017");
        aVar.a("pay_event", "pay");
        sNewReport.a(aVar);
    }

    public static void reportActivationPayClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("8010");
        aVar.a("click_event", str);
        dVar.a(aVar);
    }

    public static void reportActivationPaySuccess(String str, String str2, String str3) {
        d dVar = sNewReport;
        a aVar = new a("8011");
        aVar.a("pay_result", str2);
        aVar.a("user_type", str);
        aVar.a("activation_source", str3);
        dVar.a(aVar);
    }

    public static void reportBrowserPageShow(String str, StartFromType startFromType) {
        d dVar = sNewReport;
        if (!TextUtils.isEmpty(str) && startFromType != null) {
            StringBuilder append = new StringBuilder("func reportBrowserPageShow : url = ").append(str).append(" , fromType = ");
            BrowserUtil.a();
            append.append(BrowserUtil.a(startFromType));
            a aVar = new a("3213");
            aVar.a(SocialConstants.PARAM_URL, str);
            BrowserUtil.a();
            aVar.a("from", BrowserUtil.a(startFromType));
            BrowserUtil.a();
            BrowserUtil.a(startFromType);
            dVar.a(aVar);
        }
    }

    public static void reportUpdateXunlei(String str, String str2) {
        d dVar = sNewReport;
        a aVar = new a("1032");
        aVar.a("fromVersion", str);
        aVar.a("toVersion", str2);
        dVar.a(aVar);
    }

    public static void reportMainTabClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("1040");
        aVar.a("whichTab", str);
        dVar.a(aVar);
    }

    public static void reportDownloadEntryClick(String str) {
        a aVar = new a("1037");
        aVar.a("module", str);
        sNewReport.a(aVar);
    }

    public static void reportFriendGroupListDownload(long j) {
        a aVar = new a("12501");
        aVar.a(SocializeConstants.TENCENT_UID, j);
        sNewReport.a(aVar);
    }

    public static void reportFriendGroupListCloudPlay(boolean z, long j) {
        a aVar = new a("12502");
        aVar.a("logined", Boolean.valueOf(z));
        aVar.a(SocializeConstants.TENCENT_UID, j);
        sNewReport.a(aVar);
    }

    public static void reportPromotionGuidePageClickDownload() {
        a aVar = new a("12004");
        aVar.a("partner_id", com.xunlei.downloadprovider.a.b.g());
        sNewReport.a(aVar);
    }

    public static void reportEnterRemoteDownload(long j) {
        a aVar = new a("12601");
        aVar.a(SocializeConstants.TENCENT_UID, j);
        sNewReport.a(aVar);
    }

    public static void reportQrcodeClick(long j) {
        a aVar = new a("12606");
        aVar.a(SocializeConstants.TENCENT_UID, j);
        sNewReport.a(aVar);
    }

    public static void reportBindByQrCodeClick(long j) {
        a aVar = new a("12611");
        aVar.a(SocializeConstants.TENCENT_UID, j);
        sNewReport.a(aVar);
    }

    public static void reportUserCountsUserOption(String str, int i) {
        a aVar = new a("12702");
        aVar.a(SocializeConstants.TENCENT_UID, str);
        aVar.a("user_option", i);
        sNewReport.a(aVar);
    }

    public static void reportByJSON(String str) {
        a convertToReportParams = convertToReportParams(str);
        if (convertToReportParams != null) {
            sNewReport.a(convertToReportParams);
        }
    }

    private static a convertToReportParams(String str) {
        int i = 1;
        int i2 = 0;
        int i3 = str != null ? 1 : 0;
        if (str.equals(com.umeng.a.d)) {
            i = 0;
        }
        if ((i3 & i) == 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a(jSONObject.getString("actionId"));
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(SocializeConstants.JSON_DATA);
                while (i2 < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    aVar.a(jSONObject2.getString("key"), jSONObject2.getString(RequestHeaders.COLUMN_VALUE));
                    i2++;
                }
                return aVar;
            } catch (JSONException e) {
                JSONException e2 = e;
                e2.printStackTrace();
                return aVar;
            }
        } catch (JSONException e3) {
            e2 = e3;
            aVar = null;
            e2.printStackTrace();
            return aVar;
        }
    }

    public static void reportRelaxRefresh(int i, GuestureType guestureType) {
        a aVar = new a("12914");
        String str = guestureType == GuestureType.TOP ? "top" : "bottom";
        aVar.a("tab", com.xunlei.downloadprovider.model.protocol.b.d.a(i));
        aVar.a("refresh", str);
        sNewReport.a(aVar);
    }

    public static void reportRelaxComment(long j, int i) {
        a aVar = new a("12910");
        aVar.a("click_view", "relax_comment");
        aVar.a("funInfoId", j);
        aVar.a("funInfoType", i);
        sNewReport.a(aVar);
    }

    public static void reportRelaxGood(long j, int i) {
        a aVar = new a("12911");
        aVar.a("click_view", "relax_good");
        aVar.a("funInfoId", j);
        aVar.a("funInfoType", i);
        sNewReport.a(aVar);
    }

    public static void reportThirdPartyCallPlay(String str) {
        a aVar = new a("13101");
        aVar.a(LogBuilder.KEY_CHANNEL, str);
        sNewReport.a(aVar);
    }

    public static void reportMobilePhoneRegisterVertyCode(String str, int i) {
        a aVar = new a("18712");
        aVar.a("registerOrLogin", str);
        if (i != 200) {
            aVar.a("XLRegErrorCode", i);
            aVar.a("state", MsgConstant.KEY_FAIL);
        } else {
            aVar.a("state", "sucess");
        }
        sNewReport.a(aVar);
    }

    public static void reportDownloadFromUc() {
        sNewReport.a(new a("13201"));
    }

    public static void reportDlnaClick() {
        sNewReport.a(new a("13701"));
    }

    public static void reportClickMemberRecharge(String str) {
        a aVar = new a(String.valueOf("13801"));
        aVar.a("from", str);
        sNewReport.a(aVar);
    }

    public static void reportBrowserCollectPageDownload() {
        sNewReport.a(new a("16001"));
    }

    public static void reportBrowserCollectSniffDownload() {
        sNewReport.a(new a("16002"));
    }

    public static void reportOutsideCall(String str) {
        d dVar = sNewReport;
        a aVar = new a("1045");
        aVar.a("what", str);
        dVar.a(aVar);
    }

    public static void reportNotiAccelerateButton(String str) {
        a aVar = new a("17104");
        aVar.a("accelerateButton", str);
        sNewReport.a(aVar);
        new StringBuilder("StatReporter---reportNotiAccelerateButton(String goToWhere) ---").append(str).append("---").append(Thread.currentThread().getId());
    }

    public static void reportHotsiteItem(String str) {
        a aVar = new a("17901");
        aVar.a("item_or_star", str);
        sNewReport.a(aVar);
        new StringBuilder("StatReporter---reportHotsiteItem ---").append(str).append("---").append(Thread.currentThread().getId());
    }

    public static void reportVodListTabClick() {
        sNewReport.a(new a("17301"));
    }

    public static void reportLixianListTabClick() {
        sNewReport.a(new a("17302"));
    }

    public static void reportVodListVodPlay() {
        sNewReport.a(new a("17303"));
    }

    public static void reportVodListLocalPlay() {
        sNewReport.a(new a("17304"));
    }

    public static void reportVodListOnlinePlay() {
        sNewReport.a(new a("17305"));
    }

    public static void reportVodListVodDownload() {
        sNewReport.a(new a("17306"));
    }

    public static void reportOnlineListVodDownload() {
        sNewReport.a(new a("17307"));
    }

    public static void reportLixianListPlayOrOpenBT() {
        sNewReport.a(new a("17308"));
    }

    public static void reportLixianListVideoDownload() {
        sNewReport.a(new a("17309"));
    }

    public static void reportLixianListApkDownload() {
        sNewReport.a(new a("17310"));
    }

    public static void reportLixianListOtherDownload() {
        sNewReport.a(new a("17311"));
    }

    public static void reportOverallPlay(String str, String str2) {
        a aVar = new a("17402");
        aVar.a("from", str);
        aVar.a(JsInterface.FUNPLAY_AD_TRPE, str2);
        sNewReport.a(aVar);
    }

    public static void reportPlayRecordListChoice(String str) {
        a aVar = new a("17312");
        aVar.a("from", str);
        sNewReport.a(aVar);
    }

    public static void reportNoNetWorkTip() {
        sNewReport.a(new a("17602"));
    }

    public static void reportNoNetWorkTipChoice(String str) {
        a aVar = new a("17603");
        aVar.a("from", str);
        sNewReport.a(aVar);
    }

    public static void reportShowDialogForCreateTaskFromClipBoard() {
        sNewReport.a(new a("18601"));
    }

    public static void reportFavorClickSyn(int i, String str) {
        a aVar = new a(String.valueOf(i));
        aVar.a("clicked", str);
        if (sNewReport != null) {
            sNewReport.a(aVar);
        }
    }

    public static void reportDlnaResultNotNone() {
        sNewReport.a(new a("13702"));
    }

    public static void reportDlnaResultNone() {
        sNewReport.a(new a("13703"));
    }

    public static void reportDlnaConnectSuccess() {
        sNewReport.a(new a("13704"));
    }

    public static void reportDlnaConnectFailed() {
        sNewReport.a(new a("13705"));
    }

    public static void reportKuaiNiaoDialog(String str) {
        a aVar = new a("18102");
        aVar.a("click", str);
        if (sNewReport != null) {
            sNewReport.a(aVar);
        }
    }

    public static void reportKuaiNiaoNotification(String str) {
        a aVar = new a("18103");
        aVar.a("click", str);
        if (sNewReport != null) {
            sNewReport.a(aVar);
        }
    }

    public static void reportAutoPlayNextShow() {
        sNewReport.a(new a("12211"));
    }

    public static void reportAutoPlayNextClick(String str) {
        d dVar = sNewReport;
        a aVar = new a("12212");
        aVar.a("whatclick", str);
        dVar.a(aVar);
    }

    public static void reportVodOpen() {
        sNewReport.a(new a("12213"));
    }

    public static void reportNewAutoLogin(String str, int i, boolean z, int i2) {
        new StringBuilder("StatReporter---reportNewAutoLogin---id---success_or_fail---isvip---viptype---18701---").append(str).append("---").append(z).append("---").append(i2).append("---").append(Thread.currentThread().getId());
        a aVar = new a("18701");
        aVar.a("success_or_fail", str);
        aVar.a("errorcode", i);
        if (z) {
            aVar.a("usertype", "\u4f1a\u5458\u7528\u6237");
        } else {
            aVar.a("usertype", "\u666e\u901a\u7528\u6237");
        }
        if (i2 != 0) {
            switch (i2) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    aVar.a("viptype", "\u8ff7\u4f60\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    aVar.a("viptype", "\u666e\u901a\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    aVar.a("viptype", "\u767d\u91d1\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    aVar.a("viptype", "\u94bb\u77f3\u4f1a\u5458");
                    break;
            }
        }
        sNewReport.a(aVar);
        int i3 = z ? 1 : 0;
        if (str.equals(MsgConstant.KEY_SUCCESS)) {
            String valueOf = String.valueOf(i3);
            String valueOf2 = String.valueOf(i2);
            new StringBuilder("HubbleProxy---reportAuto_login_success---is_vip---vip_type---").append(valueOf).append("---").append(valueOf2).append("---").append(Thread.currentThread().getId());
            b.a("android_auto_login_success", "auto_login_success", new b.a().a("is_vip", valueOf).a("vip_type", valueOf2));
            return;
        }
        valueOf = String.valueOf(i);
        new StringBuilder("HubbleProxy---reportAuto_login_fail---").append(Thread.currentThread().getId());
        b.a("android_auto_login_fail", "auto_login_fail", new b.a().a("failtype", valueOf));
    }

    public static void reportUserLogout(String str, int i) {
        new StringBuilder("StatReporter---reportUserLogout---").append(str).append("---i---").append(i).append("---").append(Thread.currentThread().getId());
        a aVar = new a("18702");
        aVar.a("exit_type", str);
        if (i != 0) {
            aVar.a("errorcode", i);
        }
        sNewReport.a(aVar);
        if (str.equals("active_exit")) {
            new StringBuilder("HubbleProxy---reportUserLogoutActive---").append(Thread.currentThread().getId());
            b.a("android_logout", "logout", null);
            return;
        }
        String valueOf = String.valueOf(i);
        new StringBuilder("HubbleProxy---reportUserLogoutActive---").append(Thread.currentThread().getId());
        b.a("android_sys_logout", "sys_logout", new b.a().a("logout_reason", valueOf));
    }

    public static void reportEnterLoginpage() {
        new StringBuilder("StatReporter---reportEnterLoginpage---").append(Thread.currentThread().getId());
        sNewReport.a(new a("18703"));
    }

    public static void reportEnterLoginpageFrom(String str) {
        new StringBuilder("StatReporter---reportEnterLoginpageFrom---from---").append(str).append("---").append(Thread.currentThread().getId());
        a aVar = new a("18704");
        aVar.a("from", str);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportUserLogin_click---from---").append(str).append("---").append(Thread.currentThread().getId());
        b.a("android_user_login", "user_login_click", new b.a().a("from", str));
    }

    public static void reportMobilephoneLoginClick() {
        new StringBuilder("StatReporter---reportMobilephoneLoginClick---").append(Thread.currentThread().getId());
        sNewReport.a(new a("18706"));
    }

    public static void reportNewLoginFromLoginPage(String str, int i, boolean z, int i2) {
        a aVar = new a("18716");
        aVar.a("success_or_fail", str);
        aVar.a("errorcode", i);
        if (z) {
            aVar.a("usertype", "\u4f1a\u5458\u7528\u6237");
        } else {
            aVar.a("usertype", "\u666e\u901a\u7528\u6237");
        }
        if (i2 != 0) {
            switch (i2) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    aVar.a("viptype", "\u8ff7\u4f60\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    aVar.a("viptype", "\u666e\u901a\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    aVar.a("viptype", "\u767d\u91d1\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    aVar.a("viptype", "\u94bb\u77f3\u4f1a\u5458");
                    break;
            }
        }
        sNewReport.a(aVar);
    }

    public static void reportVodSizeChange(VideoSize videoSize) {
        a aVar = new a(String.valueOf("12214"));
        Object obj = null;
        switch (AnonymousClass_1.a[videoSize.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                obj = "full";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                obj = "100%";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                obj = "75%";
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                obj = "50%";
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            aVar.a("size", obj);
            sNewReport.a(aVar);
        }
    }

    public static void reportVodPlayLocalFile(String str, String str2, String str3) {
        a aVar = new a(String.valueOf("12215"));
        aVar.a("filesize", str);
        aVar.a("dpi", str2);
        aVar.a("format", str3);
        sNewReport.a(aVar);
    }

    public static void reportVodPlayTime(String str) {
        a aVar = new a(String.valueOf("12216"));
        aVar.a("time", str);
        sNewReport.a(aVar);
    }

    public static void reportNewLoginFromLoginPageHasVerifyCode(String str, int i, boolean z, int i2) {
        new StringBuilder("StatReporter---reportNewLoginFromLoginPageHasVerifyCode---").append(Thread.currentThread().getId());
        a aVar = new a("18719");
        aVar.a("success_or_fail", str);
        aVar.a("errorcode", i);
        if (z) {
            aVar.a("usertype", "\u4f1a\u5458\u7528\u6237");
        } else {
            aVar.a("usertype", "\u666e\u901a\u7528\u6237");
        }
        if (i2 != 0) {
            switch (i2) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    aVar.a("viptype", "\u8ff7\u4f60\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    aVar.a("viptype", "\u666e\u901a\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    aVar.a("viptype", "\u767d\u91d1\u4f1a\u5458");
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    aVar.a("viptype", "\u94bb\u77f3\u4f1a\u5458");
                    break;
            }
        }
        sNewReport.a(aVar);
    }

    public static void reportLoginOrRegTimeRangeLoginPage(String str) {
        a aVar = new a("18722");
        aVar.a("timeRange", str);
        sNewReport.a(aVar);
    }

    public static void reportBackLoginPage() {
        new StringBuilder("StatReporter---reportBackLoginPage---").append(Thread.currentThread().getId());
        sNewReport.a(new a("18729"));
        new StringBuilder("HubbleProxy---reportUserLogin_back---").append(Thread.currentThread().getId());
        b.a("android_user_login", "user_login_back", null);
    }

    public static void reportUseridCrash(String str) {
        new StringBuilder("StatReporter---reportUseridCrash---nickname---").append(str).append("---").append(Thread.currentThread().getId());
        a aVar = new a("18730");
        aVar.a("nickname", str);
        sNewReport.a(aVar);
    }

    public static void reportVip_ContinueShow(String str, Boolean bool, Boolean bool2, String str2) {
        int i = 1;
        new StringBuilder("StatReporter---reportVip_ContinueShow---##########################-----from---is_login---is_vip---renewdays---").append(str).append("---").append(bool).append("---").append(bool2).append("---").append(str2).append("---").append(Thread.currentThread().getId());
        a aVar = new a("renewTip_show");
        String str3 = (bool.booleanValue() ? 1 : 0);
        StringBuilder stringBuilder = new StringBuilder();
        if (!bool2.booleanValue()) {
            i = 0;
        }
        String toString = stringBuilder.append(i).toString();
        aVar.a("from", str);
        aVar.a(SystemUtils.IS_LOGIN, str3);
        aVar.a("is_vip", toString);
        aVar.a("renewdays", str2);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportVip_Continue---from---").append(str).append("---").append(str3).append("---").append(toString).append("---").append(str2).append("---").append(Thread.currentThread().getId());
        b.a("android_renewTip", "renewTip_show", new b.a().a("from", str).a(SystemUtils.IS_LOGIN, str3).a("is_vip", toString).a("renewdays", str2));
    }

    public static void reportVip_ContinueClick(String str) {
        a aVar = new a("renewTip_click");
        aVar.a("from", str);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportVip_Continue---from---").append(str).append("---").append(Thread.currentThread().getId());
        b.a("android_renewTip", "renewTip_click", new b.a().a("from", str));
    }

    public static void reportUserLogin_third(String str, String str2) {
        new StringBuilder("StatReporter---reportUserLogin_third---login_third---").append(str).append("---").append(Thread.currentThread().getId());
        a aVar = new a("login_third");
        aVar.a("login_account", str);
        aVar.a("from", str2);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportUserLogin_third---whichButton---").append(str).append("---").append(Thread.currentThread().getId());
        b.a("android_login_third", "login_third", new b.a().a("login_account", str).a("from", str2));
    }

    public static void reportUserLogin_third_success(String str, String str2) {
        new StringBuilder("StatReporter---reportUserLogin_third_success---login_third_success---").append(str).append("---").append(Thread.currentThread().getId());
        a aVar = new a("login_third_success");
        aVar.a("login_account", str);
        aVar.a("from", str2);
        sNewReport.a(aVar);
    }

    public static void reportUserLogin_third_fail(String str, String str2) {
        new StringBuilder("StatReporter---reportUserLogin_third_fail---login_third_fail---").append(str).append("---").append(Thread.currentThread().getId());
        a aVar = new a("login_third_fail");
        aVar.a("login_account", str);
        aVar.a("from", str2);
        sNewReport.a(aVar);
    }

    public static void reportMaildoMainClick(String str) {
        a aVar = new a("13310");
        aVar.a(c.c, str);
        sNewReport.a(aVar);
    }

    public static void reportForeground() {
        d dVar = sNewReport;
        a aVar = new a("forground");
        aVar.a("phone", new StringBuilder(Build.BRAND).append("|").append(Build.MODEL).toString());
        dVar.a(aVar);
        b.a("android_forground", "forground", new b.a().a("phone", new StringBuilder(Build.BRAND).append("|").append(Build.MODEL).toString()));
    }

    private static a getReportAdParams(String str, String str2, String str3, String str4, String str5, String str6) {
        a aVar = new a(str);
        if (!TextUtils.isEmpty(str2)) {
            aVar.a("jump_way", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            aVar.a("is_download", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            aVar.a("is_jump", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            aVar.a("adv_position", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            aVar.a("from", str6);
        }
        return aVar;
    }

    private static a getReportAdParams(String str, String str2, String str3) {
        a aVar = new a(str);
        if (!TextUtils.isEmpty(str2)) {
            aVar.a("ad_id", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            aVar.a("from", str3);
        }
        return aVar;
    }

    public static void reportAdEvent(String str, String str2, String str3, String str4, String str5, String str6) {
        sNewReport.a(getReportAdParams(str, str2, str3, str4, str5, str6));
    }

    public static void reportAdEvent(String str, String str2, String str3) {
        sNewReport.a(getReportAdParams(str, str2, str3));
    }

    public static void reportAdEventXunlei(String str, String str2, String str3, String str4, String str5, String str6) {
        a reportAdParams = getReportAdParams(str, str2, str3, str4, str5, str6);
        reportAdParams.a("ad_type", "xunlei");
        sNewReport.a(reportAdParams);
    }

    public static void reportPersonalNormalClick(String str, int i, int i2, String str2) {
        a aVar = new a(str);
        aVar.a("is_vip", i);
        if (!str.equals("per_cl_usericon")) {
            aVar.a(SystemUtils.IS_LOGIN, i2);
        }
        if (!TextUtils.isEmpty(str2)) {
            aVar.a("position", str2);
        }
        sNewReport.a(aVar);
    }

    public static void reportPersonalPayClick(int i, int i2, int i3) {
        a aVar = new a("per_cl_pay");
        aVar.a("is_vip", i);
        aVar.a("rest_days", i2);
        aVar.a("isrenew", i3);
        sNewReport.a(aVar);
    }

    public static void reportPer_accountpage_click(String str, String str2) {
        a aVar = new a("per_accountPage_click");
        aVar.a("is_vip", str);
        aVar.a("clickid", str2);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportPer_accountpage_click---String is_vip,String clickid---").append(str).append("---").append(str2).append("---").append(Thread.currentThread().getId());
        b.a("android_personal_click", "per_accountPage_click", new b.a().a("is_vip", str).a("clickid", str2));
    }

    public static void reportAppExitShow(String str, String str2, String str3) {
        a aVar = new a("appExit_show");
        aVar.a(SystemUtils.IS_LOGIN, str);
        aVar.a("is_signshow", str2);
        aVar.a("is_download", str3);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportAppExitShow---is_login---is_signshow---is_download---").append(str).append("---").append(str2).append("---").append(str3).append("---").append(Thread.currentThread().getId());
        b.a("android_appExit", "appExit_show", new b.a().a(SystemUtils.IS_LOGIN, str).a("is_signshow", str2).a("is_download", str3));
    }

    public static void reportAppExitClick(String str, String str2, String str3, String str4, String str5) {
        a aVar = new a("appExit_click");
        aVar.a("clickid", str);
        aVar.a(SystemUtils.IS_LOGIN, str2);
        aVar.a("is_signshow", str3);
        aVar.a("is_download", str4);
        aVar.a("is_tick", str5);
        sNewReport.a(aVar);
        new StringBuilder("HubbleProxy---reportAppExitShow---is_login---is_signshow---is_download---").append(str2).append("---").append(str3).append("---").append(str4).append("---").append(Thread.currentThread().getId());
        b.a("android_appExit", "appExit_click", new b.a().a("clickid", str).a(SystemUtils.IS_LOGIN, str2).a("is_signshow", str3).a("is_download", str4).a("is_tick", str5));
    }

    public static void reportOverallDownload(String str) {
        g a = g.a("android_download", "dl_create", "dl_create").a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("net_type", com.xunlei.xllib.a.b.a.a(BrothersApplication.a()));
        ThunderReporter.a(a);
        ThunderReporter.a(a, true);
    }
}
