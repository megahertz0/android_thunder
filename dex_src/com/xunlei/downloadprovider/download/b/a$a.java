package com.xunlei.downloadprovider.download.b;

import android.os.Handler;
import android.os.Message;
import com.xunlei.xllib.R;
import java.lang.ref.WeakReference;

// compiled from: TaskMonitor.java
private class a$a extends Handler {
    private WeakReference<a> a;

    public a$a(a aVar) {
        this.a = new WeakReference(aVar);
    }

    public final void handleMessage(Message message) {
        a aVar = (a) this.a.get();
        if (aVar != null) {
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyle:
                case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                    if (aVar.a.b) {
                        removeMessages(942910);
                        sendEmptyMessageDelayed(942910, 200);
                        return;
                    }
                    aVar.a(true);
                case 114:
                    int i = message.arg1;
                    int i2 = message.arg2;
                    if (i2 == 0 && i != 0) {
                        ((a) this.a.get()).c();
                    } else if (i2 != 0) {
                        ((a) this.a.get()).a(i2, false);
                    }
                case 942910:
                    if (aVar.a.b) {
                        removeMessages(942910);
                        sendEmptyMessageDelayed(942910, 200);
                        return;
                    }
                    aVar.a(false);
                default:
                    break;
            }
        }
    }
}
