package com.xunlei.tdlive.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.gson.Gson;
import com.umeng.common.inter.ITagManager;
import com.umeng.message.proguard.j;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.logging.JSR47Logger;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class IMService {
    private static int a;
    private static IMService b;
    private Context c;
    private HandlerThread d;
    private MQTTConnection e;
    private BroadcastReceiver f;

    public static class JSR47Logger2 extends JSR47Logger {
        public boolean isLoggable(int i) {
            return true;
        }

        public void trace(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
            IMService.b(new StringBuilder("trace: ").append(formatMessage(str3, objArr)).toString());
        }

        public void log(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
            IMService.b(new StringBuilder("log:  ").append(formatMessage(str3, objArr)).toString());
        }
    }

    private static class KickoutMessage {
        public String info;

        private KickoutMessage() {
            this.info = "\u60a8\u7684\u5e10\u53f7\u5728\u522b\u5904\u767b\u5f55";
        }
    }

    private class MQTTConnection extends Handler implements MqttCallback {
        static final int MAX_TRY = 10;
        private String mLastUserName;
        private boolean mNoNeedReconnect;
        private boolean mStarted;
        private int mTryCount;
        private MqttClient mqttClient;

        public MQTTConnection(Looper looper) {
            super(looper);
            this.mLastUserName = BuildConfig.VERSION_NAME;
            this.mStarted = false;
            this.mTryCount = 0;
            this.mNoNeedReconnect = false;
        }

        private void scheduleReconnect() {
            sendMessageDelayed(obtainMessage(XLPayErrorCode.XLP_GATE_PARAM_ERROR, 0, 0, new Intent(IMService.this.a(), IMService.class).setAction("com.xunlei.tdlive.sdk.IMService.RECONNECT")), 5000);
        }

        private void cancelReconnect() {
            removeMessages(XLPayErrorCode.XLP_GATE_PARAM_ERROR);
        }

        private boolean isNetworkAvailable() {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) IMService.this.a().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo == null ? false : activeNetworkInfo.isConnected();
        }

        private synchronized boolean tryConnect() {
            return tryConnect(false, null, null);
        }

        private synchronized boolean tryConnect(boolean z) {
            return tryConnect(z, null, null);
        }

        private synchronized boolean tryConnect(boolean z, String str, String str2) {
            int i;
            boolean z2 = true;
            synchronized (this) {
                if (this.mNoNeedReconnect) {
                    IMService.b("no need reconnect, sessioninvalid");
                    cancelReconnect();
                    z2 = false;
                } else {
                    String string = IMService.this.a().getSharedPreferences("XLLiveIMService", 0).getString("com.xunlei.tdlive.pref.mqttHost", null);
                    int i2 = IMService.this.a().getSharedPreferences("XLLiveIMService", 0).getInt("com.xunlei.tdlive.pref.mqttPort", a);
                    String string2 = IMService.this.a().getSharedPreferences("XLLiveIMService", 0).getString("com.xunlei.tdlive.pref.userName", null);
                    if (this.mqttClient != null && this.mqttClient.isConnected()) {
                        IMService.b(new StringBuilder("already connected force:").append(z).toString());
                        if (z) {
                            if (this.mLastUserName == null || !this.mLastUserName.equals(string2)) {
                                disconnect();
                            } else {
                                IMService.b("username equals");
                            }
                        }
                    }
                    if (isNetworkAvailable()) {
                        if (string == null || string.length() <= 0 || string2 == null || string2.length() <= 0) {
                            IMService.b(new StringBuilder("host or device id not found. started:").append(this.mStarted).toString());
                            if (this.mStarted) {
                                IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED").putExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, MqttConnectOptions.MQTT_VERSION_3_1_1).putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, "\u9519\u8bef\u7684\u7528\u6237\u540d\u548c\u5bc6\u7801"));
                            }
                        } else {
                            try {
                                cancelReconnect();
                                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                                mqttConnectOptions.setCleanSession(true);
                                this.mLastUserName = string2;
                                mqttConnectOptions.setUserName(string2);
                                mqttConnectOptions.setPassword(string2.toCharArray());
                                mqttConnectOptions.setKeepAliveInterval(MqttConnectOptions.CONNECTION_TIMEOUT_DEFAULT);
                                if (!(str == null || str2 == null)) {
                                    mqttConnectOptions.setWill(str, str2.getBytes(), 1, false);
                                }
                                this.mqttClient = new MqttClient(new StringBuilder("tcp://").append(string).append(":").append(i2).toString(), c.f, new MemoryPersistence());
                                this.mqttClient.setCallback(this);
                                this.mqttClient.connect(mqttConnectOptions);
                                this.mTryCount = 0;
                                IMService.b(new StringBuilder("connect ok, timetowait=").append(this.mqttClient.getTimeToWait()).toString());
                                IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED").putExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, 0).putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, ITagManager.SUCCESS));
                            } catch (Throwable e) {
                                if (e instanceof MqttException) {
                                    MqttException mqttException = (MqttException) e;
                                    i2 = mqttException.getReasonCode();
                                    if (i2 == 4 || i2 == 5) {
                                        IMService.b(new StringBuilder("connect error:").append(i2).append(", msg:").append(mqttException.getMessage()).toString());
                                        IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED").putExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, i2).putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, mqttException.getMessage()));
                                        z2 = false;
                                    } else {
                                        i = i2 == 0 ? 6 : i2;
                                    }
                                } else {
                                    i = 6;
                                }
                                int i3 = this.mTryCount;
                                this.mTryCount = i3 + 1;
                                if (i3 < 10) {
                                    IMService.b(new StringBuilder("tryConnect: ").append(e.getMessage()).append(", will retry after 5s, mTryCount: ").append(this.mTryCount).toString(), e);
                                    scheduleReconnect();
                                } else {
                                    IMService.b(new StringBuilder("tryConnect: ").append(e.getMessage()).append(", end retry, connect fail, mTryCount: ").append(this.mTryCount).toString(), e);
                                    IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED").putExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, i).putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, e.getMessage()));
                                }
                                z2 = false;
                            }
                        }
                        z2 = false;
                    } else {
                        IMService.b("network unavaliable, give up connect, wait network change event");
                        z2 = false;
                    }
                }
            }
            return z2;
        }

        private synchronized void disconnect() {
            cancelReconnect();
            try {
                if (!this.mNoNeedReconnect) {
                    this.mqttClient.disconnect();
                    this.mqttClient.close();
                }
            } catch (Exception e) {
                IMService.b(new StringBuilder("disconnect: ").append(e.getMessage()).toString());
            }
            this.mNoNeedReconnect = false;
            this.mLastUserName = BuildConfig.VERSION_NAME;
            this.mTryCount = 0;
            IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_DISCONNECTED"));
        }

        private synchronized boolean isConnected() {
            boolean z;
            if (this.mqttClient == null || !this.mqttClient.isConnected()) {
                z = false;
            } else {
                z = true;
            }
            return z;
        }

        private void tryPublish(String str, String str2, int i) {
            if (tryConnect(false, str, str2)) {
                try {
                    IMService.b(new StringBuilder("tryPublish topic:").append(str).append(", message:").append(str2).append(", qos=").append(i).toString());
                    this.mqttClient.publish(str, str2.getBytes(), i, false);
                    return;
                } catch (Throwable e) {
                    IMService.b(new StringBuilder("tryPublish: ").append(e.getMessage()).toString(), e);
                }
            }
            IMService.b("have no connect");
        }

        private void trySubscribe(String str) {
            if (tryConnect()) {
                try {
                    this.mqttClient.subscribe(str);
                    return;
                } catch (Throwable e) {
                    IMService.b(new StringBuilder("trySubscribe: ").append(e.getMessage()).toString(), e);
                }
            }
            IMService.b("have no connect");
        }

        public void handleMessage(Message message) {
            if ((message.what == 1000 || message.what == 1001) && message.obj != null) {
                Intent intent = (Intent) message.obj;
                String action = intent.getAction();
                if (action != null && action.length() > 0) {
                    if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.ACTION_CONNECT")) {
                        this.mStarted = true;
                        tryConnect(true);
                    } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.ACTION_DISCONNECT")) {
                        this.mStarted = false;
                        disconnect();
                        IMService.this.a().getSharedPreferences("XLLiveIMService", 0).edit().clear().commit();
                    } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.RECONNECT")) {
                        if (intent.getBooleanExtra("session_restore", false)) {
                            this.mStarted = true;
                            this.mNoNeedReconnect = false;
                            if (!isConnected()) {
                                IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED").putExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, MqttConnectOptions.MQTT_VERSION_3_1_1).putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, "\u9519\u8bef\u7684\u7528\u6237\u540d\u548c\u5bc6\u7801"));
                                return;
                            }
                            return;
                        }
                        tryConnect();
                    } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.PUBLISH")) {
                        tryPublish(intent.getStringExtra("topic"), intent.getStringExtra(j.C), intent.getIntExtra("qos", 1));
                    } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.SUBSCRIBE")) {
                        trySubscribe(intent.getStringExtra("topic"));
                    }
                }
            }
        }

        public void connectionLost(Throwable th) {
            try {
                Writer stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                    cause.printStackTrace(printWriter);
                }
                printWriter.close();
                IMService.b(new StringBuilder("Loss of connection connection downed cause:").append(stringWriter.toString()).toString());
            } catch (Exception e) {
            }
            if (th instanceof MqttException) {
                MqttException mqttException = (MqttException) th;
                IMService.b(new StringBuilder("Loss of connection connection downed:").append(mqttException.getMessage()).append(", code:").append(mqttException.getReasonCode()).toString());
            } else {
                IMService.b("Loss of connection connection downed", th);
            }
            if (this.mNoNeedReconnect) {
                IMService.b("no need reconnect, sessioninvalid");
                return;
            }
            if (this.mTryCount >= 10) {
                this.mTryCount = 0;
            }
            if (isNetworkAvailable()) {
                scheduleReconnect();
            }
            IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECT_LOST"));
        }

        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            try {
                IMService.b(new StringBuilder("deliveryComplete:").append(iMqttDeliveryToken.getTopics()[0]).toString());
            } catch (Exception e) {
                IMService.b(new StringBuilder("deliveryComplete:").append(e.toString()).toString());
            }
        }

        public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
            if (str == null || !str.equals("sessioninvalid")) {
                IMService.b(new StringBuilder("messageArrived: topic=").append(str).append(",msg=").append(mqttMessage.toString()).toString());
                byte[] payload = mqttMessage.getPayload();
                if (!preMessage(payload)) {
                    IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_PUBLISH_ARRIVED").putExtra("topic", str).putExtra("payload", payload).putExtra("qos", mqttMessage.getQos()).putExtra("retained", mqttMessage.isRetained()));
                    return;
                }
                return;
            }
            IMService.b("session invalid, kickout");
            this.mNoNeedReconnect = true;
            IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_KICKOUT").putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, "\u60a8\u7684\u5e10\u53f7\u5728\u522b\u5904\u767b\u5f55"));
        }

        private boolean preMessage(byte[] bArr) {
            byte[] bytes = "\"cmd\":\"onsessionout\"".getBytes();
            int i = 0;
            int i2 = 0;
            while (i2 < bArr.length && bArr.length < 512) {
                try {
                    if (i >= bytes.length) {
                        KickoutMessage kickoutMessage;
                        KickoutMessage kickoutMessage2 = new KickoutMessage();
                        this.mNoNeedReconnect = true;
                        try {
                            this.mqttClient.disconnect();
                            this.mqttClient.close();
                            kickoutMessage = (KickoutMessage) new Gson().fromJson(new String(bArr), KickoutMessage.class);
                        } catch (Exception e) {
                            kickoutMessage = kickoutMessage2;
                        }
                        IMService.this.a().sendBroadcast(new Intent("com.xunlei.tdlive.sdk.IMService.CALLBACK_KICKOUT").putExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG, kickoutMessage.info));
                        return true;
                    }
                    if (bArr[i2] == bytes[i]) {
                        i++;
                    } else {
                        i = 0;
                    }
                    i2++;
                } catch (Exception e2) {
                    return false;
                }
            }
            return false;
        }
    }

    static {
        a = 1883;
        b = null;
    }

    private static void a(Context context, Intent intent) {
        if (b == null) {
            IMService iMService = new IMService(context.getApplicationContext());
            b = iMService;
            iMService.b();
        }
        b.a(intent, 0, 0);
    }

    public static void a(Context context, String str, int i, String str2) {
        b(new StringBuilder("actionConnect host:").append(str).append(", port:").append(i).append(", username:").append(str2).toString());
        Editor edit = context.getApplicationContext().getSharedPreferences("XLLiveIMService", 0).edit();
        edit.putString("com.xunlei.tdlive.pref.mqttHost", str);
        edit.putInt("com.xunlei.tdlive.pref.mqttPort", i);
        edit.putString("com.xunlei.tdlive.pref.userName", str2);
        edit.commit();
        a(context.getApplicationContext(), new Intent(context.getApplicationContext(), IMService.class).setAction("com.xunlei.tdlive.sdk.IMService.ACTION_CONNECT"));
    }

    public static void a(Context context) {
        a(context.getApplicationContext(), new Intent(context.getApplicationContext(), IMService.class).setAction("com.xunlei.tdlive.sdk.IMService.RECONNECT").putExtra("session_restore", true));
    }

    public static void a(Context context, long j) {
        a(context.getApplicationContext(), new Intent(context.getApplicationContext(), IMService.class).setAction("com.xunlei.tdlive.sdk.IMService.ACTION_DISCONNECT").putExtra("delay", j));
    }

    public static void a(Context context, String str, String str2, int i) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            a(context.getApplicationContext(), new Intent(context.getApplicationContext(), IMService.class).setAction("com.xunlei.tdlive.sdk.IMService.PUBLISH").putExtra("topic", str).putExtra(j.C, str2).putExtra("qos", i));
        }
    }

    public static void a(Context context, BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECT_LOST");
        intentFilter.addAction("com.xunlei.tdlive.sdk.IMService.CALLBACK_PUBLISH_ARRIVED");
        intentFilter.addAction("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED");
        intentFilter.addAction("com.xunlei.tdlive.sdk.IMService.CALLBACK_DISCONNECTED");
        intentFilter.addAction("com.xunlei.tdlive.sdk.IMService.CALLBACK_KICKOUT");
        context.getApplicationContext().registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void b(Context context, BroadcastReceiver broadcastReceiver) {
        context.getApplicationContext().unregisterReceiver(broadcastReceiver);
    }

    private static void b(String str) {
        b(str, null);
    }

    private static void b(String str, Throwable th) {
        if (th != null) {
            XLog.e("XLLiveIMService", str, th);
        } else {
            XLog.d("XLLiveIMService", str);
        }
    }

    private IMService(Context context) {
        this.c = context;
    }

    public Context a() {
        return this.c;
    }

    public void b() {
        b("service create");
        this.d = new HandlerThread("XLLiveIMService-MQTTThread");
        this.d.start();
        this.e = new MQTTConnection(this.d.getLooper());
        this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, 0, 0, new Intent("com.xunlei.tdlive.sdk.IMService.RECONNECT")).sendToTarget();
        try {
            Context a = a();
            BroadcastReceiver anonymousClass_1 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    IMService.this.e.mTryCount = 0;
                    IMService.this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, 0, 0, new Intent("com.xunlei.tdlive.sdk.IMService.RECONNECT")).sendToTarget();
                }
            };
            this.f = anonymousClass_1;
            a.registerReceiver(anonymousClass_1, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Exception e) {
        }
    }

    public int a(Intent intent, int i, int i2) {
        b(new StringBuilder("Service started with intent=").append(intent).toString());
        if (intent != null && "com.xunlei.tdlive.sdk.IMService.KEEP_ALIVE".equals(intent.getAction())) {
            b("Service keep alive");
        } else if (intent == null || intent.getLongExtra("delay", 0) == 0) {
            this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, i, i2, intent).sendToTarget();
        } else {
            this.e.sendMessageDelayed(this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, i, i2, intent), intent.getLongExtra("delay", 0));
        }
        return 1;
    }
}
