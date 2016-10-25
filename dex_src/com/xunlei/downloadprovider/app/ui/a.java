package com.xunlei.downloadprovider.app.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.h.b;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

// compiled from: FileExplorerListView.java
public final class a implements OnItemClickListener {
    final /* synthetic */ FileExplorerListView a;

    public a(FileExplorerListView fileExplorerListView) {
        this.a = fileExplorerListView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (FileExplorerListView.a(this.a) == 1000 || FileExplorerListView.a(this.a) == 1001) {
            i--;
        }
        if (i >= 0 && i < FileExplorerListView.b(this.a).size()) {
            FileExplorerListView.a(this.a, (String) ((Map) FileExplorerListView.b(this.a).get(i)).get("info"));
            if (((Integer) ((Map) FileExplorerListView.b(this.a).get(i)).get(AgooConstants.MESSAGE_TYPE)).intValue() == 0) {
                FileExplorerListView.a(this.a, (String) ((Map) FileExplorerListView.b(this.a).get(i)).get("info"));
                new StringBuilder("mDir = ").append(FileExplorerListView.c(this.a));
                FileExplorerListView.a(this.a, FileExplorerListView.d(this.a));
                FileExplorerListView.e(this.a);
                FileExplorerListView.f(this.a).notifyDataSetChanged();
                return;
            }
            String str = (String) ((Map) FileExplorerListView.b(this.a).get(i)).get("fileName");
            int lastIndexOf = str.lastIndexOf(".");
            if (-1 == lastIndexOf || !str.substring(lastIndexOf + 1).equals("torrent") || FileExplorerListView.g(this.a) == null) {
                b.a(FileExplorerListView.c(this.a), null, true);
            } else {
                FileExplorerListView.g(this.a).onClick(FileExplorerListView.c(this.a));
            }
        }
    }
}
