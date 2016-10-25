package com.taobao.accs.utl;

import com.umeng.socialize.common.SocializeConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: Taobao
public class c {
    public static final String TAG = "FileUtils";

    public static byte[] a(File file) {
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        byte[] bArr = null;
        if (file == null || !file.exists()) {
            ALog.w(TAG, "filetoByte not exist", new Object[0]);
        } else {
            InputStream fileInputStream2;
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (Throwable th3) {
                fileInputStream = null;
                th2 = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                throw th2;
            }
            try {
                bArr = a(fileInputStream2);
                try {
                    fileInputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th4) {
                th3 = th4;
                ALog.e(TAG, "FileInputStream error", th3, new Object[0]);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            }
        }
        return bArr;
    }

    public static boolean a(byte[] bArr, File file) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (bArr == null || file == null) {
            ALog.w(TAG, "byteToFile null", SocializeConstants.JSON_DATA, bArr, "file", file);
            return false;
        }
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    ALog.e(TAG, "byteToFile write file error", th, new Object[0]);
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        return false;
                    }
                    return false;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return false;
    }

    public static byte[] a(InputStream inputStream) {
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        if (inputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[2048];
                    while (true) {
                        int read = inputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        ALog.e(TAG, "streamToByte error", th, new Object[0]);
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e2) {
                            }
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e3) {
                            }
                        }
                        throw th2;
                    }
                    return bArr;
                }
            } catch (Throwable th5) {
                byteArrayOutputStream = null;
                th2 = th5;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
        return bArr;
    }
}
