package org.eclipse.paho.client.mqttv3;

import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.Hashtable;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLASS_NAME;
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = '\udbff';
    private static final char MIN_HIGH_SURROGATE = '\ud800';
    private static final long QUIESCE_TIMEOUT = 30000;
    static Class class$0;
    private static final Logger log;
    private String clientId;
    protected ClientComms comms;
    private MqttClientPersistence persistence;
    private String serverURI;
    private Hashtable topics;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.MqttAsyncClient");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        log.setResourceName(str2);
        if (str2 == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i = 0;
        int i2 = 0;
        while (i < str2.length() - 1) {
            if (Character_isHighSurrogate(str2.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        if (i2 > 65535) {
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        MqttConnectOptions.validateURI(str);
        this.serverURI = str;
        this.clientId = str2;
        this.persistence = mqttClientPersistence;
        if (this.persistence == null) {
            this.persistence = new MemoryPersistence();
        }
        log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
        this.persistence.open(str2, str);
        this.comms = new ClientComms(this, this.persistence, mqttPingSender);
        this.persistence.close();
        this.topics = new Hashtable();
    }

    protected static boolean Character_isHighSurrogate(char c) {
        return c >= '\ud800' && c <= '\udbff';
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        int i = 0;
        log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        while (i < serverURIs.length) {
            networkModuleArr[i] = createNetworkModule(serverURIs[i], mqttConnectOptions);
            i++;
        }
        log.fine(CLASS_NAME, "createNetworkModules", "108");
        return networkModuleArr;
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        String substring;
        NetworkModule tCPNetworkModule;
        switch (MqttConnectOptions.validateURI(str)) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                substring = str.substring(SimpleLog.LOG_LEVEL_FATAL);
                String hostName = getHostName(substring);
                int port = getPort(substring, 1883);
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                tCPNetworkModule = new TCPNetworkModule(socketFactory, hostName, port, this.clientId);
                ((TCPNetworkModule) tCPNetworkModule).setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return tCPNetworkModule;
            case SimpleLog.LOG_LEVEL_TRACE:
                SSLSocketFactoryFactory sSLSocketFactoryFactory;
                substring = str.substring(SimpleLog.LOG_LEVEL_FATAL);
                String hostName2 = getHostName(substring);
                int port2 = getPort(substring, 8883);
                if (socketFactory == null) {
                    SSLSocketFactoryFactory sSLSocketFactoryFactory2 = new SSLSocketFactoryFactory();
                    Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties != null) {
                        sSLSocketFactoryFactory2.initialize(sSLProperties, null);
                    }
                    sSLSocketFactoryFactory = sSLSocketFactoryFactory2;
                    socketFactory = sSLSocketFactoryFactory2.createSocketFactory(null);
                } else if (socketFactory instanceof SSLSocketFactory) {
                    sSLSocketFactoryFactory = null;
                } else {
                    throw ExceptionHelper.createMqttException(32105);
                }
                tCPNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, hostName2, port2, this.clientId);
                ((SSLNetworkModule) tCPNetworkModule).setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory == null) {
                    return tCPNetworkModule;
                }
                String[] enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null);
                if (enabledCipherSuites == null) {
                    return tCPNetworkModule;
                }
                ((SSLNetworkModule) tCPNetworkModule).setEnabledCiphers(enabledCipherSuites);
                return tCPNetworkModule;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new LocalNetworkModule(str.substring(SpdyProtocol.PUBKEY_SEQ_ADASH));
            default:
                return null;
        }
    }

    private int getPort(String str, int i) {
        int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_toolbarStyle);
        return lastIndexOf == -1 ? i : Integer.parseInt(str.substring(lastIndexOf + 1));
    }

    private String getHostName(String str) {
        int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
        int lastIndexOf2 = str.lastIndexOf(R.styleable.AppCompatTheme_toolbarStyle);
        if (lastIndexOf2 == -1) {
            lastIndexOf2 = str.length();
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return connect(null, null);
    }

    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return connect(mqttConnectOptions, null, null);
    }

    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        } else if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        } else if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        } else if (this.comms.isClosed()) {
            throw new MqttException(32111);
        } else {
            Logger logger = log;
            String str = CLASS_NAME;
            String str2 = "connect";
            String str3 = "103";
            Object[] objArr = new Object[8];
            objArr[0] = Boolean.valueOf(mqttConnectOptions.isCleanSession());
            objArr[1] = new Integer(mqttConnectOptions.getConnectionTimeout());
            objArr[2] = new Integer(mqttConnectOptions.getKeepAliveInterval());
            objArr[3] = mqttConnectOptions.getUserName();
            objArr[4] = mqttConnectOptions.getPassword() == null ? "[null]" : "[notnull]";
            objArr[5] = mqttConnectOptions.getWillMessage() == null ? "[null]" : "[notnull]";
            objArr[6] = obj;
            objArr[7] = iMqttActionListener;
            logger.fine(str, str2, str3, objArr);
            this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions));
            IMqttToken mqttToken = new MqttToken(getClientId());
            Object connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, mqttConnectOptions, mqttToken, obj, iMqttActionListener);
            mqttToken.setActionCallback(connectActionListener);
            mqttToken.setUserContext(this);
            this.comms.setNetworkModuleIndex(0);
            connectActionListener.connect();
            return mqttToken;
        }
    }

    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(QUIESCE_TIMEOUT, obj, iMqttActionListener);
    }

    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        log.fine(CLASS_NAME, "disconnect", "104", new Object[]{new Long(j), obj, iMqttActionListener});
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            log.fine(CLASS_NAME, "disconnect", "108");
            return mqttToken;
        } catch (Throwable e) {
            log.fine(CLASS_NAME, "disconnect", "105", null, e);
            throw e;
        }
    }

    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(QUIESCE_TIMEOUT, DISCONNECT_TIMEOUT);
    }

    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(QUIESCE_TIMEOUT, j);
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.comms.disconnectForcibly(j, j2);
    }

    public boolean isConnected() {
        return this.comms.isConnected();
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getServerURI() {
        return this.serverURI;
    }

    protected MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic != null) {
            return mqttTopic;
        }
        mqttTopic = new MqttTopic(str, this.comms);
        this.topics.put(str, mqttTopic);
        return mqttTopic;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        log.fine(CLASS_NAME, "ping", "117");
        IMqttToken checkForActivity = this.comms.checkForActivity();
        log.fine(CLASS_NAME, "ping", "118");
        return checkForActivity;
    }

    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, null, null);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, null, null);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        Object obj2 = BuildConfig.VERSION_NAME;
        int i = 0;
        while (i < strArr.length) {
            if (i > 0) {
                obj2 = new StringBuffer(String.valueOf(obj2)).append(", ").toString();
            }
            String toString = new StringBuffer(String.valueOf(obj2)).append("topic=").append(strArr[i]).append(" qos=").append(iArr[i]).toString();
            MqttTopic.validate(strArr[i], true);
            i++;
            String str = toString;
        }
        log.fine(CLASS_NAME, "subscribe", "106", new Object[]{obj2, obj, iMqttActionListener});
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        log.fine(CLASS_NAME, "subscribe", "109");
        return mqttToken;
    }

    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, null, null);
    }

    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, null, null);
    }

    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Object obj2 = BuildConfig.VERSION_NAME;
        int i = 0;
        while (i < strArr.length) {
            if (i > 0) {
                obj2 = new StringBuffer(String.valueOf(obj2)).append(", ").toString();
            }
            String toString = new StringBuffer(String.valueOf(obj2)).append(strArr[i]).toString();
            MqttTopic.validate(strArr[i], true);
            i++;
            String str = toString;
        }
        log.fine(CLASS_NAME, "unsubscribe", "107", new Object[]{obj2, obj, iMqttActionListener});
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        log.fine(CLASS_NAME, "unsubscribe", "110");
        return mqttToken;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.comms.setCallback(mqttCallback);
    }

    public static String generateClientId() {
        return new StringBuffer(CLIENT_ID_PREFIX).append(System.nanoTime()).toString();
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        return publish(str, bArr, i, z, null, null);
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return publish(str, mqttMessage, null, null);
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        log.fine(CLASS_NAME, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        Object mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        log.fine(CLASS_NAME, "publish", "112");
        return mqttDeliveryToken;
    }

    public void close() throws MqttException {
        log.fine(CLASS_NAME, "close", "113");
        this.comms.close();
        log.fine(CLASS_NAME, "close", "114");
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }
}
