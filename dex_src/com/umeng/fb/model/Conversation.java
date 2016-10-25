package com.umeng.fb.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.umeng.fb.SyncListener;
import com.umeng.fb.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

public class Conversation {
    private static final String a;
    private List<Reply> b;
    private Context c;
    private String d;
    private boolean e;
    private OnChangeListener f;

    static {
        a = Conversation.class.getName();
    }

    public List<Reply> getReplyList() {
        return this.b;
    }

    private Conversation(Context context) {
        this.b = new ArrayList();
        this.e = false;
        this.c = context.getApplicationContext();
    }

    public static Conversation newInstance(Context context) {
        Conversation conversation = new Conversation(context);
        conversation.b = new ArrayList();
        conversation.d = c();
        Store.getInstance(context).saveConversation(conversation.d, conversation);
        return conversation;
    }

    protected static Conversation a(Context context, JSONArray jSONArray, String str) throws JSONException {
        Conversation conversation = new Conversation(context);
        for (int i = 0; i < jSONArray.length(); i++) {
            Reply fromJson = Reply.fromJson(jSONArray.getJSONObject(i));
            conversation.b.add(fromJson);
            if ("new_feedback".equals(fromJson.type)) {
                conversation.e = true;
            }
        }
        conversation.d = str;
        Collections.sort(conversation.b);
        Log.c(a, new StringBuilder("fromJson: json = ").append(jSONArray.toString()).append("\nfromJson: conversation = ").append(conversation.toString()).toString());
        return conversation;
    }

    public String getId() {
        return this.d;
    }

    public void addReply(Reply reply) {
        this.b.add(reply);
        Collections.sort(this.b);
        a();
    }

    public void addUserReply(String str) {
        Reply reply;
        String b = b();
        long time;
        if (this.e || this.b.size() > 0) {
            time = new Date().getTime();
            reply = new Reply(str, b, "user_reply", time);
        } else {
            time = new Date().getTime();
            reply = new Reply(str, b, "new_feedback", time);
            this.e = true;
        }
        reply.status = "sending";
        addReply(reply);
    }

    public JSONArray toJson() {
        JSONArray jSONArray = new JSONArray();
        for (Reply reply : this.b) {
            jSONArray.put(reply.toJson());
        }
        return jSONArray;
    }

    private boolean a(Reply reply) {
        for (Reply reply2 : this.b) {
            if (!TextUtils.isEmpty(reply2.reply_id) && reply2.reply_id.equals(reply.reply_id)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return toJson().toString();
    }

    public void sync(SyncListener syncListener) {
        Log.c(a, new StringBuilder("sync id=").append(this.d).append(":\t ").append(this).toString());
        new Thread(new AnonymousClass_1(this, new Handler(), syncListener)).start();
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.f = onChangeListener;
    }

    private void a() {
        Log.c(a, new StringBuilder("onChange: ").append(toString()).toString());
        Store.getInstance(this.c).saveConversation(this.d, this);
        if (this.f != null) {
            this.f.onChange();
        }
    }

    private static String b() {
        return new StringBuilder("R").append(UUID.randomUUID().toString()).toString();
    }

    private static String c() {
        return new StringBuilder("C").append(UUID.randomUUID().toString()).toString();
    }
}
