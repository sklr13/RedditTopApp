package rddt.com.redditapp.ui.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import rddt.com.redditapp.ui.activity.MainActivity;
import rddt.com.redditapp.utils.Logger;
import rddt.com.redditapp.utils.PermissionUtils;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

public abstract class BaseNavigator extends SupportAppNavigator {

    private final Logger logger = Logger.create(this);

    @NonNull
    private final FragmentActivity activity;

    @IdRes
    private final int containerId;

    BaseNavigator(@NonNull FragmentActivity activity, @IdRes int containerId) {
        super(activity, containerId);
        this.activity = activity;
        this.containerId = containerId;
    }

    @Override
    protected Intent createActivityIntent(@Nullable Context context,
                                          @Screens String screenKey,
                                          @Nullable Object data) {
        switch (screenKey) {
            case Screens.MAIN_ACTIVITY:
                return new Intent(context, MainActivity.class);
            default:
                return null;
        }
    }

    @Override
    protected void setupFragmentTransactionAnimation(Command command,
                                                     Fragment currentFragment,
                                                     Fragment nextFragment,
                                                     FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void applyCommand(Command command) {
        if (command instanceof Forward) {
            Forward forward = (Forward) command;
            String screenKey = forward.getScreenKey();
            if (Screens.SETTINGS_PERMISSIONS.equals(screenKey)) {
                PermissionUtils.showPermissionSettings(activity);
                return;
            }
        }
        super.applyCommand(command);
    }

    @Override
    protected void unknownScreen(Command command) {
        StringBuilder message = new StringBuilder();
        message.append(" class: ")
                .append(command.getClass()
                        .getSimpleName());
        if (command instanceof BackTo) {
            BackTo backToCommand = (BackTo) command;
            message.append(" screenKey: ")
                    .append(backToCommand.getScreenKey());
        } else if (command instanceof Forward) {
            Forward forwardCommand = (Forward) command;
            message.append(" screenKey: ")
                    .append(forwardCommand.getScreenKey());
            message.append(" data: ")
                    .append(forwardCommand.getTransitionData());
        } else if (command instanceof Replace) {
            Replace replaceCommand = (Replace) command;
            message.append(" screenKey: ")
                    .append(replaceCommand.getScreenKey());
            message.append(" data: ")
                    .append(replaceCommand.getTransitionData());
        } else if (command instanceof SystemMessage) {
            SystemMessage systemMessageCommand = (SystemMessage) command;
            message.append(" message: ")
                    .append(systemMessageCommand.getMessage());
        }
        logger.e("unknownScreen: " + message.toString());
    }

    @Nullable
    public Fragment getTopFragment() {
        return activity.getSupportFragmentManager()
                .findFragmentById(containerId);
    }
}
