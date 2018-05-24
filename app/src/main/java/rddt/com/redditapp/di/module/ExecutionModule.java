package rddt.com.redditapp.di.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rddt.com.redditapp.di.qualifiers.execution.Background;
import rddt.com.redditapp.di.qualifiers.execution.Foreground;

@Module
class ExecutionModule {

    @Provides
    @Background
    static Scheduler provideBackgroundScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Foreground
    static Scheduler provideForegroundScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
