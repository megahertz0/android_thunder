package com.xiaomi.account.openauth;

import android.content.Context;

class XiaomiOAuthorize$1 extends XiaomiOAuthRunnable<String> {
    final /* synthetic */ XiaomiOAuthorize this$0;
    final /* synthetic */ String val$accessToken;
    final /* synthetic */ long val$appId;
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$macAlgorithm;
    final /* synthetic */ String val$macKey;
    final /* synthetic */ String val$path;

    XiaomiOAuthorize$1(XiaomiOAuthorize xiaomiOAuthorize, Context context, String str, long j, String str2, String str3, String str4) {
        this.this$0 = xiaomiOAuthorize;
        this.val$context = context;
        this.val$path = str;
        this.val$appId = j;
        this.val$accessToken = str2;
        this.val$macKey = str3;
        this.val$macAlgorithm = str4;
    }

    public void doRun() {
        try {
            this.mFuture.set(AuthorizeApi.doHttpGet(this.val$context, this.val$path, this.val$appId, this.val$accessToken, this.val$macKey, this.val$macAlgorithm));
        } catch (XMAuthericationException e) {
            this.mFuture.setException(e);
        }
    }
}
