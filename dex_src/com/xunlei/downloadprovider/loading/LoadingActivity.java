package com.xunlei.downloadprovider.loading;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.Request;
import com.tencent.connect.common.Constants;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.b.j;
import com.xunlei.downloadprovider.ad.splash.b.k;
import com.xunlei.downloadprovider.ad.splash.b.l;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.GuideActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.MainTabSpec.Tab;
import com.xunlei.downloadprovider.frame.user.UserFeedBackUmActivity;
import com.xunlei.downloadprovider.model.e;
import com.xunlei.downloadprovider.model.f;
import com.xunlei.downloadprovider.model.protocol.networkcheck.IPAddressErrorActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.thirdpart.ThirdPartActivity;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public class LoadingActivity extends BaseActivity implements c {
    public static final String a;
    public static boolean b;
    private a A;
    private b B;
    public String c;
    public boolean d;
    private boolean e;
    private Intent f;
    private View g;
    private ViewGroup h;
    private View i;
    private ImageView j;
    private View k;
    private ImageView l;
    private boolean m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private com.xunlei.downloadprovider.vod.a.a s;
    private String t;
    private h u;
    private com.xunlei.downloadprovider.ad.splash.b.a v;
    private d w;
    private boolean x;
    private boolean y;
    private boolean z;

    public LoadingActivity() {
        this.f = null;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = false;
        this.s = null;
        this.t = "local_key_localpath_for_loading";
        this.v = null;
        this.w = null;
        this.x = false;
        this.d = false;
        this.y = false;
        this.z = false;
        this.A = new f(this);
        this.B = new b(this.A);
    }

    static {
        a = LoadingActivity.class.getName();
        b = false;
    }

    protected void onCreate(Bundle bundle) {
        Object obj = 1;
        com.xunlei.downloadprovider.frame.advertisement.b.a.a a = com.xunlei.downloadprovider.frame.advertisement.b.a.a.a();
        long currentTimeMillis = System.currentTimeMillis();
        a.b = k.a() == 0;
        a.a = currentTimeMillis;
        getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
        if (!ai.a()) {
            FeedbackAgent feedbackAgent = new FeedbackAgent(getApplicationContext());
            new FeedbackAgent(getApplicationContext()).getDefaultConversation().sync(new a(this));
            feedbackAgent.openFeedbackPush();
        }
        if (a()) {
            com.xunlei.downloadprovider.frame.advertisement.b.d.a((Context) this).a("android_guide", "android_guide", null);
            new f(new e(this)).start();
        }
        super.onCreate(bundle);
        new StringBuilder().append(BrothersApplication.c()).append(" onCreate ");
        if (VERSION.SDK_INT < 16) {
            getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1284);
        }
        Intent intent = getIntent();
        this.m = intent.getBooleanExtra("key_is_from_notification", false);
        this.n = intent.getStringExtra("key_notification_tag");
        if (this.m) {
            StatReporter.reportPushResClick(this.n, intent.getIntExtra("key_push_type", -1));
        }
        this.o = intent.getStringExtra("vod_url");
        this.p = intent.getStringExtra("vod_title");
        this.f = (Intent) intent.getParcelableExtra("business_intent");
        if (this.f != null) {
            this.c = this.f.getStringExtra(com.xunlei.downloadprovider.thirdpart.a.a);
        }
        if (intent != null) {
            this.s = com.xunlei.downloadprovider.vod.a.c.a(intent);
        }
        if (e()) {
            setRequestedOrientation(0);
        }
        setContentView(2130968858);
        com.xunlei.downloadprovider.ad.home.a.c a2 = com.xunlei.downloadprovider.ad.home.a.c.a(BrothersApplication.a());
        com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.c.a("android_advertise").b("attribute1", "adv_homeflow_first_request"));
        new com.xunlei.downloadprovider.ad.common.d.a.b(4).a(new com.xunlei.downloadprovider.ad.home.a.d(a2), "1195");
        this.g = findViewById(R.id.loading_layout);
        this.h = (ViewGroup) findViewById(2131755275);
        this.i = findViewById(R.id.bottom_layout);
        this.i.setVisibility(XZBDevice.Wait);
        this.j = (ImageView) findViewById(2131756434);
        this.k = findViewById(2131755231);
        this.k.setVisibility(XZBDevice.Wait);
        this.l = (ImageView) findViewById(2131756435);
        this.l.setVisibility(XZBDevice.Wait);
        View findViewById = findViewById(2131756294);
        int d = com.xunlei.xllib.a.d.d(this);
        if (d > 0) {
            LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
            layoutParams.bottomMargin -= d;
            findViewById.setLayoutParams(layoutParams);
        }
        try {
            com.xunlei.downloadprovider.pushmessage.c.b a3 = com.xunlei.downloadprovider.pushmessage.c.b.a();
            if (a3.a == null) {
                currentTimeMillis = -1;
            } else {
                currentTimeMillis = a3.a.getLong("update_last_time", -1);
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis != -1) {
                if (currentTimeMillis <= 0 || currentTimeMillis + 86400000 > currentTimeMillis2) {
                    boolean z = false;
                }
            }
            if (z && a3.a != null) {
                a3.a.edit().putLong("update_last_time", currentTimeMillis2).apply();
            }
            if (z) {
                com.xunlei.downloadprovider.pushmessage.d.a a4 = com.xunlei.downloadprovider.pushmessage.d.a.a(BrothersApplication.a);
                if (com.xunlei.c.a.b.a(a4.b)) {
                    com.xunlei.downloadprovider.pushmessage.d.b bVar = a4.a;
                    Request aVar = new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/push_services/peer/tags/", new com.xunlei.downloadprovider.pushmessage.d.e(bVar), new com.xunlei.downloadprovider.pushmessage.d.f(bVar));
                    aVar.setShouldCache(false);
                    aVar.setRetryPolicy(new com.android.volley.f(5000, 1, 1.0f));
                    com.xunlei.downloadprovider.j.a.a().d().a(aVar);
                }
            }
        } catch (Exception e) {
        }
    }

    private static boolean a() {
        return k.a() == 0;
    }

    private void b() {
        new StringBuilder("jumpWhenCanClick this.hasWindowFocus():").append(hasWindowFocus()).append("  can jump: ").append(this.d);
        if (this.d) {
            c();
        } else {
            this.d = true;
        }
    }

    private void c() {
        if (this.f != null) {
            h();
        } else {
            g();
        }
    }

    private void d() {
        this.B.postDelayed(new b(this), 1000);
    }

    private boolean e() {
        return this.s != null;
    }

    public final void a(DownloadService downloadService) {
        int i;
        boolean a = a();
        if (this.e) {
            i = 0;
        } else {
            i = 1;
        }
        if ((i | a) != 0) {
            IPAddressErrorActivity.a(getApplicationContext());
            IPAddressErrorActivity.a();
            i = (int) (((double) (SystemClock.uptimeMillis() - BrothersApplication.b)) / 1000.0d);
            if (0 != BrothersApplication.b) {
                StatReporter.reportStartupTime((long) i, com.xunlei.xllib.a.b.c(getApplicationContext()));
                BrothersApplication.b = 0;
            }
            int a2 = k.a();
            if (!this.m || this.n == null) {
                if (e()) {
                    com.xunlei.downloadprovider.vod.a.c.a(this.s);
                    finish();
                    overridePendingTransition(0, 0);
                } else if (a2 == 0) {
                    if (this.f != null) {
                        h();
                        finish();
                    } else {
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        Display defaultDisplay = getWindowManager().getDefaultDisplay();
                        defaultDisplay.getMetrics(displayMetrics);
                        String str = displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
                        Point point = new Point();
                        String str2 = com.umeng.a.d;
                        if (VERSION.SDK_INT > 12) {
                            defaultDisplay.getSize(point);
                            str2 = point.x + "*" + point.y;
                        }
                        StatReporter.reportInstall(str, str2);
                        Intent intent = new Intent(this, GuideActivity.class);
                        intent.setFlags(67108864);
                        this.B.postDelayed(new g(this, intent), 400);
                        if (!com.xunlei.downloadprovider.i.a.b()) {
                            ThirdPartActivity.a(this, "shortcut_download");
                        }
                    }
                }
            } else if (this.q) {
                finish();
                return;
            } else if (this.n.equals("VodPlayer")) {
                this.q = true;
                i iVar = new i();
                iVar.g = VodVideoFormat.flv;
                iVar.h = VodSourceType.normal;
                iVar.a = this.p;
                iVar.e = this.o;
                iVar.f = 1;
                Bundle bundle = new Bundle();
                bundle.putBoolean(VodPlayerActivity.BUNDEL_KEY_IS_FROM_NOTIFICATION, true);
                VodUtil.a();
                VodUtil.a((Context) this, iVar, bundle);
            }
            if (e()) {
                f();
            }
            new com.xunlei.downloadprovider.xl7.a(null, Integer.valueOf(0)).a(com.umeng.a.d);
            k.a(a2 + 1);
        }
    }

    private void f() {
        this.g.setBackgroundResource(2130838610);
        this.i.setVisibility(XZBDevice.Wait);
        this.j.setVisibility(XZBDevice.Wait);
        this.l.setVisibility(XZBDevice.Wait);
    }

    public void onResume() {
        super.onResume();
        if (!this.x) {
            this.x = true;
            com.xunlei.downloadprovider.ad.splash.b.i a = com.xunlei.downloadprovider.ad.splash.b.i.a();
            List arrayList = new ArrayList(1);
            arrayList.add("1115");
            com.xunlei.downloadprovider.ad.common.d.b.b.a().a(arrayList, new j(a));
            if (e()) {
                f();
            } else {
                String str;
                this.u = k.b(this.t);
                this.u.n = k.c(this.t);
                StringBuilder stringBuilder = new StringBuilder("\u8282\u5047\u65e5 mLoadingData: ");
                if (this.u == null) {
                    str = "null";
                } else {
                    str = this.u.toString();
                }
                stringBuilder.append(str);
                new StringBuilder("setLoadingImg: mLoadingDataForLoading --> ").append(this.u);
                if (!a() && this.u != null && this.u.m && this.u.n != null) {
                    this.j.setBackgroundDrawable(new BitmapDrawable(this.u.n));
                    this.j.setVisibility(0);
                    this.i.setVisibility(0);
                    this.k.setVisibility(XZBDevice.Wait);
                } else if (this.g.getBackground() == null) {
                    this.i.setVisibility(XZBDevice.Wait);
                    this.j.setVisibility(XZBDevice.Wait);
                    this.k.setVisibility(0);
                    if (com.xunlei.downloadprovider.i.a.f() != -1) {
                        this.l.setVisibility(0);
                        this.l.setImageResource(com.xunlei.downloadprovider.i.a.f());
                    } else {
                        this.l.setVisibility(XZBDevice.Wait);
                    }
                }
                if (!com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().b) {
                    com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().c = System.currentTimeMillis();
                }
                com.xunlei.downloadprovider.b.a iVar = new i(this.B);
                stringBuilder = new StringBuilder();
                stringBuilder.append("http://m.sjzhushou.com/startimg/start/");
                stringBuilder.append(com.xunlei.downloadprovider.a.b.g()).append(SocializeConstants.OP_DIVIDER_MINUS).append(com.xunlei.downloadprovider.a.b.w()).append(".js");
                com.xunlei.downloadprovider.b.c.e aVar = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), Constants.HTTP_GET, null, null, null, new a((byte) 0), 1000, 1000);
                new StringBuilder("getSplashData url --> ").append(null);
                aVar.setBpOnDataLoaderCompleteListener(new j(iVar));
                iVar.setBpFuture(aVar);
                i.runBox(iVar);
                com.xunlei.downloadprovider.frame.advertisement.b.a.a.a();
                String str2 = "adv_launch_time_until_load_ad";
                com.xunlei.downloadprovider.ad.splash.c.a.a(g.a("android_advertise", str2, str2).a("time", SystemClock.uptimeMillis() - BrothersApplication.b));
                if (a()) {
                    ThunderReporter.a.a("guide image");
                } else {
                    if (r.c().e != null) {
                        r.a.a a2 = r.c().e.a();
                        new StringBuilder("splashProcess adSwitch.canLoadLaunchAD: ").append(a2.b);
                        if (!a2.b) {
                            ThunderReporter.a.a("launch ad switch was closed");
                            d();
                        }
                    }
                    com.xunlei.downloadprovider.ad.splash.c.a.a(0);
                    if (com.xunlei.xllib.a.b.a(this)) {
                        AD_TYPE a3 = com.xunlei.downloadprovider.ad.common.b.a(com.xunlei.downloadprovider.ad.splash.b.i.a().b());
                        new StringBuilder("splashProcess show adType: ").append(a3.name());
                        k kVar = new k();
                        this.w = new d(Math.max(r.c().e.b(), XZBDevice.DOWNLOAD_LIST_FAILED));
                        this.w.a(new c(this));
                        l dVar = new d(this);
                        this.v = k.a(0, a3, this, this.h, dVar, this.w);
                        if (a3 == AD_TYPE.SOURCE_SSP_FLAG) {
                            this.v.a(k.a(0, AD_TYPE.SOURCE_GDT_FLAG, this, this.h, dVar, this.w));
                        }
                        this.v.b();
                        this.w.a();
                    } else {
                        d();
                    }
                }
            }
        }
        this.r = true;
        new StringBuilder("DownloadService.getInstance() == null: ").append(DownloadService.a() == null);
        if (DownloadService.a() == null) {
            DownloadService.a((c) this);
        } else {
            a(DownloadService.a());
        }
        b();
    }

    protected void onPause() {
        super.onPause();
        this.r = false;
        this.d = false;
        com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().c = -1;
        com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().d = -1;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        moveTaskToBack(true);
        return false;
    }

    protected void onDestroy() {
        if (!(this.u == null || this.u.n == null || this.u.n.isRecycled())) {
            this.u.n.recycle();
            this.u.n = null;
        }
        super.onDestroy();
    }

    private void g() {
        new StringBuilder("doGoToMainTab isActivityVisible").append(this.r);
        MainTabActivity.a((Context) this, Tab.THUNDER.getTag());
        finish();
    }

    private void h() {
        com.xunlei.downloadprovider.thirdpart.a.a.a(this, this.f);
        finish();
    }

    static /* synthetic */ void a(LoadingActivity loadingActivity, List list) {
        if (list != null && list.size() > 0) {
            String format;
            if (list.size() == 1) {
                format = String.format(loadingActivity.getResources().getString(2131232828), new Object[]{Integer.valueOf(1)});
            } else {
                format = String.format(loadingActivity.getResources().getString(2131232828), new Object[]{Integer.valueOf(list.size())});
            }
            CharSequence string = loadingActivity.getString(2131232827);
            try {
                NotificationManager notificationManager = (NotificationManager) loadingActivity.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
                PendingIntent activity = PendingIntent.getActivity(loadingActivity, (int) System.currentTimeMillis(), new Intent(loadingActivity, UserFeedBackUmActivity.class), 0);
                Notification build = new Builder(loadingActivity).setAutoCancel(true).setTicker(string).setWhen(System.currentTimeMillis()).setSmallIcon(2130837711).setDefaults(1).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).setContentTitle(string).setContentText(r1).build();
                build.contentIntent = activity;
                notificationManager.notify(0, build);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
