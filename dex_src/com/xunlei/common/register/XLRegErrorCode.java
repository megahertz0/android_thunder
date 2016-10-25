package com.xunlei.common.register;

public final class XLRegErrorCode {
    public static final int REG_ACCOUNT_EIXST = 704;
    public static final int REG_API_ERROR = 500;
    public static final int REG_EXIST = 201;
    public static final int REG_FORMAT_ERR_PARAM = 301;
    public static final int REG_FROM_BLACK = 402;
    public static final int REG_INVALID_CMD = 407;
    public static final int REG_INVALID_LOGIN = 404;
    public static final int REG_INVALID_PHONE = 700;
    public static final int REG_INVALID_SIGN = 408;
    public static final int REG_INVALID_VERIFY = 600;
    public static final int REG_INVALID_VERIFY_1 = 601;
    public static final int REG_IP_ABNORMAL = 403;
    public static final int REG_IP_CONTROL = 400;
    public static final int REG_JSON_ERROR = -2;
    public static final int REG_MAIL_EIXST = 702;
    public static final int REG_MODIFY_ERROR = 405;
    public static final int REG_MODIFY_TIMEOUT = 406;
    public static final int REG_NEED_VERIFY = 409;
    public static final int REG_NET_ERROR = -1;
    public static final int REG_NOT_ENOUGH_PARAM = 300;
    public static final int REG_PHONE_EIXST = 703;
    public static final int REG_SEND_SMS_FREQ = 401;
    public static final int REG_SIMPLE_PSW = 701;
    public static final int REG_SUCCEED = 200;

    public static String getErrorDesc(int i) {
        String str = "\u672a\u77e5\u9519\u8bef";
        if (i == 200) {
            return "\u6210\u529f";
        }
        if (i == 201) {
            return "\u5df2\u7ecf\u6ce8\u518c\u8fc7\u4e86\uff0c\u53ef\u4ee5\u76f4\u63a5\u767b\u5f55";
        }
        if (i == 300) {
            return "\u7f3a\u5c11\u53c2\u6570";
        }
        if (i == 301) {
            return "\u53c2\u6570\u683c\u5f0f\u6709\u8bef";
        }
        if (i == 400) {
            return "ip \u88ab\u8bbf\u95ee\u63a7\u5236\u673a\u5236\u5c4f\u853d";
        }
        if (i == 401) {
            return "\u540c\u4e2a\u624b\u673a\u53d1\u77ed\u4fe1\u8fc7\u4e8e\u9891\u7e41";
        }
        if (i == 402) {
            return "from \u4e0d\u5728\u767d\u540d\u5355\u5185";
        }
        if (i == 403) {
            return "\u6ce8\u518c\u7f51\u5173\u8fd4\u56de 506(ip\u5f02\u5e38)";
        }
        if (i == 404) {
            return "\u767b\u5f55\u6001\u9a8c\u8bc1\u4e0d\u901a\u8fc7";
        }
        if (i == 405) {
            return "\u4fee\u6539\u521d\u59cb\u5bc6\u7801\u65f6\u67e5\u4e0d\u5230\u5bf9\u5e94\u8bb0\u5f55";
        }
        if (i == 406) {
            return "\u4fee\u6539\u521d\u59cb\u5bc6\u7801\u65f6\u8d85\u65f6";
        }
        if (i == 407) {
            return "\u547d\u4ee4\u4e0d\u5b58\u5728";
        }
        if (i == 408) {
            return "\u7b7e\u540d\u6821\u9a8c\u4e0d\u901a\u8fc7";
        }
        if (i == 409) {
            return "\u9700\u8981\u62c9\u53d6\u9a8c\u8bc1\u7801";
        }
        if (i == 500) {
            return "\u63a5\u53e3\u5d29\u6e83";
        }
        if (i == 600) {
            return "\u9a8c\u8bc1\u7801\u9519\u8bef";
        }
        if (i == 601) {
            return "\u6ce8\u518c\u7f51\u5173\u8fd4\u56de 507(\u9a8c\u8bc1\u7801\u9519\u8bef)";
        }
        if (i == 700) {
            return "\u767b\u5f55\u624b\u673a\u4e0d\u5b58\u5728\uff0c\u4f46\u5b89\u5168\u624b\u673a\u5b58\u5728";
        }
        if (i == 701) {
            return "\u5bc6\u7801\u592a\u8fc7\u7b80\u5355";
        }
        if (i == 702) {
            return "\u90ae\u7bb1\u5df2\u88ab\u6ce8\u518c";
        }
        if (i == 703) {
            return "\u624b\u673a\u5df2\u88ab\u6ce8\u518c";
        }
        if (i == 704) {
            return "\u5e10\u53f7\u5df2\u88ab\u6ce8\u518c";
        }
        if (i == -1) {
            return "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u6ce8\u518c";
        }
        return i == -2 ? "json\u89e3\u6790\u9519\u8bef" : str;
    }
}
