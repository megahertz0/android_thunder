package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.b;
import com.umeng.socialize.utils.OauthHelper;
import com.umeng.socialize.weixin.BuildConfig;
import java.io.File;
import java.nio.ByteBuffer;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONObject;

// compiled from: Envelope.java
public final class z {
    private final byte[] a;
    private final int b;
    private final int c;
    private String d;
    private String e;
    private byte[] f;
    private byte[] g;
    private byte[] h;
    private int i;
    private int j;
    private int k;
    private byte[] l;
    private byte[] m;
    private boolean n;

    private z(byte[] bArr, String str, byte[] bArr2) throws Exception {
        this.a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.b = 1;
        this.c = 0;
        this.d = BuildConfig.VERSION_NAME;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        this.n = false;
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = str;
        this.k = bArr.length;
        this.l = s.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static String a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
        return sharedPreferences == null ? null : sharedPreferences.getString("signature", null);
    }

    private void a(String str) {
        this.f = b.a(str);
    }

    public static z a(Context context, String str, byte[] bArr) {
        try {
            String k = t.k(context);
            String c = t.c(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
            String string = sharedPreferences.getString("signature", null);
            int i = sharedPreferences.getInt("serial", 1);
            z zVar = new z(bArr, str, (c + k).getBytes());
            zVar.a(string);
            zVar.i = i;
            zVar.b();
            sharedPreferences.edit().putInt("serial", i + 1).putString("signature", b.a(zVar.f)).commit();
            zVar.b(context);
            return zVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static z b(Context context, String str, byte[] bArr) {
        try {
            String k = t.k(context);
            String c = t.c(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_general_config", 0);
            String string = sharedPreferences.getString("signature", null);
            int i = sharedPreferences.getInt("serial", 1);
            z zVar = new z(bArr, str, (c + k).getBytes());
            zVar.n = true;
            zVar.a(string);
            zVar.i = i;
            zVar.b();
            sharedPreferences.edit().putInt("serial", i + 1).putString("signature", b.a(zVar.f)).commit();
            zVar.b(context);
            return zVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void b() {
        if (this.f == null) {
            this.f = a(this.a, (int) (System.currentTimeMillis() / 1000));
        }
        if (this.n) {
            Object obj = new Object[16];
            try {
                System.arraycopy(this.f, 1, obj, 0, SpdyProtocol.CUSTOM);
                this.l = b.a(this.l, obj);
            } catch (Exception e) {
            }
        }
        this.g = a(this.f, this.j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b.a(this.f));
        stringBuilder.append(this.i);
        stringBuilder.append(this.j);
        stringBuilder.append(this.k);
        stringBuilder.append(b.a(this.g));
        this.h = b.b(stringBuilder.toString().getBytes());
    }

    private byte[] a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] b = b.b(this.m);
        byte[] b2 = b.b(this.l);
        int length = b.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (i2 = 0; i2 < 2; i2++) {
            bArr2[i2] = bArr[i2];
            bArr2[(bArr2.length - i2) - 1] = bArr[(bArr.length - i2) - 1];
        }
        byte[] bArr3 = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        while (i3 < bArr2.length) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3 % 4]);
            i3++;
        }
        return bArr2;
    }

    public final byte[] a() {
        int i;
        y bqVar = new bq();
        bqVar.a = this.d;
        bqVar.b = this.e;
        bqVar.c = b.a(this.f);
        bqVar.d = this.i;
        bqVar.a();
        bqVar.e = this.j;
        bqVar.b();
        bqVar.f = this.k;
        bqVar.c();
        byte[] bArr = this.l;
        bqVar.g = bArr == null ? null : ByteBuffer.wrap(bArr);
        if (this.n) {
            i = 1;
        } else {
            i = 0;
        }
        bqVar.j = i;
        bqVar.e();
        bqVar.h = b.a(this.g);
        bqVar.i = b.a(this.h);
        try {
            return new ae().a(bqVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void b(Context context) {
        String str = this.e;
        String str2 = cm.a(context).c.d;
        String a = b.a(this.f);
        Object obj = new Object[16];
        System.arraycopy(this.f, SimpleLog.LOG_LEVEL_DEBUG, obj, 0, SpdyProtocol.CUSTOM);
        String a2 = b.a(b.b(obj));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(OauthHelper.APP_KEY, str);
            if (str2 != null) {
                jSONObject.put("umid", str2);
            }
            jSONObject.put("signature", a);
            jSONObject.put("checksum", a2);
            str = jSONObject.toString();
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            u.a(new File(file, "exchangeIdentity.json"), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.d}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.e}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{b.a(this.f)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.i)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.j)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.k)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{b.a(this.g)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{b.a(this.h)}));
        String str = "codex : %d";
        Object[] objArr = new Object[1];
        if (!this.n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }
}
