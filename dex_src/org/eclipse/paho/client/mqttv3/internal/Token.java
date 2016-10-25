package org.eclipse.paho.client.mqttv3.internal;

import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Token {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private IMqttActionListener callback;
    private IMqttAsyncClient client;
    private volatile boolean completed;
    private MqttException exception;
    private String key;
    protected MqttMessage message;
    private int messageID;
    private boolean notified;
    private boolean pendingComplete;
    private MqttWireMessage response;
    private Object responseLock;
    private boolean sent;
    private Object sentLock;
    private String[] topics;
    private Object userContext;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.Token");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    public Token(String str) {
        this.completed = false;
        this.pendingComplete = false;
        this.sent = false;
        this.responseLock = new Object();
        this.sentLock = new Object();
        this.message = null;
        this.response = null;
        this.exception = null;
        this.topics = null;
        this.client = null;
        this.callback = null;
        this.userContext = null;
        this.messageID = 0;
        this.notified = false;
        log.setResourceName(str);
    }

    public int getMessageID() {
        return this.messageID;
    }

    public void setMessageID(int i) {
        this.messageID = i;
    }

    public boolean checkResult() throws MqttException {
        if (getException() == null) {
            return true;
        }
        throw getException();
    }

    public MqttException getException() {
        return this.exception;
    }

    public boolean isComplete() {
        return this.completed;
    }

    protected boolean isCompletePending() {
        return this.pendingComplete;
    }

    protected boolean isInUse() {
        return (getClient() == null || isComplete()) ? false : true;
    }

    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.callback = iMqttActionListener;
    }

    public IMqttActionListener getActionCallback() {
        return this.callback;
    }

    public void waitForCompletion() throws MqttException {
        waitForCompletion(-1);
    }

    public void waitForCompletion(long j) throws MqttException {
        log.fine(CLASS_NAME, "waitForCompletion", "407", new Object[]{getKey(), new Long(j), this});
        if (waitForResponse(j) != null || this.completed) {
            checkResult();
            return;
        }
        log.fine(CLASS_NAME, "waitForCompletion", "406", new Object[]{getKey(), this});
        this.exception = new MqttException(32000);
        throw this.exception;
    }

    protected MqttWireMessage waitForResponse() throws MqttException {
        return waitForResponse(-1);
    }

    protected MqttWireMessage waitForResponse(long j) throws MqttException {
        synchronized (this.responseLock) {
            Logger logger = log;
            String str = CLASS_NAME;
            String str2 = "waitForResponse";
            String str3 = "400";
            Object[] objArr = new Object[7];
            objArr[0] = getKey();
            objArr[1] = new Long(j);
            objArr[2] = new Boolean(this.sent);
            objArr[3] = new Boolean(this.completed);
            objArr[4] = this.exception == null ? "false" : "true";
            objArr[5] = this.response;
            objArr[6] = this;
            logger.fine(str, str2, str3, objArr, this.exception);
            while (!this.completed) {
                if (this.exception == null) {
                    try {
                        log.fine(CLASS_NAME, "waitForResponse", "408", new Object[]{getKey(), new Long(j)});
                        if (j <= 0) {
                            this.responseLock.wait();
                        } else {
                            this.responseLock.wait(j);
                        }
                    } catch (Throwable e) {
                        this.exception = new MqttException(e);
                    }
                }
                if (!this.completed) {
                    if (this.exception == null) {
                        if (j > 0) {
                            break;
                        }
                    } else {
                        log.fine(CLASS_NAME, "waitForResponse", "401", null, this.exception);
                        throw this.exception;
                    }
                }
            }
        }
        log.fine(CLASS_NAME, "waitForResponse", "402", new Object[]{getKey(), this.response});
        return this.response;
    }

    protected void markComplete(MqttWireMessage mqttWireMessage, MqttException mqttException) {
        log.fine(CLASS_NAME, "markComplete", "404", new Object[]{getKey(), mqttWireMessage, mqttException});
        synchronized (this.responseLock) {
            if (mqttWireMessage instanceof MqttAck) {
                this.message = null;
            }
            this.pendingComplete = true;
            this.response = mqttWireMessage;
            this.exception = mqttException;
        }
    }

    protected void notifyComplete() {
        log.fine(CLASS_NAME, "notifyComplete", "404", new Object[]{getKey(), this.response, this.exception});
        synchronized (this.responseLock) {
            if (this.exception == null && this.pendingComplete) {
                this.completed = true;
            }
            this.pendingComplete = false;
            this.responseLock.notifyAll();
        }
        synchronized (this.sentLock) {
            this.sent = true;
            this.sentLock.notifyAll();
        }
    }

    public void waitUntilSent() throws MqttException {
        synchronized (this.sentLock) {
            try {
                synchronized (this.responseLock) {
                    if (this.exception != null) {
                        throw this.exception;
                    }
                }
                while (!this.sent) {
                    try {
                        log.fine(CLASS_NAME, "waitUntilSent", "409", new Object[]{getKey()});
                        this.sentLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                if (this.sent) {
                } else if (this.exception == null) {
                    throw ExceptionHelper.createMqttException((int) SimpleLog.LOG_LEVEL_FATAL);
                } else {
                    throw this.exception;
                }
            } catch (Throwable th) {
            }
        }
    }

    protected void notifySent() {
        log.fine(CLASS_NAME, "notifySent", "403", new Object[]{getKey()});
        synchronized (this.responseLock) {
            this.response = null;
            this.completed = false;
        }
        synchronized (this.sentLock) {
            this.sent = true;
            this.sentLock.notifyAll();
        }
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    protected void setClient(IMqttAsyncClient iMqttAsyncClient) {
        this.client = iMqttAsyncClient;
    }

    public void reset() throws MqttException {
        if (isInUse()) {
            throw new MqttException(32201);
        }
        log.fine(CLASS_NAME, "reset", "410", new Object[]{getKey()});
        this.client = null;
        this.completed = false;
        this.response = null;
        this.sent = false;
        this.exception = null;
        this.userContext = null;
    }

    public MqttMessage getMessage() {
        return this.message;
    }

    public MqttWireMessage getWireMessage() {
        return this.response;
    }

    public void setMessage(MqttMessage mqttMessage) {
        this.message = mqttMessage;
    }

    public String[] getTopics() {
        return this.topics;
    }

    public void setTopics(String[] strArr) {
        this.topics = strArr;
    }

    public Object getUserContext() {
        return this.userContext;
    }

    public void setUserContext(Object obj) {
        this.userContext = obj;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setException(MqttException mqttException) {
        synchronized (this.responseLock) {
            this.exception = mqttException;
        }
    }

    public boolean isNotified() {
        return this.notified;
    }

    public void setNotified(boolean z) {
        this.notified = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(getKey());
        stringBuffer.append(" ,topics=");
        if (getTopics() != null) {
            for (int i = 0; i < getTopics().length; i++) {
                stringBuffer.append(getTopics()[i]).append(", ");
            }
        }
        stringBuffer.append(" ,usercontext=").append(getUserContext());
        stringBuffer.append(" ,isComplete=").append(isComplete());
        stringBuffer.append(" ,isNotified=").append(isNotified());
        stringBuffer.append(" ,exception=").append(getException());
        stringBuffer.append(" ,actioncallback=").append(getActionCallback());
        return stringBuffer.toString();
    }

    public int[] getGrantedQos() {
        return this.response instanceof MqttSuback ? ((MqttSuback) this.response).getGrantedQos() : new int[0];
    }

    public boolean getSessionPresent() {
        return this.response instanceof MqttConnack ? ((MqttConnack) this.response).getSessionPresent() : false;
    }

    public MqttWireMessage getResponse() {
        return this.response;
    }
}
