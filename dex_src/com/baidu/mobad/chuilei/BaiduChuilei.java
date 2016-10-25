package com.baidu.mobad.chuilei;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import com.baidu.mobads.production.b.b;
import java.util.ArrayList;
import java.util.List;

public class BaiduChuilei implements BaiduNativeNetworkListener {
    private b a;
    private BaiduChuileiNetworkListener b;
    private BaiduNative c;

    public static interface BaiduChuileiNetworkListener {
        void onChuileiFail(BaiduChuileiErrorCode baiduChuileiErrorCode);

        void onChuileiLoad(List<BaiduChuileiResponse> list);
    }

    public BaiduChuilei(Context context, String str, BaiduChuileiNetworkListener baiduChuileiNetworkListener) {
        this.a = new b(context, str);
        this.b = baiduChuileiNetworkListener;
        this.c = new BaiduNative(context, str, this, this.a);
    }

    public void makeRequest(BaiduChuileiRequestParameters baiduChuileiRequestParameters) {
        RequestParameters requestParameters = baiduChuileiRequestParameters.getRequestParameters();
        if (requestParameters == null) {
            requestParameters = new Builder().build();
        }
        this.c.makeRequest(requestParameters);
    }

    public void onNativeLoad(List<NativeResponse> list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new XAdChuileiResponse((NativeResponse) list.get(i)));
        }
        this.b.onChuileiLoad(arrayList);
    }

    public void onNativeFail(NativeErrorCode nativeErrorCode) {
        this.b.onChuileiFail(BaiduChuileiErrorCode.LOAD_AD_FAILED);
    }

    public static void setAppSid(Activity activity, String str) {
        BaiduNative.setAppSid(activity, str);
    }
}
