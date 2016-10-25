package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.dispatch.HttpDispatcher.IDispatchEventListener;
import anet.channel.strategy.k.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.Utils;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.internal.b;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

// compiled from: Taobao
class i implements IStrategyInstance, IDispatchEventListener {
    protected StrategyInfoHolder a;
    private boolean b;
    private long c;

    i() {
        this.b = false;
        this.a = null;
        this.c = 0;
    }

    public synchronized void initialize() {
        if (!this.b) {
            try {
                ALog.i("awcn.StrategyCenter", "StrategyCenter initialize started.", null, new Object[0]);
                l.a();
                HttpDispatcher.getInstance().addListener(this);
                NetworkStatusHelper.a(GlobalAppRuntimeInfo.getContext());
                this.a = StrategyInfoHolder.a();
                this.b = true;
                ALog.i("awcn.StrategyCenter", "StrategyCenter initialize finished.", null, new Object[0]);
            } catch (Throwable e) {
                ALog.e("awcn.StrategyCenter", "StrategyCenter initialize failed.", null, e, new Object[0]);
            }
        }
    }

    public synchronized void switchEnv() {
        if (this.b) {
            l.b();
            HttpDispatcher.getInstance().switchENV();
            this.a = StrategyInfoHolder.a();
        } else {
            ALog.w("awcn.StrategyCenter", "call switch Env before StrategyCenter not initialized!", null, new Object[0]);
        }
    }

    public String getSchemeByHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ALog.d("awcn.StrategyCenter", "getSchemeByHost", null, b.ELECTION_KEY_HOST, str, "scheme", a(str, null));
        return a(str, null);
    }

    public String getCNameByHost(String str) {
        if (a() || TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = (String) this.a.f.get(str);
        return TextUtils.isEmpty(str2) ? null : str2;
    }

    public String getFormalizeUrl(String str) {
        return getFormalizeUrl(str, HttpConstant.HTTP);
    }

    @Deprecated
    public String getFormalizeUrl(String str, String str2) {
        String[] parseURL = StringUtils.parseURL(str);
        if (parseURL == null) {
            ALog.e("awcn.StrategyCenter", "url is invalid.", null, "URL", str, "stack", Utils.getStackMsg(new Exception("getFormalizeUrl")));
            return null;
        }
        try {
            String str3 = parseURL[1];
            if (!str.startsWith("//")) {
                str2 = parseURL[0];
            }
            if (a(str3, str2) == null) {
                return str;
            }
            String buildString = StringUtils.buildString(a(str3, str2), ":", str.substring(str.indexOf("//")));
            try {
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.StrategyCenter", a.d, null, "raw", str, KEYS.RET, buildString);
                }
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("awcn.StrategyCenter", "getFormalizeUrl failed", null, e2, "raw", str);
                return buildString;
            }
            return buildString;
        } catch (Throwable e3) {
            e2 = e3;
            buildString = str;
            ALog.e("awcn.StrategyCenter", "getFormalizeUrl failed", null, e2, "raw", str);
            return buildString;
        }
    }

    public List<IConnStrategy> getConnStrategyListByHost(String str) {
        if (TextUtils.isEmpty(str) || a()) {
            return Collections.EMPTY_LIST;
        }
        String str2 = (String) this.a.f.get(str);
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        List<IConnStrategy> queryByHost = this.a.c().queryByHost(str2);
        if (queryByHost.isEmpty()) {
            queryByHost = this.a.e.a(str2);
        }
        if (!ALog.isPrintLog(1)) {
            return queryByHost;
        }
        ALog.d("getConnStrategyListByHost", null, b.ELECTION_KEY_HOST, str2, "result", queryByHost);
        return queryByHost;
    }

    private String a(String str, String str2) {
        if (a()) {
            return str2;
        }
        String str3 = (String) this.a.f.get(str);
        if (TextUtils.isEmpty(str3)) {
            str3 = str;
        }
        Object a = this.a.c.a(str3);
        if (a == null) {
            a = this.a.c().querySchemeByHost(str3);
            if (a != null) {
                this.a.c.a(str3, a);
            } else {
                this.a.c().a(str3);
            }
        }
        if (r1 != null && !SafeAislesMap.NO_RESULT.equals(r1)) {
            str3 = r1;
        } else if (str2 == null) {
            str3 = (String) anet.channel.b.b.a().a(XZBDevice.DOWNLOAD_LIST_RECYCLE, str3);
        } else {
            str3 = str2;
        }
        return str3;
    }

    public void forceRefreshStrategy(String str) {
        if (!a() && !TextUtils.isEmpty(str)) {
            this.a.c().a(str);
        }
    }

    public String getUnitPrefix(String str, String str2) {
        return a() ? null : this.a.b.a(str, str2);
    }

    public void setUnitPrefix(String str, String str2, String str3) {
        if (!a()) {
            this.a.b.a(str, str2, str3);
        }
    }

    public Map<String, IHRStrategy> getHRStrategyMap() {
        return a() ? Collections.EMPTY_MAP : this.a.d.a(this.a.c());
    }

    public String getClientIp() {
        return a() ? a.d : this.a.c().b;
    }

    public void notifyConnEvent(String str, IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (!a()) {
            this.a.c().notifyConnEvent(str, iConnStrategy, eventType, dVar);
        }
    }

    private boolean a() {
        if (this.a != null) {
            return false;
        }
        ALog.w("StrategyCenter not initialized", null, "isInitialized", Boolean.valueOf(this.b));
        return true;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        if (dispatchEvent.eventType == 1 && this.a != null) {
            ALog.d("awcn.StrategyCenter", "receive DNS event", null, new Object[0]);
            c a = k.a((JSONObject) dispatchEvent.extraObject);
            if (a != null) {
                this.a.a(a);
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.c > 180000) {
                    saveData();
                    this.c = currentTimeMillis;
                }
            }
        }
    }

    public synchronized void saveData() {
        new j(this).execute(new Void[0]);
    }
}
