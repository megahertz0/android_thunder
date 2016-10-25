package com.umeng.fb.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.fb.util.Log;
import com.xunlei.xiazaibao.BuildConfig;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store {
    private static final String a;
    private static Store b = null;
    private static final String d = "umeng_feedback_conversations";
    private static final String e = "umeng_feedback_user_info";
    private static final String f = "user";
    private static final String g = "uid";
    private static final String h = "device_uuid";
    private static final String i = "conversation_format_version";
    private static final String j = "last_update_at";
    private static final String k = "last_sync_at";
    private Context c;
    private SharedPreferences l;
    private SharedPreferences m;
    private Map<String, Conversation> n;

    static {
        a = Store.class.getName();
    }

    private Store(Context context) {
        this.n = new HashMap();
        this.c = context.getApplicationContext();
        this.l = this.c.getSharedPreferences(d, 0);
        this.m = this.c.getSharedPreferences(e, 0);
    }

    public static Store getInstance(Context context) {
        if (b == null) {
            b = new Store(context);
        }
        return b;
    }

    public void saveConversation(String str, Conversation conversation) {
        this.l.edit().putString(str, conversation.toJson().toString()).apply();
        this.n.put(str, conversation);
    }

    public void removeConversation(String str) {
        this.l.edit().remove(str).apply();
        this.n.remove(str);
    }

    public void saveUserInfo(UserInfo userInfo) {
        this.m.edit().putString(f, userInfo.toJson().toString()).putLong(j, System.currentTimeMillis()).apply();
    }

    public UserInfo getUserInfo() {
        String string = this.m.getString(f, BuildConfig.VERSION_NAME);
        if (BuildConfig.VERSION_NAME.equals(string)) {
            return new UserInfo();
        }
        try {
            return new UserInfo(new JSONObject(string));
        } catch (JSONException e) {
            e.printStackTrace();
            return new UserInfo();
        }
    }

    public long getUserInfoLastUpdateAt() {
        return this.m.getLong(j, 0);
    }

    public long getUserInfoLastSyncAt() {
        return this.m.getLong(k, 0);
    }

    public void setUserInfoLastSyncAt(long j) {
        this.m.edit().putLong(k, j).apply();
    }

    public Conversation getConversationById(String str) {
        if (!this.n.containsKey(str)) {
            try {
                this.n.put(str, Conversation.a(this.c, new JSONArray(this.l.getString(str, BuildConfig.VERSION_NAME)), str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return (Conversation) this.n.get(str);
    }

    public List<String> getAllConversationIds() {
        Map all = this.l.getAll();
        List<String> arrayList = new ArrayList();
        for (String str : all.keySet()) {
            arrayList.add(str);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    void a() {
        this.l.edit().clear().apply();
        this.m.edit().clear().apply();
    }

    public void setUid(String str) {
        this.m.edit().putString(g, str).apply();
    }

    public String getUid() {
        return this.m.getString(g, BuildConfig.VERSION_NAME);
    }

    public String getDeviceUUID() {
        String string = this.m.getString(h, BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        string = UUID.randomUUID().toString();
        this.m.edit().putString(h, string).apply();
        return string;
    }

    public void migrateData() {
        try {
            Map all = this.l.getAll();
            for (String str : all.keySet()) {
                String str2 = (String) all.get(str);
                Conversation newInstance = Conversation.newInstance(this.c);
                JSONArray jSONArray = new JSONArray(str2);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    try {
                        String optString = jSONObject.optString("content");
                        String optString2 = jSONObject.optString("feedback_id");
                        String optString3 = jSONObject.optString("status");
                        Date date = new Date();
                        Reply reply = new Reply(optString, optString2, jSONObject.optString(AgooConstants.MESSAGE_TYPE), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(jSONObject.optString("datetime")).getTime());
                        reply.status = optString3;
                        newInstance.addReply(reply);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                saveConversation(str, newInstance);
                Log.c(a, new StringBuilder("migrate data: id=").append(str).append("\n ").toString());
                Log.c(a, new StringBuilder("old: \n").append(str2).append("\n").toString());
                Log.c(a, new StringBuilder("new :\n").append(newInstance.toString()).toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.m.edit().putInt(i, SimpleLog.LOG_LEVEL_ERROR).apply();
    }

    public boolean isMigrated() {
        return this.m.getInt(i, 0) >= 5;
    }
}
