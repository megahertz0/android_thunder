package com.mediav.ads.sdk.attibutes;

import android.text.TextUtils;
import com.mediav.ads.sdk.interfaces.IMvVideoAdAttributes;
import java.util.HashMap;
import java.util.HashSet;

public class MvVideoAdAttributes implements IMvVideoAdAttributes {
    private HashMap<String, String> map;
    private HashSet<String> tags;

    public MvVideoAdAttributes() {
        this.map = new HashMap();
        this.tags = new HashSet();
    }

    public HashMap<String, String> getAttributes() {
        this.map.put("qhtag", TextUtils.join("_", this.tags));
        return this.map;
    }

    public void setCategory(int i) {
        this.map.put("qhchannel", String.valueOf(i));
    }

    public void setTitle(String str) {
        this.map.put("qhname", str);
    }

    public void setEpisode(int i) {
        this.map.put("qhepisode", String.valueOf(i));
    }

    public void setRegion(String str) {
        this.tags.add(str);
    }

    public void setCast(HashSet<String> hashSet) {
        this.tags.addAll(hashSet);
    }

    public void setYear(int i) {
        this.tags.add(String.valueOf(i));
    }

    public void setSource(String str) {
        this.map.put("qhsource", str);
    }
}
