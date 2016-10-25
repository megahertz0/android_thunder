package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import com.xunlei.xiazaibao.sdk.entities.GetUSBInfoResponse.Partition;
import java.util.ArrayList;
import java.util.Iterator;

public class GetUSBInfoResponse extends NoObfuscationClassBase {
    public ArrayList<DiskInfo> disklist;
    private long mTotalSpace;
    private long mUsedSpace;
    public int rtn;

    public static class DiskInfo {
        public String brand;
        public ArrayList<Partition> partitionList;
        public String sn;
    }

    public static class Partition {
        public String key;
        public String letter;
        public String partName;
        public String root;
        public long totleSize;
        public String type;
        public long usedSize;
        public String volume;

        public long getFreeRoomBytes() {
            return this.totleSize - this.usedSize;
        }

        public long getUsedRoomBytes() {
            return this.usedSize;
        }

        public boolean hasEnoughDiskRoomForFile(long j) {
            return getFreeRoomBytes() > 10240 + j;
        }
    }

    public GetUSBInfoResponse() {
        this.rtn = -1;
        this.mTotalSpace = 0;
        this.mUsedSpace = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.disklist != null) {
            Iterator it = this.disklist.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((DiskInfo) it.next()).partitionList.iterator();
                while (it2.hasNext()) {
                    Partition partition = (Partition) it2.next();
                    stringBuilder.append("key = ");
                    stringBuilder.append(partition.key);
                    stringBuilder.append("; name = ");
                    stringBuilder.append(partition.partName);
                    stringBuilder.append("; path = ");
                    stringBuilder.append(partition.root);
                    stringBuilder.append("; totleSize = ");
                    stringBuilder.append(partition.totleSize);
                    stringBuilder.append("; usedSize = ");
                    stringBuilder.append(partition.usedSize);
                    stringBuilder.append("; ");
                }
            }
        }
        return stringBuilder.toString();
    }

    public long getTotalSpace() {
        if (this.disklist == null) {
            return 0;
        }
        if (this.mTotalSpace > 0) {
            return this.mTotalSpace;
        }
        this.mTotalSpace = 0;
        Iterator it = this.disklist.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((DiskInfo) it.next()).partitionList.iterator();
            while (it2.hasNext()) {
                this.mTotalSpace += ((Partition) it2.next()).totleSize;
            }
        }
        return this.mTotalSpace;
    }

    public long getUsedSpace() {
        if (this.mUsedSpace > 0) {
            return this.mUsedSpace;
        }
        this.mUsedSpace = 0;
        Iterator it = this.disklist.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((DiskInfo) it.next()).partitionList.iterator();
            while (it2.hasNext()) {
                this.mUsedSpace += ((Partition) it2.next()).usedSize;
            }
        }
        return this.mUsedSpace;
    }

    public String findAvilablePath(long j) {
        if (this.rtn != 0) {
            return null;
        }
        Iterator it = this.disklist.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((DiskInfo) it.next()).partitionList.iterator();
            while (it2.hasNext()) {
                Partition partition = (Partition) it2.next();
                if (partition.getFreeRoomBytes() > j) {
                    return partition.root;
                }
            }
        }
        return null;
    }

    public boolean hasNoPartition() {
        return this.disklist == null || this.disklist.size() == 0 || ((DiskInfo) this.disklist.get(0)).partitionList == null || ((DiskInfo) this.disklist.get(0)).partitionList.size() == 0;
    }

    public Partition findPartition(String str) {
        if (str == null) {
            return null;
        }
        Iterator it = ((DiskInfo) this.disklist.get(0)).partitionList.iterator();
        while (it.hasNext()) {
            Partition partition = (Partition) it.next();
            if (partition.root.contains(str)) {
                return partition;
            }
            if (str.contains(partition.root)) {
                return partition;
            }
        }
        return null;
    }
}
