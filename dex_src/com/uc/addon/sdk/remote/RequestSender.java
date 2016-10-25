package com.uc.addon.sdk.remote;

import android.os.Bundle;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;

public class RequestSender {
    public static final long DEFAULT_SYNC_TIME_OUT = 3000;
    public static int RESULT_ARG_ERROR;
    public static int RESULT_EXCEPTION;
    public static int RESULT_OK;
    private IApp a;

    class AnonymousClass_1 extends Stub {
        public void onReceiveValue(Bundle bundle) throws RemoteException {
            synchronized (null) {
                if (bundle != null) {
                    null.bundle = bundle;
                }
                Object obj = null;
                obj.notify();
            }
        }
    }

    static {
        RESULT_OK = 0;
        RESULT_ARG_ERROR = 1;
        RESULT_EXCEPTION = 2;
    }

    public RequestSender(IApp iApp) {
        this.a = iApp;
    }

    public static int sendRequest(IApp iApp, String str, BaseArg baseArg, IValueCallback iValueCallback) {
        if (iApp == null) {
            throw new IllegalArgumentException("app can't be null");
        } else if (baseArg != null && !baseArg.checkArgs()) {
            return RESULT_ARG_ERROR;
        } else {
            Bundle bundle = null;
            if (baseArg != null) {
                bundle = new Bundle();
                baseArg.toBundle(bundle);
            }
            try {
                iApp.request(str, bundle, iValueCallback);
                return RESULT_OK;
            } catch (RemoteException e) {
                return RESULT_EXCEPTION;
            }
        }
    }

    protected final int a(String str, BaseArg baseArg, IValueCallback iValueCallback) {
        return sendRequest(this.a, str, baseArg, iValueCallback);
    }
}
