package rddt.com.redditapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rddt.com.redditapp.R;
import rddt.com.redditapp.mvp.contract.activity.MainActivityContract;
import rddt.com.redditapp.ui.BaseActivity;

public class MainActivity
        extends BaseActivity<MainActivityContract.View, MainActivityContract.Presenter>
        implements MainActivityContract.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().showMainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
