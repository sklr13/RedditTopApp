package rddt.com.redditapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import rddt.com.redditapp.RedditApplication;
import rddt.com.redditapp.di.module.ActivityModule;
import rddt.com.redditapp.di.module.AppModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
})

public interface AppComponent extends AndroidInjector<RedditApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<RedditApplication> {

    }
}