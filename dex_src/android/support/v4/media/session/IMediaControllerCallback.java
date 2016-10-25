package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.text.TextUtils;
import java.util.List;

public interface IMediaControllerCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControllerCallback {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        private static class Proxy implements IMediaControllerCallback {
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

            public void onEvent(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onEvent, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onSessionDestroyed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                this.mRemote.transact(TRANSACTION_onSessionDestroyed, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (playbackStateCompat != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    playbackStateCompat.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onPlaybackStateChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (mediaMetadataCompat != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    mediaMetadataCompat.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onMetadataChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onQueueChanged(List<QueueItem> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeTypedList(list);
                this.mRemote.transact(TRANSACTION_onQueueChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (charSequence != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    TextUtils.writeToParcel(charSequence, obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onQueueTitleChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (bundle != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onExtrasChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                obtain.writeInterfaceToken(DESCRIPTOR);
                if (parcelableVolumeInfo != null) {
                    obtain.writeInt(TRANSACTION_onEvent);
                    parcelableVolumeInfo.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(TRANSACTION_onVolumeInfoChanged, obtain, null, TRANSACTION_onEvent);
                obtain.recycle();
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaControllerCallback)) ? new Proxy(iBinder) : (IMediaControllerCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ParcelableVolumeInfo parcelableVolumeInfo = null;
            Bundle bundle;
            switch (i) {
                case TRANSACTION_onEvent:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    onEvent(readString, bundle);
                    return true;
                case TRANSACTION_onSessionDestroyed:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionDestroyed();
                    return true;
                case TRANSACTION_onPlaybackStateChanged:
                    PlaybackStateCompat playbackStateCompat;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        playbackStateCompat = (PlaybackStateCompat) PlaybackStateCompat.CREATOR.createFromParcel(parcel);
                    }
                    onPlaybackStateChanged(playbackStateCompat);
                    return true;
                case TRANSACTION_onMetadataChanged:
                    MediaMetadataCompat mediaMetadataCompat;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        mediaMetadataCompat = (MediaMetadataCompat) MediaMetadataCompat.CREATOR.createFromParcel(parcel);
                    }
                    onMetadataChanged(mediaMetadataCompat);
                    return true;
                case TRANSACTION_onQueueChanged:
                    parcel.enforceInterface(DESCRIPTOR);
                    onQueueChanged(parcel.createTypedArrayList(QueueItem.CREATOR));
                    return true;
                case TRANSACTION_onQueueTitleChanged:
                    CharSequence charSequence;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                    }
                    onQueueTitleChanged(charSequence);
                    return true;
                case TRANSACTION_onExtrasChanged:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    onExtrasChanged(bundle);
                    return true;
                case TRANSACTION_onVolumeInfoChanged:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        parcelableVolumeInfo = (ParcelableVolumeInfo) ParcelableVolumeInfo.CREATOR.createFromParcel(parcel);
                    }
                    onVolumeInfoChanged(parcelableVolumeInfo);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onEvent(String str, Bundle bundle) throws RemoteException;

    void onExtrasChanged(Bundle bundle) throws RemoteException;

    void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException;

    void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException;

    void onQueueChanged(List<QueueItem> list) throws RemoteException;

    void onQueueTitleChanged(CharSequence charSequence) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException;
}
