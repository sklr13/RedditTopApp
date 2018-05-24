package rddt.com.redditapp.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import rddt.com.redditapp.mvp.BaseContract;
import rddt.com.redditapp.utils.Logger;

public abstract class BaseFragment<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends DaggerFragment
        implements BaseContract.View {

    private final Logger logger = Logger.create(this);

    @Inject
    P presenter;

    @Nullable
    private Unbinder butterKnifeUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().attachView((V) this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        if (butterKnifeUnbinder != null) {
            butterKnifeUnbinder.unbind();
        }
        getPresenter().detachView();
        super.onDestroyView();
    }

    @NonNull
    protected P getPresenter() {
        return presenter;
    }

    @Nullable
    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null && isVisible()) {
            getBaseActivity().onError(throwable);
        } else {
            logger.d("activity in null or not visible");
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();
}