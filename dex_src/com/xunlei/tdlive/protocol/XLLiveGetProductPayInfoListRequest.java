package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetProductPayInfoListRequest.PayInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetProductPayInfoListRequest extends XLLiveRequest {

    public static final class PayInfo {
        public int add;
        public int coin;
        public String desc;
        public int money;
    }

    public static final class PayList extends XLLiveRespBase {
        public List<PayInfo> data;
    }

    public XLLiveGetProductPayInfoListRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=pay&a=listinfo&referfrom=android";
    }

    protected Class<?> onGetObjectClass() {
        return PayList.class;
    }
}
