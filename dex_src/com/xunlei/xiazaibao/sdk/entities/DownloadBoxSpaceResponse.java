package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.util.List;

public class DownloadBoxSpaceResponse extends NoObfuscationClassBase {
    private String msg;
    private int rtn;
    private List<SpaceEntity> space;

    public static class SpaceEntity {
        private String path;
        private String remain;

        public void setRemain(String str) {
            this.remain = str;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public String getRemain() {
            return this.remain;
        }

        public String getPath() {
            return this.path;
        }
    }

    public DownloadBoxSpaceResponse() {
        this.rtn = -1;
    }

    public void setRtn(int i) {
        this.rtn = i;
    }

    public void setSpace(List<SpaceEntity> list) {
        this.space = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getRtn() {
        return this.rtn;
    }

    public List<SpaceEntity> getSpace() {
        return this.space;
    }

    public String getMsg() {
        return this.msg;
    }
}
