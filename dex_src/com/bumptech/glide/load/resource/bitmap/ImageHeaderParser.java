package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class ImageHeaderParser {
    static final byte[] a;
    private static final int[] c;
    final b b;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);
        private final boolean a;

        static {
            GIF = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType("GIF", 0, true);
            JPEG = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType("JPEG", 1, false);
            PNG_A = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType("PNG_A", 2, true);
            PNG = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType("PNG", 3, false);
            UNKNOWN = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType("UNKNOWN", 4, false);
            b = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType[]{GIF, JPEG, PNG_A, PNG, UNKNOWN};
        }

        private ImageType(boolean z) {
            this.a = z;
        }

        public final boolean hasAlpha() {
            return this.a;
        }
    }

    private static class a {
        final ByteBuffer a;

        public a(byte[] bArr) {
            this.a = ByteBuffer.wrap(bArr);
            this.a.order(ByteOrder.BIG_ENDIAN);
        }

        public final int a() {
            return this.a.array().length;
        }

        public final int a(int i) {
            return this.a.getInt(i);
        }

        public final short b(int i) {
            return this.a.getShort(i);
        }
    }

    private static class b {
        final InputStream a;

        public b(InputStream inputStream) {
            this.a = inputStream;
        }

        public final int a() throws IOException {
            return ((this.a.read() << 8) & 65280) | (this.a.read() & 255);
        }

        public final short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public final long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip <= 0) {
                    if (this.a.read() == -1) {
                        break;
                    }
                    j2--;
                } else {
                    j2 -= skip;
                }
            }
            return j - j2;
        }

        public final int a(byte[] bArr) throws IOException {
            int length = bArr.length;
            while (length > 0) {
                int read = this.a.read(bArr, bArr.length - length, length);
                if (read == -1) {
                    break;
                }
                length -= read;
            }
            return bArr.length - length;
        }
    }

    static {
        c = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
        byte[] bArr = new byte[0];
        try {
            bArr = "Exif\u0000\u0000".getBytes(GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
        }
        a = bArr;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.b = new b(inputStream);
    }

    public final ImageType a() throws IOException {
        int a = this.b.a();
        if (a == 65496) {
            return ImageType.JPEG;
        }
        a = ((a << 16) & -65536) | (this.b.a() & 65535);
        if (a != -1991225785) {
            return (a >> 8) == 4671814 ? ImageType.GIF : ImageType.UNKNOWN;
        } else {
            this.b.a(21);
            return this.b.a.read() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        }
    }

    static int a(a aVar) {
        ByteOrder byteOrder;
        short b = aVar.b(R.styleable.Toolbar_contentInsetEnd);
        if (b == (short) 19789 || b != (short) 18761) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        aVar.a.order(byteOrder);
        int a = aVar.a(XZBDevice.Stop) + 6;
        short b2 = aVar.b(a);
        for (int i = 0; b < b2; i = b + 1) {
            int i2 = (a + 2) + (b * 12);
            short b3 = aVar.b(i2);
            if (b3 == (short) 274) {
                short b4 = aVar.b(i2 + 2);
                if (b4 > (short) 0 && b4 <= (short) 12) {
                    int a2 = aVar.a(i2 + 4);
                    if (a2 >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                            new StringBuilder("Got tagIndex=").append(b).append(" tagType=").append(b3).append(" formatCode=").append(b4).append(" componentCount=").append(a2);
                        }
                        int i3 = c[b4] + a2;
                        if (i3 <= 4) {
                            i2 += 8;
                            if (i2 < 0 || i2 > aVar.a()) {
                                if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                                    new StringBuilder("Illegal tagValueOffset=").append(i2).append(" tagType=").append(b3);
                                }
                            } else if (i3 >= 0 && i2 + i3 <= aVar.a()) {
                                return aVar.b(i2);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                    }
                } else if (Log.isLoggable("ImageHeaderParser", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                }
            }
        }
        return -1;
    }

    static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }
}
