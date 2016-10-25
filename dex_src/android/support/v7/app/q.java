package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;
import org.android.spdy.SpdyAgent;

// compiled from: AppCompatDelegateImplV23.java
final class q extends p {
    private final UiModeManager w;

    // compiled from: AppCompatDelegateImplV23.java
    class a extends a {
        a(Callback callback) {
            super(callback);
        }

        public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (q.this.p) {
                switch (i) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        return a(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }
    }

    q(Context context, Window window, l lVar) {
        super(context, window, lVar);
        this.w = (UiModeManager) context.getSystemService("uimode");
    }

    final Callback a(Callback callback) {
        return new a(callback);
    }

    final int f(int i) {
        return (i == 0 && this.w.getNightMode() == 0) ? -1 : super.f(i);
    }
}
