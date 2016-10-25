package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import java.io.InputStream;

// compiled from: ImageVideoModelLoader.java
public final class h<A> implements m<A, i> {
    private final m<A, InputStream> a;
    private final m<A, ParcelFileDescriptor> b;

    // compiled from: ImageVideoModelLoader.java
    static class a implements c<i> {
        private final c<InputStream> a;
        private final c<ParcelFileDescriptor> b;

        public final /* synthetic */ Object a(Priority priority) throws Exception {
            return b(priority);
        }

        public a(c<InputStream> cVar, c<ParcelFileDescriptor> cVar2) {
            this.a = cVar;
            this.b = cVar2;
        }

        private i b(Priority priority) throws Exception {
            InputStream inputStream;
            ParcelFileDescriptor parcelFileDescriptor = null;
            if (this.a != null) {
                try {
                    inputStream = (InputStream) this.a.a(priority);
                } catch (Exception e) {
                    if (this.b == null) {
                        throw e;
                    }
                }
                if (this.b != null) {
                    try {
                        parcelFileDescriptor = (ParcelFileDescriptor) this.b.a(priority);
                    } catch (Exception e2) {
                        if (inputStream == null) {
                            throw e2;
                        }
                    }
                }
                return new i(inputStream, parcelFileDescriptor);
            }
            inputStream = null;
            if (this.b != null) {
                parcelFileDescriptor = (ParcelFileDescriptor) this.b.a(priority);
            }
            return new i(inputStream, parcelFileDescriptor);
        }

        public final void a() {
            if (this.a != null) {
                this.a.a();
            }
            if (this.b != null) {
                this.b.a();
            }
        }

        public final String b() {
            return this.a != null ? this.a.b() : this.b.b();
        }

        public final void c() {
            if (this.a != null) {
                this.a.c();
            }
            if (this.b != null) {
                this.b.c();
            }
        }
    }

    public h(m<A, InputStream> mVar, m<A, ParcelFileDescriptor> mVar2) {
        if (mVar == null && mVar2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.a = mVar;
        this.b = mVar2;
    }

    public final c<i> a(A a, int i, int i2) {
        c a2;
        c a3;
        if (this.a != null) {
            a2 = this.a.a(a, i, i2);
        } else {
            a2 = null;
        }
        if (this.b != null) {
            a3 = this.b.a(a, i, i2);
        } else {
            a3 = null;
        }
        return (a2 == null && a3 == null) ? null : new a(a2, a3);
    }
}
