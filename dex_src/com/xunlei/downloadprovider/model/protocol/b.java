package com.xunlei.downloadprovider.model.protocol;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.xunlei.xllib.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: ApkIconUtil.java
public final class b {
    private static Object a;

    static {
        a = new Object();
    }

    public static void a(String str, Drawable drawable) throws IOException {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        File file = new File(str.substring(0, str.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle)));
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(str + ".utmp");
        OutputStream fileOutputStream = new FileOutputStream(str);
        createBitmap.compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        File file2 = new File(str);
        synchronized (a) {
            file.renameTo(file2);
        }
    }
}
