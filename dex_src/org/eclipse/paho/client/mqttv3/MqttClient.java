package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttClient implements IMqttClient {
    protected MqttAsyncClient aClient;
    protected long timeToWait;

    public MqttClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence);
    }

    public void connect() throws MqttSecurityException, MqttException {
        connect(new MqttConnectOptions());
    }

    public void connect(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        this.aClient.connect(mqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
    }

    public IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        IMqttToken connect = this.aClient.connect(mqttConnectOptions, null, null);
        connect.waitForCompletion(getTimeToWait());
        return connect;
    }

    public void disconnect() throws MqttException {
        this.aClient.disconnect().waitForCompletion();
    }

    public void disconnect(long j) throws MqttException {
        this.aClient.disconnect(j, null, null).waitForCompletion();
    }

    public void disconnectForcibly() throws MqttException {
        this.aClient.disconnectForcibly();
    }

    public void disconnectForcibly(long j) throws MqttException {
        this.aClient.disconnectForcibly(j);
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.aClient.disconnectForcibly(j, j2);
    }

    public void subscribe(String str) throws MqttException {
        subscribe(new String[]{str}, new int[]{1});
    }

    public void subscribe(String[] strArr) throws MqttException {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr);
    }

    public void subscribe(String str, int i) throws MqttException {
        subscribe(new String[]{str}, new int[]{i});
    }

    public void subscribe(String[] strArr, int[] iArr) throws MqttException {
        IMqttToken subscribe = this.aClient.subscribe(strArr, iArr, null, null);
        subscribe.waitForCompletion(getTimeToWait());
        int[] grantedQos = subscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; i++) {
            iArr[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && iArr[0] == 128) {
            throw new MqttException(128);
        }
    }

    public void unsubscribe(String str) throws MqttException {
        unsubscribe(new String[]{str});
    }

    public void unsubscribe(String[] strArr) throws MqttException {
        this.aClient.unsubscribe(strArr, null, null).waitForCompletion(getTimeToWait());
    }

    public void publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        publish(str, mqttMessage);
    }

    public void publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        this.aClient.publish(str, mqttMessage, null, null).waitForCompletion(getTimeToWait());
    }

    public void setTimeToWait(long j) throws IllegalArgumentException {
        if (j < -1) {
            throw new IllegalArgumentException();
        }
        this.timeToWait = j;
    }

    public long getTimeToWait() {
        return this.timeToWait;
    }

    public void close() throws MqttException {
        this.aClient.close();
    }

    public String getClientId() {
        return this.aClient.getClientId();
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.aClient.getPendingDeliveryTokens();
    }

    public String getServerURI() {
        return this.aClient.getServerURI();
    }

    public MqttTopic getTopic(String str) {
        return this.aClient.getTopic(str);
    }

    public boolean isConnected() {
        return this.aClient.isConnected();
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.aClient.setCallback(mqttCallback);
    }

    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }

    public Debug getDebug() {
        return this.aClient.getDebug();
    }
}
