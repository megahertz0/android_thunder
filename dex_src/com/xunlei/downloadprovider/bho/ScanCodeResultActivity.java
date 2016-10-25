package com.xunlei.downloadprovider.bho;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.businessutil.d;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.c;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScanCodeResultActivity extends ThunderTask {
    private long A;
    private boolean B;
    private boolean C;
    private boolean D;
    private a E;
    private com.xunlei.downloadprovider.a.h.a F;
    private com.xunlei.downloadprovider.a.h.b G;
    private RelativeLayout a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;
    private TextView e;
    private TextView f;
    private TextView g;
    private int h;
    private boolean i;
    private boolean j;
    private ProgressBar k;
    private TextView l;
    private Button m;
    private TextView n;
    private TextView o;
    private String p;
    private String q;
    private String r;
    private boolean s;
    private boolean t;
    private long u;
    private boolean v;
    private boolean w;
    private boolean x;
    private String y;
    private String z;

    static class a {
        public final List<DownData> a;
        public String b;

        a() {
            this.a = new ArrayList(1);
            this.b = com.umeng.a.d;
        }

        static a a(Intent intent) {
            Object stringExtra = intent.getStringExtra("urls");
            String stringExtra2 = intent.getStringExtra("partner_id");
            if (TextUtils.isEmpty(stringExtra)) {
                return null;
            }
            a aVar = new a();
            aVar.b = stringExtra2;
            try {
                JSONObject jSONObject = new JSONObject(stringExtra);
                String optString = jSONObject.optString(Impl.COLUMN_REFERER);
                JSONArray optJSONArray = jSONObject.optJSONArray("urls");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            Object optString2 = optJSONObject.optString(SocialConstants.PARAM_URL);
                            String optString3 = optJSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                            if (!TextUtils.isEmpty(optString2)) {
                                DownData downData = new DownData();
                                downData.e = optString2;
                                downData.s = optString;
                                downData.a = optString3;
                                aVar.a.add(downData);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return !aVar.a.isEmpty() ? aVar : null;
        }

        public final String toString() {
            return new StringBuilder("AddTaskActionParam{mDownloadInfos=").append(this.a).append(", mPartnerId='").append(this.b).append('\'').append('}').toString();
        }
    }

    static class b {
        static boolean a(String str) {
            try {
                String toLowerCase = new URL(str).getPath().toLowerCase();
                return toLowerCase.contains("apk") || toLowerCase.contains("torrent") || toLowerCase.contains("avi") || toLowerCase.contains("mov") || toLowerCase.contains("avi") || toLowerCase.contains("asf") || toLowerCase.contains("wmv") || toLowerCase.contains("mp4") || toLowerCase.contains("3gp") || toLowerCase.contains("mkv") || toLowerCase.contains("flv") || toLowerCase.contains("f4v") || toLowerCase.contains("rmvb") || toLowerCase.contains("rm") || toLowerCase.contains("asx") || toLowerCase.contains("mpg") || toLowerCase.contains("mpeg") || toLowerCase.contains("m4v") || toLowerCase.contains("wma") || toLowerCase.contains("mp3") || toLowerCase.contains("wav") || toLowerCase.contains("acc") || toLowerCase.contains("ape") || toLowerCase.contains("7z") || toLowerCase.contains("rar") || toLowerCase.contains("zip") || toLowerCase.contains("exe");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public ScanCodeResultActivity() {
        this.i = true;
        this.j = false;
        this.t = false;
        this.w = false;
        this.x = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = null;
        this.F = new a(this);
        this.G = new com.xunlei.downloadprovider.a.h.b(this.F);
    }

    static /* synthetic */ void d(ScanCodeResultActivity scanCodeResultActivity) {
        scanCodeResultActivity.k.setBackgroundResource(R.drawable.dlg_icon_fail);
        scanCodeResultActivity.l.setText("\u4e0b\u8f7d\u5730\u5740\u83b7\u53d6\u5931\u8d25,\u8bf7\u91cd\u8bd5");
        scanCodeResultActivity.c.setVisibility(XZBDevice.Wait);
        scanCodeResultActivity.b.setVisibility(0);
        scanCodeResultActivity.c.setVisibility(0);
    }

    static /* synthetic */ void k(ScanCodeResultActivity scanCodeResultActivity) {
        scanCodeResultActivity.k.setBackgroundResource(2131034134);
        ((AnimationDrawable) scanCodeResultActivity.k.getBackground()).start();
        scanCodeResultActivity.l.setText("\u6b63\u5728\u89e3\u6790\u4e0b\u8f7dURL...");
        scanCodeResultActivity.d.setVisibility(XZBDevice.Wait);
        scanCodeResultActivity.c.setVisibility(XZBDevice.Wait);
        scanCodeResultActivity.b.setVisibility(0);
    }

    protected void onCreate(Bundle bundle) {
        this.C = false;
        this.t = false;
        setContentView(2130968646);
        this.a = (RelativeLayout) findViewById(2131755410);
        this.b = (LinearLayout) findViewById(2131755418);
        findViewById(R.id.dlg_2btn_layout).setVisibility(0);
        this.e = (TextView) findViewById(2131755414);
        this.g = (TextView) findViewById(R.id.dlg_left_btn);
        this.f = (TextView) findViewById(R.id.dlg_right_btn);
        this.g.setOnClickListener(new f(this));
        this.f.setOnClickListener(new g(this));
        this.k = (ProgressBar) findViewById(2131755419);
        this.l = (TextView) findViewById(2131755420);
        this.m = (Button) findViewById(2131755422);
        this.m.setOnClickListener(new h(this));
        this.c = (LinearLayout) findViewById(2131755423);
        this.d = (LinearLayout) findViewById(2131755421);
        this.n = (TextView) findViewById(2131755424);
        this.o = (TextView) findViewById(2131755425);
        this.n.setOnClickListener(new i(this));
        this.o.setOnClickListener(new j(this));
        this.a.setVisibility(XZBDevice.Wait);
        this.b.setVisibility(XZBDevice.Wait);
        a(getIntent());
        super.onCreate(bundle);
        if (DownloadService.a() == null) {
            DownloadService.a(new e(this));
        }
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        a(intent);
    }

    private void a(Intent intent) {
        String dataString;
        Intent intent2 = getIntent();
        if (intent2 != null) {
            String action = intent2.getAction();
            if (action != null) {
                if (action.equals("android.intent.action.VIEW")) {
                    dataString = intent2.getDataString();
                    if (dataString == null || dataString.equals(com.umeng.a.d)) {
                        this.p = com.umeng.a.d;
                    } else {
                        this.p = dataString.replace("\r\n", com.umeng.a.d);
                        if (dataString.contains("#name=")) {
                            this.w = true;
                            int indexOf = dataString.indexOf("#name=");
                            this.p = dataString.substring(0, indexOf);
                            this.r = com.xunlei.xllib.b.b.a(dataString.substring(indexOf + 6), GameManager.DEFAULT_CHARSET);
                            StatReporter.reportOneAction("11002");
                        }
                        com.xunlei.downloadprovider.businessutil.d.a a = d.a(dataString);
                        if (a != null) {
                            this.A = a.e;
                            this.p = a.a;
                            this.y = a.c;
                            this.z = a.d;
                            this.r = a.b;
                            this.B = true;
                        }
                    }
                } else if (action.equals("com.xunlei.downloadprovider.ADD_TASK") || action.equals("com.xunlei.downloadprovide.ADD_TASK")) {
                    this.D = true;
                    this.p = intent2.getStringExtra(SocialConstants.PARAM_URL);
                    this.r = intent2.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                    if (TextUtils.isEmpty(this.p)) {
                        this.E = a.a(intent2);
                        if (this.E != null && this.E.a.size() > 0) {
                            this.p = ((DownData) this.E.a.get(0)).e;
                            this.r = ((DownData) this.E.a.get(0)).a;
                        }
                    }
                    if (this.r == null) {
                        this.r = com.umeng.a.d;
                    }
                }
            }
        }
        Uri data = intent.getData();
        if (data != null && "xunleiapp".equals(data.getScheme()) && "/sharePage".equals(data.getPath())) {
            new StringBuilder(" scanCode -------------- ").append(data.toString());
            this.p = data.getQueryParameter("taskDownload");
            if (TextUtils.isEmpty(this.p)) {
                XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u8be5\u94fe\u63a5\u5df2\u8fc7\u671f");
                finish();
                return;
            }
            this.r = data.getQueryParameter("fileNameDetail");
            if (this.r != null) {
                try {
                    this.r = URLDecoder.decode(this.r, "utf-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                this.r = com.umeng.a.d;
            }
            this.i = false;
            this.j = true;
            this.v = true;
        }
        dataString = this.p;
        if (dataString == null) {
            dataString = null;
        } else {
            indexOf = dataString.lastIndexOf("/");
            if (indexOf <= 0 || indexOf > dataString.length()) {
                dataString = null;
            } else {
                dataString = dataString.substring(indexOf + 1);
                if (!dataString.startsWith("thunder_un_")) {
                    dataString = null;
                }
            }
        }
        this.q = dataString;
        if (this.q == null) {
            if (!b.a(this.p)) {
                boolean z;
                dataString = this.p;
                if (dataString != null) {
                    if (dataString.startsWith("magnet")) {
                        z = true;
                    } else if (dataString.startsWith("thunder")) {
                        z = true;
                    } else if (dataString.startsWith("ed2k")) {
                        z = true;
                    } else if (dataString.contains("gcidtask.xunlei.com")) {
                        z = true;
                    }
                    if (!(z || this.w || this.D)) {
                        if (DownloadService.a() != null) {
                            this.C = true;
                            return;
                        }
                        this.C = false;
                        BrowserUtil.a();
                        BrowserUtil.a((Context) this, this.p, true, StartFromType.outside);
                        finish();
                        return;
                    }
                }
                z = false;
                if (DownloadService.a() != null) {
                    this.C = false;
                    BrowserUtil.a();
                    BrowserUtil.a((Context) this, this.p, true, StartFromType.outside);
                    finish();
                    return;
                }
                this.C = true;
                return;
            }
            b();
            this.a.setVisibility(0);
        } else if (c()) {
            c.a();
            c.a(this.q, this.G);
            this.G.sendEmptyMessageDelayed(XZBDevice.DOWNLOAD_LIST_RECYCLE, 1000);
        } else {
            this.k.setBackgroundResource(R.drawable.dlg_icon_fail);
            this.l.setText("\u65e0\u7f51\u7edc\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u540e\u91cd\u8bd5");
            this.c.setVisibility(XZBDevice.Wait);
            this.d.setVisibility(0);
            this.b.setVisibility(0);
        }
    }

    private void a() {
        this.f.setText("\u4e0b\u8f7d\u6587\u4ef6");
        this.h = 2130838928;
    }

    private void b() {
        if (this.D) {
            CharSequence charSequence;
            String str = this.r;
            if (TextUtils.isEmpty(this.r)) {
                str = this.p;
            }
            if (this.E != null && this.E.a.size() > 1) {
                charSequence = charSequence + " ... \u5171" + this.E.a.size() + "\u4e2a\u6587\u4ef6";
            }
            this.e.setText(charSequence);
            a();
        } else if (TextUtils.isEmpty(this.r)) {
            this.e.setText(this.p);
            a();
        } else {
            this.e.setText(this.r);
            a();
        }
    }

    private boolean c() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    static /* synthetic */ void t(ScanCodeResultActivity scanCodeResultActivity) {
        if (DownloadService.a() != null) {
            g gVar = new g(4, scanCodeResultActivity.p, null);
            gVar.d = "BHO/BHO";
            if (scanCodeResultActivity.t) {
                if (scanCodeResultActivity.w) {
                    if (scanCodeResultActivity.B) {
                        scanCodeResultActivity.createLocalTaskByGcid(scanCodeResultActivity.r, scanCodeResultActivity.A, scanCodeResultActivity.y, scanCodeResultActivity.z, null, 1, gVar, scanCodeResultActivity.G);
                    } else {
                        scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, scanCodeResultActivity.r, 0, null, null, null, 1, gVar, scanCodeResultActivity.G, false);
                    }
                } else if (scanCodeResultActivity.B) {
                    scanCodeResultActivity.createLocalTaskByGcid(scanCodeResultActivity.r, scanCodeResultActivity.A, scanCodeResultActivity.y, scanCodeResultActivity.z, null, 1, gVar, null);
                } else {
                    scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, null, 0, scanCodeResultActivity.p, null, null, 1, gVar, null, false);
                }
                StatReporter.reportOverallDownload("BHO");
                return;
            }
            if (scanCodeResultActivity.D) {
                if (scanCodeResultActivity.E == null || scanCodeResultActivity.E.a.isEmpty()) {
                    scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, null, 0, scanCodeResultActivity.p, null, null, 1, gVar, scanCodeResultActivity.G, false);
                } else {
                    scanCodeResultActivity.createTasks(XZBDevice.DOWNLOAD_LIST_ALL, scanCodeResultActivity.E.a, scanCodeResultActivity.G, XZBDevice.DOWNLOAD_LIST_ALL, null);
                }
            } else if (scanCodeResultActivity.w) {
                if (scanCodeResultActivity.B) {
                    scanCodeResultActivity.createLocalTaskByGcid(scanCodeResultActivity.r, scanCodeResultActivity.A, scanCodeResultActivity.y, scanCodeResultActivity.z, null, 1, gVar, scanCodeResultActivity.G);
                } else {
                    scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, scanCodeResultActivity.r, 0, null, null, null, 1, gVar, scanCodeResultActivity.G, false);
                }
            } else if (scanCodeResultActivity.B) {
                scanCodeResultActivity.createLocalTaskByGcid(scanCodeResultActivity.r, scanCodeResultActivity.A, scanCodeResultActivity.y, scanCodeResultActivity.z, null, 1, gVar, null);
            } else if (scanCodeResultActivity.j) {
                scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, scanCodeResultActivity.r, 0, scanCodeResultActivity.p, null, null, 1, gVar, scanCodeResultActivity.G, false);
            } else {
                scanCodeResultActivity.createLocalTask(scanCodeResultActivity.p, scanCodeResultActivity.r, 0, scanCodeResultActivity.p, null, null, 1, gVar, null, false);
            }
            String str = "BHO";
            if (scanCodeResultActivity.v) {
                str = "share_h5";
            }
            StatReporter.reportOverallDownload(str);
            return;
        }
        XLToast.a(scanCodeResultActivity.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, "\u6b63\u5728\u542f\u52a8\u670d\u52a1, \u8bf7\u7a0d\u5019...");
        if (scanCodeResultActivity.w) {
            scanCodeResultActivity.x = true;
        }
        if (scanCodeResultActivity.B) {
            scanCodeResultActivity.x = true;
        }
        scanCodeResultActivity.w = false;
        scanCodeResultActivity.B = false;
    }
}
