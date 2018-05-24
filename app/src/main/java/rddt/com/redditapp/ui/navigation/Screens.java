package rddt.com.redditapp.ui.navigation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static rddt.com.redditapp.ui.navigation.Screens.IMAGE_FRAGMENT;
import static rddt.com.redditapp.ui.navigation.Screens.MAIN_ACTIVITY;
import static rddt.com.redditapp.ui.navigation.Screens.TOP_LIST_FRAGMENT;

@Retention(SOURCE)
@StringDef({
        MAIN_ACTIVITY,
        IMAGE_FRAGMENT,
        TOP_LIST_FRAGMENT
})
public @interface Screens {

    //Activities
    String MAIN_ACTIVITY = "MAIN_ACTIVITY";

    //Fragments
    String IMAGE_FRAGMENT = "IMAGE_FRAG";
    String TOP_LIST_FRAGMENT = "TOP_LIST_FRAG";

    // Android
    String SETTINGS_PERMISSIONS = "SETTINGS_PERMISSIONS";
}
