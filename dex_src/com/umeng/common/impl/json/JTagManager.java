package com.umeng.common.impl.json;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.common.inter.ITagManager;
import com.umeng.common.inter.ITagManager.Result;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class JTagManager implements ITagManager {
    private static final String a;
    private Context b;

    static {
        a = JTagManager.class.getSimpleName();
    }

    public JTagManager(Context context) {
        this.b = context;
    }

    public Result add(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = JUtrack.sendRequest(jSONObject, MsgConstant.TAG_ENDPOINT + "/add");
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, (MsgConstant.TAG_ENDPOINT + "/add").replace("https", "http"));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result update(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = JUtrack.sendRequest(jSONObject, MsgConstant.TAG_ENDPOINT + "/update");
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, (MsgConstant.TAG_ENDPOINT + "/update").replace("https", "http"));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result delete(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = JUtrack.sendRequest(jSONObject, MsgConstant.TAG_ENDPOINT + "/delete");
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, (MsgConstant.TAG_ENDPOINT + "/delete").replace("https", "http"));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result reset(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = JUtrack.sendRequest(jSONObject, MsgConstant.TAG_ENDPOINT + "/reset");
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, (MsgConstant.TAG_ENDPOINT + "/reset").replace("https", "http"));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
        }
        return result;
    }

    public List<String> list(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = JUtrack.sendRequest(jSONObject, MsgConstant.TAG_ENDPOINT + "/get");
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, (MsgConstant.TAG_ENDPOINT + "/get").replace("https", "http"));
        }
        if (!TextUtils.equals(new Result(sendRequest).status, ITagManager.SUCCESS) || sendRequest.getString("tags") == null) {
            return null;
        }
        UmLog.d(a, sendRequest.getString("tags"));
        return Arrays.asList(sendRequest.getString("tags").split(","));
    }
}
