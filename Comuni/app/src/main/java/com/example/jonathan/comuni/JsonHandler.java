package com.example.jonathan.comuni;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Casa on 23-12-2015.
 */
public class JsonHandler {

    public String[] getEventos(String eventos) {
        try {
            JSONArray ja = new JSONArray(eventos);
            String[] result = new String[ja.length()];
            String evento;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                evento = " " + row.getString("titulo");
                result[i] = evento;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }
}
