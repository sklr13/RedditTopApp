package rddt.com.redditapp.ui.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import rddt.com.redditapp.R;
import rddt.com.redditapp.ui.activity.MainActivity;
import rddt.com.redditapp.ui.fragment.ImageFragment;
import rddt.com.redditapp.ui.fragment.RedditTopListFragment;

public class MainActivityNavigator extends BaseNavigator {

    public MainActivityNavigator(@NonNull MainActivity activity) {
        super(activity, R.id.fragment_container);
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.IMAGE_FRAGMENT:
                return ImageFragment.newInstance(data);
            case Screens.TOP_LIST_FRAGMENT:
                return RedditTopListFragment.newInstance();
            default:
                return null;
        }
    }
}