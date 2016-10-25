package anet.channel.appmonitor;

import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.StatObject;

// compiled from: Taobao
public class AppMonitor {
    private static volatile IAppMonitor appMonitor;

    // compiled from: Taobao
    final class AnonymousClass_1 implements IAppMonitor {
        final /* synthetic */ IAppMonitor val$appMonitor;

        AnonymousClass_1(IAppMonitor iAppMonitor) {
            this.val$appMonitor = iAppMonitor;
        }

        public final void register() {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.register();
            }
        }

        public final void register(Class<?> cls) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.register(cls);
            }
        }

        public final void commitStat(StatObject statObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitStat(statObject);
            }
        }

        public final void commitAlarm(AlarmObject alarmObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitAlarm(alarmObject);
            }
        }

        public final void commitCount(CountObject countObject) {
            if (this.val$appMonitor != null) {
                this.val$appMonitor.commitCount(countObject);
            }
        }
    }

    static {
        appMonitor = new DefaultAppMonitor();
    }

    public static IAppMonitor getInstance() {
        return appMonitor;
    }

    public static void setInstance(IAppMonitor iAppMonitor) {
        appMonitor = new AnonymousClass_1(iAppMonitor);
    }
}
