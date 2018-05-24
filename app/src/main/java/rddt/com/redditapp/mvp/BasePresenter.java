package rddt.com.redditapp.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import rddt.com.redditapp.mvp.exceptions.ViewNotAttachedException;

public class BasePresenter<V extends BaseContract.View>
        implements BaseContract.Presenter<V> {

    @NonNull
    private final Scheduler backgroundScheduler;

    @NonNull
    private final Scheduler foregroundScheduler;

    @Nullable
    private V view;

    @NonNull
    private CompositeDisposable compositeDisposable;

    protected BasePresenter(@NonNull Scheduler backgroundScheduler,
                            @NonNull Scheduler foregroundScheduler) {
        this.backgroundScheduler = backgroundScheduler;
        this.foregroundScheduler = foregroundScheduler;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        if (isViewAttached()) {
            getView().onError(throwable);
        }
    }

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.view = null;
        compositeDisposable.clear();
    }

    @NonNull
    @Override
    public final V getView() {
        if (view == null) {
            throw new ViewNotAttachedException();
        }
        return view;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @NonNull
    private <R> SingleSource<R> background(@NonNull Single<R> upstream) {
        return upstream.subscribeOn(backgroundScheduler)
                .observeOn(foregroundScheduler);
    }

    @NonNull
    private Completable background(@NonNull Completable upstream) {
        return upstream.subscribeOn(backgroundScheduler)
                .observeOn(foregroundScheduler);
    }

    public void subscribe(@NonNull Completable chain, @NonNull Action onSuccess) {
        Disposable disposable = chain.compose(this::background)
                .subscribe(onSuccess, this::onError);
        compositeDisposable.add(disposable);
    }

    public <T> void subscribe(@NonNull Single<T> chain, @NonNull Consumer<T> onSuccess) {
        Disposable disposable = chain.compose(this::background)
                .subscribe(onSuccess, this::onError);
        compositeDisposable.add(disposable);
    }

}