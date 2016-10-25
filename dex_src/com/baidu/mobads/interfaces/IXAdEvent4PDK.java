package com.baidu.mobads.interfaces;

import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

public interface IXAdEvent4PDK extends IOAdEvent {
    IXAdProd getAdSlot();
}
