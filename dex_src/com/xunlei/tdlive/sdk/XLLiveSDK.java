package com.xunlei.tdlive.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.a.a.c.c;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.message.entity.UMessage;
import com.xunlei.download.proguard.y;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.RecommandActivity;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.ea;
import com.xunlei.tdlive.protocol.PushTagRequest;
import com.xunlei.tdlive.protocol.ReportPushResultRequest;
import com.xunlei.tdlive.protocol.XLiveRedFlagInfoRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public class XLLiveSDK {
    private static XLLiveSDK f;
    final String a;
    final String b;
    final String c;
    final String d;
    final String e;
    private Context g;
    private IHost h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;

    static {
        f = null;
    }

    public static XLLiveSDK getInstance(Context context) {
        if (f == null) {
            XLLiveSDK xLLiveSDK = new XLLiveSDK();
            f = xLLiveSDK;
            xLLiveSDK.g = context.getApplicationContext();
        }
        return f;
    }

    private XLLiveSDK() {
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.a = UMessage.NOTIFICATION_GO_APP;
        this.b = UMessage.NOTIFICATION_GO_URL;
        this.c = "go_home";
        this.d = "go_room";
        this.e = UMessage.NOTIFICATION_GO_ACTIVITY;
    }

    public IHost host() {
        if (this.h == null) {
            try {
                this.h = (IHost) Class.forName("com.xunlei.tdlive.LiveHost").newInstance();
                this.h.init(this.g, new a());
            } catch (Throwable th) {
            }
        }
        return this.h;
    }

    public void init(String str) {
        c.a(this.g);
        ac.a(this.g);
        q.a(this.g);
        f.a(this.g).a(true);
        host().silentLogin(this.g);
        new Thread(new b(this, str)).start();
    }

    public void appOnDesk() {
        if (!this.i) {
            this.i = true;
            q.a("app_on_desk", "normal", null, null);
        }
    }

    public void play(Context context, String str) {
        play(context, str, "jinxuan");
    }

    private boolean a() {
        List runningTasks = ((ActivityManager) this.g.getSystemService("activity")).getRunningTasks(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        return (runningTasks == null || runningTasks.size() <= 0 || ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(this.g.getPackageName())) ? false : true;
    }

    public void onPushNotificationCanceled(Context context, String str) {
        XLog.d("XLLiveSDK", new StringBuilder("onPushNotificationCanceled(): ").append(str).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (UMessage.NOTIFICATION_GO_APP.equals(jSONObject.getString("after_open"))) {
                if ("go_room".equals(jSONObject.getString("business"))) {
                    a(jSONObject.getJSONObject(y.g).getString("userid"), false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playFromPush(Context context, String str) {
        XLog.d("XLLiveSDK", new StringBuilder("play data: ").append(str).toString());
        try {
            if (a()) {
                host().startMainTab(this.g);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("after_open");
                if (UMessage.NOTIFICATION_GO_URL.equals(string)) {
                    WebBrowserActivity.start(context, jSONObject.getString(SocialConstants.PARAM_URL), null, false, 268435456);
                } else if (UMessage.NOTIFICATION_GO_APP.equals(string)) {
                    string = jSONObject.getString("business");
                    if ("go_room".equals(string)) {
                        jSONObject = jSONObject.getJSONObject(y.g);
                        Object string2 = jSONObject.getString("roomid");
                        String string3 = jSONObject.getString("userid");
                        String string4 = jSONObject.getString("stream_pull");
                        String string5 = jSONObject.getString(WBConstants.GAME_PARAMS_GAME_IMAGE_URL);
                        String string6 = jSONObject.getString("avatar");
                        if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                            LivePlayerActivity.a(this.g, string2, string3, string4, string6, string5, MessageService.MSG_DB_READY_REPORT, 0, 1, "push");
                            a(string3, true);
                        }
                    } else if (UMessage.NOTIFICATION_GO_ACTIVITY.equals(string)) {
                        jSONObject = jSONObject.getJSONObject(y.g);
                        string = jSONObject.getString("class");
                        if (!TextUtils.isEmpty(string)) {
                            try {
                                if (RecommandActivity.class.equals(Class.forName(string))) {
                                    RecommandActivity.a(this.g, jSONObject.optString(SocialConstants.PARAM_URL, a.d), jSONObject.optString(WebBrowserActivity.EXTRA_TITLE, a.d), 268435456);
                                    return;
                                }
                                Intent intent = new Intent();
                                intent.addFlags(268435456);
                                intent.setClassName(this.g.getPackageName(), string);
                                context.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        XLog.d("XLLiveSDK", "invalid push data.");
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    private void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str)) {
            new ReportPushResultRequest(f.a(this.g).k(), f.a(this.g).l(), str, z).send(new f(this));
        }
    }

    public JSONObject play(Context context, String str, String str2) {
        XLog.d("XLLiveSDK", new StringBuilder("play roomInfo: ").append(str).append(", from = ").append(str2).toString());
        try {
            JSONObject jSONObject = new JSONObject(str);
            LivePlayerActivity.a(context, new Intent().putExtra("userid", jSONObject.getString("userid")).putExtra("roomid", jSONObject.getString("roomid")).putExtra("avatar", jSONObject.getString("avatar")).putExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, jSONObject.getString(WBConstants.GAME_PARAMS_GAME_IMAGE_URL)).putExtra("stream_pull", jSONObject.getString("stream_pull")).putExtra("onlinenum", jSONObject.getString("onlinenum")).putExtra("follow", jSONObject.getString("is_follow")).putExtra("hot_level", jSONObject.getString("hot_level")).putExtra("from", str2).putExtra("time", SystemClock.elapsedRealtime()));
            return jSONObject;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean canPlay() {
        boolean z = !this.k && host().showXLLiveTab(this.g) && f.a(this.g).b();
        XLog.d("XLLiveSDK", new StringBuilder("canPlay(): ret = ").append(z).toString());
        return z;
    }

    public boolean hasLiveFutrue() {
        return getSharedPreferences().getInt("open_flag", 0) != 0;
    }

    public SharedPreferences getSharedPreferences() {
        return this.g.getApplicationContext().getSharedPreferences("xllive_sdk_pf", 0);
    }

    public void queryRedflagInfo(Context context, long j, HttpRequestCallback httpRequestCallback) {
        new XLiveRedFlagInfoRequest(f.a(context).k(), f.a(context).l(), j).send(new g(this, httpRequestCallback));
    }

    public void setHasRedFlagOnXLLiveTab(boolean z) {
        this.j = z;
    }

    public boolean hasRedFlagOnXLLiveTab() {
        return this.j;
    }

    public Fragment newLiveListFragment(Handler handler) {
        Fragment eaVar = new ea();
        eaVar.a(handler);
        return eaVar;
    }

    public void managePushTag(Context context) {
        if (!this.l && f.a(this.g).b()) {
            SharedPreferences sharedPreferences = getSharedPreferences();
            boolean z = sharedPreferences.getBoolean("push_tag_set", false);
            boolean showXLLiveTab = host().showXLLiveTab(this.g);
            XLog.d("XLLiveSDK", new StringBuilder("pushTagSet = ").append(z).append(", show_xllive_tab = ").append(showXLLiveTab).toString());
            if (showXLLiveTab && !z) {
                a(this.g, true);
                sharedPreferences.edit().putBoolean("push_tag_set", true).apply();
            } else if (showXLLiveTab || !z) {
                this.l = true;
            } else {
                a(context, false);
                sharedPreferences.edit().putBoolean("push_tag_set", false).apply();
            }
        }
    }

    private void a(Context context, boolean z) {
        new PushTagRequest(f.a(context).k(), f.a(context).l(), z ? "add" : "remove", host().getHubbleGUID()).send(new h(this));
    }
}
