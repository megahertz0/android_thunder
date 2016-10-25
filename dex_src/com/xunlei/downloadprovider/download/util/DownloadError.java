package com.xunlei.downloadprovider.download.util;

import com.xunlei.common.accelerator.utils.ErrorCodeUtils;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadlib.parameter.XLConstant.XLErrorCode;
import com.xunlei.downloadprovider.download.util.DownloadError.FailureCode;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.xllib.R;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public final class DownloadError {

    static /* synthetic */ class AnonymousClass_1 {
        public static final /* synthetic */ int[] a;

        static {
            a = new int[FailureCode.values().length];
            try {
                a[FailureCode.INSUFFICIENT_SPACE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[FailureCode.PATH_CANNOT_BE_WRITTEN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[FailureCode.TORRENT_NOT_EXIST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[FailureCode.TORRENT_INVALID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[FailureCode.BT_PART_SUBTASK_DOWNLOAD_FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[FailureCode.BT_ALL_SUBTASK_DOWNLOAD_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[FailureCode.BT_SUBFILE_DOWNLOAD_FAILURE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[FailureCode.BT_TASK_DOWNLOAD_FAILURE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[FailureCode.BT_FILE_PARSE_FAILURE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[FailureCode.MAGNET_LINK_PARSE_FAILURE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[FailureCode.EMULE_LINK_PARSE_FAILURE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[FailureCode.FILE_ERROR.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[FailureCode.FILE_NOT_EXIST.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[FailureCode.GET_RESOURCE_NAME_FAILURE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[FailureCode.RESOURCE_SERVER_CONNECTION_FAILURE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[FailureCode.RESOURCE_SERVER_CONNECTION_INTERRUPTION.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[FailureCode.SENSITIVE_RESOURCE_DOWNLOAD_LIMITED.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[FailureCode.CONTINUINGLY_TASK_FAILURE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[FailureCode.DOWNLOAD_INFORMATION_UPDATE_FAILURE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[FailureCode.TASK_PARAMETER_ERROR.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[FailureCode.TASK_COUNT_MORE_THAN_UPPER_LIMIT.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[FailureCode.TASK_ALREADY_EXISTS.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[FailureCode.TASK_DELETED.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
        }
    }

    public enum FailureCode {
        INSUFFICIENT_SPACE,
        PATH_CANNOT_BE_WRITTEN,
        TORRENT_NOT_EXIST,
        TORRENT_INVALID,
        BT_PART_SUBTASK_DOWNLOAD_FAILURE,
        BT_ALL_SUBTASK_DOWNLOAD_FAILURE,
        BT_SUBFILE_DOWNLOAD_FAILURE,
        BT_TASK_DOWNLOAD_FAILURE,
        BT_FILE_PARSE_FAILURE,
        MAGNET_LINK_PARSE_FAILURE,
        EMULE_LINK_PARSE_FAILURE,
        FILE_ERROR,
        FILE_NOT_EXIST,
        GET_RESOURCE_NAME_FAILURE,
        RESOURCE_SERVER_CONNECTION_FAILURE,
        RESOURCE_SERVER_CONNECTION_INTERRUPTION,
        SENSITIVE_RESOURCE_DOWNLOAD_LIMITED,
        CONTINUINGLY_TASK_FAILURE,
        DOWNLOAD_INFORMATION_UPDATE_FAILURE,
        TASK_PARAMETER_ERROR,
        TASK_COUNT_MORE_THAN_UPPER_LIMIT,
        TASK_ALREADY_EXISTS,
        TASK_DELETED,
        TASK_LINK_FAILURE;

        static {
            INSUFFICIENT_SPACE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("INSUFFICIENT_SPACE", 0);
            PATH_CANNOT_BE_WRITTEN = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("PATH_CANNOT_BE_WRITTEN", 1);
            TORRENT_NOT_EXIST = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TORRENT_NOT_EXIST", 2);
            TORRENT_INVALID = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TORRENT_INVALID", 3);
            BT_PART_SUBTASK_DOWNLOAD_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("BT_PART_SUBTASK_DOWNLOAD_FAILURE", 4);
            BT_ALL_SUBTASK_DOWNLOAD_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("BT_ALL_SUBTASK_DOWNLOAD_FAILURE", 5);
            BT_SUBFILE_DOWNLOAD_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("BT_SUBFILE_DOWNLOAD_FAILURE", 6);
            BT_TASK_DOWNLOAD_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("BT_TASK_DOWNLOAD_FAILURE", 7);
            BT_FILE_PARSE_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("BT_FILE_PARSE_FAILURE", 8);
            MAGNET_LINK_PARSE_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("MAGNET_LINK_PARSE_FAILURE", 9);
            EMULE_LINK_PARSE_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("EMULE_LINK_PARSE_FAILURE", 10);
            FILE_ERROR = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("FILE_ERROR", 11);
            FILE_NOT_EXIST = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("FILE_NOT_EXIST", 12);
            GET_RESOURCE_NAME_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("GET_RESOURCE_NAME_FAILURE", 13);
            RESOURCE_SERVER_CONNECTION_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("RESOURCE_SERVER_CONNECTION_FAILURE", 14);
            RESOURCE_SERVER_CONNECTION_INTERRUPTION = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("RESOURCE_SERVER_CONNECTION_INTERRUPTION", 15);
            SENSITIVE_RESOURCE_DOWNLOAD_LIMITED = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("SENSITIVE_RESOURCE_DOWNLOAD_LIMITED", 16);
            CONTINUINGLY_TASK_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("CONTINUINGLY_TASK_FAILURE", 17);
            DOWNLOAD_INFORMATION_UPDATE_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("DOWNLOAD_INFORMATION_UPDATE_FAILURE", 18);
            TASK_PARAMETER_ERROR = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TASK_PARAMETER_ERROR", 19);
            TASK_COUNT_MORE_THAN_UPPER_LIMIT = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TASK_COUNT_MORE_THAN_UPPER_LIMIT", 20);
            TASK_ALREADY_EXISTS = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TASK_ALREADY_EXISTS", 21);
            TASK_DELETED = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TASK_DELETED", 22);
            TASK_LINK_FAILURE = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode("TASK_LINK_FAILURE", 23);
            a = new com.xunlei.downloadprovider.download.util.DownloadError.FailureCode[]{INSUFFICIENT_SPACE, PATH_CANNOT_BE_WRITTEN, TORRENT_NOT_EXIST, TORRENT_INVALID, BT_PART_SUBTASK_DOWNLOAD_FAILURE, BT_ALL_SUBTASK_DOWNLOAD_FAILURE, BT_SUBFILE_DOWNLOAD_FAILURE, BT_TASK_DOWNLOAD_FAILURE, BT_FILE_PARSE_FAILURE, MAGNET_LINK_PARSE_FAILURE, EMULE_LINK_PARSE_FAILURE, FILE_ERROR, FILE_NOT_EXIST, GET_RESOURCE_NAME_FAILURE, RESOURCE_SERVER_CONNECTION_FAILURE, RESOURCE_SERVER_CONNECTION_INTERRUPTION, SENSITIVE_RESOURCE_DOWNLOAD_LIMITED, CONTINUINGLY_TASK_FAILURE, DOWNLOAD_INFORMATION_UPDATE_FAILURE, TASK_PARAMETER_ERROR, TASK_COUNT_MORE_THAN_UPPER_LIMIT, TASK_ALREADY_EXISTS, TASK_DELETED, TASK_LINK_FAILURE};
        }
    }

    public enum SpeedupFailureCode {
        UNKNOWN,
        SENSITIVE_RESOURCE_LIMITED;

        static {
            UNKNOWN = new com.xunlei.downloadprovider.download.util.DownloadError.SpeedupFailureCode("UNKNOWN", 0);
            SENSITIVE_RESOURCE_LIMITED = new com.xunlei.downloadprovider.download.util.DownloadError.SpeedupFailureCode("SENSITIVE_RESOURCE_LIMITED", 1);
            a = new com.xunlei.downloadprovider.download.util.DownloadError.SpeedupFailureCode[]{UNKNOWN, SENSITIVE_RESOURCE_LIMITED};
        }
    }

    public static FailureCode a(TaskRunningInfo taskRunningInfo) {
        FailureCode failureCode;
        FailureCode failureCode2 = null;
        int i = taskRunningInfo.mOriginalStatusCode;
        switch ((int) DownloadManager.getReason(i)) {
            case XLPayErrorCode.XLP_GATE_PARAM_ERROR:
                failureCode = FailureCode.FILE_ERROR;
                break;
            case XLPayErrorCode.XLP_GATE_CFG_ERROR:
                failureCode = FailureCode.INSUFFICIENT_SPACE;
                break;
            case XLPayErrorCode.XLP_GATE_IVALID_ERROR:
                failureCode = FailureCode.TASK_ALREADY_EXISTS;
                break;
            default:
                failureCode = null;
                break;
        }
        if (failureCode != null) {
            return failureCode;
        }
        switch (i) {
            case 198:
            case 492:
            case XLErrorCode.INSUFFICIENT_DISK_SPACE:
                failureCode2 = FailureCode.INSUFFICIENT_SPACE;
                break;
            case 490:
                failureCode2 = FailureCode.TASK_DELETED;
                break;
            case XLErrorCode.TASK_ALREADY_EXIST:
                failureCode2 = FailureCode.TASK_ALREADY_EXISTS;
                break;
            case XLErrorCode.TOO_MUCH_TASK:
                failureCode2 = FailureCode.TASK_COUNT_MORE_THAN_UPPER_LIMIT;
                break;
            case XLErrorCode.PARAM_ERROR:
                failureCode2 = FailureCode.TASK_PARAMETER_ERROR;
                break;
            case XLErrorCode.TORRENT_PARSE_ERROR:
                failureCode2 = FailureCode.TORRENT_INVALID;
                break;
            case 11148:
                failureCode2 = FailureCode.TASK_LINK_FAILURE;
                break;
            case XLErrorCode.WRITE_FILE_ERR:
                failureCode2 = FailureCode.CONTINUINGLY_TASK_FAILURE;
                break;
            case XLErrorCode.OPEN_FILE_ERR:
                failureCode2 = FailureCode.PATH_CANNOT_BE_WRITTEN;
                break;
            case XLErrorCode.FILE_CFG_ERASE_ERROR:
                failureCode2 = FailureCode.DOWNLOAD_INFORMATION_UPDATE_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_NO_DATA_PIPE:
                failureCode2 = FailureCode.RESOURCE_SERVER_CONNECTION_FAILURE;
                break;
            case 111137:
                failureCode2 = FailureCode.GET_RESOURCE_NAME_FAILURE;
                break;
            case 111151:
                failureCode2 = FailureCode.SENSITIVE_RESOURCE_DOWNLOAD_LIMITED;
                break;
            case XLErrorCode.TASK_FAIL_LONG_TIME_NO_RECV_DATA:
                failureCode2 = FailureCode.RESOURCE_SERVER_CONNECTION_INTERRUPTION;
                break;
            case XLErrorCode.TASK_FAILURE_QUERY_EMULE_HUB_FAILED:
            case XLErrorCode.TASK_FAILURE_EMULE_NO_RECORD:
                failureCode2 = FailureCode.EMULE_LINK_PARSE_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_SUBTASK_FAILED:
                failureCode2 = FailureCode.BT_SUBFILE_DOWNLOAD_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_QUERY_BT_HUB_FAILED:
                failureCode2 = FailureCode.BT_FILE_PARSE_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_GET_TORRENT_FAILED:
                failureCode2 = FailureCode.MAGNET_LINK_PARSE_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_ALL_SUBTASK_FAILED:
                failureCode2 = FailureCode.BT_ALL_SUBTASK_DOWNLOAD_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_THEONLY_SUBTASK_FAILED:
                failureCode2 = FailureCode.BT_TASK_DOWNLOAD_FAILURE;
                break;
            case XLErrorCode.TASK_FAILURE_PART_SUBTASK_FAILED:
                failureCode2 = FailureCode.BT_PART_SUBTASK_DOWNLOAD_FAILURE;
                break;
        }
        if (failureCode2 != FailureCode.TORRENT_INVALID || taskRunningInfo.mTaskType != TaskType.BT) {
            return failureCode2;
        }
        try {
            return !new File(new URI(taskRunningInfo.mUrl)).exists() ? FailureCode.TORRENT_NOT_EXIST : failureCode2;
        } catch (URISyntaxException e) {
            return failureCode2;
        }
    }

    private static SpeedupFailureCode a(int i) {
        SpeedupFailureCode speedupFailureCode = SpeedupFailureCode.UNKNOWN;
        switch (i) {
            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
            case R.styleable.AppCompatTheme_seekBarStyle:
            case R.styleable.AppCompatTheme_switchStyle:
            case ErrorCodeUtils.XLA_TRY_COUNT_NOT_ENOUGH_ERROR:
            case 509:
            case 510:
                return SpeedupFailureCode.SENSITIVE_RESOURCE_LIMITED;
            case 675:
            case 676:
            case 677:
            case 678:
                return SpeedupFailureCode.SENSITIVE_RESOURCE_LIMITED;
            default:
                return speedupFailureCode;
        }
    }

    public static SpeedupFailureCode b(TaskRunningInfo taskRunningInfo) {
        SpeedupFailureCode speedupFailureCode = null;
        if (taskRunningInfo == null) {
            return null;
        }
        if (taskRunningInfo.mVipChannelStatus != 16 && taskRunningInfo.mLixianStatus != 16) {
            return null;
        }
        if (taskRunningInfo.mVipChannelStatus == 16) {
            speedupFailureCode = a(taskRunningInfo.mVipChannelStatusCode);
        }
        return (speedupFailureCode == SpeedupFailureCode.SENSITIVE_RESOURCE_LIMITED || taskRunningInfo.mLixianStatus != 16) ? speedupFailureCode : a(taskRunningInfo.mLixianStatusCode);
    }
}
