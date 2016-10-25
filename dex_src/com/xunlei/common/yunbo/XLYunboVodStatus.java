package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class XLYunboVodStatus {
    public static final int ALL = 255;
    public static final int SHOUCANG = 8;
    public static final int YIBO = 4;
    private static List<KEYVALUE> statusList;
    private int m_stauts;

    private static class KEYVALUE {
        public int key;
        public String value;

        public KEYVALUE(int i, String str) {
            this.key = 0;
            this.value = BuildConfig.VERSION_NAME;
            this.key = i;
            this.value = str;
        }
    }

    static {
        List linkedList = new LinkedList();
        statusList = linkedList;
        linkedList.add(new KEYVALUE(4, "yibo"));
        statusList.add(new KEYVALUE(8, "shoucang"));
    }

    public XLYunboVodStatus(int i) {
        this.m_stauts = 0;
        this.m_stauts = i;
    }

    public String getString() {
        if (hasStatus(ALL)) {
            return "all";
        }
        String str = BuildConfig.VERSION_NAME;
        int i = 0;
        while (i < statusList.size()) {
            String format;
            if (hasStatus(((KEYVALUE) statusList.get(i)).key)) {
                format = String.format("%s/%s", new Object[]{str, ((KEYVALUE) statusList.get(i)).value});
            } else {
                format = str;
            }
            i++;
            str = format;
        }
        return str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? str.substring(1, str.length()) : str;
    }

    public int getValue() {
        return this.m_stauts;
    }

    public boolean hasStatus(int i) {
        return (this.m_stauts & i) == i;
    }
}
