package com.xunlei.downloadprovider.frame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager.BadTokenException;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.open.yyb.AppbarJsBridge;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.BrothersApplication.d;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivityFragment;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.frame.MainTabSpec.Tab;
import com.xunlei.downloadprovider.frame.user.bn;
import com.xunlei.downloadprovider.frame.view.XLTabLayout;
import com.xunlei.downloadprovider.frame.view.XLTabView;
import com.xunlei.downloadprovider.homepage.interest.a.j;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.model.protocol.networkcheck.IPAddressErrorActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.util.f;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovider.xl7.XL7AccelerateDialogActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class MainTabActivity extends ThunderTask implements com.xunlei.downloadprovider.discovery.kuainiao.e.a, c, com.xunlei.downloadprovider.util.v.a {
    public static final String a;
    public static boolean b;
    public BaseFragment c;
    public LinearLayout d;
    public TextView e;
    public ArrayList<b> f;
    private FrameLayout g;
    private XLTabLayout h;
    private int i;
    private ae j;
    private Handler k;
    private d l;
    private g m;
    private p n;
    private LoginHelper.d o;
    private boolean p;
    private String q;
    private String r;
    private String s;
    private com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a t;
    private String u;
    private com.xunlei.downloadprovider.util.d v;

    class a extends Handler {
        private SoftReference<MainTabActivity> b;

        public a(SoftReference<MainTabActivity> softReference) {
            this.b = softReference;
        }

        public final void handleMessage(Message message) {
            if (((MainTabActivity) this.b.get()) != null) {
                switch (message.what) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        MainTabActivity.this.h.a((int) XZBDevice.DOWNLOAD_LIST_FAILED).a(MainTabActivity.this.getString(2131231704)).a(2130838641);
                        MainTabActivity.this = false;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        MainTabActivity.this.h.a((int) XZBDevice.DOWNLOAD_LIST_FAILED).a(MainTabActivity.this.getString(2131231703)).a(2130838644);
                        MainTabActivity.this = true;
                    case 3103:
                        if (message.arg1 == 0 && (message.obj instanceof com.xunlei.downloadprovider.model.protocol.c.c.a)) {
                            Object obj = message.obj;
                            MainTabActivity.c();
                        }
                    default:
                        break;
                }
            }
        }
    }

    public static interface b {
        boolean a(MotionEvent motionEvent);
    }

    public MainTabActivity() {
        this.i = 2131756477;
        this.p = false;
        this.t = new g(this);
        this.f = new ArrayList(10);
    }

    static /* synthetic */ void c() {
    }

    static {
        a = MainTabActivity.class.getSimpleName();
        b = false;
    }

    public static void a(Context context, String str) {
        a(context, str, null, false);
    }

    public static void a(Context context, String str, Bundle bundle, boolean z) {
        if (context != null && str != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("tab_tag", str);
            Intent intent = new Intent(context, MainTabActivity.class);
            intent.putExtras(bundle);
            if (z) {
                intent.addFlags(WXMediaMessage.THUMB_LENGTH_LIMIT);
            }
            context.startActivity(intent);
        }
    }

    public static void a(Activity activity) {
        a(activity, "thunder", new Bundle(), false);
    }

    public static void a(Activity activity, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("download_url", str);
        bundle.putString("download_report", str2);
        a(activity, "thunder", bundle, false);
    }

    public static void a(Activity activity, String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("download_url", str);
        bundle.putString("download_report", str4);
        bundle.putString("download_title", str2);
        bundle.putString("download_icon_url", str3);
        a(activity, "thunder", bundle, false);
    }

    protected void onCreate(Bundle bundle) {
        new StringBuilder("launch consume time: ").append(com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().b());
        if (com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().b) {
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.b("adv_launch_time", com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().b(), MessageService.MSG_DB_READY_REPORT);
        } else {
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.b("adv_launch_time", com.xunlei.downloadprovider.frame.advertisement.b.a.a.a().b(), MessageService.MSG_DB_NOTIFY_REACHED);
        }
        super.onCreate(bundle);
        getWindow().setFormat(AppbarJsBridge.Code_Java_Exception);
        aa.a((Context) this, "no_first_enter_thunder", true);
        if (com.xunlei.downloadprovider.a.b.g().equals("0x10810144") && !aa.c(this, "is_xl7_report_success")) {
            startActivity(new Intent(this, XL7AccelerateDialogActivity.class));
        }
        com.xunlei.downloadprovider.i.a.c.a = false;
        setContentView(2130968864);
        this.v = new com.xunlei.downloadprovider.util.d(this, new r(this));
        this.g = (FrameLayout) findViewById(2131755283);
        this.k = new a(new SoftReference(this));
        e();
        this.d = (LinearLayout) findViewById(2131756480);
        this.e = (TextView) this.d.findViewById(2131757136);
        this.d.findViewById(2131756279).setOnClickListener(new ac(this));
        this.l = new i(this);
        BrothersApplication.a().a(this.l);
        this.o = new j(this);
        this.n = new k(this);
        this.m = new l(this);
        LoginHelper.a().a(this.o);
        LoginHelper.a().a(this.n);
        LoginHelper.a().a(this.m);
        e a = e.a();
        com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a aVar = this.t;
        if (a.a == null) {
            a.a = new ArrayList();
        }
        if (!com.xunlei.xllib.b.d.a(a.a)) {
            a.a.clear();
        }
        a.a.add(aVar);
        b(getIntent());
        this.k.postDelayed(new x(this), (long) (((int) (Math.random() * 5000.0d)) + 5000));
        if (!com.xunlei.xllib.a.b.a(this)) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            if (com.xunlei.downloadprovider.service.downloads.task.d.l() > 0) {
                com.xunlei.downloadprovider.commonview.dialog.d dVar = new com.xunlei.downloadprovider.commonview.dialog.d(this);
                dVar.a(getString(2131231462));
                dVar.c(getString(2131231461));
                dVar.d(getString(2131231463));
                dVar.a(new u(this));
                dVar.b(new v(this));
                dVar.show();
                StatReporter.reportNoNetWorkTip();
            } else if (!com.xunlei.xllib.a.b.a(this)) {
                Toast toast = new Toast(this);
                View inflate = LayoutInflater.from(this).inflate(2130968875, null);
                toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
                toast.setView(inflate);
                toast.setDuration(0);
                toast.show();
            }
        }
        LoginHelper.a();
        if (LoginHelper.c()) {
            com.xunlei.downloadprovider.model.protocol.report.b.a(LoginHelper.a().j);
        }
        LoginHelper.a();
        if (LoginHelper.c()) {
            if (!(com.xunlei.downloadprovider.frame.user.a.b.a((Context) this).d(String.valueOf(LoginHelper.a().j)) || com.xunlei.downloadprovider.homepage.a.a.d.b == null || !com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(Constants.VIA_SHARE_TYPE_TEXT) || ((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(Constants.VIA_SHARE_TYPE_TEXT)) == null)) {
                b(true);
            }
        }
        LoginHelper.a().a(new w(this));
        new Handler().postDelayed(new y(this), 2000);
        v.a().a((com.xunlei.downloadprovider.util.v.a) this);
        com.xunlei.downloadprovider.discovery.kuainiao.e.a().a(this);
        j jVar = new j();
        if (!jVar.b() && jVar.a.getBoolean("key_is_showing", false)) {
            int a2 = jVar.a();
            if (a2 <= 10) {
                jVar.a.edit().putInt("key_show_time", a2 + 1).apply();
            }
        }
        overridePendingTransition(R.anim.translate_between_interface_right_in, R.anim.translate_between_interface_left_out);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        BaseFragment b = this.j.b("thunder");
        if (b instanceof BaseViewPagerFragment) {
            BasePageFragment a = ((BaseViewPagerFragment) b).a();
            if (a != null) {
                a.onFullScreenChange(true);
            }
        }
    }

    protected void onResume() {
        super.onResume();
        DownloadCenterActivityFragment.a = false;
        b = true;
        this.u = this.v.b();
        if (!com.xunlei.downloadprovider.util.d.a(this.u) && com.xunlei.downloadprovider.util.d.a()) {
            com.xunlei.downloadprovider.util.d.b(this.u);
            com.xunlei.downloadprovider.util.d dVar = this.v;
            Object obj = this.u;
            if (!TextUtils.isEmpty(obj)) {
                Context context = dVar.a;
                if (dVar.b == null) {
                    dVar.b = new XLAlarmDialog(context);
                }
                OnClickListener eVar = new com.xunlei.downloadprovider.util.e(dVar, context, obj);
                OnClickListener fVar = new f(dVar);
                dVar.b.setTitle(context.getString(2131231124));
                dVar.b.setContent(context.getString(2131231123));
                dVar.b.setOnClickCancelButtonListener(fVar);
                dVar.b.setOnClickConfirmButtonListener(eVar);
                if (!dVar.b.isShowing()) {
                    try {
                        dVar.b.show();
                    } catch (BadTokenException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e2) {
                        e2.printStackTrace();
                    }
                }
                StatReporter.reportShowDialogForCreateTaskFromClipBoard();
            }
        }
        if (IPAddressErrorActivity.c()) {
            IPAddressErrorActivity.b();
            return;
        }
        if (DownloadService.a() == null) {
            DownloadService.a((c) this);
        } else {
            a(DownloadService.a());
        }
        com.xunlei.downloadprovider.discovery.kuainiao.e.a();
        com.xunlei.downloadprovider.discovery.kuainiao.e.c();
        if (getWindow().getDecorView().getSystemUiVisibility() == 4096) {
            getWindow().getDecorView().setSystemUiVisibility(4102);
        }
        a();
        LoginHelper a = LoginHelper.a();
        if (a.C) {
            a.a(1);
        }
    }

    protected void onPause() {
        super.onPause();
        com.xunlei.downloadprovider.util.d.a(false);
        b = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            BrothersApplication.a().b(this.l);
            this.l = null;
        }
        LoginHelper.a().b(this.o);
        LoginHelper.a().b(this.n);
        LoginHelper.a().b(this.m);
        ae aeVar = this.j;
        if (aeVar.a != null) {
            aeVar.a.clear();
        }
        aeVar.a = null;
        v.a().b((com.xunlei.downloadprovider.util.v.a) this);
        com.xunlei.downloadprovider.discovery.kuainiao.e.a().b(this);
    }

    public final void a(v vVar) {
        runOnUiThread(new z(this));
    }

    public final void a() {
        runOnUiThread(new aa(this));
    }

    public final void a(int i, XLAccelBandInfo xLAccelBandInfo) {
        boolean z = i == 0 && xLAccelBandInfo != null;
        runOnUiThread(new ab(this, z));
    }

    private boolean d() {
        return getSharedPreferences("first_Get_kuaiNiao_Acc_Info", 0).getBoolean("firstGetKuaiNiaoAccInfo", true);
    }

    public final void a(int i, int i2, KnParams knParams) {
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        if (!this.mIsRunningOnForeground) {
            com.xunlei.downloadprovider.util.d.a(true);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (!((this.c != null && this.c.onBackPressed()) || q.a().b() || TextUtils.isEmpty(this.h.getCurrentTag()))) {
                    if (this.h.getCurrentTag().equals("thunder")) {
                        com.xunlei.downloadprovider.service.downloads.task.d.a();
                        boolean c = com.xunlei.downloadprovider.service.downloads.task.d.c();
                        LoginHelper.a();
                        this.q = LoginHelper.c() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
                        this.r = com.xunlei.downloadprovider.frame.user.a.b.a((Context) this).f(new StringBuilder().append(LoginHelper.a().j).toString()).b ? MessageService.MSG_DB_READY_REPORT : MessageService.MSG_DB_NOTIFY_REACHED;
                        this.s = c ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
                        CharSequence string;
                        CharSequence string2;
                        CharSequence string3;
                        CharSequence string4;
                        XLAlarmDialog xLAlarmDialog;
                        if (c) {
                            StatReporter.reportAppExitShow(this.q, MessageService.MSG_DB_READY_REPORT, this.s);
                            string = getString(2131232071);
                            string2 = getString(2131232064);
                            string3 = getString(2131232067);
                            string4 = getString(2131232069);
                            xLAlarmDialog = new XLAlarmDialog(this);
                            xLAlarmDialog.mContentCheckbox.setVisibility(0);
                            xLAlarmDialog.setTitle(string);
                            xLAlarmDialog.setContent(string2);
                            xLAlarmDialog.mLeftBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.mRightBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.setCancelButtonText(string3);
                            xLAlarmDialog.setOnClickCancelButtonListener(new s(this, xLAlarmDialog));
                            xLAlarmDialog.setConfirmButtonText(string4);
                            xLAlarmDialog.setOnClickConfirmButtonListener(new t(this, xLAlarmDialog));
                            xLAlarmDialog.show();
                        } else if (com.xunlei.downloadprovider.frame.user.a.b.a((Context) this).f(LoginHelper.a().j).b || aa.c(this, com.xunlei.downloadprovider.frame.user.a.b.b())) {
                            StatReporter.reportAppExitShow(this.q, MessageService.MSG_DB_READY_REPORT, this.s);
                            string = getString(2131232071);
                            string2 = getString(2131232063);
                            string3 = getString(2131232067);
                            string4 = getString(2131232069);
                            xLAlarmDialog = new XLAlarmDialog(this);
                            xLAlarmDialog.setTitle(string);
                            xLAlarmDialog.setContent(string2);
                            xLAlarmDialog.mLeftBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.mRightBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.setCancelButtonText(string3);
                            xLAlarmDialog.setOnClickCancelButtonListener(new p(this, xLAlarmDialog));
                            xLAlarmDialog.setConfirmButtonText(string4);
                            xLAlarmDialog.setOnClickConfirmButtonListener(new q(this, xLAlarmDialog));
                            xLAlarmDialog.show();
                        } else {
                            StatReporter.reportAppExitShow(this.q, this.r, this.s);
                            string = getString(2131232072);
                            string2 = getString(2131232065);
                            string3 = getString(2131232068);
                            string4 = getString(2131232070);
                            xLAlarmDialog = new XLAlarmDialog(this);
                            xLAlarmDialog.mLeftBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.mRightBtn.setTextColor(Color.parseColor("#4f5158"));
                            xLAlarmDialog.mContentRight.setVisibility(0);
                            xLAlarmDialog.mContentRight.setOnClickListener(new m(this, xLAlarmDialog));
                            xLAlarmDialog.setTitle(string);
                            xLAlarmDialog.setContent(string2);
                            xLAlarmDialog.setCancelButtonText(string3);
                            xLAlarmDialog.setOnClickCancelButtonListener(new n(this, xLAlarmDialog));
                            xLAlarmDialog.setConfirmButtonText(string4);
                            xLAlarmDialog.setOnClickConfirmButtonListener(new o(this, xLAlarmDialog));
                            xLAlarmDialog.show();
                        }
                    } else {
                        a("thunder");
                    }
                }
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public final void b() {
        if (this.d != null) {
            this.d.setVisibility(XZBDevice.Wait);
        }
    }

    private void e() {
        this.j = new ae();
        this.h = (XLTabLayout) findViewById(2131756479);
        this.h.setOnTabChangeListener(new ad(this));
        this.h.setOnClickListener(new h(this));
        int length = MainTabSpec.a.length;
        int t = com.xunlei.downloadprovider.a.b.t() / length;
        for (int i = 0; i < length; i++) {
            Object obj;
            LayoutParams layoutParams;
            Tab tab = MainTabSpec.a[i];
            View xLTabView = new XLTabView(this);
            if (tab.equals(Tab.USER)) {
                LoginHelper.a();
                if (!LoginHelper.d()) {
                    xLTabView.a = tab.getTag();
                    xLTabView.a(getString(2131231704)).a(2130838641);
                    bn.a().a = false;
                    obj = this.h;
                    layoutParams = new LinearLayout.LayoutParams(t, -1);
                    layoutParams.gravity = 17;
                    xLTabView.setOnClickListener(obj);
                    obj.addView(xLTabView, layoutParams);
                    b(i, xLTabView);
                }
            }
            xLTabView.a = tab.getTag();
            xLTabView.a(getString(tab.getText())).a(tab.getIcon());
            bn.a().a = true;
            obj = this.h;
            layoutParams = new LinearLayout.LayoutParams(t, -1);
            layoutParams.gravity = 17;
            xLTabView.setOnClickListener(obj);
            obj.addView(xLTabView, layoutParams);
            b(i, xLTabView);
        }
    }

    private static void b(int i, XLTabView xLTabView) {
        boolean b;
        boolean z = true;
        v a = v.a();
        if (i == 0) {
            b = a.b("recommend");
        } else {
            b = false;
        }
        if (i == 1) {
            b = a.b("search");
        }
        if (i == 2) {
            b = a.b("find");
        }
        if (i != 3) {
            z = b;
        } else if (!a.b("user_center")) {
            e.a();
            if (!e.f() || XZBShouleiUtil.getInstance().getDefaultDevice() == null) {
                z = false;
            }
        }
        if (z) {
            xLTabView.setPointVisible(0);
        } else {
            xLTabView.setPointVisible(XZBDevice.Wait);
        }
        new StringBuilder(" index ").append(i).append(" needShow ").append(z);
    }

    public final void a(DownloadService downloadService) {
        if (!this.p) {
            this.p = true;
            a(getIntent());
        }
        DownloadService.a().b(this.k);
    }

    private boolean a(Intent intent) {
        if (!(intent instanceof Intent) || DownloadService.a() == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra("download_url");
        if (stringExtra == null) {
            return false;
        }
        com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(3, stringExtra, null);
        gVar.d = intent.getStringExtra("download_report");
        if (intent.hasExtra("download_icon_url")) {
            String stringExtra2 = intent.getStringExtra("download_title");
            String stringExtra3 = intent.getStringExtra("download_icon_url");
            com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
            bVar.c = stringExtra3;
            bVar.e = stringExtra2;
            bVar.d = true;
            createLocalTaskWithAdditionInfo(stringExtra, stringExtra2, 0, null, null, null, 0, gVar, null, false, bVar);
        } else {
            createLocalTask(stringExtra, null, 0, null, null, null, 0, gVar, null, false);
        }
        StatReporter.reportOverallDownload("guanggao");
        return true;
    }

    private void b(Intent intent) {
        if (intent != null) {
            if (intent.getData() == null) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    if (intent != null && intent.getBooleanExtra("key_is_from_notification", false)) {
                        Bundle extras2 = intent.getExtras();
                        StatReporter.reportPushResClick(extras2.getString("key_notification_tag"), extras2.getInt("key_push_type", -1));
                    }
                    String string = extras.getString("tab_tag");
                    if (string != null) {
                        a(string);
                        return;
                    }
                    return;
                }
                return;
            }
            a("thunder");
        }
    }

    public final void a(String str) {
        if (str.equals("find") && d() && com.xunlei.downloadprovider.discovery.kuainiao.e.a().a) {
            Editor edit = getSharedPreferences("first_Get_kuaiNiao_Acc_Info", 0).edit();
            edit.putBoolean("firstGetKuaiNiaoAccInfo", false);
            edit.commit();
        }
        BaseFragment a = XLTabLayout.a(this.i, getSupportFragmentManager(), this.j, str);
        if (a != null) {
            this.c = a;
            this.h.setSelection(str);
        }
    }

    protected void onCreateTask(boolean z, int i) {
    }

    public final void a(String str, int i) {
        this.h.a(str).setPointVisible(i);
    }

    public final void a(boolean z) {
        if (z) {
            a("find", 0);
        } else {
            a("find", (int) XZBDevice.Wait);
        }
    }

    public final void b(boolean z) {
        if (z) {
            a("user", 0);
        } else {
            a("user", (int) XZBDevice.Wait);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.xunlei.downloadprovidershare.d.b().a(i, i2, intent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!(this.h == null || this.h.getCurrentTag() == null || !this.h.getCurrentTag().equals("thunder"))) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(motionEvent);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onNewIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.getBooleanExtra("exit", false)) {
            exit();
            return;
        }
        a(intent);
        b(intent);
    }

    static /* synthetic */ boolean d(MainTabActivity mainTabActivity) {
        return mainTabActivity.h.getCurrentTabView() == mainTabActivity.h.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
