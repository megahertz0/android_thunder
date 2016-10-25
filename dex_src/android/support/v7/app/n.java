package android.support.v7.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.view.g;
import android.support.v7.view.j;
import android.support.v7.view.menu.f;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

// compiled from: AppCompatDelegateImplBase.java
abstract class n extends m {
    final Context b;
    final Window c;
    final Callback d;
    final Callback e;
    final l f;
    ActionBar g;
    MenuInflater h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    boolean m;
    CharSequence n;
    boolean o;

    // compiled from: AppCompatDelegateImplBase.java
    class a extends j {
        a(Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return n.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || n.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            return (i != 0 || (menu instanceof f)) ? super.onCreatePanelMenu(i, menu) : false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            f fVar;
            if (menu instanceof f) {
                fVar = (f) menu;
            } else {
                fVar = null;
            }
            if (i == 0 && fVar == null) {
                return false;
            }
            if (fVar != null) {
                fVar.l = true;
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (fVar == null) {
                return onPreparePanel;
            }
            fVar.l = false;
            return onPreparePanel;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            n.this.e(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            n.this.d(i);
        }
    }

    abstract boolean a(int i, KeyEvent keyEvent);

    abstract boolean a(KeyEvent keyEvent);

    abstract void b(CharSequence charSequence);

    abstract void d(int i);

    abstract boolean e(int i);

    abstract void k();

    n(Context context, Window window, l lVar) {
        this.b = context;
        this.c = window;
        this.f = lVar;
        this.d = this.c.getCallback();
        if (this.d instanceof a) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.e = a(this.d);
        this.c.setCallback(this.e);
    }

    Callback a(Callback callback) {
        return new a(callback);
    }

    public final ActionBar a() {
        k();
        return this.g;
    }

    public final MenuInflater b() {
        if (this.h == null) {
            k();
            this.h = new g(this.g != null ? this.g.c() : this.b);
        }
        return this.h;
    }

    final Context l() {
        Context context = null;
        ActionBar a = a();
        if (a != null) {
            context = a.c();
        }
        return context == null ? this.b : context;
    }

    public void g() {
        this.o = true;
    }

    public boolean i() {
        return false;
    }

    public final void a(CharSequence charSequence) {
        this.n = charSequence;
        b(charSequence);
    }

    public void b(Bundle bundle) {
    }
}
