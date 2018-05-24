package rddt.com.redditapp.di.module.activity;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import rddt.com.redditapp.di.qualifiers.execution.Background;
import rddt.com.redditapp.di.qualifiers.execution.Foreground;
import rddt.com.redditapp.di.scope.ActivityScope;
import rddt.com.redditapp.mvp.contract.activity.MainActivityContract;
import rddt.com.redditapp.mvp.presenter.activity.MainActivityPresenter;
import rddt.com.redditapp.ui.activity.MainActivity;
import rddt.com.redditapp.ui.navigation.BaseNavigator;
import rddt.com.redditapp.ui.navigation.CustomRouter;
import rddt.com.redditapp.ui.navigation.MainActivityNavigator;

@Module
public class MainActivityModule {

    @ActivityScope
    @Provides
    BaseNavigator provideNavigator(MainActivity activity) {
        return new MainActivityNavigator(activity);
    }

    @ActivityScope
    @Provides
    MainActivityContract.Presenter providePresenter(@Background Scheduler backgroundScheduler,
                                                    @Foreground Scheduler foregroundScheduler,
                                                    CustomRouter customRouter) {
        return new MainActivityPresenter(backgroundScheduler, foregroundScheduler, customRouter);
    }

    @ActivityScope
    @Provides
    Activity provideActivity(MainActivity activity) {
        return activity;
    }
}
