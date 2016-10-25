package com.baidu.mobads.vo.a;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.j.m;
import com.taobao.accs.common.Constants;
import com.tencent.bugly.Bugly;
import com.umeng.a;
import java.util.HashMap;
import org.android.agoo.message.MessageService;

public class b extends a {
    public boolean n;
    private Context o;
    private IXAppInfo p;

    public b(Context context, IXAppInfo iXAppInfo) {
        super(iXAppInfo.getAdId(), iXAppInfo.getQk(), iXAppInfo.getProd());
        this.n = true;
        this.o = context;
        this.p = iXAppInfo;
    }

    protected HashMap<String, String> b() {
        String str = m.a().n().getCurrentProcessId(this.o);
        HashMap<String, String> hashMap = new HashMap();
        try {
            hashMap.put("autoOpen", this.n ? "true" : Bugly.SDK_IS_DEV);
            hashMap.put(IXAdRequestInfo.PACKAGE, this.p.getPackageName());
            hashMap.put("c", MessageService.MSG_DB_READY_REPORT);
            hashMap.put("clickProcId", str);
            hashMap.put("clickTime", this.p.getClickTime());
            hashMap.put("contentLength", this.p.getAppSize());
            hashMap.put("dlCnt", MessageService.MSG_DB_NOTIFY_REACHED);
            hashMap.put("dlTime", String.valueOf(System.currentTimeMillis() - this.p.getClickTime()));
            hashMap.put("dlTunnel", MessageService.MSG_DB_NOTIFY_DISMISS);
            hashMap.put("dlWay", this.p.isTooLarge() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
            hashMap.put("exp_id", a.d);
            hashMap.put("exp2", a.d);
            hashMap.put(Constants.KEY_ELECTION_PKG, this.p.getPackageName());
            hashMap.put("typeProcId", str);
            hashMap.put("url2", a.d);
            hashMap.put(IXAdRequestInfo.SN, m.a().e().encode(m.a().n().getDeviceId(this.o)));
        } catch (Throwable e) {
            m.a().f().d(e);
        }
        return hashMap;
    }
}
