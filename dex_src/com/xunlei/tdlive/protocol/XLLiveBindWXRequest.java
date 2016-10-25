package com.xunlei.tdlive.protocol;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.util.f.f;

public class XLLiveBindWXRequest extends XLLiveRequest {
    private String mCode;
    private String mExt;
    private String mMobile;
    private String mUnionid;

    public static class BindWXRequestResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
        }

        public BindWXRequestResp() {
            this.data = new Data();
        }
    }

    public XLLiveBindWXRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        super(str, str2);
        this.mUnionid = str3;
        this.mMobile = str4;
        this.mCode = str5;
        this.mExt = str6;
    }

    protected boolean useHttpPost() {
        return true;
    }

    protected void onAddBodyParams(f fVar) {
        fVar.b("unionid", this.mUnionid);
        fVar.b("mobile", this.mMobile);
        fVar.b(XiaomiOAuthConstants.EXTRA_CODE_2, this.mCode);
        fVar.b(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND, this.mExt);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=user&a=bind";
    }

    protected Class<?> onGetObjectClass() {
        return BindWXRequestResp.class;
    }
}
