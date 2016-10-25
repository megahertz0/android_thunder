package com.alipay.sdk.app;

import com.xunlei.common.member.XLErrorCode;

public enum i {
    SUCCEEDED(9000, "\u5904\u7406\u6210\u529f"),
    FAILED(4000, "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5"),
    CANCELED(6001, "\u7528\u6237\u53d6\u6d88"),
    NETWORK_ERROR(6002, "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38"),
    PARAMS_ERROR(4001, "\u53c2\u6570\u9519\u8bef"),
    DOUBLE_REQUEST(5000, "\u91cd\u590d\u8bf7\u6c42"),
    PAY_WAITTING(8000, "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d");
    public int h;
    public String i;

    static {
        String str = "\u5904\u7406\u6210\u529f";
        a = new i("SUCCEEDED", 0, 9000, "\u5904\u7406\u6210\u529f");
        str = "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5";
        b = new i("FAILED", 1, 4000, "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        str = "\u7528\u6237\u53d6\u6d88";
        c = new i("CANCELED", 2, 6001, "\u7528\u6237\u53d6\u6d88");
        str = "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38";
        d = new i("NETWORK_ERROR", 3, 6002, "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38");
        str = "\u53c2\u6570\u9519\u8bef";
        e = new i("PARAMS_ERROR", 4, 4001, "\u53c2\u6570\u9519\u8bef");
        String str2 = "\u91cd\u590d\u8bf7\u6c42";
        f = new i("DOUBLE_REQUEST", 5, 5000, "\u91cd\u590d\u8bf7\u6c42");
        str2 = "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d";
        g = new i("PAY_WAITTING", 6, 8000, "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d");
        j = new i[]{a, b, c, d, e, f, g};
    }

    private i(int i, String str) {
        this.h = i;
        this.i = str;
    }

    private void b(int i) {
        this.h = i;
    }

    private int a() {
        return this.h;
    }

    private void a(String str) {
        this.i = str;
    }

    private String b() {
        return this.i;
    }

    public static i a(int i) {
        switch (i) {
            case 4001:
                return e;
            case 5000:
                return f;
            case XLErrorCode.ALI_AUTH_USER_CANCLE:
                return c;
            case XLErrorCode.ALI_AUTH_NET_ERROR:
                return d;
            case 8000:
                return g;
            case 9000:
                return a;
            default:
                return b;
        }
    }
}
