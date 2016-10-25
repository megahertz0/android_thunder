package com.xunlei.common.member.act;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import com.umeng.a;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.u;

public class XLXmLoginActivity extends Activity {
    private final String TAG;
    private XiaomiOAuthFuture<XiaomiOAuthResults> mAuthFuture;
    private XiaomiOAuthResults mAuthResult;
    private String mClientFrom;
    private int mFirstLoginFlag;
    private Handler mHandler;
    private int mTaskId;

    public XLXmLoginActivity() {
        this.TAG = XLXmLoginActivity.class.getSimpleName();
        this.mClientFrom = a.d;
        this.mTaskId = 0;
        this.mAuthFuture = null;
        this.mAuthResult = null;
        this.mHandler = new Handler();
        this.mFirstLoginFlag = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mClientFrom = getIntent().getStringExtra("xm_client_from");
        this.mTaskId = getIntent().getIntExtra("xm_task", 0);
        this.mAuthFuture = new XiaomiOAuthorize().setAppId(2882303761517159179L).setRedirectUrl("https://open-api-auth.xunlei.com").startGetOAuthCode(this);
        waitXiaoMiFutureResult(this.mAuthFuture);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            sendResultToHost(0, intent.getStringExtra("xl_bind_tid"), intent.getStringExtra("xl_bind_ttoken"), intent.getStringExtra("xl_bind_tsign"));
        } else {
            sendResultToHost(i2, null, null, null);
        }
    }

    protected void checkXiaoMiAccountBind(String str) {
        m.a().k().get(m.a().h(), String.format("https://open-api-auth.xunlei.com/platform?m=BindOauthSession&op=getInfo&from=%s&code=%s&version=%s", new Object[]{this.mClientFrom, str, "1.1"}), null, new BaseHttpClientListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(int r8, org.apache.http.Header[] r9, byte[] r10) {
                throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.act.XLXmLoginActivity.AnonymousClass_1.onSuccess(int, org.apache.http.Header[], byte[]):void");
                /*
                this = this;
                r6 = 0;
                r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r8 != r0) goto L_0x0065;
            L_0x0005:
                r0 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x006e }
                r1 = "UTF-8";
                r0.<init>(r10, r1);	 Catch:{ UnsupportedEncodingException -> 0x006e }
                r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0061 }
                r1.<init>(r0);	 Catch:{ JSONException -> 0x0061 }
                r0 = "result";
                r0 = r1.getInt(r0);	 Catch:{ JSONException -> 0x0061 }
                if (r0 != 0) goto L_0x0065;
            L_0x001b:
                r0 = "url";
                r0 = r1.optString(r0);	 Catch:{ JSONException -> 0x0061 }
                r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x0061 }
                if (r2 == 0) goto L_0x004a;
            L_0x0028:
                r0 = com.xunlei.common.member.act.XLXmLoginActivity.this;	 Catch:{ JSONException -> 0x0061 }
                r2 = 0;
                r0.mFirstLoginFlag = r2;	 Catch:{ JSONException -> 0x0061 }
                r0 = com.xunlei.common.member.act.XLXmLoginActivity.this;	 Catch:{ JSONException -> 0x0061 }
                r2 = 0;
                r3 = "third_id";
                r3 = r1.getString(r3);	 Catch:{ JSONException -> 0x0061 }
                r4 = "mi_token";
                r4 = r1.getString(r4);	 Catch:{ JSONException -> 0x0061 }
                r5 = "sign";
                r1 = r1.getString(r5);	 Catch:{ JSONException -> 0x0061 }
                r0.sendResultToHost(r2, r3, r4, r1);	 Catch:{ JSONException -> 0x0061 }
            L_0x0049:
                return;
            L_0x004a:
                r1 = com.xunlei.common.member.act.XLXmLoginActivity.this;	 Catch:{ JSONException -> 0x0061 }
                r2 = 1;
                r1.mFirstLoginFlag = r2;	 Catch:{ JSONException -> 0x0061 }
                r1 = com.xunlei.common.member.act.XLXmLoginActivity.this;	 Catch:{ JSONException -> 0x0061 }
                r1 = r1.mHandler;	 Catch:{ JSONException -> 0x0061 }
                r2 = new com.xunlei.common.member.act.XLXmLoginActivity$1$1;	 Catch:{ JSONException -> 0x0061 }
                r2.<init>(r7, r0);	 Catch:{ JSONException -> 0x0061 }
                r4 = 0;
                r1.postDelayed(r2, r4);	 Catch:{ JSONException -> 0x0061 }
                goto L_0x0049;
            L_0x0061:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ UnsupportedEncodingException -> 0x006e }
            L_0x0065:
                r0 = com.xunlei.common.member.act.XLXmLoginActivity.this;
                r1 = 16781282; // 0x1000fe2 float:2.3521282E-38 double:8.291055E-317;
                r0.sendResultToHost(r1, r6, r6, r6);
                goto L_0x0049;
            L_0x006e:
                r0 = move-exception;
                r0.printStackTrace();
                goto L_0x0065;
                */
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLXmLoginActivity.this.sendResultToHost(XLErrorCode.AUTH_USER_ERROR, null, null, null);
            }
        });
    }

    protected void startBindXunleiAccount(String str) {
        Intent intent = new Intent(this, XLXmBindActivity.class);
        intent.putExtra("xl_bind_url", str);
        startActivityForResult(intent, 9527);
    }

    protected void sendResultToHost(int i, String str, String str2, String str3) {
        u uVar = (u) m.a().a(this.mTaskId);
        if (uVar != null) {
            uVar.b(this.mFirstLoginFlag);
            uVar.a(i, str, str2, str3);
        }
        finish();
    }

    private <V> void waitXiaoMiFutureResult(XiaomiOAuthFuture<V> xiaomiOAuthFuture) {
        new AsyncTask<Void, Void, V>() {
            private Exception e;

            {
                this.e = null;
            }

            protected void onPreExecute() {
                XLLog.v(XLXmLoginActivity.this.TAG, "waiting for Future result...");
            }

            protected V doInBackground(Void... voidArr) {
                try {
                    return XLXmLoginActivity.this.mAuthFuture.getResult();
                } catch (Exception e) {
                    this.e = e;
                    return null;
                } catch (Exception e2) {
                    this.e = e2;
                    return null;
                } catch (Exception e22) {
                    this.e = e22;
                    return null;
                }
            }

            protected void onPostExecute(V v) {
                int i = XLErrorCode.AUTH_USER_ERROR;
                if (v != null && (v instanceof XiaomiOAuthResults)) {
                    XLXmLoginActivity.this.mAuthResult = (XiaomiOAuthResults) v;
                    if (XLXmLoginActivity.this.mAuthResult.getErrorCode() == 0) {
                        XLXmLoginActivity.this.mHandler.postDelayed(new AnonymousClass_1(this), 0);
                        return;
                    }
                }
                if (this.e instanceof OperationCanceledException) {
                    i = XLErrorCode.AUTH_USER_CANCLE;
                }
                XLXmLoginActivity.this.sendResultToHost(i, null, null, null);
            }
        }.execute(new Void[0]);
    }
}
