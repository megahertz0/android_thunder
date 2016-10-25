package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.android.spdy.SpdyAgent;

public class MessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue<a> a;

    public static class a {
        private PushMessageReceiver a;
        private Intent b;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.a = pushMessageReceiver;
            this.b = intent;
        }

        public PushMessageReceiver a() {
            return this.a;
        }

        public Intent b() {
            return this.b;
        }
    }

    static {
        a = new ConcurrentLinkedQueue();
    }

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    public static void addJob(a aVar) {
        if (aVar != null) {
            a.add(aVar);
        }
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            a aVar = (a) a.poll();
            if (aVar != null) {
                try {
                    PushMessageReceiver a = aVar.a();
                    Intent b = aVar.b();
                    MiPushCommandMessage miPushCommandMessage;
                    switch (b.getIntExtra(PushMessageHelper.MESSAGE_TYPE, 1)) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            a a2 = i.a((Context) this).a(b);
                            if (a2 == null) {
                                return;
                            }
                            if (a2 instanceof MiPushMessage) {
                                MiPushMessage miPushMessage = (MiPushMessage) a2;
                                if (!miPushMessage.isArrivedMessage()) {
                                    a.onReceiveMessage(this, miPushMessage);
                                }
                                if (miPushMessage.getPassThrough() == 1) {
                                    a.onReceivePassThroughMessage(this, miPushMessage);
                                } else if (miPushMessage.isNotified()) {
                                    a.onNotificationMessageClicked(this, miPushMessage);
                                } else {
                                    a.onNotificationMessageArrived(this, miPushMessage);
                                }
                            } else if (a2 instanceof MiPushCommandMessage) {
                                miPushCommandMessage = (MiPushCommandMessage) a2;
                                a.onCommandResult(this, miPushCommandMessage);
                                if (TextUtils.equals(miPushCommandMessage.getCommand(), MiPushClient.COMMAND_REGISTER)) {
                                    a.onReceiveRegisterResult(this, miPushCommandMessage);
                                }
                            }
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            miPushCommandMessage = (MiPushCommandMessage) b.getSerializableExtra(PushMessageHelper.KEY_COMMAND);
                            a.onCommandResult(this, miPushCommandMessage);
                            if (TextUtils.equals(miPushCommandMessage.getCommand(), MiPushClient.COMMAND_REGISTER)) {
                                a.onReceiveRegisterResult(this, miPushCommandMessage);
                            }
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            break;
                        default:
                            break;
                    }
                } catch (Throwable e) {
                    b.a(e);
                }
            }
        }
    }
}
