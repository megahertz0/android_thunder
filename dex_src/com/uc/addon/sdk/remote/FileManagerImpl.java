package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.BaseArg;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.FileManagerDirectoryOpenArg;
import com.uc.addon.sdk.remote.protocol.IApp;
import java.io.File;

public class FileManagerImpl extends RequestSender implements FileManager {
    public FileManagerImpl(IApp iApp) {
        super(iApp);
    }

    public void openDirectory(String str) {
        if (new File(str).exists()) {
            BaseArg fileManagerDirectoryOpenArg = new FileManagerDirectoryOpenArg();
            fileManagerDirectoryOpenArg.path = str;
            a(CommandConstant.COMMAND_FILEMANAGER_DIRECTORY_OPEN, fileManagerDirectoryOpenArg, null);
            return;
        }
        DebugUtil.error(new StringBuilder("FileManager.openDirectory not exists; path = ").append(str).toString());
    }
}
