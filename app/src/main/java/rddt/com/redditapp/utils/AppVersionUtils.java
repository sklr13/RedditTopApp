package rddt.com.redditapp.utils;

import android.os.Build;
import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

public class AppVersionUtils {

    public static <T> T switchLollipop(@NonNull Callable<T> funcNotLollipop,
                                       @NonNull Callable<T> funcLollipop)
            throws Exception {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                ? funcLollipop.call()
                : funcNotLollipop.call();
    }
}