package com.taobao.accs.utl;

import java.io.ByteArrayOutputStream;

// compiled from: Taobao
public class f extends ByteArrayOutputStream {
    public f(int i) {
        super(i);
    }

    public f a(byte b) {
        write(b);
        return this;
    }

    public f a(short s) {
        write(s >> 8);
        write(s);
        return this;
    }
}
