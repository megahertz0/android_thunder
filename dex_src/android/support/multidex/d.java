package android.support.multidex;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

// compiled from: ZipUtil.java
final class d {

    // compiled from: ZipUtil.java
    static class a {
        long a;
        long b;

        a() {
        }
    }

    static long a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long a = a(randomAccessFile, a(randomAccessFile));
        randomAccessFile.close();
        return a;
    }

    private static a a(RandomAccessFile randomAccessFile) throws IOException, ZipException {
        long j = 0;
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException(new StringBuilder("File too short to be a zip file: ").append(randomAccessFile.length()).toString());
        }
        long j2 = length - 65536;
        if (j2 >= 0) {
            j = j2;
        }
        int reverseBytes = Integer.reverseBytes(101010256);
        j2 = length;
        do {
            randomAccessFile.seek(j2);
            if (randomAccessFile.readInt() != reverseBytes) {
                j2--;
            } else {
                randomAccessFile.skipBytes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                randomAccessFile.skipBytes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                randomAccessFile.skipBytes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                randomAccessFile.skipBytes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                a aVar = new a();
                aVar.b = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                aVar.a = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                return aVar;
            }
        } while (j2 >= j);
        throw new ZipException("End Of Central Directory signature not found");
    }

    private static long a(RandomAccessFile randomAccessFile, a aVar) throws IOException {
        CRC32 crc32 = new CRC32();
        long j = aVar.b;
        randomAccessFile.seek(aVar.a);
        byte[] bArr = new byte[16384];
        int read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        while (read != -1) {
            crc32.update(bArr, 0, read);
            j -= (long) read;
            if (j == 0) {
                break;
            }
            read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        }
        return crc32.getValue();
    }
}
