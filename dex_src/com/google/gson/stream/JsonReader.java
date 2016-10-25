package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import com.tencent.bugly.Bugly;
import com.xunlei.tdlive.R;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int[] pathIndices;
    private String[] pathNames;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int[] stack;
    private int stackSize;

    static {
        NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            public final void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int access$000 = jsonReader.peeked;
                if (access$000 == 0) {
                    access$000 = jsonReader.doPeek();
                }
                if (access$000 == 13) {
                    jsonReader.peeked = PEEKED_DOUBLE_QUOTED;
                } else if (access$000 == 12) {
                    jsonReader.peeked = PEEKED_SINGLE_QUOTED;
                } else if (access$000 == 14) {
                    jsonReader.peeked = PEEKED_UNQUOTED;
                } else {
                    throw new IllegalStateException(new StringBuilder("Expected a name but was ").append(jsonReader.peek()).append("  at line ").append(jsonReader.getLineNumber()).append(" column ").append(jsonReader.getColumnNumber()).append(" path ").append(jsonReader.getPath()).toString());
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        this.lenient = false;
        this.buffer = new char[1024];
        this.pos = 0;
        this.limit = 0;
        this.lineNumber = 0;
        this.lineStart = 0;
        this.peeked = 0;
        this.stack = new int[32];
        this.stackSize = 0;
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 3) {
            push(PEEKED_BEGIN_OBJECT);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder("Expected BEGIN_ARRAY but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public void endArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder("Expected END_ARRAY but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public void beginObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 1) {
            push(PEEKED_BEGIN_ARRAY);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder("Expected BEGIN_OBJECT but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public void endObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder("Expected END_OBJECT but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public boolean hasNext() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public JsonToken peek() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        switch (i) {
            case PEEKED_BEGIN_OBJECT:
                return JsonToken.BEGIN_OBJECT;
            case PEEKED_END_OBJECT:
                return JsonToken.END_OBJECT;
            case PEEKED_BEGIN_ARRAY:
                return JsonToken.BEGIN_ARRAY;
            case PEEKED_END_ARRAY:
                return JsonToken.END_ARRAY;
            case PEEKED_TRUE:
            case PEEKED_FALSE:
                return JsonToken.BOOLEAN;
            case PEEKED_NULL:
                return JsonToken.NULL;
            case PEEKED_SINGLE_QUOTED:
            case PEEKED_DOUBLE_QUOTED:
            case PEEKED_UNQUOTED:
            case PEEKED_BUFFERED:
                return JsonToken.STRING;
            case PEEKED_SINGLE_QUOTED_NAME:
            case PEEKED_DOUBLE_QUOTED_NAME:
            case PEEKED_UNQUOTED_NAME:
                return JsonToken.NAME;
            case PEEKED_LONG:
            case PEEKED_NUMBER:
                return JsonToken.NUMBER;
            case PEEKED_EOF:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private int doPeek() throws IOException {
        int nextNonWhitespace;
        int i = this.stack[this.stackSize - 1];
        if (i == 1) {
            this.stack[this.stackSize - 1] = 2;
        } else if (i == 2) {
            switch (nextNonWhitespace(true)) {
                case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    break;
                case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                    checkLenient();
                    break;
                case R.styleable.AppCompatTheme_alertDialogCenterButtons:
                    this.peeked = 4;
                    return PEEKED_END_ARRAY;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (i == 5) {
                switch (nextNonWhitespace(true)) {
                    case R.styleable.AppCompatTheme_listDividerAlertDialog:
                        break;
                    case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            nextNonWhitespace = nextNonWhitespace(true);
            switch (nextNonWhitespace) {
                case R.styleable.AppCompatTheme_actionModePasteDrawable:
                    this.peeked = 13;
                    return PEEKED_DOUBLE_QUOTED_NAME;
                case R.styleable.AppCompatTheme_actionModePopupWindowStyle:
                    checkLenient();
                    this.peeked = 12;
                    return PEEKED_SINGLE_QUOTED_NAME;
                case 125:
                    if (i != 5) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    this.pos--;
                    if (isLiteral((char) nextNonWhitespace)) {
                        this.peeked = 14;
                        return PEEKED_UNQUOTED_NAME;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (i == 4) {
            this.stack[this.stackSize - 1] = 5;
            switch (nextNonWhitespace(true)) {
                case R.styleable.AppCompatTheme_toolbarStyle:
                    break;
                case R.styleable.AppCompatTheme_popupWindowStyle:
                    checkLenient();
                    if ((this.pos < this.limit || fillBuffer(PEEKED_BEGIN_OBJECT)) && this.buffer[this.pos] == '>') {
                        this.pos++;
                    }
                    break;
                default:
                    throw syntaxError("Expected ':'");
            }
        } else if (i == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (i == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return PEEKED_EOF;
            }
            checkLenient();
            this.pos--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (nextNonWhitespace(true)) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable:
                if (this.stackSize == 1) {
                    checkLenient();
                }
                this.peeked = 9;
                return PEEKED_DOUBLE_QUOTED;
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle:
                checkLenient();
                this.peeked = 8;
                return PEEKED_SINGLE_QUOTED;
            case R.styleable.AppCompatTheme_listDividerAlertDialog:
            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                if (i != 1 || i == 2) {
                    checkLenient();
                    this.pos--;
                    this.peeked = 7;
                    return 7;
                }
                throw syntaxError("Unexpected value");
            case R.styleable.AppCompatTheme_alertDialogStyle:
                this.peeked = 3;
                return PEEKED_BEGIN_ARRAY;
            case R.styleable.AppCompatTheme_alertDialogCenterButtons:
                if (i == 1) {
                    this.peeked = 4;
                    return PEEKED_END_ARRAY;
                }
                if (i != 1) {
                }
                checkLenient();
                this.pos--;
                this.peeked = 7;
                return 7;
            case 123:
                this.peeked = 1;
                return 1;
            default:
                this.pos--;
                if (this.stackSize == 1) {
                    checkLenient();
                }
                nextNonWhitespace = peekKeyword();
                if (nextNonWhitespace != 0) {
                    return nextNonWhitespace;
                }
                nextNonWhitespace = peekNumber();
                if (nextNonWhitespace != 0) {
                    return nextNonWhitespace;
                }
                if (isLiteral(this.buffer[this.pos])) {
                    checkLenient();
                    this.peeked = 10;
                    return PEEKED_UNQUOTED;
                }
                throw syntaxError("Expected value");
        }
    }

    private int peekKeyword() throws IOException {
        String str;
        int i;
        char c = this.buffer[this.pos];
        String str2;
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = PEEKED_TRUE;
        } else if (c == 'f' || c == 'F') {
            str = Bugly.SDK_IS_DEV;
            str2 = "FALSE";
            i = PEEKED_FALSE;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = PEEKED_NULL;
        }
        int length = str.length();
        int i2 = PEEKED_BEGIN_OBJECT;
        while (i2 < length) {
            if (this.pos + i2 >= this.limit && !fillBuffer(i2 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i;
        return i;
    }

    private int peekNumber() throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        long j = 0;
        Object obj = null;
        int i3 = PEEKED_BEGIN_OBJECT;
        Object obj2 = null;
        int i4 = 0;
        int i5 = i2;
        i2 = i;
        while (true) {
            int i6;
            Object obj3;
            Object obj4;
            if (i2 + i4 == i5) {
                if (i4 == cArr.length) {
                    return PEEKED_NONE;
                }
                if (fillBuffer(i4 + 1)) {
                    i2 = this.pos;
                    i5 = this.limit;
                } else if (i6 != 2 && i3 != 0 && (j != Long.MIN_VALUE || obj != null)) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.peekedLong = j;
                    this.pos += i4;
                    this.peeked = 15;
                    return PEEKED_LONG;
                } else if (i6 == 2 && i6 != 4 && i6 != 7) {
                    return PEEKED_NONE;
                } else {
                    this.peekedNumberLength = i4;
                    this.peeked = 16;
                    return PEEKED_NUMBER;
                }
            }
            char c = cArr[i2 + i4];
            int i7;
            switch (c) {
                case R.styleable.AppCompatTheme_dialogPreferredPadding:
                    if (i6 != 5) {
                        return PEEKED_NONE;
                    }
                    obj3 = PEEKED_FALSE;
                    i6 = i3;
                    obj4 = obj;
                    break;
                case R.styleable.AppCompatTheme_actionDropDownStyle:
                    if (i6 == 0) {
                        obj3 = PEEKED_BEGIN_OBJECT;
                        i7 = i3;
                        i3 = 1;
                        i6 = i7;
                    } else if (i6 != 5) {
                        return PEEKED_NONE;
                    } else {
                        obj3 = PEEKED_FALSE;
                        i6 = i3;
                        obj4 = obj;
                    }
                    break;
                case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight:
                    if (i6 != 2) {
                        return PEEKED_NONE;
                    }
                    obj3 = PEEKED_BEGIN_ARRAY;
                    i6 = i3;
                    obj4 = obj;
                    break;
                case R.styleable.AppCompatTheme_listPreferredItemHeight:
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if (i6 != 2 && i6 != 4) {
                        return PEEKED_NONE;
                    }
                    obj3 = PEEKED_TRUE;
                    i6 = i3;
                    obj4 = obj;
                    break;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i6 == 1 || i6 == 0) {
                            j = (long) (-(c - 48));
                            obj3 = PEEKED_END_OBJECT;
                            i6 = i3;
                            obj4 = obj;
                        } else if (i6 == 2) {
                            if (j == 0) {
                                return PEEKED_NONE;
                            }
                            long j2 = (10 * j) - ((long) (c - 48));
                            i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? PEEKED_BEGIN_OBJECT : PEEKED_NONE;
                            i &= i3;
                            obj4 = obj;
                            j = j2;
                            i7 = i6;
                            i6 = i;
                            i = i7;
                        } else if (i6 == 3) {
                            obj3 = PEEKED_END_ARRAY;
                            i6 = i3;
                            obj4 = obj;
                        } else if (i6 == 5 || i6 == 6) {
                            obj3 = PEEKED_NULL;
                            i6 = i3;
                            obj4 = obj;
                        } else {
                            i = i6;
                            i6 = i3;
                            obj4 = obj;
                        }
                    }
                    if (isLiteral(c)) {
                        return PEEKED_NONE;
                    }
                    if (i6 != 2) {
                    }
                    if (i6 == 2) {
                    }
                    this.peekedNumberLength = i4;
                    this.peeked = 16;
                    return PEEKED_NUMBER;
            }
            i4++;
            obj = obj4;
            i3 = i6;
            obj2 = obj3;
        }
    }

    private boolean isLiteral(char c) throws IOException {
        switch (c) {
            case PEEKED_DOUBLE_QUOTED:
            case PEEKED_UNQUOTED:
            case PEEKED_SINGLE_QUOTED_NAME:
            case PEEKED_DOUBLE_QUOTED_NAME:
            case R.styleable.AppCompatTheme_actionModeCutDrawable:
            case R.styleable.AppCompatTheme_listDividerAlertDialog:
            case R.styleable.AppCompatTheme_toolbarStyle:
            case R.styleable.AppCompatTheme_alertDialogStyle:
            case R.styleable.AppCompatTheme_alertDialogCenterButtons:
            case '{':
            case '}':
                return false;
            case R.styleable.AppCompatTheme_actionModeSelectAllDrawable:
            case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
            case R.styleable.AppCompatTheme_popupWindowStyle:
            case R.styleable.AppCompatTheme_alertDialogButtonGroupStyle:
                checkLenient();
                return false;
            default:
                return true;
        }
    }

    public String nextName() throws IOException {
        String nextUnquotedValue;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 14) {
            nextUnquotedValue = nextUnquotedValue();
        } else if (i == 12) {
            nextUnquotedValue = nextQuotedValue('\'');
        } else if (i == 13) {
            nextUnquotedValue = nextQuotedValue('\"');
        } else {
            throw new IllegalStateException(new StringBuilder("Expected a name but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = nextUnquotedValue;
        return nextUnquotedValue;
    }

    public String nextString() throws IOException {
        String nextUnquotedValue;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 10) {
            nextUnquotedValue = nextUnquotedValue();
        } else if (i == 8) {
            nextUnquotedValue = nextQuotedValue('\'');
        } else if (i == 9) {
            nextUnquotedValue = nextQuotedValue('\"');
        } else if (i == 11) {
            nextUnquotedValue = this.peekedString;
            this.peekedString = null;
        } else if (i == 15) {
            nextUnquotedValue = Long.toString(this.peekedLong);
        } else if (i == 16) {
            nextUnquotedValue = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw new IllegalStateException(new StringBuilder("Expected a string but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        return nextUnquotedValue;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException(new StringBuilder("Expected a boolean but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
    }

    public void nextNull() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException(new StringBuilder("Expected null but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public double nextDouble() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.peekedLong;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i != 11) {
            throw new IllegalStateException(new StringBuilder("Expected a double but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (this.lenient || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new MalformedJsonException(new StringBuilder("JSON forbids NaN and infinities: ").append(parseDouble).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
    }

    public long nextLong() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.peekedLong;
        }
        long parseLong;
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
            try {
                parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException(new StringBuilder("Expected a long but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            throw new NumberFormatException(new StringBuilder("Expected a long but was ").append(this.peekedString).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peekedString = null;
        this.peeked = 0;
        iArr2 = this.pathIndices;
        i3 = this.stackSize - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    private String nextQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    return stringBuilder.toString();
                } else if (c2 == '\\') {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    stringBuilder.append(readEscapeCharacter());
                    break;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = i4;
                    }
                    i3 = i4;
                }
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.pos = i3;
            if (!fillBuffer(PEEKED_BEGIN_OBJECT)) {
                break;
            }
        }
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() throws IOException {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
            if (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case PEEKED_DOUBLE_QUOTED:
                    case PEEKED_UNQUOTED:
                    case PEEKED_SINGLE_QUOTED_NAME:
                    case PEEKED_DOUBLE_QUOTED_NAME:
                    case R.styleable.AppCompatTheme_actionModeCutDrawable:
                    case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    case R.styleable.AppCompatTheme_toolbarStyle:
                    case R.styleable.AppCompatTheme_alertDialogStyle:
                    case R.styleable.AppCompatTheme_alertDialogCenterButtons:
                    case '{':
                    case '}':
                        break;
                    case R.styleable.AppCompatTheme_actionModeSelectAllDrawable:
                    case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                    case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                    case R.styleable.AppCompatTheme_popupWindowStyle:
                    case R.styleable.AppCompatTheme_alertDialogButtonGroupStyle:
                        checkLenient();
                        break;
                    default:
                        i++;
                        break;
                }
            } else if (i >= this.buffer.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.buffer, this.pos, i);
                this.pos = i + this.pos;
                if (fillBuffer(PEEKED_BEGIN_OBJECT)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (fillBuffer(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.buffer, this.pos, i);
            } else {
                stringBuilder.append(this.buffer, this.pos, i);
                str = stringBuilder.toString();
            }
            this.pos = i + this.pos;
            return str;
        }
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.pos = i3;
                    return;
                } else if (c2 == '\\') {
                    this.pos = i3;
                    readEscapeCharacter();
                    break;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = i3;
                    }
                    i = i3;
                }
            }
            this.pos = i;
            if (!fillBuffer(PEEKED_BEGIN_OBJECT)) {
                break;
            }
        }
        throw syntaxError("Unterminated string");
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int i = PEEKED_NONE;
            while (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case PEEKED_DOUBLE_QUOTED:
                    case PEEKED_UNQUOTED:
                    case PEEKED_SINGLE_QUOTED_NAME:
                    case PEEKED_DOUBLE_QUOTED_NAME:
                    case R.styleable.AppCompatTheme_actionModeCutDrawable:
                    case R.styleable.AppCompatTheme_listDividerAlertDialog:
                    case R.styleable.AppCompatTheme_toolbarStyle:
                    case R.styleable.AppCompatTheme_alertDialogStyle:
                    case R.styleable.AppCompatTheme_alertDialogCenterButtons:
                    case '{':
                    case '}':
                        break;
                    case R.styleable.AppCompatTheme_actionModeSelectAllDrawable:
                    case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                    case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                    case R.styleable.AppCompatTheme_popupWindowStyle:
                    case R.styleable.AppCompatTheme_alertDialogButtonGroupStyle:
                        checkLenient();
                        break;
                    default:
                        i++;
                        break;
                }
                this.pos = i + this.pos;
                return;
            }
            this.pos = i + this.pos;
        } while (fillBuffer(PEEKED_BEGIN_OBJECT));
    }

    public int nextInt() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 15) {
            i = (int) this.peekedLong;
            if (this.peekedLong != ((long) i)) {
                throw new NumberFormatException(new StringBuilder("Expected an int but was ").append(this.peekedLong).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (i == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i == 8 || i == 9) {
            this.peekedString = nextQuotedValue(i == 8 ? '\'' : '\"');
            try {
                i = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                iArr = this.pathIndices;
                i2 = this.stackSize - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException(new StringBuilder("Expected an int but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        i = (int) parseDouble;
        if (((double) i) != parseDouble) {
            throw new NumberFormatException(new StringBuilder("Expected an int but was ").append(this.peekedString).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).append(" path ").append(getPath()).toString());
        }
        this.peekedString = null;
        this.peeked = 0;
        iArr = this.pathIndices;
        i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        return i;
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.peeked;
            if (i2 == 0) {
                i2 = doPeek();
            }
            if (i2 == 3) {
                push(PEEKED_BEGIN_OBJECT);
                i++;
            } else if (i2 == 1) {
                push(PEEKED_BEGIN_ARRAY);
                i++;
            } else if (i2 == 4) {
                this.stackSize--;
                i--;
            } else if (i2 == 2) {
                this.stackSize--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                skipUnquotedValue();
            } else if (i2 == 8 || i2 == 12) {
                skipQuotedValue('\'');
            } else if (i2 == 9 || i2 == 13) {
                skipQuotedValue('\"');
            } else if (i2 == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        this.pathNames[this.stackSize - 1] = "null";
    }

    private void push(int i) {
        if (this.stackSize == this.stack.length) {
            Object obj = new Object[(this.stackSize * 2)];
            Object obj2 = new Object[(this.stackSize * 2)];
            Object obj3 = new Object[(this.stackSize * 2)];
            System.arraycopy(this.stack, PEEKED_NONE, obj, PEEKED_NONE, this.stackSize);
            System.arraycopy(this.pathIndices, PEEKED_NONE, obj2, PEEKED_NONE, this.stackSize);
            System.arraycopy(this.pathNames, PEEKED_NONE, obj3, PEEKED_NONE, this.stackSize);
            this.stack = obj;
            this.pathIndices = obj2;
            this.pathNames = obj3;
        }
        int[] iArr = this.stack;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        iArr[i2] = i;
    }

    private boolean fillBuffer(int i) throws IOException {
        Object obj = this.buffer;
        this.lineStart -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(obj, this.pos, obj, PEEKED_NONE, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int read = this.in.read(obj, this.limit, obj.length - this.limit);
            if (read == -1) {
                return false;
            }
            this.limit = read + this.limit;
            if (this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && obj[0] == '\ufeff') {
                this.pos++;
                this.lineStart++;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private int getLineNumber() {
        return this.lineNumber + 1;
    }

    private int getColumnNumber() {
        return (this.pos - this.lineStart) + 1;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (fillBuffer(PEEKED_BEGIN_OBJECT)) {
                    i = this.pos;
                    i2 = this.limit;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException(new StringBuilder("End of input at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = i3;
                i = i3;
            } else if (c == ' ' || c == '\r' || c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.pos = i3;
                if (i3 == i2) {
                    this.pos--;
                    boolean fillBuffer = fillBuffer(PEEKED_END_OBJECT);
                    this.pos++;
                    if (!fillBuffer) {
                        return c;
                    }
                }
                checkLenient();
                switch (cArr[this.pos]) {
                    case R.styleable.AppCompatTheme_dialogTheme:
                        this.pos++;
                        if (skipTo("*/")) {
                            i = this.pos + 2;
                            i2 = this.limit;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    case R.styleable.AppCompatTheme_spinnerDropDownItemStyle:
                        this.pos++;
                        skipToEndOfLine();
                        i = this.pos;
                        i2 = this.limit;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.pos = i3;
                checkLenient();
                skipToEndOfLine();
                i = this.pos;
                i2 = this.limit;
            } else {
                this.pos = i3;
                return c;
            }
        }
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(PEEKED_BEGIN_OBJECT)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !fillBuffer(str.length())) {
                return false;
            }
            if (this.buffer[this.pos] == '\n') {
                this.lineNumber++;
                this.lineStart = this.pos + 1;
            } else {
                int i = 0;
                while (i < str.length()) {
                    if (this.buffer[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
    }

    public String getPath() {
        StringBuilder stringBuilder = new StringBuilder("$");
        int i = this.stackSize;
        for (int i2 = PEEKED_NONE; i2 < i; i2++) {
            switch (this.stack[i2]) {
                case PEEKED_BEGIN_OBJECT:
                case PEEKED_END_OBJECT:
                    stringBuilder.append('[').append(this.pathIndices[i2]).append(']');
                    break;
                case PEEKED_BEGIN_ARRAY:
                case PEEKED_END_ARRAY:
                case PEEKED_TRUE:
                    stringBuilder.append('.');
                    if (this.pathNames[i2] != null) {
                        stringBuilder.append(this.pathNames[i2]);
                    }
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private char readEscapeCharacter() throws IOException {
        if (this.pos != this.limit || fillBuffer(PEEKED_BEGIN_OBJECT)) {
            char[] cArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            char c = cArr[i];
            switch (c) {
                case PEEKED_UNQUOTED:
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return c;
                case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                    return '\b';
                case R.styleable.AppCompatTheme_checkboxStyle:
                    return '\f';
                case R.styleable.AppCompatTheme_spinnerStyle:
                    return '\n';
                case 'r':
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.pos + 4 <= this.limit || fillBuffer(PEEKED_END_ARRAY)) {
                        int i2 = this.pos;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.buffer[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 < 'a' || c2 > 'f') {
                                if (c2 >= 'A' && c2 <= 'F') {
                                    c = (char) (c + ((c2 - 65) + 10));
                                }
                                throw new NumberFormatException(new StringBuilder("\\u").append(new String(this.buffer, this.pos, 4)).toString());
                            } else {
                                c = (char) (c + ((c2 - 97) + 10));
                            }
                        }
                        this.pos += 4;
                        return c;
                    }
                    throw syntaxError("Unterminated escape sequence");
                default:
                    return c;
            }
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private IOException syntaxError(String str) throws IOException {
        throw new MalformedJsonException(str + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        this.pos--;
        if (this.pos + NON_EXECUTE_PREFIX.length <= this.limit || fillBuffer(NON_EXECUTE_PREFIX.length)) {
            int i = PEEKED_NONE;
            while (i < NON_EXECUTE_PREFIX.length) {
                if (this.buffer[this.pos + i] == NON_EXECUTE_PREFIX[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.pos += NON_EXECUTE_PREFIX.length;
        }
    }
}
