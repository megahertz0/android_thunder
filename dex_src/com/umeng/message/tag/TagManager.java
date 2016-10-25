package com.umeng.message.tag;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.common.impl.json.a;
import com.umeng.common.inter.ITagManager;
import com.umeng.common.inter.ITagManager.Result;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.tag.TagManager.TCallBack;
import com.umeng.message.tag.TagManager.TagListCallBack;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.encrypt.CharsetConvert;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TagManager {
    private static final String a;
    private static final String b = "ok";
    private static final String c = "fail";
    private static TagManager d;
    private static ITagManager f;
    private Context e;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ String[] b;

        AnonymousClass_1(TCallBack tCallBack, String[] strArr) {
            this.a = tCallBack;
            this.b = strArr;
        }

        public void run() {
            Exception e;
            Result result = null;
            if (TagManager.this.d()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (!TagManager.this.c()) {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e22) {
                    e22.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (this.b == null || this.b.length == 0) {
                try {
                    throw new Exception("No tags");
                } catch (Exception e222) {
                    e222.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else {
                List arrayList = new ArrayList();
                String[] strArr = this.b;
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    String str = strArr[i];
                    if (!MessageSharedPrefs.getInstance(TagManager.this.e).isTagSet(str) && !arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
                if (arrayList.size() == 0) {
                    this.a.onMessage(true, TagManager.this.e());
                    return;
                }
                JSONObject e3;
                try {
                    e3 = TagManager.this.b();
                    try {
                        e3.put("tags", TagManager.b(arrayList));
                        result = f.add(e3, true, this.b);
                        this.a.onMessage(true, result);
                    } catch (Exception e4) {
                        e222 = e4;
                    }
                } catch (Exception e5) {
                    e222 = e5;
                    e3 = null;
                    e222.printStackTrace();
                    if (e222.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            result = f.add(e3, false, this.b);
                            this.a.onMessage(true, result);
                            return;
                        } catch (Exception e2222) {
                            this.a.onMessage(false, result);
                            e2222.printStackTrace();
                        }
                    }
                    this.a.onMessage(false, result);
                }
            }
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ String[] b;

        AnonymousClass_2(TCallBack tCallBack, String[] strArr) {
            this.a = tCallBack;
            this.b = strArr;
        }

        public void run() {
            Exception e;
            Result result = null;
            if (TagManager.this.d()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (!TagManager.this.c()) {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e22) {
                    e22.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (this.b == null || this.b.length == 0) {
                try {
                    throw new Exception("No tags");
                } catch (Exception e222) {
                    e222.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else {
                List arrayList = new ArrayList();
                String[] strArr = this.b;
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    arrayList.add(strArr[i]);
                }
                if (arrayList.size() == 0) {
                    this.a.onMessage(true, TagManager.this.e());
                    return;
                }
                JSONObject e3;
                try {
                    e3 = TagManager.this.b();
                    try {
                        e3.put("tags", TagManager.b(arrayList));
                        result = f.update(e3, true, this.b);
                        this.a.onMessage(true, result);
                    } catch (Exception e4) {
                        e222 = e4;
                    }
                } catch (Exception e5) {
                    e222 = e5;
                    e3 = null;
                    e222.printStackTrace();
                    if (e222.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            result = f.update(e3, false, this.b);
                            this.a.onMessage(true, result);
                            return;
                        } catch (Exception e2222) {
                            this.a.onMessage(false, result);
                            e2222.printStackTrace();
                        }
                    }
                    this.a.onMessage(false, result);
                }
            }
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ String[] b;

        AnonymousClass_3(TCallBack tCallBack, String[] strArr) {
            this.a = tCallBack;
            this.b = strArr;
        }

        public void run() {
            Exception e;
            JSONObject e2;
            Result result = null;
            if (TagManager.this.d()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e3) {
                    e3.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.c()) {
                if (this.b == null || this.b.length == 0) {
                    try {
                        throw new Exception("No tags");
                    } catch (Exception e32) {
                        e32.printStackTrace();
                        this.a.onMessage(false, null);
                    }
                }
                try {
                    e2 = TagManager.this.b();
                    try {
                        e2.put("tags", TagManager.b(this.b));
                        result = f.delete(e2, true, this.b);
                        this.a.onMessage(true, result);
                    } catch (Exception e4) {
                        e32 = e4;
                    }
                } catch (Exception e5) {
                    e32 = e5;
                    e2 = null;
                    e32.printStackTrace();
                    if (e32.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            result = f.delete(e2, false, this.b);
                            this.a.onMessage(true, result);
                            return;
                        } catch (Exception e322) {
                            this.a.onMessage(false, result);
                            e322.printStackTrace();
                        }
                    }
                    this.a.onMessage(false, result);
                }
            } else {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e3222) {
                    e3222.printStackTrace();
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ TCallBack a;

        AnonymousClass_4(TCallBack tCallBack) {
            this.a = tCallBack;
        }

        public void run() {
            Exception e;
            JSONObject e2;
            Result result = null;
            if (TagManager.this.d()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e3) {
                    e3.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.c()) {
                try {
                    e2 = TagManager.this.b();
                    try {
                        result = f.reset(e2, true);
                        this.a.onMessage(true, result);
                    } catch (Exception e4) {
                        e3 = e4;
                    }
                } catch (Exception e5) {
                    e3 = e5;
                    e2 = null;
                    e3.printStackTrace();
                    if (e3.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            result = f.reset(e2, false);
                            this.a.onMessage(true, result);
                            return;
                        } catch (Exception e32) {
                            this.a.onMessage(false, result);
                            e32.printStackTrace();
                        }
                    }
                    this.a.onMessage(false, result);
                }
            } else {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e322) {
                    e322.printStackTrace();
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    class AnonymousClass_5 implements Runnable {
        final /* synthetic */ TagListCallBack a;

        AnonymousClass_5(TagListCallBack tagListCallBack) {
            this.a = tagListCallBack;
        }

        public void run() {
            Exception exception;
            List list;
            Exception exception2;
            JSONObject jSONObject = null;
            if (TagManager.this.d()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.c()) {
                try {
                    JSONObject e2 = TagManager.this.b();
                    try {
                        List list2 = f.list(e2, true);
                        try {
                            this.a.onMessage(true, list2);
                        } catch (Exception e3) {
                            exception = e3;
                            list = list2;
                            jSONObject = e2;
                            exception2 = exception;
                        }
                    } catch (Exception e32) {
                        exception = e32;
                        list = null;
                        jSONObject = e2;
                        exception2 = exception;
                        if (exception2.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                            list = f.list(jSONObject, false);
                            this.a.onMessage(true, list);
                            return;
                        }
                        this.a.onMessage(false, list);
                    }
                } catch (Exception e322) {
                    exception2 = e322;
                    list = null;
                    if (exception2.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                        try {
                            list = f.list(jSONObject, false);
                            this.a.onMessage(true, list);
                            return;
                        } catch (Exception e4) {
                        }
                    }
                    this.a.onMessage(false, list);
                }
            } else {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e3222) {
                    e3222.printStackTrace();
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    public static interface TCallBack {
        void onMessage(boolean z, Result result);
    }

    public static interface TagListCallBack {
        void onMessage(boolean z, List<String> list);
    }

    static {
        a = TagManager.class.getName();
    }

    private TagManager(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (d == null) {
                d = new TagManager(context.getApplicationContext());
                try {
                    f = (ITagManager) Class.forName(a.c).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tagManager = d;
        }
        return tagManager;
    }

    public void add(TCallBack tCallBack, String... strArr) {
        new Thread(new AnonymousClass_1(tCallBack, strArr)).start();
    }

    public void update(TCallBack tCallBack, String... strArr) {
        new Thread(new AnonymousClass_2(tCallBack, strArr)).start();
    }

    public void delete(TCallBack tCallBack, String... strArr) {
        new Thread(new AnonymousClass_3(tCallBack, strArr)).start();
    }

    public void reset(TCallBack tCallBack) {
        new Thread(new AnonymousClass_4(tCallBack)).start();
    }

    public void list(TagListCallBack tagListCallBack) {
        new Thread(new AnonymousClass_5(tagListCallBack)).start();
    }

    private static String b(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strArr) {
            stringBuilder.append(str).append(",");
        }
        if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private static String b(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            stringBuilder.append(str).append(",");
        }
        if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.e).getHeader());
        jSONObject.put("utdid", UmengMessageDeviceConfig.getUtdid(this.e));
        jSONObject.put("device_token", MessageSharedPrefs.getInstance(this.e).getDeviceToken());
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, String str) throws JSONException {
        String b = HttpRequest.c((CharSequence) str).H().r(HttpRequest.c).i(jSONObject.toString()).b(CharsetConvert.UTF_8);
        UmLog.d(a, new StringBuilder("postJson() url=").append(str).append("\n request = ").append(jSONObject).append("\n response = ").append(b).toString());
        return new JSONObject(b);
    }

    private boolean c() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.e))) {
            UmLog.e(a, "UTDID is empty");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.e).getDeviceToken())) {
            return true;
        } else {
            UmLog.e(a, "RegistrationId is empty");
            return false;
        }
    }

    private boolean d() {
        boolean z = true;
        if (MessageSharedPrefs.getInstance(this.e).getTagSendPolicy() != 1) {
            z = false;
        }
        if (z) {
            UmLog.d(a, "tag is disabled by the server");
        }
        return z;
    }

    private Result e() {
        Result result = new Result(new JSONObject());
        result.remain = MessageSharedPrefs.getInstance(this.e).getTagRemain();
        result.status = b;
        result.jsonString = new StringBuilder("status:").append(result.status).append(", remain:").append(result.remain).append(",description:").append(result.status).toString();
        return result;
    }
}
