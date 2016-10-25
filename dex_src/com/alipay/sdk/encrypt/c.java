package com.alipay.sdk.encrypt;

import com.taobao.accs.data.Message;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class c {
    public static byte[] a(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            GZIPOutputStream gZIPOutputStream;
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                    try {
                        byte[] bArr2 = new byte[4096];
                        while (true) {
                            int read = byteArrayInputStream2.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            gZIPOutputStream.write(bArr2, 0, read);
                        }
                        gZIPOutputStream.flush();
                        gZIPOutputStream.finish();
                        bArr2 = byteArrayOutputStream2.toByteArray();
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e) {
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e2) {
                        }
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e3) {
                        }
                        return bArr2;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        OutputStream outputStream = byteArrayOutputStream2;
                        byteArrayInputStream = byteArrayInputStream2;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Exception e6) {
                            }
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    gZIPOutputStream = null;
                    outputStream = byteArrayOutputStream2;
                    byteArrayInputStream = byteArrayInputStream2;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                gZIPOutputStream = null;
                byteArrayInputStream = byteArrayInputStream2;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            gZIPOutputStream = null;
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            throw th2;
        }
    }

    public static byte[] b(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            InputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                try {
                    byte[] bArr2 = new byte[4096];
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = gZIPInputStream.read(bArr2, 0, Message.FLAG_ERR);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr2, 0, read);
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            InputStream inputStream = byteArrayInputStream2;
                        }
                    }
                    byteArrayOutputStream2.flush();
                    bArr2 = byteArrayOutputStream2.toByteArray();
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e2) {
                    }
                    try {
                        byteArrayInputStream2.close();
                    } catch (Exception e3) {
                    }
                    return bArr2;
                } catch (Throwable th3) {
                    th2 = th3;
                    inputStream = byteArrayInputStream2;
                    byteArrayOutputStream.close();
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e4) {
                    }
                    byteArrayInputStream.close();
                    throw th2;
                }
            } catch (Throwable th4) {
                th2 = th4;
                gZIPInputStream = null;
                inputStream = byteArrayInputStream2;
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e5) {
                }
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            gZIPInputStream = null;
            byteArrayInputStream = null;
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            try {
                byteArrayInputStream.close();
            } catch (Exception e6) {
            }
            throw th2;
        }
    }
}
