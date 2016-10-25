package com.baidu.mobads.production.d;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.feeds.IXAdDummyContainer;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.production.t;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a extends com.baidu.mobads.production.a {
    public static String w;
    private b x;
    private ArrayList<IXAdInstanceInfo> y;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return n();
    }

    static {
        w = com.umeng.a.d;
    }

    public a(Context context) {
        super(context);
    }

    public a(Context context, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(null);
        this.p = SlotType.SLOT_TYPE_FEEDS;
        this.x = new b(getApplicationContext(), getActivity(), this.p);
        IXAdConstants p = m.a().p();
        this.x.b(p.getSupportedActionType4RequestingNone() + MiPushClient.ACCEPT_TIME_SEPARATOR + p.getSupportedActionType4RequestingLandingPage() + MiPushClient.ACCEPT_TIME_SEPARATOR + p.getSupportedActionType4RequestingDownload() + MiPushClient.ACCEPT_TIME_SEPARATOR + p.getSupportedActionType4RequestingAPO());
        this.x.a(Impl.STATUS_LX_VIP_ERROR_START);
        this.x.b(Impl.STATUS_PEER_NOT_FOUND_ERROR);
        this.x.e(0);
        this.x.d(str);
        this.x.c(0);
        this.x.d(1);
        this.x.f(m.a().p().getAdCreativeTypeImage());
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.x.a(width);
            this.x.b(height);
        }
    }

    protected void d() {
        this.n = 8000;
    }

    public void request() {
        super.a(this.x);
    }

    protected void a(d dVar, t tVar, int i) {
        tVar.a(dVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        iXAdContainer.start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.y = iXAdContainer.getAdContainerContext().getAdResponseInfo().getAdInstanceList();
    }

    public ArrayList<IXAdInstanceInfo> m() {
        return this.y;
    }

    public com.baidu.mobads.vo.d n() {
        return this.x;
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onImpression(view, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(-1, iXAdInstanceInfo.getThirdImpressionTrackingUrls()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(iXAdInstanceInfo.getHtmlSnippet());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getQueryKey());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getAdId());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getMainPictureUrl());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getTitle());
            stringBuffer.append("_&_");
            w = stringBuffer.toString();
        } catch (Exception e) {
        }
        try {
            return ((IXAdDummyContainer) this.h).isAdAvailable(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
        } catch (Exception e2) {
            return false;
        }
    }

    public void b(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        a(view, iXAdInstanceInfo, -1, iXAdFeedsRequestParameters);
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, int i, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onClick(view, iXAdInstanceInfo, i, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getThirdClickTrackingUrls()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onStart(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(0, iXAdInstanceInfo.getStartTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, int i, int i2, IXAdInstanceInfo iXAdInstanceInfo) {
    }

    public void c(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onComplete(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onClose(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getCloseTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onCstartcard(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(0, iXAdInstanceInfo.getCstartcardTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onFullScreen(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getFullScreenTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> a(int i, List<String> list) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(m.a().p().feedsTrackerParameterKeyProgress(), Integer.valueOf(i));
        hashMap.put(m.a().p().feedsTrackerParameterKeyList(), list);
        return hashMap;
    }

    public void c() {
        this.h.load();
    }
}
