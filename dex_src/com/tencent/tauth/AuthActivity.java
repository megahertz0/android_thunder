package com.tencent.tauth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.umeng.a;

// compiled from: ProGuard
public class AuthActivity extends Activity {
    private static final String ACTION_ADD_TO_QQFAVORITES = "addToQQFavorites";
    public static final String ACTION_KEY = "action";
    private static final String ACTION_SEND_TO_MY_COMPUTER = "sendToMyComputer";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static final String ACTION_SHARE_TO_QQ = "shareToQQ";
    private static final String ACTION_SHARE_TO_QZONE = "shareToQzone";
    private static final String ACTION_SHARE_TO_TROOP_BAR = "shareToTroopBar";
    private static final String SHARE_PRIZE_ACTIVITY_ID = "activityid";
    private static final String TAG = "openSDK_LOG.AuthActivity";
    private static int mShareQzoneBackTime;

    static {
        mShareQzoneBackTime = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            f.d(TAG, "-->onCreate, getIntent() return null");
            finish();
            return;
        }
        Uri uri = null;
        try {
            uri = getIntent().getData();
        } catch (Exception e) {
            f.e(TAG, new StringBuilder("-->onCreate, getIntent().getData() has exception! ").append(e.getMessage()).toString());
        }
        f.a(TAG, new StringBuilder("-->onCreate, uri: ").append(uri).toString());
        handleActionUri(uri);
    }

    private void handleActionUri(Uri uri) {
        f.c(TAG, "-->handleActionUri--start");
        if (uri == null || uri.toString() == null || uri.toString().equals(a.d)) {
            f.d(TAG, "-->handleActionUri, uri invalid");
            finish();
            return;
        }
        String toString = uri.toString();
        Bundle decodeUrl = Util.decodeUrl(toString.substring(toString.indexOf("#") + 1));
        if (decodeUrl == null) {
            f.d(TAG, "-->handleActionUri, bundle is null");
            finish();
            return;
        }
        String string = decodeUrl.getString(ACTION_KEY);
        f.c(TAG, new StringBuilder("-->handleActionUri, action: ").append(string).toString());
        if (string == null) {
            finish();
        } else if (string.equals(ACTION_SHARE_TO_QQ) || string.equals(ACTION_SHARE_TO_QZONE) || string.equals(ACTION_SEND_TO_MY_COMPUTER) || string.equals(ACTION_SHARE_TO_TROOP_BAR)) {
            if (string.equals(ACTION_SHARE_TO_QZONE) && SystemUtils.getAppVersionName(this, Constants.PACKAGE_QQ) != null && SystemUtils.compareQQVersion(this, SystemUtils.QQ_VERSION_NAME_5_2_0) < 0) {
                int i = mShareQzoneBackTime + 1;
                mShareQzoneBackTime = i;
                if (i == 2) {
                    mShareQzoneBackTime = 0;
                    finish();
                    return;
                }
            }
            f.c(TAG, "-->handleActionUri, most share action, start assistactivity");
            Intent intent = new Intent(this, AssistActivity.class);
            intent.putExtras(decodeUrl);
            intent.setFlags(603979776);
            startActivity(intent);
            finish();
        } else if (string.equals(ACTION_ADD_TO_QQFAVORITES)) {
            intent = getIntent();
            intent.putExtras(decodeUrl);
            intent.putExtra(Constants.KEY_ACTION, CommandConstant.COMMAND_SHARE);
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string);
            if (listnerWithAction != null) {
                UIListenerManager.getInstance().handleDataToListener(intent, listnerWithAction);
            }
            finish();
        } else {
            if (string.equals(ACTION_SHARE_PRIZE)) {
                intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                string = decodeUrl.getString("response");
                Object obj = a.d;
                try {
                    obj = Util.parseJson(string).getString(SHARE_PRIZE_ACTIVITY_ID);
                } catch (Throwable e) {
                    f.b(TAG, "sharePrize parseJson has exception.", e);
                }
                if (!TextUtils.isEmpty(obj)) {
                    intent.putExtra(ACTION_SHARE_PRIZE, true);
                    Bundle bundle = new Bundle();
                    bundle.putString(SHARE_PRIZE_ACTIVITY_ID, obj);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            }
            finish();
        }
    }
}
