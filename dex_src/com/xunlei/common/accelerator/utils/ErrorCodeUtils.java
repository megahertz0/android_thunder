package com.xunlei.common.accelerator.utils;

import android.text.TextUtils;

public final class ErrorCodeUtils {
    public static final int XLA_ACCOUNT_CHECK_SESSION_402 = 717;
    public static final int XLA_ACCOUNT_CHECK_SESSION_403 = 718;
    public static final int XLA_ACCOUNT_CHECK_SESSION_FAILED = 715;
    public static final int XLA_ACCOUNT_SDK_ERROR = 9;
    public static final int XLA_ACCOUNT_TIMEOUT = 714;
    public static final int XLA_ALLOC_ERROR = 501;
    public static final int XLA_ALREADY_SPEEDUP = 719;
    public static final int XLA_ALREADY_SPEEDUP_IN_ANOTHER_NETWORK = 720;
    public static final int XLA_ALREADY_UPGRADED = 812;
    public static final int XLA_AUTOTRY_OUT_OF_FREQUENCY = 721;
    public static final int XLA_DEVICE_KICKED = 708;
    public static final int XLA_ENCODE_ERR = 4;
    public static final int XLA_FSM_NOT_ENOUGH = 710;
    public static final int XLA_HTTP_ERR = 2;
    public static final int XLA_INTERNAL_ERROR = 500;
    public static final int XLA_IP_UNLEGAL_ERROR = 520;
    public static final int XLA_IS_QUERYING_PORTAL = 8;
    public static final int XLA_JSON_ERR = 5;
    public static final int XLA_KEEP_ALIVE_ERR = 6;
    public static final int XLA_MOBILE_NETWORK = 11;
    public static final int XLA_NET_ERR = 3;
    public static final int XLA_NORMAL_USER_BUSY_TIME = 709;
    public static final int XLA_NOT_EXIST_CHANNEL = 513;
    public static final int XLA_NO_NETWORK = 10;
    public static final int XLA_OPERATE_DIAL_ACCT_NULL = 514;
    public static final int XLA_PARAMETER_ERROR = 502;
    public static final int XLA_PAY_SUCCESS = -1000;
    public static final int XLA_REDIS_ERROR = 504;
    public static final int XLA_REPEATED_PACKAGE = 711;
    public static final int XLA_RESP_TRY_CONTACT_ERROR = 705;
    public static final int XLA_RESP_TRY_FINISHED = 704;
    public static final int XLA_SEND_TO_PROXY_FAILED = 707;
    public static final int XLA_SERVER_CONFIGURE_ERROR = 521;
    public static final int XLA_STATUS_LOADING = 2222;
    public static final int XLA_SUCCESS = 0;
    public static final int XLA_TEL_PROXY_TIMEOUT = 712;
    public static final int XLA_TIMEOUT_ERROR = 503;
    public static final int XLA_TRY_COUNT_NOT_ENOUGH_ERROR = 508;
    public static final int XLA_TRY_SERVER_TIMEOUT = 716;
    public static final int XLA_TRY_TIME_OUT_ERR = 7;
    public static final int XLA_UID_UNLEGAL_ERROR = 518;
    public static final int XLA_UNINNIT_ERROR = 1;
    public static final int XLA_UNKNOWN_ERROR = 65535;
    public static final int XLA_UNKOWN = 722;
    public static final int XLA_VIPSERVER_ERROR = 505;
    public static final int XLA_VIP_TIMEOUT = 713;

    public static String getErrorDesc(int i) {
        String str = "\u672a\u77e5\u9519\u8bef";
        switch (i) {
            case XLA_PAY_SUCCESS:
                return "\u652f\u4ed8\u6210\u529f";
            case XLA_SUCCESS:
                return "\u6210\u529f";
            case XLA_UNINNIT_ERROR:
                return "\u672a\u521d\u59cb\u5316\u9519\u8bef";
            case XLA_HTTP_ERR:
                return "Http\u9519\u8bef";
            case XLA_NET_ERR:
                return "\u7f51\u7edc\u9519\u8bef";
            case XLA_ENCODE_ERR:
                return "ENCODE\u9519\u8bef";
            case XLA_JSON_ERR:
                return "JSON\u9519\u8bef";
            case XLA_KEEP_ALIVE_ERR:
                return "\u5fc3\u8df3\u9519\u8bef";
            case XLA_TRY_TIME_OUT_ERR:
                return "\u8bd5\u7528\u8d85\u65f6\u9519\u8bef";
            case XLA_IS_QUERYING_PORTAL:
                return "\u6b63\u5728\u67e5\u8be2portal";
            case XLA_ACCOUNT_SDK_ERROR:
                return "\u5e10\u53f7sdk\u9519\u8bef";
            case XLA_NO_NETWORK:
                return "\u5f53\u524d\u65e0\u7f51\u7edc\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u8fde\u63a5";
            case XLA_MOBILE_NETWORK:
                return "\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u8bf7\u5728wifi\u73af\u5883\u4e0b\u4f7f\u7528";
            case XLA_INTERNAL_ERROR:
                return "\u5185\u90e8\u670d\u52a1\u5668\u961f\u5217\u5df2\u6ee1";
            case XLA_PARAMETER_ERROR:
                return "\u53c2\u6570\u9519\u8bef";
            case XLA_TIMEOUT_ERROR:
                return "\u670d\u52a1\u5668\u8bf7\u6c42\u51fa\u9519";
            case XLA_TRY_COUNT_NOT_ENOUGH_ERROR:
                return "\u8bd5\u7528\u6b21\u6570\u5df2\u7528\u5b8c";
            case XLA_NOT_EXIST_CHANNEL:
                return "\u901a\u9053\u4e0d\u5b58\u5728\u65f6\u95f4\u5230\u6216\u8005\u7535\u4fe1\u4ee3\u7406\u670d\u52a1\u5668\u8fd4\u56de\u9519\u8bef\u72b6\u6001\u5185\u5b58\u8bb0\u5f55\u5df2\u88ab\u6e05\u9664";
            case XLA_OPERATE_DIAL_ACCT_NULL:
                return "\u7535\u4fe1\u8fd4\u56de\u5bbd\u5e26\u8d26\u53f7\u4e3a\u7a7a";
            case XLA_UID_UNLEGAL_ERROR:
                return "\u975e\u5408\u6cd5\u4f1a\u5458";
            case XLA_IP_UNLEGAL_ERROR:
                return "ip\u4e0d\u5728\u52a0\u901f\u8303\u56f4";
            case XLA_SERVER_CONFIGURE_ERROR:
                return "\u67e5\u627e\u63a5\u53e3\u670d\u52a1\u5668\u914d\u7f6e\u5931\u8d25";
            case XLA_RESP_TRY_FINISHED:
                return "\u5f53\u6b21\u8bd5\u7528\u65f6\u95f4\u7ed3\u675f";
            case XLA_RESP_TRY_CONTACT_ERROR:
                return "\u4e0e\u8bd5\u7528\u670d\u52a1\u5668\u4ea4\u4e92\u51fa\u9519";
            case XLA_SEND_TO_PROXY_FAILED:
                return "\u53d1\u9001\u8bf7\u6c42\u5230\u4ee3\u7406\u670d\u52a1\u5668";
            case XLA_DEVICE_KICKED:
                return "\u8bbe\u5907\u88ab\u8e22";
            case XLA_NORMAL_USER_BUSY_TIME:
                return "\u95f2\u65f6\u53d8\u6210\u5fd9\u65f6";
            case XLA_FSM_NOT_ENOUGH:
                return "\u5185\u90e8\u72b6\u6001\u673a\u8017\u5149";
            case XLA_REPEATED_PACKAGE:
                return "\u5ba2\u6237\u7aef\u8bf7\u6c42\u592a\u9891\u7e41,\u8bbe\u5907\u6709\u672a\u5904\u7406\u5b8c\u8bf7\u6c42";
            case XLA_TEL_PROXY_TIMEOUT:
                return "\u7535\u4fe1\u4ee3\u7406\u670d\u52a1\u5668\u8d85\u65f6";
            case XLA_VIP_TIMEOUT:
                return "vip\u670d\u52a1\u5668\u8d85\u65f6";
            case XLA_ACCOUNT_TIMEOUT:
                return "\u8d26\u53f7\u670d\u52a1\u5668\u8d85\u65f6";
            case XLA_ACCOUNT_CHECK_SESSION_FAILED:
                return "session\u9a8c\u8bc1\u5931\u8d25";
            case XLA_TRY_SERVER_TIMEOUT:
                return "\u8bd5\u7528\u670d\u52a1\u5668\u8d85\u65f6";
            case XLA_ACCOUNT_CHECK_SESSION_402:
                return "sessionid \u88ab\u8e22\u5ba2\u6237\u7aef\u9700\u63d0\u793a\u7528\u6237\u88ab\u8e22";
            case XLA_ACCOUNT_CHECK_SESSION_403:
                return "sessionid \u8d85\u65f6\u5ba2\u6237\u7aef\u9700\u81ea\u52a8\u91cd\u65b0\u767b\u5f55";
            case XLA_ALREADY_SPEEDUP:
                return "\u5df2\u63d0\u901f";
            case XLA_ALREADY_SPEEDUP_IN_ANOTHER_NETWORK:
                return "\u5df2\u5728\u5176\u5b83\u5bbd\u5e26\u7f51\u7edc\u63d0\u901f";
            case XLA_AUTOTRY_OUT_OF_FREQUENCY:
                return "\u81ea\u52a8\u8bd5\u7528\u8d85\u51fa\u6bcf\u5929\u7684\u8bd5\u7528\u9891\u6b21";
            case XLA_ALREADY_UPGRADED:
                return "\u5f53\u524d\u7f51\u7edc\u5df2\u88ab\u63d0\u901f";
            case XLA_STATUS_LOADING:
                return "\u6b63\u5728\u8bf7\u6c42\u670d\u52a1\u5668,\u8bf7\u7a0d\u5019...";
            default:
                return str;
        }
    }

    public static String parseLink(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return getErrorDesc(i);
        }
        if (!str.contains("@@")) {
            return str;
        }
        String[] split = str.split("@@");
        return split[0].contains(",") ? split[0].split(",")[0] : split[0];
    }

    public static String getChangeClientType(String str) {
        if (str.startsWith("pc-xlnetacc")) {
            return "PC\u5ba2\u6237\u7aef";
        }
        if (str.startsWith("android-swjsq")) {
            return "android\u5feb\u9e1f\u5ba2\u6237\u7aef";
        }
        if (str.startsWith("ios")) {
            return "ios\u5feb\u9e1f\u5ba2\u6237\u7aef";
        }
        if (str.startsWith("android-shouleiknplugin")) {
            return "android\u624b\u96f7\u5ba2\u6237\u7aef";
        }
        if (str.startsWith("pc-xlknplugin")) {
            return "\u8fc5\u96f77\u5feb\u9e1f\u63d2\u4ef6";
        }
        if (str.startsWith("pc-xlkngsplugin")) {
            return "\u8fc5\u96f77\u5149\u901f\u63d2\u4ef6";
        }
        if (str.startsWith("pc-xl9knplugin")) {
            return "\u8fc5\u96f79\u5feb\u9e1f\u63d2\u4ef6";
        }
        if (str.startsWith("router-xiaomi_xlknplugin")) {
            return "\u5c0f\u7c73\u8def\u7531\u63d2\u4ef6";
        }
        if (str.startsWith("router-dlsuite_xlknplugin")) {
            return "\u4e0b\u8f7d\u5b9d\u63d2\u4ef6";
        }
        if (str.startsWith("router-dcdn_xlknplugin")) {
            return "\u8d5a\u94b1\u5b9d\u63d2\u4ef6";
        }
        return str.startsWith("TV-xiaomi_xlknplugin") ? "TV\u5ba2\u6237\u7aef" : "\u5df2\u7ecf\u5728\u5176\u4ed6\u7aef\u52a0\u901f";
    }
}
