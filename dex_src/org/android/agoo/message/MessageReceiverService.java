package org.android.agoo.message;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.taobao.accs.utl.ALog;
import org.android.agoo.control.BaseIntentService;
import org.android.agoo.service.SendMessage.Stub;

// compiled from: Taobao
public abstract class MessageReceiverService extends Service {
    Stub a;

    public abstract String getIntentServiceClassName(Context context);

    public MessageReceiverService() {
        this.a = new Stub() {
            public int doSend(Intent intent) throws RemoteException {
                BaseIntentService.runIntentInService(MessageReceiverService.this.getApplicationContext(), intent, MessageReceiverService.this.getIntentServiceClassName(MessageReceiverService.this.getApplicationContext()));
                return 0;
            }
        };
    }

    public IBinder onBind(Intent intent) {
        ALog.d("MessageReceiverService", "Message receiver aidl was binded {}", intent.getAction());
        return "org.android.agoo.client.MessageReceiverService".equals(intent.getAction()) ? this.a : this.a;
    }

    public void onCreate() {
        super.onCreate();
    }
}
