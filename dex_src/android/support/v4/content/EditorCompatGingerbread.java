package android.support.v4.content;

import android.content.SharedPreferences.Editor;

class EditorCompatGingerbread {
    EditorCompatGingerbread() {
    }

    public static void apply(Editor editor) {
        try {
            editor.apply();
        } catch (AbstractMethodError e) {
            editor.commit();
        }
    }
}
