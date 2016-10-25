package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.BrowserRoot;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.support.v4.media.MediaBrowserServiceCompatApi23.ItemCallback;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
    private static final boolean DBG = false;
    public static final String KEY_MEDIA_ITEM = "media_item";
    private static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    private static final String TAG = "MediaBrowserServiceCompat";
    private final ArrayMap<IBinder, ConnectionRecord> mConnections;
    private final ServiceHandler mHandler;
    private MediaBrowserServiceImpl mImpl;
    Token mSession;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Token val$token;

        AnonymousClass_1(Token token) {
            this.val$token = token;
        }

        public void run() {
            for (IBinder iBinder : MediaBrowserServiceCompat.this.mConnections.keySet()) {
                ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.mConnections.get(iBinder);
                try {
                    connectionRecord.callbacks.onConnect(connectionRecord.root.getRootId(), this.val$token, connectionRecord.root.getExtras());
                } catch (RemoteException e) {
                    new StringBuilder("Connection for ").append(connectionRecord.pkg).append(" is no longer valid.");
                    MediaBrowserServiceCompat.this.mConnections.remove(iBinder);
                }
            }
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ Bundle val$options;
        final /* synthetic */ String val$parentId;

        AnonymousClass_2(String str, Bundle bundle) {
            this.val$parentId = str;
            this.val$options = bundle;
        }

        public void run() {
            for (IBinder iBinder : MediaBrowserServiceCompat.this.mConnections.keySet()) {
                ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.mConnections.get(iBinder);
                List<Bundle> list = (List) connectionRecord.subscriptions.get(this.val$parentId);
                if (list != null) {
                    for (Bundle bundle : list) {
                        if (MediaBrowserCompatUtils.hasDuplicatedItems(this.val$options, bundle)) {
                            MediaBrowserServiceCompat.this.performLoadChildren(this.val$parentId, connectionRecord, bundle);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static class Result<T> {
        private Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendResultCalled;

        Result(Object obj) {
            this.mDebug = obj;
        }

        public void sendResult(T t) {
            if (this.mSendResultCalled) {
                throw new IllegalStateException(new StringBuilder("sendResult() called twice for: ").append(this.mDebug).toString());
            }
            this.mSendResultCalled = true;
            onResultSent(t, this.mFlags);
        }

        public void detach() {
            if (this.mDetachCalled) {
                throw new IllegalStateException(new StringBuilder("detach() called when detach() had already been called for: ").append(this.mDebug).toString());
            } else if (this.mSendResultCalled) {
                throw new IllegalStateException(new StringBuilder("detach() called when sendResult() had already been called for: ").append(this.mDebug).toString());
            } else {
                this.mDetachCalled = true;
            }
        }

        boolean isDone() {
            return (this.mDetachCalled || this.mSendResultCalled) ? true : DBG;
        }

        void setFlags(int i) {
            this.mFlags = i;
        }

        void onResultSent(T t, int i) {
        }
    }

    class AnonymousClass_3 extends Result<List<MediaItem>> {
        final /* synthetic */ ConnectionRecord val$connection;
        final /* synthetic */ Bundle val$options;
        final /* synthetic */ String val$parentId;

        AnonymousClass_3(Object obj, ConnectionRecord connectionRecord, String str, Bundle bundle) {
            this.val$connection = connectionRecord;
            this.val$parentId = str;
            this.val$options = bundle;
            super(obj);
        }

        void onResultSent(List<MediaItem> list, int i) {
            if (MediaBrowserServiceCompat.this.mConnections.get(this.val$connection.callbacks.asBinder()) == this.val$connection) {
                if ((i & 1) != 0) {
                    list = MediaBrowserCompatUtils.applyOptions(list, this.val$options);
                }
                try {
                    this.val$connection.callbacks.onLoadChildren(this.val$parentId, list, this.val$options);
                } catch (RemoteException e) {
                    new StringBuilder("Calling onLoadChildren() failed for id=").append(this.val$parentId).append(" package=").append(this.val$connection.pkg);
                }
            }
        }
    }

    class AnonymousClass_4 extends Result<MediaItem> {
        final /* synthetic */ ResultReceiver val$receiver;

        AnonymousClass_4(Object obj, ResultReceiver resultReceiver) {
            this.val$receiver = resultReceiver;
            super(obj);
        }

        void onResultSent(MediaItem mediaItem, int i) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_MEDIA_ITEM, mediaItem);
            this.val$receiver.send(0, bundle);
        }
    }

    public static final class BrowserRoot {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        private final Bundle mExtras;
        private final String mRootId;

        public BrowserRoot(String str, Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.mRootId = str;
            this.mExtras = bundle;
        }

        public final String getRootId() {
            return this.mRootId;
        }

        public final Bundle getExtras() {
            return this.mExtras;
        }
    }

    private class ConnectionRecord {
        ServiceCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashMap<String, List<Bundle>> subscriptions;

        private ConnectionRecord() {
            this.subscriptions = new HashMap();
        }
    }

    static interface MediaBrowserServiceImpl {
        IBinder onBind(Intent intent);

        void onCreate();
    }

    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl {
        private Object mServiceObj;

        MediaBrowserServiceImplApi21() {
        }

        public void onCreate() {
            this.mServiceObj = MediaBrowserServiceCompatApi21.createService();
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj, new ServiceImplApi21());
        }

        public IBinder onBind(Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
        }
    }

    class MediaBrowserServiceImplApi23 implements MediaBrowserServiceImpl {
        private Object mServiceObj;

        MediaBrowserServiceImplApi23() {
        }

        public void onCreate() {
            this.mServiceObj = MediaBrowserServiceCompatApi23.createService();
            MediaBrowserServiceCompatApi23.onCreate(this.mServiceObj, new ServiceImplApi23(null));
        }

        public IBinder onBind(Intent intent) {
            return MediaBrowserServiceCompatApi23.onBind(this.mServiceObj, intent);
        }
    }

    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl {
        private Messenger mMessenger;

        MediaBrowserServiceImplBase() {
        }

        public void onCreate() {
            this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
        }

        public IBinder onBind(Intent intent) {
            return SERVICE_INTERFACE.equals(intent.getAction()) ? this.mMessenger.getBinder() : null;
        }
    }

    private static interface ServiceCallbacks {
        IBinder asBinder();

        void onConnect(String str, Token token, Bundle bundle) throws RemoteException;

        void onConnectFailed() throws RemoteException;

        void onLoadChildren(String str, List<MediaItem> list, Bundle bundle) throws RemoteException;
    }

    private class ServiceCallbacksApi21 implements ServiceCallbacks {
        final android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks;
        Messenger mMessenger;

        ServiceCallbacksApi21(android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mCallbacks = serviceCallbacks;
        }

        public IBinder asBinder() {
            return this.mCallbacks.asBinder();
        }

        public void onConnect(String str, Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
            BundleCompat.putBinder(bundle, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER, this.mMessenger.getBinder());
            bundle.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, RESULT_FLAG_OPTION_NOT_HANDLED);
            this.mCallbacks.onConnect(str, token.getToken(), bundle);
        }

        public void onConnectFailed() throws RemoteException {
            this.mCallbacks.onConnectFailed();
        }

        public void onLoadChildren(String str, List<MediaItem> list, Bundle bundle) throws RemoteException {
            List list2 = null;
            if (list != null) {
                List arrayList = new ArrayList();
                for (MediaItem mediaItem : list) {
                    Parcel obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                    arrayList.add(obtain);
                }
                list2 = arrayList;
            }
            this.mCallbacks.onLoadChildren(str, list2);
        }
    }

    private class ServiceCallbacksCompat implements ServiceCallbacks {
        final Messenger mCallbacks;

        ServiceCallbacksCompat(Messenger messenger) {
            this.mCallbacks = messenger;
        }

        public IBinder asBinder() {
            return this.mCallbacks.getBinder();
        }

        public void onConnect(String str, Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, RESULT_FLAG_OPTION_NOT_HANDLED);
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN, token);
            bundle2.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, bundle);
            sendRequest(RESULT_FLAG_OPTION_NOT_HANDLED, bundle2);
        }

        public void onConnectFailed() throws RemoteException {
            sendRequest(XZBDevice.DOWNLOAD_LIST_RECYCLE, null);
        }

        public void onLoadChildren(String str, List<MediaItem> list, Bundle bundle) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            if (list != null) {
                bundle2.putParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST, list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
            }
            sendRequest(XZBDevice.DOWNLOAD_LIST_FAILED, bundle2);
        }

        private void sendRequest(int i, Bundle bundle) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            this.mCallbacks.send(obtain);
        }
    }

    private final class ServiceHandler extends Handler {
        private final ServiceImpl mServiceImpl;

        private ServiceHandler() {
            this.mServiceImpl = new ServiceImpl(null);
        }

        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case RESULT_FLAG_OPTION_NOT_HANDLED:
                    this.mServiceImpl.connect(data.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID), data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS), new ServiceCallbacksCompat(message.replyTo));
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.mServiceImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.mServiceImpl.addSubscription(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getBundle(MediaBrowserProtocol.DATA_OPTIONS), new ServiceCallbacksCompat(message.replyTo));
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.mServiceImpl.removeSubscription(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getBundle(MediaBrowserProtocol.DATA_OPTIONS), new ServiceCallbacksCompat(message.replyTo));
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.mServiceImpl.getMediaItem(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER));
                case R.styleable.Toolbar_contentInsetEnd:
                    this.mServiceImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo));
                case R.styleable.Toolbar_contentInsetLeft:
                    this.mServiceImpl.unregisterCallbacks(new ServiceCallbacksCompat(message.replyTo));
                default:
                    new StringBuilder("Unhandled message: ").append(message).append("\n  Service version: 1\n  Client version: ").append(message.arg1);
            }
        }

        public final boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_UID, Binder.getCallingUid());
            return super.sendMessageAtTime(message, j);
        }

        public final void postOrRun(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        public final ServiceImpl getServiceImpl() {
            return this.mServiceImpl;
        }
    }

    private class ServiceImpl {

        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;
            final /* synthetic */ String val$pkg;
            final /* synthetic */ Bundle val$rootHints;
            final /* synthetic */ int val$uid;

            AnonymousClass_1(ServiceCallbacks serviceCallbacks, String str, Bundle bundle, int i) {
                this.val$callbacks = serviceCallbacks;
                this.val$pkg = str;
                this.val$rootHints = bundle;
                this.val$uid = i;
            }

            public void run() {
                IBinder asBinder = this.val$callbacks.asBinder();
                MediaBrowserServiceCompat.this.mConnections.remove(asBinder);
                ConnectionRecord connectionRecord = new ConnectionRecord(null);
                connectionRecord.pkg = this.val$pkg;
                connectionRecord.rootHints = this.val$rootHints;
                connectionRecord.callbacks = this.val$callbacks;
                connectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(this.val$pkg, this.val$uid, this.val$rootHints);
                if (connectionRecord.root == null) {
                    new StringBuilder("No root for client ").append(this.val$pkg).append(" from service ").append(getClass().getName());
                    try {
                        this.val$callbacks.onConnectFailed();
                        return;
                    } catch (RemoteException e) {
                        new StringBuilder("Calling onConnectFailed() failed. Ignoring. pkg=").append(this.val$pkg);
                    }
                }
                try {
                    MediaBrowserServiceCompat.this.mConnections.put(asBinder, connectionRecord);
                    if (MediaBrowserServiceCompat.this.mSession != null) {
                        this.val$callbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                    }
                } catch (RemoteException e2) {
                    new StringBuilder("Calling onConnect() failed. Dropping client. pkg=").append(this.val$pkg);
                    MediaBrowserServiceCompat.this.mConnections.remove(asBinder);
                }
            }
        }

        class AnonymousClass_2 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;

            AnonymousClass_2(ServiceCallbacks serviceCallbacks) {
                this.val$callbacks = serviceCallbacks;
            }

            public void run() {
                MediaBrowserServiceCompat.this.mConnections.remove(this.val$callbacks.asBinder());
            }
        }

        class AnonymousClass_3 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;
            final /* synthetic */ String val$id;
            final /* synthetic */ Bundle val$options;

            AnonymousClass_3(ServiceCallbacks serviceCallbacks, String str, Bundle bundle) {
                this.val$callbacks = serviceCallbacks;
                this.val$id = str;
                this.val$options = bundle;
            }

            public void run() {
                ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.mConnections.get(this.val$callbacks.asBinder());
                if (connectionRecord == null) {
                    new StringBuilder("addSubscription for callback that isn't registered id=").append(this.val$id);
                } else {
                    MediaBrowserServiceCompat.this.addSubscription(this.val$id, connectionRecord, this.val$options);
                }
            }
        }

        class AnonymousClass_4 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;
            final /* synthetic */ String val$id;
            final /* synthetic */ Bundle val$options;

            AnonymousClass_4(ServiceCallbacks serviceCallbacks, String str, Bundle bundle) {
                this.val$callbacks = serviceCallbacks;
                this.val$id = str;
                this.val$options = bundle;
            }

            public void run() {
                ConnectionRecord connectionRecord = (ConnectionRecord) MediaBrowserServiceCompat.this.mConnections.get(this.val$callbacks.asBinder());
                if (connectionRecord == null) {
                    new StringBuilder("removeSubscription for callback that isn't registered id=").append(this.val$id);
                } else if (!MediaBrowserServiceCompat.this.removeSubscription(this.val$id, connectionRecord, this.val$options)) {
                    new StringBuilder("removeSubscription called for ").append(this.val$id).append(" which is not subscribed");
                }
            }
        }

        class AnonymousClass_5 implements Runnable {
            final /* synthetic */ String val$mediaId;
            final /* synthetic */ ResultReceiver val$receiver;

            AnonymousClass_5(String str, ResultReceiver resultReceiver) {
                this.val$mediaId = str;
                this.val$receiver = resultReceiver;
            }

            public void run() {
                MediaBrowserServiceCompat.this.performLoadItem(this.val$mediaId, this.val$receiver);
            }
        }

        class AnonymousClass_6 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;

            AnonymousClass_6(ServiceCallbacks serviceCallbacks) {
                this.val$callbacks = serviceCallbacks;
            }

            public void run() {
                IBinder asBinder = this.val$callbacks.asBinder();
                MediaBrowserServiceCompat.this.mConnections.remove(asBinder);
                ConnectionRecord connectionRecord = new ConnectionRecord(null);
                connectionRecord.callbacks = this.val$callbacks;
                MediaBrowserServiceCompat.this.mConnections.put(asBinder, connectionRecord);
            }
        }

        class AnonymousClass_7 implements Runnable {
            final /* synthetic */ ServiceCallbacks val$callbacks;

            AnonymousClass_7(ServiceCallbacks serviceCallbacks) {
                this.val$callbacks = serviceCallbacks;
            }

            public void run() {
                MediaBrowserServiceCompat.this.mConnections.remove(this.val$callbacks.asBinder());
            }
        }

        private ServiceImpl() {
        }

        public void connect(String str, int i, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            if (MediaBrowserServiceCompat.this.isValidPackage(str, i)) {
                MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_1(serviceCallbacks, str, bundle, i));
                return;
            }
            throw new IllegalArgumentException(new StringBuilder("Package/uid mismatch: uid=").append(i).append(" package=").append(str).toString());
        }

        public void disconnect(ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_2(serviceCallbacks));
        }

        public void addSubscription(String str, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_3(serviceCallbacks, str, bundle));
        }

        public void removeSubscription(String str, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_4(serviceCallbacks, str, bundle));
        }

        public void getMediaItem(String str, ResultReceiver resultReceiver) {
            if (!TextUtils.isEmpty(str) && resultReceiver != null) {
                MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_5(str, resultReceiver));
            }
        }

        public void registerCallbacks(ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_6(serviceCallbacks));
        }

        public void unregisterCallbacks(ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new AnonymousClass_7(serviceCallbacks));
        }
    }

    private class ServiceImplApi21 implements android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceImplApi21 {
        final ServiceImpl mServiceImpl;

        ServiceImplApi21() {
            this.mServiceImpl = MediaBrowserServiceCompat.this.mHandler.getServiceImpl();
        }

        public void connect(String str, Bundle bundle, android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.connect(str, Binder.getCallingUid(), bundle, new ServiceCallbacksApi21(serviceCallbacks));
        }

        public void disconnect(android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.disconnect(new ServiceCallbacksApi21(serviceCallbacks));
        }

        public void addSubscription(String str, android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.addSubscription(str, null, new ServiceCallbacksApi21(serviceCallbacks));
        }

        public void removeSubscription(String str, android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.removeSubscription(str, null, new ServiceCallbacksApi21(serviceCallbacks));
        }
    }

    private class ServiceImplApi23 extends ServiceImplApi21 implements android.support.v4.media.MediaBrowserServiceCompatApi23.ServiceImplApi23 {

        class AnonymousClass_1 extends ResultReceiver {
            final /* synthetic */ ItemCallback val$cb;

            AnonymousClass_1(Handler handler, ItemCallback itemCallback) {
                this.val$cb = itemCallback;
                super(handler);
            }

            protected void onReceiveResult(int i, Bundle bundle) {
                Parcel parcel;
                MediaItem mediaItem = (MediaItem) bundle.getParcelable(KEY_MEDIA_ITEM);
                if (mediaItem != null) {
                    Parcel obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                    parcel = obtain;
                } else {
                    parcel = null;
                }
                this.val$cb.onItemLoaded(i, bundle, parcel);
            }
        }

        private ServiceImplApi23() {
            super();
        }

        public void getMediaItem(String str, ItemCallback itemCallback) {
            this.mServiceImpl.getMediaItem(str, new AnonymousClass_1(MediaBrowserServiceCompat.this.mHandler, itemCallback));
        }
    }

    public abstract BrowserRoot onGetRoot(String str, int i, Bundle bundle);

    public abstract void onLoadChildren(String str, Result<List<MediaItem>> result);

    public MediaBrowserServiceCompat() {
        this.mConnections = new ArrayMap();
        this.mHandler = new ServiceHandler();
    }

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserServiceImplApi23();
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserServiceImplApi21();
        } else {
            this.mImpl = new MediaBrowserServiceImplBase();
        }
        this.mImpl.onCreate();
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public void onLoadChildren(String str, Result<List<MediaItem>> result, Bundle bundle) {
        result.setFlags(RESULT_FLAG_OPTION_NOT_HANDLED);
        onLoadChildren(str, result);
    }

    public void onLoadItem(String str, Result<MediaItem> result) {
        result.sendResult(null);
    }

    public void setSessionToken(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        } else if (this.mSession != null) {
            throw new IllegalStateException("The session token has already been set.");
        } else {
            this.mSession = token;
            this.mHandler.post(new AnonymousClass_1(token));
        }
    }

    public Token getSessionToken() {
        return this.mSession;
    }

    public void notifyChildrenChanged(String str) {
        notifyChildrenChangedInternal(str, null);
    }

    public void notifyChildrenChanged(String str, Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        notifyChildrenChangedInternal(str, bundle);
    }

    private void notifyChildrenChangedInternal(String str, Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.mHandler.post(new AnonymousClass_2(str, bundle));
    }

    private boolean isValidPackage(String str, int i) {
        if (str == null) {
            return DBG;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(i);
        int length = packagesForUid.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (packagesForUid[i2].equals(str)) {
                return true;
            }
        }
        return DBG;
    }

    private void addSubscription(String str, ConnectionRecord connectionRecord, Bundle bundle) {
        List list = (List) connectionRecord.subscriptions.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
        } else {
            List list2 = list;
        }
        for (Bundle bundle2 : r1) {
            if (MediaBrowserCompatUtils.areSameOptions(bundle, bundle2)) {
                return;
            }
        }
        r1.add(bundle);
        connectionRecord.subscriptions.put(str, r1);
        performLoadChildren(str, connectionRecord, bundle);
    }

    private boolean removeSubscription(String str, ConnectionRecord connectionRecord, Bundle bundle) {
        List<Bundle> list = (List) connectionRecord.subscriptions.get(str);
        if (list == null) {
            return false;
        }
        boolean z;
        for (Bundle bundle2 : list) {
            if (MediaBrowserCompatUtils.areSameOptions(bundle, bundle2)) {
                list.remove(bundle2);
                z = true;
                break;
            }
        }
        z = false;
        if (list.size() != 0) {
            return z;
        }
        connectionRecord.subscriptions.remove(str);
        return z;
    }

    private void performLoadChildren(String str, ConnectionRecord connectionRecord, Bundle bundle) {
        Result anonymousClass_3 = new AnonymousClass_3(str, connectionRecord, str, bundle);
        if (bundle == null) {
            onLoadChildren(str, anonymousClass_3);
        } else {
            onLoadChildren(str, anonymousClass_3, bundle);
        }
        if (!anonymousClass_3.isDone()) {
            throw new IllegalStateException(new StringBuilder("onLoadChildren must call detach() or sendResult() before returning for package=").append(connectionRecord.pkg).append(" id=").append(str).toString());
        }
    }

    private List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
        int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i == -1 && i2 == -1) {
            return list;
        }
        int i3 = i2 * (i - 1);
        int i4 = i3 + i2;
        if (i <= 0 || i2 <= 0 || i3 >= list.size()) {
            return Collections.emptyList();
        }
        if (i4 > list.size()) {
            i4 = list.size();
        }
        return list.subList(i3, i4);
    }

    private void performLoadItem(String str, ResultReceiver resultReceiver) {
        Result anonymousClass_4 = new AnonymousClass_4(str, resultReceiver);
        onLoadItem(str, anonymousClass_4);
        if (!anonymousClass_4.isDone()) {
            throw new IllegalStateException(new StringBuilder("onLoadItem must call detach() or sendResult() before returning for id=").append(str).toString());
        }
    }
}
