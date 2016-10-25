package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.IDialogStateListener.Stub;

public abstract class DialogStateListener extends Stub {
    private Handler a;

    class AnonymousClass_1 implements Runnable {
        private /* synthetic */ int a;
        private /* synthetic */ int b;

        AnonymousClass_1(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public void run() {
            DialogStateListener.this.onDialogStateChanged(this.a, this.b);
        }
    }

    final void a(Handler handler) {
        this.a = handler;
    }

    public abstract void onDialogStateChanged(int i, int i2);

    public void onDialogStateChangedRemote(int i, int i2) throws RemoteException {
        if (this.a != null) {
            this.a.post(new AnonymousClass_1(i, i2));
        }
    }
}
