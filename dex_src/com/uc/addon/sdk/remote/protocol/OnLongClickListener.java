package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.IOnLongClickListener.Stub;

public abstract class OnLongClickListener extends Stub {
    private Handler a;

    final void a(Handler handler) {
        this.a = handler;
    }

    public abstract void onLongClick();

    public void onLongClickRemote() throws RemoteException {
        if (this.a != null) {
            this.a.post(new Runnable() {
                public void run() {
                    OnLongClickListener.this.onLongClick();
                }
            });
        }
    }
}
