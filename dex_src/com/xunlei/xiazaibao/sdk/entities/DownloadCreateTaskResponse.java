package com.xunlei.xiazaibao.sdk.entities;

import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.util.List;

public class DownloadCreateTaskResponse extends NoObfuscationClassBase {
    private String msg;
    private int rtn;
    private List<CreateTask> tasks;

    public static class CreateTask extends NoObfuscationClassBase {
        private int id;
        private String msg;
        private String name;
        private int result;
        private String taskid;
        private String url;

        public void setId(int i) {
            this.id = i;
        }

        public void setResult(int i) {
            this.result = i;
        }

        public void setTaskid(String str) {
            this.taskid = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getId() {
            return this.id;
        }

        public int getResult() {
            return this.result;
        }

        public String getTaskid() {
            return this.taskid;
        }

        public String getName() {
            return this.name;
        }

        public String getMsg() {
            return this.msg;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public void setRtn(int i) {
        this.rtn = i;
    }

    public void setTasks(List<CreateTask> list) {
        this.tasks = list;
    }

    public int getRtn() {
        return this.rtn;
    }

    public List<CreateTask> getTasks() {
        return this.tasks;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
