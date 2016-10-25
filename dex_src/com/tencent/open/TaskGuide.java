package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.BaseMonitor;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.android.spdy.SpdyAgent;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class TaskGuide extends BaseApi {
    private static int L;
    static long b;
    private static Drawable k;
    private static Drawable l;
    private static Drawable m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int y;
    private static int z;
    private int A;
    private int B;
    private float C;
    private Interpolator D;
    private boolean E;
    private Context F;
    private boolean G;
    private boolean H;
    private long I;
    private int J;
    private int K;
    private Runnable M;
    private Runnable N;
    boolean a;
    IUiListener c;
    private LayoutParams d;
    private ViewGroup e;
    private WindowManager f;
    private Handler g;
    private h h;
    private k i;
    private k j;

    // compiled from: ProGuard
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ int a;

        AnonymousClass_1(int i) {
            this.a = i;
        }

        public void run() {
            if (!TaskGuide.this.E) {
                return;
            }
            if (this.a == 0) {
                ((i) TaskGuide.this.e.findViewById(1)).a(TaskGuide.this.i);
            } else if (this.a == 1) {
                ((i) TaskGuide.this.e.findViewById(XZBDevice.DOWNLOAD_LIST_RECYCLE)).a(TaskGuide.this.j);
            } else if (this.a == 2) {
                ((i) TaskGuide.this.e.findViewById(1)).a(TaskGuide.this.i);
                if (TaskGuide.this.e.getChildCount() > 1) {
                    ((i) TaskGuide.this.e.findViewById(XZBDevice.DOWNLOAD_LIST_RECYCLE)).a(TaskGuide.this.j);
                }
            }
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass_3(String str) {
            this.a = str;
        }

        public void run() {
            Toast.makeText(TaskGuide.this.F, new StringBuilder("\u5931\u8d25\uff1a").append(this.a).toString(), 1).show();
        }
    }

    // compiled from: ProGuard
    static /* synthetic */ class AnonymousClass_4 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[k.a().length];
            try {
                a[k.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[k.d.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[k.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[k.e.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    // compiled from: ProGuard
    private abstract class a implements IRequestListener {
        protected abstract void a(Exception exception);

        private a() {
        }

        public void onIOException(IOException iOException) {
            a(iOException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        public void onUnknowException(Exception exception) {
            a(exception);
        }
    }

    // compiled from: ProGuard
    private class b implements Runnable {
        private b() {
        }

        public void run() {
            TaskGuide.this.l();
        }
    }

    // compiled from: ProGuard
    class c implements Runnable {
        boolean a;
        float b;

        public c(boolean z) {
            this.a = false;
            this.b = 0.0f;
            this.a = z;
        }

        public void run() {
            Object obj = 1;
            SystemClock.currentThreadTimeMillis();
            this.b = (float) (((double) this.b) + 0.1d);
            float f = this.b;
            if (f > 1.0f) {
                f = 1.0f;
            }
            if (f >= 1.0f) {
                int i = 1;
            } else {
                Object obj2 = null;
            }
            int interpolation = (int) (TaskGuide.this.D.getInterpolation(f) * ((float) TaskGuide.this.J));
            if (this.a) {
                TaskGuide.this.d.y = TaskGuide.this.K + interpolation;
            } else {
                TaskGuide.this.d.y = TaskGuide.this.K - interpolation;
            }
            com.tencent.open.a.f.b("openSDK_LOG.TaskGuide", new StringBuilder("mWinParams.y = ").append(TaskGuide.this.d.y).append("deltaDistence = ").append(interpolation).toString());
            if (TaskGuide.this.E) {
                TaskGuide.this.f.updateViewLayout(TaskGuide.this.e, TaskGuide.this.d);
                obj = obj2;
            }
            if (obj != null) {
                TaskGuide.this.i();
            } else {
                TaskGuide.this.g.postDelayed(TaskGuide.this.M, 5);
            }
        }
    }

    // compiled from: ProGuard
    private class d extends a {
        int b;

        // compiled from: ProGuard
        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ Exception a;

            AnonymousClass_1(Exception exception) {
                this.a = exception;
            }

            public void run() {
                k kVar = k.a;
                if (d.this == 0) {
                    kVar = TaskGuide.this.i;
                } else {
                    kVar = TaskGuide.this.j;
                }
                if (kVar == k.c) {
                    TaskGuide.this.a(d.this, k.d);
                    TaskGuide.this.a(new StringBuilder("\u9886\u53d6\u5931\u8d25 :").append(this.a.getClass().getName()).toString());
                }
                TaskGuide.this.b(d.this);
                TaskGuide.this.d((int) IHost.CLIENT_NOFITY_INIT);
            }
        }

        public d(int i) {
            super(null);
            this.b = -1;
            this.b = i;
        }

        public void onComplete(JSONObject jSONObject) {
            String str = null;
            try {
                int i = jSONObject.getInt(Constants.KEY_HTTP_CODE);
                str = jSONObject.getString(Constants.SHARED_MESSAGE_ID_FILE);
                JSONObject jSONObject2;
                if (i == 0) {
                    TaskGuide.this.a(this.b, k.e);
                    jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("result", "\u91d1\u5238\u9886\u53d6\u6210\u529f");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TaskGuide.this.onComplete(jSONObject2);
                    TaskGuide.this.b(this.b);
                    TaskGuide.this.d((int) IHost.CLIENT_NOFITY_INIT);
                }
                TaskGuide.this.a(this.b, k.d);
                TaskGuide.this.a(str);
                jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", "\u91d1\u5238\u9886\u53d6\u5931\u8d25");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                TaskGuide.this.onComplete(jSONObject2);
                TaskGuide.this.b(this.b);
                TaskGuide.this.d((int) IHost.CLIENT_NOFITY_INIT);
            } catch (JSONException e22) {
                TaskGuide.this.a(this.b, k.d);
                TaskGuide.this.a(str);
                e22.printStackTrace();
            }
        }

        protected void a(Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            TaskGuide.this.onError(new UiError(101, "error ", "\u91d1\u5238\u9886\u53d6\u65f6\u51fa\u73b0\u5f02\u5e38"));
            if (TaskGuide.this.g != null) {
                TaskGuide.this.g.post(new AnonymousClass_1(exception));
            }
        }
    }

    // compiled from: ProGuard
    private class e extends RelativeLayout {
        int a;

        public e(Context context) {
            super(context);
            this.a = 0;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int y = (int) motionEvent.getY();
            com.tencent.open.a.f.a("openSDK_LOG.TaskGuide", new StringBuilder("onInterceptTouchEvent-- action = ").append(motionEvent.getAction()).append("currentY = ").append(y).toString());
            TaskGuide.this.d(3000);
            switch (motionEvent.getAction()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.a = y;
                    return false;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (this.a - y > ViewConfiguration.getTouchSlop() * 2) {
                        TaskGuide.this.l();
                        return true;
                    }
                    return super.onInterceptTouchEvent(motionEvent);
                default:
                    return super.onInterceptTouchEvent(motionEvent);
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            super.onTouchEvent(motionEvent);
            int y = (int) motionEvent.getY();
            com.tencent.open.a.f.b("openSDK_LOG.TaskGuide", new StringBuilder(" onTouchEvent-----startY = ").append(this.a).append("currentY = ").append(y).toString());
            switch (motionEvent.getAction()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.a = y;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (this.a - y > ViewConfiguration.getTouchSlop() * 2) {
                        TaskGuide.this.l();
                    }
                    break;
            }
            return false;
        }
    }

    // compiled from: ProGuard
    class f implements OnClickListener {
        int a;

        public f(int i) {
            this.a = i;
        }

        public void onClick(View view) {
            if (TaskGuide.this.c(this.a) == k.d) {
                TaskGuide.this.e(this.a);
                TaskGuide.this.b(this.a);
            }
            TaskGuide.this.h();
        }
    }

    // compiled from: ProGuard
    private static class g {
        int a;
        String b;
        String c;
        long d;
        int e;

        public g(int i, String str, String str2, long j, int i2) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = j;
            this.e = i2;
        }
    }

    // compiled from: ProGuard
    private static class h {
        String a;
        String b;
        g[] c;

        private h() {
        }

        public boolean a() {
            return (TextUtils.isEmpty(this.a) || this.c == null || this.c.length <= 0) ? false : true;
        }

        static h a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            h hVar = new h();
            JSONObject jSONObject2 = jSONObject.getJSONObject("task_info");
            hVar.a = jSONObject2.getString("task_id");
            hVar.b = jSONObject2.getString("task_desc");
            JSONArray jSONArray = jSONObject2.getJSONArray("step_info");
            int length = jSONArray.length();
            if (length > 0) {
                hVar.c = new g[length];
            }
            for (int i = 0; i < length; i++) {
                jSONObject2 = jSONArray.getJSONObject(i);
                hVar.c[i] = new g(jSONObject2.getInt("step_no"), jSONObject2.getString("step_desc"), jSONObject2.getString("step_gift"), jSONObject2.getLong("end_time"), jSONObject2.getInt(Impl.COLUMN_STATUS));
            }
            return hVar;
        }
    }

    // compiled from: ProGuard
    private class i extends LinearLayout {
        private TextView b;
        private Button c;
        private g d;

        public i(Context context, g gVar) {
            super(context);
            this.d = gVar;
            setOrientation(0);
            a();
        }

        private void a() {
            this.b = new TextView(TaskGuide.this.F);
            this.b.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
            this.b.setTextSize(15.0f);
            this.b.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, Impl.STATUS_DEVICE_NOT_FOUND_ERROR));
            this.b.setGravity(XZBDevice.DOWNLOAD_LIST_FAILED);
            this.b.setEllipsize(TruncateAt.END);
            this.b.setIncludeFontPadding(false);
            this.b.setSingleLine(true);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            layoutParams.leftMargin = TaskGuide.this.a((int) XZBDevice.DOWNLOAD_LIST_ALL);
            addView(this.b, layoutParams);
            this.c = new Button(TaskGuide.this.F);
            this.c.setPadding(0, 0, 0, 0);
            this.c.setTextSize(16.0f);
            this.c.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
            this.c.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, Impl.STATUS_DEVICE_NOT_FOUND_ERROR));
            this.c.setIncludeFontPadding(false);
            this.c.setOnClickListener(new f(TaskGuide.this));
            layoutParams = new LinearLayout.LayoutParams(TaskGuide.this.a(p), TaskGuide.this.a(q));
            layoutParams.leftMargin = TaskGuide.this.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            layoutParams.rightMargin = TaskGuide.this.a((int) XZBDevice.Wait);
            addView(this.c, layoutParams);
        }

        public void a(k kVar) {
            if (!TextUtils.isEmpty(this.d.b)) {
                this.b.setText(this.d.b);
            }
            switch (AnonymousClass_4.a[kVar.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.c.setEnabled(false);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (this.d.e == 1) {
                        this.c.setText(this.d.c);
                        this.c.setBackgroundDrawable(null);
                        this.c.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, 246, 0));
                        this.c.setEnabled(false);
                    } else if (this.d.e == 2) {
                        this.c.setText("\u9886\u53d6\u5956\u52b1");
                        this.c.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
                        this.c.setBackgroundDrawable(TaskGuide.this.f());
                        this.c.setEnabled(true);
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.c.setText("\u9886\u53d6\u4e2d...");
                    this.c.setEnabled(false);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.c.setText("\u5df2\u9886\u53d6");
                    this.c.setBackgroundDrawable(TaskGuide.this.g());
                    this.c.setEnabled(false);
                default:
                    break;
            }
        }
    }

    // compiled from: ProGuard
    private class j extends a {
        private j() {
            super(null);
        }

        public void onComplete(JSONObject jSONObject) {
            try {
                TaskGuide.this.h = h.a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (TaskGuide.this.h == null || !TaskGuide.this.h.a()) {
                a(null);
                return;
            }
            TaskGuide.this.showWindow();
            TaskGuide.this.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, k.d);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", "\u83b7\u53d6\u6210\u529f");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            TaskGuide.this.c.onComplete(jSONObject2);
        }

        protected void a(Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            if (exception == null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("result", "\u6682\u65e0\u4efb\u52a1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TaskGuide.this.c.onComplete(jSONObject);
            } else {
                TaskGuide.this.c.onError(new UiError(100, "error ", "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25"));
            }
            TaskGuide.this.g.post(new Runnable() {
                public void run() {
                    TaskGuide.this.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, k.a);
                }
            });
        }
    }

    // compiled from: ProGuard
    private enum k {
        INIT,
        WAITTING_BACK_TASKINFO,
        WAITTING_BACK_REWARD,
        NORAML,
        REWARD_SUCCESS,
        REWARD_FAIL;

        public static k[] a() {
            return (k[]) g.clone();
        }

        static {
            a = new k("INIT", 0);
            b = new k("WAITTING_BACK_TASKINFO", 1);
            c = new k("WAITTING_BACK_REWARD", 2);
            d = new k("NORAML", 3);
            e = new k("REWARD_SUCCESS", 4);
            f = new k("REWARD_FAIL", 5);
            g = new k[]{a, b, c, d, e, f};
        }
    }

    static {
        n = 75;
        o = 284;
        p = 75;
        q = 30;
        r = 29;
        s = 5;
        t = 74;
        u = 0;
        v = 6;
        w = 153;
        x = 30;
        y = 6;
        z = 3;
        b = 5000;
        L = 3000;
    }

    public TaskGuide(Context context, QQToken qQToken) {
        super(qQToken);
        this.d = null;
        this.e = null;
        this.g = new Handler(Looper.getMainLooper());
        this.i = k.a;
        this.j = k.a;
        this.A = 0;
        this.B = 0;
        this.C = 0.0f;
        this.D = new AccelerateInterpolator();
        this.E = false;
        this.a = false;
        this.G = false;
        this.H = false;
        this.M = null;
        this.N = null;
        this.F = context;
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    public TaskGuide(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        this.d = null;
        this.e = null;
        this.g = new Handler(Looper.getMainLooper());
        this.i = k.a;
        this.j = k.a;
        this.A = 0;
        this.B = 0;
        this.C = 0.0f;
        this.D = new AccelerateInterpolator();
        this.E = false;
        this.a = false;
        this.G = false;
        this.H = false;
        this.M = null;
        this.N = null;
        this.F = context;
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    private void c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f.getDefaultDisplay().getMetrics(displayMetrics);
        this.A = displayMetrics.widthPixels;
        this.B = displayMetrics.heightPixels;
        this.C = displayMetrics.density;
    }

    private LayoutParams a(Context context) {
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 49;
        this.f.getDefaultDisplay().getWidth();
        this.f.getDefaultDisplay().getHeight();
        layoutParams.width = a(o);
        layoutParams.height = a(n);
        layoutParams.windowAnimations = 16973826;
        layoutParams.format = 1;
        layoutParams.flags |= 520;
        layoutParams.type = 2;
        this.d = layoutParams;
        return layoutParams;
    }

    private void d() {
        if (this.d != null) {
            this.d.y = -this.d.height;
        }
    }

    private int a(int i) {
        return (int) (((float) i) * this.C);
    }

    private ViewGroup b(Context context) {
        ViewGroup eVar = new e(context);
        g[] gVarArr = this.h.c;
        View iVar;
        ViewGroup.LayoutParams layoutParams;
        if (gVarArr.length == 1) {
            iVar = new i(context, gVarArr[0]);
            iVar.setId(1);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(XZBDevice.Delete);
            eVar.addView(iVar, layoutParams);
        } else {
            iVar = new i(context, gVarArr[0]);
            iVar.setId(1);
            View iVar2 = new i(context, gVarArr[1]);
            iVar2.setId(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(XZBDevice.Predownload);
            layoutParams.setMargins(0, a((int) R.styleable.Toolbar_contentInsetEnd), 0, 0);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(XZBDevice.Predownload);
            layoutParams2.setMargins(0, a((int) XZBDevice.DOWNLOAD_LIST_ALL), 0, 0);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, 1);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
            eVar.addView(iVar, layoutParams);
            eVar.addView(iVar2, layoutParams2);
        }
        eVar.setBackgroundDrawable(e());
        return eVar;
    }

    private Drawable e() {
        if (k == null) {
            k = a("background.9.png", this.F);
        }
        return k;
    }

    private Drawable f() {
        if (l == null) {
            l = a("button_green.9.png", this.F);
        }
        return l;
    }

    private Drawable g() {
        if (m == null) {
            m = a("button_red.9.png", this.F);
        }
        return m;
    }

    private void b(int i) {
        if (this.g != null) {
            this.g.post(new AnonymousClass_1(i));
        }
    }

    private void a(int i, k kVar) {
        if (i == 0) {
            this.i = kVar;
        } else if (i == 1) {
            this.j = kVar;
        } else {
            this.i = kVar;
            this.j = kVar;
        }
    }

    private k c(int i) {
        if (i == 0) {
            return this.i;
        }
        return i == 1 ? this.j : k.a;
    }

    @SuppressLint({"ResourceAsColor"})
    public void showWindow() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                TaskGuide.this.e = TaskGuide.this.b(TaskGuide.this.F);
                TaskGuide.this.d = TaskGuide.this.a(TaskGuide.this.F);
                TaskGuide.this.d();
                WindowManager windowManager = (WindowManager) TaskGuide.this.F.getSystemService("window");
                if (!((Activity) TaskGuide.this.F).isFinishing()) {
                    if (!TaskGuide.this.E) {
                        windowManager.addView(TaskGuide.this.e, TaskGuide.this.d);
                    }
                    TaskGuide.this.E = true;
                    TaskGuide.this.b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    TaskGuide.this.k();
                }
            }
        });
        com.tencent.connect.a.a.a(this.F, this.mToken, "TaskApi", "showTaskWindow");
    }

    private void d(int i) {
        h();
        this.N = new b();
        this.g.postDelayed(this.N, (long) i);
    }

    private void h() {
        this.g.removeCallbacks(this.N);
        if (!j()) {
            this.g.removeCallbacks(this.M);
        }
    }

    private void i() {
        if (this.G) {
            d(3000);
        } else {
            removeWindow();
        }
        if (this.G) {
            LayoutParams layoutParams = this.d;
            layoutParams.flags &= -17;
            this.f.updateViewLayout(this.e, this.d);
        }
        this.G = false;
        this.H = false;
    }

    private void a(boolean z) {
        this.I = SystemClock.currentThreadTimeMillis();
        if (z) {
            this.G = true;
        } else {
            this.H = true;
        }
        this.J = this.d.height;
        this.K = this.d.y;
        LayoutParams layoutParams = this.d;
        layoutParams.flags |= 16;
        this.f.updateViewLayout(this.e, this.d);
    }

    private boolean j() {
        return this.G || this.H;
    }

    private void k() {
        if (!j()) {
            this.g.removeCallbacks(this.N);
            this.g.removeCallbacks(this.M);
            this.M = new c(true);
            a(true);
            this.g.post(this.M);
        }
    }

    private void l() {
        if (!j()) {
            this.g.removeCallbacks(this.N);
            this.g.removeCallbacks(this.M);
            this.M = new c(false);
            a(false);
            this.g.post(this.M);
        }
    }

    public void removeWindow() {
        if (this.E) {
            this.f.removeView(this.e);
            this.E = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable a(java.lang.String r8, android.content.Context r9) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.TaskGuide.a(java.lang.String, android.content.Context):android.graphics.drawable.Drawable");
        /*
        this = this;
        r1 = 0;
        r0 = r9.getApplicationContext();
        r0 = r0.getAssets();
        r2 = r0.open(r8);	 Catch:{ IOException -> 0x0034 }
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        r0 = r1;
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = ".9.png";
        r0 = r8.endsWith(r0);	 Catch:{ IOException -> 0x0034 }
        if (r0 == 0) goto L_0x0044;
    L_0x001a:
        r0 = android.graphics.BitmapFactory.decodeStream(r2);	 Catch:{ OutOfMemoryError -> 0x003c }
        r2 = r0;
    L_0x001f:
        if (r2 == 0) goto L_0x0042;
    L_0x0021:
        r3 = r2.getNinePatchChunk();	 Catch:{ IOException -> 0x0034 }
        android.graphics.NinePatch.isNinePatchChunk(r3);	 Catch:{ IOException -> 0x0034 }
        r0 = new android.graphics.drawable.NinePatchDrawable;	 Catch:{ IOException -> 0x0034 }
        r4 = new android.graphics.Rect;	 Catch:{ IOException -> 0x0034 }
        r4.<init>();	 Catch:{ IOException -> 0x0034 }
        r5 = 0;
        r0.<init>(r2, r3, r4, r5);	 Catch:{ IOException -> 0x0034 }
        goto L_0x0010;
    L_0x0034:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0038:
        r1.printStackTrace();
        goto L_0x0010;
    L_0x003c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ IOException -> 0x0034 }
        r2 = r1;
        goto L_0x001f;
    L_0x0042:
        r0 = r1;
        goto L_0x0010;
    L_0x0044:
        r0 = android.graphics.drawable.Drawable.createFromStream(r2, r8);	 Catch:{ IOException -> 0x0034 }
        r2.close();	 Catch:{ IOException -> 0x004c }
        goto L_0x0010;
    L_0x004c:
        r1 = move-exception;
        goto L_0x0038;
        */
    }

    private void a(String str) {
        this.g.post(new AnonymousClass_3(str));
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.F = activity;
        this.c = iUiListener;
        if (this.i == k.b || this.j == k.b || this.E) {
            com.tencent.open.a.f.c("openSDK_LOG.TaskGuide", new StringBuilder("showTaskGuideWindow, mState1 ==").append(this.i).append(", mState2").append(this.j).toString());
            return;
        }
        Bundle bundle2;
        this.h = null;
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
            bundle2.putAll(composeCGIParams());
        } else {
            bundle2 = composeCGIParams();
        }
        IRequestListener jVar = new j();
        bundle2.putString(JsInterface.KEY_ACTION, "task_list");
        bundle2.putString(BaseMonitor.ALARM_POINT_AUTH, "mobile");
        bundle2.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", bundle2, com.tencent.connect.common.Constants.HTTP_GET, jVar);
        a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, k.b);
    }

    private void e(int i) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString(JsInterface.KEY_ACTION, "get_gift");
        composeCGIParams.putString("task_id", this.h.a);
        composeCGIParams.putString("step_no", new Integer(i).toString());
        composeCGIParams.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", composeCGIParams, com.tencent.connect.common.Constants.HTTP_GET, new d(i));
        a(i, k.c);
        com.tencent.connect.a.a.a(this.F, this.mToken, "TaskApi", "getGift");
    }
}
