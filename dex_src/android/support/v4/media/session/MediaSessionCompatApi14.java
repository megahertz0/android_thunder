package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Bundle;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class MediaSessionCompatApi14 {
    private static final long ACTION_FAST_FORWARD = 64;
    private static final long ACTION_PAUSE = 2;
    private static final long ACTION_PLAY = 4;
    private static final long ACTION_PLAY_PAUSE = 512;
    private static final long ACTION_REWIND = 8;
    private static final long ACTION_SKIP_TO_NEXT = 32;
    private static final long ACTION_SKIP_TO_PREVIOUS = 16;
    private static final long ACTION_STOP = 1;
    private static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    private static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    private static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    private static final String METADATA_KEY_ART = "android.media.metadata.ART";
    private static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    private static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    private static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    private static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    private static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    private static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    private static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    private static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    private static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    private static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    private static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    static final int RCC_PLAYSTATE_NONE = 0;
    static final int STATE_BUFFERING = 6;
    static final int STATE_CONNECTING = 8;
    static final int STATE_ERROR = 7;
    static final int STATE_FAST_FORWARDING = 4;
    static final int STATE_NONE = 0;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 3;
    static final int STATE_REWINDING = 5;
    static final int STATE_SKIPPING_TO_NEXT = 10;
    static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    static final int STATE_STOPPED = 1;

    MediaSessionCompatApi14() {
    }

    public static Object createRemoteControlClient(PendingIntent pendingIntent) {
        return new RemoteControlClient(pendingIntent);
    }

    public static void setState(Object obj, int i) {
        ((RemoteControlClient) obj).setPlaybackState(getRccStateFromState(i));
    }

    public static void setTransportControlFlags(Object obj, long j) {
        ((RemoteControlClient) obj).setTransportControlFlags(getRccTransportControlFlagsFromActions(j));
    }

    public static void setMetadata(Object obj, Bundle bundle) {
        MetadataEditor editMetadata = ((RemoteControlClient) obj).editMetadata(true);
        buildOldMetadata(bundle, editMetadata);
        editMetadata.apply();
    }

    public static void registerRemoteControlClient(Context context, Object obj) {
        ((AudioManager) context.getSystemService("audio")).registerRemoteControlClient((RemoteControlClient) obj);
    }

    public static void unregisterRemoteControlClient(Context context, Object obj) {
        ((AudioManager) context.getSystemService("audio")).unregisterRemoteControlClient((RemoteControlClient) obj);
    }

    static int getRccStateFromState(int i) {
        switch (i) {
            case STATE_NONE:
                return STATE_NONE;
            case STATE_STOPPED:
                return STATE_STOPPED;
            case STATE_PAUSED:
                return STATE_PAUSED;
            case STATE_PLAYING:
                return STATE_PLAYING;
            case STATE_FAST_FORWARDING:
                return STATE_FAST_FORWARDING;
            case STATE_REWINDING:
                return STATE_REWINDING;
            case STATE_BUFFERING:
            case STATE_CONNECTING:
                return STATE_CONNECTING;
            case STATE_ERROR:
                return STATE_SKIPPING_TO_PREVIOUS;
            case STATE_SKIPPING_TO_PREVIOUS:
                return STATE_ERROR;
            case STATE_SKIPPING_TO_NEXT:
            case STATE_SKIPPING_TO_QUEUE_ITEM:
                return STATE_BUFFERING;
            default:
                return -1;
        }
    }

    static int getRccTransportControlFlagsFromActions(long j) {
        int i = STATE_NONE;
        if ((1 & j) != 0) {
            i = R.styleable.AppCompatTheme_actionModeCutDrawable;
        }
        if ((2 & j) != 0) {
            i |= 16;
        }
        if ((4 & j) != 0) {
            i |= 4;
        }
        if ((8 & j) != 0) {
            i |= 2;
        }
        if ((16 & j) != 0) {
            i |= 1;
        }
        if ((32 & j) != 0) {
            i |= 128;
        }
        if ((64 & j) != 0) {
            i |= 64;
        }
        return (512 & j) != 0 ? i | 8 : i;
    }

    static void buildOldMetadata(Bundle bundle, MetadataEditor metadataEditor) {
        if (bundle != null) {
            if (bundle.containsKey(METADATA_KEY_ART)) {
                metadataEditor.putBitmap(R.styleable.AppCompatTheme_buttonStyle, (Bitmap) bundle.getParcelable(METADATA_KEY_ART));
            } else if (bundle.containsKey(METADATA_KEY_ALBUM_ART)) {
                metadataEditor.putBitmap(R.styleable.AppCompatTheme_buttonStyle, (Bitmap) bundle.getParcelable(METADATA_KEY_ALBUM_ART));
            }
            if (bundle.containsKey(METADATA_KEY_ALBUM)) {
                metadataEditor.putString(STATE_STOPPED, bundle.getString(METADATA_KEY_ALBUM));
            }
            if (bundle.containsKey(METADATA_KEY_ALBUM_ARTIST)) {
                metadataEditor.putString(XZBDevice.Upload, bundle.getString(METADATA_KEY_ALBUM_ARTIST));
            }
            if (bundle.containsKey(METADATA_KEY_ARTIST)) {
                metadataEditor.putString(STATE_PAUSED, bundle.getString(METADATA_KEY_ARTIST));
            }
            if (bundle.containsKey(METADATA_KEY_AUTHOR)) {
                metadataEditor.putString(STATE_PLAYING, bundle.getString(METADATA_KEY_AUTHOR));
            }
            if (bundle.containsKey(METADATA_KEY_COMPILATION)) {
                metadataEditor.putString(XZBDevice.Delete, bundle.getString(METADATA_KEY_COMPILATION));
            }
            if (bundle.containsKey(METADATA_KEY_COMPOSER)) {
                metadataEditor.putString(STATE_FAST_FORWARDING, bundle.getString(METADATA_KEY_COMPOSER));
            }
            if (bundle.containsKey(METADATA_KEY_DATE)) {
                metadataEditor.putString(STATE_REWINDING, bundle.getString(METADATA_KEY_DATE));
            }
            if (bundle.containsKey(METADATA_KEY_DISC_NUMBER)) {
                metadataEditor.putLong(XZBDevice.Predownload, bundle.getLong(METADATA_KEY_DISC_NUMBER));
            }
            if (bundle.containsKey(METADATA_KEY_DURATION)) {
                metadataEditor.putLong(STATE_SKIPPING_TO_PREVIOUS, bundle.getLong(METADATA_KEY_DURATION));
            }
            if (bundle.containsKey(METADATA_KEY_GENRE)) {
                metadataEditor.putString(STATE_BUFFERING, bundle.getString(METADATA_KEY_GENRE));
            }
            if (bundle.containsKey(METADATA_KEY_TITLE)) {
                metadataEditor.putString(STATE_ERROR, bundle.getString(METADATA_KEY_TITLE));
            }
            if (bundle.containsKey(METADATA_KEY_TRACK_NUMBER)) {
                metadataEditor.putLong(STATE_NONE, bundle.getLong(METADATA_KEY_TRACK_NUMBER));
            }
            if (bundle.containsKey(METADATA_KEY_WRITER)) {
                metadataEditor.putString(STATE_SKIPPING_TO_QUEUE_ITEM, bundle.getString(METADATA_KEY_WRITER));
            }
        }
    }
}
