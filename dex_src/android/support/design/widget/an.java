package android.support.design.widget;

// compiled from: Snackbar.java
final class an implements b {
    final /* synthetic */ Snackbar a;

    an(Snackbar snackbar) {
        this.a = snackbar;
    }

    public final void a() {
        this.a.b.setOnLayoutChangeListener(null);
        if (this.a.d()) {
            this.a.a();
        } else {
            this.a.b();
        }
    }
}
