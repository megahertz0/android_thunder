package com.baidu.mobads.production.i;

import android.content.Context;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdInternalConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXLinearAdSlot;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.openad.interfaces.event.IOAdEventDispatcher;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.t;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class b extends a implements IXLinearAdSlot, IOAdEventDispatcher {
    private a w;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return m();
    }

    public b(Context context, String str) {
        super(context);
        setId(str);
        this.p = SlotType.SLOT_TYPE_PREROLL;
    }

    protected void d() {
        this.n = 8000;
    }

    public void c() {
        this.s.i("XPrerollAdSlot", "afterAdContainerInit()");
        dispatchEvent(new com.baidu.mobads.openad.d.b(com.baidu.mobads.openad.d.b.COMPLETE));
    }

    public void request() {
        int parseInt;
        int parseInt2;
        this.w = new a(getApplicationContext(), getActivity(), this.p, this);
        this.w.d(getId());
        HashMap parameter = getParameter();
        String str = (String) parameter.get(IXAdInternalConstants.PARAMETER_KEY_OF_BASE_WIDTH);
        String str2 = (String) parameter.get(IXAdInternalConstants.PARAMETER_KEY_OF_BASE_HEIGHT);
        if (str != null) {
            try {
                parseInt = Integer.parseInt(str);
            } catch (Exception e) {
                parseInt = 0;
            }
        } else {
            parseInt = 0;
        }
        if (str2 != null) {
            try {
                parseInt2 = Integer.parseInt(str2);
            } catch (Exception e2) {
                parseInt2 = 0;
            }
        } else {
            parseInt2 = 0;
        }
        this.w.a(parseInt);
        this.w.b(parseInt2);
        super.a(this.w);
    }

    protected void a(d dVar, t tVar, int i) {
        String str = (String) getParameter().get(IXAdInternalConstants.PARAMETER_KEY_OF_AD_REQUESTING_TIMEOUT);
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (Exception e) {
            }
        }
        tVar.a(dVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        if (iXAdContainer.getAdContainerContext().getAdInstanceInfo().getCreativeType() == CreativeType.STATIC_IMAGE || iXAdContainer.getAdContainerContext().getAdInstanceInfo().getCreativeType() == CreativeType.GIF) {
            start();
        }
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        Set hashSet = new HashSet();
        hashSet.addAll(iXAdContainer.getAdContainerContext().getAdInstanceInfo().getStartTrackers());
        a(hashSet);
    }

    private void a(Set<String> set) {
        com.baidu.mobads.openad.e.a aVar = new com.baidu.mobads.openad.e.a();
        for (String str : set) {
            d dVar = new d(str, com.umeng.a.d);
            dVar.e = 1;
            aVar.a(dVar, Boolean.valueOf(true));
        }
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.e(iXAdContainer, hashMap);
        this.m = SlotState.COMPLETED;
    }

    public com.baidu.mobads.vo.d m() {
        return this.w;
    }

    public void setVideoDisplayBase(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public void setActivityState(ActivityState activityState) {
    }

    public void setVideoState(VideoState videoState) {
    }

    public void setParameter(String str, Object obj) {
    }

    public Object getParameter(String str) {
        return null;
    }

    public void setContentVideoAssetCurrentTimePosition(double d) {
    }

    public void notifyVisitorAction(VisitorAction visitorAction) {
    }

    public void setMaxDuration(int i) {
    }

    public void setMaxAdNum(int i) {
    }

    public int getDuration() {
        return this.h == null ? super.getDuration() : (int) this.h.getDuration();
    }

    public int getPlayheadTime() {
        return this.h == null ? super.getPlayheadTime() : (int) this.h.getPlayheadTime();
    }

    public void load() {
        this.r.set(true);
        super.load();
    }

    public void start() {
        if (this.r.get()) {
            super.start();
        } else {
            load();
        }
    }

    public void stop() {
        this.s.i("XPrerollAdSlot", new StringBuilder("stop()").append(getSlotState().getValue()).toString());
        super.stop();
    }

    public void pause() {
        this.s.i("XPrerollAdSlot", new StringBuilder("pause()").append(getSlotState().getValue()).toString());
        if (getSlotState() == SlotState.PLAYING) {
            super.pause();
        }
    }

    public void resume() {
        this.s.i("XPrerollAdSlot", new StringBuilder("resume()").append(getSlotState().getValue()).toString());
        if (getSlotState() == SlotState.PAUSED) {
            super.resume();
        }
    }
}
