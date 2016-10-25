package com.xunlei.xiazaibao.sdk.synctasks;

import com.xunlei.xiazaibao.sdk.base.SyncHttpTask;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetCardTitle.CardTitleItem;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBGetCardTitle extends SyncHttpTask {
    private static final String TAG;

    public static class CardTitle {
        public List<CardTitleItem> xzb_titles;
    }

    public static class CardTitleItem {
        public String main_title;
        public String sub_title;
        public int type;
    }

    static {
        TAG = XZBGetCardTitle.class.getSimpleName();
    }

    public String getUrl() {
        return "http://xzb.xunlei.com/json/xzb_card_title.json";
    }

    public Header[] getHeader() {
        return new Header[0];
    }

    public static CardTitle parseJson(String str) {
        XZBLog.d("XZBDeviceManager", new StringBuilder("parseJson = ").append(str).toString());
        CardTitle cardTitle = new CardTitle();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("xzb_titles");
            if (optJSONArray != null) {
                cardTitle.xzb_titles = new ArrayList(optJSONArray.length());
                for (int i = 0; i < optJSONArray.length(); i++) {
                    CardTitleItem cardTitleItem = new CardTitleItem();
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    cardTitleItem.type = jSONObject.optInt(AgooConstants.MESSAGE_TYPE);
                    cardTitleItem.main_title = jSONObject.optString("main_title");
                    cardTitleItem.sub_title = jSONObject.optString("sub_title");
                    cardTitle.xzb_titles.add(cardTitleItem);
                }
            }
        } catch (JSONException e) {
        }
        return cardTitle;
    }
}
