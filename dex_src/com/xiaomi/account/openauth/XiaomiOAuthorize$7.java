package com.xiaomi.account.openauth;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

final class XiaomiOAuthorize$7 extends AsyncTask<Void, Void, XiaomiOAuthResults> {
    Exception e;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ int val$requestCode;
    final /* synthetic */ String val$typeCode;
    final /* synthetic */ XiaomiOAuthFuture val$y;

    XiaomiOAuthorize$7(XiaomiOAuthFuture xiaomiOAuthFuture, String str, Activity activity, int i) {
        this.val$y = xiaomiOAuthFuture;
        this.val$typeCode = str;
        this.val$activity = activity;
        this.val$requestCode = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final com.xiaomi.account.openauth.XiaomiOAuthResults doInBackground(java.lang.Void... r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.openauth.XiaomiOAuthorize$7.doInBackground(java.lang.Void[]):com.xiaomi.account.openauth.XiaomiOAuthResults");
        /*
        this = this;
        r0 = r1.val$y;	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
        r0 = r0.getResult();	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
        r0 = (com.xiaomi.account.openauth.XiaomiOAuthResults) r0;	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x000d:
        r0 = 0;
        goto L_0x0008;
    L_0x000f:
        r0 = move-exception;
        r1.e = r0;
        goto L_0x000d;
    L_0x0013:
        r0 = move-exception;
        r1.e = r0;
        goto L_0x000d;
        */
    }

    protected final void onPostExecute(XiaomiOAuthResults xiaomiOAuthResults) {
        int i;
        Bundle bundle = new Bundle();
        if (xiaomiOAuthResults == null) {
            if (this.e == null) {
                i = AuthorizeActivity.RESULT_CANCEL;
                bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, AuthorizeActivity.RESULT_CANCEL);
                bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, "canceled");
            } else {
                i = AuthorizeActivity.RESULT_FAIL;
                bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, AuthorizeActivity.RESULT_FAIL);
                bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, this.e.getMessage());
            }
        } else if (xiaomiOAuthResults.hasError()) {
            i = AuthorizeActivity.RESULT_FAIL;
            bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, xiaomiOAuthResults.getErrorCode());
            bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, xiaomiOAuthResults.getErrorMessage());
        } else {
            i = AuthorizeActivity.RESULT_SUCCESS;
            if (XiaomiOAuthConstants.EXTRA_CODE_2.equalsIgnoreCase(this.val$typeCode)) {
                bundle.putString(XiaomiOAuthConstants.EXTRA_CODE_2, xiaomiOAuthResults.getCode());
                bundle.putString(XiaomiOAuthConstants.EXTRA_STATE_2, xiaomiOAuthResults.getState());
                bundle.putString(XiaomiOAuthConstants.EXTRA_TOKEN_TYPE_2, xiaomiOAuthResults.getTokenType());
                bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_KEY_2, xiaomiOAuthResults.getMacKey());
                bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_ALGORITHM_2, xiaomiOAuthResults.getMacAlgorithm());
            } else {
                bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, xiaomiOAuthResults.getAccessToken());
                bundle.putString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, xiaomiOAuthResults.getExpiresIn());
                bundle.putString(XiaomiOAuthConstants.EXTRA_SCOPE_2, xiaomiOAuthResults.getScopes());
                bundle.putString(XiaomiOAuthConstants.EXTRA_STATE_2, xiaomiOAuthResults.getState());
                bundle.putString(XiaomiOAuthConstants.EXTRA_TOKEN_TYPE_2, xiaomiOAuthResults.getTokenType());
                bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_KEY_2, xiaomiOAuthResults.getMacKey());
                bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_ALGORITHM_2, xiaomiOAuthResults.getMacAlgorithm());
            }
        }
        this.val$activity.startActivityForResult(AuthorizeActivity.asMiddleActivity(this.val$activity, i, bundle), this.val$requestCode);
    }
}
