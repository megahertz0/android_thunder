package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class DocumentsContractApi19 {
    private static final String TAG = "DocumentFile";

    DocumentsContractApi19() {
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }

    public static String getName(Context context, Uri uri) {
        return queryForString(context, uri, "_display_name", null);
    }

    private static String getRawType(Context context, Uri uri) {
        return queryForString(context, uri, "mime_type", null);
    }

    public static String getType(Context context, Uri uri) {
        String rawType = getRawType(context, uri);
        return "vnd.android.document/directory".equals(rawType) ? null : rawType;
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }

    public static boolean isFile(Context context, Uri uri) {
        CharSequence rawType = getRawType(context, uri);
        return ("vnd.android.document/directory".equals(rawType) || TextUtils.isEmpty(rawType)) ? false : true;
    }

    public static long lastModified(Context context, Uri uri) {
        return queryForLong(context, uri, "last_modified", 0);
    }

    public static long length(Context context, Uri uri) {
        return queryForLong(context, uri, "_size", 0);
    }

    public static boolean canRead(Context context, Uri uri) {
        return context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty(getRawType(context, uri));
    }

    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, XZBDevice.DOWNLOAD_LIST_RECYCLE) != 0) {
            return false;
        }
        CharSequence rawType = getRawType(context, uri);
        int queryForInt = queryForInt(context, uri, Constants.KEY_FLAGS, 0);
        if (TextUtils.isEmpty(rawType)) {
            return false;
        }
        if ((queryForInt & 4) != 0) {
            return true;
        }
        if (!"vnd.android.document/directory".equals(rawType) || (queryForInt & 8) == 0) {
            return (TextUtils.isEmpty(rawType) || (queryForInt & 2) == 0) ? false : true;
        } else {
            return true;
        }
    }

    public static boolean delete(Context context, Uri uri) {
        return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
    }

    public static boolean exists(Context context, Uri uri) {
        Object e;
        try {
            AutoCloseable query = context.getContentResolver().query(uri, new String[]{"document_id"}, null, null, null);
            try {
                boolean z = query.getCount() > 0;
                closeQuietly(query);
                return z;
            } catch (Exception e2) {
                e = e2;
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return false;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            closeQuietly(query);
            throw th2;
        }
    }

    private static String queryForString(Context context, Uri uri, String str, String str2) {
        Object e;
        try {
            AutoCloseable query = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    closeQuietly(query);
                    return str2;
                }
                str2 = query.getString(0);
                closeQuietly(query);
                return str2;
            } catch (Exception e2) {
                e = e2;
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return str2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return str2;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            closeQuietly(query);
            throw th2;
        }
    }

    private static int queryForInt(Context context, Uri uri, String str, int i) {
        return (int) queryForLong(context, uri, str, (long) i);
    }

    private static long queryForLong(Context context, Uri uri, String str, long j) {
        Object e;
        try {
            AutoCloseable query = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    closeQuietly(query);
                    return j;
                }
                j = query.getLong(0);
                closeQuietly(query);
                return j;
            } catch (Exception e2) {
                e = e2;
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return j;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                new StringBuilder("Failed query: ").append(e);
                closeQuietly(query);
                return j;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            closeQuietly(query);
            throw th2;
        }
    }

    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
