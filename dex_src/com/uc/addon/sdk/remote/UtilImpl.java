package com.uc.addon.sdk.remote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.BannerBuilderRemote;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.DialogBuilder;
import com.uc.addon.sdk.remote.protocol.ExecuteCommandArg;
import com.uc.addon.sdk.remote.protocol.GetFloatViewStatuArg;
import com.uc.addon.sdk.remote.protocol.GetIconArg;
import com.uc.addon.sdk.remote.protocol.GetIconCallbackArg;
import com.uc.addon.sdk.remote.protocol.GetLanguageCallbackArg;
import com.uc.addon.sdk.remote.protocol.GetSelectionTextCallbackArg;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;
import com.uc.addon.sdk.remote.protocol.RemoteFloatView;
import com.uc.addon.sdk.remote.protocol.SaveCurrentPageArg;
import com.uc.addon.sdk.remote.protocol.ScreenshotCallbackArg;
import com.uc.addon.sdk.remote.protocol.SetDialogStateArg;
import com.uc.addon.sdk.remote.protocol.ShareArg;
import com.uc.addon.sdk.remote.protocol.ShowBannerArg;
import com.uc.addon.sdk.remote.protocol.ShowDialogArg;
import com.uc.addon.sdk.remote.protocol.ShowFloatViewArg;
import com.uc.addon.sdk.remote.protocol.ShowToastArg;
import com.uc.addon.sdk.remote.protocol.SimpleArg;
import com.uc.addon.sdk.remote.protocol.ToastBuilder;

public class UtilImpl extends RequestSender implements Util {
    private Handler a;
    private Object b;

    class AnonymousClass_1 extends Stub {
        final /* synthetic */ ValueCallback a;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ String a;

            AnonymousClass_1(String str) {
                this.a = str;
            }

            public void run() {
                AnonymousClass_1.this.a.onReceiveValue(this.a);
            }
        }

        AnonymousClass_1(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            ScreenshotCallbackArg screenshotCallbackArg = new ScreenshotCallbackArg();
            screenshotCallbackArg.fromBundle(bundle);
            if (screenshotCallbackArg.checkArgs()) {
                UtilImpl.this.a.post(new AnonymousClass_1(screenshotCallbackArg.path));
            }
        }
    }

    class AnonymousClass_2 extends Stub {
        private /* synthetic */ ValueCallback a;

        AnonymousClass_2(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            GetSelectionTextCallbackArg getSelectionTextCallbackArg = new GetSelectionTextCallbackArg();
            getSelectionTextCallbackArg.fromBundle(bundle);
            if (getSelectionTextCallbackArg.checkArgs()) {
                this.a.onReceiveValue(getSelectionTextCallbackArg.selectionText);
            }
        }
    }

    class AnonymousClass_3 extends Stub {
        private /* synthetic */ Object a;

        AnonymousClass_3(Object obj) {
            this.a = obj;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            synchronized (this.a) {
                if (bundle != null) {
                    GetFloatViewStatuArg getFloatViewStatuArg = new GetFloatViewStatuArg();
                    getFloatViewStatuArg.fromBundle(bundle);
                    UtilImpl.this = Boolean.valueOf(getFloatViewStatuArg.isShowing);
                }
                this.a.notify();
            }
        }
    }

    class AnonymousClass_4 extends Stub {
        private /* synthetic */ Object a;

        AnonymousClass_4(Object obj) {
            this.a = obj;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            synchronized (this.a) {
                if (bundle != null) {
                    GetLanguageCallbackArg getLanguageCallbackArg = new GetLanguageCallbackArg();
                    getLanguageCallbackArg.fromBundle(bundle);
                    UtilImpl.this = getLanguageCallbackArg.language;
                }
                this.a.notify();
            }
        }
    }

    class AnonymousClass_5 extends Stub {
        private /* synthetic */ Object a;

        AnonymousClass_5(Object obj) {
            this.a = obj;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            synchronized (this.a) {
                if (bundle != null) {
                    GetIconCallbackArg getIconCallbackArg = new GetIconCallbackArg();
                    getIconCallbackArg.fromBundle(bundle);
                    UtilImpl.this = getIconCallbackArg.mIcon;
                }
                this.a.notify();
            }
        }
    }

    class AnonymousClass_6 extends Stub {
        private /* synthetic */ ValueCallback a;

        AnonymousClass_6(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            SimpleArg simpleArg = new SimpleArg();
            simpleArg.fromBundle(bundle);
            this.a.onReceiveValue((String) simpleArg.value);
        }
    }

    public UtilImpl(IApp iApp, Handler handler) {
        super(iApp);
        this.a = handler;
    }

    public void dismissDialog(int i) throws RemoteException {
        BaseArg setDialogStateArg = new SetDialogStateArg();
        setDialogStateArg.mDialogId = i;
        setDialogStateArg.mDialogState = 3;
        if (a(CommandConstant.COMMAND_SET_DIALOG_STATE, setDialogStateArg, null) == RequestSender.RESULT_EXCEPTION) {
            throw new RemoteException();
        }
    }

    public void dismissFloatView(RemoteFloatView remoteFloatView) {
        BaseArg showFloatViewArg = new ShowFloatViewArg();
        showFloatViewArg.remoteView = remoteFloatView;
        a(CommandConstant.COMMAND_DIMISS_FLOATVIEW, showFloatViewArg, null);
    }

    public void executeCommand(int i, String str) {
        BaseArg executeCommandArg = new ExecuteCommandArg();
        executeCommandArg.mCommand = i;
        executeCommandArg.mParams = str;
        a(CommandConstant.COMMAND_EXECUTE, executeCommandArg, null);
    }

    public synchronized String getBrowserLanguage() {
        try {
            this.b = null;
            Object obj = new Object();
            IValueCallback anonymousClass_4 = new AnonymousClass_4(obj);
            synchronized (obj) {
                if (a(CommandConstant.COMMAND_GET_LANGUAGE, null, anonymousClass_4) == RequestSender.RESULT_OK) {
                    int i = 1;
                } else {
                    Object obj2 = null;
                }
                if (obj2 == 1) {
                    try {
                        obj.wait(TabsImpl.SYNC_TIME_OUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return (String) this.b;
    }

    public void getBrowserScreenShot(ValueCallback valueCallback) {
        a(CommandConstant.COMMAND_GET_BROWSER_SCREENSHOT, null, valueCallback != null ? new AnonymousClass_1(valueCallback) : null);
    }

    public synchronized Bitmap getFavicon(String str) {
        Bitmap bitmap = null;
        synchronized (this) {
            if (str != null) {
                try {
                    if (str.trim().length() != 0) {
                        this.b = null;
                        Object obj = new Object();
                        IValueCallback anonymousClass_5 = new AnonymousClass_5(obj);
                        synchronized (obj) {
                            Object obj2;
                            BaseArg getIconArg = new GetIconArg();
                            getIconArg.mUrl = str;
                            if (a(CommandConstant.COMMAND_GET_FAVICON, getIconArg, anonymousClass_5) == RequestSender.RESULT_OK) {
                                int i = 1;
                            } else {
                                obj2 = null;
                            }
                            if (obj2 == 1) {
                                try {
                                    obj.wait(TabsImpl.SYNC_TIME_OUT);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        bitmap = (Bitmap) this.b;
                    }
                } catch (Throwable th) {
                }
            }
        }
        return bitmap;
    }

    public void getSelectionText(ValueCallback valueCallback) {
        if (valueCallback != null) {
            a(CommandConstant.COMMAND_GET_SELECTION_TEXT, null, new AnonymousClass_2(valueCallback));
        }
    }

    public boolean isBrowserAlive() {
        return a(CommandConstant.COMMAND_PING, null, null) == RequestSender.RESULT_OK;
    }

    public synchronized boolean isFloatViewShowing(RemoteFloatView remoteFloatView) {
        boolean z = false;
        synchronized (this) {
            try {
                BaseArg showFloatViewArg = new ShowFloatViewArg();
                showFloatViewArg.remoteView = remoteFloatView;
                Object obj = new Object();
                IValueCallback anonymousClass_3 = new AnonymousClass_3(obj);
                synchronized (obj) {
                    if (a(CommandConstant.COMMAND_GET_FLOATVIEW_STATUS, showFloatViewArg, anonymousClass_3) == RequestSender.RESULT_OK) {
                        int i = 1;
                    } else {
                        boolean z2 = false;
                    }
                    if (z2) {
                        try {
                            obj.wait(TabsImpl.SYNC_TIME_OUT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (this.b != null && (this.b instanceof Boolean)) {
                    z = ((Boolean) this.b).booleanValue();
                }
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public void saveCurrentPage(String str, int i, ValueCallback valueCallback) {
        IValueCallback iValueCallback = null;
        if (valueCallback != null) {
            iValueCallback = new AnonymousClass_6(valueCallback);
        }
        BaseArg saveCurrentPageArg = new SaveCurrentPageArg();
        saveCurrentPageArg.savePath = str;
        saveCurrentPageArg.saveType = i;
        a(CommandConstant.COMMAND_SAVE_CURRENT_PAGE, saveCurrentPageArg, iValueCallback);
    }

    public void share(Intent intent) {
        BaseArg shareArg = new ShareArg();
        shareArg.intent = intent;
        a(CommandConstant.COMMAND_SHARE, shareArg, null);
    }

    public void showBanner(BannerBuilderRemote bannerBuilderRemote) throws RemoteException {
        if (bannerBuilderRemote != null) {
            bannerBuilderRemote.setHandler(this.a);
            BaseArg showBannerArg = new ShowBannerArg();
            showBannerArg.bannerBuilder = bannerBuilderRemote;
            if (a(CommandConstant.COMMAND_SHOW_BANNER, showBannerArg, null) == RequestSender.RESULT_EXCEPTION) {
                throw new RemoteException();
            }
        }
    }

    public void showDialog(DialogBuilder dialogBuilder) throws RemoteException {
        if (dialogBuilder != null) {
            dialogBuilder.setHandler(this.a);
            BaseArg showDialogArg = new ShowDialogArg();
            showDialogArg.dialogBuilder = dialogBuilder;
            if (a(CommandConstant.COMMAND_SHOW_DIALOG, showDialogArg, null) == RequestSender.RESULT_EXCEPTION) {
                throw new RemoteException();
            }
        }
    }

    public void showFloatView(RemoteFloatView remoteFloatView) throws RemoteException {
        BaseArg showFloatViewArg = new ShowFloatViewArg();
        showFloatViewArg.remoteView = remoteFloatView;
        if (a(CommandConstant.COMMAND_SHOW_FLOATVIEW, showFloatViewArg, null) == RequestSender.RESULT_EXCEPTION) {
            throw new RemoteException();
        }
    }

    public void showToast(ToastBuilder toastBuilder) {
        if (toastBuilder != null) {
            BaseArg showToastArg = new ShowToastArg();
            showToastArg.toastBuilder = toastBuilder;
            a(CommandConstant.COMMAND_SHOW_TOAST, showToastArg, null);
        }
    }

    public void startActivity(Intent intent) {
        if (intent == null) {
            DebugUtil.error("startActivity intent can't be null");
            return;
        }
        BaseArg simpleArg = new SimpleArg();
        simpleArg.value = intent;
        a(CommandConstant.COMMAND_START_ACTIVITY, simpleArg, null);
    }

    public boolean updateFloatView(RemoteFloatView remoteFloatView) {
        BaseArg showFloatViewArg = new ShowFloatViewArg();
        showFloatViewArg.remoteView = remoteFloatView;
        return a(CommandConstant.COMMAND_UPDATE_FLOATVIEW, showFloatViewArg, null) != RequestSender.RESULT_EXCEPTION;
    }
}
