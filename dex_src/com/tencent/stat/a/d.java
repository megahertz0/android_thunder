package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONObject;

public class d extends e {
    private String a;
    private int l;
    private int m;

    public d(Context context, int i, int i2, Throwable th) {
        super(context, i);
        this.m = 100;
        if (th != null) {
            Throwable th2 = new Throwable(th);
            try {
                StackTraceElement[] stackTrace = th2.getStackTrace();
                if (stackTrace != null && stackTrace.length > this.m) {
                    StackTraceElement[] stackTraceElementArr = new StackTraceElement[this.m];
                    for (int i3 = 0; i3 < this.m; i3++) {
                        stackTraceElementArr[i3] = stackTrace[i3];
                    }
                    th2.setStackTrace(stackTraceElementArr);
                }
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th2.printStackTrace(printWriter);
            this.a = stringWriter.toString();
            this.l = i2;
            printWriter.close();
        }
    }

    public d(Context context, int i, String str, int i2, int i3) {
        super(context, i);
        this.m = 100;
        if (str != null) {
            if (i3 <= 0) {
                i3 = StatConfig.getMaxReportEventLength();
            }
            if (str.length() <= i3) {
                this.a = str;
            } else {
                this.a = str.substring(0, i3);
            }
        }
        this.l = i2;
    }

    public f a() {
        return f.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "er", this.a);
        jSONObject.put("ea", this.l);
        return true;
    }
}
