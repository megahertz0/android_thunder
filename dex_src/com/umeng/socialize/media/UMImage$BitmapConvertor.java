package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import com.xunlei.xllib.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class UMImage$BitmapConvertor extends UMImage$ConfiguredConvertor {
    private Bitmap a;

    public UMImage$BitmapConvertor(Bitmap bitmap) {
        this.a = bitmap;
    }

    public File asFile() {
        Exception e;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            File generateCacheFile = this.config.generateCacheFile(AesHelper.md5(this.a.toString()));
            OutputStream fileOutputStream = new FileOutputStream(generateCacheFile);
            try {
                int rowBytes = (this.a.getRowBytes() * this.a.getHeight()) / 1024;
                Log.d(new StringBuilder("### bitmap size = ").append(rowBytes).append(" KB").toString());
                int i = R.styleable.AppCompatTheme_buttonStyle;
                if (((float) rowBytes) > this.config.mImageSizeLimit) {
                    i = (int) ((this.config.mImageSizeLimit / ((float) rowBytes)) * 100.0f);
                }
                Log.d(new StringBuilder("### \u538b\u7f29\u8d28\u91cf : ").append(i).toString());
                if (!this.a.isRecycled()) {
                    this.a.compress(CompressFormat.JPEG, i, fileOutputStream);
                }
                Log.d(new StringBuilder("##save bitmap ").append(generateCacheFile.getAbsolutePath()).toString());
                Log.d(new StringBuilder("#### \u56fe\u7247\u5e8f\u5217\u5316\u8017\u65f6 : ").append(System.currentTimeMillis() - currentTimeMillis).append(" ms.").toString());
                try {
                    fileOutputStream.close();
                    return generateCacheFile;
                } catch (IOException e2) {
                    return generateCacheFile;
                }
            } catch (Exception e3) {
                e = e3;
                Log.e(new StringBuilder("Sorry cannot setImage..[").append(e.toString()).append("]").toString());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            try {
                Log.e(new StringBuilder("Sorry cannot setImage..[").append(e.toString()).append("]").toString());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                    }
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                }
            }
            throw th2;
        }
    }

    public String asUrl() {
        return null;
    }

    public byte[] asBinary() {
        return BitmapUtils.bitmap2Bytes(this.a);
    }

    public Bitmap asBitmap() {
        return this.a;
    }
}
