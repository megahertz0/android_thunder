package android.support.v7.widget;

// compiled from: RecyclerView.java
final class bb implements Runnable {
    final /* synthetic */ RecyclerView a;

    bb(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final void run() {
        if (this.a.mItemAnimator != null) {
            this.a.mItemAnimator.a();
        }
        this.a.mPostedAnimatorRunner = false;
    }
}
