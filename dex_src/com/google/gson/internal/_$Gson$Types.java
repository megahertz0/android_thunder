package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

// compiled from: $Gson$Types.java
public final class _$Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY;

    // compiled from: $Gson$Types.java
    private static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = _$Gson$Types.canonicalize(type);
        }

        public final Type getGenericComponentType() {
            return this.componentType;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && _$Gson$Types.equals(this, (GenericArrayType) obj);
        }

        public final int hashCode() {
            return this.componentType.hashCode();
        }

        public final String toString() {
            return _$Gson$Types.typeToString(this.componentType) + "[]";
        }
    }

    // compiled from: $Gson$Types.java
    private static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                boolean z;
                Class cls = (Class) type2;
                int i2 = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
                if (type == null && i2 == 0) {
                    z = false;
                } else {
                    z = true;
                }
                _$Gson$Preconditions.checkArgument(z);
            }
            this.ownerType = type == null ? null : _$Gson$Types.canonicalize(type);
            this.rawType = _$Gson$Types.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            while (i < this.typeArguments.length) {
                _$Gson$Preconditions.checkNotNull(this.typeArguments[i]);
                _$Gson$Types.checkNotPrimitive(this.typeArguments[i]);
                this.typeArguments[i] = _$Gson$Types.canonicalize(this.typeArguments[i]);
                i++;
            }
        }

        public final Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public final Type getRawType() {
            return this.rawType;
        }

        public final Type getOwnerType() {
            return this.ownerType;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && _$Gson$Types.equals(this, (ParameterizedType) obj);
        }

        public final int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ _$Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder.append(_$Gson$Types.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append("<").append(_$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                stringBuilder.append(", ").append(_$Gson$Types.typeToString(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    // compiled from: $Gson$Types.java
    private static final class WildcardTypeImpl implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            boolean z;
            boolean z2 = true;
            _$Gson$Preconditions.checkArgument(typeArr2.length <= 1);
            if (typeArr.length == 1) {
                z = true;
            } else {
                z = false;
            }
            _$Gson$Preconditions.checkArgument(z);
            if (typeArr2.length == 1) {
                _$Gson$Preconditions.checkNotNull(typeArr2[0]);
                _$Gson$Types.checkNotPrimitive(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    z2 = false;
                }
                _$Gson$Preconditions.checkArgument(z2);
                this.lowerBound = _$Gson$Types.canonicalize(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            _$Gson$Preconditions.checkNotNull(typeArr[0]);
            _$Gson$Types.checkNotPrimitive(typeArr[0]);
            this.lowerBound = null;
            this.upperBound = _$Gson$Types.canonicalize(typeArr[0]);
        }

        public final Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public final Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return _$Gson$Types.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        public final boolean equals(Object obj) {
            return (obj instanceof WildcardType) && _$Gson$Types.equals(this, (WildcardType) obj);
        }

        public final int hashCode() {
            return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public final String toString() {
            if (this.lowerBound != null) {
                return new StringBuilder("? super ").append(_$Gson$Types.typeToString(this.lowerBound)).toString();
            }
            return this.upperBound == Object.class ? "?" : new StringBuilder("? extends ").append(_$Gson$Types.typeToString(this.upperBound)).toString();
        }
    }

    static {
        EMPTY_TYPE_ARRAY = new Type[0];
    }

    private _$Gson$Types() {
        throw new UnsupportedOperationException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    public static WildcardType subtypeOf(Type type) {
        return new WildcardTypeImpl(new Type[]{type}, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type});
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            GenericArrayTypeImpl genericArrayTypeImpl;
            Class cls = (Class) type;
            if (cls.isArray()) {
                genericArrayTypeImpl = new GenericArrayTypeImpl(canonicalize(cls.getComponentType()));
            } else {
                Class cls2 = cls;
            }
            return genericArrayTypeImpl;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Class<?> getRawType(Type type) {
        Object obj;
        Type type2 = type;
        while (!(obj instanceof Class)) {
            if (obj instanceof ParameterizedType) {
                type2 = ((ParameterizedType) obj).getRawType();
                _$Gson$Preconditions.checkArgument(type2 instanceof Class);
                return (Class) type2;
            } else if (obj instanceof GenericArrayType) {
                return Array.newInstance(getRawType(((GenericArrayType) obj).getGenericComponentType()), 0).getClass();
            } else {
                if (obj instanceof TypeVariable) {
                    return Object.class;
                }
                if (obj instanceof WildcardType) {
                    obj = ((WildcardType) obj).getUpperBounds()[0];
                } else {
                    throw new IllegalArgumentException(new StringBuilder("Expected a Class, ParameterizedType, or GenericArrayType, but <").append(obj).append("> is of type ").append(obj == null ? "null" : obj.getClass().getName()).toString());
                }
            }
        }
        return (Class) obj;
    }

    static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean equals(Type type, Type type2) {
        Type type3 = type2;
        Type type4 = type;
        Object genericComponentType;
        Object genericComponentType2;
        while (genericComponentType != genericComponentType2) {
            if (genericComponentType instanceof Class) {
                return genericComponentType.equals(genericComponentType2);
            }
            if (genericComponentType instanceof ParameterizedType) {
                if (!(genericComponentType2 instanceof ParameterizedType)) {
                    return false;
                }
                ParameterizedType parameterizedType = (ParameterizedType) genericComponentType;
                ParameterizedType parameterizedType2 = (ParameterizedType) genericComponentType2;
                return equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
            } else if (genericComponentType instanceof GenericArrayType) {
                if (!(genericComponentType2 instanceof GenericArrayType)) {
                    return false;
                }
                GenericArrayType genericArrayType = (GenericArrayType) genericComponentType2;
                genericComponentType = ((GenericArrayType) genericComponentType).getGenericComponentType();
                genericComponentType2 = genericArrayType.getGenericComponentType();
            } else if (genericComponentType instanceof WildcardType) {
                if (!(genericComponentType2 instanceof WildcardType)) {
                    return false;
                }
                WildcardType wildcardType = (WildcardType) genericComponentType;
                WildcardType wildcardType2 = (WildcardType) genericComponentType2;
                return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
            } else if (!(genericComponentType instanceof TypeVariable)) {
                return false;
            } else {
                if (!(genericComponentType2 instanceof TypeVariable)) {
                    return false;
                }
                TypeVariable typeVariable = (TypeVariable) genericComponentType;
                TypeVariable typeVariable2 = (TypeVariable) genericComponentType2;
                return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
            }
        }
        return true;
    }

    private static int hashCodeOrZero(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        Class<?> cls3 = cls;
        Type type2 = type;
        while (cls2 != cls3) {
            if (cls2.isInterface()) {
                Class[] interfaces = cls3.getInterfaces();
                int length = interfaces.length;
                for (int i = 0; i < length; i++) {
                    if (interfaces[i] == cls2) {
                        return cls3.getGenericInterfaces()[i];
                    }
                    if (cls2.isAssignableFrom(interfaces[i])) {
                        type = cls3.getGenericInterfaces()[i];
                        Class cls4 = interfaces[i];
                        type2 = type;
                        break;
                    }
                }
            }
            if (cls3.isInterface()) {
                return cls2;
            }
            while (cls4 != Object.class) {
                Class<?> superclass = cls4.getSuperclass();
                if (superclass == cls2) {
                    return cls4.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    type = cls4.getGenericSuperclass();
                    cls3 = superclass;
                    type2 = type;
                } else {
                    cls3 = superclass;
                }
            }
            return cls2;
        }
        return type2;
    }

    static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        _$Gson$Preconditions.checkArgument(cls2.isAssignableFrom(cls));
        return resolve(type, cls, getGenericSupertype(type, cls, cls2));
    }

    public static Type getArrayComponentType(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type getCollectionElementType(Type type, Class<?> cls) {
        Type supertype = getSupertype(type, cls, Collection.class);
        if (supertype instanceof WildcardType) {
            supertype = ((WildcardType) supertype).getUpperBounds()[0];
        }
        return supertype instanceof ParameterizedType ? ((ParameterizedType) supertype).getActualTypeArguments()[0] : Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type supertype = getSupertype(type, cls, Map.class);
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType) supertype).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        Type type3 = type2;
        while (type3 instanceof TypeVariable) {
            type3 = (TypeVariable) type3;
            type2 = resolveTypeVariable(type, cls, type3);
            if (type2 == type3) {
                return type2;
            }
            type3 = type2;
        }
        Type componentType;
        Type resolve;
        if ((type3 instanceof Class) && ((Class) type3).isArray()) {
            Class cls2 = (Class) type3;
            componentType = cls2.getComponentType();
            resolve = resolve(type, cls, componentType);
            return componentType != resolve ? arrayOf(resolve) : cls2;
        } else if (type3 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type3;
            componentType = genericArrayType.getGenericComponentType();
            resolve = resolve(type, cls, componentType);
            return componentType != resolve ? arrayOf(resolve) : genericArrayType;
        } else if (type3 instanceof ParameterizedType) {
            int i;
            ParameterizedType parameterizedType = (ParameterizedType) type3;
            componentType = parameterizedType.getOwnerType();
            Type resolve2 = resolve(type, cls, componentType);
            if (resolve2 != componentType) {
                i = 1;
            } else {
                i = 0;
            }
            actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            Type[] typeArr = actualTypeArguments;
            for (int i2 = 0; i2 < length; i2++) {
                Type resolve3 = resolve(type, cls, typeArr[i2]);
                if (resolve3 != typeArr[i2]) {
                    if (i == 0) {
                        typeArr = (Type[]) typeArr.clone();
                        i = 1;
                    }
                    typeArr[i2] = resolve3;
                }
            }
            return i != 0 ? newParameterizedTypeWithOwner(resolve2, parameterizedType.getRawType(), typeArr) : parameterizedType;
        } else if (!(type3 instanceof WildcardType)) {
            return type3;
        } else {
            WildcardType wildcardType = (WildcardType) type3;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            actualTypeArguments = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                resolve = resolve(type, cls, lowerBounds[0]);
                return resolve != lowerBounds[0] ? supertypeOf(resolve) : wildcardType;
            } else if (actualTypeArguments.length != 1) {
                return wildcardType;
            } else {
                componentType = resolve(type, cls, actualTypeArguments[0]);
                return componentType != actualTypeArguments[0] ? subtypeOf(componentType) : wildcardType;
            }
        }
    }

    static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    private static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    private static void checkNotPrimitive(Type type) {
        boolean z = ((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true;
        _$Gson$Preconditions.checkArgument(z);
    }
}
