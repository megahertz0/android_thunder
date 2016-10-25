package com.nostra13.universalimageloader.core.download;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import anet.channel.util.HttpConstant;
import com.nostra13.universalimageloader.b.b;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.android.spdy.SpdyAgent;

// compiled from: BaseImageDownloader.java
public final class a implements ImageDownloader {
    protected final Context a;
    protected final int b;
    protected final int c;

    // compiled from: BaseImageDownloader.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Scheme.values().length];
            try {
                a[Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Scheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Scheme.FILE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Scheme.CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Scheme.ASSETS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[Scheme.DRAWABLE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[Scheme.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public a(Context context) {
        this(context, (byte) 0);
    }

    private a(Context context, byte b) {
        this.a = context.getApplicationContext();
        this.b = 5000;
        this.c = 20000;
    }

    public final InputStream a(String str, Object obj) throws IOException {
        boolean z = true;
        String crop;
        switch (AnonymousClass_1.a[Scheme.ofUri(str).ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return a(str);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                crop = Scheme.FILE.crop(str);
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
                if (mimeTypeFromExtension == null || !mimeTypeFromExtension.startsWith("video/")) {
                    z = false;
                }
                if (!z) {
                    return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(new FileInputStream(crop), 32768), (int) new File(crop).length());
                }
                if (VERSION.SDK_INT < 8) {
                    return null;
                }
                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(crop, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                if (createVideoThumbnail == null) {
                    return null;
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            case XZBDevice.DOWNLOAD_LIST_ALL:
                boolean z2;
                ContentResolver contentResolver = this.a.getContentResolver();
                Uri parse = Uri.parse(str);
                crop = this.a.getContentResolver().getType(parse);
                if (crop == null || !crop.startsWith("video/")) {
                    int i = 0;
                } else {
                    z2 = true;
                }
                if (z2) {
                    Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
                    if (thumbnail != null) {
                        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream2);
                        return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                    }
                } else if (str.startsWith("content://com.android.contacts/")) {
                    ContentResolver contentResolver2 = this.a.getContentResolver();
                    return VERSION.SDK_INT >= 14 ? Contacts.openContactPhotoInputStream(contentResolver2, parse, true) : Contacts.openContactPhotoInputStream(contentResolver2, parse);
                }
                return contentResolver.openInputStream(parse);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return this.a.getAssets().open(Scheme.ASSETS.crop(str));
            case R.styleable.Toolbar_contentInsetEnd:
                return this.a.getResources().openRawResource(Integer.parseInt(Scheme.DRAWABLE.crop(str)));
            default:
                throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
        }
    }

    private InputStream a(String str) throws IOException {
        Object obj = null;
        HttpURLConnection b = b(str);
        int i = 0;
        while (b.getResponseCode() / 100 == 3 && i < 5) {
            b = b(b.getHeaderField(HttpConstant.LOCATION));
            i++;
        }
        try {
            Object inputStream = b.getInputStream();
            if (b.getResponseCode() == 200) {
                obj = 1;
            }
            if (obj != null) {
                return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(inputStream, 32768), b.getContentLength());
            }
            b.a(inputStream);
            throw new IOException(new StringBuilder("Image request failed with response code ").append(b.getResponseCode()).toString());
        } catch (IOException e) {
            errorStream = b.getErrorStream();
            do {
                try {
                } catch (IOException e2) {
                    Closeable errorStream;
                    b.a(errorStream);
                }
            } while (errorStream.read(new byte[32768], 0, WXMediaMessage.THUMB_LENGTH_LIMIT) != -1);
            b.a(errorStream);
            throw e;
        }
    }

    private HttpURLConnection b(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        return httpURLConnection;
    }
}
