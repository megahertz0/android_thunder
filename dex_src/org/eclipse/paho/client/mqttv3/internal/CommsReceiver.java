package org.eclipse.paho.client.mqttv3.internal;

import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsReceiver implements Runnable {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;
    private MqttInputStream in;
    private Object lifecycle;
    private Thread recThread;
    private volatile boolean receiving;
    private boolean running;
    private CommsTokenStore tokenStore;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsReceiver");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        this.running = false;
        this.lifecycle = new Object();
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.recThread = null;
        this.in = new MqttInputStream(clientState, inputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str) {
        log.fine(CLASS_NAME, "start", "855");
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                this.recThread = new Thread(this, str);
                this.recThread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            log.fine(CLASS_NAME, "stop", "850");
            if (this.running) {
                this.running = false;
                this.receiving = false;
                if (!Thread.currentThread().equals(this.recThread)) {
                    try {
                        this.recThread.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        this.recThread = null;
        log.fine(CLASS_NAME, "stop", "851");
    }

    public void run() {
        MqttToken mqttToken = null;
        while (this.running && this.in != null) {
            try {
                MqttToken mqttToken2;
                log.fine(CLASS_NAME, "run", "852");
                this.receiving = this.in.available() > 0;
                MqttWireMessage readMqttWireMessage = this.in.readMqttWireMessage();
                this.receiving = false;
                if (readMqttWireMessage instanceof MqttAck) {
                    mqttToken = this.tokenStore.getToken(readMqttWireMessage);
                    if (mqttToken != null) {
                        synchronized (mqttToken) {
                            this.clientState.notifyReceivedAck((MqttAck) readMqttWireMessage);
                        }
                        mqttToken2 = mqttToken;
                        this.receiving = false;
                        mqttToken = mqttToken2;
                    } else {
                        throw new MqttException(6);
                    }
                } else {
                    this.clientState.notifyReceivedMsg(readMqttWireMessage);
                    mqttToken2 = mqttToken;
                    this.receiving = false;
                    mqttToken = mqttToken2;
                }
            } catch (Throwable e) {
                MqttToken mqttToken3 = mqttToken;
                log.fine(CLASS_NAME, "run", "856", null, e);
                this.running = false;
                this.clientComms.shutdownConnection(mqttToken3, e);
                mqttToken2 = mqttToken3;
            } catch (Throwable e2) {
                log.fine(CLASS_NAME, "run", "853");
                this.running = false;
                if (this.clientComms.isDisconnecting()) {
                    mqttToken2 = mqttToken;
                } else {
                    this.clientComms.shutdownConnection(mqttToken, new MqttException(32109, e2));
                    mqttToken2 = mqttToken;
                }
            }
        }
        log.fine(CLASS_NAME, "run", "854");
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isReceiving() {
        return this.receiving;
    }
}
