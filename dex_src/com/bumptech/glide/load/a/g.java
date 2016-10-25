package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.Priority;
import java.io.FileNotFoundException;
import java.io.IOException;

// compiled from: LocalUriFetcher.java
public abstract class g<T> implements c<T> {
    private final Uri a;
    private final Context b;
    private T c;

    protected abstract T a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    protected abstract void a(T t) throws IOException;

    public g(Context context, Uri uri) {
        this.b = context.getApplicationContext();
        this.a = uri;
    }

    public final T a(Priority priority) throws Exception {
        this.c = a(this.a, this.b.getContentResolver());
        return this.c;
    }

    public final void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (IOException e) {
            }
        }
    }

    public final void c() {
    }

    public final String b() {
        return this.a.toString();
    }
}
