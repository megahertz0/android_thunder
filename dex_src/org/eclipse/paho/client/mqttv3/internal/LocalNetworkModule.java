package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

public class LocalNetworkModule implements NetworkModule {
    static Class class$0;
    private String brokerName;
    private Object localAdapter;
    private Class localListener;

    public LocalNetworkModule(String str) {
        this.brokerName = str;
    }

    public void start() throws IOException, MqttException {
        if (ExceptionHelper.isClassAvailable("com.ibm.mqttdirect.modules.local.bindings.localListener")) {
            try {
                this.localListener = Class.forName("com.ibm.mqttdirect.modules.local.bindings.localListener");
                Class cls = this.localListener;
                String str = "connect";
                Class[] clsArr = new Class[1];
                Class cls2 = class$0;
                if (cls2 == null) {
                    cls2 = Class.forName("java.lang.String");
                    class$0 = cls2;
                }
                clsArr[0] = cls2;
                this.localAdapter = cls.getMethod(str, clsArr).invoke(null, new Object[]{this.brokerName});
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch (Exception e2) {
            }
            if (this.localAdapter == null) {
                throw ExceptionHelper.createMqttException(32103);
            }
            return;
        }
        throw ExceptionHelper.createMqttException(32103);
    }

    public InputStream getInputStream() throws IOException {
        try {
            return (InputStream) this.localListener.getMethod("getClientInputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public OutputStream getOutputStream() throws IOException {
        try {
            return (OutputStream) this.localListener.getMethod("getClientOutputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public void stop() throws IOException {
        if (this.localAdapter != null) {
            try {
                this.localListener.getMethod("close", new Class[0]).invoke(this.localAdapter, new Object[0]);
            } catch (Exception e) {
            }
        }
    }
}
