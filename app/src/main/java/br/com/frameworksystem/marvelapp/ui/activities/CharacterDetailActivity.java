package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;

/**
 * Created by User on 28/06/2016.
 */
public class CharacterDetailActivity extends PrincipalActivity {

    private Character character;

    //no oncreate se define o layout de activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setando o layout
        setContentView(R.layout.activity_character_detail);
        character = (Character) getIntent().getSerializableExtra("character");

        //inserindo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //coloca titulo no topo da tela
        setTitle(character.getName());
        //mesmo comportamento da action bar, mas continua customizavel
        setSupportActionBar(toolbar);
        // seta de voltar para a home - recria a pilha - volta para a parent a activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.character_imagem);
        Picasso.with(this).load(character.getThumbnailUrl()).centerCrop().resize(400, 400).into(imageView);

        TextView textView = (TextView) findViewById(R.id.character_descricao);
        textView.setText(character.getDescription());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.characte_detail, menu);

        ShareCompat.IntentBuilder intent = ShareCompat.IntentBuilder.from(this).
                setText(character.getDescription()).setType("text/plain");
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));

        actionProvider.setShareIntent(intent.getIntent());

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            share();
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        //validando se o usuario clicou no botao share
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, character.getName());
        intent.putExtra(Intent.EXTRA_TEXT, character.getDescription());

        intent.setType("text/plain");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
