package com.tencent.mm.sdk.openapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import com.tencent.mm.sdk.c.a.a;
import com.tencent.mm.sdk.c.a.b;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class MMSharedPreferences implements SharedPreferences {
    private final String[] columns;
    private final ContentResolver cr;
    private REditor editor;
    private final HashMap<String, Object> values;

    private static class REditor implements Editor {
        private boolean clear;
        private ContentResolver cr;
        private Set<String> remove;
        private Map<String, Object> values;

        public REditor(ContentResolver contentResolver) {
            this.values = new HashMap();
            this.remove = new HashSet();
            this.clear = false;
            this.cr = contentResolver;
        }

        public void apply() {
        }

        public Editor clear() {
            this.clear = true;
            return this;
        }

        public boolean commit() {
            ContentValues contentValues = new ContentValues();
            if (this.clear) {
                this.cr.delete(b.CONTENT_URI, null, null);
                this.clear = false;
            }
            for (String str : this.remove) {
                this.cr.delete(b.CONTENT_URI, "key = ?", new String[]{str});
            }
            for (Entry entry : this.values.entrySet()) {
                int i;
                boolean z;
                Object value = entry.getValue();
                if (value == null) {
                    com.tencent.mm.sdk.b.b.b("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
                    i = 0;
                } else if (value instanceof Integer) {
                    i = 1;
                } else if (value instanceof Long) {
                    i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                } else if (value instanceof String) {
                    i = XZBDevice.DOWNLOAD_LIST_FAILED;
                } else if (value instanceof Boolean) {
                    i = XZBDevice.DOWNLOAD_LIST_ALL;
                } else if (value instanceof Float) {
                    i = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                } else if (value instanceof Double) {
                    i = R.styleable.Toolbar_contentInsetEnd;
                } else {
                    com.tencent.mm.sdk.b.b.b("MicroMsg.SDK.PluginProvider.Resolver", new StringBuilder("unresolve failed, unknown type=").append(value.getClass().toString()).toString());
                    i = 0;
                }
                if (i == 0) {
                    z = false;
                } else {
                    contentValues.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(i));
                    contentValues.put(RequestHeaders.COLUMN_VALUE, value.toString());
                    z = true;
                }
                if (z) {
                    this.cr.update(b.CONTENT_URI, contentValues, "key = ?", new String[]{(String) entry.getKey()});
                }
            }
            return true;
        }

        public Editor putBoolean(String str, boolean z) {
            this.values.put(str, Boolean.valueOf(z));
            this.remove.remove(str);
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.values.put(str, Float.valueOf(f));
            this.remove.remove(str);
            return this;
        }

        public Editor putInt(String str, int i) {
            this.values.put(str, Integer.valueOf(i));
            this.remove.remove(str);
            return this;
        }

        public Editor putLong(String str, long j) {
            this.values.put(str, Long.valueOf(j));
            this.remove.remove(str);
            return this;
        }

        public Editor putString(String str, String str2) {
            this.values.put(str, str2);
            this.remove.remove(str);
            return this;
        }

        public Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        public Editor remove(String str) {
            this.remove.add(str);
            return this;
        }
    }

    public MMSharedPreferences(Context context) {
        this.columns = new String[]{DownloadManager.COLUMN_ID, "key", JsInterface.FUNPLAY_AD_TRPE, RequestHeaders.COLUMN_VALUE};
        this.values = new HashMap();
        this.editor = null;
        this.cr = context.getContentResolver();
    }

    private Object getValue(String str) {
        try {
            Cursor query = this.cr.query(b.CONTENT_URI, this.columns, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object a = query.moveToFirst() ? a.a(query.getInt(query.getColumnIndex(JsInterface.FUNPLAY_AD_TRPE)), query.getString(query.getColumnIndex(RequestHeaders.COLUMN_VALUE))) : null;
            query.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean contains(String str) {
        return getValue(str) != null;
    }

    public Editor edit() {
        if (this.editor == null) {
            this.editor = new REditor(this.cr);
        }
        return this.editor;
    }

    public Map<String, ?> getAll() {
        try {
            Cursor query = this.cr.query(b.CONTENT_URI, this.columns, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex("key");
            int columnIndex2 = query.getColumnIndex(JsInterface.FUNPLAY_AD_TRPE);
            int columnIndex3 = query.getColumnIndex(RequestHeaders.COLUMN_VALUE);
            while (query.moveToNext()) {
                this.values.put(query.getString(columnIndex), a.a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.values;
        } catch (Exception e) {
            e.printStackTrace();
            return this.values;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Boolean)) ? z : ((Boolean) value).booleanValue();
    }

    public float getFloat(String str, float f) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Float)) ? f : ((Float) value).floatValue();
    }

    public int getInt(String str, int i) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Integer)) ? i : ((Integer) value).intValue();
    }

    public long getLong(String str, long j) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Long)) ? j : ((Long) value).longValue();
    }

    public String getString(String str, String str2) {
        Object value = getValue(str);
        return (value == null || !(value instanceof String)) ? str2 : (String) value;
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
