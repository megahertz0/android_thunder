package com.xunlei.downloadprovider.member.payment.external;

import android.text.TextUtils;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.pay.param.XLAliPayContractParam;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxContractParam;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.AnonymousClass_1;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.android.spdy.SpdyAgent;

public abstract class PayUtil {
    public static boolean a;
    public static boolean b;

    public enum OrderType {
        OPEN,
        RENEW,
        UPGRADE;

        static {
            OPEN = new com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType("OPEN", 0);
            RENEW = new com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType("RENEW", 1);
            UPGRADE = new com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType("UPGRADE", 2);
            a = new com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType[]{OPEN, RENEW, UPGRADE};
        }

        public final int toXLSdkOrderType() {
            return this == UPGRADE ? 1 : 0;
        }

        public final String getText() {
            return getText(false);
        }

        public final String getText(boolean z) {
            String str = a.d;
            switch (AnonymousClass_1.a[ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (!z) {
                        return "\u5f00\u901a";
                    }
                    return "\u5347\u7ea7";
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return "\u5347\u7ea7";
                default:
                    return str;
            }
        }
    }

    static {
        a = false;
        b = false;
    }

    public static String a(int i) {
        String str = "\u4f1a\u5458";
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

    public static String a(OrderType orderType, int i) {
        String text = orderType.getText();
        return text + a(i);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return a.d;
        }
        if (str.length() < 6) {
            return str;
        }
        String substring = str.substring(0, XZBDevice.DOWNLOAD_LIST_ALL);
        String substring2 = str.substring(XZBDevice.DOWNLOAD_LIST_ALL, R.styleable.Toolbar_contentInsetEnd);
        return substring + SocializeConstants.OP_DIVIDER_MINUS + substring2 + SocializeConstants.OP_DIVIDER_MINUS + str.substring(R.styleable.Toolbar_contentInsetEnd);
    }

    public static String a(String str, String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BaseJsInterface.JS_PREFIX);
        stringBuilder.append(str);
        stringBuilder.append('(');
        for (int i = 0; i <= 0; i++) {
            stringBuilder.append("'");
            stringBuilder.append(strArr[0]);
            stringBuilder.append("'");
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void a() {
        DownloadService.h();
        new StringBuilder("---PayUtil---sendMessageToService ---").append(Thread.currentThread().getId());
        j.a();
        if (!LoginHelper.c()) {
            LoginHelper.a().a(false);
        }
    }

    public static String a(float f) {
        int i = (int) f;
        return ((float) i) != f ? new DecimalFormat("0.0").format((double) f) : String.valueOf(i);
    }

    public static long b(String str) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        if (TextUtils.isEmpty(str) || !Pattern.compile("[0-9]*").matcher(str).matches()) {
            return -1;
        }
        instance.set(Integer.parseInt(str.substring(0, XZBDevice.DOWNLOAD_LIST_ALL)), Integer.parseInt(str.substring(XZBDevice.DOWNLOAD_LIST_ALL, R.styleable.Toolbar_contentInsetEnd)) - 1, Integer.parseInt(str.substring(R.styleable.Toolbar_contentInsetEnd, XZBDevice.Wait)));
        long timeInMillis = instance.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        long j = timeInMillis % 86400000;
        timeInMillis /= 86400000;
        return j > 0 ? timeInMillis + 1 : timeInMillis;
    }

    public static boolean a(int i, int i2, int i3) {
        return Math.max(i2, i) == Math.min(i, i3);
    }

    public static boolean a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return instance.get(1) == instance2.get(1) && instance.get(XZBDevice.DOWNLOAD_LIST_RECYCLE) == instance2.get(XZBDevice.DOWNLOAD_LIST_RECYCLE) && instance.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED) == instance2.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
    }

    public static Date c(String str) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer d(String str) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception e) {
            parseInt = -1;
        }
        return Integer.valueOf(parseInt);
    }

    public static float e(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 1.0f;
        }
    }

    public static XLAliPayContractParam b(int i, int i2, int i3) {
        j a = j.a();
        XLAliPayContractParam xLAliPayContractParam = new XLAliPayContractParam();
        xLAliPayContractParam.mMonth = i;
        xLAliPayContractParam.mOrderType = i3;
        xLAliPayContractParam.mSource = "shoulei_android";
        xLAliPayContractParam.mVasType = i2;
        xLAliPayContractParam.mUserId = (int) a.a.j;
        xLAliPayContractParam.mSessionId = a.a.k;
        return xLAliPayContractParam;
    }

    public static XLWxContractParam c(int i, int i2, int i3) {
        j a = j.a();
        XLWxContractParam xLWxContractParam = new XLWxContractParam();
        xLWxContractParam.mVasType = i2;
        xLWxContractParam.mOrderType = i3;
        xLWxContractParam.mMonth = i;
        xLWxContractParam.mUserId = (int) a.a.j;
        xLWxContractParam.mSessionId = a.a.k;
        xLWxContractParam.mSource = "shoulei_android";
        xLWxContractParam.mWxAppId = "wx3e6556568beeebdd";
        return xLWxContractParam;
    }

    public static XLAlipayParam a(XLPayParam xLPayParam) {
        XLAlipayParam xLAlipayParam = new XLAlipayParam();
        xLAlipayParam.mMonth = xLPayParam.mMonth;
        xLAlipayParam.mReferFrom = xLPayParam.mReferFrom;
        xLAlipayParam.mOrderType = xLPayParam.mOrderType;
        xLAlipayParam.mSource = "shoulei_android";
        xLAlipayParam.mVasType = xLPayParam.mVasType;
        xLAlipayParam.mParamExt1 = xLPayParam.mParamExt1;
        return xLAlipayParam;
    }
}
