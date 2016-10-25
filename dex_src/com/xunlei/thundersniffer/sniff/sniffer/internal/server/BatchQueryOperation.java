package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.xunlei.thundersniffer.context.volley.RequestManager;
import com.xunlei.thundersniffer.operation.IOperation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BatchQueryOperation<T> extends IOperation {
    public final int mBatchCount;
    protected SvrOperationManager mOperationManager;
    public List<T> mSourceData;
    public List<T> mTodoBatch;

    public abstract void onFinish();

    public abstract boolean onHandleQueryBatch(ArrayList<T> arrayList);

    public abstract void preprocessTodoBatch(List<T> list);

    public BatchQueryOperation(SvrOperationManager svrOperationManager, List<T> list) {
        this.mBatchCount = 10;
        this.mOperationManager = svrOperationManager;
        this.mSourceData = list;
    }

    public void cancel() {
        setCancelled(true);
        if (this.mOperationManager != null) {
            this.mOperationManager.remove(this);
        }
    }

    public void finish() {
        setFinished(true);
        if (this.mOperationManager != null) {
            this.mOperationManager.remove(this);
        }
        onFinish();
    }

    public void start() {
        execute();
    }

    public void onExecute() {
        if (!canExecute()) {
            return;
        }
        if (this.mSourceData == null || this.mSourceData.isEmpty()) {
            finish();
            return;
        }
        this.mTodoBatch = new ArrayList(this.mSourceData);
        preprocessTodoBatch(this.mTodoBatch);
        checkNextInner();
    }

    protected void queryBatch(ArrayList<T> arrayList) {
        if (!onHandleQueryBatch(arrayList)) {
            checkNext();
        }
    }

    protected final synchronized void checkNextInner() {
        if (!(isCancelled() || isFinished())) {
            if (this.mTodoBatch == null || this.mTodoBatch.isEmpty()) {
                finish();
            } else {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                Iterator it = this.mTodoBatch.iterator();
                while (it.hasNext() && i < 10) {
                    Object next = it.next();
                    it.remove();
                    arrayList.add(next);
                    i++;
                }
                queryBatch(arrayList);
            }
        }
    }

    public void checkNext() {
        RequestManager.executorService().execute(new a(this));
    }
}
