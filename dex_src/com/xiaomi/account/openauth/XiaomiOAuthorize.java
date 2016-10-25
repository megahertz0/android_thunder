package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.account.IXiaomiAuthResponse;
import com.xiaomi.auth.AuthConstants;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class XiaomiOAuthorize {
    private static final String ACCOUNT_TYPE = "com.xiaomi";
    private static final String TAG = "XiaomiOAuthorize";
    private static final String TYPE_CODE = "code";
    private static final String TYPE_TOKEN = "token";
    private Long mAppId;
    private boolean mKeepCookies;
    private boolean mNotUseMiui;
    private String mRedirectUrl;
    private int[] mScopes;
    private Boolean mSkipConfirm;
    private String mState;

    public XiaomiOAuthorize() {
        this.mNotUseMiui = false;
        this.mScopes = null;
        this.mAppId = null;
        this.mRedirectUrl = null;
        this.mSkipConfirm = null;
        this.mState = null;
        this.mKeepCookies = false;
    }

    public XiaomiOAuthorize setScope(int[] iArr) {
        this.mScopes = iArr;
        return this;
    }

    public XiaomiOAuthorize setAppId(long j) {
        this.mAppId = Long.valueOf(j);
        return this;
    }

    public XiaomiOAuthorize setRedirectUrl(String str) {
        this.mRedirectUrl = str;
        return this;
    }

    public XiaomiOAuthorize setSkipConfirm(boolean z) {
        this.mSkipConfirm = Boolean.valueOf(z);
        return this;
    }

    public XiaomiOAuthorize setNoMiui(boolean z) {
        this.mNotUseMiui = z;
        return this;
    }

    public XiaomiOAuthorize setState(String str) {
        this.mState = str;
        return this;
    }

    public XiaomiOAuthorize setKeepCookies(boolean z) {
        this.mKeepCookies = z;
        return this;
    }

    public XiaomiOAuthFuture<XiaomiOAuthResults> startGetAccessToken(Activity activity) {
        return oauth(activity, TYPE_TOKEN);
    }

    public XiaomiOAuthFuture<XiaomiOAuthResults> startGetOAuthCode(Activity activity) {
        return oauth(activity, TYPE_CODE);
    }

    public XiaomiOAuthFuture<String> callOpenApi(Context context, long j, String str, String str2, String str3, String str4) {
        return new AnonymousClass_1(this, context, str, j, str2, str3, str4).start();
    }

    private XiaomiOAuthFutureImpl<XiaomiOAuthResults> oauth(Activity activity, String str) {
        if (activity == null || activity.isFinishing()) {
            throw new IllegalArgumentException("activity should not be null and should be running");
        } else if (this.mAppId == null || this.mAppId.longValue() <= 0) {
            throw new IllegalArgumentException("client id is error!!!");
        } else if (TextUtils.isEmpty(this.mRedirectUrl)) {
            throw new IllegalArgumentException("redirect url is empty!!!");
        } else if (!TextUtils.isEmpty(str)) {
            return new AnonymousClass_2(this, activity, str).start();
        } else {
            throw new IllegalArgumentException("responseType is empty!!!");
        }
    }

    private static Bundle getAccessTokenFromMiuiInResponseWay(Context context, Bundle bundle, IXiaomiAuthResponse iXiaomiAuthResponse) throws ExecutionException, InterruptedException {
        return (Bundle) new AnonymousClass_3(context, null, bundle, iXiaomiAuthResponse).start().get();
    }

    private static Bundle getAccessTokenFromMiui(Context context, Account account, Bundle bundle) throws ExecutionException, InterruptedException {
        return (Bundle) new AnonymousClass_4(context, account, bundle).start().get();
    }

    private static boolean isMiui(Context context) {
        try {
            return ((Boolean) new AnonymousClass_5(context, null, null).start().get()).booleanValue();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e2) {
            return false;
        }
    }

    private boolean supportResponseWayWithMiui(Context context) {
        try {
            return ((Boolean) new AnonymousClass_6(this, context, null, null).start().get()).booleanValue();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e2) {
            return false;
        }
    }

    private Account getXiaomiAccount(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType(ACCOUNT_TYPE);
        return accountsByType.length == 0 ? null : accountsByType[0];
    }

    private static String makeScopeString(int[] iArr) {
        int i = 0;
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            if (i2 > 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(i3);
            i++;
            i2 = i4;
        }
        return stringBuilder.toString();
    }

    @Deprecated
    public static void startGetAccessToken(Activity activity, long j, String str, Bundle bundle, int i) {
        startGetOAuthorize(activity, j, str, TYPE_TOKEN, bundle, i);
    }

    @Deprecated
    public static void startGetOAuthCode(Activity activity, long j, String str, Bundle bundle, int i) {
        startGetOAuthorize(activity, j, str, TYPE_CODE, bundle, i);
    }

    @Deprecated
    private static void startGetOAuthorize(Activity activity, long j, String str, String str2, Bundle bundle, int i) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        XiaomiOAuthorize state = new XiaomiOAuthorize().setAppId(j).setRedirectUrl(str).setScope(scopeStringToIntArray(bundle.getString(AuthConstants.EXTRA_SCOPE))).setState(bundle.getString(AuthConstants.EXTRA_STATE));
        if (bundle.containsKey(AuthConstants.EXTRA_SKIP_CONFIRM)) {
            state.setSkipConfirm(bundle.getBoolean(AuthConstants.EXTRA_SKIP_CONFIRM));
        }
        new AnonymousClass_7(TYPE_CODE.equalsIgnoreCase(str2) ? state.startGetOAuthCode(activity) : state.startGetAccessToken(activity), str2, activity, i).execute(new Void[0]);
    }

    @Deprecated
    private static int[] scopeStringToIntArray(String str) {
        int i;
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            String[] split = str.split(" ");
            int length = split.length;
            for (i = 0; i < length; i++) {
                try {
                    arrayList.add(Integer.valueOf(split[i]));
                } catch (NumberFormatException e) {
                }
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (i = 0; i < iArr.length; i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }
}
