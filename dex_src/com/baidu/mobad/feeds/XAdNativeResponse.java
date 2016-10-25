package com.baidu.mobad.feeds;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.webkit.WebView;
import com.baidu.mobad.feeds.NativeResponse.MaterialType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.j.m;
import com.umeng.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class XAdNativeResponse implements NativeResponse {
    private IXAdInstanceInfo a;
    private BaiduNative b;
    private boolean c;
    private IXAdFeedsRequestParameters d;
    private IXAdContainer e;

    class AnonymousClass_1 implements OnClickListener {
        final /* synthetic */ Context a;
        final /* synthetic */ View b;
        final /* synthetic */ int c;

        AnonymousClass_1(Context context, View view, int i) {
            this.a = context;
            this.b = view;
            this.c = i;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            XAdNativeResponse.this.a(this.a);
            XAdNativeResponse.this.b.handleClick(this.b, XAdNativeResponse.this.a, this.c, XAdNativeResponse.this);
        }
    }

    public XAdNativeResponse(IXAdInstanceInfo iXAdInstanceInfo, BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters, IXAdContainer iXAdContainer) {
        this.c = false;
        this.a = iXAdInstanceInfo;
        this.b = baiduNative;
        this.e = iXAdContainer;
        if (this.a.getActionType() == m.a().p().getActTypeDownload()) {
            this.c = true;
        }
        this.d = iXAdFeedsRequestParameters;
    }

    public String getAdLogoUrl() {
        return "https://cpro.baidustatic.com/cpro/ui/noexpire/img/mob_adicon.png";
    }

    public String getBaiduLogoUrl() {
        return "https://cpro.baidustatic.com/cpro/ui/noexpire/img/2.0.1/bd-logo4.png";
    }

    public String getTitle() {
        return this.a.getTitle();
    }

    public String getDesc() {
        return this.a.getDescription();
    }

    public String getIconUrl() {
        String iconUrl = this.a.getIconUrl();
        return (iconUrl == null || iconUrl.equals(a.d)) ? this.a.getMainPictureUrl() : iconUrl;
    }

    public String getImageUrl() {
        return this.a.getMainPictureUrl();
    }

    public boolean isDownloadApp() {
        return this.c;
    }

    public void setIsDownloadApp(boolean z) {
        this.c = z;
    }

    public boolean isAdAvailable(Context context) {
        return this.b.isAdAvailable(context, this.a, this.d);
    }

    public long getAppSize() {
        return this.a.getAppSize();
    }

    public String getAppPackage() {
        return this.a.getAppPackageName();
    }

    public List<String> getMultiPicUrls() {
        try {
            JSONArray optJSONArray = this.a.getOriginJsonObject().optJSONArray("morepics");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<String> arrayList = new ArrayList();
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    arrayList.add(optJSONArray.getString(i));
                    i++;
                } catch (Exception e) {
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }

    public Map<String, String> getExtras() {
        return null;
    }

    public void recordImpression(View view) {
        this.b.recordImpression(view, this.a, this.d);
    }

    public void handleClick(View view) {
        handleClick(view, -1);
    }

    private void a(Context context) {
        if (m.a().n().is3GConnected(context).booleanValue()) {
            this.a.setActionOnlyWifi(false);
        } else {
            this.a.setActionOnlyWifi(true);
        }
    }

    private void a(View view, int i) {
        Context context = view.getContext();
        Builder builder = new Builder(context);
        builder.setMessage(new StringBuilder("\u786e\u8ba4\u4e0b\u8f7d\"").append(getTitle()).append("\"?").toString());
        builder.setTitle("\u63d0\u793a");
        builder.setPositiveButton("\u786e\u8ba4", new AnonymousClass_1(context, view, i));
        builder.setNegativeButton("\u53d6\u6d88", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void handleClick(View view, int i) {
        if (isDownloadApp()) {
            Context context = view.getContext();
            if (this.d.getAPPConfirmPolicy() == 3) {
                this.a.setActionOnlyWifi(false);
            } else {
                if (this.d.getAPPConfirmPolicy() != 4) {
                    if (this.d.getAPPConfirmPolicy() == 2) {
                        a(view, i);
                        return;
                    } else if (this.d.getAPPConfirmPolicy() != 1) {
                        return;
                    } else {
                        if (m.a().n().is3GConnected(context).booleanValue()) {
                            a(view, i);
                            return;
                        }
                    }
                }
                a(context);
                this.b.handleClick(view, this.a, i, this.d);
                return;
            }
        }
        this.b.handleClick(view, this.a, i, this.d);
    }

    public void onStart(Context context) {
        this.b.handleOnStart(context, this.a, this.d);
    }

    public void onError(Context context, int i, int i2) {
        this.b.handleOnError(context, i, i2, this.a);
    }

    public void onComplete(Context context) {
        this.b.handleOnComplete(context, this.a, this.d);
    }

    public void onClose(Context context, int i) {
        this.b.handleOnClose(context, i, this.a, this.d);
    }

    public void onFullScreen(Context context, int i) {
        this.b.handleOnFullScreen(context, i, this.a, this.d);
    }

    public String getVideoUrl() {
        return this.a.getVideoUrl();
    }

    public int getDuration() {
        return this.a.getVideoDuration();
    }

    public MaterialType getMaterialType() {
        if (this.a.getCreativeType() == CreativeType.VIDEO) {
            return MaterialType.VIDEO;
        }
        return this.a.getCreativeType() == CreativeType.HTML ? MaterialType.HTML : MaterialType.NORMAL;
    }

    public String getHtmlSnippet() {
        return this.a.getHtmlSnippet();
    }

    public WebView getWebView() {
        return (WebView) this.e.getAdView();
    }

    public void onClickAd(Context context) {
        this.b.handleOnClickAd(context, this.a, this.d);
    }

    public int getMainPicWidth() {
        return this.a.getMainMaterialWidth();
    }

    public int getMainPicHeight() {
        return this.a.getMainMaterialHeight();
    }

    public String getBrandName() {
        return this.a.getAppName();
    }
}
