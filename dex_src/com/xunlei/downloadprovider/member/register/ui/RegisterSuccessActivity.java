package com.xunlei.downloadprovider.member.register.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.frame.user.account.h;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.R;

public class RegisterSuccessActivity extends BaseActivity {
    private b a;
    private View b;
    private TextView c;
    private ImageView d;
    private EditText e;
    private ImageView f;
    private ImageView g;
    private Button h;
    private final int i;
    private boolean j;
    private boolean k;
    private h l;
    private final p m;
    private a n;

    public RegisterSuccessActivity() {
        this.i = 109;
        this.l = new h(this);
        this.m = new ae(this);
        this.n = new w(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968615);
        this.b = findViewById(R.id.titlebar_left);
        this.c = (TextView) findViewById(R.id.titlebar_title);
        this.c.setText("\u5b8c\u5584\u4e2a\u4eba\u8d44\u6599");
        this.d = (ImageView) findViewById(2131755257);
        this.e = (EditText) findViewById(2131755259);
        this.f = (ImageView) findViewById(2131755258);
        this.g = (ImageView) findViewById(2131755260);
        this.h = (Button) findViewById(2131755261);
        this.b.setOnClickListener(new v(this));
        this.d.setOnClickListener(new x(this));
        this.e.addTextChangedListener(new y(this));
        this.e.setOnFocusChangeListener(new z(this));
        this.g.setOnClickListener(new aa(this));
        this.h.setOnClickListener(new ab(this));
        LoginHelper a = LoginHelper.a();
        a.M = new ac(this);
        a.L = new ad(this);
    }

    protected void onResume() {
        super.onResume();
        LoginHelper.a().a(this.m);
        this.a = new b(this.n);
        String str = "register_head_change_show";
        k.a(g.a("android_personal_account", str, str));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.l.a(i, i2, intent, "phone_register_login");
        this.k = true;
        super.onActivityResult(i, i2, intent);
    }
}
