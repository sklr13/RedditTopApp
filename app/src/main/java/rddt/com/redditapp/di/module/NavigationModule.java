package rddt.com.redditapp.di.module;

import dagger.Module;
import dagger.Provides;
import rddt.com.redditapp.di.scope.ActivityScope;
import rddt.com.redditapp.ui.navigation.CustomRouter;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;

@Module
public class NavigationModule {

    private final Cicerone<CustomRouter> cicerone;

    public NavigationModule() {
        cicerone = Cicerone.create(new CustomRouter());
    }

    @Provides
    @ActivityScope
    CustomRouter provideRouter() {
        return cicerone.getRouter();
    }

    @Provides
    @ActivityScope
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
