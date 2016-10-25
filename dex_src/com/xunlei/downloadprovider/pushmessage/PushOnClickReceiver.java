package com.xunlei.downloadprovider.pushmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.NotificationProxyBroadcastReceiver;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.downloadprovider.pushmessage.d.g;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

public class PushOnClickReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            new Intent().addFlags(268435456);
            a aVar = (a) intent.getSerializableExtra("mqtt_result");
            if (aVar != null) {
                int intExtra = intent.getIntExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, -1);
                new StringBuilder("onReceive PushResult result =").append(aVar).append(",action=").append(intExtra);
                switch (intExtra) {
                    case Impl.STATUS_PEER_NOT_FOUND_ERROR:
                        switch (aVar.u) {
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                try {
                                    UTrack.getInstance(context).trackMsgClick(new UMessage(new JSONObject(aVar.a)));
                                    new StringBuilder("dispatchJump -----------msg.displayType=").append(aVar.s);
                                    Intent a = f.a(context, aVar);
                                    if (a != null) {
                                        context.startActivity(a);
                                    }
                                } catch (JSONException e) {
                                }
                        }
                        if (aVar != null) {
                            switch (aVar.s) {
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                case XZBDevice.DOWNLOAD_LIST_FAILED:
                                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                                    g.a(aVar.r, aVar.l);
                                default:
                                    break;
                            }
                        }
                    case Impl.STATUS_TIME_OUT:
                        switch (aVar.u) {
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                try {
                                    UTrack.getInstance(context).trackMsgDismissed(new UMessage(new JSONObject(aVar.a)));
                                    XLLiveSDK.getInstance(context).onPushNotificationCanceled(context, aVar.t);
                                } catch (JSONException e2) {
                                }
                            default:
                                break;
                        }
                    default:
                        break;
                }
            }
        } catch (Exception e3) {
        }
    }
}
