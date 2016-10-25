package com.umeng.fb.model;

import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class Reply implements Comparable<Reply> {
    public static final String STATUS_NOT_SENT = "not_sent";
    public static final String STATUS_SENDING = "sending";
    public static final String STATUS_SENT = "sent";
    public static final String TYPE_DEV_REPLY = "dev_reply";
    public static final String TYPE_NEW_FEEDBACK = "new_feedback";
    public static final String TYPE_USER_REPLY = "user_reply";
    private static final String a;
    private static final String b = "content";
    private static final String c = "reply_id";
    private static final String d = "type";
    private static final String e = "created_at";
    private static final String f = "status";
    public String content;
    public long created_at;
    public String feedback_id;
    public String reply_id;
    public String status;
    public String type;

    static {
        a = Reply.class.getName();
    }

    public Reply(String str, String str2, String str3, long j) {
        this.content = str;
        this.reply_id = str2;
        this.type = str3;
        this.created_at = j;
        this.status = STATUS_NOT_SENT;
    }

    public static Reply fromJson(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString(b, BuildConfig.VERSION_NAME);
        String optString2 = jSONObject.optString(c, BuildConfig.VERSION_NAME);
        String string = jSONObject.getString(d);
        Reply reply = new Reply(optString, optString2, string, jSONObject.getLong(e));
        if (TYPE_DEV_REPLY.equals(string)) {
            reply.status = STATUS_SENT;
        } else {
            reply.status = jSONObject.optString(f, STATUS_NOT_SENT);
        }
        return reply;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(b, this.content);
            jSONObject.put(c, this.reply_id);
            jSONObject.put(d, this.type);
            jSONObject.put(e, this.created_at);
            jSONObject.put(f, this.status);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int compareTo(Reply reply) {
        long j = this.created_at - reply.created_at;
        if (j > 0) {
            return 1;
        }
        return j == 0 ? 0 : -1;
    }

    public String toString() {
        return toJson().toString();
    }
}
