package com.xunlei.downloadprovider.download.taskDetail.bt;

import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.service.downloads.task.info.BTSubTaskInfo;

public class BtTaskItemFileInfo extends BTSubTaskInfo {
    public static final int ViewTypeBtItem = 0;
    public static final int ViewTypeNameAndIcon = 2;
    public static final int ViewTypeShareBar = 3;
    public static final int ViewTypeSpeedInfo = 1;
    public boolean isSelected;
    public int itemType;
    public EFileCategoryType mFileCategoryType;
    public boolean mIsFileMissing;
    public int mVideoDuration;
    public int mVideoPlayedTime;

    public BtTaskItemFileInfo() {
        this.isSelected = false;
        this.mIsFileMissing = false;
        this.itemType = 0;
    }
}
