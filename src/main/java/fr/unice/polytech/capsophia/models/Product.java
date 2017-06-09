package fr.unice.polytech.capsophia.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihane on 5/9/17.
 */

public class Product extends RealmObject  {
    @PrimaryKey
    private String name;
    private String description;
    private String picturePath;
    private Shop shop;
    private int price;
    private boolean favorited;
    private RealmList<Media> medias = new RealmList<>();

    public RealmList<Media> getMedias() {
        return medias;
    }

    public void setMedias(RealmList<Media> medias) {
        this.medias = medias;
    }

    public Product(String name, String description, String picturePath, Shop shop, int price) {
        this.name = name;
        this.description = description;
        this.picturePath = picturePath;
        this.shop = shop;
        this.price = price;

        this.medias.add(new Media(picturePath));
    }

    public Product() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
