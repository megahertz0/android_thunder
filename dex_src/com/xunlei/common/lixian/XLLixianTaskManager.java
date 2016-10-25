package com.xunlei.common.lixian;

import java.util.HashMap;
import java.util.Map;

public class XLLixianTaskManager {
    private static Map m_lixianTaskMap;

    static {
        m_lixianTaskMap = new HashMap();
    }

    public static void clear() {
        m_lixianTaskMap.clear();
    }

    public static XLLixianTask createTask(long j, int i) {
        XLLixianTask task = getTask(j);
        if (task == null) {
            XLLX_RESTYPE xllx_restype = XLLX_RESTYPE.get(i);
            task = XLLX_RESTYPE.Bt_All == xllx_restype ? new XLLixianBtTask(j, i) : new XLLixianNormalTask(j, i);
            if (XLLX_RESTYPE.Bt_File != xllx_restype) {
                m_lixianTaskMap.put(Long.valueOf(j), task);
            }
        }
        return task;
    }

    public static XLLixianTask getTask(long j) {
        return m_lixianTaskMap.containsKey(Long.valueOf(j)) ? (XLLixianTask) m_lixianTaskMap.get(Long.valueOf(j)) : null;
    }

    public static void removeTask(long j) {
        m_lixianTaskMap.remove(Long.valueOf(j));
    }
}
