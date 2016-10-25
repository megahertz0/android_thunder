package com.xiaomi.account.openauth;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.xiaomi.auth.AuthConstants;

public class XiaomiOAuthResults {
    private final Bundle contentBundle;
    private final Error errorResult;
    private final Success successResult;

    public static class Error {
        public final int errorCode;
        public final String errorMessage;

        public Error(int i, String str) {
            this.errorCode = i;
            this.errorMessage = str;
        }

        public String toString() {
            return new StringBuilder("errorCode=").append(this.errorCode).append(",errorMessage=").append(this.errorMessage).toString();
        }

        private static com.xiaomi.account.openauth.XiaomiOAuthResults.Error parseBundle(Bundle bundle) {
            return new com.xiaomi.account.openauth.XiaomiOAuthResults.Error(XiaomiOAuthResults.getIntCompatibly(bundle, AuthConstants.EXTRA_ERROR_CODE, "error"), XiaomiOAuthResults.getStringCompatibly(bundle, AuthConstants.EXTRA_ERROR_DESCRIPTION, "error_description"));
        }
    }

    private static class Success {
        public final String accessToken;
        public final String code;
        public final String expiresIn;
        public final String macAlgorithm;
        public final String macKey;
        public final String scopes;
        public final String state;
        public final String tokenType;

        public Success(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.accessToken = str;
            this.expiresIn = str2;
            this.scopes = str3;
            this.state = str4;
            this.tokenType = str5;
            this.macKey = str6;
            this.macAlgorithm = str7;
            this.code = str8;
        }

        public String toString() {
            return new StringBuilder("accessToken=").append(this.accessToken).append(",expiresIn=").append(this.expiresIn).append(",scope=").append(this.scopes).append(",state=").append(this.state).append(",tokenType=").append(this.tokenType).append(",macKey=").append(this.macKey).append(",macAlogorithm=").append(this.macAlgorithm).append(",code=").append(this.code).toString();
        }

        private static Success parseBundle(Bundle bundle) {
            return new Success(XiaomiOAuthResults.getStringCompatibly(bundle, Constants.PARAM_ACCESS_TOKEN, AuthConstants.EXTRA_ACCESS_TOKEN), XiaomiOAuthResults.getIntOrStringCompatibly(bundle, Constants.PARAM_EXPIRES_IN, AuthConstants.EXTRA_EXPIRES_IN), XiaomiOAuthResults.getStringCompatibly(bundle, Constants.PARAM_SCOPE, AuthConstants.EXTRA_SCOPE), XiaomiOAuthResults.getStringCompatibly(bundle, "state", AuthConstants.EXTRA_STATE), XiaomiOAuthResults.getStringCompatibly(bundle, "token_type", AuthConstants.EXTRA_TOKEN_TYPE), XiaomiOAuthResults.getStringCompatibly(bundle, "mac_key", AuthConstants.EXTRA_MAC_KEY), XiaomiOAuthResults.getStringCompatibly(bundle, "mac_algorithm", AuthConstants.EXTRA_MAC_ALGORITHM), XiaomiOAuthResults.getStringCompatibly(bundle, com.taobao.accs.common.Constants.KEY_HTTP_CODE, "extra_code"));
        }
    }

    private XiaomiOAuthResults(Bundle bundle, Error error) {
        this.contentBundle = bundle;
        this.successResult = null;
        this.errorResult = error;
    }

    private XiaomiOAuthResults(Bundle bundle, Success success) {
        this.contentBundle = bundle;
        this.successResult = success;
        this.errorResult = null;
    }

    public Bundle getContentBundle() {
        return this.contentBundle;
    }

    public String getAccessToken() {
        return this.successResult != null ? this.successResult.accessToken : null;
    }

    public String getExpiresIn() {
        return this.successResult != null ? this.successResult.expiresIn : null;
    }

    public String getScopes() {
        return this.successResult != null ? this.successResult.scopes : null;
    }

    public String getState() {
        return this.successResult != null ? this.successResult.state : null;
    }

    public String getTokenType() {
        return this.successResult != null ? this.successResult.tokenType : null;
    }

    public String getMacKey() {
        return this.successResult != null ? this.successResult.macKey : null;
    }

    public String getMacAlgorithm() {
        return this.successResult != null ? this.successResult.macAlgorithm : null;
    }

    public String getCode() {
        return this.successResult != null ? this.successResult.code : null;
    }

    public int getErrorCode() {
        return this.errorResult != null ? this.errorResult.errorCode : 0;
    }

    public String getErrorMessage() {
        return this.errorResult != null ? this.errorResult.errorMessage : null;
    }

    public boolean hasError() {
        return this.errorResult != null;
    }

    private static String getStringCompatibly(Bundle bundle, String str, String str2) {
        return bundle.containsKey(str) ? bundle.getString(str) : bundle.getString(str2);
    }

    private static int getIntCompatibly(Bundle bundle, String str, String str2) {
        return bundle.containsKey(str) ? bundle.getInt(str) : bundle.getInt(str2);
    }

    private static String getIntOrStringCompatibly(Bundle bundle, String str, String str2) {
        String[] strArr = new String[]{str, str2};
        for (int i = 0; i < 2; i++) {
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(str3) && bundle.containsKey(str3)) {
                Object obj = bundle.get(str3);
                if (obj != null) {
                    return obj instanceof Integer ? ((Integer) obj).toString() : obj.toString();
                }
            }
        }
        return null;
    }

    public String toString() {
        if (this.successResult != null) {
            return this.successResult.toString();
        }
        if (this.errorResult != null) {
            return this.errorResult.toString();
        }
        throw new IllegalStateException("should not be here.");
    }

    public static XiaomiOAuthResults parseBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return getIntCompatibly(bundle, AuthConstants.EXTRA_ERROR_CODE, "error") != 0 ? new XiaomiOAuthResults(bundle, Error.parseBundle(bundle)) : new XiaomiOAuthResults(bundle, Success.parseBundle(bundle));
    }
}
