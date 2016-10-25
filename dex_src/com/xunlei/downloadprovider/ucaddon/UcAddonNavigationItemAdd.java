package com.xunlei.downloadprovider.ucaddon;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import com.uc.addon.sdk.remote.AbstractExtension;
import com.uc.addon.sdk.remote.Browser;
import com.uc.addon.sdk.remote.protocol.NavigationItem;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;

public class UcAddonNavigationItemAdd extends AbstractExtension {
    public void onInvoke() {
        addNavigation();
        startToThunder();
    }

    private void addNavigation() {
        String str = "\u8fc5\u96f7\u5927\u7247";
        String str2 = "http://m.sjzhushou.com/v2/detail/detail_uc_trans.html";
        Browser browser = getBrowser();
        if (!browser.navigation.existItem(str, str2)) {
            NavigationItem navigationItem = new NavigationItem();
            navigationItem.icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), 16908294);
            navigationItem.icon = ((BitmapDrawable) getApplicationContext().getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
            navigationItem.url = str2;
            navigationItem.title = str;
            browser.navigation.addItem(navigationItem);
        }
    }

    private void startToThunder() {
        try {
            DownloadCenterActivity.a(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
