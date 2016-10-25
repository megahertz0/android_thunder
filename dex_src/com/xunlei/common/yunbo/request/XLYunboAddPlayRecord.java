package com.xunlei.common.yunbo.request;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYB_ADDVIDEO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboAddPlayRecord extends XLYunboRequestBase {
    private List<XLYB_ADDVIDEO> m_recordlist;

    public XLYunboAddPlayRecord() {
        this.m_recordlist = new LinkedList();
    }

    public void addUrl(XLYB_ADDVIDEO[] xlyb_addvideoArr) {
        for (Object obj : xlyb_addvideoArr) {
            this.m_recordlist.add(obj);
        }
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnAddVideo2Favorite(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLYB_ADDVIDEO[]) objArr[3], objArr[4]);
    }

    public boolean execute() {
        String format = String.format("http://i.vod.xunlei.com/req_add_record?userid=%d&from=%s&platform=%d&sessionid=%s", new Object[]{Long.valueOf(getInitData().userId), getInitData().productName, Integer.valueOf(getInitData().platform), getInitData().userSessionId});
        String str = BuildConfig.VERSION_NAME;
        for (int i = 0; i < this.m_recordlist.size(); i++) {
            XLYB_ADDVIDEO xlyb_addvideo = (XLYB_ADDVIDEO) this.m_recordlist.get(i);
            str = String.format("%s,{\"id\":%d , \"url\":\"%s\" , \"name\":\"%s\"}", new Object[]{str, Integer.valueOf(xlyb_addvideo.index), URLCoder.encode(xlyb_addvideo.url, CharsetConvert.UTF_8), URLCoder.encode(xlyb_addvideo.name, CharsetConvert.UTF_8)});
        }
        if (str.startsWith(",")) {
            str = str.substring(1, str.length());
        }
        XLYunboRequestHandler.getHandler().post(format, String.format("{\"urls\":[%s], \"to\":\"%s\"}", new Object[]{str, "shoucang"}).getBytes(), new BaseHttpClientListener() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                try {
                    XLYB_ADDVIDEO[] xlyb_addvideoArr;
                    JSONObject jSONObject = new JSONObject(new String(bArr, CharsetConvert.UTF_8)).getJSONObject("resp");
                    int optInt = jSONObject.optInt("ret");
                    String optString = jSONObject.optString("error_msg");
                    Object obj = null;
                    JSONArray optJSONArray = jSONObject.optJSONArray(SHubBatchQueryKeys.res);
                    if (optJSONArray != null) {
                        XLYB_ADDVIDEO[] xlyb_addvideoArr2 = new XLYB_ADDVIDEO[optJSONArray.length()];
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            xlyb_addvideoArr2[i2] = new XLYB_ADDVIDEO();
                            JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i2);
                            xlyb_addvideoArr2[i2].result = jSONObject2.optInt("result");
                            xlyb_addvideoArr2[i2].index = jSONObject2.optInt(AgooConstants.MESSAGE_ID);
                            xlyb_addvideoArr2[i2].url = jSONObject2.optString(SHubBatchQueryKeys.url);
                            xlyb_addvideoArr2[i2].name = jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                            xlyb_addvideoArr2[i2].url_hash = jSONObject2.optString("url_hash");
                            xlyb_addvideoArr2[i2].gcid = jSONObject2.optString(SHubBatchQueryKeys.gcid);
                        }
                        xlyb_addvideoArr = xlyb_addvideoArr2;
                    }
                    XLYunboAddPlayRecord.this.fireListener(new Object[]{Integer.valueOf(optInt), optString, Integer.valueOf(XLYunboAddPlayRecord.this.getId()), xlyb_addvideoArr, XLYunboAddPlayRecord.this.getUserData()});
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLYunboAddPlayRecord.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e.getMessage(), Integer.valueOf(XLYunboAddPlayRecord.this.getId()), null, XLYunboAddPlayRecord.this.getUserData()});
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLYunboAddPlayRecord.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboAddPlayRecord.this.getId()), null, XLYunboAddPlayRecord.this.getUserData()});
            }
        });
        return true;
    }
}
