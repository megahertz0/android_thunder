package com.umeng.message;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.g;
import com.umeng.message.provider.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import org.json.JSONObject;

public class UmengMessageCallbackHandlerService extends IntentService {
    public static final String TAG;
    private Context a;

    static {
        TAG = UmengMessageCallbackHandlerService.class.getName();
    }

    public UmengMessageCallbackHandlerService() {
        super("UmengMessageCallbackHandlerService");
        this.a = this;
    }

    protected void onHandleIntent(Intent intent) {
        g.a(this.a, Process.myPid());
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION)) {
                try {
                    String stringExtra = intent.getStringExtra(MsgConstant.KEY_REGISTRATION_ID);
                    boolean booleanExtra = intent.getBooleanExtra(Impl.COLUMN_STATUS, false);
                    new StringBuilder("enable(): register-->:").append(stringExtra).append(",status:").append(booleanExtra);
                    IUmengRegisterCallback registerCallback = PushAgent.getInstance(this.a).getRegisterCallback();
                    if (booleanExtra) {
                        String deviceToken = MessageSharedPrefs.getInstance(this.a).getDeviceToken();
                        if (!(stringExtra == null || deviceToken == null || stringExtra.equals(deviceToken))) {
                            MessageSharedPrefs.getInstance(this.a).setHasResgister(false);
                            MessageSharedPrefs.getInstance(this.a).setDeviceToken(stringExtra);
                            ContentResolver contentResolver = this.a.getContentResolver();
                            a.a(this.a);
                            contentResolver.delete(a.e, null, null);
                        }
                        if (registerCallback != null) {
                            UTrack.getInstance(this.a).trackRegister();
                            registerCallback.onSuccess(stringExtra);
                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                public void run() {
                                    PushAgent.getInstance(UmengMessageCallbackHandlerService.this).onAppStart();
                                }
                            }, 10000);
                        }
                    } else if (registerCallback != null) {
                        registerCallback.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION)) {
                try {
                    booleanExtra = intent.getBooleanExtra(Impl.COLUMN_STATUS, false);
                    callback = PushAgent.getInstance(this.a).getCallback();
                    if (booleanExtra) {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    } else if (callback != null) {
                        callback.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION)) {
                try {
                    booleanExtra = intent.getBooleanExtra(Impl.COLUMN_STATUS, false);
                    callback = PushAgent.getInstance(this.a).getCallback();
                    if (booleanExtra) {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    } else if (callback != null) {
                        callback.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION)) {
                try {
                    UHandler messageHandler = PushAgent.getInstance(this.a).getMessageHandler();
                    new StringBuilder("messageHandler=").append(messageHandler);
                    if (messageHandler != null) {
                        UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
                        uMessage.message_id = intent.getStringExtra(SocializeConstants.WEIBO_ID);
                        uMessage.task_id = intent.getStringExtra("task_id");
                        messageHandler.handleMessage(this.a, uMessage);
                    }
                } catch (Exception e222) {
                    e222.toString();
                }
            }
        }
    }
}
