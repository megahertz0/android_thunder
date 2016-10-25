package com.xunlei.downloadprovider.ucaddon;

import android.net.Uri;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.AbstractEventReceiver;
import com.uc.addon.sdk.remote.Browser;
import com.uc.addon.sdk.remote.EventBase;
import com.uc.addon.sdk.remote.EventDownload;
import com.uc.addon.sdk.remote.protocol.BannerBuilderRemote;
import com.uc.addon.sdk.remote.protocol.BannerClickListener;
import com.uc.addon.sdk.remote.protocol.DialogBuilder;
import com.uc.addon.sdk.remote.protocol.ToastBuilder;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadEngine;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.c;
import com.xunlei.downloadprovider.web.w;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UcAddonEventRecevier extends AbstractEventReceiver {
    private b mHandler;
    private a mMessageListener;
    private BannerClickListener mTaskAddSuccessClkListener;

    public UcAddonEventRecevier() {
        this.mMessageListener = new a(this);
        this.mTaskAddSuccessClkListener = new b(this);
        this.mHandler = new b(this.mMessageListener);
    }

    private void onTaskAddSuccess(TaskInfo taskInfo) {
        String str = taskInfo.mFileName;
        Browser browser = getBrowser();
        BannerBuilderRemote bannerBuilderRemote = new BannerBuilderRemote();
        int length = str.length();
        if (length > 18) {
            String substring = str.substring(0, SimpleLog.LOG_LEVEL_FATAL);
            str = substring + "..." + str.substring(length - 6);
        }
        if (!str.equals(BuildConfig.VERSION_NAME)) {
            str = str + " \r\n";
        }
        bannerBuilderRemote.addTextLabel(str + "\u64cd\u4f5c\u6210\u529f", 0);
        bannerBuilderRemote.addButton("\u67e5\u770b", 123);
        bannerBuilderRemote.setBannerClickListener(this.mTaskAddSuccessClkListener);
        try {
            browser.util.showBanner(bannerBuilderRemote);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onTaskAddFail(TaskInfo taskInfo, int i) {
        String str;
        if (i == 102409) {
            str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u4efb\u52a1\u5df2\u5b58\u5728!";
        } else if (i == 13) {
            if (k.d()) {
                str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u62d2\u7edd\u8bbf\u95ee!";
            } else {
                str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25\uff0cSD\u5361\u4e0d\u5b58\u5728\u4e86!";
            }
        } else if (i == 3173) {
            str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u78c1\u76d8\u7a7a\u95f4\u4e0d\u8db3";
        } else if (i == 102439 || i == 102448) {
            str = "\u94fe\u63a5\u8f93\u5165\u4e0d\u5b8c\u6574\u6216\u4e0d\u51c6\u786e";
        } else if (i == 102416) {
            str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u6587\u4ef6\u5df2\u7ecf\u5b58\u5728!";
        } else {
            str = "\u521b\u5efa\u4efb\u52a1\u5931\u8d25,\u4e0d\u53ef\u7528\u7684url!";
        }
        getBrowser().util.showToast(ToastBuilder.makeText(str, 0));
    }

    private void handleTaskFinishedTorrentFile() {
        Browser browser = getBrowser();
        DialogBuilder dialogBuilder = new DialogBuilder();
        dialogBuilder.setTitle(getApplicationContext().getString(R.string.uc_task_success_tips_title));
        dialogBuilder.setMessage(getApplicationContext().getString(R.string.uc_task_success_tips_content));
        dialogBuilder.setPositiveButton(getApplicationContext().getString(R.string.uc_task_success_tips_pos_btn), new c(this));
        dialogBuilder.setNegativeButton(getApplicationContext().getString(R.string.uc_task_success_tips_neg_btn), new d(this));
        try {
            browser.util.showDialog(dialogBuilder);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onTaskStatusChanged(TaskInfo taskInfo) {
        if (taskInfo.mTaskStatus == 8) {
            taskInfo.mFileName.toLowerCase().endsWith(".torrent");
        }
    }

    public void onEvent(int i, EventBase eventBase) {
        if (i == 1300 && eventBase != null && (eventBase instanceof EventDownload)) {
            EventDownload eventDownload = (EventDownload) eventBase;
            if (eventDownload != null) {
                String str = eventDownload.url;
                String str2 = eventDownload.filename;
                String str3 = eventDownload.path;
                if (DownloadService.a() == null) {
                    DownloadService.a(new e(this, str, str2, str3));
                } else {
                    onDonwloadServicePrepared(str, str2, str3);
                }
            }
        }
    }

    private void onDonwloadServicePrepared(String str, String str2, String str3) {
        DownloadService.a().b(this.mHandler);
        w.a();
        String a = w.a("UCCall", "down", str2, str);
        w.a();
        w.b(a);
        if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            DownloadBtFileExplorerActivity.startSelf(getApplicationContext(), Uri.fromFile(new File(str)).toString(), SpdyProtocol.PUBKEY_PSEQ_ADASH, "BHO/outer_app_click_resource_download_url");
        } else {
            String str4 = null;
            if (!(str2 == null || str2.equals(BuildConfig.VERSION_NAME))) {
                str4 = str2;
            }
            if (com.xunlei.downloadprovider.a.b.i() >= 19) {
                DownloadService.a().a(str, str4, 2057, "BHO/outer_app_click_resource_download_url", this.mHandler);
            } else {
                DownloadService a2 = DownloadService.a();
                b bVar = this.mHandler;
                str4 = DownloadService.b(str4);
                com.xunlei.downloadprovider.notification.a.a(a2).g = true;
                DownloadEngine downloadEngine = a2.d;
                c cVar = new c();
                cVar.a(str, str4, "BHO/outer_app_click_resource_download_url", str3);
                cVar.c = bVar;
                downloadEngine.a(cVar, bVar);
            }
        }
        StatReporter.reportDownloadFromUc();
        StatReporter.reportOverallDownload("outer_app_click_resource_download_url");
    }

    private void startToThunder() {
        try {
            DownloadCenterActivity.a(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
