package com.baidu.mobads.openad.interfaces.event;

import java.util.Map;

public interface IOAdEvent {
    int getCode();

    Map<String, Object> getData();

    String getMessage();

    Object getTarget();

    String getType();

    void setTarget(Object obj);
}
