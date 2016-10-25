package com.xunlei.tdlive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.xunlei.c.a.b;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.homepage.HomeFragment;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.sdk.HttpRequestCallback;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.XLog;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XLLiveFragment extends BasePageFragment {
    private static final int CHECK_RED_FLAG_INTERVAL = 10000;
    private static final String TAG = "XLLiveFragment";
    private static Activity mHostActivity;
    private FrameLayout mContainerView;
    private ErrorView mErrorView;
    private Handler mHandler;
    private boolean mIsActive;
    private boolean mRedFlagFirstStart;
    private BroadcastReceiver mRedFlagReceiver;
    private boolean mShow;
    private Timer mTimer;
    private View mView;

    class AnonymousClass_4 extends TimerTask {
        final /* synthetic */ long val$lastQuery;
        final /* synthetic */ SharedPreferences val$prefs;

        AnonymousClass_4(SharedPreferences sharedPreferences, long j) {
            this.val$prefs = sharedPreferences;
            this.val$lastQuery = j;
        }

        public void run() {
            long j = this.val$prefs.getLong("red_flag_next_query", 0);
            long elapsedRealtime = SystemClock.elapsedRealtime() - j;
            if (this.val$lastQuery == 0 || elapsedRealtime >= 0) {
                XLLiveSDK.getInstance(XLLiveFragment.this.getActivity()).queryRedflagInfo(XLLiveFragment.this.getActivity(), this.val$lastQuery, new RedFlagQueryCallback(XLLiveFragment.this));
            }
            XLog.d("redflag", new StringBuilder("do check task() deta = ").append(elapsedRealtime).append(", next = ").append(j).toString());
        }
    }

    private static final class RedFlagQueryCallback implements HttpRequestCallback {
        private WeakReference<XLLiveFragment> mContainerFragment;

        public RedFlagQueryCallback(XLLiveFragment xLLiveFragment) {
            this.mContainerFragment = new WeakReference(xLLiveFragment);
        }

        public final void onResponse(int i, String str, String str2) {
            XLLiveFragment xLLiveFragment = (XLLiveFragment) this.mContainerFragment.get();
            if (xLLiveFragment != null && i == 0 && !TextUtils.isEmpty(str2)) {
                xLLiveFragment.onQueryResult(str2);
            }
        }
    }

    public static Activity getHostMainActivity() {
        return mHostActivity;
    }

    public XLLiveFragment() {
        this.mRedFlagFirstStart = true;
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        mHostActivity = getActivity();
        this.mContainerView = new FrameLayout(getActivity());
        this.mContainerView.setId(16908290);
        this.mErrorView = new ErrorView(getActivity());
        this.mErrorView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.mErrorView.setActionButtonListener(new OnClickListener() {
            public void onClick(View view) {
                XLLiveFragment.this.mErrorView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (XLLiveFragment.this.mHandler != null) {
                    XLLiveFragment.this.mHandler.sendEmptyMessage(ChatMessage.FLAG_SYS_NOTIFY);
                }
            }
        });
        this.mErrorView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        View frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        frameLayout.addView(this.mContainerView, -1, -1);
        frameLayout.addView(this.mErrorView, -1, -1);
        return frameLayout;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        XLLiveSDK.getInstance(BrothersApplication.a()).appOnDesk();
        if (this.mView != view) {
            this.mView = view;
            try {
                getChildFragmentManager().beginTransaction().replace(16908290, XLLiveSDK.getInstance(BrothersApplication.a()).newLiveListFragment(new Handler() {
                    public void handleMessage(Message message) {
                        if (message.what == 2000) {
                            XLLiveFragment.this.mHandler = (Handler) message.obj;
                            if (XLLiveFragment.this.mShow) {
                                XLLiveFragment.this.onPageSelected();
                            }
                        } else if (message.what == 2001) {
                            boolean a = b.a(BrothersApplication.a());
                            XLLiveFragment.this.updateErrorViewVisibility(a, message.arg1);
                            if (message.arg1 > 0 && !a) {
                                XLToast.b(BrothersApplication.a(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
                            }
                        } else if (message.what == 2002) {
                            XLToast.b(BrothersApplication.a(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
                        }
                    }
                })).commit();
            } catch (Throwable th) {
            }
        }
    }

    public void onPageSelected() {
        super.onPageSelected();
        this.mShow = true;
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(XLPayErrorCode.XLP_GATE_PARAM_ERROR);
        }
        XLLiveSDK.getInstance(getContext()).managePushTag(getContext());
        this.mIsActive = true;
        endTimer();
        ChoicenessReporter.a(v.a().b("livestream"));
    }

    public void onPageOff() {
        super.onPageOff();
        this.mShow = false;
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(XLPayErrorCode.XLP_GATE_GEN_ERROR);
        }
        this.mIsActive = false;
        startTimer();
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsActive) {
            startTimer();
        }
    }

    public void onPause() {
        super.onPause();
        if (!this.mIsActive) {
            endTimer();
        }
    }

    public void onMainTabClick(boolean z) {
        super.onMainTabClick(z);
        if (!z && this.mHandler != null) {
            this.mHandler.sendEmptyMessage(ChatMessage.FLAG_SYS_NOTIFY);
        }
    }

    private void updateErrorViewVisibility(boolean z, int i) {
        if (i <= 0) {
            if (z) {
                this.mErrorView.setErrorType(0);
            } else {
                this.mErrorView.setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
            }
            this.mErrorView.setVisibility(0);
            return;
        }
        this.mErrorView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    private void startTimer() {
        long j = 10000;
        if (XLUserUtil.getInstance().userIsOnline() && XLLiveHelpers.showXLLiveTable(getActivity()) && canQueryToday() && this.mTimer == null) {
            XLog.d("redflag", "startTimer()");
            try {
                SharedPreferences sharedPreferences = getSharedPreferences();
                long j2 = sharedPreferences.getLong("red_flag_last_query", 0);
                Editor edit = sharedPreferences.edit();
                edit.remove("red_flag_next_query");
                edit.commit();
                if (this.mRedFlagFirstStart) {
                    this.mRedFlagFirstStart = false;
                } else {
                    j = 0;
                }
                this.mTimer = new Timer();
                this.mTimer.schedule(new AnonymousClass_4(sharedPreferences, j2), j, 10000);
            } catch (Throwable th) {
            }
        }
    }

    private void onQueryResult(String str) {
        try {
            JsonWrapper jsonWrapper = new JsonWrapper(str);
            int i = jsonWrapper.getInt("new", 0);
            long j = jsonWrapper.getLong("t", 0);
            long j2 = jsonWrapper.getLong("next", 60);
            int i2 = jsonWrapper.getInt("show", MqttConnectOptions.MQTT_VERSION_3_1);
            XLog.d("redflag", new StringBuilder("data = ").append(str).toString());
            SharedPreferences sharedPreferences = getSharedPreferences();
            Editor edit = sharedPreferences.edit();
            edit.putLong("red_flag_last_query", j);
            edit.putLong("red_flag_next_query", (j2 * 1000) + SystemClock.elapsedRealtime());
            edit.putInt("red_flag_max_count", i2);
            if (i == 1) {
                endTimer();
                showRegFlagOnXLLive(getActivity().getSupportFragmentManager(), true);
                i2 = sharedPreferences.getInt("red_flag_day", -1);
                i = Calendar.getInstance().get(SimpleLog.LOG_LEVEL_ERROR);
                if (i != i2) {
                    edit.putInt("red_flag_day", i);
                    edit.putInt("red_flag_count", 1);
                } else {
                    edit.putInt("red_flag_count", sharedPreferences.getInt("red_flag_count", 0) + 1);
                }
            }
            edit.commit();
        } catch (Throwable th) {
        }
    }

    private void endTimer() {
        XLog.d("redflag", "endTimer()");
        try {
            if (this.mTimer != null) {
                this.mTimer.cancel();
                this.mTimer = null;
            }
            if (this.mRedFlagReceiver != null) {
                getActivity().unregisterReceiver(this.mRedFlagReceiver);
                this.mRedFlagReceiver = null;
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showRegFlagOnXLLive(android.support.v4.app.FragmentManager r8, boolean r9) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.XLLiveFragment.showRegFlagOnXLLive(android.support.v4.app.FragmentManager, boolean):void");
        /*
        this = this;
        r1 = 0;
        if (r9 == 0) goto L_0x000f;
    L_0x0003:
        r0 = r7.getContext();	 Catch:{ Throwable -> 0x004d }
        r0 = com.xunlei.tdlive.sdk.XLLiveSDK.getInstance(r0);	 Catch:{ Throwable -> 0x004d }
        r2 = 1;
        r0.setHasRedFlagOnXLLiveTab(r2);	 Catch:{ Throwable -> 0x004d }
    L_0x000f:
        r2 = r7.getHomeFragment(r8);	 Catch:{ Throwable -> 0x004d }
        r3 = "livestream";
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x004d }
        if (r0 != 0) goto L_0x0046;
    L_0x001c:
        r4 = r2.a;	 Catch:{ Throwable -> 0x004d }
        r0 = r2.g;	 Catch:{ Throwable -> 0x004d }
        r0 = r0.b;	 Catch:{ Throwable -> 0x004d }
        r5 = r0.length;	 Catch:{ Throwable -> 0x004d }
        r0 = r1;
    L_0x0024:
        if (r0 >= r5) goto L_0x0046;
    L_0x0026:
        r6 = r2.e(r0);	 Catch:{ Throwable -> 0x004d }
        r6 = r3.equals(r6);	 Catch:{ Throwable -> 0x004d }
        if (r6 == 0) goto L_0x004a;
    L_0x0030:
        r2 = r4.getTabWidget();	 Catch:{ Throwable -> 0x004d }
        r0 = r2.getChildTabViewAt(r0);	 Catch:{ Throwable -> 0x004d }
        r2 = 2131757042; // 0x7f1007f2 float:1.9145009E38 double:1.0532279197E-314;
        r0 = r0.findViewById(r2);	 Catch:{ Throwable -> 0x004d }
        r0 = (android.widget.ImageView) r0;	 Catch:{ Throwable -> 0x004d }
        if (r9 == 0) goto L_0x0047;
    L_0x0043:
        r0.setVisibility(r1);	 Catch:{ Throwable -> 0x004d }
    L_0x0046:
        return;
    L_0x0047:
        r1 = 8;
        goto L_0x0043;
    L_0x004a:
        r0 = r0 + 1;
        goto L_0x0024;
    L_0x004d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0046;
        */
    }

    private HomeFragment getHomeFragment(FragmentManager fragmentManager) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof HomeFragment) {
                return (HomeFragment) fragment;
            }
        }
        return null;
    }

    private boolean canQueryToday() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences();
            int i = sharedPreferences.getInt("red_flag_day", -1);
            int i2 = sharedPreferences.getInt("red_flag_max_count", MqttConnectOptions.MQTT_VERSION_3_1);
            int i3 = Calendar.getInstance().get(SimpleLog.LOG_LEVEL_ERROR);
            if (i3 == i) {
                return sharedPreferences.getInt("red_flag_count", -1) < i2;
            } else {
                Editor edit = sharedPreferences.edit();
                edit.putInt("red_flag_day", i3);
                edit.remove("red_flag_count");
                edit.apply();
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private SharedPreferences getSharedPreferences() {
        return getActivity().getSharedPreferences("xllive_red_flag", 0);
    }
}
