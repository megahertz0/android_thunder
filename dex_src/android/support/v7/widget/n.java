package android.support.v7.widget;

import android.database.DataSetObserver;

// compiled from: ActivityChooserView.java
final class n extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    n(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public final void onChanged() {
        super.onChanged();
        ActivityChooserView.c(this.a);
    }
}
