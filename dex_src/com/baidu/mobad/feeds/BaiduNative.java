package com.baidu.mobad.feeds;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import com.baidu.mobads.h.q;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.d.a;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BaiduNative {
    private final Context a;
    private final String b;
    private a c;
    private BaiduNativeNetworkListener d;
    private BaiduNativeEventListener e;

    public static interface BaiduNativeNetworkListener {
        void onNativeFail(NativeErrorCode nativeErrorCode);

        void onNativeLoad(List<NativeResponse> list);
    }

    public static interface BaiduNativeEventListener {
        void onClicked();

        void onImpressionSended();
    }

    class CustomIOAdEventListener implements IOAdEventListener {
        private IXAdFeedsRequestParameters b;

        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ List a;

            AnonymousClass_1(List list) {
                this.a = list;
            }

            public void run() {
                CustomIOAdEventListener.this.a.d.onNativeLoad(this.a);
            }
        }

        public CustomIOAdEventListener(IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
            this.b = iXAdFeedsRequestParameters;
        }

        public void run(IOAdEvent iOAdEvent) {
            if (IXAdEvent.AD_STARTED.equals(iOAdEvent.getType())) {
                BaiduNative.this.c.removeAllListeners();
                if (BaiduNative.this.d != null) {
                    List arrayList = new ArrayList();
                    HashSet hashSet = new HashSet();
                    IXAdConstants p = m.a().p();
                    for (int i = 0; i < BaiduNative.this.c.m().size(); i++) {
                        boolean z;
                        boolean z2;
                        IXAdInstanceInfo iXAdInstanceInfo = (IXAdInstanceInfo) BaiduNative.this.c.m().get(i);
                        String appPackageName = iXAdInstanceInfo.getAppPackageName();
                        if (iXAdInstanceInfo.getActionType() != p.getActTypeDownload() || (appPackageName == null || appPackageName.equals(com.umeng.a.d) || appPackageName.equals("null") || hashSet.contains(appPackageName))) {
                            z = false;
                            int i2 = 1;
                        } else {
                            hashSet.add(appPackageName);
                            if (m.a().l().isInstalled(BaiduNative.this, appPackageName)) {
                                int i3 = 1;
                                z2 = false;
                            }
                            z = false;
                            z2 = false;
                        }
                        if (!z2) {
                            XAdNativeResponse xAdNativeResponse = new XAdNativeResponse(iXAdInstanceInfo, BaiduNative.this, this.b, BaiduNative.this.c.getCurrentXAdContainer());
                            if (z) {
                                xAdNativeResponse.setIsDownloadApp(false);
                            }
                            arrayList.add(xAdNativeResponse);
                        }
                    }
                    m.a().m().a(new AnonymousClass_1(arrayList));
                }
            }
            if (IXAdEvent.AD_ERROR.equals(iOAdEvent.getType())) {
                BaiduNative.this.c.removeAllListeners();
                iOAdEvent.getData().get(Constants.SHARED_MESSAGE_ID_FILE);
                if (BaiduNative.this.d != null) {
                    m.a().m().a(new Runnable() {
                        public void run() {
                            CustomIOAdEventListener.this.d.onNativeFail(NativeErrorCode.LOAD_AD_FAILED);
                        }
                    });
                }
            }
        }
    }

    public BaiduNative(Context context, String str, BaiduNativeNetworkListener baiduNativeNetworkListener) {
        this(context, str, baiduNativeNetworkListener, new a(context, str));
    }

    public BaiduNative(Context context, String str, BaiduNativeNetworkListener baiduNativeNetworkListener, a aVar) {
        this.a = context;
        m.a().a(context.getApplicationContext());
        this.b = str;
        this.d = baiduNativeNetworkListener;
        q.a(context).a();
        this.c = aVar;
    }

    public void destroy() {
    }

    @Deprecated
    public void setNativeEventListener(BaiduNativeEventListener baiduNativeEventListener) {
        this.e = baiduNativeEventListener;
    }

    public void makeRequest() {
        makeRequest(null);
    }

    public void makeRequest(RequestParameters requestParameters) {
        if (requestParameters == null) {
            requestParameters = new Builder().build();
        }
        requestParameters.mPlacementId = this.b;
        IOAdEventListener customIOAdEventListener = new CustomIOAdEventListener(requestParameters);
        this.c.addEventListener(IXAdEvent.AD_STARTED, customIOAdEventListener);
        this.c.addEventListener(IXAdEvent.AD_ERROR, customIOAdEventListener);
        this.c.a(requestParameters);
        this.c.request();
    }

    protected void recordImpression(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(view, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected boolean isAdAvailable(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        return this.c.a(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleClick(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.b(view, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleClick(View view, IXAdInstanceInfo iXAdInstanceInfo, int i, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(view, iXAdInstanceInfo, i, iXAdFeedsRequestParameters);
    }

    protected void handleOnStart(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.b(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleOnError(Context context, int i, int i2, IXAdInstanceInfo iXAdInstanceInfo) {
        this.c.a(context, i, i2, iXAdInstanceInfo);
    }

    protected void handleOnComplete(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.c(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleOnClose(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.a(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleOnClickAd(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.d(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    protected void handleOnFullScreen(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        this.c.b(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters);
    }

    public static void setAppSid(Context context, String str) {
        m.a().m().setAppId(str);
    }
}
