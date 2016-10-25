package com.xunlei.downloadprovider.frame.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import com.android.volley.r;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.homepage.recommend.b.a;
import com.xunlei.downloadprovider.homepage.recommend.b.i;
import com.xunlei.downloadprovider.homepage.recommend.b.j;

public class ReportActivity extends BaseActivity {
    private ImageView a;
    private RadioGroup b;
    private EditText c;
    private Button d;
    private int e;
    private int f;
    private String g;
    private String h;
    private long i;
    private EditText j;
    private ScrollView k;
    private long l;
    private String m;
    private String n;

    public ReportActivity() {
        this.e = -1;
        this.l = -1;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968616);
        this.k = (ScrollView) findViewById(2131755264);
        this.a = (ImageView) findViewById(2131755212);
        this.b = (RadioGroup) findViewById(2131755265);
        this.c = (EditText) findViewById(2131755273);
        this.j = (EditText) findViewById(2131755272);
        this.d = (Button) findViewById(2131755274);
        this.b.clearCheck();
        this.c.setEnabled(true);
        this.c.clearFocus();
        this.j.requestFocus();
        this.d.setEnabled(false);
        this.a.setOnClickListener(new aq(this));
        this.b.setOnCheckedChangeListener(new ar(this));
        this.c.setOnClickListener(new as(this));
        this.c.setOnFocusChangeListener(new at(this));
        this.d.setOnClickListener(new au(this));
        Intent intent = getIntent();
        if (intent.hasExtra("report_target")) {
            this.f = intent.getIntExtra("report_target", 1);
            if (this.f == 1) {
                this.g = intent.getStringExtra("comment_res_id");
                this.h = intent.getStringExtra("comment_source_id");
                this.i = intent.getLongExtra("comment_id", -1);
                if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.h) || this.i <= 0) {
                    throw new IllegalArgumentException("Intent must include values below: EXTRA_COMMENT_RES_ID EXTRA_COMMENT_SOURCE_ID EXTRA_COMMENT_ID");
                }
                return;
            } else if (this.f == 2) {
                this.l = intent.getLongExtra(SocializeConstants.TENCENT_UID, -1);
                return;
            } else if (this.f == 3) {
                this.m = String.valueOf(intent.getLongExtra("extra_video_res_id", -1));
                this.n = intent.getStringExtra("extra_video_gcid");
                return;
            } else {
                return;
            }
        }
        throw new IllegalArgumentException("You must deliver EXTRA_REPORT_TARGET value when start ReportActivity");
    }

    static /* synthetic */ void j(ReportActivity reportActivity) {
        x xVar = new x(reportActivity);
        xVar.a("\u6b63\u5728\u63d0\u4ea4...");
        xVar.show();
        a a = a.a(BrothersApplication.a);
        String str = reportActivity.m;
        String str2 = reportActivity.n;
        String d = b.d();
        int i = reportActivity.e;
        r.b axVar = new ax(reportActivity, xVar);
        r.a ayVar = new ay(reportActivity, xVar);
        if (com.xunlei.c.a.b.a(a.a)) {
            com.xunlei.downloadprovider.homepage.recommend.b.b bVar = a.b;
            com.xunlei.downloadprovider.j.a.a().e().a(new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/counter/report", com.xunlei.downloadprovider.homepage.recommend.b.b.a(str, str2, d, i), new i(bVar, axVar), new j(bVar, ayVar)));
        }
    }
}
