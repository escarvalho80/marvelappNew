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
import br.com.frameworksystem.marvelapp.api.CharacterApi;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.ui.adapter.CharacterAdapter;

/**
 * Created by User on 23/06/2016.
 */
public class CharacterFragment extends Fragment {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private Boolean isComic = false;

    public static Fragment newInstancia(Boolean isComic) {

        CharacterFragment characterFragment = new CharacterFragment();
        characterFragment.isComic = isComic;
        return characterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment);
        recyclerView.setLayoutManager(layoutManager);

        getCharacters();

    }

    private void getCharacters(){
        final CharacterApi characterApi = new CharacterApi(getActivity());
        characterApi.characters(new CharacterApi.OnCharactersListener(){

            @Override
            public void onCharacters(final List<Character> characters, int errorCode) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (characters != null){
                            characterAdapter = new CharacterAdapter(getActivity(), characters, recyclerView, isComic);
                            recyclerView.setAdapter(characterAdapter);
                        }else{
                            Toast.makeText(getActivity(), R.string.msg_erro_generic, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

}
