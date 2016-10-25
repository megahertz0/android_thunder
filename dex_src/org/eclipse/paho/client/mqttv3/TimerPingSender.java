package org.eclipse.paho.client.mqttv3;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class TimerPingSender implements MqttPingSender {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientComms comms;
    private Timer timer;

    private class PingTask extends TimerTask {
        private static final String methodName = "PingTask.run";
        final TimerPingSender this$0;

        private PingTask(TimerPingSender timerPingSender) {
            this.this$0 = timerPingSender;
        }

        PingTask(TimerPingSender timerPingSender, PingTask pingTask) {
            this(timerPingSender);
        }

        public void run() {
            TimerPingSender.access$0().fine(TimerPingSender.access$1(), methodName, "660", new Object[]{new Long(System.currentTimeMillis())});
            TimerPingSender.access$2(this.this$0).checkForActivity();
        }
    }

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.TimerPingSender");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    }

    static String access$1() {
        return CLASS_NAME;
    }

    static Logger access$0() {
        return log;
    }

    static ClientComms access$2(TimerPingSender timerPingSender) {
        return timerPingSender.comms;
    }

    public void init(ClientComms clientComms) {
        if (clientComms == null) {
            throw new IllegalArgumentException("ClientComms cannot be null.");
        }
        this.comms = clientComms;
    }

    public void start() {
        log.fine(CLASS_NAME, "start", "659", new Object[]{this.comms.getClient().getClientId()});
        this.timer = new Timer(new StringBuffer("MQTT Ping: ").append(r0).toString());
        this.timer.schedule(new PingTask(this, null), this.comms.getKeepAlive());
    }

    public void stop() {
        log.fine(CLASS_NAME, "stop", "661", null);
        if (this.timer != null) {
            this.timer.cancel();
        }
    }

    public void schedule(long j) {
        this.timer.schedule(new PingTask(this, null), j);
    }
}
