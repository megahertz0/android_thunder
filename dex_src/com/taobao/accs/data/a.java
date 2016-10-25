package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import java.util.Map;
import org.android.agoo.accs.AgooService;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public class a extends e {
    private static AgooService a;
    private static final Map<String, String> b;

    static {
        a = new AgooService();
        b = new AliyunMsgDistribute$1();
    }

    protected boolean a(int i, String str) {
        return false;
    }

    protected boolean a(Context context, String str, Intent intent, IAppReceiver iAppReceiver) {
        if (UtilityImpl.isMainProcess(context)) {
            return false;
        }
        ALog.i("AliyunMsgDistribute", "start MsgDistributeService", Constants.KEY_DATA_ID, str);
        intent.setClassName(intent.getPackage(), b());
        context.startService(intent);
        return true;
    }

    protected void a(Context context, IAppReceiver iAppReceiver, Intent intent, String str, String str2, int i, int i2) {
        AccsDataListener listener = GlobalClientInfo.getInstance(context).getListener(str);
        if (b.containsKey(str)) {
            a.a();
            a.b();
        } else if (listener != null) {
            AccsAbstractDataListener.onReceiveData(context, intent, listener);
        } else {
            ALog.e("AliyunMsgDistribute", new StringBuilder("callback is null dataId:").append(str2).append(" serviceId\uff1a").append(str).append(" command:").append(i).toString(), new Object[0]);
            b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, "service is null");
        }
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss", new StringBuilder("commandId=").append(i).toString(), new StringBuilder("serviceId=").append(str).append(" errorCode=").append(i2).append(" dataId=").append(str2).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
        b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_TO_BUSS, new StringBuilder("2commandId=").append(i).append("serviceId=").append(str).toString(), 0.0d);
    }

    protected void a(Context context, IAppReceiver iAppReceiver, Intent intent, int i, int i2) {
        super.a(context, iAppReceiver, intent, i, i2);
    }

    protected String a() {
        return "com.alibaba.sdk.android.push.ChannelService";
    }

    protected String b() {
        return "com.alibaba.sdk.android.push.MsgService";
    }
}
