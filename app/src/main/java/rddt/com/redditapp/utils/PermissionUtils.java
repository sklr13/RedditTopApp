package rddt.com.redditapp.utils;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class help us resolve permission issues on Chinese devices
 */
public final class PermissionUtils {

    private static final Logger logger = Logger.create(PermissionUtils.class);

    private PermissionUtils() {
        //none
    }

    public static void showPermissionSettings(@NonNull Activity activity) {
        boolean miui = isMIUI();
        logger.d("MIUI: " + miui);
        if (miui) {
            try {
                // MIUI 8
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter",
                        "com.miui.permcenter.permissions.PermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", activity.getPackageName());
                activity.startActivity(localIntent);
                logger.d("MIUI 8 ");
                return;
            } catch (Exception e) {
                try {
                    // MIUI 5/6/7
                    Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    localIntent.setClassName("com.miui.securitycenter",
                            "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    localIntent.putExtra("extra_pkgname", activity.getPackageName());
                    activity.startActivity(localIntent);
                    logger.d("MIUI < 8 ");
                    return;
                } catch (Exception e1) {
                    logger.d(e1, "can't find permission settings activity");
                }
            }
        }
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    private static boolean isMIUI() {
        String device = Build.MANUFACTURER;
        if ("Xiaomi".equals(device)) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(new File(Environment.getRootDirectory(),
                        "build.prop")));
                return prop.getProperty("ro.miui.ui.version.code", null) != null
                        || prop.getProperty("ro.miui.ui.version.name", null) != null
                        || prop.getProperty("ro.miui.internal.storage", null) != null;
            } catch (IOException e) {
                logger.d(e, "can't find properties");
            }
        }
        return false;
    }
}