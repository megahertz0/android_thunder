package com.xunlei.tdlive.protocol;

import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.util.f.f;

public class XLLiveFeedbackRequest extends XLLiveRequest {
    private String mContent;
    private String mPhone;
    private String mQQ;

    public static class FeedbackResp extends XLLiveRespBase {
    }

    public XLLiveFeedbackRequest(String str, String str2, String str3, String str4, String str5) {
        super(str, str2);
        this.mPhone = str4;
        this.mQQ = str3;
        this.mContent = str5;
    }

    protected boolean useHttpPost() {
        return true;
    }

    protected void onAddBodyParams(f fVar) {
        fVar.b("phone_num", this.mPhone);
        fVar.b("qq_num", this.mQQ);
        fVar.b(WeiXinShareContent.TYPE_TEXT, this.mContent);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=site&a=userfeedback";
    }

    protected Class<?> onGetObjectClass() {
        return FeedbackResp.class;
    }
}
