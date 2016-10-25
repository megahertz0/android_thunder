package anet.channel;

import android.text.TextUtils;
import anet.channel.AccsSessionManager.Callback;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import com.alipay.sdk.cons.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
class a implements Callback {
    final /* synthetic */ AccsSessionManager a;

    a(AccsSessionManager accsSessionManager) {
        this.a = accsSessionManager;
    }

    public int getSessionCount() {
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    public String getSessionKey(int i) {
        if (i >= getSessionCount()) {
            throw new IllegalArgumentException("index exceeds count!");
        }
        String a;
        if (i == 0) {
            a = n.a();
        } else {
            if (i == 1) {
                Object unitPrefix = StrategyCenter.getInstance().getUnitPrefix(GlobalAppRuntimeInfo.getUserId(), GlobalAppRuntimeInfo.getUtdid());
                if (!TextUtils.isEmpty(unitPrefix)) {
                    a = n.b(unitPrefix);
                }
            }
            a = null;
        }
        if (a == null) {
            return null;
        }
        String schemeByHost = StrategyCenter.getInstance().getSchemeByHost(a);
        if (TextUtils.isEmpty(schemeByHost)) {
            schemeByHost = b.a;
        }
        return n.a(schemeByHost, a);
    }
}
