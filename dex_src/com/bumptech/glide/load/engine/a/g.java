package com.bumptech.glide.load.engine.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

// compiled from: LruPoolStrategy.java
interface g {
    Bitmap a();

    Bitmap a(int i, int i2, Config config);

    void a(Bitmap bitmap);

    String b(int i, int i2, Config config);

    String b(Bitmap bitmap);

    int c(Bitmap bitmap);
}
