package com.tencent.open.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// compiled from: ProGuard
public final class ThreadManager {
    public static final Executor NETWORK_EXECUTOR;
    private static Handler a;
    private static Object b;
    private static Handler c;
    private static HandlerThread d;
    private static Handler e;
    private static HandlerThread f;

    // compiled from: ProGuard
    private static class SerialExecutor implements Executor {
        final Queue<Runnable> a;
        Runnable b;

        // compiled from: ProGuard
        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ Runnable a;

            AnonymousClass_1(Runnable runnable) {
                this.a = runnable;
            }

            public void run() {
                this.a.run();
                SerialExecutor.this.a();
            }
        }

        private SerialExecutor() {
            this.a = new LinkedList();
        }

        public synchronized void execute(Runnable runnable) {
            this.a.offer(new AnonymousClass_1(runnable));
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnable = (Runnable) this.a.poll();
            this.b = runnable;
            if (runnable != null) {
                NETWORK_EXECUTOR.execute(this.b);
            }
        }
    }

    static {
        b = new Object();
        NETWORK_EXECUTOR = a();
    }

    private static Executor a() {
        Executor threadPoolExecutor;
        if (VERSION.SDK_INT >= 11) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } else {
            try {
                Field declaredField = AsyncTask.class.getDeclaredField("sExecutor");
                declaredField.setAccessible(true);
                threadPoolExecutor = (Executor) declaredField.get(null);
            } catch (Exception e) {
                threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
            }
        }
        if (threadPoolExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) threadPoolExecutor).setCorePoolSize(XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        return threadPoolExecutor;
    }

    public static void init() {
    }

    public static void executeOnNetWorkThread(Runnable runnable) {
        try {
            NETWORK_EXECUTOR.execute(runnable);
        } catch (RejectedExecutionException e) {
        }
    }

    public static Handler getMainHandler() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new Handler(Looper.getMainLooper());
                }
            }
        }
        return a;
    }

    public static Handler getFileThreadHandler() {
        if (e == null) {
            synchronized (ThreadManager.class) {
                HandlerThread handlerThread = new HandlerThread("SDK_FILE_RW");
                f = handlerThread;
                handlerThread.start();
                e = new Handler(f.getLooper());
            }
        }
        return e;
    }

    public static Looper getFileThreadLooper() {
        return getFileThreadHandler().getLooper();
    }

    public static Thread getSubThread() {
        if (d == null) {
            getSubThreadHandler();
        }
        return d;
    }

    public static Handler getSubThreadHandler() {
        if (c == null) {
            synchronized (ThreadManager.class) {
                HandlerThread handlerThread = new HandlerThread("SDK_SUB");
                d = handlerThread;
                handlerThread.start();
                c = new Handler(d.getLooper());
            }
        }
        return c;
    }

    public static Looper getSubThreadLooper() {
        return getSubThreadHandler().getLooper();
    }

    public static void executeOnSubThread(Runnable runnable) {
        getSubThreadHandler().post(runnable);
    }

    public static void executeOnFileThread(Runnable runnable) {
        getFileThreadHandler().post(runnable);
    }

    public static Executor newSerialExecutor() {
        return new SerialExecutor();
    }
}
