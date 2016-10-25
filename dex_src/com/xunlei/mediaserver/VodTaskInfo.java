package com.xunlei.mediaserver;

import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class VodTaskInfo {
    private int can_download_peer_res_num;
    private long cdn_download_bytes;
    private int cdn_speed;
    private int connect_peer_res_num;
    private long downloadBytes;
    private int failed_peer_res_num;
    private long fileSize;
    private long p2p_download_bytes;
    private int p2p_speed;
    private int peer_res_num;
    private int speed;

    public boolean setJsonToObject(String str, VodTaskInfo vodTaskInfo) {
        if (str == null || !str.contains("ret")) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("ret") == 0) {
                jSONObject = jSONObject.getJSONObject("resp");
                vodTaskInfo.fileSize = jSONObject.getLong(SHubBatchQueryKeys.filesize);
                vodTaskInfo.speed = jSONObject.getInt("speed");
                vodTaskInfo.cdn_speed = jSONObject.getInt("cdn_speed");
                vodTaskInfo.p2p_speed = jSONObject.getInt("p2p_speed");
                vodTaskInfo.downloadBytes = jSONObject.getLong("downloadbytes");
                vodTaskInfo.cdn_download_bytes = jSONObject.getLong("cdn_download_bytes");
                vodTaskInfo.p2p_download_bytes = jSONObject.getLong("p2p_download_bytes");
                vodTaskInfo.peer_res_num = jSONObject.getInt("peer_res_num");
                vodTaskInfo.connect_peer_res_num = jSONObject.getInt("connect_peer_res_num");
                vodTaskInfo.can_download_peer_res_num = jSONObject.getInt("can_download_peer_res_num");
                vodTaskInfo.failed_peer_res_num = jSONObject.getInt("failed_peer_res_num");
                new StringBuilder("cdn_speed:").append(vodTaskInfo.cdn_speed).append(" p2p_speed:").append(vodTaskInfo.p2p_speed).append("peer_res_num").append(vodTaskInfo.peer_res_num);
                return true;
            }
            vodTaskInfo.speed = 0;
            vodTaskInfo.cdn_speed = 0;
            vodTaskInfo.p2p_speed = 0;
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            vodTaskInfo.speed = 0;
            vodTaskInfo.cdn_speed = 0;
            vodTaskInfo.p2p_speed = 0;
            return false;
        }
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getCdnSpeed() {
        return this.cdn_speed;
    }

    public int getP2pSpeed() {
        return this.p2p_speed;
    }

    public long getDownloadBytes() {
        return this.downloadBytes;
    }

    public long getCdnDownloadBytes() {
        return this.cdn_download_bytes;
    }

    public long getP2pDownloadBytes() {
        return this.p2p_download_bytes;
    }

    public int getPeerResNum() {
        return this.peer_res_num;
    }

    public int getConnectPeerResNum() {
        return this.connect_peer_res_num;
    }

    public int getCanDownloadPeerResNum() {
        return this.can_download_peer_res_num;
    }

    public int getFailPeerResNum() {
        return this.failed_peer_res_num;
    }
}
