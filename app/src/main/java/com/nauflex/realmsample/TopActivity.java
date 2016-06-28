package com.nauflex.realmsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nauflex.realmsample.preference.PreferenceActivity;

public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        setTitle("Menu");
    }

    public void onClickPreferenceButton(View view) {
        Intent intent = new Intent(TopActivity.this, PreferenceActivity.class);
        startActivity(intent);
    }
}
