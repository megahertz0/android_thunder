package com.xunlei.downloadprovider.frame.user;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserFeedBackUmActivity.java
final class bq extends Handler {
    final /* synthetic */ UserFeedBackUmActivity a;

    bq(UserFeedBackUmActivity userFeedBackUmActivity) {
        this.a = userFeedBackUmActivity;
    }

    public final void handleMessage(Message message) {
        int size;
        int i;
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                size = UserFeedBackUmActivity.a(this.a).size();
                if (size > 10) {
                    Object arrayList = new ArrayList();
                    for (i = size - 10; i < size; i++) {
                        arrayList.add(UserFeedBackUmActivity.a(this.a).get(i));
                    }
                    if (arrayList.size() == 10) {
                        UserFeedBackUmActivity.a(this.a).clear();
                        UserFeedBackUmActivity.a(this.a).addAll(arrayList);
                        arrayList.clear();
                    }
                }
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                i = UserFeedBackUmActivity.b(this.a).getReplyList().size();
                size = UserFeedBackUmActivity.a(this.a).size();
                if (size - UserFeedBackUmActivity.c(this.a) > 0) {
                    UserFeedBackUmActivity.a(this.a).remove((size - UserFeedBackUmActivity.c(this.a)) - 1);
                }
                UserFeedBackUmActivity.a(this.a).add(UserFeedBackUmActivity.b(this.a).getReplyList().get(i - 1));
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (UserFeedBackUmActivity.d(this.a) != null) {
                    UserFeedBackUmActivity.d(this.a).m();
                }
                size = UserFeedBackUmActivity.b(this.a).getReplyList().size();
                int size2 = UserFeedBackUmActivity.a(this.a).size();
                if (size - size2 > 10) {
                    for (i = (size - size2) - 1; i >= (size - size2) - 10; i--) {
                        UserFeedBackUmActivity.a(this.a).add(0, UserFeedBackUmActivity.b(this.a).getReplyList().get(i));
                    }
                } else {
                    for (i = (size - size2) - 1; i >= 0; i--) {
                        UserFeedBackUmActivity.a(this.a).add(0, UserFeedBackUmActivity.b(this.a).getReplyList().get(i));
                    }
                }
                break;
        }
        if (UserFeedBackUmActivity.e(this.a) != null) {
            UserFeedBackUmActivity.e(this.a).notifyDataSetChanged();
        }
    }
}
