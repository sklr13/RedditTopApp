package rddt.com.redditapp.mvp;

import android.support.annotation.NonNull;

public interface BaseContract {

    interface View {
        void onError(@NonNull Throwable throwable);
    }

    interface Presenter<V extends View> {
        void onError(@NonNull Throwable throwable);

        void attachView(@NonNull V view);

        void detachView();

        @NonNull
        V getView();

        boolean isViewAttached();
    }
}