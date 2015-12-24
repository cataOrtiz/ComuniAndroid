package com.example.jonathan.comuni;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Casa on 24-12-2015.
 */
public class systemUtilities {


    Context context;

    /**
     * Constructor
     */
    public systemUtilities(Context context) {
        this.context = context;
    }// SystemUtilities(Context context)

    /**
     * Método que consulta el estado de la conexión a Internet
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

}
