package com.xunlei.tdlive.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class BaseActivity extends FragmentActivity {
    private static HashSet<a> sGlobalAECS;
    private boolean mDestroyed;
    private boolean mEditMode;
    private boolean mFragmentActivity;
    private Handler mHandler;
    private Dialog mLoadingDialog;
    private boolean mMainActivity;
    private Handler mMsgHandler;
    private ProgressDialog mProgressDialog;
    private HashMap<Integer, Timer> mTimers;
    public View mTitleBar;
    protected View mTitleBarBackground;
    protected View mTitleBarCenter;
    protected TextView mTitleBarCenterText;
    public View mTitleBarLeft;
    public TextView mTitleBarLeftText;
    public TextView mTitleBarLeftText2;
    protected TextView mTitleBarMessage;
    public View mTitleBarRight;
    public TextView mTitleBarRightText;
    protected View mTitleMessageBar;
    private Toast mToast;

    public static interface OnCheckUpdateStateChangeListener {
        void a();

        void a(int i, String str);
    }

    public static interface a {
        void a(Activity activity);

        void b(Activity activity);

        void c(Activity activity);

        void d(Activity activity);
    }

    private class b extends TimerTask {
        private int b;

        public b(int i) {
            this.b = 0;
            this.b = i;
        }

        public void run() {
            BaseActivity.this.mHandler.obtainMessage(6535, this.b, this.b).sendToTarget();
        }
    }

    public static void attachGlobalActivityEvents(a aVar) {
        if (sGlobalAECS == null) {
            sGlobalAECS = new HashSet();
        }
        sGlobalAECS.add(aVar);
    }

    public static void detachGlobalActivityEvents(a aVar) {
        if (sGlobalAECS != null) {
            sGlobalAECS.remove(aVar);
        }
    }

    public BaseActivity() {
        this(false, false);
    }

    public BaseActivity(boolean z, boolean z2) {
        this.mTimers = new HashMap();
        this.mFragmentActivity = false;
        this.mEditMode = false;
        this.mDestroyed = false;
        this.mHandler = new e(this);
        this.mMsgHandler = new f(this);
        this.mMainActivity = z;
        this.mFragmentActivity = z2;
    }

    public void beginEditMode() {
        this.mEditMode = true;
        onEditModeChanged(this.mEditMode);
    }

    public void endEditMode() {
        this.mEditMode = false;
        onEditModeChanged(this.mEditMode);
    }

    public boolean isEditMode() {
        return this.mEditMode;
    }

    public ProgressDialog showProgressDialog(String str, String str2, boolean z) {
        try {
            if (this.mProgressDialog == null) {
                this.mProgressDialog = ProgressDialog.show(this, str, str2, true, true);
            }
            if (!(this.mProgressDialog == null || this.mProgressDialog.isShowing())) {
                this.mProgressDialog.setCanceledOnTouchOutside(z);
                this.mProgressDialog.setTitle(str);
                this.mProgressDialog.setMessage(str2);
                this.mProgressDialog.show();
            }
        } catch (Exception e) {
        }
        return this.mProgressDialog;
    }

    public void hideProgressDialog() {
        try {
            if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public Dialog showLoadingDialog(String str, boolean z) {
        try {
            if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
                this.mLoadingDialog = null;
            }
            View inflate = LayoutInflater.from(this).inflate(R.layout.xllive_loading, null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_view);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.img);
            TextView textView = (TextView) inflate.findViewById(R.id.tipTextView);
            textView.setText(str);
            if (str == null || str.length() <= 0) {
                textView.setVisibility(XZBDevice.Wait);
            }
            imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.xllive_loading_circle_ani));
            this.mLoadingDialog = new Dialog(this, R.style.loading_dialog);
            this.mLoadingDialog.setCanceledOnTouchOutside(z);
            this.mLoadingDialog.setCancelable(z);
            this.mLoadingDialog.setContentView(linearLayout);
            this.mLoadingDialog.show();
        } catch (Exception e) {
        }
        return this.mLoadingDialog;
    }

    public void hideLoadingDialog() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }

    public Toast showToast(String str) {
        return showToast(str, 0);
    }

    public Toast showToast(int i, int i2) {
        return showToast(getString(i), i2);
    }

    public Toast showToast(String str, int i) {
        if (this.mToast == null) {
            this.mToast = Toast.makeText(this, str, i);
        }
        this.mToast.setDuration(i);
        this.mToast.setText(str);
        this.mToast.show();
        return this.mToast;
    }

    public Toast showToast(int i, int i2, int i3, int i4, int i5) {
        return showToast(getString(i), i2, i3, i4, i5);
    }

    public Toast showToast(String str, int i, int i2, int i3, int i4) {
        View inflate = getLayoutInflater().inflate(i2, null);
        ((TextView) inflate.findViewById(i3)).setText(str);
        this.mToast = new Toast(this);
        this.mToast.setView(inflate);
        this.mToast.setGravity(i4, 0, 0);
        this.mToast.setDuration(i);
        this.mToast.show();
        return this.mToast;
    }

    public void hideToast() {
        if (this.mToast != null) {
            this.mToast.cancel();
        }
    }

    public long setTimer(int i, long j) {
        return setTimer(i, j, j);
    }

    public long setTimer(int i, long j, long j2) {
        killTimer(i);
        Timer timer = new Timer();
        this.mTimers.put(Integer.valueOf(i), timer);
        try {
            timer.schedule(new b(i), j, j2);
        } catch (Exception e) {
        }
        return (long) i;
    }

    public void killTimer(int i) {
        if (this.mTimers.containsKey(Integer.valueOf(i))) {
            try {
                ((Timer) this.mTimers.get(Integer.valueOf(i))).cancel();
                this.mTimers.remove(Integer.valueOf(i));
            } catch (Exception e) {
                this.mTimers.remove(Integer.valueOf(i));
            }
        }
    }

    public void post(Runnable runnable, int i) {
        if (i <= 0) {
            this.mMsgHandler.post(runnable);
        } else {
            this.mMsgHandler.postDelayed(runnable, (long) i);
        }
    }

    public void sendMessage(int i, int i2, int i3, Object obj) {
        sendMessage(this.mMsgHandler.obtainMessage(i, i2, i3, obj));
    }

    public void sendMessage(int i, int i2, int i3) {
        sendMessage(i, i2, i3, null);
    }

    public void sendMessage(Message message) {
        sendMessageDelayed(message, 0);
    }

    public void sendMessageDelayed(int i, int i2, int i3, Object obj, long j) {
        sendMessageDelayed(this.mMsgHandler.obtainMessage(i, i2, i3, obj), j);
    }

    public void sendMessageDelayed(Message message, long j) {
        if (j == 0) {
            this.mMsgHandler.sendMessage(message);
        } else {
            this.mMsgHandler.sendMessageDelayed(message, j);
        }
    }

    public void startActivity(Intent intent, int i, int i2) {
        super.startActivity(intent);
        overridePendingTransition(i, i2);
    }

    public boolean isDestroyed() {
        try {
            return super.isDestroyed();
        } catch (Throwable th) {
            return this.mDestroyed;
        }
    }

    public void finish(int i, int i2) {
        super.finish();
        overridePendingTransition(i, i2);
    }

    public void putString(String str, String str2) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(str, str2).commit();
    }

    public void putInt(String str, int i) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt(str, i).commit();
    }

    public void putFloat(String str, float f) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putFloat(str, f).commit();
    }

    public void putLong(String str, long j) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putLong(str, j).commit();
    }

    public void putBoolean(String str, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(str, z).commit();
    }

    public int getInt(String str, int i) {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(str, i);
    }

    public long getLong(String str, long j) {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(str, j);
    }

    public boolean getBoolean(String str, boolean z) {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(str, z);
    }

    public String getString(String str, String str2) {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(str, str2);
    }

    public void checkUpdate(OnCheckUpdateStateChangeListener onCheckUpdateStateChangeListener) {
        com.xunlei.tdlive.c.a.a(false);
        com.xunlei.tdlive.c.a.a(new g(this, onCheckUpdateStateChangeListener));
        if (onCheckUpdateStateChangeListener != null) {
            onCheckUpdateStateChangeListener.a();
        }
        com.xunlei.tdlive.c.a.c((Context) this);
    }

    public void setTitleBarVisible(boolean z) {
        if (tryInitTitleBar()) {
            this.mTitleBar.setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    public void setTitleBarBackgroundResource(int i) {
        if (tryInitTitleBar()) {
            try {
                this.mTitleBarBackground.setBackgroundResource(i);
            } catch (Exception e) {
            }
        }
    }

    public void setTitle(String str) {
        if (tryInitTitleBar()) {
            this.mTitleBarCenterText.setText(str);
        }
    }

    public void setLeftVisible(boolean z) {
        setLeftVisible(z, false);
    }

    public void setLeftVisible(boolean z, boolean z2) {
        int i = 0;
        if (tryInitTitleBar()) {
            int i2;
            View view = this.mTitleBarLeft;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            TextView textView = this.mTitleBarLeftText2;
            if (!z2) {
                i = 8;
            }
            textView.setVisibility(i);
        }
    }

    public void setLeftText(String str) {
        if (tryInitTitleBar()) {
            this.mTitleBarLeftText.setText(str);
        }
    }

    public void setLeftDrawable(Drawable drawable) {
        if (tryInitTitleBar()) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mTitleBarLeftText.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void setLeftClickListener(OnClickListener onClickListener) {
        if (tryInitTitleBar()) {
            this.mTitleBarLeft.setOnClickListener(onClickListener);
        }
    }

    public void setLeftTextClickListener(OnClickListener onClickListener) {
        if (tryInitTitleBar()) {
            this.mTitleBarLeftText.setOnClickListener(onClickListener);
        }
    }

    public void setLeftText2ClickListener(OnClickListener onClickListener) {
        if (tryInitTitleBar()) {
            this.mTitleBarLeftText2.setOnClickListener(onClickListener);
        }
    }

    public void setRightVisible(boolean z) {
        if (tryInitTitleBar()) {
            this.mTitleBarRight.setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    public void setRightText(String str) {
        if (tryInitTitleBar()) {
            this.mTitleBarRightText.setText(str);
        }
    }

    public void setRightDrawable(Drawable drawable) {
        if (tryInitTitleBar()) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.mTitleBarRightText.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void setRightClickListener(OnClickListener onClickListener) {
        if (tryInitTitleBar()) {
            this.mTitleBarRight.setOnClickListener(onClickListener);
        }
    }

    public void setRightLongClickListener(OnLongClickListener onLongClickListener) {
        if (tryInitTitleBar()) {
            this.mTitleBarRight.setOnLongClickListener(onLongClickListener);
        }
    }

    protected boolean tryInitTitleBar() {
        if (this.mTitleBar != null) {
            return true;
        }
        this.mTitleBar = findViewById(R.id.xllive_title_bar);
        if (this.mTitleBar != null) {
            this.mTitleBarBackground = this.mTitleBar.findViewById(R.id.background);
            this.mTitleBarLeft = this.mTitleBar.findViewById(R.id.left);
            this.mTitleBarLeftText = (TextView) this.mTitleBar.findViewById(R.id.ltext);
            this.mTitleBarLeftText2 = (TextView) this.mTitleBar.findViewById(R.id.ltext2);
            this.mTitleBarCenter = this.mTitleBar.findViewById(R.id.center);
            this.mTitleBarCenterText = (TextView) this.mTitleBar.findViewById(R.id.ctext);
            this.mTitleBarRight = this.mTitleBar.findViewById(R.id.right);
            this.mTitleBarRightText = (TextView) this.mTitleBar.findViewById(R.id.rtext);
            this.mTitleMessageBar = this.mTitleBar.findViewById(R.id.title_msg_bar);
            this.mTitleBarMessage = (TextView) this.mTitleMessageBar.findViewById(R.id.title_msg_text);
        }
        return this.mTitleBar != null;
    }

    public void setContentView(int i) {
        super.setContentView(i);
        tryInitTitleBar();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public void onCreate(Bundle bundle) {
        super.setTheme(16973837);
        super.onCreate(bundle);
        try {
            if (sGlobalAECS != null) {
                Iterator it = sGlobalAECS.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a(this);
                }
            }
        } catch (Throwable th) {
        }
        if (this.mMainActivity) {
            com.xunlei.tdlive.c.a.b(false);
            com.xunlei.tdlive.c.a.c(false);
            com.xunlei.tdlive.c.a.d(false);
            String a = com.xunlei.tdlive.c.a.a((Context) this, "silentUpdate");
            if (a != null && a.equals("true")) {
                com.xunlei.tdlive.c.a.b((Context) this);
            }
        }
    }

    public void onResume() {
        super.onResume();
        tryInitTitleBar();
        try {
            if (sGlobalAECS != null) {
                Iterator it = sGlobalAECS.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).c(this);
                }
            }
        } catch (Throwable th) {
        }
        if (!this.mFragmentActivity) {
            q.b(getClass().getSimpleName());
        }
        q.a();
    }

    public void onPause() {
        super.onPause();
        if (!this.mFragmentActivity) {
            q.c(getClass().getSimpleName());
        }
        q.b();
        try {
            if (sGlobalAECS != null) {
                Iterator it = sGlobalAECS.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).d(this);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void onDestroy() {
        Iterator it;
        hideProgressDialog();
        hideLoadingDialog();
        super.onDestroy();
        for (Integer num : this.mTimers.keySet()) {
            try {
                ((Timer) this.mTimers.get(num)).cancel();
            } catch (Exception e) {
            }
        }
        this.mTimers.clear();
        this.mDestroyed = true;
        try {
            if (sGlobalAECS != null) {
                it = sGlobalAECS.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).b(this);
                }
            }
        } catch (Throwable th) {
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!isEditMode()) {
            return super.onKeyDown(i, keyEvent);
        }
        endEditMode();
        return true;
    }

    protected void onResumeFragments() {
        try {
            super.onResumeFragments();
        } catch (Exception e) {
        }
    }

    public void onTimer(int i) {
    }

    protected void onMessage(Message message) {
    }

    public void onEditModeChanged(boolean z) {
    }
}
