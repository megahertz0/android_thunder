package com.google.gson;

import com.google.gson.internal._$Gson$Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field) {
        _$Gson$Preconditions.checkNotNull(field);
        this.field = field;
    }

    public final Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public final String getName() {
        return this.field.getName();
    }

    public final Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public final Class<?> getDeclaredClass() {
        return this.field.getType();
    }

    public final <T extends Annotation> T getAnnotation(Class<T> cls) {
        return this.field.getAnnotation(cls);
    }

    public final Collection<Annotation> getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public final boolean hasModifier(int i) {
        return (this.field.getModifiers() & i) != 0;
    }

    final Object get(Object obj) throws IllegalAccessException {
        return this.field.get(obj);
    }

    final boolean isSynthetic() {
        return this.field.isSynthetic();
    }
}
