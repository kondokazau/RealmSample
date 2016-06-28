package com.nauflex.realmsample.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nauflex.realmsample.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    private static final String TAG_REALM_ACTIVITY = "realm-activity";
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        realm = Realm.getDefaultInstance();

        // タイトルの設定
        setTitle("Realm");

        // データ全削除
        deleteData(-1);

        // データの登録
        addData();

        // データの更新
        updateData();

        // データの検索および表示
        displayFindResults();
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    // データの登録
    private void addData() {
        // 登録用のデータ作成
        PcRealmModel[] modelArray = makeData();

        realm.beginTransaction();

        for(PcRealmModel model: modelArray) {
            realm.copyToRealm(model);
        }

        realm.commitTransaction();

        Log.d(TAG_REALM_ACTIVITY, String.format("%d data is registered.", modelArray.length));
    }

    // データの更新
    private void updateData() {
        // 更新データの検索
        RealmResults<PcRealmModel> results = findPc(4);

        // 更新の設定
        PcRealmModel target = new PcRealmModel();
        target.pcId = results.get(0).pcId;
        target.modelName = results.get(0).modelName;
        target.startDate = results.get(0).startDate;
        target.endDate = results.get(0).endDate;
        target.remarks = "Realm更新";

        // 更新の実行
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(target);
        realm.commitTransaction();
    }

    // データの削除
    private void deleteData(int pcId) {
        // 削除データの検索
        final RealmResults<PcRealmModel> results = findPc(pcId);
        int cnt = results.size();

        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();

        Log.d(TAG_REALM_ACTIVITY, String.format("%d data is deleted.", cnt));
    }

    // データの検索
    private RealmResults<PcRealmModel> findPc(int number) {
        RealmQuery<PcRealmModel> query = realm.where(PcRealmModel.class);
        if(number >= 0) {
            query.equalTo("pcId", number);
        }
        RealmResults<PcRealmModel> results = query.findAll();

        return results;
    }

    // データの検索および表示
    private void displayFindResults() {
        // 全データの検索
        RealmResults<PcRealmModel> results = findPc(-1);

        // 検索結果から文字列連結
        String combinedString = "";
        for(PcRealmModel result: results) {
            combinedString += String.format("%d %s %tY/%tm/%td %tY/%tm/%td %s\n",
                    result.pcId,
                    result.modelName,
                    result.startDate, result.startDate, result.startDate,
                    result.endDate, result.endDate, result.endDate,
                    result.remarks);
        }

        // 連結した文字列を表示
        TextView tv = (TextView) findViewById(R.id.result);
        tv.setText(combinedString);
    }

    // 登録用のデータ作成
    private PcRealmModel[] makeData() {
        PcRealmModel[] modelArray = new PcRealmModel[5];
        modelArray[0] = new PcRealmModel();
        modelArray[0].pcId = 0;
        modelArray[0].modelName = "15-af146AU";
        modelArray[0].startDate = convertDateString("2016/01/01");
        modelArray[0].endDate = convertDateString("2016/03/31");
        modelArray[0].remarks = "故障のため廃棄";

        modelArray[1] = new PcRealmModel();
        modelArray[1].pcId = 1;
        modelArray[1].modelName = "15-ab251TU";
        modelArray[1].startDate = convertDateString("2016/01/01");

        modelArray[2] = new PcRealmModel();
        modelArray[2].pcId = 2;
        modelArray[2].modelName = "Inspiron 11 3000";
        modelArray[2].startDate = convertDateString("2016/04/01");

        modelArray[3] = new PcRealmModel();
        modelArray[3].pcId = 3;
        modelArray[3].modelName = "PAZ15VB-SNA-K";
        modelArray[3].startDate = convertDateString("2016/04/01");

        modelArray[4] = new PcRealmModel();
        modelArray[4].pcId = 4;
        modelArray[4].modelName = "LB-F571X-SSD2-KK";
        modelArray[4].startDate = convertDateString("2016/04/01");

        return modelArray;
    }

    // 日付の文字列をDate型に変換
    private Date convertDateString(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.parse(input);

        } catch(Exception e) {
            return null;
        }
    }
}
