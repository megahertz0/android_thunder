package com.google.zxing;

import java.util.List;

public enum DecodeHintType {
    OTHER(Object.class),
    PURE_BARCODE(Void.class),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(Void.class),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(int[].class),
    ASSUME_CODE_39_CHECK_DIGIT(Void.class),
    ASSUME_GS1(Void.class),
    RETURN_CODABAR_START_END(Void.class),
    NEED_RESULT_POINT_CALLBACK(p.class),
    ALLOWED_EAN_EXTENSIONS(int[].class);
    private final Class<?> a;

    static {
        Class cls = Object.class;
        OTHER = new DecodeHintType("OTHER", 0, Object.class);
        cls = Void.class;
        PURE_BARCODE = new DecodeHintType("PURE_BARCODE", 1, Void.class);
        cls = List.class;
        POSSIBLE_FORMATS = new DecodeHintType("POSSIBLE_FORMATS", 2, List.class);
        cls = Void.class;
        TRY_HARDER = new DecodeHintType("TRY_HARDER", 3, Void.class);
        cls = String.class;
        CHARACTER_SET = new DecodeHintType("CHARACTER_SET", 4, String.class);
        Class cls2 = int[].class;
        ALLOWED_LENGTHS = new DecodeHintType("ALLOWED_LENGTHS", 5, int[].class);
        cls2 = Void.class;
        ASSUME_CODE_39_CHECK_DIGIT = new DecodeHintType("ASSUME_CODE_39_CHECK_DIGIT", 6, Void.class);
        cls2 = Void.class;
        ASSUME_GS1 = new DecodeHintType("ASSUME_GS1", 7, Void.class);
        cls2 = Void.class;
        RETURN_CODABAR_START_END = new DecodeHintType("RETURN_CODABAR_START_END", 8, Void.class);
        cls2 = p.class;
        NEED_RESULT_POINT_CALLBACK = new DecodeHintType("NEED_RESULT_POINT_CALLBACK", 9, p.class);
        cls2 = int[].class;
        ALLOWED_EAN_EXTENSIONS = new DecodeHintType("ALLOWED_EAN_EXTENSIONS", 10, int[].class);
        b = new DecodeHintType[]{OTHER, PURE_BARCODE, POSSIBLE_FORMATS, TRY_HARDER, CHARACTER_SET, ALLOWED_LENGTHS, ASSUME_CODE_39_CHECK_DIGIT, ASSUME_GS1, RETURN_CODABAR_START_END, NEED_RESULT_POINT_CALLBACK, ALLOWED_EAN_EXTENSIONS};
    }

    private DecodeHintType(Class<?> cls) {
        this.a = cls;
    }

    public final Class<?> getValueType() {
        return this.a;
    }
}
