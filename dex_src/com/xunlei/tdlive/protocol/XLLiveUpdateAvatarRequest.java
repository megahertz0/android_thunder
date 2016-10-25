package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.protocol.XLLiveUpdateAvatarRequest.AvatarData;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.f.f;
import java.io.File;

public class XLLiveUpdateAvatarRequest extends XLLiveRequest {
    private static final int AVATAR_MAX_SIZE = 1048576;
    private File mAvatar;

    public static final class AvatarData {
        public String avatar;
    }

    public static final class AvatarResp extends XLLiveRespBase {
        public AvatarData data;
    }

    public XLLiveUpdateAvatarRequest(String str, String str2, File file) {
        super(str, str2);
        this.mAvatar = file;
    }

    protected String onGetURL() {
        return "http://upload.live.xunlei.com/caller?c=user&a=upload";
    }

    public boolean checkParams() {
        if (this.mAvatar != null && this.mAvatar.exists() && this.mAvatar.length() <= 1048576) {
            return true;
        }
        XLog.w("XLLiveRequest", "avatar file not exits or size is greater than 1048576");
        return false;
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

    protected void onAddBodyParams(f fVar) {
        if (this.mAvatar == null || !this.mAvatar.exists() || this.mAvatar.length() > 1048576) {
            XLog.w("XLLiveRequest", "avatar file not exits or size is greater than 1048576");
        } else {
            fVar.a("avatar", this.mAvatar);
        }
    }

    protected Class<?> onGetObjectClass() {
        return AvatarResp.class;
    }
}
