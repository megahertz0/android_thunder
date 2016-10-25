package com.baidu.mobad.nativevideo;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.NativeResponse.MaterialType;
import com.baidu.mobad.feeds.XAdNativeResponse;
import com.baidu.mobad.nativevideo.BaiduVideoResponse.PrerollMaterialType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class XAdVideoResponse implements BaiduVideoResponse {
    NativeResponse a;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[MaterialType.values().length];
            try {
                a[MaterialType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MaterialType.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public XAdVideoResponse(IXAdInstanceInfo iXAdInstanceInfo, BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters, IXAdContainer iXAdContainer) {
        this.a = new XAdNativeResponse(iXAdInstanceInfo, baiduNative, iXAdFeedsRequestParameters, iXAdContainer);
    }

    public XAdVideoResponse(NativeResponse nativeResponse) {
        this.a = nativeResponse;
    }

    public void recordImpression(View view) {
        this.a.recordImpression(view);
    }

    public void handleClick(View view) {
        this.a.handleClick(view);
    }

    public void handleClick(View view, int i) {
        this.a.handleClick(view, i);
    }

    public void onStart(Context context) {
        this.a.onStart(context);
    }

    public void onError(Context context, int i, int i2) {
        this.a.onError(context, i, i2);
    }

    public void onComplete(Context context) {
        this.a.onComplete(context);
    }

    public void onClose(Context context, int i) {
        this.a.onClose(context, i);
    }

    public void onClickAd(Context context) {
        this.a.onClickAd(context);
    }

    public void onFullScreen(Context context, int i) {
        this.a.onFullScreen(context, i);
    }

    public String getVideoUrl() {
        return this.a.getVideoUrl();
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    public PrerollMaterialType getMaterialType() {
        PrerollMaterialType prerollMaterialType = PrerollMaterialType.NORMAL;
        switch (AnonymousClass_1.a[this.a.getMaterialType().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return PrerollMaterialType.VIDEO;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return this.a.getImageUrl().endsWith(".gif") ? PrerollMaterialType.GIF : prerollMaterialType;
            default:
                return prerollMaterialType;
        }
    }

    public String getImageUrl() {
        return this.a.getImageUrl();
    }

    public boolean isDownloadApp() {
        return this.a.isDownloadApp();
    }

    public String getAdLogoUrl() {
        return this.a.getAdLogoUrl();
    }

    public String getBaiduLogoUrl() {
        return this.a.getBaiduLogoUrl();
    }
}
