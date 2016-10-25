package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat.CustomAction;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_FROM_URI = 8192;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Creator<PlaybackStateCompat> CREATOR;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    private final long mActions;
    private final long mActiveItemId;
    private final long mBufferedPosition;
    private List<CustomAction> mCustomActions;
    private final CharSequence mErrorMessage;
    private final Bundle mExtras;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private Object mStateObj;
    private final long mUpdateTime;

    @Retention(RetentionPolicy.SOURCE)
    public static @interface Actions {
    }

    public static final class Builder {
        private long mActions;
        private long mActiveItemId;
        private long mBufferedPosition;
        private final List<CustomAction> mCustomActions;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder() {
            this.mCustomActions = new ArrayList();
            this.mActiveItemId = -1;
        }

        public Builder(PlaybackStateCompat playbackStateCompat) {
            this.mCustomActions = new ArrayList();
            this.mActiveItemId = -1;
            this.mState = playbackStateCompat.mState;
            this.mPosition = playbackStateCompat.mPosition;
            this.mRate = playbackStateCompat.mSpeed;
            this.mUpdateTime = playbackStateCompat.mUpdateTime;
            this.mBufferedPosition = playbackStateCompat.mBufferedPosition;
            this.mActions = playbackStateCompat.mActions;
            this.mErrorMessage = playbackStateCompat.mErrorMessage;
            if (playbackStateCompat.mCustomActions != null) {
                this.mCustomActions.addAll(playbackStateCompat.mCustomActions);
            }
            this.mActiveItemId = playbackStateCompat.mActiveItemId;
            this.mExtras = playbackStateCompat.mExtras;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setState(int i, long j, float f) {
            return setState(i, j, f, SystemClock.elapsedRealtime());
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setState(int i, long j, float f, long j2) {
            this.mState = i;
            this.mPosition = j;
            this.mUpdateTime = j2;
            this.mRate = f;
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setBufferedPosition(long j) {
            this.mBufferedPosition = j;
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setActions(long j) {
            this.mActions = j;
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder addCustomAction(String str, String str2, int i) {
            return addCustomAction(new CustomAction(str2, i, null, null));
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder addCustomAction(CustomAction customAction) {
            if (customAction == null) {
                throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
            }
            this.mCustomActions.add(customAction);
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setActiveQueueItemId(long j) {
            this.mActiveItemId = j;
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setErrorMessage(CharSequence charSequence) {
            this.mErrorMessage = charSequence;
            return this;
        }

        public final android.support.v4.media.session.PlaybackStateCompat.Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public final PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras, null);
        }
    }

    public static final class CustomAction implements Parcelable {
        public static final Creator<android.support.v4.media.session.PlaybackStateCompat.CustomAction> CREATOR;
        private final String mAction;
        private Object mCustomActionObj;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        public static final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String str, CharSequence charSequence, int i) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
                } else if (TextUtils.isEmpty(charSequence)) {
                    throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
                } else if (i == 0) {
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                } else {
                    this.mAction = str;
                    this.mName = charSequence;
                    this.mIcon = i;
                }
            }

            public final android.support.v4.media.session.PlaybackStateCompat.CustomAction.Builder setExtras(Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }

            public final android.support.v4.media.session.PlaybackStateCompat.CustomAction build() {
                return new android.support.v4.media.session.PlaybackStateCompat.CustomAction(this.mName, this.mIcon, this.mExtras, null);
            }
        }

        private CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.mAction = str;
            this.mName = charSequence;
            this.mIcon = i;
            this.mExtras = bundle;
        }

        private CustomAction(Parcel parcel) {
            this.mAction = parcel.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.mIcon = parcel.readInt();
            this.mExtras = parcel.readBundle();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, parcel, i);
            parcel.writeInt(this.mIcon);
            parcel.writeBundle(this.mExtras);
        }

        public final int describeContents() {
            return STATE_NONE;
        }

        public static android.support.v4.media.session.PlaybackStateCompat.CustomAction fromCustomAction(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            android.support.v4.media.session.PlaybackStateCompat.CustomAction customAction = new android.support.v4.media.session.PlaybackStateCompat.CustomAction(CustomAction.getAction(obj), CustomAction.getName(obj), CustomAction.getIcon(obj), CustomAction.getExtras(obj));
            customAction.mCustomActionObj = obj;
            return customAction;
        }

        public final Object getCustomAction() {
            if (this.mCustomActionObj != null || VERSION.SDK_INT < 21) {
                return this.mCustomActionObj;
            }
            this.mCustomActionObj = CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
            return this.mCustomActionObj;
        }

        static {
            CREATOR = new Creator<android.support.v4.media.session.PlaybackStateCompat.CustomAction>() {
                public final android.support.v4.media.session.PlaybackStateCompat.CustomAction createFromParcel(Parcel parcel) {
                    return new android.support.v4.media.session.PlaybackStateCompat.CustomAction(null);
                }

                public final android.support.v4.media.session.PlaybackStateCompat.CustomAction[] newArray(int i) {
                    return new android.support.v4.media.session.PlaybackStateCompat.CustomAction[i];
                }
            };
        }

        public final String getAction() {
            return this.mAction;
        }

        public final CharSequence getName() {
            return this.mName;
        }

        public final int getIcon() {
            return this.mIcon;
        }

        public final Bundle getExtras() {
            return this.mExtras;
        }

        public final String toString() {
            return new StringBuilder("Action:mName='").append(this.mName).append(", mIcon=").append(this.mIcon).append(", mExtras=").append(this.mExtras).toString();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public static @interface State {
    }

    private PlaybackStateCompat(int i, long j, long j2, float f, long j3, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.mState = i;
        this.mPosition = j;
        this.mBufferedPosition = j2;
        this.mSpeed = f;
        this.mActions = j3;
        this.mErrorMessage = charSequence;
        this.mUpdateTime = j4;
        this.mCustomActions = new ArrayList(list);
        this.mActiveItemId = j5;
        this.mExtras = bundle;
    }

    private PlaybackStateCompat(Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mCustomActions = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.mActiveItemId = parcel.readLong();
        this.mExtras = parcel.readBundle();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
        stringBuilder.append("state=").append(this.mState);
        stringBuilder.append(", position=").append(this.mPosition);
        stringBuilder.append(", buffered position=").append(this.mBufferedPosition);
        stringBuilder.append(", speed=").append(this.mSpeed);
        stringBuilder.append(", updated=").append(this.mUpdateTime);
        stringBuilder.append(", actions=").append(this.mActions);
        stringBuilder.append(", error=").append(this.mErrorMessage);
        stringBuilder.append(", custom actions=").append(this.mCustomActions);
        stringBuilder.append(", active item id=").append(this.mActiveItemId);
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }

    public final int describeContents() {
        return STATE_NONE;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mBufferedPosition);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, parcel, i);
        parcel.writeTypedList(this.mCustomActions);
        parcel.writeLong(this.mActiveItemId);
        parcel.writeBundle(this.mExtras);
    }

    public final int getState() {
        return this.mState;
    }

    public final long getPosition() {
        return this.mPosition;
    }

    public final long getBufferedPosition() {
        return this.mBufferedPosition;
    }

    public final float getPlaybackSpeed() {
        return this.mSpeed;
    }

    public final long getActions() {
        return this.mActions;
    }

    public final List<CustomAction> getCustomActions() {
        return this.mCustomActions;
    }

    public final CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public final long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    public final long getActiveQueueItemId() {
        return this.mActiveItemId;
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj) {
        if (obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> customActions = PlaybackStateCompatApi21.getCustomActions(obj);
        List list = null;
        if (customActions != null) {
            list = new ArrayList(customActions.size());
            for (Object obj2 : customActions) {
                list.add(CustomAction.fromCustomAction(obj2));
            }
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(obj), PlaybackStateCompatApi21.getPosition(obj), PlaybackStateCompatApi21.getBufferedPosition(obj), PlaybackStateCompatApi21.getPlaybackSpeed(obj), PlaybackStateCompatApi21.getActions(obj), PlaybackStateCompatApi21.getErrorMessage(obj), PlaybackStateCompatApi21.getLastPositionUpdateTime(obj), list, PlaybackStateCompatApi21.getActiveQueueItemId(obj), VERSION.SDK_INT >= 22 ? PlaybackStateCompatApi22.getExtras(obj) : null);
        playbackStateCompat.mStateObj = obj;
        return playbackStateCompat;
    }

    public final Object getPlaybackState() {
        if (this.mStateObj != null || VERSION.SDK_INT < 21) {
            return this.mStateObj;
        }
        List list = null;
        if (this.mCustomActions != null) {
            list = new ArrayList(this.mCustomActions.size());
            for (CustomAction customAction : this.mCustomActions) {
                list.add(customAction.getCustomAction());
            }
        }
        if (VERSION.SDK_INT >= 22) {
            this.mStateObj = PlaybackStateCompatApi22.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, list, this.mActiveItemId, this.mExtras);
        } else {
            this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, list, this.mActiveItemId);
        }
        return this.mStateObj;
    }

    static {
        CREATOR = new Creator<PlaybackStateCompat>() {
            public final PlaybackStateCompat createFromParcel(Parcel parcel) {
                return new PlaybackStateCompat(null);
            }

            public final PlaybackStateCompat[] newArray(int i) {
                return new PlaybackStateCompat[i];
            }
        };
    }
}
