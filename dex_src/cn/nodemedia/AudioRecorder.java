package cn.nodemedia;

import android.media.AudioRecord;
import android.os.Process;
import com.taobao.accs.data.Message;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class AudioRecorder {
    private AudioRecord mAudioRecord;
    private boolean mAudioRecordReleased;
    private int mFrameBufferSize;
    private boolean mIsPause;
    private RecordAudioThread mRecordAudioThread;
    private boolean mRecordThreadExitFlag;

    class RecordAudioThread extends Thread {
        RecordAudioThread() {
        }

        public void run() {
            try {
                Process.setThreadPriority(-19);
            } catch (Exception e) {
                new StringBuilder("Set record thread priority failed: ").append(e.getMessage());
            }
            byte[] bArr = new byte[AudioRecorder.this.mFrameBufferSize];
            while (!AudioRecorder.this.mRecordThreadExitFlag) {
                int read = AudioRecorder.this.mAudioRecord.read(bArr, 0, AudioRecorder.this.mFrameBufferSize);
                if (read != -3 && read != -2 && !AudioRecorder.this.mIsPause) {
                    LivePublisher.putAudioData(bArr, AudioRecorder.this.mFrameBufferSize);
                }
            }
        }
    }

    public AudioRecorder() {
        this.mAudioRecord = null;
        this.mRecordAudioThread = null;
        this.mRecordThreadExitFlag = false;
        this.mAudioRecordReleased = true;
        this.mIsPause = false;
    }

    public int startAudioRecoder(int i, int i2, int i3) {
        int i4 = Message.FLAG_RET;
        releaseAudioRecorder();
        this.mFrameBufferSize = i3 * 2;
        int i5 = i2 == 1 ? R.styleable.Toolbar_titleMarginBottom : XZBDevice.Fail;
        try {
            int minBufferSize = AudioRecord.getMinBufferSize(i, i5, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            if (minBufferSize >= 2048) {
                i4 = minBufferSize;
            }
            this.mAudioRecord = new AudioRecord(1, i, i5, 2, i4);
            this.mAudioRecord.startRecording();
            minBufferSize = this.mAudioRecord.read(new byte[this.mFrameBufferSize], 0, this.mFrameBufferSize);
            if (minBufferSize == -3 || minBufferSize == -2) {
                throw new Exception();
            }
            if (this.mRecordAudioThread == null) {
                this.mAudioRecordReleased = false;
                this.mRecordThreadExitFlag = false;
                this.mRecordAudioThread = new RecordAudioThread();
                this.mRecordAudioThread.start();
            }
            this.mIsPause = false;
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public void pause() {
        this.mIsPause = true;
    }

    public void resume() {
        this.mIsPause = false;
    }

    public void releaseAudioRecorder() {
        if (!this.mAudioRecordReleased) {
            if (this.mRecordAudioThread != null) {
                this.mRecordThreadExitFlag = true;
                this.mRecordAudioThread = null;
            }
            if (this.mAudioRecord != null) {
                this.mAudioRecord.stop();
                this.mAudioRecord.release();
                this.mAudioRecord = null;
            }
            this.mAudioRecordReleased = true;
        }
    }
}
