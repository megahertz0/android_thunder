package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceImplApi21;
import android.support.v4.media.MediaBrowserServiceCompatApi23.ItemCallback;
import android.support.v4.media.MediaBrowserServiceCompatApi23.ServiceImplApi23;

class MediaBrowserServiceCompatApi23 extends MediaBrowserServiceCompatApi21 {
    private static final String TAG = "MediaBrowserServiceCompatApi21";

    public static interface ServiceImplApi23 extends ServiceImplApi21 {
        void getMediaItem(String str, ItemCallback itemCallback);
    }

    public static interface ItemCallback {
        void onItemLoaded(int i, Bundle bundle, Parcel parcel);
    }

    static class MediaBrowserServiceAdaptorApi23 extends MediaBrowserServiceAdaptorApi21 {

        private static class ServiceBinderProxyApi23 extends ServiceBinderProxyApi21 {
            ServiceImplApi23 mServiceImpl;

            class AnonymousClass_1 implements ItemCallback {
                final /* synthetic */ String val$KEY_MEDIA_ITEM;
                final /* synthetic */ ResultReceiver val$receiver;

                AnonymousClass_1(String str, ResultReceiver resultReceiver) {
                    this.val$KEY_MEDIA_ITEM = str;
                    this.val$receiver = resultReceiver;
                }

                public void onItemLoaded(int i, Bundle bundle, Parcel parcel) {
                    if (parcel != null) {
                        parcel.setDataPosition(0);
                        bundle.putParcelable(this.val$KEY_MEDIA_ITEM, (MediaItem) MediaItem.CREATOR.createFromParcel(parcel));
                        parcel.recycle();
                    }
                    this.val$receiver.send(i, bundle);
                }
            }

            ServiceBinderProxyApi23(ServiceImplApi23 serviceImplApi23) {
                super(serviceImplApi23);
                this.mServiceImpl = serviceImplApi23;
            }

            public void getMediaItem(String str, ResultReceiver resultReceiver) {
                try {
                    this.mServiceImpl.getMediaItem(str, new AnonymousClass_1((String) MediaBrowserService.class.getDeclaredField("KEY_MEDIA_ITEM").get(null), resultReceiver));
                } catch (IllegalAccessException e) {
                } catch (NoSuchFieldException e2) {
                }
            }
        }

        MediaBrowserServiceAdaptorApi23() {
        }

        public void onCreate(ServiceImplApi23 serviceImplApi23) {
            this.mBinder = new ServiceBinderProxyApi23(serviceImplApi23);
        }
    }

    MediaBrowserServiceCompatApi23() {
    }

    public static Object createService() {
        return new MediaBrowserServiceAdaptorApi23();
    }

    public static void onCreate(Object obj, ServiceImplApi23 serviceImplApi23) {
        ((MediaBrowserServiceAdaptorApi23) obj).onCreate(serviceImplApi23);
    }
}
