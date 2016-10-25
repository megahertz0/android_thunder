package org.eclipse.paho.client.mqttv3;

import com.xunlei.xiazaibao.BuildConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import org.android.agoo.common.AgooConstants;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttConnectOptions {
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    protected static final int URI_TYPE_LOCAL = 2;
    protected static final int URI_TYPE_SSL = 1;
    protected static final int URI_TYPE_TCP = 0;
    private int MqttVersion;
    private boolean cleanSession;
    private int connectionTimeout;
    private int keepAliveInterval;
    private char[] password;
    private String[] serverURIs;
    private SocketFactory socketFactory;
    private Properties sslClientProps;
    private String userName;
    private String willDestination;
    private MqttMessage willMessage;

    public MqttConnectOptions() {
        this.keepAliveInterval = 60;
        this.willDestination = null;
        this.willMessage = null;
        this.sslClientProps = null;
        this.cleanSession = true;
        this.connectionTimeout = 30;
        this.serverURIs = null;
        this.MqttVersion = 0;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        if (str == null || !str.trim().equals(BuildConfig.VERSION_NAME)) {
            this.userName = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setWill(MqttTopic mqttTopic, byte[] bArr, int i, boolean z) {
        String name = mqttTopic.getName();
        validateWill(name, bArr);
        setWill(name, new MqttMessage(bArr), i, z);
    }

    public void setWill(String str, byte[] bArr, int i, boolean z) {
        validateWill(str, bArr);
        setWill(str, new MqttMessage(bArr), i, z);
    }

    private void validateWill(String str, Object obj) {
        if (str == null || obj == null) {
            throw new IllegalArgumentException();
        }
        MqttTopic.validate(str, false);
    }

    protected void setWill(String str, MqttMessage mqttMessage, int i, boolean z) {
        this.willDestination = str;
        this.willMessage = mqttMessage;
        this.willMessage.setQos(i);
        this.willMessage.setRetained(z);
        this.willMessage.setMutable(false);
    }

    public int getKeepAliveInterval() {
        return this.keepAliveInterval;
    }

    public int getMqttVersion() {
        return this.MqttVersion;
    }

    public void setKeepAliveInterval(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.keepAliveInterval = i;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.connectionTimeout = i;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public String getWillDestination() {
        return this.willDestination;
    }

    public MqttMessage getWillMessage() {
        return this.willMessage;
    }

    public Properties getSSLProperties() {
        return this.sslClientProps;
    }

    public void setSSLProperties(Properties properties) {
        this.sslClientProps = properties;
    }

    public boolean isCleanSession() {
        return this.cleanSession;
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public String[] getServerURIs() {
        return this.serverURIs;
    }

    public void setServerURIs(String[] strArr) {
        for (int i = MQTT_VERSION_DEFAULT; i < strArr.length; i++) {
            validateURI(strArr[i]);
        }
        this.serverURIs = strArr;
    }

    protected static int validateURI(String str) {
        try {
            URI uri = new URI(str);
            if (!uri.getPath().equals(BuildConfig.VERSION_NAME)) {
                throw new IllegalArgumentException(str);
            } else if (uri.getScheme().equals("tcp")) {
                return MQTT_VERSION_DEFAULT;
            } else {
                if (uri.getScheme().equals("ssl")) {
                    return URI_TYPE_SSL;
                }
                if (uri.getScheme().equals(AgooConstants.MESSAGE_LOCAL)) {
                    return URI_TYPE_LOCAL;
                }
                throw new IllegalArgumentException(str);
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(str);
        }
    }

    public void setMqttVersion(int i) throws IllegalArgumentException {
        if (i == 0 || i == 3 || i == 4) {
            this.MqttVersion = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("MqttVersion", new Integer(getMqttVersion()));
        properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
        properties.put("ConTimeout", new Integer(getConnectionTimeout()));
        properties.put("KeepAliveInterval", new Integer(getKeepAliveInterval()));
        properties.put("UserName", getUserName() == null ? "null" : getUserName());
        properties.put("WillDestination", getWillDestination() == null ? "null" : getWillDestination());
        if (getSocketFactory() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", getSSLProperties());
        }
        return properties;
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }
}
