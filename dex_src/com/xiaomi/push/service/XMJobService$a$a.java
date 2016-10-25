package com.xiaomi.push.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.timers.a;
import org.apache.commons.logging.impl.SimpleLog;

private class XMJobService$a$a extends Handler {
    JobService a;

    XMJobService$a$a(JobService jobService) {
        super(jobService.getMainLooper());
        this.a = jobService;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case SimpleLog.LOG_LEVEL_TRACE:
                JobParameters jobParameters = (JobParameters) message.obj;
                b.a(new StringBuilder("Job finished ").append(jobParameters.getJobId()).toString());
                this.a.jobFinished(jobParameters, false);
                if (jobParameters.getJobId() == 1) {
                    a.a(false);
                }
            default:
                break;
        }
    }
}
