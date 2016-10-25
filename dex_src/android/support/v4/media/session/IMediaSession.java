package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

public interface IMediaSession extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 31;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 32;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        private static class Proxy implements IMediaSession {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (resultReceiverWrapper != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    resultReceiverWrapper.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_sendCommand, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (keyEvent != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    keyEvent.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_sendMediaButton, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                this.mRemote.transact(TRANSACTION_registerCallbackListener, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                this.mRemote.transact(TRANSACTION_unregisterCallbackListener, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public boolean isTransportControlEnabled() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_isTransportControlEnabled, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            }

            public String getPackageName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getPackageName, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            }

            public String getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getTag, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            }

            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                PendingIntent pendingIntent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getLaunchPendingIntent, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(obtain2);
                } else {
                    pendingIntent = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return pendingIntent;
            }

            public long getFlags() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getFlags, obtain, obtain2, 0);
                obtain2.readException();
                long readLong = obtain2.readLong();
                obtain2.recycle();
                obtain.recycle();
                return readLong;
            }

            public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
                ParcelableVolumeInfo parcelableVolumeInfo;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getVolumeAttributes, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    parcelableVolumeInfo = (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(obtain2);
                } else {
                    parcelableVolumeInfo = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return parcelableVolumeInfo;
            }

            public void adjustVolume(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeInt(i);
                obtain.writeInt(i2);
                obtain.writeString(str);
                this.mRemote.transact(TRANSACTION_adjustVolume, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void setVolumeTo(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeInt(i);
                obtain.writeInt(i2);
                obtain.writeString(str);
                this.mRemote.transact(TRANSACTION_setVolumeTo, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_play, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_playFromMediaId, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_playFromSearch, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (uri != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    uri.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_playFromUri, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void skipToQueueItem(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeLong(j);
                this.mRemote.transact(TRANSACTION_skipToQueueItem, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_pause, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_stop, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_next, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_previous, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void fastForward() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_fastForward, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void rewind() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_rewind, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void seekTo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeLong(j);
                this.mRemote.transact(TRANSACTION_seekTo, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (ratingCompat != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    ratingCompat.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_rate, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_sendCommand);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_sendCustomAction, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            }

            public MediaMetadataCompat getMetadata() throws RemoteException {
                MediaMetadataCompat mediaMetadataCompat;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getMetadata, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    mediaMetadataCompat = (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(obtain2);
                } else {
                    mediaMetadataCompat = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return mediaMetadataCompat;
            }

            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                PlaybackStateCompat playbackStateCompat;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getPlaybackState, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    playbackStateCompat = (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(obtain2);
                } else {
                    playbackStateCompat = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return playbackStateCompat;
            }

            public List<QueueItem> getQueue() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getQueue, obtain, obtain2, 0);
                obtain2.readException();
                List createTypedArrayList = obtain2.createTypedArrayList(QueueItem.CREATOR);
                obtain2.recycle();
                obtain.recycle();
                return createTypedArrayList;
            }

            public CharSequence getQueueTitle() throws RemoteException {
                CharSequence charSequence;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getQueueTitle, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(obtain2);
                } else {
                    charSequence = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return charSequence;
            }

            public Bundle getExtras() throws RemoteException {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getExtras, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                } else {
                    bundle = null;
                }
                obtain2.recycle();
                obtain.recycle();
                return bundle;
            }

            public int getRatingType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_getRatingType, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                obtain2.recycle();
                obtain.recycle();
                return readInt;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession)) ? new Proxy(iBinder) : (IMediaSession) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean sendMediaButton;
            int i4;
            String packageName;
            String readString;
            Bundle bundle;
            switch (i) {
                case TRANSACTION_sendCommand:
                    Bundle bundle2;
                    ResultReceiverWrapper resultReceiverWrapper;
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        resultReceiverWrapper = (ResultReceiverWrapper) ResultReceiverWrapper.CREATOR.createFromParcel(parcel);
                    } else {
                        resultReceiverWrapper = null;
                    }
                    sendCommand(readString2, bundle2, resultReceiverWrapper);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_sendMediaButton:
                    KeyEvent keyEvent;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        keyEvent = (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel);
                    } else {
                        keyEvent = null;
                    }
                    sendMediaButton = sendMediaButton(keyEvent);
                    parcel2.writeNoException();
                    if (sendMediaButton) {
                        i4 = 1;
                    } else {
                        i4 = 0;
                    }
                    parcel2.writeInt(i4);
                    return true;
                case TRANSACTION_registerCallbackListener:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallbackListener(android.support.v4.media.session.IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_unregisterCallbackListener:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallbackListener(android.support.v4.media.session.IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_isTransportControlEnabled:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendMediaButton = isTransportControlEnabled();
                    parcel2.writeNoException();
                    if (sendMediaButton) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_getPackageName:
                    parcel.enforceInterface(DESCRIPTOR);
                    packageName = getPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case TRANSACTION_getTag:
                    parcel.enforceInterface(DESCRIPTOR);
                    packageName = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case TRANSACTION_getLaunchPendingIntent:
                    parcel.enforceInterface(DESCRIPTOR);
                    PendingIntent launchPendingIntent = getLaunchPendingIntent();
                    parcel2.writeNoException();
                    if (launchPendingIntent != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        launchPendingIntent.writeToParcel(parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getFlags:
                    parcel.enforceInterface(DESCRIPTOR);
                    long flags = getFlags();
                    parcel2.writeNoException();
                    parcel2.writeLong(flags);
                    return true;
                case TRANSACTION_getVolumeAttributes:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelableVolumeInfo volumeAttributes = getVolumeAttributes();
                    parcel2.writeNoException();
                    if (volumeAttributes != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        volumeAttributes.writeToParcel(parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_adjustVolume:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustVolume(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setVolumeTo:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolumeTo(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_play:
                    parcel.enforceInterface(DESCRIPTOR);
                    play();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_playFromMediaId:
                    parcel.enforceInterface(DESCRIPTOR);
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    playFromMediaId(readString, bundle);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_playFromSearch:
                    parcel.enforceInterface(DESCRIPTOR);
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    playFromSearch(readString, bundle);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_playFromUri:
                    Uri uri;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        uri = (Uri) Uri.CREATOR.createFromParcel(parcel);
                    } else {
                        uri = null;
                    }
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    playFromUri(uri, bundle);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_skipToQueueItem:
                    parcel.enforceInterface(DESCRIPTOR);
                    skipToQueueItem(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_pause:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_stop:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_next:
                    parcel.enforceInterface(DESCRIPTOR);
                    next();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_previous:
                    parcel.enforceInterface(DESCRIPTOR);
                    previous();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_fastForward:
                    parcel.enforceInterface(DESCRIPTOR);
                    fastForward();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_rewind:
                    parcel.enforceInterface(DESCRIPTOR);
                    rewind();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_seekTo:
                    parcel.enforceInterface(DESCRIPTOR);
                    seekTo(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_rate:
                    RatingCompat ratingCompat;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        ratingCompat = (RatingCompat) RatingCompat.CREATOR.createFromParcel(parcel);
                    } else {
                        ratingCompat = null;
                    }
                    rate(ratingCompat);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_sendCustomAction:
                    parcel.enforceInterface(DESCRIPTOR);
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    sendCustomAction(readString, bundle);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getMetadata:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaMetadataCompat metadata = getMetadata();
                    parcel2.writeNoException();
                    if (metadata != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        metadata.writeToParcel(parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getPlaybackState:
                    parcel.enforceInterface(DESCRIPTOR);
                    PlaybackStateCompat playbackState = getPlaybackState();
                    parcel2.writeNoException();
                    if (playbackState != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        playbackState.writeToParcel(parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getQueue:
                    parcel.enforceInterface(DESCRIPTOR);
                    List queue = getQueue();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(queue);
                    return true;
                case TRANSACTION_getQueueTitle:
                    parcel.enforceInterface(DESCRIPTOR);
                    CharSequence queueTitle = getQueueTitle();
                    parcel2.writeNoException();
                    if (queueTitle != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        TextUtils.writeToParcel(queueTitle, parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getExtras:
                    parcel.enforceInterface(DESCRIPTOR);
                    bundle = getExtras();
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(TRANSACTION_sendCommand);
                        bundle.writeToParcel(parcel2, TRANSACTION_sendCommand);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getRatingType:
                    parcel.enforceInterface(DESCRIPTOR);
                    i4 = getRatingType();
                    parcel2.writeNoException();
                    parcel2.writeInt(i4);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void adjustVolume(int i, int i2, String str) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List<QueueItem> getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String str, Bundle bundle) throws RemoteException;

    void playFromSearch(String str, Bundle bundle) throws RemoteException;

    void playFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat ratingCompat) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long j) throws RemoteException;

    void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void sendCustomAction(String str, Bundle bundle) throws RemoteException;

    boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException;

    void setVolumeTo(int i, int i2, String str) throws RemoteException;

    void skipToQueueItem(long j) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;
}
