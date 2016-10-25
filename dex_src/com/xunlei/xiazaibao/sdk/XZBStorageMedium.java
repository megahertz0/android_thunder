package com.xunlei.xiazaibao.sdk;

import java.util.ArrayList;
import java.util.List;

public class XZBStorageMedium {
    public String brand;
    public List<Partition> partitionList;
    public String sn;

    public static class Partition {
        public String encryptionType;
        public String key;
        public String letter;
        public String partName;
        public String root;
        public long totleSize;
        public String type;
        public long usedSize;
        public String volume;
    }

    public XZBStorageMedium() {
        this.partitionList = new ArrayList();
    }

    public int getPartitionCount() {
        return this.partitionList.size();
    }

    public Partition getPartition(int i) {
        return (Partition) this.partitionList.get(i);
    }
}
