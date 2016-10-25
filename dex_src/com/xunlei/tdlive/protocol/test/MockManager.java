package com.xunlei.tdlive.protocol.test;

import com.xunlei.tdlive.protocol.XLLiveRequest;
import java.util.ArrayList;
import java.util.List;

public class MockManager {
    public static boolean ENABLE;
    private static MockManager sInst;
    public List<MockItem> mMockList;

    static {
        ENABLE = false;
    }

    private MockManager() {
        this.mMockList = new ArrayList();
    }

    public static MockManager instance() {
        if (sInst == null) {
            sInst = new MockManager();
        }
        return sInst;
    }

    public void addMock(Class<? extends XLLiveRequest> cls, Object obj) {
    }

    public void removeMock(Class<?> cls) {
        for (MockItem mockItem : this.mMockList) {
            if (mockItem.cls.equals(cls)) {
                this.mMockList.remove(mockItem);
                return;
            }
        }
    }

    public Object getMockData(Class<?> cls) {
        return null;
    }
}
