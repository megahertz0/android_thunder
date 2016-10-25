package com.baidu.mobads.interfaces.download;

import android.graphics.Bitmap;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;

public interface IXAdStaticImgDownloader extends IOAdDownloader {
    Bitmap getBitmap();
}
