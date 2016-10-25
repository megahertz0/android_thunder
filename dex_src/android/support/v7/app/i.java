package android.support.v7.app;

import android.support.v7.app.a.a;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: AlertController.java
public final class i implements OnItemClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public i(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.u.onClick(this.a.b, i);
        if (!this.b.E) {
            this.a.b.dismiss();
        }
    }
}
