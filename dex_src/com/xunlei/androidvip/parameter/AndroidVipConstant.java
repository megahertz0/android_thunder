package com.xunlei.androidvip.parameter;

public class AndroidVipConstant {

    public enum XLCreateTaskMode {
        NEW_TASK,
        CONTINUE_TASK
    }

    public enum XLDownloadHeaderState {
        GDHS_UNKOWN,
        GDHS_REQUESTING,
        GDHS_SUCCESS,
        GDHS_ERROR
    }

    public static class XLErrorCode {
        public static final int ALREADY_INIT = 9101;
        public static final int APPNAME_APPKEY_ERROR = 9116;
        public static final int CONTINUE_NO_NAME = 9115;
        public static final int CREATE_THREAD_ERROR = 9117;
        public static final int DISK_FULL = 9110;
        public static final int DYNAMIC_PARAM_FAIL = 9114;
        public static final int FILE_EXISTED = 9109;
        public static final int NO_ERROR = 9000;
        public static final int PARAM_ERROR = 9112;
        public static final int SCHEMA_NOT_SUPPORT = 9113;
        public static final int SDK_NOT_INIT = 9102;
        public static final int TASK_ALREADY_EXIST = 9103;
        public static final int TASK_ALREADY_RUNNING = 9106;
        public static final int TASK_ALREADY_STOPPED = 9105;
        public static final int TASK_FINISH = 9118;
        public static final int TASK_NOT_EXIST = 9104;
        public static final int TASK_NOT_IDLE = 9120;
        public static final int TASK_NOT_RUNNING = 9119;
        public static final int TASK_NOT_START = 9107;
        public static final int TASK_STILL_RUNNING = 9108;
        public static final int TASK_TYPE_NOT_SUPPORT = 9121;
        public static final int TOO_MUCH_TASK = 9111;
    }

    public enum XLNetWorkCarrier {
        NWC_Unknow,
        NWC_CMCC,
        NWC_CU,
        NWC_CT
    }

    public enum XLQueryIndexStatus {
        QIS_UNQUERY,
        QIS_QUERYING,
        QIS_QUERY_HAVE_INFO,
        QIS_QUERY_HAVENT_INFO
    }

    public enum XLResStrategy {
        RUS_PRIOR_USE
    }

    public static class XLTaskStatus {
        public static final int TASK_FAILED = 3;
        public static final int TASK_IDLE = 0;
        public static final int TASK_RUNNING = 1;
        public static final int TASK_STOPPED = 4;
        public static final int TASK_SUCCESS = 2;
    }

    public static class XLTaskType {
        public static final int P2SP_TASK_TYP = 1;
    }

    public enum XLVipManagerStatus {
        MANAGER_UNINIT,
        MANAGER_INIT_FAIL,
        MANAGER_RUNNING
    }
}
