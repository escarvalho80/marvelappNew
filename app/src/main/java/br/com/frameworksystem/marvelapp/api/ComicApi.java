package br.com.frameworksystem.marvelapp.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import br.com.frameworksystem.marvelapp.model.Comic;
import br.com.frameworksystem.marvelapp.model.MarvelCollection;
import br.com.frameworksystem.marvelapp.model.MarvelResponse;
import br.com.frameworksystem.marvelapp.util.Constante;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 12/07/2016.
 */
public class ComicApi extends BaseApi {
    public ComicApi(Context context) {
        super(context);
    }

    public void comics(final OnComicsListener onComicsListener){
        Request request = new Request.Builder()
                .url(Constante.API_COMICS)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onComicsListener != null){
                    onComicsListener.onComics(null,500);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response == null){
                    return;
                }

                if (response.isSuccessful()){
                    Gson gson = new Gson();
                    MarvelResponse<MarvelCollection<Comic>> comics = gson.fromJson(
                            response.body().charStream(),
                            new TypeToken<MarvelResponse<MarvelCollection<Comic>>>() {
                            }.getType());

                    onComicsListener.onComics(comics.data.results, 0);
                }else {
                    onComicsListener.onComics(null, response.code());
                }
            }
        });
    }

    public interface OnComicsListener {
        void onComics(List<Comic> comics, int errorCode);
    }
}
