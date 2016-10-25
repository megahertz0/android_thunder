package u.aly;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// compiled from: UTDIdTracker.java
public final class cq extends a {
    private static final Pattern d;
    private Context e;

    static {
        d = Pattern.compile("UTDID\">([^<]+)");
    }

    public cq(Context context) {
        super("utdid");
        this.e = context;
    }

    public final String b() {
        return c();
    }

    private String c() {
        File d = d();
        if (d == null || !d.exists()) {
            return null;
        }
        try {
            String group;
            InputStream fileInputStream = new FileInputStream(d);
            CharSequence a = u.a(fileInputStream);
            if (a != null) {
                Matcher matcher = d.matcher(a);
                if (matcher.find()) {
                    group = matcher.group(1);
                    u.c(fileInputStream);
                    return group;
                }
            }
            group = null;
            u.c(fileInputStream);
            return group;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File d() {
        File file = null;
        if (!t.a(this.e, "android.permission.WRITE_EXTERNAL_STORAGE") || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            file = new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            return file;
        } catch (Exception e) {
            return file;
        }
    }
}
