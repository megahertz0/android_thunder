package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.StatArg;

public class StatImpl extends RequestSender implements Stat {
    public StatImpl(IApp iApp) {
        super(iApp);
    }

    public void addMgrStat(String str, String str2) {
        BaseArg statArg = new StatArg();
        statArg.id = str;
        statArg.key = str2;
        statArg.isMgrStat = true;
        a(CommandConstant.COMMAND_STAT, statArg, null);
    }

    public void addStat(String str, String str2) {
        BaseArg statArg = new StatArg();
        statArg.id = str;
        statArg.key = str2;
        statArg.isMgrStat = false;
        a(CommandConstant.COMMAND_STAT, statArg, null);
    }
}
