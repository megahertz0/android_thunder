package com.xunlei.tdlive;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.member.act.XLWxParam;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.control.a;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class LoginActivity extends BaseActivity implements OnClickListener {
    final String[] a;
    private AutoCompleteTextView b;
    private EditText c;
    private String d;
    private ImageView e;
    private EditText f;
    private int g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private ImageView m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private XLOnUserListener q;

    public LoginActivity() {
        this.d = BuildConfig.VERSION_NAME;
        this.g = 0;
        this.a = new String[]{"qq.com", "163.com", "126.com", "sina.com", "gmail.com", "hotmail.com", "sohu.com", "yahoo.com", "foxmail.com"};
        this.q = new dc(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.Theme_LoginActivity);
        setContentView(R.layout.xllive_activity_login);
        this.b = (AutoCompleteTextView) findViewById(R.id.username);
        this.c = (EditText) findViewById(R.id.password);
        this.e = (ImageView) findViewById(R.id.verifyCodeImage);
        this.f = (EditText) findViewById(R.id.verifyCode);
        this.h = findViewById(R.id.verifyCodeArea);
        this.i = findViewById(R.id.lineBelowVerifyCode);
        this.m = (ImageView) findViewById(R.id.clearInput_username);
        this.n = (ImageView) findViewById(R.id.clearInput_password);
        this.e = (ImageView) findViewById(R.id.verifyCodeImage);
        this.k = findViewById(R.id.accountIcon);
        this.l = findViewById(R.id.passwordIcon);
        this.j = findViewById(R.id.btnForgot);
        this.o = (TextView) findViewById(R.id.btnLogin);
        this.p = (TextView) findViewById(R.id.tvErrorDesc);
        a();
        b();
        this.j.setOnClickListener(new dd(this));
        this.o.setOnClickListener(this);
        d();
    }

    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.xl_login));
        setLeftVisible(true);
        setLeftClickListener(this);
        setRightVisible(true);
        setRightClickListener(this);
        setRightText("\u6ce8\u518c");
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        this.mTitleBarRightText.setBackgroundResource(R.drawable.xllive_save_btn_bkg);
        this.mTitleBarRightText.setTextColor(-1);
        this.mTitleBarRightText.setGravity(R.styleable.Toolbar_maxButtonHeight);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = (int) d.a(getApplicationContext(), 14.0f);
        int a = (int) d.a(getApplicationContext(), 4.0f);
        this.mTitleBarRightText.setPadding(a, 0, a, 0);
        this.mTitleBarRightText.setLayoutParams(layoutParams);
    }

    private void a() {
        this.f.addTextChangedListener(new de(this));
        this.e.setOnClickListener(new df(this));
    }

    private void b() {
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.b.setOnFocusChangeListener(new dg(this));
        ListAdapter aVar = new a(this, R.layout.xllive_input_text_drop_item, 16908308);
        aVar.a("@", this.a);
        this.b.setAdapter(aVar);
        this.b.addTextChangedListener(new dh(this));
        this.c.setOnFocusChangeListener(new di(this));
        this.c.addTextChangedListener(new dj(this));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLogin) {
            f();
            if (XLUserUtil.getInstance().userIsOnline()) {
                setResult(-1);
                finish();
                return;
            }
            c();
        } else if (id == R.id.btnWxLogin) {
            XLWxParam xLWxParam = new XLWxParam();
            xLWxParam.mWxAppId = "wx18eada9ea7fbf76c";
            XLUserUtil.getInstance().userThirdLogin(MqttConnectOptions.MQTT_VERSION_3_1, xLWxParam, this.q, "wx-login");
        } else if (id == R.id.clearInput_username) {
            this.b.setText(null);
        } else if (id == R.id.clearInput_password) {
            this.c.setText(null);
        } else if (id == R.id.left) {
            finish();
        } else if (id == R.id.right) {
            q.a("zb_resign_page_show", "login_xunlei_page", null);
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private boolean c() {
        Object toString = this.b.getText().toString();
        Object toString2 = this.c.getText().toString();
        if (TextUtils.isEmpty(toString)) {
            a(R.string.username_cannot_empty);
            return false;
        } else if (TextUtils.isEmpty(toString2)) {
            a(R.string.passwd_cannot_empty);
            return false;
        } else if (this.g == 3) {
            a(R.string.input_verify_code_error);
            return false;
        } else {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                a(getResources().getString(R.string.no_connection));
                return false;
            }
            this.o.setText(R.string.logining);
            this.o.setEnabled(false);
            f.a().b(0);
            XLUserUtil.getInstance().userAccountLogin(toString, toString2, this.d, this.f.getText().toString(), this.q, null);
            return true;
        }
    }

    private void a(String str, byte[] bArr) {
        this.d = str;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            this.e.setImageBitmap(decodeByteArray);
            a(true);
        }
    }

    private void a(boolean z) {
        int i;
        int i2 = 0;
        this.g = z ? MqttConnectOptions.MQTT_VERSION_3_1 : SimpleLog.LOG_LEVEL_DEBUG;
        View view = this.h;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.i;
        if (!z) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    private void d() {
        Object string = PreferenceManager.getDefaultSharedPreferences(this).getString("last_login_user", BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(string)) {
            this.b.requestFocus();
            return;
        }
        this.b.setText(string);
        this.b.setSelection(string.length());
        this.c.requestFocus();
    }

    private void e() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putString("last_login_user", this.b.getText().toString());
        edit.apply();
    }

    private void a(String str) {
        this.p.setVisibility(0);
        this.p.setText(str);
    }

    private void a(int i) {
        this.p.setVisibility(0);
        this.p.setText(i);
    }

    private void f() {
        this.p.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    private String b(int i) {
        if (i == 2) {
            return "\u60a8\u8f93\u5165\u7684\u5e10\u53f7\u6709\u8bef";
        }
        if (i == 7) {
            return "\u60a8\u7684\u5e10\u53f7\u5b58\u5728\u5f02\u5e38\uff0c\u5df2\u88ab\u9501\u5b9a";
        }
        if (i == 3) {
            return "\u60a8\u8f93\u5165\u7684\u5bc6\u7801\u6709\u8bef";
        }
        if (i == 16781309) {
            return "\u7f51\u7edc\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55";
        }
        if (i == 8) {
            return "\u7cfb\u7edf\u5185\u90e8\u5347\u7ea7\u4e2d\uff0c\u8bf7\u7a0d\u540e\u767b\u5f55";
        }
        if (i == 16781312) {
            return "\u672a\u77e5\u9519\u8bef,\u8bf7\u91cd\u65b0\u767b\u5f55";
        }
        if (i == 404 || i == 405 || i == 411 || i == 403 || i == 406 || i == 400 || i == 407 || i == 408) {
            return "\u60a8\u8f93\u5165\u7684\u9a8c\u8bc1\u7801\u6709\u8bef";
        }
        return i == 16781312 ? "\u5fae\u4fe1\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55" : new StringBuilder("\u767b\u5f55\u9519\u8bef\uff1a").append(i).toString();
    }
}
