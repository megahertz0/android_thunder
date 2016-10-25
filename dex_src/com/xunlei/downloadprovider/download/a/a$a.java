package com.xunlei.downloadprovider.download.a;

import android.os.AsyncTask;
import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: DownloadCenterControl.java
class a$a extends AsyncTask<a$b, Long, a$b> {
    a$a() {
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        a$b com_xunlei_downloadprovider_download_a_a_b = ((a$b[]) objArr)[0];
        if (com_xunlei_downloadprovider_download_a_a_b != null) {
            d.a();
            com_xunlei_downloadprovider_download_a_a_b.e = d.c(com_xunlei_downloadprovider_download_a_a_b.a);
        }
        return com_xunlei_downloadprovider_download_a_a_b;
    }
}
