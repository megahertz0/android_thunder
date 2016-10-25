package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttToken;

public class ConnectActionListener implements IMqttActionListener {
    private MqttAsyncClient client;
    private ClientComms comms;
    private MqttConnectOptions options;
    private int originalMqttVersion;
    private MqttClientPersistence persistence;
    private IMqttActionListener userCallback;
    private Object userContext;
    private MqttToken userToken;

    public ConnectActionListener(MqttAsyncClient mqttAsyncClient, MqttClientPersistence mqttClientPersistence, ClientComms clientComms, MqttConnectOptions mqttConnectOptions, MqttToken mqttToken, Object obj, IMqttActionListener iMqttActionListener) {
        this.persistence = mqttClientPersistence;
        this.client = mqttAsyncClient;
        this.comms = clientComms;
        this.options = mqttConnectOptions;
        this.userToken = mqttToken;
        this.userContext = obj;
        this.userCallback = iMqttActionListener;
        this.originalMqttVersion = mqttConnectOptions.getMqttVersion();
    }

    public void onSuccess(IMqttToken iMqttToken) {
        if (this.originalMqttVersion == 0) {
            this.options.setMqttVersion(0);
        }
        this.userToken.internalTok.markComplete(iMqttToken.getResponse(), null);
        this.userToken.internalTok.notifyComplete();
        if (this.userCallback != null) {
            this.userToken.setUserContext(this.userContext);
            this.userCallback.onSuccess(this.userToken);
        }
    }

    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        int length = this.comms.getNetworkModules().length;
        int networkModuleIndex = this.comms.getNetworkModuleIndex();
        if (networkModuleIndex + 1 < length || (this.originalMqttVersion == 0 && this.options.getMqttVersion() == 4)) {
            if (this.originalMqttVersion != 0) {
                this.comms.setNetworkModuleIndex(networkModuleIndex + 1);
            } else if (this.options.getMqttVersion() == 4) {
                this.options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            } else {
                this.options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
                this.comms.setNetworkModuleIndex(networkModuleIndex + 1);
            }
            try {
                connect();
                return;
            } catch (Throwable e) {
                onFailure(iMqttToken, e);
            }
        }
        MqttException mqttException;
        if (this.originalMqttVersion == 0) {
            this.options.setMqttVersion(0);
        }
        if (th instanceof MqttException) {
            mqttException = (MqttException) th;
        } else {
            mqttException = new MqttException(th);
        }
        this.userToken.internalTok.markComplete(null, mqttException);
        this.userToken.internalTok.notifyComplete();
        if (this.userCallback != null) {
            this.userToken.setUserContext(this.userContext);
            this.userCallback.onFailure(this.userToken, th);
        }
    }

    public void connect() throws MqttPersistenceException {
        IMqttToken mqttToken = new MqttToken(this.client.getClientId());
        mqttToken.setActionCallback(this);
        mqttToken.setUserContext(this);
        this.persistence.open(this.client.getClientId(), this.client.getServerURI());
        if (this.options.isCleanSession()) {
            this.persistence.clear();
        }
        if (this.options.getMqttVersion() == 0) {
            this.options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
        try {
            this.comms.connect(this.options, mqttToken);
        } catch (Throwable e) {
            onFailure(mqttToken, e);
        }
    }
}
