package com.umeng.message;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.agoo.BaseNotifyClickActivity;
import com.umeng.common.UmLog;
import com.umeng.message.entity.UMessage;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class UmengNotifyClickActivity extends BaseNotifyClickActivity {
    private static final String a;

    static {
        a = UmengNotifyClickActivity.class.getName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onStart() {
        super.onStart();
    }

    public void onMessage(Intent intent) {
        String stringExtra = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        UmLog.d(a, new StringBuilder("onMessage():[").append(stringExtra).append("]").toString());
        try {
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            uMessage.message_id = intent.getStringExtra(AgooConstants.MESSAGE_ID);
            uMessage.task_id = intent.getStringExtra(AgooConstants.MESSAGE_TASK_ID);
            UTrack.getInstance(this).trackMiPushMsgClick(uMessage);
        } catch (JSONException e) {
            e.printStackTrace();
            UmLog.d(a, e.toString());
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
