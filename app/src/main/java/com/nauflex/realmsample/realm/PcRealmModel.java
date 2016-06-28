package com.nauflex.realmsample.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by doublek on 2016/06/28.
 */
public class PcRealmModel extends RealmObject {

    // PCのID
    @PrimaryKey
    public int pcId;
    // PCの型番
    @Required
    public String modelName;
    // PCの購入日
    public Date startDate;
    // PCの廃棄日
    public Date endDate;
    // 備考
    public String remarks;

}
