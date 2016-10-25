package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import com.xunlei.xiazaibao.sdk.entities.DownloadTasks;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBDeleteTask extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private boolean mDeleteFile;
    private String mPid;
    private boolean mRecycleTask;
    private ArrayList<String> mTaskIds;

    static {
        TAG = XZBDeleteTask.class.getSimpleName();
    }

    public XZBDeleteTask(String str) {
        this.mPid = str;
        this.mTaskIds = new ArrayList();
        setDeleteFile(true);
        setRecycleTask(true);
    }

    public XZBDeleteTask(String str, boolean z) {
        this.mPid = str;
        this.mTaskIds = new ArrayList();
        setDeleteFile(z);
        setRecycleTask(true);
    }

    public boolean isDeleteFile() {
        return this.mDeleteFile;
    }

    public void setDeleteFile(boolean z) {
        this.mDeleteFile = z;
    }

    public boolean isRecycleTask() {
        return this.mRecycleTask;
    }

    public void setRecycleTask(boolean z) {
        this.mRecycleTask = z;
    }

    public void addDownloadTaskId(String str, int i) {
        this.mTaskIds.add(str + "_" + i);
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl() + "/del");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append("&tasks=");
        for (int i = 0; i < this.mTaskIds.size(); i++) {
            if (i == 0) {
                stringBuffer.append((String) this.mTaskIds.get(i));
            } else {
                stringBuffer.append(new StringBuilder(",").append((String) this.mTaskIds.get(i)).toString());
            }
        }
        stringBuffer.append(new StringBuilder("&deleteFile=").append(this.mDeleteFile).toString());
        stringBuffer.append(new StringBuilder("&recycleTask=").append(this.mRecycleTask).toString());
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public Header[] getHeader() {
        return super.getHeader();
    }

    public static DownloadTasks parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static DownloadTasks parserJson(String str) {
        DownloadTasks downloadTasks = new DownloadTasks();
        try {
            JSONObject jSONObject = new JSONObject(str);
            downloadTasks.setRtn(jSONObject.optInt("rtn"));
            downloadTasks.msg = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tasks");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                DownloadTaskResult downloadTaskResult = new DownloadTaskResult();
                downloadTaskResult.setId(jSONObject2.optString(AgooConstants.MESSAGE_ID));
                downloadTaskResult.setResult(jSONObject2.optInt("result"));
                downloadTaskResult.setMsg(jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
                arrayList.add(downloadTaskResult);
            }
            downloadTasks.setTasks(arrayList);
        } catch (JSONException e) {
        }
        return downloadTasks;
    }
}
