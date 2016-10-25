package com.xunlei.common.yunbo.request;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.common.yunbo.XLYunboVodStatus;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboGetPlayList extends XLYunboRequestBase {
    private int m_maxnum;
    private int m_offset;
    private String m_order;
    private XLYunboVodStatus m_playflag;

    public XLYunboGetPlayList() {
        this.m_maxnum = 10;
        this.m_offset = 0;
        this.m_order = "create";
        this.m_playflag = null;
    }

    public void setReqInfo(int i, int i2, int i3, int i4) {
        this.m_maxnum = i;
        this.m_offset = i2;
        this.m_playflag = new XLYunboVodStatus(i3);
        switch (i4) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.m_order = "create";
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.m_order = "commit";
            default:
                break;
        }
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnObtainVideoList(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLYB_VODINFO[]) objArr[3], ((Integer) objArr[4]).intValue(), objArr[5]);
    }

    public boolean execute() {
        XLYunboRequestHandler.getHandler().get(String.format("http://I.vod.xunlei.com/req_history_play_list/req_num/%d/req_offset/%d?type=%s&order=%s", new Object[]{Integer.valueOf(this.m_maxnum), Integer.valueOf(this.m_offset), this.m_playflag.getString(), this.m_order}), new BaseHttpClientListener() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                try {
                    XLYB_VODINFO[] xlyb_vodinfoArr;
                    String str = null;
                    try {
                        str = CharsetConvert.inputStreamToGBK(new ByteArrayInputStream(bArr));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("resp");
                    int optInt = optJSONObject.optInt("ret");
                    optJSONObject.optLong("userid");
                    int optInt2 = optJSONObject.optInt("record_num");
                    String optString = optJSONObject.optString("error_msg");
                    JSONArray optJSONArray = optJSONObject.optJSONArray("history_play_list");
                    Object obj = null;
                    if (optJSONArray != null) {
                        XLYB_VODINFO[] xlyb_vodinfoArr2 = new XLYB_VODINFO[optJSONArray.length()];
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            xlyb_vodinfoArr2[i2] = new XLYB_VODINFO();
                            optJSONObject = (JSONObject) optJSONArray.get(i2);
                            xlyb_vodinfoArr2[i2].createtime = optJSONObject.optString("createtime");
                            xlyb_vodinfoArr2[i2].duration = optJSONObject.optInt("duration");
                            xlyb_vodinfoArr2[i2].tasktype = optJSONObject.optInt("tasktype");
                            xlyb_vodinfoArr2[i2].url_hash = URLCoder.decode(optJSONObject.optString("url_hash"), CharsetConvert.UTF_8);
                            xlyb_vodinfoArr2[i2].filesize = optJSONObject.optLong("file_size");
                            xlyb_vodinfoArr2[i2].src_url = URLCoder.decode(optJSONObject.optString("src_url"), CharsetConvert.UTF_8);
                            xlyb_vodinfoArr2[i2].playflag = optJSONObject.optInt("playflag");
                            xlyb_vodinfoArr2[i2].filename = URLCoder.decode(optJSONObject.optString("file_name"), CharsetConvert.UTF_8);
                            xlyb_vodinfoArr2[i2].cid = optJSONObject.optString(SHubBatchQueryKeys.cid);
                            xlyb_vodinfoArr2[i2].gcid = optJSONObject.optString(SHubBatchQueryKeys.gcid);
                            xlyb_vodinfoArr2[i2].playtime = optJSONObject.optString("playtime");
                            xlyb_vodinfoArr2[i2].url = URLCoder.decode(optJSONObject.optString(SHubBatchQueryKeys.url), CharsetConvert.UTF_8);
                        }
                        xlyb_vodinfoArr = xlyb_vodinfoArr2;
                    }
                    XLYunboGetPlayList.this.fireListener(new Object[]{Integer.valueOf(optInt), optString, Integer.valueOf(XLYunboGetPlayList.this.getId()), xlyb_vodinfoArr, Integer.valueOf(optInt2), XLYunboGetPlayList.this.getUserData()});
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    XLYunboGetPlayList.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e2.getMessage(), Integer.valueOf(XLYunboGetPlayList.this.getId()), null, Integer.valueOf(-1), XLYunboGetPlayList.this.getUserData()});
                }
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLYunboGetPlayList.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboGetPlayList.this.getId()), null, Integer.valueOf(-1), XLYunboGetPlayList.this.getUserData()});
            }
        });
        return true;
    }
}
