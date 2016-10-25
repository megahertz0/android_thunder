package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MemoryPersistence implements MqttClientPersistence {
    private Hashtable data;

    public void close() throws MqttPersistenceException {
        this.data.clear();
    }

    public Enumeration keys() throws MqttPersistenceException {
        return this.data.keys();
    }

    public MqttPersistable get(String str) throws MqttPersistenceException {
        return (MqttPersistable) this.data.get(str);
    }

    public void open(String str, String str2) throws MqttPersistenceException {
        this.data = new Hashtable();
    }

    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        this.data.put(str, mqttPersistable);
    }

    public void remove(String str) throws MqttPersistenceException {
        this.data.remove(str);
    }

    public void clear() throws MqttPersistenceException {
        this.data.clear();
    }

    public boolean containsKey(String str) throws MqttPersistenceException {
        return this.data.containsKey(str);
    }
}
