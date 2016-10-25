package com.sina.weibo.sdk.constant;

public class WBConstants {
    public static final String ACTION_START_TIME = "other_app_action_start_time";
    public static final String ACTION_WEIBO_REGISTER = "com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER";
    public static final String ACTION_WEIBO_RESPONSE = "com.sina.weibo.sdk.Intent.ACTION_WEIBO_RESPONSE";
    public static final String ACTION_WEIBO_SDK_PERMISSION = "com.sina.weibo.permission.WEIBO_SDK_PERMISSION";
    public static final String ACTIVITY_REQ_SDK = "com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY";
    public static final String ACTIVITY_RESP_SDK = "com.sina.weibo.sdk.action.ACTION_SDK_RESP_ACTIVITY";
    public static final String ACTIVITY_WEIBO = "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY";
    public static final String ACTIVITY_WEIBO_PAY = "com.sina.weibo.sdk.action.ACTION_WEIBO_PAY_ACTIVITY";
    public static final String ACTIVITY_WEIBO_PAY_REQ = "com.sina.weibo.sdk.action.ACTION_SDK_REQ_PAY_ACTIVITY";
    public static final String AID = "aid";
    public static final String AUTH_ACCESS_TOKEN = "access_token";
    public static final String AUTH_PARAMS_AID = "aid";
    public static final String AUTH_PARAMS_CLIENT_ID = "client_id";
    public static final String AUTH_PARAMS_CLIENT_SECRET = "client_secret";
    public static final String AUTH_PARAMS_CODE = "code";
    public static final String AUTH_PARAMS_DISPLAY = "display";
    public static final String AUTH_PARAMS_GRANT_TYPE = "grant_type";
    public static final String AUTH_PARAMS_KEY_HASH = "key_hash";
    public static final String AUTH_PARAMS_PACKAGE_NAME = "packagename";
    public static final String AUTH_PARAMS_REDIRECT_URL = "redirect_uri";
    public static final String AUTH_PARAMS_RESPONSE_TYPE = "response_type";
    public static final String AUTH_PARAMS_SCOPE = "scope";
    public static final String AUTH_PARAMS_VERSION = "version";
    public static final int COMMAND_FROM_WEIBO = 2;
    public static final int COMMAND_PAY = 4;
    public static final int COMMAND_SSO = 3;
    public static final int COMMAND_TO_WEIBO = 1;
    public static final String COMMAND_TYPE_KEY = "_weibo_command_type";
    public static final String GAME_PARAMS_ACHIEVEMENT_ID = "achievement_id";
    public static final String GAME_PARAMS_DESCRIPTION = "description";
    public static final String GAME_PARAMS_GAME_ACHIEVEMENT_TITLE = "title";
    public static final String GAME_PARAMS_GAME_ACHIEVEMENT_TYPE_URL = "url";
    public static final String GAME_PARAMS_GAME_CREATE_TIME = "create_time";
    public static final String GAME_PARAMS_GAME_ID = "game_id";
    public static final String GAME_PARAMS_GAME_IMAGE_URL = "image";
    public static final String GAME_PARAMS_GAME_POINT = "game_point";
    public static final String GAME_PARAMS_SCORE = "score";
    public static final String GAME_PARAMS_SOURCE = "source";
    public static final String GAME_PARAMS_UID = "uid";
    public static final int SDK_ACTIVITY_FOR_RESULT_CODE = 765;
    public static final String SDK_DELIVER_ACTION = "com.sina.weibo.action.WB_SDK_ACTIVITY_DELIVER";
    public static final String SDK_IS_SCHEME = "sdk_is_scheme";
    public static final int SDK_NEW_PAY_VERSION = 1920;
    public static final String SDK_REAL_ACTION = "sdk_real_action";
    public static final String SDK_REDIRECT_URL = "sdk_redirect_uri";
    public static final String SDK_REQUESTCODE = "sdk_requestcode";
    public static final String SDK_WEOYOU_SHARECARDID = "shareCardId";
    public static final String SDK_WEOYOU_SHAREDESC = "shareDesc";
    public static final String SDK_WEOYOU_SHAREIMAGE = "shareImage";
    public static final String SDK_WEOYOU_SHARETITLE = "shareTitle";
    public static final String SDK_WEOYOU_SHAREURL = "shareUrl";
    public static final String SIGN = "_weibo_sign";
    public static final String SSO_APP_KEY = "appKey";
    public static final String SSO_KEY_HASH = "key_hash";
    public static final String SSO_PACKAGE_NAME = "packagename";
    public static final String SSO_REDIRECT_URL = "redirectUri";
    public static final String SSO_USER_SCOPE = "scope";
    public static final String TRAN = "_weibo_transaction";
    public static final String WEIBO_DOWNLOAD_URL = "http://app.sina.cn/appdetail.php?appID=84560";
    public static final int WEIBO_FLAG_SDK = 538116905;
    public static final String WEIBO_SDK_VERSION_CODE = "0031405000";
    public static final String WEIBO_SIGN = "18da2bf10352443a00a5e046d9fca6bd";

    public static interface Base {
        public static final String APP_KEY = "_weibo_appKey";
        public static final String APP_PKG = "_weibo_appPackage";
        public static final String SDK_VER = "_weibo_sdkVersion";
    }

    public static interface ErrorCode {
        public static final int ERR_CANCEL = 1;
        public static final int ERR_FAIL = 2;
        public static final int ERR_OK = 0;
    }

    public static interface Media {
        public static final String DESC = "_weibo_object_description";
        public static final String SDK_VER = "_weibo_object_sdkVer";
        public static final String THUMB_DATA = "_weibo_object_thumbdata";
        public static final String TITLE = "_weibo_object_title";
        public static final String URL = "_weibo_object_url";
    }

    public static interface Msg {
        public static final String IDENTIFY = "_weibo_message_identify";
        public static final String IMAGE = "_weibo_message_image";
        public static final String IMAGE_EXTRA = "_weibo_message_image_extra";
        public static final String MEDIA = "_weibo_message_media";
        public static final String MEDIA_EXTRA = "_weibo_message_media_extra";
        public static final String TEXT = "_weibo_message_text";
        public static final String TEXT_EXTRA = "_weibo_message_text_extra";
    }

    public static interface Response {
        public static final String ERRCODE = "_weibo_resp_errcode";
        public static final String ERRMSG = "_weibo_resp_errstr";
    }

    public static interface SDK {
        public static final String FLAG = "_weibo_flag";
    }
}
