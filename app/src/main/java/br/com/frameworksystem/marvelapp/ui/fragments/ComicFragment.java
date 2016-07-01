package br.com.frameworksystem.marvelapp.ui.fragments;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.frameworksystem.marvelapp.Mock;
import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.ui.adapter.ComicAdapter;

/**
 * Created by fo050220 on 30/06/2016.
 */
public class ComicFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    private String nameComic;

    public static ComicFragment newInstancia(String nameComic) {
        ComicFragment comicFragment = new ComicFragment();
        comicFragment.nameComic = nameComic;

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

        comicAdapter = new ComicAdapter(getActivity(), Mock.getCommics(nameComic), recyclerView);

        recyclerView.setAdapter(comicAdapter);
    }
}