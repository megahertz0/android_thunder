package com.uc.addon.sdk.remote;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.HistoryDeleteArg;
import com.uc.addon.sdk.remote.protocol.HistorySearchParam;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;
import com.uc.addon.sdk.remote.protocol.SimpleArg;
import com.uc.addon.sdk.remote.protocol.SimpleArgUtil;

public class HistoryImpl extends RequestSender implements History {
    private Handler a;

    class AnonymousClass_1 extends Stub {
        final /* synthetic */ ValueCallback a;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ SimpleArg a;

            AnonymousClass_1(SimpleArg simpleArg) {
                this.a = simpleArg;
            }

            public void run() {
                AnonymousClass_1.this.a.onReceiveValue(this.a.value);
            }
        }

        AnonymousClass_1(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            SimpleArg simpleArg = new SimpleArg();
            simpleArg.fromBundle(bundle);
            HistoryImpl.this.a.post(new AnonymousClass_1(simpleArg));
        }
    }

    public HistoryImpl(IApp iApp) {
        super(iApp);
        this.a = new Handler(Looper.getMainLooper());
    }

    public void delete(String str, String str2, int i) {
        BaseArg historyDeleteArg = new HistoryDeleteArg();
        historyDeleteArg.type = i;
        historyDeleteArg.url = str2;
        historyDeleteArg.title = str;
        a(CommandConstant.COMMAND_HISTORY_DELETE, historyDeleteArg, null);
    }

    public void search(HistorySearchParam historySearchParam, ValueCallback valueCallback) {
        if (historySearchParam == null) {
            DebugUtil.error("History search HistorySearchParam can't null");
        }
        if (valueCallback == null) {
            DebugUtil.error("History search callback can't null");
        }
        a(CommandConstant.COMMAND_HISTORY_SEARCH, SimpleArgUtil.buildArg(historySearchParam), new AnonymousClass_1(valueCallback));
    }
}
