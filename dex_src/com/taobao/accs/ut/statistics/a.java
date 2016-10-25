package com.taobao.accs.ut.statistics;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class a implements UTInterface {
    public String a;
    public String b;
    public boolean c;
    public String d;
    private final String e;
    private boolean f;

    public a() {
        this.e = "BindApp";
        this.f = false;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(int i) {
        switch (i) {
            case ErrCode.ERR_AUTH_DENIED:
                a("msg too large");
            case AppbarJsBridge.Code_Java_Exception:
                a("service not available");
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                a("param error");
            case SniffingResourceGroup.PAGETYPE_NONE:
                a("network fail");
            case Impl.STATUS_SUCCESS:
                break;
            case XLErrorCode.OAUTH_PARAM_ERROR:
                a("app not bind");
            default:
                a(String.valueOf(i));
        }
    }

    public void commitUT() {
        b("BindApp");
    }

    private void b(String str) {
        Throwable th;
        String str2;
        String str3;
        if (!this.f) {
            this.f = true;
            Map hashMap = new HashMap();
            try {
                Object obj = this.a;
                try {
                    Object obj2 = "212";
                    try {
                        hashMap.put("device_id", this.a);
                        hashMap.put("bind_date", this.b);
                        hashMap.put(KEYS.RET, this.c ? "y" : IXAdRequestInfo.AD_COUNT);
                        hashMap.put("fail_reasons", this.d);
                        hashMap.put("push_token", com.umeng.a.d);
                        if (ALog.isPrintLog(Level.D)) {
                            ALog.d("accs.BindAppStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, (String) obj, null, (String) obj2, hashMap), new Object[0]);
                        }
                        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, str, obj, null, obj2, hashMap);
                    } catch (Throwable th2) {
                        th = th2;
                        ALog.d("accs.BindAppStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, null, str3, hashMap) + " " + th.toString(), new Object[0]);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str3 = null;
                    ALog.d("accs.BindAppStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, null, str3, hashMap) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
                str3 = null;
                str2 = null;
                ALog.d("accs.BindAppStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, null, str3, hashMap) + " " + th.toString(), new Object[0]);
            }
        }
    }
}
