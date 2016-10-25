package com.tencent.mm.sdk.b;

import android.os.Bundle;

public final class a {
    public static int a(Bundle bundle, String str) {
        if (bundle == null) {
            return -1;
        }
        try {
            return bundle.getInt(str, -1);
        } catch (Exception e) {
            b.a("MicroMsg.IntentUtil", "getIntExtra exception:%s", e.getMessage());
            return -1;
        }
    }

    public static String b(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            b.a("MicroMsg.IntentUtil", "getStringExtra exception:%s", e.getMessage());
            return null;
        }
    }
}
