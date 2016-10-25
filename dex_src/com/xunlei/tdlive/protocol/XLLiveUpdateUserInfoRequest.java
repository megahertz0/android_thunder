package com.xunlei.tdlive.protocol;

import android.text.TextUtils;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.f.f;

public class XLLiveUpdateUserInfoRequest extends XLLiveRequest {
    private String mNickName;
    private int mSex;
    private String mSign;

    public XLLiveUpdateUserInfoRequest(String str, String str2) {
        super(str, str2);
    }

    public XLLiveUpdateUserInfoRequest nickName(String str) {
        this.mNickName = str;
        return this;
    }

    public XLLiveUpdateUserInfoRequest sign(String str) {
        this.mSign = str;
        return this;
    }

    public XLLiveUpdateUserInfoRequest sex(int i) {
        this.mSex = i;
        return this;
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=user&a=update";
    }

    public void send(JsonCallBack jsonCallBack) {
        if (checkParams()) {
            super.send(jsonCallBack);
        }
    }

    public void send(ObjectCallBack objectCallBack) {
        if (checkParams()) {
            super.send(objectCallBack);
        }
    }

    protected boolean useHttpPost() {
        return true;
    }

    private boolean checkParams() {
        boolean z;
        Object obj = null;
        if (!TextUtils.isEmpty(this.mNickName)) {
            int i = 1;
        }
        if (!TextUtils.isEmpty(this.mSign)) {
            z = true;
        }
        return (this.mSex == 1 || this.mSex == 2) ? true : z;
    }

    protected void onAddBodyParams(f fVar) {
        Object obj = null;
        if (!TextUtils.isEmpty(this.mNickName)) {
            fVar.b("nickname", this.mNickName);
            int i = 1;
        }
        if (!TextUtils.isEmpty(this.mSign)) {
            fVar.b("sign", this.mSign);
            i = 1;
        }
        if (this.mSex == 1 || this.mSex == 2) {
            fVar.b("sex", String.valueOf(this.mSex));
            i = 1;
        }
        if (obj == null) {
            XLog.w("XLLiveRequest", "nothing is changed.");
        }
    }
}
