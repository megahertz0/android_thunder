package com.google.zxing.qrcode.decoder;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public enum Mode {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    private final int[] a;
    private final int b;

    static {
        int[] iArr = new int[]{0, 0, 0};
        TERMINATOR = new Mode("TERMINATOR", 0, new int[]{0, 0, 0}, 0);
        iArr = new int[]{10, 12, 14};
        NUMERIC = new Mode("NUMERIC", 1, new int[]{10, 12, 14}, 1);
        iArr = new int[]{9, 11, 13};
        ALPHANUMERIC = new Mode("ALPHANUMERIC", 2, new int[]{9, 11, 13}, 2);
        iArr = new int[]{0, 0, 0};
        STRUCTURED_APPEND = new Mode("STRUCTURED_APPEND", 3, new int[]{0, 0, 0}, 3);
        iArr = new int[]{8, 16, 16};
        BYTE = new Mode("BYTE", 4, new int[]{8, 16, 16}, 4);
        int[] iArr2 = new int[]{0, 0, 0};
        ECI = new Mode("ECI", 5, new int[]{0, 0, 0}, 7);
        iArr2 = new int[]{8, 10, 12};
        KANJI = new Mode("KANJI", 6, new int[]{8, 10, 12}, 8);
        iArr2 = new int[]{0, 0, 0};
        FNC1_FIRST_POSITION = new Mode("FNC1_FIRST_POSITION", 7, new int[]{0, 0, 0}, 5);
        iArr2 = new int[]{0, 0, 0};
        FNC1_SECOND_POSITION = new Mode("FNC1_SECOND_POSITION", 8, new int[]{0, 0, 0}, 9);
        iArr2 = new int[]{8, 10, 12};
        HANZI = new Mode("HANZI", 9, new int[]{8, 10, 12}, 13);
        c = new Mode[]{TERMINATOR, NUMERIC, ALPHANUMERIC, STRUCTURED_APPEND, BYTE, ECI, KANJI, FNC1_FIRST_POSITION, FNC1_SECOND_POSITION, HANZI};
    }

    private Mode(int[] iArr, int i) {
        this.a = iArr;
        this.b = i;
    }

    public static Mode forBits(int i) {
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return TERMINATOR;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return NUMERIC;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return ALPHANUMERIC;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return STRUCTURED_APPEND;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return BYTE;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return FNC1_FIRST_POSITION;
            case R.styleable.Toolbar_contentInsetLeft:
                return ECI;
            case XZBDevice.Wait:
                return KANJI;
            case XZBDevice.Pause:
                return FNC1_SECOND_POSITION;
            case XZBDevice.Upload:
                return HANZI;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final int getBits() {
        return this.b;
    }

    public final int getCharacterCountBits(h hVar) {
        int i = hVar.a;
        if (i <= 9) {
            i = 0;
        } else {
            i = i <= 26 ? 1 : XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        return this.a[i];
    }
}
