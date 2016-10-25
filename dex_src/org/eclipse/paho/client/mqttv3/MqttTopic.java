package org.eclipse.paho.client.mqttv3;

import com.xunlei.common.encrypt.CharsetConvert;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.util.Strings;

public class MqttTopic {
    private static final int MAX_TOPIC_LEN = 65535;
    private static final int MIN_TOPIC_LEN = 1;
    public static final String MULTI_LEVEL_WILDCARD = "#";
    public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
    private static final char NUL = '\u0000';
    public static final String SINGLE_LEVEL_WILDCARD = "+";
    public static final String TOPIC_LEVEL_SEPARATOR = "/";
    public static final String TOPIC_WILDCARDS = "#+";
    private ClientComms comms;
    private String name;

    public MqttTopic(String str, ClientComms clientComms) {
        this.comms = clientComms;
        this.name = str;
    }

    public MqttDeliveryToken publish(byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(mqttMessage);
    }

    public MqttDeliveryToken publish(MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        MqttToken mqttDeliveryToken = new MqttDeliveryToken(this.comms.getClient().getClientId());
        mqttDeliveryToken.setMessage(mqttMessage);
        this.comms.sendNoWait(createPublish(mqttMessage), mqttDeliveryToken);
        mqttDeliveryToken.internalTok.waitUntilSent();
        return mqttDeliveryToken;
    }

    public String getName() {
        return this.name;
    }

    private MqttPublish createPublish(MqttMessage mqttMessage) {
        return new MqttPublish(getName(), mqttMessage);
    }

    public String toString() {
        return getName();
    }

    public static void validate(String str, boolean z) {
        try {
            int length = str.getBytes(CharsetConvert.UTF_8).length;
            if (length <= 0 || length > 65535) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", new Object[]{new Integer(1), new Integer(65535)}));
            } else if (z) {
                if (!Strings.equalsAny(str, new CharSequence[]{MULTI_LEVEL_WILDCARD, SINGLE_LEVEL_WILDCARD})) {
                    if (Strings.countMatches(str, MULTI_LEVEL_WILDCARD) > 1 || (str.contains(MULTI_LEVEL_WILDCARD) && !str.endsWith(MULTI_LEVEL_WILDCARD_PATTERN))) {
                        throw new IllegalArgumentException(new StringBuffer("Invalid usage of multi-level wildcard in topic string: ").append(str).toString());
                    }
                    validateSingleLevelWildcard(str);
                }
            } else if (Strings.containsAny((CharSequence) str, TOPIC_WILDCARDS)) {
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private static void validateSingleLevelWildcard(String str) {
        char charAt = SINGLE_LEVEL_WILDCARD.charAt(0);
        char charAt2 = TOPIC_LEVEL_SEPARATOR.charAt(0);
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        for (int i = 0; i < length; i++) {
            char c;
            if (i - 1 >= 0) {
                char c2 = toCharArray[i - 1];
            } else {
                int i2 = 0;
            }
            if (i + 1 < length) {
                c = toCharArray[i + 1];
            } else {
                c = '\u0000';
            }
            if (toCharArray[i] == charAt) {
                if (c2 == charAt2 || c2 == '\u0000') {
                    if (c == charAt2 || c == '\u0000') {
                    }
                }
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", new Object[]{str}));
            }
        }
    }
}
