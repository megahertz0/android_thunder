package com.xunlei.downloadprovider.player;

import android.content.Context;
import android.view.OrientationEventListener;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ThunderMediaPlayer.java
final class af extends OrientationEventListener {
    final /* synthetic */ ab a;

    af(ab abVar, Context context) {
        this.a = abVar;
        super(context);
    }

    public final void onOrientationChanged(int i) {
        if (i != -1) {
            if ((i >= 0 && i <= 30) || i >= 330) {
                if (this.a.w) {
                    if (this.a.v == 2) {
                        this.a.w = false;
                    }
                } else if (this.a.l) {
                    if (this.a.y) {
                        this.a.q().setRequestedOrientation(1);
                    } else {
                        this.a.l();
                    }
                }
                this.a.v = 1;
            } else if ((i >= 60 && i <= 120) || (i >= 240 && i <= 300)) {
                if (this.a.x) {
                    if (this.a.v == 1) {
                        this.a.x = false;
                    }
                } else if (this.a.y) {
                    this.a.q().setRequestedOrientation(SimpleLog.LOG_LEVEL_FATAL);
                } else {
                    this.a.k();
                }
                this.a.v = 2;
            }
        }
    }
}
