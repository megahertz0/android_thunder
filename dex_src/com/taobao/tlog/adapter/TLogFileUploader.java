package com.taobao.tlog.adapter;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.common.Constants;
import com.taobao.tao.log.TLogConstant;
import com.taobao.tao.log.TLogUtils;
import com.taobao.tao.log.collect.LogFileUploadManager;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import java.util.HashMap;
import java.util.Map;

public class TLogFileUploader {
    private static boolean isValid;

    static {
        isValid = false;
        try {
            Class.forName("com.taobao.tao.log.TLog");
            isValid = true;
        } catch (ClassNotFoundException e) {
            isValid = false;
        }
    }

    public static void uploadLogFile(Context context, Map<String, Object> map) {
        uploadLogFile(context, map, null);
    }

    public static void uploadLogFile(Context context, Map<String, Object> map, String str) {
        if (isValid) {
            LogFileUploadManager instances = LogFileUploadManager.getInstances(context);
            if (str == null) {
                instances.addFiles(TLogUtils.getFilePath(TLogConstant.FILE_PREFIX, 1, null));
            } else {
                instances.addFiles(TLogUtils.getFilePath(str, 1, null));
            }
            instances.setType("client");
            instances.setExtData(map);
            Map hashMap = new HashMap();
            hashMap.put("userId", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            hashMap.put(Constants.KEY_SERVICE_ID, "motu-remote");
            hashMap.put("serialNumber", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            hashMap.put(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            instances.setReportParams(hashMap);
            instances.startUpload();
        }
    }
}
