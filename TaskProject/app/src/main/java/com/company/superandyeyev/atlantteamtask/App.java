package com.company.superandyeyev.atlantteamtask;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by DIMON on 16.10.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
