package com.example.jonathan.comuni;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Casa on 16-01-2016.
 */
public class httpGet_usuario extends AsyncTask<String, Void, String> {

    private Context context;

    /**
     * Constructor
     */
    public httpGet_usuario(Context context) {
        this.context = context;
    }// HttpGet(Context context)

    /**
     * Método que realiza la petición al servidor
     */
    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (MalformedURLException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (ProtocolException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// doInBackground(String... urls)


    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent("httpData_usuario").putExtra("data_usuario", result);
        context.sendBroadcast(intent);
    }// onPostExecute(String result)
}