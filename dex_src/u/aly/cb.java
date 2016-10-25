package u.aly;

import java.util.BitSet;

// compiled from: TTupleProtocol.java
public final class cb extends an {
    public final Class<? extends cc> s() {
        return cf.class;
    }

    public final BitSet b(int i) throws ac {
        int i2 = 0;
        int ceil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i3 = 0; i3 < ceil; i3++) {
            bArr[i3] = k();
        }
        BitSet bitSet = new BitSet();
        while (i2 < bArr.length * 8) {
            if ((bArr[(bArr.length - (i2 / 8)) - 1] & (1 << (i2 % 8))) > 0) {
                bitSet.set(i2);
            }
            i2++;
        }
        return bitSet;
    }

    public final void a(BitSet bitSet, int i) throws ac {
        int i2;
        int i3 = 0;
        byte[] bArr = new byte[((int) Math.ceil(((double) i) / 8.0d))];
        for (i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        i2 = bArr.length;
        while (i3 < i2) {
            a(bArr[i3]);
            i3++;
        }
    }
}
