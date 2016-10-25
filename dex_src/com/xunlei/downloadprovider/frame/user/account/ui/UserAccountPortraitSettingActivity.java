package com.xunlei.downloadprovider.frame.user.account.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.user.account.h;
import com.xunlei.downloadprovider.frame.user.account.l;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserAccountPortraitSettingActivity extends BaseActivity {
    public static String a;
    private final int b;
    private ImageView c;
    private b d;
    private h e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private Executor p;
    private final p q;
    private a r;

    public UserAccountPortraitSettingActivity() {
        this.b = 107;
        this.e = new h(this);
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.p = Executors.newSingleThreadExecutor();
        this.q = new p(this);
        this.r = new q(this);
    }

    static /* synthetic */ void a(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, XLThirdUserInfo xLThirdUserInfo, ImageView imageView, ImageView imageView2, int i) {
        if (userAccountPortraitSettingActivity.e != null) {
            userAccountPortraitSettingActivity.l = true;
            String stringValue = xLThirdUserInfo.getStringValue("headimgurl");
            String stringValue2 = xLThirdUserInfo.getStringValue("nickname");
            String stringValue3 = xLThirdUserInfo.getStringValue("gender");
            userAccountPortraitSettingActivity.e.a(stringValue, false, imageView, true);
            imageView2.setVisibility(0);
            userAccountPortraitSettingActivity.p.execute(new x(userAccountPortraitSettingActivity, i, stringValue, stringValue2, stringValue3));
        }
    }

    static /* synthetic */ void a(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, String str, int i, ImageView imageView, ImageView imageView2) {
        if (userAccountPortraitSettingActivity.e != null && str != null && b(i)) {
            userAccountPortraitSettingActivity.e.a(str, false, imageView, false);
            imageView2.setVisibility(0);
        }
    }

    static {
        a = "refreshPortrait";
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LoginHelper.a().a(this.q);
        setContentView(2130968624);
        this.c = (ImageView) findViewById(2131755307);
        this.f = (ImageView) findViewById(2131755309);
        this.i = (ImageView) findViewById(2131755310);
        this.g = (ImageView) findViewById(2131755311);
        this.j = (ImageView) findViewById(2131755312);
        this.h = (ImageView) findViewById(2131755313);
        this.k = (ImageView) findViewById(2131755314);
        ViewGroup viewGroup = (ViewGroup) findViewById(2131755238);
        f fVar = new f((Activity) this);
        fVar.f = viewGroup;
        fVar.f.setBackgroundColor(0);
        fVar.f.findViewById(R.id.xreader_common_divide).setVisibility(XZBDevice.Wait);
        fVar.f.findViewById(com.xunlei.tdlive.R.id.white_button_line).setVisibility(XZBDevice.Wait);
        fVar.g.setImageDrawable(ResourcesCompat.getDrawable(getResources(), 2130838453, null));
        fVar.i.setText(getResources().getString(2131232983));
        fVar.i.setTextColor(-1);
        this.c.setOnClickListener(new o(this));
        this.f.setOnClickListener(new r(this));
        this.g.setOnClickListener(new s(this));
        this.h.setOnClickListener(new t(this));
        LoginHelper.a().M = new u(this);
        LoginHelper.a().a(this.q);
        bx bxVar = new bx();
        this.e.a(bxVar.e, bxVar.e(), this.c, false);
        LoginHelper.a().a(new y(this, l.a().a(XZBDevice.DOWNLOAD_LIST_FAILED), l.a().a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED), l.a().a(XZBDevice.DOWNLOAD_LIST_RECYCLE)));
    }

    private static boolean b(int i) {
        return i == 0 || 1 == i;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.e.a(i, i2, intent, "account_center");
        this.l = true;
        super.onActivityResult(i, i2, intent);
    }

    protected void onResume() {
        super.onResume();
        this.d = new b(this.r);
    }

    static /* synthetic */ void a(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, boolean z, int i, ImageView imageView, ImageView imageView2) {
        if (z) {
            LoginHelper a = LoginHelper.a();
            a.O = new w(userAccountPortraitSettingActivity, imageView, imageView2, i);
            XLUserUtil.getInstance().userGetOtherAccountInfo(i, LoginHelper.c(i), a.I, null);
            return;
        }
        LoginHelper.a().a(i, new v(userAccountPortraitSettingActivity, imageView, imageView2, i));
    }

    static /* synthetic */ void k(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity) {
        Intent intent = new Intent();
        intent.putExtra(a, true);
        userAccountPortraitSettingActivity.setResult(com.xunlei.tdlive.R.styleable.AppCompatTheme_buttonStyleSmall, intent);
    }

    static /* synthetic */ void a(UserAccountPortraitSettingActivity userAccountPortraitSettingActivity, int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                userAccountPortraitSettingActivity.o = 0;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                userAccountPortraitSettingActivity.m = 0;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                userAccountPortraitSettingActivity.n = 0;
            default:
                break;
        }
    }
}
