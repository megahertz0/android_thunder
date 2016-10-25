package u.aly;

import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TField.java
public final class br {
    public final String a;
    public final byte b;
    public final short c;

    public br() {
        this(BuildConfig.VERSION_NAME, (byte) 0, (short) 0);
    }

    public br(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public final String toString() {
        return new StringBuilder("<TField name:'").append(this.a).append("' type:").append(this.b).append(" field-id:").append(this.c).append(">").toString();
    }
}
