package com.uc.addon.sdk.remote;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.CreateTabArg;
import com.uc.addon.sdk.remote.protocol.CreateTabCallbackArg;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.GetTabPropertiesCallbackArg;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.uc.addon.sdk.remote.protocol.IValueCallback.Stub;
import com.uc.addon.sdk.remote.protocol.JSParam;
import com.uc.addon.sdk.remote.protocol.LoadJavaScriptArg;
import com.uc.addon.sdk.remote.protocol.PageUpDownArg;
import com.uc.addon.sdk.remote.protocol.SimpleArg;
import com.uc.addon.sdk.remote.protocol.StringUtil;
import com.uc.addon.sdk.remote.protocol.TabProperties;
import com.uc.addon.sdk.remote.protocol.TabSimpleArg;
import com.uc.addon.sdk.remote.protocol.TabUpdateProperties;
import com.uc.addon.sdk.remote.protocol.UpdateTabArg;

public class TabsImpl extends RequestSender implements Tabs {
    public static final long SYNC_TIME_OUT = 3000;
    private Handler a;
    private Object b;
    private Object c;
    private BrowserImpl d;

    class AnonymousClass_4 extends Stub {
        final /* synthetic */ ValueCallback a;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ SimpleArg a;

            AnonymousClass_1(SimpleArg simpleArg) {
                this.a = simpleArg;
            }

            public void run() {
                AnonymousClass_4.this.a.onReceiveValue(this.a.value);
            }
        }

        AnonymousClass_4(ValueCallback valueCallback) {
            this.a = valueCallback;
        }

        public void onReceiveValue(Bundle bundle) throws RemoteException {
            SimpleArg simpleArg = new SimpleArg();
            simpleArg.fromBundle(bundle);
            TabsImpl.this.a.post(new AnonymousClass_1(simpleArg));
        }
    }

    public TabsImpl(IApp iApp, BrowserImpl browserImpl) {
        super(iApp);
        this.a = new Handler(Looper.getMainLooper());
        this.b = new Object();
        this.d = browserImpl;
    }

    static /* synthetic */ Integer a(Bundle bundle) {
        CreateTabCallbackArg createTabCallbackArg = new CreateTabCallbackArg();
        createTabCallbackArg.fromBundle(bundle);
        if (createTabCallbackArg.checkArgs()) {
            return Integer.valueOf(createTabCallbackArg.tabId);
        }
        new StringBuilder("checking arg fail for: ").append(createTabCallbackArg);
        return Integer.valueOf(-1);
    }

    private static boolean a(String str) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        return a(trim, "http:") || a(trim, "https:") || a(trim, "file:") || a(trim, "ftp:");
    }

    private static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        int length = str2.length();
        return length <= str.length() && str2.equalsIgnoreCase(str.substring(0, length));
    }

    static /* synthetic */ TabProperties b(Bundle bundle) {
        GetTabPropertiesCallbackArg getTabPropertiesCallbackArg = new GetTabPropertiesCallbackArg();
        getTabPropertiesCallbackArg.fromBundle(bundle);
        return getTabPropertiesCallbackArg.checkArgs() ? getTabPropertiesCallbackArg.tabProperties : null;
    }

    public synchronized int create(String str, boolean z) {
        int i = -1;
        synchronized (this) {
            try {
                this.c = null;
                if (a(str)) {
                    BaseArg createTabArg = new CreateTabArg();
                    createTabArg.url = str;
                    createTabArg.isActive = z;
                    IValueCallback anonymousClass_1 = new Stub() {
                        public void onReceiveValue(Bundle bundle) throws RemoteException {
                            synchronized (TabsImpl.this.b) {
                                TabsImpl.this.c = TabsImpl.a(bundle);
                                TabsImpl.this.b.notify();
                            }
                        }
                    };
                    synchronized (this.b) {
                        Object obj;
                        if (a(CommandConstant.COMMAND_CREATE_TAB, createTabArg, anonymousClass_1) == RequestSender.RESULT_OK) {
                            int i2 = 1;
                        } else {
                            obj = null;
                        }
                        if (obj == 1) {
                            try {
                                this.b.wait(SYNC_TIME_OUT);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (this.c != null) {
                        i = ((Integer) this.c).intValue();
                    }
                }
            } catch (Throwable th) {
            }
        }
        return i;
    }

    public void getAllTabs(ValueCallback valueCallback) {
        a(CommandConstant.COMMAND_GET_ALL_TABS, null, new AnonymousClass_4(valueCallback));
    }

    public synchronized TabProperties getCurrentTab() {
        try {
            this.c = null;
            IValueCallback anonymousClass_2 = new Stub() {
                public void onReceiveValue(Bundle bundle) throws RemoteException {
                    synchronized (TabsImpl.this.b) {
                        TabsImpl.this.c = TabsImpl.b(bundle);
                        TabsImpl.this.b.notify();
                    }
                }
            };
            synchronized (this.b) {
                if ((a(CommandConstant.COMMAND_GET_TAB_PROPERTY, null, anonymousClass_2) == RequestSender.RESULT_OK ? 1 : null) != null) {
                    try {
                        this.b.wait(SYNC_TIME_OUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return (TabProperties) this.c;
    }

    public synchronized TabProperties getTabProperties(int i) {
        try {
            this.c = null;
            IValueCallback anonymousClass_3 = new Stub() {
                public void onReceiveValue(Bundle bundle) throws RemoteException {
                    synchronized (TabsImpl.this.b) {
                        TabsImpl.this.c = TabsImpl.b(bundle);
                        TabsImpl.this.b.notify();
                    }
                }
            };
            BaseArg tabSimpleArg = new TabSimpleArg();
            tabSimpleArg.tabId = i;
            synchronized (this.b) {
                if ((a(CommandConstant.COMMAND_GET_TAB_PROPERTY, tabSimpleArg, anonymousClass_3) == RequestSender.RESULT_OK ? 1 : null) != null) {
                    try {
                        this.b.wait(SYNC_TIME_OUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return (TabProperties) this.c;
    }

    public void goBack(int i) {
        BaseArg tabSimpleArg = new TabSimpleArg();
        tabSimpleArg.tabId = i;
        a(CommandConstant.COMMAND_GO_BACKWARD, tabSimpleArg, null);
    }

    public void goForward(int i) {
        BaseArg tabSimpleArg = new TabSimpleArg();
        tabSimpleArg.tabId = i;
        a(CommandConstant.COMMAND_GO_FORWARD, tabSimpleArg, null);
    }

    public void loadJavascript(int i, JSParam jSParam, AbstractJSExtension abstractJSExtension) {
        if (jSParam == null) {
            DebugUtil.error("JSParam can't be null");
        } else if (StringUtil.isEmpty(jSParam.javascript)) {
            DebugUtil.error("JSParam javascript can't be null or empty string");
        } else {
            if (!jSParam.javascript.startsWith("javascript:(function(){")) {
                jSParam.javascript = new StringBuilder("javascript:(function(){").append(jSParam.javascript).append("})();").toString();
            }
            BaseArg loadJavaScriptArg = new LoadJavaScriptArg();
            boolean z = false;
            if (abstractJSExtension != null) {
                String name = abstractJSExtension.getClass().getName();
                if (!this.d.a(name)) {
                    this.d.a((AbstractEventReceiver) abstractJSExtension);
                    Object obj = 1;
                }
                loadJavaScriptArg.extensionName = name;
            }
            loadJavaScriptArg.bResgister = z;
            loadJavaScriptArg.tabID = i;
            loadJavaScriptArg.jsParam = jSParam;
            Context a = this.d.a();
            if (a != null) {
                loadJavaScriptArg.addonId = a.getApplicationInfo().packageName;
                a(CommandConstant.COMMAND_LOAD_JS, loadJavaScriptArg, null);
            }
        }
    }

    public void pageDown(int i, boolean z) {
        BaseArg pageUpDownArg = new PageUpDownArg();
        pageUpDownArg.id = i;
        pageUpDownArg.topBottom = z;
        a(CommandConstant.COMMAND_PAGE_DOWN, pageUpDownArg, null);
    }

    public void pageUp(int i, boolean z) {
        BaseArg pageUpDownArg = new PageUpDownArg();
        pageUpDownArg.id = i;
        pageUpDownArg.topBottom = z;
        a(CommandConstant.COMMAND_PAGE_UP, pageUpDownArg, null);
    }

    public void remove(int i) {
        BaseArg tabSimpleArg = new TabSimpleArg();
        tabSimpleArg.tabId = i;
        a(CommandConstant.COMMAND_REMOVE_TAB, tabSimpleArg, null);
    }

    public void update(int i, TabUpdateProperties tabUpdateProperties) {
        BaseArg updateTabArg = new UpdateTabArg();
        updateTabArg.tabId = i;
        updateTabArg.tabProperties = tabUpdateProperties;
        if (tabUpdateProperties != null && a(tabUpdateProperties.url)) {
            a(CommandConstant.COMMAND_UPDATE_TAB, updateTabArg, null);
        }
    }

    public void zoomIn(int i) {
        BaseArg tabSimpleArg = new TabSimpleArg();
        tabSimpleArg.tabId = i;
        a(CommandConstant.COMMAND_ZOOM_IN, tabSimpleArg, null);
    }

    public void zoomOut(int i) {
        BaseArg tabSimpleArg = new TabSimpleArg();
        tabSimpleArg.tabId = i;
        a(CommandConstant.COMMAND_ZOOM_OUT, tabSimpleArg, null);
    }
}
