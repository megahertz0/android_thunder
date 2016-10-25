package com.mediav.ads.sdk.log;

import android.content.Context;
import com.mediav.ads.sdk.adcore.Config;
import com.sina.weibo.sdk.component.GameManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LogFileManager {
    private static String getLogPath() {
        File file = new File(Utils.getCacheDir(), Config.ERROR_LOG_FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                MVLog.e(new StringBuilder("create log file error:").append(e.getMessage()).toString());
                return null;
            }
        }
        return Utils.getCacheDir() + "/mvad_updatesdk_error";
    }

    public static ArrayList<JSONObject> getAllLogs() {
        ArrayList<JSONObject> arrayList = new ArrayList();
        try {
            String logPath = getLogPath();
            if (logPath != null) {
                FileInputStream fileInputStream = new FileInputStream(logPath);
                int available = fileInputStream.available();
                if (available > 0) {
                    byte[] bArr = new byte[available];
                    fileInputStream.read(bArr);
                    for (String str : EncodingUtils.getString(bArr, GameManager.DEFAULT_CHARSET).split("\n")) {
                        arrayList.add(new JSONObject(str));
                    }
                }
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return arrayList;
    }

    public static synchronized void saveLog(HashMap<String, String> hashMap) {
        synchronized (LogFileManager.class) {
            if (getLogPath() != null) {
                ArrayList arrayList;
                ArrayList allLogs = getAllLogs();
                int size = allLogs.size();
                if (size >= 50) {
                    arrayList = new ArrayList(allLogs.subList(size - 20, size));
                } else {
                    arrayList = allLogs;
                }
                arrayList.add(new JSONObject(hashMap));
                int size2 = arrayList.size();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < size2; i++) {
                    stringBuilder.append(((JSONObject) arrayList.get(i)).toString());
                    if (i != size2 - 1) {
                        stringBuilder.append("\n");
                    }
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(getLogPath());
                    fileOutputStream.write(stringBuilder.toString().getBytes());
                    fileOutputStream.close();
                } catch (Exception e) {
                    MVLog.e(e.getMessage());
                }
            }
        }
    }

    private static void clearLogs() {
        String logPath = getLogPath();
        if (logPath != null) {
            new File(logPath).delete();
        }
    }

    public static void uploadAllLogs(Context context) {
        ArrayList allLogs = getAllLogs();
        clearLogs();
        int size = allLogs.size();
        for (int i = 0; i < size; i++) {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = (JSONObject) allLogs.get(i);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    hashMap.put(str, jSONObject.getString(str));
                } catch (JSONException e) {
                    MVLog.e(new StringBuilder("Read Logs Error:").append(e.getMessage()).toString());
                }
            }
            LogUploader.postLog(hashMap, context, false);
        }
    }
}
