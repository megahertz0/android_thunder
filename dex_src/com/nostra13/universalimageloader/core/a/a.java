package com.nostra13.universalimageloader.core.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.c;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;

// compiled from: BaseImageDecoder.java
public final class a implements b {
    protected final boolean a;

    // compiled from: BaseImageDecoder.java
    protected static class a {
        public final int a;
        public final boolean b;

        protected a() {
            this.a = 0;
            this.b = false;
        }

        protected a(int i, boolean z) {
            this.a = i;
            this.b = z;
        }
    }

    // compiled from: BaseImageDecoder.java
    protected static class b {
        public final c a;
        public final a b;

        protected b(c cVar, a aVar) {
            this.a = cVar;
            this.b = aVar;
        }
    }

    public a(boolean z) {
        this.a = z;
    }

    public final Bitmap a(c cVar) throws IOException {
        InputStream b = b(cVar);
        if (b == null) {
            com.nostra13.universalimageloader.b.c.d("No stream for image [%s]", cVar.a);
            return null;
        }
        boolean z;
        int i;
        a a;
        b bVar;
        c cVar2;
        ImageScaleType imageScaleType;
        Bitmap decodeStream;
        int i2;
        boolean z2;
        Matrix matrix;
        ImageScaleType imageScaleType2;
        float b2;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(b, null, options);
        String str = cVar.b;
        if (cVar.h) {
            if ("image/jpeg".equalsIgnoreCase(options.outMimeType) && Scheme.ofUri(str) == Scheme.FILE) {
                z = true;
            } else {
                i = 0;
            }
            if (z) {
                a = a(str);
                bVar = new b(new c(options.outWidth, options.outHeight, a.a), a);
                b = a(b, cVar);
                cVar2 = bVar.a;
                imageScaleType = cVar.d;
                if (imageScaleType == ImageScaleType.NONE) {
                    i = 1;
                } else if (imageScaleType != ImageScaleType.NONE_SAFE) {
                    i = com.nostra13.universalimageloader.b.a.a(cVar2);
                } else {
                    c cVar3 = cVar.c;
                    if (imageScaleType != ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    i = com.nostra13.universalimageloader.b.a.a(cVar2, cVar3, cVar.e, z);
                }
                if (i > 1 && this.a) {
                    com.nostra13.universalimageloader.b.c.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", cVar2, new c(cVar2.a / i, cVar2.b / i), Integer.valueOf(i), cVar.a);
                }
                options = cVar.i;
                options.inSampleSize = i;
                decodeStream = BitmapFactory.decodeStream(b, null, options);
                com.nostra13.universalimageloader.b.b.a(b);
                if (decodeStream != null) {
                    com.nostra13.universalimageloader.b.c.d("Image can't be decoded [%s]", cVar.a);
                    return decodeStream;
                }
                Bitmap createBitmap;
                i2 = bVar.b.a;
                z2 = bVar.b.b;
                matrix = new Matrix();
                imageScaleType2 = cVar.d;
                if (imageScaleType2 == ImageScaleType.EXACTLY || imageScaleType2 == ImageScaleType.EXACTLY_STRETCHED) {
                    b2 = com.nostra13.universalimageloader.b.a.b(new c(decodeStream.getWidth(), decodeStream.getHeight(), i2), cVar.c, cVar.e, imageScaleType2 != ImageScaleType.EXACTLY_STRETCHED);
                    if (Float.compare(b2, 1.0f) != 0) {
                        matrix.setScale(b2, b2);
                        if (this.a) {
                            com.nostra13.universalimageloader.b.c.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", r7, new c((int) (((float) r7.a) * b2), (int) (((float) r7.b) * b2)), Float.valueOf(b2), cVar.a);
                        }
                    }
                }
                if (z2) {
                    matrix.postScale(-1.0f, 1.0f);
                    if (this.a) {
                        com.nostra13.universalimageloader.b.c.a("Flip image horizontally [%s]", cVar.a);
                    }
                }
                if (i2 != 0) {
                    matrix.postRotate((float) i2);
                    if (this.a) {
                        com.nostra13.universalimageloader.b.c.a("Rotate image on %1$d\u00b0 [%2$s]", Integer.valueOf(i2), cVar.a);
                    }
                }
                createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
                if (createBitmap != decodeStream) {
                    decodeStream.recycle();
                }
                return createBitmap;
            }
        }
        a = new a();
        bVar = new b(new c(options.outWidth, options.outHeight, a.a), a);
        b = a(b, cVar);
        cVar2 = bVar.a;
        imageScaleType = cVar.d;
        if (imageScaleType == ImageScaleType.NONE) {
            i = 1;
        } else if (imageScaleType != ImageScaleType.NONE_SAFE) {
            c cVar32 = cVar.c;
            if (imageScaleType != ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                z = false;
            } else {
                z = true;
            }
            i = com.nostra13.universalimageloader.b.a.a(cVar2, cVar32, cVar.e, z);
        } else {
            i = com.nostra13.universalimageloader.b.a.a(cVar2);
        }
        com.nostra13.universalimageloader.b.c.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", cVar2, new c(cVar2.a / i, cVar2.b / i), Integer.valueOf(i), cVar.a);
        options = cVar.i;
        options.inSampleSize = i;
        decodeStream = BitmapFactory.decodeStream(b, null, options);
        com.nostra13.universalimageloader.b.b.a(b);
        if (decodeStream != null) {
            i2 = bVar.b.a;
            z2 = bVar.b.b;
            matrix = new Matrix();
            imageScaleType2 = cVar.d;
            if (imageScaleType2 != ImageScaleType.EXACTLY_STRETCHED) {
            }
            b2 = com.nostra13.universalimageloader.b.a.b(new c(decodeStream.getWidth(), decodeStream.getHeight(), i2), cVar.c, cVar.e, imageScaleType2 != ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(b2, 1.0f) != 0) {
                matrix.setScale(b2, b2);
                if (this.a) {
                    com.nostra13.universalimageloader.b.c.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", r7, new c((int) (((float) r7.a) * b2), (int) (((float) r7.b) * b2)), Float.valueOf(b2), cVar.a);
                }
            }
            if (z2) {
                matrix.postScale(-1.0f, 1.0f);
                if (this.a) {
                    com.nostra13.universalimageloader.b.c.a("Flip image horizontally [%s]", cVar.a);
                }
            }
            if (i2 != 0) {
                matrix.postRotate((float) i2);
                if (this.a) {
                    com.nostra13.universalimageloader.b.c.a("Rotate image on %1$d\u00b0 [%2$s]", Integer.valueOf(i2), cVar.a);
                }
            }
            createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
            if (createBitmap != decodeStream) {
                decodeStream.recycle();
            }
            return createBitmap;
        }
        com.nostra13.universalimageloader.b.c.d("Image can't be decoded [%s]", cVar.a);
        return decodeStream;
    }

    private static a a(String str) {
        int i = 0;
        boolean z = true;
        try {
            switch (new ExifInterface(Scheme.FILE.crop(str)).getAttributeInt("Orientation", 1)) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return new a(i, z);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    z = false;
                    i = 180;
                    return new a(i, z);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    i = 180;
                    return new a(i, z);
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    i = 270;
                    return new a(i, z);
                case R.styleable.Toolbar_contentInsetEnd:
                    int i2 = 0;
                    i = R.styleable.AppCompatTheme_controlBackground;
                    return new a(i, z);
                case R.styleable.Toolbar_contentInsetLeft:
                    i = R.styleable.AppCompatTheme_controlBackground;
                    return new a(i, z);
                case XZBDevice.Wait:
                    z = false;
                    i = 270;
                    return new a(i, z);
            }
        } catch (IOException e) {
            com.nostra13.universalimageloader.b.c.c("Can't read EXIF tags from file [%s]", str);
        }
        z = false;
        return new a(i, z);
    }

    private static InputStream a(InputStream inputStream, c cVar) throws IOException {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException e) {
            }
        }
        com.nostra13.universalimageloader.b.b.a(inputStream);
        return b(cVar);
    }

    private static InputStream b(c cVar) throws IOException {
        return cVar.f.a(cVar.b, cVar.g);
    }
}
