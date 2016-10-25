package com.xunlei.downloadprovider.util.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadlib.parameter.XLConstant.XLErrorCode;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CrackUtil.java
final class d extends Handler {
    final /* synthetic */ c a;

    d(c cVar, Looper looper) {
        this.a = cVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        if (message.obj != null && c.a(this.a).containsKey(message.obj)) {
            c.b(this.a).removeMessages(XLErrorCode.APPKEY_CHECKER_ERROR, message.obj);
            a aVar = (a) c.a(this.a).remove(message.obj);
            switch (message.what) {
                case XLErrorCode.APPKEY_CHECKER_ERROR:
                    if (aVar != null) {
                        aVar.mCancel = true;
                        if (aVar.getOnCrackCallback() != null) {
                            switch (aVar.mOperateType) {
                                case SimpleLog.LOG_LEVEL_TRACE:
                                    aVar.getOnCrackCallback().a(XLPayErrorCode.XLP_GATE_SIGN_ERROR, null);
                                    break;
                                case SimpleLog.LOG_LEVEL_DEBUG:
                                    aVar.getOnCrackCallback().a(XLPayErrorCode.XLP_GATE_SIGN_ERROR, aVar.mTitle, aVar.mRefURL, aVar.mTAG, aVar.mID, null);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                case 9902:
                    if (aVar != null) {
                        String string = message.getData().getString("crack_key_json_data");
                        if (aVar.getOnCrackCallback() != null) {
                            aVar.getOnCrackCallback().a(0, string);
                        }
                    }
                    break;
                case 9903:
                    if (aVar != null) {
                        String string2 = message.getData().getString("crack_key_json_data");
                        if (aVar.getOnCrackCallback() != null) {
                            aVar.getOnCrackCallback().a(0, aVar.mTitle, aVar.mRefURL, aVar.mTAG, aVar.mID, string2);
                        }
                    }
                    break;
            }
        }
        super.handleMessage(message);
    }
}
