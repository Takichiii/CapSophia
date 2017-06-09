package fr.unice.polytech.capsophia.util;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import fr.unice.polytech.capsophia.models.Category;
import fr.unice.polytech.capsophia.models.Event;
import fr.unice.polytech.capsophia.models.Media;
import fr.unice.polytech.capsophia.models.Product;
import fr.unice.polytech.capsophia.models.Shop;
import io.realm.DynamicRealmObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by jihane on 28/04/2017.
 */

public class RealmDBHelper {
    Realm realm;

    static RealmDBHelper instance;

    public static RealmDBHelper getInstance(Context context){
        if (instance==null){
            instance = new RealmDBHelper(context);
        }
        return instance;
    }

    public RealmDBHelper(Context context){
        Realm.init(context);
        /*

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
        */
        realm = Realm.getDefaultInstance();
    }

    public <E extends  RealmModel>  void save (final E model){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(model);
            }
        });
    }

    public void setSave(final Event event){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (event.isSaved())
                    event.setSaved(false);
                else
                    event.setSaved(true);

                realm.copyToRealmOrUpdate(event);
            }
        });
    }


    public void setFavorite(final Product product){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (product.isFavorited())
                    product.setFavorited(false);
                else
                    product.setFavorited(true);

                realm.copyToRealmOrUpdate(product);
            }
        });
    }




    public <E extends RealmModel> RealmResults<E> find(Class<E> model){
        return realm.where(model).findAll();
    }

    public <E extends RealmModel> RealmResults<E> findSpecific(Class<E> model, String key, Object isEvent){
        if (isEvent instanceof Boolean)
            return realm.where(model).equalTo(key, Boolean.parseBoolean(isEvent.toString())).findAll();
        else
            return realm.where(model).equalTo(key, isEvent.toString()).findAll();
    }

    public void generateData() throws ParseException {
        //Create 5 categories
        Category category1 = new Category("Mode Femme", "https://media.brandalley.com/2017/06/07/pipe/27846/003brandalley_04_image.jpg");
        Category category2 = new Category("Décoration Maisons Cadeaux", "https://media.brandalley.com/2017/06/07/pipe/27857/011brandalley_05_image.jpg");
        Category category5 = new Category("Mode Homme", "http://media.brandalley.com/2017/06/07/pipe/27831/003brandalley_02_image.jpg");
        Category category4 = new Category("Sport", "http://p6.storage.canalblog.com/62/58/584049/49437386.jpg");
        Category category3 = new Category("Bijouterie", "http://media.brandalley.com/2017/06/07/pipe/27860/003brandalley_03_image.jpg");

        //create 3 shops for each category
        Shop shop7 = new Shop("GLAMOUR PARIS", category1, "https://media.brandalley.com/2017/06/02/pipe/27751/002brandalley_03_image.jpg",  "Description of C&M", "01 02 03 04", "Cannes");
        Shop shop8 = new Shop("MONOMEN", category1, "https://media.brandalley.com/2017/06/02/pipe/27744/002brandalley_06_image.jpg",  "Description of C&M", "01 02 03 04", "Nice");
        Shop shop6 = new Shop("MANOUKIAN", category1, "https://media.brandalley.com/2017/06/07/pipe/27846/003brandalley_04_image.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");
        Shop shop4 = new Shop("Lola Moretti", category1, "https://media.brandalley.com/2017/06/06/pipe/27848/003brandalley_06_image.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");
        Shop shop5 = new Shop("JOLIDON", category1, "https://media.brandalley.com/2017/06/02/pipe/27509/003brandalley_05b_image.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");
        Shop shop3 = new Shop("Ornella Dutti", category1, "https://media.brandalley.com/2017/06/07/pipe/27883/004brandalley_06_image.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");
        Shop shop2 = new Shop("Saint Hilaire", category1, "https://media.brandalley.com/2017/06/01/pipe/27633/005brandalley_06_image.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");
        Shop shop1 = new Shop("Zara", category1, "http://whatruwearing.net/wp-content/uploads/2017/04/zara-online-main.jpg",  "Description of C&M", "01 02 03 04", "Saint Laurent");

        category1.getShops().add(shop1);
        category1.getShops().add(shop2);
        category1.getShops().add(shop3);
        category1.getShops().add(shop4);
        category1.getShops().add(shop5);
        category1.getShops().add(shop6);
        category1.getShops().add(shop7);
        category1.getShops().add(shop8);


        Shop shop9 = new Shop("Vigor", category3, "https://media.brandalley.com/2017/06/07/pipe/27860/003brandalley_03_image.jpg", "Description", "01 02 03 04", "Saint Laurent");
        Shop shop10 = new Shop("Or Bella", category3, "https://media.brandalley.com/2017/06/02/pipe/27832/002brandalley_03_image.jpg", "Description", "01 02 03 04", "Saint Laurent");


        category3.getShops().add(shop9);
        category3.getShops().add(shop10);

        Shop shop11 = new Shop("Cotton Box", category2, "https://media.brandalley.com/2017/06/02/pipe/27761/009brandalley_04a_image.jpg", "Description", "01 02 03 04", "Saint Laurent");
        Shop shop13 = new Shop("Carla Ferreri", category2, "https://media.brandalley.com/2017/06/06/pipe/27839/003brandalley_04_image.jpg", "Description", "01 02 03 04", "Saint Laurent");
        Shop shop12 = new Shop("Premier", category2, "https://media.brandalley.com/2017/06/08/pipe/27734/013brandalley_03_image.jpg", "Description", "01 02 03 04", "Saint Laurent");


        category2.getShops().add(shop11);
        category2.getShops().add(shop12);
        category2.getShops().add(shop13);

        Media media1 = new Media("https://media.brandalley.com/img_rayons/400x400/2017/05/31/931/2502931_2.jpg");
        Media media2 = new Media("http://photos.cdn.fastmag.fr/photos/44/236x354/vua162600032130/_/2/photo.jpg");
        Media media3 = new Media("https://www.futbolemotion.com/fr/acheter/basket/nike/free-hypervenom-iii-fc-fk-college-navy-metallic-gold-blue-fox");

        Media media4 = new Media("https://media.brandalley.com/img_rayons/400x400/128/1289089_1.jpg");
        Media media5 = new Media("https://media.brandalley.com/img_rayons/400x400/128/1289089_3.jpg");
        Media media6 = new Media("https://media.brandalley.com/img_rayons/400x400/128/1289089_4.jpg");

        //create some products in shop 1
        Product product1 = new Product("BIRKENSTOCK", "Arizona Metallic Copper", "https://media.brandalley.com/img_rayons/400x400/128/1289089_2.jpg", shop1, 120);
        Product product2= new Product("Lunettes de soleil", "VL1626 ECAILLE PINK BROWN", "http://photos.cdn.fastmag.fr/photos/44/236x354/vua162600032130/_/2/photo.jpg", shop1, 120);
        Product product3 = new Product("Baskets de sport", "Nike Free Hypervenom iii", "https://www.futbolemotion.com/fr/acheter/basket/nike/free-hypervenom-iii-fc-fk-college-navy-metallic-gold-blue-fox", shop1, 120);


        product1.getMedias().add(media4);
        product1.getMedias().add(media5);
        product1.getMedias().add(media6);

        product2.getMedias().add(media1);
        product2.getMedias().add(media2);
        product3.getMedias().add(media3);

        shop1.getProductsList().add(product1);
        shop1.getProductsList().add(product2);
        shop1.getProductsList().add(product3);


        //shop1.getProductsList().add(product4);
        //save(product1);
        //save(product2);
        //save(product3);
        //save(product4);

        save(category1);
        save(category2);
        save(category3);
        save(category4);
        save(category5);

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

        Category concours = new Category("Concours", "http://www.parquecomercialmendibil.com/images_noticias/grandes/1444834482p.jpg");
        concours.setEventCategory(true);
        Category stages = new Category("Stages", "http://www.parquecomercialmendibil.com/images_noticias/grandes/1444834482p.jpg");
        stages.setEventCategory(true);
        Category jeux = new Category("Jeux", "http://www.parquecomercialmendibil.com/images_noticias/grandes/1444834482p.jpg");
        jeux.setEventCategory(true);

        Event concoursEvent1 = new Event("Grande tombola de l'année", concours, "https://media.brandalley.com/encards/3/170608_verychic_v6.jpg","Passez l'été en 5* face à la mer", format.parse("June 26, 2017"), 25);
        concours.getEvents().add(concoursEvent1);
        Event stagesEvent1 = new Event("Surf stage", stages, "https://media.brandalley.com/2017/06/06/pipe/27803/13_image.jpg","Apprenez à surfer avec nos partenaires", format.parse("June 26, 2017"), 25);
        stages.getEvents().add(stagesEvent1);

        save(concours);
        save(stages);
        save(jeux);
    }




}
