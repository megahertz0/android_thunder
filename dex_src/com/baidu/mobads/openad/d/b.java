package com.baidu.mobads.openad.d;

import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.umeng.a;
import java.util.HashMap;

public class b implements IOAdEvent {
    public static final String COMPLETE = "complete";
    public static final String EVENT_MESSAGE = "message";
    private final String a;
    private final HashMap<String, Object> b;
    private final int c;
    private Object d;

    public b(String str) {
        this(str, 0, new HashMap());
    }

    public b(String str, String str2) {
        this(str, 0, str2);
    }

    public b(String str, HashMap<String, Object> hashMap) {
        this(str, 0, (HashMap) hashMap);
    }

    public b(String str, int i) {
        this(str, i, new HashMap());
    }

    public b(String str, int i, HashMap<String, Object> hashMap) {
        this.a = str;
        this.c = i;
        this.b = hashMap;
    }

    public b(String str, int i, String str2) {
        this(str, i, new HashMap());
        this.b.put(EVENT_MESSAGE, str2);
    }

    public String getType() {
        return this.a;
    }

    public int getCode() {
        return this.c;
    }

    public HashMap<String, Object> getData() {
        return this.b;
    }

    public void setTarget(Object obj) {
        this.d = obj;
    }

    public Object getTarget() {
        return this.d;
    }

    public String getMessage() {
        try {
            return (String) this.b.get(EVENT_MESSAGE);
        } catch (Exception e) {
            return a.d;
        }
    }
}
