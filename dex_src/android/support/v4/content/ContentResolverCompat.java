package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.os.CancellationSignal;

public final class ContentResolverCompat {
    private static final ContentResolverCompatImpl IMPL;

    static interface ContentResolverCompatImpl {
        Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);
    }

    static class ContentResolverCompatImplBase implements ContentResolverCompatImpl {
        ContentResolverCompatImplBase() {
        }

        public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            return contentResolver.query(uri, strArr, str, strArr2, str2);
        }
    }

    static class ContentResolverCompatImplJB extends ContentResolverCompatImplBase {
        ContentResolverCompatImplJB() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.database.Cursor query(android.content.ContentResolver r8, android.net.Uri r9, java.lang.String[] r10, java.lang.String r11, java.lang.String[] r12, java.lang.String r13, android.support.v4.os.CancellationSignal r14) {
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.ContentResolverCompat.ContentResolverCompatImplJB.query(android.content.ContentResolver, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, android.support.v4.os.CancellationSignal):android.database.Cursor");
            /*
            this = this;
            if (r14 == 0) goto L_0x0011;
        L_0x0002:
            r6 = r14.getCancellationSignalObject();	 Catch:{ Exception -> 0x0013 }
        L_0x0006:
            r0 = r8;
            r1 = r9;
            r2 = r10;
            r3 = r11;
            r4 = r12;
            r5 = r13;
            r0 = android.support.v4.content.ContentResolverCompatJellybean.query(r0, r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0013 }
            return r0;
        L_0x0011:
            r6 = 0;
            goto L_0x0006;
        L_0x0013:
            r0 = move-exception;
            r1 = android.support.v4.content.ContentResolverCompatJellybean.isFrameworkOperationCanceledException(r0);
            if (r1 == 0) goto L_0x0020;
        L_0x001a:
            r0 = new android.support.v4.os.OperationCanceledException;
            r0.<init>();
            throw r0;
        L_0x0020:
            throw r0;
            */
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            IMPL = new ContentResolverCompatImplJB();
        } else {
            IMPL = new ContentResolverCompatImplBase();
        }
    }

    private ContentResolverCompat() {
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return IMPL.query(contentResolver, uri, strArr, str, strArr2, str2, cancellationSignal);
    }
}
