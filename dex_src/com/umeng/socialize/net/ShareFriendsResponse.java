package com.umeng.socialize.net;

import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.umeng.a;
import com.umeng.socialize.bean.UMFriend;
import com.umeng.socialize.bean.UMFriend.PinYin;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ShareFriendsResponse extends SocializeReseponse {
    public List<UMFriend> mFriends;

    public ShareFriendsResponse(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void parseJsonObject() {
        JSONObject jSONObject = this.mJsonData;
        if (jSONObject == null) {
            Log.e("SocializeReseponse", "data json is null....");
            return;
        }
        this.mFriends = new ArrayList();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                Object toString = keys.next().toString();
                jSONObject = (JSONObject) this.mJsonData.get(toString);
                if (jSONObject.has(SelectCountryActivity.EXTRA_COUNTRY_NAME)) {
                    String string = jSONObject.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                    if (!TextUtils.isEmpty(toString) && !TextUtils.isEmpty(string)) {
                        UMFriend uMFriend = new UMFriend();
                        uMFriend.setFid(toString);
                        uMFriend.setName(string);
                        CharSequence optString = jSONObject.optString("link_name", a.d);
                        if (!TextUtils.isEmpty(optString)) {
                            CharSequence charSequence = optString;
                        }
                        uMFriend.setLinkName(string);
                        string = jSONObject.optString("pinyin", a.d);
                        if (!TextUtils.isEmpty(string)) {
                            PinYin pinYin = new PinYin();
                            pinYin.mInitial = String.valueOf(reverse(string.charAt(0)));
                            pinYin.mTotalPinyin = string;
                            uMFriend.setPinyin(pinYin);
                        }
                        if (jSONObject.has("profile_image_url")) {
                            uMFriend.setIcon(jSONObject.getString("profile_image_url"));
                        }
                        this.mFriends.add(uMFriend);
                    }
                }
            } catch (Exception e) {
                Log.e("SocializeReseponse", "Parse friend data error", e);
            }
        }
    }

    public static char reverse(char c) {
        return (c < 'a' || c > 'z') ? c : (char) (c - 32);
    }
}
