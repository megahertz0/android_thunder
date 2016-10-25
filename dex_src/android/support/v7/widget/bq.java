package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

// compiled from: SearchView.java
final class bq implements OnItemSelectedListener {
    final /* synthetic */ SearchView a;

    bq(SearchView searchView) {
        this.a = searchView;
    }

    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        SearchView.b(this.a, i);
    }

    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
