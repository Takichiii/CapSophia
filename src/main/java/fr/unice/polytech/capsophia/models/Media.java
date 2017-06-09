package fr.unice.polytech.capsophia.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihane on 31/05/2017.
 */

public class Media extends RealmObject {
    @PrimaryKey
    private String url;

    public Media(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Media(String url){
        this.url= url;

    }
}
