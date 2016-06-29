package br.com.frameworksystem.marvelapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.ui.fragments.CharacterFragment;

/**
 * Created by User on 28/06/2016.
 */
public class CharacterDetailActivity extends AppCompatActivity{

    private Character character;

    //no oncreate se define o layout de activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setando o layout
        setContentView(R.layout.activity_character_detail);

        //inserindo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //mesmo comportamento da action bar, mas continua customizavel
        setSupportActionBar(toolbar);

        // seta de voltar para a home - recria a pilha - volta para a parent a activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        character = (Character) getIntent().getSerializableExtra("character");

        //coloca titulo no topo da tela
        setTitle(character.getName());

        ImageView imageView = (ImageView) findViewById(R.id.character_imagem);
        Picasso.with(this).load(character.getThumbnailUrl()).centerCrop().resize(400,400).into(imageView);

        TextView textView = (TextView) findViewById(R.id.character_descricao);

        textView.setText(character.getDescription());

    }
}
