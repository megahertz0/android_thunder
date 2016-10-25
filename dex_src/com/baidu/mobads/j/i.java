package com.baidu.mobads.j;

import android.content.Context;
import android.os.Environment;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class i implements IXAdIOUtils {
    public String getStoreagePath(Context context, String str, String str2) {
        try {
            return getExternalFilesDir(context).getPath() + str2;
        } catch (Exception e) {
            return str + str2;
        }
    }

    public String getStoreagePath(Context context) {
        return getStoreagePath(context, IXAdIOUtils.DEFAULT_SD_CARD_PATH, IXAdIOUtils.DEFAULT_CACHE_PATH);
    }

    public File getExternalFilesDir(Context context) {
        try {
            return m.a().n().isUseOldStoragePath() ? Environment.getExternalStorageDirectory() : context.getExternalFilesDir(null);
        } catch (Exception e) {
            m.a().f().e("XAdIOUtils", e.getMessage());
            return null;
        }
    }

    public File deleteFileRecursive(File file) {
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File deleteFileRecursive = deleteFileRecursive(listFiles[i]);
                    if (deleteFileRecursive != null) {
                        return deleteFileRecursive;
                    }
                }
            }
            return file.delete() ? null : file;
        } catch (Exception e) {
            return file.delete() ? null : file;
        }
    }

    public File deleteFileRecursive(String str) {
        return deleteFileRecursive(new File(str));
    }

    public boolean renameFile(String str, String str2) {
        try {
            File file = new File(str);
            return file.exists() ? file.renameTo(new File(str2)) : false;
        } catch (Exception e) {
            return false;
        }
    }

    public void copyFileInputStream(InputStream inputStream, String str) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(str);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            fileOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th2;
        }
    }

    public void copyFileFromAssetsTo(Context context, String str, String str2) {
        try {
            m.a().k().copyFileInputStream(context.getAssets().open(str), str2);
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }
}
