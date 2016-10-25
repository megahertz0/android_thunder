package com.xunlei.downloadprovider.download.create;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.ui.FileManageView;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public class CreateBtTask extends BaseActivity implements OnClickListener {
    private static ArrayList<String> a;
    private boolean b;
    private a c;
    private String d;
    private boolean e;
    private int f;
    private b g;
    private int h;
    private volatile boolean i;
    private f j;
    private RelativeLayout k;
    private TextView l;
    private TextView m;
    private RelativeLayout n;
    private TextView o;
    private View p;
    private View q;
    private TextView r;
    private FileManageView s;
    private String t;
    private String u;
    private String v;
    private final FileFilter w;
    private int x;

    private static final class a extends Handler {
        private WeakReference<CreateBtTask> a;

        public a(CreateBtTask createBtTask) {
            this.a = null;
            this.a = new WeakReference(createBtTask);
        }

        public final void handleMessage(Message message) {
            CreateBtTask createBtTask = (CreateBtTask) this.a.get();
            if (createBtTask != null && !createBtTask.isFinishing()) {
                switch (message.what) {
                    case com.taobao.accs.data.Message.FLAG_ERR:
                        createBtTask.k.getVisibility();
                    case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                        createBtTask.l.setText(createBtTask.getString(2131230912));
                        createBtTask.m.setText(createBtTask.getString(2131230913, new Object[]{Integer.valueOf(createBtTask.f)}));
                    case InputDeviceCompat.SOURCE_TOUCHSCREEN:
                        createBtTask.l.setText(createBtTask.getString(2131230912) + ((String) message.obj));
                        createBtTask.m.setText(createBtTask.getString(2131230913, new Object[]{Integer.valueOf(message.arg1)}));
                    case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                        if (createBtTask.h != 0) {
                            createBtTask.g = null;
                            if (createBtTask.b) {
                                createBtTask.b = false;
                            }
                            createBtTask.k.setVisibility(XZBDevice.Wait);
                            createBtTask.j.n.setVisibility(0);
                            createBtTask.a(1);
                            if (a.isEmpty()) {
                                createBtTask.n.setVisibility(0);
                            }
                            createBtTask.s.c();
                            createBtTask.s.b(a);
                        }
                    default:
                        break;
                }
            }
        }
    }

    private final class b extends Thread {
        private b() {
        }

        public final void run() {
            a = CreateBtTask.this.a(CreateBtTask.m(CreateBtTask.this));
            CreateBtTask.this.c.removeMessages(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            CreateBtTask.this.c.sendEmptyMessage(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
    }

    public CreateBtTask() {
        this.b = true;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = 0;
        this.g = null;
        this.h = -1;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.r = null;
        this.s = null;
        this.t = k.b();
        this.u = k.c();
        this.v = null;
        this.w = new a(this);
        this.x = 0;
    }

    static {
        a = null;
    }

    protected void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        a(getIntent(), false);
        setContentView(2130968641);
        this.j = new f((Activity) this);
        this.j.g.setImageResource(R.drawable.common_back_icon_selector);
        this.j.i.setText(2131231128);
        this.j.n.setImageResource(2130837694);
        this.j.n.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.k = (RelativeLayout) findViewById(2131755388);
        this.l = (TextView) findViewById(2131755391);
        this.m = (TextView) findViewById(2131755390);
        this.l.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.m.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.n = (RelativeLayout) findViewById(2131755393);
        this.o = (TextView) findViewById(2131755392);
        this.o.setTextColor(-12171706);
        this.o.setOnClickListener(this);
        this.q = findViewById(2131755396);
        this.q.setOnClickListener(this);
        this.p = findViewById(2131755395);
        this.p.setOnClickListener(this);
        this.j.g.setOnClickListener(this);
        this.j.n.setOnClickListener(this);
        String str2 = this.v;
        this.r = (TextView) findViewById(2131755385);
        this.s = (FileManageView) findViewById(2131755387);
        this.s.setPathView(this.r);
        this.s.setCanEdit(false);
        this.s.setNeedMoreInfo(false);
        if (this.u == null || this.u.length() == 0 || k.a(this.u) == 0) {
            this.s.setLimitInDirectory(this.t);
            if (str2 == null) {
                str = this.t;
            }
            str = str2;
        } else if (this.t == null || this.t.length() == 0 || k.a(this.t) == 0) {
            this.s.setLimitInDirectory(this.u);
            if (str2 == null) {
                str = this.u;
            }
            str = str2;
        } else {
            FileManageView fileManageView = this.s;
            List arrayList = new ArrayList();
            File file = new File(this.t);
            Map hashMap = new HashMap();
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, new StringBuilder("/").append(getString(2131231973)).toString());
            hashMap.put("fileName", getString(2131231974));
            hashMap.put("info", file.getAbsolutePath());
            if (file.isDirectory()) {
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(0));
            } else {
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(1));
            }
            arrayList.add(hashMap);
            if (this.u != null) {
                file = new File(this.u);
                hashMap = new HashMap();
                hashMap.put(WebBrowserActivity.EXTRA_TITLE, new StringBuilder("/").append(getString(2131232398)).toString());
                hashMap.put("fileName", getString(2131232399));
                hashMap.put("info", file.getAbsolutePath());
                if (file.isDirectory()) {
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(0));
                } else {
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(1));
                }
                arrayList.add(hashMap);
            }
            fileManageView.d = arrayList;
            fileManageView.e = false;
            this.s.setLimitInDirectory("///homepage");
            if (str2 == null) {
                str = "///homepage";
            }
            str = str2;
        }
        FileManageView fileManageView2 = this.s;
        String str3 = ".torrent";
        if ("///homepage".equals(str)) {
            fileManageView2.i = 1003;
            fileManageView2.f = null;
        } else if (fileManageView2.h == null || !c.a("///homepage", fileManageView2.h)) {
            fileManageView2.i = 1000;
        } else {
            fileManageView2.i = 1003;
            fileManageView2.f = str;
        }
        fileManageView2.b = str;
        fileManageView2.c = str3;
        fileManageView2.setFadingEdgeLength(0);
        fileManageView2.setScrollingCacheEnabled(false);
        fileManageView2.a = fileManageView2.getData();
        fileManageView2.g = new com.xunlei.downloadprovider.app.ui.FileManageView.b();
        fileManageView2.setAdapter(fileManageView2.g);
        fileManageView2.a();
        fileManageView2.setOnItemClickListener(fileManageView2);
        fileManageView2.setOnItemLongClickListener(fileManageView2);
        fileManageView2.f = null;
        this.s.setOnFileOperateListener(new b(this));
        this.f = 0;
        if (a == null) {
            a = new ArrayList();
        } else if (!a.isEmpty()) {
            this.b = false;
        }
        this.c = new a(this);
        a(0);
    }

    protected void onNewIntent(Intent intent) {
        this.e = false;
        setIntent(intent);
        a(intent, true);
    }

    public void onResume() {
        super.onResume();
        if (DownloadService.a() != null && this.d != null && !this.e) {
            DownloadBtFileExplorerActivity.startSelf(this, Uri.fromFile(new File(this.d)).toString(), XZBDevice.Pause, null);
            this.e = true;
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    protected void onDestroy() {
        super.onDestroy();
        a(false);
        this.c.removeMessages(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        this.c.removeMessages(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    public void onBackPressed() {
        boolean z;
        int i;
        if (this.h == 0) {
            FileManageView fileManageView = this.s;
            if (fileManageView.b()) {
                fileManageView.a((String) fileManageView.a(new File(fileManageView.b)).get("info"));
                i = 1;
            } else {
                if (fileManageView.j && fileManageView.j) {
                    fileManageView.j = false;
                    fileManageView.setAllItemSelectedState(false);
                    fileManageView.g.notifyDataSetChanged();
                }
                z = false;
            }
            if (z) {
                z = false;
            } else {
                i = 1;
            }
        } else {
            i = 1;
        }
        if (z) {
            finish();
        }
    }

    private void a(Intent intent, boolean z) {
        this.v = intent.getStringExtra("last_torrent_open_path");
        Uri data = intent.getData();
        if (data != null) {
            this.d = data.getPath();
            if (!this.d.substring(this.d.lastIndexOf(".")).equalsIgnoreCase(".torrent")) {
                if (z) {
                    moveTaskToBack(true);
                } else {
                    finish();
                }
            }
        }
    }

    private void a(int i) {
        if (this.h != i) {
            if (this.x == 0) {
                this.x = g.a(getApplicationContext(), TitleBar.SHAREBTN_RIGHT_MARGIN);
            }
            this.h = i;
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.r.setVisibility(0);
                    this.p.setBackgroundResource(2130837708);
                    this.q.setBackgroundResource(2130837709);
                    this.n.setVisibility(XZBDevice.Wait);
                    this.j.n.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.r.setVisibility(XZBDevice.Wait);
                    this.p.setBackgroundResource(2130837709);
                    this.q.setBackgroundResource(2130837708);
                    if (!this.b) {
                        this.j.n.setVisibility(0);
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.s.c();
                default:
                    break;
            }
        }
    }

    private ArrayList<String> a(List<String> list) {
        ArrayList<String> arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size && !Thread.currentThread().isInterrupted() && this.i; i++) {
            String str = (String) list.get(i);
            if (str != null && str.length() != 0 && !Thread.currentThread().isInterrupted() && this.i && !str.contains(com.xunlei.downloadprovider.businessutil.a.f())) {
                File file = new File(str);
                if (file.exists() && !file.getName().startsWith(".")) {
                    if (file.isDirectory()) {
                        File[] listFiles = file.listFiles(this.w);
                        if (listFiles != null && listFiles.length > 0) {
                            int i2 = 0;
                            while (i2 < listFiles.length && !Thread.currentThread().isInterrupted() && this.i) {
                                if (listFiles[i2].isDirectory()) {
                                    if (!listFiles[i2].getName().startsWith(".") && !listFiles[i2].getName().equals("com.android.fileexplorer")) {
                                        List arrayList2 = new ArrayList();
                                        arrayList2.add(listFiles[i2].getPath());
                                        Collection a = a(arrayList2);
                                        if (!a.isEmpty()) {
                                            arrayList.addAll(a);
                                        }
                                    }
                                } else if (listFiles[i2].isFile() && listFiles[i2].getName().endsWith(".torrent")) {
                                    arrayList.add(listFiles[i2].getPath());
                                    this.f++;
                                }
                                i2++;
                            }
                        }
                        this.c.obtainMessage(InputDeviceCompat.SOURCE_TOUCHSCREEN, this.f, 0, file.getPath()).sendToTarget();
                    } else if (file.isFile() && file.getName().toLowerCase(Locale.getDefault()).endsWith(".torrent")) {
                        arrayList.add(file.getPath());
                    }
                }
            }
        }
        return arrayList;
    }

    private void b() {
        if (this.g == null) {
            this.f = 0;
            this.c.sendEmptyMessage(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            this.g = new b();
            this.g.start();
            this.i = true;
        }
    }

    private void a(boolean z) {
        if (!(this.g == null || this.g.isInterrupted())) {
            this.g.interrupt();
            if (!z) {
                this.k.setVisibility(XZBDevice.Wait);
            }
        }
        this.i = false;
        this.g = null;
        this.f = a.size();
        if (z) {
            this.k.setVisibility(XZBDevice.Wait);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755392:
                a(true);
                a(1);
            case 2131755395:
                com.xunlei.downloadprovider.download.report.a.j("task_create");
                if (this.h == 1 || this.h == 2) {
                    a(false);
                    a(0);
                    this.s.c();
                    this.s.setNeedMoreInfo(false);
                    if (this.u == null || this.u.length() == 0 || k.a(this.u) == 0) {
                        this.s.a(this.t, ".torrent");
                    } else if (this.t == null || this.t.length() == 0 || k.a(this.t) == 0) {
                        this.s.a(this.u, ".torrent");
                    } else {
                        this.s.a("///homepage", ".torrent");
                    }
                }
            case 2131755396:
                com.xunlei.downloadprovider.download.report.a.j("task_auto");
                if (this.h == 0) {
                    a(1);
                    this.s.setNeedMoreInfo(true);
                    if (!this.b) {
                        if (a.isEmpty()) {
                            this.n.setVisibility(0);
                        }
                        this.s.a(a);
                    } else if (a.isEmpty()) {
                        this.s.a(a);
                        this.n.setVisibility(XZBDevice.Wait);
                        this.c.sendEmptyMessage(com.taobao.accs.data.Message.FLAG_ERR);
                        this.k.setVisibility(0);
                        this.j.n.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                        a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                        b();
                    } else {
                        this.n.setVisibility(XZBDevice.Wait);
                        this.j.n.setVisibility(0);
                        this.b = false;
                        this.s.a(a);
                    }
                }
            case com.xunlei.xiazaibao.R.id.titlebar_left:
                finish();
            case R.id.titlebar_right_iv:
                this.b = false;
                this.j.n.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                this.k.setVisibility(0);
                this.n.setVisibility(XZBDevice.Wait);
                this.s.a(a);
                a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                this.c.sendEmptyMessage(com.taobao.accs.data.Message.FLAG_ERR);
                b();
            default:
                break;
        }
    }

    static /* synthetic */ void a(String str) {
        Editor edit = com.xunlei.downloadprovider.businessutil.b.a().a.getSharedPreferences("settingstate", 0).edit();
        edit.putString("last_torrent_open_path", str);
        edit.commit();
    }

    static /* synthetic */ ArrayList m(CreateBtTask createBtTask) {
        Object obj = 1;
        ArrayList arrayList = new ArrayList();
        if (createBtTask.u == null || createBtTask.u.length() == 0 || k.a(createBtTask.u) <= 0) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (createBtTask.t == null || createBtTask.t.length() == 0 || k.a(createBtTask.t) <= 0) {
            obj = null;
        }
        if (obj2 == null || obj == null) {
            if (obj != null) {
                arrayList.add(createBtTask.t);
            } else if (obj2 != null) {
                arrayList.add(createBtTask.u);
            }
        } else if (createBtTask.t.contains(createBtTask.u)) {
            arrayList.add(createBtTask.u);
        } else if (createBtTask.u.contains(createBtTask.t)) {
            arrayList.add(createBtTask.t);
        } else {
            arrayList.add(createBtTask.t);
            arrayList.add(createBtTask.u);
        }
        return arrayList;
    }
}
