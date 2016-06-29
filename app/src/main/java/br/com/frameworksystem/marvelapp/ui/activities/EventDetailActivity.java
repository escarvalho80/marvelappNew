package br.com.frameworksystem.marvelapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Event;

/**
 * Created by User on 28/06/2016.
 */
public class EventDetailActivity extends AppCompatActivity {

    private Event event;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.item_event_detail);

        if (getIntent().hasExtra("event")) {
            event = (Event) getIntent().getSerializableExtra("event");
        }

        WebView webView = (WebView) findViewById(R.id.webview_event_detail);
        webView.loadUrl(event.getUrl());
    }
}
