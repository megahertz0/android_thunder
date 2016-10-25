package com.umeng.fb.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import com.umeng.fb.ConversationActivity;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.res.f;
import com.umeng.fb.util.Log;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.j;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackPushImpl implements IFeedbackPush {
    private static IFeedbackPush b;
    private final String a;
    private Context c;
    public String conversation_id;
    private Class<?> d;
    public List<Reply> devReplyList;
    private SharedPreferences e;
    private final String f;
    public boolean fbFragmentTag;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private IFeedbackPushCallbacks k;
    private boolean l;

    public static interface IFeedbackPushCallbacks {
        void onAddPushDevReply();
    }

    public static IFeedbackPush getInstance(Context context) {
        if (b == null) {
            b = new FeedbackPushImpl(context);
        }
        return b;
    }

    private FeedbackPushImpl(Context context) {
        this.a = FeedbackPushImpl.class.getName();
        this.d = null;
        this.f = "feedback_push";
        this.g = j.z;
        this.h = "umeng_feedback";
        this.i = "feedback_id";
        this.j = "switch";
        this.fbFragmentTag = false;
        this.c = context;
        this.devReplyList = new ArrayList();
        this.e = this.c.getSharedPreferences("feedback_push", 0);
    }

    public void init(Class<?> cls, boolean z) {
        this.d = cls;
        init(z);
    }

    public void init(boolean z) {
        a();
        if (!z) {
            try {
                PushAgent.getInstance(this.c).setMessageHandler(new UmengMessageHandler() {
                    public void dealWithCustomMessage(Context context, UMessage uMessage) {
                        FeedbackPushImpl.this.dealFBMessage(new FBMessage(uMessage.custom));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Class.forName("com.umeng.message.PushAgent");
            } catch (Exception e2) {
            }
        }
    }

    public boolean onFBMessage(Intent intent) {
        try {
            UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra(AgooConstants.MESSAGE_BODY)));
            Log.c(this.a, new StringBuilder("received push message in onFBMessage - ").append(uMessage.custom).toString());
            return dealFBMessage(new FBMessage(uMessage.custom));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dealFBMessage(FBMessage fBMessage) {
        boolean z = false;
        Log.c(this.a, new StringBuilder("received push message  - ").append(fBMessage.custom).toString());
        if (!a(fBMessage.custom)) {
            return false;
        }
        if (this.e == null) {
            this.e = this.c.getSharedPreferences("feedback_push", 0);
        }
        this.l = this.e.getBoolean("switch", false);
        if (!this.l) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(fBMessage.custom);
            String string = jSONObject.getString("feedback_id");
            Reply fromJson = Reply.fromJson(jSONObject);
            fromJson.feedback_id = string;
            this.devReplyList.add(fromJson);
            z = this.fbFragmentTag;
            if (!z) {
                a(this.devReplyList, string);
            } else if (this.conversation_id == null || !this.conversation_id.endsWith(string)) {
                a(this.devReplyList, string);
            } else {
                this.k.onAddPushDevReply();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    private boolean a(String str) {
        try {
            return new JSONObject(str).optString("feedback_id", null) != null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a() {
        Log.c(this.a, new StringBuilder("setAlias UUID ").append(Store.getInstance(this.c).getDeviceUUID()).toString());
        if (!this.e.getBoolean(j.z, false)) {
            new Thread() {
                public void run() {
                    int i = 0;
                    while (i < 10) {
                        try {
                            if (PushAgent.getInstance(FeedbackPushImpl.this.c).addAlias(Store.getInstance(FeedbackPushImpl.this.c).getDeviceUUID(), "umeng_feedback")) {
                                FeedbackPushImpl.this.e.edit().putBoolean(j.z, true).apply();
                                return;
                            }
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            i++;
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }

    private void a(List<Reply> list, String str) {
        String string;
        if (list.size() == 1) {
            string = this.c.getResources().getString(f.b(this.c));
            string = String.format(Locale.US, string, new Object[]{((Reply) list.get(0)).content});
        } else {
            String string2 = this.c.getResources().getString(f.c(this.c));
            string = String.format(Locale.US, string2, new Object[]{Integer.valueOf(list.size())});
        }
        NotificationManager notificationManager = (NotificationManager) this.c.getSystemService("notification");
        CharSequence string3 = this.c.getString(f.a(this.c));
        try {
            Intent intent;
            int i = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).applicationInfo.icon;
            if (this.d != null) {
                intent = new Intent(this.c, this.d);
            } else {
                intent = new Intent(this.c, ConversationActivity.class);
            }
            intent.setFlags(131072);
            intent.putExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, str);
            notificationManager.notify(0, new Builder(this.c).setSmallIcon(i).setContentTitle(string3).setTicker(string3).setContentText(r1).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.c, (int) SystemClock.uptimeMillis(), intent, 134217728)).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFBPushCallbacks(IFeedbackPushCallbacks iFeedbackPushCallbacks) {
        this.k = iFeedbackPushCallbacks;
    }

    public void enable() {
        this.e.edit().putBoolean("switch", true).apply();
        this.l = true;
    }

    public void disable() {
        this.e.edit().putBoolean("switch", false).apply();
        this.l = false;
    }
}
