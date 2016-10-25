package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.utils.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class UMImage$BinaryConvertor extends UMImage$ConfiguredConvertor {
    private UMImage$ConvertConfig a;
    private byte[] b;

    public UMImage$BinaryConvertor(byte[] bArr) {
        this.a = new UMImage$ConvertConfig();
        this.b = bArr;
    }

    public File asFile() {
        try {
            return a(this.b, this.a.generateCacheFile(getFileName()));
        } catch (IOException e) {
            Log.e(new StringBuilder("Sorry cannot setImage..[").append(e.toString()).append("]").toString());
            return null;
        }
    }

    public String asUrl() {
        return null;
    }

    public byte[] asBinary() {
        return this.b;
    }

    public Bitmap asBitmap() {
        return this.b != null ? BitmapFactory.decodeByteArray(this.b, 0, this.b.length) : null;
    }

    public String getFileName() {
        return AesHelper.md5(String.valueOf(System.currentTimeMillis()));
    }

    private File a(byte[] bArr, File file) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bufferedOutputStream2.write(bArr);
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e) {
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                return file;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedOutputStream2 = null;
            try {
                Exception e4;
                e4.printStackTrace();
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th2;
            }
            return file;
        } catch (Throwable th3) {
            th2 = th3;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e6) {
                }
            }
            throw th2;
        }
        return file;
    }
}
