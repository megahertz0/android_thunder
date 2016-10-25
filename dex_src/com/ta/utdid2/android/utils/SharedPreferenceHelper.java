package com.ta.utdid2.android.utils;

import android.annotation.TargetApi;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceHelper {
    @TargetApi(9)
    public static void apply(Editor editor) {
        if (editor != null) {
            editor.apply();
        }
    }
}
