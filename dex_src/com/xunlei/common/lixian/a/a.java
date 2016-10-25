package com.xunlei.common.lixian.a;

import com.xunlei.common.encrypt.AES;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.encrypt.ZLIB;
import com.xunlei.xllib.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.android.spdy.SpdyProtocol;

public final class a {
    private ByteArrayOutputStream a;
    private f b;

    public a() {
        this.a = null;
        this.b = null;
    }

    public a(short s) {
        this.a = null;
        this.b = null;
        this.b = new f();
        this.b.a("header_cmdid", Short.valueOf(s));
    }

    private a(byte[] bArr) {
        this.a = null;
        this.b = null;
        b(bArr);
    }

    private static byte[] c(byte[] bArr) {
        int i = R.styleable.Toolbar_titleMargins;
        if (bArr == null || bArr.length < 12) {
            return null;
        }
        byte[] encrypt = MD5.encrypt(Arrays.copyOfRange(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH));
        byte[] bArr2 = new byte[16];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i <= bArr.length) {
            int length = bArr.length - i >= 16 ? 16 : bArr.length - i;
            Arrays.fill(bArr2, (byte) (16 - length));
            System.arraycopy(bArr, i, bArr2, 0, length);
            byteArrayOutputStream.write(AES.encrypt(bArr2, encrypt));
            i += 16;
        }
        byteArrayOutputStream.flush();
        e eVar = new e();
        eVar.write(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        eVar.a(byteArrayOutputStream.size());
        eVar.write(byteArrayOutputStream.toByteArray());
        eVar.flush();
        return eVar.a();
    }

    private static byte[] d(byte[] bArr) {
        int i = R.styleable.Toolbar_titleMargins;
        if (bArr == null || bArr.length < 12 || (bArr.length - 12) % 16 != 0) {
            return null;
        }
        byte[] encrypt = MD5.encrypt(Arrays.copyOfRange(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i < bArr.length) {
            byteArrayOutputStream.write(AES.decrypt(Arrays.copyOfRange(bArr, i, i + 16), encrypt));
            i += 16;
        }
        byteArrayOutputStream.flush();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        byte b = toByteArray[toByteArray.length - 1];
        if (b <= null || b > 16) {
            return null;
        }
        int length = toByteArray.length - b;
        if (length < 0 || length > toByteArray.length) {
            return null;
        }
        e eVar = new e();
        eVar.write(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        eVar.a(length);
        eVar.write(toByteArray, 0, length);
        eVar.flush();
        return eVar.a();
    }

    public final f a() {
        return this.b;
    }

    public final void a(byte[] bArr) {
        try {
            this.a = new ByteArrayOutputStream();
            this.a.write(bArr);
            this.a.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final boolean b(byte[] bArr) {
        int i = R.styleable.Toolbar_titleMargins;
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                if (bArr.length >= 12 && (bArr.length - 12) % 16 == 0) {
                    byte[] encrypt = MD5.encrypt(Arrays.copyOfRange(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (i < bArr.length) {
                        byteArrayOutputStream.write(AES.decrypt(Arrays.copyOfRange(bArr, i, i + 16), encrypt));
                        i += 16;
                    }
                    byteArrayOutputStream.flush();
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    byte b = toByteArray[toByteArray.length - 1];
                    if (b > null && b <= 16) {
                        int length = toByteArray.length - b;
                        if (length >= 0 && length <= toByteArray.length) {
                            e eVar = new e();
                            eVar.write(bArr, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
                            eVar.a(length);
                            eVar.write(toByteArray, 0, length);
                            eVar.flush();
                            bArr2 = eVar.a();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        this.b = new f();
        i = this.b.b();
        try {
            if (!this.b.a(Arrays.copyOfRange(bArr2, 0, i))) {
                return false;
            }
            bArr2 = Arrays.copyOfRange(bArr2, i, bArr2.length);
            if (Short.valueOf(this.b.a("header_compress").toString()).shortValue() != (short) 0) {
                bArr2 = ZLIB.decrypt(bArr2);
            }
            if (bArr2 == null) {
                return false;
            }
            this.b.a("header_datalen", Integer.valueOf(bArr2.length));
            try {
                this.a = new ByteArrayOutputStream();
                this.a.write(bArr2);
                this.a.flush();
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (IOException e22) {
            e22.printStackTrace();
            return false;
        }
    }

    public final byte[] b() {
        return this.a != null ? this.a.toByteArray() : null;
    }

    public final byte[] c() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (this.b != null) {
                byteArrayOutputStream.write(this.b.a());
            }
            if (this.a != null) {
                byte[] toByteArray = this.a.toByteArray();
                if (Short.valueOf(this.b.a("header_compress").toString()).shortValue() != (short) 0) {
                    toByteArray = ZLIB.encrypt(toByteArray);
                }
                if (toByteArray != null) {
                    byteArrayOutputStream.write(toByteArray);
                }
            }
            byteArrayOutputStream.flush();
            return c(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
