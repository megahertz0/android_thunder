package com.xunlei.downloadprovider.member.register.b;

import com.qq.e.comm.constants.ErrorCode.NetWorkError;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import com.taobao.accs.common.Constants;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;

// compiled from: XLRegisterErrorMsg.java
public final class a {
    public static String a(int i) {
        switch (i) {
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                return "\u767b\u5f55\u4fe1\u606f\u5f02\u5e38";
            case SniffingResourceGroup.PAGETYPE_NONE:
                return "\u65e0\u7f51\u7edc\u8fde\u63a5";
            case Impl.STATUS_SUCCESS:
                return "\u6ce8\u518c\u6210\u529f";
            case Constants.COMMAND_PING:
                return "\u5e10\u53f7\u5df2\u5b58\u5728";
            case XLErrorCode.OAUTH_PARAM_ERROR:
                return "\u8bf7\u5b8c\u6574\u586b\u5199\u4fe1\u606f";
            case Constants.COMMAND_STOP_FOR_ELECTION:
                return "\u975e\u6cd5\u8bf7\u6c42\uff0c\u8bf7\u91cd\u8bd5";
            case Impl.STATUS_BAD_REQUEST:
                return "IP\u5730\u5740\u8bbf\u95ee\u5f02\u5e38";
            case XLErrorCode.OAUTH_APP_NOT_EXIST:
                return "\u77ed\u4fe1\u53d1\u9001\u592a\u9891\u7e41\uff0c\u7a0d\u540e\u91cd\u8bd5";
            case NetWorkError.RETRY_TIME_NATIVE_ERROR:
                return "\u975e\u6cd5\u8bf7\u6c42\uff0c\u8bf7\u91cd\u8bd5";
            case XLErrorCode.VERIFY_ERR_INCORRECT:
                return "IP\u5730\u5740\u8bbf\u95ee\u5f02\u5e38";
            case XLErrorCode.VERIFY_ERR_DUPLICATE:
                return "\u624b\u673a\u9a8c\u8bc1\u5931\u6548\uff0c\u540e\u7eed\u53ef\u5728\u5b89\u5168\u4e2d\u5fc3\u4fee\u6539\u5bc6\u7801";
            case XLErrorCode.VERIFY_ERR_EXPIRE:
                return "\u5df2\u7ecf\u8bbe\u7f6e\u8fc7\u521d\u59cb\u5bc6\u7801";
            case Impl.STATUS_NOT_ACCEPTABLE:
                return "\u7f51\u7edc\u8d85\u65f6\uff0c\u540e\u7eed\u53ef\u5728\u5b89\u5168\u4e2d\u5fc3\u8bbe\u7f6e\u5bc6\u7801";
            case Impl.STATUS_PEER_NOT_FOUND_ERROR:
                return "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u7a0d\u540e\u91cd\u8bd5";
            case Impl.STATUS_LX_VIP_ERROR_START:
            case OtherError.NETWORK_TYPE_ERROR:
                return "\u9a8c\u8bc1\u7801\u9519\u8bef";
            case XLErrorCode.OAUTH_REG_CODE_ERROR:
                return "\u624b\u673a\u5df2\u7ecf\u5b58\u5728";
            case XLErrorCode.OAUTH_REG_TOKEN_ERROR:
                return "\u5bc6\u7801\u592a\u7b80\u5355";
            case XLErrorCode.OAUTH_REF_TOKEN_ERROR:
                return "\u90ae\u7bb1\u5df2\u88ab\u6ce8\u518c";
            case XLErrorCode.OAUTH_CID_INVALID:
                return "\u624b\u673a\u5df2\u88ab\u6ce8\u518c";
            default:
                return "\u7f51\u7edc\u4e0d\u7ed9\u529b\uff0c\u8bf7\u91cd\u8bd5";
        }
    }
}
