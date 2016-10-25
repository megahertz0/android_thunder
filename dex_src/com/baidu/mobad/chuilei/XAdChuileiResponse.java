package com.baidu.mobad.chuilei;

import android.view.View;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.XAdNativeResponse;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;

class XAdChuileiResponse implements BaiduChuileiResponse {
    NativeResponse a;

    public XAdChuileiResponse(IXAdInstanceInfo iXAdInstanceInfo, BaiduNative baiduNative, IXAdFeedsRequestParameters iXAdFeedsRequestParameters, IXAdContainer iXAdContainer) {
        this.a = new XAdNativeResponse(iXAdInstanceInfo, baiduNative, iXAdFeedsRequestParameters, iXAdContainer);
    }

    public XAdChuileiResponse(NativeResponse nativeResponse) {
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

    public String getTitle() {
        return this.a.getTitle();
    }

    public String getImageUrl() {
        return this.a.getImageUrl();
    }
}
