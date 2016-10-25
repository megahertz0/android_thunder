package com.tencent.mm.sdk.diffdev;

import com.tencent.mm.sdk.diffdev.a.a;

public class DiffDevOAuthFactory {
    public static final int MAX_SUPPORTED_VERSION = 1;
    private static final String TAG = "MicroMsg.SDK.DiffDevOAuthFactory";
    public static final int VERSION_1 = 1;
    private static IDiffDevOAuth v1Instance;

    static {
        v1Instance = null;
    }

    private DiffDevOAuthFactory() {
    }

    public static IDiffDevOAuth getDiffDevOAuth() {
        return getDiffDevOAuth(VERSION_1);
    }

    public static IDiffDevOAuth getDiffDevOAuth(int i) {
        if (i > 1) {
            return null;
        }
        switch (i) {
            case VERSION_1:
                if (v1Instance == null) {
                    v1Instance = new a();
                }
                return v1Instance;
            default:
                return null;
        }
    }
}
