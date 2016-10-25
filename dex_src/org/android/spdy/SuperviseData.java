package org.android.spdy;

public class SuperviseData {
    public int bodySize;
    public int compressSize;
    public int recvBodySize;
    public int recvCompressSize;
    public int recvUncompressSize;
    public long requestStart;
    public long responseEnd;
    public long responseStart;
    public long sendEnd;
    public long sendStart;
    public int uncompressSize;

    SuperviseData() {
    }
}
