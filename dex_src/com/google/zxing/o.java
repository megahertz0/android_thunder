package com.google.zxing;

import com.google.zxing.common.a.a;

// compiled from: ResultPoint.java
public class o {
    public final float a;
    public final float b;

    public o(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return this.a == oVar.a && this.b == oVar.b;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append('(');
        stringBuilder.append(this.a);
        stringBuilder.append(',');
        stringBuilder.append(this.b);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void a(o[] oVarArr) {
        o oVar;
        o oVar2;
        o oVar3;
        float a = a(oVarArr[0], oVarArr[1]);
        float a2 = a(oVarArr[1], oVarArr[2]);
        float a3 = a(oVarArr[0], oVarArr[2]);
        if (a2 >= a && a2 >= a3) {
            oVar = oVarArr[0];
            oVar2 = oVarArr[1];
            oVar3 = oVarArr[2];
        } else if (a3 < a2 || a3 < a) {
            oVar = oVarArr[2];
            oVar2 = oVarArr[0];
            oVar3 = oVarArr[1];
        } else {
            oVar = oVarArr[1];
            oVar2 = oVarArr[0];
            oVar3 = oVarArr[2];
        }
        float f = oVar.a;
        float f2 = oVar.b;
        if (((oVar3.a - f) * (oVar2.b - f2)) - ((oVar2.a - f) * (oVar3.b - f2)) >= 0.0f) {
            o oVar4 = oVar3;
            oVar3 = oVar2;
            oVar2 = oVar4;
        }
        oVarArr[0] = oVar3;
        oVarArr[1] = oVar;
        oVarArr[2] = oVar2;
    }

    public static float a(o oVar, o oVar2) {
        return a.a(oVar.a, oVar.b, oVar2.a, oVar2.b);
    }
}
