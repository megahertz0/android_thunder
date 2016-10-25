package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_USERINFO;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import java.io.IOException;

public final class y extends XLLixianRequestBase {
    protected static boolean a(XLLX_USERINFO xllx_userinfo, byte[] bArr) {
        try {
            b bVar = new b(bArr);
            xllx_userinfo.Max_task_num = bVar.a();
            xllx_userinfo.Max_store = bVar.b();
            xllx_userinfo.Vip_store = bVar.b();
            xllx_userinfo.Buy_store = bVar.b();
            xllx_userinfo.Buy_num_task = (short) bVar.d();
            xllx_userinfo.Buy_num_connection = (short) bVar.d();
            xllx_userinfo.Buy_bandwidth = bVar.a();
            xllx_userinfo.Buy_task_live_time = bVar.a();
            xllx_userinfo.Expire_date = bVar.e();
            xllx_userinfo.Available_sapce = bVar.b();
            xllx_userinfo.Total_num = bVar.a();
            xllx_userinfo.History_task_total_num = bVar.a();
            xllx_userinfo.Suspending_num = bVar.a();
            xllx_userinfo.Downloading_num = bVar.a();
            xllx_userinfo.Waiting_num = bVar.a();
            xllx_userinfo.Complete_num = bVar.a();
            xllx_userinfo.Store_period = bVar.b();
            xllx_userinfo.Cookie = bVar.e();
            xllx_userinfo.Vip_level = (short) bVar.d();
            xllx_userinfo.User_type = (short) bVar.d();
            xllx_userinfo.Goldbean_num = bVar.b();
            xllx_userinfo.Silverbean_num = bVar.b();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean execute() {
        a aVar = new a((short) 16);
        try {
            e eVar = new e();
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.flush();
            aVar.a(eVar.a());
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.a().a(aVar.c(), new z(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainLixianUserInfo(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLX_USERINFO) objArr[3], objArr[4]);
    }
}
