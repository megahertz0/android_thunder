package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import com.xunlei.xiazaibao.sdk.entities.DownloadCreateTaskResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadCreateTaskResponse.CreateTask;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask.CreateTaskItemInfo;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask.ExtJsonInfo;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBCreateTask extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private CreateTaskInfo mCreateTaskInfo;
    private String mPid;

    public static class CreateTaskInfo extends NoObfuscationClassBase {
        public String path;
        public ArrayList<CreateTaskItemInfo> tasks;

        public CreateTaskInfo() {
            this.tasks = new ArrayList();
        }
    }

    public static class CreateTaskItemInfo extends NoObfuscationClassBase {
        public String cid;
        public ExtJsonInfo ext_json;
        public long filesize;
        public String gcid;
        public String name;
        public String ref_url;
        public String url;
    }

    public static class ExtJsonInfo extends NoObfuscationClassBase {
        public int autoname;
        public String cookie;
    }

    static {
        TAG = XZBCreateTask.class.getSimpleName();
    }

    public XZBCreateTask(String str, CreateTaskInfo createTaskInfo) {
        this.mPid = str;
        this.mCreateTaskInfo = createTaskInfo;
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl() + "/createTask");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public String getStringBody() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("json=");
        JSONObject jSONObject = new JSONObject();
        try {
            stringBuffer.append(URLEncoder.encode(objectToJson(this.mCreateTaskInfo), "utf-8"));
            XZBLog.d(TAG, new StringBuilder("body = ").append(stringBuffer.toString()).toString());
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public void writeBody(OutputStream outputStream) {
        try {
            outputStream.write(getStringBody().getBytes());
        } catch (Exception e) {
        }
    }

    public static DownloadCreateTaskResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static DownloadCreateTaskResponse parserJson(String str) {
        DownloadCreateTaskResponse downloadCreateTaskResponse = new DownloadCreateTaskResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            downloadCreateTaskResponse.setRtn(jSONObject.optInt("rtn"));
            downloadCreateTaskResponse.setMsg(jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tasks");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                CreateTask createTask = new CreateTask();
                createTask.setId(jSONObject2.optInt(AgooConstants.MESSAGE_ID));
                createTask.setResult(jSONObject2.optInt("result"));
                createTask.setTaskid(jSONObject2.optString("taskid"));
                createTask.setName(jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME));
                createTask.setMsg(jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
                createTask.setUrl(jSONObject2.optString(SHubBatchQueryKeys.url));
                arrayList.add(createTask);
            }
            downloadCreateTaskResponse.setTasks(arrayList);
        } catch (JSONException e) {
        }
        return downloadCreateTaskResponse;
    }

    private static String objectToJson(CreateTaskInfo createTaskInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (createTaskInfo.path != null) {
                jSONObject.put("path", createTaskInfo.path);
            }
            JSONArray jSONArray = new JSONArray();
            if (createTaskInfo.tasks != null) {
                Iterator it = createTaskInfo.tasks.iterator();
                while (it.hasNext()) {
                    CreateTaskItemInfo createTaskItemInfo = (CreateTaskItemInfo) it.next();
                    JSONObject jSONObject2 = new JSONObject();
                    if (createTaskItemInfo.url != null) {
                        jSONObject2.put(SHubBatchQueryKeys.url, createTaskItemInfo.url);
                    }
                    if (createTaskItemInfo.ref_url != null) {
                        jSONObject2.put("ref_url", createTaskItemInfo.ref_url);
                    }
                    if (createTaskItemInfo.name != null) {
                        jSONObject2.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, createTaskItemInfo.name);
                    }
                    if (createTaskItemInfo.gcid != null) {
                        jSONObject2.put(SHubBatchQueryKeys.gcid, createTaskItemInfo.gcid);
                    }
                    if (createTaskItemInfo.cid != null) {
                        jSONObject2.put(SHubBatchQueryKeys.cid, createTaskItemInfo.cid);
                    }
                    jSONObject2.put(SHubBatchQueryKeys.filesize, createTaskItemInfo.filesize);
                    JSONObject jSONObject3 = new JSONObject();
                    if (createTaskItemInfo.ext_json != null) {
                        jSONObject3.put("autoname", createTaskItemInfo.ext_json.autoname);
                        if (createTaskItemInfo.ext_json.cookie != null) {
                            jSONObject3.put("cookie", createTaskItemInfo.ext_json.cookie);
                        }
                        jSONObject2.put("ext_json", jSONObject3);
                    }
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("tasks", jSONArray);
            String toString = jSONObject.toString();
            XZBLog.d(TAG, new StringBuilder("objectToJson = ").append(toString).toString());
            return toString;
        } catch (JSONException e) {
            return BuildConfig.VERSION_NAME;
        }
    }
}
