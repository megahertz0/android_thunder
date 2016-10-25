package com.baidu.mobad.feeds;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import java.util.List;
import java.util.Map;

public interface NativeResponse {

    public enum MaterialType {
        NORMAL,
        VIDEO,
        HTML;

        static {
            NORMAL = new com.baidu.mobad.feeds.NativeResponse.MaterialType("NORMAL", 0);
            VIDEO = new com.baidu.mobad.feeds.NativeResponse.MaterialType("VIDEO", 1);
            HTML = new com.baidu.mobad.feeds.NativeResponse.MaterialType("HTML", 2);
            a = new com.baidu.mobad.feeds.NativeResponse.MaterialType[]{NORMAL, VIDEO, HTML};
        }
    }

    String getAdLogoUrl();

    String getAppPackage();

    long getAppSize();

    String getBaiduLogoUrl();

    String getBrandName();

    String getDesc();

    int getDuration();

    Map<String, String> getExtras();

    String getHtmlSnippet();

    String getIconUrl();

    String getImageUrl();

    int getMainPicHeight();

    int getMainPicWidth();

    MaterialType getMaterialType();

    List<String> getMultiPicUrls();

    String getTitle();

    String getVideoUrl();

    WebView getWebView();

    void handleClick(View view);

    void handleClick(View view, int i);

    boolean isAdAvailable(Context context);

    boolean isDownloadApp();

    void onClickAd(Context context);

    void onClose(Context context, int i);

    void onComplete(Context context);

    void onError(Context context, int i, int i2);

    void onFullScreen(Context context, int i);

    void onStart(Context context);

    void recordImpression(View view);
}
