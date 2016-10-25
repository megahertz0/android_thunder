package com.xunlei.downloadprovider.download.center.widget;

import com.xunlei.downloadprovider.R;
import com.xunlei.xllib.b.e;

// compiled from: DownloadStorageView.java
public final class u extends a {
    final /* synthetic */ DownloadStorageView a;

    public u(DownloadStorageView downloadStorageView) {
        this.a = downloadStorageView;
    }

    protected final /* bridge */ /* synthetic */ void onCancelled(Object obj) {
        super.onCancelled((b) obj);
        this.a.a = null;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        b bVar = (b) obj;
        this.a.a = null;
        long j = bVar.a;
        long j2 = j + bVar.b;
        this.a.setProgress((int) (j2 == 0 ? 0.0f : (float) ((100 * j) / j2)));
        this.a.setUsedStorageText(this.a.getContext().getString(R.string.download_storage_used_fmt, new Object[]{e.a(j)}));
        this.a.setRemainStorageText(this.a.getContext().getString(R.string.download_storage_remain_fmt, new Object[]{e.a(r4)}));
    }

    protected final void onCancelled() {
        super.onCancelled();
        this.a.a = null;
    }
}
