package com.google.zxing.common;

import com.google.zxing.e;
import com.sina.weibo.sdk.component.GameManager;
import java.util.HashMap;
import java.util.Map;

public enum CharacterSetECI {
    Cp437(new int[]{0, 2}, new String[0]),
    ISO8859_1(new int[]{1, 3}, new String[1]),
    ISO8859_2(4, new String[1]),
    ISO8859_3(5, new String[1]),
    ISO8859_4(6, new String[1]),
    ISO8859_5(7, new String[1]),
    ISO8859_6(8, new String[1]),
    ISO8859_7(9, new String[1]),
    ISO8859_8(10, new String[1]),
    ISO8859_9(11, new String[1]),
    ISO8859_10(12, new String[1]),
    ISO8859_11(13, new String[1]),
    ISO8859_13(15, new String[1]),
    ISO8859_14(16, new String[1]),
    ISO8859_15(17, new String[1]),
    ISO8859_16(18, new String[1]),
    SJIS(20, new String[1]),
    Cp1250(21, new String[1]),
    Cp1251(22, new String[1]),
    Cp1252(23, new String[1]),
    Cp1256(24, new String[1]),
    UnicodeBigUnmarked(25, new String[2]),
    UTF8(26, new String[1]),
    ASCII(new int[]{27, 170}, new String[1]),
    GB18030(29, new String[3]),
    EUC_KR(30, new String[1]);
    private static final Map<Integer, CharacterSetECI> a;
    private static final Map<String, CharacterSetECI> b;
    private final int[] c;
    private final String[] d;

    static {
        int[] iArr = new int[]{0, 2};
        String[] strArr = new String[0];
        Cp437 = new CharacterSetECI("Cp437", 0, new int[]{0, 2}, new String[0]);
        iArr = new int[]{1, 3};
        strArr = new String[]{"ISO-8859-1"};
        ISO8859_1 = new CharacterSetECI("ISO8859_1", 1, new int[]{1, 3}, new String[1]);
        String[] strArr2 = new String[]{"ISO-8859-2"};
        ISO8859_2 = new CharacterSetECI("ISO8859_2", 2, 4, new String[1]);
        strArr = new String[]{"ISO-8859-3"};
        ISO8859_3 = new CharacterSetECI("ISO8859_3", 3, 5, new String[1]);
        strArr = new String[]{"ISO-8859-4"};
        ISO8859_4 = new CharacterSetECI("ISO8859_4", 4, 6, new String[1]);
        String[] strArr3 = new String[]{"ISO-8859-5"};
        ISO8859_5 = new CharacterSetECI("ISO8859_5", 5, 7, new String[1]);
        strArr3 = new String[]{"ISO-8859-6"};
        ISO8859_6 = new CharacterSetECI("ISO8859_6", 6, 8, new String[1]);
        strArr3 = new String[]{"ISO-8859-7"};
        ISO8859_7 = new CharacterSetECI("ISO8859_7", 7, 9, new String[1]);
        strArr3 = new String[]{"ISO-8859-8"};
        ISO8859_8 = new CharacterSetECI("ISO8859_8", 8, 10, new String[1]);
        strArr3 = new String[]{"ISO-8859-9"};
        ISO8859_9 = new CharacterSetECI("ISO8859_9", 9, 11, new String[1]);
        strArr3 = new String[]{"ISO-8859-10"};
        ISO8859_10 = new CharacterSetECI("ISO8859_10", 10, 12, new String[1]);
        strArr3 = new String[]{"ISO-8859-11"};
        ISO8859_11 = new CharacterSetECI("ISO8859_11", 11, 13, new String[1]);
        strArr3 = new String[]{"ISO-8859-13"};
        ISO8859_13 = new CharacterSetECI("ISO8859_13", 12, 15, new String[1]);
        strArr3 = new String[]{"ISO-8859-14"};
        ISO8859_14 = new CharacterSetECI("ISO8859_14", 13, 16, new String[1]);
        strArr3 = new String[]{"ISO-8859-15"};
        ISO8859_15 = new CharacterSetECI("ISO8859_15", 14, 17, new String[1]);
        strArr3 = new String[]{"ISO-8859-16"};
        ISO8859_16 = new CharacterSetECI("ISO8859_16", 15, 18, new String[1]);
        strArr3 = new String[]{"Shift_JIS"};
        SJIS = new CharacterSetECI("SJIS", 16, 20, new String[1]);
        strArr3 = new String[]{"windows-1250"};
        Cp1250 = new CharacterSetECI("Cp1250", 17, 21, new String[1]);
        strArr3 = new String[]{"windows-1251"};
        Cp1251 = new CharacterSetECI("Cp1251", 18, 22, new String[1]);
        strArr3 = new String[]{"windows-1252"};
        Cp1252 = new CharacterSetECI("Cp1252", 19, 23, new String[1]);
        strArr3 = new String[]{"windows-1256"};
        Cp1256 = new CharacterSetECI("Cp1256", 20, 24, new String[1]);
        strArr3 = new String[]{"UTF-16BE", "UnicodeBig"};
        UnicodeBigUnmarked = new CharacterSetECI("UnicodeBigUnmarked", 21, 25, new String[2]);
        strArr3 = new String[]{GameManager.DEFAULT_CHARSET};
        UTF8 = new CharacterSetECI("UTF8", 22, 26, new String[1]);
        int[] iArr2 = new int[]{27, 170};
        strArr3 = new String[]{"US-ASCII"};
        ASCII = new CharacterSetECI("ASCII", 23, new int[]{27, 170}, new String[1]);
        Big5 = new CharacterSetECI("Big5");
        strArr3 = new String[]{"GB2312", "EUC_CN", "GBK"};
        GB18030 = new CharacterSetECI("GB18030", 25, 29, new String[3]);
        strArr3 = new String[]{"EUC-KR"};
        EUC_KR = new CharacterSetECI("EUC_KR", 26, 30, new String[1]);
        e = new CharacterSetECI[]{Cp437, ISO8859_1, ISO8859_2, ISO8859_3, ISO8859_4, ISO8859_5, ISO8859_6, ISO8859_7, ISO8859_8, ISO8859_9, ISO8859_10, ISO8859_11, ISO8859_13, ISO8859_14, ISO8859_15, ISO8859_16, SJIS, Cp1250, Cp1251, Cp1252, Cp1256, UnicodeBigUnmarked, UTF8, ASCII, Big5, GB18030, EUC_KR};
        a = new HashMap();
        b = new HashMap();
        CharacterSetECI[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            int i2;
            CharacterSetECI characterSetECI = values[i];
            int[] iArr3 = characterSetECI.c;
            int length2 = iArr3.length;
            for (i2 = 0; i2 < length2; i2++) {
                a.put(Integer.valueOf(iArr3[i2]), characterSetECI);
            }
            b.put(characterSetECI.name(), characterSetECI);
            String[] strArr4 = characterSetECI.d;
            length2 = strArr4.length;
            for (i2 = 0; i2 < length2; i2++) {
                b.put(strArr4[i2], characterSetECI);
            }
        }
    }

    private CharacterSetECI(int i) {
        this((String) i, 24, new int[]{28}, new String[0]);
    }

    private CharacterSetECI(int i, String... strArr) {
        this.c = new int[]{i};
        this.d = strArr;
    }

    private CharacterSetECI(int[] iArr, String... strArr) {
        this.c = iArr;
        this.d = strArr;
    }

    public final int getValue() {
        return this.c[0];
    }

    public static CharacterSetECI getCharacterSetECIByValue(int i) throws e {
        if (i >= 0 && i < 900) {
            return (CharacterSetECI) a.get(Integer.valueOf(i));
        }
        throw e.a();
    }

    public static CharacterSetECI getCharacterSetECIByName(String str) {
        return (CharacterSetECI) b.get(str);
    }
}
