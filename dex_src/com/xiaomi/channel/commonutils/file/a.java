package com.xiaomi.channel.commonutils.file;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.taobao.accs.data.Message;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class a {
    public static final String[] a;

    static {
        a = new String[]{"jpg", "png", "bmp", "gif", "webp"};
    }

    public static void a(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(File file, File file2) {
        OutputStream outputStream;
        Throwable th;
        String str = null;
        try {
            OutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file, false));
            str = null;
            try {
                a(zipOutputStream, file2, null, null);
                a(zipOutputStream);
            } catch (FileNotFoundException e) {
            } catch (IOException e2) {
                IOException iOException = e2;
                outputStream = zipOutputStream;
                r0 = iOException;
                b.a(new StringBuilder("zip file failure + ").append(r0.getMessage()).toString());
                a(outputStream);
            } catch (Throwable th2) {
                Throwable th3 = th2;
                outputStream = zipOutputStream;
                th = th3;
                a(outputStream);
                throw th;
            }
        } catch (FileNotFoundException e3) {
            String str2 = str;
            a(zipOutputStream);
        } catch (IOException e4) {
            r0 = e4;
            try {
                IOException iOException2;
                b.a(new StringBuilder("zip file failure + ").append(iOException2.getMessage()).toString());
                a(outputStream);
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
            }
            try {
                outputStream.close();
            } catch (IOException e2) {
            }
        }
    }

    public static void a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) {
        IOException iOException;
        Throwable th;
        InputStream inputStream = null;
        int i = 0;
        if (str == null) {
            Object obj = com.umeng.a.d;
        }
        try {
            InputStream inputStream2;
            if (file.isDirectory()) {
                File[] listFiles = fileFilter != null ? file.listFiles(fileFilter) : file.listFiles();
                zipOutputStream.putNextEntry(new ZipEntry(obj + File.separator));
                String str2 = TextUtils.isEmpty(obj) ? com.umeng.a.d : obj + File.separator;
                for (int i2 = 0; i2 < listFiles.length; i2++) {
                    a(zipOutputStream, listFiles[i2], str2 + listFiles[i2].getName(), null);
                }
                File[] listFiles2 = file.listFiles(new b());
                if (listFiles2 != null) {
                    int length = listFiles2.length;
                    while (i < length) {
                        File file2 = listFiles2[i];
                        a(zipOutputStream, file2, str2 + File.separator + file2.getName(), fileFilter);
                        i++;
                    }
                }
                inputStream2 = null;
            } else {
                if (TextUtils.isEmpty(obj)) {
                    zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(new Date().getTime()) + ".txt"));
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry(obj));
                }
                inputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        i = inputStream2.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, i);
                    }
                } catch (IOException e) {
                    IOException iOException2 = e;
                    inputStream = inputStream2;
                    iOException = iOException2;
                    try {
                        b.d(new StringBuilder("zipFiction failed with exception:").append(iOException.toString()).toString());
                        a(inputStream);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    inputStream = inputStream2;
                    th = th4;
                    a(inputStream);
                    throw th;
                }
            }
            a(inputStream2);
        } catch (IOException e2) {
            iOException = e2;
            b.d(new StringBuilder("zipFiction failed with exception:").append(iOException.toString()).toString());
            a(inputStream);
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            bArr = toByteArray;
            return bArr;
        } catch (Exception e) {
            return bArr;
        }
    }

    public static byte[] b(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, Message.FLAG_REQ_BIT2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    bArr = byteArrayOutputStream.toByteArray();
                    a(inputStream);
                    a(byteArrayOutputStream);
                    return bArr;
                }
            } catch (Exception e) {
                e.printStackTrace();
                a(inputStream);
                a(byteArrayOutputStream);
                return null;
            }
        }
    }
}
