package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;
import com.xunlei.tdlive.R;

public abstract class TransportPerformer {
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public abstract boolean onIsPlaying();

    public abstract void onPause();

    public abstract void onSeekTo(long j);

    public abstract void onStart();

    public abstract void onStop();

    public int onGetBufferPercentage() {
        return R.styleable.AppCompatTheme_buttonStyle;
    }

    public int onGetTransportControlFlags() {
        return R.styleable.AppCompatTheme_popupMenuStyle;
    }

    public boolean onMediaButtonDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case R.styleable.AppCompatTheme_panelMenuListWidth:
            case R.styleable.AppCompatTheme_colorControlNormal:
                if (onIsPlaying()) {
                    onPause();
                } else {
                    onStart();
                }
                break;
            case R.styleable.AppCompatTheme_colorControlActivated:
                onStop();
                break;
            case TransportMediator.KEYCODE_MEDIA_PLAY:
                onStart();
                break;
            case TransportMediator.KEYCODE_MEDIA_PAUSE:
                onPause();
                break;
        }
        return true;
    }

    public boolean onMediaButtonUp(int i, KeyEvent keyEvent) {
        return true;
    }

    public void onAudioFocusChange(int i) {
        Object obj = null;
        switch (i) {
            case AUDIOFOCUS_LOSS:
                obj = TransportMediator.KEYCODE_MEDIA_PAUSE;
                break;
        }
        if (obj != null) {
            long uptimeMillis = SystemClock.uptimeMillis();
            onMediaButtonDown(TransportMediator.KEYCODE_MEDIA_PAUSE, new KeyEvent(uptimeMillis, uptimeMillis, 0, 127, 0));
            onMediaButtonUp(TransportMediator.KEYCODE_MEDIA_PAUSE, new KeyEvent(uptimeMillis, uptimeMillis, 1, 127, 0));
        }
    }
}
