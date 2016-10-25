package com.aplayer.aplayerandroid;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

// compiled from: APlayerAndroid.java
final class c implements Callback {
    final /* synthetic */ APlayerAndroid a;

    c(APlayerAndroid aPlayerAndroid) {
        this.a = aPlayerAndroid;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        APlayerAndroid.access$0();
        new StringBuilder("surface Changed format=").append(i).append(", width=").append(i2).append(", height=").append(i3);
        if (!APlayerAndroid.access$1(this.a)) {
            APlayerAndroid.access$19(this.a, APlayerAndroid.access$18(this.a), APlayerAndroid.access$2(this.a));
            APlayerAndroid.access$20(this.a);
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        APlayerAndroid.access$0();
        if (!APlayerAndroid.access$1(this.a)) {
            APlayerAndroid.access$21(this.a, surfaceHolder.getSurface());
            if (this.a.IsSystemPlayer()) {
                APlayerAndroid.access$16(this.a).a(APlayerAndroid.access$18(this.a));
            }
            APlayerAndroid.access$19(this.a, APlayerAndroid.access$18(this.a), APlayerAndroid.access$2(this.a));
            int GetState = this.a.GetState();
            if ((GetState == 3 || GetState == 2 || GetState == 5 || GetState == 4) && APlayerAndroid.access$22(this.a)) {
                APlayerAndroid.access$0();
                i access$23 = APlayerAndroid.access$23(this.a);
                if (access$23.a != null) {
                    access$23.a(access$23.c);
                }
                this.a.SetPosition(APlayerAndroid.access$24(this.a));
            }
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        APlayerAndroid.access$19(this.a, null, APlayerAndroid.access$2(this.a));
        if (!this.a.IsSystemPlayer() && APlayerAndroid.access$22(this.a)) {
            APlayerAndroid.access$0();
            APlayerAndroid.access$23(this.a).c();
            APlayerAndroid.access$25(this.a, this.a.GetPosition());
        }
        if (APlayerAndroid.access$26(this.a) != null) {
            APlayerAndroid.access$26(this.a);
        }
    }
}
