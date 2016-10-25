package com.alipay.sdk.packet;

import com.alipay.sdk.cons.a;
import com.alipay.sdk.encrypt.c;
import com.alipay.sdk.encrypt.d;
import com.alipay.sdk.util.k;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Locale;

public final class e {
    private boolean a;
    private String b;

    public e(boolean z) {
        this.a = z;
        this.b = k.c();
    }

    public final c a(b bVar, boolean z) {
        byte[] a;
        byte[] bytes = bVar.a.getBytes();
        byte[] bytes2 = bVar.b.getBytes();
        if (z) {
            try {
                bytes2 = c.a(bytes2);
            } catch (Exception e) {
                z = false;
            }
        }
        if (this.a) {
            byte[] a2 = d.a(this.b, a.b);
            bytes2 = com.alipay.sdk.encrypt.e.a(this.b, bytes2);
            a = a(bytes, a2, bytes2);
        } else {
            a = a(bytes, bytes2);
        }
        return new c(z, a);
    }

    public final b a(c cVar) {
        String str;
        String str2;
        String str3;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cVar.b);
            try {
                byte[] bArr = new byte[5];
                byteArrayInputStream.read(bArr);
                byte[] bArr2 = new byte[Integer.parseInt(new String(bArr))];
                byteArrayInputStream.read(bArr2);
                str = new String(bArr2);
                try {
                    bArr2 = new byte[5];
                    byteArrayInputStream.read(bArr2);
                    int parseInt = Integer.parseInt(new String(bArr2));
                    if (parseInt > 0) {
                        byte[] b;
                        bArr2 = new byte[parseInt];
                        byteArrayInputStream.read(bArr2);
                        if (this.a) {
                            bArr2 = com.alipay.sdk.encrypt.e.b(this.b, bArr2);
                        }
                        if (cVar.a) {
                            b = c.b(bArr2);
                        } else {
                            b = bArr2;
                        }
                        str2 = new String(b);
                    } else {
                        str2 = null;
                    }
                    try {
                        byteArrayInputStream.close();
                        str3 = str2;
                    } catch (Exception e) {
                        str3 = str2;
                    }
                } catch (Exception e2) {
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                        str3 = null;
                    } else {
                        str3 = null;
                    }
                    if (str == null) {
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th2;
                }
            } catch (Exception e3) {
                str = null;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                    str3 = null;
                } else {
                    str3 = null;
                }
                if (str == null) {
                }
            } catch (Throwable th3) {
                Throwable th22 = th3;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th22;
            }
        } catch (Exception e4) {
            byteArrayInputStream = null;
            str = null;
            if (byteArrayInputStream != null) {
                str3 = null;
            } else {
                try {
                    byteArrayInputStream.close();
                    str3 = null;
                } catch (Exception e5) {
                    str3 = null;
                }
            }
            if (str == null) {
            }
        } catch (Throwable th4) {
            Throwable th5 = th4;
            byteArrayInputStream = null;
            th22 = th5;
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e6) {
                }
            }
            throw th22;
        }
        return (str == null || str3 != null) ? new b(str, str3) : null;
    }

    private static byte[] a(String str, String str2) {
        return d.a(str, str2);
    }

    private static byte[] a(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.a(str, bArr);
    }

    private static byte[] b(String str, byte[] bArr) {
        return com.alipay.sdk.encrypt.e.b(str, bArr);
    }

    private static byte[] a(byte[]... bArr) {
        DataOutputStream dataOutputStream;
        Throwable th;
        int i = 0;
        byte[] bArr2 = null;
        if (bArr.length != 0) {
            ByteArrayOutputStream byteArrayOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    while (i < bArr.length) {
                        try {
                            int length = bArr[i].length;
                            dataOutputStream.write(String.format(Locale.getDefault(), "%05d", new Object[]{Integer.valueOf(length)}).getBytes());
                            dataOutputStream.write(bArr[i]);
                            i++;
                        } catch (Exception e) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    dataOutputStream.flush();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                    }
                    try {
                        dataOutputStream.close();
                    } catch (Exception e3) {
                    }
                } catch (Exception e4) {
                    dataOutputStream = null;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    return bArr2;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    dataOutputStream = null;
                    th = th4;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                dataOutputStream = null;
                byteArrayOutputStream = null;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e6) {
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (Exception e7) {
                    }
                }
                return bArr2;
            } catch (Throwable th32) {
                byteArrayOutputStream = null;
                th = th32;
                dataOutputStream = null;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e8) {
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (Exception e9) {
                    }
                }
                throw th;
            }
        }
        return bArr2;
    }

    private static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", new Object[]{Integer.valueOf(i)});
    }

    private static int a(String str) {
        return Integer.parseInt(str);
    }
}
