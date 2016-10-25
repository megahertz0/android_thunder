package com.uc.addon.sdk.remote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.BannerBuilderRemote;
import com.uc.addon.sdk.remote.protocol.DialogBuilder;
import com.uc.addon.sdk.remote.protocol.RemoteFloatView;
import com.uc.addon.sdk.remote.protocol.ToastBuilder;

public interface Util {
    public static final byte MAX_ICON_SIZE = (byte) 32;

    void dismissDialog(int i) throws RemoteException;

    void dismissFloatView(RemoteFloatView remoteFloatView);

    void executeCommand(int i, String str);

    String getBrowserLanguage();

    void getBrowserScreenShot(ValueCallback valueCallback);

    Bitmap getFavicon(String str);

    void getSelectionText(ValueCallback valueCallback);

    boolean isBrowserAlive();

    boolean isFloatViewShowing(RemoteFloatView remoteFloatView);

    void saveCurrentPage(String str, int i, ValueCallback valueCallback);

    void share(Intent intent);

    void showBanner(BannerBuilderRemote bannerBuilderRemote) throws RemoteException;

    void showDialog(DialogBuilder dialogBuilder) throws RemoteException;

    void showFloatView(RemoteFloatView remoteFloatView) throws RemoteException;

    void showToast(ToastBuilder toastBuilder);

    void startActivity(Intent intent);

    boolean updateFloatView(RemoteFloatView remoteFloatView);
}
