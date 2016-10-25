package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.e.b;
import android.support.v7.widget.RecyclerView.t;

// compiled from: RecyclerView.java
final class bd implements b {
    final /* synthetic */ RecyclerView a;

    bd(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final void a(t tVar, b bVar, b bVar2) {
        this.a.mRecycler.b(tVar);
        this.a.animateDisappearance(tVar, bVar, bVar2);
    }

    public final void b(t tVar, b bVar, b bVar2) {
        this.a.animateAppearance(tVar, bVar, bVar2);
    }

    public final void c(t tVar, b bVar, b bVar2) {
        tVar.setIsRecyclable(false);
        if (this.a.mDataSetHasChangedAfterLayout) {
            if (this.a.mItemAnimator.a(tVar, tVar, bVar, bVar2)) {
                this.a.postAnimationRunner();
            }
        } else if (this.a.mItemAnimator.c(tVar, bVar, bVar2)) {
            this.a.postAnimationRunner();
        }
    }

    public final void a(t tVar) {
        this.a.mLayout.a(tVar.itemView, this.a.mRecycler);
    }
}
