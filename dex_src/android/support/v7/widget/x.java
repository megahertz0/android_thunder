package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: AppCompatSpinner.java
final class x implements OnItemClickListener {
    final /* synthetic */ AppCompatSpinner a;
    final /* synthetic */ b b;

    x(b bVar, AppCompatSpinner appCompatSpinner) {
        this.b = bVar;
        this.a = appCompatSpinner;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.b.setSelection(i);
        if (this.b.b.getOnItemClickListener() != null) {
            this.b.b.performItemClick(view, i, this.b.n.getItemId(i));
        }
        this.b.d();
    }
}
