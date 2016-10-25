package android.support.design.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BottomSheetDialog.java
final class h implements OnClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (this.a.isShowing()) {
            this.a.cancel();
        }
    }
}
