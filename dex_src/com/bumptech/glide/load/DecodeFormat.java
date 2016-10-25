package com.bumptech.glide.load;

public enum DecodeFormat {
    ALWAYS_ARGB_8888,
    PREFER_ARGB_8888,
    PREFER_RGB_565;
    public static final DecodeFormat DEFAULT;

    static {
        ALWAYS_ARGB_8888 = new DecodeFormat("ALWAYS_ARGB_8888", 0);
        PREFER_ARGB_8888 = new DecodeFormat("PREFER_ARGB_8888", 1);
        PREFER_RGB_565 = new DecodeFormat("PREFER_RGB_565", 2);
        a = new DecodeFormat[]{ALWAYS_ARGB_8888, PREFER_ARGB_8888, PREFER_RGB_565};
        DEFAULT = PREFER_RGB_565;
    }
}
