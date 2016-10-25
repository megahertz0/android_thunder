package com.xunlei.downloadprovider.download.center;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.download.center.widget.af;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.download.taskDetail.DownloadCenterDetailFragment;
import com.xunlei.downloadprovider.download.taskDetail.k;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.downloadprovidershare.data.ShareBean.OperationType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadCenterActivity extends ThunderTask implements com.xunlei.downloadprovider.download.taskDetail.DownloadCenterDetailFragment.a, com.xunlei.downloadprovidershare.d.a {
    DownloadCenterActivityFragment a;
    DownloadCenterDetailFragment b;
    public com.xunlei.downloadprovider.download.tasklist.a.a c;
    private Bundle d;
    private d e;
    private boolean f;
    private boolean g;
    private a h;
    private af i;
    private Bitmap j;
    private com.xunlei.downloadprovider.a.h.a k;
    private b l;

    private class a extends BroadcastReceiver {
        private a() {
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && "com.xunLei.downloadCenter.MoreOperate".equals(action)) {
                TaskRunningInfo a = h.a().a(((com.xunlei.downloadprovider.download.tasklist.a.a) intent.getSerializableExtra("taskInfo")).getTaskId());
                if (a != null) {
                    DownloadCenterActivity downloadCenterActivity = DownloadCenterActivity.this;
                    if (downloadCenterActivity.b != null) {
                        DownloadCenterDetailFragment downloadCenterDetailFragment = downloadCenterActivity.b;
                        if (a != null) {
                            downloadCenterDetailFragment.t = false;
                            DownloadCenterDetailFragment.a((com.xunlei.downloadprovider.download.tasklist.a.a) a);
                            downloadCenterDetailFragment.s = false;
                            downloadCenterDetailFragment.k = a;
                            com.xunlei.downloadprovider.service.downloads.task.d.a();
                            downloadCenterDetailFragment.l = com.xunlei.downloadprovider.service.downloads.task.d.i(a.mTaskId);
                            if (downloadCenterDetailFragment.l == null) {
                                downloadCenterDetailFragment.l = new c();
                                downloadCenterDetailFragment.l.g = a.mTaskId;
                            }
                            if (downloadCenterDetailFragment.p != null) {
                                downloadCenterDetailFragment.p.a(a, downloadCenterDetailFragment.l);
                            }
                            downloadCenterDetailFragment.c();
                            if (n.c(a)) {
                                downloadCenterDetailFragment.o.setCurrentItem(0, false);
                                downloadCenterDetailFragment.n.setVisibility(XZBDevice.Wait);
                                downloadCenterDetailFragment.o.setCanScroll(false);
                            } else {
                                downloadCenterDetailFragment.o.setCurrentItem(1, false);
                                downloadCenterDetailFragment.n.setVisibility(XZBDevice.Wait);
                                downloadCenterDetailFragment.o.setCanScroll(false);
                            }
                            downloadCenterDetailFragment.v.post(new k(downloadCenterDetailFragment));
                        }
                        DownloadCenterActivity.this.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                    }
                }
            }
        }
    }

    public DownloadCenterActivity() {
        this.f = false;
        this.g = false;
        this.h = new a();
        this.j = null;
        this.k = new d(this);
        this.l = new b(this.k);
    }

    public static void a(Context context, String str) {
        c(context, -1, str);
    }

    public static void a(Context context, long j, String str) {
        c(context, j, str);
    }

    public static void b(Context context, long j, String str) {
        a(context, j, str, true, null);
    }

    public static void a(Context context, long j, String str, TaskRunningInfo taskRunningInfo, String str2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_key_vodplay_taskinfo", taskRunningInfo);
        bundle.putString("extra_key_vodplay_from", str2);
        a(context, j, str, true, bundle);
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, DownloadCenterActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("is_from_uc", true);
        intent.putExtra("back_to_home_page", false);
        intent.putExtra("from", "other");
        context.startActivity(intent);
    }

    public static void a(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DownloadCenterActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("from", "BHO_SDK");
        intent.putExtra("sdk_arguments", bundle);
        intent.putExtra("back_to_home_page", true);
        context.startActivity(intent);
    }

    private static void c(Context context, long j, String str) {
        a(context, j, str, false, null);
    }

    private static void a(Context context, long j, String str, boolean z, Bundle bundle) {
        Intent intent = new Intent(context, DownloadCenterActivity.class);
        if (context instanceof Activity) {
            intent.setFlags(67108864);
        } else {
            intent.setFlags(268435456);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra(com.umeng.message.proguard.k.l, j);
        intent.putExtra("from", str);
        intent.putExtra("extra_key_should_open_detailpage", z);
        context.startActivity(intent);
        if ("alarmDialog".equals(str)) {
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.translate_alpha_in, R.anim.translate_alpha_out);
            }
        } else if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.translate_between_interface_right_in, R.anim.translate_between_interface_left_out);
        }
    }

    public void onBackPressed() {
        if (this.a != null && this.a.c) {
            this.a.b();
        } else if (this.b == null || !this.b.isVisible()) {
            try {
                super.onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        } else {
            DownloadCenterDetailFragment downloadCenterDetailFragment = this.b;
            if (downloadCenterDetailFragment.h) {
                downloadCenterDetailFragment.a(false);
            } else if (downloadCenterDetailFragment.isVisible()) {
                downloadCenterDetailFragment.b(true);
            }
        }
    }

    public void finish() {
        if (this.f) {
            RunningTaskInfo runningTaskInfo = (RunningTaskInfo) ((ActivityManager) BrothersApplication.a().getSystemService("activity")).getRunningTasks(1).get(0);
            if (runningTaskInfo.baseActivity.equals(runningTaskInfo.topActivity)) {
                MainTabActivity.a((Context) this, "thunder");
            }
        }
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public final void a() {
        this.a.b.a.setVisibility(0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("from");
        if (getIntent().hasExtra("back_to_home_page")) {
            this.f = getIntent().getBooleanExtra("back_to_home_page", false);
        } else if (!(stringExtra == null || stringExtra.equals("other") || stringExtra.equals("alarmDialog") || this.f)) {
            this.f = true;
        }
        if ("from_running_noti".equals(stringExtra)) {
            com.xunlei.downloadprovider.download.report.a.a("download_in", "in_video");
        }
        if ("from_bxbb_noti_bar".equals(stringExtra)) {
            com.xunlei.downloadprovider.download.report.a.a("download_in_bxbb", "in_video_bxbb");
        }
        if ("sniff".equals(stringExtra)) {
            m.a().b = true;
        }
        g.a();
        g.g();
        com.xunlei.downloadprovider.download.tasklist.list.a.n.a();
        com.xunlei.downloadprovider.download.tasklist.list.a.n.d = null;
        setContentView(2130968607);
        b(getIntent());
        a(getIntent());
        this.a = (DownloadCenterActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        long longExtra = getIntent().getLongExtra(com.umeng.message.proguard.k.l, -1);
        boolean booleanExtra = getIntent().getBooleanExtra("extra_key_should_open_detailpage", false);
        if (this.a == null) {
            this.a = new DownloadCenterActivityFragment();
            Bundle arguments = this.a.getArguments();
            if (arguments == null) {
                arguments = new Bundle();
                this.a.setArguments(arguments);
            }
            arguments.putLong(com.umeng.message.proguard.k.l, longExtra);
            arguments.putString("from", getIntent().getStringExtra("from"));
            arguments.putBoolean("extra_key_should_open_detailpage", booleanExtra);
        }
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.a);
        beginTransaction.commitAllowingStateLoss();
        this.b = (DownloadCenterDetailFragment) getSupportFragmentManager().findFragmentById(2131755210);
        try {
            this.b.getView().setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        com.xunlei.downloadprovider.download.c.a.a().a(false);
        if (getIntent().getBooleanExtra("extra_key_should_open_detailpage", false)) {
            TaskRunningInfo a = h.a().a(longExtra);
            if (a != null) {
                com.xunlei.downloadprovider.download.a.a.a((Context) this, a);
            }
        }
        c(getIntent());
        if (!this.g) {
            this.g = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xunLei.downloadCenter.MoreOperate");
            registerReceiver(this.h, intentFilter);
        }
    }

    protected void onNewIntent(Intent intent) {
        Bundle bundle;
        super.onNewIntent(intent);
        b(intent);
        a(intent);
        boolean booleanExtra = intent.getBooleanExtra("extra_key_should_open_detailpage", false);
        DownloadCenterActivityFragment downloadCenterActivityFragment = (DownloadCenterActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        long longExtra = intent.getLongExtra(com.umeng.message.proguard.k.l, -1);
        Bundle arguments = downloadCenterActivityFragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            downloadCenterActivityFragment.setArguments(arguments);
            bundle = arguments;
        } else {
            bundle = arguments;
        }
        bundle.putLong(com.umeng.message.proguard.k.l, longExtra);
        bundle.putBoolean("extra_key_should_open_detailpage", false);
        String stringExtra = intent.getStringExtra("from");
        if ("from_running_noti".equals(stringExtra)) {
            com.xunlei.downloadprovider.download.report.a.a("download_in", "in_video");
        }
        if ("from_bxbb_noti_bar".equals(stringExtra)) {
            com.xunlei.downloadprovider.download.report.a.a("download_in_bxbb", "in_video_bxbb");
        }
        if (booleanExtra) {
            TaskRunningInfo a = h.a().a(longExtra);
            if (a != null) {
                com.xunlei.downloadprovider.download.a.a.a((Context) this, a);
            }
        }
        c(intent);
    }

    public void onResume() {
        super.onResume();
        if (DownloadService.a() == null) {
            DownloadService.a(null);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.h);
        com.xunlei.downloadprovider.download.tasklist.list.c.g.a = false;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    private void a(Intent intent) {
        DLCenterEntry dLCenterEntry = DLCenterEntry.other;
        String stringExtra = intent.getStringExtra("from");
        try {
            String str;
            String str2;
            if (TextUtils.isEmpty(stringExtra) || !stringExtra.endsWith("_noti")) {
                if (!TextUtils.isEmpty(stringExtra)) {
                    DLCenterEntry valueOf = DLCenterEntry.valueOf(stringExtra);
                    if (valueOf != null) {
                        stringExtra = valueOf.toString();
                    }
                }
                if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("task_free_trial_notify")) {
                    com.xunlei.downloadprovider.download.report.a.e();
                }
                str = com.umeng.a.d;
                str2 = com.umeng.a.d;
                if (this.d != null) {
                    str2 = this.d.getString("app_id");
                    str = this.d.getString("partner_id");
                }
                com.xunlei.downloadprovider.download.report.a.a(stringExtra, str, str2);
            }
            stringExtra = DLCenterEntry.download_push.toString();
            com.xunlei.downloadprovider.download.report.a.e();
            str = com.umeng.a.d;
            str2 = com.umeng.a.d;
            if (this.d != null) {
                str2 = this.d.getString("app_id");
                str = this.d.getString("partner_id");
            }
            com.xunlei.downloadprovider.download.report.a.a(stringExtra, str, str2);
        } catch (Exception e) {
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.xunlei.downloadprovidershare.d.b().a(i, i2, intent);
    }

    private void b(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("sdk_arguments");
        if (bundleExtra != null) {
            this.d = bundleExtra;
            List parcelableArrayList = bundleExtra.getParcelableArrayList("tasks");
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                DownData downData = (DownData) parcelableArrayList.get(0);
                com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(4, downData.e, null);
                gVar.d = "BHO/BHO_SDK";
                if (parcelableArrayList.size() > 1) {
                    createTasks(XZBDevice.DOWNLOAD_LIST_ALL, parcelableArrayList, this.l, 4, StartFromType.bho_sdk);
                } else {
                    createTask(downData, this.l, gVar, false);
                }
                StatReporter.reportOverallDownload("BHO_SDK");
            }
        }
    }

    private void c(Intent intent) {
        TaskRunningInfo taskRunningInfo = (TaskRunningInfo) intent.getSerializableExtra("extra_key_vodplay_taskinfo");
        String stringExtra = intent.getStringExtra("extra_key_vodplay_from");
        if (taskRunningInfo != null) {
            com.xunlei.downloadprovider.download.a.a.a(this, taskRunningInfo.mFilePath, taskRunningInfo.mFileName, taskRunningInfo.mTaskId, -1, taskRunningInfo.mCID, taskRunningInfo.mGCID, stringExtra);
        }
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        String a = ba.a(share_media, shareBean);
        if (shareBean.m == OperationType.None) {
            com.xunlei.downloadprovider.frame.user.a.a().a(String.valueOf(LoginHelper.a().j));
        } else if (shareBean.m == OperationType.Qr) {
            String str = shareBean.a;
            if (this.i != null) {
                this.i.dismiss();
            }
            this.j = null;
            try {
                this.j = new com.xunlei.downloadprovider.download.util.m().a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            runOnUiThread(new a(this));
        }
        com.xunlei.downloadprovider.download.report.a.c(a, shareBean.e);
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        com.xunlei.downloadprovider.download.report.a.d(ba.a(share_media, shareBean), com.xunlei.downloadprovidershare.d.a(i), shareBean.e);
    }

    static /* synthetic */ void d(DownloadCenterActivity downloadCenterActivity) {
        String str = MessageService.MSG_DB_READY_REPORT;
        String str2 = MessageService.MSG_DB_READY_REPORT;
        if (downloadCenterActivity.d != null) {
            str = downloadCenterActivity.d.getString("app_id");
            str2 = downloadCenterActivity.d.getString("partner_id");
        }
        com.xunlei.downloadprovider.service.downloads.a.a.a("BHO_SDK", str, str2);
    }

    static /* synthetic */ void e(DownloadCenterActivity downloadCenterActivity) {
        if (downloadCenterActivity.e != null && downloadCenterActivity.e.isShowing()) {
            downloadCenterActivity.e.dismiss();
        }
        Object obj = com.umeng.a.d;
        Object obj2 = com.umeng.a.d;
        if (downloadCenterActivity.d != null) {
            String string = downloadCenterActivity.d.getString("sdk_key");
            if (string != null) {
                try {
                    JSONObject optJSONObject = new JSONObject(string).optJSONObject("hostApp");
                    obj = optJSONObject.optString("appLabel");
                    obj2 = optJSONObject.optString(JsInterface.KEY_APK_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(obj2) && !TextUtils.isEmpty(obj)) {
                OnClickListener cVar = new c(downloadCenterActivity, obj2);
                downloadCenterActivity.e = new d(downloadCenterActivity);
                downloadCenterActivity.e.b((CharSequence) "\u521b\u5efa\u4efb\u52a1\u6210\u529f");
                downloadCenterActivity.e.c(new StringBuilder("\u8fd4\u56de").append(obj).toString());
                downloadCenterActivity.e.d("\u7559\u5728\u8fc5\u96f7");
                downloadCenterActivity.e.a(cVar);
                downloadCenterActivity.e.show();
            }
        }
    }
}
