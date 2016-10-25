package com.xunlei.downloadprovider.app;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.xunlei.downloadprovider.a.a.b;
import com.xunlei.downloadprovider.app.ui.c;
import com.xunlei.downloadprovider.app.ui.d;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.frame.BaseFragmentActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.user.PersonalSpaceActivity;
import com.xunlei.downloadprovider.frame.user.ReportActivity;
import com.xunlei.downloadprovider.frame.user.account.ui.UserAccountPortraitSettingActivity;
import com.xunlei.downloadprovider.launch.LaunchActivity;
import com.xunlei.downloadprovider.launch.a.a;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;
import com.xunlei.downloadprovider.thirdpart.ThirdPartActivity;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.RemoteDownloadListActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends BaseFragmentActivity {
    public static final int REQUESTCODE_REQUIRED_PERMISSIONS_FOR_LAUNCH = 80000;
    private final String TAG;
    public boolean mIsRunningOnForeground;
    a mRequiredPermissionDialog;
    private c mSystemBarTintManager;
    private Toast mToast;
    private q mXLOneBtnHintDialog;
    public com.nostra13.universalimageloader.core.c options;

    public BaseActivity() {
        this.mRequiredPermissionDialog = null;
        this.TAG = BaseActivity.class.getSimpleName();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initWindowAppearance();
        new StringBuilder("onCreate:").append(this).append("--").append(getTaskId());
        BrothersApplication.a().g.push(new WeakReference(this));
        if (!(this instanceof LaunchActivity) && !(this instanceof ThirdPartActivity)) {
            this.options = initDisplayOption();
            umengPushOnStart();
            handleIntent(getIntent());
        }
    }

    private void handleIntent(Intent intent) {
        Uri data = intent.getData();
        new StringBuilder("Uri:  ").append(data);
        if (data != null) {
            String path = data.getPath();
            if ("/hotResource".equals(path) || "/resourceDetail".equals(path)) {
                String queryParameter = data.getQueryParameter("h5Type");
                g gVar = new g();
                gVar.a = "android_forground";
                gVar.b = "forground_h5";
                gVar.c = "forground_h5";
                String str = "h5_type";
                if (queryParameter == null) {
                    queryParameter = "h5_type";
                }
                gVar.a(str, queryParameter, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                ThunderReporter.a(gVar, true);
            }
        }
    }

    private void umengPushOnStart() {
        PushAgent.getInstance(getApplicationContext()).onAppStart();
    }

    private void initWindowAppearance() {
        if (shouldApplySystemStatusBarTint()) {
            setStatusBarBackgroundColorResource(2131689507);
        }
    }

    public void setStatusBarBackgroundColorResource(int i) {
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
            getWindow().addFlags(67108864);
            if (this.mSystemBarTintManager == null) {
                this.mSystemBarTintManager = new c(this);
            }
            c cVar = this.mSystemBarTintManager;
            int color = getResources().getColor(i);
            if (cVar.a) {
                if (cVar.d != null) {
                    cVar.d.setVisibility(XZBDevice.Wait);
                }
                cVar.c.setVisibility(0);
                cVar.c.setBackgroundColor(color);
            }
            cVar = this.mSystemBarTintManager;
            cVar.b = true;
            if (cVar.a) {
                cVar.c.setVisibility(0);
            }
        }
    }

    public void performSystemBarAlphaAnimation(boolean z) {
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21 && this.mSystemBarTintManager != null) {
            c cVar = this.mSystemBarTintManager;
            if (cVar.d != null) {
                Animation loadAnimation;
                cVar.d.setVisibility(0);
                if (cVar.e != null) {
                    cVar.e.setVisibility(XZBDevice.Wait);
                }
                if (z) {
                    loadAnimation = AnimationUtils.loadAnimation(BrothersApplication.a(), 2131034126);
                } else {
                    loadAnimation = AnimationUtils.loadAnimation(BrothersApplication.a(), 2131034127);
                }
                loadAnimation.setDuration(300);
                loadAnimation.setAnimationListener(new d(cVar, z));
                cVar.d.startAnimation(loadAnimation);
            }
        }
    }

    @Deprecated
    public void setStatusBarBgColr(int i) {
        if (isIncludeActivity(this)) {
            setStatusBarBackgroundColorResource(i);
        }
    }

    public void animationBarAlpha(boolean z) {
        if (shouldApplySystemStatusBarTint()) {
            performSystemBarAlphaAnimation(z);
        }
    }

    public void setContentView(int i) {
        if (VERSION.SDK_INT < 19 || !shouldApplySystemStatusBarTint()) {
            super.setContentView(i);
            return;
        }
        View inflate = getLayoutInflater().inflate(i, null);
        if (inflate != null) {
            inflate.setFitsSystemWindows(true);
            super.setContentView(inflate);
        }
    }

    private boolean isIncludeActivity(BaseActivity baseActivity) {
        return (baseActivity instanceof MainTabActivity) || (baseActivity instanceof DownloadCenterActivity) || (baseActivity instanceof RemoteDownloadListActivity) || (baseActivity instanceof SearchActivity) || (baseActivity instanceof DownloadBtFileExplorerActivity) || (baseActivity instanceof UserAccountPortraitSettingActivity) || (baseActivity instanceof ReportActivity) || (baseActivity instanceof PersonalSpaceActivity);
    }

    public boolean shouldApplySystemStatusBarTint() {
        return isIncludeActivity(this);
    }

    public static com.nostra13.universalimageloader.core.c initDisplayOption() {
        com.nostra13.universalimageloader.core.c.a aVar = new com.nostra13.universalimageloader.core.c.a();
        aVar.a = 2130837804;
        aVar.b = 2130837804;
        aVar.c = 2130837804;
        aVar.m = true;
        aVar.h = true;
        aVar.a();
        return aVar.b();
    }

    public void onResume() {
        new StringBuilder("onResume:").append(this);
        super.onResume();
        this.mIsRunningOnForeground = true;
        if (BrothersApplication.h) {
            logOutNotByUser(null);
        }
        StatReporter.reportActivityResume(this);
        updateOnForeground();
        BrothersApplication.a().n = this;
    }

    private void updateOnForeground() {
        boolean z = BrothersApplication.a().i;
        boolean g = BrothersApplication.a().g();
        new StringBuilder("hubble lastForeground:").append(z).append(" nowForeground:").append(g);
        if (z != g) {
            BrothersApplication.a().i = g;
            if (g) {
                StatReporter.reportForeground();
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (!isAppRunningOnForeground()) {
            this.mIsRunningOnForeground = false;
        }
        updateOnForeground();
    }

    private boolean isAppRunningOnForeground() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(getPackageName())) {
                if (runningAppProcessInfo.importance == 100) {
                    new StringBuilder("\u5904\u4e8e\u524d\u53f0").append(runningAppProcessInfo.processName);
                    return true;
                }
                new StringBuilder("\u5904\u4e8e\u540e\u53f0").append(runningAppProcessInfo.processName);
                return false;
            }
        }
        return false;
    }

    public void logOutNotByUser(OnClickListener onClickListener) {
        q qVar = new q(this);
        qVar.a(getString(2131230804));
        qVar.d(getString(R.string.gotit));
        if (onClickListener != null) {
            qVar.b(onClickListener);
        }
        b.a((Context) this).a(100102);
        qVar.show();
        BrothersApplication.h = false;
    }

    public void onPause() {
        new StringBuilder("onPause:").append(this);
        if (this.mXLOneBtnHintDialog != null) {
            this.mXLOneBtnHintDialog = null;
        }
        super.onPause();
        StatReporter.reportActivityPause(this);
    }

    public void popupOneBtnDialog(String str, OnClickListener onClickListener) {
        if (this.mXLOneBtnHintDialog == null) {
            this.mXLOneBtnHintDialog = new q(this);
        }
        this.mXLOneBtnHintDialog.a(str);
        if (onClickListener != null) {
            this.mXLOneBtnHintDialog.b(onClickListener);
        }
        this.mXLOneBtnHintDialog.show();
    }

    public void onDestroy() {
        new StringBuilder("onDestroy:").append(this);
        BrothersApplication a = BrothersApplication.a();
        if (!a.g.empty() && ((WeakReference) a.g.peek()).get() == this) {
            a.g.pop();
        }
        super.onDestroy();
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    public void exit() {
        finish();
        BrothersApplication.a();
        BrothersApplication.b();
    }

    public void showToast(String str) {
        if (this.mToast != null) {
            this.mToast.cancel();
        }
        this.mToast = Toast.makeText(this, str, 0);
        this.mToast.show();
    }

    public void showToast(int i) {
        showToast(getString(i));
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Object obj = null;
        if (80000 == i) {
            int i2;
            if (iArr.length > 0) {
                int length = iArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    if (iArr[i3] == -1) {
                        i2 = -1;
                    }
                }
            }
            if (i2 == -1) {
                i2 = requestRequiredPermissionsForLaunch();
            }
            if (i2 == 0) {
                onRequiredPermissionsForLaunchReady();
            }
        }
    }

    public static int checkRequiredPermissionsForLaunch(Context context) {
        return 0;
    }

    public int requestRequiredPermissionsForLaunch() {
        return 0;
    }

    public static int checkRequiredPermissionsForLaunchImpl(Context context) {
        if (VERSION.SDK_INT >= 23) {
            String[] strArr = new String[]{MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_READ_PHONE_STATE};
            for (int i = 0; i < 2; i++) {
                if (context.checkSelfPermission(strArr[i]) == -1) {
                    return -1;
                }
            }
        }
        return 0;
    }

    protected int requestRequiredPermissionsForLaunchImpl() {
        int i = 0;
        if (this.mRequiredPermissionDialog != null && this.mRequiredPermissionDialog.isShowing()) {
            this.mRequiredPermissionDialog.dismiss();
            this.mRequiredPermissionDialog = null;
        }
        if (VERSION.SDK_INT < 23) {
            return 0;
        }
        String[] strArr = new String[]{MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_READ_PHONE_STATE};
        ArrayList arrayList = new ArrayList(2);
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2) {
            int i4;
            String str = strArr[i2];
            if (checkSelfPermission(str) == -1) {
                arrayList.add(str);
                i4 = -1;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        if (arrayList.isEmpty()) {
            return i3;
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        i2 = strArr2.length;
        int i5 = 0;
        while (i < i2) {
            if (shouldShowRequestPermissionRationale(strArr2[i])) {
                i5++;
            }
            i++;
        }
        if (i5 > 0) {
            this.mRequiredPermissionDialog = new a(this, new a(this, arrayList));
            this.mRequiredPermissionDialog.a(new b(this, strArr2));
            this.mRequiredPermissionDialog.show();
            return i3;
        }
        this.mRequiredPermissionDialog = new a(this, new c(this, arrayList));
        this.mRequiredPermissionDialog.a(new d(this, strArr2));
        this.mRequiredPermissionDialog.show();
        return i3;
    }

    public void onRequiredPermissionsForLaunchReady() {
    }
}
