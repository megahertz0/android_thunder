package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;

public class SimpleArg implements BaseArg {
    public static final String KEY_VALUE = "value";
    public Object value;

    public SimpleArg() {
        this.value = null;
    }

    public boolean checkArgs() {
        return this.value != null;
    }

    public void fromBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SimpleArg.class.getClassLoader());
            Object obj = bundle.get(KEY_VALUE);
            if (obj != null) {
                this.value = obj;
            }
        }
    }

    public void toBundle(Bundle bundle) {
        Object obj = this.value;
        if (obj != null && bundle != null) {
            if (obj instanceof Integer) {
                bundle.putInt(KEY_VALUE, ((Integer) this.value).intValue());
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(KEY_VALUE, ((Boolean) this.value).booleanValue());
            } else if (obj instanceof String) {
                bundle.putString(KEY_VALUE, (String) obj);
            } else if (obj instanceof Double) {
                bundle.putDouble(KEY_VALUE, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(KEY_VALUE, ((Float) obj).floatValue());
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable(KEY_VALUE, (Parcelable) obj);
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(KEY_VALUE, (Serializable) obj);
            }
        }
    }
}
