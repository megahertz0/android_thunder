package com.inmobi.ads;

import android.os.Handler;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.af.a;

// compiled from: NativeStrandClientPositioningSource.java
class u implements af {
    private final Handler a;
    private final InMobiClientPositioning b;

    // compiled from: NativeStrandClientPositioningSource.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ a a;

        AnonymousClass_1(a aVar) {
            this.a = aVar;
        }

        public void run() {
            this.a.a(u.this);
        }
    }

    public u(InMobiClientPositioning inMobiClientPositioning) {
        this.a = new Handler();
        this.b = inMobiClientPositioning;
    }

    public void a(long j, a aVar) {
        this.a.post(new AnonymousClass_1(aVar));
    }
}
