package rddt.com.redditapp.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rddt.com.redditapp.RedditApplication;

@Module
public class AppModule {

    @Provides
    @Singleton
    static Context context(RedditApplication application) {
        return application.getApplicationContext();
    }
}
