package com.baidu.mobads.interfaces;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import java.util.HashMap;

public interface IXAdProd {
    public static final String SLOT_DID_FINISH = "slot_did_finish";
    public static final String SLOT_DID_LOADED = "slot_did_loaded";
    public static final String SLOT_DID_START = "slot_did_start";
    public static final String SLOT_ERROR = "slot_error";

    Activity getActivity();

    IXAdContainerFactory getAdContainerFactory();

    IXAdRequestInfo getAdRequestInfo();

    IXAdResponseInfo getAdResponseInfo();

    Context getApplicationContext();

    IXAdInstanceInfo getCurrentAdInstance();

    IXAdContainer getCurrentXAdContainer();

    int getDuration();

    String getId();

    HashMap<String, String> getParameter();

    int getPlayheadTime();

    ViewGroup getProdBase();

    IXAdProdInfo getProdInfo();

    SlotState getSlotState();

    SlotType getType();

    Boolean isAdServerRequestingSuccess();

    void load();

    void pause();

    void request();

    void resize();

    void resume();

    void setActivity(Context context);

    void setAdResponseInfo(IXAdResponseInfo iXAdResponseInfo);

    void setAdSlotBase(RelativeLayout relativeLayout);

    void setId(String str);

    void setParameter(HashMap<String, String> hashMap);

    void start();

    void stop();
}
