package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.AdapterView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;

// compiled from: LiveListFragment.java
class t implements OnClickListener {
    final /* synthetic */ AdapterView a;
    final /* synthetic */ int b;
    final /* synthetic */ q c;

    t(q qVar, AdapterView adapterView, int i) {
        this.c = qVar;
        this.a = adapterView;
        this.b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        if (i == 1) {
            JsonWrapper jsonWrapper = (JsonWrapper) this.a.getAdapter().getItem(this.b);
            if (jsonWrapper != null) {
                String string;
                if (jsonWrapper.getInt(AgooConstants.MESSAGE_TYPE, 0) == 1) {
                    string = jsonWrapper.getString("action", "download");
                    CharSequence string2 = jsonWrapper.getString("action_url", BuildConfig.VERSION_NAME);
                    if (string.equals("openurl")) {
                        try {
                            DispatcherActivity.a(this.c.getActivity(), Uri.parse(string2), 0);
                        } catch (Exception e) {
                        }
                    } else {
                        String str;
                        if (TextUtils.isEmpty(string2)) {
                            string2 = e.p;
                        }
                        if (TextUtils.isEmpty(str)) {
                            str = "http://down.sandai.net/xllive/xllive_android.apk";
                        }
                        XLLiveSDK.getInstance(this.c.getActivity()).host().download(this.c.getActivity(), str);
                    }
                } else {
                    int i2 = jsonWrapper.getInt("status", 0);
                    string = jsonWrapper.getString("roomid", BuildConfig.VERSION_NAME);
                    String string3 = jsonWrapper.getString("userid", BuildConfig.VERSION_NAME);
                    String string4 = jsonWrapper.getObject("userinfo", "{}").getString("avatar", BuildConfig.VERSION_NAME);
                    String string5 = jsonWrapper.getString(WeiXinShareContent.TYPE_IMAGE, string4);
                    String string6 = jsonWrapper.getString("onlinenum", "0");
                    String string7 = jsonWrapper.getString("stream_pull", BuildConfig.VERSION_NAME);
                    int i3 = jsonWrapper.getObject("seq2", "{}").getInt("hot_level", 0);
                    int i4 = jsonWrapper.getObject("seq2", "{}").getInt("is_follow", 0);
                    if (i2 == 1 || i2 == 3) {
                        LivePlayerActivity.a(this.c.getActivity(), string, string3, string7, string4, string5, string6, i3, i4, "label");
                    } else if (i2 == 2) {
                        LiveReplayActivity.a(this.c.getActivity(), string, string3, jsonWrapper.getString("play_hls_url", BuildConfig.VERSION_NAME), string4, string5, string6, i3, i4, "label");
                    }
                }
                q.e("home_label_click").a(WeiXinShareContent.TYPE_VIDEO).a("viewid", q.e("zb_content_read").d("viewid")).a("roomid", jsonWrapper.getString("roomid", BuildConfig.VERSION_NAME)).a("hostid", jsonWrapper.getString("userid", BuildConfig.VERSION_NAME)).a("viewernum", jsonWrapper.getInt("onlinenum", 0)).a("rn", jsonWrapper.getInt("position", 0)).a("grayid", q.d(this.c).a()).a("hosttype", jsonWrapper.getObject("seq2", "{}").getInt("hot_level", 0)).a("follow", jsonWrapper.getObject("seq2", "{}").getInt("is_follow", 0)).a("recommend", jsonWrapper.getObject("seq2", "{}").getInt("is_recommend", 0)).a("sign", jsonWrapper.getObject("seq2", "{}").getInt("is_sign", 0)).a("isdl", jsonWrapper.getInt(AgooConstants.MESSAGE_TYPE, 0)).a("livestat", jsonWrapper.getInt("status", 0) == 2 ? "replay" : "live").b(new String[0]);
            }
        }
    }
}
