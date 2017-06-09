package fr.unice.polytech.capsophia.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihane on 5/9/17.
 */

public class Category extends RealmObject {
    public Category() {
    }

    @PrimaryKey
    private String name;
    private String image;
    private boolean isEventCategory = false;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    private RealmList<Shop> shops;
    private RealmList<Event> events;

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
        this.shops =new RealmList<>();
        this.events = new RealmList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Shop> getShops() {
        return shops;
    }

    public void setShops(RealmList<Shop> shops) {
        this.shops = shops;
    }

    public RealmList<Event> getEvents() {
        return events;
    }

    public void setEvents(RealmList<Event> events) {
        this.events = events;
    }

    public boolean isEventCategory() {
        return isEventCategory;
    }

    public void setEventCategory(boolean eventCategory) {
        isEventCategory = eventCategory;
    }
}
