package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.IOnClickListener.Stub;

public abstract class OnClickListener extends Stub {
    private Handler a;

    final void a(Handler handler) {
        this.a = handler;
    }

    public abstract void onClick();

    public void onClickRemote() throws RemoteException {
        if (this.a != null) {
            this.a.post(new Runnable() {
                public void run() {
                    OnClickListener.this.onClick();
                }
            });
        }
    }
}
