package android.support.v7.app;

import android.content.Context;
import android.support.v7.app.a.a;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// compiled from: AlertController.java
public final class g extends ArrayAdapter<CharSequence> {
    final /* synthetic */ ListView a;
    final /* synthetic */ a b;

    public g(a aVar, Context context, int i, CharSequence[] charSequenceArr, ListView listView) {
        this.b = aVar;
        this.a = listView;
        super(context, i, 16908308, charSequenceArr);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        if (this.b.C != null && this.b.C[i]) {
            this.a.setItemChecked(i, true);
        }
        return view2;
    }
}
