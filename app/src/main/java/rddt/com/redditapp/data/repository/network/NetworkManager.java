package rddt.com.redditapp.data.repository.network;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;

import io.reactivex.Single;
import rddt.com.redditapp.utils.AppVersionUtils;

import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class NetworkManager {
    @NonNull
    private final ConnectivityManager connectivityManager;

    public NetworkManager(@NonNull ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @NonNull
    public Single<Boolean> isInternetWifeAvailable() {
        return isInternetWifeAvailableBackground(TYPE_WIFI);
    }

    @NonNull
    public Single<Boolean> isInternetMobileAvailable() {
        return isInternetWifeAvailableBackground(TYPE_MOBILE);
    }

    @NonNull
    public Single<Boolean> isInternetAvailable() {
        return  Single.defer(() -> Single.just(true));
    }

    @NonNull
    private Single<Boolean> isInternetWifeAvailableBackground(int networkType) {
        return Single.defer(() -> Single.just(isInternetAvailable(networkType)));
    }

    private boolean isInternetAvailable(int networkType) throws Exception {
        return AppVersionUtils.switchLollipop(() -> isInternetAvailablePreLollipop(networkType),
                () -> isInternetAvailableLollipop(networkType));
    }

    @SuppressWarnings("deprecation")
    private boolean isInternetAvailablePreLollipop(int networkType) {
        return connectivityManager.getNetworkInfo(networkType).isConnected();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean isInternetAvailableLollipop(int networkType) {
        Network[] networkArr = connectivityManager.getAllNetworks();

        for (Network mNetwork : networkArr) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(mNetwork);

            if (networkInfo.getType() == networkType
                    && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }

        return false;
    }
}
