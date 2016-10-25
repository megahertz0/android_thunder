package com.umeng.socialize.net.utils;

import com.xunlei.xllib.R;
import org.android.agoo.common.a;
import org.android.spdy.SpdyProtocol;

public abstract class BaseNCodec {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD = (byte) 61;
    protected static final byte PAD_DEFAULT = (byte) 61;
    protected byte[] mBuffer;
    private final int mChunkSeparatorLength;
    protected int mCurrentLinePos;
    private final int mEncodedBlockSize;
    protected boolean mEof;
    protected final int mLineLength;
    protected int mModulus;
    protected int mPos;
    private int mReadPos;
    private final int mUnencodedBlockSize;

    abstract void decode(byte[] bArr, int i, int i2);

    abstract void encode(byte[] bArr, int i, int i2);

    protected abstract boolean isInAlphabet(byte b);

    protected BaseNCodec(int i, int i2, int i3, int i4) {
        this.mUnencodedBlockSize = i;
        this.mEncodedBlockSize = i2;
        int i5 = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.mLineLength = i5;
        this.mChunkSeparatorLength = i4;
    }

    boolean hasData() {
        return this.mBuffer != null;
    }

    int available() {
        return this.mBuffer != null ? this.mPos - this.mReadPos : 0;
    }

    protected int getDefaultBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

    private void resizeBuffer() {
        if (this.mBuffer == null) {
            this.mBuffer = new byte[getDefaultBufferSize()];
            this.mPos = 0;
            this.mReadPos = 0;
            return;
        }
        Object obj = new Object[(this.mBuffer.length * 2)];
        System.arraycopy(this.mBuffer, 0, obj, 0, this.mBuffer.length);
        this.mBuffer = obj;
    }

    protected void ensureBufferSize(int i) {
        if (this.mBuffer == null || this.mBuffer.length < this.mPos + i) {
            resizeBuffer();
        }
    }

    int readResults(byte[] bArr, int i, int i2) {
        if (this.mBuffer == null) {
            return this.mEof ? -1 : 0;
        } else {
            int min = Math.min(available(), i2);
            System.arraycopy(this.mBuffer, this.mReadPos, bArr, i, min);
            this.mReadPos += min;
            if (this.mReadPos < this.mPos) {
                return min;
            }
            this.mBuffer = null;
            return min;
        }
    }

    protected static boolean isWhiteSpace(byte b) {
        switch (b) {
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case R.styleable.Toolbar_titleMarginStart:
            case a.ORDERED:
                return true;
            default:
                return false;
        }
    }

    private void reset() {
        this.mBuffer = null;
        this.mPos = 0;
        this.mReadPos = 0;
        this.mCurrentLinePos = 0;
        this.mModulus = 0;
        this.mEof = false;
    }

    public Object encode(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new Exception("Parameter supplied to Base-N encode is not a byte[]");
    }

    public String encodeToString(byte[] bArr) {
        return AesHelper.newStringUtf8(encode(bArr));
    }

    public Object decode(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new Exception("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(AesHelper.getBytesUtf8(str));
    }

    public byte[] decode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        decode(bArr, 0, bArr.length);
        decode(bArr, 0, -1);
        bArr = new byte[this.mPos];
        readResults(bArr, 0, bArr.length);
        return bArr;
    }

    public byte[] encode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        encode(bArr, 0, bArr.length);
        encode(bArr, 0, -1);
        bArr = new byte[(this.mPos - this.mReadPos)];
        readResults(bArr, 0, bArr.length);
        return bArr;
    }

    public String encodeAsString(byte[] bArr) {
        return AesHelper.newStringUtf8(encode(bArr));
    }

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        int i = 0;
        while (i < bArr.length) {
            if (!isInAlphabet(bArr[i])) {
                if (!z) {
                    return false;
                }
                if (bArr[i] != 61 && !isWhiteSpace(bArr[i])) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(AesHelper.getBytesUtf8(str), true);
    }

    protected boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        int i = 0;
        while (i < bArr.length) {
            if (61 != bArr[i] && !isInAlphabet(bArr[i])) {
                i++;
            }
            return true;
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        long length = ((long) (((bArr.length + this.mUnencodedBlockSize) - 1) / this.mUnencodedBlockSize)) * ((long) this.mEncodedBlockSize);
        return this.mLineLength > 0 ? length + ((((((long) this.mLineLength) + length) - 1) / ((long) this.mLineLength)) * ((long) this.mChunkSeparatorLength)) : length;
    }
}
