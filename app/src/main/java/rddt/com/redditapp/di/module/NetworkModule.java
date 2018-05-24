package rddt.com.redditapp.di.module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rddt.com.redditapp.BuildConfig;
import rddt.com.redditapp.data.repository.network.NetworkManager;
import rddt.com.redditapp.data.repository.network.retrofit.NetworkInterface;
import rddt.com.redditapp.di.scope.ActivityScope;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @NonNull
    @Provides
    @ActivityScope
    Interceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }


    @NonNull
    @Provides
    @ActivityScope
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @NonNull
    @Provides
    @ActivityScope
    GsonConverterFactory provideGsonConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        return GsonConverterFactory.create(builder.create());
    }

    @NonNull
    @Provides
    Retrofit.Builder provideRetrofitBuilder(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL);
    }

    @NonNull
    @Provides
    @ActivityScope
    NetworkInterface provideNetworkInterface(OkHttpClient okHttpClient,
                                             Retrofit.Builder builder) {
        return builder.client(okHttpClient)
                .build()
                .create(NetworkInterface.class);
    }


    @NonNull
    @Provides
    @ActivityScope
    NetworkManager provideNetworkManager(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return new NetworkManager(connectivityManager);
    }
}
