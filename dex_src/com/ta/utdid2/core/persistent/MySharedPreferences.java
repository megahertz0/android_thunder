package com.ta.utdid2.core.persistent;

import java.util.Map;

public interface MySharedPreferences {

    public static interface MyEditor {
        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor clear();

        boolean commit();

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor putBoolean(String str, boolean z);

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor putFloat(String str, float f);

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor putInt(String str, int i);

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor putLong(String str, long j);

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor putString(String str, String str2);

        com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor remove(String str);
    }

    public static interface OnSharedPreferenceChangeListener {
        void onSharedPreferenceChanged(MySharedPreferences mySharedPreferences, String str);
    }

    boolean checkFile();

    boolean contains(String str);

    MyEditor edit();

    Map<String, ?> getAll();

    boolean getBoolean(String str, boolean z);

    float getFloat(String str, float f);

    int getInt(String str, int i);

    long getLong(String str, long j);

    String getString(String str, String str2);

    void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);
}
