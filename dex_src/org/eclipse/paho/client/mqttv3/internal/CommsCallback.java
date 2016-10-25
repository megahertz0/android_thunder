package org.eclipse.paho.client.mqttv3.internal;

import java.util.Vector;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsCallback implements Runnable {
    private static final String CLASS_NAME;
    private static final int INBOUND_QUEUE_SIZE = 10;
    static Class class$0;
    private static final Logger log;
    private Thread callbackThread;
    private ClientComms clientComms;
    private ClientState clientState;
    private Vector completeQueue;
    private Object lifecycle;
    private Vector messageQueue;
    private MqttCallback mqttCallback;
    private boolean quiescing;
    public boolean running;
    private Object spaceAvailable;
    private Object workAvailable;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsCallback");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    CommsCallback(ClientComms clientComms) {
        this.running = false;
        this.quiescing = false;
        this.lifecycle = new Object();
        this.workAvailable = new Object();
        this.spaceAvailable = new Object();
        this.clientComms = clientComms;
        this.messageQueue = new Vector(10);
        this.completeQueue = new Vector(10);
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public void start(String str) {
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.messageQueue.clear();
                this.completeQueue.clear();
                this.running = true;
                this.quiescing = false;
                this.callbackThread = new Thread(this, str);
                this.callbackThread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            if (this.running) {
                log.fine(CLASS_NAME, "stop", "700");
                this.running = false;
                if (!Thread.currentThread().equals(this.callbackThread)) {
                    try {
                        synchronized (this.workAvailable) {
                            log.fine(CLASS_NAME, "stop", "701");
                            this.workAvailable.notifyAll();
                        }
                        this.callbackThread.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.callbackThread = null;
            log.fine(CLASS_NAME, "stop", "703");
        }
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
    }

    public void run() {
        while (this.running) {
            try {
                synchronized (this.workAvailable) {
                    if (this.running && this.messageQueue.isEmpty() && this.completeQueue.isEmpty()) {
                        log.fine(CLASS_NAME, "run", "704");
                        this.workAvailable.wait();
                    }
                }
            } catch (InterruptedException e) {
            }
            try {
                if (this.running) {
                    MqttToken mqttToken;
                    MqttPublish mqttPublish;
                    synchronized (this.completeQueue) {
                        if (this.completeQueue.isEmpty()) {
                            mqttToken = null;
                        } else {
                            mqttToken = (MqttToken) this.completeQueue.elementAt(0);
                            this.completeQueue.removeElementAt(0);
                        }
                    }
                    if (mqttToken != null) {
                        handleActionComplete(mqttToken);
                    }
                    synchronized (this.messageQueue) {
                        if (this.messageQueue.isEmpty()) {
                            mqttPublish = null;
                        } else {
                            mqttPublish = (MqttPublish) this.messageQueue.elementAt(0);
                            this.messageQueue.removeElementAt(0);
                        }
                    }
                    if (mqttPublish != null) {
                        handleMessage(mqttPublish);
                    }
                }
                if (this.quiescing) {
                    this.clientState.checkQuiesceLock();
                }
            } catch (Throwable th) {
                try {
                    log.fine(CLASS_NAME, "run", "714", null, th);
                    this.running = false;
                    this.clientComms.shutdownConnection(null, new MqttException(th));
                } catch (Throwable th2) {
                    synchronized (this.spaceAvailable) {
                    }
                    log.fine(CLASS_NAME, "run", "706");
                    this.spaceAvailable.notifyAll();
                }
            }
            synchronized (this.spaceAvailable) {
                log.fine(CLASS_NAME, "run", "706");
                this.spaceAvailable.notifyAll();
            }
        }
    }

    private void handleActionComplete(MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            log.fine(CLASS_NAME, "handleActionComplete", "705", new Object[]{mqttToken.internalTok.getKey()});
            mqttToken.internalTok.notifyComplete();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.mqttCallback != null && (mqttToken instanceof MqttDeliveryToken) && mqttToken.isComplete()) {
                    this.mqttCallback.deliveryComplete((MqttDeliveryToken) mqttToken);
                }
                fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete()) {
                if ((mqttToken instanceof MqttDeliveryToken) || (mqttToken.getActionCallback() instanceof IMqttActionListener)) {
                    mqttToken.internalTok.setNotified(true);
                }
            }
            if (mqttToken.isComplete()) {
                this.clientState.notifyComplete(mqttToken);
            }
        }
    }

    public void connectionLost(MqttException mqttException) {
        try {
            if (this.mqttCallback != null && mqttException != null) {
                log.fine(CLASS_NAME, "connectionLost", "708", new Object[]{mqttException});
                this.mqttCallback.connectionLost(mqttException);
            }
        } catch (Throwable th) {
            log.fine(CLASS_NAME, "connectionLost", "720", new Object[]{th});
        }
    }

    public void fireActionEvent(MqttToken mqttToken) {
        if (mqttToken != null) {
            IMqttActionListener actionCallback = mqttToken.getActionCallback();
            if (actionCallback == null) {
                return;
            }
            if (mqttToken.getException() == null) {
                log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
                actionCallback.onSuccess(mqttToken);
                return;
            }
            log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onFailure(mqttToken, mqttToken.getException());
        }
    }

    public void messageArrived(MqttPublish mqttPublish) {
        if (this.mqttCallback != null) {
            synchronized (this.spaceAvailable) {
                while (this.running && !this.quiescing && this.messageQueue.size() >= 10) {
                    try {
                        log.fine(CLASS_NAME, "messageArrived", "709");
                        this.spaceAvailable.wait(200);
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (!this.quiescing) {
                this.messageQueue.addElement(mqttPublish);
                synchronized (this.workAvailable) {
                    log.fine(CLASS_NAME, "messageArrived", "710");
                    this.workAvailable.notifyAll();
                }
            }
        }
    }

    public void quiesce() {
        this.quiescing = true;
        synchronized (this.spaceAvailable) {
            log.fine(CLASS_NAME, "quiesce", "711");
            this.spaceAvailable.notifyAll();
        }
    }

    public boolean isQuiesced() {
        return this.quiescing && this.completeQueue.size() == 0 && this.messageQueue.size() == 0;
    }

    private void handleMessage(MqttPublish mqttPublish) throws MqttException, Exception {
        if (this.mqttCallback != null) {
            log.fine(CLASS_NAME, "handleMessage", "713", new Object[]{new Integer(mqttPublish.getMessageId()), mqttPublish.getTopicName()});
            this.mqttCallback.messageArrived(r0, mqttPublish.getMessage());
            if (mqttPublish.getMessage().getQos() == 1) {
                this.clientComms.internalSend(new MqttPubAck(mqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
            } else if (mqttPublish.getMessage().getQos() == 2) {
                this.clientComms.deliveryComplete(mqttPublish);
                this.clientComms.internalSend(new MqttPubComp(mqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
            }
        }
    }

    public void asyncOperationComplete(MqttToken mqttToken) {
        if (this.running) {
            this.completeQueue.addElement(mqttToken);
            synchronized (this.workAvailable) {
                log.fine(CLASS_NAME, "asyncOperationComplete", "715", new Object[]{mqttToken.internalTok.getKey()});
                this.workAvailable.notifyAll();
            }
            return;
        }
        try {
            handleActionComplete(mqttToken);
        } catch (Throwable th) {
            log.fine(CLASS_NAME, "asyncOperationComplete", "719", null, th);
            this.clientComms.shutdownConnection(null, new MqttException(th));
        }
    }

    protected Thread getThread() {
        return this.callbackThread;
    }
}
