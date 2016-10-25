package com.baidu.mobads.interfaces.utils;

public interface IXAdConstants {
    String deviceNetworkTypeCdma();

    String deviceNetworkTypeEdge();

    String deviceNetworkTypeEhrpd();

    String deviceNetworkTypeEvdo0();

    String deviceNetworkTypeEvdoA();

    String deviceNetworkTypeEvdoB();

    String deviceNetworkTypeGprs();

    String deviceNetworkTypeHsdpa();

    String deviceNetworkTypeHspa();

    String deviceNetworkTypeHspaPlus();

    String deviceNetworkTypeHsupa();

    String deviceNetworkTypeIden();

    String deviceNetworkTypeLte();

    String deviceNetworkTypeLxRtt();

    String deviceNetworkTypeUmts();

    String deviceNetworkTypeUnknown();

    String deviceNetworkTypeWifi();

    String errorIo();

    String errorNullAsset();

    String errorTimeout();

    String errorUnknown();

    String feedsTrackerParameterKeyList();

    String feedsTrackerParameterKeyProgress();

    int getActTypeDownload();

    int getActTypeLandingPage();

    int getActTypeMakeCall();

    int getActTypeNothing2Do();

    int getActTypeOpenExternalApp();

    int getActTypeOpenMap();

    int getActTypePlayVideo();

    int getActTypeRichMedia();

    int getActTypeSendMail();

    int getActTypeSendSMS();

    int getAdCreativeTypeImage();

    int getAdCreativeTypeRichmedia();

    int getAdCreativeTypeText();

    int getAdCreativeTypeVideo();

    String getAppPackageNameOfPublisher();

    String getAppSec();

    String getAppSid();

    int getCanSendCalender();

    int getCanSendEmail();

    int getCanSendSMS();

    int getCanShowDownload();

    int getCanShowMap();

    String getInfoKeyErrorCode();

    String getInfoKeyErrorMessage();

    String getInfoKeyErrorModule();

    @Deprecated
    String getProductionTypeBanner();

    String getProductionTypeChuilei();

    @Deprecated
    String getProductionTypeFeeds();

    String getProductionTypeFrontlink();

    @Deprecated
    String getProductionTypeIcon();

    @Deprecated
    String getProductionTypeInterstitial();

    @Deprecated
    String getProductionTypeNRWall();

    @Deprecated
    String getProductionTypeRWall();

    @Deprecated
    String getProductionTypeSplash();

    @Deprecated
    String getProductionTypeVideo();

    @Deprecated
    String getProductionTypeWall();

    String getRemoteVersion();

    String getSN();

    String getSupportedActionType4RequestingAPO();

    String getSupportedActionType4RequestingDownload();

    String getSupportedActionType4RequestingLandingPage();

    String getSupportedActionType4RequestingMail();

    String getSupportedActionType4RequestingMakeCall();

    String getSupportedActionType4RequestingMap();

    String getSupportedActionType4RequestingNone();

    String getSupportedActionType4RequestingRichMedia();

    String getSupportedActionType4RequestingSMS();

    String getSupportedActionType4RequestingVideo();

    String[] getSupportedBrowsers();

    String mraidNetworkTypeCell();

    String mraidNetworkTypeOffline();

    String mraidNetworkTypeUnknown();

    String mraidNetworkTypeWifi();

    String resourceRequestStateFailed();

    String resourceRequestStateIdel();

    String resourceRequestStateRequesting();

    String resourceRequestStateSuccess();

    void setSupportedBrowsers(String[] strArr);

    String videoStateError();

    String videoStateIdle();

    String videoStatePause();

    String videoStatePerparing();

    String videoStatePlaybackCompleted();

    String videoStatePlaying();

    String videoStatePrepared();
}
