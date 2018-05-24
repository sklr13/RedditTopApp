package rddt.com.redditapp.utils;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import rddt.com.redditapp.BuildConfig;
import rddt.com.redditapp.mvp.exceptions.BaseException;

@SuppressWarnings("ConstantConditions")
public class Logger {

    private static final int MIN_LEVEL = BuildConfig.DEBUG ? Log.VERBOSE : Log.ERROR;

    @NonNull
    private final String tag;

    private Logger(@NonNull Class<?> aClass) {
        this.tag = BuildConfig.DEBUG ? aClass.getSimpleName() : aClass.getCanonicalName();
    }

    @NonNull
    public static Logger create(@NonNull Class<?> aClass) {
        return new Logger(aClass);
    }

    @NonNull
    public static Logger create(@NonNull Object classForSimpleName) {
        return new Logger(classForSimpleName.getClass());
    }

    public void v(@Nullable String msg) {
        if (MIN_LEVEL <= Log.VERBOSE) {
            Log.v(tag, convertToMessage(msg));
        }
    }

    public void v(@Nullable Throwable e, @Nullable String msg) {
        if (MIN_LEVEL <= Log.VERBOSE) {
            Log.v(tag, convertToMessage(msg, e));
        }
    }

    public void d(@Nullable String msg) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.d(tag, convertToMessage(msg));
        }
    }

    public void d(@Nullable Throwable e) {
        d(e, null);
    }

    public void d(@Nullable Throwable e, @Nullable String msg) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.d(tag, convertToMessage(msg), e);
        }
    }

    public void w(@Nullable String msg) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.w(tag, convertToMessage(msg));
        }
    }

    public void w(@Nullable Throwable e) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.w(tag, null, e);
        }
    }

    public void w(@Nullable Throwable e, @Nullable String msg) {
        if (MIN_LEVEL <= Log.DEBUG) {
            Log.w(tag, convertToMessage(msg), e);
        }
    }

    public void e(@Nullable String msg) {
        e(null, msg);
    }

    public void e(@Nullable Throwable e) {
        e(e, null);
    }

    public void e(@Nullable Throwable e, @Nullable String msg) {
        if (MIN_LEVEL <= Log.ERROR) {
            Log.e(tag, convertToMessage(msg), new BaseException("Handled error", e));
        }
    }

    @NonNull
    private String convertToMessage(@Nullable String msg) {
        return msg == null ? "" : msg;
    }

    @NonNull
    private String convertToMessage(@Nullable String msg, @Nullable Throwable throwable) {
        StringBuilder message = new StringBuilder();
        if (!TextUtils.isEmpty(msg)) {
            message.append(msg);
        }
        if (throwable != null) {
            message.append(" : ")
                    .append(throwable.getClass()
                            .getSimpleName());
            message.append(" : ")
                    .append(throwable.getMessage());
        }
        return message.toString();
    }
}
