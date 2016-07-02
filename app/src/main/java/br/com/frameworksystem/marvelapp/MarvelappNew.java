package br.com.frameworksystem.marvelapp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User on 01/07/2016.
 */
public class MarvelappNew extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Italic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....
    }
}
