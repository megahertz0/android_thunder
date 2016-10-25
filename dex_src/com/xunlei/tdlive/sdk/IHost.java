package com.xunlei.tdlive.sdk;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

public interface IHost {
    public static final int CLIENT_NOFITY_BASE = 2000;
    public static final int CLIENT_NOFITY_INIT = 2000;
    public static final int CLIENT_NOFITY_NO_NETWORK_ERROR = 2002;
    public static final int CLIENT_NOFITY_REFRESH_LIST_END = 2001;
    public static final int ERROR_VIEW_TYPE_EMPTY = 0;
    public static final int ERROR_VIEW_TYPE_GONE = 1;
    public static final int ERROR_VIEW_TYPE_INVALID_NETWORK = 2;
    public static final int ERROR_VIEW_TYPE_OTHER = -1;
    public static final int HOST_NOFITY_BASE = 1000;
    public static final int HOST_NOFITY_PAGE_DESELECTED = 1002;
    public static final int HOST_NOFITY_PAGE_SELECTED = 1001;
    public static final int HOST_NOFITY_REFRESH_LIST = 1000;

    void backToXLiveList(Context context);

    void download(Context context, String str);

    String getHubbleGUID();

    String getPeerId();

    void init(Context context, IClient iClient);

    void limitSpeed(Context context, int i);

    void login(Context context);

    View newErrorView(Context context, OnClickListener onClickListener);

    void pay(Context context, int i, int i2, String str, int i3, String str2);

    void setErrorViewType(Context context, View view, int i);

    void share(Context context, SHARE_MEDIA share_media, String str, String str2, String str3, String str4);

    boolean showXLLiveTab(Context context);

    void silentLogin(Context context);

    void startMainTab(Context context);

    void traceEvent(Context context, String str, String str2, String str3, Map<String, String> map);
}
