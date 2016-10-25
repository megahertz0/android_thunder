package com.tencent.stat.common;

import com.umeng.socialize.common.SocializeConstants;

public final class StatLogger {
    private String a;
    private boolean b;
    private int c;

    public StatLogger() {
        this.a = "default";
        this.b = true;
        this.c = 2;
    }

    public StatLogger(String str) {
        this.a = "default";
        this.b = true;
        this.c = 2;
        this.a = str;
    }

    private String a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return new StringBuilder("[").append(Thread.currentThread().getName()).append(SocializeConstants.OP_OPEN_PAREN).append(Thread.currentThread().getId()).append("): ").append(stackTraceElement.getFileName()).append(":").append(stackTraceElement.getLineNumber()).append("]").toString();
            }
        }
        return null;
    }

    public final void d(Object obj) {
        if (isDebugEnable()) {
            debug(obj);
        }
    }

    public final void debug(Object obj) {
        if (this.c <= 3) {
            String a = a();
            if (a == null) {
                obj.toString();
            } else {
                new StringBuilder().append(a).append(" - ").append(obj);
            }
        }
    }

    public final void e(Exception exception) {
        if (isDebugEnable()) {
            error(exception);
        }
    }

    public final void e(Object obj) {
        if (isDebugEnable()) {
            error(obj);
        }
    }

    public final void error(Exception exception) {
        if (this.c <= 6) {
            StringBuffer stringBuffer = new StringBuffer();
            String a = a();
            StackTraceElement[] stackTrace = exception.getStackTrace();
            if (a != null) {
                stringBuffer.append(a + " - " + exception + "\r\n");
            } else {
                stringBuffer.append(exception + "\r\n");
            }
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        stringBuffer.append(new StringBuilder("[ ").append(stackTraceElement.getFileName()).append(":").append(stackTraceElement.getLineNumber()).append(" ]\r\n").toString());
                    }
                }
            }
        }
    }

    public final void error(Object obj) {
        if (this.c <= 6) {
            String a = a();
            if (a == null) {
                obj.toString();
            } else {
                new StringBuilder().append(a).append(" - ").append(obj);
            }
        }
    }

    public final int getLogLevel() {
        return this.c;
    }

    public final void i(Object obj) {
        if (isDebugEnable()) {
            info(obj);
        }
    }

    public final void info(Object obj) {
        if (this.c <= 4) {
            String a = a();
            if (a == null) {
                obj.toString();
            } else {
                new StringBuilder().append(a).append(" - ").append(obj);
            }
        }
    }

    public final boolean isDebugEnable() {
        return this.b;
    }

    public final void setDebugEnable(boolean z) {
        this.b = z;
    }

    public final void setLogLevel(int i) {
        this.c = i;
    }

    public final void setTag(String str) {
        this.a = str;
    }

    public final void v(Object obj) {
        if (isDebugEnable()) {
            verbose(obj);
        }
    }

    public final void verbose(Object obj) {
        if (this.c <= 2) {
            String a = a();
            if (a == null) {
                obj.toString();
            } else {
                new StringBuilder().append(a).append(" - ").append(obj);
            }
        }
    }

    public final void w(Object obj) {
        if (isDebugEnable()) {
            warn(obj);
        }
    }

    public final void warn(Object obj) {
        if (this.c <= 5) {
            String a = a();
            if (a == null) {
                obj.toString();
            } else {
                new StringBuilder().append(a).append(" - ").append(obj);
            }
        }
    }
}
