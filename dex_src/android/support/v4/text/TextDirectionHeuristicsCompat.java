package android.support.v4.text;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    private static interface TextDirectionAlgorithm {
        int checkRtl(CharSequence charSequence, int i, int i2);
    }

    private static class AnyStrong implements TextDirectionAlgorithm {
        public static final AnyStrong INSTANCE_LTR;
        public static final AnyStrong INSTANCE_RTL;
        private final boolean mLookForRtl;

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3;
            int i4 = i + i2;
            Object obj = null;
            while (i < i4) {
                switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charSequence.charAt(i)))) {
                    case STATE_TRUE:
                        if (this.mLookForRtl) {
                            return 0;
                        }
                        i3 = 1;
                        break;
                    case STATE_FALSE:
                        if (!this.mLookForRtl) {
                            return STATE_FALSE;
                        }
                        i3 = 1;
                        break;
                    default:
                        break;
                }
                i++;
            }
            if (i3 != 0) {
                return !this.mLookForRtl ? 0 : STATE_FALSE;
            } else {
                return STATE_UNKNOWN;
            }
        }

        private AnyStrong(boolean z) {
            this.mLookForRtl = z;
        }

        static {
            INSTANCE_RTL = new AnyStrong(true);
            INSTANCE_LTR = new AnyStrong(false);
        }
    }

    private static class FirstStrong implements TextDirectionAlgorithm {
        public static final FirstStrong INSTANCE;

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i + i2;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }

        private FirstStrong() {
        }

        static {
            INSTANCE = new FirstStrong();
        }
    }

    private static abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        protected abstract boolean defaultIsRtl();

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionAlgorithm) {
            this.mAlgorithm = textDirectionAlgorithm;
        }

        public boolean isRtl(char[] cArr, int i, int i2) {
            return isRtl(CharBuffer.wrap(cArr), i, i2);
        }

        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            if (charSequence != null && i >= 0 && i2 >= 0 && charSequence.length() - i2 >= i) {
                return this.mAlgorithm == null ? defaultIsRtl() : doCheck(charSequence, i, i2);
            } else {
                throw new IllegalArgumentException();
            }
        }

        private boolean doCheck(CharSequence charSequence, int i, int i2) {
            switch (this.mAlgorithm.checkRtl(charSequence, i, i2)) {
                case STATE_TRUE:
                    return true;
                case STATE_FALSE:
                    return false;
                default:
                    return defaultIsRtl();
            }
        }
    }

    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        private TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionAlgorithm, boolean z) {
            super(textDirectionAlgorithm);
            this.mDefaultIsRtl = z;
        }

        protected boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        public static final TextDirectionHeuristicLocale INSTANCE;

        public TextDirectionHeuristicLocale() {
            super(null);
        }

        protected boolean defaultIsRtl() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }

        static {
            INSTANCE = new TextDirectionHeuristicLocale();
        }
    }

    static {
        LTR = new TextDirectionHeuristicInternal(false, null);
        RTL = new TextDirectionHeuristicInternal(true, null);
        FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(false, null);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(true, null);
        ANYRTL_LTR = new TextDirectionHeuristicInternal(false, null);
        LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    }

    private static int isRtlText(int i) {
        switch (i) {
            case STATE_TRUE:
                return STATE_FALSE;
            case STATE_FALSE:
            case STATE_UNKNOWN:
                return STATE_TRUE;
            default:
                return STATE_UNKNOWN;
        }
    }

    private static int isRtlTextOrFormat(int i) {
        switch (i) {
            case STATE_TRUE:
            case XZBDevice.Predownload:
            case XZBDevice.Delete:
                return STATE_FALSE;
            case STATE_FALSE:
            case STATE_UNKNOWN:
            case R.styleable.Toolbar_titleMarginBottom:
            case R.styleable.Toolbar_maxButtonHeight:
                return STATE_TRUE;
            default:
                return STATE_UNKNOWN;
        }
    }

    private TextDirectionHeuristicsCompat() {
    }
}
