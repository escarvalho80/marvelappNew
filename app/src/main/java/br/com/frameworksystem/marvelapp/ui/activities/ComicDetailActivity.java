package br.com.frameworksystem.marvelapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Comic;

/**
 * Created by User on 29/06/2016.
 */
public class ComicDetailActivity extends AppCompatActivity {

    private Comic comic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comic_detail);
        comic = (Comic) getIntent().getSerializableExtra("comic");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(comic.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ImageView imageView = (ImageView) findViewById(R.id.comic_imagem);
        Picasso.with(this).load(comic.getThumbnailUrl()).centerCrop().resize(400, 400).into(imageView);

        TextView textView = (TextView) findViewById(R.id.comic_descricao);
        textView.setText(comic.getDescription());

    }

}
