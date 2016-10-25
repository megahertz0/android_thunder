package com.xunlei.tdlive.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

public class IMClient implements Callback {
    private Context a;
    private Handler b;
    private IMClientCallback c;
    private BroadcastReceiver d;

    public static interface IMClientCallback {
        void onIMConnected(int i, String str);

        void onIMConnectionLost();

        void onIMDisconnected();

        void onIMKickout(String str);

        void onIMMessageArrived(String str, byte[] bArr, int i, boolean z);
    }

    private IMClient() {
        this.d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                IMClient.this.b.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, intent).sendToTarget();
            }
        };
        this.b = new Handler(this);
    }

    public static IMClient a(Context context, IMClientCallback iMClientCallback) {
        IMClient iMClient = new IMClient();
        iMClient.a = context.getApplicationContext();
        iMClient.c = iMClientCallback;
        if (iMClient.c != null) {
            IMService.a(iMClient.a, iMClient.d);
        }
        return iMClient;
    }

    public void a() {
        if (this.c != null) {
            IMService.b(this.a, this.d);
        }
    }

    public void a(IMMessage iMMessage) {
        IMService.a(this.a, iMMessage.a(), iMMessage.c(), iMMessage.b());
    }

    public void a(String str, int i, String str2) {
        IMService.a(this.a, str, i, str2);
    }

    public void b() {
        IMService.a(this.a);
    }

    public void c() {
        a(0);
    }

    public void a(long j) {
        IMService.a(this.a, j);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1000 && this.c != null) {
            Intent intent = (Intent) message.obj;
            if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECT_LOST")) {
                this.c.onIMConnectionLost();
            } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.CALLBACK_PUBLISH_ARRIVED")) {
                this.c.onIMMessageArrived(intent.getStringExtra("topic"), intent.getByteArrayExtra("payload"), intent.getIntExtra("qos", 0), intent.getBooleanExtra("retained", false));
            } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.CALLBACK_CONNECTED")) {
                this.c.onIMConnected(intent.getIntExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, -1), intent.getStringExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.CALLBACK_DISCONNECTED")) {
                this.c.onIMDisconnected();
            } else if (intent.getAction().equals("com.xunlei.tdlive.sdk.IMService.CALLBACK_KICKOUT")) {
                this.c.onIMKickout(intent.getStringExtra(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            }
        }
        return true;
    }
}
