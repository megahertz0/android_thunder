package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.uc.addon.sdk.remote.Tabs;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.a.c;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.l;
import com.xunlei.tdlive.control.FragmentTabHost;
import com.xunlei.tdlive.control.FragmentTabHost.ReclickLinearLayout;
import com.xunlei.tdlive.im.IMClient;
import com.xunlei.tdlive.im.KickMessage;
import com.xunlei.tdlive.im.MessageDispatcher;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveGetOnlineConfigRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.q;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends BaseActivity implements OnClickListener, h, JsonCallBack {
    private boolean a;
    private int b;
    private boolean c;
    private boolean d;
    private int e;
    private FrameLayout f;
    private FragmentTabHost g;
    private MessageDispatcher h;
    private IMClient i;
    private l j;

    public MainActivity() {
        super(true, true);
        this.a = false;
        this.b = -1;
        this.c = true;
        this.d = true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_main);
        XLog.d("MainActivity", "onCreate()");
        if (getIntent() != null) {
            this.e = getIntent().getIntExtra("MainActivity.EXTRA_TRACE_FLAG", 0);
        }
        this.f = (FrameLayout) findViewById(R.id.bottomBar);
        FrameLayout frameLayout = this.f;
        View fragmentTabHost = new FragmentTabHost(this);
        this.g = fragmentTabHost;
        frameLayout.addView(fragmentTabHost, -1, -1);
        this.g.setup(this, getSupportFragmentManager(), R.id.tabcontainer);
        this.g.addTab(this.g.newTabSpec("main").setIndicator(a(16908313, R.drawable.xllive_tab_home_selector, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)), q.class, bundle);
        this.g.addTab(this.g.newTabSpec(a.d).setIndicator(a(0, 0, XZBDevice.DOWNLOAD_LIST_RECYCLE)), null, bundle);
        this.g.addTab(this.g.newTabSpec("usercenter").setIndicator(a(16908314, R.drawable.xllive_tab_user_selector, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)), q.class, bundle);
        this.g.getTabWidget().setLayoutParams(new LayoutParams(-1, -1));
        MessageDispatcher messageDispatcher = new MessageDispatcher(new a(this));
        this.h = messageDispatcher;
        this.i = IMClient.a(this, messageDispatcher);
        new XLLiveGetOnlineConfigRequest().send(this);
        findViewById(R.id.publisher).setOnClickListener(this);
        c.a(this);
    }

    protected void onResume() {
        super.onResume();
        XLog.d("MainActivity", "onResume()");
        if (this.d) {
            this.d = false;
            post(new dn(this), XZBDevice.Stop);
            return;
        }
        if (this.b != -1) {
            this.g.setCurrentTab(this.b);
            this.b = -1;
        }
        if (e.d) {
            e.d = false;
            XLUserUtil.getInstance().userLogout(null, null);
            com.xunlei.tdlive.base.c cVar = new com.xunlei.tdlive.base.c(this, "\u63d0\u793a", "\u60a8\u7684\u8d26\u53f7\u5b58\u5728\u8fdd\u89c4\u64cd\u4f5c\uff0c\u5df2\u88ab\u5c01\u7981\uff0c\u82e5\u6709\u76f8\u5173\u7591\u95ee\u8bf7\u62e8\u6253[400-1111-000]\u7533\u8bc9", "\u786e\u5b9a", new String[0]);
            cVar.setCanceledOnTouchOutside(false);
            cVar.b(new do_(this));
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String stringExtra = intent.getStringExtra("MainActivity.EXTRA_PAGE_TAG");
        if ("main".equals(stringExtra)) {
            this.g.setCurrentTab(0);
        } else if ("usercenter".equals(stringExtra)) {
            this.g.setCurrentTab(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            a(true, true);
        }
    }

    protected void onStop() {
        super.onStop();
        this.c = false;
        XLog.d("MainActivity", "onStop()");
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        XLog.d("MainActivity", "onSaveInstanceState()");
    }

    protected void onDestroy() {
        super.onDestroy();
        XLog.d("MainActivity", "onDestroy()");
        if (this.a) {
            this.i.a(KickMessage.build(f.a((Context) this).k()));
            this.i.c();
        }
        this.i.a();
        this.h.a();
        c();
        a.a();
    }

    private void c() {
        com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
        aVar.a("exittime", SystemClock.elapsedRealtime() - gb.a());
        com.xunlei.tdlive.util.q.a("app_exit", null, null, aVar.a());
        gb.b();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            this.e = 4;
            this.b = 0;
            if (!d()) {
                b();
            }
        } else if (i == 103) {
            b();
        } else if (i == 101) {
            if (i2 != -1) {
                finish();
            } else {
                a(false);
            }
        } else if (i == 102) {
            this.e = 3;
        }
    }

    protected void onTimer(int i) {
        killTimer(i);
        this.a = false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.a) {
                hideToast();
            } else {
                this.a = true;
                setTimer(R.styleable.AppCompatTheme_buttonStyle, 1500);
                showToast(R.string.toast_key_back_quit, 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.publisher) {
            LivePlayerActivity.a(this, null, R.styleable.AppCompatTheme_checkboxStyle, e.j);
            Map hashMap = new HashMap(1);
            hashMap.put("network", String.valueOf(ac.b()));
            com.xunlei.tdlive.util.q.a("live_button", null, null, hashMap);
            com.xunlei.tdlive.util.q.a("live_prepare", "tabbutton", null, hashMap);
        }
    }

    public void b() {
        if (!f.a().b()) {
            a();
        }
    }

    private boolean d() {
        return false;
    }

    public void a() {
        XLog.d("MainActivity", "show login acitivity");
        com.xunlei.tdlive.util.q.a("login_page_show", this.c ? "first_start" : null, null, null);
        Intent intent = new Intent(this, LoginGuideActivity.class);
        intent.putExtra("LoginGuideActivity.first_start", this.c);
        startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public void a(boolean z, boolean z2) {
        View findViewById = findViewById(R.id.container_bar);
        if (findViewById != null) {
            if (z) {
                a(findViewById);
                findViewById.setVisibility(0);
                return;
            }
            findViewById.setVisibility(XZBDevice.Wait);
        }
    }

    private void a(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.bottomMargin != 0) {
            marginLayoutParams.bottomMargin = 0;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public void a(MotionEvent motionEvent) {
        if (this.j == null) {
            this.j = new l();
        }
        View findViewById = findViewById(R.id.container_bar);
        if (findViewById != null) {
            this.j.a(motionEvent, findViewById, false);
        }
    }

    private void a(boolean z) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("SplashActivity.waiting_login", z);
        startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyle);
    }

    private View a(int i, int i2, int i3) {
        dp dpVar = new dp(this);
        ReclickLinearLayout newClickableTab = this.g.newClickableTab();
        newClickableTab.setId(i);
        newClickableTab.setLayoutParams(new LayoutParams(0, -1, (float) i3));
        newClickableTab.setOrientation(1);
        newClickableTab.setGravity(R.styleable.Toolbar_maxButtonHeight);
        newClickableTab.setMyOnClickListener(dpVar);
        newClickableTab.setTag(Integer.valueOf(i2));
        View relativeLayout = new RelativeLayout(this);
        if (i2 != 0) {
            View imageView = new ImageView(this);
            imageView.setId(16908295);
            imageView.setImageResource(i2);
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(1, 16908295);
            View imageView2 = new ImageView(this);
            imageView2.setId(16908296);
            imageView2.setImageResource(R.drawable.xllive_red_flag);
            imageView2.setLayoutParams(layoutParams);
            imageView2.setVisibility(XZBDevice.Wait);
            relativeLayout.addView(imageView, Tabs.TAB_CREATE_REACH_MAX_COUNT, Tabs.TAB_CREATE_REACH_MAX_COUNT);
            relativeLayout.addView(imageView2);
        }
        newClickableTab.addView(relativeLayout, Tabs.TAB_CREATE_REACH_MAX_COUNT, Tabs.TAB_CREATE_REACH_MAX_COUNT);
        return newClickableTab;
    }

    private void e() {
        String k = f.a((Context) this).k();
        if ("usercenter".equals(this.g.getCurrentTabTag())) {
            putInt(new StringBuilder("LAST_USER_MAIL_NUMBER_").append(k).toString(), e.a);
        } else if (getInt(new StringBuilder("LAST_USER_MAIL_NUMBER_").append(k).toString(), e.a) != e.a) {
            View tabViewByTag = this.g.getTabViewByTag("usercenter");
            if (tabViewByTag != null) {
                tabViewByTag = tabViewByTag.findViewById(16908296);
                if (tabViewByTag != null) {
                    tabViewByTag.setVisibility(0);
                }
            }
        }
    }

    public void a(String str) {
        String str2;
        String str3;
        Map map;
        XLog.d("MainActivity", new StringBuilder("tractPageShow() tag = ").append(str).append(", flag = ").append(this.e).toString());
        if ("main".equals(str)) {
            str2 = "home_page_show";
            if (this.e == 3) {
                str3 = str2;
                str2 = "back";
                map = null;
            } else if (this.e == 2) {
                str3 = str2;
                str2 = "icon";
                map = null;
            } else if (this.e == 4) {
                str3 = str2;
                str2 = "start";
                map = null;
            } else if (this.e == 5) {
                str3 = str2;
                str2 = "centerattention";
                map = null;
            } else {
                map = null;
                str3 = str2;
                str2 = null;
            }
        } else if ("usercenter".equals(str)) {
            String str4;
            str3 = "center_page_show";
            if (this.e == 1) {
                str4 = "icon";
            } else {
                str4 = null;
            }
            com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
            aVar.a("hostid", f.a().k());
            HashMap a = aVar.a();
            str2 = str4;
            HashMap hashMap = a;
        } else {
            map = null;
            str2 = null;
            str3 = null;
        }
        com.xunlei.tdlive.util.q.a(str3, str2, null, map);
        this.e = 0;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getObject(SocializeConstants.JSON_DATA, "{}");
            JsonWrapper object2 = object.getObject("rate_limit", "{}");
            JsonWrapper object3 = object.getObject("play_buffer_time", "{}");
            JsonWrapper array = object.getArray("skincare_uids", "[]");
            JsonWrapper array2 = object.getArray("share_titles", "[]");
            JsonWrapper object4 = object.getObject("app_color", "{}");
            JsonWrapper object5 = object.getObject("in_room", "{}");
            e.f = object.getInt("beauty_low_fps", e.f);
            e.n = object.getInt("close_room_tick_count", e.n);
            e.r = object5.getInt("l_min", e.r);
            e.s = object5.getInt("l_vip_min", e.s);
            e.m = object2.getInt(ParamKey.COUNT, e.m);
            e.k = object2.getInt("medium", e.k);
            e.l = object2.getInt("low", e.l);
            e.g = array.toString();
            e.h = array2.toString();
            e.i = object.getString("share_content", a.d);
            e.c = object3.getInt("buffer_time", e.c);
            e.b = object3.getInt("max_buffer_time", e.b);
            e.j = object.getBoolean("user_verify", e.j);
            Iterator keys = object4.keys();
            while (keys != null && keys.hasNext()) {
                String str2 = (String) keys.next();
                array = object4.getObject(str2, "{}");
                e.o.put(str2, array.getString("former", a.d) + "=" + array.getString("latter", a.d));
            }
            a.a((Context) this, object.getString("report_url", "http://biz.live.xunlei.com/caller?c=site&a=report&from=app"));
        }
    }
}
