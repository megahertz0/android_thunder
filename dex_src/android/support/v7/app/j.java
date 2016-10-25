package android.support.v7.app;

import android.support.v7.app.a.a;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// compiled from: AlertController.java
public final class j implements OnItemClickListener {
    final /* synthetic */ ListView a;
    final /* synthetic */ a b;
    final /* synthetic */ a c;

    public j(a aVar, ListView listView, a aVar2) {
        this.c = aVar;
        this.a = listView;
        this.b = aVar2;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.c.C != null) {
            this.c.C[i] = this.a.isItemChecked(i);
        }
        this.c.G.onClick(this.b.b, i, this.a.isItemChecked(i));
    }
}
