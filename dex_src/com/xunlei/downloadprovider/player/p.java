package com.xunlei.downloadprovider.player;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.android.spdy.SpdyProtocol;

// compiled from: MediaPlayerGestureView.java
final class p extends AnimatorListenerAdapter {
    final /* synthetic */ View a;
    final /* synthetic */ MediaPlayerGestureView b;

    p(MediaPlayerGestureView mediaPlayerGestureView, View view) {
        this.b = mediaPlayerGestureView;
        this.a = view;
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
