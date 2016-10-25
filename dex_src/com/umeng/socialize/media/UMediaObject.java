package com.umeng.socialize.media;

import java.util.Map;

public interface UMediaObject {
    MediaType getMediaType();

    boolean isMultiMedia();

    boolean isUrlMedia();

    byte[] toByte();

    String toUrl();

    Map<String, Object> toUrlExtraParams();
}
