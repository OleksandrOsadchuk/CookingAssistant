package com.oleksandr.cookingassistant;

/**
 * Created by Oleksandr on 16-10-09.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CseJsonParser {

    public static List parseJson(String jsonString) {
        List<Recipe> list = new ArrayList<Recipe>();
        try {
            JSONObject jo = new JSONObject(jsonString);
            JSONArray ja = jo.getJSONArray("items");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject j = (JSONObject) ja.get(i);

                Recipe r = new Recipe();
                r.setTitle(j.getString("title"));
                r.setDescription(j.getString("snippet"));
                r.setLink(j.getString("link"));
                r.setPhoto(j.getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("src"));

                r.setBitmap(imageLoader(r.getPhoto()));

                list.add(r);
            }

        } catch (Exception e) {e.printStackTrace();}
        return list;
    }

    private static Bitmap imageLoader(String url){
        Bitmap b = null;
        try {
            InputStream in = (InputStream) new URL(url).getContent();
            b = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }




}
