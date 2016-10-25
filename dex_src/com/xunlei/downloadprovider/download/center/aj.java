package com.xunlei.downloadprovider.download.center;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import com.xunlei.downloadprovider.download.tasklist.a.h;

// compiled from: DownloadCenterActivityFragment.java
final class aj implements LoaderCallbacks<Cursor> {
    final /* synthetic */ DownloadCenterActivityFragment$c a;

    aj(DownloadCenterActivityFragment$c downloadCenterActivityFragment$c) {
        this.a = downloadCenterActivityFragment$c;
    }

    public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (loader != null) {
            DownloadCenterActivityFragment$c downloadCenterActivityFragment$c = this.a;
            new StringBuilder("onLoadFinished - TaskLoader : ").append(loader);
            if (h.a() != null) {
                if (downloadCenterActivityFragment$c.b) {
                    downloadCenterActivityFragment$c.b = false;
                    h.a().a(cursor, true);
                } else {
                    h.a().a(cursor, false);
                }
            }
        }
        DownloadCenterActivityFragment.l(this.a.e);
    }

    public final Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return DownloadCenterActivityFragment$c.a(this.a.e.getActivity());
    }

    public final void onLoaderReset(Loader<Cursor> loader) {
        DownloadCenterActivityFragment.l(this.a.e);
        if (loader != null) {
            try {
                loader.stopLoading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
