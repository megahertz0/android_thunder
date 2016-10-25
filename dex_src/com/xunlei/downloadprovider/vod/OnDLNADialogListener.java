package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD;

public interface OnDLNADialogListener {
    void onDialogDismiss(boolean z, MediaPlayerPlayCMD mediaPlayerPlayCMD);

    void onListChange(boolean z);
}
