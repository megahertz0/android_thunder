package com.bumptech.glide.load.engine.executor;

import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy;

// compiled from: FifoPriorityThreadPoolExecutor.java
enum c extends UncaughtThrowableStrategy {
    c(String str) {
        super(str, 2, (byte) 0);
    }

    protected final void handle(Throwable th) {
        super.handle(th);
        throw new RuntimeException(th);
    }
}
