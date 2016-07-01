package br.com.frameworksystem.marvelapp.ui.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Downloader;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.comic_detail, menu);

        ShareCompat.IntentBuilder intent = ShareCompat.IntentBuilder.from(this).
                setText(comic.getDescription()).setType("text/plain");

        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share_comic));

        actionProvider.setShareIntent(intent.getIntent());

//        DownloadManager downloadManager = new ''


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_download){

            Uri uri = Uri.parse(comic.getThumbnailUrl());

            DownloadManager.Request request = new DownloadManager.Request(uri);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            //Set whether this download may proceed over a roaming connection.
            request.setAllowedOverRoaming(false);

            request.setTitle(comic.getTitle());
            //Set a description of this download, to be displayed in notifications (if enabled)
            request.setDescription("Android Data download using DownloadManager.");
            //Set the local destination for the downloaded file to a path within the application's external files directory
            //request.setDestnInExtFilesDir(this, Environment.DIRECTORY_DOWNLOADS,"");

            Intent intent = new Intent();
            intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
            startActivity(intent);



        }

        return super.onOptionsItemSelected(item);
    }
}
