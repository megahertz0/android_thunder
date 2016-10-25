package com.xiaomi.account.openauth;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.account.IXiaomiAuthResponse;
import com.xiaomi.account.IXiaomiAuthResponse$Stub;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

private class XiaomiOAuthorize$XiaomiTokenFuture extends FutureTask<Bundle> {
    private final Activity mActivity;
    private final XiaomiOAuthFutureImpl<XiaomiOAuthResults> mRealFuture;

    public XiaomiOAuthorize$XiaomiTokenFuture(Activity activity, XiaomiOAuthFutureImpl<XiaomiOAuthResults> xiaomiOAuthFutureImpl) {
        super(new Callable<Bundle>() {
            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
        this.mActivity = activity;
        this.mRealFuture = xiaomiOAuthFutureImpl;
    }

    public Bundle get(long j, TimeUnit timeUnit) {
        throw new IllegalStateException("this should never be called");
    }

    public Bundle get() {
        throw new IllegalStateException("this should never be called");
    }

    public void set(Bundle bundle) {
        if (bundle == null || !bundle.containsKey(XiaomiOAuthConstants.EXTRA_INTENT)) {
            this.mRealFuture.set(XiaomiOAuthResults.parseBundle(bundle));
            return;
        }
        handleIntentResult((Intent) bundle.getParcelable(XiaomiOAuthConstants.EXTRA_INTENT));
    }

    public boolean handleIntentResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        extras.setClassLoader(getClass().getClassLoader());
        if (!extras.containsKey(XiaomiOAuthConstants.EXTRA_RESPONSE)) {
            intent = AuthorizeActivity.asMiddleActivity(this.mActivity, intent, wrapWithinResponse());
        }
        this.mActivity.startActivity(intent);
        return true;
    }

    private IXiaomiAuthResponse wrapWithinResponse() {
        return new IXiaomiAuthResponse$Stub() {
            public void onResult(Bundle bundle) throws RemoteException {
                XiaomiOAuthorize$XiaomiTokenFuture.this.set(bundle);
            }

            public void onCancel() throws RemoteException {
                XiaomiOAuthorize$XiaomiTokenFuture.this.setException(new OperationCanceledException());
            }
        };
    }

    protected void setException(Throwable th) {
        this.mRealFuture.setException(th);
    }
}
