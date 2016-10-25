package com.xunlei.tdlive;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.UMShareAPI;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.d.b;
import com.xunlei.tdlive.d.e;
import com.xunlei.tdlive.d.g;
import com.xunlei.tdlive.im.CloseRoomMessage;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveClosePublishRoomRequest;
import com.xunlei.tdlive.protocol.XLLivePersonVerifyRequest;
import com.xunlei.tdlive.protocol.XLLivePersonVerifyRequest.PersonVerifyResp;
import com.xunlei.tdlive.protocol.XLLiveQueryStreamInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

public class LivePlayerActivity extends BaseActivity {
    public static long a;
    private static com.xunlei.tdlive.d.a b;
    private static b c;
    private static XLLivePersonVerifyRequest d;
    private au e;
    private co f;
    private FrameLayout g;
    private FrameLayout h;
    private a i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private BroadcastReceiver q;

    public static class a {
        public String a;
        public String b;
        public String c;
    }

    public LivePlayerActivity() {
        this.h = null;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = new ab(this);
    }

    static {
        b = e.h();
        c = g.d();
        d = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_player);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.g = (FrameLayout) findViewById(R.id.play_loading);
        this.h = (FrameLayout) findViewById(R.id.playView);
        if (getIntent().getAction() == null || getIntent().getAction().equals("com.xunlei.tdlive.ACTION_LIVE_PLAY")) {
            String str;
            this.n = false;
            this.i = new a();
            this.i.a = getIntent().getStringExtra("roomid");
            this.i.b = getIntent().getStringExtra("userid");
            this.i.c = getIntent().getStringExtra("stream_pull");
            this.h.postDelayed(new af(this), 500);
            if (!b.d()) {
                b.a(this, null, this.i.c, null);
            }
            b.a(this.h);
            b.a(new ag(this));
            String stringExtra = getIntent().getStringExtra("avatar");
            Object stringExtra2 = getIntent().getStringExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL);
            if (TextUtils.isEmpty(stringExtra2)) {
                str = stringExtra;
            }
            if (!TextUtils.isEmpty(str)) {
                com.xunlei.tdlive.util.a.a((Context) this).a((ImageView) findViewById(R.id.thumb), str, new ah(this));
            }
            View findViewById = this.g.findViewById(R.id.play_loading_ani);
            findViewById.setAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.2f));
            findViewById.getAnimation().setRepeatMode(1);
            findViewById.getAnimation().setRepeatCount(-1);
            findViewById.getAnimation().setDuration(800);
            findViewById.getAnimation().start();
            setTimer(IHost.HOST_NOFITY_REFRESH_LIST, 1000);
        } else if ("com.xunlei.tdlive.ACTION_LIVE_PUBLISH".equals(getIntent().getAction())) {
            this.n = true;
            b();
        } else if ("com.xunlei.tdlive.ACTION_LIVE_PUBLISH_RESTORE".equals(getIntent().getAction())) {
            this.n = true;
            a(getIntent().getStringExtra("roomid"));
        }
        try {
            registerReceiver(this.q, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Exception e) {
        }
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.p = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!isFinishing()) {
            b.a();
            c.a();
        }
        try {
            unregisterReceiver(this.q);
        } catch (Exception e) {
        }
        this.e = null;
        this.f = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.a(configuration);
        c.a(configuration);
    }

    protected void onPause() {
        super.onPause();
        b.b();
        c.b();
        if (this.e != null) {
            this.e.j();
        }
    }

    protected void onResume() {
        super.onResume();
        b.c();
        c.c();
        if (this.e != null) {
            this.e.i();
        }
        if (!this.p) {
            this.q.onReceive(this, getIntent());
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            UMShareAPI.get(this).onActivityResult(i, i2, intent);
        } catch (Exception e) {
        }
    }

    protected void onTimer(int i) {
        if (i == 1001) {
            if (this.i != null && this.i.a != null) {
                a.a(this.i.a, this.j, this.k, this.l, this.m);
            }
        } else if (i == 1000 && this.e != null) {
            this.g.setVisibility(this.e.d() ? XZBDevice.DOWNLOAD_LIST_ALL : 0);
        }
    }

    private void a(String str) {
        if (str == null || str.length() <= 0) {
            XLog.e("LivePlayerActivity", "doPublishRestore roomid null");
            finish();
            return;
        }
        String k = f.a((Context) this).k();
        String l = f.a((Context) this).l();
        ObjectCallBack aiVar = new ai(this);
        this.i = new a();
        this.i.b = k;
        this.i.a = str;
        this.e = new au(this, true, this.i.a, this.i.b);
        this.e.setOwnerActivity(this);
        this.e.show();
        new XLLiveQueryStreamInfoRequest(k, l, 0).send(new aj(this, k, str, l, aiVar));
    }

    private void b() {
        String k = f.a((Context) this).k();
        String l = f.a((Context) this).l();
        a(k, l);
        new XLLiveQueryStreamInfoRequest(k, l, 0).send(new am(this, k));
    }

    private void a(String str, String str2) {
        this.f = new co(this);
        this.f.setOwnerActivity(this);
        this.f.a();
        this.f.a("\u76f4\u64ad\u51c6\u5907\u4e2d...", false);
        this.f.a(new ap(this, str, str2));
    }

    public void finish() {
        XLog.d("LivePlayerActivity", XLog.getCallerStackTraceElement().toString());
        if (!this.n || this.i == null) {
            if (this.e != null && this.e.c()) {
                int i;
                int i2;
                int i3;
                String str = com.umeng.a.d;
                String str2 = com.umeng.a.d;
                CloseRoomMessage e = this.e.e();
                if (e == null || e.data == null) {
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                } else {
                    i3 = e.data.current_point;
                    i2 = e.data.current_user;
                    i = b(e.data.start_time, e.data.end_time);
                }
                com.xunlei.tdlive.play.a.c.a f = this.e.a.getPresenter().f();
                if (!(f == null || f.a == null)) {
                    str = f.a.a;
                    String str3 = f.a.c;
                    if (str == null) {
                        str = com.umeng.a.d;
                    }
                    if (str3 == null) {
                        str3 = com.umeng.a.d;
                    }
                    str2 = str3;
                }
                LivePublishEndingActivity.a(this, true, false, this.e.f(), i3, i2, i, str, str2);
            }
            c();
        } else if (this.e == null || !this.e.b()) {
            cn cnVar = new cn(this);
            cnVar.setCancelable(true);
            cnVar.setCanceledOnTouchOutside(true);
            cnVar.a(new ac(this));
            cnVar.show();
        } else {
            XLog.e("LivePlayerActivity", "\u5f02\u5e38\u6216\u670d\u52a1\u5668\u8ba9\u5173\uff0c\u9759\u9ed8\u5173\u95ed\u623f\u95f4");
            String a = this.e.a();
            if (a == null || a.length() <= 0) {
                b(a);
            } else {
                new c(this, "\u76f4\u64ad\u7ed3\u675f", this.e.a(), "\u786e\u5b9a", new String[0]).b(new at(this, a));
            }
        }
    }

    private void b(String str) {
        XLog.d("LivePlayerActivity", XLog.getCallerStackTraceElement().toString());
        if (!this.o) {
            this.o = true;
            if (!this.n || this.i == null || this.i.a == null || this.i.a.length() <= 0) {
                c();
            } else {
                new XLLiveClosePublishRoomRequest(f.a((Context) this).k(), f.a((Context) this).l(), this.i.a, str).send(new ad(this));
            }
        }
    }

    private void c() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
            Map hashMap = new HashMap();
            hashMap.put("hostid", this.i.b);
            hashMap.put("viewernum", String.valueOf(this.e.g()));
            hashMap.put("likenum", String.valueOf(this.e.h()));
            hashMap.put("follow", MessageService.MSG_DB_READY_REPORT);
            q.a("user_like", null, null, hashMap);
        }
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
        b.a();
        c.a();
        super.finish();
    }

    @SuppressLint({"SimpleDateFormat"})
    private int b(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return (int) ((simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()) / 1000);
        } catch (Exception e) {
            try {
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                return (int) ((simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()) / 1000);
            } catch (Exception e2) {
                return 0;
            }
        }
    }

    private void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("hostid", this.i.b);
            hashMap.put(SocialConstants.PARAM_URL, Uri.encode(this.i.c));
            hashMap.put("playid", q.e("live_room_show").d("playid"));
            hashMap.put("network", String.valueOf(ac.b()));
            hashMap.put("duration", String.valueOf(i4));
            hashMap.put("start_time", String.valueOf(j));
            hashMap.put("connect_duration", String.valueOf(i));
            hashMap.put("load_duration", String.valueOf(i3));
            hashMap.put("errorcode", String.valueOf(i5));
            q.a("live_stop", str, null, hashMap);
        } catch (Exception e) {
        }
    }

    private void b(long j, int i, int i2, int i3, int i4, String str, int i5) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("hostid", this.i.b);
            hashMap.put(SocialConstants.PARAM_URL, Uri.encode(this.i.c));
            hashMap.put("playid", q.e("live_room_show").d("playid"));
            hashMap.put("network", String.valueOf(ac.b()));
            hashMap.put("video_type", "live");
            hashMap.put("duration", String.valueOf(i4));
            hashMap.put("start_time", String.valueOf(j));
            hashMap.put("connect_duration", String.valueOf(i));
            hashMap.put("buffer_duration", String.valueOf(i2));
            hashMap.put("load_duration", String.valueOf(i3));
            hashMap.put("errorcode", String.valueOf(i5));
            q.a("video_play_stop", str, null, hashMap);
        } catch (Exception e) {
        }
    }

    private boolean c(String str) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(com.xunlei.tdlive.modal.e.g);
            for (int i = 0; i < jsonWrapper.getLength(); i++) {
                if (jsonWrapper.getString(i, com.umeng.a.d).equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void a(Activity activity, String str, int i, boolean z) {
        ObjectCallBack aeVar = new ae(str, activity, i);
        if (z) {
            String k = f.a((Context) activity).k();
            String l = f.a((Context) activity).l();
            if (d != null) {
                d.cancel();
            }
            XLLivePersonVerifyRequest xLLivePersonVerifyRequest = new XLLivePersonVerifyRequest(k, l);
            d = xLLivePersonVerifyRequest;
            xLLivePersonVerifyRequest.send(aeVar);
            return;
        }
        aeVar.onResponse(0, com.umeng.a.d, new PersonVerifyResp());
    }

    public static void a(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("time", 0);
        if (longExtra > 0) {
            a = longExtra;
        }
        b.a(context, null, intent.getStringExtra("stream_pull"), null);
        context.startActivity(new Intent(intent).setFlags(268435456).setAction("com.xunlei.tdlive.ACTION_LIVE_PLAY").setClass(context, LivePlayerActivity.class));
        q.e("live_room_show").c("playid").a("hostid", intent.getStringExtra("userid")).a("viewernum", intent.getStringExtra("onlinenum")).a("follow", intent.getIntExtra("follow", 0)).a(intent.getStringExtra("from")).b("live").a("time").b(new String[0]).a("time", SystemClock.elapsedRealtime());
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, String str7) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            a(context, new Intent(context, LivePlayerActivity.class).setAction("com.xunlei.tdlive.ACTION_LIVE_PLAY").putExtra("roomid", str).putExtra("userid", str2).putExtra("avatar", str4).putExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, str5).putExtra("onlinenum", str6).putExtra("stream_pull", str3).putExtra("follow", i2).putExtra("from", str7));
        }
    }
}
