package u.aly;

// compiled from: ShortStack.java
public final class x {
    int a;
    private short[] b;

    public x() {
        this.a = -1;
        this.b = new short[15];
    }

    public final short a() {
        short[] sArr = this.b;
        int i = this.a;
        this.a = i - 1;
        return sArr[i];
    }

    public final void a(short s) {
        if (this.b.length == this.a + 1) {
            Object obj = new Object[(this.b.length * 2)];
            System.arraycopy(this.b, 0, obj, 0, this.b.length);
            this.b = obj;
        }
        short[] sArr = this.b;
        int i = this.a + 1;
        this.a = i;
        sArr[i] = s;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.b.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.a) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.b[i]);
            if (i == this.a) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
