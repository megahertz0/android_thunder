package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.ui.SlipButton;
import com.xunlei.downloadprovider.app.ui.SlipButton.a;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class TaskSettingActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener, a {
    public static final String a;
    private TextView b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private RelativeLayout g;
    private RelativeLayout h;
    private SlipButton i;
    private SlipButton j;
    private SlipButton k;
    private View l;
    private View m;
    private CheckBox n;
    private int o;

    static /* synthetic */ void a() {
    }

    static {
        a = TaskSettingActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968652);
        this.b = (TextView) findViewById(R.id.titlebar_title);
        this.b.setText(2131232550);
        this.c = findViewById(R.id.titlebar_left);
        this.c.setOnClickListener(this);
        new f((Activity) this).j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.d = findViewById(2131755456);
        this.d.setOnClickListener(this);
        this.e = findViewById(2131755458);
        this.e.setOnClickListener(this);
        this.f = (TextView) findViewById(2131755457);
        this.o = b.a().j();
        if (this.o <= 0 || this.o > 5) {
            this.o = 3;
            b.a().b(this.o);
        }
        this.f.setText(this.o);
        this.h = (RelativeLayout) findViewById(2131755463);
        this.g = (RelativeLayout) findViewById(2131755459);
        this.i = (SlipButton) findViewById(2131755465);
        this.j = (SlipButton) findViewById(2131755460);
        this.k = (SlipButton) findViewById(2131755462);
        this.l = findViewById(2131755461);
        this.i.a = this;
        this.h.setOnClickListener(this);
        this.i.a(b.a().a.getSharedPreferences("settingstate", 0).getBoolean("wifi_warn", false), false);
        this.i.setVisibility(XZBDevice.Wait);
        findViewById(2131755463).setVisibility(XZBDevice.Wait);
        this.j.a = this;
        this.g.setOnClickListener(this);
        this.j.a(b.a().e(), false);
        this.k.a = this;
        this.l.setOnClickListener(this);
        this.k.a(b.a().f(), false);
        this.m = findViewById(2131755466);
        this.n = (CheckBox) findViewById(2131755468);
        this.n.setChecked(b.a().b());
        this.m.setOnClickListener(this);
        this.n.setOnCheckedChangeListener(this);
    }

    public void onResume() {
        super.onResume();
        b();
        new Handler().postDelayed(new ae(this), 50);
    }

    public void onClick(View view) {
        boolean z = false;
        boolean z2 = true;
        switch (view.getId()) {
            case 2131755456:
                if (this.o - 1 > 0) {
                    this.o--;
                    this.f.setText(this.o);
                }
                b();
            case 2131755458:
                if (this.o + 1 <= 5) {
                    this.o++;
                    this.f.setText(this.o);
                }
                b();
            case 2131755459:
                if (!this.j.getSwitchState()) {
                    z = true;
                }
                this.j.a(z, true);
            case 2131755461:
                if (!this.k.getSwitchState()) {
                    z = true;
                }
                this.k.a(z, true);
            case 2131755463:
                if (!this.i.getSwitchState()) {
                    z = true;
                }
                this.i.a(z, true);
            case 2131755466:
                CheckBox checkBox = this.n;
                if (this.n.isChecked()) {
                    z2 = false;
                }
                checkBox.setChecked(z2);
            case R.id.titlebar_left:
                onBackPressed();
            default:
                break;
        }
    }

    private void b() {
        if (this.o <= 1) {
            this.d.setEnabled(false);
        } else {
            this.d.setEnabled(true);
        }
        if (this.o >= 5) {
            this.e.setEnabled(false);
        } else {
            this.e.setEnabled(true);
        }
        if (this.o <= 5 && this.o > 0) {
            d.a();
            d.a(this.o);
        }
    }

    public final void a(View view, boolean z) {
        Editor edit;
        switch (view.getId()) {
            case 2131755460:
                edit = b.a().a.getSharedPreferences("settingstate", 0).edit();
                edit.putBoolean("install", z);
                edit.commit();
                StatReporter.reportSwitcherClick(5003, "autoInstallApk", z, null);
            case 2131755462:
                edit = b.a().a.getSharedPreferences("settingstate", 0).edit();
                edit.putBoolean("remove", z);
                edit.commit();
                StatReporter.reportSwitcherClick(5003, "autoDelApk", z, null);
            case 2131755465:
                edit = b.a().a.getSharedPreferences("settingstate", 0).edit();
                edit.putBoolean("wifi_warn", z);
                edit.commit();
                StatReporter.reportSwitcherClick(5003, "networkNotify", z, null);
            default:
                break;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case 2131755468:
                Editor edit = b.a().a.getSharedPreferences("settingstate", 0).edit();
                edit.putBoolean("high_speed_channel", z);
                edit.commit();
                StatReporter.reportSwitcherClick(5003, "autoAccerate", z, null);
                if (z) {
                    LoginHelper.a();
                    if (!LoginHelper.c()) {
                        return;
                    }
                    if (LoginHelper.a().f() || LoginHelper.a().y > 0) {
                        d.a();
                        d.h();
                    }
                }
            default:
                break;
        }
    }

    public void onPause() {
        try {
            if (this.o != b.a().j()) {
                b.a().b(this.o);
                d.a();
                d.a(this.o);
                StatReporter.reportClick(5003, "asynDownloadCount", this.o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }
}
