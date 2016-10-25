package com.xunlei.download;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.alipay.sdk.util.h;
import com.android.providers.downloads.DownloadProvider;
import com.android.providers.downloads.XlTaskHelper;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.connect.dataprovider.ErrorCode;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.DownloadManager.Request;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.download.proguard.ab;
import com.xunlei.download.proguard.ah;
import com.xunlei.download.proguard.ak;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import com.xunlei.download.proguard.n;
import com.xunlei.download.proguard.r;
import com.xunlei.download.proguard.v;
import com.xunlei.download.proguard.y;
import com.xunlei.download.proguard.z;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.GetFileName;
import com.xunlei.downloadlib.parameter.MaxDownloadSpeedParam;
import com.xunlei.downloadlib.parameter.XLTaskLocalUrl;
import com.xunlei.tdlive.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DownloadManager {
    public static final String ACTION_DOWNLOAD_COMPLETE = "android.intent.action.DOWNLOAD_COMPLETE";
    @Deprecated
    public static final String ACTION_DOWNLOAD_START_OR_COMPLETE = "android.intent.action.DOWNLOAD_START_OR_COMPLETE";
    public static final String ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
    public static final String ACTION_VIEW_DOWNLOADS = "android.intent.action.VIEW_DOWNLOADS";
    public static final String COLUMN_ADDITION_LX_SPEED = "addition_lx_speed";
    public static final String COLUMN_ADDITION_VIP_SPEED = "addition_vip_speed";
    public static final String COLUMN_ALLOW_WRITE = "allow_write";
    public static final String COLUMN_APK_PACKAGE = "apk_package";
    public static final String COLUMN_APK_VERSION = "apk_version";
    public static final String COLUMN_BT_PARENT_ID = "bt_parent_id";
    public static final String COLUMN_BT_SELECT_SET = "bt_select_set";
    public static final String COLUMN_BT_SUB_INDEX = "bt_sub_index";
    public static final String COLUMN_BT_SUB_IS_SELECTED = "bt_sub_is_selected";
    public static final String COLUMN_BYTES_DOWNLOADED_SO_FAR = "bytes_so_far";
    public static final String COLUMN_CID = "cid";
    public static final String COLUMN_CREATE_TIME = "create_time";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DOWNLOAD_DURATION = "download_duration";
    public static final String COLUMN_DOWNLOAD_SPEED = "download_speed";
    public static final String COLUMN_ERROR_MSG = "errorMsg";
    public static final String COLUMN_GCID = "gcid";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IS_LX_SPEEDUP = "is_lx_speedup";
    public static final String COLUMN_IS_VIP_SPEEDUP = "is_vip_speedup";
    public static final String COLUMN_LAST_MODIFIED_TIMESTAMP = "last_modified_timestamp";
    public static final String COLUMN_LOCAL_FILENAME = "local_filename";
    public static final String COLUMN_LOCAL_URI = "local_uri";
    public static final String COLUMN_LX_PROGRESS = "lx_progress";
    public static final String COLUMN_LX_RECEIVE_SIZE = "lx_receive_size";
    public static final String COLUMN_LX_STATUS = "lx_status";
    public static final String COLUMN_MEDIAPROVIDER_URI = "mediaprovider_uri";
    public static final String COLUMN_MEDIA_TYPE = "media_type";
    public static final String COLUMN_ORIGIN_RECEIVE_SIZE = "origin_receive_size";
    public static final String COLUMN_ORIGIN_SPEED = "origin_speed";
    public static final String COLUMN_P2P_RECEIVE_SIZE = "p2p_receive_size";
    public static final String COLUMN_P2P_SPEED = "p2p_speed";
    public static final String COLUMN_P2S_RECEIVE_SIZE = "p2s_receive_size";
    public static final String COLUMN_P2S_SPEED = "p2s_speed";
    public static final String COLUMN_REASON = "reason";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_STATUS_ORIGINAL = "status_original";
    public static final String COLUMN_TASK_TYPE = "task_type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TOTAL_SIZE_BYTES = "total_size";
    public static final String COLUMN_URI = "uri";
    public static final String COLUMN_VIP_RECEIVE_SIZE = "vip_receive_size";
    public static final String COLUMN_VIP_STATUS = "vip_status";
    public static final String COLUMN_XUNLEI_SPDY = "xunlei_spdy";
    public static final String DB_PATH_KEY = "db_path";
    public static final int ERROR_BLOCKED = 1010;
    public static final int ERROR_CANNOT_RESUME = 1008;
    public static final int ERROR_DEVICE_NOT_FOUND = 1007;
    public static final int ERROR_FILE_ALREADY_EXISTS = 1009;
    public static final int ERROR_FILE_ERROR = 1001;
    public static final int ERROR_HTTP_CLIENT_ERROR = 1012;
    public static final int ERROR_HTTP_DATA_ERROR = 1004;
    public static final int ERROR_HTTP_SERVER_ERROR = 1011;
    public static final int ERROR_INSUFFICIENT_SPACE = 1006;
    public static final int ERROR_TOO_MANY_REDIRECTS = 1005;
    public static final int ERROR_UNHANDLED_HTTP_CODE = 1002;
    public static final int ERROR_UNKNOWN = 1000;
    public static final String EXTRA_DOWNLOAD_ID = "extra_download_id";
    public static final String EXTRA_DOWNLOAD_STATE = "extra_download_state";
    public static final String EXTRA_DOWNLOAD_STATE_ORIGINAL = "extra_download_state_original";
    public static final String EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS = "extra_click_download_ids";
    public static final String INTENT_EXTRAS_SORT_BY_SIZE = "android.app.DownloadManager.extra_sortBySize";
    public static final int INVAlID_TASK_ID = -1;
    public static final String KEY_APP_KEY = "com.xunlei.download.APP_KEY";
    public static final String KEY_DOWNLOAD_SPEED_LIMIT = "com.xunlei.download.DOWNLOAD_SPEED_LIMIT";
    public static final String KEY_MAX_BYTES_OVER_MOBILE = "com.xunlei.download.MAX_BYTES_OVER_MOBILE";
    public static final String KEY_MAX_CONCURRENT_DOWNLOADS = "com.xunlei.download.MAX_CONCURRENT_DOWNLOADS";
    public static final String KEY_PRODUCT_NAME = "com.xunlei.download.PRODUCT_NAME";
    public static final String KEY_RECOMMENDED_MAX_BYTES_OVER_MOBILE = "com.xunlei.download.RECOMMENDED_MAX_BYTES_OVER_MOBILE";
    public static final String KEY_RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS = "com.xunlei.download.RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS";
    public static final String KEY_RECOMMENDED_MAX_CONCURRENT_DOWNLOADS = "com.xunlei.download.RECOMMENDED_MAX_CONCURRENT_DOWNLOADS";
    public static final String KEY_SHOW_NOTIFY = "com.xunlei.download.SHOW_NOTIFY";
    public static final String KEY_UPLOAD_SPEED_LIMIT = "com.xunlei.download.UPLOAD_SPEED_LIMIT";
    public static final String LOG_TAG = "DownloadManager";
    public static final long MAX_BYTES_OVER_MOBILE = 4194304;
    public static final int MAX_CONCURRENT_DOWNLOADS = 10;
    public static final int PAUSED_BY_APP = 5;
    public static final int PAUSED_QUEUED_FOR_WIFI = 3;
    public static final int PAUSED_UNKNOWN = 4;
    public static final int PAUSED_WAITING_FOR_NETWORK = 2;
    public static final int PAUSED_WAITING_TO_RETRY = 1;
    public static final long RECOMMENDED_MAX_BYTES_OVERMOBILE = 524288;
    public static final int RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS = 3;
    public static final int RECOMMENDED_MAX_CONCURRENT_DOWNLOADS = 5;
    public static final String SDK_VERSION = "1.0.0.9";
    public static final boolean SHOW_NOTIFY = true;
    public static final int STATUS_FAILED = 16;
    public static final int STATUS_PAUSED = 4;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_SUCCESSFUL = 8;
    public static final String TASK_GROUP_URI_PREFIX = "group://";
    public static final String[] UNDERLYING_COLUMNS;
    static final /* synthetic */ boolean a;
    private static final int b = 31;
    private static long g = 0;
    private static long h = 0;
    private static DownloadManager i = null;
    private static final String j = "non-dwnldmngr-download-dont-retry2download";
    private ContentResolver c;
    private Context d;
    private String e;
    private Uri f;

    public static class CursorTranslator extends CursorWrapper {
        private Uri a;

        public CursorTranslator(Cursor cursor, Uri uri) {
            super(cursor);
            this.a = uri;
        }

        public int getInt(int i) {
            return (int) getLong(i);
        }

        public long getLong(int i) {
            if (getColumnName(i).equals(COLUMN_REASON)) {
                return DownloadManager.getReason(super.getInt(getColumnIndex(COLUMN_STATUS)));
            }
            if (getColumnName(i).equals(COLUMN_STATUS)) {
                return (long) DownloadManager.translateStatus(super.getInt(getColumnIndex(COLUMN_STATUS)));
            }
            if (getColumnName(i).equals(COLUMN_LX_STATUS)) {
                return (long) DownloadManager.translateStatus(super.getInt(getColumnIndex(COLUMN_LX_STATUS)));
            }
            if (getColumnName(i).equals(COLUMN_VIP_STATUS)) {
                return (long) DownloadManager.translateStatus(super.getInt(getColumnIndex(COLUMN_VIP_STATUS)));
            }
            return getColumnName(i).equals(COLUMN_STATUS_ORIGINAL) ? (long) super.getInt(getColumnIndex(COLUMN_STATUS)) : super.getLong(i);
        }

        public String getString(int i) {
            return getColumnName(i).equals(COLUMN_LOCAL_URI) ? a() : super.getString(i);
        }

        private String a() {
            long j = getLong(getColumnIndex(Impl.COLUMN_DESTINATION));
            if (j == 4 || j == 0 || j == 6) {
                String string = getString(getColumnIndex(COLUMN_LOCAL_FILENAME));
                return string == null ? null : Uri.fromFile(new File(string)).toString();
            } else {
                return ContentUris.withAppendedId(this.a, getLong(getColumnIndex(COLUMN_ID))).toString();
            }
        }
    }

    public class DownloadManagerException extends r {
        public DownloadManagerException(int i, String str) {
            super(i, str);
        }
    }

    public static class Request {
        public static final int NETWORK_BLUETOOTH = 4;
        public static final int NETWORK_MOBILE = 1;
        public static final int NETWORK_MOBILE_ONCE = 8;
        public static final int NETWORK_WIFI = 2;
        public static final int VISIBILITY_HIDDEN = 2;
        public static final int VISIBILITY_VISIBLE = 0;
        public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
        public static final int VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION = 3;
        static final /* synthetic */ boolean f;
        private static final int w = 0;
        private static final int x = 2;
        protected Uri a;
        protected Uri b;
        boolean c;
        CharSequence d;
        String e;
        private List<Pair<String, String>> g;
        private CharSequence h;
        private String i;
        private int j;
        private boolean k;
        private boolean l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        private boolean q;
        private String r;
        private String s;
        private boolean t;
        private long u;
        private boolean v;
        private int y;

        static {
            f = !DownloadManager.class.desiredAssertionStatus() ? SHOW_NOTIFY : f;
        }

        public Request(Uri uri) {
            this.c = false;
            this.g = new ArrayList();
            this.j = -1;
            this.k = true;
            this.l = true;
            this.m = true;
            this.n = false;
            this.o = false;
            this.p = false;
            this.q = true;
            this.r = null;
            this.s = null;
            this.t = true;
            this.u = 0;
            this.v = false;
            this.e = null;
            this.y = 0;
            if (uri == null) {
                throw new NullPointerException();
            }
            this.a = uri;
        }

        Request(String str) {
            this(Uri.parse(str));
        }

        public void setTaskGroup(long j) {
            this.u = j;
        }

        public com.xunlei.download.DownloadManager.Request setDestinationUri(String str, String str2) {
            if (str2 == null || str2.length() == 0) {
                str2 = "{filename}";
            }
            setDestinationUri(Uri.fromFile(new File(str, str2)));
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDestinationUri(Uri uri) {
            this.b = uri;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDestinationToSystemCache() {
            this.o = true;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDestinationInExternalFilesDir(Context context, String str, String str2) {
            File externalFilesDir = context.getExternalFilesDir(str);
            if (externalFilesDir == null) {
                throw new IllegalStateException("Failed to get external storage files directory");
            }
            if (externalFilesDir.exists()) {
                if (!externalFilesDir.isDirectory()) {
                    throw new IllegalStateException(externalFilesDir.getAbsolutePath() + " already exists and is not a directory");
                }
            } else if (!externalFilesDir.mkdirs()) {
                throw new IllegalStateException(new StringBuilder("Unable to create directory: ").append(externalFilesDir.getAbsolutePath()).toString());
            }
            a(externalFilesDir, str2);
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDestinationInExternalPublicDir(String str, String str2) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(str);
            if (externalStoragePublicDirectory == null) {
                throw new IllegalStateException("Failed to get external storage public directory");
            }
            if (externalStoragePublicDirectory.exists()) {
                if (!externalStoragePublicDirectory.isDirectory()) {
                    throw new IllegalStateException(externalStoragePublicDirectory.getAbsolutePath() + " already exists and is not a directory");
                }
            } else if (!externalStoragePublicDirectory.mkdirs()) {
                throw new IllegalStateException(new StringBuilder("Unable to create directory: ").append(externalStoragePublicDirectory.getAbsolutePath()).toString());
            }
            a(externalStoragePublicDirectory, str2);
            return this;
        }

        private void a(File file, String str) {
            if (str == null) {
                throw new NullPointerException("subPath cannot be null");
            }
            this.b = Uri.withAppendedPath(Uri.fromFile(file), str);
        }

        public void allowScanningByMediaScanner() {
            this.n = true;
        }

        public com.xunlei.download.DownloadManager.Request addRequestHeader(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("header cannot be null");
            } else if (str.contains(":")) {
                throw new IllegalArgumentException("header may not contain ':'");
            } else {
                if (str2 == null) {
                    str2 = a.d;
                }
                this.g.add(Pair.create(str, str2));
                return this;
            }
        }

        public com.xunlei.download.DownloadManager.Request setTitle(CharSequence charSequence) {
            this.d = charSequence.toString().trim();
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDescription(CharSequence charSequence) {
            this.h = charSequence;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setMimeType(String str) {
            this.i = str;
            return this;
        }

        @Deprecated
        public com.xunlei.download.DownloadManager.Request setShowRunningNotification(boolean z) {
            return z ? setNotificationVisibility(w) : setNotificationVisibility(x);
        }

        public com.xunlei.download.DownloadManager.Request setNotificationVisibility(int i) {
            this.y = i;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setAllowedNetworkTypes(int i) {
            if (i == 1) {
                am.d(LOG_TAG, "must include NETWORK_WIFI");
            } else {
                this.j = i;
            }
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setAllowedOverRoaming(boolean z) {
            this.k = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setAllowedOverMetered(boolean z) {
            this.l = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setVisibleInDownloadsUi(boolean z) {
            this.m = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDownloadDelay(boolean z) {
            this.p = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDownloadSpdy(boolean z) {
            this.q = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setBtSelectSet(long[] jArr) {
            StringBuilder stringBuilder = new StringBuilder();
            int length = jArr.length;
            for (int i = w; i < length; i++) {
                stringBuilder.append(jArr[i]).append(h.b);
            }
            this.r = stringBuilder.toString();
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setBtSelectSet(int[] iArr) {
            StringBuilder stringBuilder = new StringBuilder();
            int length = iArr.length;
            for (int i = w; i < length; i++) {
                stringBuilder.append(iArr[i]).append(h.b);
            }
            this.r = stringBuilder.toString();
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setBtInfoHash(String str) {
            this.s = str;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setAllowedAutoResume(boolean z) {
            this.t = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request forceDownloadInMobileNetwork() {
            this.c = true;
            return this;
        }

        public com.xunlei.download.DownloadManager.Request setDownloadTaskXLOrigin(String str) {
            this.e = str;
            return this;
        }

        ContentValues a(Context context, String str) {
            ContentValues contentValues = new ContentValues();
            if (f || this.a != null) {
                contentValues.put(COLUMN_URI, this.a.toString());
                contentValues.put(Impl.COLUMN_IS_PUBLIC_API, Boolean.valueOf(SHOW_NOTIFY));
                contentValues.put(Impl.COLUMN_NOTIFICATION_PACKAGE, str);
                if (this.b != null) {
                    contentValues.put(Impl.COLUMN_DESTINATION, Integer.valueOf(NETWORK_BLUETOOTH));
                    contentValues.put(Impl.COLUMN_FILE_NAME_HINT, this.b.toString());
                } else {
                    contentValues.put(Impl.COLUMN_DESTINATION, Integer.valueOf(this.o ? RECOMMENDED_MAX_CONCURRENT_DOWNLOADS : 2));
                }
                contentValues.put(Impl.COLUMN_MEDIA_SCANNED, Integer.valueOf(this.n ? w : 2));
                if (!this.g.isEmpty()) {
                    a(contentValues);
                }
                if (this.d == null) {
                    GetFileName getFileName = new GetFileName();
                    String b = z.b(context, this.a.toString());
                    if (!b.startsWith("file://")) {
                        if (XLDownloadManager.getInstance(context).getFileNameFromUrl(b, getFileName) != 9000 || TextUtils.isEmpty(getFileName.getFileName())) {
                            contentValues.put(COLUMN_TITLE, "download ");
                            am.e(LOG_TAG, new StringBuilder("getFileNameFromUrl Failed,url=").append(b).toString());
                        } else {
                            contentValues.put(COLUMN_TITLE, getFileName.getFileName() + " ");
                        }
                    }
                } else {
                    contentValues.put(COLUMN_TITLE, this.d.toString());
                }
                a(contentValues, COLUMN_DESCRIPTION, this.h);
                a(contentValues, Impl.COLUMN_MIME_TYPE, this.i);
                if (this.m) {
                    contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(this.y));
                } else {
                    contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(x));
                }
                contentValues.put(Impl.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(this.j));
                contentValues.put(Impl.COLUMN_ALLOW_ROAMING, Boolean.valueOf(this.k));
                contentValues.put(Impl.COLUMN_ALLOW_METERED, Boolean.valueOf(this.l));
                contentValues.put(Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, Boolean.valueOf(this.m));
                if (this.p) {
                    contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(VISIBILITY_VISIBLE_NOTIFY_COMPLETED));
                    contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PAUSED_BY_APP));
                }
                if (this.r != null) {
                    contentValues.put(COLUMN_BT_SELECT_SET, this.r);
                }
                if (this.c) {
                    contentValues.put(Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, Integer.valueOf(VISIBILITY_VISIBLE_NOTIFY_COMPLETED));
                }
                if (this.s != null) {
                    contentValues.put(Impl.COLUMN_BT_INFO_HASH, this.s);
                }
                contentValues.put(COLUMN_XUNLEI_SPDY, Boolean.valueOf(this.q));
                contentValues.put(Impl.COLUMN_ALLOW_AUTO_RESUME, Boolean.valueOf(this.t));
                if (this.u != 0) {
                    contentValues.put(Impl.COLUMN_GROUP_ID, Long.valueOf(this.u));
                } else if (contentValues.containsKey(Impl.COLUMN_ALLOWED_NETWORK_TYPES) && contentValues.getAsInteger(Impl.COLUMN_ALLOWED_NETWORK_TYPES).intValue() == 8) {
                    contentValues.put(Impl.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(x));
                    this.v = true;
                }
                a(contentValues, Impl.COLUMN_XL_ORIGIN, this.e);
                return contentValues;
            }
            throw new AssertionError();
        }

        boolean a() {
            return this.v;
        }

        private void a(ContentValues contentValues) {
            int i = 0;
            for (Pair pair : this.g) {
                contentValues.put(new StringBuilder(RequestHeaders.INSERT_KEY_PREFIX).append(i).toString(), ((String) pair.first) + ": " + ((String) pair.second));
                i++;
            }
        }

        private void a(ContentValues contentValues, String str, Object obj) {
            if (obj != null) {
                contentValues.put(str, obj.toString());
            }
        }
    }

    public static class GroupRequest extends Request {
        private ArrayList<Request> g;

        public GroupRequest() {
            this(null);
        }

        public GroupRequest(String[] strArr) {
            super(a.d);
            this.g = new ArrayList();
            if (strArr != null) {
                if (this.d == null) {
                    this.d = DownloadManager.b(strArr);
                }
                for (String str : strArr) {
                    if (str.startsWith("file://")) {
                        am.d(LOG_TAG, "BTTask can not be added as a subtask.");
                    } else {
                        this.g.add(new Request(Uri.parse(str)));
                    }
                }
            }
            this.a = Uri.parse(new StringBuilder(TASK_GROUP_URI_PREFIX).append(String.valueOf((System.currentTimeMillis() + this).hashCode())).toString());
        }

        public com.xunlei.download.DownloadManager.GroupRequest addSubRequest(Request request) {
            if (request == null) {
                throw new IllegalArgumentException("null argument");
            } else if (request instanceof com.xunlei.download.DownloadManager.GroupRequest) {
                throw new IllegalArgumentException("GroupTask can not be added as a subtask.");
            } else {
                if (request.a.toString().startsWith("file://")) {
                    am.d(LOG_TAG, "BTTask can not be added as a subtask.");
                } else {
                    this.g.add(request);
                }
                return this;
            }
        }

        public ArrayList<Request> getSubList() {
            return this.g;
        }

        ContentValues a(Context context, String str) {
            if (this.d == null) {
                this.d = new StringBuilder("TaskGroup").append(this.a.getHost()).toString();
            }
            return super.a(context, str);
        }
    }

    public static class Property {
        public static final String PROP_JUMP_KEY = "prop:xl_jump_key";
        public static final String PROP_SESSION_ID = "prop:xl_session_id";
        public static final String PROP_TOKEN = "prop:xl_token";
        public static final String PROP_USER_ID = "prop:xl_user_id";
    }

    public static class Query {
        public static final int ORDER_ASCENDING = 1;
        public static final int ORDER_DESCENDING = 2;
        private long[] a;
        private Integer b;
        private String c;
        private int d;
        private boolean e;
        private boolean f;
        private String[] g;

        public Query() {
            this.a = null;
            this.b = null;
            this.c = Impl.COLUMN_LAST_MODIFICATION;
            this.d = 2;
            this.e = false;
            this.f = false;
            this.g = null;
        }

        public com.xunlei.download.DownloadManager.Query setFilterById(long... jArr) {
            this.a = jArr;
            return this;
        }

        public com.xunlei.download.DownloadManager.Query setProjection(String... strArr) {
            this.g = strArr;
            return this;
        }

        public String[] getProjection() {
            return this.g == null ? UNDERLYING_COLUMNS : this.g;
        }

        public com.xunlei.download.DownloadManager.Query setFilterByStatus(int i) {
            if ((i & 31) != 0) {
                this.b = Integer.valueOf(i);
                return this;
            }
            throw new IllegalArgumentException("invaild status.");
        }

        public com.xunlei.download.DownloadManager.Query setOnlyIncludeVisibleInDownloadsUi(boolean z) {
            this.e = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Query setOnlyIncludeMainTasks(boolean z) {
            this.f = z;
            return this;
        }

        public com.xunlei.download.DownloadManager.Query orderBy(String str, int i) {
            if (i == 1 || i == 2) {
                if (str.equals(COLUMN_LAST_MODIFIED_TIMESTAMP)) {
                    this.c = Impl.COLUMN_LAST_MODIFICATION;
                } else if (str.equals(COLUMN_TOTAL_SIZE_BYTES)) {
                    this.c = d.b;
                } else if (str.equals(COLUMN_ID)) {
                    this.c = COLUMN_ID;
                } else {
                    this.c = str;
                }
                this.d = i;
                return this;
            }
            throw new IllegalArgumentException(new StringBuilder("Invalid direction: ").append(i).toString());
        }

        Cursor a(ContentResolver contentResolver, Uri uri) {
            try {
                return contentResolver.query(uri, getProjection(), getSelection(), getSelectionArgs(), getSortOrder());
            } catch (Throwable e) {
                e.printStackTrace();
                am.a(e);
                return null;
            }
        }

        public String getSelection() {
            Iterable arrayList = new ArrayList();
            if (this.a != null) {
                arrayList.add(DownloadManager.a(this.a));
            }
            if (this.b != null) {
                Iterable arrayList2 = new ArrayList();
                if ((this.b.intValue() & 1) != 0) {
                    arrayList2.add(a("=", (int) Impl.STATUS_PENDING));
                }
                if ((this.b.intValue() & 2) != 0) {
                    arrayList2.add(a("=", (int) Impl.STATUS_RUNNING));
                }
                if ((this.b.intValue() & 4) != 0) {
                    arrayList2.add(a("=", (int) Impl.STATUS_PAUSED_BY_APP));
                    arrayList2.add(a("=", (int) Impl.STATUS_WAITING_TO_RETRY));
                    arrayList2.add(a("=", (int) Impl.STATUS_WAITING_FOR_NETWORK));
                    arrayList2.add(a("=", (int) Impl.STATUS_QUEUED_FOR_WIFI));
                }
                if ((this.b.intValue() & 8) != 0) {
                    arrayList2.add(a("=", (int) Impl.STATUS_SUCCESS));
                }
                if ((this.b.intValue() & 16) != 0) {
                    arrayList2.add(a(">=", (int) Impl.STATUS_BAD_REQUEST));
                    arrayList2.add(a("=", (int) Impl.STATUS_INSUFFICIENT_SPACE_ERROR));
                    arrayList2.add(a("=", (int) Impl.STATUS_DEVICE_NOT_FOUND_ERROR));
                }
                arrayList.add(a(b.d, arrayList2));
            }
            if (this.e) {
                arrayList.add("is_visible_in_downloads_ui != '0'");
            }
            if (this.f) {
                arrayList.add("group_id = '0'");
            }
            arrayList.add("deleted != '1'");
            return a(b.c, arrayList);
        }

        public String getSortOrder() {
            return this.c + " " + (this.d == 1 ? "ASC" : "DESC");
        }

        public String[] getSelectionArgs() {
            return this.a != null ? DownloadManager.b(this.a) : new String[0];
        }

        private String a(String str, Iterable<String> iterable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SocializeConstants.OP_OPEN_PAREN);
            int i = 1;
            for (String str2 : iterable) {
                if (r1 == null) {
                    stringBuilder.append(str);
                }
                stringBuilder.append(str2);
                Object obj = null;
            }
            stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
            return stringBuilder.toString();
        }

        private String a(String str, int i) {
            return new StringBuilder(COLUMN_STATUS).append(str).append("'").append(i).append("'").toString();
        }
    }

    public static class TaskImportRequest extends Request {
        private long g;
        private long h;

        public TaskImportRequest(Uri uri, long j, long j2) {
            super(uri);
            if (j <= 0 || (j2 != -1 && j2 < j)) {
                throw new IllegalArgumentException("Illegal Argumment (currentBytes<=0 || (totalBytes != -1 && totalBytes<currentBytes)");
            }
            this.g = j;
            this.h = j2;
        }

        ContentValues a(Context context, String str) {
            if (this.b == null) {
                throw new IllegalArgumentException("Destination need to be set using setDestinationUri");
            }
            setDownloadSpdy(SHOW_NOTIFY);
            ContentValues a = super.a(context, str);
            a.put(Impl._DATA, this.b.getPath());
            a.put(d.b, Long.valueOf(this.h));
            a.put(Impl.COLUMN_CURRENT_BYTES, Long.valueOf(this.g));
            return a;
        }
    }

    public enum TaskType {
        UNKOWN,
        HTTP,
        FTP,
        MAGNET,
        BT,
        ED2K,
        CID,
        GROUP;

        static {
            UNKOWN = new com.xunlei.download.DownloadManager.TaskType("UNKOWN", 0);
            HTTP = new com.xunlei.download.DownloadManager.TaskType("HTTP", 1);
            FTP = new com.xunlei.download.DownloadManager.TaskType("FTP", 2);
            MAGNET = new com.xunlei.download.DownloadManager.TaskType("MAGNET", 3);
            BT = new com.xunlei.download.DownloadManager.TaskType("BT", 4);
            ED2K = new com.xunlei.download.DownloadManager.TaskType("ED2K", 5);
            CID = new com.xunlei.download.DownloadManager.TaskType("CID", 6);
            GROUP = new com.xunlei.download.DownloadManager.TaskType("GROUP", 7);
            a = new com.xunlei.download.DownloadManager.TaskType[]{UNKOWN, HTTP, FTP, MAGNET, BT, ED2K, CID, GROUP};
        }
    }

    static {
        boolean z;
        if (DownloadManager.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        UNDERLYING_COLUMNS = new String[]{COLUMN_ID, Impl._DATA, "_data AS local_filename", COLUMN_MEDIAPROVIDER_URI, Impl.COLUMN_DESTINATION, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_URI, COLUMN_STATUS, Impl.COLUMN_FILE_NAME_HINT, Impl.COLUMN_MIME_TYPE, "mimetype AS media_type", d.b, "total_bytes AS total_size", Impl.COLUMN_LAST_MODIFICATION, "lastmod AS last_modified_timestamp", Impl.COLUMN_CURRENT_BYTES, "current_bytes AS bytes_so_far", COLUMN_DOWNLOAD_SPEED, COLUMN_ORIGIN_SPEED, COLUMN_P2S_SPEED, COLUMN_ADDITION_VIP_SPEED, COLUMN_CID, COLUMN_GCID, COLUMN_ERROR_MSG, y.g, COLUMN_ALLOW_WRITE, Impl.COLUMN_ALLOW_AUTO_RESUME, COLUMN_APK_VERSION, COLUMN_APK_PACKAGE, COLUMN_VIP_RECEIVE_SIZE, COLUMN_VIP_STATUS, COLUMN_ADDITION_LX_SPEED, COLUMN_LX_RECEIVE_SIZE, COLUMN_LX_STATUS, COLUMN_LX_PROGRESS, COLUMN_P2P_SPEED, COLUMN_P2P_RECEIVE_SIZE, COLUMN_P2S_RECEIVE_SIZE, COLUMN_ORIGIN_RECEIVE_SIZE, COLUMN_TASK_TYPE, Impl.COLUMN_GROUP_ID, Impl.COLUMN_RES_TOTAL, Impl.COLUMN_RES_USED_TOTAL, Impl.COLUMN_BT_INFO_HASH, COLUMN_XUNLEI_SPDY, COLUMN_BT_SELECT_SET, COLUMN_IS_VIP_SPEEDUP, COLUMN_IS_LX_SPEEDUP, COLUMN_CREATE_TIME, COLUMN_DOWNLOAD_DURATION, "'placeholder' AS local_uri", "'placeholder' AS reason", "'placeholder' AS status_original"};
        g = 524288;
        h = 4194304;
        i = null;
    }

    public static synchronized DownloadManager getInstanceFor(Context context) {
        DownloadManager instanceFor;
        synchronized (DownloadManager.class) {
            instanceFor = getInstanceFor(context, DownloadProvider.class);
        }
        return instanceFor;
    }

    public static synchronized DownloadManager getInstanceFor(Context context, Class<? extends DownloadProvider> cls) {
        DownloadManager instanceFor;
        synchronized (DownloadManager.class) {
            instanceFor = getInstanceFor(context, cls, new File(a.d));
        }
        return instanceFor;
    }

    public static synchronized DownloadManager getInstanceFor(Context context, Class<? extends DownloadProvider> cls, File file) {
        DownloadManager downloadManager;
        synchronized (DownloadManager.class) {
            if (i == null) {
                ProviderInfo[] providerInfoArr;
                ProviderInfo providerInfo;
                if (cls == null) {
                    cls = DownloadProvider.class;
                }
                try {
                    providerInfoArr = context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), STATUS_SUCCESSFUL).providers;
                } catch (Throwable e) {
                    am.a(LOG_TAG, "Failed get Package info.", e);
                    Object[] objArr = null;
                }
                if (providerInfoArr != null) {
                    for (int i = 0; i < providerInfoArr.length; i++) {
                        if (TextUtils.equals(providerInfoArr[i].name, cls == null ? DownloadProvider.class.getName() : cls.getName())) {
                            providerInfo = providerInfoArr[i];
                            break;
                        }
                    }
                }
                providerInfo = null;
                if (providerInfo == null || providerInfo.authority == null || providerInfo.authority.length() <= 0) {
                    throw new IllegalArgumentException("Not found the definition for this Provider in AndroidManifest.xml.");
                }
                DownloadProvider.a(providerInfo.authority);
                DownloadManager downloadManager2 = new DownloadManager(context.getContentResolver(), context.getPackageName(), Uri.parse(new StringBuilder("content://").append(providerInfo.authority).append("/my_downloads").toString()));
                i = downloadManager2;
                downloadManager2.d = context.getApplicationContext();
                if (file == null || !file.getPath().equals(a.d)) {
                    context.getSharedPreferences(LOG_TAG, 0).edit().putString(DB_PATH_KEY, file != null ? file.getPath() : null).commit();
                }
                try {
                    i.c.query(i.f, null, null, null, null).close();
                } catch (Throwable e2) {
                    am.a(e2);
                }
                String path = Environment.getExternalStorageDirectory().getPath();
                if (am.a(new File(path, "xunlei_ds_log.ini").getPath())) {
                    ak.a().a(context, new File(path, am.c()).getPath());
                }
            }
            downloadManager = i;
        }
        return downloadManager;
    }

    public static synchronized DownloadManager getInstanceFor(Context context, Uri uri) {
        DownloadManager downloadManager;
        synchronized (DownloadManager.class) {
            if (i == null) {
                i = new DownloadManager(context.getContentResolver(), context.getPackageName(), uri);
                String path = Environment.getExternalStorageDirectory().getPath();
                if (am.a(new File(path, "xunlei_ds_log.ini").getPath())) {
                    ak.a().a(context, new File(path, am.c()).getPath());
                }
            }
            downloadManager = i;
        }
        return downloadManager;
    }

    private DownloadManager(ContentResolver contentResolver, String str) {
        this.c = contentResolver;
        this.e = str;
        this.f = Impl.ALL_DOWNLOADS_CONTENT_URI;
    }

    private DownloadManager(ContentResolver contentResolver, String str, Uri uri) {
        if (uri == null || contentResolver == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("input param can't be null");
        }
        this.f = uri;
        this.c = contentResolver;
        this.e = str;
    }

    private DownloadManager(ContentResolver contentResolver, String str, Uri uri, long j, long j2) {
        this(contentResolver, str, uri);
        g = j;
        h = j2;
    }

    public File getTaskDBFile(Context context) {
        String string = context.getSharedPreferences(LOG_TAG, 0).getString(DB_PATH_KEY, null);
        return string != null ? new File(string) : null;
    }

    public long enqueue(Request request) {
        return request instanceof GroupRequest ? a((GroupRequest) request) : a(request);
    }

    public int markRowDeleted(long... jArr) {
        if (jArr == null || jArr.length == 0) {
            throw new IllegalArgumentException("input param 'ids' can't be null");
        }
        int i;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Impl.COLUMN_DELETED, Integer.valueOf(STATUS_PENDING));
        List arrayList = new ArrayList();
        int length = jArr.length;
        for (i = 0; i < length; i++) {
            long j = jArr[i];
            arrayList.add(Long.valueOf(j));
            v.a().a(j);
        }
        long[] c = c(arrayList);
        int length2 = c.length;
        for (i = 0; i < length2; i++) {
            j = c[i];
            arrayList.add(Long.valueOf(j));
            v.a().a(j);
        }
        try {
            return arrayList.size() == 1 ? this.c.update(ContentUris.withAppendedId(this.f, ((Long) arrayList.get(0)).longValue()), contentValues, null, null) : this.c.update(this.f, contentValues, a(arrayList), b(arrayList));
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    public int remove(long... jArr) {
        am.b(LOG_TAG, new StringBuilder("remove,ids=").append(Arrays.toString(jArr)).toString());
        XlTaskHelper.a().b(jArr);
        return markRowDeleted(jArr);
    }

    public int remove(boolean z, long... jArr) {
        return remove(z, SHOW_NOTIFY, jArr);
    }

    public int remove(boolean z, boolean z2, long... jArr) {
        Throwable e;
        am.b(LOG_TAG, new StringBuilder("remove remove,RecordOnly= ").append(z).append("deleteIfNotComplete=").append(z2).append("ids=").append(Arrays.toString(jArr)).toString());
        if (!z) {
            return markRowDeleted(jArr);
        }
        if (jArr == null || jArr.length == 0) {
            throw new IllegalArgumentException("input param 'ids' can't be null");
        }
        String[] strArr = new String[]{COLUMN_ID, COLUMN_STATUS, COLUMN_TASK_TYPE};
        try {
            Cursor query;
            if (jArr.length == 1) {
                query = this.c.query(ContentUris.withAppendedId(this.f, jArr[0]), strArr, null, null, null);
            } else {
                query = this.c.query(this.f, strArr, a(jArr), b(jArr), null);
            }
            try {
                int columnIndex = query.getColumnIndex(COLUMN_ID);
                int columnIndex2 = query.getColumnIndex(COLUMN_STATUS);
                int columnIndex3 = query.getColumnIndex(COLUMN_TASK_TYPE);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List arrayList3 = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    long j = query.getLong(columnIndex);
                    if (TaskType.GROUP.ordinal() == query.getInt(columnIndex3)) {
                        arrayList3.add(Long.valueOf(j));
                    } else {
                        if (z2) {
                            if (200 != query.getInt(columnIndex2)) {
                                arrayList2.add(Long.valueOf(j));
                            }
                        }
                        arrayList.add(Long.valueOf(j));
                    }
                    XlTaskHelper.a().b(j);
                    query.moveToNext();
                }
                columnIndex2 = 0;
                if (arrayList.size() > 1) {
                    long[] jArr2 = new long[arrayList.size()];
                    for (columnIndex2 = 0; columnIndex2 < jArr2.length; columnIndex2++) {
                        jArr2[columnIndex2] = ((Long) arrayList.get(columnIndex2)).longValue();
                    }
                    columnIndex2 = this.c.delete(this.f, a(jArr2), b(jArr2)) + 0;
                } else if (arrayList.size() == 1) {
                    columnIndex2 = this.c.delete(ContentUris.withAppendedId(this.f, ((Long) arrayList.get(0)).longValue()), null, null) + 0;
                }
                if (arrayList2.size() > 0) {
                    long[] jArr3 = new long[arrayList2.size()];
                    for (columnIndex3 = 0; columnIndex3 < jArr3.length; columnIndex3++) {
                        jArr3[columnIndex3] = ((Long) arrayList2.get(columnIndex3)).longValue();
                    }
                    columnIndex = markRowDeleted(jArr3) + columnIndex2;
                } else {
                    columnIndex = columnIndex2;
                }
                if (arrayList3.size() > 0) {
                    a(z2, arrayList3);
                }
                if (query == null) {
                    return columnIndex;
                }
                query.close();
                return columnIndex;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                e.printStackTrace();
                am.a(e);
                if (query != null) {
                    query.close();
                }
                return 0;
            } catch (Throwable th) {
                e = th;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    private int a(boolean z, List<Long> list) {
        int i = 0;
        if (list == null || list.size() == 0) {
            return 0;
        }
        int i2;
        long[] c = c(list);
        if (c == null || c.length <= 0) {
            i2 = 0;
        } else {
            i2 = remove(SHOW_NOTIFY, z, c) + 0;
        }
        long[] jArr = new long[list.size()];
        while (i < jArr.length) {
            jArr[i] = ((Long) list.get(i)).longValue();
            i++;
        }
        try {
            i2 += this.c.delete(this.f, a(jArr), b(jArr));
            return i2;
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return i2;
        }
    }

    public Cursor query(Query query) {
        Cursor a = query.a(this.c, this.f);
        return a == null ? null : new CursorTranslator(a, this.f);
    }

    public ParcelFileDescriptor openDownloadedFile(long j) throws FileNotFoundException {
        try {
            return this.c.openFileDescriptor(getDownloadUri(j), "r");
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return null;
        }
    }

    public Uri getUriForDownloadedFile(long j) {
        Uri uri = null;
        try {
            Cursor query = query(new Query().setFilterById(j));
            if (query != null) {
                try {
                    if (query.moveToFirst() && 8 == query.getInt(query.getColumnIndexOrThrow(COLUMN_STATUS))) {
                        int i = query.getInt(query.getColumnIndexOrThrow(Impl.COLUMN_DESTINATION));
                        if (i == 1 || i == 5 || i == 3 || i == 2) {
                            uri = ContentUris.withAppendedId(this.f, j);
                            if (query != null) {
                                query.close();
                            }
                        } else {
                            uri = Uri.fromFile(new File(query.getString(query.getColumnIndexOrThrow(COLUMN_LOCAL_FILENAME))));
                            if (query != null) {
                                query.close();
                            }
                        }
                    } else if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (query != null) {
                        query.close();
                    }
                    throw th2;
                }
            } else if (query != null) {
                query.close();
            }
            return uri;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            query = null;
            th2 = th4;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    public String getMimeTypeForDownloadedFile(long j) {
        String str = null;
        try {
            Cursor query = query(new Query().setFilterById(j));
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndexOrThrow(COLUMN_MEDIA_TYPE));
                        if (query != null) {
                            query.close();
                        }
                    } else if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (query != null) {
                        query.close();
                    }
                    throw th2;
                }
            } else if (query != null) {
                query.close();
            }
            return str;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            query = null;
            th2 = th4;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    public static Long getMaxBytesOverMobile(Context context) {
        return Long.valueOf(ab.a(context, KEY_MAX_BYTES_OVER_MOBILE, (long) MAX_BYTES_OVER_MOBILE));
    }

    public static Long getRecommendedMaxBytesOverMobile(Context context) {
        return Long.valueOf(ab.a(context, KEY_RECOMMENDED_MAX_BYTES_OVER_MOBILE, (long) RECOMMENDED_MAX_BYTES_OVERMOBILE));
    }

    public long addCompletedDownload(String str, String str2, boolean z, String str3, String str4, long j, boolean z2) {
        return addCompletedDownload(j, str, str2, z, str3, str4, j, z2, a);
    }

    public long addCompletedDownload(String str, String str2, boolean z, String str3, String str4, long j, boolean z2, boolean z3) {
        return addCompletedDownload(j, str, str2, z, str3, str4, j, z2, z3);
    }

    public long addCompletedDownload(String str, String str2, String str3, boolean z, String str4, String str5, long j, boolean z2, boolean z3) {
        a(COLUMN_TITLE, str2);
        a(COLUMN_DESCRIPTION, str3);
        a("path", str5);
        a("mimeType", str4);
        if (j < 0) {
            throw new IllegalArgumentException(" invalid value for param: totalBytes");
        }
        Uri insert;
        ContentValues a = new Request(str).setTitle(str2).setDescription(str3).setMimeType(str4).a(this.d, null);
        a.put(Impl.COLUMN_DESTINATION, Integer.valueOf(R.styleable.Toolbar_contentInsetEnd));
        a.put(Impl._DATA, str5);
        a.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_SUCCESS));
        a.put(d.b, Long.valueOf(j));
        a.put(Impl.COLUMN_MEDIA_SCANNED, Integer.valueOf(z ? 0 : STATUS_RUNNING));
        a.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(z2 ? STATUS_PENDING : 0));
        a.put(COLUMN_ALLOW_WRITE, Integer.valueOf(z3 ? STATUS_PENDING : 0));
        try {
            insert = this.c.insert(this.f, a);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            insert = null;
        }
        return insert == null ? -1 : Long.parseLong(insert.getLastPathSegment());
    }

    public long addCompletedBtSubTask(long j, int i, String str, String str2, String str3, long j2) {
        a(COLUMN_TITLE, str);
        a("path", str3);
        a("mimeType", str2);
        if (j2 < 0) {
            throw new IllegalArgumentException(" invalid value for param: totalBytes");
        }
        Uri insert;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BT_PARENT_ID, Long.valueOf(j));
        contentValues.put(COLUMN_BT_SUB_INDEX, Integer.valueOf(i));
        contentValues.put(COLUMN_TITLE, str);
        contentValues.put(Impl.COLUMN_MIME_TYPE, str2);
        contentValues.put(Impl._DATA, str3);
        contentValues.put(d.b, Long.valueOf(j2));
        contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_SUCCESS));
        contentValues.put(COLUMN_BT_SUB_IS_SELECTED, Integer.valueOf(STATUS_PENDING));
        try {
            insert = this.c.insert(getBtSubTaskUri(), contentValues);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            insert = null;
        }
        return insert == null ? -1 : Long.parseLong(insert.getLastPathSegment());
    }

    private static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException(str + " can't be null");
        }
    }

    public Uri getDownloadUri(long j) {
        return ContentUris.withAppendedId(this.f, j);
    }

    public Uri getDownloadUri() {
        return this.f;
    }

    public Uri getBtSubTaskUri() {
        return DownloadProvider.c;
    }

    public Uri getTaskGroupUri(long j) {
        return Uri.parse(String.format(new StringBuilder("content://").append(DownloadProvider.e).append("/xl_task_group/%d").toString(), new Object[]{Long.valueOf(j)}));
    }

    static String a(long[] jArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SocializeConstants.OP_OPEN_PAREN);
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                stringBuilder.append("OR ");
            }
            stringBuilder.append(COLUMN_ID);
            stringBuilder.append(" = ? ");
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }

    static String a(List<Long> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SocializeConstants.OP_OPEN_PAREN);
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append("OR ");
            }
            stringBuilder.append(COLUMN_ID);
            stringBuilder.append(" = ? ");
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }

    static String[] b(long[] jArr) {
        String[] strArr = new String[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            strArr[i] = Long.toString(jArr[i]);
        }
        return strArr;
    }

    static String[] b(List<Long> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = Long.toString(((Long) list.get(i)).longValue());
        }
        return strArr;
    }

    public static int translateStatus(int i) {
        switch (i) {
            case Impl.STATUS_PENDING:
                return STATUS_PENDING;
            case Impl.STATUS_RUNNING:
                return STATUS_RUNNING;
            case Impl.STATUS_PAUSED_BY_APP:
            case Impl.STATUS_WAITING_TO_RETRY:
            case Impl.STATUS_WAITING_FOR_NETWORK:
            case Impl.STATUS_QUEUED_FOR_WIFI:
                return STATUS_PAUSED;
            case Impl.STATUS_SUCCESS:
                return STATUS_SUCCESSFUL;
            default:
                if (a || Impl.isStatusError(i)) {
                    return STATUS_FAILED;
                }
                throw new AssertionError();
        }
    }

    public static long getReason(int i) {
        switch (translateStatus(i)) {
            case STATUS_PAUSED:
                return a(i);
            case STATUS_FAILED:
                return b(i);
            default:
                return 0;
        }
    }

    private static long a(int i) {
        switch (i) {
            case Impl.STATUS_PAUSED_BY_APP:
                return 5;
            case Impl.STATUS_WAITING_TO_RETRY:
                return 1;
            case Impl.STATUS_WAITING_FOR_NETWORK:
                return PlaybackStateCompat.ACTION_PAUSE;
            case Impl.STATUS_QUEUED_FOR_WIFI:
                return 3;
            default:
                return PlaybackStateCompat.ACTION_PLAY;
        }
    }

    private static long b(int i) {
        if ((400 <= i && i < 488) || (500 <= i && i < 600)) {
            return (400 > i || i >= 488) ? 1011 : 1012;
        } else {
            switch (i) {
                case Impl.STATUS_INSUFFICIENT_SPACE_ERROR:
                    return 1006;
                case Impl.STATUS_DEVICE_NOT_FOUND_ERROR:
                    return 1007;
                case Impl.STATUS_FILE_ALREADY_EXISTS_ERROR:
                    return 1009;
                case Impl.STATUS_CANNOT_RESUME:
                    return 1008;
                case Impl.STATUS_FILE_ERROR:
                    return 1001;
                case Impl.STATUS_UNHANDLED_REDIRECT:
                case Impl.STATUS_UNHANDLED_HTTP_CODE:
                    return 1002;
                case Impl.STATUS_HTTP_DATA_ERROR:
                    return 1004;
                case Impl.STATUS_TOO_MANY_REDIRECTS:
                    return 1005;
                default:
                    return 1000;
            }
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            am.b(LOG_TAG, new StringBuilder("deleteFileIfExists() deleting ").append(str).toString());
            File file = new File(str);
            if (file.exists() && !file.delete()) {
                am.c(LOG_TAG, new StringBuilder("file: '").append(str).append("' couldn't be deleted").toString());
            }
            file = new File(str + ".cfg");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2);
                }
            }
            file.delete();
        }
    }

    private ArrayList<String> a(int i, long j) {
        Cursor cursor = null;
        String[] strArr = new String[]{Impl._DATA};
        try {
            Cursor query;
            if (i == TaskType.GROUP.ordinal()) {
                query = this.c.query(this.f, strArr, "group_id = ?", new String[]{String.valueOf(j)}, null);
            } else if (i != TaskType.BT.ordinal()) {
                return null;
            } else {
                query = this.c.query(DownloadProvider.c, strArr, "bt_parent_id = ?", new String[]{String.valueOf(j)}, null);
            }
            try {
                ArrayList<String> arrayList = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    CharSequence string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                    query.moveToNext();
                }
                if (query == null) {
                    return arrayList;
                }
                query.close();
                return arrayList;
            } catch (Exception e) {
                e = e;
                try {
                    Throwable e2;
                    e2.printStackTrace();
                    am.a(e2);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th) {
                    e2 = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw e2;
                }
            }
        } catch (Exception e3) {
            e2 = e3;
            query = null;
            e2.printStackTrace();
            am.a(e2);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            e2 = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw e2;
        }
    }

    public int restartDownload(long... jArr) {
        return restartDownload(a, a, jArr);
    }

    public int restartDownload(boolean z, long... jArr) {
        return restartDownload(z, a, jArr);
    }

    public int restartDownload(boolean z, boolean z2, long... jArr) {
        am.b(LOG_TAG, new StringBuilder("restartDownload ids=").append(Arrays.toString(jArr)).toString());
        Query query = new Query();
        query.setFilterById(jArr);
        query.setProjection(COLUMN_ID, COLUMN_STATUS, COLUMN_TASK_TYPE, Impl._DATA, Impl.COLUMN_BT_INFO_HASH);
        Cursor query2 = query(query);
        ArrayList arrayList = new ArrayList();
        Object obj = null;
        if (query2 != null) {
            int columnIndex = query2.getColumnIndex(COLUMN_ID);
            int columnIndex2 = query2.getColumnIndex(COLUMN_STATUS);
            int columnIndex3 = query2.getColumnIndex(COLUMN_TASK_TYPE);
            int columnIndex4 = query2.getColumnIndex(Impl._DATA);
            int columnIndex5 = query2.getColumnIndex(Impl.COLUMN_BT_INFO_HASH);
            query2.moveToFirst();
            Object obj2 = null;
            while (!query2.isAfterLast()) {
                long j = query2.getLong(columnIndex);
                int i = query2.getInt(columnIndex2);
                int i2 = query2.getInt(columnIndex3);
                if (i == 8 || i == 16) {
                    String string = query2.getString(columnIndex4);
                    if (i2 != TaskType.GROUP.ordinal() && i2 != TaskType.BT.ordinal()) {
                        a(string);
                    } else if (!TextUtils.isEmpty(string)) {
                        ArrayList a = a(i2, j);
                        if (a != null) {
                            Iterator it = a.iterator();
                            while (it.hasNext()) {
                                a((String) it.next());
                            }
                        }
                        if (i2 == TaskType.BT.ordinal() && !TextUtils.isEmpty(query2.getString(columnIndex5))) {
                            new File(string, new StringBuilder(".").append(query2.getString(columnIndex5)).toString()).delete();
                        }
                        if (i2 != TaskType.BT.ordinal()) {
                            a(new File(string));
                        }
                    }
                    if (i2 == TaskType.GROUP.ordinal()) {
                        arrayList.add(Long.valueOf(j));
                    } else if (i2 == TaskType.BT.ordinal()) {
                        obj2 = STATUS_PENDING;
                    }
                    query2.moveToNext();
                } else {
                    am.c(LOG_TAG, new StringBuilder("Cannot restart incomplete download: ").append(query2.getLong(query2.getColumnIndex(COLUMN_ID))).toString());
                    query2.close();
                    return 0;
                }
            }
            query2.close();
            obj = obj2;
        }
        if (arrayList.size() > 0) {
            a(arrayList);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Impl.COLUMN_CURRENT_BYTES, Integer.valueOf(0));
        contentValues.put(d.b, Integer.valueOf(INVAlID_TASK_ID));
        if (obj == null) {
            contentValues.putNull(Impl._DATA);
        }
        contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(0));
        contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
        contentValues.put(Impl.COLUMN_FAILED_CONNECTIONS, Integer.valueOf(0));
        contentValues.put(COLUMN_VIP_RECEIVE_SIZE, Integer.valueOf(0));
        contentValues.put(COLUMN_LX_RECEIVE_SIZE, Integer.valueOf(0));
        contentValues.put(COLUMN_P2P_RECEIVE_SIZE, Integer.valueOf(0));
        contentValues.put(COLUMN_P2S_RECEIVE_SIZE, Integer.valueOf(0));
        contentValues.put(COLUMN_ORIGIN_RECEIVE_SIZE, Integer.valueOf(0));
        contentValues.put(COLUMN_CREATE_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put(COLUMN_DOWNLOAD_DURATION, Integer.valueOf(0));
        if (z) {
            if (z2) {
                contentValues.put(Impl.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(STATUS_RUNNING));
                XlTaskHelper.a().a(jArr);
            } else {
                contentValues.put(Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, Integer.valueOf(STATUS_PENDING));
            }
        }
        try {
            return this.c.update(this.f, contentValues, a(jArr), b(jArr));
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    private int a(ArrayList<Long> arrayList) {
        long[] c = c(arrayList);
        return (c == null || c.length <= 0) ? 0 : restartDownload(c) + 0;
    }

    public int pauseDownload(long... jArr) {
        Throwable e;
        am.b(LOG_TAG, new StringBuilder("pauseDownload ids=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length == 0) {
            am.c(LOG_TAG, "input param 'ids' can't be null");
            return 0;
        }
        String[] strArr = new String[]{COLUMN_ID, Impl.COLUMN_CONTROL, COLUMN_STATUS};
        List arrayList = new ArrayList(jArr.length);
        try {
            int i;
            Cursor query = this.c.query(this.f, strArr, a(jArr), b(jArr), null);
            try {
                int columnIndex = query.getColumnIndex(COLUMN_ID);
                int columnIndex2 = query.getColumnIndex(COLUMN_STATUS);
                int columnIndex3 = query.getColumnIndex(Impl.COLUMN_CONTROL);
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    long j = query.getLong(columnIndex);
                    i = query.getInt(columnIndex2);
                    int i2 = query.getInt(columnIndex3);
                    boolean isStatusCompleted = Impl.isStatusCompleted(i);
                    if (isStatusCompleted || i2 == 1) {
                        String str;
                        String str2 = "pauseDownload meet %s task id=%d";
                        Object[] objArr = new Object[2];
                        if (isStatusCompleted) {
                            str = "completed";
                        } else {
                            str = "paused";
                        }
                        objArr[0] = str;
                        objArr[1] = Long.valueOf(j);
                        String.format(str2, objArr);
                    } else {
                        arrayList.add(Long.valueOf(j));
                    }
                    query.moveToNext();
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                e.printStackTrace();
                am.a(e);
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                e = th;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
            if (arrayList.size() > 0) {
                return 0;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(STATUS_PENDING));
            contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PAUSED_BY_APP));
            i = this.c.update(this.f, contentValues, a(arrayList), b(arrayList));
            XlTaskHelper.a().b(arrayList);
            v.a().a(arrayList);
            return i;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
        if (arrayList.size() > 0) {
            return 0;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Impl.COLUMN_CONTROL, Integer.valueOf(STATUS_PENDING));
        contentValues2.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PAUSED_BY_APP));
        try {
            i = this.c.update(this.f, contentValues2, a(arrayList), b(arrayList));
        } catch (Throwable e4) {
            e4.printStackTrace();
            am.a(e4);
            i = 0;
        }
        XlTaskHelper.a().b(arrayList);
        v.a().a(arrayList);
        return i;
    }

    public int resumeDownload(long... jArr) {
        return resumeDownload(a, a, jArr);
    }

    public int resumeDownload(boolean z, long... jArr) {
        return resumeDownload(z, a, jArr);
    }

    public int resumeDownload(boolean z, boolean z2, long... jArr) {
        am.b(LOG_TAG, new StringBuilder("resumeDownload forceDownload=").append(z).append(" ids=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length == 0) {
            am.c(LOG_TAG, "input param 'ids' can't be null");
            return 0;
        }
        String[] strArr = new String[]{Impl.COLUMN_CONTROL, COLUMN_STATUS, COLUMN_ID};
        List arrayList = new ArrayList();
        try {
            Cursor query = this.c.query(this.f, strArr, a(jArr), b(jArr), null);
            try {
                int columnIndex = query.getColumnIndex(COLUMN_ID);
                int columnIndex2 = query.getColumnIndex(COLUMN_STATUS);
                int columnIndex3 = query.getColumnIndex(Impl.COLUMN_CONTROL);
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    long j = query.getLong(columnIndex);
                    int i = query.getInt(columnIndex2);
                    int i2 = query.getInt(columnIndex3);
                    if (Impl.isStatusSuccess(i) || (i2 == 0 && (i == 190 || i == 192))) {
                        new StringBuilder("Cannot resume a completed task. download: ").append(j).append(", status = ").append(i).append(", control = ").append(i2);
                    } else {
                        arrayList.add(Long.valueOf(j));
                    }
                    query.moveToNext();
                }
                if (query != null) {
                    query.close();
                }
                v.a().b(arrayList);
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(0));
                contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
                contentValues.put(Impl.COLUMN_FAILED_CONNECTIONS, Integer.valueOf(0));
                if (z) {
                    if (z2) {
                        contentValues.put(Impl.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(STATUS_RUNNING));
                        XlTaskHelper.a().a(arrayList);
                    } else {
                        contentValues.put(Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, Integer.valueOf(STATUS_PENDING));
                    }
                }
                try {
                    if (arrayList.size() == 1) {
                        return this.c.update(ContentUris.withAppendedId(this.f, ((Long) arrayList.get(0)).longValue()), contentValues, null, null);
                    }
                    if (arrayList.size() > 0) {
                        return this.c.update(this.f, contentValues, a(arrayList), b(arrayList));
                    }
                    return 0;
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                Throwable e4;
                e4.printStackTrace();
                am.a(e4);
                if (query != null) {
                    query.close();
                }
                return 0;
            } catch (Throwable th) {
                e4 = th;
                if (query != null) {
                    query.close();
                }
                throw e4;
            }
        } catch (Throwable th2) {
            e4 = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e4;
        }
    }

    public int suspendDownload(long... jArr) {
        am.b(LOG_TAG, new StringBuilder("suspendDownload  ids=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length == 0) {
            am.c(LOG_TAG, "input param 'ids' can't be null");
            return 0;
        }
        String[] strArr = new String[]{Impl.COLUMN_CONTROL, COLUMN_STATUS, COLUMN_ID};
        List arrayList = new ArrayList();
        try {
            Cursor query = this.c.query(this.f, strArr, a(jArr), b(jArr), null);
            try {
                int columnIndex = query.getColumnIndex(COLUMN_ID);
                int columnIndex2 = query.getColumnIndex(COLUMN_STATUS);
                int columnIndex3 = query.getColumnIndex(Impl.COLUMN_CONTROL);
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    long j = query.getLong(columnIndex);
                    int i = query.getInt(columnIndex2);
                    int i2 = query.getInt(columnIndex3);
                    if (!Impl.isStatusCompleted(i) && i2 == 0 && (i == 190 || i == 192)) {
                        arrayList.add(Long.valueOf(j));
                    } else {
                        new StringBuilder("Cannot suspend a completed task. download: ").append(j).append(", status = ").append(i).append(", control = ").append(i2);
                    }
                    query.moveToNext();
                }
                if (query != null) {
                    query.close();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(MAX_CONCURRENT_DOWNLOADS));
                try {
                    if (arrayList.size() == 1) {
                        columnIndex = this.c.update(ContentUris.withAppendedId(this.f, ((Long) arrayList.get(0)).longValue()), contentValues, null, null);
                    } else {
                        if (arrayList.size() > 0) {
                            columnIndex = this.c.update(this.f, contentValues, a(arrayList), b(arrayList));
                        }
                        columnIndex = 0;
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                }
                return columnIndex;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                Throwable e4;
                e4.printStackTrace();
                am.a(e4);
                if (query == null) {
                    return 0;
                }
                query.close();
                return 0;
            } catch (Throwable th) {
                e4 = th;
                if (query != null) {
                    query.close();
                }
                throw e4;
            }
        } catch (Throwable th2) {
            e4 = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e4;
        }
    }

    public int openVIPSpeedUp(long... jArr) {
        am.b(LOG_TAG, new StringBuilder("openVIPSpeedUp  ids=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length == 0) {
            am.c(LOG_TAG, "input param 'ids' can't be null");
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_VIP_SPEEDUP, Integer.valueOf(STATUS_PENDING));
        try {
            return jArr.length == 1 ? this.c.update(ContentUris.withAppendedId(this.f, jArr[0]), contentValues, null, null) : this.c.update(this.f, contentValues, a(jArr), b(jArr));
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    public int openLXSpeedUp(long... jArr) {
        am.b(LOG_TAG, new StringBuilder("openLXSpeedUp  ids=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length == 0) {
            am.c(LOG_TAG, "input param 'ids' can't be null");
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_LX_SPEEDUP, Integer.valueOf(STATUS_PENDING));
        try {
            return jArr.length == 1 ? this.c.update(ContentUris.withAppendedId(this.f, jArr[0]), contentValues, null, null) : this.c.update(this.f, contentValues, a(jArr), b(jArr));
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    public int selectBtSubTask(long j, long[] jArr) {
        int i;
        am.b(LOG_TAG, new StringBuilder("selectBtSubTask id=").append(j).append(", index=").append(Arrays.toString(jArr)).toString());
        if (jArr == null || jArr.length <= 0) {
            return INVAlID_TASK_ID;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = jArr.length;
        for (i = 0; i < length; i++) {
            stringBuilder.append(jArr[i]).append(h.b);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BT_SELECT_SET, stringBuilder.toString());
        contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(0));
        contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
        try {
            i = this.c.update(ContentUris.withAppendedId(this.f, j), contentValues, null, null);
            try {
                v.a().a(-1, -1);
                return i;
            } catch (Exception e) {
                Throwable e2 = e;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            i = 0;
            e2 = th;
            e2.printStackTrace();
            am.a(e2);
            return i;
        }
    }

    public int selectGroupSubTask(long j, long j2, boolean z) {
        Throwable e;
        am.b(LOG_TAG, new StringBuilder("selectGroupSubTask id=").append(j).append(", subId=").append(j2).append(", selected=").append(z).toString());
        String[] strArr = new String[]{COLUMN_BT_SELECT_SET};
        Uri withAppendedId = ContentUris.withAppendedId(this.f, j);
        try {
            Cursor query = this.c.query(withAppendedId, strArr, null, null, null);
            try {
                int update;
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COLUMN_BT_SELECT_SET, z ? ah.a(string, j2) : ah.b(string, j2));
                    update = this.c.update(withAppendedId, contentValues, null, null);
                } else {
                    update = 0;
                }
                if (query == null) {
                    return update;
                }
                query.close();
                return update;
            } catch (Exception e2) {
                e = e2;
                r1 = query;
                e.printStackTrace();
                am.a(e);
                if (r1 != null) {
                    return 0;
                }
                r1.close();
                return 0;
            } catch (Throwable th) {
                e = th;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            r1 = null;
            try {
                e.printStackTrace();
                am.a(e);
                Cursor cursor;
                if (cursor != null) {
                    return 0;
                }
                cursor.close();
                return 0;
            } catch (Throwable th2) {
                e = th2;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Throwable th3) {
            e = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    public int selectGroupSubTask(long j, long[] jArr, boolean z) {
        Throwable e;
        Cursor cursor;
        am.b(LOG_TAG, new StringBuilder("selectGroupSubTask id=").append(j).append(", subIds=").append(Arrays.toString(jArr)).append(", selected=").append(z).toString());
        String[] strArr = new String[]{COLUMN_BT_SELECT_SET};
        Uri withAppendedId = ContentUris.withAppendedId(this.f, j);
        try {
            Cursor query = this.c.query(withAppendedId, strArr, null, null, null);
            try {
                int i;
                if (query.moveToFirst()) {
                    HashSet a = ah.a(query.getString(0));
                    int length = jArr.length;
                    for (i = 0; i < length; i++) {
                        long j2 = jArr[i];
                        if (z) {
                            a.add(Long.valueOf(j2));
                        } else {
                            a.remove(Long.valueOf(j2));
                        }
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COLUMN_BT_SELECT_SET, ah.a(a));
                    i = this.c.update(withAppendedId, contentValues, null, null);
                } else {
                    i = 0;
                }
                if (query == null) {
                    return i;
                }
                query.close();
                return i;
            } catch (Exception e2) {
                e = e2;
                cursor = query;
                try {
                    e.printStackTrace();
                    am.a(e);
                    if (cursor != null) {
                        return 0;
                    }
                    cursor.close();
                    return 0;
                } catch (Throwable th) {
                    e = th;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            e.printStackTrace();
            am.a(e);
            if (cursor != null) {
                return 0;
            }
            cursor.close();
            return 0;
        } catch (Throwable th3) {
            e = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    private void a(String[] strArr, String str, String str2) throws RemoteException, OperationApplicationException {
        if (str2 == null) {
            str2 = b(strArr);
        }
        Request request = new Request(a(strArr, str2));
        File file = new File(str, str2);
        request.setTitle(str2);
        request.setDestinationUri(Uri.fromFile(file));
        long enqueue = enqueue(request);
        ArrayList arrayList = new ArrayList();
        for (String str3 : strArr) {
            Builder newInsert = ContentProviderOperation.newInsert(getInstanceFor(this.d).getDownloadUri());
            Request request2 = new Request(Uri.parse(str3));
            request2.setTaskGroup(enqueue);
            request2.setDestinationUri(file.getPath(), null);
            newInsert.withValues(request2.a(this.d, this.e));
            arrayList.add(newInsert.build());
        }
        try {
            this.d.getContentResolver().applyBatch(DownloadProvider.e, arrayList);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
    }

    private int a(Request... requestArr) {
        int i = 0;
        int length = requestArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (a(requestArr[i2]) != -1) {
                i++;
            }
        }
        return i;
    }

    private long a(GroupRequest groupRequest) {
        ArrayList subList = groupRequest.getSubList();
        if (subList.size() == 0) {
            return -1;
        }
        long a = a((Request) groupRequest);
        if (a != -1) {
            String path;
            if (groupRequest.b != null) {
                path = Uri.withAppendedPath(groupRequest.b, groupRequest.d.toString()).getPath();
            } else {
                path = null;
            }
            Iterator it = subList.iterator();
            while (it.hasNext()) {
                Request request = (Request) it.next();
                request.setTaskGroup(a);
                if (groupRequest.c) {
                    request.forceDownloadInMobileNetwork();
                }
                if (request.b == null && path != null) {
                    request.setDestinationUri(path, null);
                }
                if (request.e == null) {
                    request.e = groupRequest.e;
                }
            }
            if (a((Request[]) subList.toArray(new Request[subList.size()])) != 0) {
                return a;
            }
            try {
                this.c.delete(this.f, "_id=?", new String[]{String.valueOf(a)});
            } catch (Throwable e) {
                e.printStackTrace();
                am.a(e);
            }
        }
        return -1;
    }

    private long a(Request request) {
        try {
            Uri insert = this.c.insert(this.f, request.a(this.d, this.e));
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            insert = null;
        }
        if (insert == null) {
            return -1;
        }
        long parseLong = Long.parseLong(insert.getLastPathSegment());
        if (!request.a()) {
            return parseLong;
        }
        XlTaskHelper.a().a(parseLong);
        return parseLong;
    }

    private static String b(String[] strArr) {
        return new StringBuilder("TaskGroup-").append(strArr.length).append(SocializeConstants.OP_DIVIDER_MINUS).append(System.currentTimeMillis()).toString();
    }

    private static Uri a(String[] strArr, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null) {
            stringBuilder.append(str);
        }
        stringBuilder.append(System.currentTimeMillis());
        for (String str2 : strArr) {
            stringBuilder.append(str2);
        }
        return Uri.parse(new StringBuilder(TASK_GROUP_URI_PREFIX).append(stringBuilder.toString()).toString());
    }

    private long[] c(List<Long> list) {
        Cursor cursor = null;
        if (list == null || list.size() == 0) {
            return cursor;
        }
        try {
            cursor = this.c.query(this.f, new String[]{COLUMN_ID}, a((List) list).replaceAll(COLUMN_ID, Impl.COLUMN_GROUP_ID), b((List) list), null);
            long[] jArr = new long[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext()) {
                jArr[i] = cursor.getLong(0);
                i++;
            }
            if (cursor == null) {
                return jArr;
            }
            cursor.close();
            return jArr;
        } catch (Throwable e) {
            try {
                e.printStackTrace();
                am.a(e);
                if (cursor != null) {
                    cursor.close();
                }
                return new long[0];
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    @Deprecated
    public void setRecommandMaxConcurrentBtSubDownloads(int i) {
        am.b(LOG_TAG, new StringBuilder("setRecommandMaxConcurrentBtSubDownloads count=").append(i).toString());
        setMaxConcurrentSubDownloads(i);
    }

    @Deprecated
    public int getRecommandMaxConcurrentBtSubDownloads() {
        return getMaxConcurrentSubDownloads();
    }

    public int getMaxConcurrentSubDownloads() {
        int a = ab.a(this.d, KEY_RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS, (int) RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS);
        try {
            return Integer.parseInt(getProperty(KEY_RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS, String.valueOf(a)));
        } catch (Exception e) {
            return a;
        }
    }

    public void setMaxConcurrentSubDownloads(int i) {
        am.b(LOG_TAG, new StringBuilder("setMaxConcurrentSubDownloads count=").append(i).toString());
        setProperty(KEY_RECOMMENDED_MAX_CONCURRENT_BT_SUB_DOWNLOADS, String.valueOf(i));
    }

    public void setRecommandMaxConcurrentDownloads(int i) {
        am.b(LOG_TAG, new StringBuilder("setRecommandMaxConcurrentDownloads count=").append(i).toString());
        setProperty(KEY_RECOMMENDED_MAX_CONCURRENT_DOWNLOADS, String.valueOf(i));
    }

    public int getRecommandMaxConcurrentDownloads() {
        int a = ab.a(this.d, KEY_RECOMMENDED_MAX_CONCURRENT_DOWNLOADS, (int) RECOMMENDED_MAX_CONCURRENT_DOWNLOADS);
        try {
            return Integer.parseInt(getProperty(KEY_RECOMMENDED_MAX_CONCURRENT_DOWNLOADS, String.valueOf(a)));
        } catch (Exception e) {
            return a;
        }
    }

    public String getProperty(String str, String str2) {
        try {
            Cursor query = this.c.query(DownloadProvider.d, null, "_key=?", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndexOrThrow(y.l));
                    }
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                    return str2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            e = e2;
            query = null;
            try {
                Throwable e3;
                e3.printStackTrace();
                am.a(e3);
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                e3 = th;
                if (query != null) {
                    query.close();
                }
                throw e3;
            }
            return str2;
        } catch (Throwable th2) {
            e3 = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e3;
        }
        return str2;
    }

    public int setProperty(String str, String str2) {
        am.b(LOG_TAG, new StringBuilder("setProperty key=").append(str).append(", value=").append(str2).toString());
        if (str == null || str.length() == 0 || str2 == null) {
            return 0;
        }
        ContentValues contentValues;
        if (str.equals(Property.PROP_USER_ID)) {
            XLDownloadManager.getInstance(this.d).setUserId(str2);
            if (str2.equals(a.d)) {
                contentValues = new ContentValues();
                contentValues.put(COLUMN_IS_VIP_SPEEDUP, Integer.valueOf(0));
                contentValues.put(COLUMN_IS_LX_SPEEDUP, Integer.valueOf(0));
                contentValues.put(COLUMN_VIP_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
                contentValues.put(COLUMN_LX_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
                try {
                    this.c.update(this.f, contentValues, null, null);
                } catch (Throwable e) {
                    am.a(e);
                    return INVAlID_TASK_ID;
                }
            }
        }
        contentValues = new ContentValues();
        contentValues.put(y.k, str);
        contentValues.put(y.l, str2);
        try {
            if (this.c.update(DownloadProvider.d, contentValues, "_key=?", new String[]{str}) <= 0) {
                return this.c.insert(DownloadProvider.d, contentValues) != null ? 1 : 0;
            } else {
                return 1;
            }
        } catch (Throwable e2) {
            e2.printStackTrace();
            am.a(e2);
            return 0;
        }
    }

    public int setAllowedNetworkTypes(int i, long... jArr) {
        am.b(LOG_TAG, new StringBuilder("setAllowedNetworkTypes flag=").append(i).append(", ids=").append(Arrays.toString(jArr)).toString());
        if (i == 8) {
            i = STATUS_RUNNING;
            XlTaskHelper.a().a(jArr);
        } else if (i == 1) {
            am.d(LOG_TAG, "must include Request.NETWORK_WIFI");
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Impl.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(i));
        try {
            if (jArr.length == 1) {
                return this.c.update(ContentUris.withAppendedId(this.f, jArr[0]), contentValues, null, null);
            }
            return jArr.length > 0 ? this.c.update(this.f, contentValues, a(jArr), b(jArr)) : 0;
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            return 0;
        }
    }

    public String getPlayUrl(String str) throws DownloadManagerException {
        am.b(LOG_TAG, new StringBuilder("getPlayUrl path=").append(str).toString());
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            throw new DownloadManagerException(492, "file not exist");
        }
        XLTaskLocalUrl xLTaskLocalUrl = new XLTaskLocalUrl();
        int localUrl = XLDownloadManager.getInstance(this.d).getLocalUrl(str, xLTaskLocalUrl);
        if (localUrl == 9000) {
            return xLTaskLocalUrl.mStrUrl;
        }
        throw new DownloadManagerException(localUrl, new StringBuilder("errcode:").append(localUrl).toString());
    }

    public int setPlayTask(long j) {
        return setPlayTask(j, -1);
    }

    public int setPlayTask(long j, long j2) {
        Cursor query;
        Throwable e;
        am.b(LOG_TAG, new StringBuilder("setPlayTask id=").append(j).append(", index = ").append(j2).toString());
        if (j >= 0) {
            if (j2 >= 0) {
                Cursor cursor = null;
                try {
                    query = this.c.query(DownloadProvider.c, new String[]{COLUMN_STATUS}, "bt_parent_id=? AND bt_sub_index=?", new String[]{String.valueOf(j), String.valueOf(j2)}, null);
                    try {
                        if (!query.moveToFirst()) {
                            if (query != null) {
                                query.close();
                            }
                            return ErrCode.ERR_BAN;
                        } else if (Impl.isStatusCompleted(query.getInt(query.getColumnIndex(COLUMN_STATUS)))) {
                            if (query != null) {
                                query.close();
                            }
                            return AppbarJsBridge.AUTHORIZE_FAIL;
                        } else if (query != null) {
                            query.close();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        am.a(e);
                        if (query != null) {
                            query.close();
                        }
                        return ErrorCode.PathIsNull;
                    }
                } catch (Exception e3) {
                    e = e3;
                    query = null;
                    try {
                        e.printStackTrace();
                        am.a(e);
                        if (query != null) {
                            query.close();
                        }
                        return ErrorCode.PathIsNull;
                    } catch (Throwable th) {
                        e = th;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw e;
                }
            }
            String[] strArr = new String[]{Impl.COLUMN_CONTROL, COLUMN_STATUS};
            Cursor query2;
            try {
                Uri withAppendedId = ContentUris.withAppendedId(this.f, j);
                query2 = this.c.query(withAppendedId, strArr, null, null, null);
                try {
                    int columnIndex = query2.getColumnIndex(COLUMN_STATUS);
                    int columnIndex2 = query2.getColumnIndex(Impl.COLUMN_CONTROL);
                    if (query2.moveToFirst()) {
                        columnIndex = query2.getInt(columnIndex);
                        columnIndex2 = query2.getInt(columnIndex2);
                        if (Impl.isStatusCompleted(columnIndex)) {
                            if (query2 != null) {
                                query2.close();
                            }
                            return INVAlID_TASK_ID;
                        }
                        if (!(columnIndex2 == 0 && (columnIndex == 190 || columnIndex == 192))) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(Impl.COLUMN_CONTROL, Integer.valueOf(0));
                            contentValues.put(COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
                            if (this.c.update(withAppendedId, contentValues, null, null) != 1) {
                                if (query2 != null) {
                                    query2.close();
                                }
                                return Tabs.TAB_CREATE_REACH_MAX_COUNT;
                            }
                        }
                        if (query2 != null) {
                            query2.close();
                        }
                    } else {
                        if (query2 != null) {
                            query2.close();
                        }
                        return AppbarJsBridge.Code_Java_Exception;
                    }
                } catch (Exception e4) {
                    e = e4;
                    query = query2;
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                    return ErrCode.ERR_AUTH_DENIED;
                } catch (Throwable th3) {
                    e = th3;
                    if (query2 != null) {
                        query2.close();
                    }
                    throw e;
                }
            } catch (Exception e5) {
                e = e5;
                query = null;
                try {
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                    return ErrCode.ERR_AUTH_DENIED;
                } catch (Throwable th4) {
                    e = th4;
                    query2 = query;
                    if (query2 != null) {
                        query2.close();
                    }
                    throw e;
                }
            } catch (Throwable th5) {
                e = th5;
                query2 = null;
                if (query2 != null) {
                    query2.close();
                }
                throw e;
            }
        }
        v.a().a(j, j2);
        v.a().a(this.d);
        return STATUS_PENDING;
    }

    public boolean setSpeedLimit(long j, long j2) {
        long j3;
        long j4;
        if (j < -1) {
            j3 = -1;
        } else {
            j3 = j;
        }
        if (j2 < -1) {
            j2 = -1;
        }
        int property = (setProperty(KEY_DOWNLOAD_SPEED_LIMIT, String.valueOf(j3)) + 0) + setProperty(KEY_UPLOAD_SPEED_LIMIT, String.valueOf(j2));
        if (j3 > 0) {
            j4 = j3 * 1024;
        } else {
            j4 = j3;
        }
        if (j2 > 0) {
            j2 *= 1024;
        }
        XLDownloadManager.getInstance(this.d).setSpeedLimit(j4, j2);
        return property == 2 ? SHOW_NOTIFY : a;
    }

    public long getDownloadSpeedLimit() {
        try {
            return Long.parseLong(getProperty(KEY_DOWNLOAD_SPEED_LIMIT, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE));
        } catch (Exception e) {
            return -1;
        }
    }

    public long getUploadSpeedLimit() {
        try {
            return Long.parseLong(getProperty(KEY_UPLOAD_SPEED_LIMIT, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE));
        } catch (Exception e) {
            return -1;
        }
    }

    public int checkHighSpeedTrialResource(long j) {
        int a = n.a().a(j);
        am.b(LOG_TAG, new StringBuilder("checkHighSpeedTrialResource id=").append(j).append(", ret = ").append(a).toString());
        return a;
    }

    public int getHighSpeedTrialTimes(long j) {
        int b = n.a().b(j);
        am.b(LOG_TAG, new StringBuilder("getHighSpeedTrialTimes id=").append(j).append(", ret = ").append(b).toString());
        return b;
    }

    public void enterHighSpeedTrial(long j) {
        n.a().c(j);
        am.b(LOG_TAG, new StringBuilder("enterHighSpeedTrial id=").append(j).toString());
    }

    public boolean isEnteredHighSpeedTrial(long j) {
        boolean e = n.a().e(j);
        am.b(LOG_TAG, new StringBuilder("isEnteredHighSpeedTrial id=").append(j).append(", ret = ").append(e).toString());
        return e;
    }

    public int getHighSpeedDuration(long j) {
        int f = n.a().f(j);
        am.b(LOG_TAG, new StringBuilder("getHighSpeedDuration id=").append(j).append(", ret = ").append(f).toString());
        return f;
    }

    public void stopHighSpeedTrial(long j) {
        am.b(LOG_TAG, new StringBuilder("stopHighSpeedTrial, id=").append(j).toString());
        n.a().d(j);
    }

    public long getMaxDownloadSpeed() throws DownloadManagerException {
        am.b(LOG_TAG, "getMaxDownloadSpeed");
        MaxDownloadSpeedParam maxDownloadSpeedParam = new MaxDownloadSpeedParam();
        int maxDownloadSpeed = XLDownloadManager.getInstance(this.d).getMaxDownloadSpeed(maxDownloadSpeedParam);
        if (maxDownloadSpeed == 9000) {
            return maxDownloadSpeedParam.mSpeed;
        }
        throw new DownloadManagerException(maxDownloadSpeed, new StringBuilder("errcode:").append(maxDownloadSpeed).toString());
    }

    public void setHighSpeedTrialEnable(boolean z) {
        am.b(LOG_TAG, new StringBuilder("setHighSpeedTrialEnable enable=").append(z).toString());
        n.a().a(z);
    }

    public boolean getHighSpeedTrialEnable() {
        boolean b = n.a().b();
        am.b(LOG_TAG, new StringBuilder("getHighSpeedTrialEnable enable=").append(b).toString());
        return b;
    }
}
