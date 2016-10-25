package com.xunlei.common.yunbo.request;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboDeletePlayHistroy extends XLYunboRequestBase {
    private boolean m_bdeleteall;
    private List<String> m_btInfoHash;
    private int m_from;
    private List<String> m_urlHash;

    public XLYunboDeletePlayHistroy() {
        this.m_urlHash = new LinkedList();
        this.m_btInfoHash = new LinkedList();
        this.m_from = 0;
        this.m_bdeleteall = false;
    }

    public void setTarget(int i) {
        this.m_from = i;
    }

    public void delete(String str, String str2) {
        if (str.startsWith("bt://")) {
            this.m_btInfoHash.add(str.toUpperCase().replaceAll("BT://", BuildConfig.VERSION_NAME));
        }
        this.m_urlHash.add(str2);
    }

    public void deleteAll() {
        this.m_urlHash.clear();
        this.m_btInfoHash.clear();
        this.m_bdeleteall = true;
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnDeleteVideo(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), objArr[3]);
    }

    public boolean execute() {
        String substring;
        int i;
        String str = BuildConfig.VERSION_NAME;
        if (this.m_urlHash.size() > 0) {
            str = "&url_hash=";
            int i2 = 0;
            while (i2 < this.m_urlHash.size()) {
                String str2 = str + ((String) this.m_urlHash.get(i2)) + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                i2++;
                str = str2;
            }
            substring = str.substring(0, str.length() - 1);
        } else {
            substring = str;
        }
        str = BuildConfig.VERSION_NAME;
        if (this.m_btInfoHash.size() > 0) {
            str = "&info_hash=";
            i = 0;
            while (i < this.m_btInfoHash.size()) {
                String str3 = str + ((String) this.m_btInfoHash.get(i)) + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                i++;
                str = str3;
            }
            str = str.substring(0, str.length() - 1);
        }
        i = this.m_bdeleteall ? 1 : 0;
        XLYunboRequestHandler.getHandler().get(String.format("http://i.vod.xunlei.com/req_del_list?flag=%d%s%s&sessionid=%s&from=%d", new Object[]{Integer.valueOf(i), substring, str, getInitData().userSessionId, Integer.valueOf(this.m_from)}), new BaseHttpClientListener() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                try {
                    String str = null;
                    try {
                        str = CharsetConvert.inputStreamToGBK(new ByteArrayInputStream(bArr));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject jSONObject = new JSONObject(str).getJSONObject("resp");
                    int optInt = jSONObject.optInt("ret");
                    str = jSONObject.optString("error_msg");
                    XLYunboDeletePlayHistroy.this.fireListener(new Object[]{Integer.valueOf(optInt), str, Integer.valueOf(XLYunboDeletePlayHistroy.this.getId()), XLYunboDeletePlayHistroy.this.getUserData()});
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    XLYunboDeletePlayHistroy.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e2.getMessage(), Integer.valueOf(XLYunboDeletePlayHistroy.this.getId()), XLYunboDeletePlayHistroy.this.getUserData()});
                }
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLYunboDeletePlayHistroy.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboDeletePlayHistroy.this.getId()), XLYunboDeletePlayHistroy.this.getUserData()});
            }
        });
        return true;
    }
}
