package com.xunlei.xiazaibao.sdk.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import com.xunlei.xllib.R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public abstract class AsynTask implements Runnable {
    private static int MAGID_GOTOMAINTHREAD;
    private static final String TAG;
    private static Handler ms_handler;
    private static int ms_taskSeq;
    private static ThreadPoolExecutor ms_threadPool;
    private Object m_callbackObject;
    private String m_callbackfunction;
    private Object[] m_resultObjects;
    private int m_taskSeq;
    private Object m_userdata;

    class AnonymousClass_1 extends Handler {
        AnonymousClass_1(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == MAGID_GOTOMAINTHREAD) {
                AsynTask asynTask = (AsynTask) message.obj;
                asynTask.callFunction(asynTask.m_callbackObject, asynTask.m_callbackfunction, asynTask.m_resultObjects);
            }
        }
    }

    static {
        TAG = AsynTask.class.getSimpleName();
        ms_handler = null;
        ms_taskSeq = 0;
        MAGID_GOTOMAINTHREAD = 9029;
    }

    public AsynTask(Object obj, String str, Object obj2) {
        int i = ms_taskSeq;
        ms_taskSeq = i + 1;
        this.m_taskSeq = i;
        if (ms_handler == null) {
            ms_handler = new AnonymousClass_1(Looper.getMainLooper());
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MqttConnectOptions.MQTT_VERSION_3_1_1);
            ms_threadPool = threadPoolExecutor;
            threadPoolExecutor.setCorePoolSize(MqttConnectOptions.MQTT_VERSION_3_1_1);
            ms_threadPool.setMaximumPoolSize(R.styleable.Toolbar_navigationIcon);
            ms_threadPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
                public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                    XZBLog.d(TAG, "rejectedExecution");
                }
            });
        }
        this.m_callbackObject = obj;
        this.m_userdata = obj2;
        this.m_callbackfunction = str;
    }

    public boolean fireCallback(Object... objArr) {
        if (this.m_callbackObject == null) {
            return false;
        }
        this.m_resultObjects = objArr;
        Message message = new Message();
        message.what = MAGID_GOTOMAINTHREAD;
        message.obj = this;
        ms_handler.sendMessage(message);
        return true;
    }

    private void callFunction(Object obj, String str, Object... objArr) {
        Method[] methods = obj.getClass().getMethods();
        int length = methods.length;
        for (int i = 0; i < length; i++) {
            Method method = methods[i];
            if (method.getName().compareTo(str) == 0) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == objArr.length) {
                    Object obj2;
                    int i2 = 0;
                    while (i2 < parameterTypes.length) {
                        Object obj3 = objArr[i2];
                        if (obj3 != null && !isInherit(parameterTypes[i2], obj3.getClass())) {
                            obj2 = null;
                            break;
                        }
                        i2++;
                    }
                    i2 = 1;
                    if (obj2 != null) {
                        try {
                            method.invoke(obj, objArr);
                            return;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private boolean isInherit(Class<?> cls, Class<?> cls2) {
        Object obj = -1;
        Class[] clsArr = new Class[]{Integer.TYPE, Long.TYPE, Character.TYPE, Byte.TYPE};
        Class[] clsArr2 = new Class[]{Integer.class, Long.class, Character.class, Byte.class};
        if (cls.isAssignableFrom(cls2)) {
            return true;
        }
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Object obj2 = -1;
        Object obj3 = -1;
        Object obj4 = -1;
        for (i = 0; i < 4; i++) {
            if (clsArr[i].getName().compareTo(cls.getName()) == 0) {
                i2 = 0;
                i3 = i;
            }
            if (clsArr[i].getName().compareTo(cls2.getName()) == 0) {
                i4 = 0;
                i5 = i;
            }
        }
        for (i = 0; i < 4; i++) {
            if (clsArr2[i].getName().compareTo(cls.getName()) == 0) {
                boolean z = true;
                i3 = i;
            }
            if (clsArr2[i].getName().compareTo(cls2.getName()) == 0) {
                boolean z2 = true;
                i5 = i;
            }
        }
        return i3 == i5 && i5 >= 0 && i2 != i4;
    }

    public int commit() {
        ms_threadPool.submit(this);
        return getTaskId();
    }

    public int getTaskId() {
        return this.m_taskSeq;
    }

    public Object getUserdata() {
        return this.m_userdata;
    }
}
