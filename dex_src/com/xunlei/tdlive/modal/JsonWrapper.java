package com.xunlei.tdlive.modal;

import com.google.gson.Gson;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonWrapper implements Serializable {
    private static final long serialVersionUID = -3918552506396099585L;
    private JSONArray mJsonArr;
    private JSONObject mJsonObj;

    public String toString() {
        if (this.mJsonObj != null) {
            return this.mJsonObj.toString();
        }
        return this.mJsonArr != null ? this.mJsonArr.toString() : a.d;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        if (this.mJsonObj != null) {
            objectOutputStream.write(this.mJsonObj.toString().getBytes());
        } else if (this.mJsonArr != null) {
            objectOutputStream.write(this.mJsonArr.toString().getBytes());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(objectInputStream, GameManager.DEFAULT_CHARSET));
        char[] cArr = new char[256];
        String str = a.d;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                str = str.concat(new String(cArr, 0, read));
            } else {
                bufferedReader.close();
                try {
                    this.mJsonObj = new JSONObject(str);
                    return;
                } catch (JSONException e) {
                    try {
                        this.mJsonArr = new JSONArray(str);
                    } catch (JSONException e2) {
                        throw new IOException(e2.toString());
                    }
                }
            }
        }
    }

    public JsonWrapper(String str) {
        try {
            this.mJsonObj = new JSONObject(str);
        } catch (JSONException e) {
            e.toString();
            try {
                this.mJsonArr = new JSONArray(str);
            } catch (JSONException e2) {
                e2.toString();
            }
        }
    }

    public JsonWrapper(JSONObject jSONObject) {
        this.mJsonObj = jSONObject;
    }

    public JsonWrapper(JSONArray jSONArray) {
        this.mJsonArr = jSONArray;
    }

    public JsonWrapper(Object obj) {
        this(new Gson().toJson(obj));
    }

    public static JsonWrapper loadFromStream(InputStream inputStream, String str) {
        byte[] bArr = new byte[256];
        String str2 = a.d;
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                str2 = str2.concat(new String(bArr, 0, read));
            } catch (Throwable th) {
            }
        }
        if (str2.length() > 0) {
            str = str2;
        }
        return new JsonWrapper(str);
    }

    public boolean writeToStream(OutputStream outputStream) {
        try {
            if (this.mJsonArr != null) {
                outputStream.write(this.mJsonArr.toString().getBytes());
            } else if (this.mJsonObj != null) {
                outputStream.write(this.mJsonObj.toString().getBytes());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static JsonWrapper loadFromFile(String str) {
        char[] cArr = new char[256];
        String str2 = a.d;
        try {
            InputStream fileInputStream = new FileInputStream(new File(str));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, GameManager.DEFAULT_CHARSET));
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read == -1) {
                    break;
                }
                str2 = str2.concat(new String(cArr, 0, read));
            }
            bufferedReader.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        return new JsonWrapper(str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeToFile(java.lang.String r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.writeToFile(java.lang.String):boolean");
        /*
        this = this;
        r0 = r2.mJsonArr;
        if (r0 != 0) goto L_0x0008;
    L_0x0004:
        r0 = r2.mJsonObj;
        if (r0 == 0) goto L_0x003b;
    L_0x0008:
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003a }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x003a }
        r1.<init>(r3);	 Catch:{ Exception -> 0x003a }
        r0.<init>(r1);	 Catch:{ Exception -> 0x003a }
        r1 = r2.mJsonArr;	 Catch:{ Exception -> 0x003a }
        if (r1 == 0) goto L_0x0028;
    L_0x0016:
        r1 = r2.mJsonArr;	 Catch:{ Exception -> 0x003a }
        r1 = r1.toString();	 Catch:{ Exception -> 0x003a }
        r1 = r1.getBytes();	 Catch:{ Exception -> 0x003a }
        r0.write(r1);	 Catch:{ Exception -> 0x003a }
    L_0x0023:
        r0.close();	 Catch:{ Exception -> 0x003a }
        r0 = 1;
    L_0x0027:
        return r0;
    L_0x0028:
        r1 = r2.mJsonObj;	 Catch:{ Exception -> 0x003a }
        if (r1 == 0) goto L_0x0023;
    L_0x002c:
        r1 = r2.mJsonObj;	 Catch:{ Exception -> 0x003a }
        r1 = r1.toString();	 Catch:{ Exception -> 0x003a }
        r1 = r1.getBytes();	 Catch:{ Exception -> 0x003a }
        r0.write(r1);	 Catch:{ Exception -> 0x003a }
        goto L_0x0023;
    L_0x003a:
        r0 = move-exception;
    L_0x003b:
        r0 = 0;
        goto L_0x0027;
        */
    }

    public boolean isArray() {
        return this.mJsonArr != null;
    }

    public int getLength() {
        return isArray() ? this.mJsonArr.length() : 0;
    }

    public boolean getBoolean(String str, boolean z) {
        if (this.mJsonObj == null) {
            return z;
        }
        try {
            return this.mJsonObj.getBoolean(str);
        } catch (JSONException e) {
            return z;
        }
    }

    public String getString(String str, String str2) {
        if (this.mJsonObj == null) {
            return str2;
        }
        try {
            String string = this.mJsonObj.getString(str);
            return "null".equals(string) ? a.d : string;
        } catch (JSONException e) {
            return str2;
        }
    }

    public String getString(int i, String str) {
        if (this.mJsonArr == null) {
            return str;
        }
        try {
            String string = this.mJsonArr.getString(i);
            return "null".equals(string) ? a.d : string;
        } catch (JSONException e) {
            return str;
        }
    }

    public int getInt(String str, int i) {
        if (this.mJsonObj == null) {
            return i;
        }
        try {
            return this.mJsonObj.getInt(str);
        } catch (JSONException e) {
            return i;
        }
    }

    public int getInt(int i, int i2) {
        if (this.mJsonArr == null) {
            return i2;
        }
        try {
            return this.mJsonArr.getInt(i);
        } catch (JSONException e) {
            return i2;
        }
    }

    public long getLong(String str, long j) {
        if (this.mJsonObj == null) {
            return j;
        }
        try {
            return this.mJsonObj.getLong(str);
        } catch (JSONException e) {
            return j;
        }
    }

    public long getLong(int i, long j) {
        if (this.mJsonArr == null) {
            return j;
        }
        try {
            return this.mJsonArr.getLong(i);
        } catch (JSONException e) {
            return j;
        }
    }

    public float getFloat(String str, float f) {
        return (float) getDouble(str, (double) f);
    }

    public float getFloat(int i, float f) {
        return (float) getDouble(i, (double) f);
    }

    public double getDouble(String str, double d) {
        if (this.mJsonObj == null) {
            return d;
        }
        try {
            return this.mJsonObj.getDouble(str);
        } catch (JSONException e) {
            return d;
        }
    }

    public double getDouble(int i, double d) {
        if (this.mJsonArr == null) {
            return d;
        }
        try {
            return this.mJsonArr.getDouble(i);
        } catch (JSONException e) {
            return d;
        }
    }

    public JsonWrapper getArray(String str) {
        if (this.mJsonObj != null) {
            try {
                return new JsonWrapper(this.mJsonObj.getJSONArray(str));
            } catch (JSONException e) {
                new StringBuilder("key: ").append(str).append(", ").append(e.toString());
            }
        }
        return null;
    }

    public JsonWrapper getArray(String str, String str2) {
        JsonWrapper array = getArray(str);
        return array == null ? new JsonWrapper(str2) : array;
    }

    public JsonWrapper getArray(int i) {
        if (this.mJsonArr != null) {
            try {
                return new JsonWrapper(this.mJsonArr.getJSONArray(i));
            } catch (JSONException e) {
                new StringBuilder("index: ").append(i).append(", ").append(e.toString());
            }
        }
        return null;
    }

    public JsonWrapper getArray(int i, String str) {
        JsonWrapper array = getArray(i);
        return array == null ? new JsonWrapper(str) : array;
    }

    public JsonWrapper getObject(String str) {
        if (this.mJsonObj != null) {
            try {
                return new JsonWrapper(this.mJsonObj.getJSONObject(str));
            } catch (JSONException e) {
                new StringBuilder("key: ").append(str).append(", ").append(e.toString());
            }
        }
        return null;
    }

    public JsonWrapper getObject(String str, String str2) {
        JsonWrapper object = getObject(str);
        return object == null ? new JsonWrapper(str2) : object;
    }

    public JsonWrapper getObject(int i) {
        if (this.mJsonArr != null) {
            try {
                return new JsonWrapper(this.mJsonArr.getJSONObject(i));
            } catch (JSONException e) {
                new StringBuilder("index: ").append(i).append(", ").append(e.toString());
            }
        }
        return null;
    }

    public JsonWrapper getObject(int i, String str) {
        JsonWrapper object = getObject(i);
        return object == null ? new JsonWrapper(str) : object;
    }

    public JsonWrapper add(JsonWrapper jsonWrapper) {
        if (this.mJsonArr != null) {
            if (jsonWrapper.isArray()) {
                for (int i = 0; i < jsonWrapper.getLength(); i++) {
                    try {
                        this.mJsonArr.put(jsonWrapper.mJsonArr.get(i));
                    } catch (JSONException e) {
                    }
                }
            } else {
                this.mJsonArr.put(jsonWrapper.mJsonObj);
            }
        }
        return this;
    }

    public JsonWrapper replace(JsonWrapper jsonWrapper, int i, int i2) {
        int i3 = 0;
        if (this.mJsonArr != null) {
            JSONArray jSONArray = new JSONArray();
            int i4 = 0;
            while (i4 < i && i4 < this.mJsonArr.length()) {
                try {
                    jSONArray.put(this.mJsonArr.get(i4));
                } catch (JSONException e) {
                }
                i4++;
            }
            if (jsonWrapper.isArray()) {
                while (i3 < jsonWrapper.getLength()) {
                    try {
                        jSONArray.put(jsonWrapper.mJsonArr.get(i3));
                    } catch (JSONException e2) {
                    }
                    i3++;
                }
            } else {
                jSONArray.put(jsonWrapper.mJsonObj);
            }
            while (i2 < this.mJsonArr.length()) {
                try {
                    jSONArray.put(this.mJsonArr.get(i2));
                } catch (JSONException e3) {
                }
                i2++;
            }
            this.mJsonArr = jSONArray;
        }
        return this;
    }

    public JsonWrapper put(JsonWrapper jsonWrapper) {
        return put(-1, jsonWrapper);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper put(int r3, com.xunlei.tdlive.modal.JsonWrapper r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.put(int, com.xunlei.tdlive.modal.JsonWrapper):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r2.mJsonArr;
        if (r0 == 0) goto L_0x0013;
    L_0x0004:
        if (r3 >= 0) goto L_0x001e;
    L_0x0006:
        r0 = r4.isArray();	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x0014;
    L_0x000c:
        r0 = r2.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r1 = r4.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r0.put(r1);	 Catch:{ Exception -> 0x001c }
    L_0x0013:
        return r2;
    L_0x0014:
        r0 = r2.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r1 = r4.mJsonObj;	 Catch:{ Exception -> 0x001c }
        r0.put(r1);	 Catch:{ Exception -> 0x001c }
        goto L_0x0013;
    L_0x001c:
        r0 = move-exception;
        goto L_0x0013;
    L_0x001e:
        r0 = r4.isArray();	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x002c;
    L_0x0024:
        r0 = r2.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r1 = r4.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r0.put(r3, r1);	 Catch:{ Exception -> 0x001c }
        goto L_0x0013;
    L_0x002c:
        r0 = r2.mJsonArr;	 Catch:{ Exception -> 0x001c }
        r1 = r4.mJsonObj;	 Catch:{ Exception -> 0x001c }
        r0.put(r3, r1);	 Catch:{ Exception -> 0x001c }
        goto L_0x0013;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper put(java.lang.String r4, com.xunlei.tdlive.modal.JsonWrapper r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.put(java.lang.String, com.xunlei.tdlive.modal.JsonWrapper):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r3.mJsonObj;
        if (r0 == 0) goto L_0x0019;
    L_0x0004:
        r0 = r5.mJsonArr;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        r0 = r5.mJsonObj;
        if (r0 == 0) goto L_0x0019;
    L_0x000c:
        r1 = r3.mJsonObj;	 Catch:{ JSONException -> 0x001d }
        r0 = r5.isArray();	 Catch:{ JSONException -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r5.mJsonArr;	 Catch:{ JSONException -> 0x001d }
    L_0x0016:
        r1.put(r4, r0);	 Catch:{ JSONException -> 0x001d }
    L_0x0019:
        return r3;
    L_0x001a:
        r0 = r5.mJsonObj;	 Catch:{ JSONException -> 0x001d }
        goto L_0x0016;
    L_0x001d:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = "key: ";
        r1.<init>(r2);
        r1 = r1.append(r4);
        r2 = ", ";
        r1 = r1.append(r2);
        r0 = r0.toString();
        r1.append(r0);
        goto L_0x0019;
        */
    }

    public JsonWrapper putString(String str, String str2) {
        if (this.mJsonObj != null) {
            try {
                this.mJsonObj.put(str, str2);
            } catch (JSONException e) {
            }
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper putString(int r2, java.lang.String r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.putString(int, java.lang.String):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r1.mJsonArr;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        if (r2 >= 0) goto L_0x000c;
    L_0x0006:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r3);	 Catch:{ JSONException -> 0x0012 }
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x0012 }
        goto L_0x000b;
    L_0x0012:
        r0 = move-exception;
        goto L_0x000b;
        */
    }

    public JsonWrapper putInt(String str, int i) {
        if (this.mJsonObj != null) {
            try {
                this.mJsonObj.put(str, i);
            } catch (JSONException e) {
            }
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper putInt(int r2, int r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.putInt(int, int):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r1.mJsonArr;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        if (r2 >= 0) goto L_0x000c;
    L_0x0006:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r3);	 Catch:{ JSONException -> 0x0012 }
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x0012 }
        goto L_0x000b;
    L_0x0012:
        r0 = move-exception;
        goto L_0x000b;
        */
    }

    public JsonWrapper putLong(String str, long j) {
        if (this.mJsonObj != null) {
            try {
                this.mJsonObj.put(str, j);
            } catch (JSONException e) {
            }
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper putLong(int r3, long r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.putLong(int, long):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r2.mJsonArr;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        if (r3 >= 0) goto L_0x000c;
    L_0x0006:
        r0 = r2.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r4);	 Catch:{ JSONException -> 0x0012 }
    L_0x000b:
        return r2;
    L_0x000c:
        r0 = r2.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r3, r4);	 Catch:{ JSONException -> 0x0012 }
        goto L_0x000b;
    L_0x0012:
        r0 = move-exception;
        goto L_0x000b;
        */
    }

    public JsonWrapper putObject(String str, Object obj) {
        if (this.mJsonObj != null) {
            try {
                this.mJsonObj.put(str, obj);
            } catch (JSONException e) {
            }
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xunlei.tdlive.modal.JsonWrapper putObject(int r2, java.lang.Object r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.modal.JsonWrapper.putObject(int, java.lang.Object):com.xunlei.tdlive.modal.JsonWrapper");
        /*
        this = this;
        r0 = r1.mJsonArr;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        if (r2 >= 0) goto L_0x000c;
    L_0x0006:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r3);	 Catch:{ JSONException -> 0x0012 }
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = r1.mJsonArr;	 Catch:{ JSONException -> 0x0012 }
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x0012 }
        goto L_0x000b;
    L_0x0012:
        r0 = move-exception;
        goto L_0x000b;
        */
    }

    public Iterator<String> keys() {
        return this.mJsonObj != null ? this.mJsonObj.keys() : null;
    }

    public <T> T toObject(Class<T> cls) {
        return new Gson().fromJson(toString(), (Class) cls);
    }
}
