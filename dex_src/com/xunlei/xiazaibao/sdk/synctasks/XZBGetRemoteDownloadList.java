package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.message.util.HttpRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpHeader;
import com.xunlei.xiazaibao.sdk.entities.DownloadLixianChannel;
import com.xunlei.xiazaibao.sdk.entities.DownloadSubBtInfo;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import com.xunlei.xiazaibao.sdk.entities.DownloadVipChannel;
import com.xunlei.xiazaibao.sdk.entities.QueryDownloadListResponse;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBGetRemoteDownloadList extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private int mNeedUrl;
    private int mNumber;
    private String mPid;
    private int mPos;
    private int mType;

    static {
        TAG = XZBGetRemoteDownloadList.class.getSimpleName();
    }

    public XZBGetRemoteDownloadList(String str, int i, int i2, int i3, int i4) {
        this.mPid = str;
        this.mPos = i;
        this.mNumber = i2;
        this.mType = i3;
        this.mNeedUrl = i4;
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl());
        stringBuffer.append("/list");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append("&pos=");
        stringBuffer.append(this.mPos);
        stringBuffer.append("&number=");
        stringBuffer.append(this.mNumber);
        stringBuffer.append("&type=");
        stringBuffer.append(this.mType);
        stringBuffer.append("&needUrl=");
        stringBuffer.append(this.mNeedUrl);
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public Header[] getHeader() {
        return new Header[]{new HttpHeader("Cookie", getCookie()), new HttpHeader(HttpRequest.l, getContentType()), new HttpHeader(HttpRequest.g, getEncoding())};
    }

    public String getEncoding() {
        return HttpRequest.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String gzipUncompress(byte[] r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.xiazaibao.sdk.synctasks.XZBGetRemoteDownloadList.gzipUncompress(byte[]):java.lang.String");
        /*
        r0 = 1;
        r1 = 0;
        r2 = 2;
        r2 = new byte[r2];
        r3 = r5[r1];
        r2[r1] = r3;
        r3 = r5[r0];
        r2[r0] = r3;
        r3 = r2[r1];
        r3 = r3 << 8;
        r2 = r2[r0];
        r2 = r2 & 255;
        r2 = r2 | r3;
        r3 = 8075; // 0x1f8b float:1.1315E-41 double:3.9896E-320;
        if (r2 != r3) goto L_0x0046;
    L_0x001a:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0050 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x0050 }
        if (r0 == 0) goto L_0x002c;
    L_0x0026:
        r0 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0050 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0050 }
        r1 = r0;
    L_0x002c:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0050 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0050 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0050 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3.<init>(r0, r4);	 Catch:{ Exception -> 0x0050 }
        r0 = r3.readLine();	 Catch:{ Exception -> 0x0050 }
    L_0x003c:
        if (r0 == 0) goto L_0x0048;
    L_0x003e:
        r2.append(r0);	 Catch:{ Exception -> 0x0050 }
        r0 = r3.readLine();	 Catch:{ Exception -> 0x0050 }
        goto L_0x003c;
    L_0x0046:
        r0 = r1;
        goto L_0x001a;
    L_0x0048:
        r1.close();	 Catch:{ Exception -> 0x0050 }
    L_0x004b:
        r0 = r2.toString();
        return r0;
    L_0x0050:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004b;
        */
    }

    public static QueryDownloadListResponse parseResult(XZBGetRemoteDownloadList xZBGetRemoteDownloadList) {
        long j = 0;
        try {
            QueryDownloadListResponse parserJson = parserJson(gzipUncompress(xZBGetRemoteDownloadList.getResponse().getBodybytes()));
            if (parserJson != null) {
                long j2;
                if (parserJson.getRtn() == 0) {
                    List<DownloadTaskInfo> tasks = parserJson.getTasks();
                    if (!(tasks == null || tasks.isEmpty())) {
                        j2 = 0;
                        for (DownloadTaskInfo downloadTaskInfo : tasks) {
                            long speed;
                            if (downloadTaskInfo.getState() == 0) {
                                j2 += downloadTaskInfo.getSpeed();
                                if (downloadTaskInfo.getVipChannel() != null) {
                                    j += downloadTaskInfo.getVipChannel().getSpeed();
                                }
                                if (downloadTaskInfo.getLixianChannel() != null) {
                                    speed = downloadTaskInfo.getLixianChannel().getSpeed() + j;
                                    j = j2;
                                    j2 = j;
                                    j = speed;
                                }
                            }
                            speed = j;
                            j = j2;
                            j2 = j;
                            j = speed;
                        }
                        parserJson.speedCount = j2;
                        parserJson.lixian_vip_speedCount = j;
                    }
                }
                j2 = 0;
                parserJson.speedCount = j2;
                parserJson.lixian_vip_speedCount = j;
            }
            return parserJson;
        } catch (Exception e) {
            return null;
        }
    }

    private static QueryDownloadListResponse parserJson(String str) {
        QueryDownloadListResponse queryDownloadListResponse = new QueryDownloadListResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            queryDownloadListResponse.setRtn(jSONObject.optInt("rtn"));
            queryDownloadListResponse.setSync(jSONObject.optInt("sync"));
            queryDownloadListResponse.msg = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
            queryDownloadListResponse.setDlNum(jSONObject.optInt("dlNum"));
            queryDownloadListResponse.setRecycleNum(jSONObject.optInt("recycleNum"));
            queryDownloadListResponse.setCompleteNum(jSONObject.optInt("completeNum"));
            queryDownloadListResponse.setServerFailNum(jSONObject.optInt("serverFailNum"));
            queryDownloadListResponse.speedCount = jSONObject.optLong("speedCount");
            queryDownloadListResponse.lixian_vip_speedCount = jSONObject.optLong("lixian_vip_speedCount");
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tasks");
            for (int i = 0; i < jSONArray.length(); i++) {
                jSONObject = jSONArray.getJSONObject(i);
                DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo();
                downloadTaskInfo.setProgress(jSONObject.optInt("progress"));
                downloadTaskInfo.setCreateTime(jSONObject.optLong("createTime"));
                downloadTaskInfo.setSpeed(jSONObject.optLong("speed"));
                downloadTaskInfo.setState(jSONObject.optInt(XiaomiOAuthConstants.EXTRA_STATE_2));
                downloadTaskInfo.setType(jSONObject.optInt(AgooConstants.MESSAGE_TYPE));
                JSONObject optJSONObject = jSONObject.optJSONObject("vipChannel");
                DownloadVipChannel downloadVipChannel = new DownloadVipChannel();
                downloadVipChannel.setSpeed((int) optJSONObject.optLong("speed"));
                downloadVipChannel.setFailCode(optJSONObject.optInt("failCode"));
                downloadVipChannel.setDlBytes((int) optJSONObject.optLong("dlBytes"));
                downloadVipChannel.setAvailable(optJSONObject.optInt("available"));
                downloadVipChannel.setType(optJSONObject.optInt(AgooConstants.MESSAGE_TYPE));
                downloadVipChannel.setOpened(optJSONObject.optInt("opened"));
                downloadTaskInfo.setVipChannel(downloadVipChannel);
                downloadTaskInfo.setUrl(jSONObject.optString(SHubBatchQueryKeys.url));
                downloadTaskInfo.setSize(jSONObject.optLong("size"));
                downloadTaskInfo.setDownTime(jSONObject.optLong("downTime"));
                downloadTaskInfo.setId(jSONObject.optString(AgooConstants.MESSAGE_ID));
                downloadTaskInfo.setRemainTime(jSONObject.optLong("remainTime"));
                downloadTaskInfo.setFailCode(jSONObject.optInt("failCode"));
                downloadTaskInfo.setName(jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME));
                downloadTaskInfo.setPath(jSONObject.optString("path"));
                optJSONObject = jSONObject.optJSONObject("lixianChannel");
                DownloadLixianChannel downloadLixianChannel = new DownloadLixianChannel();
                downloadLixianChannel.setSpeed((int) optJSONObject.optLong("speed"));
                downloadLixianChannel.setServerSpeed((int) optJSONObject.optLong("serverSpeed"));
                downloadLixianChannel.setFailCode(optJSONObject.optInt("failCode"));
                downloadLixianChannel.setDlBytes((int) optJSONObject.optLong("dlBytes"));
                downloadLixianChannel.setState(optJSONObject.optInt(XiaomiOAuthConstants.EXTRA_STATE_2));
                downloadLixianChannel.setServerProgress((int) optJSONObject.optLong("serverProgress"));
                downloadTaskInfo.setLixianChannel(downloadLixianChannel);
                downloadTaskInfo.setCompleteTime(jSONObject.optLong("completeTime"));
                List arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject.getJSONArray("subList");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    DownloadSubBtInfo downloadSubBtInfo = new DownloadSubBtInfo();
                    JSONObject optJSONObject2 = jSONArray2.optJSONObject(i2);
                    downloadSubBtInfo.setProgress(optJSONObject2.optInt("progress"));
                    downloadSubBtInfo.setId(optJSONObject2.optInt(AgooConstants.MESSAGE_ID));
                    downloadSubBtInfo.setSelected(optJSONObject2.optInt("selected"));
                    downloadSubBtInfo.setFailCode(optJSONObject2.optInt("failCode"));
                    downloadSubBtInfo.setStatus(optJSONObject2.optInt("status"));
                    downloadSubBtInfo.setName(optJSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME));
                    downloadSubBtInfo.setSize(optJSONObject2.optLong("size"));
                    arrayList2.add(downloadSubBtInfo);
                }
                downloadTaskInfo.setSubList(arrayList2);
                arrayList.add(downloadTaskInfo);
            }
            queryDownloadListResponse.setTasks(arrayList);
        } catch (JSONException e) {
        }
        return queryDownloadListResponse;
    }
}
