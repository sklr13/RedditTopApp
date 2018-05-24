package rddt.com.redditapp;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;
import rddt.com.redditapp.di.component.DaggerAppComponent;
import rddt.com.redditapp.utils.Logger;

public class RedditApplication extends DaggerApplication {

    private final Logger logger = Logger.create(this);

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> logger.e(throwable, "Error without subscriber"));
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }
}
