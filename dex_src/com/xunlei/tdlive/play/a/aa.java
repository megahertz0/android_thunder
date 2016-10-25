package com.xunlei.tdlive.play.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.OvershootInterpolator;
import android.widget.SeekBar;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.x;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.im.IMClient;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.im.KickMessage;
import com.xunlei.tdlive.im.MessageDispatcher;
import com.xunlei.tdlive.im.MessageDispatcher.ConnectCallback;
import com.xunlei.tdlive.im.OutRoomMessage;
import com.xunlei.tdlive.play.a.ar.a;
import com.xunlei.tdlive.play.view.ChatListView;
import com.xunlei.tdlive.play.view.FullScreenLayout;
import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.play.view.o;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.view.AnimationSurfaceView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ReplayDialogPresenter.java
public class aa extends b implements OnClickListener {
    ConnectCallback a;
    private o b;
    private c c;
    private String d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private x i;
    private IMClient j;
    private MessageDispatcher k;
    private AnimationSurfaceView l;
    private ar m;
    private a n;
    private b o;

    // compiled from: ReplayDialogPresenter.java
    public static interface b {
        void a(boolean z);
    }

    public aa(o oVar, Context context, String str, b bVar, a aVar) {
        super(oVar);
        this.e = 0;
        this.f = false;
        this.g = false;
        this.h = false;
        this.a = new al(this);
        this.b = oVar;
        this.d = str;
        this.e = e.a();
        this.n = aVar;
        this.o = bVar;
        this.c = new c(this, f.a(e()).k(), f.a(e()).l(), this.d);
        MessageDispatcher messageDispatcher = new MessageDispatcher(this.a);
        this.k = messageDispatcher;
        this.j = IMClient.a(context, messageDispatcher);
        this.j.b();
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        this.b.f().setOnClickListener(this);
        this.b.f().setOnTouchListener(new e(this));
        this.b.g().setOnClickListener(new ab(this));
        o();
        n();
        b(false);
        m();
        h();
        p();
    }

    private void h() {
        this.m = new ar();
        this.m.a(this.n);
        this.m.a(new f(this, null));
        SeekBar seekBar = l().mRePlayButtonLayout.seekBar;
        seekBar.setMax(this.m.a());
        seekBar.setProgress(this.m.b());
        seekBar.setOnSeekBarChangeListener(new d(this, null));
    }

    public void d() {
        super.d();
        this.j.a(InRoomMessage.build(f.a(e()).k(), this.d, ac.j() ? anet.channel.strategy.dispatch.a.ANDROID : "android_sdk", ac.g()));
        this.m.c();
    }

    public void c() {
        super.c();
        this.j.a(OutRoomMessage.build(f.a(e()).k(), this.d, ac.j() ? anet.channel.strategy.dispatch.a.ANDROID : "android_sdk", ac.g()));
        if (!ac.j()) {
            this.j.a(KickMessage.build(f.a().k()));
            this.j.c();
        }
        this.j.a();
        this.k.a();
        this.c.a();
        this.m.d();
    }

    private void a(boolean z) {
        if ((this.f ^ z) != 0) {
            Context e = e();
            View j = j();
            Point a = d.a(e);
            float translationX = j.getTranslationX();
            j.setTranslationX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            TimeInterpolator overshootInterpolator = new OvershootInterpolator();
            if (l().getAlpha() == 0.0f) {
                l().setAlpha(1.0f);
            }
            if (!k().isShown()) {
                k().setVisibility(0);
            }
            ObjectAnimator ofFloat;
            ObjectAnimator ofFloat2;
            if (z) {
                ofFloat = ObjectAnimator.ofFloat(l(), "translationX", new float[]{translationX, (float) a.x});
                ofFloat.addListener(new ac(this));
                ofFloat.setInterpolator(overshootInterpolator);
                ofFloat.setDuration(500).start();
                ofFloat2 = ObjectAnimator.ofFloat(k(), "translationX", new float[]{(float) (-a.x), 0.0f});
                ofFloat2.setInterpolator(overshootInterpolator);
                ofFloat2.setDuration(500).start();
            } else {
                ofFloat = ObjectAnimator.ofFloat(k(), "translationX", new float[]{translationX, (float) (-a.x)});
                ofFloat.addListener(new ad(this));
                ofFloat.setInterpolator(overshootInterpolator);
                ofFloat.setDuration(500).start();
                ofFloat2 = ObjectAnimator.ofFloat(l(), "translationX", new float[]{(float) a.x, 0.0f});
                ofFloat2.setInterpolator(overshootInterpolator);
                ofFloat2.setDuration(500).start();
            }
            this.g = true;
        }
    }

    private void b(boolean z) {
        if (z) {
            l().setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            k().setVisibility(0);
        } else {
            l().setAlpha(1.0f);
            k().setVisibility(XZBDevice.Wait);
        }
        this.f = z;
    }

    private void i() {
    }

    private View j() {
        return this.b.d();
    }

    private FullScreenLayout k() {
        return this.b.e();
    }

    private NormalScreenLayout l() {
        return this.b.h();
    }

    private void m() {
        ChatListView m = this.b.m();
        m.setVerticalScrollBarEnabled(false);
        m.setExtraTouchEventHandler(new a(this, null));
        this.i = new x(e(), 50, m);
        this.i.a();
        m.setAdapter(this.i);
        m.setOnItemClickListener(new ae(this));
        l().postDelayed(new af(this, m), 100);
    }

    private void c(boolean z) {
        this.i.a(z);
    }

    private void n() {
        l().setPresenter(new ap(l(), this.b.getOwnerActivity()));
        l().getPresenter().a(new aj(this));
        l().getPresenter().a(f.a(e()).k(), f.a(e()).l(), this.d, false, this.e);
        l().mRePlayButtonLayout.full_screen_btn.setOnClickListener(this);
        l().mRePlayButtonLayout.start_stop_btn.setOnClickListener(this);
    }

    private void o() {
        k().mFullScreenButton.setOnClickListener(new ak(this));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.replay_full_screen_btn || id == R.id.publish_full_sreen_btn) {
            boolean z;
            if (this.f) {
                z = false;
            } else {
                z = true;
            }
            a(z);
        } else if (id == R.id.replay_start_stop_btn && this.n != null) {
            if (this.h) {
                this.n.l();
            } else {
                this.n.k();
            }
        }
    }

    private void a(String str, String str2, String str3, LevelInfo levelInfo, int i, String str4, String str5, String str6, int i2) {
        String str7 = com.umeng.a.d;
        if (levelInfo != null) {
            String iconFullPath = (levelInfo == null || TextUtils.isEmpty(levelInfo.icon2)) ? levelInfo.getIconFullPath() : levelInfo.getIcon2FullPath();
            str7 = iconFullPath;
        }
        String str8 = com.umeng.a.d;
        String str9 = com.umeng.a.d;
        String str10 = com.umeng.a.d;
        try {
            c.a f = l().getPresenter().f();
            str8 = f.a.a;
            str9 = f.a.b;
            str10 = f.a.c;
        } catch (Exception e) {
        }
        com.xunlei.tdlive.modal.b.a aVar = new com.xunlei.tdlive.modal.b.a(str, str2, str3, str7, str9, str8, str10, i, str4, str5, str6, i2);
        if (!this.l.addSeniorGift(aVar)) {
            this.b.k().addReminding(aVar);
        }
        String toString = new StringBuilder("[GIFTX_").append(str6).append("]").toString();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.user.userid = str;
        chatMessage.user.nickname = str2;
        chatMessage.user.level = levelInfo;
        chatMessage.content = (f.a().k().equals(str) ? "\u6211" : com.umeng.a.d) + "\u9001\u51fa\u4e00\u4e2a" + toString;
        chatMessage.flag = 2;
        this.i.a(chatMessage);
        c(true);
    }

    private void p() {
        this.l = this.b.l();
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || (this.b.c() || keyEvent.getAction() != 0)) {
            g().clearFlags(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            g().setDimAmount(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            l().showChatInputBar(false);
            return true;
        }
        this.o.a(false);
        return super.a(keyEvent);
    }

    private void a(ah.a aVar) {
        ah ahVar = new ah(f());
        com.xunlei.tdlive.play.view.b.a aVar2 = new com.xunlei.tdlive.play.view.b.a();
        aVar2.a(g());
        aVar2.a(this.b.f());
        aVar.m = !f.a().k().equals(aVar.f);
        aVar.q = false;
        aVar2.a(aVar);
        ahVar.a(aVar2);
        ahVar.b();
    }
}
