package com.xunlei.downloadprovider.member.payment.external;

import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.member.payment.ui.PayOpenFragment;
import com.xunlei.downloadprovider.member.payment.ui.PayUpgradeFragment;
import com.xunlei.downloadprovider.member.payment.ui.x;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import org.android.agoo.message.MessageService;

// compiled from: PayPageUtil.java
public final class g {
    public j a;
    public boolean b;

    public g() {
        this.a = j.a();
        this.b = true;
    }

    public final List<x> a(List<x> list, PayConfigurationParam payConfigurationParam, int i) {
        if (payConfigurationParam != null) {
            Serializable serializable;
            Class cls;
            payConfigurationParam.type = i;
            boolean a = a();
            int vastype = payConfigurationParam.getVastype();
            String tabTitle = payConfigurationParam.getTabTitle(vastype);
            Serializable orderType = payConfigurationParam.getOrderType(payConfigurationParam.getOp());
            if (this.b || orderType != OrderType.UPGRADE) {
                serializable = orderType;
            } else {
                serializable = OrderType.OPEN;
            }
            if (TextUtils.isEmpty(payConfigurationParam.getShowMonth())) {
                payConfigurationParam.showMonth = PayConfigurationParam.defaultShowMonth;
            }
            if (TextUtils.isEmpty(payConfigurationParam.recommondMonth)) {
                payConfigurationParam.recommondMonth = String.valueOf(MessageService.MSG_DB_NOTIFY_DISMISS);
            }
            ArrayList monthList = payConfigurationParam.getMonthList(false);
            if (!monthList.contains(PayUtil.d(payConfigurationParam.recommondMonth)) && monthList.size() > 0) {
                Collections.sort(monthList);
                payConfigurationParam.recommondMonth = String.valueOf(monthList.get(monthList.size() - 1));
            }
            if (serializable == OrderType.UPGRADE) {
                cls = PayUpgradeFragment.class;
            } else {
                cls = PayOpenFragment.class;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("ExpiredToday", a);
            bundle.putInt("VasType", vastype);
            bundle.putSerializable("OrderType", orderType);
            bundle.putSerializable("RealOrderType", serializable);
            bundle.putSerializable("extra_pay_config", payConfigurationParam);
            list.add(new x(tabTitle, cls, bundle));
        }
        return list;
    }

    public final boolean a() {
        GregorianCalendar gregorianCalendar;
        j jVar = this.a;
        if (!TextUtils.isEmpty(jVar.f())) {
            String f = jVar.f();
            if (f.length() >= 6 && TextUtils.isDigitsOnly(f)) {
                gregorianCalendar = new GregorianCalendar(Integer.parseInt(f.substring(0, XZBDevice.DOWNLOAD_LIST_ALL)), Integer.parseInt(f.substring(XZBDevice.DOWNLOAD_LIST_ALL, R.styleable.Toolbar_contentInsetEnd)) - 1, Integer.parseInt(f.substring(R.styleable.Toolbar_contentInsetEnd)));
                return gregorianCalendar == null && gregorianCalendar.getTimeInMillis() < System.currentTimeMillis() + 86400000;
            }
        }
        gregorianCalendar = null;
        if (gregorianCalendar == null) {
            return false;
        }
    }
}
