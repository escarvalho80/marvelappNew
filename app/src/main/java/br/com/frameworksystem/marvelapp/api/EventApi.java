package br.com.frameworksystem.marvelapp.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.model.Event;
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
public class EventApi extends BaseApi {

    public EventApi(Context context) {
        super(context);
    }

    public void events(final OnEventsListener onEventsListener) {

        Request request = new Request.Builder()
                .url(Constante.API_EVENTS)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onEventsListener != null) {
                    onEventsListener.onEvents(null, 500);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onEventsListener == null) {
                    return;
                }

                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    MarvelResponse<MarvelCollection<Event>> events = gson.fromJson(
                            response.body().charStream(),
                            new TypeToken<MarvelResponse<MarvelCollection<Event>>>() {
                    }.getType());

                    onEventsListener.onEvents(events.data.results, 0);
                } else {
                    onEventsListener.onEvents(null, response.code());
                }
            }
        });


    }

    public interface OnEventsListener {
        void onEvents(List<Event> events, int errorCode);
    }

}
