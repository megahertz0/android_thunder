package com.baidu.mobads.openad.interfaces.download;

import com.baidu.mobads.interfaces.download.IXAdStaticImgDownloader;
import java.net.URL;
import java.util.ArrayList;

public interface IOAdDownloaderManager {
    IOAdDownloader createAdsApkDownloader(URL url, String str, String str2, int i, String str3, String str4);

    IXAdStaticImgDownloader createImgHttpDownloader(URL url, String str, String str2);

    IOAdDownloader createSimpleFileDownloader(URL url, String str, String str2, boolean z);

    IOAdDownloader getAdsApkDownloader(String str);

    ArrayList<IOAdDownloader> getAllAdsApkDownloaderes();

    Boolean removeAdsApkDownloader(String str);

    void removeAllAdsApkDownloaderes();

    void resumeUndownloadedAfterRestartApp(long j);
}
