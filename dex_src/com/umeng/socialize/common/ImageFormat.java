package com.umeng.socialize.common;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageFormat {
    public static final int FORMAT_BMP = 3;
    public static final int FORMAT_GIF = 1;
    public static final int FORMAT_IFF = 5;
    public static final int FORMAT_JPEG = 0;
    public static final String[] FORMAT_NAMES;
    public static final int FORMAT_PBM = 7;
    public static final int FORMAT_PCX = 4;
    public static final int FORMAT_PGM = 8;
    public static final int FORMAT_PNG = 2;
    public static final int FORMAT_PPM = 9;
    public static final int FORMAT_PSD = 10;
    public static final int FORMAT_RAS = 6;
    public static final int FORMAT_SWF = 11;

    static {
        FORMAT_NAMES = new String[]{"jpeg", "gif", "png", "bmp", "pcx", "iff", "ras", "pbm", "pgm", "ppm", "psd", "swf"};
    }

    public static String checkFormat(byte[] bArr) {
        try {
            String str;
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                int read = byteArrayInputStream.read();
                int read2 = byteArrayInputStream.read();
            } catch (Exception e) {
                str = BuildConfig.VERSION_NAME;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                return str;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th2;
            }
            if (read == 71 && read2 == 73) {
                str = FORMAT_NAMES[1];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return str;
            } else if (read == 137 && read2 == 80) {
                str = FORMAT_NAMES[2];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
                return str;
            } else if (read == 255 && read2 == 216) {
                str = FORMAT_NAMES[0];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
                return str;
            } else if (read == 66 && read2 == 77) {
                str = FORMAT_NAMES[3];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
                return str;
            } else if (read == 10 && read2 < 6) {
                str = FORMAT_NAMES[4];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
                return str;
            } else if (read == 70 && read2 == 79) {
                str = FORMAT_NAMES[5];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e222222) {
                    e222222.printStackTrace();
                }
                return str;
            } else if (read == 89 && read2 == 166) {
                str = FORMAT_NAMES[6];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e2222222) {
                    e2222222.printStackTrace();
                }
                return str;
            } else if (read == 80 && read2 >= 49 && read2 <= 54) {
                read = read2 - 48;
                if (read <= 0 || read > 6) {
                    str = BuildConfig.VERSION_NAME;
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e22222222) {
                        e22222222.printStackTrace();
                    }
                    return str;
                }
                str = FORMAT_NAMES[new int[]{7, 8, 9}[(read - 1) % 3]];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e222222222) {
                    e222222222.printStackTrace();
                }
                return str;
            } else if (read == 56 && read2 == 66) {
                str = FORMAT_NAMES[10];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e2222222222) {
                    e2222222222.printStackTrace();
                }
                return str;
            } else if (read == 70 && read2 == 87) {
                str = FORMAT_NAMES[11];
                try {
                    byteArrayInputStream.close();
                } catch (IOException e22222222222) {
                    e22222222222.printStackTrace();
                }
                return str;
            } else {
                str = BuildConfig.VERSION_NAME;
                try {
                    byteArrayInputStream.close();
                } catch (IOException e222222222222) {
                    e222222222222.printStackTrace();
                }
                return str;
            }
        } catch (Exception e3) {
            byteArrayInputStream = null;
            str = BuildConfig.VERSION_NAME;
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e2222222222222) {
                    e2222222222222.printStackTrace();
                }
            }
            return str;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayInputStream = null;
            th2 = th4;
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e22222222222222) {
                    e22222222222222.printStackTrace();
                }
            }
            throw th2;
        }
    }
}
