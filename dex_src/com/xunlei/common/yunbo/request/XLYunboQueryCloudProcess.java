package com.xunlei.common.yunbo.request;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYB_VIDEOPROCESS;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboQueryCloudProcess extends XLYunboRequestBase {
    private int m_type;
    private List<String> m_urllist;

    public XLYunboQueryCloudProcess() {
        this.m_urllist = new LinkedList();
        this.m_type = 0;
    }

    public void addUrl(String[] strArr, int i) {
        if (strArr != null && strArr.length != 0) {
            this.m_type = i;
            for (Object obj : strArr) {
                this.m_urllist.add(obj);
            }
        }
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnObtainVideoProcess(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLYB_VIDEOPROCESS[]) objArr[3], objArr[4]);
    }

    public boolean execute() {
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.m_urllist.size(); i++) {
                jSONArray.put(this.m_urllist.get(i));
            }
            JSONObject jSONObject = new JSONObject();
            if (this.m_type == 1) {
                jSONObject.put("url_list", jSONArray);
            } else if (this.m_type == 2) {
                jSONObject.put("url_hash_list", jSONArray);
            }
            jSONObject.put("platform", getInitData().platform);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("req", jSONObject);
            String toString = jSONObject2.toString();
            XLYunboRequestHandler.getHandler().post("http://i.vod.xunlei.com/req_progress_query", toString.getBytes(), new BaseHttpClientListener() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    try {
                        XLYB_VIDEOPROCESS[] xlyb_videoprocessArr;
                        String str = null;
                        try {
                            str = CharsetConvert.inputStreamToGBK(new ByteArrayInputStream(bArr));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject("resp");
                        int optInt = optJSONObject.optInt("ret");
                        String optString = optJSONObject.optString("error_msg");
                        Object obj = null;
                        JSONArray optJSONArray = optJSONObject.optJSONArray("progress_info_list");
                        if (optJSONArray != null) {
                            XLYB_VIDEOPROCESS[] xlyb_videoprocessArr2 = new XLYB_VIDEOPROCESS[optJSONArray.length()];
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                JSONObject jSONObject = (JSONObject) optJSONArray.get(i2);
                                if (jSONObject != null) {
                                    xlyb_videoprocessArr2[i2] = new XLYB_VIDEOPROCESS();
                                    String str2 = BuildConfig.VERSION_NAME;
                                    if (XLYunboQueryCloudProcess.this.m_type == 1) {
                                        str2 = SHubBatchQueryKeys.url;
                                    } else if (XLYunboQueryCloudProcess.this.m_type == 2) {
                                        str2 = "url_hash";
                                    }
                                    xlyb_videoprocessArr2[i2].url = jSONObject.optString(str2);
                                    xlyb_videoprocessArr2[i2].urltype = XLYunboQueryCloudProcess.this.m_type;
                                    String[] split = jSONObject.optString("progress").split("_");
                                    if (split.length >= 2) {
                                        xlyb_videoprocessArr2[i2].status = Integer.valueOf(split[0]).intValue();
                                        xlyb_videoprocessArr2[i2].process = Integer.valueOf(split[1]).intValue();
                                    }
                                }
                            }
                            xlyb_videoprocessArr = xlyb_videoprocessArr2;
                        }
                        XLYunboQueryCloudProcess.this.fireListener(new Object[]{Integer.valueOf(optInt), optString, Integer.valueOf(XLYunboQueryCloudProcess.this.getId()), xlyb_videoprocessArr, XLYunboQueryCloudProcess.this.getUserData()});
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        XLYunboQueryCloudProcess.this.fireListener(new Object[]{Integer.valueOf(-1), e2.getMessage(), Integer.valueOf(XLYunboQueryCloudProcess.this.getId()), null, XLYunboQueryCloudProcess.this.getUserData()});
                    }
                }

                public void onFailure(Throwable th, byte[] bArr) {
                    XLYunboQueryCloudProcess.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboQueryCloudProcess.this.getId()), null, XLYunboQueryCloudProcess.this.getUserData()});
                }
            });
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
