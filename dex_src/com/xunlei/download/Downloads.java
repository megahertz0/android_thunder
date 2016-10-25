package com.xunlei.download;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import anet.channel.util.HttpConstant;
import com.xunlei.download.proguard.am;

public final class Downloads {
    private static final String a = "notificationpackage=? AND notificationclass=?";

    public static final class Impl implements BaseColumns {
        public static final String ACTION_DOWNLOAD_COMPLETED = "android.intent.action.DOWNLOAD_COMPLETED";
        public static final String ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
        public static final Uri ALL_DOWNLOADS_CONTENT_URI;
        public static final String COLUMN_ADDITION_LX_SPEED = "addition_lx_speed";
        public static final String COLUMN_ADDITION_VIP_SPEED = "addition_vip_speed";
        public static final String COLUMN_ALLOWED_NETWORK_TYPES = "allowed_network_types";
        public static final String COLUMN_ALLOW_AUTO_RESUME = "allow_auto_resume";
        public static final String COLUMN_ALLOW_METERED = "allow_metered";
        public static final String COLUMN_ALLOW_ROAMING = "allow_roaming";
        public static final String COLUMN_ALLOW_WRITE = "allow_write";
        public static final String COLUMN_APK_PACKAGE = "apk_package";
        public static final String COLUMN_APK_VERSION = "apk_version";
        public static final String COLUMN_APP_DATA = "entity";
        public static final String COLUMN_BT_INFO_HASH = "etag";
        public static final String COLUMN_BT_PARENT_ID = "bt_parent_id";
        public static final String COLUMN_BT_SELECT_SET = "bt_select_set";
        public static final String COLUMN_BT_SUB_INDEX = "bt_sub_index";
        public static final String COLUMN_BT_SUB_IS_SELECTED = "bt_sub_is_selected";
        public static final String COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT = "bypass_recommended_size_limit";
        public static final String COLUMN_CID = "cid";
        public static final String COLUMN_CONTROL = "control";
        public static final String COLUMN_COOKIE_DATA = "cookiedata";
        public static final String COLUMN_CREATE_TIME = "create_time";
        public static final String COLUMN_CURRENT_BYTES = "current_bytes";
        public static final String COLUMN_DELETED = "deleted";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DESTINATION = "destination";
        public static final String COLUMN_DOWNLOAD_DURATION = "download_duration";
        public static final String COLUMN_DOWNLOAD_SPEED = "download_speed";
        public static final String COLUMN_ERROR_MSG = "errorMsg";
        public static final String COLUMN_EXTRA = "extra";
        public static final String COLUMN_FAILED_CONNECTIONS = "numfailed";
        public static final String COLUMN_FILE_NAME_HINT = "hint";
        public static final String COLUMN_GCID = "gcid";
        public static final String COLUMN_GROUP_ID = "group_id";
        public static final String COLUMN_IS_LX_SPEEDUP = "is_lx_speedup";
        public static final String COLUMN_IS_PUBLIC_API = "is_public_api";
        public static final String COLUMN_IS_VIP_SPEEDUP = "is_vip_speedup";
        public static final String COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI = "is_visible_in_downloads_ui";
        public static final String COLUMN_LAST_MODIFICATION = "lastmod";
        public static final String COLUMN_LAST_UPDATESRC = "lastUpdateSrc";
        public static final String COLUMN_LX_PROGRESS = "lx_progress";
        public static final String COLUMN_LX_RECEIVE_SIZE = "lx_receive_size";
        public static final String COLUMN_LX_STATUS = "lx_status";
        public static final String COLUMN_MEDIAPROVIDER_URI = "mediaprovider_uri";
        public static final String COLUMN_MEDIA_SCANNED = "scanned";
        public static final String COLUMN_MIME_TYPE = "mimetype";
        public static final String COLUMN_NOTIFICATION_CLASS = "notificationclass";
        public static final String COLUMN_NOTIFICATION_EXTRAS = "notificationextras";
        public static final String COLUMN_NOTIFICATION_PACKAGE = "notificationpackage";
        public static final String COLUMN_NO_INTEGRITY = "no_integrity";
        public static final String COLUMN_ORIGIN_RECEIVE_SIZE = "origin_receive_size";
        public static final String COLUMN_ORIGIN_SPEED = "origin_speed";
        public static final String COLUMN_OTHER_UID = "otheruid";
        public static final String COLUMN_P2P_RECEIVE_SIZE = "p2p_receive_size";
        public static final String COLUMN_P2P_SPEED = "p2p_speed";
        public static final String COLUMN_P2S_RECEIVE_SIZE = "p2s_receive_size";
        public static final String COLUMN_P2S_SPEED = "p2s_speed";
        public static final String COLUMN_REFERER = "referer";
        public static final String COLUMN_RES_TOTAL = "res_total";
        public static final String COLUMN_RES_USED_TOTAL = "res_used_total";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_TASK_TYPE = "task_type";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TOTAL_BYTES = "total_bytes";
        public static final String COLUMN_URI = "uri";
        public static final String COLUMN_USER_AGENT = "useragent";
        public static final String COLUMN_VIP_RECEIVE_SIZE = "vip_receive_size";
        public static final String COLUMN_VIP_STATUS = "vip_status";
        public static final String COLUMN_VISIBILITY = "visibility";
        public static final String COLUMN_XL_ORIGIN = "xl_origin";
        public static final String COLUMN_XUNLEI_SPDY = "xunlei_spdy";
        public static final Uri CONTENT_URI;
        public static final int CONTROL_PAUSED = 1;
        public static final int CONTROL_RUN = 0;
        public static final int CONTROL_SUSPEND = 10;
        public static final int DESTINATION_CACHE_PARTITION = 1;
        public static final int DESTINATION_CACHE_PARTITION_NOROAMING = 3;
        public static final int DESTINATION_CACHE_PARTITION_PURGEABLE = 2;
        public static final int DESTINATION_EXTERNAL = 0;
        public static final int DESTINATION_FILE_URI = 4;
        public static final int DESTINATION_NON_DOWNLOADMANAGER_DOWNLOAD = 6;
        public static final int DESTINATION_SYSTEMCACHE_PARTITION = 5;
        public static final int LAST_UPDATESRC_DONT_NOTIFY_DOWNLOADSVC = 1;
        public static final int LAST_UPDATESRC_NOT_RELEVANT = 0;
        public static final int MIN_ARTIFICIAL_ERROR_STATUS = 488;
        public static final String PERMISSION_ACCESS = "android.permission.ACCESS_DOWNLOAD_MANAGER";
        public static final String PERMISSION_ACCESS_ADVANCED = "android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED";
        public static final String PERMISSION_ACCESS_ALL = "android.permission.ACCESS_ALL_DOWNLOADS";
        public static final String PERMISSION_CACHE = "android.permission.ACCESS_CACHE_FILESYSTEM";
        public static final String PERMISSION_CACHE_NON_PURGEABLE = "android.permission.DOWNLOAD_CACHE_NON_PURGEABLE";
        public static final String PERMISSION_NO_NOTIFICATION = "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
        public static final String PERMISSION_SEND_INTENTS = "android.permission.SEND_DOWNLOAD_COMPLETED_INTENTS";
        public static final Uri PUBLICLY_ACCESSIBLE_DOWNLOADS_URI;
        public static final String PUBLICLY_ACCESSIBLE_DOWNLOADS_URI_SEGMENT = "public_downloads";
        public static final int STATUS_BAD_REQUEST = 400;
        @Deprecated
        public static final int STATUS_BLOCKED = 498;
        public static final int STATUS_CANCELED = 490;
        public static final int STATUS_CANNOT_RESUME = 489;
        public static final int STATUS_DELETE = 1;
        public static final int STATUS_DEVICE_NOT_FOUND_ERROR = 199;
        public static final int STATUS_FILE_ALREADY_EXISTS_ERROR = 488;
        public static final int STATUS_FILE_ERROR = 492;
        public static final int STATUS_HTTP_DATA_ERROR = 495;
        public static final int STATUS_HTTP_EXCEPTION = 496;
        public static final int STATUS_INSERT = 0;
        public static final int STATUS_INSUFFICIENT_SPACE_ERROR = 198;
        public static final int STATUS_LENGTH_REQUIRED = 411;
        public static final int STATUS_LX_VIP_ERROR_START = 600;
        public static final int STATUS_NOT_ACCEPTABLE = 406;
        public static final int STATUS_PAUSED_BY_APP = 193;
        public static final int STATUS_PEER_NOT_FOUND_ERROR = 500;
        public static final int STATUS_PENDING = 190;
        public static final int STATUS_PENDING_PLAY_STAT = 191;
        public static final int STATUS_PRECONDITION_FAILED = 412;
        public static final int STATUS_QUEUED_FOR_WIFI = 196;
        public static final int STATUS_RUNNING = 192;
        public static final int STATUS_SUCCESS = 200;
        public static final int STATUS_TIME_OUT = 501;
        public static final int STATUS_TOO_MANY_REDIRECTS = 497;
        public static final int STATUS_UNHANDLED_HTTP_CODE = 494;
        public static final int STATUS_UNHANDLED_REDIRECT = 493;
        public static final int STATUS_UNKNOWN_ERROR = 491;
        public static final int STATUS_WAITING_FOR_NETWORK = 195;
        public static final int STATUS_WAITING_TO_RETRY = 194;
        public static final int STATUS_XUNLEI_SPDY_EXCEPTION = 499;
        public static final int VISIBILITY_HIDDEN = 2;
        public static final int VISIBILITY_VISIBLE = 0;
        public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
        public static final String _DATA = "_data";

        public static class RequestHeaders {
            public static final String COLUMN_DOWNLOAD_ID = "download_id";
            public static final String COLUMN_HEADER = "header";
            public static final String COLUMN_VALUE = "value";
            public static final String HEADERS_DB_TABLE = "request_headers";
            public static final String INSERT_KEY_PREFIX = "http_header_";
            public static final String URI_SEGMENT = "headers";
        }

        private Impl() {
        }

        static {
            CONTENT_URI = Uri.parse("content://downloads/my_downloads");
            ALL_DOWNLOADS_CONTENT_URI = Uri.parse("content://downloads/all_downloads");
            PUBLICLY_ACCESSIBLE_DOWNLOADS_URI = Uri.parse("content://downloads/public_downloads");
        }

        public static boolean isStatusInformational(int i) {
            return i >= 100 && i <= 200;
        }

        public static boolean isStatusSuccess(int i) {
            return i >= 200 && i < 300;
        }

        public static boolean isStatusError(int i) {
            return i >= 400;
        }

        public static boolean isStatusClientError(int i) {
            return i >= 400 && i < 500;
        }

        public static boolean isStatusServerError(int i) {
            return i >= 500 && i < 600;
        }

        public static boolean isNotificationToBeDisplayed(int i) {
            return i == 1 || i == 3;
        }

        public static boolean isStatusCompleted(int i) {
            return (i >= 200 && i < 300) || i >= 400;
        }

        public static String statusToString(int i) {
            switch (i) {
                case STATUS_PENDING:
                    return "PENDING";
                case STATUS_RUNNING:
                    return "RUNNING";
                case STATUS_PAUSED_BY_APP:
                    return "PAUSED_BY_APP";
                case STATUS_WAITING_TO_RETRY:
                    return "WAITING_TO_RETRY";
                case STATUS_WAITING_FOR_NETWORK:
                    return "WAITING_FOR_NETWORK";
                case STATUS_QUEUED_FOR_WIFI:
                    return "QUEUED_FOR_WIFI";
                case STATUS_INSUFFICIENT_SPACE_ERROR:
                    return "INSUFFICIENT_SPACE_ERROR";
                case STATUS_DEVICE_NOT_FOUND_ERROR:
                    return "DEVICE_NOT_FOUND_ERROR";
                case STATUS_SUCCESS:
                    return HttpConstant.SUCCESS;
                case STATUS_BAD_REQUEST:
                    return "BAD_REQUEST";
                case STATUS_NOT_ACCEPTABLE:
                    return "NOT_ACCEPTABLE";
                case STATUS_LENGTH_REQUIRED:
                    return "LENGTH_REQUIRED";
                case STATUS_PRECONDITION_FAILED:
                    return "PRECONDITION_FAILED";
                case STATUS_FILE_ALREADY_EXISTS_ERROR:
                    return "FILE_ALREADY_EXISTS_ERROR";
                case STATUS_CANNOT_RESUME:
                    return "CANNOT_RESUME";
                case STATUS_CANCELED:
                    return "CANCELED";
                case STATUS_UNKNOWN_ERROR:
                    return "UNKNOWN_ERROR";
                case STATUS_FILE_ERROR:
                    return "FILE_ERROR";
                case STATUS_UNHANDLED_REDIRECT:
                    return "UNHANDLED_REDIRECT";
                case STATUS_UNHANDLED_HTTP_CODE:
                    return "UNHANDLED_HTTP_CODE";
                case STATUS_HTTP_DATA_ERROR:
                    return "HTTP_DATA_ERROR";
                case STATUS_HTTP_EXCEPTION:
                    return "HTTP_EXCEPTION";
                case STATUS_TOO_MANY_REDIRECTS:
                    return "TOO_MANY_REDIRECTS";
                case STATUS_BLOCKED:
                    return "BLOCKED";
                case STATUS_XUNLEI_SPDY_EXCEPTION:
                    return "XUNLEI_SPDY_EXCEPTION";
                case STATUS_PEER_NOT_FOUND_ERROR:
                    return "PEER_NOT_FOUND_ERROR";
                case STATUS_TIME_OUT:
                    return "time_out";
                default:
                    return Integer.toString(i);
            }
        }
    }

    private Downloads() {
    }

    public static final void removeAllDownloadsByPackage(Context context, String str, String str2) {
        try {
            context.getContentResolver().delete(Impl.CONTENT_URI, a, new String[]{str, str2});
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
    }

    public static final void removeAllDownloadsByPackage(Context context, Uri uri, String str, String str2) {
        try {
            context.getContentResolver().delete(uri, a, new String[]{str, str2});
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
    }
}
