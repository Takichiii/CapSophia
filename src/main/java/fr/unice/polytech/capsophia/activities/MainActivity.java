package fr.unice.polytech.capsophia.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.adapters.CustomAdapter;
import fr.unice.polytech.capsophia.fragments.EventsFragment;
import fr.unice.polytech.capsophia.fragments.InfosFragment;
import fr.unice.polytech.capsophia.fragments.ProductsFragment;
import fr.unice.polytech.capsophia.fragments.CategoriesFragment;
import fr.unice.polytech.capsophia.models.DrawerItem;
import fr.unice.polytech.capsophia.models.Event;
import fr.unice.polytech.capsophia.models.Product;
import fr.unice.polytech.capsophia.models.Shop;
import fr.unice.polytech.capsophia.util.RealmDBHelper;
import io.realm.RealmResults;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    String[]titles = {"Infos", "Boutiques", "Evènements", "Mes Evènements", "Mes Produits"};
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar topToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(Manifest.permission.READ_CALENDAR);
        checkPermission(Manifest.permission.WRITE_CALENDAR);
        setContentView(R.layout.activity_main);

        RealmDBHelper instance = RealmDBHelper.getInstance(this);

        RealmResults<Shop> shops = instance.find(Shop.class);

        //if (shops.isEmpty())
            try {
                instance.generateData();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        mTitle = mDrawerTitle = getTitle();

        topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        topToolBar.setLogo(R.drawable.lolo);
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        List<DrawerItem> listViewItems = new ArrayList<DrawerItem>();
        listViewItems.add(new DrawerItem("Infos", R.drawable.imageone));
        listViewItems.add(new DrawerItem("Boutiques", R.drawable.imagetwo));
        listViewItems.add(new DrawerItem("Evènements", R.drawable.imagethree));
        listViewItems.add(new DrawerItem("Mes Evènements", R.drawable.imagefour));
        listViewItems.add(new DrawerItem("Mes Produits", R.drawable.imagefive));


        mDrawerList.setAdapter(new CustomAdapter(this, listViewItems));

        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerLƒistener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // make Toast when click
                selectItemFragment(position);
            }
        }
        );
        selectItemFragment(1);
    }

    private void selectItemFragment(int position){

        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position) {
            case 0:
                fragment = new InfosFragment();
                break;
            default:
            case 1:
                fragment = CategoriesFragment.newInstance(false);
                break;
            case 2:
                fragment = CategoriesFragment.newInstance(true);
                break;
            case 3:
                RealmResults<Event> events = RealmDBHelper.getInstance(this).findSpecific(Event.class, "saved", true);
                fragment = EventsFragment.newInstance(events);
                break;
            case 4:
                RealmResults<Product> products = RealmDBHelper.getInstance(this).findSpecific(Product.class, "favorited", true);
                fragment = ProductsFragment.newInstance(products);
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(titles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void checkPermission(String permission){
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        100);
            }
        }
    }
}
