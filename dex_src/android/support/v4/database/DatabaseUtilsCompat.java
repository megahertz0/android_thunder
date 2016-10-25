package android.support.v4.database;

import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;

public final class DatabaseUtilsCompat {
    private DatabaseUtilsCompat() {
    }

    public static String concatenateWhere(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return TextUtils.isEmpty(str2) ? str : new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(str).append(") AND (").append(str2).append(SocializeConstants.OP_CLOSE_PAREN).toString();
    }

    public static String[] appendSelectionArgs(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0) {
            return strArr2;
        }
        Object obj = new Object[(strArr.length + strArr2.length)];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        System.arraycopy(strArr2, 0, obj, strArr.length, strArr2.length);
        return obj;
    }
}
