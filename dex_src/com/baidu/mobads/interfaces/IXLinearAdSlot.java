package com.baidu.mobads.interfaces;

import android.app.Activity;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

public interface IXLinearAdSlot extends IXAdProd {
    void addEventListener(String str, IOAdEventListener iOAdEventListener);

    void dispatchEvent(IOAdEvent iOAdEvent);

    void dispose();

    Activity getActivity();

    Object getParameter(String str);

    void notifyVisitorAction(VisitorAction visitorAction);

    void removeEventListener(String str, IOAdEventListener iOAdEventListener);

    void setActivityState(ActivityState activityState);

    void setContentVideoAssetCurrentTimePosition(double d);

    void setMaxAdNum(int i);

    void setMaxDuration(int i);

    void setParameter(String str, Object obj);

    void setVideoDisplayBase(RelativeLayout relativeLayout);

    void setVideoState(VideoState videoState);
}
