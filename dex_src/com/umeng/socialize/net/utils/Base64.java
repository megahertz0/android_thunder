package com.umeng.socialize.net.utils;

import org.apache.commons.logging.impl.SimpleLog;

public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    static final byte[] CHUNK_SEPARATOR;
    private static final byte[] DECODE_TABLE;
    private static final int MASK_6BITS = 63;
    private static final byte[] STANDARD_ENCODE_TABLE;
    private static final byte[] URL_SAFE_ENCODE_TABLE;
    private int mBitWorkArea;
    private final int mDecodeSize;
    private final byte[] mDecodeTable;
    private final int mEncodeSize;
    private final byte[] mEncodeTable;
    private final byte[] mLineSeparator;

    static {
        CHUNK_SEPARATOR = new byte[]{(byte) 13, (byte) 10};
        STANDARD_ENCODE_TABLE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        URL_SAFE_ENCODE_TABLE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        DECODE_TABLE = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) 62, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51};
    }

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        int i2;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = bArr.length;
        }
        super(3, 4, i, i2);
        this.mDecodeTable = DECODE_TABLE;
        if (bArr == null) {
            this.mEncodeSize = 4;
            this.mLineSeparator = null;
        } else if (containsAlphabetOrPad(bArr)) {
            throw new IllegalArgumentException(new StringBuilder("lineSeparator must not contain base64 characters: [").append(AesHelper.newStringUtf8(bArr)).append("]").toString());
        } else if (i > 0) {
            this.mEncodeSize = bArr.length + 4;
            this.mLineSeparator = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mLineSeparator, 0, bArr.length);
        } else {
            this.mEncodeSize = 4;
            this.mLineSeparator = null;
        }
        this.mDecodeSize = this.mEncodeSize - 1;
        this.mEncodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    void encode(byte[] bArr, int i, int i2) {
        if (!this.mEof) {
            int i3;
            int i4;
            if (i2 < 0) {
                this.mEof = true;
                if (this.mModulus != 0 || this.mLineLength != 0) {
                    ensureBufferSize(this.mEncodeSize);
                    i3 = this.mPos;
                    byte[] bArr2;
                    switch (this.mModulus) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            bArr2 = this.mBuffer;
                            i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea >> 2) & 63];
                            bArr2 = this.mBuffer;
                            i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea << 4) & 63];
                            if (this.mEncodeTable == STANDARD_ENCODE_TABLE) {
                                bArr2 = this.mBuffer;
                                i4 = this.mPos;
                                this.mPos = i4 + 1;
                                bArr2[i4] = (byte) 61;
                                bArr2 = this.mBuffer;
                                i4 = this.mPos;
                                this.mPos = i4 + 1;
                                bArr2[i4] = (byte) 61;
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            bArr2 = this.mBuffer;
                            i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea >> 10) & 63];
                            bArr2 = this.mBuffer;
                            i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea >> 4) & 63];
                            bArr2 = this.mBuffer;
                            i4 = this.mPos;
                            this.mPos = i4 + 1;
                            bArr2[i4] = this.mEncodeTable[(this.mBitWorkArea << 2) & 63];
                            if (this.mEncodeTable == STANDARD_ENCODE_TABLE) {
                                bArr2 = this.mBuffer;
                                i4 = this.mPos;
                                this.mPos = i4 + 1;
                                bArr2[i4] = (byte) 61;
                            }
                            break;
                    }
                    this.mCurrentLinePos = (this.mPos - i3) + this.mCurrentLinePos;
                    if (this.mLineLength > 0 && this.mCurrentLinePos > 0) {
                        System.arraycopy(this.mLineSeparator, 0, this.mBuffer, this.mPos, this.mLineSeparator.length);
                        this.mPos += this.mLineSeparator.length;
                        return;
                    }
                    return;
                }
                return;
            }
            int i5 = 0;
            while (i5 < i2) {
                ensureBufferSize(this.mEncodeSize);
                this.mModulus = (this.mModulus + 1) % 3;
                i4 = i + 1;
                i3 = bArr[i];
                if (i3 < 0) {
                    i3 += 256;
                }
                this.mBitWorkArea = i3 + (this.mBitWorkArea << 8);
                if (this.mModulus == 0) {
                    byte[] bArr3 = this.mBuffer;
                    int i6 = this.mPos;
                    this.mPos = i6 + 1;
                    bArr3[i6] = this.mEncodeTable[(this.mBitWorkArea >> 18) & 63];
                    bArr3 = this.mBuffer;
                    i6 = this.mPos;
                    this.mPos = i6 + 1;
                    bArr3[i6] = this.mEncodeTable[(this.mBitWorkArea >> 12) & 63];
                    bArr3 = this.mBuffer;
                    i6 = this.mPos;
                    this.mPos = i6 + 1;
                    bArr3[i6] = this.mEncodeTable[(this.mBitWorkArea >> 6) & 63];
                    bArr3 = this.mBuffer;
                    i6 = this.mPos;
                    this.mPos = i6 + 1;
                    bArr3[i6] = this.mEncodeTable[this.mBitWorkArea & 63];
                    this.mCurrentLinePos += 4;
                    if (this.mLineLength > 0 && this.mLineLength <= this.mCurrentLinePos) {
                        System.arraycopy(this.mLineSeparator, 0, this.mBuffer, this.mPos, this.mLineSeparator.length);
                        this.mPos += this.mLineSeparator.length;
                        this.mCurrentLinePos = 0;
                    }
                }
                i5++;
                i = i4;
            }
        }
    }

    void decode(byte[] bArr, int i, int i2) {
        if (!this.mEof) {
            int i3;
            if (i2 < 0) {
                this.mEof = true;
            }
            int i4 = 0;
            while (i4 < i2) {
                ensureBufferSize(this.mDecodeSize);
                i3 = i + 1;
                byte b = bArr[i];
                if (b == 61) {
                    this.mEof = true;
                    break;
                }
                if (b >= null && b < DECODE_TABLE.length) {
                    b = DECODE_TABLE[b];
                    if (b >= null) {
                        this.mModulus = (this.mModulus + 1) % 4;
                        this.mBitWorkArea = b + (this.mBitWorkArea << 6);
                        if (this.mModulus == 0) {
                            byte[] bArr2 = this.mBuffer;
                            int i5 = this.mPos;
                            this.mPos = i5 + 1;
                            bArr2[i5] = (byte) ((this.mBitWorkArea >> 16) & 255);
                            bArr2 = this.mBuffer;
                            i5 = this.mPos;
                            this.mPos = i5 + 1;
                            bArr2[i5] = (byte) ((this.mBitWorkArea >> 8) & 255);
                            bArr2 = this.mBuffer;
                            i5 = this.mPos;
                            this.mPos = i5 + 1;
                            bArr2[i5] = (byte) (this.mBitWorkArea & 255);
                        }
                    }
                }
                i4++;
                i = i3;
            }
            if (this.mEof && this.mModulus != 0) {
                ensureBufferSize(this.mDecodeSize);
                byte[] bArr3;
                switch (this.mModulus) {
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        this.mBitWorkArea >>= 4;
                        bArr3 = this.mBuffer;
                        i3 = this.mPos;
                        this.mPos = i3 + 1;
                        bArr3[i3] = (byte) (this.mBitWorkArea & 255);
                    case BYTES_PER_UNENCODED_BLOCK:
                        this.mBitWorkArea >>= 2;
                        bArr3 = this.mBuffer;
                        i3 = this.mPos;
                        this.mPos = i3 + 1;
                        bArr3[i3] = (byte) ((this.mBitWorkArea >> 8) & 255);
                        bArr3 = this.mBuffer;
                        i3 = this.mPos;
                        this.mPos = i3 + 1;
                        bArr3[i3] = (byte) (this.mBitWorkArea & 255);
                    default:
                        break;
                }
            }
        }
    }

    public static String encodeBase64String(byte[] bArr) {
        return AesHelper.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= ((long) i)) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException(new StringBuilder("Input array too big, the output array would be bigger (").append(encodedLength).append(") than the specified maximum size of ").append(i).toString());
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    protected boolean isInAlphabet(byte b) {
        return b >= null && b < this.mDecodeTable.length && this.mDecodeTable[b] != -1;
    }
}
