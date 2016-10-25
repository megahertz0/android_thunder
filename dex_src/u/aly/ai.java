package u.aly;

import java.io.Serializable;

// compiled from: FieldValueMetaData.java
public class ai implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public ai(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public ai(byte b) {
        this(b, false);
    }
}
