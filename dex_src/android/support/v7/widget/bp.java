package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: SearchView.java
final class bp implements OnItemClickListener {
    final /* synthetic */ SearchView a;

    bp(SearchView searchView) {
        this.a = searchView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(i);
    }
}
