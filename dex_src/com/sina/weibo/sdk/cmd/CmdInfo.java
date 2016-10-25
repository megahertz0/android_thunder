package com.sina.weibo.sdk.cmd;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.taobao.agoo.a.a.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CmdInfo {
    private static final String TAG;
    private int frequency;
    private List<AppInstallCmd> mInstallCmds;
    private List<AppInvokeCmd> mInvokeCmds;

    static {
        TAG = BaseCmd.class.getName();
    }

    public CmdInfo(String str) throws WeiboException {
        initFromJsonStr(str);
    }

    private void initFromJsonStr(String str) throws WeiboException {
        int i = 0;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error") || jSONObject.has("error_code")) {
                    LogUtil.w(TAG, "load cmd api has error !!!");
                    throw new WeiboException("load cmd api has error !!!");
                }
                JSONObject optJSONObject = jSONObject.optJSONObject(b.JSON_CMD);
                if (optJSONObject != null) {
                    this.frequency = optJSONObject.optInt("frequency");
                    JSONArray optJSONArray = optJSONObject.optJSONArray("app_install");
                    if (optJSONArray != null) {
                        this.mInstallCmds = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            this.mInstallCmds.add(new AppInstallCmd(optJSONArray.getJSONObject(i2)));
                        }
                    }
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("app_invoke");
                    if (optJSONArray2 != null) {
                        this.mInvokeCmds = new ArrayList();
                        while (i < optJSONArray2.length()) {
                            this.mInvokeCmds.add(new AppInvokeCmd(optJSONArray2.getJSONObject(i)));
                            i++;
                        }
                    }
                }
            } catch (JSONException e) {
                LogUtil.d(TAG, new StringBuilder("parse NotificationInfo error: ").append(e.getMessage()).toString());
            }
        }
    }

    public List<AppInstallCmd> getInstallCmds() {
        return this.mInstallCmds;
    }

    public void setInstallCmds(List<AppInstallCmd> list) {
        this.mInstallCmds = list;
    }

    public List<AppInvokeCmd> getInvokeCmds() {
        return this.mInvokeCmds;
    }

    public void setInvokeCmds(List<AppInvokeCmd> list) {
        this.mInvokeCmds = list;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }
}
