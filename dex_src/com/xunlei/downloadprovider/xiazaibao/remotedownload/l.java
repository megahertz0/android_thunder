package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.q.a;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBottomView;
import java.util.List;

// compiled from: RemoteDownloadContainerFragment.java
final class l implements a {
    final /* synthetic */ RemoteDownloadContainerFragment a;

    l(RemoteDownloadContainerFragment remoteDownloadContainerFragment) {
        this.a = remoteDownloadContainerFragment;
    }

    public final void a() {
        boolean z;
        RemoteDownloadListFragment b = this.a.g.b();
        q qVar = b.b;
        int size = qVar.c.size();
        List d = qVar.d();
        if (qVar.a()) {
            size--;
        }
        if (qVar.c.contains(qVar.b)) {
            size--;
        }
        if (d.size() == size) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            RemoteDownloadContainerFragment.b(this.a).a(false);
        } else {
            RemoteDownloadContainerFragment.b(this.a).a(true);
        }
        if (b.b.d().isEmpty()) {
            RemoteDownloadContainerFragment.c(this.a).a();
            RemoteDownloadContainerFragment.b(this.a).setTitle(this.a.getResources().getString(R.string.download_list_select_title));
            return;
        }
        XZBDownloadBottomView c = RemoteDownloadContainerFragment.c(this.a);
        c.b.setClickable(true);
        c.d.setClickable(true);
        c.g.setClickable(true);
        c.j.setClickable(true);
        c.c.setEnabled(true);
        c.f.setEnabled(true);
        c.i.setEnabled(true);
        c.l.setEnabled(true);
        c.a.setTextColor(c.getResources().getColor(R.color.download_list_bottom_enable));
        c.e.setTextColor(c.getResources().getColor(R.color.download_list_bottom_enable));
        c.h.setTextColor(c.getResources().getColor(R.color.download_list_bottom_enable));
        c.k.setTextColor(c.getResources().getColor(R.color.download_list_bottom_enable));
        RemoteDownloadContainerFragment.b(this.a).setTitle(this.a.getActivity().getResources().getString(R.string.download_list_selected_file, new Object[]{Integer.valueOf(b.b.d().size())}));
    }
}
