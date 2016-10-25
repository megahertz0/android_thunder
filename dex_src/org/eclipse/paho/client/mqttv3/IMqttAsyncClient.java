package org.eclipse.paho.client.mqttv3;

public interface IMqttAsyncClient {
    void close() throws MqttException;

    IMqttToken connect() throws MqttException, MqttSecurityException;

    IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException;

    IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException;

    IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException;

    IMqttToken disconnect() throws MqttException;

    IMqttToken disconnect(long j) throws MqttException;

    IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException;

    IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException;

    void disconnectForcibly() throws MqttException;

    void disconnectForcibly(long j) throws MqttException;

    void disconnectForcibly(long j, long j2) throws MqttException;

    String getClientId();

    IMqttDeliveryToken[] getPendingDeliveryTokens();

    String getServerURI();

    boolean isConnected();

    IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException;

    IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException;

    IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException;

    IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException;

    void setCallback(MqttCallback mqttCallback);

    IMqttToken subscribe(String str, int i) throws MqttException;

    IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException;

    IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException;

    IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException;

    IMqttToken unsubscribe(String str) throws MqttException;

    IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException;

    IMqttToken unsubscribe(String[] strArr) throws MqttException;

    IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException;
}
