package com.nauflex.realmsample.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by doublek on 2016/06/26.
 */
public class PcPreferenceModel {

    private String PC_MASTER_NAME = "PC_MASTER";
    private String LAST_PC_MASTER_NUMBER_KEY = "last_pc_master_number";
    private String PREFERENCENAME = "preferenceSample";
    private SharedPreferences pr;
    private SharedPreferences.Editor editor;

    // デフォルトコンストラクタ
    public PcPreferenceModel(Context context) {
        pr = context.getSharedPreferences(PREFERENCENAME, Context.MODE_PRIVATE);
        editor = pr.edit();
    }

    // PCマスタの最後のカウンタを取得
    private String getLastPcMasterNumber() {
        return pr.getString(LAST_PC_MASTER_NUMBER_KEY, "0");
    }

    // PCマスタの最後のカウンタを登録
    private void setLastPcMasterNumber(String number) {
        editor.putString(LAST_PC_MASTER_NUMBER_KEY, number);
        editor.commit();
    }

    // PCマスタのレコード取得
    public String[] getPcMasterRecord(String number) {
        String recordString = pr.getString(PC_MASTER_NAME+number, null);

        if (recordString == null) {
            return null;
        }

        return recordString.split("\t");
    }
    
    // PCマスタのレコード登録
    public void setPcMasterRecord(String[] content) {
        // 登録するレコード内容の文字列連結
        String combinedContent = "";
        for (String item: content) {
            if (combinedContent.length() > 0) {
                combinedContent += "\t";
            }

            combinedContent += item;
        }

        // 登録されている最後のカウンタを取得
        int lastNumber = Integer.valueOf(getLastPcMasterNumber());
        String registNumberKey = String.valueOf(lastNumber + 1);

        editor.putString(registNumberKey, combinedContent);
        editor.commit();

        // 登録されている最後のカウンタをカウントアップ
        setLastPcMasterNumber(registNumberKey);
    }
}
