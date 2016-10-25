package com.xunlei.downloadprovider.b.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xunlei.xllib.R;

// compiled from: BpRunnerManager.java
final class c extends Handler {
    final /* synthetic */ b a;

    c(b bVar, Looper looper) {
        this.a = bVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                b bVar = this.a;
                bVar.c--;
                this.a.a((d) message.obj);
                this.a.b();
                break;
        }
        super.handleMessage(message);
    }
}
