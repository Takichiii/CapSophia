package fr.unice.polytech.capsophia.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihane on 5/9/17.
 */

public class Shop extends RealmObject {
    @PrimaryKey
    public String name;
    public Category category;
    private String logoPath;
    private RealmList<Product> productsList;
    private String description;
    private String telephone;
    private String location;
    private boolean favorited;

    public Shop(String name, Category category, String logoPath, String description, String telephone, String location) {
        this.name = name;
        this.category = category;
        this.logoPath = logoPath;
        this.description = description;
        this.telephone = telephone;
        this.location = location;
        this.productsList = new RealmList<>();
    }


    public void setProductsList(RealmList<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Shop)) {
            return false;
        }
        return ((Shop)obj).name.equals(this.name);
    }


    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public String getDescription() {
        return description;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLocation() {
        return location;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLocation(String vouchers) {
        this.location = vouchers;
    }


    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public Shop() {
    }


}
