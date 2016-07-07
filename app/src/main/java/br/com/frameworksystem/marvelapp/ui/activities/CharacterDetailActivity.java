package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LogPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.bd.SQLiteHelper;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.service.MP3Player;
import br.com.frameworksystem.marvelapp.service.MP3Service;
import br.com.frameworksystem.marvelapp.util.Constante;

/**
 * Created by User on 28/06/2016.
 */
public class CharacterDetailActivity extends PrincipalActivity {

    private Character character;
    private SQLiteDatabase db;
    private List<Character> characterList = new ArrayList<Character>();

    private MP3Player mp3Player;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mp3Player = ((MP3Service.MP3Binder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp3Player = null;
        }
    };

    private void connectService(){
        Intent intent = new Intent(this, MP3Service.class);
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unbindService(serviceConnection);
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectService();
    }

    //no oncreate se define o layout de activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = SQLiteHelper.getDataBase(this);

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

        List<Character> characterList = recuperaDado();

        Button play = (Button) findViewById(R.id.player);
        play.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp3Player.play("http://www.instantsfun.es/audio/acdc.mp3");
                    }
                }
        );

        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp3Player.stop();
                    }
                }

        );


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
        } else if (item.getItemId() == R.id.action_favorito) {
            item.setIcon(R.drawable.ic_action_favoritado);
            registerRemoveFavorito();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registerRemoveFavorito() {

        if (!db.isOpen()) {
            db = SQLiteHelper.getDataBase(this);
        }

        if (character.getFavorite() == 1){
            db.delete(Constante.CHARATER_TABLE, "name", new String[]{character.getName()});
        }else{

            ContentValues dados = new ContentValues();

            dados.put("name", character.getName());
            dados.put("description", character.getDescription());
            dados.put("favorite", 1);

            long retorno = db.insert(Constante.CHARATER_TABLE, null, dados);
            if(retorno > 0){
                Log.i("OK", "Inseriu no banco");
            }

        }

    }

    private List<Character> recuperaDado(){

        Cursor cursor ;
        String where = "name = ? ";
        String[] colunas = new String[] {"id", "name", "description", "favorite"};
        String argumentos[] = new String[] {character.getName()};
        cursor = db.query(Constante.CHARATER_TABLE, colunas, where, argumentos, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        while (cursor.moveToNext()){
            characterList.add(montaDadoCharater(cursor));
        }

        db.close();
        return characterList;
    }

    private Character montaDadoCharater(Cursor cursor){

        Character character = new Character();

        character.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
        character.setName(cursor.getString(cursor.getColumnIndex("name")));
        character.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        character.setFavorite((long) cursor.getInt(cursor.getColumnIndex("favorite")));

        return character;
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
