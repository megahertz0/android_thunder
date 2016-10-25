package com.baidu.mobad.nativevideo;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.production.d.a;
import java.util.ArrayList;
import java.util.List;

public class BaiduVideoNative implements BaiduNativeNetworkListener {
    private a a;
    private BaiduVideoNetworkListener b;
    private BaiduNative c;

    public static interface BaiduVideoNetworkListener {
        void onAdFail(NativeErrorCode nativeErrorCode);

        void onAdLoad(List<BaiduVideoResponse> list);
    }

    public BaiduVideoNative(Context context, String str, BaiduVideoNetworkListener baiduVideoNetworkListener) {
        this.a = new com.baidu.mobads.production.j.a(context, str);
        this.b = baiduVideoNetworkListener;
        this.c = new BaiduNative(context, str, this, this.a);
    }

    public void makeRequest(RequestParameters requestParameters) {
        this.c.makeRequest(requestParameters);
    }

    public void onNativeLoad(List<NativeResponse> list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new XAdVideoResponse((NativeResponse) list.get(i)));
        }
        this.b.onAdLoad(arrayList);
    }

    public void onNativeFail(NativeErrorCode nativeErrorCode) {
        this.b.onAdFail(nativeErrorCode);
    }

    public static void setAppSid(Activity activity, String str) {
        BaiduNative.setAppSid(activity, str);
    }
}
