package com.example.apii.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Category {

    String category;

    //Parcel requires empty constructor
    public Category(){

    }

    public Category(String catStr) {
        category = catStr;
    }

    public static List<Category> fromJsonArray(JSONArray categoryJSONArray){
        List<Category> categories = new ArrayList<>();
        for(int i=0;i<categoryJSONArray.length();i++){
            try{
                categories.add(new Category(categoryJSONArray.getString(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return categories;
    }

    public String getCategory() {
        return category;
    }

}
