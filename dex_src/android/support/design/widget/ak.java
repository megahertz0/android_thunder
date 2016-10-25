package android.support.design.widget;

import android.support.design.widget.SwipeDismissBehavior.a;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Snackbar.java
final class ak implements a {
    final /* synthetic */ Snackbar a;

    ak(Snackbar snackbar) {
        this.a = snackbar;
    }

    public final void a(View view) {
        view.setVisibility(XZBDevice.Wait);
        Snackbar.a(this.a);
    }

    public final void a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                ar.a().b(this.a.c);
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                ar.a().a(this.a.c);
            default:
                break;
        }
    }
}
