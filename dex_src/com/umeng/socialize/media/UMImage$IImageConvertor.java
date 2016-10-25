package com.umeng.socialize.media;

import android.graphics.Bitmap;
import java.io.File;

interface UMImage$IImageConvertor {
    byte[] asBinary();

    Bitmap asBitmap();

    File asFile();

    String asUrl();
}
