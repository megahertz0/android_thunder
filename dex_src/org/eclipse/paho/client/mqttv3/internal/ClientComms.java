package org.eclipse.paho.client.mqttv3.internal;

import java.util.Properties;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientComms {
    public static String BUILD_LEVEL = null;
    private static final String CLASS_NAME;
    private static final byte CLOSED = (byte) 4;
    private static final byte CONNECTED = (byte) 0;
    private static final byte CONNECTING = (byte) 1;
    private static final byte DISCONNECTED = (byte) 3;
    private static final byte DISCONNECTING = (byte) 2;
    public static String VERSION;
    static Class class$0;
    private static final Logger log;
    private CommsCallback callback;
    private IMqttAsyncClient client;
    private ClientState clientState;
    private boolean closePending;
    private Object conLock;
    private MqttConnectOptions conOptions;
    private byte conState;
    private int networkModuleIndex;
    private NetworkModule[] networkModules;
    private MqttClientPersistence persistence;
    private MqttPingSender pingSender;
    private CommsReceiver receiver;
    private CommsSender sender;
    private boolean stoppingComms;
    private CommsTokenStore tokenStore;

    private class ConnectBG implements Runnable {
        Thread cBg;
        ClientComms clientComms;
        MqttConnect conPacket;
        MqttToken conToken;
        final ClientComms this$0;

        ConnectBG(ClientComms clientComms, ClientComms clientComms2, MqttToken mqttToken, MqttConnect mqttConnect) {
            this.this$0 = clientComms;
            this.clientComms = null;
            this.cBg = null;
            this.clientComms = clientComms2;
            this.conToken = mqttToken;
            this.conPacket = mqttConnect;
            this.cBg = new Thread(this, new StringBuffer("MQTT Con: ").append(clientComms.getClient().getClientId()).toString());
        }

        void start() {
            this.cBg.start();
        }

        public void run() {
            MqttException mqttException = null;
            ClientComms.access$0().fine(ClientComms.access$1(), "connectBG:run", "220");
            try {
                MqttDeliveryToken[] outstandingDelTokens = ClientComms.access$2(this.this$0).getOutstandingDelTokens();
                for (MqttDeliveryToken mqttDeliveryToken : outstandingDelTokens) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.access$2(this.this$0).saveToken(this.conToken, this.conPacket);
                NetworkModule networkModule = ClientComms.access$3(this.this$0)[ClientComms.access$4(this.this$0)];
                networkModule.start();
                ClientComms.access$6(this.this$0, new CommsReceiver(this.clientComms, ClientComms.access$5(this.this$0), ClientComms.access$2(this.this$0), networkModule.getInputStream()));
                ClientComms.access$7(this.this$0).start(new StringBuffer("MQTT Rec: ").append(this.this$0.getClient().getClientId()).toString());
                ClientComms.access$8(this.this$0, new CommsSender(this.clientComms, ClientComms.access$5(this.this$0), ClientComms.access$2(this.this$0), networkModule.getOutputStream()));
                ClientComms.access$9(this.this$0).start(new StringBuffer("MQTT Snd: ").append(this.this$0.getClient().getClientId()).toString());
                ClientComms.access$10(this.this$0).start(new StringBuffer("MQTT Call: ").append(this.this$0.getClient().getClientId()).toString());
                this.this$0.internalSend(this.conPacket, this.conToken);
            } catch (Throwable e) {
                ClientComms.access$0().fine(ClientComms.access$1(), "connectBG:run", "212", null, e);
                Throwable th = e;
            } catch (Throwable e2) {
                ClientComms.access$0().fine(ClientComms.access$1(), "connectBG:run", "209", null, e2);
                mqttException = ExceptionHelper.createMqttException(e2);
            }
            if (mqttException != null) {
                this.this$0.shutdownConnection(this.conToken, mqttException);
            }
        }
    }

    private class DisconnectBG implements Runnable {
        Thread dBg;
        MqttDisconnect disconnect;
        long quiesceTimeout;
        final ClientComms this$0;
        MqttToken token;

        DisconnectBG(ClientComms clientComms, MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) {
            this.this$0 = clientComms;
            this.dBg = null;
            this.disconnect = mqttDisconnect;
            this.quiesceTimeout = j;
            this.token = mqttToken;
        }

        void start() {
            this.dBg = new Thread(this, new StringBuffer("MQTT Disc: ").append(this.this$0.getClient().getClientId()).toString());
            this.dBg.start();
        }

        public void run() {
            ClientComms.access$0().fine(ClientComms.access$1(), "disconnectBG:run", "221");
            ClientComms.access$5(this.this$0).quiesce(this.quiesceTimeout);
            try {
                this.this$0.internalSend(this.disconnect, this.token);
                this.token.internalTok.waitUntilSent();
            } catch (MqttException e) {
            }
            this.token.internalTok.markComplete(null, null);
            this.this$0.shutdownConnection(this.token, null);
        }
    }

    static {
        VERSION = "${project.version}";
        BUILD_LEVEL = "L${build.level}";
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    static String access$1() {
        return CLASS_NAME;
    }

    static Logger access$0() {
        return log;
    }

    static int access$4(ClientComms clientComms) {
        return clientComms.networkModuleIndex;
    }

    static NetworkModule[] access$3(ClientComms clientComms) {
        return clientComms.networkModules;
    }

    static void access$6(ClientComms clientComms, CommsReceiver commsReceiver) {
        clientComms.receiver = commsReceiver;
    }

    static CommsReceiver access$7(ClientComms clientComms) {
        return clientComms.receiver;
    }

    static void access$8(ClientComms clientComms, CommsSender commsSender) {
        clientComms.sender = commsSender;
    }

    static CommsSender access$9(ClientComms clientComms) {
        return clientComms.sender;
    }

    static CommsCallback access$10(ClientComms clientComms) {
        return clientComms.callback;
    }

    static ClientState access$5(ClientComms clientComms) {
        return clientComms.clientState;
    }

    static CommsTokenStore access$2(ClientComms clientComms) {
        return clientComms.tokenStore;
    }

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.stoppingComms = false;
        this.conState = (byte) 3;
        this.conLock = new Object();
        this.closePending = false;
        this.conState = (byte) 3;
        this.client = iMqttAsyncClient;
        this.persistence = mqttClientPersistence;
        this.pingSender = mqttPingSender;
        this.pingSender.init(this);
        this.tokenStore = new CommsTokenStore(getClient().getClientId());
        this.callback = new CommsCallback(this);
        this.clientState = new ClientState(mqttClientPersistence, this.tokenStore, this.callback, this, mqttPingSender);
        this.callback.setClientState(this.clientState);
        log.setResourceName(getClient().getClientId());
    }

    CommsReceiver getReceiver() {
        return this.receiver;
    }

    void internalSend(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        log.fine(CLASS_NAME, "internalSend", "200", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.setClient(getClient());
            try {
                this.clientState.send(mqttWireMessage, mqttToken);
                return;
            } catch (MqttException e) {
                if (mqttWireMessage instanceof MqttPublish) {
                    this.clientState.undo((MqttPublish) mqttWireMessage);
                }
                throw e;
            }
        }
        log.fine(CLASS_NAME, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        throw new MqttException(32201);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (!isConnected()) {
            if ((isConnected() || !(mqttWireMessage instanceof MqttConnect)) && !(isDisconnecting() && (mqttWireMessage instanceof MqttDisconnect))) {
                log.fine(CLASS_NAME, "sendNoWait", "208");
                throw ExceptionHelper.createMqttException(32104);
            }
        }
        internalSend(mqttWireMessage, mqttToken);
    }

    public void close() throws MqttException {
        synchronized (this.conLock) {
            if (!isClosed()) {
                if (!isDisconnected()) {
                    log.fine(CLASS_NAME, "close", "224");
                    if (isConnecting()) {
                        throw new MqttException(32110);
                    } else if (isConnected()) {
                        throw ExceptionHelper.createMqttException(32100);
                    } else if (isDisconnecting()) {
                        this.closePending = true;
                        return;
                    }
                }
                this.conState = (byte) 4;
                this.clientState.close();
                this.clientState = null;
                this.callback = null;
                this.persistence = null;
                this.sender = null;
                this.pingSender = null;
                this.receiver = null;
                this.networkModules = null;
                this.conOptions = null;
                this.tokenStore = null;
            }
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (!isDisconnected() || this.closePending) {
                log.fine(CLASS_NAME, "connect", "207", new Object[]{new Byte(this.conState)});
                if (isClosed() || this.closePending) {
                    throw new MqttException(32111);
                } else if (isConnecting()) {
                    throw new MqttException(32110);
                } else if (isDisconnecting()) {
                    throw new MqttException(32102);
                } else {
                    throw ExceptionHelper.createMqttException(32100);
                }
            }
            log.fine(CLASS_NAME, "connect", "214");
            this.conState = (byte) 1;
            this.conOptions = mqttConnectOptions;
            MqttConnect mqttConnect = new MqttConnect(this.client.getClientId(), mqttConnectOptions.getMqttVersion(), mqttConnectOptions.isCleanSession(), mqttConnectOptions.getKeepAliveInterval(), mqttConnectOptions.getUserName(), mqttConnectOptions.getPassword(), mqttConnectOptions.getWillMessage(), mqttConnectOptions.getWillDestination());
            this.clientState.setKeepAliveSecs((long) mqttConnectOptions.getKeepAliveInterval());
            this.clientState.setCleanSession(mqttConnectOptions.isCleanSession());
            this.tokenStore.open();
            new ConnectBG(this, this, mqttToken, mqttConnect).start();
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.conLock) {
            if (returnCode == 0) {
                log.fine(CLASS_NAME, "connectComplete", "215");
                this.conState = (byte) 0;
                return;
            }
            log.fine(CLASS_NAME, "connectComplete", "204", new Object[]{new Integer(returnCode)});
            throw mqttException;
        }
    }

    public void shutdownConnection(MqttToken mqttToken, MqttException mqttException) {
        int i = 1;
        synchronized (this.conLock) {
            if (this.stoppingComms || this.closePending) {
                return;
            }
            this.stoppingComms = true;
            log.fine(CLASS_NAME, "shutdownConnection", "216");
            if (isConnected() || isDisconnecting()) {
                int i2 = 1;
            } else {
                Object obj = null;
            }
            this.conState = (byte) 2;
            if (!(mqttToken == null || mqttToken.isComplete())) {
                mqttToken.internalTok.setException(mqttException);
            }
            if (this.callback != null) {
                this.callback.stop();
            }
            try {
                if (this.networkModules != null) {
                    NetworkModule networkModule = this.networkModules[this.networkModuleIndex];
                    if (networkModule != null) {
                        networkModule.stop();
                    }
                }
            } catch (Exception e) {
            }
            if (this.receiver != null) {
                this.receiver.stop();
            }
            this.tokenStore.quiesce(new MqttException(32102));
            MqttToken handleOldTokens = handleOldTokens(mqttToken, mqttException);
            try {
                this.clientState.disconnected(mqttException);
            } catch (Exception e2) {
            }
            if (this.sender != null) {
                this.sender.stop();
            }
            if (this.pingSender != null) {
                this.pingSender.stop();
            }
            try {
                if (this.persistence != null) {
                    this.persistence.close();
                }
            } catch (Exception e3) {
            }
            synchronized (this.conLock) {
                log.fine(CLASS_NAME, "shutdownConnection", "217");
                this.conState = (byte) 3;
                this.stoppingComms = false;
            }
            int i3 = handleOldTokens != null ? 1 : 0;
            if (this.callback == null) {
                i = 0;
            }
            if ((i3 & i) != 0) {
                this.callback.asyncOperationComplete(handleOldTokens);
            }
            if (!(i2 == 0 || this.callback == null)) {
                this.callback.connectionLost(mqttException);
            }
            synchronized (this.conLock) {
                if (this.closePending) {
                    try {
                        close();
                    } catch (Exception e4) {
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.eclipse.paho.client.mqttv3.MqttToken handleOldTokens(org.eclipse.paho.client.mqttv3.MqttToken r6, org.eclipse.paho.client.mqttv3.MqttException r7) {
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientComms.handleOldTokens(org.eclipse.paho.client.mqttv3.MqttToken, org.eclipse.paho.client.mqttv3.MqttException):org.eclipse.paho.client.mqttv3.MqttToken");
        /*
        this = this;
        r0 = log;
        r1 = CLASS_NAME;
        r2 = "handleOldTokens";
        r3 = "222";
        r0.fine(r1, r2, r3);
        r1 = 0;
        if (r6 == 0) goto L_0x0029;
    L_0x0010:
        r0 = r5.tokenStore;	 Catch:{ Exception -> 0x006a }
        r2 = r6.internalTok;	 Catch:{ Exception -> 0x006a }
        r2 = r2.getKey();	 Catch:{ Exception -> 0x006a }
        r0 = r0.getToken(r2);	 Catch:{ Exception -> 0x006a }
        if (r0 != 0) goto L_0x0029;
    L_0x001e:
        r0 = r5.tokenStore;	 Catch:{ Exception -> 0x006a }
        r2 = r6.internalTok;	 Catch:{ Exception -> 0x006a }
        r2 = r2.getKey();	 Catch:{ Exception -> 0x006a }
        r0.saveToken(r6, r2);	 Catch:{ Exception -> 0x006a }
    L_0x0029:
        r0 = r5.clientState;	 Catch:{ Exception -> 0x006a }
        r0 = r0.resolveOldTokens(r7);	 Catch:{ Exception -> 0x006a }
        r2 = r0.elements();	 Catch:{ Exception -> 0x006a }
    L_0x0033:
        r0 = r2.hasMoreElements();	 Catch:{ Exception -> 0x0067 }
        if (r0 != 0) goto L_0x003b;
    L_0x0039:
        r0 = r1;
    L_0x003a:
        return r0;
    L_0x003b:
        r0 = r2.nextElement();	 Catch:{ Exception -> 0x0067 }
        r0 = (org.eclipse.paho.client.mqttv3.MqttToken) r0;	 Catch:{ Exception -> 0x0067 }
        r3 = r0.internalTok;	 Catch:{ Exception -> 0x0067 }
        r3 = r3.getKey();	 Catch:{ Exception -> 0x0067 }
        r4 = "Disc";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x0067 }
        if (r3 != 0) goto L_0x005f;
    L_0x0050:
        r3 = r0.internalTok;	 Catch:{ Exception -> 0x0067 }
        r3 = r3.getKey();	 Catch:{ Exception -> 0x0067 }
        r4 = "Con";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x0067 }
        if (r3 == 0) goto L_0x0061;
    L_0x005f:
        r1 = r0;
        goto L_0x0033;
    L_0x0061:
        r3 = r5.callback;	 Catch:{ Exception -> 0x0067 }
        r3.asyncOperationComplete(r0);	 Catch:{ Exception -> 0x0067 }
        goto L_0x0033;
    L_0x0067:
        r0 = move-exception;
        r0 = r1;
        goto L_0x003a;
    L_0x006a:
        r0 = move-exception;
        r0 = r1;
        goto L_0x003a;
        */
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (isClosed()) {
                log.fine(CLASS_NAME, "disconnect", "223");
                throw ExceptionHelper.createMqttException(32111);
            } else if (isDisconnected()) {
                log.fine(CLASS_NAME, "disconnect", "211");
                throw ExceptionHelper.createMqttException(32101);
            } else if (isDisconnecting()) {
                log.fine(CLASS_NAME, "disconnect", "219");
                throw ExceptionHelper.createMqttException(32102);
            } else if (Thread.currentThread() == this.callback.getThread()) {
                log.fine(CLASS_NAME, "disconnect", "210");
                throw ExceptionHelper.createMqttException(32107);
            } else {
                log.fine(CLASS_NAME, "disconnect", "218");
                this.conState = (byte) 2;
                new DisconnectBG(this, mqttDisconnect, j, mqttToken).start();
            }
        }
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.clientState.quiesce(j);
        MqttToken mqttToken = new MqttToken(this.client.getClientId());
        try {
            internalSend(new MqttDisconnect(), mqttToken);
            mqttToken.waitForCompletion(j2);
        } catch (Exception e) {
        }
        mqttToken.internalTok.markComplete(null, null);
        shutdownConnection(mqttToken, null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z = true;
        synchronized (this.conLock) {
            if (this.conState != (byte) 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 2;
        }
        return z;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 4;
        }
        return z;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.callback.setCallback(mqttCallback);
    }

    protected MqttTopic getTopic(String str) {
        return new MqttTopic(str, this);
    }

    public void setNetworkModuleIndex(int i) {
        this.networkModuleIndex = i;
    }

    public int getNetworkModuleIndex() {
        return this.networkModuleIndex;
    }

    public NetworkModule[] getNetworkModules() {
        return this.networkModules;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.networkModules = networkModuleArr;
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.tokenStore.getOutstandingDelTokens();
    }

    protected void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.clientState.deliveryComplete(mqttPublish);
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    public long getKeepAlive() {
        return this.clientState.getKeepAlive();
    }

    public ClientState getClientState() {
        return this.clientState;
    }

    public MqttConnectOptions getConOptions() {
        return this.conOptions;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", new Integer(this.conState));
        properties.put("serverURI", getClient().getServerURI());
        properties.put("callback", this.callback);
        properties.put("stoppingComms", new Boolean(this.stoppingComms));
        return properties;
    }

    public MqttToken checkForActivity() {
        try {
            return this.clientState.checkForActivity();
        } catch (Exception e) {
            handleRunException(e);
            return null;
        } catch (Exception e2) {
            handleRunException(e2);
            return null;
        }
    }

    private void handleRunException(Exception exception) {
        log.fine(CLASS_NAME, "handleRunException", "804", null, exception);
        if (exception instanceof MqttException) {
            MqttException mqttException = (MqttException) exception;
        } else {
            exception = new MqttException(32109, exception);
        }
        shutdownConnection(null, exception);
    }
}
