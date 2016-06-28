package com.nauflex.realmsample.preference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nauflex.realmsample.R;

public class PreferenceActivity extends AppCompatActivity {

    Spinner pcModelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        // タイトルの設定
        setTitle("Preference");

        // 保有PCマスタ情報の登録
        registMasterPc();

    }

    // 保有PCマスタ情報の登録
    private void registMasterPc() {
        String pcMasterInfo[][] = {
                {"15-af146AU", "2016/01/01", "2016/03/31", "故障のため廃棄"},
                {"15-ab251TU", "2016/01/01", "", ""},
                {"Inspiron 11 3000", "2016/04/01", "", ""},
                {"PAZ15VB-SNA-K", "2016/04/01", "", ""},
                {"LB-F571X-SSD2-KK", "2016/04/01", "", ""}
        };

        PcPreferenceModel model = new PcPreferenceModel(getApplicationContext());

        for (String[] item: pcMasterInfo) {
            model.setPcMasterRecord(item);
        }

    }

}
