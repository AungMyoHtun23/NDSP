package com.example.ndsp.Language;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // override the base context of application to update default locale for the application
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base,"en"));
    }
}
