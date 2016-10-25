package com.xunlei.common.yunbo.request;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYB_BTSUBFILE;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboGetBTSubList extends XLYunboRequestBase {
    private String m_infohash;
    private int m_offset;
    private int m_reqnum;

    public XLYunboGetBTSubList() {
        this.m_infohash = BuildConfig.VERSION_NAME;
        this.m_reqnum = 10;
        this.m_offset = 0;
    }

    public void setBtInfo(String str, int i, int i2) {
        String toUpperCase = str.toUpperCase();
        if (toUpperCase.startsWith("BT://")) {
            this.m_infohash = toUpperCase.replaceAll("BT://", BuildConfig.VERSION_NAME);
        } else {
            this.m_infohash = str;
        }
        this.m_reqnum = i;
        this.m_offset = i2;
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnObtainBtSubfileList(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (String) objArr[3], (XLYB_BTSUBFILE[]) objArr[4], ((Integer) objArr[5]).intValue(), objArr[6]);
    }

    public boolean execute() {
        XLYunboRequestHandler.getHandler().get(String.format("http://I.vod.xunlei.com/req_subBT/info_hash/%s/req_num/%d/req_offset/%d", new Object[]{this.m_infohash, Integer.valueOf(this.m_reqnum), Integer.valueOf(this.m_offset)}), new BaseHttpClientListener() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                try {
                    XLYB_BTSUBFILE[] xlyb_btsubfileArr;
                    String str = null;
                    try {
                        str = CharsetConvert.inputStreamToGBK(new ByteArrayInputStream(bArr));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject jSONObject = new JSONObject(str).getJSONObject("resp");
                    int optInt = jSONObject.optInt("ret");
                    int optInt2 = jSONObject.optInt("record_num");
                    String optString = jSONObject.optString("error_msg");
                    Object obj = null;
                    JSONArray optJSONArray = jSONObject.optJSONArray("subfile_list");
                    if (optJSONArray != null) {
                        XLYB_BTSUBFILE[] xlyb_btsubfileArr2 = new XLYB_BTSUBFILE[optJSONArray.length()];
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            xlyb_btsubfileArr2[i2] = new XLYB_BTSUBFILE();
                            JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i2);
                            xlyb_btsubfileArr2[i2].fileindex = jSONObject2.optInt("index");
                            xlyb_btsubfileArr2[i2].duration = jSONObject2.optInt("duration");
                            xlyb_btsubfileArr2[i2].urlhash = URLCoder.decode(jSONObject2.optString("url_hash"), CharsetConvert.UTF_8);
                            xlyb_btsubfileArr2[i2].filesize = jSONObject2.optLong("file_size");
                            xlyb_btsubfileArr2[i2].filename = URLCoder.decode(jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME), CharsetConvert.UTF_8);
                            xlyb_btsubfileArr2[i2].cid = jSONObject2.optString(SHubBatchQueryKeys.cid);
                            xlyb_btsubfileArr2[i2].gcid = jSONObject2.optString(SHubBatchQueryKeys.gcid);
                        }
                        xlyb_btsubfileArr = xlyb_btsubfileArr2;
                    }
                    XLYunboGetBTSubList.this.fireListener(new Object[]{Integer.valueOf(optInt), optString, Integer.valueOf(XLYunboGetBTSubList.this.getId()), XLYunboGetBTSubList.this.m_infohash, xlyb_btsubfileArr, Integer.valueOf(optInt2), XLYunboGetBTSubList.this.getUserData()});
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    XLYunboGetBTSubList.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e2.getMessage(), Integer.valueOf(XLYunboGetBTSubList.this.getId()), XLYunboGetBTSubList.this.m_infohash, null, Integer.valueOf(-1), XLYunboGetBTSubList.this.getUserData()});
                }
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLYunboGetBTSubList.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboGetBTSubList.this.getId()), XLYunboGetBTSubList.this.m_infohash, null, Integer.valueOf(-1), XLYunboGetBTSubList.this.getUserData()});
            }
        });
        return true;
    }
}
