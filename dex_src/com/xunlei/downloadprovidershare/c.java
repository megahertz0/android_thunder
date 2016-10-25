package com.xunlei.downloadprovidershare;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;

// compiled from: ShareFileIconTypeUtil.java
final class c extends HashMap<String, Integer> {
    c() {
        int i = 0;
        String[] strArr = new String[]{"png", "jpeg", "bmp", "jpg", SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, "jpe", "gif", "jpeg2000"};
        for (int i2 = 0; i2 < 8; i2++) {
            put(strArr[i2], Integer.valueOf(R.drawable.share_ic_task_file_image));
        }
        put("wmv", Integer.valueOf(R.drawable.share_ic_task_file_wmv));
        put("asf", Integer.valueOf(R.drawable.share_ic_task_file_asf));
        put("asx", Integer.valueOf(R.drawable.share_ic_task_file_asx));
        put("rm", Integer.valueOf(R.drawable.share_ic_task_file_rm));
        put("rmvb", Integer.valueOf(R.drawable.share_ic_task_file_rmvb));
        put("mpg", Integer.valueOf(R.drawable.share_ic_task_file_mpg));
        put("mpeg", Integer.valueOf(R.drawable.share_ic_task_file_mpeg));
        put("mpe", Integer.valueOf(R.drawable.share_ic_task_file_mpe));
        put("3gp", Integer.valueOf(R.drawable.share_ic_task_file_3gp));
        put("mov", Integer.valueOf(R.drawable.share_ic_task_file_mov));
        put("mp4", Integer.valueOf(R.drawable.share_ic_task_file_mp4));
        put("m4v", Integer.valueOf(R.drawable.share_ic_task_file_m4v));
        put("avi", Integer.valueOf(R.drawable.share_ic_task_file_avi));
        put("mkv", Integer.valueOf(R.drawable.share_ic_task_file_mkv));
        put("flv", Integer.valueOf(R.drawable.share_ic_task_file_flv));
        put("f4v", Integer.valueOf(R.drawable.share_ic_task_file_f4v));
        put("vob", Integer.valueOf(R.drawable.share_ic_task_file_vob));
        put("ts", Integer.valueOf(R.drawable.share_ic_task_file_ts));
        put("xv", Integer.valueOf(R.drawable.share_ic_task_file_xv));
        put("apk", Integer.valueOf(R.drawable.share_ic_task_file_apk));
        put("rar", Integer.valueOf(R.drawable.share_ic_task_file_rar));
        put("zip", Integer.valueOf(R.drawable.share_ic_task_file_zip));
        put("7zip", Integer.valueOf(R.drawable.share_ic_task_file_7z));
        put("7z", Integer.valueOf(R.drawable.share_ic_task_file_7z));
        put("tgz", Integer.valueOf(R.drawable.share_ic_task_file_tar));
        String[] strArr2 = new String[]{"txt", "pdf", "rtf"};
        while (i < 3) {
            put(strArr2[i], Integer.valueOf(R.drawable.share_ic_task_file_text));
            i++;
        }
        put("doc", Integer.valueOf(R.drawable.share_ic_task_file_doc));
        put("docx", Integer.valueOf(R.drawable.share_ic_task_file_doc));
        put("xls", Integer.valueOf(R.drawable.share_ic_task_file_xls));
        put("xlsx", Integer.valueOf(R.drawable.share_ic_task_file_xls));
        put("ppt", Integer.valueOf(R.drawable.share_ic_task_file_ppt));
        put("pptx", Integer.valueOf(R.drawable.share_ic_task_file_ppt));
        put("torrent", Integer.valueOf(R.drawable.share_ic_task_file_torrent));
    }
}
