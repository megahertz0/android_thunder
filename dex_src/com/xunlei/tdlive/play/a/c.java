package com.xunlei.tdlive.play.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.ae;
import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.control.ToggleButton;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.play.view.NormalScreenLayout;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.protocol.XLLiveGetFollowRequest;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.RoomUser;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: BaseNormalScreenLayoutPresenter.java
public abstract class c implements com.xunlei.tdlive.play.view.a.a, b {
    private static String u;
    protected NormalScreenLayout a;
    protected com.xunlei.tdlive.play.a.q.a b;
    protected String c;
    protected a d;
    protected List<d> e;
    protected ae f;
    protected long g;
    protected e h;
    protected Activity i;
    protected boolean j;
    protected boolean k;
    protected av l;
    protected at m;
    protected int n;
    protected String o;
    protected boolean p;
    protected int q;
    protected Handler r;
    protected final OnClickListener s;
    protected final OnClickListener t;
    private Runnable v;
    private Runnable w;

    // compiled from: BaseNormalScreenLayoutPresenter.java
    public static class a {
        public a a;
        public List<RoomUser> b;
        public int c;
        public long d;
        public long e;
        public String f;
        public String g;

        // compiled from: BaseNormalScreenLayoutPresenter.java
        public static class a {
            public String a;
            public String b;
            public String c;
            public String d;
            public LevelInfo e;
        }

        public a() {
            this.a = new a();
            this.b = new ArrayList();
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = com.umeng.a.d;
            this.g = com.umeng.a.d;
        }
    }

    protected abstract void a(View view);

    abstract boolean e();

    static {
        u = c.class.getSimpleName();
    }

    public c(NormalScreenLayout normalScreenLayout, Activity activity) {
        this.c = com.umeng.a.d;
        this.e = new ArrayList();
        this.g = 0;
        this.j = false;
        this.k = false;
        this.n = 0;
        this.o = com.umeng.a.d;
        this.p = false;
        this.q = 0;
        this.s = new l(this);
        this.t = new m(this);
        if (normalScreenLayout == null) {
            throw new IllegalArgumentException("param error!");
        }
        this.a = normalScreenLayout;
        if (activity == null) {
            throw new IllegalArgumentException("param error!");
        }
        this.i = activity;
    }

    public void a() {
        this.r = new Handler();
        this.h = new e();
        this.a.mPopularityArea.setOnClickListener(new d(this, this));
        this.f = new ae(d());
        this.a.mVisitorList.setAdapter(this.f);
        this.f.a(new f(this));
        this.a.mHeadArea.setOnClickListener(new g(this));
        this.a.mAttendButton.setOnClickListener(new i(this));
        if (!f.a().b()) {
            f.a().a((b) this);
        }
        this.a.mPublishButtonLayout.publish_share_btn.setOnClickListener(this.s);
        this.a.mPublishButtonLayout.publish_full_sreen_btn.setOnClickListener(this.t);
        this.a.mPlayButtonLayout.laud_btn.setOnClickListener(this.t);
        this.v = new k(this);
        this.r.postDelayed(this.v, BuglyBroadcastRecevier.UPLOADLIMITED);
    }

    public void b() {
        f.a().b((b) this);
        if (this.v != null) {
            this.r.removeCallbacks(this.v);
            this.v = null;
        }
        if (this.w != null) {
            this.r.removeCallbacks(this.w);
            this.w = null;
        }
    }

    public void a(String str, String str2, String str3, boolean z, int i) {
        this.c = str3;
        a(0);
        this.a.setPopularityCount(0);
    }

    public void a(String str) {
        for (int i = 0; i < this.e.size(); i++) {
            if (((d) this.e.get(i)).a.equals(str)) {
                this.e.remove(i);
                this.f.a(this.e);
                a(this.g - 1);
                return;
            }
        }
    }

    public void a(List<d> list, long j) {
        this.e = list;
        this.f.a(list, j);
    }

    public long c() {
        return this.g;
    }

    public void a(long j) {
        XLog.i(u, new StringBuilder("setOnlineCount: ").append(j).toString());
        this.g = j;
        this.a.setOnLineCount(j);
    }

    public void a(InRoomMessage inRoomMessage) {
        this.a.mInRoombar.addMessage(inRoomMessage);
    }

    public void a(boolean z) {
        if (z) {
            i();
        }
    }

    public void b(long j) {
        if (this.d != null) {
            this.d.d = j;
        }
        this.a.setPopularityCount(j);
    }

    public void a(com.xunlei.tdlive.play.a.q.a aVar) {
        this.b = aVar;
    }

    public void b(boolean z) {
    }

    public Context d() {
        return this.a.getContext();
    }

    public a f() {
        return this.d;
    }

    protected void g() {
        String str = this.d.a.a;
        long j = this.d.e;
        long j2 = this.d.d;
        a(this.a.mHeadImage, this.d.a.c, R.drawable.xllive_avatar_default);
        l();
        this.a.setHostName(str);
        a(j);
        this.a.setPopularityCount(j2);
    }

    private void a(ImageView imageView, String str, int i) {
        com.xunlei.tdlive.util.a.a(d()).a((View) imageView, str, com.xunlei.tdlive.util.a.a(d(), i));
    }

    private void l() {
        LevelInfo levelInfo = this.d.a.e;
        if (levelInfo != null) {
            ImageView imageView = this.a.mGradeImage;
            if (!TextUtils.isEmpty(levelInfo.icon2)) {
                a(imageView, levelInfo.getIcon2FullPath(), R.drawable.xllive_user_grade_zero);
            } else if (!TextUtils.isEmpty(levelInfo.icon)) {
                a(imageView, levelInfo.getIconFullPath(), R.drawable.xllive_user_grade_zero);
            } else {
                return;
            }
            imageView.setVisibility(0);
        }
    }

    public boolean h() {
        return this.j;
    }

    public void c(boolean z) {
        this.j = z;
    }

    private void d(boolean z) {
        if (this.k || !z) {
            this.a.setAttendSelect(z);
        } else {
            n.a(d(), "\u221a\u8c22\u8c22\u5173\u6ce8\u5566");
            this.a.fadedByAnimation();
        }
        this.k = z;
    }

    protected void i() {
        if (this.d != null) {
            Object obj = this.d.a.b;
            if (!TextUtils.isEmpty(obj)) {
                if (f.a().b()) {
                    new XLLiveGetFollowRequest(f.a().k(), f.a().l(), obj, this.c).send(new n(this));
                } else {
                    this.a.setAttendSelect(false);
                }
            }
        }
    }

    private void m() {
        try {
            ToggleButton toggleButton = this.a.mAttendButton;
            View view = (ImageView) LayoutInflater.from(d()).inflate(R.layout.xllive_attention_popup_bkg, null, false);
            PopupWindow popupWindow = new PopupWindow(view, -2, -2);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.showAsDropDown(toggleButton, (int) com.xunlei.tdlive.util.d.a(view.getContext(), TitleBar.BACKBTN_LEFT_MARGIN), XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.02f);
            translateAnimation.setRepeatMode(1);
            translateAnimation.setRepeatCount(R.styleable.Toolbar_navigationIcon);
            translateAnimation.setDuration(300);
            view.startAnimation(translateAnimation);
            try {
                q.e("attention_pop_show").a("hostid", f().a.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.w = new o(this, popupWindow);
            this.r.postDelayed(this.w, 6000);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, boolean z) {
        new XLLiveFollowRequest(f.a().k(), f.a().l(), str, z).send(new e(this, z));
        q.e("user_attention").a("roomhost").b(z ? "attention" : "noattention").a("userid", str).b(new String[0]);
    }
}
