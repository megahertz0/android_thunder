package com.xunlei.downloadprovider.member.payment.bean;

import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;

public class PayConfigurationParam implements Serializable {
    public static final String defaultShowMonth = "12,6,3,1";
    private static final long serialVersionUID = 1000001;
    public String id;
    public String limit;
    public String limitdays;
    public String limitdays2;
    public String limittype;
    public String limittype2;
    public String member;
    public String mode;
    public String op;
    public String order;
    public String recommondMonth;
    public String recommondUpMonth;
    public String showMonth;
    public String tips;
    public int type;
    public String vastype;

    public int getMember() {
        return PayUtil.d(this.member).intValue();
    }

    public int getLimit() {
        return PayUtil.d(this.limit).intValue();
    }

    public int getLimittype() {
        return PayUtil.d(this.limittype).intValue();
    }

    public int getLimitdays() {
        return PayUtil.d(this.limitdays).intValue();
    }

    public int getLimittype2() {
        return PayUtil.d(this.limittype2).intValue();
    }

    public int getLimitdays2() {
        return PayUtil.d(this.limitdays2).intValue();
    }

    public int getOp() {
        return PayUtil.d(this.op).intValue();
    }

    public int getVastype() {
        return PayUtil.d(this.vastype).intValue();
    }

    public String getShowMonth() {
        return this.showMonth;
    }

    public int getRecommondMonth() {
        return PayUtil.d(this.recommondMonth).intValue();
    }

    public int getRecommondUpMonth() {
        return PayUtil.d(this.recommondUpMonth).intValue() == -1 ? 0 : PayUtil.d(this.recommondUpMonth).intValue();
    }

    public int getMode() {
        return PayUtil.d(this.mode).intValue();
    }

    public String getTips() {
        return this.tips;
    }

    public int getType() {
        return this.type;
    }

    public ArrayList<Integer> getMonthList(boolean z) {
        ArrayList<Integer> arrayList = new ArrayList();
        if (z) {
            arrayList.add(Integer.valueOf(XZBDevice.Fail));
            arrayList.add(Integer.valueOf(R.styleable.Toolbar_contentInsetEnd));
            arrayList.add(Integer.valueOf(XZBDevice.DOWNLOAD_LIST_FAILED));
            arrayList.add(Integer.valueOf(1));
        } else {
            String[] split = this.showMonth.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            if (split.length > 0) {
                for (String str : split) {
                    arrayList.add(PayUtil.d(str));
                }
            }
        }
        return arrayList;
    }

    public static PayConfigurationParam getDefaultMatchParams(int i, int i2, int i3) {
        return getDefaultMatchParams(i, i2, i3, MessageService.MSG_DB_NOTIFY_DISMISS);
    }

    public static PayConfigurationParam getDefaultMatchParams(int i, int i2, int i3, String str) {
        PayConfigurationParam payConfigurationParam = new PayConfigurationParam();
        payConfigurationParam.op = String.valueOf(i);
        payConfigurationParam.mode = String.valueOf(i3);
        payConfigurationParam.vastype = String.valueOf(i2);
        payConfigurationParam.showMonth = defaultShowMonth;
        payConfigurationParam.tips = a.d;
        if (i == 0) {
            payConfigurationParam.recommondMonth = str;
        } else {
            payConfigurationParam.recommondUpMonth = MessageService.MSG_DB_READY_REPORT;
        }
        return payConfigurationParam;
    }

    public static PayConfigurationParam getDefaultMatchParams(int i, PayConfigurationParam payConfigurationParam) {
        PayConfigurationParam payConfigurationParam2 = new PayConfigurationParam();
        payConfigurationParam2.op = payConfigurationParam.op;
        payConfigurationParam2.mode = payConfigurationParam.mode;
        payConfigurationParam2.vastype = String.valueOf(i);
        payConfigurationParam2.showMonth = payConfigurationParam.showMonth;
        payConfigurationParam2.tips = a.d;
        payConfigurationParam2.recommondMonth = payConfigurationParam.recommondMonth;
        payConfigurationParam2.recommondUpMonth = payConfigurationParam.recommondUpMonth;
        return payConfigurationParam2;
    }

    public OrderType getOrderType(int i) {
        return i == 1 ? OrderType.UPGRADE : OrderType.OPEN;
    }

    public String getTabTitle(int i) {
        String str = a.d;
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "\u666e\u901a\u4f1a\u5458";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "\u767d\u91d1\u4f1a\u5458";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "\u8d85\u7ea7\u4f1a\u5458";
            case 204:
                return "\u5feb\u9e1f\u4f1a\u5458";
            default:
                return str;
        }
    }
}
