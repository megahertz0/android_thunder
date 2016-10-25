package com.nostra13.universalimageloader.core.assist;

public enum ImageScaleType {
    NONE,
    NONE_SAFE,
    IN_SAMPLE_POWER_OF_2,
    IN_SAMPLE_INT,
    EXACTLY,
    EXACTLY_STRETCHED;

    static {
        NONE = new ImageScaleType("NONE", 0);
        NONE_SAFE = new ImageScaleType("NONE_SAFE", 1);
        IN_SAMPLE_POWER_OF_2 = new ImageScaleType("IN_SAMPLE_POWER_OF_2", 2);
        IN_SAMPLE_INT = new ImageScaleType("IN_SAMPLE_INT", 3);
        EXACTLY = new ImageScaleType("EXACTLY", 4);
        EXACTLY_STRETCHED = new ImageScaleType("EXACTLY_STRETCHED", 5);
        a = new ImageScaleType[]{NONE, NONE_SAFE, IN_SAMPLE_POWER_OF_2, IN_SAMPLE_INT, EXACTLY, EXACTLY_STRETCHED};
    }
}
