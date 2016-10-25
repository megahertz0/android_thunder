package com.xunlei.downloadprovider.download.create;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.cons.b;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.k;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUrlTask extends ThunderTask {
    ListView a;
    a b;
    List<String> c;
    private f d;
    private Button e;
    private EditText f;
    private boolean g;
    private ImageView h;
    private boolean i;
    private String j;
    private String k;
    private String[] l;
    private String[] m;

    private class a extends BaseAdapter {
        final /* synthetic */ CreateUrlTask a;
        private LayoutInflater b;
        private List<String> c;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(Context context, List<String> list) {
            this.a = context;
            this.b = LayoutInflater.from(list);
            this.c = null;
        }

        public final int getCount() {
            return this.c == null ? 0 : this.c.size();
        }

        public final String a(int i) {
            return (String) this.c.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.b.inflate(2130968643, null);
                a aVar2 = new a(this);
                aVar2.a = (TextView) view.findViewById(2131755405);
                aVar2.a.setSingleLine();
                aVar2.a.setEllipsize(TruncateAt.START);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText(a(i));
            return view;
        }
    }

    public CreateUrlTask() {
        this.g = true;
        this.h = null;
        this.i = false;
        this.l = new String[]{"www", "wap", "http://", "ed2k://", "ftp://", "thunder://", "http://www"};
        this.m = new String[]{"com", "cn", "com.cn", "edu.cn", c.a, "org", "apk", "avi", "mp4", "mp3", "mpeg", "mpg", "rm", "rmvb"};
    }

    static /* synthetic */ void b(CreateUrlTask createUrlTask) {
        String trim = createUrlTask.f.getText().toString().trim();
        if ((trim != null && trim.equals(com.umeng.a.d)) || trim == null) {
            XLToast.a(createUrlTask.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a");
        } else if (a(trim)) {
            XLToast.a(createUrlTask.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u4e0d\u53ef\u7528\u7684\u4e0b\u8f7d\u5730\u5740");
        } else {
            String substring;
            String str;
            com.xunlei.downloadprovider.download.report.a.i("click");
            int indexOf = trim.indexOf(":");
            if (-1 != indexOf) {
                substring = trim.substring(0, indexOf);
                if (substring.equalsIgnoreCase(HttpConstant.HTTP)) {
                    substring = new StringBuilder(HttpConstant.HTTP).append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase(b.a)) {
                    substring = new StringBuilder(b.a).append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("ed2k")) {
                    substring = new StringBuilder("ed2k").append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("thunder")) {
                    substring = new StringBuilder("thunder").append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("ftp")) {
                    substring = new StringBuilder("ftp").append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase("magnet")) {
                    substring = new StringBuilder("magnet").append(trim.substring(indexOf)).toString();
                } else if (substring.equalsIgnoreCase(Impl.COLUMN_CID)) {
                    substring = new StringBuilder(Impl.COLUMN_CID).append(trim.substring(indexOf)).toString();
                } else if (!a(trim)) {
                    substring = new StringBuilder("http://").append(trim).toString();
                } else {
                    return;
                }
            }
            substring = new StringBuilder("http://").append(trim).toString();
            createUrlTask.f.clearFocus();
            if (!substring.equals(createUrlTask.j) || TextUtils.isEmpty(createUrlTask.k)) {
                str = null;
            } else {
                str = createUrlTask.k;
            }
            if (DownloadService.a() != null) {
                g gVar = new g(3, substring, null);
                gVar.d = "manual/manual_newtask";
                createUrlTask.i = true;
                Handler handler = BrothersApplication.a().e;
                handler.a = createUrlTask;
                createUrlTask.createLocalTask(substring, str, 0, null, null, null, 0, gVar, handler, false);
            }
            StatReporter.reportOverallDownload("manual_newtask");
        }
    }

    protected void onCreateTask(boolean z, int i) {
        com.xunlei.downloadprovider.download.report.a.i(z ? "task_success" : "task_fail");
        this.i = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = false;
        setContentView(2130968642);
        this.d = new f((Activity) this);
        this.d.g.setImageResource(R.drawable.common_back_icon_selector);
        this.d.j.setBackgroundResource(R.drawable.common_back_icon_selector);
        this.d.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.d.i.setText(2131231131);
        this.f = (EditText) findViewById(2131755398);
        this.f.requestFocus();
        this.e = (Button) findViewById(2131755400);
        this.e.setOnClickListener(new c(this));
        this.h = (ImageView) findViewById(2131755399);
        this.h.setOnClickListener(new d(this));
        this.f.setOnClickListener(new e(this));
        this.f.setOnKeyListener(new f(this));
        this.f.addTextChangedListener(new g(this));
        this.a = (ListView) findViewById(2131755401);
        this.c = new ArrayList();
        this.b = new a(this, this);
        this.a.setOnItemClickListener(new h(this));
        CharSequence a = a();
        if (!TextUtils.isEmpty(a)) {
            String[] strArr = new String[]{HttpConstant.HTTP, b.a, "ftp", "ed2k", "magnet", "thunder"};
            int i = 0;
            while (i < 6) {
                if (a.startsWith(strArr[i])) {
                    i = com.xunlei.downloadprovider.util.c.a.b(a);
                    if (i == 1 || i == 2) {
                        this.f.setText(a);
                    }
                } else {
                    i++;
                }
            }
        }
        findViewById(2131755404).setOnClickListener(new i(this));
        this.d.g.setOnClickListener(new j(this));
    }

    private String a() {
        this.j = com.xunlei.downloadprovider.download.util.a.b((Context) this);
        this.k = null;
        List<String> a = com.xunlei.downloadprovider.download.util.a.a((Context) this);
        Pattern compile = Pattern.compile("\\|file\\|([^\\|]+)");
        if (a.size() > 0) {
            for (String str : a) {
                if (str.startsWith("thundertask://")) {
                    try {
                        Matcher matcher = compile.matcher(str);
                        if (matcher.find()) {
                            Object group = matcher.group(1);
                            if (!TextUtils.isEmpty(group)) {
                                this.k = k.b(group, GameManager.DEFAULT_CHARSET);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.j;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        if (i == 100) {
            finish();
            BrothersApplication.a().e.a = null;
            DownloadCenterActivity.a(this, j, com.umeng.a.d);
        } else if (i == 101 && i2 == 102409) {
            super.handleTaskOperator(i, i2, j, taskInfo);
            return true;
        }
        this.i = false;
        return false;
    }

    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void onPause() {
        com.xunlei.downloadprovider.a.b.a(this, this.f);
        super.onPause();
    }

    private static boolean a(String str) {
        if (str.compareToIgnoreCase("http://") == 0) {
            return true;
        }
        if (str.compareToIgnoreCase("ed2k://") == 0) {
            return true;
        }
        if (str.compareToIgnoreCase("magnet:?") == 0) {
            return true;
        }
        if (str.compareToIgnoreCase("thunder://") == 0) {
            return true;
        }
        if (str.compareToIgnoreCase("ftp://") == 0) {
            return true;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("http://") || toLowerCase.startsWith("thunder://") || toLowerCase.startsWith("ed2k://") || toLowerCase.startsWith("ftp://") || toLowerCase.startsWith("magnet:?") || toLowerCase.startsWith("https://")) {
            return !TextUtils.isEmpty(str) ? Pattern.compile("^[A-Za-z0-9]+$").matcher(str).matches() : false;
        } else {
            return true;
        }
    }
}
