package org.eclipse.paho.client.mqttv3;

public interface IMqttClient {
    void close() throws MqttException;

    void connect() throws MqttSecurityException, MqttException;

    void connect(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException;

    IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException;

    void disconnect() throws MqttException;

    void disconnect(long j) throws MqttException;

    void disconnectForcibly() throws MqttException;

    void disconnectForcibly(long j) throws MqttException;

    void disconnectForcibly(long j, long j2) throws MqttException;

    String getClientId();

    IMqttDeliveryToken[] getPendingDeliveryTokens();

    String getServerURI();

    MqttTopic getTopic(String str);

    boolean isConnected();

    void publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException;

    void publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException;

    void setCallback(MqttCallback mqttCallback);

    void subscribe(String str) throws MqttException, MqttSecurityException;

    void subscribe(String str, int i) throws MqttException;

    void subscribe(String[] strArr) throws MqttException;

    void subscribe(String[] strArr, int[] iArr) throws MqttException;

    void unsubscribe(String str) throws MqttException;

    void unsubscribe(String[] strArr) throws MqttException;
}
