package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

// compiled from: ProGuard
public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected static final int FINISH_BY_TIMEOUT = 0;
    private static final String RESTART_FLAG = "RESTART_FLAG";
    private static final String TAG = "openSDK_LOG.AssistActivity";
    protected Handler handler;
    private boolean isRestart;

    public AssistActivity() {
        this.isRestart = false;
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case FINISH_BY_TIMEOUT:
                        if (!AssistActivity.this.isFinishing()) {
                            f.d(TAG, "-->finish by timeout");
                            AssistActivity.this.finish();
                        }
                    default:
                        break;
                }
            }
        };
    }

    public static Intent getAssistActivityIntent(Context context) {
        return new Intent(context, AssistActivity.class);
    }

    protected void onCreate(Bundle bundle) {
        int i = FINISH_BY_TIMEOUT;
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(XZBDevice.DOWNLOAD_LIST_FAILED);
        f.b(TAG, "--onCreate--");
        if (getIntent() == null) {
            f.e(TAG, "-->onCreate--getIntent() returns null");
            finish();
        }
        Intent intent = (Intent) getIntent().getParcelableExtra(EXTRA_INTENT);
        if (intent != null) {
            i = intent.getIntExtra(Constants.KEY_REQUEST_CODE, FINISH_BY_TIMEOUT);
        }
        Bundle bundleExtra = getIntent().getBundleExtra(SystemUtils.H5_SHARE_DATA);
        if (bundle != null) {
            this.isRestart = bundle.getBoolean(RESTART_FLAG);
        }
        if (this.isRestart) {
            f.b(TAG, "is restart");
        } else if (bundleExtra != null) {
            f.d(TAG, "--onCreate--h5 bundle not null, will open browser");
            openBrowser(bundleExtra);
        } else if (intent != null) {
            f.c(TAG, new StringBuilder("--onCreate--activityIntent not null, will start activity, reqcode = ").append(i).toString());
            startActivityForResult(intent, i);
        } else {
            f.e(TAG, "--onCreate--activityIntent is null");
            finish();
        }
    }

    protected void onStart() {
        f.b(TAG, "-->onStart");
        super.onStart();
    }

    protected void onResume() {
        f.b(TAG, "-->onResume");
        super.onResume();
        Intent intent = getIntent();
        if (!intent.getBooleanExtra(SystemUtils.IS_LOGIN, false)) {
            if (!(intent.getBooleanExtra(SystemUtils.IS_QQ_MOBILE_SHARE, false) || !this.isRestart || isFinishing())) {
                finish();
            }
            this.handler.sendMessage(this.handler.obtainMessage(FINISH_BY_TIMEOUT));
        }
    }

    protected void onPause() {
        f.b(TAG, "-->onPause");
        this.handler.removeMessages(FINISH_BY_TIMEOUT);
        super.onPause();
    }

    protected void onStop() {
        f.b(TAG, "-->onStop");
        super.onStop();
    }

    protected void onDestroy() {
        f.b(TAG, "-->onDestroy");
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        f.c(TAG, "--onNewIntent");
        super.onNewIntent(intent);
        intent.putExtra(Constants.KEY_ACTION, CommandConstant.COMMAND_SHARE);
        setResult(-1, intent);
        if (!isFinishing()) {
            f.c(TAG, "--onNewIntent--activity not finished, finish now");
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        f.b(TAG, "--onSaveInstanceState--");
        bundle.putBoolean(RESTART_FLAG, true);
        super.onSaveInstanceState(bundle);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        f.c(TAG, new StringBuilder("--onActivityResult--requestCode: ").append(i).append(" | resultCode: ").append(i2).append("data = null ? ").append(intent == null).toString());
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (intent != null) {
                intent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_LOGIN);
            }
            setResultData(i2, intent);
            finish();
        }
    }

    public void setResultData(int i, Intent intent) {
        if (intent == null) {
            f.d(TAG, "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            setResult(FINISH_BY_TIMEOUT, intent);
            return;
        }
        try {
            Object stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
            f.b(TAG, new StringBuilder("--setResultDataForLogin-- ").append(stringExtra).toString());
            if (TextUtils.isEmpty(stringExtra)) {
                f.d(TAG, "--setResultData--response is empty, setResult ACTIVITY_OK");
                setResult(-1, intent);
                return;
            }
            JSONObject jSONObject = new JSONObject(stringExtra);
            CharSequence optString = jSONObject.optString(SocialConstants.PARAM_OPEN_ID);
            CharSequence optString2 = jSONObject.optString(Constants.PARAM_ACCESS_TOKEN);
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                f.d(TAG, "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                setResult(FINISH_BY_TIMEOUT, intent);
                return;
            }
            f.c(TAG, "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
            setResult(-1, intent);
        } catch (Exception e) {
            f.e(TAG, "--setResultData--parse response failed");
            e.printStackTrace();
        }
    }

    private void openBrowser(Bundle bundle) {
        String string = bundle.getString("viaShareType");
        String string2 = bundle.getString("callbackAction");
        String string3 = bundle.getString(SocialConstants.PARAM_URL);
        String string4 = bundle.getString("openId");
        String string5 = bundle.getString("appId");
        String str = a.d;
        String str2 = a.d;
        if (SystemUtils.QQ_SHARE_CALLBACK_ACTION.equals(string2)) {
            str = Constants.VIA_SHARE_TO_QQ;
            str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        } else if (SystemUtils.QZONE_SHARE_CALLBACK_ACTION.equals(string2)) {
            str = Constants.VIA_SHARE_TO_QZONE;
            str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
        }
        if (Util.openBrowser(this, string3)) {
            d.a().a(string4, string5, str, str2, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_READY_REPORT, string, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_READY_REPORT);
        } else {
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string2);
            if (listnerWithAction != null) {
                listnerWithAction.onError(new UiError(-6, Constants.MSG_OPEN_BROWSER_ERROR, null));
            }
            d.a().a(string4, string5, str, str2, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_NOTIFY_REACHED, string, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_READY_REPORT);
            finish();
        }
        getIntent().removeExtra("shareH5");
    }
}
