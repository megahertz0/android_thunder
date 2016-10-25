package android.support.v7.widget;

import android.database.DataSetObserver;

// compiled from: ActivityChooserView.java
final class k extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    k(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public final void onChanged() {
        super.onChanged();
        this.a.b.notifyDataSetChanged();
    }

    public final void onInvalidated() {
        super.onInvalidated();
        this.a.b.notifyDataSetInvalidated();
    }
}
