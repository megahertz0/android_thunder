package com.xunlei.common.pay;

public final class XLPayErrorCode {
    public static final int XLP_ALI_CAN_ERROR = 101;
    public static final int XLP_ALI_DISCONTRACT_ERROR = 103;
    public static final int XLP_ALI_NOT_INSTALL = 102;
    public static final int XLP_ALI_PAY_ERROR = 100;
    public static final int XLP_ALI_QCONTRACT_ERROR = 104;
    public static final int XLP_ALI_QCONTRACT_EXIST = 105;
    public static final int XLP_BD_CANCLE = 151;
    public static final int XLP_BD_LOGIN_ERROR = 154;
    public static final int XLP_BD_LOGIN_OUT = 156;
    public static final int XLP_BD_NO_SUPPORT = 152;
    public static final int XLP_BD_PAYING = 150;
    public static final int XLP_BD_PAY_ERROR = 155;
    public static final int XLP_BD_TOKEN_INVALID = 153;
    public static final int XLP_CANCLE_ERROR = 1000001;
    public static final int XLP_COMMON_ERROR = 200;
    public static final int XLP_COMMON_INVALID_PARAM = 201;
    public static final int XLP_CONTRACT_EXIST = 1000005;
    public static final int XLP_CONTRACT_QUERY_ERROR = 1000004;
    public static final int XLP_GATE_API_ERROR = 1004;
    public static final int XLP_GATE_CFG_ERROR = 1006;
    public static final int XLP_GATE_GEN_ERROR = 1002;
    public static final int XLP_GATE_IDIVALID_ERROR = 1012;
    public static final int XLP_GATE_IVALID_ERROR = 1009;
    public static final int XLP_GATE_JXREQ_ERROR = 1010;
    public static final int XLP_GATE_JX_ERROR = 1007;
    public static final int XLP_GATE_NAME_ERROR = 1011;
    public static final int XLP_GATE_NOT_CONTRACT = 1919;
    public static final int XLP_GATE_PARAM_ERROR = 1001;
    public static final int XLP_GATE_RCFG_ERROR = 10005;
    public static final int XLP_GATE_SIGN_ERROR = 1003;
    public static final int XLP_GATE_UNKOWN_ERROR = 99;
    public static final int XLP_GATE_ZBIVALID_ERROR = 1008;
    public static final int XLP_GET_ORDER_ERROR = 1000003;
    public static final int XLP_GET_PRICE_ERROR = 1000002;
    public static final int XLP_NET_GATE_ERROR_BASE = 1000000;
    public static final int XLP_ORDER_ABNORMAL_ACC = 2;
    public static final int XLP_ORDER_ALREADY_DVIP = 7;
    public static final int XLP_ORDER_BIND_ACC = 401;
    public static final int XLP_ORDER_BIND_WX_ERROR = 19;
    public static final int XLP_ORDER_CANT_PAY = 116;
    public static final int XLP_ORDER_CANT_VIP = 8;
    public static final int XLP_ORDER_CARD_PROCESS_POST = 10091;
    public static final int XLP_ORDER_CARD_UNSURPPORT = 82019;
    public static final int XLP_ORDER_CHANGE_CARD = 10083;
    public static final int XLP_ORDER_ENCODE_EEROR = 82009;
    public static final int XLP_ORDER_FAILED_DUPLICATE = 10076;
    public static final int XLP_ORDER_FAILED_POST = 10076;
    public static final int XLP_ORDER_GATEWAY_ERROR = 9;
    public static final int XLP_ORDER_INVALID_ACC = 16;
    public static final int XLP_ORDER_INVALID_BALANCE = 10131;
    public static final int XLP_ORDER_INVALID_BALANCE_ORDER = 81006;
    public static final int XLP_ORDER_INVALID_CARD = 81000;
    public static final int XLP_ORDER_INVALID_CARD_PSW = 81007;
    public static final int XLP_ORDER_INVALID_FORMAT = 10014;
    public static final int XLP_ORDER_INVALID_PARAM = 300;
    public static final int XLP_ORDER_INVALID_PARTNER = 14;
    public static final int XLP_ORDER_INVALID_REQ = 700;
    public static final int XLP_ORDER_INVALID_TOKEN = 400;
    public static final int XLP_ORDER_INVALID_UPGRADE = 17;
    public static final int XLP_ORDER_IVALID_USER = 1;
    public static final int XLP_ORDER_MD5_ERROR = 11111;
    public static final int XLP_ORDER_NONEED_UPGRADE = 18;
    public static final int XLP_ORDER_NOT_ALLOW_DVIP = 6;
    public static final int XLP_ORDER_OP_SUCCEED = 200;
    public static final int XLP_ORDER_ORDER_ERROR = 5;
    public static final int XLP_ORDER_ORDER_EXIST = 12;
    public static final int XLP_ORDER_ORDER_NOT_EIXST = 91;
    public static final int XLP_ORDER_ORDER_PARAM_ERROR = 13;
    public static final int XLP_ORDER_ORDER_PROCESS = 92;
    public static final int XLP_ORDER_ORDER_PROCESS_POST = 10120;
    public static final int XLP_ORDER_PARAM_ERROR = 3;
    public static final int XLP_ORDER_PRICE_ERROR = 10119;
    public static final int XLP_ORDER_PRICE_VER_ERROR = 4;
    public static final int XLP_ORDER_PROCESSING = 10120;
    public static final int XLP_ORDER_SERVER_ERROR = 10;
    public static final int XLP_ORDER_SING_ERROR = 11;
    public static final int XLP_ORDER_SP_CHANNEL_CLOSE = 10029;
    public static final int XLP_ORDER_SP_PRICE_CLOSE = 10030;
    public static final int XLP_ORDER_SP_PROCESSING = 40000;
    public static final int XLP_ORDER_SP_UPGRADE = 10124;
    public static final int XLP_ORDER_SUCCEED_DUPLICATE = 10016;
    public static final int XLP_ORDER_SUCCEED_POST = 10016;
    public static final int XLP_ORDER_SYS_ERROR = 500;
    public static final int XLP_ORDER_UNKNOWN_EEOR = 99;
    public static final int XLP_ORDER_UNMATCH = 2;
    public static final int XLP_ORDER_UNSURPPORT = 115;
    public static final int XLP_ORDER_USED_ERROR = 10082;
    public static final int XLP_SUCCESS = 0;
    public static final int XLP_UNKNOWN_ERROR = -1;
    public static final int XLP_WX_AUT_ERROR = 52;
    public static final int XLP_WX_CAN_ERROR = 51;
    public static final int XLP_WX_DISCONTRACT_ERROR = 56;
    public static final int XLP_WX_NOT_INSTALL = 54;
    public static final int XLP_WX_PAY_ERROR = 53;
    public static final int XLP_WX_QCONTRACT_ERROR = 57;
    public static final int XLP_WX_QCONTRACT_EXIST = 55;
    public static final int XLP_WX_REQ_ERROR = 50;

    public static String getErrorDesc(int i) {
        String str = "\u672a\u77e5\u9519\u8bef";
        if (i == 0) {
            return "\u6210\u529f";
        }
        if (i == -1) {
            return "\u672a\u77e5\u9519\u8bef";
        }
        if (i == 1000001) {
            return "\u7528\u6237\u53d6\u6d88";
        }
        if (i == 1000002) {
            return "\u83b7\u53d6\u4ef7\u683c\u9519\u8bef";
        }
        if (i == 1000003) {
            return "\u83b7\u53d6\u8ba2\u5355\u9519\u8bef";
        }
        if (i == 1000004) {
            return "\u67e5\u8be2\u7b7e\u7ea6\u5173\u7cfb\u5931\u8d25";
        }
        if (i == 1000005) {
            return "\u5df2\u6709\u7b7e\u7ea6\u5173\u7cfb";
        }
        if (i == 50) {
            return "\u8bf7\u6c42\u5fae\u4fe1\u652f\u4ed8\u5931\u8d25";
        }
        if (i == 51) {
            return "\u5fae\u4fe1\u7528\u6237\u53d6\u6d88\u652f\u4ed8";
        }
        if (i == 52) {
            return "\u5fae\u4fe1\u6388\u6743\u88ab\u62d2\u7edd";
        }
        if (i == 53) {
            return "\u5fae\u4fe1\u652f\u4ed8\u5931\u8d25";
        }
        if (i == 54) {
            return "\u5fae\u4fe1\u672a\u5b89\u88c5";
        }
        if (i == 55) {
            return "\u5fae\u4fe1\u5305\u6708\u5df2\u7ecf\u7b7e\u7ea6";
        }
        if (i == 56) {
            return "\u5fae\u4fe1\u5305\u6708\u89e3\u9664\u7b7e\u7ea6\u5931\u8d25";
        }
        if (i == 57) {
            return "\u5fae\u4fe1\u5305\u6708\u67e5\u8be2\u7b7e\u7ea6\u5931\u8d25";
        }
        if (i == 100) {
            return "\u652f\u4ed8\u5931\u8d25";
        }
        if (i == 101) {
            return "\u652f\u4ed8\u53d6\u6d88";
        }
        if (i == 102) {
            return "\u652f\u4ed8\u5b9d\u94b1\u5305\u672a\u5b89\u88c5";
        }
        if (i == 103) {
            return "\u652f\u4ed8\u5305\u5305\u6708\u89e3\u9664\u7b7e\u7ea6\u5931\u8d25";
        }
        if (i == 104) {
            return "\u652f\u4ed8\u5305\u5305\u6708\u67e5\u8be2\u7b7e\u7ea6\u5931\u8d25";
        }
        if (i == 105) {
            return "\u652f\u4ed8\u5305\u5305\u6708\u5df2\u7ecf\u7b7e\u7ea6";
        }
        if (i == 150) {
            return "\u6b63\u5728\u652f\u4ed8";
        }
        if (i == 151) {
            return "\u652f\u4ed8\u53d6\u6d88";
        }
        if (i == 152) {
            return "\u4e0d\u652f\u6301\u8be5\u79cd\u652f\u4ed8\u65b9\u5f0f";
        }
        if (i == 153) {
            return "\u65e0\u6548\u767b\u5f55\u6001";
        }
        if (i == 154) {
            return "\u767b\u5f55\u9519\u8bef";
        }
        if (i == 155) {
            return "\u652f\u4ed8\u5931\u8d25";
        }
        if (i == 156) {
            return "\u9000\u51fa\u767b\u5f55";
        }
        if (i == 200) {
            return "\u901a\u7528\u9519\u8bef";
        }
        if (i == 201) {
            return "\u53c2\u6570\u9519\u8bef";
        }
        if (i == 99) {
            return "\u7f51\u5173\u672a\u77e5\u9519\u8bef";
        }
        if (i == 1001) {
            return "\u8bf7\u6c42\u53c2\u6570\u4e0d\u5168\u6216\u8005\u975e\u6cd5";
        }
        if (i == 1002) {
            return "\u751f\u6210\u8ba2\u5355\u8bf7\u6c42\u62a5\u6587\u5931\u8d25";
        }
        if (i == 1003) {
            return "\u7b7e\u540d\u6821\u9a8c\u5931\u8d25";
        }
        if (i == 1004) {
            return "\u63a5\u53e3\u8c03\u7528\u5931\u8d25";
        }
        if (i == 10005) {
            return "\u8bfb\u53d6\u914d\u7f6e\u6587\u4ef6\u5931\u8d25";
        }
        if (i == 1006) {
            return "\u914d\u7f6e\u6587\u4ef6\u51fa\u9519";
        }
        if (i == 1007) {
            return "\u89e3\u6790\u63a5\u53e3\u54cd\u5e94\u5931\u8d25";
        }
        if (i == 1008) {
            return "\u603b\u8868\u8ba2\u5355\u4e0d\u5b58\u5728";
        }
        if (i == 1009) {
            return "\u8ba2\u5355\u4e0d\u5b58\u5728";
        }
        if (i == 1010) {
            return "\u89e3\u6790\u8bf7\u6c42\u5931\u8d25";
        }
        if (i == 1011) {
            return "\u5546\u54c1\u540d\u79f0\u8fc7\u957f";
        }
        if (i == 1012) {
            return "\u672a\u914d\u7f6e\u7684\u516c\u4f17\u5e10\u53f7ID";
        }
        if (i == 1919) {
            return "\u5305\u6708\u81ea\u52a8\u6263\u8d39\u672a\u7b7e\u7ea6";
        }
        if (i == 1) {
            return "\u8fc5\u96f7\u7528\u6237\u4e0d\u5b58\u5728";
        }
        if (i == 2) {
            return "\u67e5\u8be2\u8fc5\u96f7\u5e10\u53f7\u5f02\u5e38";
        }
        if (i == 3) {
            return "\u53c2\u6570\u4e0d\u6b63\u786e\uff0c\u8bf7\u91cd\u8bd5";
        }
        if (i == 4) {
            return "\u4ef7\u683c\u9a8c\u8bc1\u5931\u8d25";
        }
        if (i == 5) {
            return "\u8ba2\u5355\u9519\u8bef\uff0c\u8bf7\u91cd\u8bd5";
        }
        if (i == 6) {
            return "\u5f53\u524d\u5e10\u53f7\u4e0d\u5141\u8bb8\u8d2d\u4e70\u94bb\u77f3\u4f1a\u5458";
        }
        if (i == 7) {
            return "\u5f53\u524d\u5e10\u53f7\u5df2\u7ecf\u662f\u94bb\u77f3\u4f1a\u5458\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55";
        }
        if (i == 8) {
            return "\u624b\u673a\u5305\u6708/\u5305\u5e74\u4f1a\u5458\u7528\u6237\uff0c\u6682\u4e0d\u80fd\u8865\u9f50\u8d2d\u4e70\u4f1a\u5458";
        }
        if (i == 9) {
            return "\u652f\u4ed8\u7f51\u5173\u5f02\u5e38";
        }
        if (i == 10) {
            return "\u670d\u52a1\u5668\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5";
        }
        if (i == 11) {
            return "\u7b7e\u540d\u5b57\u7b26\u4e32\u4e0d\u6b63\u786e";
        }
        if (i == 12) {
            return "\u8ba2\u5355\u53f7\u5df2\u5b58\u5728";
        }
        if (i == 13) {
            return "\u8ba2\u5355\u53c2\u6570\u503c\u4e0d\u7b26\u5408";
        }
        if (i == 14) {
            return "\u65e0\u6548\u5546\u6237\u53f7";
        }
        if (i == 16) {
            return "\u8fc5\u96f7\u8d26\u6237\u4e0d\u5b58\u5728";
        }
        if (i == 17) {
            return "\u65e0\u53ef\u5347\u7ea7\u7684\u8ba2\u5355";
        }
        if (i == 18) {
            return "\u767d\u91d1\u7528\u6237\u53ef\u9886\u53d6\u94bb\u77f3\u4f53\u9a8c\uff0c\u65e0\u9700\u5347\u7ea7";
        }
        if (i == 19) {
            return "\u83b7\u53d6\u5fae\u4fe1\u7ed1\u5b9a\u5173\u7cfb\u5931\u8d25\uff0c\u8bf7\u56de\u5230\u516c\u4f17\u5e10\u53f7\u91cd\u65b0\u7ed1\u5b9a\u8fc5\u96f7\u5e10\u53f7\u3002";
        }
        if (i == 91) {
            return "\u67e5\u8be2\u7684\u652f\u4ed8\u8ba2\u5355\u4e0d\u5b58\u5728";
        }
        if (i == 92) {
            return "\u8ba2\u5355\u6b63\u5728\u5904\u7406\u4e2d";
        }
        if (i == 99) {
            return "\u672a\u77e5\u9519\u8bef";
        }
        if (i == 115) {
            return "\u4f1a\u5458\u5145\u503c\u6682\u4e0d\u652f\u6301\u4f01\u4e1a/\u94bb\u77f3\u5b50\u5e10\u53f7";
        }
        if (i == 116) {
            return "\u6682\u505c\u4f1a\u5458\u4e0d\u80fd\u652f\u4ed8\uff0c\u8bf7\u5148\u5230\u4f1a\u5458\u5b98\u7f51\u6fc0\u6d3b\u60a8\u7684\u4f1a\u5458\u3002";
        }
        if (i == 200) {
            return "\u64cd\u4f5c\u6210\u529f";
        }
        if (i == 300) {
            return "\u53c2\u6570\u9519\u8bef";
        }
        if (i == 400) {
            return "\u8bbf\u95eeToken\u65e0\u6548";
        }
        if (i == 401) {
            return "\u5f88\u62b1\u6b49\uff0c\u5f53\u524d\u5e10\u53f7\u7c7b\u578b\u4e0d\u652f\u6301\u652f\u4ed8\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a\u8fc5\u96f7\u5e10\u53f7\u3002";
        }
        if (i == 500) {
            return "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002";
        }
        if (i == 700) {
            return "\u65e0\u6548\u8bf7\u6c42";
        }
        if (i == 82009) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0c\u5361\u53f7\u5bc6\u7801\u52a0\u89e3\u5bc6\u5931\u8d25";
        }
        if (i == 10016) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0c\u8be5\u8ba2\u5355\u652f\u4ed8\u5df2\u6210\u529f\uff0c\u4e0d\u80fd\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10076) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0c\u8be5\u8ba2\u5355\u652f\u4ed8\u5df2\u5931\u8d25\uff0c\u4e0d\u80fd\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10120) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0c\u8be5\u8ba2\u5355\u6b63\u5728\u5904\u7406\u4e2d";
        }
        if (i == 10014) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0c\u91d1\u989d\u683c\u5f0f\u5f02\u5e38";
        }
        if (i == 11111) {
            return "\u652f\u4ed8\u8bf7\u6c42\u5931\u8d25\uff0cMD5\u9a8c\u8bc1\u5931\u8d25\u6216\u8ba2\u5355\u53c2\u6570\u6709\u8bef";
        }
        if (i == 2) {
            return "\u652f\u4ed8\u5931\u8d25\uff01\u60a8\u9009\u62e9\u7684\u5145\u503c\u5361\u9762\u503c\u4e0e\u5b9e\u9645\u9762\u503c\u4e0d\u7b26\u3002";
        }
        if (i == 10016) {
            return "\u8be5\u8ba2\u5355\u652f\u4ed8\u5df2\u6210\u529f\uff0c\u4e0d\u80fd\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10029) {
            return "\u8fd0\u8425\u5546\u7cfb\u7edf\u7ef4\u62a4\uff0c\u652f\u4ed8\u901a\u9053\u6682\u65f6\u5173\u95ed";
        }
        if (i == 10030) {
            return "\u8fd0\u8425\u5546\u7cfb\u7edf\u7ef4\u62a4\uff0c\u8be5\u9762\u503c\u6682\u65f6\u5173\u95ed";
        }
        if (i == 10076) {
            return "\u8be5\u8ba2\u5355\u652f\u4ed8\u5df2\u5931\u8d25\uff0c\u4e0d\u80fd\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10082) {
            return "\u8be5\u5361\u5df2\u88ab\u4f7f\u7528\uff0c\u8bf7\u66f4\u6362\u5176\u4ed6\u5145\u503c\u5361\u652f\u4ed8";
        }
        if (i == 10083) {
            return "\u5f88\u62b1\u6b49\uff01\u8be5\u5361\u5df2\u8fde\u7eed\u4e8c\u6b21\u652f\u4ed8\u4e0d\u6210\u529f\uff0c\u8bf7\u66f4\u6362\u5176\u4ed6\u5145\u503c\u5361\u652f\u4ed8\u3002";
        }
        if (i == 10091) {
            return "\u8be5\u5361\u6b63\u5728\u5904\u7406\u4e2d\uff0c\u8bf7\u4e0d\u8981\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10119) {
            return "\u5145\u503c\u5361\u9762\u989d\u9009\u62e9\u9519\u8bef";
        }
        if (i == 10120) {
            return "\u8be5\u8ba2\u5355\u6b63\u5728\u5904\u7406\u4e2d\uff0c\u4e0d\u80fd\u91cd\u590d\u63d0\u4ea4";
        }
        if (i == 10124) {
            return "\u7531\u4e8e\u8fd0\u8425\u5546\u7cfb\u7edf\u4e34\u65f6\u7ef4\u62a4\uff0c\u8be5\u7701\u5145\u503c\u5361\u6682\u65f6\u65e0\u6cd5\u652f\u4ed8\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002";
        }
        if (i == 10131) {
            return "\u4f59\u989d\u5361\u4f59\u989d\u4e0d\u8db3";
        }
        if (i == 81000) {
            return "\u8be5\u5361\u5df2\u5931\u6548\uff0c\u8bf7\u66f4\u6362\u5176\u4ed6\u5145\u503c\u5361\u652f\u4ed8";
        }
        if (i == 81006) {
            return "\u5145\u503c\u5361\u91d1\u989d\u4e0d\u8db3\u4ee5\u652f\u4ed8\u8ba2\u5355";
        }
        if (i == 81007) {
            return "\u65e0\u6548\u7684\u5361\u53f7\u5bc6\u7801";
        }
        if (i == 82019) {
            return "\u6682\u4e0d\u652f\u6301\u8be5\u5361\u652f\u4ed8";
        }
        return i == 40000 ? "\u8fd0\u8425\u5546\u6b63\u5728\u5904\u7406\u4e2d" : str;
    }
}
