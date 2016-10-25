package u.aly;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

// compiled from: UError.java
public final class i extends av implements dc {
    public i() {
        this.a = System.currentTimeMillis();
        b();
        this.c = aw.a;
    }

    public i(String str) {
        this();
        this.b = str;
    }

    public i(Throwable th) {
        this();
        this.b = a(th);
    }

    public final i a() {
        this.c = aw.b;
        return this;
    }

    private static String a(Throwable th) {
        if (th == null) {
            return null;
        }
        try {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            String toString = stringWriter.toString();
            printWriter.close();
            stringWriter.close();
            return toString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a(bp bpVar, String str) {
        if (bpVar.b() > 0) {
            for (be beVar : bpVar.f) {
                if (str.equals(beVar.a)) {
                    break;
                }
            }
        }
        be beVar2 = null;
        if (beVar2 == null) {
            beVar2 = new be();
            beVar2.a = str;
            bpVar.a(beVar2);
        }
        if (beVar2.b == null) {
            beVar2.b = new ArrayList();
        }
        beVar2.b.add(this);
    }
}
