package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import com.bumptech.glide.Priority;
import java.io.IOException;

// compiled from: AssetPathFetcher.java
public abstract class a<T> implements c<T> {
    private final String a;
    private final AssetManager b;
    private T c;

    protected abstract T a(AssetManager assetManager, String str) throws IOException;

    protected abstract void a(T t) throws IOException;

    public a(AssetManager assetManager, String str) {
        this.b = assetManager;
        this.a = str;
    }

    public final T a(Priority priority) throws Exception {
        this.c = a(this.b, this.a);
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

    public final String b() {
        return this.a;
    }

    public final void c() {
    }
}
