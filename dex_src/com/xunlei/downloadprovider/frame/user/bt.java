package com.xunlei.downloadprovider.frame.user;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserFeedBackUmActivity.java
final class bt implements OnClickListener {
    final /* synthetic */ UserFeedBackUmActivity a;

    bt(UserFeedBackUmActivity userFeedBackUmActivity) {
        this.a = userFeedBackUmActivity;
    }

    public final void onClick(View view) {
        Object toString = UserFeedBackUmActivity.f(this.a).getText().toString();
        UserFeedBackUmActivity.f(this.a).getEditableText().clear();
        if (!TextUtils.isEmpty(toString)) {
            UserFeedBackUmActivity.b(this.a).addUserReply(toString);
            int size = UserFeedBackUmActivity.b(this.a).getReplyList().size();
            if (size > 0) {
                UserFeedBackUmActivity.a(this.a).add(UserFeedBackUmActivity.b(this.a).getReplyList().get(size - 1));
                UserFeedBackUmActivity.g(this.a).sendEmptyMessage(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            UserFeedBackUmActivity.a(this.a, SimpleLog.LOG_LEVEL_DEBUG);
        }
    }
}
