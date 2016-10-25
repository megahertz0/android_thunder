package com.xunlei.downloadprovider.qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.zxing.client.a.a.b;
import com.google.zxing.client.a.a.d.a;
import com.google.zxing.client.a.c;
import com.google.zxing.client.a.d;
import com.taobao.accs.common.Constants;
import com.tencent.open.yyb.TitleBar;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.commonview.dialog.XLOneBtnDialogActivity;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.g.e;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.downloadprovider.model.protocol.g.l;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;
import com.xunlei.downloadprovider.qrcode.a.i;
import com.xunlei.downloadprovider.qrcode.view.ViewfinderView;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class CameraActivity extends ThunderTask implements Callback, OnClickListener, a, d {
    public static String a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static boolean i;
    private SurfaceHolder A;
    private c B;
    private com.xunlei.downloadprovider.qrcode.b.c C;
    private Bitmap D;
    private boolean E;
    private boolean F;
    private boolean G;
    private long H;
    private int I;
    private int J;
    private int K;
    private int L;
    private boolean M;
    private boolean N;
    private com.xunlei.downloadprovider.qrcode.b.a O;
    private TranslateAnimation P;
    private WakeLock Q;
    private boolean R;
    private XLBaseDialog S;
    private String T;
    private boolean U;
    private int V;
    private boolean W;
    private String X;
    private com.xunlei.downloadprovider.qrcode.view.a Y;
    private Handler Z;
    private boolean aa;
    private k ab;
    private boolean ac;
    public long h;
    private RelativeLayout j;
    private View k;
    private ImageView l;
    private SurfaceView m;
    private ViewGroup n;
    private TextView o;
    private ViewfinderView p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private View t;
    private PopupWindow u;
    private TextView v;
    private TextView w;
    private TextView x;
    private Button y;
    private View z;

    public CameraActivity() {
        this.D = null;
        this.E = false;
        this.F = false;
        this.G = false;
        this.h = -1;
        this.P = null;
        this.R = false;
        this.U = false;
        this.V = 0;
        this.W = false;
        this.X = com.umeng.a.d;
        this.Y = null;
        this.Z = new a(this);
        this.aa = false;
        this.ac = false;
    }

    static {
        a = "fromhelp";
        b = 0;
        c = -1;
        d = -1;
        e = -1;
        i = false;
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams layoutParams;
        boolean z;
        new StringBuilder().append(getClass()).append("---onCreate---").append(Thread.currentThread().getId());
        this.C = com.xunlei.downloadprovider.qrcode.b.c.a((Context) this);
        this.I = this.C.a;
        this.J = this.C.b;
        new StringBuilder("mScreenWidth=").append(this.I).append(", mScreenHeight=").append(this.J);
        d();
        this.H = SystemClock.uptimeMillis();
        setContentView(2130968640);
        this.j = (RelativeLayout) findViewById(2131755378);
        this.j.setEnabled(false);
        this.j.setClickable(false);
        this.j.setLongClickable(false);
        this.k = findViewById(2131755379);
        this.k.setVisibility(0);
        this.k.setOnClickListener(this);
        this.o = (TextView) findViewById(2131755380);
        this.t = findViewById(2131755381);
        this.t.setOnClickListener(this);
        this.y = (Button) findViewById(2131755375);
        this.y.setOnClickListener(this);
        this.p = (ViewfinderView) findViewById(2131755369);
        this.p.setVisibility(0);
        this.q = (ImageView) findViewById(2131755376);
        n();
        this.v = (TextView) findViewById(2131755372);
        this.w = (TextView) findViewById(2131755373);
        this.x = (TextView) findViewById(2131755374);
        i();
        if (this.F) {
            this.v.setText(getString(2131230832));
            this.w.setVisibility(0);
            this.x.setVisibility(XZBDevice.Wait);
            this.y.setVisibility(XZBDevice.Wait);
        } else if (this.G) {
            this.o.setText(2131232276);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(XZBDevice.Upload);
            this.o.setLayoutParams(layoutParams);
            this.x.setVisibility(XZBDevice.Wait);
            this.w.setVisibility(XZBDevice.Wait);
            this.y.setVisibility(XZBDevice.Wait);
            this.t.setVisibility(XZBDevice.Wait);
        } else {
            this.v.setText(getString(2131232048));
            this.w.setVisibility(XZBDevice.Wait);
            this.x.setVisibility(0);
            this.y.setVisibility(0);
        }
        this.s = (ImageView) findViewById(2131755370);
        this.s.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.K = this.s.getMeasuredWidth();
        this.L = this.s.getMeasuredHeight();
        layoutParams = this.s.getLayoutParams();
        this.s.setLayoutParams(new AbsoluteLayout.LayoutParams(layoutParams.width, layoutParams.height, (this.I / 2) - (this.K / 2), (e - (this.L / 2)) + g.a(getApplicationContext(), TitleBar.SHAREBTN_RIGHT_MARGIN)));
        this.Y = new com.xunlei.downloadprovider.qrcode.view.a();
        com.xunlei.downloadprovider.qrcode.view.a aVar = this.Y;
        View findViewById = findViewById(2131755383);
        aVar.a = findViewById;
        aVar.b = (ImageView) findViewById.findViewById(2131755470);
        aVar.c = (TextView) findViewById.findViewById(2131755471);
        aVar.d = (RelativeLayout) findViewById.findViewById(2131755469);
        aVar.a.setVisibility(XZBDevice.Wait);
        this.r = (ImageView) findViewById(2131755368);
        this.r.setOnClickListener(this);
        this.z = findViewById(2131755382);
        this.z.setVisibility(XZBDevice.Wait);
        this.l = (ImageView) findViewById(2131755377);
        this.l.setOnClickListener(new d(this));
        if (this.E) {
            this.j.setVisibility(XZBDevice.Wait);
            this.l.setVisibility(0);
        }
        this.n = (ViewGroup) findViewById(2131755366);
        this.m = (SurfaceView) findViewById(2131755367);
        this.A = this.m.getHolder();
        this.A.addCallback(this);
        this.A.setType(XZBDevice.DOWNLOAD_LIST_FAILED);
        View inflate = LayoutInflater.from(this).inflate(2130968935, null);
        this.u = new PopupWindow(inflate, -1, -2, true);
        this.u.setBackgroundDrawable(new ColorDrawable(0));
        if (!(inflate == null || this.u == null)) {
            inflate.setOnClickListener(new e(this));
            this.u.setFocusable(true);
            this.u.setOutsideTouchable(true);
        }
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.Q = ((PowerManager) getSystemService("power")).newWakeLock(XZBDevice.Stop, getClass().getName());
        com.google.zxing.client.a.a.d.a((Context) this);
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.CAMERA") == 0) {
            z = true;
        } else {
            requestPermissions(new String[]{"android.permission.CAMERA"}, 80001);
            z = false;
        }
        if (z) {
            e();
        }
        if (this.s.getVisibility() == 0) {
            this.s.setVisibility(XZBDevice.Wait);
        }
        if (this.l.getVisibility() == 0) {
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            this.l.setAnimation(alphaAnimation);
            alphaAnimation.setDuration(2000);
            alphaAnimation.startNow();
        }
        this.O = new com.xunlei.downloadprovider.qrcode.b.a(this);
        if (((AudioManager) getSystemService("audio")).getRingerMode() != 2) {
            this.O.a = false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.H < 1000) {
            z = this.Z.sendEmptyMessageDelayed(1111, 1000 - (uptimeMillis - this.H));
        } else {
            z = this.Z.sendEmptyMessage(1111);
        }
        if (!z) {
            this.z.setVisibility(XZBDevice.Wait);
        }
        super.onCreate(bundle);
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        d();
    }

    private void d() {
        Intent intent = getIntent();
        this.F = intent.getBooleanExtra("fromAdhoc", false);
        this.E = intent.getBooleanExtra("fromDesk", false);
        this.G = intent.getBooleanExtra("remote_download", false);
        new StringBuilder("handleIntent mIsFromDesk = ").append(this.E).append(",mIsFromAdhoc=").append(this.F);
    }

    public void onResume() {
        boolean z = true;
        i = true;
        if (this.m.getParent() == null) {
            this.n.addView(this.m, 0);
        }
        this.Q.acquire();
        if (VERSION.SDK_INT >= 23 && checkSelfPermission("android.permission.CAMERA") != 0) {
            z = false;
        }
        if (z) {
            if (this.aa) {
                g();
            } else {
                e();
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences("scancode", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean("guid", false).commit();
        }
        super.onResume();
    }

    private void e() {
        if (!this.aa) {
            this.aa = true;
            g();
            j();
            i();
            n();
            this.P = new TranslateAnimation(0.0f, 0.0f, (float) c, (float) d);
            new StringBuilder("start pos = ").append(c).append("   end pos = ").append(d);
            this.P.setInterpolator(new AccelerateDecelerateInterpolator());
            this.P.setRepeatCount(-1);
            this.P.setRepeatMode(1);
            this.P.setDuration(TabsImpl.SYNC_TIME_OUT);
            this.P.startNow();
            if (this.p.getVisibility() == 0) {
                this.q.setVisibility(0);
                this.q.setAnimation(this.P);
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Object obj = null;
        if (80001 == i) {
            if (iArr.length > 0) {
                int length = iArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (iArr[i2] == -1) {
                        int i3 = -1;
                    }
                }
            }
            if (obj != null) {
                f();
            } else if (this.aa) {
                g();
            } else {
                e();
            }
        }
    }

    private void f() {
        if (this.Y != null) {
            this.Y.a();
        }
        this.Z.sendEmptyMessageDelayed(R.styleable.Toolbar_titleMarginBottom, TabsImpl.SYNC_TIME_OUT);
    }

    private void g() {
        com.google.zxing.client.a.a.d.a((Context) this);
        ViewfinderView.setShowCross(Boolean.valueOf(true));
        BrothersApplication.a().e.a = this;
        try {
            com.google.zxing.client.a.a.d b = com.google.zxing.client.a.a.d.b();
            if (b.c == null) {
                b.c = Camera.open();
                if (b.c == null && VERSION.SDK_INT >= 9) {
                    for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                        CameraInfo cameraInfo = new CameraInfo();
                        Camera.getCameraInfo(i, cameraInfo);
                        if (cameraInfo.facing == 1) {
                            b.c = Camera.open(i);
                            break;
                        }
                    }
                }
                if (b.c == null) {
                    throw new Exception();
                }
                if (!b.f) {
                    b.f = true;
                    b bVar = b.b;
                    Parameters parameters = b.c.getParameters();
                    bVar.f = parameters.flatten();
                    bVar.d = parameters.getPreviewFormat();
                    bVar.e = parameters.get("preview-format");
                    Display defaultDisplay = ((WindowManager) bVar.a.getSystemService("window")).getDefaultDisplay();
                    bVar.b = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                    new StringBuilder("Screen resolution: ").append(bVar.b);
                    Point point = bVar.b;
                    String replaceAll = parameters.get("preview-size-values").replaceAll("980x800,", com.umeng.a.d).replaceAll("960x720", com.umeng.a.d).replaceAll("1072x800", com.umeng.a.d);
                    String str;
                    if (replaceAll == null) {
                        str = parameters.get("preview-size-value");
                    } else {
                        str = replaceAll;
                    }
                    Point point2 = null;
                    if (r1 != null) {
                        point2 = b.a(r1, point);
                    }
                    if (point2 == null) {
                        point2 = new Point((point.x >> 3) << 3, (point.y >> 3) << 3);
                    }
                    bVar.c = point2;
                    new StringBuilder("Camera resolution: ").append(bVar.c);
                }
                b.b.a(b.c);
            }
            if (this.Y != null) {
                this.Y.b();
            }
            com.google.zxing.client.a.a.d b2 = com.google.zxing.client.a.a.d.b();
            ErrorCallback cVar = new c(this);
            if (b2.c != null) {
                b2.c.setErrorCallback(cVar);
            }
            if (!o()) {
                k();
            }
        } catch (Exception e) {
            f();
        }
    }

    public void onPause() {
        if (com.google.zxing.client.a.a.d.b() != null) {
            com.google.zxing.client.a.a.d.b().c();
            com.google.zxing.client.a.a.d b = com.google.zxing.client.a.a.d.b();
            if (b.c != null && b.g) {
                b.c.setPreviewCallback(null);
                b.c.stopPreview();
                b.h.a(null, 0);
                b.i.a(null, 0);
                b.g = false;
            }
            b = com.google.zxing.client.a.a.d.b();
            if (b.c != null) {
                com.google.zxing.client.a.a.c.a();
                b.c.release();
                b.c = null;
            }
        }
        l();
        BrothersApplication.a().e.a = null;
        if (this.Y != null) {
            this.Y.b();
        }
        this.Z.removeMessages(R.styleable.Toolbar_titleMarginBottom);
        this.Q.release();
        this.n.removeView(this.m);
        if (LoginHelper.a().u != null) {
            com.xunlei.downloadprovider.a.a.b.a(getApplicationContext()).a(406429);
            Intent intent = new Intent();
            intent.setClass(this, XLOneBtnDialogActivity.class);
            intent.putExtra("dlg_type", 406429);
            intent.addFlags(268435456);
            intent.putExtra("vip_logout_title", getString(2131231665));
            startActivity(intent);
            LoginHelper.a().u = null;
        }
        super.onPause();
    }

    private void h() {
        if (this.S == null || !this.S.isShowing()) {
            a(0, -1);
        } else {
            c();
        }
    }

    public final void a(int i, long j) {
        if (this.G && this.W) {
            Intent intent = new Intent();
            intent.putExtra("bind_peerid", this.T);
            intent.putExtra("bind_success_or_not", this.U);
            intent.putExtra("bind_errorcode", this.V);
            intent.putExtra("bind_key", this.X);
            setResult(R.styleable.Toolbar_contentInsetEnd, intent);
            finish();
            return;
        }
        if (1 == i) {
            DownloadCenterActivity.a(this, j, com.umeng.a.d);
        } else if (this.E) {
            MainTabActivity.a((Context) this, "thunder");
        }
        finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public void onDestroy() {
        i = false;
        if (!(this.D == null || this.D.isRecycled())) {
            this.D.recycle();
        }
        super.onDestroy();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    private void i() {
        this.v.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.v.getMeasuredWidth();
        int measuredHeight = this.v.getMeasuredHeight();
        LayoutParams layoutParams = this.v.getLayoutParams();
        this.v.setLayoutParams(new AbsoluteLayout.LayoutParams(layoutParams.width, layoutParams.height, (this.I / 2) - (measuredWidth / 2), (int) (((double) d) + (((double) measuredHeight) * 1.5d))));
    }

    private void a(String str) {
        new StringBuilder().append(getClass()).append("---showURLDialog(String url)---").append(Thread.currentThread().getId());
        XLBaseDialog iVar = new i(this, this.Z, str, com.xunlei.downloadprovider.util.c.a.b(str));
        this.S = iVar;
        iVar.show();
    }

    private void j() {
        Object obj = 1;
        if (this.M && com.google.zxing.client.a.a.d.b() != null) {
            try {
                com.google.zxing.client.a.a.d b = com.google.zxing.client.a.a.d.b();
                SurfaceHolder surfaceHolder = this.A;
                if (b.c != null) {
                    b.c.setPreviewDisplay(surfaceHolder);
                }
                b = com.google.zxing.client.a.a.d.b();
                if (b.c != null) {
                    b.c.setPreviewCallback(b.h);
                    b bVar = b.b;
                    Camera camera = b.c;
                    if (camera != null) {
                        Object obj2;
                        bVar.g = true;
                        if (Build.MODEL.equalsIgnoreCase("X10i") || Build.BOARD.equalsIgnoreCase("X10i")) {
                            obj2 = null;
                        } else {
                            int i = 1;
                        }
                        if (obj2 != null) {
                            String a;
                            Parameters parameters = camera.getParameters();
                            String str = Build.MODEL;
                            if (str.equalsIgnoreCase("zte u985") || Build.BOARD.equalsIgnoreCase("zte u985")) {
                                obj = null;
                            } else if (str.equalsIgnoreCase("X10i") || Build.BOARD.equalsIgnoreCase("X10i")) {
                                obj = null;
                            }
                            if (obj != null) {
                                a = b.a(parameters.getSupportedFocusModes(), "auto", "macro");
                                if (a != null) {
                                    parameters.setFocusMode(a);
                                }
                            }
                            a = b.a(parameters.getSupportedSceneModes(), "auto", "barcode");
                            if (a != null) {
                                parameters.setSceneMode(a);
                            }
                            parameters.setExposureCompensation(0);
                            camera.setParameters(parameters);
                        }
                    }
                }
                com.google.zxing.client.a.a.d b2 = com.google.zxing.client.a.a.d.b();
                try {
                    if (b2.c != null && !b2.g) {
                        b2.c.startPreview();
                        b2.g = true;
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                m();
            }
        }
    }

    private void k() {
        this.R = false;
        if (com.google.zxing.client.a.a.d.b() != null) {
            com.google.zxing.client.a.a.d b = com.google.zxing.client.a.a.d.b();
            b.a = this;
            b.d = null;
            b.e = null;
        }
        if (this.B != null) {
            this.B.a();
        }
        this.B = new c(this);
    }

    private void l() {
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    private void a(String str, String str2) {
        new StringBuilder("createNewTaskByUrl url=").append(str).append(",refUrl=").append(null);
        if (DownloadService.a() != null) {
            if (this.ab != null) {
                String str3 = this.ab.k;
            }
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(1, str, null);
            gVar.d = "manual/manual_codeScan";
            createLocalTask(str, str2, 0, null, null, null, 0, gVar, null, false);
        }
    }

    protected void onCreateTask(boolean z, int i) {
        new StringBuilder("onCreateTask create=").append(z).append(",reportType=").append(i).append(",mXunleiScanCodeResult=").append(this.ab);
        if (!(!z || this.ab == null || this.ab.j == null)) {
            if (12 == i || 15 == i) {
                new e(null, null).a(this.ab.j);
            }
        }
        StatReporter.reportOverallDownload("manual_codeScan");
        com.xunlei.downloadprovider.download.report.a.h(z ? "task_success" : "task_fail");
        if (!z) {
            c();
        }
    }

    public boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        this.h = j;
        if (i != 101) {
            a(1, j);
            return true;
        }
        if (i2 != 102409) {
            c();
        }
        return super.handleTaskOperator(i, i2, j, taskInfo);
    }

    private void m() {
        if (this.Y != null) {
            this.Y.a();
        }
    }

    public final void c() {
        this.r.setImageBitmap(null);
        this.r.setVisibility(XZBDevice.Wait);
        this.m.setVisibility(0);
        if (this.p.getVisibility() == 0) {
            this.q.setAnimation(this.P);
            this.q.setVisibility(0);
        }
        ViewfinderView.setShowCross(Boolean.valueOf(true));
        this.s.setVisibility(XZBDevice.Wait);
        if (this.M && com.google.zxing.client.a.a.d.b() != null) {
            k();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755368:
                c();
            case 2131755375:
                if (this.p.getVisibility() == 0) {
                    HelpActivity.a((Context) this, "file:///android_asset/help/code.html");
                }
            case 2131755379:
                if (this.p.getVisibility() == 0) {
                    StatReporter.reportQRClick("back");
                }
                h();
            case 2131755381:
                StatReporter.reportQRClick("rightBtn");
                if (this.p.getVisibility() == 0) {
                    this.u.showAsDropDown(this.j, 0, 0);
                }
            default:
                break;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            h();
        }
        return true;
    }

    private void n() {
        int i = 120;
        this.q.measure(0, 0);
        f = this.q.getMeasuredWidth();
        g = this.q.getMeasuredHeight();
        int i2 = f;
        if (i2 >= 120) {
            if (i2 > 480) {
                i = 480;
            } else {
                i = i2;
            }
        }
        int a = ((this.J - i) / 2) - g.a(getApplicationContext(), 77.0f);
        new StringBuilder("Util.dip2px(52)=").append(g.a(getApplicationContext(), 77.0f));
        if (a < g.a(getApplicationContext(), 77.0f)) {
            a = g.a(getApplicationContext(), 90.0f);
        }
        a -= (g * 2) / 3;
        c = a;
        d = ((a + i) - ((g * 1) / 4)) - 6;
        e = (i / 2) + c;
        new StringBuilder("calculateAnimationPos mAniStartPos=").append(c).append(",mAniStopPos=").append(d).append(",mSuccessTipPos=").append(e);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            Intent intent2 = new Intent(this, LocalScancodeActivity.class);
            intent2.setData(intent.getData());
            startActivity(intent2);
        }
    }

    public final Rect a(Point point) {
        int i = 120;
        if (point == null || this.p == null || this.q == null) {
            return null;
        }
        if (this.p.getVisibility() != 0) {
            return null;
        }
        int i2 = f;
        if (i2 >= 120) {
            if (i2 > 480) {
                i = 480;
            } else {
                i = i2;
            }
        }
        int i3 = (point.x - i) / 2;
        int a = ((point.y - i) / 2) - g.a(getApplicationContext(), 77.0f);
        new StringBuilder("Util.dip2px(52)=").append(g.a(getApplicationContext(), 77.0f));
        if (a < g.a(getApplicationContext(), 77.0f)) {
            a = g.a(getApplicationContext(), 90.0f);
        }
        Rect rect = new Rect(i3, a, i3 + i, a + i);
        LayoutParams layoutParams = this.q.getLayoutParams();
        layoutParams.width = i;
        this.q.setLayoutParams(layoutParams);
        c = rect.top - (this.q.getMeasuredHeight() / 2);
        d = i + rect.top;
        return rect;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.M = true;
        j();
        if (!o()) {
            k();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.M = false;
    }

    public final void a() {
        ViewfinderView viewfinderView = this.p;
        viewfinderView.a = null;
        viewfinderView.invalidate();
    }

    public final void a(String str, Bitmap bitmap) {
        if (!this.R) {
            this.R = true;
            if (bitmap != null) {
                com.xunlei.downloadprovider.qrcode.b.a aVar = this.O;
                if (aVar.a && aVar.b != null) {
                    aVar.b.start();
                }
                ((Vibrator) aVar.c.getSystemService("vibrator")).vibrate(Constants.ST_UPLOAD_MAX_COUNT);
            }
            if (str != null) {
                String trim = str.trim();
                if (this.u != null && this.u.isShowing()) {
                    this.u.dismiss();
                }
                this.v.setVisibility(XZBDevice.Wait);
                this.w.setVisibility(XZBDevice.Wait);
                this.D = this.C.a(com.xunlei.downloadprovider.qrcode.b.c.b(bitmap));
                this.r.setImageBitmap(this.D);
                this.r.setVisibility(0);
                if (com.google.zxing.client.a.a.d.b() != null) {
                    com.google.zxing.client.a.a.d.b().c();
                }
                l();
                this.s.setVisibility(0);
                this.q.setAnimation(null);
                this.q.setVisibility(XZBDevice.Wait);
                this.Z.obtainMessage(0, trim).sendToTarget();
                this.N |= 1;
                long uptimeMillis = SystemClock.uptimeMillis();
                int i = (int) (((double) (uptimeMillis - this.H)) / 1000.0d);
                this.H = uptimeMillis;
                if (com.xunlei.downloadprovider.model.protocol.c.a() != null && DownloadService.a() != null) {
                    StatReporter.reportPhotographRecognition(0, (long) i);
                }
            }
        }
    }

    public final Handler b() {
        return this.B;
    }

    private boolean o() {
        SharedPreferences sharedPreferences = getSharedPreferences("scancode", 0);
        return sharedPreferences != null ? sharedPreferences.getBoolean("guid", true) : true;
    }

    static /* synthetic */ void a(CameraActivity cameraActivity, String str) {
        XLBaseDialog eVar = new com.xunlei.downloadprovider.qrcode.a.e(cameraActivity, cameraActivity.Z, str);
        cameraActivity.S = eVar;
        eVar.a.setText(cameraActivity.getString(2131232268));
        eVar.c = new f(cameraActivity);
        eVar.show();
    }

    static /* synthetic */ void c(CameraActivity cameraActivity, String str) {
        new StringBuilder().append(cameraActivity.getClass()).append("---showResultView---").append(str).append("---").append(Thread.currentThread().getId());
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("from=xl7")) {
                new StringBuilder().append(cameraActivity.getClass()).append("---scanResult.contains---").append(Thread.currentThread().getId());
                try {
                    new com.xunlei.downloadprovider.xl7.a(cameraActivity.Z, Integer.valueOf(0)).a(str.replace("http://", com.umeng.a.d));
                } catch (Exception e) {
                }
            }
            XLBaseDialog aVar;
            switch (com.xunlei.downloadprovider.util.c.a.b(str)) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    cameraActivity.a(str);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (l.a(str) == null) {
                        cameraActivity.a(str);
                    } else if (com.xunlei.xllib.a.b.h(cameraActivity) || com.xunlei.xllib.a.b.g(cameraActivity)) {
                        aVar = new com.xunlei.downloadprovider.qrcode.a.a(cameraActivity, cameraActivity.Z, str);
                        cameraActivity.S = aVar;
                        aVar.show();
                        aVar.a();
                    } else {
                        aVar = new com.xunlei.downloadprovider.qrcode.a.a(cameraActivity, cameraActivity.Z, null);
                        cameraActivity.S = aVar;
                        aVar.b();
                        aVar.show();
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    aVar = new com.xunlei.downloadprovider.qrcode.a.e(cameraActivity, cameraActivity.Z, str);
                    cameraActivity.S = aVar;
                    aVar.show();
                default:
                    break;
            }
        }
    }

    static /* synthetic */ void d(CameraActivity cameraActivity, String str) {
        if (str.startsWith("fileName=")) {
            String substring;
            try {
                substring = str.substring(XZBDevice.Pause, str.indexOf(";h"));
                try {
                    str = str.substring(str.indexOf(";h") + 1, str.length());
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                substring = null;
            }
            com.xunlei.downloadprovider.businessutil.d.a a = com.xunlei.downloadprovider.businessutil.d.a(str);
            if (a == null || DownloadService.a() == null) {
                cameraActivity.a(str, substring);
                return;
            }
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(1, str, null);
            gVar.d = "manual/manual_codeScan";
            cameraActivity.createLocalTaskByGcid(a.b, a.e, a.c, a.d, null, 1, gVar, null);
            return;
        }
        cameraActivity.a(str, null);
    }

    static /* synthetic */ void b(CameraActivity cameraActivity, k kVar) {
        XLBaseDialog iVar = new i(cameraActivity, cameraActivity.Z, kVar);
        cameraActivity.S = iVar;
        iVar.show();
    }
}
