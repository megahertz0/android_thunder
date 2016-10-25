package android.support.design.widget;

import android.os.Handler.Callback;
import android.os.Message;
import org.android.spdy.SpdyAgent;

// compiled from: SnackbarManager.java
final class as implements Callback {
    final /* synthetic */ ar a;

    as(ar arVar) {
        this.a = arVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_TEST_SERVER:
                ar arVar = this.a;
                b bVar = (b) message.obj;
                synchronized (arVar.a) {
                    if (arVar.b == bVar || arVar.c == bVar) {
                        arVar.a(bVar);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
