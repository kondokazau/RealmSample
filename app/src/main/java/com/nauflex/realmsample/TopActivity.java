package com.nauflex.realmsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nauflex.realmsample.preference.PreferenceActivity;
import com.nauflex.realmsample.realm.RealmActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        setTitle("Menu");

        // Realm初期設定
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public void onClickPreferenceButton(View view) {
        Intent intent = new Intent(TopActivity.this, PreferenceActivity.class);
        startActivity(intent);
    }

    public void onClickRealmButton(View view) {
        Intent intent = new Intent(TopActivity.this, RealmActivity.class);
        startActivity(intent);
    }
}
