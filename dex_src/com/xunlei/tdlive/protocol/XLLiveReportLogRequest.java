package com.xunlei.tdlive.protocol;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.util.f.f;

public class XLLiveReportLogRequest extends XLLiveRequest {
    private JsonCallBack mJsonCallBack;
    private String mMsg;
    private String mReportURL;

    public static class ReportLogResp extends XLLiveRespBase {
    }

    public XLLiveReportLogRequest(String str, String str2, String str3, JsonCallBack jsonCallBack) {
        super(str, str2);
        this.mJsonCallBack = jsonCallBack;
        this.mReportURL = str3;
    }

    public void send(String str) {
        this.mMsg = str;
        super.send(this.mJsonCallBack);
    }

    protected boolean useHttpPost() {
        return true;
    }

    protected void onAddBodyParams(f fVar) {
        fVar.b(SocializeProtocolConstants.PROTOCOL_KEY_DATA, this.mMsg);
    }

    protected String onGetURL() {
        return this.mReportURL;
    }

    protected Class<?> onGetObjectClass() {
        return ReportLogResp.class;
    }
}
