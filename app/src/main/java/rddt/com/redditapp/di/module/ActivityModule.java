package rddt.com.redditapp.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import rddt.com.redditapp.di.module.activity.MainActivityFragmentProviderModule;
import rddt.com.redditapp.di.module.activity.MainActivityModule;
import rddt.com.redditapp.di.scope.ActivityScope;
import rddt.com.redditapp.ui.activity.MainActivity;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            MainActivityFragmentProviderModule.class,
            MainActivityModule.class,
            NavigationModule.class,
            ExecutionModule.class,
            RepositoryModule.class,
            NetworkModule.class
    })
    abstract MainActivity contributesMainActivityInjector();
}
