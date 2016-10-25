package com.xunlei.tdlive.aniengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;
import com.xunlei.tdlive.aniengine.a.a;
import java.io.IOException;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ResourceLoader.java
public class x implements a {
    private Context a;

    public x(Context context) {
        this.a = context.getApplicationContext();
    }

    public float a(float f) {
        return TypedValue.applyDimension(SimpleLog.LOG_LEVEL_DEBUG, f, this.a.getResources().getDisplayMetrics());
    }

    public float b(float f) {
        return ((float) this.a.getResources().getDisplayMetrics().widthPixels) / f;
    }

    public String[] a(String str) {
        try {
            return this.a.getResources().getAssets().list(str);
        } catch (IOException e) {
            return null;
        }
    }

    public Bitmap b(String str) {
        return (str.startsWith("http://") || str.startsWith("file://") || str.startsWith("assets://")) ? com.xunlei.tdlive.util.a.a(this.a).c(str) : com.xunlei.tdlive.util.a.a(this.a).c(new StringBuilder("assets://").append(str).toString());
    }
}
