package com.umeng.socialize.net.base;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.UResponse;
import com.umeng.socialize.utils.Log;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SocializeReseponse extends UResponse {
    protected static final String TAG = "SocializeReseponse";
    public JSONObject mJsonData;
    public String mMsg;
    public int mStCode;

    public SocializeReseponse(JSONObject jSONObject) {
        super(jSONObject);
        this.mStCode = -103;
        this.mJsonData = parseStatus(jSONObject);
        parseJsonObject();
    }

    public boolean isOk() {
        boolean z;
        String str = "umeng_share_response";
        StringBuilder stringBuilder = new StringBuilder("is http 200:");
        if (this.mStCode == 200) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, stringBuilder.append(z).toString());
        return this.mStCode == 200;
    }

    public JSONObject getJsonData() {
        return this.mJsonData;
    }

    private JSONObject parseStatus(JSONObject jSONObject) {
        if (jSONObject == null) {
            Log.e(TAG, "failed requesting");
            return null;
        }
        try {
            this.mStCode = jSONObject.optInt("st", SocializeConstants.SERVER_RETURN_PARAMS_ILLEGAL);
            if (this.mStCode == 0) {
                Log.e(TAG, "no status code in response.");
                return null;
            }
            this.mMsg = jSONObject.optString(SocialConstants.PARAM_SEND_MSG, a.d);
            String optString = jSONObject.optString(SocializeConstants.JSON_DATA, null);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            if (this.mStCode != 200) {
                parseErrorMsg(optString);
            }
            return new JSONObject(optString);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Data body can`t convert to json ");
            return null;
        }
    }

    public void parseJsonObject() {
    }

    private void parseErrorMsg(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                Object string = jSONObject2.getString(SocialConstants.PARAM_SEND_MSG);
                if (TextUtils.isEmpty(string)) {
                    printLog(str2, jSONObject2.getJSONObject(SocializeConstants.JSON_DATA).getString("platform_error"));
                } else {
                    printLog(str2, string);
                }
            }
        } catch (Exception e) {
        }
    }

    private void printLog(String str, String str2) {
        Log.e(TAG, new StringBuilder("error message -> ").append(str).append(" : ").append(str2).toString());
    }
}
