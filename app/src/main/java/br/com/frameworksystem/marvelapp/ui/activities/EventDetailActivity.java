package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Event;
import br.com.frameworksystem.marvelapp.model.MarvelImage;
import br.com.frameworksystem.marvelapp.service.IMarvelappService;
import br.com.frameworksystem.marvelapp.service.LogService;
import br.com.frameworksystem.marvelapp.service.MarvelappService;

/**
 * Created by User on 28/06/2016.
 */
public class EventDetailActivity extends PrincipalActivity {

    private Event event;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.item_event_detail);

        Intent intent = new Intent(this, LogService.class);
//        intent.putExtra()


        if (getIntent().hasExtra("event")) {
            event = (Event) getIntent().getSerializableExtra("event");
        }

        WebView webView = (WebView) findViewById(R.id.webview_event_detail);
        webView.loadUrl(event.getUrls().get(0).url);
    }
}
