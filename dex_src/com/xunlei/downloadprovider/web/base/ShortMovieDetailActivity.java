package com.xunlei.downloadprovider.web.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.user.ReportActivity;
import com.xunlei.downloadprovider.homepage.b;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.player.y;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

public class ShortMovieDetailActivity extends ThunderTask implements a {
    public static String a;
    private static ao e;
    private ImageView A;
    private ImageView B;
    private View C;
    private View D;
    private ImageView E;
    private TextView F;
    private ImageView G;
    private String H;
    private String I;
    private String J;
    private String K;
    private int L;
    private int M;
    private b N;
    private com.xunlei.downloadprovider.web.base.model.b<u> O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private Handler W;
    private int X;
    private BroadcastReceiver Y;
    private Runnable Z;
    private com.xunlei.downloadprovider.player.b aa;
    String b;
    String c;
    TextView d;
    private ao f;
    private final String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private ai m;
    private int n;
    private int o;
    private boolean p;
    private ShortMovieDetailFragment q;
    private ViewGroup r;
    private View s;
    private ViewGroup t;
    private TextView u;
    private int v;
    private boolean w;
    private boolean x;
    private ab y;
    private int z;

    public enum From {
        VIDEO_CHANNEL("videoChannel"),
        HOT_VIDEO("hotvideo"),
        VIDEO_REC("video_rec"),
        HOME_HOT_SRC("home_hotSrc"),
        PLAY_LIST("play_list"),
        SHARE_PAGE("from_share_page"),
        FEED_FLOW("feedflow"),
        HOME_PAGE(JsInterface.PAGE_HOME),
        HOME_VIDEO_AUTO("home_video_auto"),
        PUSH("push"),
        KANDAN("kandan"),
        VIDEO_SCREEN("video_screen"),
        VIDEO_SCREEN_AUTO("video_screen_auto"),
        VIDEO_HOT_DISCUSS("video_hot_discuss"),
        PERSONAL_SPACE("personal_space"),
        HOME_PAGE_AD("ad");
        private final String a;

        static {
            String str = "videoChannel";
            VIDEO_CHANNEL = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("VIDEO_CHANNEL", 0, "videoChannel");
            str = "hotvideo";
            HOT_VIDEO = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("HOT_VIDEO", 1, "hotvideo");
            str = "video_rec";
            VIDEO_REC = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("VIDEO_REC", 2, "video_rec");
            str = "home_hotSrc";
            HOME_HOT_SRC = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("HOME_HOT_SRC", 3, "home_hotSrc");
            str = "play_list";
            PLAY_LIST = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("PLAY_LIST", 4, "play_list");
            String str2 = "from_share_page";
            SHARE_PAGE = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("SHARE_PAGE", 5, "from_share_page");
            str2 = "feedflow";
            FEED_FLOW = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("FEED_FLOW", 6, "feedflow");
            str2 = JsInterface.PAGE_HOME;
            HOME_PAGE = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("HOME_PAGE", 7, JsInterface.PAGE_HOME);
            str2 = "home_video_auto";
            HOME_VIDEO_AUTO = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("HOME_VIDEO_AUTO", 8, "home_video_auto");
            str2 = "push";
            PUSH = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("PUSH", 9, "push");
            str2 = "kandan";
            KANDAN = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("KANDAN", 10, "kandan");
            str2 = "video_screen";
            VIDEO_SCREEN = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("VIDEO_SCREEN", 11, "video_screen");
            str2 = "video_screen_auto";
            VIDEO_SCREEN_AUTO = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("VIDEO_SCREEN_AUTO", 12, "video_screen_auto");
            str2 = "video_hot_discuss";
            VIDEO_HOT_DISCUSS = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("VIDEO_HOT_DISCUSS", 13, "video_hot_discuss");
            str2 = "personal_space";
            PERSONAL_SPACE = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("PERSONAL_SPACE", 14, "personal_space");
            str2 = "ad";
            HOME_PAGE_AD = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From("HOME_PAGE_AD", 15, "ad");
            b = new com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From[]{VIDEO_CHANNEL, HOT_VIDEO, VIDEO_REC, HOME_HOT_SRC, PLAY_LIST, SHARE_PAGE, FEED_FLOW, HOME_PAGE, HOME_VIDEO_AUTO, PUSH, KANDAN, VIDEO_SCREEN, VIDEO_SCREEN_AUTO, VIDEO_HOT_DISCUSS, PERSONAL_SPACE, HOME_PAGE_AD};
        }

        private From(String str) {
            this.a = str;
        }

        public final String getText() {
            return this.a;
        }
    }

    public ShortMovieDetailActivity() {
        this.g = "video_url";
        this.p = true;
        this.z = 0;
        this.L = -1;
        this.M = 0;
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = false;
        this.V = false;
        this.X = 0;
        this.Y = new y(this);
        this.aa = new ae(this);
    }

    static /* synthetic */ int g(ShortMovieDetailActivity shortMovieDetailActivity) {
        int i = shortMovieDetailActivity.X - 1;
        shortMovieDetailActivity.X = i;
        return i;
    }

    static {
        a = "seek_to_comment";
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setContentView(2130968974);
        this.O = new com.xunlei.downloadprovider.web.base.model.b();
        this.W = new Handler();
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            if ("/resourceDetail".equals(data.getPath())) {
                this.h = data.getQueryParameter(SocialConstants.PARAM_URL);
                this.i = data.getQueryParameter(WebBrowserActivity.EXTRA_TITLE);
                this.b = data.getQueryParameter("movieKey");
                this.c = data.getQueryParameter(Impl.COLUMN_GCID);
                this.j = From.a(From.SHARE_PAGE);
            }
        } else {
            this.h = intent.getStringExtra(SocialConstants.PARAM_URL);
            this.b = intent.getStringExtra("movie_id");
            this.c = intent.getStringExtra(Impl.COLUMN_GCID);
            if (this.c == null) {
                this.c = com.umeng.a.d;
            }
            this.i = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE);
            this.j = intent.getStringExtra("from");
            this.l = intent.getStringExtra("first_img_url");
            this.k = intent.getStringExtra("play");
            this.v = intent.getIntExtra("fav_count", 0);
            this.w = intent.getBooleanExtra("has fav", false);
            this.x = intent.getBooleanExtra(a, false);
            this.H = intent.getStringExtra("category_icon");
            this.I = intent.getStringExtra("category_name");
            this.J = intent.getStringExtra("category_poster");
            this.K = intent.getStringExtra("category_v_url");
            this.L = intent.getIntExtra("category_type", -1);
            this.M = intent.getIntExtra("category_v_level", -1);
            this.n = intent.getIntExtra("played_position", 0);
            this.o = intent.getIntExtra("total_time", 0);
        }
        u uVar = new u();
        uVar.a = this.b;
        uVar.g = this.c;
        uVar.b = this.i;
        uVar.e = this.k;
        this.O.a(uVar);
        this.f = e;
        this.C = findViewById(2131756970);
        this.C.setVisibility(0);
        this.A = (ImageView) findViewById(2131755282);
        if (From.HOME_PAGE_AD.getText().contentEquals(this.j)) {
            this.A.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        } else {
            this.A.setVisibility(0);
        }
        this.B = (ImageView) findViewById(2131755212);
        this.B.setOnClickListener(new ab(this));
        this.A.setOnClickListener(new ac(this));
        this.t = (ViewGroup) findViewById(2131756966);
        this.r = (ViewGroup) findViewById(2131756967);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, e());
        layoutParams.addRule(XZBDevice.Stop);
        this.t.setLayoutParams(layoutParams);
        this.u = (TextView) findViewById(2131756968);
        this.u.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.u.setOnClickListener(new ah(this));
        this.s = findViewById(2131756969);
        this.s.setVisibility(0);
        this.q = new ShortMovieDetailFragment();
        new StringBuilder("movie id=>").append(this.b);
        Bundle bundle2 = new Bundle();
        bundle2.putString("movie_id", this.b);
        bundle2.putString(Impl.COLUMN_GCID, this.c);
        bundle2.putString("movie_title", this.i);
        bundle2.putString("movie_url", this.k);
        bundle2.putInt("movie_fav_count", this.v);
        bundle2.putBoolean("movie_has_fav", this.w);
        bundle2.putBoolean("movie_seek_to_comment", this.x);
        bundle2.putString("category_icon", this.H);
        bundle2.putString("category_name", this.I);
        bundle2.putString("category_poster", this.J);
        bundle2.putInt("category_type", this.L);
        bundle2.putString("from", this.j);
        this.q.setArguments(bundle2);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(2131756969, this.q);
        beginTransaction.commit();
        this.D = findViewById(2131756536);
        this.D.setVisibility(XZBDevice.Wait);
        this.D.setOnClickListener(new ai(this));
        this.E = (ImageView) findViewById(2131756537);
        this.d = (TextView) findViewById(2131756539);
        this.F = (TextView) findViewById(2131756538);
        this.G = (ImageView) findViewById(2131756279);
        this.G.setOnClickListener(new aj(this));
        this.y = q.a().a((Context) this, this.aa, getIntent().getIntExtra("player_id", -1));
        this.y.b = "short_video_detail_player";
        this.y.n = false;
        this.y.o.setShowControllerOnIdle(true);
        this.y.b((int) XZBDevice.DOWNLOAD_LIST_FAILED);
        this.y.o.setShouldShowLoadingBackground(true);
        this.y.a(null);
        this.y.s();
        this.S = aa.a.b(this, "auto_play_next_shortmovie", true);
        this.y.d(true);
        ab abVar = this.y;
        y alVar = new al(this);
        abVar.o.setOnControllerClickListener(alVar);
        abVar.p.setOnControllerClickListener(alVar);
        this.y.m = new am(this);
        this.y.r = new z(this);
        abVar = this.y;
        abVar.o.setVisiableListener(new aa(this));
        if (!(TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.k))) {
            ai aiVar = new ai(this.b, this.k, this.i, this.j);
            aiVar.d = this.c;
            aiVar.g = this.n;
            aiVar.k = this.l;
            this.y.a(aiVar);
        }
        this.N = new b(this);
        this.N.a = new ag(this);
        this.y.a();
        this.Z = new af(this);
    }

    public final void a(boolean z) {
        if (this.U != z) {
            this.D.clearAnimation();
            this.U = z;
            if (z) {
                this.D.setVisibility(0);
                this.D.animate().setInterpolator(new DecelerateInterpolator()).setDuration(250).translationY(AutoScrollHelper.RELATIVE_UNSPECIFIED).setListener(null);
                return;
            }
            this.D.animate().setInterpolator(new DecelerateInterpolator()).setDuration(250).translationY((float) this.D.getHeight()).setListener(new ak(this));
        }
    }

    private boolean d() {
        if (isFinishing() || this.y == null) {
            return false;
        }
        long g = (long) this.y.g();
        long h = (long) this.y.h();
        return this.S && !this.R && !this.T && h > 3500 && g > 3500 && g - h <= 3500;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.y != null && this.y.l) {
            float rawY = motionEvent.getRawY();
            int[] iArr = new int[2];
            this.t.getLocationOnScreen(iArr);
            if (rawY > ((float) (iArr[1] + this.t.getMeasuredHeight())) && this.N.a(motionEvent)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        new StringBuilder("orientation=").append(configuration.orientation == 2 ? "landscape" : "portrait");
    }

    private int e() {
        if (this.z == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.z = (displayMetrics.widthPixels * 9) / 16;
        }
        return this.z;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString("video_url", this.k);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        this.k = bundle.getString("video_url");
    }

    protected void onResume() {
        super.onResume();
        this.P = false;
        bk.a(this.b, this.j);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.Y, intentFilter);
        if (this.Q) {
            this.Q = false;
            this.y.a(this.m);
        }
    }

    protected void onPause() {
        this.P = true;
        unregisterReceiver(this.Y);
        if (this.y != null) {
            if (this.y.i()) {
                this.y.c(true);
                a(false);
            } else {
                this.y.e.a();
                this.R = false;
                this.V = false;
            }
        }
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.y != null) {
            q.a().a(this.y);
            this.y = null;
        }
    }

    public void onBackPressed() {
        if (!this.y.r()) {
            finish();
            if (From.SHARE_PAGE.equals(this.j)) {
                MainTabActivity.a((Context) this, "thunder");
                return;
            }
            RunningTaskInfo runningTaskInfo = (RunningTaskInfo) ((ActivityManager) BrothersApplication.a().getSystemService("activity")).getRunningTasks(1).get(0);
            if (runningTaskInfo.baseActivity.equals(runningTaskInfo.topActivity)) {
                MainTabActivity.a((Context) this, "thunder");
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 101) {
            LoginHelper.a();
            if (LoginHelper.c() && this.q != null) {
                this.q.b();
            }
        } else if (i != 102) {
            d.b().a(i, i2, intent);
        } else if (this.y != null && this.p) {
            this.y.c();
        }
    }

    public static void a(Context context, int i, String str, From from, String str2, String str3, String str4, String str5, String str6, String str7, int i2, int i3) {
        Intent intent = new Intent(context, ShortMovieDetailActivity.class);
        intent.putExtra("from", From.a(from));
        intent.putExtra(SocialConstants.PARAM_URL, str5);
        intent.putExtra("movie_id", str2);
        intent.putExtra(Impl.COLUMN_GCID, str3);
        intent.putExtra("category_name", str);
        intent.putExtra("category_type", i);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str4);
        intent.putExtra("play", str6);
        intent.putExtra("first_img_url", str7);
        intent.putExtra("fav_count", i2);
        intent.putExtra("has fav", false);
        intent.putExtra("player_id", i3);
        context.startActivity(intent);
    }

    public static void a(Context context, int i, From from, ao aoVar, boolean z) {
        if (aoVar != null) {
            e = aoVar;
            Intent intent = new Intent(context, ShortMovieDetailActivity.class);
            new StringBuilder("set movie id =>").append(aoVar.a);
            intent.putExtra("from", From.a(from));
            intent.putExtra(SocialConstants.PARAM_URL, com.umeng.a.d);
            intent.putExtra("movie_id", aoVar.a);
            intent.putExtra(Impl.COLUMN_GCID, aoVar.q);
            intent.putExtra(WebBrowserActivity.EXTRA_TITLE, aoVar.b);
            intent.putExtra("play", aoVar.c);
            intent.putExtra("first_img_url", aoVar.d);
            intent.putExtra("fav_count", aoVar.h);
            intent.putExtra("has fav", aoVar.e);
            intent.putExtra(a, z);
            intent.putExtra("category_icon", aoVar.l);
            intent.putExtra("category_name", aoVar.n);
            intent.putExtra("category_poster", aoVar.m);
            intent.putExtra("category_v_url", aoVar.B);
            intent.putExtra("category_v_level", aoVar.A);
            intent.putExtra("category_type", aoVar.g);
            intent.putExtra("player_id", i);
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(0, 0);
            }
        }
    }

    public static void a(Context context, From from, String str, String str2, String str3, int i, int i2) {
        Intent intent = new Intent(context, ShortMovieDetailActivity.class);
        intent.putExtra("from", From.a(from));
        intent.putExtra("movie_id", str);
        intent.putExtra(Impl.COLUMN_GCID, str2);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str3);
        intent.putExtra("play", null);
        intent.putExtra("played_position", i);
        intent.putExtra("total_time", i2);
        context.startActivity(intent);
    }

    public final void a(u uVar, boolean z, boolean z2) {
        new StringBuilder("onRecommendVideoToPlay--fromItemClick=").append(z).append("|autoNext=").append(z2);
        if (this.y != null) {
            String a = z ? From.a(From.VIDEO_REC) : z2 ? From.a(From.VIDEO_SCREEN_AUTO) : From.a(From.VIDEO_SCREEN);
            ai aiVar = new ai(uVar.a, uVar.e, uVar.b, a);
            aiVar.d = uVar.g;
            aiVar.j = uVar.t;
            this.y.a(aiVar);
            c(uVar);
            this.O.a(uVar);
            this.y.e(!this.O.a());
        }
    }

    public final void a(String str, u uVar) {
        if (this.y != null) {
            if ("ok".contentEquals(str)) {
                this.u.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                ai u = this.y.u();
                if ((u == null || !TextUtils.equals(u.c, uVar.a)) && uVar != null) {
                    this.m = new ai(uVar.a, uVar.e, uVar.b, this.j);
                    this.m.d = uVar.g;
                    if (this.n > 0) {
                        this.m.g = this.n;
                        this.n = 0;
                    }
                    this.Q = true;
                    if (!this.P) {
                        this.Q = false;
                        this.y.a(this.m);
                    }
                    c(uVar);
                }
            } else if ("reject".contentEquals(str)) {
                this.u.setText(getResources().getString(2131233085));
                this.u.setVisibility(0);
                this.y.e.a();
                this.R = false;
                this.V = false;
                com.xunlei.downloadprovider.download.c.a a = com.xunlei.downloadprovider.download.c.a.a();
                if (a.a == a.b) {
                    a.c();
                }
            }
        }
    }

    private void c(u uVar) {
        this.b = uVar.a;
        this.c = uVar.g;
        this.i = uVar.b;
        this.k = uVar.e;
        this.H = uVar.h;
        this.J = uVar.j;
        this.M = uVar.r;
        this.K = uVar.s;
        this.I = uVar.i;
        this.L = uVar.n;
        this.v = uVar.l;
        this.w = uVar.k;
        this.V = false;
        this.R = false;
    }

    public final void a(List<u> list) {
        boolean z = true;
        if (this.y != null) {
            if (list == null || list.isEmpty()) {
                ab abVar = this.y;
                if (this.O.b()) {
                    z = false;
                }
                abVar.f(z);
                return;
            }
            this.y.f(true);
            u uVar = (u) list.get(0);
            com.nostra13.universalimageloader.core.d.a().a(uVar.c, this.E, new ad(this));
            this.F.setText(uVar.b);
        }
    }

    public final void a() {
        if (this.y != null) {
            this.y.c(false);
            a(false);
        }
    }

    public final void b(boolean z) {
        int i = 0;
        this.t.setVisibility(z ? XZBDevice.Wait : 0);
        ImageView imageView = this.A;
        if (z || From.HOME_PAGE_AD.getText().contentEquals(this.j)) {
            i = XZBDevice.DOWNLOAD_LIST_ALL;
        }
        imageView.setVisibility(i);
    }

    public final void a(u uVar) {
        if (this.f != null && uVar != null && TextUtils.equals(this.f.a, uVar.a)) {
            this.f.e = uVar.k;
            this.f.h = uVar.l;
        }
    }

    public final void b(u uVar) {
        if (this.f != null && uVar != null && TextUtils.equals(this.f.a, uVar.a)) {
            new StringBuilder("onCommentNumChanged == ").append(uVar.f);
            if (uVar.f != null) {
                this.f.w = Integer.parseInt(uVar.f);
            }
        }
    }

    public final void a(c cVar) {
        if (this.f != null && cVar != null && this.f.r == cVar.a) {
            this.f.u = cVar.n;
        }
    }

    public final void c(boolean z) {
        this.S = z;
        if (this.y != null) {
            this.y.z = z;
        }
        if (!z) {
            this.W.removeCallbacks(this.Z);
            this.V = false;
            a(false);
        }
    }

    public final void b() {
        if (this.q != null) {
            ShortMovieDetailFragment shortMovieDetailFragment = this.q;
            if (shortMovieDetailFragment.b != null && !TextUtils.isEmpty(shortMovieDetailFragment.b.e)) {
                com.xunlei.downloadprovider.web.base.model.d dVar = shortMovieDetailFragment.a;
                FragmentActivity fragmentActivity = shortMovieDetailFragment.mActivity;
                u uVar = shortMovieDetailFragment.b;
                if (fragmentActivity instanceof ThunderTask) {
                    String str = uVar.b;
                    String str2 = uVar.e;
                    DownData downData = new DownData();
                    downData.a = str + ".mp4";
                    downData.e = str2;
                    g gVar = new g();
                    gVar.b = str2;
                    gVar.a = 17;
                    gVar.d = "break/break_shortvideo_hot";
                    gVar.c = "android_client";
                    ((ThunderTask) fragmentActivity).createTask(downData, dVar.f, gVar, false);
                    StatReporter.reportOverallDownload("break_shortvideo_hot");
                }
            } else if (com.xunlei.xllib.a.b.a(shortMovieDetailFragment.mActivity)) {
                XLToast.b(shortMovieDetailFragment.mActivity, XLToastType.XLTOAST_TYPE_ALARM, shortMovieDetailFragment.mActivity.getResources().getString(2131231369));
            } else {
                XLToast.b(shortMovieDetailFragment.mActivity, XLToastType.XLTOAST_TYPE_ALARM, shortMovieDetailFragment.mActivity.getResources().getString(R.string.no_net_work_4_toast));
            }
        }
    }

    public final void c() {
        if (this.f != null) {
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("report_target", XZBDevice.DOWNLOAD_LIST_FAILED);
            intent.putExtra("extra_video_res_id", this.f.a);
            intent.putExtra("extra_video_gcid", this.f.q);
            startActivity(intent);
        }
        if (this.y != null) {
            this.p = this.y.t();
        }
    }

    static /* synthetic */ void l(ShortMovieDetailActivity shortMovieDetailActivity) {
        shortMovieDetailActivity.W.removeCallbacks(shortMovieDetailActivity.Z);
        shortMovieDetailActivity.W.post(shortMovieDetailActivity.Z);
    }
}
