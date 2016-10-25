package com.xunlei.tdlive.play.a;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.open.SocialConstants;
import com.xunlei.tdlive.d.c;
import com.xunlei.tdlive.play.a.aa.b;
import com.xunlei.tdlive.play.a.ar.a;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.play.view.o;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: ReplayActivityPresenter.java
public class v extends p implements b, a {
    public LiveReplayActivity a;
    public o b;
    private com.xunlei.tdlive.d.a c;
    private String d;
    private String e;
    private String f;
    private String g;
    private boolean h;
    private BroadcastReceiver i;

    public v(LiveReplayActivity liveReplayActivity) {
        super(liveReplayActivity);
        this.c = c.h();
        this.h = false;
        this.i = new w(this);
        this.a = liveReplayActivity;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        Context context = this.a;
        this.d = f().getStringExtra("roomid");
        this.e = f().getStringExtra("stream_pull");
        this.f = f().getStringExtra("userid");
        this.g = f().getStringExtra("onlinenum");
        if (this.g == null) {
            this.g = MessageService.MSG_DB_READY_REPORT;
        }
        this.c.a(context, context.b(), this.e, new x(this));
        this.b = new o(context, false, this.d, this, this);
        this.b.setOwnerActivity(context);
        if (!this.b.isShowing()) {
            this.b.show();
        }
        Object stringExtra = f().getStringExtra("avatar");
        CharSequence stringExtra2 = f().getStringExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL);
        if (!TextUtils.isEmpty(stringExtra2)) {
            CharSequence charSequence = stringExtra2;
        }
        if (!TextUtils.isEmpty(r0)) {
            com.xunlei.tdlive.util.a.a(context).a(context.c(), r0, new y(this));
        }
        try {
            this.a.registerReceiver(this.i, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Exception e) {
        }
    }

    public void b() {
        super.b();
        this.c.c();
        if (!this.h) {
            this.i.onReceive(this.a, f());
        }
    }

    public void c() {
        super.c();
        this.c.b();
    }

    public void h() {
        super.h();
        this.h = false;
    }

    public void a() {
        super.a();
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        this.c.a();
        try {
            this.a.unregisterReceiver(this.i);
        } catch (Exception e) {
        }
    }

    public void a(boolean z) {
        if (z) {
            String k = f.a(this.a).k();
            String l = f.a(this.a).l();
            this.a.showLoadingDialog("\u6b63\u5728\u5173\u95ed...", false);
            new XLLiveGetRoomInfoRequest(k, l, this.d).send(new z(this));
            return;
        }
        g();
    }

    protected void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("hostid", this.f);
            hashMap.put(SocialConstants.PARAM_URL, Uri.encode(this.e));
            hashMap.put("playid", q.e("live_room_show").d("playid"));
            hashMap.put("network", String.valueOf(ac.b()));
            hashMap.put("video_type", "replay");
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

    public void g() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        this.c.a();
        super.g();
    }

    public int i() {
        return this.c.f();
    }

    public int j() {
        return this.c.g();
    }

    public void a(int i) {
        this.c.a(i);
    }

    public void k() {
        this.c.b();
    }

    public void l() {
        this.c.c();
    }

    public boolean m() {
        return this.c.e();
    }

    @SuppressLint({"SimpleDateFormat"})
    private int a(String str, String str2) {
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
}
