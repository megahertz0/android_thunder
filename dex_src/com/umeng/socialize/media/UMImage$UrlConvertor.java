package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import java.io.File;
import java.io.FileOutputStream;

class UMImage$UrlConvertor extends UMImage$ConfiguredConvertor {
    private String a;

    public UMImage$UrlConvertor(String str) {
        this.a = null;
        this.a = str;
    }

    public File asFile() {
        File generateCacheFile;
        try {
            generateCacheFile = this.config.generateCacheFile(AesHelper.md5(this.a));
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(generateCacheFile);
                fileOutputStream.write(asBinary());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                return generateCacheFile;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            generateCacheFile = null;
            e2 = exception;
            e2.printStackTrace();
            return generateCacheFile;
        }
        return generateCacheFile;
    }

    public String asUrl() {
        return this.a;
    }

    public byte[] asBinary() {
        return SocializeNetUtils.getNetData(this.a);
    }

    public Bitmap asBitmap() {
        byte[] asBinary = asBinary();
        return asBinary != null ? BitmapFactory.decodeByteArray(asBinary, 0, asBinary.length) : null;
    }
}
