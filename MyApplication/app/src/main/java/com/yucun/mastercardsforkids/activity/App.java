package com.yucun.mastercardsforkids.activity;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by yucunli on 2015-09-26.
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "L7fz5E8GBRwomEclplEO97Nkb9rIdOPsUtixJBCN", "Mpi7TiPH7AQeaAJIgFeHknnSA81F6DCqt27SBvlp"); // Your Application ID and Client Key are defined elsewhere
    }
}
