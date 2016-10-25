package android.support.v4.media;

import android.content.Intent;
import android.media.MediaDescription.Builder;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacksApi21;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceImplApi21;
import java.util.ArrayList;
import java.util.List;

class MediaBrowserServiceCompatApi21 {

    public static interface ServiceImplApi21 {
        void addSubscription(String str, ServiceCallbacks serviceCallbacks);

        void connect(String str, Bundle bundle, ServiceCallbacks serviceCallbacks);

        void disconnect(ServiceCallbacks serviceCallbacks);

        void removeSubscription(String str, ServiceCallbacks serviceCallbacks);
    }

    static class MediaBrowserServiceAdaptorApi21 {
        ServiceBinderProxyApi21 mBinder;

        static class ServiceBinderProxyApi21 extends Stub {
            final ServiceImplApi21 mServiceImpl;

            ServiceBinderProxyApi21(ServiceImplApi21 serviceImplApi21) {
                this.mServiceImpl = serviceImplApi21;
            }

            public void connect(String str, Bundle bundle, Object obj) {
                this.mServiceImpl.connect(str, bundle, new ServiceCallbacksApi21(obj));
            }

            public void disconnect(Object obj) {
                this.mServiceImpl.disconnect(new ServiceCallbacksApi21(obj));
            }

            public void addSubscription(String str, Object obj) {
                this.mServiceImpl.addSubscription(str, new ServiceCallbacksApi21(obj));
            }

            public void removeSubscription(String str, Object obj) {
                this.mServiceImpl.removeSubscription(str, new ServiceCallbacksApi21(obj));
            }

            public void getMediaItem(String str, ResultReceiver resultReceiver) {
            }
        }

        MediaBrowserServiceAdaptorApi21() {
        }

        public void onCreate(ServiceImplApi21 serviceImplApi21) {
            this.mBinder = new ServiceBinderProxyApi21(serviceImplApi21);
        }

        public IBinder onBind(Intent intent) {
            return SERVICE_INTERFACE.equals(intent.getAction()) ? this.mBinder : null;
        }
    }

    public static interface ServiceCallbacks {
        IBinder asBinder();

        void onConnect(String str, Object obj, Bundle bundle) throws RemoteException;

        void onConnectFailed() throws RemoteException;

        void onLoadChildren(String str, List<Parcel> list) throws RemoteException;
    }

    public static class ServiceCallbacksApi21 implements ServiceCallbacks {
        private static Object sNullParceledListSliceObj;
        private final IMediaBrowserServiceCallbacksAdapterApi21 mCallbacks;

        static {
            MediaItem mediaItem = new MediaItem(new Builder().setMediaId("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM").build(), 0);
            List arrayList = new ArrayList();
            arrayList.add(mediaItem);
            sNullParceledListSliceObj = ParceledListSliceAdapterApi21.newInstance(arrayList);
        }

        ServiceCallbacksApi21(Object obj) {
            this.mCallbacks = new IMediaBrowserServiceCallbacksAdapterApi21(obj);
        }

        public IBinder asBinder() {
            return this.mCallbacks.asBinder();
        }

        public void onConnect(String str, Object obj, Bundle bundle) throws RemoteException {
            this.mCallbacks.onConnect(str, obj, bundle);
        }

        public void onConnectFailed() throws RemoteException {
            this.mCallbacks.onConnectFailed();
        }

        public void onLoadChildren(String str, List<Parcel> list) throws RemoteException {
            List list2;
            Object obj = null;
            if (list != null) {
                List arrayList = new ArrayList();
                for (Parcel parcel : list) {
                    parcel.setDataPosition(0);
                    arrayList.add(MediaItem.CREATOR.createFromParcel(parcel));
                    parcel.recycle();
                }
                list2 = arrayList;
            } else {
                list2 = null;
            }
            if (VERSION.SDK_INT <= 23) {
                obj = list2 == null ? sNullParceledListSliceObj : ParceledListSliceAdapterApi21.newInstance(list2);
            } else if (list2 != null) {
                obj = ParceledListSliceAdapterApi21.newInstance(list2);
            }
            this.mCallbacks.onLoadChildren(str, obj);
        }
    }

    MediaBrowserServiceCompatApi21() {
    }

    public static Object createService() {
        return new MediaBrowserServiceAdaptorApi21();
    }

    public static void onCreate(Object obj, ServiceImplApi21 serviceImplApi21) {
        ((MediaBrowserServiceAdaptorApi21) obj).onCreate(serviceImplApi21);
    }

    public static IBinder onBind(Object obj, Intent intent) {
        return ((MediaBrowserServiceAdaptorApi21) obj).onBind(intent);
    }
}
