package com.google.zxing.datamatrix.encoder;

public enum SymbolShapeHint {
    FORCE_NONE,
    FORCE_SQUARE,
    FORCE_RECTANGLE;

    static {
        FORCE_NONE = new SymbolShapeHint("FORCE_NONE", 0);
        FORCE_SQUARE = new SymbolShapeHint("FORCE_SQUARE", 1);
        FORCE_RECTANGLE = new SymbolShapeHint("FORCE_RECTANGLE", 2);
        a = new SymbolShapeHint[]{FORCE_NONE, FORCE_SQUARE, FORCE_RECTANGLE};
    }
}
