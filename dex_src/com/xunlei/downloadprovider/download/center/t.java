package com.xunlei.downloadprovider.download.center;

import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;

// compiled from: DownloadCenterActivityFragment.java
final class t implements OnDismissListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    t(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onDismiss() {
        LayoutParams attributes = this.a.getActivity().getWindow().getAttributes();
        attributes.alpha = 1.0f;
        this.a.getActivity().getWindow().setAttributes(attributes);
    }
}
