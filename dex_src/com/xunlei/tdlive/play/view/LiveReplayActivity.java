package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sina.weibo.sdk.constant.WBConstants;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.UMShareAPI;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.play.a.a;
import com.xunlei.tdlive.play.a.v;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class LiveReplayActivity extends BaseMvpActivity {
    private v a;
    private FrameLayout b;
    private ImageView c;
    private FrameLayout d;

    public LiveReplayActivity() {
        this.a = new v(this);
    }

    protected void a(Bundle bundle) {
        setContentView(R.layout.xllive_activity_player);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.b = (FrameLayout) findViewById(R.id.playView);
        this.c = (ImageView) findViewById(R.id.thumb);
        this.d = (FrameLayout) findViewById(R.id.play_loading);
        this.d.setVisibility(0);
        View findViewById = this.d.findViewById(R.id.play_loading_ani);
        findViewById.setAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.2f));
        findViewById.getAnimation().setRepeatMode(1);
        findViewById.getAnimation().setRepeatCount(-1);
        findViewById.getAnimation().setDuration(800);
        findViewById.getAnimation().start();
        setTimer(IHost.HOST_NOFITY_PAGE_SELECTED, Constants.ST_UPLOAD_MAX_COUNT);
    }

    protected void onTimer(int i) {
        if (i == 1001 && this.a.b != null) {
            this.d.setVisibility(this.a.b.b() ? XZBDevice.DOWNLOAD_LIST_ALL : 0);
        }
    }

    protected a a() {
        return this.a;
    }

    public FrameLayout b() {
        return this.b;
    }

    public ImageView c() {
        return this.c;
    }

    public void d() {
        killTimer(IHost.HOST_NOFITY_PAGE_SELECTED);
        this.d.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void finish() {
        killTimer(IHost.HOST_NOFITY_PAGE_SELECTED);
        d();
        super.finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            UMShareAPI.get(this).onActivityResult(i, i2, intent);
        } catch (Exception e) {
        }
    }

    public static void a(Context context, Intent intent) {
        context.startActivity(new Intent(intent).setFlags(268435456).setClass(context, LiveReplayActivity.class));
        q.e("live_room_show").c("playid").a("hostid", intent.getStringExtra("userid")).a("viewernum", intent.getStringExtra("onlinenum")).a("follow", intent.getIntExtra("follow", 0)).a(intent.getStringExtra("from")).b("replay").a("time").b(new String[0]).a("time", SystemClock.elapsedRealtime());
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, String str7) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            Intent intent = new Intent(context, LiveReplayActivity.class);
            intent.putExtra("userid", str2);
            intent.putExtra("roomid", str);
            intent.putExtra("stream_pull", str3);
            intent.putExtra("avatar", str4);
            intent.putExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, str5);
            intent.putExtra("onlinenum", str6);
            intent.putExtra("hottype", i);
            intent.putExtra("follow", i2);
            intent.putExtra("from", str7);
            a(context, intent);
        }
    }
}
