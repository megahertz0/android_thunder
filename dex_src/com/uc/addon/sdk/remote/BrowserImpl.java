package com.uc.addon.sdk.remote;

import android.content.Context;
import android.os.Bundle;
import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.DisConnectBrowserArg;
import com.uc.addon.sdk.remote.protocol.DownloadEventArg;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.uc.addon.sdk.remote.protocol.JSExtensionCallbackArg;
import com.uc.addon.sdk.remote.protocol.MemoryEventArg;
import com.uc.addon.sdk.remote.protocol.PageFinishEventArg;
import com.uc.addon.sdk.remote.protocol.PageStartEventArg;
import com.uc.addon.sdk.remote.protocol.TabCreateEventArg;
import com.uc.addon.sdk.remote.protocol.TabRemoteEventArg;
import com.uc.addon.sdk.remote.protocol.TranslateEventArg;
import com.uc.addon.sdk.remote.protocol.ViewFileArg;
import com.xunlei.tdlive.sdk.IHost;
import java.util.HashMap;

public class BrowserImpl extends RequestSender {
    private Browser a;
    private Context b;
    private HashMap c;
    private HashMap d;
    private HashMap e;

    public BrowserImpl(IApp iApp) {
        super(iApp);
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
    }

    private static boolean a(Bundle bundle) {
        return bundle != null;
    }

    private static boolean a(BaseArg baseArg) {
        if (baseArg.checkArgs()) {
            return true;
        }
        new StringBuilder("checking arg fail: ").append(baseArg);
        return false;
    }

    private AbstractEventReceiver b(String str) {
        AbstractEventReceiver abstractEventReceiver = (AbstractEventReceiver) this.c.get(str);
        if (abstractEventReceiver == null) {
            abstractEventReceiver = (AbstractEventReceiver) this.e.get(str);
        }
        if (abstractEventReceiver != null) {
            return abstractEventReceiver;
        }
        abstractEventReceiver = d(str);
        this.c.put(str, abstractEventReceiver);
        return abstractEventReceiver;
    }

    private AbstractExtension c(String str) {
        if (str == null) {
            return null;
        }
        try {
            Class forName = Class.forName(str);
            if (forName == null) {
                return null;
            }
            AbstractExtension abstractExtension;
            Object newInstance = forName.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof AbstractExtension) {
                abstractExtension = (AbstractExtension) newInstance;
                try {
                    abstractExtension.a(this.b, this.a);
                    AbstractExtension.a();
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    return abstractExtension;
                }
            }
            abstractExtension = null;
            return abstractExtension;
        } catch (Exception e3) {
            Exception exception = e3;
            abstractExtension = null;
            e2 = exception;
            e2.printStackTrace();
            return abstractExtension;
        }
    }

    private AbstractEventReceiver d(String str) {
        if (str == null) {
            return null;
        }
        try {
            Class forName = Class.forName(str);
            if (forName == null) {
                new StringBuilder("null returned by Class.forName( ").append(str).append(" )");
                return null;
            }
            AbstractEventReceiver abstractEventReceiver;
            Object newInstance = forName.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof AbstractEventReceiver) {
                abstractEventReceiver = (AbstractEventReceiver) newInstance;
                try {
                    abstractEventReceiver.a(this.b, this.a);
                    AbstractEventReceiver.a();
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    return abstractEventReceiver;
                }
            }
            new StringBuilder().append(str).append(" is not inherit from EventReceiver");
            abstractEventReceiver = null;
            return abstractEventReceiver;
        } catch (Exception e3) {
            Exception exception = e3;
            abstractEventReceiver = null;
            e2 = exception;
            e2.printStackTrace();
            return abstractEventReceiver;
        }
    }

    protected final Context a() {
        return this.b;
    }

    final void a(AbstractEventReceiver abstractEventReceiver) {
        if (abstractEventReceiver != null) {
            String name = abstractEventReceiver.getClass().getName();
            abstractEventReceiver.a(this.b, this.a);
            AbstractEventReceiver.a();
            this.e.put(name, abstractEventReceiver);
        }
    }

    final boolean a(String str) {
        return this.c.containsKey(str) || this.e.containsKey(str);
    }

    final void b() {
        BaseArg disConnectBrowserArg = new DisConnectBrowserArg();
        disConnectBrowserArg.id = this.b.getPackageName();
        a(CommandConstant.COMMAND_DISCONNECT, disConnectBrowserArg, null);
    }

    public void init(Context context, Browser browser) {
        this.b = context;
        this.a = browser;
    }

    public void request(String str, Bundle bundle, IValueCallback iValueCallback) {
        if (str != null) {
            String string;
            if (str.equals(CommandConstant.COMMAND_EVENT_VISUALEXTENSION_CLICK)) {
                if (bundle != null) {
                    string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                    DebugUtil.uc_assert(string != null, "extension name can't be null");
                    if (string != null) {
                        AbstractExtension abstractExtension = (AbstractExtension) this.d.get(string);
                        if (abstractExtension == null) {
                            abstractExtension = c(string);
                            this.d.put(string, abstractExtension);
                        }
                        if (abstractExtension != null) {
                            abstractExtension.invoke();
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_JSEXTENSION_CALLBACK)) {
                if (bundle != null) {
                    string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                    jSExtensionCallbackArg = new JSExtensionCallbackArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg) && string != null) {
                        b = b(string);
                        if (b != null && (b instanceof AbstractJSExtension)) {
                            eventJSExtensionCallback = new EventJSExtensionCallback(jSExtensionCallbackArg.tabID, jSExtensionCallbackArg.method, jSExtensionCallbackArg.argsJson, iValueCallback);
                            b.a(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_TAB_CREATED)) {
                if (a(bundle)) {
                    string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                    jSExtensionCallbackArg = new TabCreateEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg) && string != null) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventTabCreated();
                            eventJSExtensionCallback.a(jSExtensionCallbackArg);
                            b.a((int) IHost.HOST_NOFITY_REFRESH_LIST, eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_TAB_REMOVED)) {
                if (a(bundle)) {
                    string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                    jSExtensionCallbackArg = new TabRemoteEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg) && string != null) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventTabRemoved();
                            eventJSExtensionCallback.tabId = jSExtensionCallbackArg.id;
                            b.a((int) IHost.HOST_NOFITY_PAGE_SELECTED, eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_CAMERA)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    b = b(string);
                    if (b != null) {
                        b.a((int) EventIds.EVENT_CAMERA, new EventCamera(iValueCallback));
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_SHARE)) {
                if (a(bundle)) {
                    b = b(bundle.getString(KeyConstant.KEY_EXTENSION_NAME));
                    if (b != null) {
                        b.a((int) EventIds.EVENT_SHARE, new EventShare(bundle));
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_VIEW_FILE)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    ViewFileArg viewFileArg = new ViewFileArg();
                    viewFileArg.fromBundle(bundle);
                    b = b(string);
                    if (b != null) {
                        eventJSExtensionCallback = new EventViewFile();
                        eventJSExtensionCallback.intent = viewFileArg.intent;
                        b.a(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_MEMORY_STATE)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    jSExtensionCallbackArg = new MemoryEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg)) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventMemoryState();
                            eventJSExtensionCallback.mState = jSExtensionCallbackArg.mState;
                            b.onEvent(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_TRANSLATE)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    jSExtensionCallbackArg = new TranslateEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg)) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventTranslate();
                            eventJSExtensionCallback.text = jSExtensionCallbackArg.text;
                            b.onEvent(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_PAGE_STARTED)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    jSExtensionCallbackArg = new PageStartEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg)) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventPageStarted();
                            eventJSExtensionCallback.id = jSExtensionCallbackArg.id;
                            eventJSExtensionCallback.url = jSExtensionCallbackArg.url;
                            b.onEvent(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_VIDEO_EXPAND)) {
                if (a(bundle)) {
                    BaseArg downloadTaskArg = new DownloadTaskArg();
                    downloadTaskArg.fromBundle(bundle);
                    string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                    if (a(downloadTaskArg) && string != null) {
                        AbstractEventReceiver b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventVideoExpand();
                            eventJSExtensionCallback.a(downloadTaskArg.downloadTask);
                            b.a((int) EventIds.EVENT_VIDEOEXPAND, eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_PAGE_FINISHED)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    jSExtensionCallbackArg = new PageFinishEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg)) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventPageFinished();
                            eventJSExtensionCallback.id = jSExtensionCallbackArg.id;
                            eventJSExtensionCallback.url = jSExtensionCallbackArg.url;
                            b.onEvent(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            } else if (str.equals(CommandConstant.COMMAND_EVENT_BOOT_COMPLETED)) {
                if (a(bundle)) {
                    b = b(bundle.getString(KeyConstant.KEY_EXTENSION_NAME));
                    if (b != null) {
                        b.a((int) EventIds.EVENT_BOOT_COMPLETE, null);
                    }
                }
            } else if (str.equals(CommandConstant.COMMADN_EVENT_DOWNLOAD)) {
                string = bundle.getString(KeyConstant.KEY_EXTENSION_NAME);
                if (string != null) {
                    jSExtensionCallbackArg = new DownloadEventArg();
                    jSExtensionCallbackArg.fromBundle(bundle);
                    if (a(jSExtensionCallbackArg)) {
                        b = b(string);
                        if (b != null) {
                            eventJSExtensionCallback = new EventDownload();
                            eventJSExtensionCallback.filename = jSExtensionCallbackArg.filename;
                            eventJSExtensionCallback.url = jSExtensionCallbackArg.url;
                            eventJSExtensionCallback.path = jSExtensionCallbackArg.path;
                            b.onEvent(eventJSExtensionCallback.getEventId(), eventJSExtensionCallback);
                        }
                    }
                }
            }
        }
    }
}
