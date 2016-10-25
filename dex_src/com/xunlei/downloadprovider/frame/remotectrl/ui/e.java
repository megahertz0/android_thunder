package com.xunlei.downloadprovider.frame.remotectrl.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.UserAccountInfoActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.qrcode.CameraActivity;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: RemoteDownloadActivity.java
final class e implements a {
    final /* synthetic */ RemoteDownloadActivity a;

    e(RemoteDownloadActivity remoteDownloadActivity) {
        this.a = remoteDownloadActivity;
    }

    public final void a(Message message) {
        String str = null;
        String str2;
        Intent intent;
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                str = RemoteDownloadActivity.a;
                new StringBuilder("handle ADD_TASK_SUCCESS : time = ").append(System.currentTimeMillis());
                if (message.obj instanceof TaskInfo) {
                    TaskInfo taskInfo = (TaskInfo) message.obj;
                    RemoteDownloadActivity.a(taskInfo);
                    if (this.a.isBatch()) {
                        this.a.updateBatchDialog(true, message.arg1, taskInfo.mTaskId, taskInfo.mFileName);
                    }
                }
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                TaskInfo taskInfo2 = (TaskInfo) message.obj;
                if (this.a.isBatch()) {
                    this.a.updateBatchDialog(false, message.arg1, taskInfo2.mTaskId, taskInfo2.mFileName);
                } else {
                    this.a.handleTaskOperator(message.what, message.arg1, taskInfo2.mTaskId, taskInfo2);
                }
            case JsInterface.MSG_JS_OPEN_BROWSER_PAGE:
                str2 = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_OPEN_BROWSER_PAGE---").append(Thread.currentThread().getId());
                str2 = message.getData().getString(JsInterface.URL_KEY);
                BrowserUtil.a();
                BrowserUtil.b(this.a, str2, true, null);
            case JsInterface.MSG_JS_OPEN_DETAIL_PAGE:
                str = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_OPEN_DETAIL_PAGE---").append(Thread.currentThread().getId());
                str = message.getData().getString(JsInterface.URL_KEY);
                str2 = com.umeng.a.d;
                BrowserUtil.a();
                BrowserUtil.a(this.a, str, str2);
            case JsInterface.MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK:
                str = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK---").append(Thread.currentThread().getId());
                if (message.obj instanceof String) {
                    str = (String) message.obj;
                    new StringBuilder().append(getClass()).append("---MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK---callbackJson---").append(str).append(Thread.currentThread().getId());
                    RemoteDownloadActivity.a(this.a, str);
                }
            case JsInterface.MSG_JS_GO_TO_DOWNLOAD_LIST:
                str = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_GO_TO_DOWNLOAD_LIST---").append(Thread.currentThread().getId());
                DownloadCenterActivity.a(this.a, com.umeng.a.d);
            case JsInterface.MSG_JS_INSTALL_APK:
                str = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_INSTALL_APK---").append(Thread.currentThread().getId());
                str = (String) message.obj;
                str2 = RemoteDownloadActivity.a;
                if (str != null) {
                    try {
                        File file = new File(str);
                        if (file.exists()) {
                            Uri fromFile = Uri.fromFile(file);
                            intent = new Intent("android.intent.action.VIEW");
                            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                            intent.setFlags(268435456);
                            this.a.startActivity(intent);
                        }
                    } catch (Exception e) {
                        str2 = RemoteDownloadActivity.a;
                        new StringBuilder("open fail ").append(e.getMessage());
                    }
                }
            case JsInterface.MSG_JS_GO_TO_USER_INFO:
                str2 = RemoteDownloadActivity.a;
                new StringBuilder().append(getClass()).append("---handleMessage---MSG_JS_GO_TO_USER_INFO---").append(Thread.currentThread().getId());
                if (message.obj instanceof String) {
                    str = (String) message.obj;
                }
                intent = new Intent(this.a, UserAccountInfoActivity.class);
                intent.putExtra("callbackJson", str);
                this.a.startActivity(intent);
            case JsInterface.MSG_ADD_REMOTE_DEVICE_BY_QRCODE_SCAN:
                if (message.obj instanceof String) {
                    str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            this.a.f = new JSONObject(str).getString(com.alipay.sdk.authjs.a.c);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                StatReporter.reportQrcodeClick(LoginHelper.a().j);
                Intent intent2 = new Intent(this.a, CameraActivity.class);
                intent2.putExtra("remote_download", true);
                this.a.startActivityForResult(intent2, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            case JsInterface.MSG_BACK_TO_FIND_FROM_REMOTE_MAIN_PAGE:
                this.a.onBackPressed();
            case JsInterface.MSG_LOG_OUT_CALLBACK:
                if (message.obj instanceof String) {
                    str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            this.a.g = new JSONObject(str).getString(com.alipay.sdk.authjs.a.c);
                        } catch (JSONException e22) {
                            e22.printStackTrace();
                        }
                    }
                }
            default:
                break;
        }
    }
}
