package com.xunlei.common.httpclient.request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

// compiled from: SerializableCookie.java
public final class d implements Serializable {
    private static final long a = 6374381828722046732L;
    private final transient Cookie b;
    private transient BasicClientCookie c;

    public d(Cookie cookie) {
        this.b = cookie;
    }

    public final Cookie a() {
        return this.c != null ? this.c : this.b;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.b.getName());
        objectOutputStream.writeObject(this.b.getValue());
        objectOutputStream.writeObject(this.b.getComment());
        objectOutputStream.writeObject(this.b.getDomain());
        objectOutputStream.writeObject(this.b.getExpiryDate());
        objectOutputStream.writeObject(this.b.getPath());
        objectOutputStream.writeInt(this.b.getVersion());
        objectOutputStream.writeBoolean(this.b.isSecure());
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.c = new BasicClientCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.c.setComment((String) objectInputStream.readObject());
        this.c.setDomain((String) objectInputStream.readObject());
        this.c.setExpiryDate((Date) objectInputStream.readObject());
        this.c.setPath((String) objectInputStream.readObject());
        this.c.setVersion(objectInputStream.readInt());
        this.c.setSecure(objectInputStream.readBoolean());
    }
}
