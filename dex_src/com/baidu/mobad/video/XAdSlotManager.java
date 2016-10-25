package com.baidu.mobad.video;

import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdProd;
import com.baidu.mobads.interfaces.IXLinearAdSlot;
import java.util.ArrayList;
import java.util.HashMap;

public class XAdSlotManager {
    private HashMap<String, IXAdProd> a;

    public XAdSlotManager() {
        this.a = new HashMap();
    }

    public Boolean containsAdSlot(String str) {
        return Boolean.valueOf(this.a.containsKey(str));
    }

    public void addAdSlot(IXAdProd iXAdProd) {
        this.a.put(iXAdProd.getId(), iXAdProd);
    }

    public IXAdProd retrieveAdSlotById(String str) {
        return (IXAdProd) this.a.get(str);
    }

    public IXLinearAdSlot retrievePrerollAdSlot() {
        return (IXLinearAdSlot) retrieve1stAdSlotByType(SlotType.SLOT_TYPE_PREROLL);
    }

    public IXLinearAdSlot retrievePostAdSlot() {
        return (IXLinearAdSlot) retrieve1stAdSlotByType(SlotType.SLOT_TYPE_POSTROLL);
    }

    public IXAdProd retrievePauseAdSlot() {
        return retrieve1stAdSlotByType(SlotType.SLOT_TYPE_PAUSE_ROLL);
    }

    public IXAdProd retrieve1stAdSlotByType(SlotType slotType) {
        ArrayList retrieveAdSlotsByType = retrieveAdSlotsByType(slotType);
        return retrieveAdSlotsByType.size() > 0 ? (IXAdProd) retrieveAdSlotsByType.get(0) : null;
    }

    public ArrayList<IXAdProd> retrieveAdSlotsByType(SlotType slotType) {
        ArrayList<IXAdProd> arrayList = new ArrayList();
        for (Object obj : this.a.keySet()) {
            IXAdProd iXAdProd = (IXAdProd) this.a.get(obj);
            if (iXAdProd.getType() == slotType) {
                arrayList.add(iXAdProd);
            }
        }
        return arrayList;
    }
}
