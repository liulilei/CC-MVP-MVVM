package com.androiddesk.base.component.utils.logger;

import com.orhanobut.logger.Logger;

/**
 * 日志工具类
 *
 * @author lll
 */

public class LogUtils {

    public static final String TAG = "desk";

    public synchronized static void v(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        String toStringBuffer = "[" + traceElement.getFileName() + " | " +
                traceElement.getLineNumber() + " | " + traceElement.getMethodName() + "]" + msg;
        Logger.t(TAG).i(toStringBuffer);
    }

    public synchronized static void d(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        String toStringBuffer = "[" + traceElement.getFileName() + " | " +
                traceElement.getLineNumber() + " | " + traceElement.getMethodName() + "] " + msg;
        Logger.t(TAG).i(toStringBuffer);
    }

    public synchronized static void i(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        String toStringBuffer = "[" + traceElement.getFileName() + " | " +
                traceElement.getLineNumber() + " | " + traceElement.getMethodName() + "] " + msg;
        Logger.t(TAG).i(toStringBuffer);
    }

    public synchronized static void w(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        String toStringBuffer = "[" + traceElement.getFileName() + " | " +
                traceElement.getLineNumber() + " | " + traceElement.getMethodName() + "] " + msg;
        Logger.t(TAG).w(toStringBuffer);
    }

    public synchronized static void e(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        String toStringBuffer = "[" + traceElement.getFileName() + " | " +
                traceElement.getLineNumber() + " | " + traceElement.getMethodName() + "] " + msg;
        Logger.t(TAG).e(toStringBuffer);
    }


    public synchronized static void v(String... msgs) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuilder toStringBuffer = new StringBuilder("[").append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("] ");
        if (msgs != null) {
            toStringBuffer.append("Log.v");
        }
        assert msgs != null;
        for (String msg : msgs) {
            toStringBuffer.append(String.format("===%s", msg));
        }
        Logger.t(TAG).i(toStringBuffer.toString());
    }

    public synchronized static void d(String... msgs) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuilder toStringBuffer = new StringBuilder("[").append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("] ");
        if (msgs != null) {
            toStringBuffer.append("Log.d");
        }
        assert msgs != null;
        for (String msg : msgs) {
            toStringBuffer.append(String.format("===%s", msg));
        }
        Logger.t(TAG).i(toStringBuffer.toString());
    }

    public synchronized static void i(String... msgs) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuilder toStringBuffer = new StringBuilder("[").append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("] ");
        if (msgs != null) {
            toStringBuffer.append("Log.i");
        }
        assert msgs != null;
        for (String msg : msgs) {
            toStringBuffer.append(String.format("===%s", msg));
        }
        Logger.t(TAG).i(toStringBuffer.toString());
    }

    public synchronized static void w(String... msgs) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuilder toStringBuffer = new StringBuilder("[").append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("] ");
        if (msgs != null) {
            toStringBuffer.append("Log.w");
        }
        assert msgs != null;
        for (String msg : msgs) {
            toStringBuffer.append(String.format("===%s", msg));
        }
        Logger.t(TAG).w(toStringBuffer.toString());
    }

    public synchronized static void e(String... msgs) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuilder toStringBuffer = new StringBuilder("[").append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("] ");
        if (msgs != null) {
            toStringBuffer.append("Log.e");
        }
        assert msgs != null;
        for (String msg : msgs) {
            toStringBuffer.append(String.format("===%s", msg));
        }
        Logger.t(TAG).e(toStringBuffer.toString());
    }
}
