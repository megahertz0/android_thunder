package com.umeng.socialize.media;

abstract class UMImage$ConfiguredConvertor implements UMImage$IImageConvertor {
    public UMImage$ConvertConfig config;

    UMImage$ConfiguredConvertor() {
        this.config = null;
    }

    public void setConfig(UMImage$ConvertConfig uMImage$ConvertConfig) {
        this.config = uMImage$ConvertConfig;
    }
}
