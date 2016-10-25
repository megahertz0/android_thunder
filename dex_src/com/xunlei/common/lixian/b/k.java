package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;

public final class k extends XLLixianRequestBase {
    private String a;

    public k() {
        this.a = BuildConfig.VERSION_NAME;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final boolean execute() {
        if (this.a.isEmpty()) {
            return false;
        }
        a aVar = new a((short) 21);
        e eVar = new e();
        XLLX_INITDATA initData = super.getInitData();
        try {
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.a(this.a);
            eVar.flush();
            aVar.a(eVar.a());
            try {
                eVar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            try {
                e2.printStackTrace();
                try {
                    eVar.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    eVar.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        d.a().a(aVar.c(), new l(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainBtSeedFileContent(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (byte[]) objArr[3], objArr[4]);
    }
}
