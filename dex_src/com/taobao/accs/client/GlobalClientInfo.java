package com.taobao.accs.client;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.a.a.a;
import com.taobao.accs.base.AccsAbstractDataListener;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// compiled from: Taobao
public class GlobalClientInfo {
    public static final String AGOO_SERVICE_ID = "agooSend";
    public static final boolean SUPPORT_AUTO_UNIT = false;
    public static String a;
    public static int b;
    public static boolean c;
    private static volatile GlobalClientInfo d;
    private static Context e;
    private String f;
    private ILoginInfo g;
    private IAppReceiver h;
    private ActivityManager i;
    private ConnectivityManager j;
    private Map<String, Set<Integer>> k;
    private a l;
    private Map<String, String> m;
    private Map<String, AccsAbstractDataListener> n;

    static {
        b = -1;
        c = true;
    }

    public static GlobalClientInfo getInstance(Context context) {
        if (d == null) {
            synchronized (GlobalClientInfo.class) {
                if (d == null) {
                    d = new GlobalClientInfo(context);
                }
            }
        }
        return d;
    }

    public static Context getContext() {
        return e;
    }

    private GlobalClientInfo(Context context) {
        this.m = new ConcurrentHashMap<String, String>() {
            {
                put(AGOO_SERVICE_ID, "org.android.agoo.accs.AgooService");
                put("agooAck", "org.android.agoo.accs.AgooService");
                put("agooTokenReport", "org.android.agoo.accs.AgooService");
            }
        };
        this.n = new ConcurrentHashMap();
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        if (e == null) {
            e = context.getApplicationContext();
        }
        com.taobao.accs.common.a.a(new c(this));
    }

    public void setAppSecret(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
            a.a(e).a(str);
        }
    }

    public String getAppSecret() {
        return this.f;
    }

    public ActivityManager getActivityManager() {
        if (this.i == null) {
            this.i = (ActivityManager) e.getSystemService("activity");
        }
        return this.i;
    }

    public ConnectivityManager getConnectivityManager() {
        if (this.j == null) {
            this.j = (ConnectivityManager) e.getSystemService("connectivity");
        }
        return this.j;
    }

    public void setLoginInfoImpl(ILoginInfo iLoginInfo) {
        if (iLoginInfo != null) {
            this.g = iLoginInfo;
        }
    }

    public void clearLoginInfoImpl() {
        this.g = null;
    }

    public String getSid() {
        return this.g == null ? null : this.g.getSid();
    }

    public String getUserId() {
        return this.g == null ? null : this.g.getUserId();
    }

    public String getNick() {
        return this.g == null ? null : this.g.getNick();
    }

    public void setAppReceiver(IAppReceiver iAppReceiver) {
        if (iAppReceiver != null) {
            this.h = iAppReceiver;
            a.a(e).a(iAppReceiver);
        }
    }

    public IAppReceiver getAppReceiver() {
        return this.h;
    }

    public void registerService(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.m.put(str, str2);
        }
    }

    public void unRegisterService(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.m.remove(str);
        }
    }

    public String getService(String str) {
        return (String) this.m.get(str);
    }

    public void registerListener(String str, AccsAbstractDataListener accsAbstractDataListener) {
        if (!TextUtils.isEmpty(str) && accsAbstractDataListener != null) {
            this.n.put(str, accsAbstractDataListener);
        }
    }

    public void unregisterListener(String str) {
        this.n.remove(str);
    }

    public AccsAbstractDataListener getListener(String str) {
        return (AccsAbstractDataListener) this.n.get(str);
    }

    public void setElectionBlackList(Map<String, Set<Integer>> map) {
        this.k = map;
    }

    public Map<String, Set<Integer>> getElectionBlackList() {
        return this.k;
    }

    public void setElectionReslt(a aVar) {
        this.l = aVar;
    }

    public a getElectionResult() {
        return this.l;
    }
}
