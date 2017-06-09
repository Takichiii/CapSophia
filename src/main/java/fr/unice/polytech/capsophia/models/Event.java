package fr.unice.polytech.capsophia.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jihane on 5/7/17.
 */

public class Event extends RealmObject  {

    @PrimaryKey
    public String name;
    public Category category;
    private String imagePath;
    private String description;
    private Date dateDebut;
    private int duree;
    private boolean saved;

    public Event(String name, Category category, String imagePath, String description, Date dateDebut, int duree) {
        this.name = name;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
