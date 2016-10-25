package com.baidu.mobads.interfaces.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public interface IXAdPackageUtils {

    public static class ApkInfo {
        private PackageInfo a;
        public final String appName;
        public final String packageName;
        public final int versionCode;
        public final String versionName;

        public ApkInfo(Context context, PackageInfo packageInfo) {
            this.a = packageInfo;
            this.packageName = packageInfo.packageName;
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
            this.appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("p", this.packageName);
                jSONObject.put(IXAdRequestInfo.V, this.versionName);
                jSONObject.put("c", this.versionCode);
                jSONObject.put("s", new File(this.a.applicationInfo.sourceDir).lastModified());
            } catch (JSONException e) {
            }
            return jSONObject;
        }

        public JSONObject toRecentJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("p", this.packageName);
                jSONObject.put(IXAdRequestInfo.V, this.versionName);
                jSONObject.put("c", this.versionCode);
            } catch (JSONException e) {
            }
            return jSONObject;
        }
    }

    int getAppVersion(Context context);

    Intent getInstallIntent(String str);

    ApkInfo getLocalApkFileInfo(Context context, String str);

    boolean isForeground(Context context, String str);

    boolean isInstalled(Context context, String str);

    boolean isSystemPackage(PackageInfo packageInfo);

    void openApp(Context context, String str);

    boolean sendAPOInfo(Context context, String str, String str2, int i, int i2);

    void sendAPOIsSuccess(Context context, boolean z, int i, String str, String str2);

    void sendDialerIsSuccess(Context context, boolean z, int i, String str);
}
