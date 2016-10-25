package com.xunlei.downloadprovider.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

public class MediaPlayerErrorView extends FrameLayout implements t {
    private ab a;

    public MediaPlayerErrorView(Context context) {
        super(context);
        a(context);
    }

    public MediaPlayerErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MediaPlayerErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.media_player_error_view, this, true);
        findViewById(R.id.retry_btn).setOnClickListener(new m(this));
        setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setMediaPlayer(ab abVar) {
        this.a = abVar;
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        if (mediaPlayerState2 == MediaPlayerState.ERROR) {
            setVisibility(0);
        } else {
            setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }
}
