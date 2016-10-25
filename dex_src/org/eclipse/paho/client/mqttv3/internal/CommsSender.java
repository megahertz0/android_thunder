package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsSender implements Runnable {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;
    private Object lifecycle;
    private MqttOutputStream out;
    private boolean running;
    private Thread sendThread;
    private CommsTokenStore tokenStore;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsSender");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public CommsSender(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, OutputStream outputStream) {
        this.running = false;
        this.lifecycle = new Object();
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.sendThread = null;
        this.out = new MqttOutputStream(clientState, outputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str) {
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                this.sendThread = new Thread(this, str);
                this.sendThread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            log.fine(CLASS_NAME, "stop", "800");
            if (this.running) {
                this.running = false;
                if (!Thread.currentThread().equals(this.sendThread)) {
                    try {
                        this.clientState.notifyQueueLock();
                        this.sendThread.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.sendThread = null;
            log.fine(CLASS_NAME, "stop", "801");
        }
    }

    public void run() {
        MqttWireMessage mqttWireMessage = null;
        while (this.running && this.out != null) {
            try {
                mqttWireMessage = this.clientState.get();
                if (mqttWireMessage != null) {
                    log.fine(CLASS_NAME, "run", "802", new Object[]{mqttWireMessage.getKey(), mqttWireMessage});
                    if (mqttWireMessage instanceof MqttAck) {
                        this.out.write(mqttWireMessage);
                        this.out.flush();
                    } else {
                        MqttToken token = this.tokenStore.getToken(mqttWireMessage);
                        if (token != null) {
                            synchronized (token) {
                                this.out.write(mqttWireMessage);
                                this.out.flush();
                                this.clientState.notifySent(mqttWireMessage);
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    log.fine(CLASS_NAME, "run", "803");
                    this.running = false;
                }
            } catch (IOException e) {
                if (!(mqttWireMessage instanceof MqttDisconnect)) {
                    throw e;
                }
            } catch (Exception e2) {
                handleRunException(mqttWireMessage, e2);
            } catch (Exception e22) {
                handleRunException(mqttWireMessage, e22);
            } catch (Throwable th) {
            }
        }
        log.fine(CLASS_NAME, "run", "805");
    }

    private void handleRunException(MqttWireMessage mqttWireMessage, Exception exception) {
        log.fine(CLASS_NAME, "handleRunException", "804", null, exception);
        if (exception instanceof MqttException) {
            MqttException mqttException = (MqttException) exception;
        } else {
            exception = new MqttException(32109, exception);
        }
        this.running = false;
        this.clientComms.shutdownConnection(null, exception);
    }
}
