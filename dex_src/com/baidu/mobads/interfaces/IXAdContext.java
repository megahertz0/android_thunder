package com.baidu.mobads.interfaces;

import android.app.Activity;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

public interface IXAdContext {
    void addEventListener(String str, IOAdEventListener iOAdEventListener);

    void dispatchEvent(IOAdEvent iOAdEvent);

    void dispose();

    Activity getActivity();

    Object getParameter(String str);

    IXAdProd getSlotById(String str);

    IXAdManager getXAdManager();

    IXLinearAdSlot newPrerollAdSlot(String str, int i, int i2);

    void notifyVisitorAction(VisitorAction visitorAction);

    void removeEventListener(String str, IOAdEventListener iOAdEventListener);

    void setActivity(Activity activity);

    void setActivityState(ActivityState activityState);

    void setAdCreativeLoadingTimeout(int i);

    void setAdServerRequestingTimeout(int i);

    void setContentVideoPlayheadTime(double d);

    void setContentVideoScreenMode(ScreenSizeMode screenSizeMode);

    void setContentVideoState(VideoState videoState);

    void setParameter(String str, Object obj);

    void setVideoDisplayBase(RelativeLayout relativeLayout);

    void setVideoDisplayBaseHeight(int i);

    void setVideoDisplayBaseWidth(int i);

    void submitRequest();
}
