package rddt.com.redditapp.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import rddt.com.redditapp.mvp.BaseContract;
import rddt.com.redditapp.ui.navigation.BaseNavigator;
import ru.terrakok.cicerone.NavigatorHolder;

public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends DaggerAppCompatActivity
        implements BaseContract.View {

    @Inject
    P presenter;

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    BaseNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        getPresenter().attachView((V) this);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onStop() {
        getPresenter().detachView();
        super.onStop();
    }

    @Override
    public void onError(@NonNull Throwable trowable) {
        Toast.makeText(this, trowable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @NonNull
    protected P getPresenter() {
        return presenter;
    }

    @LayoutRes
    protected abstract int getLayoutId();
}