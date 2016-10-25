package com.xunlei.tdlive.protocol;

import android.text.TextUtils;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest.BindDetail;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest.WxInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

public class XLLiveGetBindInfoRequest extends XLLiveRequest {

    public static final class BindDetail {
        public String ext;
        public int is_bind;
        public int is_follow;
        public String userid;

        public final boolean isBind() {
            return this.is_bind == 1;
        }

        public final boolean isFollow() {
            return this.is_follow == 1;
        }

        public final WxInfo getWxInfo() {
            if (!TextUtils.isEmpty(this.ext)) {
                try {
                    JSONObject jSONObject = new JSONObject(URLDecoder.decode(this.ext, CharsetConvert.UTF_8));
                    WxInfo wxInfo = new WxInfo();
                    wxInfo.headimgurl = jSONObject.getString("headimgurl");
                    wxInfo.nickname = jSONObject.getString("nickname");
                    return wxInfo;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }
    }

    public static final class BindInfo extends XLLiveRespBase {
        public BindDetail data;
    }

    public static final class WxInfo {
        public String headimgurl;
        public String nickname;
    }

    public XLLiveGetBindInfoRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=user&a=checkbindwx";
    }

    protected Class<?> onGetObjectClass() {
        return BindInfo.class;
    }
}
