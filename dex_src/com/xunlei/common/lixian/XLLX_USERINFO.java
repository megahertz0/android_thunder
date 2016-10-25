package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;

public class XLLX_USERINFO {
    public long Available_sapce;
    public int Buy_bandwidth;
    public short Buy_num_connection;
    public short Buy_num_task;
    public long Buy_store;
    public int Buy_task_live_time;
    public int Complete_num;
    public String Cookie;
    public int Downloading_num;
    public String Expire_date;
    public long Goldbean_num;
    public int History_task_total_num;
    public long Max_store;
    public int Max_task_num;
    public long Silverbean_num;
    public long Store_period;
    public int Suspending_num;
    public int Total_num;
    public short User_type;
    public short Vip_level;
    public long Vip_store;
    public int Waiting_num;

    public XLLX_USERINFO() {
        this.Max_task_num = 0;
        this.Max_store = 0;
        this.Vip_store = 0;
        this.Buy_store = 0;
        this.Buy_num_task = (short) 0;
        this.Buy_num_connection = (short) 0;
        this.Buy_bandwidth = 0;
        this.Buy_task_live_time = 0;
        this.Expire_date = BuildConfig.VERSION_NAME;
        this.Available_sapce = 0;
        this.Total_num = 0;
        this.History_task_total_num = 0;
        this.Suspending_num = 0;
        this.Downloading_num = 0;
        this.Waiting_num = 0;
        this.Complete_num = 0;
        this.Store_period = 0;
        this.Cookie = BuildConfig.VERSION_NAME;
        this.Vip_level = (short) 0;
        this.User_type = (short) 0;
        this.Goldbean_num = 0;
        this.Silverbean_num = 0;
    }
}
