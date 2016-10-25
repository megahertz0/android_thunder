package com.uc.addon.sdk.remote;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.BooleanCallbackArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;
import com.uc.addon.sdk.remote.protocol.NavigationExistArg;
import com.uc.addon.sdk.remote.protocol.NavigationItem;
import com.uc.addon.sdk.remote.protocol.SimpleArg;
import com.xunlei.tdlive.R;

public class NavigationImpl extends RequestSender implements Navigation {
    private boolean a;

    class AnonymousClass_1 extends Stub {
        private /* synthetic */ Object a;

        AnonymousClass_1(Object obj) {
            this.a = obj;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            synchronized (this.a) {
                if (bundle != null) {
                    BooleanCallbackArg booleanCallbackArg = new BooleanCallbackArg();
                    booleanCallbackArg.fromBundle(bundle);
                    NavigationImpl.this.a = booleanCallbackArg.result;
                }
                this.a.notify();
            }
        }
    }

    public NavigationImpl(IApp iApp) {
        super(iApp);
    }

    public void addItem(NavigationItem navigationItem) {
        if (navigationItem == null || navigationItem.icon == null) {
            DebugUtil.error("addItem NavigationItem invalid");
            return;
        }
        Bitmap bitmap = navigationItem.icon;
        if (!(bitmap.getWidth() == 90 && bitmap.getHeight() == 90)) {
            navigationItem.icon = Bitmap.createScaledBitmap(bitmap, R.styleable.AppCompatTheme_controlBackground, R.styleable.AppCompatTheme_controlBackground, false);
        }
        BaseArg simpleArg = new SimpleArg();
        simpleArg.value = navigationItem;
        a(CommandConstant.COMMAND_NAVIGATION_ITEM_ADD, simpleArg, null);
    }

    public boolean existItem(String str, String str2) {
        boolean z = false;
        BaseArg navigationExistArg = new NavigationExistArg();
        navigationExistArg.title = str;
        navigationExistArg.url = str2;
        this.a = false;
        Object obj = new Object();
        IValueCallback anonymousClass_1 = new AnonymousClass_1(obj);
        synchronized (obj) {
            if (a(CommandConstant.COMMAND_NAVIGATION_ITEM_EXIST, navigationExistArg, anonymousClass_1) == RequestSender.RESULT_OK) {
                z = true;
            }
            if (z) {
                try {
                    obj.wait(TabsImpl.SYNC_TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.a;
    }
}
