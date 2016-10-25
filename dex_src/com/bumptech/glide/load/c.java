package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.j;
import java.util.Arrays;
import java.util.Collection;

// compiled from: MultiTransformation.java
public final class c<T> implements f<T> {
    private final Collection<? extends f<T>> a;
    private String b;

    @SafeVarargs
    public c(f<T>... fVarArr) {
        if (fVarArr.length <= 0) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.a = Arrays.asList(fVarArr);
    }

    public final j<T> a(j<T> jVar, int i, int i2) {
        j<T> jVar2 = jVar;
        for (f fVar : this.a) {
            j<T> a = fVar.a(jVar2, i, i2);
            if (jVar2 != null && !jVar2.equals(jVar) && !jVar2.equals(a)) {
                jVar2.c();
            }
            jVar2 = a;
        }
        return jVar2;
    }

    public final String a() {
        if (this.b == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (f fVar : this.a) {
                stringBuilder.append(fVar.a());
            }
            this.b = stringBuilder.toString();
        }
        return this.b;
    }
}
