package com.xiaomi.account.openauth;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

class XiaomiOAuthorize$2 extends XiaomiOAuthRunnable<XiaomiOAuthResults> {
    final /* synthetic */ XiaomiOAuthorize this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$responseType;

    XiaomiOAuthorize$2(XiaomiOAuthorize xiaomiOAuthorize, Activity activity, String str) {
        this.this$0 = xiaomiOAuthorize;
        this.val$activity = activity;
        this.val$responseType = str;
    }

    public void doRun() {
        try {
            run(new XiaomiOAuthorize$XiaomiTokenFuture(this.val$activity, this.mFuture));
        } catch (ExecutionException e) {
            this.mFuture.setException(e.getCause());
        } catch (Throwable e2) {
            this.mFuture.setException(e2);
        } catch (Throwable e22) {
            this.mFuture.setException(e22);
        } catch (Throwable e222) {
            this.mFuture.setException(e222);
        }
    }

    private void run(XiaomiOAuthorize$XiaomiTokenFuture xiaomiOAuthorize$XiaomiTokenFuture) throws ExecutionException, InterruptedException, IOException, OperationCanceledException {
        XiaomiOAuthorize$OAuthStage xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.INIT;
        while (true) {
            switch (XiaomiOAuthorize$8.$SwitchMap$com$xiaomi$account$openauth$XiaomiOAuthorize$OAuthStage[xiaomiOAuthorize$OAuthStage.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (XiaomiOAuthorize.access$000(this.this$0) || !XiaomiOAuthorize.access$100(this.val$activity)) {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_3RD_PARTY;
                    } else if (XiaomiOAuthorize.access$200(this.this$0, this.val$activity)) {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_MIUI_WITH_RESPONSE;
                    } else if (XiaomiOAuthorize.access$300(this.this$0, this.val$activity) != null) {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_MIUI;
                    } else {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.ADD_SYSTEM_ACCOUNT;
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    try {
                        Bundle bundle = (Bundle) AccountManager.get(this.val$activity).addAccount("com.xiaomi", null, null, null, this.val$activity, null, null).getResult();
                        if (bundle != null && bundle.containsKey("authAccount")) {
                            xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_MIUI;
                        }
                        xiaomiOAuthorize$XiaomiTokenFuture.setException(new Exception("fail to add account"));
                        return;
                    } catch (SecurityException e) {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_3RD_PARTY;
                    } catch (AuthenticatorException e2) {
                        xiaomiOAuthorize$OAuthStage = XiaomiOAuthorize$OAuthStage.OAUTH_FROM_3RD_PARTY;
                    }
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    xiaomiOAuthorize$XiaomiTokenFuture.set(XiaomiOAuthorize.access$400(this.val$activity, XiaomiOAuthorize.access$300(this.this$0, this.val$activity), makeOptions()));
                    return;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    XiaomiOAuthorize.access$600(this.val$activity, makeOptions(), XiaomiOAuthorize$XiaomiTokenFuture.access$500(xiaomiOAuthorize$XiaomiTokenFuture));
                    return;
                case SimpleLog.LOG_LEVEL_ERROR:
                    xiaomiOAuthorize$XiaomiTokenFuture.handleIntentResult(AuthorizeActivity.getIntent(this.val$activity, String.valueOf(XiaomiOAuthorize.access$700(this.this$0)), XiaomiOAuthorize.access$800(this.this$0), this.val$responseType, XiaomiOAuthorize.access$1000(XiaomiOAuthorize.access$900(this.this$0)), XiaomiOAuthorize.access$1100(this.this$0), XiaomiOAuthorize.access$1200(this.this$0), XiaomiOAuthorize.access$1300(this.this$0), XiaomiOAuthorize$XiaomiTokenFuture.access$500(xiaomiOAuthorize$XiaomiTokenFuture)));
                    return;
                default:
                    break;
            }
        }
    }

    private Bundle makeOptions() {
        Bundle bundle = new Bundle();
        bundle.putString(XiaomiOAuthConstants.EXTRA_CLIENT_ID, String.valueOf(XiaomiOAuthorize.access$700(this.this$0)));
        bundle.putString(XiaomiOAuthConstants.EXTRA_REDIRECT_URI, XiaomiOAuthorize.access$800(this.this$0));
        bundle.putString(XiaomiOAuthConstants.EXTRA_RESPONSE_TYPE, this.val$responseType);
        if (XiaomiOAuthorize.access$1200(this.this$0) != null) {
            bundle.putBoolean(XiaomiOAuthConstants.EXTRA_SKIP_CONFIRM, XiaomiOAuthorize.access$1200(this.this$0).booleanValue());
        }
        if (!TextUtils.isEmpty(XiaomiOAuthorize.access$1100(this.this$0))) {
            bundle.putString(XiaomiOAuthConstants.EXTRA_STATE, XiaomiOAuthorize.access$1100(this.this$0));
        }
        Object access$1000 = XiaomiOAuthorize.access$1000(XiaomiOAuthorize.access$900(this.this$0));
        if (!TextUtils.isEmpty(access$1000)) {
            bundle.putString(XiaomiOAuthConstants.EXTRA_SCOPE, access$1000);
        }
        return bundle;
    }
}
