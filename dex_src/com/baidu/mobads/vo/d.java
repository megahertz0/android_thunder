package com.baidu.mobads.vo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import anet.channel.strategy.dispatch.a;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdSettings.b;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.j.m;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import org.android.agoo.message.MessageService;

public abstract class d implements IXAdRequestInfo {
    private String a;
    public String b;
    protected String c;
    protected Context d;
    protected Activity e;
    public SlotType f;
    protected IXAdProdInfo g;
    public IXAdConstants h;
    public IXAdURIUitls i;
    private String j;
    private int k;
    private int l;
    private int m;
    private String n;
    private String o;
    private int p;
    private int q;
    private int r;
    private String s;
    private String t;
    private String u;
    private boolean v;
    private long w;

    public abstract HashMap<String, String> a();

    public d(Context context, Activity activity, SlotType slotType) {
        this.c = "TODO";
        this.a = a.ANDROID;
        this.j = com.umeng.a.d;
        this.m = m.a().p().getAdCreativeTypeImage();
        this.n = "LP,DL";
        this.o = com.umeng.a.d;
        this.q = 0;
        this.s = com.umeng.a.d;
        this.t = com.umeng.a.d;
        this.u = com.umeng.a.d;
        this.v = true;
        this.w = System.currentTimeMillis();
        this.b = com.umeng.a.d;
        this.h = m.a().p();
        this.i = m.a().i();
        Activity activity2 = null;
        if (context instanceof Activity) {
            activity2 = (Activity) context;
        }
        this.e = activity2;
        if (activity2 != null) {
            context = activity2.getApplicationContext();
        }
        this.d = context;
        if (this.e == null && activity != null) {
            this.e = activity;
        }
        this.f = slotType;
        this.g = new b(this, this.f);
        c(this.f.getValue());
    }

    public IXAdProdInfo d() {
        return this.g;
    }

    @TargetApi(4)
    protected HashMap<String, String> e() {
        IXAdSystemUtils n = m.a().n();
        IXAdCommonUtils m = m.a().m();
        HashMap<String, String> hashMap = new HashMap();
        try {
            Object obj;
            Object obj2;
            hashMap.put(c.a, n.getNetworkCatagory(this.d));
            hashMap.put("u", "default");
            hashMap.put("ie", MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(IXAdRequestInfo.AD_COUNT, getN());
            hashMap.put(IXAdRequestInfo.MAX_TITLE_LENGTH, "512");
            hashMap.put(IXAdRequestInfo.MAX_CONTENT_LENGTH, "512");
            hashMap.put(IXAdRequestInfo.TEST_MODE, MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put(ShareActivity.KEY_AT, getAt());
            hashMap.put(IXAdRequestInfo.V, f() + "_" + com.baidu.mobads.a.a.c + "_4.1.30");
            hashMap.put(IXAdRequestInfo.CS, com.umeng.a.d);
            hashMap.put(IXAdRequestInfo.PACKAGE, m.getAppPackage(this.d));
            hashMap.put(IXAdRequestInfo.SDK_VALID, "sdk_8.27");
            String appId = m.getAppId(this.d);
            hashMap.put(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, appId + "_cpr");
            hashMap.put(SocialConstants.PARAM_APP_ID, appId);
            hashMap.put(IXAdRequestInfo.PHONE_TYPE, Build.MODEL);
            hashMap.put(IXAdRequestInfo.BRAND, n.getPhoneOSBrand());
            DisplayMetrics displayMetrics = m.getDisplayMetrics(this.d);
            hashMap.put(IXAdRequestInfo.DENSITY, displayMetrics.density);
            hashMap.put(IXAdRequestInfo.WIDTH, getW());
            hashMap.put(IXAdRequestInfo.HEIGHT, getH());
            Rect screenRect = m.getScreenRect(this.d);
            hashMap.put(IXAdRequestInfo.SCREEN_WIDTH, screenRect.width());
            hashMap.put(IXAdRequestInfo.SCREEN_HEIGHT, screenRect.height());
            hashMap.put(IXAdRequestInfo.QUERY_WIDTH, String.valueOf(Math.round(((float) getW()) / displayMetrics.density)));
            hashMap.put(IXAdRequestInfo.QUERY_HEIGHT, String.valueOf(Math.round(((float) getH()) / displayMetrics.density)));
            hashMap.put(IXAdRequestInfo.SN, n.getSn(this.d));
            try {
                obj = com.umeng.a.d;
                List cell = n.getCell(this.d);
                if (cell.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < cell.size(); i++) {
                        String[] strArr = (String[]) cell.get(i);
                        stringBuilder.append(String.format("%s_%s_%s|", new Object[]{strArr[0], strArr[1], strArr[2]}));
                    }
                    obj = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
            } catch (Exception e) {
                obj = com.umeng.a.d;
            }
            hashMap.put(Impl.COLUMN_CID, obj);
            hashMap.put(IXAdRequestInfo.NETWORK_OPERATOR, n.getNetworkOperator(this.d));
            hashMap.put(IXAdRequestInfo.IMSI, m.getSubscriberId(this.d));
            try {
                double[] gps = n.getGPS(this.d);
                appId = com.umeng.a.d;
                if (gps != null) {
                    System.currentTimeMillis();
                    appId = String.format("%s_%s_%s", new Object[]{Double.valueOf(gps[0]), Double.valueOf(gps[1]), Double.valueOf(gps[2])});
                }
                String str = appId;
            } catch (Exception e2) {
                obj2 = com.umeng.a.d;
            }
            hashMap.put(IXAdRequestInfo.GPS, obj2);
            try {
                obj2 = com.umeng.a.d;
                List wifi = n.getWIFI(this.d);
            } catch (Exception e3) {
            }
            if (wifi.size() > 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                for (int i2 = 0; i2 < wifi.size(); i2++) {
                    strArr = (String[]) wifi.get(i2);
                    stringBuilder2.append(String.format("%s_%s|", new Object[]{strArr[0], strArr[1]}));
                }
                obj = stringBuilder2.substring(0, stringBuilder2.length() - 1);
                hashMap.put(IXAdRequestInfo.WIFI, obj);
                hashMap.put("swi", (UtilityImpl.NET_TYPE_WIFI.equals(n.getNetworkType(this.d)) ? 1 : 0));
                hashMap.put("tel", com.umeng.a.d);
                try {
                    hashMap.put("uk", URLEncoder.encode(getUk(), "utf-8"));
                    hashMap.put("sex", URLEncoder.encode(getSex(), "utf-8"));
                    hashMap.put("zip", URLEncoder.encode(getZip(), "utf-8"));
                } catch (Exception e4) {
                }
                hashMap.put("tab", n.isTablet(this.d) ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
                hashMap.put("sdc", n.getAppSDC() + MiPushClient.ACCEPT_TIME_SEPARATOR + n.getMem());
                hashMap.put(SocialConstants.PARAM_ACT, getAct());
                hashMap.put("prod", getProd());
                hashMap.put(Constants.KEY_OS_VERSION, a.ANDROID);
                hashMap.put(IXAdRequestInfo.OSV, VERSION.RELEASE);
                hashMap.put(IXAdRequestInfo.BDR, VERSION.SDK_INT);
                hashMap.put("apinfo", m.getBaiduMapsInfo(this.d));
                hashMap.put("apid", getApid());
                hashMap.put("chid", m.getChannelId());
                hashMap.put("apt", MessageService.MSG_DB_READY_REPORT);
                hashMap.put("ap", getAp());
                hashMap.put("nt", n.getNetType(this.d));
                hashMap.put("udid", com.umeng.a.d);
                hashMap.put("ses", getSes());
                hashMap.put("android_id", n.getAndroidId(this.d));
                hashMap.put(Constants.KEY_IMEI, n.getIMEI(this.d));
                hashMap.put("mac", n.getMacAddress(this.d));
                hashMap.put("cuid", n.getCUID(this.d));
                hashMap.put(IXAdRequestInfo.P_VER, "8.27");
                hashMap.put("req_id", m.createRequestId(this.d, getApid()));
                hashMap.put("cssid", n.isWifiConnected(this.d).booleanValue() ? n.getWifiConnected(this.d) : com.umeng.a.d);
                if (AdSettings.getSupportHttps().equals(b.c.a())) {
                    hashMap.put("rpt", String.valueOf(b.c.a()));
                }
                return hashMap;
            }
            obj = obj2;
            hashMap.put(IXAdRequestInfo.WIFI, obj);
            if (UtilityImpl.NET_TYPE_WIFI.equals(n.getNetworkType(this.d))) {
            }
            hashMap.put("swi", (UtilityImpl.NET_TYPE_WIFI.equals(n.getNetworkType(this.d)) ? 1 : 0));
            hashMap.put("tel", com.umeng.a.d);
            hashMap.put("uk", URLEncoder.encode(getUk(), "utf-8"));
            hashMap.put("sex", URLEncoder.encode(getSex(), "utf-8"));
            hashMap.put("zip", URLEncoder.encode(getZip(), "utf-8"));
            if (n.isTablet(this.d)) {
            }
            hashMap.put("tab", n.isTablet(this.d) ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
            hashMap.put("sdc", n.getAppSDC() + MiPushClient.ACCEPT_TIME_SEPARATOR + n.getMem());
            hashMap.put(SocialConstants.PARAM_ACT, getAct());
            hashMap.put("prod", getProd());
            hashMap.put(Constants.KEY_OS_VERSION, a.ANDROID);
            hashMap.put(IXAdRequestInfo.OSV, VERSION.RELEASE);
            hashMap.put(IXAdRequestInfo.BDR, VERSION.SDK_INT);
            hashMap.put("apinfo", m.getBaiduMapsInfo(this.d));
            hashMap.put("apid", getApid());
            hashMap.put("chid", m.getChannelId());
            hashMap.put("apt", MessageService.MSG_DB_READY_REPORT);
            hashMap.put("ap", getAp());
            hashMap.put("nt", n.getNetType(this.d));
            hashMap.put("udid", com.umeng.a.d);
            hashMap.put("ses", getSes());
            hashMap.put("android_id", n.getAndroidId(this.d));
            hashMap.put(Constants.KEY_IMEI, n.getIMEI(this.d));
            hashMap.put("mac", n.getMacAddress(this.d));
            hashMap.put("cuid", n.getCUID(this.d));
            hashMap.put(IXAdRequestInfo.P_VER, "8.27");
            hashMap.put("req_id", m.createRequestId(this.d, getApid()));
            if (n.isWifiConnected(this.d).booleanValue()) {
            }
            hashMap.put("cssid", n.isWifiConnected(this.d).booleanValue() ? n.getWifiConnected(this.d) : com.umeng.a.d);
            if (AdSettings.getSupportHttps().equals(b.c.a())) {
                hashMap.put("rpt", String.valueOf(b.c.a()));
            }
        } catch (Exception e5) {
        }
        return hashMap;
    }

    public String b() {
        HashMap e = e();
        e.putAll(a());
        return m.a().i().getRequestAdUrl(this.b, e);
    }

    public boolean isCanClick() {
        return this.v;
    }

    public void a(boolean z) {
        this.v = z;
    }

    public String f() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int getW() {
        return this.k;
    }

    public void a(int i) {
        this.k = i;
    }

    public int getH() {
        return this.l;
    }

    public void b(int i) {
        this.l = i;
    }

    public String getAct() {
        return this.n;
    }

    public void b(String str) {
        this.n = str;
    }

    public String getProd() {
        return this.o;
    }

    public void c(String str) {
        this.o = str;
    }

    public int getApt() {
        return this.p;
    }

    public void c(int i) {
        this.p = i;
    }

    public int getN() {
        return this.r;
    }

    public void d(int i) {
        this.r = i;
    }

    public String getUk() {
        return this.s;
    }

    public String getSex() {
        return this.t;
    }

    public String getZip() {
        return this.u;
    }

    public long getSes() {
        return this.w;
    }

    public int getAp() {
        return this.q;
    }

    public void e(int i) {
        this.q = i;
    }

    public String getApid() {
        return this.j;
    }

    public void d(String str) {
        this.j = str;
    }

    public int getAt() {
        return this.m;
    }

    public void f(int i) {
        this.m = i;
    }
}
