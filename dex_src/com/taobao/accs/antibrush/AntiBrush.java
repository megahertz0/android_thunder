package com.taobao.accs.antibrush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.a;
import com.taobao.accs.data.e;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.android.agoo.intent.IntentUtil;

// compiled from: Taobao
public class AntiBrush {
    private static final String ANTI_ATTACK_ACTION = "mtopsdk.mtop.antiattack.checkcode.validate.activity_action";
    private static final String ANTI_ATTACK_RESULT_ACTION = "mtopsdk.extra.antiattack.result.notify.action";
    public static final int STATUS_BRUSH = 419;
    private static final String TAG = "AntiBrush";
    private static String mHost;
    private static volatile boolean mIsInCheckCodeActivity;
    private static ScheduledFuture<?> mTimeoutTask;
    private BroadcastReceiver mAntiAttackReceiver;
    private Context mContext;

    // compiled from: Taobao
    public static class AntiReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            try {
                String stringExtra = intent.getStringExtra("Result");
                ALog.e(TAG, new StringBuilder("anti onReceive result: ").append(stringExtra).toString(), new Object[0]);
                if (stringExtra.equalsIgnoreCase(MsgConstant.KEY_SUCCESS)) {
                    z = true;
                }
                AntiBrush.onResult(GlobalClientInfo.getContext(), z);
            } catch (Throwable e) {
                ALog.e(TAG, "anti onReceive", e, new Object[0]);
                AntiBrush.onResult(GlobalClientInfo.getContext(), false);
            }
        }
    }

    static {
        mIsInCheckCodeActivity = false;
    }

    public boolean checkAntiBrush(URL url, Map<ExtHeaderType, String> map) {
        boolean z;
        if (map != null) {
            int i;
            try {
            } catch (Throwable th) {
                Throwable th2 = th;
                i = 0;
                ALog.e(TAG, "checkAntiBrush error", th2, new Object[0]);
                return z;
            }
            if (UtilityImpl.isForeground(this.mContext)) {
                String str = (String) map.get(ExtHeaderType.TYPE_STATUS);
                if (TextUtils.isEmpty(str)) {
                    i = 0;
                } else {
                    i = Integer.valueOf(str).intValue();
                }
                if (i == 419) {
                    str = (String) map.get(ExtHeaderType.TYPE_LOCATION);
                    if (!TextUtils.isEmpty(str)) {
                        ALog.e(TAG, new StringBuilder("start anti bursh location:").append(str).toString(), new Object[0]);
                        handleantiBrush(str);
                        if (mTimeoutTask != null) {
                            mTimeoutTask.cancel(true);
                            mTimeoutTask = null;
                        }
                        mTimeoutTask = a.a(new a(this), BuglyBroadcastRecevier.UPLOADLIMITED, TimeUnit.MILLISECONDS);
                        mHost = url == null ? null : url.getHost();
                        z = true;
                        try {
                            if (!TextUtils.isEmpty(GlobalClientInfo.a) && TextUtils.isEmpty(b.a(mHost))) {
                                ALog.e(TAG, "cookie invalid, clear", new Object[0]);
                                UtilityImpl.clearCookie(this.mContext);
                            }
                        } catch (Throwable th3) {
                            th2 = th3;
                            ALog.e(TAG, "checkAntiBrush error", th2, new Object[0]);
                            return z;
                        }
                        return z;
                    }
                }
            }
        }
        z = false;
        ALog.e(TAG, "cookie invalid, clear", new Object[0]);
        UtilityImpl.clearCookie(this.mContext);
        return z;
    }

    public AntiBrush(Context context) {
        this.mAntiAttackReceiver = null;
        this.mContext = context.getApplicationContext();
    }

    public static void onResult(Context context, boolean z) {
        mIsInCheckCodeActivity = false;
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(IntentUtil.AGOO_COMMAND, R.styleable.AppCompatTheme_editTextStyle);
        intent.putExtra(Constants.KEY_ANTI_BRUSH_RET, z);
        e.a(context, intent);
        if (mTimeoutTask != null) {
            mTimeoutTask.cancel(true);
            mTimeoutTask = null;
        }
        if (mHost != null) {
            UtilityImpl.storeCookie(context, b.a(mHost));
        }
    }

    private void handleantiBrush(String str) {
        if (mIsInCheckCodeActivity) {
            ALog.e(TAG, "handleantiBrush return", "mIsInCheckCodeActivity", Boolean.valueOf(mIsInCheckCodeActivity));
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction(ANTI_ATTACK_ACTION);
            intent.setPackage(this.mContext.getPackageName());
            intent.setFlags(268435456);
            intent.putExtra(HttpConstant.LOCATION, str);
            ALog.e(TAG, "handleAntiBrush:", new Object[0]);
            this.mContext.startActivity(intent);
            mIsInCheckCodeActivity = true;
            if (this.mAntiAttackReceiver == null) {
                this.mAntiAttackReceiver = new AntiReceiver();
            }
            this.mContext.registerReceiver(this.mAntiAttackReceiver, new IntentFilter(ANTI_ATTACK_RESULT_ACTION));
        } catch (Throwable th) {
            ALog.e(TAG, "handleantiBrush", th, new Object[0]);
        }
    }
}
