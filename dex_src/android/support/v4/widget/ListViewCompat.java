package android.support.v4.widget;

import android.os.Build.VERSION;
import android.widget.ListView;

public final class ListViewCompat {
    public static void scrollListBy(ListView listView, int i) {
        if (VERSION.SDK_INT >= 19) {
            ListViewCompatKitKat.scrollListBy(listView, i);
        } else {
            ListViewCompatDonut.scrollListBy(listView, i);
        }
    }

    private ListViewCompat() {
    }
}
