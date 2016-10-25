package org.a.a;

import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class d {
    private int a;
    private Reader b;
    private char c;
    private boolean d;

    private d(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.b = reader;
        this.d = false;
        this.a = 0;
    }

    public d(String str) {
        this(new StringReader(str));
    }

    private String a(int i) {
        int i2 = 0;
        if (i == 0) {
            return BuildConfig.VERSION_NAME;
        }
        char[] cArr = new char[i];
        if (this.d) {
            this.d = false;
            cArr[0] = this.c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (Throwable e) {
                throw new a(e);
            }
        }
        this.a += i2;
        if (i2 < i) {
            throw a("Substring bounds error");
        }
        this.c = cArr[i - 1];
        return new String(cArr);
    }

    public final a a(String str) {
        return new a(str + toString());
    }

    public final void a() {
        if (this.d || this.a <= 0) {
            throw new a("Stepping back two steps is not supported");
        }
        this.a--;
        this.d = true;
    }

    public final char b() {
        if (this.d) {
            this.d = false;
            if (this.c != '\u0000') {
                this.a++;
            }
            return this.c;
        }
        try {
            int read = this.b.read();
            if (read <= 0) {
                this.c = '\u0000';
                return '\u0000';
            }
            this.a++;
            this.c = (char) read;
            return this.c;
        } catch (Throwable e) {
            throw new a(e);
        }
    }

    public final char c() {
        while (true) {
            char b = b();
            if (b == '/') {
                switch (b()) {
                    case R.styleable.AppCompatTheme_dialogTheme:
                        while (true) {
                            b = b();
                            if (b == '\u0000') {
                                throw a("Unclosed comment");
                            } else if (b == '*' && b() != '/') {
                                a();
                            }
                        }
                    case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                        do {
                            b = b();
                            if (b == '\n' || b == '\r') {
                            }
                        } while (b != '\u0000');
                    default:
                        a();
                        return '/';
                }
            } else if (b == '#') {
                do {
                    b = b();
                    if (b == '\n' || b == '\r') {
                    }
                } while (b != '\u0000');
            } else {
                if (b == '\u0000' || b > ' ') {
                }
                return b;
            }
        }
    }

    public final Object d() {
        char c = c();
        switch (c) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable:
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle:
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    char b = b();
                    switch (b) {
                        case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                        case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        case R.styleable.Toolbar_titleMarginStart:
                            throw a("Unterminated string");
                        case R.styleable.AppCompatTheme_alertDialogButtonGroupStyle:
                            b = b();
                            switch (b) {
                                case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                                    stringBuffer.append('\b');
                                    break;
                                case R.styleable.AppCompatTheme_checkboxStyle:
                                    stringBuffer.append('\f');
                                    break;
                                case R.styleable.AppCompatTheme_spinnerStyle:
                                    stringBuffer.append('\n');
                                    break;
                                case 'r':
                                    stringBuffer.append('\r');
                                    break;
                                case XLPayErrorCode.XLP_ORDER_CANT_PAY:
                                    stringBuffer.append('\t');
                                    break;
                                case 'u':
                                    stringBuffer.append((char) Integer.parseInt(a((int) MqttConnectOptions.MQTT_VERSION_3_1_1), SpdyProtocol.CUSTOM));
                                    break;
                                case 'x':
                                    stringBuffer.append((char) Integer.parseInt(a((int) SimpleLog.LOG_LEVEL_DEBUG), SpdyProtocol.CUSTOM));
                                    break;
                                default:
                                    stringBuffer.append(b);
                                    break;
                            }
                            break;
                        default:
                            if (b == c) {
                                return stringBuffer.toString();
                            }
                            stringBuffer.append(b);
                            break;
                    }
                }
                break;
            case R.styleable.AppCompatTheme_textAppearanceLargePopupMenu:
            case R.styleable.AppCompatTheme_alertDialogStyle:
                a();
                return new b(this);
            case '{':
                a();
                return new c(this);
            default:
                StringBuffer stringBuffer2 = new StringBuffer();
                char c2 = c;
                while (c2 >= ' ' && ",:]}/\\\"[{;=#".indexOf(c2) < 0) {
                    stringBuffer2.append(c2);
                    c2 = b();
                }
                a();
                String trim = stringBuffer2.toString().trim();
                if (trim.equals(BuildConfig.VERSION_NAME)) {
                    throw a("Missing value");
                } else if (trim.equalsIgnoreCase("true")) {
                    return Boolean.TRUE;
                } else {
                    if (trim.equalsIgnoreCase("false")) {
                        return Boolean.FALSE;
                    }
                    if (trim.equalsIgnoreCase("null")) {
                        return c.b;
                    }
                    if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+') {
                        return trim;
                    }
                    if (c == '0') {
                        if (trim.length() <= 2 || !(trim.charAt(1) == 'x' || trim.charAt(1) == 'X')) {
                            try {
                                return new Integer(Integer.parseInt(trim, SpdyProtocol.PUBKEY_SEQ_ADASH));
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                return new Integer(Integer.parseInt(trim.substring(SimpleLog.LOG_LEVEL_DEBUG), SpdyProtocol.CUSTOM));
                            } catch (Exception e2) {
                            }
                        }
                    }
                    try {
                        return new Integer(trim);
                    } catch (Exception e3) {
                        try {
                            return new Long(trim);
                        } catch (Exception e4) {
                            try {
                                return new Double(trim);
                            } catch (Exception e5) {
                                return trim;
                            }
                        }
                    }
                }
        }
    }

    public final String toString() {
        return new StringBuilder(" at character ").append(this.a).toString();
    }
}
