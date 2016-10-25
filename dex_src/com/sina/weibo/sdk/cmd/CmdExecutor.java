package com.sina.weibo.sdk.cmd;

interface CmdExecutor<T extends BaseCmd> {
    boolean doExecutor(T t);
}
