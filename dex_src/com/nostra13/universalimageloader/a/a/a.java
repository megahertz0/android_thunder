package com.nostra13.universalimageloader.a.a;

import android.graphics.Bitmap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// compiled from: DiskCache.java
public interface a {
    File a(String str);

    void a();

    boolean a(String str, Bitmap bitmap) throws IOException;

    boolean a(String str, InputStream inputStream, com.nostra13.universalimageloader.b.b.a aVar) throws IOException;
}
