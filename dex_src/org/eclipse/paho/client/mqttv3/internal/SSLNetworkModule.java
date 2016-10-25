package org.eclipse.paho.client.mqttv3.internal;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class SSLNetworkModule extends TCPNetworkModule {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private String[] enabledCiphers;
    private int handshakeTimeoutSecs;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public SSLNetworkModule(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        log.setResourceName(str2);
    }

    public String[] getEnabledCiphers() {
        return this.enabledCiphers;
    }

    public void setEnabledCiphers(String[] strArr) {
        this.enabledCiphers = strArr;
        if (this.socket != null && strArr != null) {
            if (log.isLoggable(SimpleLog.LOG_LEVEL_ERROR)) {
                Object obj = BuildConfig.VERSION_NAME;
                int i = 0;
                while (i < strArr.length) {
                    if (i > 0) {
                        obj = new StringBuffer(String.valueOf(obj)).append(",").toString();
                    }
                    String toString = new StringBuffer(String.valueOf(obj)).append(strArr[i]).toString();
                    i++;
                    String str = toString;
                }
                log.fine(CLASS_NAME, "setEnabledCiphers", "260", new Object[]{obj});
            }
            ((SSLSocket) this.socket).setEnabledCipherSuites(strArr);
        }
    }

    public void setSSLhandshakeTimeout(int i) {
        super.setConnectTimeout(i);
        this.handshakeTimeoutSecs = i;
    }

    public void start() throws IOException, MqttException {
        super.start();
        setEnabledCiphers(this.enabledCiphers);
        int soTimeout = this.socket.getSoTimeout();
        if (soTimeout == 0) {
            this.socket.setSoTimeout(this.handshakeTimeoutSecs * 1000);
        }
        ((SSLSocket) this.socket).startHandshake();
        this.socket.setSoTimeout(soTimeout);
    }
}
