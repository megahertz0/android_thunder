package com.xunlei.common.accelerator.model;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.accelerator.bean.AccelInfoResultBean;
import com.xunlei.common.accelerator.bean.KeepResultBean;
import com.xunlei.common.accelerator.bean.PortalResultBean;
import com.xunlei.common.accelerator.bean.StartAccelResultBean;
import com.xunlei.common.accelerator.bean.StopAccelResultBean;
import com.xunlei.common.accelerator.bean.TryInfoResultBean;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.common.accelerator.bean.XLAccelTryInfo;
import com.xunlei.common.accelerator.bean.XLAccelUser;
import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class XLAccelModel extends BaseModel {
    public static final int RT_BAND_INFO = 0;
    public static final int RT_START_ACCEL = 1;
    public static final int RT_STOP_ACCEL = 2;

    public PortalResultBean parsePortalData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            PortalResultBean portalResultBean = new PortalResultBean();
            int i = jSONObject.getInt("errno");
            portalResultBean.setError(i);
            if (i != 0) {
                return portalResultBean;
            }
            String string = jSONObject.getString("interface_ip");
            String string2 = jSONObject.getString("interface_port");
            portalResultBean.setIp(string);
            portalResultBean.setPort(string2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("http://").append(string).append(":").append(string2);
            portalResultBean.setAddress(stringBuffer.toString());
            return portalResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccelInfoResultBean parseBandInfoData(String str) {
        int i = RT_BAND_INFO;
        try {
            JSONObject jSONObject = new JSONObject(str);
            AccelInfoResultBean accelInfoResultBean = new AccelInfoResultBean();
            if (!jSONObject.isNull("errno")) {
                i = jSONObject.getInt("errno");
                accelInfoResultBean.setError(i);
            }
            if (!jSONObject.isNull("sequence")) {
                accelInfoResultBean.setSeq(jSONObject.getInt("sequence"));
            }
            if (!jSONObject.isNull("richmessage")) {
                accelInfoResultBean.setRichmessage(jSONObject.getString("richmessage"));
            }
            if (!jSONObject.isNull("dial_account")) {
                accelInfoResultBean.setDialAccount(jSONObject.getString("dial_account"));
            }
            if (i != 0) {
                return accelInfoResultBean;
            }
            XLAccelBandInfo obtainBandInfo = obtainBandInfo(jSONObject, RT_BAND_INFO);
            if (obtainBandInfo.mCanUpgrade == 0) {
                accelInfoResultBean.setRichmessage("\u5f53\u524d\u5e26\u5bbd\u5df2\u7ecf\u8fbe\u5230\u6700\u5927\u503c");
            }
            accelInfoResultBean.setXlAccelBandInfo(obtainBandInfo);
            return accelInfoResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TryInfoResultBean parseTryAccelInfoData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("sequence");
            int i2 = jSONObject.getInt("errno");
            String string = jSONObject.getString("richmessage");
            TryInfoResultBean tryInfoResultBean = new TryInfoResultBean();
            tryInfoResultBean.setSeq(i);
            tryInfoResultBean.setError(i2);
            tryInfoResultBean.setRichmessage(string);
            if (i2 != 0) {
                return tryInfoResultBean;
            }
            XLAccelTryInfo xLAccelTryInfo = new XLAccelTryInfo();
            xLAccelTryInfo.mNumOfTry = jSONObject.getInt("number_of_try");
            xLAccelTryInfo.mTryDuration = jSONObject.getInt("try_duration");
            tryInfoResultBean.setXlAccelTryInfo(xLAccelTryInfo);
            return tryInfoResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public StartAccelResultBean parseStartAccelData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("sequence");
            int i2 = jSONObject.getInt("errno");
            String string = jSONObject.getString("richmessage");
            StartAccelResultBean startAccelResultBean = new StartAccelResultBean();
            startAccelResultBean.setSeq(i);
            startAccelResultBean.setError(i2);
            startAccelResultBean.setRichmessage(string);
            if (i2 != 0) {
                return startAccelResultBean;
            }
            startAccelResultBean.setXlAccelBandInfo(obtainBandInfo(jSONObject, RT_START_ACCEL));
            return startAccelResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public StopAccelResultBean parseStopAccelData(String str) {
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("sequence");
            int i2 = jSONObject.getInt("errno");
            String str3 = BuildConfig.VERSION_NAME;
            if (jSONObject.isNull("richmessage")) {
                str2 = str3;
            } else {
                str2 = jSONObject.getString("richmessage");
            }
            StopAccelResultBean stopAccelResultBean = new StopAccelResultBean();
            stopAccelResultBean.setSeq(i);
            stopAccelResultBean.setError(i2);
            stopAccelResultBean.setRichmessage(str2);
            if (i2 != 0 || jSONObject.isNull("bandwidth")) {
                return stopAccelResultBean;
            }
            stopAccelResultBean.setXlAccelBandInfo(obtainBandInfo(jSONObject, RT_STOP_ACCEL));
            return stopAccelResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private XLAccelBandInfo obtainBandInfo(JSONObject jSONObject, int i) throws JSONException {
        XLAccelBandInfo xLAccelBandInfo = new XLAccelBandInfo();
        JSONObject jSONObject2 = jSONObject.getJSONObject("bandwidth");
        xLAccelBandInfo.mCurrentBandWidth.mDownStream = jSONObject2.getLong("downstream");
        xLAccelBandInfo.mCurrentBandWidth.mUpStream = jSONObject2.getLong("upstream");
        if (i == 0) {
            xLAccelBandInfo.mCanUpgrade = jSONObject.getInt("can_upgrade");
            jSONObject2 = jSONObject.getJSONObject("max_bandwidth");
            xLAccelBandInfo.mMaxBandWidth.mDownStream = jSONObject2.getLong("downstream");
            xLAccelBandInfo.mMaxBandWidth.mUpStream = jSONObject2.getLong("upstream");
        }
        xLAccelBandInfo.mBandWidthInfo.mServiceProvider = jSONObject.getString("sp");
        xLAccelBandInfo.mBandWidthInfo.mDialAccount = jSONObject.getString("dial_account");
        xLAccelBandInfo.mBandWidthInfo.mProvince = jSONObject.getString("province");
        xLAccelBandInfo.mBandWidthInfo.mServiceProviderName = jSONObject.getString("sp_name");
        xLAccelBandInfo.mBandWidthInfo.mProvinceName = jSONObject.getString("province_name");
        return xLAccelBandInfo;
    }

    public KeepResultBean parseKeepAliveData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("sequence");
            int i2 = jSONObject.getInt("errno");
            String string = jSONObject.getString("richmessage");
            String string2 = jSONObject.getString("client_type");
            KeepResultBean keepResultBean = new KeepResultBean();
            keepResultBean.setSeq(i);
            keepResultBean.setError(i2);
            keepResultBean.setRichmessage(string);
            keepResultBean.setClient_type(string2);
            if (!jSONObject.isNull("timestamp")) {
                keepResultBean.setTimestamp(jSONObject.getString("timestamp"));
            }
            if (!jSONObject.isNull("upgrade_type")) {
                keepResultBean.setUpgrade_type(String.valueOf(jSONObject.getInt("upgrade_type")));
            }
            if (jSONObject.isNull("userid")) {
                return keepResultBean;
            }
            keepResultBean.setUserid(jSONObject.getString("userid"));
            return keepResultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bandInfoToJson(int i, int i2, String str, XLAccelBandInfo xLAccelBandInfo) {
        JSONObject baseJsonObj = getBaseJsonObj(i, i2, str);
        if (xLAccelBandInfo != null) {
            try {
                JSONObject jSONObject;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("mCanUpgrade", xLAccelBandInfo.mCanUpgrade);
                if (xLAccelBandInfo.mCurrentBandWidth != null) {
                    jSONObject = new JSONObject();
                    jSONObject.put("mUpStream", xLAccelBandInfo.mCurrentBandWidth.mUpStream);
                    jSONObject.put("mDownStream", xLAccelBandInfo.mCurrentBandWidth.mDownStream);
                    jSONObject2.put("mCurrentBandWidth", jSONObject);
                }
                if (xLAccelBandInfo.mMaxBandWidth != null) {
                    jSONObject = new JSONObject();
                    jSONObject.put("mUpStream", xLAccelBandInfo.mMaxBandWidth.mUpStream);
                    jSONObject.put("mDownStream", xLAccelBandInfo.mMaxBandWidth.mDownStream);
                    jSONObject2.put("mMaxBandWidth", jSONObject);
                }
                if (xLAccelBandInfo.mBandWidthInfo != null) {
                    jSONObject = new JSONObject();
                    jSONObject.put("mServiceProvider", xLAccelBandInfo.mBandWidthInfo.mServiceProvider);
                    jSONObject.put("mServiceProviderName", xLAccelBandInfo.mBandWidthInfo.mServiceProviderName);
                    jSONObject.put("mDialAccount", xLAccelBandInfo.mBandWidthInfo.mDialAccount);
                    jSONObject.put("mProvince", xLAccelBandInfo.mBandWidthInfo.mProvince);
                    jSONObject.put("mProvinceName", xLAccelBandInfo.mBandWidthInfo.mProvinceName);
                    jSONObject2.put("mBandWidthInfo", jSONObject);
                }
                baseJsonObj.put("xbi", jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseJsonObj.toString();
    }

    public static String tryInfoToJson(int i, int i2, String str, XLAccelTryInfo xLAccelTryInfo) {
        JSONObject baseJsonObj = getBaseJsonObj(i, i2, str);
        if (xLAccelTryInfo != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mNumOfTry", xLAccelTryInfo.mNumOfTry);
                jSONObject.put("mTryDuration", xLAccelTryInfo.mTryDuration);
                jSONObject.put("mRemainTime", xLAccelTryInfo.mRemainTime);
                baseJsonObj.put("tryInfo", jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseJsonObj.toString();
    }

    private static JSONObject getBaseJsonObj(int i, int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seq", i);
            jSONObject.put(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, i2);
            jSONObject.put("errorDesc", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String UserInfoToJsonJ(XLAccelUser xLAccelUser) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mUserID", xLAccelUser.mUserID);
            jSONObject.put("mSessionID", xLAccelUser.mSessionID);
            jSONObject.put("mUserType", xLAccelUser.mUserType);
            jSONObject.put("mVipType", xLAccelUser.mVipType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
