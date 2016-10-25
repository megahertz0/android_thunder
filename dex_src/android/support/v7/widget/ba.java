package android.support.v7.widget;

// compiled from: RecyclerView.java
final class ba implements Runnable {
    final /* synthetic */ RecyclerView a;

    ba(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final void run() {
        if (this.a.mFirstLayoutComplete && !this.a.isLayoutRequested()) {
            if (this.a.mLayoutFrozen) {
                this.a.mLayoutRequestEaten = true;
            } else {
                this.a.consumePendingUpdateOperations();
            }
        }
    }
}
