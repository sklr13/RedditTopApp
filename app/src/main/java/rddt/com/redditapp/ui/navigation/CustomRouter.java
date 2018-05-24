package rddt.com.redditapp.ui.navigation;

import android.support.annotation.Nullable;

import ru.terrakok.cicerone.Router;

public class CustomRouter extends Router {

    @Override
    public void navigateTo(@Screens String screenKey) {
        super.navigateTo(screenKey);
    }

    @Override
    public void navigateTo(@Screens String screenKey, @Nullable Object data) {
        super.navigateTo(screenKey, data);
    }

    @Override
    public void newScreenChain(@Screens String screenKey) {
        super.newScreenChain(screenKey);
    }

    @Override
    public void newScreenChain(@Screens String screenKey, @Nullable Object data) {
        super.newScreenChain(screenKey, data);
    }

    @Override
    public void newRootScreen(@Screens String screenKey) {
        super.newRootScreen(screenKey);
    }

    @Override
    public void newRootScreen(@Screens String screenKey, @Nullable Object data) {
        super.newRootScreen(screenKey, data);
    }

    @Override
    public void replaceScreen(@Screens String screenKey) {
        super.replaceScreen(screenKey);
    }

    @Override
    public void replaceScreen(@Screens String screenKey, @Nullable Object data) {
        super.replaceScreen(screenKey, data);
    }

    @Override
    public void backTo(@Screens String screenKey) {
        super.backTo(screenKey);
    }
}