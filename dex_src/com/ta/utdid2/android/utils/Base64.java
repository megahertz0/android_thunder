package com.ta.utdid2.android.utils;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    static class Decoder extends Coder {
        private static final int[] DECODE;
        private static final int[] DECODE_WEBSAFE;
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        static {
            int[] iArr = new int[256];
            iArr[0] = -1;
            iArr[1] = -1;
            iArr[2] = -1;
            iArr[3] = -1;
            iArr[4] = -1;
            iArr[5] = -1;
            iArr[6] = -1;
            iArr[7] = -1;
            iArr[8] = -1;
            iArr[9] = -1;
            iArr[10] = -1;
            iArr[11] = -1;
            iArr[12] = -1;
            iArr[13] = -1;
            iArr[14] = -1;
            iArr[15] = -1;
            iArr[16] = -1;
            iArr[17] = -1;
            iArr[18] = -1;
            iArr[19] = -1;
            iArr[20] = -1;
            iArr[21] = -1;
            iArr[22] = -1;
            iArr[23] = -1;
            iArr[24] = -1;
            iArr[25] = -1;
            iArr[26] = -1;
            iArr[27] = -1;
            iArr[28] = -1;
            iArr[29] = -1;
            iArr[30] = -1;
            iArr[31] = -1;
            iArr[32] = -1;
            iArr[33] = -1;
            iArr[34] = -1;
            iArr[35] = -1;
            iArr[36] = -1;
            iArr[37] = -1;
            iArr[38] = -1;
            iArr[39] = -1;
            iArr[40] = -1;
            iArr[41] = -1;
            iArr[42] = -1;
            iArr[43] = 62;
            iArr[44] = -1;
            iArr[45] = -1;
            iArr[46] = -1;
            iArr[47] = 63;
            iArr[48] = 52;
            iArr[49] = 53;
            iArr[50] = 54;
            iArr[51] = 55;
            iArr[52] = 56;
            iArr[53] = 57;
            iArr[54] = 58;
            iArr[55] = 59;
            iArr[56] = 60;
            iArr[57] = 61;
            iArr[58] = -1;
            iArr[59] = -1;
            iArr[60] = -1;
            iArr[61] = -2;
            iArr[62] = -1;
            iArr[63] = -1;
            iArr[64] = -1;
            iArr[66] = 1;
            iArr[67] = 2;
            iArr[68] = 3;
            iArr[69] = 4;
            iArr[70] = 5;
            iArr[71] = 6;
            iArr[72] = 7;
            iArr[73] = 8;
            iArr[74] = 9;
            iArr[75] = 10;
            iArr[76] = 11;
            iArr[77] = 12;
            iArr[78] = 13;
            iArr[79] = 14;
            iArr[80] = 15;
            iArr[81] = 16;
            iArr[82] = 17;
            iArr[83] = 18;
            iArr[84] = 19;
            iArr[85] = 20;
            iArr[86] = 21;
            iArr[87] = 22;
            iArr[88] = 23;
            iArr[89] = 24;
            iArr[90] = 25;
            iArr[91] = -1;
            iArr[92] = -1;
            iArr[93] = -1;
            iArr[94] = -1;
            iArr[95] = -1;
            iArr[96] = -1;
            iArr[97] = 26;
            iArr[98] = 27;
            iArr[99] = 28;
            iArr[100] = 29;
            iArr[101] = 30;
            iArr[102] = 31;
            iArr[103] = 32;
            iArr[104] = 33;
            iArr[105] = 34;
            iArr[106] = 35;
            iArr[107] = 36;
            iArr[108] = 37;
            iArr[109] = 38;
            iArr[110] = 39;
            iArr[111] = 40;
            iArr[112] = 41;
            iArr[113] = 42;
            iArr[114] = 43;
            iArr[115] = 44;
            iArr[116] = 45;
            iArr[117] = 46;
            iArr[118] = 47;
            iArr[119] = 48;
            iArr[120] = 49;
            iArr[121] = 50;
            iArr[122] = 51;
            iArr[123] = -1;
            iArr[124] = -1;
            iArr[125] = -1;
            iArr[126] = -1;
            iArr[127] = -1;
            iArr[128] = -1;
            iArr[129] = -1;
            iArr[130] = -1;
            iArr[131] = -1;
            iArr[132] = -1;
            iArr[133] = -1;
            iArr[134] = -1;
            iArr[135] = -1;
            iArr[136] = -1;
            iArr[137] = -1;
            iArr[138] = -1;
            iArr[139] = -1;
            iArr[140] = -1;
            iArr[141] = -1;
            iArr[142] = -1;
            iArr[143] = -1;
            iArr[144] = -1;
            iArr[145] = -1;
            iArr[146] = -1;
            iArr[147] = -1;
            iArr[148] = -1;
            iArr[149] = -1;
            iArr[150] = -1;
            iArr[151] = -1;
            iArr[152] = -1;
            iArr[153] = -1;
            iArr[154] = -1;
            iArr[155] = -1;
            iArr[156] = -1;
            iArr[157] = -1;
            iArr[158] = -1;
            iArr[159] = -1;
            iArr[160] = -1;
            iArr[161] = -1;
            iArr[162] = -1;
            iArr[163] = -1;
            iArr[164] = -1;
            iArr[165] = -1;
            iArr[166] = -1;
            iArr[167] = -1;
            iArr[168] = -1;
            iArr[169] = -1;
            iArr[170] = -1;
            iArr[171] = -1;
            iArr[172] = -1;
            iArr[173] = -1;
            iArr[174] = -1;
            iArr[175] = -1;
            iArr[176] = -1;
            iArr[177] = -1;
            iArr[178] = -1;
            iArr[179] = -1;
            iArr[180] = -1;
            iArr[181] = -1;
            iArr[182] = -1;
            iArr[183] = -1;
            iArr[184] = -1;
            iArr[185] = -1;
            iArr[186] = -1;
            iArr[187] = -1;
            iArr[188] = -1;
            iArr[189] = -1;
            iArr[190] = -1;
            iArr[191] = -1;
            iArr[192] = -1;
            iArr[193] = -1;
            iArr[194] = -1;
            iArr[195] = -1;
            iArr[196] = -1;
            iArr[197] = -1;
            iArr[198] = -1;
            iArr[199] = -1;
            iArr[200] = -1;
            iArr[201] = -1;
            iArr[202] = -1;
            iArr[203] = -1;
            iArr[204] = -1;
            iArr[205] = -1;
            iArr[206] = -1;
            iArr[207] = -1;
            iArr[208] = -1;
            iArr[209] = -1;
            iArr[210] = -1;
            iArr[211] = -1;
            iArr[212] = -1;
            iArr[213] = -1;
            iArr[214] = -1;
            iArr[215] = -1;
            iArr[216] = -1;
            iArr[217] = -1;
            iArr[218] = -1;
            iArr[219] = -1;
            iArr[220] = -1;
            iArr[221] = -1;
            iArr[222] = -1;
            iArr[223] = -1;
            iArr[224] = -1;
            iArr[225] = -1;
            iArr[226] = -1;
            iArr[227] = -1;
            iArr[228] = -1;
            iArr[229] = -1;
            iArr[230] = -1;
            iArr[231] = -1;
            iArr[232] = -1;
            iArr[233] = -1;
            iArr[234] = -1;
            iArr[235] = -1;
            iArr[236] = -1;
            iArr[237] = -1;
            iArr[238] = -1;
            iArr[239] = -1;
            iArr[240] = -1;
            iArr[241] = -1;
            iArr[242] = -1;
            iArr[243] = -1;
            iArr[244] = -1;
            iArr[245] = -1;
            iArr[246] = -1;
            iArr[247] = -1;
            iArr[248] = -1;
            iArr[249] = -1;
            iArr[250] = -1;
            iArr[251] = -1;
            iArr[252] = -1;
            iArr[253] = -1;
            iArr[254] = -1;
            iArr[255] = -1;
            DECODE = iArr;
            iArr = new int[256];
            iArr[0] = -1;
            iArr[1] = -1;
            iArr[2] = -1;
            iArr[3] = -1;
            iArr[4] = -1;
            iArr[5] = -1;
            iArr[6] = -1;
            iArr[7] = -1;
            iArr[8] = -1;
            iArr[9] = -1;
            iArr[10] = -1;
            iArr[11] = -1;
            iArr[12] = -1;
            iArr[13] = -1;
            iArr[14] = -1;
            iArr[15] = -1;
            iArr[16] = -1;
            iArr[17] = -1;
            iArr[18] = -1;
            iArr[19] = -1;
            iArr[20] = -1;
            iArr[21] = -1;
            iArr[22] = -1;
            iArr[23] = -1;
            iArr[24] = -1;
            iArr[25] = -1;
            iArr[26] = -1;
            iArr[27] = -1;
            iArr[28] = -1;
            iArr[29] = -1;
            iArr[30] = -1;
            iArr[31] = -1;
            iArr[32] = -1;
            iArr[33] = -1;
            iArr[34] = -1;
            iArr[35] = -1;
            iArr[36] = -1;
            iArr[37] = -1;
            iArr[38] = -1;
            iArr[39] = -1;
            iArr[40] = -1;
            iArr[41] = -1;
            iArr[42] = -1;
            iArr[43] = -1;
            iArr[44] = -1;
            iArr[45] = 62;
            iArr[46] = -1;
            iArr[47] = -1;
            iArr[48] = 52;
            iArr[49] = 53;
            iArr[50] = 54;
            iArr[51] = 55;
            iArr[52] = 56;
            iArr[53] = 57;
            iArr[54] = 58;
            iArr[55] = 59;
            iArr[56] = 60;
            iArr[57] = 61;
            iArr[58] = -1;
            iArr[59] = -1;
            iArr[60] = -1;
            iArr[61] = -2;
            iArr[62] = -1;
            iArr[63] = -1;
            iArr[64] = -1;
            iArr[66] = 1;
            iArr[67] = 2;
            iArr[68] = 3;
            iArr[69] = 4;
            iArr[70] = 5;
            iArr[71] = 6;
            iArr[72] = 7;
            iArr[73] = 8;
            iArr[74] = 9;
            iArr[75] = 10;
            iArr[76] = 11;
            iArr[77] = 12;
            iArr[78] = 13;
            iArr[79] = 14;
            iArr[80] = 15;
            iArr[81] = 16;
            iArr[82] = 17;
            iArr[83] = 18;
            iArr[84] = 19;
            iArr[85] = 20;
            iArr[86] = 21;
            iArr[87] = 22;
            iArr[88] = 23;
            iArr[89] = 24;
            iArr[90] = 25;
            iArr[91] = -1;
            iArr[92] = -1;
            iArr[93] = -1;
            iArr[94] = -1;
            iArr[95] = 63;
            iArr[96] = -1;
            iArr[97] = 26;
            iArr[98] = 27;
            iArr[99] = 28;
            iArr[100] = 29;
            iArr[101] = 30;
            iArr[102] = 31;
            iArr[103] = 32;
            iArr[104] = 33;
            iArr[105] = 34;
            iArr[106] = 35;
            iArr[107] = 36;
            iArr[108] = 37;
            iArr[109] = 38;
            iArr[110] = 39;
            iArr[111] = 40;
            iArr[112] = 41;
            iArr[113] = 42;
            iArr[114] = 43;
            iArr[115] = 44;
            iArr[116] = 45;
            iArr[117] = 46;
            iArr[118] = 47;
            iArr[119] = 48;
            iArr[120] = 49;
            iArr[121] = 50;
            iArr[122] = 51;
            iArr[123] = -1;
            iArr[124] = -1;
            iArr[125] = -1;
            iArr[126] = -1;
            iArr[127] = -1;
            iArr[128] = -1;
            iArr[129] = -1;
            iArr[130] = -1;
            iArr[131] = -1;
            iArr[132] = -1;
            iArr[133] = -1;
            iArr[134] = -1;
            iArr[135] = -1;
            iArr[136] = -1;
            iArr[137] = -1;
            iArr[138] = -1;
            iArr[139] = -1;
            iArr[140] = -1;
            iArr[141] = -1;
            iArr[142] = -1;
            iArr[143] = -1;
            iArr[144] = -1;
            iArr[145] = -1;
            iArr[146] = -1;
            iArr[147] = -1;
            iArr[148] = -1;
            iArr[149] = -1;
            iArr[150] = -1;
            iArr[151] = -1;
            iArr[152] = -1;
            iArr[153] = -1;
            iArr[154] = -1;
            iArr[155] = -1;
            iArr[156] = -1;
            iArr[157] = -1;
            iArr[158] = -1;
            iArr[159] = -1;
            iArr[160] = -1;
            iArr[161] = -1;
            iArr[162] = -1;
            iArr[163] = -1;
            iArr[164] = -1;
            iArr[165] = -1;
            iArr[166] = -1;
            iArr[167] = -1;
            iArr[168] = -1;
            iArr[169] = -1;
            iArr[170] = -1;
            iArr[171] = -1;
            iArr[172] = -1;
            iArr[173] = -1;
            iArr[174] = -1;
            iArr[175] = -1;
            iArr[176] = -1;
            iArr[177] = -1;
            iArr[178] = -1;
            iArr[179] = -1;
            iArr[180] = -1;
            iArr[181] = -1;
            iArr[182] = -1;
            iArr[183] = -1;
            iArr[184] = -1;
            iArr[185] = -1;
            iArr[186] = -1;
            iArr[187] = -1;
            iArr[188] = -1;
            iArr[189] = -1;
            iArr[190] = -1;
            iArr[191] = -1;
            iArr[192] = -1;
            iArr[193] = -1;
            iArr[194] = -1;
            iArr[195] = -1;
            iArr[196] = -1;
            iArr[197] = -1;
            iArr[198] = -1;
            iArr[199] = -1;
            iArr[200] = -1;
            iArr[201] = -1;
            iArr[202] = -1;
            iArr[203] = -1;
            iArr[204] = -1;
            iArr[205] = -1;
            iArr[206] = -1;
            iArr[207] = -1;
            iArr[208] = -1;
            iArr[209] = -1;
            iArr[210] = -1;
            iArr[211] = -1;
            iArr[212] = -1;
            iArr[213] = -1;
            iArr[214] = -1;
            iArr[215] = -1;
            iArr[216] = -1;
            iArr[217] = -1;
            iArr[218] = -1;
            iArr[219] = -1;
            iArr[220] = -1;
            iArr[221] = -1;
            iArr[222] = -1;
            iArr[223] = -1;
            iArr[224] = -1;
            iArr[225] = -1;
            iArr[226] = -1;
            iArr[227] = -1;
            iArr[228] = -1;
            iArr[229] = -1;
            iArr[230] = -1;
            iArr[231] = -1;
            iArr[232] = -1;
            iArr[233] = -1;
            iArr[234] = -1;
            iArr[235] = -1;
            iArr[236] = -1;
            iArr[237] = -1;
            iArr[238] = -1;
            iArr[239] = -1;
            iArr[240] = -1;
            iArr[241] = -1;
            iArr[242] = -1;
            iArr[243] = -1;
            iArr[244] = -1;
            iArr[245] = -1;
            iArr[246] = -1;
            iArr[247] = -1;
            iArr[248] = -1;
            iArr[249] = -1;
            iArr[250] = -1;
            iArr[251] = -1;
            iArr[252] = -1;
            iArr[253] = -1;
            iArr[254] = -1;
            iArr[255] = -1;
            DECODE_WEBSAFE = iArr;
        }

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            if (this.state == 6) {
                return $assertionsDisabled;
            }
            int i3 = i2 + i;
            int i4 = this.state;
            int i5 = this.value;
            int i6 = DEFAULT;
            byte[] bArr2 = this.output;
            int[] iArr = this.alphabet;
            int i7 = i4;
            i4 = i;
            while (i4 < i3) {
                if (i7 == 0) {
                    while (i4 + 4 <= i3) {
                        i5 = (((iArr[bArr[i4] & 255] << 18) | (iArr[bArr[i4 + 1] & 255] << 12)) | (iArr[bArr[i4 + 2] & 255] << 6)) | iArr[bArr[i4 + 3] & 255];
                        if (i5 >= 0) {
                            bArr2[i6 + 2] = (byte) i5;
                            bArr2[i6 + 1] = (byte) (i5 >> 8);
                            bArr2[i6] = (byte) (i5 >> 16);
                            i6 += 3;
                            i4 += 4;
                        } else if (i4 >= i3) {
                            i4 = i5;
                            if (z) {
                                switch (i7) {
                                    case NO_PADDING:
                                        this.state = 6;
                                        return $assertionsDisabled;
                                    case NO_WRAP:
                                        i5 = i6 + 1;
                                        bArr2[i6] = (byte) (i4 >> 4);
                                        i6 = i5;
                                        this.state = i7;
                                        this.op = i6;
                                        return true;
                                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                                        i5 = i6 + 1;
                                        bArr2[i6] = (byte) (i4 >> 10);
                                        i6 = i5 + 1;
                                        bArr2[i5] = (byte) (i4 >> 2);
                                        this.state = i7;
                                        this.op = i6;
                                        return true;
                                    case CRLF:
                                        this.state = 6;
                                        return $assertionsDisabled;
                                    default:
                                        this.state = i7;
                                        this.op = i6;
                                        return true;
                                }
                            }
                            this.state = i7;
                            this.value = i4;
                            this.op = i6;
                            return true;
                        }
                    }
                    if (i4 >= i3) {
                        i4 = i5;
                        if (z) {
                            switch (i7) {
                                case NO_PADDING:
                                    this.state = 6;
                                    return $assertionsDisabled;
                                case NO_WRAP:
                                    i5 = i6 + 1;
                                    bArr2[i6] = (byte) (i4 >> 4);
                                    i6 = i5;
                                    this.state = i7;
                                    this.op = i6;
                                    return true;
                                case XZBDevice.DOWNLOAD_LIST_FAILED:
                                    i5 = i6 + 1;
                                    bArr2[i6] = (byte) (i4 >> 10);
                                    i6 = i5 + 1;
                                    bArr2[i5] = (byte) (i4 >> 2);
                                    this.state = i7;
                                    this.op = i6;
                                    return true;
                                case CRLF:
                                    this.state = 6;
                                    return $assertionsDisabled;
                                default:
                                    this.state = i7;
                                    this.op = i6;
                                    return true;
                            }
                        }
                        this.state = i7;
                        this.value = i4;
                        this.op = i6;
                        return true;
                    }
                }
                i = i4 + 1;
                i4 = iArr[bArr[i4] & 255];
                switch (i7) {
                    case DEFAULT:
                        if (i4 >= 0) {
                            i7++;
                            i5 = i4;
                            i4 = i;
                        } else {
                            if (i4 != -1) {
                                this.state = 6;
                                return $assertionsDisabled;
                            }
                            i4 = i;
                        }
                        break;
                    case NO_PADDING:
                        if (i4 >= 0) {
                            i5 = (i5 << 6) | i4;
                            i7++;
                            i4 = i;
                        } else {
                            if (i4 != -1) {
                                this.state = 6;
                                return $assertionsDisabled;
                            }
                            i4 = i;
                        }
                        break;
                    case NO_WRAP:
                        if (i4 >= 0) {
                            i5 = (i5 << 6) | i4;
                            i7++;
                            i4 = i;
                        } else if (i4 == -2) {
                            i4 = i6 + 1;
                            bArr2[i6] = (byte) (i5 >> 4);
                            i7 = 4;
                            i6 = i4;
                            i4 = i;
                        } else {
                            if (i4 != -1) {
                                this.state = 6;
                                return $assertionsDisabled;
                            }
                            i4 = i;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        if (i4 >= 0) {
                            i5 = (i5 << 6) | i4;
                            bArr2[i6 + 2] = (byte) i5;
                            bArr2[i6 + 1] = (byte) (i5 >> 8);
                            bArr2[i6] = (byte) (i5 >> 16);
                            i6 += 3;
                            i7 = 0;
                            i4 = i;
                        } else if (i4 == -2) {
                            bArr2[i6 + 1] = (byte) (i5 >> 2);
                            bArr2[i6] = (byte) (i5 >> 10);
                            i6 += 2;
                            i7 = 5;
                            i4 = i;
                        } else {
                            if (i4 != -1) {
                                this.state = 6;
                                return $assertionsDisabled;
                            }
                            i4 = i;
                        }
                        break;
                    case CRLF:
                        if (i4 == -2) {
                            i7++;
                            i4 = i;
                        } else {
                            if (i4 != -1) {
                                this.state = 6;
                                return $assertionsDisabled;
                            }
                            i4 = i;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (i4 != -1) {
                            this.state = 6;
                            return $assertionsDisabled;
                        }
                        i4 = i;
                        break;
                    default:
                        i4 = i;
                        break;
                }
            }
            i4 = i5;
            if (z) {
                this.state = i7;
                this.value = i4;
                this.op = i6;
                return true;
            }
            switch (i7) {
                case NO_PADDING:
                    this.state = 6;
                    return $assertionsDisabled;
                case NO_WRAP:
                    i5 = i6 + 1;
                    bArr2[i6] = (byte) (i4 >> 4);
                    i6 = i5;
                    this.state = i7;
                    this.op = i6;
                    return true;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    i5 = i6 + 1;
                    bArr2[i6] = (byte) (i4 >> 10);
                    i6 = i5 + 1;
                    bArr2[i5] = (byte) (i4 >> 2);
                    this.state = i7;
                    this.op = i6;
                    return true;
                case CRLF:
                    this.state = 6;
                    return $assertionsDisabled;
                default:
                    this.state = i7;
                    this.op = i6;
                    return true;
            }
        }
    }

    static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled;
        private static final byte[] ENCODE;
        private static final byte[] ENCODE_WEBSAFE;
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            $assertionsDisabled = !Base64.class.desiredAssertionStatus() ? true : $assertionsDisabled;
            ENCODE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
            ENCODE_WEBSAFE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        }

        public Encoder(int i, byte[] bArr) {
            boolean z;
            boolean z2 = true;
            this.output = bArr;
            this.do_padding = (i & 1) == 0;
            if ((i & 2) == 0) {
                z = true;
            } else {
                z = false;
            }
            this.do_newline = z;
            if ((i & 4) == 0) {
                z2 = false;
            }
            this.do_cr = z2;
            this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.do_newline ? LINE_GROUPS : -1;
        }

        public int maxOutputSize(int i) {
            return ((i * 8) / 5) + 10;
        }

        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            byte[] bArr2 = this.alphabet;
            byte[] bArr3 = this.output;
            int i7 = DEFAULT;
            int i8 = this.count;
            int i9 = i2 + i;
            switch (this.tailLen) {
                case DEFAULT:
                    i3 = -1;
                    i4 = i;
                    break;
                case NO_PADDING:
                    if (i + 2 <= i9) {
                        i4 = i + 1;
                        i = i4 + 1;
                        i5 = (((this.tail[0] & 255) << 16) | ((bArr[i] & 255) << 8)) | (bArr[i4] & 255);
                        this.tailLen = 0;
                        i3 = i5;
                        i4 = i;
                    }
                    i3 = -1;
                    i4 = i;
                    break;
                case NO_WRAP:
                    if (i + 1 <= i9) {
                        i4 = i + 1;
                        i5 = (((this.tail[0] & 255) << 16) | ((this.tail[1] & 255) << 8)) | (bArr[i] & 255);
                        this.tailLen = 0;
                        i3 = i5;
                    }
                    i3 = -1;
                    i4 = i;
                    break;
                default:
                    i3 = -1;
                    i4 = i;
                    break;
            }
            if (i3 != -1) {
                bArr3[0] = bArr2[(i3 >> 18) & 63];
                bArr3[1] = bArr2[(i3 >> 12) & 63];
                bArr3[2] = bArr2[(i3 >> 6) & 63];
                i5 = CRLF;
                bArr3[3] = bArr2[i3 & 63];
                i8--;
                if (i8 == 0) {
                    if (this.do_cr) {
                        i5 = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                        bArr3[4] = (byte) 13;
                    }
                    i7 = i5 + 1;
                    bArr3[i5] = (byte) 10;
                    i6 = 19;
                } else {
                    i6 = i8;
                    i7 = 4;
                }
            } else {
                i6 = i8;
            }
            while (i4 + 3 <= i9) {
                i5 = (((bArr[i4] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8)) | (bArr[i4 + 2] & 255);
                bArr3[i7] = bArr2[(i5 >> 18) & 63];
                bArr3[i7 + 1] = bArr2[(i5 >> 12) & 63];
                bArr3[i7 + 2] = bArr2[(i5 >> 6) & 63];
                bArr3[i7 + 3] = bArr2[i5 & 63];
                i8 = i4 + 3;
                i4 = i7 + 4;
                i5 = i6 - 1;
                if (i5 == 0) {
                    if (this.do_cr) {
                        i5 = i4 + 1;
                        bArr3[i4] = (byte) 13;
                    } else {
                        i5 = i4;
                    }
                    i7 = i5 + 1;
                    bArr3[i5] = (byte) 10;
                    i4 = i8;
                    i6 = 19;
                } else {
                    i6 = i5;
                    i7 = i4;
                    i4 = i8;
                }
            }
            byte[] bArr4;
            if (z) {
                if (i4 - this.tailLen == i9 - 1) {
                    if (this.tailLen > 0) {
                        bArr4 = this.tail;
                        i8 = NO_PADDING;
                        i5 = bArr4[0];
                    } else {
                        i8 = i4 + 1;
                        i5 = bArr[i4];
                        i4 = i8;
                        i8 = 0;
                    }
                    i3 = (i5 & 255) << 4;
                    this.tailLen -= i8;
                    i8 = i7 + 1;
                    bArr3[i7] = bArr2[(i3 >> 6) & 63];
                    i5 = i8 + 1;
                    bArr3[i8] = bArr2[i3 & 63];
                    if (this.do_padding) {
                        i8 = i5 + 1;
                        bArr3[i5] = (byte) 61;
                        i5 = i8 + 1;
                        bArr3[i8] = (byte) 61;
                    }
                    if (this.do_newline) {
                        if (this.do_cr) {
                            i8 = i5 + 1;
                            bArr3[i5] = (byte) 13;
                            i5 = i8;
                        }
                        i7 = i5 + 1;
                        bArr3[i5] = (byte) 10;
                    }
                    i7 = i5;
                } else if (i4 - this.tailLen == i9 - 2) {
                    if (this.tailLen > 1) {
                        bArr4 = this.tail;
                        i8 = NO_PADDING;
                        i5 = bArr4[0];
                    } else {
                        i8 = i4 + 1;
                        i5 = bArr[i4];
                        i4 = i8;
                        i8 = 0;
                    }
                    int i10 = (i5 & 255) << 10;
                    if (this.tailLen > 0) {
                        i3 = i8 + 1;
                        i5 = this.tail[i8];
                        i8 = i3;
                    } else {
                        i3 = i4 + 1;
                        i5 = bArr[i4];
                        i4 = i3;
                    }
                    i5 = ((i5 & 255) << 2) | i10;
                    this.tailLen -= i8;
                    i8 = i7 + 1;
                    bArr3[i7] = bArr2[(i5 >> 12) & 63];
                    i3 = i8 + 1;
                    bArr3[i8] = bArr2[(i5 >> 6) & 63];
                    i8 = i3 + 1;
                    bArr3[i3] = bArr2[i5 & 63];
                    if (this.do_padding) {
                        i5 = i8 + 1;
                        bArr3[i8] = (byte) 61;
                    } else {
                        i5 = i8;
                    }
                    if (this.do_newline) {
                        if (this.do_cr) {
                            i8 = i5 + 1;
                            bArr3[i5] = (byte) 13;
                            i5 = i8;
                        }
                        i7 = i5 + 1;
                        bArr3[i5] = (byte) 10;
                    }
                    i7 = i5;
                } else if (this.do_newline && i7 > 0 && i6 != 19) {
                    if (this.do_cr) {
                        i5 = i7 + 1;
                        bArr3[i7] = (byte) 13;
                    } else {
                        i5 = i7;
                    }
                    i7 = i5 + 1;
                    bArr3[i5] = (byte) 10;
                }
                if (!$assertionsDisabled && this.tailLen != 0) {
                    throw new AssertionError();
                } else if (!($assertionsDisabled || r1 == i9)) {
                    throw new AssertionError();
                }
            } else if (i4 == i9 - 1) {
                bArr4 = this.tail;
                i8 = this.tailLen;
                this.tailLen = i8 + 1;
                bArr4[i8] = bArr[i4];
            } else if (i4 == i9 - 2) {
                bArr4 = this.tail;
                i8 = this.tailLen;
                this.tailLen = i8 + 1;
                bArr4[i8] = bArr[i4];
                bArr4 = this.tail;
                i8 = this.tailLen;
                this.tailLen = i8 + 1;
                bArr4[i8] = bArr[i4 + 1];
            }
            this.op = i7;
            this.count = i6;
            return true;
        }
    }

    static {
        $assertionsDisabled = !Base64.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, DEFAULT, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[((i2 * 3) / 4)]);
        if (!decoder.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            byte[] bArr2 = new byte[decoder.op];
            System.arraycopy(decoder.output, DEFAULT, bArr2, DEFAULT, decoder.op);
            return bArr2;
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, DEFAULT, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!encoder.do_padding) {
            switch (i2 % 3) {
                case DEFAULT:
                    break;
                case NO_PADDING:
                    i4 += 2;
                    break;
                case NO_WRAP:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (encoder.do_newline && i2 > 0) {
            i4 += (encoder.do_cr ? NO_WRAP : 1) * (((i2 - 1) / 57) + 1);
        }
        encoder.output = new byte[i4];
        encoder.process(bArr, i, i2, true);
        if ($assertionsDisabled || encoder.op == i4) {
            return encoder.output;
        }
        throw new AssertionError();
    }

    private Base64() {
    }
}
