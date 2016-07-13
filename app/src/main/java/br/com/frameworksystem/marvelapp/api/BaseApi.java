package br.com.frameworksystem.marvelapp.api;

import android.content.Context;

import br.com.frameworksystem.marvelapp.MarvelappNew;
import okhttp3.OkHttpClient;

/**
 * Created by User on 12/07/2016.
 */
public class BaseApi {

    protected final Context context;
    protected final OkHttpClient okHttpClient;

    public BaseApi(Context context) {
        this.context = context;
        this.okHttpClient = MarvelappNew.getInstance(context).getOkHttpClient();
    }
}
