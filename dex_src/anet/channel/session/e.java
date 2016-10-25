package anet.channel.session;

import android.content.Context;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.Session.Status;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.entity.a;
import anet.channel.request.Cancelable;
import anet.channel.request.FutureCancelable;
import anet.channel.request.Request;
import anet.channel.request.Request.Builder;
import anet.channel.statist.RequestStatistic;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.g;
import com.alipay.sdk.cons.b;
import com.xunlei.tdlive.R;
import java.util.HashSet;
import java.util.Set;

// compiled from: Taobao
public class e extends Session {
    public static Set<String> a;

    static {
        a = new HashSet();
    }

    public e(Context context, a aVar) {
        super(context, aVar, aVar.c());
        if (this.mConnStrategy == null) {
            ConnType connType = (this.mHost == null || !this.mHost.startsWith(b.a)) ? ConnType.HTTP : ConnType.HTTPS;
            this.mConnType = connType;
        }
    }

    public static boolean a(String str, String str2, int i) {
        return a.contains(StringUtils.buildString(str, str2, String.valueOf(i)));
    }

    public boolean isAvailable() {
        return this.mStatus == Status.AUTH_SUCC;
    }

    protected void connect() {
        try {
            ALog.i("awcn.HttpSession", "HttpSession connect", null, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.mHost);
            Request build = new Builder().setUrl(this.mHost).build();
            build.setDnsOptimize(this.mIp, this.mPort);
            c.a(new f(this, build), R.styleable.Toolbar_contentInsetLeft);
        } catch (Throwable th) {
            ALog.e("awcn.HttpSession", "HTTP connect fail.", null, th, new Object[0]);
        }
    }

    public void close() {
        notifyStatus(Status.DISCONNECTED, null);
    }

    public void close(boolean z) {
        this.autoReCreate = false;
        close();
    }

    protected Runnable getRecvTimeOutRunnable() {
        return null;
    }

    public void ping(boolean z) {
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        FutureCancelable futureCancelable = FutureCancelable.NULL;
        RequestStatistic requestStatistic = request != null ? request.rs : new RequestStatistic(n.e(this.mHost), null);
        requestStatistic.setConnType(this.mConnType);
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(ErrorConstant.ERROR_PARAM_ILLEGAL, ErrorConstant.getErrMsg(ErrorConstant.ERROR_PARAM_ILLEGAL), requestStatistic);
            }
            return futureCancelable;
        }
        try {
            request.setDnsOptimize(this.mIp, this.mPort);
            requestStatistic.start = System.currentTimeMillis();
            FutureCancelable futureCancelable2 = new FutureCancelable(c.a(new g(this, request, requestCb), g.a(request.getUrl())), request.getSeq());
            return futureCancelable2;
        } catch (Throwable th) {
            if (requestCb != null) {
                requestCb.onFinish(ErrorConstant.ERROR_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_EXCEPTION, th.toString()), requestStatistic);
            }
            return futureCancelable;
        }
    }
}
