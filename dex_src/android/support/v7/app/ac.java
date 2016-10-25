package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.f;
import android.support.v7.widget.ae;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window.Callback;
import com.xunlei.tdlive.R;
import java.util.ArrayList;

// compiled from: ToolbarActionBar.java
final class ac extends ActionBar {
    ae a;
    Callback b;
    private boolean c;
    private boolean d;
    private ArrayList<Object> e;
    private final Runnable f;

    // compiled from: ToolbarActionBar.java
    private final class a implements android.support.v7.view.menu.m.a {
        private boolean b;

        private a() {
        }

        public final boolean a(f fVar) {
            if (ac.this.b == null) {
                return false;
            }
            ac.this.b.onMenuOpened(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
            return true;
        }

        public final void a(f fVar, boolean z) {
            if (!this.b) {
                this.b = true;
                ac.this.l();
                if (ac.this.b != null) {
                    ac.this.b.onPanelClosed(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
                }
                this.b = false;
            }
        }
    }

    // compiled from: ToolbarActionBar.java
    private final class b implements android.support.v7.view.menu.f.a {
        private b() {
        }

        public final boolean a(f fVar, MenuItem menuItem) {
            return false;
        }

        public final void a(f fVar) {
            if (ac.this.b == null) {
                return;
            }
            if (ac.this.g()) {
                ac.this.b.onPanelClosed(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
            } else if (ac.this.b.onPreparePanel(0, null, fVar)) {
                ac.this.b.onMenuOpened(R.styleable.AppCompatTheme_ratingBarStyleSmall, fVar);
            }
        }
    }

    public final Context c() {
        return this.a.b();
    }

    public final void a(boolean z) {
    }

    public final void b(boolean z) {
    }

    public final void a(Configuration configuration) {
        super.a(configuration);
    }

    public final void a(CharSequence charSequence) {
        this.a.a(charSequence);
    }

    public final boolean f() {
        ViewGroup a = this.a.a();
        if (a == null || a.hasFocus()) {
            return false;
        }
        a.requestFocus();
        return true;
    }

    public final int a() {
        return this.a.m();
    }

    public final boolean b() {
        return this.a.o() == 0;
    }

    public final boolean d() {
        this.a.a().removeCallbacks(this.f);
        ViewCompat.postOnAnimation(this.a.a(), this.f);
        return true;
    }

    public final boolean e() {
        if (!this.a.c()) {
            return false;
        }
        this.a.d();
        return true;
    }

    public final boolean a(int i, KeyEvent keyEvent) {
        if (!this.c) {
            this.a.a(new a(), new b());
            this.c = true;
        }
        Menu p = this.a.p();
        if (p != null) {
            boolean z;
            if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                z = true;
            } else {
                z = false;
            }
            p.setQwertyMode(z);
            p.performShortcut(i, keyEvent, 0);
        }
        return true;
    }

    final void g() {
        this.a.a().removeCallbacks(this.f);
    }

    public final void c(boolean z) {
        if (z != this.d) {
            this.d = z;
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                this.e.get(i);
            }
        }
    }
}
