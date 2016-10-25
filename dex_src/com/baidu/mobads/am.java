package com.baidu.mobads;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.j.m;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class am implements IXAdConstants {
    private static String[] a;

    public String getInfoKeyErrorCode() {
        return "INFO_KEY_ERROR_CODE";
    }

    public String getInfoKeyErrorMessage() {
        return "INFO_KEY_ERROR_MESSAGE";
    }

    public String getInfoKeyErrorModule() {
        return "INFO_KEY_ERROR_MODULE";
    }

    public int getAdCreativeTypeText() {
        return 1;
    }

    public int getAdCreativeTypeImage() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public String feedsTrackerParameterKeyProgress() {
        return NotificationCompatApi21.CATEGORY_PROGRESS;
    }

    public String feedsTrackerParameterKeyList() {
        return "trackerUrl";
    }

    public int getAdCreativeTypeVideo() {
        return XZBDevice.Wait;
    }

    public int getAdCreativeTypeRichmedia() {
        return R.styleable.Toolbar_titleMarginBottom;
    }

    public String getSupportedActionType4RequestingNone() {
        return "NA";
    }

    public String getSupportedActionType4RequestingLandingPage() {
        return "LP";
    }

    public String getSupportedActionType4RequestingDownload() {
        return "DL";
    }

    public String getSupportedActionType4RequestingAPO() {
        return "APO";
    }

    public String getSupportedActionType4RequestingMap() {
        return "MAP";
    }

    public String getSupportedActionType4RequestingSMS() {
        return "SMS";
    }

    public String getSupportedActionType4RequestingMail() {
        return "MAIL";
    }

    public String getSupportedActionType4RequestingMakeCall() {
        return "PHONE";
    }

    public String getSupportedActionType4RequestingVideo() {
        return "VIDEO";
    }

    public String getSupportedActionType4RequestingRichMedia() {
        return "RM";
    }

    public String getRemoteVersion() {
        IXAdContainerFactory c = m.a().c();
        return c != null ? c.getRemoteVersion() : "0.0.0";
    }

    public String getSN() {
        return m.a().n().getEncodedSN(m.a().d());
    }

    public String getAppSec() {
        return m.a().m().getAppSec(m.a().d());
    }

    public String getAppSid() {
        return m.a().m().getAppId(m.a().d());
    }

    public String getAppPackageNameOfPublisher() {
        return m.a().d().getPackageName();
    }

    public int getCanSendEmail() {
        return 0;
    }

    public int getCanSendSMS() {
        return 1;
    }

    public int getCanShowMap() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public int getCanShowDownload() {
        return XZBDevice.DOWNLOAD_LIST_FAILED;
    }

    public int getCanSendCalender() {
        return XZBDevice.DOWNLOAD_LIST_ALL;
    }

    public String getProductionTypeBanner() {
        return SlotType.SLOT_TYPE_BANNER.getValue();
    }

    public String getProductionTypeSplash() {
        return SlotType.SLOT_TYPE_SPLASH.getValue();
    }

    public String getProductionTypeFrontlink() {
        return SlotType.SLOT_TYPE_FRONTLINK.getValue();
    }

    public String getProductionTypeChuilei() {
        return SlotType.SLOT_TYPE_VERLINK.getValue();
    }

    public String getProductionTypeWall() {
        return "wall";
    }

    public String getProductionTypeRWall() {
        return "rwall";
    }

    public String getProductionTypeVideo() {
        return "video";
    }

    public String getProductionTypeIcon() {
        return "icon";
    }

    public String getProductionTypeInterstitial() {
        return SlotType.SLOT_TYPE_INTERSTITIAL.getValue();
    }

    public String getProductionTypeNRWall() {
        return "nrwall";
    }

    public String getProductionTypeFeeds() {
        return SlotType.SLOT_TYPE_FEEDS.getValue();
    }

    static {
        a = new String[]{"com.android.chrome", "com.UCMobile", "com.uc.browser", "com.uc.browser.hd", "com.tencent.mtt", "com.tencent.padbrowser", "com.baidu.browser.apps", "com.android.browser", "com.oupeng.mini.android", "com.oupeng.mobile", "com.oupeng.browser", "com.opera.mini.android", "com.opera.browser", "com.opera.browser.beta", "com.mediawoz.xbrowser", "com.mx.browser", "com.mx.browser.tablet", "org.mozilla.firefox", "com.tiantianmini.android.browser", "com.ijinshan.browser_fast", "sogou.mobile.explorer", "com.dolphin.browser.cn", "com.qihoo.browser", "com.baidu.searchbox"};
    }

    public String[] getSupportedBrowsers() {
        return a;
    }

    public void setSupportedBrowsers(String[] strArr) {
        a = strArr;
    }

    public int getActTypeLandingPage() {
        return 1;
    }

    public int getActTypeDownload() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public int getActTypeOpenMap() {
        return XZBDevice.DOWNLOAD_LIST_ALL;
    }

    public int getActTypeSendSMS() {
        return XZBDevice.Wait;
    }

    public int getActTypeSendMail() {
        return R.styleable.Toolbar_titleMarginBottom;
    }

    public int getActTypeMakeCall() {
        return R.styleable.AppCompatTheme_actionModeCutDrawable;
    }

    public int getActTypePlayVideo() {
        return R.styleable.AppCompatTheme_imageButtonStyle;
    }

    public int getActTypeRichMedia() {
        return AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    }

    public int getActTypeNothing2Do() {
        return AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
    }

    public int getActTypeOpenExternalApp() {
        return AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
    }

    public String errorIo() {
        return "errorIo";
    }

    public String errorTimeout() {
        return "errorTimeout";
    }

    public String errorNullAsset() {
        return "errorNullAsset";
    }

    public String errorUnknown() {
        return "errorUnknown";
    }

    public String videoStateError() {
        return "videoStateError";
    }

    public String videoStateIdle() {
        return "videoStateIdle";
    }

    public String videoStatePerparing() {
        return "videoStatePerparing";
    }

    public String videoStatePrepared() {
        return "videoStatePrepared";
    }

    public String videoStatePlaying() {
        return "videoStatePlaying";
    }

    public String videoStatePause() {
        return "videoStatePause";
    }

    public String videoStatePlaybackCompleted() {
        return "videoStatePlaybackCompleted";
    }

    public String resourceRequestStateIdel() {
        return "resourceRequestStateIdel";
    }

    public String resourceRequestStateRequesting() {
        return "resourceRequestStateRequesting";
    }

    public String resourceRequestStateSuccess() {
        return "resourceRequestStateSuccess";
    }

    public String resourceRequestStateFailed() {
        return "resourceRequestStateFailed";
    }

    public String deviceNetworkTypeWifi() {
        return "deviceNetworkTypeWifi";
    }

    public String deviceNetworkTypeUnknown() {
        return "deviceNetworkTypeUnknown";
    }

    public String deviceNetworkTypeGprs() {
        return "deviceNetworkTypeGprs";
    }

    public String deviceNetworkTypeEdge() {
        return "deviceNetworkTypeEdge";
    }

    public String deviceNetworkTypeUmts() {
        return "deviceNetworkTypeUmts";
    }

    public String deviceNetworkTypeCdma() {
        return "deviceNetworkTypeCdma";
    }

    public String deviceNetworkTypeEvdo0() {
        return "deviceNetworkTypeEvdo0";
    }

    public String deviceNetworkTypeEvdoA() {
        return "deviceNetworkTypeEvdoA";
    }

    public String deviceNetworkTypeLxRtt() {
        return "deviceNetworkTypeLxRtt";
    }

    public String deviceNetworkTypeHsdpa() {
        return "deviceNetworkTypeHsdpa";
    }

    public String deviceNetworkTypeHsupa() {
        return "deviceNetworkTypeHsupa";
    }

    public String deviceNetworkTypeHspa() {
        return "deviceNetworkTypeHspa";
    }

    public String deviceNetworkTypeIden() {
        return "deviceNetworkTypeIden";
    }

    public String deviceNetworkTypeEvdoB() {
        return "deviceNetworkTypeEvdoB";
    }

    public String deviceNetworkTypeLte() {
        return "deviceNetworkTypeLte";
    }

    public String deviceNetworkTypeEhrpd() {
        return "deviceNetworkTypeEhrpd";
    }

    public String deviceNetworkTypeHspaPlus() {
        return "deviceNetworkTypeHspaPlus";
    }

    public String mraidNetworkTypeWifi() {
        return "mraidNetworkTypeWifi";
    }

    public String mraidNetworkTypeCell() {
        return "mraidNetworkTypeCell";
    }

    public String mraidNetworkTypeOffline() {
        return "mraidNetworkTypeOffline";
    }

    public String mraidNetworkTypeUnknown() {
        return "mraidNetworkTypeUnknown";
    }
}
