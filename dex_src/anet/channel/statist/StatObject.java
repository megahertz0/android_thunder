package anet.channel.statist;

import java.io.Serializable;

// compiled from: Taobao
public abstract class StatObject implements Serializable {
    public boolean beforeCommit() {
        return true;
    }
}
