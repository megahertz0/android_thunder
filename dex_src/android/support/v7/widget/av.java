package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

// compiled from: ListPopupWindow.java
final class av implements OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow a;

    av(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i != -1) {
            a a = this.a.d;
            if (a != null) {
                a.h = false;
            }
        }
    }

    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
