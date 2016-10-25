package com.xunlei.tdlive.im;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.google.gson.Gson;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.tdlive.im.IMClient.IMClientCallback;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.XLog;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class MessageDispatcher implements Callback, IMClientCallback {
    static HashMap<String, Class<?>> a;
    private ConnectCallback b;
    private HandlerThread c;
    private Handler d;
    private Handler e;
    private HashMap<String, OnMessageCallback<?>> f;

    public static abstract class ConnectCallback {
        public void onIMConnected(int i, String str) {
        }

        public void onIMDisconnected() {
        }

        public void onIMConnectionLost() {
        }

        public void onIMKickout(String str) {
        }
    }

    public static abstract class OnMessageCallback<T> {
        public long __msg_count_;

        public abstract void onMessage(T t);

        public OnMessageCallback() {
            this.__msg_count_ = 0;
        }

        public long getDelayMillis() {
            return 0;
        }

        public boolean onPreMessage(T t) {
            return false;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        a = hashMap;
        hashMap.put("unknown", BaseMessage.class);
        a.put("onlike", LikeMessage.class);
        a.put("onsendchat", ChatMessage.class);
        a.put("onkick", KickMessage.class);
        a.put("oninroom", InRoomMessage.class);
        a.put("onoutroom", OutRoomMessage.class);
        a.put("oncloseroom", CloseRoomMessage.class);
        a.put("onsendgift", GiftMessage.class);
        a.put("onroomuserlist", RoomUserListMessage.class);
        a.put("onroomusernum", RoomUserNumMessage.class);
        a.put("onrecorddata", ReplayDataMessage.class);
        a.put("ondeniedchat", DeniedChatMessage.class);
        a.put("onallowchat", AllowChatMessage.class);
        a.put("onsysmsg", SysNotifyMessage.class);
        a.put("onvcconnection", VoiceConnectMessage.class);
        a.put("onvcreply", VoiceCreplyMessage.class);
        a.put("onvcclose", VoiceCloseMessage.class);
    }

    public MessageDispatcher() {
        this(null);
    }

    public MessageDispatcher(ConnectCallback connectCallback) {
        this.f = new HashMap();
        this.b = connectCallback;
        this.c = new HandlerThread("MessageDispatcher-Thread");
        this.c.start();
        this.e = new Handler(this.c.getLooper(), this);
        this.d = new Handler(this);
    }

    public void a(OnMessageCallback<?> onMessageCallback) {
        try {
            this.f.put(((Class) ((ParameterizedType) onMessageCallback.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName(), onMessageCallback);
        } catch (Exception e) {
            XLog.e("MessageDispatcher", new StringBuilder("addDispatchMessage error:").append(e.toString()).toString());
        }
    }

    public void a() {
        this.f.clear();
        this.c.quit();
    }

    public void onIMConnected(int i, String str) {
        if (this.b != null) {
            this.b.onIMConnected(i, str);
        }
    }

    public void onIMDisconnected() {
        if (this.b != null) {
            this.b.onIMDisconnected();
        }
    }

    public void onIMConnectionLost() {
        if (this.b != null) {
            this.b.onIMConnectionLost();
        }
    }

    public void onIMKickout(String str) {
        if (this.b != null) {
            this.b.onIMKickout(str);
        }
    }

    public void onIMMessageArrived(String str, byte[] bArr, int i, boolean z) {
        this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, bArr).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1000 && this.f.size() > 0) {
            String str = new String((byte[]) message.obj);
            Class cls = (Class) a.get(new JsonWrapper(str).getString("cmd", "unknown"));
            if (cls != null) {
                try {
                    Message obtainMessage = this.d.obtainMessage(XLPayErrorCode.XLP_GATE_PARAM_ERROR, new Gson().fromJson(str, cls));
                    if (!a(obtainMessage)) {
                        obtainMessage.sendToTarget();
                    }
                } catch (Exception e) {
                }
            }
        } else if (message.what == 1001 && this.f.size() > 0) {
            try {
                OnMessageCallback onMessageCallback = (OnMessageCallback) this.f.get(message.obj.getClass().getSimpleName());
                long delayMillis = onMessageCallback.getDelayMillis();
                if (delayMillis <= 0) {
                    b(message);
                } else {
                    Handler handler = this.d;
                    Message obtainMessage2 = this.d.obtainMessage(XLPayErrorCode.XLP_GATE_GEN_ERROR, message.obj);
                    long j = onMessageCallback.__msg_count_ + 1;
                    onMessageCallback.__msg_count_ = j;
                    handler.sendMessageDelayed(obtainMessage2, delayMillis * j);
                }
            } catch (Exception e2) {
                b(message);
            }
        } else if (message.what == 1002 && this.f.size() > 0) {
            b(message);
        }
        return true;
    }

    private boolean a(Message message) {
        try {
            OnMessageCallback onMessageCallback = (OnMessageCallback) this.f.get(message.obj.getClass().getSimpleName());
            return ((Boolean) onMessageCallback.getClass().getMethod("onPreMessage", new Class[]{message.obj.getClass()}).invoke(onMessageCallback, new Object[]{message.obj})).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    private void b(Message message) {
        try {
            OnMessageCallback onMessageCallback = (OnMessageCallback) this.f.get(message.obj.getClass().getSimpleName());
            onMessageCallback.getClass().getMethod("onMessage", new Class[]{message.obj.getClass()}).invoke(onMessageCallback, new Object[]{message.obj});
            long j = onMessageCallback.__msg_count_ - 1;
            onMessageCallback.__msg_count_ = j;
            if (j <= 0) {
                onMessageCallback.__msg_count_ = 0;
            }
        } catch (Exception e) {
        }
    }
}
