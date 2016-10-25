package com.taobao.accs.common;

// compiled from: Taobao
public class Constants {
    public static final String ACCS_IMPL_NAME = "com.taobao.accs.internal.ACCSManagerImpl";
    public static final String ACTION_COMMAND = "com.taobao.accs.intent.action.COMMAND";
    public static final String ACTION_CONNECT_INFO = "com.taobao.accs.intent.action.CONNECTINFO";
    public static final String ACTION_ELECTION = "com.taobao.accs.intent.action.ELECTION";
    public static final String ACTION_GET_VERSION = "com.taobao.accs.intent.action.GET_VERSION";
    public static final String ACTION_PING = "org.agoo.android.intent.action.PING_V4";
    public static final String ACTION_RECEIVE = "com.taobao.accs.intent.action.RECEIVE";
    public static final String ACTION_SEND = "com.taobao.accs.intent.action.SEND";
    public static final String ACTION_SERVICE = "com.taobao.accs.intent.action.SERVICE";
    public static final String ACTION_START_FROM_AGOO = "com.taobao.accs.intent.action.START_FROM_AGOO";
    public static final String ACTION_START_SERVICE = "com.taobao.accs.intent.action.START_SERVICE";
    public static final String ALIYUN_SERVICE_HOST = "accscdn4public.m.taobao.com";
    public static final String ALIYUN_SERVICE_IP = "140.205.166.95";
    public static final long CLIENT_FLUSH_INTERVAL = 86400000;
    public static final int COMMAND_ANTI_BRUSH = 104;
    public static final int COMMAND_BIND_APP = 1;
    public static final int COMMAND_BIND_SERVICE = 5;
    public static final int COMMAND_BIND_USER = 3;
    public static final int COMMAND_CONNECT_INFO = 103;
    public static final int COMMAND_ELECTION = 105;
    public static final int COMMAND_GET_VERSION = 401;
    public static final int COMMAND_HANDSHAKE = 200;
    public static final int COMMAND_PING = 201;
    public static final int COMMAND_RECEIVE_DATA = 101;
    public static final int COMMAND_ROUTING_ACK = 106;
    public static final int COMMAND_SEND_DATA = 100;
    public static final int COMMAND_SEND_STATIST = 102;
    public static final int COMMAND_STOP_FOR_ELECTION = 301;
    public static final int COMMAND_UNBIND_APP = 2;
    public static final int COMMAND_UNBIND_SERVICE = 6;
    public static final int COMMAND_UNBIND_USER = 4;
    public static final int COMPRESS_GZIP = 1;
    public static final int COMPRESS_NONE = 0;
    public static final String DB_NAME = "accs.db";
    public static final int DB_VERSION = 3;
    public static final int DEBUG = 2;
    public static final int ELECTION_MODE_CLIENT = 0;
    public static final int ELECTION_MODE_SERVER = 1;
    public static final int ELECTION_VERSION = 1;
    public static final String KEY_ANTI_BRUSH_COOKIE = "anti_brush_cookie";
    public static final String KEY_ANTI_BRUSH_RET = "anti_brush_ret";
    public static final String KEY_APPS = "apps";
    public static final String KEY_APP_KEY = "appKey";
    public static final String KEY_APP_SECRET = "app_sercet";
    public static final String KEY_APP_VERSION = "appVersion";
    public static final String KEY_APP_VERSION_CODE = "appVersionCode";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_BUSINESSID = "businessId";
    public static final String KEY_CENTER_HOST = "is_center_host";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_CONNECT_AVAILABLE = "connect_avail";
    public static final String KEY_CONNECT_INFO = "connect_info";
    public static final String KEY_DATA = "data";
    public static final String KEY_DATA_ID = "dataId";
    public static final String KEY_DEVICE_ID = "deviceId";
    public static final String KEY_DEVICE_TOKEN = "accsToken";
    public static final String KEY_ELECTION_MODE = "election_mode";
    public static final String KEY_ELECTION_PACKS = "packs";
    public static final String KEY_ELECTION_PKG = "pkg";
    public static final String KEY_ELECTION_SDKV = "sdkv";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_DETAIL = "errorDetail";
    public static final String KEY_EXT_TAG = "extTag";
    public static final String KEY_FLAGS = "flags";
    public static final String KEY_FOUCE_BIND = "fouce_bind";
    public static final String KEY_FOUCE_DISABLE = "fouce_disable";
    public static final String KEY_HOST = "host";
    public static final String KEY_HTTP_CODE = "code";
    public static final String KEY_IMEI = "imei";
    public static final String KEY_IMSI = "imsi";
    public static final String KEY_LAST_TRY_UPLOAD_COUNT = "last_try_upload_count";
    public static final String KEY_LAST_UPLOAD_TIME = "lastUpload";
    public static final String KEY_MODEL = "model";
    public static final String KEY_NEED_BUSINESS_ACK = "bizAck";
    public static final String KEY_OS_TYPE = "osType";
    public static final String KEY_OS_VERSION = "os";
    public static final String KEY_PACKAGE_NAME = "packageName";
    public static final String KEY_PACKAGE_NAMES = "packageNames";
    public static final String KEY_PROXY_HOST = "proxy_host";
    public static final String KEY_PROXY_PORT = "proxy_port";
    public static final String KEY_SDK_VERSION = "sdkVersion";
    public static final String KEY_SECURITY_SIGN = "sign";
    public static final String KEY_SEND_REQDATA = "reqdata";
    public static final String KEY_SEND_TYPE = "send_type";
    public static final String KEY_SERVICE_ID = "serviceId";
    public static final String KEY_SID = "sid";
    public static final String KEY_SOURCE = "source";
    public static final String KEY_TARGET = "target";
    public static final String KEY_TTID = "ttid";
    public static final String KEY_TYPE_INAPP = "type_inapp";
    public static final String KEY_USER_ID = "userInfo";
    public static final boolean LOG = true;
    public static final int NODE_FRONT = 0;
    public static final int NODE_REAR = 1;
    public static final int OS_ANDROID = 1;
    public static final int PORT = 443;
    public static final int PREVIEW = 1;
    public static final byte PROTOCOL_VERSION = (byte) 2;
    public static final String RECEIVER_IMPL_NAME = "com.taobao.accs.internal.ReceiverImpl";
    public static final int RELEASE = 0;
    public static final int SDK_VERSION_CODE = 212;
    public static final String SEND_TYPE_RES = "res";
    public static final String SERVICE_HOST = "accscdn.m.taobao.com";
    public static final String SERVICE_IMPL_NAME = "com.taobao.accs.internal.ServiceImpl";
    public static final String SHARED_FOLDER = "accs";
    public static final String SHARED_MESSAGE_ID_FILE = "message";
    public static final String SHARED_VERSION_FILE = "version";
    public static final String SOCKET_PORT = "73B5B6060CA4F42C20EA4C5501EBCCBA";
    public static final String SP_APP_SECRET = "app_sercet";
    public static final String SP_CHANNEL_FILE_NAME = "ACCS_SDK_CHANNEL";
    public static final String SP_COOKIE_FILE_NAME = "ACCS_COOKIE";
    public static final String SP_FILE_NAME = "ACCS_SDK";
    public static final String SP_KEY_APPKEY = "appkey";
    public static final String SP_KEY_COOKIE_EXPIRED = "cookie_expired";
    public static final String SP_KEY_COOKIE_SEC = "cookie_sec";
    public static final String SP_KEY_DEBUG_MODE = "debug_mode";
    public static final String SP_KEY_ELECTION_ENABLE = "election_enable";
    public static final String SP_KEY_ELECTION_RESULT = "election_result";
    public static final String SP_KEY_ELECTION_VERSION = "election_version";
    public static final String SP_KEY_HB_SMART_ENABLE = "smart_hb_enable";
    public static final String SP_KEY_LOAD_SO_TIMES = "load_so_times";
    public static final String SP_KEY_SERVICE_END = "service_end";
    public static final String SP_KEY_SERVICE_START = "service_start";
    public static final String SP_KEY_TNET_LOG_OFF = "tnet_log_off";
    public static final String SP_KEY_UPDATE_DONE = "update_done";
    public static final String SP_KEY_UPDATE_FOLDER = "update_folder";
    public static final String SP_KEY_UPDATE_VERSION = "update_verion";
    public static final String SP_KEY_UTDID = "utdid";
    public static final String SP_KEY_VERSION = "version";
    public static final String SP_LOAD_SO_FILE_NAME = "ACCS_LOAD_SO";
    public static final String SP_TT_ID = "app_tt_id";
    public static final String SP_UPLOAD_ACCS_POLICY = "upload_accs_policy";
    public static final int ST_TRY_UPLOAD_MAX_COUNT = 10;
    public static final long ST_UPLOAD_MAX_COUNT = 200;
    public static final long ST_UPLOAD_TIME_INTERVAL = 14400000;
    public static final String TARGET_CONTROL = "3|dm|";
    public static final String TARGET_ELECTION = "4|sal|el";
    public static final String TARGET_SERVICE_PRE = "2|";
    public static final String TARGET_STATIST = "4|sal|st";
    public static final String UPDATE_DEXOPT_FILE = "accs.dex";
    public static final String UPDATE_DEX_FILE = "accs.zip";
    public static final boolean UT_OFF = false;
    public static final boolean WJAS_OFF = true;

    // compiled from: Taobao
    public enum Operate {
        ASK_VERSION(0),
        REPORT_VERSION(1),
        TRY_ELECTION(2),
        START_ELECTION(3),
        RESULT_ELECTION(4),
        PING_ELECTION(5);
        private int a;

        private Operate(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }
}
