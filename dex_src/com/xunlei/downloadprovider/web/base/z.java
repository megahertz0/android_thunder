package com.xunlei.downloadprovider.web.base;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ShortMovieDetailActivity.java
final class z implements OnCompletionListener {
    final /* synthetic */ ShortMovieDetailActivity a;

    z(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.a.A.setVisibility(From.HOME_PAGE_AD.getText().contentEquals(this.a.j) ? XZBDevice.DOWNLOAD_LIST_ALL : 0);
        this.a.B.setVisibility(0);
    }
}
