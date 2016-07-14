package br.com.frameworksystem.marvelapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.api.ComicApi;
import br.com.frameworksystem.marvelapp.model.Comic;
import br.com.frameworksystem.marvelapp.ui.adapter.ComicAdapter;

/**
 * Created by fo050220 on 30/06/2016.
 */
public class ComicFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    private String nameComic;
    private String idComic;

    public static ComicFragment newInstancia(String nameComic, String idComic) {
        ComicFragment comicFragment = new ComicFragment();
        comicFragment.nameComic = nameComic;
        comicFragment.idComic = idComic;

        return comicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment);
        recyclerView.setLayoutManager(gridLayoutManager);



        getComics();

    }

    private void getComics() {

        final ComicApi comicApi = new ComicApi(getActivity(), this.idComic);

        comicApi.comics(new ComicApi.OnComicsListener() {
            @Override
            public void onComics(final List<Comic> comics, int errorCode) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (comics != null){
                            comicAdapter = new ComicAdapter(getActivity(), comics, recyclerView);

                            recyclerView.setAdapter(comicAdapter);
                        }else {
                            Toast.makeText(getActivity(), R.string.msg_erro_generic, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
