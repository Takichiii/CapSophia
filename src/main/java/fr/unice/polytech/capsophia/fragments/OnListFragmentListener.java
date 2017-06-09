package fr.unice.polytech.capsophia.fragments;

import io.realm.RealmModel;

/**
 * Created by eamosse on 04/06/2017.
 */

    public interface OnListFragmentListener<E extends RealmModel>{
        void onListFragmentInteraction(E item);
    }
