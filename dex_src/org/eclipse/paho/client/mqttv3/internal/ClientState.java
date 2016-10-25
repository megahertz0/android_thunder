package org.eclipse.paho.client.mqttv3.internal;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientState {
    private static final String CLASS_NAME;
    private static final int DEFAULT_MAX_INFLIGHT = 10;
    private static final int MAX_MSG_ID = 65535;
    private static final int MIN_MSG_ID = 1;
    private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
    private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
    private static final String PERSISTENCE_SENT_PREFIX = "s-";
    static Class class$0;
    private static final Logger log;
    private int actualInFlight;
    private CommsCallback callback;
    private boolean cleanSession;
    private ClientComms clientComms;
    private boolean connected;
    private int inFlightPubRels;
    private Hashtable inUseMsgIds;
    private Hashtable inboundQoS2;
    private long keepAlive;
    private long lastInboundActivity;
    private long lastOutboundActivity;
    private long lastPing;
    private int maxInflight;
    private int nextMsgId;
    private Hashtable outboundQoS1;
    private Hashtable outboundQoS2;
    private volatile Vector pendingFlows;
    private volatile Vector pendingMessages;
    private MqttClientPersistence persistence;
    private MqttWireMessage pingCommand;
    private int pingOutstanding;
    private Object pingOutstandingLock;
    private MqttPingSender pingSender;
    private Object queueLock;
    private Object quiesceLock;
    private boolean quiescing;
    private CommsTokenStore tokenStore;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientState");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    protected ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender) throws MqttException {
        this.nextMsgId = 0;
        this.clientComms = null;
        this.callback = null;
        this.maxInflight = 10;
        this.actualInFlight = 0;
        this.inFlightPubRels = 0;
        this.queueLock = new Object();
        this.quiesceLock = new Object();
        this.quiescing = false;
        this.lastOutboundActivity = 0;
        this.lastInboundActivity = 0;
        this.lastPing = 0;
        this.pingOutstandingLock = new Object();
        this.pingOutstanding = 0;
        this.connected = false;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.inboundQoS2 = null;
        this.pingSender = null;
        log.setResourceName(clientComms.getClient().getClientId());
        log.finer(CLASS_NAME, "<Init>", BuildConfig.VERSION_NAME);
        this.inUseMsgIds = new Hashtable();
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        this.outboundQoS2 = new Hashtable();
        this.outboundQoS1 = new Hashtable();
        this.inboundQoS2 = new Hashtable();
        this.pingCommand = new MqttPingReq();
        this.inFlightPubRels = 0;
        this.actualInFlight = 0;
        this.persistence = mqttClientPersistence;
        this.callback = commsCallback;
        this.tokenStore = commsTokenStore;
        this.clientComms = clientComms;
        this.pingSender = mqttPingSender;
        restoreState();
    }

    protected void setKeepAliveSecs(long j) {
        this.keepAlive = 1000 * j;
    }

    protected long getKeepAlive() {
        return this.keepAlive;
    }

    protected void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    private String getSendPersistenceKey(MqttWireMessage mqttWireMessage) {
        return new StringBuffer(PERSISTENCE_SENT_PREFIX).append(mqttWireMessage.getMessageId()).toString();
    }

    private String getSendConfirmPersistenceKey(MqttWireMessage mqttWireMessage) {
        return new StringBuffer(PERSISTENCE_CONFIRMED_PREFIX).append(mqttWireMessage.getMessageId()).toString();
    }

    private String getReceivedPersistenceKey(MqttWireMessage mqttWireMessage) {
        return new StringBuffer(PERSISTENCE_RECEIVED_PREFIX).append(mqttWireMessage.getMessageId()).toString();
    }

    protected void clearState() throws MqttException {
        log.fine(CLASS_NAME, "clearState", ">");
        this.persistence.clear();
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
    }

    private MqttWireMessage restoreMessage(String str, MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage createWireMessage;
        try {
            createWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (Throwable e) {
            log.fine(CLASS_NAME, "restoreMessage", "602", new Object[]{str}, e);
            if (!(e.getCause() instanceof EOFException)) {
                throw e;
            } else if (str != null) {
                this.persistence.remove(str);
                createWireMessage = null;
            } else {
                createWireMessage = null;
            }
        }
        log.fine(CLASS_NAME, "restoreMessage", "601", new Object[]{str, createWireMessage});
        return createWireMessage;
    }

    private void insertInOrder(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        for (int i = 0; i < vector.size(); i++) {
            if (((MqttWireMessage) vector.elementAt(i)).getMessageId() > messageId) {
                vector.insertElementAt(mqttWireMessage, i);
                return;
            }
        }
        vector.addElement(mqttWireMessage);
    }

    private Vector reOrder(Vector vector) {
        int i = 0;
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i2)).getMessageId();
            if (messageId - i5 > i4) {
                i4 = messageId - i5;
                i3 = i2;
            }
            i2++;
            i5 = messageId;
        }
        if (((MqttWireMessage) vector.elementAt(0)).getMessageId() + (65535 - i5) > i4) {
            i3 = 0;
        }
        for (int i6 = i3; i6 < vector.size(); i6++) {
            vector2.addElement(vector.elementAt(i6));
        }
        while (i < i3) {
            vector2.addElement(vector.elementAt(i));
            i++;
        }
        return vector2;
    }

    protected void restoreState() throws MqttException {
        Enumeration keys = this.persistence.keys();
        int i = this.nextMsgId;
        Vector vector = new Vector();
        log.fine(CLASS_NAME, "restoreState", "600");
        int i2 = i;
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MqttWireMessage restoreMessage = restoreMessage(str, this.persistence.get(str));
            if (restoreMessage != null) {
                if (str.startsWith(PERSISTENCE_RECEIVED_PREFIX)) {
                    log.fine(CLASS_NAME, "restoreState", "604", new Object[]{str, restoreMessage});
                    this.inboundQoS2.put(new Integer(restoreMessage.getMessageId()), restoreMessage);
                } else if (str.startsWith(PERSISTENCE_SENT_PREFIX)) {
                    MqttPublish mqttPublish = (MqttPublish) restoreMessage;
                    int max = Math.max(mqttPublish.getMessageId(), i2);
                    if (this.persistence.containsKey(getSendConfirmPersistenceKey(mqttPublish))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) restoreMessage(str, this.persistence.get(getSendConfirmPersistenceKey(mqttPublish)));
                        if (mqttPubRel != null) {
                            log.fine(CLASS_NAME, "restoreState", "605", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "606", new Object[]{str, restoreMessage});
                        }
                    } else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, restoreMessage});
                            this.outboundQoS1.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        }
                    }
                    this.tokenStore.restoreToken(mqttPublish).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessageId()));
                    i2 = max;
                } else if (str.startsWith(PERSISTENCE_CONFIRMED_PREFIX)) {
                    if (!this.persistence.containsKey(getSendPersistenceKey((MqttPubRel) restoreMessage))) {
                        vector.addElement(str);
                    }
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            log.fine(CLASS_NAME, "restoreState", "609", new Object[]{(String) elements.nextElement()});
            this.persistence.remove(str);
        }
        this.nextMsgId = i2;
    }

    private void restoreInflightMessages() {
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        Enumeration keys = this.outboundQoS2.keys();
        while (keys.hasMoreElements()) {
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.outboundQoS2.get(keys.nextElement());
            if (mqttWireMessage instanceof MqttPublish) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "610", new Object[]{r2});
                mqttWireMessage.setDuplicate(true);
                insertInOrder(this.pendingMessages, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "611", new Object[]{r2});
                insertInOrder(this.pendingFlows, (MqttPubRel) mqttWireMessage);
            }
        }
        keys = this.outboundQoS1.keys();
        while (keys.hasMoreElements()) {
            MqttPublish mqttPublish = (MqttPublish) this.outboundQoS1.get(keys.nextElement());
            mqttPublish.setDuplicate(true);
            log.fine(CLASS_NAME, "restoreInflightMessages", "612", new Object[]{r2});
            insertInOrder(this.pendingMessages, mqttPublish);
        }
        this.pendingFlows = reOrder(this.pendingFlows);
        this.pendingMessages = reOrder(this.pendingMessages);
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0) {
            mqttWireMessage.setMessageId(getNextMessageId());
        }
        if (mqttToken != null) {
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception e) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.queueLock) {
                if (this.actualInFlight >= this.maxInflight) {
                    log.fine(CLASS_NAME, "send", "613", new Object[]{new Integer(this.actualInFlight)});
                    throw new MqttException(32202);
                }
                log.fine(CLASS_NAME, "send", "628", new Object[]{new Integer(mqttWireMessage.getMessageId()), new Integer(((MqttPublish) mqttWireMessage).getMessage().getQos()), mqttWireMessage});
                switch (((MqttPublish) mqttWireMessage).getMessage().getQos()) {
                    case MIN_MSG_ID:
                        this.outboundQoS1.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                }
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingMessages.addElement(mqttWireMessage);
                this.queueLock.notifyAll();
            }
            return;
        }
        log.fine(CLASS_NAME, "send", "615", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.queueLock) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingFlows.insertElementAt(mqttWireMessage, 0);
                this.queueLock.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.pingCommand = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.persistence.put(getSendConfirmPersistenceKey(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.persistence.remove(getReceivedPersistenceKey(mqttWireMessage));
        }
        synchronized (this.queueLock) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
            }
            this.pendingFlows.addElement(mqttWireMessage);
            this.queueLock.notifyAll();
        }
    }

    protected void undo(MqttPublish mqttPublish) throws MqttPersistenceException {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "undo", "618", new Object[]{new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.outboundQoS1.remove(new Integer(mqttPublish.getMessageId()));
            } else {
                this.outboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
            }
            this.pendingMessages.removeElement(mqttPublish);
            this.persistence.remove(getSendPersistenceKey(mqttPublish));
            this.tokenStore.removeToken((MqttWireMessage) mqttPublish);
            checkQuiesceLock();
        }
    }

    public MqttToken checkForActivity() throws MqttException {
        log.fine(CLASS_NAME, "checkForActivity", "616", new Object[0]);
        synchronized (this.quiesceLock) {
            if (this.quiescing) {
                return null;
            }
            MqttToken mqttToken = null;
            getKeepAlive();
            if (!this.connected || this.keepAlive <= 0) {
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.pingOutstandingLock) {
                if (this.pingOutstanding > 0 && currentTimeMillis - this.lastInboundActivity >= this.keepAlive + 100) {
                    log.severe(CLASS_NAME, "checkForActivity", "619", new Object[]{new Long(this.keepAlive), new Long(this.lastOutboundActivity), new Long(this.lastInboundActivity), new Long(currentTimeMillis), new Long(this.lastPing)});
                    throw ExceptionHelper.createMqttException(32000);
                } else if (this.pingOutstanding != 0 || currentTimeMillis - this.lastOutboundActivity < 2 * this.keepAlive) {
                    if ((this.pingOutstanding != 0 || currentTimeMillis - this.lastInboundActivity < this.keepAlive - 100) && currentTimeMillis - this.lastOutboundActivity < this.keepAlive - 100) {
                        log.fine(CLASS_NAME, "checkForActivity", "634", null);
                        currentTimeMillis = Math.max(1, getKeepAlive() - (currentTimeMillis - this.lastOutboundActivity));
                    } else {
                        log.fine(CLASS_NAME, "checkForActivity", "620", new Object[]{new Long(this.keepAlive), new Long(this.lastOutboundActivity), new Long(this.lastInboundActivity)});
                        MqttToken mqttToken2 = new MqttToken(this.clientComms.getClient().getClientId());
                        this.tokenStore.saveToken(mqttToken2, this.pingCommand);
                        this.pendingFlows.insertElementAt(this.pingCommand, 0);
                        long keepAlive = getKeepAlive();
                        notifyQueueLock();
                        long j = keepAlive;
                        mqttToken = mqttToken2;
                        currentTimeMillis = j;
                    }
                } else {
                    log.severe(CLASS_NAME, "checkForActivity", "642", new Object[]{new Long(this.keepAlive), new Long(this.lastOutboundActivity), new Long(this.lastInboundActivity), new Long(currentTimeMillis), new Long(this.lastPing)});
                    throw ExceptionHelper.createMqttException(32002);
                }
            }
            log.fine(CLASS_NAME, "checkForActivity", "624", new Object[]{new Long(currentTimeMillis)});
            this.pingSender.schedule(currentTimeMillis);
            return mqttToken;
        }
    }

    protected MqttWireMessage get() throws MqttException {
        synchronized (this.queueLock) {
            MqttWireMessage mqttWireMessage;
            Object obj = null;
            while (mqttWireMessage == null) {
                if ((this.pendingMessages.isEmpty() && this.pendingFlows.isEmpty()) || (this.pendingFlows.isEmpty() && this.actualInFlight >= this.maxInflight)) {
                    try {
                        log.fine(CLASS_NAME, "get", "644");
                        this.queueLock.wait();
                        log.fine(CLASS_NAME, "get", "647");
                    } catch (InterruptedException e) {
                    }
                }
                if (!this.connected && (this.pendingFlows.isEmpty() || !(((MqttWireMessage) this.pendingFlows.elementAt(0)) instanceof MqttConnect))) {
                    log.fine(CLASS_NAME, "get", "621");
                    return null;
                } else if (!this.pendingFlows.isEmpty()) {
                    r0 = (MqttWireMessage) this.pendingFlows.remove(0);
                    if (r0 instanceof MqttPubRel) {
                        this.inFlightPubRels++;
                        log.fine(CLASS_NAME, "get", "617", new Object[]{new Integer(this.inFlightPubRels)});
                    }
                    checkQuiesceLock();
                    mqttWireMessage = r0;
                } else if (!this.pendingMessages.isEmpty()) {
                    if (this.actualInFlight < this.maxInflight) {
                        r0 = (MqttWireMessage) this.pendingMessages.elementAt(0);
                        this.pendingMessages.removeElementAt(0);
                        this.actualInFlight++;
                        log.fine(CLASS_NAME, "get", "623", new Object[]{new Integer(this.actualInFlight)});
                        mqttWireMessage = r0;
                    } else {
                        log.fine(CLASS_NAME, "get", "622");
                    }
                }
            }
            return mqttWireMessage;
        }
    }

    public void setKeepAliveInterval(long j) {
        this.keepAlive = j;
    }

    public void notifySentBytes(int i) {
        if (i > 0) {
            this.lastOutboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifySentBytes", "631", new Object[]{new Integer(i)});
    }

    protected void notifySent(MqttWireMessage mqttWireMessage) {
        this.lastOutboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = this.tokenStore.getToken(mqttWireMessage);
        token.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.pingOutstandingLock) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    synchronized (this.pingOutstandingLock) {
                        this.lastPing = currentTimeMillis;
                        this.pingOutstanding++;
                    }
                    log.fine(CLASS_NAME, "notifySent", "635", new Object[]{new Integer(this.pingOutstanding)});
                } catch (Throwable th) {
                }
            }
        } else if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.markComplete(null, null);
            this.callback.asyncOperationComplete(token);
            decrementInFlight();
            releaseMessageId(mqttWireMessage.getMessageId());
            this.tokenStore.removeToken(mqttWireMessage);
            checkQuiesceLock();
        }
    }

    private void decrementInFlight() {
        synchronized (this.queueLock) {
            this.actualInFlight--;
            log.fine(CLASS_NAME, "decrementInFlight", "646", new Object[]{new Integer(this.actualInFlight)});
            if (!checkQuiesceLock()) {
                this.queueLock.notifyAll();
            }
        }
    }

    protected boolean checkQuiesceLock() {
        int count = this.tokenStore.count();
        if (!this.quiescing || count != 0 || this.pendingFlows.size() != 0 || !this.callback.isQuiesced()) {
            return false;
        }
        log.fine(CLASS_NAME, "checkQuiesceLock", "626", new Object[]{new Boolean(this.quiescing), new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), Boolean.valueOf(this.callback.isQuiesced()), new Integer(count)});
        synchronized (this.quiesceLock) {
            this.quiesceLock.notifyAll();
        }
        return true;
    }

    public void notifyReceivedBytes(int i) {
        if (i > 0) {
            this.lastInboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifyReceivedBytes", "630", new Object[]{new Integer(i)});
    }

    protected void notifyReceivedAck(MqttAck mqttAck) throws MqttException {
        this.lastInboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifyReceivedAck", "627", new Object[]{new Integer(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.tokenStore.getToken((MqttWireMessage) mqttAck);
        if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if ((mqttAck instanceof MqttPubAck) || (mqttAck instanceof MqttPubComp)) {
            notifyResult(mqttAck, token, null);
        } else if (mqttAck instanceof MqttPingResp) {
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
                notifyResult(mqttAck, token, null);
                if (this.pingOutstanding == 0) {
                    this.tokenStore.removeToken((MqttWireMessage) mqttAck);
                }
            }
            log.fine(CLASS_NAME, "notifyReceivedAck", "636", new Object[]{new Integer(this.pingOutstanding)});
        } else if (mqttAck instanceof MqttConnack) {
            int returnCode = ((MqttConnack) mqttAck).getReturnCode();
            if (returnCode == 0) {
                synchronized (this.queueLock) {
                    if (this.cleanSession) {
                        clearState();
                        this.tokenStore.saveToken(token, (MqttWireMessage) mqttAck);
                    }
                    this.inFlightPubRels = 0;
                    this.actualInFlight = 0;
                    restoreInflightMessages();
                    connected();
                }
                this.clientComms.connectComplete((MqttConnack) mqttAck, null);
                notifyResult(mqttAck, token, null);
                this.tokenStore.removeToken((MqttWireMessage) mqttAck);
                synchronized (this.queueLock) {
                    this.queueLock.notifyAll();
                }
            } else {
                throw ExceptionHelper.createMqttException(returnCode);
            }
        } else {
            notifyResult(mqttAck, token, null);
            releaseMessageId(mqttAck.getMessageId());
            this.tokenStore.removeToken((MqttWireMessage) mqttAck);
        }
        checkQuiesceLock();
    }

    protected void notifyReceivedMsg(MqttWireMessage mqttWireMessage) throws MqttException {
        this.lastInboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifyReceivedMsg", "651", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (!this.quiescing) {
            MqttPublish mqttPublish;
            if (mqttWireMessage instanceof MqttPublish) {
                mqttPublish = (MqttPublish) mqttWireMessage;
                switch (mqttPublish.getMessage().getQos()) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    case MIN_MSG_ID:
                        if (this.callback != null) {
                            this.callback.messageArrived(mqttPublish);
                        }
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        this.persistence.put(getReceivedPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        this.inboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        send(new MqttPubRec(mqttPublish), null);
                    default:
                        break;
                }
            } else if (mqttWireMessage instanceof MqttPubRel) {
                mqttPublish = (MqttPublish) this.inboundQoS2.get(new Integer(mqttWireMessage.getMessageId()));
                if (mqttPublish == null) {
                    send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
                } else if (this.callback != null) {
                    this.callback.messageArrived(mqttPublish);
                }
            }
        }
    }

    protected void notifyComplete(MqttToken mqttToken) throws MqttException {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage != null && (wireMessage instanceof MqttAck)) {
            log.fine(CLASS_NAME, "notifyComplete", "629", new Object[]{new Integer(wireMessage.getMessageId()), mqttToken, wireMessage});
            MqttAck mqttAck = (MqttAck) wireMessage;
            if (mqttAck instanceof MqttPubAck) {
                this.persistence.remove(getSendPersistenceKey(wireMessage));
                this.outboundQoS1.remove(new Integer(mqttAck.getMessageId()));
                decrementInFlight();
                releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                log.fine(CLASS_NAME, "notifyComplete", "650", new Object[]{new Integer(mqttAck.getMessageId())});
            } else if (mqttAck instanceof MqttPubComp) {
                this.persistence.remove(getSendPersistenceKey(wireMessage));
                this.persistence.remove(getSendConfirmPersistenceKey(wireMessage));
                this.outboundQoS2.remove(new Integer(mqttAck.getMessageId()));
                this.inFlightPubRels--;
                decrementInFlight();
                releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                log.fine(CLASS_NAME, "notifyComplete", "645", new Object[]{new Integer(mqttAck.getMessageId()), new Integer(this.inFlightPubRels)});
            }
            checkQuiesceLock();
        }
    }

    protected void notifyResult(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.markComplete(mqttWireMessage, mqttException);
        if (!(mqttWireMessage == null || !(mqttWireMessage instanceof MqttAck) || (mqttWireMessage instanceof MqttPubRec))) {
            log.fine(CLASS_NAME, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            log.fine(CLASS_NAME, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
    }

    public void connected() {
        log.fine(CLASS_NAME, "connected", "631");
        this.connected = true;
        this.pingSender.start();
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        log.fine(CLASS_NAME, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.tokenStore.getOutstandingTokens();
        Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) elements.nextElement();
            synchronized (mqttToken) {
                if (!(mqttToken.isComplete() || mqttToken.internalTok.isCompletePending() || mqttToken.getException() != null)) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.tokenStore.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    public void disconnected(MqttException mqttException) {
        log.fine(CLASS_NAME, "disconnected", "633", new Object[]{mqttException});
        this.connected = false;
        try {
            if (this.cleanSession) {
                clearState();
            }
            this.pendingMessages.clear();
            this.pendingFlows.clear();
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = 0;
            }
        } catch (MqttException e) {
        }
    }

    private synchronized void releaseMessageId(int i) {
        this.inUseMsgIds.remove(new Integer(i));
    }

    private synchronized int getNextMessageId() throws MqttException {
        int i = this.nextMsgId;
        int i2 = 0;
        do {
            this.nextMsgId++;
            if (this.nextMsgId > 65535) {
                this.nextMsgId = 1;
            }
            if (this.nextMsgId == i) {
                i2++;
                if (i2 == 2) {
                    throw ExceptionHelper.createMqttException(32001);
                }
            }
        } while (this.inUseMsgIds.containsKey(new Integer(this.nextMsgId)));
        Integer num = new Integer(this.nextMsgId);
        this.inUseMsgIds.put(num, num);
        return this.nextMsgId;
    }

    public void quiesce(long j) {
        if (j > 0) {
            log.fine(CLASS_NAME, "quiesce", "637", new Object[]{new Long(j)});
            synchronized (this.queueLock) {
                this.quiescing = true;
            }
            this.callback.quiesce();
            notifyQueueLock();
            synchronized (this.quiesceLock) {
                try {
                    if (this.tokenStore.count() > 0 || this.pendingFlows.size() > 0 || !this.callback.isQuiesced()) {
                        log.fine(CLASS_NAME, "quiesce", "639", new Object[]{new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), new Integer(r0)});
                        this.quiesceLock.wait(j);
                    }
                } catch (InterruptedException e) {
                }
            }
            synchronized (this.queueLock) {
                this.pendingMessages.clear();
                this.pendingFlows.clear();
                this.quiescing = false;
                this.actualInFlight = 0;
            }
            log.fine(CLASS_NAME, "quiesce", "640");
        }
    }

    public void notifyQueueLock() {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "notifyQueueLock", "638");
            this.queueLock.notifyAll();
        }
    }

    protected void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{new Integer(mqttPublish.getMessageId())});
        this.persistence.remove(getReceivedPersistenceKey(mqttPublish));
        this.inboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
    }

    protected void close() {
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
        this.inUseMsgIds = null;
        this.pendingMessages = null;
        this.pendingFlows = null;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.inboundQoS2 = null;
        this.tokenStore = null;
        this.callback = null;
        this.clientComms = null;
        this.persistence = null;
        this.pingCommand = null;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.inUseMsgIds);
        properties.put("pendingMessages", this.pendingMessages);
        properties.put("pendingFlows", this.pendingFlows);
        properties.put("maxInflight", new Integer(this.maxInflight));
        properties.put("nextMsgID", new Integer(this.nextMsgId));
        properties.put("actualInFlight", new Integer(this.actualInFlight));
        properties.put("inFlightPubRels", new Integer(this.inFlightPubRels));
        properties.put("quiescing", Boolean.valueOf(this.quiescing));
        properties.put("pingoutstanding", new Integer(this.pingOutstanding));
        properties.put("lastOutboundActivity", new Long(this.lastOutboundActivity));
        properties.put("lastInboundActivity", new Long(this.lastInboundActivity));
        properties.put("outboundQoS2", this.outboundQoS2);
        properties.put("outboundQoS1", this.outboundQoS1);
        properties.put("inboundQoS2", this.inboundQoS2);
        properties.put("tokens", this.tokenStore);
        return properties;
    }
}
