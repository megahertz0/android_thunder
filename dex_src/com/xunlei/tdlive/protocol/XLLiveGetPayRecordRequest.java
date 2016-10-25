package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetPayRecordRequest.Record;
import com.xunlei.tdlive.protocol.XLLiveGetPayRecordRequest.RecordList;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetPayRecordRequest extends XLLiveRequest {
    private static final int S_CHECKING = 0;
    private static final int S_CHECK_NO_PASS = 1;
    private static final int S_FAILED = 2;
    private static final int S_SUCCEED = 3;
    private int mPage;

    public static final class Record {
        public static final int T_NORMAL = 0;
        public static final int T_SIGNER = 1;
        public int coin;
        public String error;
        public String info;
        public int money;
        public int status;
        public String status_time;
        public int type;
        public int userid;

        public Record() {
            this.status = -1;
        }

        public final int getStatusDesc() {
            switch (this.status) {
                case T_NORMAL:
                    return R.string.withdraw_status_checking;
                case T_SIGNER:
                    return R.string.withdraw_status_no_pass;
                case S_FAILED:
                    return R.string.withdraw_status_failed;
                case S_SUCCEED:
                    return R.string.withdraw_status_succeed;
                default:
                    return R.string.unknown;
            }
        }
    }

    public static final class RecordList {
        public List<Record> lists;
        public int sum;
    }

    public static final class RecordListResp extends XLLiveRespBase {
        public RecordList data;
    }

    public XLLiveGetPayRecordRequest(String str, String str2, int i) {
        super(str, str2);
        this.mPage = i;
    }

    protected String onGetURL() {
        return String.format("http://biz.live.xunlei.com/caller?c=user&a=exchangelist&page=%d", new Object[]{Integer.valueOf(this.mPage)});
    }

    protected Class<?> onGetObjectClass() {
        return RecordListResp.class;
    }
}
