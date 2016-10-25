package org.apache.thrift;

public class a {
    private short[] a;
    private int b;

    public a(int i) {
        this.b = -1;
        this.a = new short[i];
    }

    private void c() {
        Object obj = new Object[(this.a.length * 2)];
        System.arraycopy(this.a, 0, obj, 0, this.a.length);
        this.a = obj;
    }

    public short a() {
        short[] sArr = this.a;
        int i = this.b;
        this.b = i - 1;
        return sArr[i];
    }

    public void a(short s) {
        if (this.a.length == this.b + 1) {
            c();
        }
        short[] sArr = this.a;
        int i = this.b + 1;
        this.b = i;
        sArr[i] = s;
    }

    public void b() {
        this.b = -1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.a.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.b) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.a[i]);
            if (i == this.b) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
