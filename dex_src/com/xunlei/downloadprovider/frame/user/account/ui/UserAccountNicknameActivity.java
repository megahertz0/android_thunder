package com.xunlei.downloadprovider.frame.user.account.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Timer;

public class UserAccountNicknameActivity extends BaseActivity {
    String a;
    private EditText b;
    private FrameLayout c;
    private f d;
    private final LoginHelper e;
    private boolean f;
    private TextWatcher g;

    public UserAccountNicknameActivity() {
        this.e = LoginHelper.a();
        this.g = new n(this);
    }

    static /* synthetic */ int a(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt > 'z') {
                i2 += 2;
            } else {
                i2++;
            }
            i++;
        }
        return i2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968623);
        this.b = (EditText) findViewById(2131755303);
        this.c = (FrameLayout) findViewById(2131755304);
        this.d = new f((Activity) this);
        this.d.i.setText(getResources().getString(2131232982));
        this.d.k.setVisibility(0);
        this.d.k.setText("\u5b8c\u6210");
        a();
        this.d.k.setOnClickListener(new j(this));
        this.d.i.setOnClickListener(new k(this));
        this.c.setOnClickListener(new l(this));
        LoginHelper.a().L = new m(this);
        this.b.addTextChangedListener(this.g);
        this.b.setText(new bx().f);
        this.b.setSelection(this.b.getText().toString().length());
        this.b.requestFocus();
        new Timer().schedule(new i(this), 300);
    }

    private void a() {
        if (TextUtils.isEmpty(this.b.getText())) {
            this.d.k.setClickable(false);
            this.d.k.setTextColor(getResources().getColor(R.color.common_gray_btn_normal));
            this.c.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            return;
        }
        this.d.k.setClickable(true);
        this.d.k.setTextColor(getResources().getColor(2131690079));
        this.c.setVisibility(0);
    }

    protected void onPause() {
        super.onPause();
        this.e.s();
        if (!this.f) {
            k.b("account_center", "cancel");
        }
    }
}
