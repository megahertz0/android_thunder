package com.bumptech.glide.load.engine.executor;

import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy;

// compiled from: FifoPriorityThreadPoolExecutor.java
enum b extends UncaughtThrowableStrategy {
    b(String str) {
        super(str, 1, (byte) 0);
    }

    protected final void handle(Throwable th) {
    }
}
