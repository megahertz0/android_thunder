package com.xunlei.downloadprovider.frame.user.account.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.user.account.view.UserAccountItem;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.SexType;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.login.ui.UserAccountInfoActivity;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class UserAccountInfoActivityNew extends BaseActivity {
    private final int a;
    private final int b;
    private UserAccountItem c;
    private UserAccountItem d;
    private UserAccountItem e;
    private UserAccountItem f;
    private UserAccountItem g;
    private UserAccountItem h;
    private f i;
    private final LoginHelper j;
    private b k;
    private int l;
    private SexType m;
    private boolean n;
    private final p o;
    private a p;

    public UserAccountInfoActivityNew() {
        this.a = 101;
        this.b = 105;
        this.j = LoginHelper.a();
        this.o = new f(this);
        this.p = new g(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j.a(this.o);
        setContentView(2130968622);
        this.c = (UserAccountItem) findViewById(2131755297);
        this.c.setVisibility(0);
        this.c.getAccountItemTipText().setVisibility(XZBDevice.Wait);
        this.c.getAccountItemTipPic().setVisibility(XZBDevice.Wait);
        this.c.setAccountItemName(2131232945);
        this.d = (UserAccountItem) findViewById(2131755298);
        this.d.setAccountItemName(2131232942);
        this.d.setAccountItemTipPicVisible(0);
        this.e = (UserAccountItem) findViewById(2131755299);
        this.e.setAccountItemName(2131232941);
        this.e.setAccountItemTipPicVisible(XZBDevice.Wait);
        this.f = (UserAccountItem) findViewById(2131755300);
        this.f.setAccountItemName(2131232944);
        this.f.setAccountItemTipPicVisible(XZBDevice.Wait);
        this.g = (UserAccountItem) findViewById(2131755301);
        this.g.getAccountItemTipText().setVisibility(XZBDevice.Wait);
        this.g.getAccountItemTipPic().setVisibility(XZBDevice.Wait);
        this.g.setAccountItemName(2131232943);
        this.h = (UserAccountItem) findViewById(2131755302);
        if (this.j.f() || aa.c(getApplicationContext(), "isvip")) {
            this.h.setVisibility(0);
            this.h.getAccountItemTipText().setVisibility(XZBDevice.Wait);
            this.h.getAccountItemTipPic().setVisibility(XZBDevice.Wait);
            this.h.setAccountItemName(2131232940);
        } else {
            this.h.setVisibility(XZBDevice.Wait);
        }
        this.i = new f((Activity) this);
        this.i.i.setText(getResources().getString(2131232981));
        OnClickListener aVar = new a(this);
        this.i.i.setOnClickListener(new b(this));
        this.c.setOnClickListener(aVar);
        this.d.setOnClickListener(aVar);
        this.e.setOnClickListener(aVar);
        this.f.setOnClickListener(aVar);
        this.g.setOnClickListener(aVar);
        this.h.setOnClickListener(aVar);
        LoginHelper.a().a(this.o);
        this.j.s();
        bx bxVar = new bx();
        a(bxVar.e, bxVar.e());
        this.e.getAccountItemTipText().setText(bxVar.f);
        this.l = a(bxVar.i);
        if (this.l != -1) {
            this.f.getAccountItemTipText().setText(this.l);
            if (this.l == 2131232976) {
                this.f.setAccountItemTipIcon(ResourcesCompat.getDrawable(getResources(), 2130839579, null));
                return;
            } else if (this.l == 2131232975) {
                this.f.setAccountItemTipIcon(ResourcesCompat.getDrawable(getResources(), 2130839586, null));
                return;
            } else {
                this.f.getAccountItemTipIcon().setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                return;
            }
        }
        this.f.getAccountItemTipText().setText(2131232977);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                if (intent != null) {
                    this.n = intent.getBooleanExtra(UserAccountPortraitSettingActivity.a, false);
                }
            default:
                break;
        }
    }

    private void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            String replace = str.replace("/50x50", "/300x300");
            c.a aVar = new c.a();
            aVar.a = 2130838392;
            aVar.h = true;
            aVar.i = true;
            d.a().a(replace, this.d.getAccountItemTipPic(), aVar.b(), new e(this, z));
        }
    }

    protected void onResume() {
        super.onResume();
        this.k = new b(this.p);
        this.j.L = new h(this);
        if (this.n) {
            LoginHelper.a().s();
        }
    }

    private static int a(String str) {
        if ("u".equals(str)) {
            return 2131232977;
        }
        if ("m".equals(str)) {
            return 2131232976;
        }
        return "f".equals(str) ? 2131232975 : -1;
    }

    static /* synthetic */ void a(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        Intent intent = new Intent();
        intent.setClass(userAccountInfoActivityNew, UserAccountPortraitSettingActivity.class);
        userAccountInfoActivityNew.startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    static /* synthetic */ void b(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        Intent intent = new Intent();
        intent.setClass(userAccountInfoActivityNew, UserAccountNicknameActivity.class);
        userAccountInfoActivityNew.startActivity(intent);
    }

    static /* synthetic */ void c(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        com.xunlei.downloadprovider.frame.user.account.a.b cVar = new c(userAccountInfoActivityNew);
        com.xunlei.downloadprovider.frame.user.account.a.a dVar = new d(userAccountInfoActivityNew);
        CharSequence string = userAccountInfoActivityNew.getString(2131232976);
        CharSequence string2 = userAccountInfoActivityNew.getString(2131232975);
        Dialog a = com.xunlei.downloadprovider.frame.user.account.a.a(userAccountInfoActivityNew, 2130968629);
        dVar.a((ImageView) a.findViewById(2131755335), (ImageView) a.findViewById(2131755338));
        a.findViewById(R.id.cancel).setOnClickListener(new com.xunlei.downloadprovider.frame.user.account.b(a, cVar));
        RelativeLayout relativeLayout = (RelativeLayout) a.findViewById(2131755333);
        ((TextView) a.findViewById(2131755330)).setText(string);
        relativeLayout.setOnClickListener(new com.xunlei.downloadprovider.frame.user.account.c(a, cVar));
        relativeLayout = (RelativeLayout) a.findViewById(2131755336);
        ((TextView) a.findViewById(2131755331)).setText(string2);
        relativeLayout.setOnClickListener(new com.xunlei.downloadprovider.frame.user.account.d(a, cVar));
        a.show();
    }

    static /* synthetic */ void d(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        Intent intent = new Intent();
        intent.setClass(userAccountInfoActivityNew, UserAccountInfoActivity.class);
        userAccountInfoActivityNew.startActivity(intent);
    }

    static /* synthetic */ void i(UserAccountInfoActivityNew userAccountInfoActivityNew) {
        bx bxVar = new bx();
        if (bxVar.a) {
            userAccountInfoActivityNew.e.getAccountItemTipText().setText(bxVar.f);
            userAccountInfoActivityNew.l = a(bxVar.i);
            if (userAccountInfoActivityNew.n) {
                userAccountInfoActivityNew.a(bxVar.e, bxVar.e());
                userAccountInfoActivityNew.n = false;
            }
        }
    }
}
