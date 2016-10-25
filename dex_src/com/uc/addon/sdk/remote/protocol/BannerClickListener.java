package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.IBannerClickListener.Stub;

public abstract class BannerClickListener extends Stub {
    private Handler a;

    class AnonymousClass_1 implements Runnable {
        private /* synthetic */ int a;

        AnonymousClass_1(int i) {
            this.a = i;
        }

        public void run() {
            BannerClickListener.this.onBannerClick(this.a);
        }
    }

    final void a(Handler handler) {
        this.a = handler;
    }

    public abstract void onBannerClick(int i);

    public void onBannerClickRemote(int i) throws RemoteException {
        if (this.a != null) {
            this.a.post(new AnonymousClass_1(i));
        }
    }

    public abstract void onBannerDismiss();

    public void onBannerDismissRemote() throws RemoteException {
        if (this.a != null) {
            this.a.post(new Runnable() {
                public void run() {
                    BannerClickListener.this.onBannerDismiss();
                }
            });
        }
    }
}
