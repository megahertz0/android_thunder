package com.taobao.tlog.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.taobao.tao.log.ITLogController;
import com.taobao.tao.log.TLogInitializer;
import com.taobao.tao.log.TLogUtils;
import com.taobao.tao.log.TaskManager;
import com.taobao.wswitch.api.business.ConfigContainerAdapter;
import com.taobao.wswitch.business.ConfigContainer;
import com.taobao.wswitch.model.Config;
import com.tencent.bugly.Bugly;
import java.util.Map;

public class TLogConfigSwitchReceiver extends BroadcastReceiver {
    private static final String TAG = "TLogConfigSwitchReceiver";

    public static void init(Context context) {
        String[] strArr = new String[]{"remote_debuger_android"};
        ConfigContainer.getInstance().initialize(context, strArr);
        ConfigContainerAdapter.getInstance().addIntentActionGroupNames(strArr);
        String intentActionName = ConfigContainer.getInstance().getIntentActionName("remote_debuger_android");
        context.getApplicationContext().registerReceiver(new TLogConfigSwitchReceiver(), new IntentFilter(intentActionName));
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Object action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.contains("remote_debuger_android")) {
                Config configByGroupName = ConfigContainer.getInstance().getConfigByGroupName("remote_debuger_android");
                if (configByGroupName != null) {
                    Map data = configByGroupName.getData();
                    if (data != null) {
                        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                        String str = (String) data.get("tlog_destroy");
                        String str2 = (String) data.get("tlog_switch");
                        String str3 = (String) data.get("tlog_level");
                        String str4 = (String) data.get("tlog_module");
                        String str5 = (String) data.get("tlog_endtime");
                        String str6 = (String) data.get("tlog_pull");
                        ITLogController tLogControler = TLogInitializer.getTLogControler();
                        if (tLogControler != null) {
                            new StringBuilder("The tlogDestroy is : ").append(str).append("  tlogSwitch is : ").append(str2).append("  tlogLevel is : ").append(str3).append("  tlogModule is : ").append(str4);
                            if (!TextUtils.isEmpty(str)) {
                                if ("true".equalsIgnoreCase(str)) {
                                    TLogInitializer.delete();
                                    tLogControler.openLog(false);
                                    tLogControler.destroyLog(true);
                                    edit.putBoolean("tlog_switch", false);
                                    return;
                                }
                                tLogControler.destroyLog(false);
                                if (!TextUtils.isEmpty(str2)) {
                                    if ("true".equalsIgnoreCase(str2)) {
                                        tLogControler.openLog(true);
                                        edit.putBoolean("tlog_switch", true);
                                    } else if (Bugly.SDK_IS_DEV.equalsIgnoreCase(str2)) {
                                        tLogControler.openLog(false);
                                        edit.putBoolean("tlog_switch", false);
                                    }
                                    if (!TextUtils.isEmpty(str3)) {
                                        tLogControler.setLogLevel(str3);
                                        edit.putString("tlog_level", str3);
                                        if (!TextUtils.isEmpty(str4)) {
                                            tLogControler.setModuleFilter(TLogUtils.makeModule(str4));
                                            edit.putString("tlog_module", str4);
                                            if (TextUtils.isEmpty(str5)) {
                                                tLogControler.setEndTime(System.currentTimeMillis());
                                                edit.putLong("tlog_endtime", System.currentTimeMillis());
                                            } else {
                                                long parseInt;
                                                try {
                                                    parseInt = ((long) (Integer.parseInt(str5) * 1000)) + System.currentTimeMillis();
                                                } catch (NumberFormatException e) {
                                                    parseInt = System.currentTimeMillis();
                                                }
                                                long currentTimeMillis = System.currentTimeMillis() + 86400000;
                                                if (parseInt > System.currentTimeMillis() && parseInt < currentTimeMillis) {
                                                    tLogControler.setEndTime(parseInt);
                                                    edit.putLong("tlog_endtime", parseInt);
                                                } else if (parseInt >= currentTimeMillis) {
                                                    tLogControler.setEndTime(currentTimeMillis);
                                                    edit.putLong("tlog_endtime", currentTimeMillis);
                                                } else {
                                                    tLogControler.setEndTime(System.currentTimeMillis());
                                                    edit.putLong("tlog_endtime", System.currentTimeMillis());
                                                }
                                            }
                                            if (!TextUtils.isEmpty(str6)) {
                                                if (str6.equals("true")) {
                                                    TaskManager.getInstance().queryTraceStatus(context);
                                                }
                                                edit.putString("tlog_pull", str6);
                                            }
                                            edit.putString("tlog_version", TLogUtils.getAppBuildVersion(context));
                                            edit.apply();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
