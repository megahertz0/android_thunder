package com.baidu.mobads.production;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerEventListener;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.j.d;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.a;
import com.baidu.mobads.vo.XAdInstanceInfo;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class o implements IXAdContainerEventListener {
    private Context a;
    private final a b;
    private AtomicBoolean c;
    private AtomicBoolean d;
    private AtomicBoolean e;
    private AtomicBoolean f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    static /* synthetic */ int d(o oVar) {
        int i = oVar.g;
        oVar.g = i + 1;
        return i;
    }

    static /* synthetic */ int h(o oVar) {
        int i = oVar.j;
        oVar.j = i + 1;
        return i;
    }

    public o(Context context, a aVar) {
        this.g = 0;
        this.h = 2;
        this.i = 15;
        this.j = 0;
        this.k = 2;
        this.l = 15;
        this.a = context;
        this.b = aVar;
        this.c = new AtomicBoolean(false);
        this.d = new AtomicBoolean(false);
        this.e = new AtomicBoolean(false);
        this.f = new AtomicBoolean(false);
    }

    private void a(Context context, String str, String str2) {
        try {
            this.g = 0;
            Timer timer = new Timer();
            timer.schedule(new p(this, m.a().l(), context, str2, timer, str), 0, 1000);
        } catch (Exception e) {
        }
    }

    private void a(Context context, String str) {
        try {
            this.j = 0;
            Timer timer = new Timer();
            timer.schedule(new q(this, m.a().l(), context, str, timer), 0, 1000);
        } catch (Exception e) {
        }
    }

    public void onAdClicked(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        int i;
        Boolean bool2;
        d m = m.a().m();
        IXAdConstants p = m.a().p();
        IXAdPackageUtils l = m.a().l();
        IXNonLinearAdSlot iXNonLinearAdSlot = this.b;
        IXAdResource adResource = iXAdContainer.getAdContainerContext().getAdResource();
        Boolean valueOf = Boolean.valueOf(false);
        String clickThroughUrl = iXAdInstanceInfo.getClickThroughUrl();
        int actionType = iXAdInstanceInfo.getActionType();
        Collection arrayList = new ArrayList();
        List thirdClickTrackingUrls = iXAdInstanceInfo.getThirdClickTrackingUrls();
        for (i = 0; i < thirdClickTrackingUrls.size(); i++) {
            arrayList.add(((String) thirdClickTrackingUrls.get(i)).replaceAll("\\$\\{PROGRESS\\}", String.valueOf((int) iXAdContainer.getPlayheadTime())));
        }
        Set hashSet = new HashSet();
        hashSet.addAll(arrayList);
        a(hashSet);
        int optInt;
        if (actionType == p.getActTypeOpenExternalApp()) {
            JSONObject jSONObject;
            Boolean valueOf2 = Boolean.valueOf(true);
            try {
                jSONObject = new JSONObject(clickThroughUrl);
            } catch (Exception e) {
                try {
                    JSONObject jSONObject2 = new JSONObject(iXAdInstanceInfo.getAppOpenStrs());
                    a aVar = new a();
                    com.baidu.mobads.openad.e.d dVar = new com.baidu.mobads.openad.e.d(clickThroughUrl, com.umeng.a.d);
                    dVar.e = 1;
                    aVar.a(dVar, Boolean.valueOf(true));
                    jSONObject = jSONObject2;
                } catch (Exception e2) {
                }
            }
            String optString = jSONObject.optString(JsInterface.KEY_PAGE, com.umeng.a.d);
            if (!l.sendAPOInfo(iXNonLinearAdSlot.getApplicationContext(), optString, iXAdInstanceInfo.getAppPackageName(), 366, jSONObject.optInt("fb_act", 0))) {
                optInt = jSONObject.optInt("fb_act", 0);
                IXAdInstanceInfo xAdInstanceInfo = new XAdInstanceInfo(new JSONObject());
                if (optInt == p.getActTypeLandingPage()) {
                    xAdInstanceInfo.setActionType(p.getActTypeLandingPage());
                    xAdInstanceInfo.setClickThroughUrl(jSONObject.optString("fallback", com.umeng.a.d));
                    xAdInstanceInfo.setTitle(iXAdInstanceInfo.getTitle());
                    xAdInstanceInfo.setInapp(true);
                    onAdClicked(iXAdContainer, xAdInstanceInfo, bool, hashMap);
                } else if (optInt == p.getActTypeDownload()) {
                    xAdInstanceInfo.setActionType(p.getActTypeDownload());
                    xAdInstanceInfo.setClickThroughUrl(jSONObject.optString("fallback", com.umeng.a.d));
                    xAdInstanceInfo.setTitle(iXAdInstanceInfo.getTitle());
                    xAdInstanceInfo.setInapp(true);
                    xAdInstanceInfo.setAPOOpen(true);
                    xAdInstanceInfo.setPage(optString);
                    xAdInstanceInfo.setAppPackageName(iXAdInstanceInfo.getAppPackageName());
                    onAdClicked(iXAdContainer, xAdInstanceInfo, bool, hashMap);
                }
            } else if (bool.booleanValue()) {
                new com.baidu.mobads.command.b.a(iXNonLinearAdSlot, iXAdInstanceInfo, adResource, optString).a();
            }
            a(iXNonLinearAdSlot.getApplicationContext(), optString, iXAdInstanceInfo.getAppPackageName());
            bool2 = valueOf2;
        } else if (actionType == p.getActTypeDownload()) {
            bool2 = Boolean.valueOf(false);
            if (bool.booleanValue()) {
                new com.baidu.mobads.command.a.a(iXNonLinearAdSlot, iXAdInstanceInfo, adResource).a();
            }
        } else if (actionType == p.getActTypeLandingPage() || actionType == p.getActTypeOpenMap()) {
            if (this.b.getProdInfo().getProdType() != p.getProductionTypeSplash()) {
                bool2 = Boolean.valueOf(true);
            } else {
                bool2 = valueOf;
            }
            if (bool.booleanValue()) {
                if (iXAdInstanceInfo.isInapp()) {
                    new com.baidu.mobads.command.c.a(iXNonLinearAdSlot, iXAdInstanceInfo, adResource, clickThroughUrl).a();
                } else {
                    m.browserOutside(iXAdContainer.getAdContainerContext().getApplicationContext(), clickThroughUrl);
                }
            }
        } else {
            if (actionType == p.getActTypeMakeCall() || actionType == p.getActTypeSendSMS() || actionType == p.getActTypeSendMail()) {
                valueOf = Boolean.valueOf(true);
                if (bool.booleanValue()) {
                    new com.baidu.mobads.command.b.a(iXNonLinearAdSlot, iXAdInstanceInfo, adResource, clickThroughUrl).a();
                }
                if (actionType == p.getActTypeMakeCall()) {
                    PackageManager packageManager = iXNonLinearAdSlot.getApplicationContext().getPackageManager();
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(clickThroughUrl));
                    List queryIntentActivities = packageManager.queryIntentActivities(intent, R.styleable.AppCompatTheme_imageButtonStyle);
                    if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                        Object obj;
                        i = 0;
                        String str = null;
                        while (i < queryIntentActivities.size()) {
                            if (i > 0 && !str.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.processName)) {
                                obj = null;
                                break;
                            }
                            i++;
                            str = ((ResolveInfo) queryIntentActivities.get(i)).activityInfo.processName;
                        }
                        optInt = 1;
                        if (obj != null) {
                            a(iXNonLinearAdSlot.getApplicationContext(), str);
                        }
                    }
                    bool2 = valueOf;
                }
            } else if (actionType != p.getActTypeNothing2Do()) {
                p.getActTypeRichMedia();
            }
            bool2 = valueOf;
        }
        if (bool2.booleanValue()) {
            this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_CLICK_THRU));
        }
        this.b.dispatchEvent(new com.baidu.mobads.f.a("AdUserClick"));
    }

    public void onAdLoaded(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.a(iXAdContainer, (HashMap) hashMap);
        } else {
            new Handler(this.a.getMainLooper()).post(new r(this, iXAdContainer, hashMap));
        }
    }

    public void onAdStarted(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.b(iXAdContainer, hashMap);
        } else {
            new Handler(this.a.getMainLooper()).post(new s(this, iXAdContainer, hashMap));
        }
    }

    public void onAdImpression(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        a(iXAdInstanceInfo.getImpressionUrls());
    }

    public void onAdStoped(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, Boolean bool2, HashMap<String, Object> hashMap) {
        if (iXAdInstanceInfo != null) {
            Set hashSet = new HashSet();
            hashSet.addAll(iXAdInstanceInfo.getCloseTrackers());
            a(hashSet);
        }
        if (bool2.booleanValue()) {
            IXAdContainerContext adContainerContext = iXAdContainer.getAdContainerContext();
            this.b.a(adContainerContext.getAdResponseInfo(), adContainerContext.getAdInstanceInfo());
            return;
        }
        this.b.e(iXAdContainer, hashMap);
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STOPPED));
    }

    public void onAdError(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
            if (hashMap != null) {
                IXAdConstants p = m.a().p();
                com.baidu.mobads.c.a.a().a(hashMap.get(p.getInfoKeyErrorCode()) + MiPushClient.ACCEPT_TIME_SEPARATOR + hashMap.get(p.getInfoKeyErrorMessage()) + MiPushClient.ACCEPT_TIME_SEPARATOR + hashMap.get(p.getInfoKeyErrorModule()));
            }
            this.e.set(true);
            this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        }
    }

    public void onAdPlaying(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_PLAYING));
    }

    public void onAdPaused(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_PAUSED));
    }

    public void onAdLinearChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdExpandedChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserClosed(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public void onAdDurationChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdRemainingTimeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVolumeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSizeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSkippableStateChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSkipped(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdInteraction(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserAcceptInvitation(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserMinimize(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoStart(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoFirstQuartile(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoMidpoint(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoThirdQuartile(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoComplete(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdCustomEvent(String str, IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    private void a(Set<String> set) {
        a aVar = new a();
        for (String str : set) {
            com.baidu.mobads.openad.e.d dVar = new com.baidu.mobads.openad.e.d(str, com.umeng.a.d);
            dVar.e = 1;
            aVar.a(dVar, Boolean.valueOf(true));
        }
    }
}
