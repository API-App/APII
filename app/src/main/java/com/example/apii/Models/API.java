package com.example.apii.Models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class API {

    String title;
    String description;
    String auth;
    Boolean https;
    String cors;
    String link;
    String category;

    //Parcel requires empty constructor
    public API(){

    }

    public API(JSONObject jsonObject) throws JSONException{
        title = jsonObject.getString("API");
        description = jsonObject.getString("Description");
        auth = jsonObject.getString("Auth");
        https = jsonObject.getBoolean("HTTPS");
        cors = jsonObject.getString("Cors");
        link = jsonObject.getString("Link");
        category = jsonObject.getString("Category");
    }

    public static List<API> fromJsonArray(JSONArray apiJSONArray){
        List<API> apis = new ArrayList<>();
        for(int i=0;i<apiJSONArray.length();i++){
            try{
                apis.add(new API(apiJSONArray.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return apis;
    }

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getAuth() {return auth;}
    public Boolean getHttps() {return https;}
    public String getCors() {return cors;}
    public String getLink() {return link;}
    public String getCategory() {return category;}

}
