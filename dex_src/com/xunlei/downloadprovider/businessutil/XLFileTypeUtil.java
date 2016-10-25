package com.xunlei.downloadprovider.businessutil;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.android.spdy.SpdyAgent;

public final class XLFileTypeUtil {
    static String[] a;
    static String[] b;
    static String[] c;
    static String[] d;
    static String[] e;
    static String[] f;
    static String[] g;
    static String[] h;
    static String[] i;
    static final HashMap<String, Integer> j;

    public enum EFileCategoryType {
        E_OTHER_CATEGORY,
        E_VIDEO_CATEGORY,
        E_MUSIC_CATEGORY,
        E_BOOK_CATEGORY,
        E_SOFTWARE_CATEGORY,
        E_PICTURE_CATEGORY,
        E_ZIP_CATEGORY,
        E_TORRENT_CATEGORY,
        E_EXE_CATEGORY,
        E_APPLICATION_CATEGORY,
        E_XLFILES_DOWNLOAD_CATEGORY,
        E_XLFILES_PC_TRANSFER_CATEGORY,
        E_XLFILES_ADHOC_RECEIVE_CATEGORY,
        E_XLFILE_UPPER,
        E_XLDIR_CATEGORY;

        static {
            E_OTHER_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_OTHER_CATEGORY", 0);
            E_VIDEO_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_VIDEO_CATEGORY", 1);
            E_MUSIC_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_MUSIC_CATEGORY", 2);
            E_BOOK_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_BOOK_CATEGORY", 3);
            E_SOFTWARE_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_SOFTWARE_CATEGORY", 4);
            E_PICTURE_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_PICTURE_CATEGORY", 5);
            E_ZIP_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_ZIP_CATEGORY", 6);
            E_TORRENT_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_TORRENT_CATEGORY", 7);
            E_EXE_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_EXE_CATEGORY", 8);
            E_APPLICATION_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_APPLICATION_CATEGORY", 9);
            E_XLFILES_DOWNLOAD_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_XLFILES_DOWNLOAD_CATEGORY", 10);
            E_XLFILES_PC_TRANSFER_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_XLFILES_PC_TRANSFER_CATEGORY", 11);
            E_XLFILES_ADHOC_RECEIVE_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_XLFILES_ADHOC_RECEIVE_CATEGORY", 12);
            E_XLFILE_UPPER = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_XLFILE_UPPER", 13);
            E_XLDIR_CATEGORY = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType("E_XLDIR_CATEGORY", 14);
            a = new com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType[]{E_OTHER_CATEGORY, E_VIDEO_CATEGORY, E_MUSIC_CATEGORY, E_BOOK_CATEGORY, E_SOFTWARE_CATEGORY, E_PICTURE_CATEGORY, E_ZIP_CATEGORY, E_TORRENT_CATEGORY, E_EXE_CATEGORY, E_APPLICATION_CATEGORY, E_XLFILES_DOWNLOAD_CATEGORY, E_XLFILES_PC_TRANSFER_CATEGORY, E_XLFILES_ADHOC_RECEIVE_CATEGORY, E_XLFILE_UPPER, E_XLDIR_CATEGORY};
        }
    }

    static {
        a = new String[]{"png", "jpeg", "bmp", "jpg", "icon", "jpe", "gif", "jpeg2000"};
        b = new String[]{"wmv", "asf", "asx", "rm", "rmvb", "mpg", "mpeg", "mpe", "3gp", "mov", "mp4", "m4v", "avi", "mkv", "flv", "f4v", "vob", MsgConstant.KEY_TS, "xv"};
        c = new String[]{"wav", "midi", "cda", "mp3", "mp3pro", "wma", "sacd", "vqf", "ra", "rmx", "voc", "au", "aif", "snd", "aac", "flac", "ape"};
        d = new String[]{ShareActivity.KEY_TEXT, "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "rtf"};
        e = new String[]{"apk", "APK"};
        f = new String[]{"zip", "rar", "7z", "7zip", "tgz"};
        g = new String[]{"torrent"};
        h = new String[]{"exe"};
        i = new String[]{"flv", "mp4", "3gp", "rmvb", "mkv", "avi", "mov", "wmv", "asf", "f4v", "m4v", "rm", "xv", MsgConstant.KEY_TS, "mpg", "mpe", "mpeg"};
        j = new c();
    }

    public static EFileCategoryType a(String str) {
        return b(str, false);
    }

    @SuppressLint({"DefaultLocale"})
    private static EFileCategoryType b(String str, boolean z) {
        int i = 0;
        EFileCategoryType eFileCategoryType = EFileCategoryType.E_OTHER_CATEGORY;
        if (str == null) {
            return eFileCategoryType;
        }
        String trim = str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase().trim();
        int i2 = 0;
        while (i2 < b.length) {
            if (trim.compareTo(b[i2]) != 0) {
                if (TextUtils.isEmpty(trim) || !trim.startsWith(b[i2])) {
                }
                i2++;
            }
            return EFileCategoryType.E_VIDEO_CATEGORY;
        }
        for (i2 = 0; i2 < c.length; i2++) {
            if (trim.compareTo(c[i2]) == 0) {
                return EFileCategoryType.E_MUSIC_CATEGORY;
            }
        }
        for (i2 = 0; i2 < d.length; i2++) {
            if (trim.compareTo(d[i2]) == 0) {
                return EFileCategoryType.E_BOOK_CATEGORY;
            }
        }
        for (i2 = 0; i2 < e.length; i2++) {
            if (trim.compareTo(e[i2]) == 0) {
                return EFileCategoryType.E_SOFTWARE_CATEGORY;
            }
        }
        for (i2 = 0; i2 < f.length; i2++) {
            if (trim.compareTo(f[i2]) == 0) {
                return EFileCategoryType.E_ZIP_CATEGORY;
            }
        }
        for (i2 = 0; i2 < a.length; i2++) {
            if (trim.compareTo(a[i2]) == 0) {
                return EFileCategoryType.E_PICTURE_CATEGORY;
            }
        }
        for (i2 = 0; i2 < g.length; i2++) {
            if (trim.compareTo(g[i2]) == 0) {
                return EFileCategoryType.E_TORRENT_CATEGORY;
            }
        }
        while (i < h.length) {
            if (trim.compareTo(h[i]) == 0) {
                return EFileCategoryType.E_EXE_CATEGORY;
            }
            i++;
        }
        if (z) {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                return EFileCategoryType.E_XLDIR_CATEGORY;
            }
        }
        return eFileCategoryType;
    }

    public static int b(String str) {
        EFileCategoryType b = b(str, true);
        if (b == EFileCategoryType.E_VIDEO_CATEGORY) {
            return c(str);
        }
        switch (AnonymousClass_1.a[b.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return R.drawable.ic_dl_video;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return R.drawable.ic_dl_music;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return R.drawable.ic_dl_text;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return R.drawable.ic_dl_apk;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return R.drawable.ic_dl_image;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                return R.drawable.ic_dl_rar;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetLeft:
                return R.drawable.ic_dl_torrent;
            case XZBDevice.Wait:
                return R.drawable.ic_dl_other;
            case XZBDevice.Pause:
                return R.drawable.ic_dl_folder;
            default:
                return R.drawable.ic_dl_other;
        }
    }

    public static int c(String str) {
        int i = R.drawable.ic_dl_video;
        String toLowerCase = a(str, false).toLowerCase();
        return j.containsKey(toLowerCase) ? ((Integer) j.get(toLowerCase)).intValue() : i;
    }

    public static int d(String str) {
        String toLowerCase = a(str, false).toLowerCase();
        if (j.containsKey(toLowerCase)) {
            return ((Integer) j.get(toLowerCase)).intValue();
        }
        int i = R.drawable.ic_dl_other;
        if (str == null) {
            return i;
        }
        switch (AnonymousClass_1.a[b(str, false).ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return c(str);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return R.drawable.ic_dl_music;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return R.drawable.ic_dl_text;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return R.drawable.ic_dl_apk;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return R.drawable.ic_dl_image;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                return R.drawable.ic_dl_rar;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetLeft:
                return R.drawable.ic_dl_torrent;
            case XZBDevice.Wait:
                return R.drawable.ic_dl_other;
            default:
                return R.drawable.ic_dl_other;
        }
    }

    public static boolean e(String str) {
        String trim = str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase().trim();
        for (int i = 0; i < i.length; i++) {
            if (trim.compareTo(i[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static String f(String str) {
        return a(str, true);
    }

    public static String a(String str, boolean z) {
        int i;
        if (TextUtils.isEmpty(str)) {
            i = -1;
        } else {
            i = str.lastIndexOf(".");
        }
        if (i == -1 || (!z && i + 1 == str.length())) {
            return a.d;
        }
        return z ? str.substring(i).trim() : str.substring(i + 1).trim();
    }

    public static String a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0#");
        String str = "MB";
        String str2 = "KB";
        String str3 = "GB";
        String str4 = "B";
        float f = (float) j;
        float f2 = ((f / 1024.0f) / 1024.0f) / 1024.0f;
        if (f2 >= 1.0f) {
            StringBuilder stringBuilder = new StringBuilder();
            int i = (int) f2;
            if (((float) i) == f2) {
                stringBuilder.append(i + ".0 ");
            } else {
                stringBuilder.append(decimalFormat.format((double) f2) + " ");
            }
            stringBuilder.append(str3);
            return stringBuilder.toString();
        }
        float f3 = (f / 1024.0f) / 1024.0f;
        int i2;
        if (f3 >= 1.0f) {
            StringBuilder stringBuilder2 = new StringBuilder();
            i2 = (int) f3;
            if (((float) i2) == f3) {
                stringBuilder2.append(i2 + ".0 ");
            } else {
                stringBuilder2.append(decimalFormat.format((double) f3) + " ");
            }
            stringBuilder2.append(str);
            return stringBuilder2.toString();
        }
        float f4 = f / 1024.0f;
        if (f4 >= 1.0f) {
            StringBuilder stringBuilder3 = new StringBuilder();
            i2 = (int) f4;
            if (((float) i2) == f4) {
                stringBuilder3.append(i2 + ".0 ");
            } else {
                stringBuilder3.append(decimalFormat.format((double) f4) + " ");
            }
            stringBuilder3.append(str2);
            return stringBuilder3.toString();
        }
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(j + " ");
        stringBuilder4.append(str4);
        return stringBuilder4.toString();
    }
}
