package android.support.v7.widget;

// compiled from: SearchView.java
final class bt implements Runnable {
    final /* synthetic */ SearchView a;

    bt(SearchView searchView) {
        this.a = searchView;
    }

    public final void run() {
        if (this.a.A != null && (this.a.A instanceof cg)) {
            this.a.A.changeCursor(null);
        }
    }
}
