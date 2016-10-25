package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;

// compiled from: FileDescriptorBitmapDecoder.java
public final class k implements d<ParcelFileDescriptor, Bitmap> {
    private final u a;
    private final c b;
    private DecodeFormat c;

    public final /* synthetic */ j a(Object obj, int i, int i2) throws IOException {
        Bitmap frameAtTime;
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) obj;
        u uVar = this.a;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        if (uVar.a >= 0) {
            frameAtTime = mediaMetadataRetriever.getFrameAtTime((long) uVar.a);
        } else {
            frameAtTime = mediaMetadataRetriever.getFrameAtTime();
        }
        mediaMetadataRetriever.release();
        parcelFileDescriptor.close();
        return c.a(frameAtTime, this.b);
    }

    public k(c cVar, DecodeFormat decodeFormat) {
        this(new u(), cVar, decodeFormat);
    }

    private k(u uVar, c cVar, DecodeFormat decodeFormat) {
        this.a = uVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public final String a() {
        return "FileDescriptorBitmapDecoder.com.bumptech.glide.load.data.bitmap";
    }
}
