package org.a.a;

import com.xunlei.xllib.R;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class c {
    public static final Object b;
    public Map a;

    private static final class a {
        private a() {
        }

        protected final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return "null";
        }
    }

    static {
        b = new a();
    }

    public c() {
        this.a = new HashMap();
    }

    public c(String str) {
        this(new d(str));
    }

    public c(Map map) {
        if (map == null) {
            map = new HashMap();
        }
        this.a = map;
    }

    public c(d dVar) {
        this();
        if (dVar.c() != '{') {
            throw dVar.a("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (dVar.c()) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    throw dVar.a("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    dVar.a();
                    String toString = dVar.d().toString();
                    char c = dVar.c();
                    if (c == '=') {
                        if (dVar.b() != '>') {
                            dVar.a();
                        }
                    } else if (c != ':') {
                        throw dVar.a("Expected a ':' after a key");
                    }
                    Object d = dVar.d();
                    if (toString == null) {
                        throw new a("Null key.");
                    }
                    if (d != null) {
                        b(d);
                        this.a.put(toString, d);
                    } else {
                        this.a.remove(toString);
                    }
                    switch (dVar.c()) {
                        case R.styleable.AppCompatTheme_listDividerAlertDialog:
                        case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                            if (dVar.c() != '}') {
                                dVar.a();
                            } else {
                                return;
                            }
                        case '}':
                            return;
                        default:
                            throw dVar.a("Expected a ',' or '}'");
                    }
            }
        }
    }

    static String a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof c) || (obj instanceof b)) ? obj.toString() : obj instanceof Map ? new c((Map) obj).toString() : obj instanceof Collection ? new b((Collection) obj).toString() : obj.getClass().isArray() ? new b(obj).toString() : b(obj.toString());
        } else {
            obj = (Number) obj;
            if (obj == null) {
                throw new a("Null pointer");
            }
            b(obj);
            String toString = obj.toString();
            if (toString.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight) <= 0 || toString.indexOf(R.styleable.AppCompatTheme_buttonStyleSmall) >= 0 || toString.indexOf(R.styleable.AppCompatTheme_listPreferredItemHeight) >= 0) {
                return toString;
            }
            while (toString.endsWith("0")) {
                toString = toString.substring(0, toString.length() - 1);
            }
            return toString.endsWith(".") ? toString.substring(0, toString.length() - 1) : toString;
        }
    }

    public static String b(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append('\"');
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    stringBuffer.append("\\b");
                    break;
                case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                    stringBuffer.append("\\t");
                    break;
                case SpdyProtocol.PUBKEY_SEQ_OPEN:
                    stringBuffer.append("\\n");
                    break;
                case R.styleable.Toolbar_titleMargins:
                    stringBuffer.append("\\f");
                    break;
                case R.styleable.Toolbar_titleMarginStart:
                    stringBuffer.append("\\r");
                    break;
                case R.styleable.AppCompatTheme_actionModePasteDrawable:
                case R.styleable.AppCompatTheme_alertDialogButtonGroupStyle:
                    stringBuffer.append('\\');
                    stringBuffer.append(charAt);
                    break;
                case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                    if (i2 == 60) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt);
                    break;
                default:
                    if (charAt >= ' ') {
                        if ((charAt < '\u0080' || charAt >= '\u00a0') && (charAt < '\u2000' || charAt >= '\u2100')) {
                            stringBuffer.append(charAt);
                        }
                    }
                    String toString = new StringBuilder("000").append(Integer.toHexString(charAt)).toString();
                    stringBuffer.append(new StringBuilder("\\u").append(toString.substring(toString.length() - 4)).toString());
                    break;
            }
            i++;
            char c = charAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    private static void b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                throw new a("JSON does not allow non-finite numbers.");
            }
        } else if (!(obj instanceof Float)) {
        } else {
            if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                throw new a("JSON does not allow non-finite numbers.");
            }
        }
    }

    public final Object a(String str) {
        Object obj = str == null ? null : this.a.get(str);
        if (obj != null) {
            return obj;
        }
        throw new a(new StringBuilder("JSONObject[").append(b(str)).append("] not found.").toString());
    }

    public final Iterator a() {
        return this.a.keySet().iterator();
    }

    public String toString() {
        try {
            Iterator a = a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a.next();
                stringBuffer.append(b(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(a(this.a.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
