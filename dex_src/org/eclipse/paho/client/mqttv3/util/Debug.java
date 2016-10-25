package org.eclipse.paho.client.mqttv3.util;

import com.xunlei.xllib.R;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Debug {
    private static final String CLASS_NAME;
    static Class class$0 = null;
    private static final String lineSep;
    private static final Logger log;
    private static final String separator = "==============";
    private String clientID;
    private ClientComms comms;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
        lineSep = System.getProperty("line.separator", "\n");
    }

    public Debug(String str, ClientComms clientComms) {
        this.clientID = str;
        this.comms = clientComms;
        log.setResourceName(str);
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpBaseDebug() {
        dumpVersion();
        dumpSystemProperties();
        dumpMemoryTrace();
    }

    protected void dumpMemoryTrace() {
        log.dumpTrace();
    }

    protected void dumpVersion() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new StringBuffer(String.valueOf(lineSep)).append("============== Version Info ==============").append(lineSep).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left("Version", R.styleable.Toolbar_navigationIcon, ' '))).append(":  ").append(ClientComms.VERSION).append(lineSep).toString());
        stringBuffer.append(new StringBuffer(String.valueOf(left("Build Level", R.styleable.Toolbar_navigationIcon, ' '))).append(":  ").append(ClientComms.BUILD_LEVEL).append(lineSep).toString());
        stringBuffer.append(new StringBuffer("==========================================").append(lineSep).toString());
        log.fine(CLASS_NAME, "dumpVersion", stringBuffer.toString());
    }

    public void dumpSystemProperties() {
        log.fine(CLASS_NAME, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    public void dumpClientState() {
        if (this.comms != null && this.comms.getClientState() != null) {
            log.fine(CLASS_NAME, "dumpClientState", dumpProperties(this.comms.getClientState().getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : ClientState").toString()).toString());
        }
    }

    public void dumpClientComms() {
        if (this.comms != null) {
            log.fine(CLASS_NAME, "dumpClientComms", dumpProperties(this.comms.getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : ClientComms").toString()).toString());
        }
    }

    public void dumpConOptions() {
        if (this.comms != null) {
            log.fine(CLASS_NAME, "dumpConOptions", dumpProperties(this.comms.getConOptions().getDebug(), new StringBuffer(String.valueOf(this.clientID)).append(" : Connect Options").toString()).toString());
        }
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration propertyNames = properties.propertyNames();
        stringBuffer.append(new StringBuffer(String.valueOf(lineSep)).append("============== ").append(str).append(" ==============").append(lineSep).toString());
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            stringBuffer.append(new StringBuffer(String.valueOf(left(str2, R.styleable.AppCompatTheme_actionModeCloseButtonStyle, ' '))).append(":  ").append(properties.get(str2)).append(lineSep).toString());
        }
        stringBuffer.append(new StringBuffer("==========================================").append(lineSep).toString());
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length < 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(c);
        }
    }
}
