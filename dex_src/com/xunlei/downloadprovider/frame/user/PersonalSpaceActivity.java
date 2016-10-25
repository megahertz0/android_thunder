package com.xunlei.downloadprovider.frame.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.a;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.R;

public class PersonalSpaceActivity extends BaseActivity implements a {
    private static final String a;
    private int b;
    private ImageView c;
    private long d;
    private String e;
    private String f;
    private Toolbar g;
    private AppBarLayout h;
    private CollapsingToolbarLayout i;
    private d j;
    private c k;
    private HistoryCommentItemFragment l;
    private ao m;
    private String n;

    public enum From {
        VIDEO_DETAIL_DISCUSS_NAME("videodetail_discuss_name"),
        VIDEO_DETAIL_DISCUSS_HEAD("videodetail_discuss_head"),
        HOMEPAGE_DISCUSS_NAME("homepage_discuss_name"),
        HOMEPAGE_DISCUSS_HEAD("homepage_discuss_head"),
        FEED_FLOW_DISCUSS_NAME("feedflow_discuss_name"),
        FEED_FLOW_DISCUSS_HEAD("feedflow_discuss_head"),
        PERSONAL_SPACE("personal_space"),
        ACCOUNT_CENTER("account_center");
        private final String a;

        static {
            String str = "videodetail_discuss_name";
            VIDEO_DETAIL_DISCUSS_NAME = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("VIDEO_DETAIL_DISCUSS_NAME", 0, "videodetail_discuss_name");
            str = "videodetail_discuss_head";
            VIDEO_DETAIL_DISCUSS_HEAD = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("VIDEO_DETAIL_DISCUSS_HEAD", 1, "videodetail_discuss_head");
            str = "homepage_discuss_name";
            HOMEPAGE_DISCUSS_NAME = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("HOMEPAGE_DISCUSS_NAME", 2, "homepage_discuss_name");
            str = "homepage_discuss_head";
            HOMEPAGE_DISCUSS_HEAD = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("HOMEPAGE_DISCUSS_HEAD", 3, "homepage_discuss_head");
            str = "feedflow_discuss_name";
            FEED_FLOW_DISCUSS_NAME = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("FEED_FLOW_DISCUSS_NAME", 4, "feedflow_discuss_name");
            String str2 = "feedflow_discuss_head";
            FEED_FLOW_DISCUSS_HEAD = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("FEED_FLOW_DISCUSS_HEAD", 5, "feedflow_discuss_head");
            str2 = "personal_space";
            PERSONAL_SPACE = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("PERSONAL_SPACE", 6, "personal_space");
            str2 = "account_center";
            ACCOUNT_CENTER = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From("ACCOUNT_CENTER", 7, "account_center");
            b = new com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity.From[]{VIDEO_DETAIL_DISCUSS_NAME, VIDEO_DETAIL_DISCUSS_HEAD, HOMEPAGE_DISCUSS_NAME, HOMEPAGE_DISCUSS_HEAD, FEED_FLOW_DISCUSS_NAME, FEED_FLOW_DISCUSS_HEAD, PERSONAL_SPACE, ACCOUNT_CENTER};
        }

        private From(String str) {
            this.a = str;
        }

        public final String getText() {
            return this.a;
        }
    }

    static {
        a = PersonalSpaceActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968612);
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getLongExtra(SocializeConstants.TENCENT_UID, -1);
            this.e = intent.getStringExtra("user_name");
            this.f = intent.getStringExtra("user_avatar");
            this.n = intent.getStringExtra("from");
        }
        this.j = d.a();
        this.j.a(e.a(this));
        c.a aVar = new c.a();
        aVar.a = 2130838392;
        aVar.b = 2130838392;
        aVar.c = 2130838392;
        aVar.m = true;
        aVar.h = true;
        aVar.i = true;
        aVar.q = new com.nostra13.universalimageloader.core.b.c(Integer.MAX_VALUE);
        this.k = aVar.b();
        this.c = (ImageView) findViewById(2131755236);
        this.g = (Toolbar) findViewById(2131755237);
        this.h = (AppBarLayout) findViewById(2131755234);
        this.i = (CollapsingToolbarLayout) findViewById(2131755235);
        if (TextUtils.isEmpty(this.e) || this.e.trim().length() <= 0) {
            this.i.setTitle("\u8fc5\u96f7\u7528\u6237");
        } else {
            this.i.setTitle(this.e);
        }
        if (!TextUtils.isEmpty(this.f)) {
            this.j.a(this.f, this.c, this.k);
        }
        this.l = HistoryCommentItemFragment.a(this.d);
        getSupportFragmentManager().beginTransaction().add((int) R.id.fragment_container, this.l).commit();
        this.m = new ao(this);
        Toolbar toolbar = this.g;
        toolbar.getMenuInflater().inflate(2131820544, toolbar.getMenu());
        this.g.setNavigationOnClickListener(new al(this));
        this.g.a(null, null);
        this.h.a((a) this);
        this.i.setOnTouchListener(new am(this));
        ao aoVar = this.m;
        aoVar.a.setOnClickListener(new an(this));
    }

    protected void onResume() {
        super.onResume();
        String str = this.n;
        com.xunlei.downloadprovidercommon.a.c a = com.xunlei.downloadprovidercommon.a.a.a("android_personal_account", "personal_space_show");
        a.b("from", str);
        com.xunlei.downloadprovidercommon.a.d.a(a);
    }

    public final void a(AppBarLayout appBarLayout, int i) {
        if (this.b == 0) {
            this.b = appBarLayout.getTotalScrollRange();
        }
        int abs = (Math.abs(i) * 100) / this.b;
        ViewCompat.setScaleX(this.c, 1.0f - (((float) abs) / 100.0f));
        ViewCompat.setScaleY(this.c, 1.0f - (((float) abs) / 100.0f));
        ViewCompat.setTranslationY(this.c, -0.475f * ((float) i));
    }

    public static void a(Context context, From from, long j, String str, String str2) {
        Intent intent = new Intent(context, PersonalSpaceActivity.class);
        intent.putExtra(SocializeConstants.TENCENT_UID, j);
        intent.putExtra("user_name", str);
        intent.putExtra("user_avatar", str2);
        intent.putExtra("from", from.getText());
        context.startActivity(intent);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1101) {
            LoginHelper.a();
            if (LoginHelper.c() && this.l != null) {
                this.l.a();
            }
        }
    }
}
