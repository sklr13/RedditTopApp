package rddt.com.redditapp.mvp.contract.activity;

import rddt.com.redditapp.mvp.BaseContract;

public interface MainActivityContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<View> {

        void showMainFragment();
    }
}