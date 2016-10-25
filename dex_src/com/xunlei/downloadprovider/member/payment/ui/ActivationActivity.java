package com.xunlei.downloadprovider.member.payment.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.a.a;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.payment.a.d;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ActivationActivity extends BaseActivity implements OnClickListener {
    private static String a;
    private EditText b;
    private EditText c;
    private ImageView d;
    private Button e;
    private Button f;
    private Button g;
    private ProgressBar h;
    private Bitmap i;
    private a j;
    private String k;
    private String l;
    private String m;
    private j n;
    private boolean o;
    private Handler p;
    private p q;

    public ActivationActivity() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = j.a();
        this.o = true;
        this.p = new a(this);
        this.q = new b(this);
    }

    static {
        a = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StatReporter.reportActivationPayClick("click");
        setContentView(2130968601);
        findViewById(R.id.titlebar_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.titlebar_title)).setText(getResources().getString(2131230808));
        this.b = (EditText) findViewById(2131755168);
        this.c = (EditText) findViewById(2131755176);
        this.d = (ImageView) findViewById(2131755172);
        this.e = (Button) findViewById(2131755177);
        this.f = (Button) findViewById(2131755169);
        this.g = (Button) findViewById(2131755175);
        this.h = (ProgressBar) findViewById(2131755173);
        this.b.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(new c(this));
        this.g.setOnClickListener(new d(this));
        this.b.setOnFocusChangeListener(new e(this));
        this.b.addTextChangedListener(new f(this));
        this.c.setOnFocusChangeListener(new g(this));
        this.c.addTextChangedListener(new h(this));
        this.l = getIntent().getStringExtra("activation_src_key");
        this.m = getIntent().getStringExtra("pay_from");
        new StringBuilder("activationSrc = ").append(this.l);
        b.a("pay_key_show", this.m, this.n.g(), this.n.d());
    }

    public void onResume() {
        super.onResume();
        if (a != null) {
            this.b.setText(a);
            if (com.umeng.a.d.equals(this.b.getText().toString().trim())) {
                this.b.requestFocus();
            } else {
                this.c.requestFocus();
            }
        }
        this.o = true;
        b();
    }

    public void onPause() {
        super.onPause();
        if (this.o) {
            a = this.b.getText().toString().trim();
        } else {
            a = com.umeng.a.d;
        }
    }

    public void onDestroy() {
        c();
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755168:
                if (com.umeng.a.d.equals(this.b.getText().toString().trim())) {
                    this.f.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                } else {
                    this.f.setVisibility(0);
                }
                this.b.requestFocus();
            case 2131755172:
                b();
            case 2131755176:
                if (com.umeng.a.d.equals(this.c.getText().toString().trim())) {
                    this.g.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                } else {
                    this.g.setVisibility(0);
                }
                this.c.requestFocus();
            case 2131755177:
                if (com.xunlei.xllib.a.b.a(getApplicationContext())) {
                    String trim = this.b.getText().toString().trim();
                    String trim2 = this.c.getText().toString().trim();
                    if (com.umeng.a.d.equals(trim2) && com.umeng.a.d.equals(trim)) {
                        XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, getResources().getString(2131230807));
                        this.b.requestFocus();
                        return;
                    } else if (com.umeng.a.d.equals(trim)) {
                        XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, getResources().getString(2131230807));
                        this.b.requestFocus();
                        return;
                    } else if (com.umeng.a.d.equals(trim2)) {
                        XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, getResources().getString(2131233082));
                        this.c.requestFocus();
                        return;
                    } else {
                        if (!(trim2 == null || com.umeng.a.d.equals(trim2) || trim == null || com.umeng.a.d.equals(trim))) {
                            com.xunlei.downloadprovider.member.payment.a.a aVar = new com.xunlei.downloadprovider.member.payment.a.a(getApplicationContext(), String.valueOf(j.a().a.j), trim, trim2, this.j.b, "wx", this.p);
                            this.b.setTextColor(-7829368);
                            this.b.setFocusableInTouchMode(false);
                            this.b.setEnabled(false);
                            this.c.setTextColor(-7829368);
                            this.c.setFocusableInTouchMode(false);
                            this.c.setEnabled(false);
                            this.d.setClickable(false);
                            this.e.setText(2131232100);
                            this.e.setClickable(false);
                            this.f.setVisibility(XZBDevice.Wait);
                            this.g.setVisibility(XZBDevice.Wait);
                            com.xunlei.downloadprovider.a.b.a(getApplicationContext(), getCurrentFocus());
                        }
                        b.a("pay_key_submit", this.m, this.n.g(), this.n.d());
                        return;
                    }
                }
                XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, getResources().getString(2131232509));
            case R.id.titlebar_left:
                finish();
            default:
                break;
        }
    }

    private void a() {
        this.b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.b.setFocusableInTouchMode(true);
        this.b.setEnabled(true);
        this.c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.c.setFocusableInTouchMode(true);
        this.c.setEnabled(true);
        this.d.setClickable(true);
        this.e.setText(2131232095);
        this.e.setClickable(true);
    }

    private void b() {
        c();
        this.h.setVisibility(0);
        com.xunlei.downloadprovider.member.payment.a.a aVar = new com.xunlei.downloadprovider.member.payment.a.a();
        Handler handler = this.p;
        if (!aVar.b) {
            new d(aVar, handler).start();
        }
    }

    private void c() {
        if (this.i != null && !this.i.isRecycled()) {
            this.i.recycle();
            this.i = null;
        }
    }

    static /* synthetic */ void d(ActivationActivity activationActivity) {
        String str = com.umeng.a.d;
        if (activationActivity.n.a.j()) {
            str = "diamond";
        } else if (activationActivity.n.a.i()) {
            str = "platinum";
        } else if (activationActivity.n.c()) {
            str = "vip";
        }
        if (activationActivity.l == null) {
            StatReporter.reportActivationPaySuccess(str, MsgConstant.KEY_SUCCESS, "default");
        } else if (activationActivity.l.equals("ordinary")) {
            StatReporter.reportActivationPaySuccess(str, MsgConstant.KEY_SUCCESS, "ordinary");
        } else if (activationActivity.l.equals("activity")) {
            StatReporter.reportActivationPaySuccess(str, MsgConstant.KEY_SUCCESS, "activity");
        }
        b.a("pay_key_success", activationActivity.m, activationActivity.n.g(), activationActivity.n.d());
    }

    static /* synthetic */ void k(ActivationActivity activationActivity) {
        activationActivity.a();
        activationActivity.b.setText(com.umeng.a.d);
        activationActivity.c.setText(com.umeng.a.d);
        XLToast.a(activationActivity.getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, activationActivity.getResources().getString(2131232099));
    }
}
