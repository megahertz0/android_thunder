package u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import u.aly.cm.a;

// compiled from: ABTest.java
public final class m implements di {
    private static m h;
    boolean a;
    int b;
    int c;
    private int d;
    private float e;
    private float f;
    private Context g;

    static {
        h = null;
    }

    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (h == null) {
                a aVar = cm.a(context).c;
                h = new m(context, aVar.c(), aVar.a(0));
            }
            mVar = h;
        }
        return mVar;
    }

    private m(Context context, String str, int i) {
        this.a = false;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = null;
        this.g = context;
        b(str, i);
    }

    private static float a(String str, int i) {
        int i2 = i * 2;
        return str == null ? 0.0f : ((float) Integer.valueOf(str.substring(i2, i2 + 5), SpdyProtocol.CUSTOM).intValue()) / 1048576.0f;
    }

    private void b(String str, int i) {
        int[] iArr = null;
        float f = 0.0f;
        this.d = i;
        String a = z.a(this.g);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.a = false;
            return;
        }
        try {
            this.e = a(a, R.styleable.Toolbar_titleMargins);
            this.f = a(a, SimpleLog.LOG_LEVEL_FATAL);
            int i2;
            int i3;
            if (str.startsWith("SIG7")) {
                if (str != null) {
                    float floatValue;
                    String[] split = str.split("\\|");
                    if (split[2].equals("SIG13")) {
                        floatValue = Float.valueOf(split[3]).floatValue();
                    } else {
                        floatValue = 0.0f;
                    }
                    if (this.e <= floatValue) {
                        float[] fArr;
                        if (split[0].equals("SIG7")) {
                            String[] split2 = split[1].split(",");
                            float[] fArr2 = new float[split2.length];
                            for (int i4 = 0; i4 < split2.length; i4++) {
                                fArr2[i4] = Float.valueOf(split2[i4]).floatValue();
                            }
                            fArr = fArr2;
                        } else {
                            int[] iArr2 = null;
                        }
                        if (split[4].equals("RPT")) {
                            split = split[5].split(",");
                            iArr = new int[split.length];
                            for (i2 = 0; i2 < split.length; i2++) {
                                iArr[i2] = Integer.valueOf(split[i2]).intValue();
                            }
                        }
                        floatValue = 0.0f;
                        i3 = 0;
                        while (i3 < fArr.length) {
                            floatValue += fArr[i3];
                            if (this.f < floatValue) {
                                break;
                            }
                            i3++;
                        }
                        i3 = -1;
                        if (i3 != -1) {
                            this.a = true;
                            this.c = i3 + 1;
                            this.b = iArr[i3];
                            return;
                        }
                    }
                    this.a = false;
                }
            } else if (str.startsWith("FIXED") && str != null) {
                String[] split3 = str.split("\\|");
                if (split3[2].equals("SIG13")) {
                    f = Float.valueOf(split3[3]).floatValue();
                }
                if (this.e > f) {
                    this.a = false;
                    return;
                }
                if (split3[0].equals("FIXED")) {
                    i2 = Integer.valueOf(split3[1]).intValue();
                } else {
                    i2 = -1;
                }
                if (split3[4].equals("RPT")) {
                    split3 = split3[5].split(",");
                    iArr = new int[split3.length];
                    for (i3 = 0; i3 < split3.length; i3++) {
                        iArr[i3] = Integer.valueOf(split3[i3]).intValue();
                    }
                }
                if (i2 != -1) {
                    this.a = true;
                    this.c = i2;
                    this.b = iArr[i2 - 1];
                    return;
                }
                this.a = false;
            }
        } catch (Throwable e) {
            this.a = false;
            v.b(new StringBuilder("v:").append(str).toString(), e);
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(",").length;
        int parseInt = Integer.parseInt(split[1]);
        return length >= parseInt && parseInt > 0;
    }

    public final void a(a aVar) {
        b(aVar.c(), aVar.a(0));
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" p13:");
        stringBuilder.append(this.e);
        stringBuilder.append(" p07:");
        stringBuilder.append(this.f);
        stringBuilder.append(" policy:");
        stringBuilder.append(this.b);
        stringBuilder.append(" interval:");
        stringBuilder.append(this.d);
        return stringBuilder.toString();
    }
}
