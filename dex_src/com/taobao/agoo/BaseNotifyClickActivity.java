package com.taobao.agoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import java.util.HashSet;
import java.util.Set;
import org.android.agoo.common.MsgDO;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public class BaseNotifyClickActivity extends Activity {
    private static final String TAG = "accs.BaseNotifyClickActivity";
    private static final String TAOBAO_PACKAGE_NAME = "com.taobao.taobao";
    private static Set<INotifyListener> notifyListeners;
    private AgooFactory agooFactory;
    private String msgSource;
    private NotifManager notifyManager;

    // compiled from: Taobao
    public static interface INotifyListener {
        String getMsgSource();

        String parseMsgFromIntent(Intent intent);
    }

    public static void addNotifyListener(INotifyListener iNotifyListener) {
        if (notifyListeners == null) {
            notifyListeners = new HashSet();
        }
        notifyListeners.add(iNotifyListener);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ALog.i(TAG, "onCreate", new Object[0]);
        buildMessage(getIntent());
    }

    public void onStart() {
        super.onStart();
    }

    public void onMessage(Intent intent) {
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ALog.i(TAG, "onNewIntent", new Object[0]);
        buildMessage(intent);
    }

    private void buildMessage(Intent intent) {
        new a(this, intent).start();
    }

    private String parseMsgFromNotifyListener(Intent intent) {
        String str = null;
        if (notifyListeners == null || notifyListeners.size() <= 0) {
            ALog.e(TAG, "cann't parse as notifyListeners is empty", new Object[0]);
            return null;
        }
        for (INotifyListener iNotifyListener : notifyListeners) {
            String parseMsgFromIntent = iNotifyListener.parseMsgFromIntent(intent);
            this.msgSource = iNotifyListener.getMsgSource();
            if (!TextUtils.isEmpty(parseMsgFromIntent) && !TextUtils.isEmpty(this.msgSource)) {
                ALog.i(TAG, new StringBuilder("result: ").append(parseMsgFromIntent).append(" msgSource: ").append(this.msgSource).toString(), new Object[0]);
                return parseMsgFromIntent;
            }
            str = parseMsgFromIntent;
        }
        return str;
    }

    private String parseMsgFromChannel(Intent intent) {
        try {
            String str = (String) Class.forName("com.xiaomi.mipush.sdk.PushMessageHelper").getField("KEY_MESSAGE").get(null);
            if (intent.getSerializableExtra(str) == null) {
                return null;
            }
            Class forName = Class.forName("com.xiaomi.mipush.sdk.MiPushMessage");
            str = (String) forName.getMethod("getContent", null).invoke(forName.cast(intent.getSerializableExtra(str)), new Object[0]);
            try {
                this.msgSource = MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI;
                return str;
            } catch (Exception e) {
                Object e2 = e;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str = null;
            Exception exception2 = exception;
            ALog.e(TAG, new StringBuilder("parseMsgFromChannel exception: ").append(e2).toString(), new Object[0]);
            return str;
        }
    }

    private void reportClickNotifyMsg(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(SocializeConstants.WEIBO_ID);
            String stringExtra2 = intent.getStringExtra("message_source");
            String stringExtra3 = intent.getStringExtra("report");
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = stringExtra;
            msgDO.messageSource = stringExtra2;
            msgDO.reportStr = stringExtra3;
            msgDO.msgStatus = MessageService.MSG_ACCS_NOTIFY_CLICK;
            ALog.i(TAG, new StringBuilder("reportClickNotifyMsg messageId:").append(stringExtra).append(" source:").append(stringExtra2).append(" reportStr:").append(stringExtra3).append(" status:").append(msgDO.msgStatus).toString(), new Object[0]);
            this.notifyManager.report(msgDO, null);
        } catch (Exception e) {
            ALog.e(TAG, new StringBuilder("reportClickNotifyMsg exception: ").append(e).toString(), new Object[0]);
        }
    }
}
