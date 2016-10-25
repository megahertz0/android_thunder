package com.tencent.open.b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

// compiled from: ProGuard
public class b implements Serializable {
    public final HashMap<String, String> a;

    public b(Bundle bundle) {
        this.a = new HashMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.a.put(str, bundle.getString(str));
            }
        }
    }
}
