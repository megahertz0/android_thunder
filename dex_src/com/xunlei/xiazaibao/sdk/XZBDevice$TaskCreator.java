package com.xunlei.xiazaibao.sdk;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadCreateTaskResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadCreateTaskResponse.CreateTask;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask.CreateTaskInfo;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask.CreateTaskItemInfo;
import com.xunlei.xiazaibao.sdk.synctasks.XZBCreateTask.ExtJsonInfo;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;

public class XZBDevice$TaskCreator {
    public String message;
    public int result;
    public UrlData[] urlRetDatas;

    public int createTask(XZBDevice xZBDevice, UrlData[] urlDataArr) {
        int i;
        if (urlDataArr != null) {
            this.urlRetDatas = new UrlData[urlDataArr.length];
        }
        ArrayList arrayList = new ArrayList();
        int length = urlDataArr.length;
        for (i = 0; i < length; i++) {
            UrlData urlData = urlDataArr[i];
            if (urlData != null && !TextUtils.isEmpty(urlData.downloadUrl)) {
                urlData.downloadUrl = urlData.downloadUrl.trim();
                CreateTaskItemInfo createTaskItemInfo = new CreateTaskItemInfo();
                createTaskItemInfo.url = urlData.downloadUrl;
                createTaskItemInfo.ref_url = urlData.refrenceUrl;
                if (!TextUtils.isEmpty(urlData.name)) {
                    createTaskItemInfo.name = urlData.name;
                }
                ExtJsonInfo extJsonInfo = new ExtJsonInfo();
                if (TextUtils.isEmpty(createTaskItemInfo.name)) {
                    extJsonInfo.autoname = 1;
                } else {
                    extJsonInfo.autoname = 0;
                }
                extJsonInfo.cookie = BuildConfig.VERSION_NAME;
                if (!TextUtils.isEmpty(createTaskItemInfo.url)) {
                    String[] split = createTaskItemInfo.url.split("&");
                    int length2 = split.length;
                    for (int i2 = 0; i2 < length2; i2++) {
                        String str = split[i2];
                        if (str.startsWith("ck=")) {
                            extJsonInfo.autoname = 1;
                            extJsonInfo.cookie = new StringBuilder("kuaichuanid=").append(str.substring(str.indexOf("=") + 1)).toString();
                            break;
                        }
                    }
                }
                createTaskItemInfo.ext_json = extJsonInfo;
                arrayList.add(createTaskItemInfo);
            }
        }
        CreateTaskInfo createTaskInfo = new CreateTaskInfo();
        createTaskInfo.path = xZBDevice.getDownloadPath();
        createTaskInfo.tasks = arrayList;
        HttpResponse httpPost = new XZBCreateTask(xZBDevice.getPid(), createTaskInfo).httpPost();
        if (httpPost.getStatusCode() != 200) {
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("createDownloadTask XZBCreateTask error errorcode = ").append(httpPost.getStatusCode()).toString());
            this.message = "create normal task request fail";
            this.result = httpPost.getStatusCode();
            return this.result;
        }
        String stringBody = httpPost.getStringBody();
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("createDownloadTask XZBCreateTask response = ").append(stringBody).toString());
        DownloadCreateTaskResponse parseResult = XZBCreateTask.parseResult(stringBody);
        if (parseResult == null) {
            XZBLog.d(XZBDevice.access$300(), "createDownloadTask XZBCreateTask response null ");
            this.message = "create normal task response null";
            this.result = -6;
            return this.result;
        }
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("createDownloadTask XZBCreateTask rtn = ").append(parseResult.getRtn()).toString());
        if (parseResult.getRtn() != 0) {
            this.message = parseResult.getMsg();
            this.result = parseResult.getRtn();
            return this.result;
        }
        List<CreateTask> tasks = parseResult.getTasks();
        if (tasks == null || tasks.isEmpty()) {
            this.message = "create normal task error";
            this.result = -7;
            return this.result;
        }
        i = 0;
        for (CreateTask createTask : tasks) {
            if (createTask != null) {
                this.urlRetDatas[i] = new UrlData();
                this.urlRetDatas[i].result = createTask.getResult();
                this.urlRetDatas[i].downloadUrl = createTask.getUrl();
                this.urlRetDatas[i].refrenceUrl = createTask.getUrl();
                this.urlRetDatas[i].errMsg = XZBDevice.getCreateTaskErrorMsg(this.urlRetDatas[i].result);
                i++;
            }
        }
        this.message = "create normal task success";
        this.result = 0;
        return this.result;
    }
}
