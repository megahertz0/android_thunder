package org.a.a;

import com.xunlei.xllib.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class b {
    public ArrayList a;

    public b() {
        this.a = new ArrayList();
    }

    public b(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.a.add(Array.get(obj, i));
            }
            return;
        }
        throw new a("JSONArray initial value should be a string or collection or array.");
    }

    public b(String str) {
        this(new d(str));
    }

    public b(Collection collection) {
        this.a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public b(d dVar) {
        this();
        char c = dVar.c();
        if (c == '[') {
            c = ']';
        } else if (c == '(') {
            c = ')';
        } else {
            throw dVar.a("A JSONArray text must start with '['");
        }
        if (dVar.c() != ']') {
            dVar.a();
            while (true) {
                if (dVar.c() == ',') {
                    dVar.a();
                    this.a.add(null);
                } else {
                    dVar.a();
                    this.a.add(dVar.d());
                }
                char c2 = dVar.c();
                switch (c2) {
                    case R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu:
                    case R.styleable.AppCompatTheme_alertDialogCenterButtons:
                        if (c != c2) {
                            throw dVar.a(new StringBuilder("Expected a '").append(new Character(c)).append("'").toString());
                        }
                        return;
                    case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                        if (dVar.c() != ']') {
                            dVar.a();
                        } else {
                            return;
                        }
                    default:
                        throw dVar.a("Expected a ',' or ']'");
                }
            }
        }
    }

    private String a(String str) {
        int size = this.a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(c.a(this.a.get(i)));
        }
        return stringBuffer.toString();
    }

    public final Object a(int i) {
        Object obj = (i < 0 || i >= this.a.size()) ? null : this.a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new a(new StringBuilder("JSONArray[").append(i).append("] not found.").toString());
    }

    public String toString() {
        try {
            return new StringBuilder("[").append(a(",")).append(']').toString();
        } catch (Exception e) {
            return null;
        }
    }
}
