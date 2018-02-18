package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Log.d("parseSandwichJson", json);

        /* Creates a new JSONObject with name/value mappings from the json string */
        JSONObject sandwichData = new JSONObject(json);

        /* Creates a new JSONObject from sandwichData for the name: name*/
        JSONObject sandwichName = sandwichData.getJSONObject("name");

        /* ArrayList to hold the alsoKnownAs Strings*/
        List<String> listAlsoKnownAs = new ArrayList<String>();
        JSONArray arrAlsoKnownAs = sandwichName.getJSONArray("alsoKnownAs");
        for (int i=0; i<arrAlsoKnownAs.length(); i++) {
            listAlsoKnownAs.add(arrAlsoKnownAs.getString(i));
        }

        /* ArrayList to hold the ingredients Strings*/
        List<String> listIngredients = new ArrayList<String>();
        JSONArray arrIngredients = sandwichData.getJSONArray("ingredients");
        for (int i=0; i<arrIngredients.length(); i++) {
            listIngredients.add(arrIngredients.getString(i));
        }

        /* Build the sandwich object */
        Sandwich sandwich = new Sandwich(
                sandwichName.getString("mainName"),
                listAlsoKnownAs,
                sandwichData.getString("placeOfOrigin"),
                sandwichData.getString("description"),
                sandwichData.getString("image"),
                listIngredients
        );

        return sandwich;
    }
}
