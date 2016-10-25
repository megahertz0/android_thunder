package com.xunlei.downloadprovider.frame.user.account.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class UserAccountSecurityActivity extends BaseActivity {
    private View a;
    private TextView b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private View j;
    private LoginHelper k;
    private int l;
    private int m;
    private int n;
    private OnClickListener o;

    public UserAccountSecurityActivity() {
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = new ad(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968625);
        this.a = findViewById(R.id.titlebar_left);
        this.a.setOnClickListener(new aa(this));
        this.b = (TextView) findViewById(R.id.titlebar_title);
        this.b.setText(2131232964);
        this.c = findViewById(2131755315);
        this.c.setOnClickListener(new ab(this));
        this.d = (TextView) findViewById(2131755317);
        this.e = (TextView) findViewById(2131755320);
        this.f = (TextView) findViewById(2131755323);
        this.g = (ImageView) findViewById(2131755318);
        this.h = (ImageView) findViewById(2131755321);
        this.i = (ImageView) findViewById(2131755324);
        this.g.setOnClickListener(this.o);
        this.h.setOnClickListener(this.o);
        this.i.setOnClickListener(this.o);
        this.j = findViewById(2131755325);
        this.j.setOnClickListener(new ac(this));
        this.k = LoginHelper.a();
        this.k.a(new z(this));
    }

    static /* synthetic */ void a(UserAccountSecurityActivity userAccountSecurityActivity, View view, int i) {
        int i2 = 2130839468;
        if (i == 1) {
            view.setBackgroundResource(2130839468);
            view.setOnClickListener(new ag(userAccountSecurityActivity));
            return;
        }
        if (i != 0) {
            i2 = 2130839467;
        }
        view.setBackgroundResource(i2);
    }

    static /* synthetic */ void a(UserAccountSecurityActivity userAccountSecurityActivity, int i, String str) {
        TextView textView = null;
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                textView = userAccountSecurityActivity.f;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                textView = userAccountSecurityActivity.d;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                textView = userAccountSecurityActivity.e;
                break;
        }
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(XZBDevice.Wait);
            return;
        }
        textView.setVisibility(0);
        textView.setText(new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(str).append(SocializeConstants.OP_CLOSE_PAREN).toString());
    }

    static /* synthetic */ void a(UserAccountSecurityActivity userAccountSecurityActivity, View view, int i, int i2) {
        String str;
        g a;
        if (i2 == -1) {
            str = "account_third_bind";
            a = g.a("android_personal_account", str, str);
            a.a("account_type", k.a(i));
            k.a(a);
            userAccountSecurityActivity.k.a(i, new ae(userAccountSecurityActivity, view));
        } else if (i2 == 0) {
            str = "account_third_unbind";
            a = g.a("android_personal_account", str, str);
            a.a("account_type", k.a(i));
            k.a(a);
            LoginHelper loginHelper = userAccountSecurityActivity.k;
            loginHelper.N = new af(userAccountSecurityActivity, view);
            XLUserUtil.getInstance().userUnBindOtherAccount(i, loginHelper.I, null);
        }
    }

    static /* synthetic */ void a(UserAccountSecurityActivity userAccountSecurityActivity, int i, int i2) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                userAccountSecurityActivity.n = i2;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                userAccountSecurityActivity.l = i2;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                userAccountSecurityActivity.m = i2;
            default:
                break;
        }
    }
}
