package com.xunlei.tdlive;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.xunlei.tdlive.a.x;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.control.c.a;
import com.xunlei.tdlive.im.AllowChatMessage;
import com.xunlei.tdlive.im.BaseMessage;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.im.CloseRoomMessage;
import com.xunlei.tdlive.im.DeniedChatMessage;
import com.xunlei.tdlive.im.GiftMessage;
import com.xunlei.tdlive.im.IMClient;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.im.KickMessage;
import com.xunlei.tdlive.im.LikeMessage;
import com.xunlei.tdlive.im.MessageDispatcher;
import com.xunlei.tdlive.im.MessageDispatcher.ConnectCallback;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.OutRoomMessage;
import com.xunlei.tdlive.im.RoomUserListMessage;
import com.xunlei.tdlive.im.RoomUserNumMessage;
import com.xunlei.tdlive.im.SysNotifyMessage;
import com.xunlei.tdlive.im.VoiceCloseMessage;
import com.xunlei.tdlive.im.VoiceConnectMessage;
import com.xunlei.tdlive.im.VoiceCreplyMessage;
import com.xunlei.tdlive.play.a.c;
import com.xunlei.tdlive.play.view.ChatListView;
import com.xunlei.tdlive.play.view.ConnectMicView;
import com.xunlei.tdlive.play.view.FullScreenLayout;
import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.protocol.XLLiveGetGiftListRequest;
import com.xunlei.tdlive.protocol.XLLiveSetPublishStateRequest;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.view.AnimationSurfaceView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Date;

@SuppressLint({"ClickableViewAccessibility"})
// compiled from: LivePlayerDialog.java
public class au extends h implements OnClickListener, i$a {
    private View A;
    private View B;
    private View C;
    private TextView D;
    private int E;
    private boolean F;
    private final String G;
    private final String H;
    private int I;
    private String J;
    private boolean K;
    private boolean L;
    private boolean M;
    private String N;
    private boolean O;
    private int P;
    private int Q;
    private boolean R;
    private boolean S;
    private CloseRoomMessage T;
    private a U;
    private AnimationSurfaceView V;
    private fz W;
    private BroadcastReceiver X;
    private Handler Y;
    private Runnable Z;
    public NormalScreenLayout a;
    private boolean aa;
    private int ab;
    private b ac;
    private BroadcastReceiver ad;
    ConnectCallback b;
    OnMessageCallback<LikeMessage> c;
    OnMessageCallback<ChatMessage> d;
    OnMessageCallback<CloseRoomMessage> e;
    OnMessageCallback<InRoomMessage> f;
    OnMessageCallback<OutRoomMessage> g;
    OnMessageCallback<GiftMessage> h;
    OnMessageCallback<RoomUserListMessage> i;
    OnMessageCallback<RoomUserNumMessage> j;
    OnMessageCallback<DeniedChatMessage> k;
    OnMessageCallback<AllowChatMessage> l;
    OnMessageCallback<SysNotifyMessage> m;
    OnMessageCallback<VoiceConnectMessage> n;
    OnMessageCallback<VoiceCreplyMessage> o;
    OnMessageCallback<VoiceCloseMessage> p;
    private IMClient q;
    private MessageDispatcher r;
    private x s;
    private ChatListView t;
    private EditText u;
    private InputMethodManager v;
    private i w;
    private View x;
    private TextView y;
    private FullScreenLayout z;

    static /* synthetic */ int e(au auVar) {
        int i = auVar.E;
        auVar.E = i + 1;
        return i;
    }

    static /* synthetic */ int r(au auVar) {
        int i = auVar.Q + 1;
        auVar.Q = i;
        return i;
    }

    public au(Context context, boolean z, String str, String str2) {
        super(context, R.style.TransparentDialogStyle);
        this.F = false;
        this.I = 0;
        this.J = null;
        this.K = false;
        this.L = false;
        this.M = false;
        this.N = com.umeng.a.d;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.R = false;
        this.S = false;
        this.aa = false;
        this.ab = -1;
        this.ac = new av(this);
        this.ad = new bg(this);
        this.b = new bi(this);
        this.c = new bk(this);
        this.d = new bl(this);
        this.e = new bm(this);
        this.f = new bn(this);
        this.g = new bo(this);
        this.h = new bp(this);
        this.i = new bq(this);
        this.j = new br(this);
        this.k = new bt(this);
        this.l = new bu(this);
        this.m = new bv(this);
        this.n = new bw(this);
        this.o = new bx(this);
        this.p = new by(this);
        this.Y = new Handler();
        this.G = str;
        this.H = str2;
        this.F = z;
        this.I = e.a();
        this.J = new StringBuilder("\u70b9\u4eae\u4e86[HEART_").append(String.format("#%X", new Object[]{Integer.valueOf(this.I)})).append("]").toString();
        this.v = (InputMethodManager) context.getSystemService("input_method");
        MessageDispatcher messageDispatcher = new MessageDispatcher(this.b);
        this.r = messageDispatcher;
        this.q = IMClient.a(context, messageDispatcher);
        this.q.b();
        s();
    }

    public void a(a aVar) {
        this.U = aVar;
    }

    public void a(boolean z) {
        this.O = z;
    }

    public String a() {
        return this.N;
    }

    public boolean b() {
        return this.F && this.M;
    }

    public boolean c() {
        return !this.F && this.L;
    }

    public boolean d() {
        return this.x.getVisibility() == 0;
    }

    public CloseRoomMessage e() {
        return this.T;
    }

    public String f() {
        return this.H;
    }

    public long g() {
        return this.a != null ? this.a.getPresenter().c() : 0;
    }

    public int h() {
        return this.P;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_player);
        a((Dialog) this);
        this.A = findViewById(R.id.root);
        this.A.setOnClickListener(this);
        this.A.setOnTouchListener(new b(this));
        findViewById(R.id.close_btn).setOnClickListener(new bs(this));
        this.B = findViewById(R.id.movable_layout);
        this.D = (TextView) findViewById(R.id.count_down_text);
        this.C = findViewById(R.id.count_down_layout);
        this.y = (TextView) findViewById(R.id.network_tip);
        this.x = findViewById(R.id.network_tip_layout);
        this.x.setVisibility(!ac.a() ? 0 : XZBDevice.Wait);
        k();
        l();
        g(false);
        o();
        n();
        if (this.F) {
            t();
        }
    }

    private boolean b(boolean z) {
        XLog.d("LivePlayerDialog", new StringBuilder("checkIsConnectMic state:").append(this.a.mConnectMicView.getState()).toString());
        if (this.a.mConnectMicView.getState() != ConnectMicView.a.c) {
            return false;
        }
        c(z);
        return true;
    }

    private void c(boolean z) {
        int i = f.a().b(this.H) ? 1 : 0;
        if (z) {
            this.a.getPresenter().j().a(i, this.G, new cc(this));
        } else {
            this.a.getPresenter().j().c(i, this.G);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || (x() || keyEvent.getAction() != 0)) {
            getWindow().clearFlags(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            getWindow().setDimAmount(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.a.showChatInputBar(false);
            d(false);
            return true;
        }
        if (!b(true)) {
            onBackPressed();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void d(boolean z) {
        this.V.setChatState(z);
    }

    public void i() {
        if (this.F) {
            a(true, true);
        }
        if (this.w != null && this.w.isShowing()) {
            this.w.a();
        }
    }

    public void j() {
        if (this.F) {
            a(false, false);
        }
    }

    protected void onStart() {
        super.onStart();
        f.a().a(this.ac);
        y();
        try {
            IntentFilter intentFilter = new IntentFilter("com.xunlei.tdlive.ACTION_SHOW_GIFT_DIALOG");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            getContext().getApplicationContext().registerReceiver(this.ad, intentFilter);
        } catch (Exception e) {
        }
        if (!this.F && ac.j()) {
            this.q.a(InRoomMessage.build(f.a(getContext()).k(), this.G, ac.j() ? anet.channel.strategy.dispatch.a.ANDROID : "android_sdk", ac.g()));
        }
        this.a.getPresenter().k().a(this.F, this.G);
        this.a.getPresenter().k().a();
        if (!ac.j()) {
            XLLiveSDK.getInstance(getContext()).host().limitSpeed(getContext(), com.xunlei.tdlive.modal.e.t);
        }
        if (this.F) {
            this.Y.postDelayed(new cd(this), BuglyBroadcastRecevier.UPLOADLIMITED);
        }
    }

    protected void onStop() {
        super.onStop();
        f.a().b(this.ac);
        z();
        if (this.Z != null) {
            this.Y.removeCallbacks(this.Z);
        }
        try {
            getContext().getApplicationContext().unregisterReceiver(this.ad);
        } catch (Exception e) {
        }
        if (!this.F) {
            this.q.a(OutRoomMessage.build(f.a(getContext()).k(), this.G, ac.j() ? anet.channel.strategy.dispatch.a.ANDROID : "android_sdk", ac.g()));
        }
        if (!ac.j()) {
            this.q.a(KickMessage.build(f.a().k()));
            this.q.c();
        }
        this.q.a();
        this.r.a();
        this.s.b();
        this.a.getPresenter().k().b();
        try {
            q.e("user_like").a("likenum", this.P).a("votenum", this.Q).a("follow", q.e("live_room_show").d("is_follow")).a("liketime", q.e("user_like").b("time", 0) - q.e("live_room_show").b("time", 0)).a("time").b(new String[0]);
        } catch (Exception e2) {
        }
        if (!ac.j()) {
            XLLiveSDK.getInstance(getContext()).host().limitSpeed(getContext(), -1);
        }
    }

    public void onBackPressed() {
        XLog.d("LivePlayerDialog", XLog.getCallerStackTraceElement().toString());
        getOwnerActivity().finish();
    }

    public void onClick(View view) {
        boolean z = false;
        int id = view.getId();
        if (id == R.id.gif_btn) {
            a("room", -1, this.a.mPlayButtonLayout.showRedFlagForGiftBtn(false));
        } else if (id == R.id.chat_send) {
            r();
        } else if (id == R.id.chat_btn) {
            f.a().a(getOwnerActivity(), "speak", new ce(this));
            try {
                q.b e = q.e("live_room_show");
                q.e("speak_send").a("speak_entry").a("hostid", e.d("hostid")).a("viewernum", e.d("viewernum")).a("follow", e.d("follow")).a("hosttype", e.d("hosttype")).a("speaktime", SystemClock.elapsedRealtime() - e.b("time", 0)).b(new String[0]);
            } catch (Exception e2) {
            }
        } else if (id == R.id.full_screen_btn || id == R.id.publish_full_sreen_btn) {
            if (!this.K) {
                z = true;
            }
            e(z);
        } else if (id == R.id.laud_btn) {
            q();
        }
    }

    public void a(int i, GiftMessage giftMessage) {
        if (i == 0) {
            if (this.a.mGiftReminderView != null) {
                String str = com.umeng.a.d;
                String str2 = com.umeng.a.d;
                String str3 = com.umeng.a.d;
                try {
                    c.a f = this.a.getPresenter().f();
                    str = f.a.a;
                    str2 = f.a.b;
                    str3 = f.a.c;
                } catch (Exception e) {
                }
                String str4 = null;
                if (!(giftMessage.userInfo.level == null || giftMessage.userInfo.level.icon2 == null || giftMessage.userInfo.level.icon2.length() <= 0)) {
                    str4 = giftMessage.userInfo.level.getIcon2FullPath();
                }
                String a = c.a(giftMessage.giftInfo.path, giftMessage.giftid);
                com.xunlei.tdlive.modal.b.a aVar = new com.xunlei.tdlive.modal.b.a(giftMessage.userInfo.userid, giftMessage.userInfo.nickname, giftMessage.userInfo.avatar, str4, str2, str, str3, giftMessage.giftid, giftMessage.giftInfo.name, giftMessage.giftInfo.content, a, giftMessage.giftInfo.continue_num);
                if (!this.V.addSeniorGift(aVar)) {
                    this.a.mGiftReminderView.addReminding(aVar);
                }
                String[] b = b("onsendgift");
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.user.userid = giftMessage.userInfo.userid;
                chatMessage.user.nickname = giftMessage.userInfo.nickname;
                chatMessage.user.level = giftMessage.userInfo.level;
                chatMessage.content = (f.a().b(giftMessage.userInfo.userid) ? "\u6211" : com.umeng.a.d) + "\u9001\u51fa\u4e00\u4e2a[GIFTX_" + a + "]";
                chatMessage.flag = 2;
                chatMessage.color1 = b[0];
                chatMessage.color2 = b[1];
                this.s.a(chatMessage);
                f(false);
            }
            this.a.getPresenter().b((long) giftMessage.playerInfo.total_point);
        }
    }

    private static void a(Dialog dialog) {
        if (dialog != null) {
            LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.width = -1;
            attributes.height = -1;
            dialog.getWindow().setAttributes(attributes);
        }
    }

    private void a(boolean z, boolean z2) {
        if (z) {
            XLog.e("LivePlayerDialog", new StringBuilder("onResume roomid ").append(this.G).append(", StateRequest.STATE_RESUME").toString());
            new XLLiveSetPublishStateRequest(f.a(getContext()).k(), f.a(getContext()).l(), this.G, 1).send(new cf(this, z2));
            return;
        }
        XLog.e("LivePlayerDialog", new StringBuilder("onPause roomid ").append(this.G).append(", StateRequest.STATE_PAUSE").toString());
        new XLLiveSetPublishStateRequest(f.a(getContext()).k(), f.a(getContext()).l(), this.G, 3).send(null);
        this.Y.removeCallbacksAndMessages(null);
    }

    private void k() {
        this.z = (FullScreenLayout) findViewById(R.id.full_screen_layout);
        this.z.mFullScreenButton.setOnClickListener(new ci(this));
        this.z.findViewById(R.id.publish_full_sreen_btn).setVisibility(XZBDevice.Wait);
    }

    private void l() {
        this.a = (NormalScreenLayout) findViewById(R.id.normal_screen_layout);
        this.V = this.a.mAnimationView;
        this.a.setPresenter(new com.xunlei.tdlive.play.a.q(this.a, getOwnerActivity()));
        this.a.mSendButton.setOnClickListener(this);
        this.a.mPlayButtonLayout.chat_btn.setOnClickListener(this);
        this.a.mPlayButtonLayout.full_screen_btn.setOnClickListener(this);
        this.a.mPlayButtonLayout.gif_btn.setOnClickListener(this);
        this.a.mPlayButtonLayout.laud_btn.setOnClickListener(this);
        this.a.mPublishButtonLayout.publish_full_sreen_btn.setOnClickListener(this);
        this.a.mPlayButtonLayout.setLaudBitmap(e.a(getContext(), this.I));
        this.a.mConnectMicView.setTag(this.F ? com.taobao.accs.internal.b.ELECTION_KEY_HOST : "user");
        this.a.getPresenter().a(new cj(this));
        this.a.getPresenter().a(f.a(getContext()).k(), f.a(getContext()).l(), this.G, this.F, this.I);
        this.a.getPresenter().k().a(new c(this, null));
        m();
    }

    private void m() {
        String k = f.a().k();
        String l = f.a().l();
        SharedPreferences sharedPreferences = XLLiveSDK.getInstance(getContext()).getSharedPreferences();
        int date = new Date().getDate();
        int i = sharedPreferences.getInt(k + "_red_flag_new", 0);
        if (i == 0) {
            this.Y.postDelayed(new aw(this), 1500);
        } else if (i != date) {
            new XLLiveGetGiftListRequest(k, l).send(new ax(this, sharedPreferences));
            this.Z = new ay(this);
            this.Y.postDelayed(this.Z, 90000);
        }
    }

    private void a(String str) {
        if (w()) {
            XLog.d("LivePlayerDialog", "dialog is show, show tip 5s later");
            this.Y.postDelayed(this.Z, 5000);
            return;
        }
        this.Z = null;
        SharedPreferences sharedPreferences = XLLiveSDK.getInstance(getContext()).getSharedPreferences();
        String str2 = f.a().k() + "_red_flag_new";
        int i = sharedPreferences.getInt(str2, 0);
        int date = new Date().getDate();
        if (i != date) {
            this.a.mPlayButtonLayout.showRedFlagForGiftBtn(true);
            this.a.mPlayButtonLayout.showTipOnGiftBtn(str);
            q.e("gift_action").a("gift_action_red_show").b(new String[0]);
            sharedPreferences.edit().putInt(str2, date).apply();
        }
    }

    private void n() {
        this.t = this.a.mMessagesView;
        this.t.setVerticalScrollBarEnabled(false);
        this.t.setExtraTouchEventHandler(new a(this, null));
        this.s = new x(getContext(), 50, this.t);
        this.s.a();
        this.t.setAdapter(this.s);
        this.t.setOnItemClickListener(new az(this));
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ba(this));
    }

    private void o() {
        this.u = this.a.mInputMessageView;
        this.u.setOnEditorActionListener(new bd(this));
        this.u.addTextChangedListener(new be(this));
    }

    private String p() {
        String trim = this.u.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.u.requestFocus();
            return null;
        }
        boolean startsWith = trim.startsWith("debug:");
        if (startsWith) {
            v();
            if (this.W == null) {
                this.W = new fz(getContext(), this.G);
                this.W.setOwnerActivity(getOwnerActivity());
            }
            if (!this.W.isShowing()) {
                this.W.show();
            }
        } else {
            startsWith = trim.startsWith("log:on");
            if (startsWith) {
                XLog.enableLog(true);
            } else {
                startsWith = trim.startsWith("log:off");
                if (startsWith) {
                    XLog.enableLog(false);
                }
            }
        }
        this.u.setText(com.umeng.a.d);
        return startsWith ? null : trim;
    }

    private void q() {
        String k = f.a(getContext()).k();
        if (!this.R) {
            this.R = true;
            this.q.a(ChatMessage.build(k, this.G, 1, this.J));
        }
        this.q.a(LikeMessage.build(k, this.G, String.format("#%X", new Object[]{Integer.valueOf(this.I)})));
        this.V.addFloatUnit(this.I);
        this.P++;
        if (q.e("user_like").d("time").length() <= 0) {
            q.e("user_like").a("time", SystemClock.elapsedRealtime());
        }
    }

    private void r() {
        String p = p();
        if (p != null) {
            if (this.a.getPresenter().h()) {
                n.a(getOwnerActivity(), "\u60a8\u5f53\u524d\u65e0\u6cd5\u53d1\u8a00");
                return;
            }
            this.q.a(ChatMessage.build(f.a(getContext()).k(), this.G, 0, p));
            try {
                q.e("speak_send").a("speak_send").a("speaktime", SystemClock.elapsedRealtime() - q.e("live_room_show").b("time", 0)).b(new String[0]);
            } catch (Exception e) {
            }
        }
    }

    private void e(boolean z) {
        if ((this.K ^ z) != 0) {
            Point a = d.a(getContext());
            float translationX = this.B.getTranslationX();
            this.B.setTranslationX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            TimeInterpolator overshootInterpolator = new OvershootInterpolator();
            if (this.a.getAlpha() == 0.0f) {
                this.a.setAlpha(1.0f);
            }
            if (!this.z.isShown()) {
                this.z.setVisibility(0);
            }
            ObjectAnimator ofFloat;
            ObjectAnimator ofFloat2;
            if (z) {
                ofFloat = ObjectAnimator.ofFloat(this.a, "translationX", new float[]{translationX, (float) a.x});
                ofFloat.addListener(new bf(this));
                ofFloat.setInterpolator(overshootInterpolator);
                ofFloat.setDuration(500).start();
                ofFloat2 = ObjectAnimator.ofFloat(this.z, "translationX", new float[]{(float) (-a.x), 0.0f});
                ofFloat2.setInterpolator(overshootInterpolator);
                ofFloat2.setDuration(500).start();
            } else {
                ofFloat = ObjectAnimator.ofFloat(this.z, "translationX", new float[]{translationX, (float) (-a.x)});
                ofFloat.addListener(new bh(this));
                ofFloat.setInterpolator(overshootInterpolator);
                ofFloat.setDuration(500).start();
                ofFloat2 = ObjectAnimator.ofFloat(this.a, "translationX", new float[]{(float) a.x, 0.0f});
                ofFloat2.setInterpolator(overshootInterpolator);
                ofFloat2.setDuration(500).start();
            }
            this.S = true;
        }
    }

    private void s() {
        this.r.a(this.c);
        this.r.a(this.d);
        this.r.a(this.e);
        this.r.a(this.f);
        this.r.a(this.h);
        this.r.a(this.i);
        this.r.a(this.j);
        this.r.a(this.k);
        this.r.a(this.l);
        this.r.a(this.m);
        this.r.a(this.n);
        this.r.a(this.o);
        this.r.a(this.p);
    }

    private void f(boolean z) {
        this.s.a(z);
    }

    private String[] b(String str) {
        String[] split;
        String str2 = (String) com.xunlei.tdlive.modal.e.o.get(str);
        if (str2 != null) {
            split = str2.split("=");
        } else {
            split = null;
        }
        if (split != null) {
            return split;
        }
        return new String[]{null, null};
    }

    private <T extends BaseMessage> void a(T t) {
        int i;
        Exception e;
        int i2;
        Object obj = null;
        try {
            Object obj2;
            if (t instanceof GiftMessage) {
                GiftMessage giftMessage = (GiftMessage) t;
                if (f.a().b(giftMessage.userInfo.userid)) {
                    i = giftMessage.userInfo.level.current;
                    try {
                        obj2 = giftMessage.userInfo.nickname;
                    } catch (Exception e2) {
                        e = e2;
                        obj2 = null;
                        e.printStackTrace();
                        if (i == -1) {
                        }
                    }
                    try {
                        obj = giftMessage.userInfo.level.getIconFullPath();
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        if (i == -1) {
                        }
                    }
                    if (i == -1 && this.ab >= 0 && i > this.ab && !TextUtils.isEmpty(obj2) && !TextUtils.isEmpty(obj)) {
                        XLog.d("LivePlayerDialog", new StringBuilder("level upgrade from ").append(this.ab).append(" to ").append(i).toString());
                        this.ab = i;
                        try {
                            this.a.getPresenter().a(obj2, this.a.getPresenter().f().a.a, i, obj);
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    return;
                }
                return;
            }
            String str;
            String str2;
            if (t instanceof ChatMessage) {
                ChatMessage chatMessage = (ChatMessage) t;
                if (f.a().b(chatMessage.user.userid)) {
                    i2 = chatMessage.user.level.current;
                    try {
                        str = chatMessage.user.nickname;
                    } catch (Exception e5) {
                        e = e5;
                        i = i2;
                        obj2 = null;
                        e.printStackTrace();
                        if (i == -1) {
                        }
                    }
                    try {
                        obj = chatMessage.user.level.getIconFullPath();
                    } catch (Exception e6) {
                        Exception exception = e6;
                        i = i2;
                        str2 = str;
                        e = exception;
                        e.printStackTrace();
                        if (i == -1) {
                        }
                    }
                }
                return;
            }
            str = null;
            i2 = -1;
            i = i2;
            str2 = str;
            if (i == -1) {
            }
        } catch (Exception e7) {
            e = e7;
            obj2 = null;
            i = -1;
            e.printStackTrace();
            if (i == -1) {
            }
        }
    }

    private void t() {
        new com.xunlei.tdlive.control.c().a(this.D, this.C, this.U);
    }

    private void g(boolean z) {
        if (z) {
            this.a.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.z.setVisibility(0);
        } else {
            this.a.setAlpha(1.0f);
            this.z.setVisibility(XZBDevice.Wait);
        }
        this.K = z;
    }

    private boolean u() {
        if (!x()) {
            return false;
        }
        getWindow().clearFlags(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        getWindow().setDimAmount(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.v.hideSoftInputFromWindow(this.u.getWindowToken(), 0);
        this.a.showChatInputBar(false);
        d(false);
        return true;
    }

    private void v() {
        if (!u() && !this.F) {
            q();
        }
    }

    private void a(String str, int i, boolean z) {
        if (!this.F) {
            if (this.w == null || !this.w.isShowing()) {
                this.w = new i(getContext(), this.G, this.H, i, str, z, this);
                this.w.setOwnerActivity(getOwnerActivity());
                this.w.setOnDismissListener(new bz(this));
                this.w.show();
                this.a.mPlayButtonLayout.showAnimation(false);
                u();
            }
        }
    }

    private boolean w() {
        return (this.w != null && this.w.isShowing()) || this.aa;
    }

    private boolean x() {
        return this.a.mChatEditLayout.getVisibility() != 8;
    }

    private void a(ah.a aVar) {
        boolean z = true;
        try {
            if (this.F) {
                aVar.l = true;
            } else {
                if (f.a().b(aVar.f)) {
                    z = false;
                }
                aVar.m = z;
            }
            aVar.q = true;
            this.aa = true;
            ah ahVar = new ah(getOwnerActivity());
            com.xunlei.tdlive.play.view.b.a aVar2 = new com.xunlei.tdlive.play.view.b.a();
            aVar2.a(getWindow());
            aVar2.a(this.A);
            aVar2.a(aVar);
            ahVar.a(aVar2);
            ahVar.b();
            ahVar.a(new ca(this));
        } catch (Throwable th) {
        }
    }

    private void y() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xunlei.tdlive.GIFT_CLICKED");
        if (this.X == null) {
            this.X = new cb(this);
        }
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.X, intentFilter);
    }

    private void z() {
        if (this.X != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.X);
            this.X = null;
        }
    }
}
