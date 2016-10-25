package com.umeng.fb;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.FbSwitch;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.push.FeedbackPushImpl;
import com.umeng.fb.res.f;
import com.umeng.fb.util.Log;
import com.umeng.message.entity.UMessage;
import java.util.List;
import java.util.Locale;

public class FeedbackAgent {
    private static final String a;
    private static boolean d;
    private Context b;
    private Store c;

    static {
        a = FeedbackAgent.class.getName();
        d = false;
    }

    public FeedbackAgent(Context context) {
        this.b = context;
        this.c = Store.getInstance(this.b);
        a();
    }

    private void a() {
        if (!this.c.isMigrated()) {
            this.c.migrateData();
        }
        if (TextUtils.isEmpty(this.c.getUid())) {
            new AnonymousClass_1(this).start();
        }
    }

    public void setDebug(boolean z) {
        Log.LOG = z;
    }

    public List<String> getAllConversationIds() {
        return this.c.getAllConversationIds();
    }

    public Conversation getConversationById(String str) {
        return this.c.getConversationById(str);
    }

    public Conversation getDefaultConversation() {
        List allConversationIds = getAllConversationIds();
        if (allConversationIds == null || allConversationIds.size() <= 0) {
            Log.c(a, "getDefaultConversation: No conversation saved locally. Create a new one.");
            return Conversation.newInstance(this.b);
        }
        Log.c(a, new StringBuilder("getDefaultConversation: There are ").append(allConversationIds.size()).append(" saved locally, use the first one by default.").toString());
        return getConversationById((String) allConversationIds.get(0));
    }

    public void sync() {
        getDefaultConversation().sync(new AnonymousClass_2(this));
    }

    public void showReplyNotification(List<Reply> list) {
        String string;
        if (list.size() == 1) {
            string = this.b.getResources().getString(f.b(this.b));
            string = String.format(Locale.US, string, new Object[]{((Reply) list.get(0)).content});
        } else {
            String string2 = this.b.getResources().getString(f.c(this.b));
            string = String.format(Locale.US, string2, new Object[]{Integer.valueOf(list.size())});
        }
        NotificationManager notificationManager = (NotificationManager) this.b.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        CharSequence string3 = this.b.getString(f.a(this.b));
        Intent intent = new Intent(this.b, ConversationActivity.class);
        intent.setFlags(AccessibilityNodeInfoCompat.ACTION_SET_SELECTION);
        PendingIntent activity = PendingIntent.getActivity(this.b, (int) SystemClock.uptimeMillis(), intent, 134217728);
        try {
            notificationManager.notify(0, new Builder(this.b).setSmallIcon(this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0).applicationInfo.icon).setContentTitle(string3).setTicker(string3).setContentText(r1).setAutoCancel(true).setContentIntent(activity).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserInfo getUserInfo() {
        return this.c.getUserInfo();
    }

    public void setUserInfo(UserInfo userInfo) {
        this.c.saveUserInfo(userInfo);
    }

    public long getUserInfoLastUpdateAt() {
        return this.c.getUserInfoLastUpdateAt();
    }

    public void startFeedbackActivity() {
        try {
            this.b.startActivity(new Intent(this.b, ConversationActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFeedbackPush() {
        FeedbackPushImpl.getInstance(this.b).enable();
    }

    public void closeFeedbackPush() {
        FeedbackPushImpl.getInstance(this.b).disable();
    }

    public void removeWelcomeInfo() {
        FbSwitch.getInstance(this.b).setWelcomeInfoSwitch(false);
    }

    public void setWelcomeInfo() {
        FbSwitch.getInstance(this.b).setWelcomeInfoSwitch(true);
    }

    public void setWelcomeInfo(String str) {
        FbSwitch.getInstance(this.b).setWelcomeInfoSwitch(true);
        if (str != null) {
            FbSwitch.getInstance(this.b).setWelcomeInfo(str);
        }
    }

    public void updateUserInfo() {
        new Thread(new AnonymousClass_3(this)).start();
    }
}
