package com.xunlei.downloadprovider.vod;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.core.ThunderWebView;

public class VodPlayerForBtActivity extends ThunderTask {
    private static final String a;
    private ThunderWebView b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private com.xunlei.downloadprovider.a.h.a h;
    private b i;

    private class a extends AsyncTask<Void, Void, Void> {
        private final Handler b;
        private final String c;

        protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        }

        public a(String str, Handler handler) {
            this.b = handler;
            this.c = str;
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            if (this.c != null) {
                DownloadService.a();
                DownloadService.e();
            }
            if (this.b != null) {
                this.b.sendEmptyMessage(3001);
            }
            return null;
        }
    }

    public VodPlayerForBtActivity() {
        this.b = null;
        this.c = false;
        this.f = null;
        this.g = false;
        this.h = new am(this);
        this.i = new b(this.h);
    }

    static /* synthetic */ void a(VodPlayerForBtActivity vodPlayerForBtActivity, String str) {
        long j;
        vodPlayerForBtActivity.f = str;
        if (str == null || str.equals(com.umeng.a.d)) {
            j = -1;
        } else {
            j = DownloadService.a().a(str);
        }
        if (j != -1) {
            d.a();
            TaskInfo d = d.d(j);
            new a(d.mFilePath + d.mFileName, vodPlayerForBtActivity.i).execute(new Void[0]);
        } else if (str.startsWith("/")) {
            new a(str, vodPlayerForBtActivity.i).execute(new Void[0]);
        } else {
            vodPlayerForBtActivity.createLocalTask(str, null, 0, null, null, null, 0, new g(38, str, null), vodPlayerForBtActivity.i, false);
        }
    }

    static {
        a = VodPlayerForBtActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string = getIntent().getExtras().getString("cooperation_url");
        this.d = string;
        this.e = getIntent().getExtras().getString("cooperation_caller_packagename");
        setContentView(2130969040);
        this.b = (ThunderWebView) findViewById(2131757180);
        DownloadService.a().b(this.i);
        this.b.setJsCallbackMessageListener(this.h);
        String str = "m.sjzhushou.com/v2/detail/seed.html?";
        ThunderWebView thunderWebView = this.b;
        if (thunderWebView != null) {
            if (TextUtils.isEmpty(str)) {
                XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u94fe\u63a5\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a");
            } else {
                thunderWebView.a(str);
            }
        }
        this.b.postDelayed(new an(this, string), TabsImpl.SYNC_TIME_OUT);
    }

    protected void onResume() {
        super.onResume();
        if (!this.c) {
            this.i.sendMessageDelayed(this.i.obtainMessage(), 300);
            this.c = true;
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        if (this.b != null) {
            this.b.c();
        }
        if (this.i != null) {
            DownloadService.a().c(this.i);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        com.xunlei.downloadprovider.thirdpart.thirdpartycallplay.a.a(this.e);
        finish();
    }
}
