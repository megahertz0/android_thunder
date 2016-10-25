package com.xunlei.common.yunbo.request;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.encrypt.AES;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.HextoChar;
import com.xunlei.common.encrypt.RsaEncode;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYB_PLAYINFO;
import com.xunlei.common.yunbo.XLYB_PLAYINFO.PLAYINFO;
import com.xunlei.common.yunbo.XLYB_REQPLAYINFO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XLYunboObtainVideoPlayUrl extends XLYunboRequestBase {
    private static final long MINUM_16_Long = 1000000000000000L;
    private static final String REQUEST_PARAM_KEY_POST_INFO = "post_info";
    private byte[] m_aeskey;
    XLYB_REQPLAYINFO m_reqInfo;

    private class ReqNameValuePair implements NameValuePair {
        private String m_name;
        private String m_value;

        public ReqNameValuePair(String str, String str2) {
            this.m_name = BuildConfig.VERSION_NAME;
            this.m_value = BuildConfig.VERSION_NAME;
            this.m_name = str;
            this.m_value = str2;
        }

        public String getName() {
            return this.m_name;
        }

        public String getValue() {
            return this.m_value;
        }
    }

    public XLYunboObtainVideoPlayUrl() {
        this.m_reqInfo = null;
        this.m_aeskey = null;
    }

    public void setUrlInfo(XLYB_REQPLAYINFO xlyb_reqplayinfo) {
        this.m_reqInfo = new XLYB_REQPLAYINFO();
        this.m_reqInfo.copyfrom(xlyb_reqplayinfo);
    }

    private static byte[] genAESKey(long j) {
        byte[] bArr = null;
        try {
            bArr = AES.getRawKey(SpdyProtocol.SLIGHTSSLV2, String.valueOf(j).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bArr == null) {
            bArr = String.valueOf((new Random().nextLong() % 1000000000000000L) + 1000000000000000L).getBytes();
        }
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] == null) {
                bArr[i] = (byte) 1;
            }
        }
        return bArr;
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        return xLYunboListener.OnObtainVideoPlayUrl(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLYB_PLAYINFO) objArr[3], objArr[4]);
    }

    public boolean execute() {
        if (this.m_reqInfo.clientid.compareToIgnoreCase(BuildConfig.VERSION_NAME) == 0 || this.m_reqInfo.rsa_module.compareToIgnoreCase(BuildConfig.VERSION_NAME) == 0 || this.m_reqInfo.rsa_exponent.compareToIgnoreCase(BuildConfig.VERSION_NAME) == 0) {
            return false;
        }
        this.m_aeskey = genAESKey(getInitData().userId);
        byte[] encodeUseRSA = RsaEncode.encodeUseRSA(this.m_aeskey, this.m_reqInfo.rsa_module, this.m_reqInfo.rsa_exponent);
        if (encodeUseRSA == null) {
            return false;
        }
        encodeUseRSA = HextoChar.bytes_to_hex(encodeUseRSA, encodeUseRSA.length);
        if (encodeUseRSA == null) {
            return false;
        }
        String str = new String(encodeUseRSA);
        str = String.format("http://ci.vod.xunlei.com/vod_client/get_url?id=%s&key=%s&verify=%s", new Object[]{this.m_reqInfo.clientid, str, this.m_reqInfo.verify});
        try {
            CharSequence charSequence = this.m_reqInfo.url;
            if (charSequence.startsWith("bt://")) {
                if (this.m_reqInfo.index >= 0) {
                    charSequence = String.format("%s/%d", new Object[]{this.m_reqInfo.url, Integer.valueOf(this.m_reqInfo.index)});
                }
            } else if (!charSequence.startsWith("magnet:")) {
                if (charSequence.indexOf("//") < 0) {
                    charSequence = String.format("bt://%s/%d", new Object[]{this.m_reqInfo.url, Integer.valueOf(this.m_reqInfo.index)});
                } else if (charSequence.startsWith("cid://")) {
                    charSequence = BuildConfig.VERSION_NAME;
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userid", getInitData().userId);
            jSONObject.put("operationid", this.m_reqInfo.operationid);
            jSONObject.put("sessionid", getInitData().userSessionId);
            jSONObject.put(SHubBatchQueryKeys.url, URLCoder.encode(charSequence, CharsetConvert.UTF_8));
            jSONObject.put(SHubBatchQueryKeys.gcid, this.m_reqInfo.gcid);
            jSONObject.put(SHubBatchQueryKeys.cid, this.m_reqInfo.cid);
            jSONObject.put(SHubBatchQueryKeys.filesize, this.m_reqInfo.filesize);
            jSONObject.put("vod_type", this.m_reqInfo.vod_type);
            if (TextUtils.isEmpty(charSequence)) {
                jSONObject.put(SHubBatchQueryKeys.filename, URLCoder.encode(this.m_reqInfo.url, CharsetConvert.UTF_8));
            }
            encodeUseRSA = AES.encrypt(jSONObject.toString().getBytes(CharsetConvert.UTF_8), this.m_aeskey, false, false);
            if (encodeUseRSA == null) {
                return false;
            }
            String encodeToString = Base64.encodeToString(encodeUseRSA, 0);
            if (encodeToString == null) {
                return false;
            }
            encodeToString = URLCoder.encode(encodeToString, CharsetConvert.UTF_8);
            List arrayList = new ArrayList();
            arrayList.add(new ReqNameValuePair(REQUEST_PARAM_KEY_POST_INFO, encodeToString));
            XLYunboRequestHandler.getHandler().post(str, new UrlEncodedFormEntity(arrayList, CharsetConvert.UTF_8), new BaseHttpClientListener() {
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    try {
                        byte[] decode = Base64.decode(CharsetConvert.inputStreamToGBK(new ByteArrayInputStream(bArr)), 0);
                        if (decode == null) {
                            XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(-5), "base64.decode error", Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                            return;
                        }
                        decode = AES.decrypt(decode, XLYunboObtainVideoPlayUrl.this.m_aeskey, false, false);
                        if (decode == null) {
                            XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(-5), "AES.decrypt error", Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                            return;
                        }
                        JSONObject optJSONObject = new JSONObject(new String(decode, CharsetConvert.UTF_8)).optJSONObject("resp");
                        XLYB_PLAYINFO xlyb_playinfo = new XLYB_PLAYINFO();
                        xlyb_playinfo.reqdata = XLYunboObtainVideoPlayUrl.this.m_reqInfo;
                        xlyb_playinfo.result = optJSONObject.getInt("ret");
                        xlyb_playinfo.state = optJSONObject.optInt(XiaomiOAuthConstants.EXTRA_STATE_2);
                        xlyb_playinfo.duration = optJSONObject.optLong("duration");
                        xlyb_playinfo.remain_time = optJSONObject.optInt("remain_time");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("play_url");
                        if (optJSONArray != null) {
                            xlyb_playinfo.videoList = new PLAYINFO[optJSONArray.length()];
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                optJSONObject = (JSONObject) optJSONArray.get(i2);
                                if (optJSONObject != null) {
                                    xlyb_playinfo.videoList[i2] = new PLAYINFO();
                                    xlyb_playinfo.videoList[i2].resolution_type = optJSONObject.optInt("resolution_type");
                                    xlyb_playinfo.videoList[i2].vod_url = optJSONObject.optString("vod_url");
                                    xlyb_playinfo.videoList[i2].gcid = optJSONObject.optString(SHubBatchQueryKeys.gcid);
                                    xlyb_playinfo.videoList[i2].cid = optJSONObject.optString(SHubBatchQueryKeys.cid);
                                    xlyb_playinfo.videoList[i2].filesize = optJSONObject.optLong("file_size");
                                    xlyb_playinfo.videoList[i2].has_subtitle = optJSONObject.optInt("has_subtitle");
                                }
                            }
                        }
                        XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(xlyb_playinfo.result), BuildConfig.VERSION_NAME, Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), xlyb_playinfo, XLYunboObtainVideoPlayUrl.this.getUserData()});
                    } catch (JSONException e) {
                        e.printStackTrace();
                        XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e.getMessage(), Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                        XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL), e2.getMessage(), Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(-4), e3.getMessage(), Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                    }
                }

                public void onFailure(Throwable th, byte[] bArr) {
                    if (th instanceof SocketTimeoutException) {
                        XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(-1), "socket time out", Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                        return;
                    }
                    XLYunboObtainVideoPlayUrl.this.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(XLYunboObtainVideoPlayUrl.this.getId()), null, XLYunboObtainVideoPlayUrl.this.getUserData()});
                }
            });
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
