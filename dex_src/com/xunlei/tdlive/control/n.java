package com.xunlei.tdlive.control;

import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: MagnetView.java
class n extends Handler {
    int a;
    final /* synthetic */ MagnetView b;
    private Matrix c;
    private float d;

    n(MagnetView magnetView) {
        this.b = magnetView;
        this.c = new Matrix();
        this.a = 0;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.c.set(this.b.getImageMatrix());
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                if (this.b.l) {
                    this.b.l = false;
                    this.a = 0;
                    this.d = (float) Math.sqrt(Math.sqrt((double) this.b.i));
                    this.b.a(this.c, this.d);
                    this.b.q.sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
                }
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.b.a(this.c, this.d);
                if (this.a < 4) {
                    this.b.q.sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
                } else {
                    this.b.l = true;
                    if (!(this.b.m || this.b.d == null)) {
                        this.b.d.a();
                    }
                }
                this.a++;
            case SimpleLog.LOG_LEVEL_FATAL:
                if (this.b.l) {
                    this.b.l = false;
                    this.a = 0;
                    this.d = (float) Math.sqrt(Math.sqrt((double) (1.0f / this.b.i)));
                    this.b.a(this.c, this.d);
                    this.b.q.sendEmptyMessage(SimpleLog.LOG_LEVEL_DEBUG);
                    return;
                }
                this.b.q.sendEmptyMessage(SimpleLog.LOG_LEVEL_FATAL);
            default:
                break;
        }
    }
}
