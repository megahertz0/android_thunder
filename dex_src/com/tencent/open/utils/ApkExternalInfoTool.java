package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

// compiled from: ProGuard
public final class ApkExternalInfoTool {
    public static final String CHANNELID = "channelNo";
    private static final ZipLong a;
    private static final ZipShort b;

    // compiled from: ProGuard
    private static class ApkExternalInfo {
        Properties a;
        byte[] b;

        private ApkExternalInfo() {
            this.a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr != null) {
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                int length = b.getBytes().length;
                byte[] bArr2 = new byte[length];
                wrap.get(bArr2);
                if (!b.equals(new ZipShort(bArr2))) {
                    throw new ProtocolException(new StringBuilder("unknow protocl [").append(Arrays.toString(bArr)).append("]").toString());
                } else if (bArr.length - length > 2) {
                    bArr2 = new byte[2];
                    wrap.get(bArr2);
                    int value = new ZipShort(bArr2).getValue();
                    if ((bArr.length - length) - 2 >= value) {
                        byte[] bArr3 = new byte[value];
                        wrap.get(bArr3);
                        this.a.load(new ByteArrayInputStream(bArr3));
                        length = ((bArr.length - length) - value) - 2;
                        if (length > 0) {
                            this.b = new byte[length];
                            wrap.get(this.b);
                        }
                    }
                }
            }
        }

        public String toString() {
            return new StringBuilder("ApkExternalInfo [p=").append(this.a).append(", otherData=").append(Arrays.toString(this.b)).append("]").toString();
        }
    }

    static {
        a = new ZipLong(101010256);
        b = new ZipShort(38651);
    }

    public static String read(File file, String str) throws IOException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                byte[] a = a(randomAccessFile);
                if (a == null) {
                    randomAccessFile.close();
                    return null;
                }
                ApkExternalInfo apkExternalInfo = new ApkExternalInfo();
                apkExternalInfo.a(a);
                String property = apkExternalInfo.a.getProperty(str);
                randomAccessFile.close();
                return property;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            randomAccessFile = null;
            th2 = th4;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th2;
        }
    }

    public static String readChannelId(File file) throws IOException {
        return read(file, CHANNELID);
    }

    private static byte[] a(RandomAccessFile randomAccessFile) throws IOException {
        int i = 1;
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bytes = a.getBytes();
        int read = randomAccessFile.read();
        while (r2 != (byte) -1) {
            if (r2 == bytes[0] && randomAccessFile.read() == bytes[1] && randomAccessFile.read() == bytes[2] && randomAccessFile.read() == bytes[3]) {
                break;
            }
            length--;
            randomAccessFile.seek(length);
            read = randomAccessFile.read();
        }
        i = 0;
        if (i == 0) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek((16 + length) + 4);
        byte[] bArr = new byte[2];
        randomAccessFile.readFully(bArr);
        i = new ZipShort(bArr).getValue();
        if (i == 0) {
            return null;
        }
        bArr = new byte[i];
        randomAccessFile.read(bArr);
        return bArr;
    }
}
