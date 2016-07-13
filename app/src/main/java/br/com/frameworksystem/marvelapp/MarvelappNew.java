package br.com.frameworksystem.marvelapp;

import android.app.Application;
import android.content.Context;

import br.com.frameworksystem.marvelapp.api.AuthInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User on 01/07/2016.
 */
public class MarvelappNew extends Application {

    private OkHttpClient okHttpClient;
    private final int cacheSize = 10 * 1024 * 1024;
    private static final String PUBLIC_KEY = "b488574c9d350227e2fb420fc1bc0557";
    private static final String PRIVATE_KEY = "eda55fe1d9a30c52fc3b53bd62afb577ada47420";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-BlackItalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        createOkHttpClient();

    }

    public void createOkHttpClient(){
        Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new AuthInterceptor(PUBLIC_KEY, PRIVATE_KEY));
        builder.cache(cache);

        okHttpClient = builder.build();
    }

    public static MarvelappNew getInstance(Context context){
        return (MarvelappNew)context.getApplicationContext();
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

}
